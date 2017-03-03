package com.zhongzhou.Excavator.Util.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientCall {

	public static String doJsonPost( String url , String jsonString) throws ClientProtocolException, IOException {
		
		HttpEntity httpEntity = null;
		
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();

		StringEntity entity = new StringEntity(jsonString,"UTF-8");//解决中文乱码问题    
		entity.setContentEncoding("UTF-8");    
		entity.setContentType("application/json;charset=UTF-8");    
		httpPost.setEntity(entity);
		
		HttpResponse resp = client.execute(httpPost);
		//if(resp.getStatusLine().getStatusCode() == 200) {
		
		String result=EntityUtils.toString( resp.getEntity(), "UTF-8" );
		
		client.close();
		
		return result;
	}
}
