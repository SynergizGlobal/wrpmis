package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.ActivitiesProgressReport;

public interface ActivitiesProgressReportDao {
	
	List<ActivitiesProgressReport> getProjectsFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getWorksFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getFobFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;
	
	List<ActivitiesProgressReport> getContractorsFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getHodFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getDyhodFilterListInStripChartReport(ActivitiesProgressReport obj) throws Exception;

	Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getStripChartReportData(ActivitiesProgressReport obj) throws Exception;
	
	/****************************************************************************************/

	List<ActivitiesProgressReport> getStripChartDPRReportData(ActivitiesProgressReport obj) throws Exception;

	ActivitiesProgressReport getStripChartDPRReportDetails(ActivitiesProgressReport obj) throws Exception;
}
