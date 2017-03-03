package com.zhongzhou.Excavator.model.system;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Component;

@Component(value="userContext")  
public class UserContext implements Serializable {

	private static final long serialVersionUID = 2390379117346789188L;
	
	public static final String sessionContextKey = "system.userContext";
	
	private SysUser sysUser;
	private List<String> userPermissions;
	private int failedLoginTime = 0;
	
	public List<String> getUserPermissions() {
		return userPermissions;
	}
	public void setUserPermissions(List<String> userPermissions) {
		this.userPermissions = userPermissions;
	}
	
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public int getFailedLoginTime() {
		return failedLoginTime;
	}
	public void setFailedLoginTime(int failedLoginTime) {
		this.failedLoginTime = failedLoginTime;
	}
}
