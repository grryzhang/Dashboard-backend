package com.zhongzhou.Excavator.model.dataIndex;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;


public interface IndexIntermediateResult<T> {

	public String getIndexId();
	public void   setIndexId(String indexId);

	public String getIndexCondition() ;
	public void setIndexCondition(String indexCondition);

	public String getDataType();
	public void setDataType(String resultType);
}
