package com.synergizglobal.pmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.ActivitiesProgressReport;

public interface StructureStatusReportService {

	ActivitiesProgressReport getActivitiesStatusReportData(
			ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getStructuresList(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getProjectsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getWorksFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getFobFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsListInActivities() throws Exception;
	List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;
	List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;
	
	List<ActivitiesProgressReport> getContarctDetaisl(ActivitiesProgressReport obj) throws Exception;
	List<ActivitiesProgressReport> getAllContractDetails(ActivitiesProgressReport obj) throws Exception;
	
	List<ActivitiesProgressReport> getStructureRemarks(ActivitiesProgressReport obj) throws Exception;
	String getActivitiesRemarks(String structure, String from_date) throws Exception;
	String getContractorName(String contract_id) throws Exception;
	String getWorkName(String work_id) throws Exception;
	String getContractName(String contract_id) throws Exception;
	Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj) throws Exception;
	String getReportforthePeriodActivitiesRemarks(String structure, String from_date, String to_date) throws Exception;	

}
