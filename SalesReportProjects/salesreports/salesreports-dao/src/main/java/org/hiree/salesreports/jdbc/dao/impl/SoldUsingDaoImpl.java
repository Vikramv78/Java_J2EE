package org.hiree.salesreports.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hiree.salesreports.jdbc.dao.interfaces.ISoldUsingDao;
import org.hiree.salesreports.rest.dto.SoldUsingDTO;
import org.hiree.salesreports.util.constant.QueryConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("soldUsingDao")
public class SoldUsingDaoImpl implements ISoldUsingDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5761471939059016037L;
	Logger logger = LoggerFactory.getLogger(SoldUsingDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SoldUsingDTO> getAllSoldUsingByUserID(long userID) throws Exception {
		logger.debug("SoldUsingDaoImpl : getAllSoldUsingByUserID : BEGIN");
		List<SoldUsingDTO> query = jdbcTemplate.query( QueryConstants.SOLD_USING.SELECT_SOLD_USING_USER_ID,new Object[] {new Long(userID)},new SoldUsingMapper());
		logger.debug("SoldUsingDaoImpl : getAllSoldUsingByUserID : END");
		return query;
	}
	
	@Override
	public List<SoldUsingDTO> getAllSoldUsingAllUsers() throws Exception {
		logger.debug("SoldUsingDaoImpl : getAllSoldUsingAllUsers : BEGIN");
		List<SoldUsingDTO> query = jdbcTemplate.query( QueryConstants.SOLD_USING.SELECT_SOLD_USING_ALL_USERD,new SoldUsingALlUsersMapper());
		logger.debug("SoldUsingDaoImpl : getAllSoldUsingAllUsers : END");
		return query;
	}
	
	@Override
	public void saveSoldUsing(SoldUsingDTO SoldUsingDTO,String userName) throws Exception{
		String query = QueryConstants.SOLD_USING.ADD_SOLD_USING;
		jdbcTemplate.update(query, new Object[] {new Long(SoldUsingDTO.getUserId()),SoldUsingDTO.getSoldName().toUpperCase(),userName});
	
	}
	@Override
	public  int getSoldUsingByUserAndNameCount(SoldUsingDTO SoldUsingDTO) throws Exception {
		
		try {
		String query = QueryConstants.SOLD_USING.CHECK_SOLD_USING_USER_ID_SOLD_NAME_EXIST;
		return  jdbcTemplate.queryForObject(query,new Object[] {new Long(SoldUsingDTO.getUserId()),SoldUsingDTO.getSoldName()},Integer.class);
		}catch(final EmptyResultDataAccessException e){
			return 0;
		}			
	}
			
	@Override
	public List<Long> getSoldUsingByNames(String names) throws Exception {
		logger.debug("SoldUsingDaoImpl : getSoldUsingByNames : BEGIN");
		List<String> items = Arrays.asList(names.split("\\s*,\\s*"));
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("SOLD_USING_NAME", items);
		NamedParameterJdbcTemplate template =   new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		List<Long> rspFrmtListMap = template.queryForList(QueryConstants.SOLD_USING.SELECT_SOLD_USING_BY_NAME, paramSource,Long.class);
		logger.debug("SoldUsingDaoImpl : getSoldUsingByNames : END");
		return rspFrmtListMap;
	}
}

final class SoldUsingMapper implements RowMapper<SoldUsingDTO> {
	  
    public SoldUsingMapper(){
    	
    }
    public SoldUsingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SoldUsingDTO SoldUsingDTO = new SoldUsingDTO();
    	SoldUsingDTO.setSoldUsingId(rs.getLong("SOLD_USING_ID"));
    	SoldUsingDTO.setSoldName(rs.getString("SOLD_NAME"));
    	
    		
	return SoldUsingDTO;
}

   
}
final class SoldUsingALlUsersMapper implements RowMapper<SoldUsingDTO> {
	  
    public SoldUsingALlUsersMapper(){
    	
    }
    public SoldUsingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	SoldUsingDTO SoldUsingDTO = new SoldUsingDTO();
    	
    	SoldUsingDTO.setSoldName(rs.getString("SOLD_NAME"));
    	
    		
	return SoldUsingDTO;
}
}


