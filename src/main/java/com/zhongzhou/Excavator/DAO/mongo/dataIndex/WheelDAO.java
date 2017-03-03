package com.zhongzhou.Excavator.DAO.mongo.dataIndex;

import java.util.List;

import javax.annotation.Resource;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.zhongzhou.Excavator.model.dataIndex.WebDataMongoData;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.WheelSearchParameters;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.WebDataWheel;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.Wheel;
import com.zhongzhou.Excavator.springsupport.injectlist.DataSourceList;

@Component
public class WheelDAO {

	@Resource(name=DataSourceList.MONGO_MD_OUTERNET_DOCUMENTS)
	Datastore  mongoMorphiaDataStore;

	public long countWheelData( WheelSearchParameters searchParameters ){
		
		Query query = this.prepareQuery(searchParameters);
		
		query.offset(0).limit(0);
		
		long count = query.countAll();
		
		return count;
	}

	public List< WebDataMongoData<Wheel> > getWheelData( WheelSearchParameters searchParameters ){
		
		Query query = this.prepareQuery(searchParameters);
		
		List< WebDataMongoData<Wheel> > result = query.asList();
		
		return result;
	}
	
	public List< WebDataMongoData<Wheel> > getWheelData( WheelSearchParameters searchParameters , String[] excludeFields, String [] includeFields ){
		
		Query query = this.prepareQuery(searchParameters);
		
		if( excludeFields != null ){
			query.retrievedFields( false, excludeFields );
		}
		if( includeFields != null ){
			query.retrievedFields( true, includeFields );
		}
		
		List< WebDataMongoData<Wheel> > result = query.asList();
		
		return result;
	}
	
	private Query prepareQuery( WheelSearchParameters searchParameters ){
		
		Query query = mongoMorphiaDataStore
				.createQuery((new WebDataWheel()).getClass())
				.disableValidation();
		
		if( searchParameters.wheelIds != null ){
			
			query.field("data.id").in( searchParameters.wheelIds );
		}
		
		if( searchParameters.limit > 0 && searchParameters.start >= 0 ){
			query.offset( searchParameters.start ).limit( searchParameters.limit );
		}
		
		return query;
	}
}
