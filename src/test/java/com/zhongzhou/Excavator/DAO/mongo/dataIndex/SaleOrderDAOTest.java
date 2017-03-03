package com.zhongzhou.Excavator.DAO.mongo.dataIndex;

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
import com.zhongzhou.Excavator.model.dataIndex.IndexSearchParameters;
import com.zhongzhou.Excavator.model.dataIndex.RecommendIndex;
import com.zhongzhou.Excavator.service.dataIndex.WheelDataIndexService;
import com.zhongzhou.Excavator.springsupport.injectlist.DAOBeanNameList;
import com.zhongzhou.Excavator.springsupport.injectlist.DataSourceList;

public class SaleOrderDAOTest {
	
	private static XmlWebApplicationContext  context;
	private static String[] configs = { "classpath:applicationContext.xml" }; 
	
	private static IndexIntermediateReulstDAO indexIntermediateReulstDAO;
	
	@BeforeClass  
	public static void configTest(){

		try {
			context = new XmlWebApplicationContext ();
			context.setConfigLocations(configs);
			
			context.refresh();
			
			indexIntermediateReulstDAO = context.getBean(IndexIntermediateReulstDAO.class);
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

		IndexSearchParameters searchParameters = new IndexSearchParameters();
		
		List< RecommendIndex > result = indexIntermediateReulstDAO.getCorporationIndexList( searchParameters );
		
		System.out.println( result.size() );
		
		Assert.assertNotEquals( null, result );
		Assert.assertEquals( true, result.size() > 0 );
	}
}
