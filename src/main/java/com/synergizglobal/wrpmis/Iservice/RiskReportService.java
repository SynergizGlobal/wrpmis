package com.synergizglobal.wrpmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.wrpmis.model.RiskReport;

public interface RiskReportService {

	RiskReport getRiskAnalysisReportData(RiskReport obj) throws Exception;

	List<RiskReport> getWorksListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getSubWorksListInRiskReport(RiskReport obj) throws Exception;
	List<RiskReport> getWorkId(RiskReport obj) throws Exception;

	List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getPrioritizationOfRisks(RiskReport obj) throws Exception;

	List<RiskReport> getReductionPlanRisks(RiskReport obj) throws Exception;

	List<RiskReport> getSummaryOfRiskAssessmentOfProjects() throws Exception;

	Map<String,List<RiskReport>> getTop5RiskAreas() throws Exception;


}
