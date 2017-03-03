/**
 * 
 */
/**
 * @author zhanghuanping
 *
 */
package FunctionalTest;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.zhongzhou.Excavator.Exception.DashBoardServiceException;
import com.zhongzhou.Excavator.service.dataIndex.WheelDataIndexService;

public class PropertiesLoader{
	
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
	public void testReg(){
		
		try {
			
			Resource resource = new ClassPathResource("/properties/URLList.properties"); 
			
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
			System.out.println( props );
		} catch (IOException e) {
			throw new DashBoardServiceException( "Failed to load service url list properties." , e );
		}
	}
}