package com.zhongzhou.Excavator.DAO.mongo.NC.report;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.zhongzhou.Excavator.model.NC.report.NCReportMongoData;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderItemState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderStateSearchParameters;
import com.zhongzhou.Excavator.service.dataIndex.WheelDataIndexService;
import com.zhongzhou.Excavator.springsupport.injectlist.DAOBeanNameList;
import com.zhongzhou.Excavator.springsupport.injectlist.DataSourceList;

public class SaleOrderDAOTest {
	
	private static XmlWebApplicationContext  context;
	private static String[] configs = { "classpath:applicationContext.xml" }; 
	
	private static SaleOrderDAO saleOrderDAO;
	
	@BeforeClass  
	public static void configTest(){

		try {
			context = new XmlWebApplicationContext ();
			context.setConfigLocations(configs);
			
			context.refresh();
			
			saleOrderDAO = (SaleOrderDAO)context.getBean(DAOBeanNameList.mongo_nc_report_saleOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAll() throws SQLException{
		
		this.testSelectLastVersion(); 
	}
	
	//@Test
	public void testSelectLastVersion(){

		SaleOrderStateSearchParameters searchParameters = new SaleOrderStateSearchParameters();
		searchParameters.customerName = "RTX";
		
		List<SaleOrderState> saleOrderStates = saleOrderDAO.selectLastVersionReport( searchParameters );
		
		System.out.println( saleOrderStates.size() );
		
		Assert.assertNotEquals( null, saleOrderStates );
		Assert.assertEquals( true, saleOrderStates.size() > 0 );
	}
}
