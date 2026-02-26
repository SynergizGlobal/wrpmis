package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.SafetyReportDao;
import com.synergizglobal.wrpmis.Iservice.SafetyReportService;
import com.synergizglobal.wrpmis.model.Safety;
@Service
public class SafetyReportServiceImpl implements SafetyReportService{
	@Autowired
	SafetyReportDao safetyDao;

	@Override
	public List<Safety> getWorksListInSafetyReport(Safety obj) throws Exception {
		return safetyDao.getWorksListInSafetyReport(obj);
	}

	@Override
	public List<Safety> getContractsListInSafetyReport(Safety obj) throws Exception {
		return safetyDao.getContractsListInSafetyReport(obj);
	}

	@Override
	public List<Safety> getHODListInSafetyReport(Safety obj) throws Exception {
		return safetyDao.getHODListInSafetyReport(obj);
	}

	@Override
	public List<Safety> getSafetyReportData(Safety obj) throws Exception {
		return safetyDao.getSafetyReportData(obj);
	}

	@Override
	public List<Safety> getStatusListInSafetyReport(Safety obj) throws Exception {
		return safetyDao.getStatusListInSafetyReport(obj);
	}
	
	@Override
	public List<Safety> getSafetySummaryReport(Safety obj) throws Exception {
		return safetyDao.getSafetySummaryReport(obj);
	}	
	
	
}
