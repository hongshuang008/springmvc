package com.dreamchain.skeleton.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dreamchain.skeleton.base.BaseDao_fromHibernateDaoSupport;
import com.dreamchain.skeleton.model.AndroidAPPVersion;
import com.dreamchain.skeleton.model.DeviceVersion;
import com.dreamchain.skeleton.model.Friend;
import com.dreamchain.skeleton.model.IOSAPPVersion;

@Repository
public class DeviceVersionDaoImpl  extends BaseDao_fromHibernateDaoSupport<DeviceVersion,Long>{

	public DeviceVersion getLastDeviceVersion() {
		int count= this.count();
		
		if(count>0){
			List<DeviceVersion> ps = this.findAll(count-1, 1);
			return ps.get(0);
		}else{
			return null;
		}
	}
}
