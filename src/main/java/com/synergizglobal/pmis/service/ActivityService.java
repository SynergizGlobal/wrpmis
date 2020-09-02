package com.synergizglobal.pmis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synergizglobal.pmis.dao.ActivityDao;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.Notification;


@Component
public class ActivityService {

	@Autowired
	ActivityDao dao;
	
	/**
	 * This method get the work list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getWorksList(Activity activity) throws Exception {
		return dao.getWorksList(activity);
	}
	/**
	 * This method get the contract list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getContractsList(Activity activity) throws Exception {
		return dao.getContractsList(activity);
	}	
	/**
	 * This method get the activities list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getActivitiesList(Activity activity) throws Exception {
		return dao.getActivitiesList(activity);
	}
	/**
	 * This method get the location list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getLocationsList(Activity activity) throws Exception {
		return dao.getLocationsList(activity);
	}
	/**
	 * This method get the activity types list
	 * 
	 * @param activity is object for the model class Activity
     * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getActivityTypesList(Activity activity) throws Exception {
		return dao.getActivityTypesList(activity);
	}	
	/**
	 * This method get the tasks list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getTasksList(Activity activity) throws Exception {
		return dao.getTasksList(activity);
	}
	/**
	 * This method get the quantities 
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is obj that is object type of model class Activity  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Activity getQuantities(Activity activity) throws Exception {
		return dao.getQuantities(activity);
	}
	/**
	 * This method get the quantities 
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is obj that is object type of model class Activity  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getIssueCategories() throws Exception {
		return dao.getIssueCategories();
	}
	/**
	 * This method save the progress
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean saveProgress(Activity activity) throws Exception {
		return dao.saveProgress(activity);
	}
	/**
	 * This method update the data date
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is activityDataId that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public String updateDataDate(Activity activity) throws Exception {
		return dao.updateDataDate(activity);
	}
	/**
	 * This method get the activity data
	 * 
	 * @param workId it is string type variable that holds the workId
	 * @return type of this method is obj that is object type of model class Activity  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Activity getActivityData(String workId) throws Exception {
		return dao.getActivityData(workId);
	}
	/**
	 * This method update the activities
	 * 
	 * @param activitiesList is list type object
	 * @param workId it is string type variable that holds the workId
	 * @return type of this method is count that is integer type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public int updateActivities(List<Activity> activitiesList,String workId) throws Exception {
		return dao.updateActivities(activitiesList,workId);
	}
	/**
	 * This method get the progress history
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is activities that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getProgressHistory(Activity obj) throws Exception {
		List<Activity> progressHistory = null;
		try {
			progressHistory = dao.getProgressHistory(obj);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return progressHistory;
	}
	

	/**
	 * This method get the current status activities
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getCurrentStatusActivities(Activity obj) throws Exception {
		return dao.getCurrentStatusActivities(obj);
	}
	
	/**
	 * This method get the input file path
	 * 
	 * @param progressId it is string type variable that holds the progress id
	 * @return type of this method is filePath that is String type  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public String getInputFilePath(String progressId) throws Exception {
		return dao.getInputFilePath(progressId);
	}
	
	/**
	 * This method get the activity data history
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getActivityDataHistory() throws Exception {
		return dao.getActivityDataHistory();
	}
	/**
	 * This method get the works
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getWorks() throws Exception {
		return dao.getWorks();
	}
	/**
	 * This method get the projects list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getProjectsListForSearch() throws Exception {
		return dao.getProjectsListForSearch();
	}
	/**
	 * This method get the works list for search
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getWorksListForSearch(Activity obj) throws Exception {
		return dao.getWorksListForSearch(obj);
	}
	/**
	 * This method update the work table
	 * 
	 * @param workId it is string type variable that holds the workId
	 * @param updatedBy it is string type variable that holds the updatedBy
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateWorkTable(String workId,String updatedBy) throws Exception {
		return dao.updateWorkTable(workId,updatedBy);
	}
	
	
	/*******************************************************************************/
	/**
	 * This method get the due activities
	 * 
	 * @param workId it is string type variable that holds the workId
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Notification> getDueActivities(String workId) throws Exception {
		return dao.getDueActivities(workId);
	}
	/**
	 * This method create notifications
	 * 
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean createNotifications() throws Exception {
		return dao.createNotifications();
	}
	/**
	 * This method read the notification
	 * 
	 * @param notificationId it is string type variable that holds the notification id
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean readNotification(String notificationId) throws Exception {
		return dao.readNotification(notificationId);
	}
	/**
	 * This method close the notification
	 * 
	 * @param notificationId it is string type variable that holds the notification id
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean closeNotification(String notificationId) throws Exception {
		return dao.closeNotification(notificationId);
	}
	
}
