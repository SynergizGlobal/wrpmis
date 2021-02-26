package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Activity;

public interface ActivitiesUploadDao {
	
	int[] uploadActivities(List<Activity> activityList) throws Exception;
	
	List<Activity> getWorksInActivitiesUpload(Activity obj) throws Exception;

	List<Activity> getContractsInActivitiesUpload(Activity obj) throws Exception;

	List<Activity> getStructureTypesInActivitiesUpload(Activity obj) throws Exception;

	boolean addFileInActivitiesDataTable(String data_remarks, Activity activity) throws Exception;
	
	List<Activity> getWorksListFilter(Activity obj) throws Exception;

	List<Activity> getContractsListFilter(Activity obj) throws Exception;

	List<Activity> getStructureTypesListFilter(Activity obj) throws Exception;

	List<Activity> getActivitiesUploadFilesList(Activity obj) throws Exception;

}
