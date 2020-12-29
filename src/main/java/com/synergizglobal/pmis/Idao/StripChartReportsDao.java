package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.StripChartReport;

public interface StripChartReportsDao {

	List<StripChartReport> getWorksListInRiskReport(StripChartReport obj) throws Exception;

	List<StripChartReport> getContractsListInStripChartReport(StripChartReport obj) throws Exception;

	List<StripChartReport> getStripChartDPRReportData(StripChartReport obj) throws Exception;

	StripChartReport getStripChartDPRReportDetails(StripChartReport obj) throws Exception;

}
