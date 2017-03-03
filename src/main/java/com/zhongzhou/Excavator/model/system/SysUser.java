package com.zhongzhou.Excavator.model.system;

import java.io.Serializable;

public class SysUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2434338038104655107L;

	private String id;
	
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
