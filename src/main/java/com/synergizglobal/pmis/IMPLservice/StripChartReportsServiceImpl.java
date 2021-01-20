package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.StripChartReportsDao;
import com.synergizglobal.pmis.Iservice.StripChartReportsService;
import com.synergizglobal.pmis.model.StripChartReport;

@Service
public class StripChartReportsServiceImpl implements StripChartReportsService{
	@Autowired
	StripChartReportsDao dao;
	
	@Override
	public List<StripChartReport> getProjectsFilterListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getProjectsFilterListInStripChartReport(obj);
	}
	
	@Override
	public List<StripChartReport> getWorksFilterListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getWorksFilterListInStripChartReport(obj);
	}

	@Override
	public List<StripChartReport> getContractsFilterListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getContractsFilterListInStripChartReport(obj);
	}

	@Override
	public List<StripChartReport> getContractorsFilterListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getContractorsFilterListInStripChartReport(obj);
	}

	@Override
	public List<StripChartReport> getHodFilterListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getHodFilterListInStripChartReport(obj);
	}

	@Override
	public List<StripChartReport> getDyhodFilterListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getDyhodFilterListInStripChartReport(obj);
	}

	@Override
	public Map<StripChartReport, List<StripChartReport>> getStripChartReportData(StripChartReport obj)
			throws Exception {
		return dao.getStripChartReportData(obj);
	}
	
	/*****************************************************************************************************/

	@Override
	public List<StripChartReport> getStripChartDPRReportData(StripChartReport obj) throws Exception {
		return dao.getStripChartDPRReportData(obj);
	}

	@Override
	public StripChartReport getStripChartDPRReportDetails(StripChartReport obj) throws Exception {
		return dao.getStripChartDPRReportDetails(obj);
	}

}
