package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesUploadDao {

	List<StripChart> getWorksListFilter(StripChart obj) throws Exception;

	List<StripChart> getContractsListFilter(StripChart obj) throws Exception;

	List<StripChart> getStructureListFilter(StripChart obj) throws Exception;

	List<StripChart> getComponentIdsListFilter(StripChart obj) throws Exception;

	List<StripChart> getComponentsListFilter(StripChart obj) throws Exception;

	int getTotalRecords(StripChart obj, String searchParameter) throws Exception;

	List<StripChart> getActivitiesList(StripChart obj, Integer startIndex, Integer offset, String searchParameter) throws Exception;

	int uploadActivities(List<Activity> activityList) throws Exception;
	
	List<Activity> getWorksInActivitiesUpload(Activity obj) throws Exception;

	List<Activity> getContractsInActivitiesUpload(Activity obj) throws Exception;

	List<Activity> getStructureTypesInActivitiesUpload(Activity obj) throws Exception;

}
