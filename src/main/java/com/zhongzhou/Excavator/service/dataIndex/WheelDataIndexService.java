package com.zhongzhou.Excavator.service.dataIndex;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.zhongzhou.Excavator.DAO.mongo.dataIndex.IndexIntermediateReulstDAO;
import com.zhongzhou.Excavator.DAO.mongo.dataIndex.WheelDAO;
import com.zhongzhou.Excavator.Exception.DashBoardServiceException;
import com.zhongzhou.Excavator.Util.web.HttpClientCall;
import com.zhongzhou.Excavator.model.dataIndex.IndexSearchParameters;
import com.zhongzhou.Excavator.model.dataIndex.RecommendIndex;
import com.zhongzhou.Excavator.model.dataIndex.WebDataMongoData;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.DataIndexedCorporation;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.CorporationIndexSearchParameters;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.Wheel;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.WheelSearchParameters;
import com.zhongzhou.common.model.web.JsonResponse;
import com.zhongzhou.common.util.BeanUtil;

@Service
public class WheelDataIndexService {
	
	@Autowired
	IndexIntermediateReulstDAO indexIntermediateResultDAO;
	
	@Autowired
	WheelDAO wheelDAO;
	
	public Map<String,String> callRemoteDataIndexService( String jsonString ){
		
		Resource resource = null;
		Properties props = null;

		try {
		    resource = new ClassPathResource("/properties/URLList.properties"); 
			
		    props = PropertiesLoaderUtils.loadProperties(resource);
			
			String wheelIndexServiceUrl = (String)props.get("outer.webService.dataIndex.wheel");
			
			if( wheelIndexServiceUrl == null || wheelIndexServiceUrl.length() <= 0 ){
				throw new DashBoardServiceException( " properties:outer.webService.dataIndex.wheel is missing, please check configuration." );
			}
			
			String responseResultString = HttpClientCall.doJsonPost( wheelIndexServiceUrl, jsonString );
			
			JsonResponse response = BeanUtil.beanJaksonUnSerializer(responseResultString.getBytes(), JsonResponse.class );
			
			if( response.getSuccess() ){
				Map<String,String> remoteData = (Map<String,String>)response.getData();
				
				String indexId = remoteData.get("indexId") ;
				
				if( indexId == null || indexId.length() <= 0  ){
					throw new DashBoardServiceException( "Remote Service workding indexID missing,please check the remote service status and response: " + responseResultString );
				}
				
				Map<String,String> result = new HashMap<String,String>();
				result.put("indexId", indexId);
				
				return result; 			
			}else{
				throw new DashBoardServiceException( "Remote Service Error, response: " + responseResultString );
			}
			
		} catch (Exception e) {
			throw new DashBoardServiceException( "Failed to load service url list properties." , e );
		}
	}
	
	public List<RecommendIndex> getRecommendIndex( IndexSearchParameters searchParameters ){
		
		List<RecommendIndex> indexList = this.indexIntermediateResultDAO.getCorporationIndexList(searchParameters);
		
		return indexList;
	}
	
	public long countCorporationIndex( IndexSearchParameters searchParameters ){
		
		long count = this.indexIntermediateResultDAO.countCorporationIndexResult(searchParameters);
		
		return count;
	}
	
	public List< DataIndexedCorporation > getIndexedCorporationDataWithoutWheel( CorporationIndexSearchParameters searchParameters ){

		List<DataIndexedCorporation> indexedCorporations = this.indexIntermediateResultDAO.getCorporationIndexResult(searchParameters);
		
		return indexedCorporations;
	}
		
	public List< DataIndexedCorporation > getIndexedCorporationData( CorporationIndexSearchParameters searchParameters ){

		List<DataIndexedCorporation> indexedCorporations = this.indexIntermediateResultDAO.getCorporationIndexResult(searchParameters);
		
		List<String> wheelIds = new ArrayList<String>();
		for( DataIndexedCorporation indexedCorporation : indexedCorporations ){
			
			if( indexedCorporation.wheelIDs != null ){
				int count = searchParameters.wheelsLimit;
				if( searchParameters.wheelsLimit > count || searchParameters.wheelsLimit < 0 ){
					count = indexedCorporation.wheelIDs.size();
				}
				for( String wheelID : indexedCorporation.wheelIDs ){
					if( count <= 0 ){
						break;
					}
					wheelIds.add( wheelID );
					count--;
				}
			}
		}
		
		WheelSearchParameters wheelSearchParameters = new WheelSearchParameters();
		wheelSearchParameters.wheelIds = wheelIds;
		wheelSearchParameters.start = -1;
		wheelSearchParameters.limit = -1;
		String[] excludeFields = new String[]{"data.specification","data.productDescription"};
		List<WebDataMongoData<Wheel>> wheels = wheelDAO.getWheelData( wheelSearchParameters , excludeFields, null );
		
		Map<String, Wheel> wheelsMap = new HashMap<String, Wheel>();
		for( WebDataMongoData<Wheel> wheelData : wheels ){
			
			if( wheelData != null && wheelData.getData() != null ){
				wheelsMap.put( wheelData.getData().getId() , wheelData.getData() );
			}
		}
			
		for( DataIndexedCorporation corp : indexedCorporations ){
				
			corp.wheels = new ArrayList<Wheel>();
			if( corp.wheelIDs != null ){
				for( String wheelID : corp.wheelIDs ){
					Wheel wheel = wheelsMap.get( wheelID );
					if( wheel != null ){
						corp.wheels.add( wheel );
					}
				}
			}
		}
		
		return indexedCorporations;
	}
}
