package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesExportReportDao {

	List<StripChart> getProjectsFilterListInActivitiesExportReport(StripChart obj) throws Exception;

	List<StripChart> getWorksFilterListInActivitiesExportReport(StripChart obj) throws Exception;

	List<StripChart> getContractListInActivitiesExportReport(StripChart obj) throws Exception;

	StripChart generateActivitiesExportReport(StripChart obj) throws Exception;

	List<StripChart> generateTPCStatusReport(StripChart obj) throws Exception;

	List<StripChart> getDivisionList(StripChart obj) throws Exception;

}
