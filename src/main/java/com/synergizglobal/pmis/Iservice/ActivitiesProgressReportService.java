package com.synergizglobal.pmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.pmis.model.ActivitiesProgressReport;

public interface ActivitiesProgressReportService {
	
	List<ActivitiesProgressReport> getProjectsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getWorksFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getFobFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;
	
	List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(ActivitiesProgressReport obj) throws Exception;

	Map<ActivitiesProgressReport, Map<String, List<ActivitiesProgressReport>>> getActivitiesReportData(ActivitiesProgressReport obj) throws Exception;

	String getActivitiesRemarks(String structure, String from_date) throws Exception;
	String getContractorName(String contract_id) throws Exception;
	String getWorkName(String work_id) throws Exception;
	String getContractName(String contract_id) throws Exception;

	List<ActivitiesProgressReport> getContarctDetaisl(ActivitiesProgressReport obj) throws Exception;
	
	
}
