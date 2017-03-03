package com.zhongzhou.Excavator.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhongzhou.Excavator.model.NC.report.SaleOrderState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderStateSearchParameters;
import com.zhongzhou.Excavator.service.impl.order.SaleOrderService;
import com.zhongzhou.Excavator.springsupport.injectlist.ServiceNameList;
import com.zhongzhou.common.model.web.JsonResponse;

@Controller
public class SaleOrderController {
	
	@Resource( name=ServiceNameList.ORDER_SALE_ORDER_Service )
	private SaleOrderService saleOrderService;

	@RequestMapping(method=RequestMethod.POST, value="/SOState/theLast")
	public @ResponseBody JsonResponse getLastSOStates( 
			@RequestBody SaleOrderStateSearchParameters searchParameters,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session ) throws Exception{  
		
		JsonResponse result = new JsonResponse();
		
		searchParameters.customerName = "RTX";
		List<SaleOrderState> saleOrder = saleOrderService.getSaleOrderState( searchParameters );
		long count = saleOrderService.countSaleOrderState(searchParameters);
		
		result.setSuccess( true );
		result.setTotalResult( count );
		result.setActionMessage( "Success" );
		result.setData( saleOrder );
		
		return result;
	}
}
