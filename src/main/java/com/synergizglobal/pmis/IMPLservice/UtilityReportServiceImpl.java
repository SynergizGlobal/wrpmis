package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.UtilityReportDao;
import com.synergizglobal.pmis.Iservice.UtilityReportService;
import com.synergizglobal.pmis.model.UtilityShifting;
@Service
public class UtilityReportServiceImpl implements UtilityReportService{
	@Autowired
	UtilityReportDao dao;

	@Override
	public List<UtilityShifting> getProjectsFilterListInutilityReport(UtilityShifting obj) throws Exception {
		return dao.getProjectsFilterListInutilityReport(obj);
	}

	@Override
	public List<UtilityShifting> getWorksFilterListInutilityReport(UtilityShifting obj) throws Exception {
		return dao.getWorksFilterListInutilityReport(obj);
	}

	@Override
	public List<UtilityShifting> getExecutionAgencyListInutilityReport(UtilityShifting obj) throws Exception {
		return dao.getExecutionAgencyListInutilityReport(obj);
	}

	@Override
	public UtilityShifting getUtilityShiftingData(UtilityShifting obj) throws Exception {
		return dao.getUtilityShiftingData(obj);
	}

	@Override
	public Map<String, List<UtilityShifting>> getUtilityShiftingReportData(UtilityShifting obj) throws Exception {
		return dao.getUtilityShiftingReportData(obj);
	}

	
}
