package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.RiskReport;

public interface RiskReportDao {

	RiskReport getRiskAnalysisReportData(RiskReport obj) throws Exception;

	List<RiskReport> getWorksListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getSubWorksListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getPrioritizationOfRisks(RiskReport obj) throws Exception;

	List<RiskReport> getReductionPlanRisks(RiskReport obj) throws Exception;


}
