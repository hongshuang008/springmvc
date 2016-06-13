package com.dreamchain.skeleton.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamchain.skeleton.dao.impl.AndroidAPPVersionDaoImpl;
import com.dreamchain.skeleton.dao.impl.DeviceVersionDaoImpl;
import com.dreamchain.skeleton.dao.impl.IOSAPPVersionDaoImpl;
import com.dreamchain.skeleton.model.AndroidAPPVersion;
import com.dreamchain.skeleton.model.DeviceVersion;
import com.dreamchain.skeleton.model.IOSAPPVersion;

@Service
public class VersionServiceImpl{

	@Autowired
	AndroidAPPVersionDaoImpl androidAPPVersionDaoImpl;
	@Autowired
	IOSAPPVersionDaoImpl iOSAPPVersionDaoImpl;
	@Autowired
	DeviceVersionDaoImpl deviceVersionDaoImpl;
	
	public AndroidAPPVersion getLastAndroidAPPVersion() {
		return androidAPPVersionDaoImpl.getLastAndroidAPPVersion();
	}
	public DeviceVersion getLastDeviceVersion() {
		return deviceVersionDaoImpl.getLastDeviceVersion();
	}
	public IOSAPPVersion getLastIOSAPPVersion() {
		return iOSAPPVersionDaoImpl.getLastIOSAPPVersion();
	}
}
