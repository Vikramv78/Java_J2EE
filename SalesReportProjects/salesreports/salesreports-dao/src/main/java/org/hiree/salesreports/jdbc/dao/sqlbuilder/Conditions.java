package org.hiree.salesreports.jdbc.dao.sqlbuilder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class Conditions {

	
	public static enum Operation { AND, OR }
	
	private Operation operation;
	private String sql;
	//private List<Object> values;
	Map<String, Object> values = new HashMap<String, Object>();
	MapSqlParameterSource paramSource;
	public Conditions(Operation operation, Condition... conditions){
		
		this.operation = operation;
		this.values = new HashMap<String, Object>();

		StringBuilder builder = new StringBuilder();
		String separator = " AND ";
		if (operation.equals(Operation.OR)){
			separator = " OR ";
		}
		boolean flag = false;
		for (Condition condition: conditions){
			if (flag){
				builder.append(separator);
			}
			flag = true;
			builder.append(condition.getClause());
			if (condition.getValue() != null){
				values.put(condition.getClause(),condition.getValue());
				paramSource.addValue(condition.getClause(),condition.getValue());
			}
		}
		
		this.sql = builder.toString();
		
	}
	
	public Conditions(Operation operation, Conditions... conditions){
		
		this.operation = operation;
		this.values = new HashMap<String, Object>();
		this.paramSource  = new MapSqlParameterSource();

		StringBuilder builder = new StringBuilder();
		String separator = " AND ";
		if (operation.equals(Operation.OR)){
			separator = " OR ";
		}
		boolean flag = false;
		
		for (Conditions c: conditions){
			if (flag) {
				builder.append(separator);
			}
			flag = true;
			builder.append(" ( " + c.getSql() + " ) ");
			this.values.putAll(c.getValues());
			this.paramSource.addValues(paramSource.getValues());
			
		}
		
		this.sql = builder.toString();
		
	}
	
	public String getSql(){
		return sql;
	}
	
	public Map<String, Object> getValues(){
		return values;
	}
	public MapSqlParameterSource getParamSource(){
		return paramSource;
	}
	
	public Operation getOperation(){
		return operation;
}
}
