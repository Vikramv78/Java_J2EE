package org.hiree.salesreports.rest.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("userSession")
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

	UserDTO userDto;
	public UserSession(UserDTO userDto){
		this.userDto=userDto;
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	
	
}
