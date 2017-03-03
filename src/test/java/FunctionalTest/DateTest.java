package FunctionalTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {

	//@Test
	public void test() throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = sdf.parse("1899-11-30");  
	    
	    Date date1 = sdf.parse("2000-01-01");  
	    
	    System.out.println(  date.getTime() );
	    System.out.println(  date1.getTime() );
	    
	    if( date.getTime() <   date1.getTime() )  System.out.println(  "test" );
	    
	}
	
	@Test
	public void testLong2Date() throws Exception{
		
		Date date = new Date( new Long("1488340800000") );
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		
	    System.out.println( date );
	    System.out.println( sdf.format(date) );
	}
}
