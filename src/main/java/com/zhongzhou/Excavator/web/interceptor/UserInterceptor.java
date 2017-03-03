package com.zhongzhou.Excavator.web.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhongzhou.Excavator.service.system.UserLoginService;
import com.zhongzhou.Excavator.springsupport.injectlist.ServiceNameList;

public class UserInterceptor implements HandlerInterceptor{
	
	private Logger log = Logger.getLogger(UserInterceptor.class); 
	
	@Resource( name = ServiceNameList.SYSTEM_USER_LOGIN_Service )
	UserLoginService userLoginService;

	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object arg2, 
								Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, 
						   HttpServletResponse response,
						   Object arg2, 
						   ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response,
							 Object arg2) throws Exception {
		
		boolean result = userLoginService.checkIfUserLogined();
		
		return result;
	}

}
