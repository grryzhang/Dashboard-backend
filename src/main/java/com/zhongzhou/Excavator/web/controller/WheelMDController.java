package com.zhongzhou.Excavator.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhongzhou.Excavator.model.dataIndex.item.wheel.Wheel;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.WheelSearchParameters;
import com.zhongzhou.Excavator.service.MD.WheelMDService;
import com.zhongzhou.common.model.web.JsonResponse;

@Controller
public class WheelMDController {

	@Autowired
	private WheelMDService wheelMDService;

	@RequestMapping(method=RequestMethod.POST, value="/wheels")
	public @ResponseBody JsonResponse getLastSOStates( 
			@RequestBody WheelSearchParameters searchParameters,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session ) throws Exception{  
		
		JsonResponse result = new JsonResponse();
		
		List<Wheel> data = wheelMDService.getWheels(searchParameters);
		
		result.setSuccess( true );
		result.setTotalResult( data.size() );
		result.setActionMessage( "Success" );
		result.setData( data );
		
		return result;
	}
}
