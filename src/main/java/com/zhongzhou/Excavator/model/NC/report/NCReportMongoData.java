package com.zhongzhou.Excavator.model.NC.report;

import org.mongodb.morphia.annotations.Entity;

@Entity( value="report_nc_saleOrder" )
public class NCReportMongoData<T> {

	public long insertTime;
	
	public long version;
	
	public String modelClassName;
	
	public Class<T> modelClass;
	
	public T data;
}
