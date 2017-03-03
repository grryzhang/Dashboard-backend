package com.zhongzhou.Excavator.service.impl.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.zhongzhou.Excavator.springsupport.SpringContextHolder;
import com.zhongzhou.Excavator.springsupport.injectlist.ServiceNameList;
import com.zhongzhou.Excavator.model.system.SysUser;
import com.zhongzhou.Excavator.model.system.UserContext;
import com.zhongzhou.Excavator.service.system.UserLoginService;
import com.zhongzhou.common.Exception.UserPermissionException;

@Service( ServiceNameList.SYSTEM_USER_LOGIN_Service )
public class UserLoginServiceImpl implements UserLoginService{

	private Logger log = Logger.getLogger(UserLoginServiceImpl.class);  

	@Autowired
	SpringContextHolder springContextHolder;
	@Autowired
	private  HttpServletRequest request;

	@Override
	public boolean checkIfUserLogined() throws UserPermissionException {
		
		/*
		UserContext userContext = this.getUserContext();
		
		if( userContext == null ){ return false; }
		if( userContext.getSysUser() == null ){ return false; }
		if( userContext.getSysUser().getUserName() == null ){ return false; }
		
		*/
		
		return true;
	}

	@Override
	public boolean userLogin( String userName , String password ) throws UserPermissionException {
		
		UserContext userContext = null;
		
		/*
		try{
			
			SysUser sysUser = new SysUser();
			String systemUserName = "system";
			String systemPassword = "zzg";
			sysUser.setUserName( systemUserName );
			
			if( systemUserName.equals(userName) && systemPassword.equals(password) ){
				
				userContext = this.getUserContext();
				userContext.setSysUser( sysUser );
				
				//session.invalidate();
				
				userContext.setFailedLoginTime(0);
				
				this.setUserContext(userContext);
					
				return true;
			}
			
		}catch(Exception e){
			UserPermissionException ue = new UserPermissionException(e);
			log.error( "CheckUserPermission Exception:" + e);
			throw ue;
		}
		*/
		
		return true;
	}

	public void logOut() throws UserPermissionException{
		
		request.getSession().removeAttribute(UserContext.sessionContextKey);
		request.getSession().invalidate();
	}
	
	private UserContext getUserContext(){
		
		try{
			UserContext userContext = (UserContext)request.getSession().getAttribute(UserContext.sessionContextKey); 
			
			if( userContext == null ){
				userContext = new UserContext();
				request.getSession().setAttribute( UserContext.sessionContextKey, userContext );
			}
			
			return userContext;
		}catch( Exception e ){
			return null;
		}
		
	}
	
	public void setUserContext( UserContext userContext ){
		
		request.getSession().setAttribute( UserContext.sessionContextKey, userContext );
	}
}
