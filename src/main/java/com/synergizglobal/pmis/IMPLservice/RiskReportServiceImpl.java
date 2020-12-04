package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.RiskReportDao;
import com.synergizglobal.pmis.Iservice.RiskReportService;
import com.synergizglobal.pmis.model.RiskReport;

@Service
public class RiskReportServiceImpl implements RiskReportService{
	
	@Autowired
	RiskReportDao dao;
	
	@Override
	public RiskReport getRiskAnalysisReportData(RiskReport obj) throws Exception {
		return dao.getRiskAnalysisReportData(obj);
	}

	@Override
	public List<RiskReport> getWorksListInRiskReport(RiskReport obj) throws Exception {
		return dao.getWorksListInRiskReport(obj);
	}

	@Override
	public List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception {
		return dao.getAssessmentDateListInRiskReport(obj);
	}

}
