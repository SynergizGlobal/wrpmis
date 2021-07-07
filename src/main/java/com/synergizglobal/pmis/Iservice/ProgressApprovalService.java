package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Activity;

public interface ProgressApprovalService {

	List<Activity> getApprovableActivities(Activity obj) throws Exception;

	List<Activity> getWorksInApprovableActivities(Activity obj) throws Exception;

	List<Activity> getContractsInApprovableActivities(Activity obj) throws Exception;
	
	List<Activity> getStructuresInApprovableActivities(Activity obj) throws Exception;
	
	List<Activity> getDepartmentsInApprovableActivities(Activity obj) throws Exception;
	
	List<Activity> getUpdatedByListInApprovableActivities(Activity obj) throws Exception;

	Activity approveActivityProgress(Activity obj) throws Exception;
}
