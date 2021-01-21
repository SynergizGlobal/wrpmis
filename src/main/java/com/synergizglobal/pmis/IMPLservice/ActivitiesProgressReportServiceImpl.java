package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesProgressReportDao;
import com.synergizglobal.pmis.Iservice.ActivitiesProgressReportService;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;

@Service
public class ActivitiesProgressReportServiceImpl implements ActivitiesProgressReportService{
	@Autowired
	ActivitiesProgressReportDao dao;
	
	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getProjectsFilterListInStripChartReport(obj);
	}
	
	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getWorksFilterListInStripChartReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractsFilterListInStripChartReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractorsFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractorsFilterListInStripChartReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getHodFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getHodFilterListInStripChartReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getDyhodFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getDyhodFilterListInStripChartReport(obj);
	}

	@Override
	public Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getStripChartReportData(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getStripChartReportData(obj);
	}
	
	/*****************************************************************************************************/

	@Override
	public List<ActivitiesProgressReport> getStripChartDPRReportData(ActivitiesProgressReport obj) throws Exception {
		return dao.getStripChartDPRReportData(obj);
	}

	@Override
	public ActivitiesProgressReport getStripChartDPRReportDetails(ActivitiesProgressReport obj) throws Exception {
		return dao.getStripChartDPRReportDetails(obj);
	}

}
