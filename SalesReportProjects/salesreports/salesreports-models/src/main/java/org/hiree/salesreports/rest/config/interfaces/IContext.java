package org.hiree.salesreports.rest.config.interfaces;

import org.hiree.salesreports.rest.dto.UserDTO;

public interface IContext {
	/**
	 * Returns whether current thread user is set or not
	 * 
	 * @return <b>User</>
	 */
	boolean isThreadUser();

	/**
	 * Returns current thread user, usually set in either filter or servlet
	 * 
	 * @return <b>User</>
	 */
	UserDTO getThreadUser();
	
	/**
	 * Sets user into thread local repository
	 * 
	 * @param user
	 */
	void setThreadUser(UserDTO user);
	
	/**
	 * Clears thread user
	 */
	void clearThreadUser();
	
}
