package com.zhongzhou.Excavator.service.system;

import com.zhongzhou.common.Exception.UserPermissionException;

public interface UserLoginService {

	public boolean userLogin( String userName , String password ) throws UserPermissionException;

	public void logOut() throws UserPermissionException;
	
	public boolean checkIfUserLogined() throws UserPermissionException;
}
