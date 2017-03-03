
/**
 * @author zhanghuanping
 *
 */
package com.zhongzhou.Excavator.DAO.mongo.NC.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.aggregation.Accumulator;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;


import com.zhongzhou.Excavator.model.NC.report.NCReportMongoData;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderItemState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderState;
import com.zhongzhou.Excavator.model.NC.report.SaleOrderStateSearchParameters;
import com.zhongzhou.Excavator.springsupport.injectlist.DAOBeanNameList;
import com.zhongzhou.Excavator.springsupport.injectlist.DataSourceList;

@Service( DAOBeanNameList.mongo_nc_report_saleOrder )
public class SaleOrderDAO{
	
	@Resource(name=DataSourceList.MONGO_MD_OUTERNET_DOCUMENTS)
	Datastore  mongoMorphiaDataStore;
	
	public List<SaleOrderState> selectLastVersionReport( SaleOrderStateSearchParameters searchParameters ){
		
		List<SaleOrderState> orderState = new ArrayList<SaleOrderState>();

		Query< NCReportMongoData<SaleOrderState> > query 
			= ( Query< NCReportMongoData<SaleOrderState> > )mongoMorphiaDataStore
				.createQuery( (new NCReportMongoData<SaleOrderState>() ).getClass() );
		this.prepareQuery(query, searchParameters);
		query.offset(searchParameters.start).limit(searchParameters.limit);
			
		List< NCReportMongoData<SaleOrderState> > orderStateData = query.asList();
		if( orderStateData!= null && orderStateData.size() > 0 ){
			for( NCReportMongoData<SaleOrderState> oneDoc : orderStateData ){
				orderState.add( oneDoc.data );
			}
		}
		return orderState;
	}
	
	public long countLastVersionReport( SaleOrderStateSearchParameters searchParameters ){
		
		long orderStateDataCount = 0;
		
		Query< NCReportMongoData<SaleOrderState> > query 
		= ( Query< NCReportMongoData<SaleOrderState> > )mongoMorphiaDataStore
			.createQuery( (new NCReportMongoData<SaleOrderState>() ).getClass() );
		this.prepareQuery(query, searchParameters);
			
		orderStateDataCount = query.countAll();
	

		return orderStateDataCount;
	}
	
	private void prepareQuery( final Query< NCReportMongoData<SaleOrderState> > query, SaleOrderStateSearchParameters searchParameters ){
		
		query.filter( "modelClassName", SaleOrderState.class.getName() ).disableValidation();
	
		if( searchParameters.customerName != null ){
			query.filter("data.customerName", searchParameters.customerName );
		}
		if( searchParameters.customerOrderId != null ){
			query.filter("data.customerOrderId", searchParameters.customerOrderId );
		}
		if( searchParameters.fuzzy != null ){
			
			SaleOrderItemState itemState = new SaleOrderItemState();
			itemState.type = searchParameters.fuzzy;
			
			query.or(
				query.criteria("data.customerOrderId").equal( searchParameters.fuzzy ),
				query.criteria("data.items").hasThisElement( itemState )
			); 
		}
		query.filter( "data.isTheLastVersion", true ).order("-data.version,-data.customerOrderId");
	}
}
