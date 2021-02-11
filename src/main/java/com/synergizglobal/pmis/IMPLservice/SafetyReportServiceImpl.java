package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.SafetyReportDao;
import com.synergizglobal.pmis.Iservice.SafetyReportService;
import com.synergizglobal.pmis.model.Safety;
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
	public List<Safety> getSafetyReportData(Safety obj) throws Exception {
		return safetyDao.getSafetyReportData(obj);
	}
}
