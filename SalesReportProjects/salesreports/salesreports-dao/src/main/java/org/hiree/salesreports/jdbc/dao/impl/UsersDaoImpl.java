package org.hiree.salesreports.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.hiree.salesreports.jdbc.dao.interfaces.IUsersDao;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.hiree.salesreports.util.constant.QueryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository("usersDao")
public class UsersDaoImpl implements IUsersDao{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3825763612477735484L;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);
	@Override
	public UserDTO authorizeUser(String userName, String password) throws Exception {
		try{
			logger.debug("UsersDaoImpl : authorizeUser : BEGIN");
			UserDTO query = jdbcTemplate.queryForObject( QueryConstants.USERS.SELECT_USERS_BY_USER_NAME_AND_PASSWORD,new Object[] {userName, password},new UsersMapper());
			logger.debug("UsersDaoImpl : authorizeUser : END");
			return query;
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}
}

final class UsersMapper implements RowMapper<UserDTO> {
	  
    public UsersMapper(){
    	
    }
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UserDTO userVO = new UserDTO();
    	userVO.setUserID(rs.getLong("USERS_ID"));
    	userVO.setFname(rs.getString("FIRST_NAME"));
    	userVO.setLname(rs.getString("LAST_NAME"));
    	userVO.setEmail(rs.getString("EMAIL_ID"));
    	
    		
	return userVO;
}


}
