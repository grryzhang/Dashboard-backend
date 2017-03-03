package com.zhongzhou.Excavator.model.dataIndex.item.wheel;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

import com.zhongzhou.Excavator.model.dataIndex.IndexIntermediateResult;
import com.zhongzhou.Excavator.model.dataIndex.RecommendIndex;


@Entity( value="index_intermediate_result_corporation" , noClassnameStored=true )
public class DataIndexedCorporation extends RecommendIndex {
	
	public static final String type = "data_index_corporation";
	
	public String corpId; // suggest to be it's URL

	public Double corpScore;
	
	public Corporation corp;
	
	public List<String> wheelIDs;
	
	public List<Wheel> wheels;
}
