package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesExportService {

	List<StripChart> getProjectsFilterListInActivitiesExportReport(StripChart obj) throws Exception;

	List<StripChart> getWorksFilterListInActivitiesExportReport(StripChart obj) throws Exception;

	List<StripChart> getContractListInActivitiesExportReport(StripChart obj) throws Exception;

	StripChart generateActivitiesExportReport(StripChart obj) throws Exception;

	List<StripChart> generateTPCStatusReport(StripChart obj) throws Exception;

	List<StripChart> getDivisionList(StripChart obj) throws Exception;

	List<StripChart> generateMCDOProgressReport(StripChart obj) throws Exception;

}
