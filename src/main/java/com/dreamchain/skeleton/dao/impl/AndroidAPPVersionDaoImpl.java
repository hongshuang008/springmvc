package com.dreamchain.skeleton.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dreamchain.skeleton.base.BaseDao_fromHibernateDaoSupport;
import com.dreamchain.skeleton.model.AndroidAPPVersion;
import com.dreamchain.skeleton.model.Friend;
import com.dreamchain.skeleton.model.Person;

@Repository
public class AndroidAPPVersionDaoImpl  extends BaseDao_fromHibernateDaoSupport<AndroidAPPVersion,Long>{

	public AndroidAPPVersion getLastAndroidAPPVersion() {
		int count= this.count();
		if(count>0){
			List<AndroidAPPVersion> ps = this.findAll(count-1, 1);
			return ps.get(0);
		}else{
			return null;
		}
	}
}
