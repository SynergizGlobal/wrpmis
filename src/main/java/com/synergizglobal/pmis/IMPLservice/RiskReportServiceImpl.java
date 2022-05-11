package com.synergizglobal.pmis.IMPLservice;

import java.util.List;
import java.util.Map;

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
	public List<RiskReport> getSubWorksListInRiskReport(RiskReport obj) throws Exception {
		return dao.getSubWorksListInRiskReport(obj);
	}

	@Override
	public List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception {
		return dao.getAssessmentDateListInRiskReport(obj);
	}

	@Override
	public List<RiskReport> getPrioritizationOfRisks(RiskReport obj) throws Exception {
		return dao.getPrioritizationOfRisks(obj);
	}

	@Override
	public List<RiskReport> getReductionPlanRisks(RiskReport obj) throws Exception {
		return dao.getReductionPlanRisks(obj);
	}

	@Override
	public List<RiskReport> getSummaryOfRiskAssessmentOfProjects() throws Exception {
		return dao.getSummaryOfRiskAssessmentOfProjects();
	}

	@Override
	public Map<String,List<RiskReport>> getTop5RiskAreas() throws Exception {
		return dao.getTop5RiskAreas();
	}
	@Override
	public List<RiskReport> getWorkId(RiskReport obj) throws Exception {
		return dao.getWorkId(obj);
	}
	

}
