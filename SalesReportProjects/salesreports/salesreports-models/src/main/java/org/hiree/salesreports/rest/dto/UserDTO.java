package org.hiree.salesreports.rest.dto;

import org.hiree.salesreports.rest.dto.interfaces.Payload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;
@Component
//@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDTO implements Payload{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 6736576264400499138L;
	private long userID ;     
	  private String login; 
	  private String password; 
	  private String email; 
	  private String fname; 
	  private String lname;
	  private String fullUsername;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFullUsername() {
		return fullUsername;
	}
	public void setFullUsername(String fullUsername) {
		this.fullUsername = fullUsername;
	} 
	 
	
	

}
