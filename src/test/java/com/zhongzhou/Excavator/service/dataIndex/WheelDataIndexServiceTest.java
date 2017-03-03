package com.zhongzhou.Excavator.service.dataIndex;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.zhongzhou.Excavator.DAO.mongo.NC.report.SaleOrderDAO;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderStateSearchParameters;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.DataIndexedCorporation;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.CorporationIndexSearchParameters;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.Wheel;
import com.zhongzhou.Excavator.springsupport.injectlist.DAOBeanNameList;

public class WheelDataIndexServiceTest {

	private static XmlWebApplicationContext  context;
	private static String[] configs = { "classpath:applicationContext.xml" }; 
	
	private static WheelDataIndexService wheelDataIndexService;
	
	@BeforeClass  
	public static void configTest(){

		try {
			context = new XmlWebApplicationContext ();
			context.setConfigLocations(configs);
			
			context.refresh();
			
			wheelDataIndexService = context.getBean( WheelDataIndexService.class );
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Test
	public void testAll() throws SQLException{
		
		//this.testGetIndexedCorporationData(); 
		this.testCallRemoteDataIndexService();
	}
	
	public void testCallRemoteDataIndexService(){
		
		wheelDataIndexService.callRemoteDataIndexService( "{}" );
	}
	
	//@Test
	public void testGetIndexedCorporationData(){

		CorporationIndexSearchParameters searchParameters = new CorporationIndexSearchParameters();
		searchParameters.indexIds = new ArrayList<String>( Arrays.asList("5869ec5d-2f3d-4573-a6c6-29385fc1ecc1") );
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		
		List< DataIndexedCorporation > indexedCorporations = wheelDataIndexService.getIndexedCorporationData(searchParameters);
		
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		
		for( DataIndexedCorporation indexedCorporation : indexedCorporations){
			
			System.out.println( indexedCorporation.corp.getName() + ":" + indexedCorporation.corpScore );
			for( Wheel wheel : indexedCorporation.wheels ){
				System.out.println( "     " + wheel.getName() );
			}
			System.out.println( "==============================================" );
		}
		
		Assert.assertNotEquals( null, indexedCorporations );
		Assert.assertEquals( true, indexedCorporations.size() > 0 );
	}
}
