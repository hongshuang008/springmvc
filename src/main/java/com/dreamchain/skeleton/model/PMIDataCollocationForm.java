package com.dreamchain.skeleton.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.dreamchain.skeleton.bean.PMIDataBean;

public class PMIDataCollocationForm {
	
	private List<PMIDataBean> pMIDataCollocations;
	
	public List<PMIDataBean> getpMIDataCollocations() {
		return pMIDataCollocations;
	}

	public void setpMIDataCollocations(List<PMIDataBean> pMIDataCollocations) {
		this.pMIDataCollocations = pMIDataCollocations;
	}

	public List<PMIData> getPMIDataModelCollocations() throws ParseException{
		List<PMIData> results = new ArrayList<PMIData>();
		for (PMIDataBean bean : pMIDataCollocations) {
			results.add(bean.bean2model());
		}
		return results;
	}
	
}
