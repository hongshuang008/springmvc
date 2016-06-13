package com.dreamchain.skeleton.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dreamchain.skeleton.base.BaseService;
import com.dreamchain.skeleton.dao.impl.PMIDaoImpl;
import com.dreamchain.skeleton.model.PMIData;
import com.dreamchain.skeleton.model.PMIDataCollocationForm;

@Service
public class PMIServiceImpl extends BaseService<PMIData,Long>{

	@Autowired
	PMIDaoImpl dao;
	@Autowired
	public PMIServiceImpl(PMIDaoImpl dao) {
		super(dao);
	}
	
	public List getHistoryPMIData(final Map param) throws Exception{
		return dao.getHistoryPMIData(param);
	}
	
	
	@Transactional
	public void savePMIList(PMIDataCollocationForm form)  throws Exception{
		dao.batchSaveENTITY(form.getPMIDataModelCollocations());
//		dao.batchSaveENTITY2(form.getpMIDataCollocations());
	}
}
