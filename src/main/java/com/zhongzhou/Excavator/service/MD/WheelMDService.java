package com.zhongzhou.Excavator.service.MD;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongzhou.Excavator.DAO.mongo.dataIndex.WheelDAO;
import com.zhongzhou.Excavator.model.dataIndex.WebDataMongoData;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.Wheel;
import com.zhongzhou.Excavator.model.dataIndex.item.wheel.WheelSearchParameters;

@Service
public class WheelMDService {

	@Autowired
	WheelDAO wheelDAO;
	
	public List<Wheel> getWheels( WheelSearchParameters searchParameters ) {
		
		List<WebDataMongoData<Wheel>> wheelData = wheelDAO.getWheelData(searchParameters);
		List<Wheel> result = new ArrayList<Wheel>();
		
		for( WebDataMongoData<Wheel> oneData : wheelData ){
			result.add( oneData.getData() );
		}
		
		return result;
	}
}
