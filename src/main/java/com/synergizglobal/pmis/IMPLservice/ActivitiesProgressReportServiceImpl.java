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
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getProjectsFilterListInActivitiesReport(obj);
	}
	
	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getWorksFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractsFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getFobFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getContractorsFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getHodFilterListInActivitiesReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception {
		return dao.getDyhodFilterListInActivitiesReport(obj);
	}

	@Override
	public Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getActivitiesReportData(obj);
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
