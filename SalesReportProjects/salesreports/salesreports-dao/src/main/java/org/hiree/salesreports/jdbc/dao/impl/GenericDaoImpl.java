package org.hiree.salesreports.jdbc.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hiree.salesreports.jdbc.dao.interfaces.IGenericDao;
import org.hiree.salesreports.jdbc.dao.sqlbuilder.SqlBuilder;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.util.enums.TableMappingEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("genericDao")
public class GenericDaoImpl implements IGenericDao{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4038413811626846076L;

	Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<DialogColumnRowInfoDTO> getGenericDataAllRowsInfoFromTable(String tableName) {
		logger.debug("GenericDaoImpl : selectAllRowsFromTable : BEGIN");
		SqlBuilder sqlBuilder = new SqlBuilder(tableName);
		String query = sqlBuilder.select("*").toSql();
    	//List<DialogColumnRowInfoDTO> result = jdbcTemplate.query("select * from test", new DialogColumnRowInfo());
		List<DialogColumnRowInfoDTO> result = jdbcTemplate.query(query, new DialogColumnRowInfo(tableName));
		logger.debug("GenericDaoImpl : selectAllRowsFromTable : END");
		return result;
	}
	
	@Override
	public DialogColumnRowInfoDTO getGenericDataSelectedRowInfo(String tableName,Object primaryKeyValue){
		logger.debug("GenericDaoImpl : getSelectedRowInfo : BEGIN");
		SqlBuilder sqlBuilder = new SqlBuilder(tableName);
		String query = sqlBuilder.select("*").where(SqlBuilder.equal(TableMappingEnum.getRowId(tableName), primaryKeyValue)).toSql();
		NamedParameterJdbcTemplate template =   new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		DialogColumnRowInfoDTO result =(DialogColumnRowInfoDTO) template.queryForObject(query, sqlBuilder.getQueryParameterValues(), new DialogColumnRowInfo(tableName));
		logger.debug("GenericDaoImpl : getSelectedRowInfo : END");
		return result;
	}
	
	@Override
	public void updateGenericDataSelectedRowInfo(String tableName,Map<String, Object> parameters,Object primaryKey){
		logger.debug("GenericDaoImpl : updateGenericDataSelectedRowInfo : BEGIN");
		SqlBuilder sqlBuilder = new SqlBuilder(tableName);
		String query = sqlBuilder.update(parameters).where(SqlBuilder.equal(TableMappingEnum.getRowId(tableName), primaryKey)).toSql();
		NamedParameterJdbcTemplate template =   new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		template.update(query, sqlBuilder.getQueryParameterValues());
		logger.debug("GenericDaoImpl : updateGenericDataSelectedRowInfo : END");
		
	}
	
	public void insertGenericDataSelectedRowInfo(String tableName,Map<String, Object> parameters){
		logger.debug("GenericDaoImpl : updateGenericDataSelectedRowInfo : BEGIN");
		SqlBuilder sqlBuilder = new SqlBuilder(tableName);
		/*Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ENO", "");
		parameters.put("NAME", "VIKRAM");
		parameters.put("DEPT", "CSE");
		SqlBuilder sqlBuilder = new SqlBuilder("TEST");
		String sql = sqlBuilder.insert(parameters,"TEST").toSql();*/
		String query = sqlBuilder.insert(parameters,tableName).toSql();
		NamedParameterJdbcTemplate template =   new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		template.update(query, sqlBuilder.getQueryParameterValues());
		logger.debug("GenericDaoImpl : updateGenericDataSelectedRowInfo : END");
		
	}
	@Override
	public List<String> getTableColumnName(String tableName){
		TableMetaDataContext tableMetadataContext = new TableMetaDataContext();
	    tableMetadataContext.setTableName(tableName);
	    tableMetadataContext.processMetaData(dataSource, Collections.<String>emptyList(), new String[0]);
	    return tableMetadataContext.getTableColumns();
	}
	
}


