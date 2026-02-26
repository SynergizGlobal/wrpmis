package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.ActivitiesProgressReport;

public interface ProgressUpdateReportService {

	List<ActivitiesProgressReport> getContractsFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getWorksFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractorsFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getHodFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getDyhodFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getProjectsFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getFobFilterListInProgressReport(ActivitiesProgressReport obj) throws Exception;

	ActivitiesProgressReport getActivitiesProgressUpdate(ActivitiesProgressReport obj) throws Exception;

}
