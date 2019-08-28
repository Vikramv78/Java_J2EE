package org.hiree.salesreports.rest.config;

import org.hiree.salesreports.rest.config.interfaces.IContext;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import org.springframework.util.Assert;

@Component("iContext")
public class IContextImpl implements IContext, InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * IBIS Context holder
	 */
	private static final ThreadLocal<IContext> cntxts = new ThreadLocal<IContext>();
	
	/**
	 * Whether to load the references on startup. Default is <b>true</b>
	 */
	public static boolean loadReferencesOnStartup = true;
	
	/**
	 * User holder
	 */
	private final ThreadLocal<UserDTO> users = new ThreadLocal<UserDTO>();
	
	
	@Override
	public boolean isThreadUser() {
		// TODO Auto-generated method stub
		return users.get() != null;
	}

	protected UserDTO getThreadUserInternal() {
		UserDTO user = users.get();
		if (user==null) {
			Assert.notNull(user, "User is not set for current thread ["+Thread.currentThread().getName()+"]");
		}
		return user;
	}
	@Override
	public UserDTO getThreadUser() {
		// TODO Auto-generated method stub
		return getThreadUserInternal();
	}

	@Override
	public void setThreadUser(UserDTO user) {
		users.set(user);
		cntxts.set(this);
		
	}

	@Override
	public void clearThreadUser() {
		users.set(null);
		cntxts.set(null);
	}

}
