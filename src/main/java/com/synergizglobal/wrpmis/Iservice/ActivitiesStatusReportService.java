package com.synergizglobal.wrpmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.wrpmis.model.ActivitiesProgressReport;

public interface ActivitiesStatusReportService {

	ActivitiesProgressReport getActivitiesStatusReportData(
			ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getStructuresList(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getProjectsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getWorksFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getFobFilterListInActivitiesStatusReport(ActivitiesProgressReport obj) throws Exception;

	List<ActivitiesProgressReport> getContractsListInActivities() throws Exception;

}
