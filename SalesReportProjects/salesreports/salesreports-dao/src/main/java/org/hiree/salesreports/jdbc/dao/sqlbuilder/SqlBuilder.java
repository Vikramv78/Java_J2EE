package org.hiree.salesreports.jdbc.dao.sqlbuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

import org.hiree.salesreports.util.enums.TableMappingEnum;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


/**
 * @author woemler
 * 
 * Example usage:
 * 
 * SqlBuilder sqlBuilder = new SqlBuilder(tableDescription);
 * String sql = sqlBuilder.select("*")
 *   .from("users")
 *   .where(
 *     and(
 *       eq("category", "active"),
 *       notEq("name", "Joe")
 *     )
 *   )
 *   .groupBy("name", "address")
 *   .orderBy(
 *     new Sort(
 *       new Sort.Order(Sort.Direction.ASC, "name"), 
 *       new Sort.Order(Sort.Direction.DESC, "address")
 *     )
 *   )
 *   .limit(10, 50)
 *   .toSql();
 *   
 *  Should produce the string:
 *  
 *   SELECT * FROM users 
 *   WHERE category = ? AND name != ? 
 *   GROUP BY name, address
 *   ORDER BY name ASC, address DESC
 *   LIMIT 10, 50
 *   
 *  List<Object> parameters = sqlBuilder.getQueryParameterValues;
 * 
 *  Should return a list with { 'active', 'Joe' }
 * 
 */
public class SqlBuilder {
	private String tableName;
	private String selectClause = "*";
	private String fromClause = "";
	private String whereClause = "";
	private String groupByClause = "";
	private String orderByClause = "";
	private String limitClause = "";
	private String insertClause = "";
	private String updateClause = "";

	private enum Mode {
		SELECT, INSERT, UPDATE, DELETE
	};

	private Mode mode = Mode.SELECT;

	private List<String> idColumns;
	private LinkedHashMap<String, SortOrder> sorts;
	//private List<Object> queryParameterValues;
	Map<String, Object> queryParameterValues = new HashMap<String, Object>();
	MapSqlParameterSource paramSource ;
	public SqlBuilder() {
	}
	public SqlBuilder(String tableName){
		this.tableName = tableName;
		this.fromClause = tableName;
		this.queryParameterValues = new HashMap<String, Object>();
		this.paramSource =new MapSqlParameterSource();;
     }

	//// SELECT

	public String getSelectClause() {
		return "SELECT " + selectClause;
	}

	public void setSelectClause(String selectClause) {
		this.selectClause = selectClause;
	}

	public SqlBuilder select(String clause) {
		this.selectClause = clause;
		return this;
	}

	public SqlBuilder update(Map<String, Object> parameters) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean flag = false;
		for (Map.Entry param : parameters.entrySet()) {
			if (flag) {
				stringBuilder.append(", ");
			}
			flag = true;
			stringBuilder.append(param.getKey() + " = :"+param.getKey());
			queryParameterValues.put(param.getKey().toString(),param.getValue());
			paramSource.addValue(param.getKey().toString(),param.getValue());
		}
		updateClause = tableName + " SET " + stringBuilder.toString();
		mode = Mode.UPDATE;
		return this;
	}

	public String getUpdateClause() {
		return "UPDATE " + updateClause;
	}

	public String getWhereClause() {
		return whereClause.equals("") ? whereClause : " WHERE " + whereClause;
	}

	public SqlBuilder insert(Map<String, Object> parameters,String tableName) {
		StringBuilder columnString = new StringBuilder(" (");
		StringBuilder valueString = new StringBuilder(" VALUES (");
		boolean flag = false;
		for (Map.Entry param : parameters.entrySet()) {
			if (flag) {
				columnString.append(",");
				valueString.append(",");
			}
			flag = true;
			columnString.append(param.getKey());
			//valueString.append("?");
			if(TableMappingEnum.getRowId(tableName).equals(param.getKey())){
				valueString.append(TableMappingEnum.getSequenceName(tableName));
			}else{
				valueString.append(":"+param.getKey());
				paramSource.addValue(param.getKey().toString(),param.getValue());
			}
			
			
			queryParameterValues.put(param.getKey().toString(),param.getValue());
			
		}
		columnString.append(") ");
		valueString.append(") ");
		insertClause = tableName + columnString.toString() + valueString.toString();
		mode = Mode.INSERT;
		return this;
	}

	public String getInsertClause() {
		return "INSERT INTO " + insertClause;
	}

	//// GROUP BY

	public String getGroupByClause() {
		return groupByClause.equals("") ? groupByClause : " GROUP BY " + groupByClause;
	}

	public void setGroupByClause(String groupByClause) {
		this.groupByClause = groupByClause;
	}

	public SqlBuilder groupBy(String... columns) {
		StringBuilder builder = new StringBuilder();
		boolean flag = false;
		for (String column : columns) {
			if (flag) {
				builder.append(", ");
			}
			flag = true;
			builder.append(column);
		}
		groupByClause = builder.toString();
		return this;
	}
	//// ORDER BY

	public String getOrderByClause() {
		return orderByClause.equals("") ? orderByClause : " ORDER BY " + orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	

	public SqlBuilder orderBy(String column) {
		orderByClause = column + " ASC";
		return this;
	}

	//// LIMIT

	public String getLimitClause() {
		return limitClause.equals("") ? limitClause : " LIMIT " + limitClause;
	}

	public void setLimitClause(String limitClause) {
		this.limitClause = limitClause;
	}

	public SqlBuilder limit(Integer offset, Integer count) {
		limitClause = offset.toString() + "," + count.toString();
		return this;
	}

	public SqlBuilder limit(Integer count) {
		limitClause = count.toString();
		return this;
	}

	//// FROM

	public String getFromClause() {
		return " FROM " + fromClause;
	}

	public void setFromClause(String fromClause) {
		this.fromClause = fromClause;
	}

	public SqlBuilder from(String clause) {
		this.fromClause = clause;
		return this;
	}


	public void setWhereClause(String whereClause){
		this.whereClause = whereClause;
	}
	
	public SqlBuilder where(Conditions conditions){
		whereClause = conditions.getSql();
		queryParameterValues.putAll(conditions.getValues());
		paramSource.addValues(conditions.getParamSource().getValues());
		return this;
	}
	
	public SqlBuilder where(Condition condition){
		whereClause = condition.getClause();
		queryParameterValues.put(condition.getColumn(),condition.getValue());
		paramSource.addValue(condition.getColumn(),condition.getValue());
		return this;
	}
	
	public static Conditions and(Condition... conditions){
		return new Conditions(Conditions.Operation.AND, conditions);
	}

	public static Conditions and(Conditions... conditions){
		return new Conditions(Conditions.Operation.AND, conditions);
	}
	
	public static Conditions or(Condition... conditions){
		return new Conditions(Conditions.Operation.OR, conditions);
	}

	public static Conditions or(Conditions... conditions){
		return new Conditions(Conditions.Operation.OR, conditions);
	}
	
	public static Condition equal(String column, Object value){
		return new Condition(column, value, Evaluation.EQUALS);
	}
	
	public static Condition notEqual(String column, Object value){
		return new Condition(column, value, Evaluation.NOT_EQUALS);
	}

	public static Condition in(String column, Object[] value){
		return new Condition(column, value, Evaluation.IN);
	}

	public static Condition notIn(String column, Object[] value){
		return new Condition(column, value, Evaluation.NOT_IN);
	}

	public static Condition isNull(String column){
		return new Condition(column, null, Evaluation.IS_NULL);
	}
	
	public static Condition notNull(String column){
		return new Condition(column, null, Evaluation.NOT_NULL);
}
	/*
	 * public SqlBuilder limit(Pageable pageable){ limitClause =
	 * String.valueOf(pageable.getOffset()) + "," +
	 * String.valueOf(pageable.getPageSize()); return this; }
	 */

	public String getDeleteClause() {
		return "DELETE ";
	}

	public String toSql() {
		String sql;
		switch (mode) {
		case SELECT:
			sql = getSelectClause() + getFromClause() + getWhereClause() + getGroupByClause() + getOrderByClause()
					+ getLimitClause();
			break;
		case INSERT:
			sql = getInsertClause();
			break;
		case UPDATE:
			sql = getUpdateClause() + getWhereClause();
			break;
		case DELETE:
			sql = getDeleteClause() + getFromClause() + getWhereClause();
			break;
		default:
			sql = getSelectClause() + getFromClause() + getWhereClause() + getGroupByClause() + getOrderByClause()
					+ getLimitClause();
		}
		System.out.println(sql);
		return sql;
	}
	public Map<String, Object> getQueryParameterValues(){
		return queryParameterValues;
    }
	public MapSqlParameterSource getParamSource(){
		return paramSource;
}
	public static void main(String args[]){
		
	/*	Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("NAME", "VIKRAM");
		parameters.put("DEPT", "CSE");
		SqlBuilder sqlBuilder = new SqlBuilder("TEST");
		String sql = sqlBuilder.update(parameters).where(equal("ENO", 1)).toSql();*/
		
		/*SqlBuilder sqlBuilder = new SqlBuilder("TEST");
		String sql = sqlBuilder.select("*").toSql();
		System.out.println("sql --->"+sql);*/
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ENO", "");
		parameters.put("NAME", "VIKRAM");
		parameters.put("DEPT", "CSE");
		SqlBuilder sqlBuilder = new SqlBuilder("TEST");
		String sql = sqlBuilder.insert(parameters,"TEST").toSql();
	}
}
