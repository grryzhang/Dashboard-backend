package com.zhongzhou.Excavator.service.impl.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.zhongzhou.Excavator.springsupport.SpringContextHolder;
import com.zhongzhou.Excavator.springsupport.injectlist.DAOBeanNameList;
import com.zhongzhou.Excavator.springsupport.injectlist.DataSourceList;
import com.zhongzhou.Excavator.springsupport.injectlist.ServiceNameList;
import com.zhongzhou.Excavator.DAO.mongo.NC.report.SaleOrderDAO;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderStateSearchParameters;
import com.zhongzhou.Excavator.model.system.SysUser;
import com.zhongzhou.Excavator.model.system.UserContext;
import com.zhongzhou.Excavator.service.system.UserLoginService;
import com.zhongzhou.common.Exception.ServiceRuntimeException;
import com.zhongzhou.common.Exception.UserPermissionException;

@Service( ServiceNameList.ORDER_SALE_ORDER_Service )
public class SaleOrderService{

	private Logger log = Logger.getLogger(SaleOrderService.class);  
	
	@Resource( name = DAOBeanNameList.mongo_nc_report_saleOrder )
	SaleOrderDAO saleOrderDAO;

	public List<SaleOrderState> getSaleOrderState( SaleOrderStateSearchParameters searchParameters ) {
		
		
		try{
			
			searchParameters.customerName = "RTX";
			
			return this.saleOrderDAO.selectLastVersionReport( searchParameters );
			
		}catch( RuntimeException e ){
			throw new ServiceRuntimeException("Failed to get LastVersion sale order states.",e);
		}
		
	}
	
	public long countSaleOrderState( SaleOrderStateSearchParameters searchParameters ) {
		
		
		try{
			
			searchParameters.customerName = "RTX";
			
			return this.saleOrderDAO.countLastVersionReport( searchParameters );
			
		}catch( RuntimeException e ){
			throw new ServiceRuntimeException("Failed to count LastVersion sale order states.",e);
		}
	}
	
}
