package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SafetyDetailsReportDao;
import com.synergizglobal.pmis.Iservice.SafetyDetailsReportService;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Safety;
@Service
public class SafetyDetailsReportServiceImpl implements SafetyDetailsReportService{

	@Autowired
	SafetyDetailsReportDao dao;

	@Override
	public List<Safety> getContractsListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getContractsListInSafetyDetailsReport(obj);
	}

	@Override
	public List<Safety> getHODListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getHODListInSafetyDetailsReport(obj);
	}

	@Override
	public List<Safety> getWorksListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getWorksListInSafetyDetailsReport(obj);
	}

	@Override
	public List<Safety> getStatusListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getStatusListInSafetyDetailsReport(obj);
	}

	@Override
	public List<Safety> getLocationsListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getLocationsListInSafetyDetailsReport(obj);
	}

	@Override
	public List<Safety> getTitlesListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getTitlesListInSafetyDetailsReport(obj);
	}

	@Override
	public List<Safety> getCategoriesListInSafetyDetailsReport(Safety obj) throws Exception {
		return dao.getCategoriesListInSafetyDetailsReport(obj);
	}

	@Override
	public Safety getSafetyDetails(Safety obj) throws Exception {
		return dao.getSafetyDetails(obj);
	}
}
