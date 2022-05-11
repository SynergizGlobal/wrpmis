package com.synergizglobal.pmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.RiskReport;

public interface RiskReportService {

	RiskReport getRiskAnalysisReportData(RiskReport obj) throws Exception;

	List<RiskReport> getWorksListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getSubWorksListInRiskReport(RiskReport obj) throws Exception;
	String getWorkId(String sub_work) throws Exception;

	List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getPrioritizationOfRisks(RiskReport obj) throws Exception;

	List<RiskReport> getReductionPlanRisks(RiskReport obj) throws Exception;

	List<RiskReport> getSummaryOfRiskAssessmentOfProjects() throws Exception;

	Map<String,List<RiskReport>> getTop5RiskAreas() throws Exception;


}
