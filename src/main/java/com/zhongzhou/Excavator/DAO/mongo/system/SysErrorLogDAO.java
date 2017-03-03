package com.zhongzhou.Excavator.DAO.mongo.system;

import javax.annotation.Resource;

import org.mongodb.morphia.Datastore;
import org.springframework.stereotype.Service;

import com.zhongzhou.Excavator.springsupport.injectlist.DAOBeanNameList;
import com.zhongzhou.Excavator.springsupport.injectlist.DataSourceList;
import com.zhongzhou.common.model.log.SysErrorLog;

@Service
public class SysErrorLogDAO {

	@Resource(name=DataSourceList.MONGO_MD_OUTERNET_DOCUMENTS)
	Datastore  mongoMorphiaDataStore;
	
	public void insertLog( SysErrorLog sysErrorLog ){
		
		mongoMorphiaDataStore.save( sysErrorLog );
	}
}
