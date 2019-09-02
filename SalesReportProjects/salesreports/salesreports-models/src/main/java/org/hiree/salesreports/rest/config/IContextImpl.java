package org.hiree.salesreports.rest.config;

import org.hiree.salesreports.rest.config.interfaces.IContext;
import org.hiree.salesreports.rest.dto.UserDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

//@Component("iContext")
public class IContextImpl implements IContext, InitializingBean {

		
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
	
	

	/* ---------------------------------------------------
	   IBIS Context section
	   --------------------------------------------------- */
	
	/**
	 * Returns the IBIS Context instance that is set on the current thread. This
	 * is useful to retrieve IBIS Context object in a static way, which resolves to the
	 * right IBIS Context instance, irrespective on the number of IBIS Context instances
	 * that are instantiated (even across multiple Spring Application Contexts)<br>
	 * <br>
	 * Internally, it piggybacks on the setThreadUser() method call that associates user
	 * to the current thread. While it does that, it also associates the IBIS Context
	 * instance to the thread
	 * 
	 * @return <b>IbisContext</b> IBIS Context instance
	 */
	public static IContext getIContextForThread() {
		return cntxts.get();
	}
	
	/**
	 * Returns user set on the current thread. This is useful to retrieve thread user in a
	 * static way
	 * 
	 * @return <b>User</b> User set on thread
	 * @see #getIbisContextForThread()
	 */
	public static UserDTO getUserForThread() {
		return getIContextForThread().getThreadUser();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
