package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

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
	public List<StripChartReport> getWorksListInRiskReport(StripChartReport obj) throws Exception {
		return dao.getWorksListInRiskReport(obj);
	}

	@Override
	public List<StripChartReport> getContractsListInStripChartReport(StripChartReport obj) throws Exception {
		return dao.getContractsListInStripChartReport(obj);
	}

	@Override
	public List<StripChartReport> getStripChartDPRReportData(StripChartReport obj) throws Exception {
		return dao.getStripChartDPRReportData(obj);
	}

	@Override
	public StripChartReport getStripChartDPRReportDetails(StripChartReport obj) throws Exception {
		return dao.getStripChartDPRReportDetails(obj);
	}

}
