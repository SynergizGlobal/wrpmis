package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.ActivitiesProgressReport;

public interface ActivitiesProgressReportDao {
	
	List<ActivitiesProgressReport> getProjectsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getWorksFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getFobFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;
	
	List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj) throws Exception;
	
	/****************************************************************************************/

	List<ActivitiesProgressReport> getStripChartDPRReportData(ActivitiesProgressReport obj) throws Exception;

	ActivitiesProgressReport getStripChartDPRReportDetails(ActivitiesProgressReport obj) throws Exception;
}
