package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.RiskReport;

public interface RiskReportService {

	RiskReport getRiskAnalysisReportData(RiskReport obj) throws Exception;

	List<RiskReport> getWorksListInRiskReport(RiskReport obj) throws Exception;

	List<RiskReport> getAssessmentDateListInRiskReport(RiskReport obj) throws Exception;

}
