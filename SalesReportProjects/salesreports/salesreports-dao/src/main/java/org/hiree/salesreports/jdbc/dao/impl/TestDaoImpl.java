package org.hiree.salesreports.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hiree.salesreports.jdbc.dao.interfaces.ITestDao;
import org.hiree.salesreports.jdbc.dao.sqlbuilder.SqlBuilder;
import org.hiree.salesreports.rest.dto.TestTableDTO;
import org.hiree.salesreports.rest.dto.dialog.DialogColumnRowInfoDTO;
import org.hiree.salesreports.rest.dto.dialog.RowColumnInfoDTO;
import org.hiree.salesreports.util.enums.TableMappingEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("testDao")
public class TestDaoImpl implements ITestDao {

	/**
		 * 
		 */
	private static final long serialVersionUID = 8488639269048035562L;

	Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;

	public List<TestTableDTO> getAllTestData() {
		logger.debug("TestDaoImpl : getAllTestData : BEGIN");

		List<TestTableDTO> query = jdbcTemplate.query("select * from test", new TestActorMapper());
		logger.debug("TestDaoImpl : getAllTestData : END");
		return query;
	}
	
	public TestTableDTO getTestData(String id){
		TestTableDTO query = (TestTableDTO) jdbcTemplate.queryForObject("select * from test where eno=?",new Object[] {id}, new TestActorMapper());
		return query;
	}
	
	public List<DialogColumnRowInfoDTO> getDialogColumnRowInfoList(String tableName) {
		logger.debug("TestDaoImpl : getDialogColumnRowInfoList : BEGIN");
    	List<DialogColumnRowInfoDTO> query = jdbcTemplate.query("select * from test", new DialogColumnRowInfo(tableName));
		logger.debug("TestDaoImpl : getDialogColumnRowInfoList : END");
		return query;
	}
	
	public DialogColumnRowInfoDTO getDialogColumnRowInfo(String id){
		DialogColumnRowInfoDTO query = (DialogColumnRowInfoDTO) jdbcTemplate.queryForObject("select * from test where eno=?",new Object[] {id}, new DialogColumnRowInfo("TEST"));
		return query;
	}
	
	public List<String> getTableColumnName(String tableName){
		TableMetaDataContext tableMetadataContext = new TableMetaDataContext();
	    tableMetadataContext.setTableName(tableName);
	    tableMetadataContext.processMetaData(dataSource, Collections.<String>emptyList(), new String[0]);
	    return tableMetadataContext.getTableColumns();
	}
	
	
	@Override
	public void updateGenericData(String tableName,Map<String, Object> parameters,Object primaryKey){
		
		SqlBuilder sqlBuilder = new SqlBuilder(tableName);
		String query = sqlBuilder.update(parameters).where(SqlBuilder.equal(TableMappingEnum.getRowId(tableName), primaryKey)).toSql();
		NamedParameterJdbcTemplate template =   new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		template.update(query, sqlBuilder.getQueryParameterValues());
		
		
	}
	


}


final class DialogColumnRowInfo implements RowMapper<DialogColumnRowInfoDTO>{
	String tableName = null ;
	public DialogColumnRowInfo(String tableName) {
		this.tableName=tableName;
	}

	public DialogColumnRowInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DialogColumnRowInfoDTO dialogColumnRowInfoDTO = new DialogColumnRowInfoDTO();
		dialogColumnRowInfoDTO.setTableName(tableName);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnCount = rsmd.getColumnCount();
        for(int i = 1 ; i <= columnCount ; i++){
        	 RowColumnInfoDTO rowColumnInfoDTO = new RowColumnInfoDTO();
        	 String columName= rsmd.getColumnName(i);
        	 rowColumnInfoDTO.setFieldName(columName);
        	 if (rs.getObject(columName) != null) {
        		 rowColumnInfoDTO.setFieldValue(String.valueOf(rs.getObject(columName)));
               
             }
        	
        	 rowColumnInfoDTO.setOrder(i);
        	 //rowColumnInfoDTO.setType(rsmd.getColumnTypeName(i));
        	 rowColumnInfoDTO.setType("textbox");
        	 dialogColumnRowInfoDTO.getRowcoulmn().add(rowColumnInfoDTO);
        	 //dialogColumnRowInfoDTO.setTableName(rsmd.getTableName(i));
        }
	
		return dialogColumnRowInfoDTO;
	}

}
final class TestActorMapper implements RowMapper<TestTableDTO> {

	public TestActorMapper() {

	}

	public TestTableDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TestTableDTO tstObject = new TestTableDTO();
		tstObject.setEmpNumber(rs.getInt("eno"));
		tstObject.setName(rs.getString("name"));
		tstObject.setDept(rs.getString("dept"));

		return tstObject;
	}

}
