package com.synergizglobal.pmis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.EncryptDecrypt;
import com.synergizglobal.pmis.common.QyeryLogger;
import com.synergizglobal.pmis.common.TimeAgo;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.Notification;


@Component
public class ActivityDao {
	Logger logger = Logger.getLogger(ActivityDao.class);
	@Autowired
	DataSource dataSource;
	
	
	/*******************************************************************************************************************/
	/**
	 * This method get the work list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getWorksList(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			
			
			String qry = "SELECT wbs_5_code,wbs_5_name FROM mrvc_activity_view_2 where activity_status_id <> ? group by wbs_5_code";
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setWorkId(resultSet.getString("wbs_5_code"));
				obj.setWorkName(resultSet.getString("wbs_5_name"));
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}
	
	/**
	 * This method get the contract list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getContractsList(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "SELECT wbs_4_code,wbs_4_name FROM mrvc_activity_view_2 where activity_status_id <> ? and wbs_5_code = ? group by wbs_4_code";
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			statement.setString(2, activity.getWorkId());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setContractId(resultSet.getString("wbs_4_code"));
				obj.setContractName(resultSet.getString("wbs_4_name"));
				objects.add(obj);
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}
	
	/**
	 * This method get the activities list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getActivitiesList(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			
			
			String qry = "SELECT wbs_3_code,wbs_3_name FROM mrvc_activity_view_2 "
					+ "WHERE activity_status_id <> ? and wbs_4_code = ? and wbs_5_code = ? group by wbs_3_code";
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			statement.setString(2, activity.getContractId());
			statement.setString(3, activity.getWorkId());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setActivityId(resultSet.getString("wbs_3_code"));
				obj.setActivity(resultSet.getString("wbs_3_name"));
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}

	/**
	 * This method get the location list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getLocationsList(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			
			
			String qry = "SELECT wbs_2_code,wbs_2_name FROM mrvc_activity_view_2 "
					+ "WHERE activity_status_id <> ? and wbs_3_code = ? and wbs_4_code = ? and wbs_5_code = ? group by wbs_2_code";
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			statement.setString(2, activity.getActivityId());
			statement.setString(3, activity.getContractId());
			statement.setString(4, activity.getWorkId());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setLocationId(resultSet.getString("wbs_2_code"));
				obj.setLocation(resultSet.getString("wbs_2_name"));
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}

	/**
	 * This method get the activity types list
	 * 
	 * @param activity is object for the model class Activity
     * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getActivityTypesList(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT wbs_1_code,wbs_1_name FROM mrvc_activity_view_2 "
					+ "WHERE activity_status_id <> ? and wbs_2_code = ? and wbs_3_code = ? and wbs_4_code = ? and wbs_5_code = ? group by wbs_1_code";
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			statement.setString(2, activity.getLocationId());
			statement.setString(3, activity.getActivityId());
			statement.setString(4, activity.getContractId());
			statement.setString(5, activity.getWorkId());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setActivityTypeId(resultSet.getString("wbs_1_code"));
				obj.setActivityType(resultSet.getString("wbs_1_name"));
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}
	
	
	/**
	 * This method get the tasks list
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getTasksList(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "SELECT activity_id,p6_task_code,activity_name,DATE_FORMAT(base_start_date,'%d-%m-%Y') AS base_start_date,DATE_FORMAT(base_finish_date,'%d-%m-%Y') AS base_finish_date,"
					+ "DATE_FORMAT(early_start_date,'%d-%m-%Y') AS early_start_date,DATE_FORMAT(early_finish_date,'%d-%m-%Y') AS early_finish_date,DATE_FORMAT(act_start_date,'%d-%m-%Y') AS act_start_date,DATE_FORMAT(act_finish_date,'%d-%m-%Y') AS act_finish_date,"
					+ "activity_weight,activity_status_id,activity_status,"
					+ "complete_pct "
					+ "FROM mrvc_activity_view_2 where boq_total is not null and boq_total > 0 and boq_completed < boq_total and activity_status_id <> ? ";
			
			if(!StringUtils.isEmpty(activity.getActivityTypeId())) {
				qry = qry + "and wbs_1_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getLocationId())) {
				qry = qry + "and wbs_2_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getActivityId())) {
				qry = qry + "and wbs_3_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getContractId())) {
				qry = qry + "and wbs_4_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getWorkId())) {
				qry = qry + "and wbs_5_code = ? ";
			}
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			int c = 2;
			if(!StringUtils.isEmpty(activity.getActivityTypeId())) {
				statement.setString(c++, activity.getActivityTypeId());
			}
			if(!StringUtils.isEmpty(activity.getLocationId())) {
				statement.setString(c++, activity.getLocationId());
			}
			if(!StringUtils.isEmpty(activity.getActivityId())) {
				statement.setString(c++, activity.getActivityId());
			}
			if(!StringUtils.isEmpty(activity.getContractId())) {
				statement.setString(c++, activity.getContractId());
			}
			if(!StringUtils.isEmpty(activity.getWorkId())) {
				statement.setString(c++, activity.getWorkId());
			}
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setActivityId(resultSet.getString("activity_id"));
				obj.setTaskId(resultSet.getString("p6_task_code"));
				obj.setTaskName(resultSet.getString("activity_name"));
				obj.setActivityStatusId(resultSet.getString("activity_status_id"));
				obj.setActivityStatus(resultSet.getString("activity_status"));
				obj.setBaseStart(resultSet.getString("base_start_date"));
				obj.setBaseEnd(resultSet.getString("base_finish_date"));
				obj.setPlannedStart(resultSet.getString("early_start_date"));
				obj.setPlannedFinish(resultSet.getString("early_finish_date"));
				obj.setActualStart(resultSet.getString("act_start_date"));
				obj.setActualFinish(resultSet.getString("act_finish_date"));
				obj.setWeight(resultSet.getString("activity_weight"));
				obj.setActivityWeight(resultSet.getString("activity_weight"));
				obj.setPercentageComplete(resultSet.getString("complete_pct"));
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}	
	
	
	/**
	 * This method get the quantities 
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is obj that is object type of model class Activity  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Activity getQuantities(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		Activity obj = null;
		String percentageComplete = "0";
		String percentageRemaining = "0";
		String boqTotal = "0";
		String boqCompleted = "0";
		String boqRemaining = "0";
		
		try {
			NumberFormat formatter = new DecimalFormat("0.##");
		
			connection = dataSource.getConnection();
			
			
			String qry = "SELECT activity_id,p6_task_code,activity_name,DATE_FORMAT(base_start_date,'%d-%m-%Y') AS base_start_date,DATE_FORMAT(base_finish_date,'%d-%m-%Y') AS base_finish_date,"
					+ "DATE_FORMAT(early_start_date,'%d-%m-%Y') AS early_start_date,DATE_FORMAT(early_finish_date,'%d-%m-%Y') AS early_finish_date,DATE_FORMAT(act_start_date,'%d-%m-%Y') AS act_start_date,DATE_FORMAT(act_finish_date,'%d-%m-%Y') AS act_finish_date,"
					+ "activity_weight,activity_status_id,activity_status,"
					+ "complete_pct,boq_total,boq_completed,boq_units,wbs_1_code as activityTypeId,wbs_2_code as locationId,wbs_3_code as activityId,wbs_4_code as contractId,wbs_5_code as workId "
					+ "FROM mrvc_activity_view_2 where activity_status_id <> ? and activity_id = ? ";
					//+ "and wbs_1_code = ? and wbs_2_code = ? and wbs_3_code = ? and wbs_4_code = ? and wbs_5_code = ?";
			
			if(!StringUtils.isEmpty(activity.getActivityTypeId())) {
				qry = qry + "and wbs_1_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getLocationId())) {
				qry = qry + "and wbs_2_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getActivityId())) {
				qry = qry + "and wbs_3_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getContractId())) {
				qry = qry + "and wbs_4_code = ? ";
			}
			if(!StringUtils.isEmpty(activity.getWorkId())) {
				qry = qry + "and wbs_5_code = ? ";
			}
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			statement.setString(2, activity.getTaskId());
			int c = 3;
			if(!StringUtils.isEmpty(activity.getActivityTypeId())) {
				statement.setString(c++, activity.getActivityTypeId());
			}
			if(!StringUtils.isEmpty(activity.getLocationId())) {
				statement.setString(c++, activity.getLocationId());
			}
			if(!StringUtils.isEmpty(activity.getActivityId())) {
				statement.setString(c++, activity.getActivityId());
			}
			if(!StringUtils.isEmpty(activity.getContractId())) {
				statement.setString(c++, activity.getContractId());
			}
			if(!StringUtils.isEmpty(activity.getWorkId())) {
				statement.setString(c++, activity.getWorkId());
			}

			/*statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			statement.setString(2, activity.getTaskId());
			statement.setString(3, activity.getActivityTypeId());
			statement.setString(4, activity.getLocationId());
			statement.setString(5, activity.getActivityId());
			statement.setString(6, activity.getContractId());
			statement.setString(7, activity.getWorkId());*/
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				obj = new Activity();
				obj.setActivityId(resultSet.getString("activity_id"));
				obj.setTaskId(resultSet.getString("p6_task_code"));
				obj.setTaskName(resultSet.getString("activity_name"));
				obj.setActivityStatusId(resultSet.getString("activity_status_id"));
				obj.setActivityStatus(resultSet.getString("activity_status"));
				obj.setBaseStart(resultSet.getString("base_start_date"));
				obj.setBaseEnd(resultSet.getString("base_finish_date"));
				obj.setPlannedStart(resultSet.getString("early_start_date"));
				obj.setPlannedFinish(resultSet.getString("early_finish_date"));
				obj.setActualStart(resultSet.getString("act_start_date"));
				obj.setActualFinish(resultSet.getString("act_finish_date"));
				obj.setWeight(resultSet.getString("activity_weight"));
				obj.setActivityWeight(resultSet.getString("activity_weight"));
				
				obj.setBoqTotal(resultSet.getString("boq_total"));
				//obj.setBoqCompleted(resultSet.getString("boq_completed"));
				obj.setBoqUnits(resultSet.getString("boq_units"));
				
				String value = resultSet.getString("complete_pct");
				if(!StringUtils.isEmpty(value)){
					percentageComplete = value;
					percentageRemaining = String.valueOf(100 - Double.parseDouble(value));
				}else{
					percentageRemaining = "100";
				}
				obj.setPercentageComplete(formatter.format(Double.parseDouble(percentageComplete)));
				obj.setPercentageRemaining(formatter.format(Double.parseDouble(percentageRemaining)));
				
				boqTotal = resultSet.getString("boq_total");
				obj.setBoqTotal(!StringUtils.isEmpty(boqTotal)?boqTotal:"0");
				boqCompleted = resultSet.getString("boq_completed");
				if(!StringUtils.isEmpty(boqTotal) && !StringUtils.isEmpty(boqCompleted)){
					boqRemaining = String.valueOf( Double.parseDouble(boqTotal) - Double.parseDouble(boqCompleted));
				}else if(!StringUtils.isEmpty(boqTotal) && StringUtils.isEmpty(boqCompleted)){
					boqCompleted = "0";
					boqRemaining = String.valueOf( Double.parseDouble(boqTotal) - Double.parseDouble(boqCompleted));
				}else {
					boqCompleted = "0";
					boqRemaining = !StringUtils.isEmpty(boqTotal)?boqTotal:"0";
				}
				obj.setBoqCompleted(formatter.format(Double.parseDouble(boqCompleted)));
				obj.setBoqRemaining(formatter.format(Double.parseDouble(boqRemaining)));
				
				obj.setActivityTypeId(resultSet.getString("activityTypeId"));
				obj.setLocationId(resultSet.getString("locationId"));
				obj.setWbs3CodeActivityId(resultSet.getString("activityId"));
				obj.setContractId(resultSet.getString("contractId"));
				obj.setWorkId(resultSet.getString("workId"));
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return obj;
	}
	
	/**
	 * This method get the issue categories
	 * 
	 * @return type of this method is issueCategories that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getIssueCategories() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> issueCategories = new ArrayList<Activity>();
		Activity activity = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT issue_category_id,category FROM issue_category";
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				activity = new Activity();
				activity.setIssueCategoryId(resultSet.getString("issue_category_id"));
				activity.setIssueCategory(resultSet.getString("category"));
				issueCategories.add(activity);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return issueCategories;
	}
	
	/**
	 * This method save the progress
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean saveProgress(Activity activity) throws Exception {
		
		Connection connection = null;
		PreparedStatement statement = null;
		boolean flag = false;
		ResultSet resultSet = null;
		String issueId = null;
		
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);  
			
			String qry2 = "SELECT max(issue_id) as maxId FROM issues";
			statement = connection.prepareStatement(qry2);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				issueId = String.valueOf(resultSet.getLong("maxId")+1);
			}
			if(resultSet != null){resultSet.close();}
			if(statement != null){statement.close();}
			
			if(!StringUtils.isEmpty(activity.getIssue()) && activity.getIssue().equalsIgnoreCase("yes")){
				String issuesQry = "INSERT INTO issues(issue_id,description,issue_priority_type_id_fk,issue_category_id_fk,"
						+ "issue_status_id_fk,reported_by)VALUES(?,?,?,?,?,?)";				
				
				statement = connection.prepareStatement(issuesQry);
				statement.setString(1, issueId);
				statement.setString(2, !StringUtils.isEmpty(activity.getIssueDesc())?activity.getIssueDesc():null);
				statement.setString(3, !StringUtils.isEmpty(activity.getIssuePriorityId())?activity.getIssuePriorityId():null);
				statement.setString(4, !StringUtils.isEmpty(activity.getIssueCategoryId())?activity.getIssueCategoryId():null);
				statement.setString(5, "3");
				statement.setString(6, !StringUtils.isEmpty(activity.getCreatedById())?activity.getCreatedById():null);
				
				int c = statement.executeUpdate();
				if(c > 0) {
					flag = true;
					
					QyeryLogger logTableObj = new QyeryLogger();
					logTableObj.setFormName("Activity");
					logTableObj.setTableName("issues");
					logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_INSERT_ID);
					String dataFields = "issue_id = "+issueId+",description = "+activity.getIssueDesc()+",issue_priority_type_id_fk = "+activity.getIssuePriorityId()+",issue_category_id_fk = "+activity.getIssueCategoryId()+",issue_status_id_fk = 3,reported_by = "+activity.getCreatedById();
					logTableObj.setDataFields(dataFields);
					String whereCondition = null;
					logTableObj.setWhereCondition(whereCondition);
					logTableObj.setCreatedBy(activity.getCreatedBy());
					
					CommonMethods.queryLogger(logTableObj, connection);
				}
				
				
				if(resultSet != null){resultSet.close();}
				if(statement != null){statement.close();}
				
			}else{
				issueId = null;
			}
			
			
			
			String qry = "INSERT INTO progress(progress_date,activity_id_fk,percentage_complete,issue_id_fk,attachment_url,remarks,created_by)"
					+ "VALUES(?,?,?,?,?,?,?)";			
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, !StringUtils.isEmpty(activity.getReportingDate()) ? activity.getReportingDate() : null);
			statement.setString(2, activity.getTaskId());
			//statement.setString(3, activity.getPercentageWorkDone());
			statement.setString(3, activity.getBoqCompletedOnThisDate());
			statement.setString(4, issueId);
			statement.setString(5, activity.getFilesPath());
			statement.setString(6, activity.getRemarks());
			statement.setString(7, activity.getCreatedBy());
			int count = statement.executeUpdate();
			if(count > 0) {
				flag = true;
				
				QyeryLogger logTableObj = new QyeryLogger();
				logTableObj.setFormName("Activity");
				logTableObj.setTableName("progress");
				logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_INSERT_ID);
				String dataFields = "progress_date = "+activity.getReportingDate()+",activity_id_fk = "+activity.getTaskId()+",percentage_complete = "+activity.getBoqCompletedOnThisDate()+",issue_id_fk = "+issueId+",attachment_url = "+activity.getFilesPath()+",remarks = "+activity.getRemarks()+",remarks = "+activity.getCreatedBy();
				logTableObj.setDataFields(dataFields);
				String whereCondition = null;
				logTableObj.setWhereCondition(whereCondition);
				logTableObj.setCreatedBy(activity.getCreatedBy());
				
				CommonMethods.queryLogger(logTableObj, connection);
			}
			
			if(resultSet != null){resultSet.close();}
			if(statement != null){statement.close();}
			
			/*String qry3 = "SELECT complete_pct FROM activity where activity_id = ? and complete_pct is not null and complete_pct <> ''";
			statement = connection.prepareStatement(qry3);
			statement.setString(1, activity.getTaskId());
			resultSet = statement.executeQuery();  
			float progress = 0.00f;
			if(resultSet.next()) {
				progress = resultSet.getFloat("complete_pct");
			}
			if(resultSet != null){resultSet.close();}
			if(statement != null){statement.close();}*/
			
			String qry4 = "SELECT boq_completed FROM activity where activity_id = ?";
			statement = connection.prepareStatement(qry4);
			statement.setString(1, activity.getTaskId());
			resultSet = statement.executeQuery();  
			float boqCompleted = 0.00f;
			if(resultSet.next()) {
				boqCompleted = resultSet.getFloat("boq_completed");
			}
			if(resultSet != null){resultSet.close();}
			if(statement != null){statement.close();}
			
			String statusId = null;
			if(activity.getMarkAsComplete().equalsIgnoreCase("true")) {
				statusId = CommonConstants.ACTIVITY_COMPLETED;
				activity.setActualFinish(activity.getReportingDate());
			}else if(boqCompleted == 0 && Float.parseFloat(activity.getBoqCompletedOnThisDate()) > 0) {
				statusId = CommonConstants.ACTIVITY_IN_PROGRESS;
				activity.setActualStart(activity.getReportingDate());
			}
			
			String statusQry = "UPDATE activity SET ";
			
			//if(!StringUtils.isEmpty(activity.getPercentageWorkDone())){
				//statusQry = statusQry + "complete_pct = ? + ?";
			//}			
				
			statusQry = statusQry + "boq_completed = ? + ?";
				
			/*if(!StringUtils.isEmpty(statusId)){
				statusQry = statusQry + ",activity_status_id_fk = ?";
			}				
			if(!StringUtils.isEmpty(activity.getActualStart())){
				statusQry = statusQry + ",act_start_date = ?";
			}			
			if(!StringUtils.isEmpty(activity.getActualFinish())){
				statusQry = statusQry + ",act_finish_date = ?";
			}*/
			
			
			statusQry = statusQry + " WHERE activity_id = ?";	
			statement = connection.prepareStatement(statusQry);
					
			
			//if(!StringUtils.isEmpty(activity.getPercentageWorkDone())){
				//statement.setFloat(1,Float.parseFloat(activity.getPercentageWorkDone()));
				//statement.setFloat(2,progress);
			//}
			
			statement.setFloat(1,Float.parseFloat(activity.getBoqCompletedOnThisDate()));
			statement.setFloat(2,boqCompleted);
				
			int index = 3;
			/*if(!StringUtils.isEmpty(statusId)){
				statement.setString(index++, statusId);	
			}
			if(!StringUtils.isEmpty(activity.getActualStart())){
				statement.setString(index++,activity.getActualStart());
			}			
			if(!StringUtils.isEmpty(activity.getActualFinish())){
				statement.setString(index++,activity.getActualFinish());
			}*/
			
			statement.setString(index++, activity.getTaskId());
			int c = statement.executeUpdate();
			if(c > 0) {				
				if(resultSet != null){resultSet.close();}
				if(statement != null){statement.close();}
				
				
				QyeryLogger logTableObj = new QyeryLogger();
				logTableObj.setFormName("Activity");
				logTableObj.setTableName("activity");
				logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_UPDATE_ID);
				//String dataFields = "boq_completed = "+(Float.parseFloat(activity.getBoqCompletedOnThisDate())+boqCompleted)+",activity_status_id_fk = "+statusId+",act_start_date = "+activity.getActualStart()+",act_finish_date = "+activity.getActualFinish();
				String dataFields = "boq_completed = "+(Float.parseFloat(activity.getBoqCompletedOnThisDate())+boqCompleted);
				
				logTableObj.setDataFields(dataFields);
				String whereCondition = "activity_id = "+activity.getTaskId();
				logTableObj.setWhereCondition(whereCondition);
				logTableObj.setCreatedBy(activity.getCreatedBy());
				
				CommonMethods.queryLogger(logTableObj, connection);
				
				
				statement = connection.prepareStatement("select sum(Actual_Percentage)*100 as actualOverallProgress from actual_percentage where work_id = ?");
				statement.setString(1, activity.getWorkId());
				resultSet = statement.executeQuery();  
				double actualOverallProgress = 0.00f;
				if(resultSet.next()) {
					actualOverallProgress = resultSet.getDouble("actualOverallProgress");
				}
				if(resultSet != null){resultSet.close();}
				if(statement != null){statement.close();}
				
				
				statement = connection.prepareStatement("update `work` set actual_pct = ? where work_id = ?");
				statement.setDouble(1, actualOverallProgress);
				statement.setString(2, activity.getWorkId());
				int c1 = statement.executeUpdate();
				if(c1 > 0) {
					if(resultSet != null){resultSet.close();}
					if(statement != null){statement.close();}
					
					logTableObj = new QyeryLogger();
					logTableObj.setFormName("Activity");
					logTableObj.setTableName("work");
					logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_UPDATE_ID);
					dataFields = "actual_pct = "+actualOverallProgress;
					logTableObj.setDataFields(dataFields);
					whereCondition = "work_id = "+activity.getWorkId();
					logTableObj.setWhereCondition(whereCondition);
					logTableObj.setCreatedBy(activity.getCreatedBy());
					
					CommonMethods.queryLogger(logTableObj, connection);
				}
				flag = true;	
			}
			
			if(flag) {
				connection.commit();
			}else{
				connection.rollback();
			}
		}catch(Exception e){ 
			connection.rollback();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return flag;
	}
	
	/**
	 * This method update the data date
	 * 
	 * @param activity is object for the model class Activity
	 * @return type of this method is activityDataId that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public String updateDataDate(Activity activity) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String activityDataId = null;
		try {
			connection = dataSource.getConnection();
			String qry = "update activity_data set is_active = ? where work_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.INACTIVE);
			statement.setString(2, activity.getWorkId());
			int c = statement.executeUpdate();
			if(c > 0) {			
				QyeryLogger logTableObj = new QyeryLogger();
				logTableObj.setFormName("P6 data");
				logTableObj.setTableName("activity_data");
				logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_UPDATE_ID);
				String dataFields = "is_active = "+CommonConstants.INACTIVE;
				logTableObj.setDataFields(dataFields);
				String whereCondition = "work_id_fk = "+activity.getWorkId();
				logTableObj.setWhereCondition(whereCondition);
				logTableObj.setCreatedBy(activity.getCreatedBy());
				
				CommonMethods.queryLogger(logTableObj, connection);
			}
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
			
			
			String insertQry = "insert into activity_data(data_date,is_active,p6_file_name,created_by,work_id_fk)values(?,?,?,?,?)";
			
			statement = connection.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, activity.getDataDate());
			statement.setString(2, CommonConstants.ACTIVE);
			statement.setString(3, activity.getP6FileName());
			statement.setString(4, activity.getCreatedBy());
			statement.setString(5, activity.getWorkId());
			int count = statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if(count > 0) {
				if (resultSet.next()) {
					activityDataId = String.valueOf(resultSet.getLong(1));
				}
				QyeryLogger logTableObj = new QyeryLogger();
				logTableObj.setFormName("P6 data");
				logTableObj.setTableName("activity_data");
				logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_INSERT_ID);
				String dataFields = "data_date = "+activity.getDataDate()+",is_active = "+CommonConstants.ACTIVE+",p6_file_name = "+activity.getP6FileName()+",created_by = "+activity.getCreatedBy()+",work_id_fk = "+activity.getWorkId();
				logTableObj.setDataFields(dataFields);
				String whereCondition = null;
				logTableObj.setWhereCondition(whereCondition);
				logTableObj.setCreatedBy(activity.getCreatedBy());
				
				CommonMethods.queryLogger(logTableObj, connection);
				
				DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return activityDataId;
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
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select sum(Actual_Percentage)*100 as actualOverallProgress from actual_percentage where work_id = ? and `date` <= Data_Date");
			statement.setString(1, workId);
			resultSet = statement.executeQuery();  
			double actualOverallProgress = 0.00f;
			if(resultSet.next()) {
				actualOverallProgress = resultSet.getDouble("actualOverallProgress");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
			
			
			statement = connection.prepareStatement("select sum(Base_Percentage)*100 as overallBasePercentage from base_percentage where work_id = ? and `date` <= Data_Date");
			statement.setString(1, workId);
			resultSet = statement.executeQuery();  
			double overallBasePercentage = 0.00f;
			if(resultSet.next()) {
				overallBasePercentage = resultSet.getDouble("overallBasePercentage");
			}
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
			
			/*************************************************************************************************************/			
			
			statement = connection.prepareStatement("update `work` set actual_pct = ?,planned_pct = ? where work_id = ?");
			statement.setDouble(1, actualOverallProgress);
			statement.setDouble(2, overallBasePercentage);
			statement.setString(3, workId);
			int c = statement.executeUpdate();
			if(c > 0) {
				flag = true;
				
				
				QyeryLogger logTableObj = new QyeryLogger();
				logTableObj.setFormName("P6 data");
				logTableObj.setTableName("work");
				logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_UPDATE_ID);
				String dataFields = "actual_pct = "+actualOverallProgress+",planned_pct = "+overallBasePercentage;
				logTableObj.setDataFields(dataFields);
				String whereCondition = "work_id = "+workId;
				logTableObj.setWhereCondition(whereCondition);
				logTableObj.setCreatedBy(updatedBy);
				
				CommonMethods.queryLogger(logTableObj, connection);
				
				DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
			}
			
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return flag;
	}


	/**
	 * This method get the activity data
	 * 
	 * @param workId it is string type variable that holds the workId
	 * @return type of this method is obj that is object type of model class Activity  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Activity getActivityData(String workId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			
			String qry = "SELECT ad.id,DATE_FORMAT(data_date,'%d-%m-%Y') AS data_date,"
					+ "is_active,p6_file_name,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date,created_by,"
					+ "DATE_FORMAT(modified_date,'%d-%m-%Y') AS modified_date,modified_by,bs.status,work_id_fk,work_name "
					+ "FROM activity_data ad "
					+ "left outer join binary_store bs on ad.is_active = bs.binary_id "
					+ "left outer join `work` w on ad.work_id_fk = w.work_id "
					+ "where is_active = ? and work_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVE);
			statement.setString(2, workId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				obj = new Activity();
				obj.setActivityDataId(resultSet.getString("ad.id"));
				obj.setDataDate(resultSet.getString("data_date"));
				obj.setIsActive(resultSet.getString("bs.status"));
				obj.setP6FileName(resultSet.getString("p6_file_name"));
				
				obj.setCreatedDate(resultSet.getString("created_date"));
				obj.setCreatedBy(resultSet.getString("created_by"));
				obj.setModifiedDate(resultSet.getString("modified_date"));
				obj.setModifiedBy(resultSet.getString("modified_by"));	
				
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setWorkName(resultSet.getString("work_name"));
			}			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return obj;
	}

	/**
	 * This method update the activities
	 * 
	 * @param activitiesList is list type object
	 * @param workId it is string type variable that holds the workId
	 * @return type of this method is count that is integer type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public int updateActivities(List<Activity> activitiesList, String workId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			connection = dataSource.getConnection();
			
			/*String qry = "UPDATE activity a set activity_status_id_fk = ?,base_start_date = ?,base_finish_date = ?,early_start_date = ?,"
					+ "early_finish_date = ?,act_start_date = ?,act_finish_date = ?,complete_pct = ?,a.float = ? "
					+ "WHERE p6_task_code = ? AND wbs_id_fk = ?";*/
			
			String qry = "UPDATE activity set activity_status_id_fk = ?,early_start_date = ?,"
					+ "early_finish_date = ?,act_start_date = ?,act_finish_date = ?,complete_pct = ?,`float` = ?,boq_completed = ? "
					+ "WHERE p6_task_code = ? AND wbs_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			
			List<QyeryLogger>  logTableObjs = new ArrayList<QyeryLogger>();
			QyeryLogger logTableObj = null;
			for (Activity activity : activitiesList) {
				
				String activityStatusId = null;
				
				if(!StringUtils.isEmpty(activity)) {
					
					String completePct = "0";
					//String boqCompleted = activity.getBoqCompleted();
					String boqTotal = null;
					String boqCompleted = null;
					Activity boqDetails = getBoqDetails(activity.getTaskCode(), activity.getWbsId(), connection);
					if(!StringUtils.isEmpty(boqDetails)) {
						boqTotal = boqDetails.getBoqTotal();
						boqCompleted = boqDetails.getBoqCompleted();
					}
					if(!StringUtils.isEmpty(activity.getActivityStatus())) {
						/*if(activity.getActivityStatus().toLowerCase().equals("not started")) {
							activityStatusId = CommonConstants.ACTIVITY_NOT_STARTED;
						}else if(activity.getActivityStatus().toLowerCase().equals("in progress")) {
							activityStatusId = CommonConstants.ACTIVITY_IN_PROGRESS;
						}else if(activity.getActivityStatus().toLowerCase().equals("completed")) {
							activityStatusId = CommonConstants.ACTIVITY_COMPLETED;
						}*/
						activityStatusId = getActivityStatusId(activity.getActivityStatus(), connection);
						if(!StringUtils.isEmpty(activityStatusId) && activityStatusId.equals(CommonConstants.ACTIVITY_COMPLETED)) {
							completePct = "100";
							//boqCompleted = getBoqTotal(activity.getTaskCode(), activity.getWbsId(), connection);
							boqCompleted = boqTotal;
						}
					}
					int pCount = 1;
					statement.setString(pCount++, activityStatusId);
					/*statement.setString(pCount++, !StringUtils.isEmpty(activity.getBaseStart())?activity.getBaseStart():null);
					statement.setString(pCount++, !StringUtils.isEmpty(activity.getBaseEnd())?activity.getBaseEnd():null);*/
					statement.setString(pCount++, !StringUtils.isEmpty(activity.getPlannedStart())?activity.getPlannedStart():null);
					statement.setString(pCount++, !StringUtils.isEmpty(activity.getPlannedFinish())?activity.getPlannedFinish():null);
					statement.setString(pCount++, !StringUtils.isEmpty(activity.getActualStart())?activity.getActualStart():null);
					statement.setString(pCount++, !StringUtils.isEmpty(activity.getActualFinish())?activity.getActualFinish():null);
					//statement.setString(pCount++, !StringUtils.isEmpty(activity.getPercentageComplete())?activity.getPercentageComplete():null);
					statement.setString(pCount++, completePct);
					statement.setString(pCount++, !StringUtils.isEmpty(activity.getFloatValue())?activity.getFloatValue():null);
					statement.setString(pCount++, !StringUtils.isEmpty(boqCompleted)?boqCompleted:null);
					
					statement.setString(pCount++, activity.getTaskCode());
					statement.setString(pCount++, activity.getWbsId());
					statement.addBatch();
					
					
					/*******************************************************************/
					logTableObj = new QyeryLogger();
					logTableObj.setFormName("P6 data");
					logTableObj.setTableName("activity");
					logTableObj.setQueryTypeId(CommonConstants.QUERY_TYPE_UPDATE_ID);
					String dataFields = "activity_status_id_fk = ?,"
							+ "early_start_date = "+activity.getPlannedStart()
							+ "early_finish_date = "+activity.getPlannedFinish()
							+ "act_start_date = "+activity.getActualStart()
							+ "act_finish_date = "+activity.getActualFinish()
							+ "complete_pct = "+completePct
							+ "float = "+activity.getFloatValue()
							+ "boq_completed = "+boqCompleted;
					logTableObj.setDataFields(dataFields);
					String whereCondition = "p6_task_code = "+activity.getTaskCode()+" AND wbs_id_fk = "+activity.getWbsId();
					logTableObj.setWhereCondition(whereCondition);
					logTableObj.setCreatedBy(activity.getCreatedBy());
					
					logTableObjs.add(logTableObj);
				}
				
			}
			
			int[] c = statement.executeBatch();
			for (int i = 0; i < c.length; i++) {
				count = count + c[i];
			}
			
			if(count > 0) {
				CommonMethods.queryLoggerBatch(logTableObjs, connection);
				DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
			}
			
				
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return count;
	}
	
	/**
	 * This method get the BoqDetails
	 * 
	 * @param taskCode it is string type variable that holds the taskCode
	 * @param wbsId it is string type variable that holds the wbsId
	 * @param connection is object for the Connection Interface
	 * @return type of this method is boqData that is object type of model class Activity  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private Activity getBoqDetails(String taskCode, String wbsId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Activity boqData = null;
		try {
			String qry = "SELECT boq_total,boq_completed FROM activity WHERE p6_task_code = ? AND wbs_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, taskCode);
			statement.setString(2, wbsId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				boqData = new Activity();
				boqData.setBoqTotal(resultSet.getString("boq_total"));	
				boqData.setBoqCompleted(resultSet.getString("boq_completed"));	
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return boqData;
	}
	
	/**
	 * This method get the BoqTotal
	 * 
	 * @param taskCode it is string type variable that holds the taskCode
	 * @param wbsId it is string type variable that holds the wbsId
	 * @param connection is object for the Connection Interface.
	 * @return type of this method is boqTotal that is string type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getBoqTotal(String taskCode, String wbsId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String boqTotal = null;
		try {
			String qry = "SELECT boq_total FROM activity WHERE p6_task_code = ? AND wbs_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, taskCode);
			statement.setString(2, wbsId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				boqTotal = resultSet.getString("boq_total");				
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return boqTotal;
	}
	
	/**
	 * This method get the completePctValue
	 * 
	 * @param taskCode it is string type variable that holds the taskCode
	 * @param wbsId it is string type variable that holds the wbsId
	 * @param connection is object for the Connection Interface
	 * @return type of this method is complete_pct that is String type  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getCompletePctValue(String taskCode, String wbsId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String complete_pct = null;
		try {
			String qry = "SELECT complete_pct FROM activity where WHERE p6_task_code = ? AND wbs_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, taskCode);
			statement.setString(2, wbsId);
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				complete_pct = resultSet.getString("complete_pct");				
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return complete_pct;
	}
	
	/**
	 * This method get the activity status id
	 * 
	 * @param activityStatus it is string type variable that holds the activity status
	 * @param connection is object for the Connection Interface
	 * @return type of this method is activityStatusId that is String type  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getActivityStatusId(String activityStatus, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String activityStatusId = null;
		try {
			String qry = "SELECT status_id FROM `status` where LOWER(status_name) = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, activityStatus.toLowerCase());
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				activityStatusId = resultSet.getString("status_id");				
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return activityStatusId;
	}
	
	/**
	 * This method get the progress history
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is activities that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getProgressHistory(Activity obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> activities = new ArrayList<Activity>();
		Activity activity = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT p.progress_id,DATE_FORMAT(progress_date,'%d-%m-%Y') as reportingDate,p.activity_id_fk,p.percentage_complete,p.issue_id_fk,attachment_url,remarks,DATE_FORMAT(p.created_date,'%d-%m-%Y') as created_date,p.created_by,"
					+ "activity_name,i.description,a.boq_total,a.boq_completed,a.boq_units,"
					+ "wbs_1_code,wbs_2_code,wbs_3_code,wbs_4_code,wbs_5_code,wbs_1_name,wbs_2_name,wbs_3_name,wbs_4_name,wbs_5_name "
					+ "FROM progress p "
					+ "LEFT OUTER JOIN mrvc_activity_view_2 a ON p.activity_id_fk = a.activity_id "
					+ "LEFT OUTER JOIN issues i ON p.issue_id_fk = i.issue_id ";
			
			if(!StringUtils.isEmpty(obj.getWorkId())) {
				qry = qry + "where wbs_5_code = ?";
			}	
			
			qry = qry + " ORDER BY p.created_date DESC";
			
			statement = connection.prepareStatement(qry);

			if(!StringUtils.isEmpty(obj.getWorkId())) {
				statement.setString(1, obj.getWorkId());
			}
						
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				activity = new Activity();
				activity.setProgressId(resultSet.getString("p.progress_id"));
				activity.setReportingDate(resultSet.getString("reportingDate"));
				activity.setTaskId(resultSet.getString("activity_id_fk"));
				activity.setTaskName(resultSet.getString("activity_name"));
				activity.setPercentageComplete(resultSet.getString("percentage_complete"));
				activity.setIssue(resultSet.getString("description"));
				activity.setRemarks(resultSet.getString("remarks"));
				activity.setCreatedBy(resultSet.getString("created_by"));
				activity.setCreatedDate(resultSet.getString("created_date"));
				activity.setInputFilesPath(resultSet.getString("attachment_url"));
				
				activity.setBoqTotal(resultSet.getString("a.boq_total"));
				activity.setCumulativeBoqCompleted(resultSet.getString("a.boq_completed"));
				activity.setBoqUnits(resultSet.getString("a.boq_units"));
				
				/*activity.setWbsName(getWbsName(resultSet.getString("wbs_id_fk"),connection));
				if(!StringUtils.isEmpty(activity.getInputFilesPath())) {
					String folderKey = activity.getInputFilesPath();
					
					List<String> inputFiles = AmazonS3Example.getObjectslistFromFolder(folderKey);
					
					activity.setInputFiles(inputFiles);
				}*/
				
				activity.setWorkId(resultSet.getString("wbs_5_code"));
				activity.setContractId(resultSet.getString("wbs_4_code"));
				activity.setActivityId(resultSet.getString("wbs_3_code"));
				activity.setLocationId(resultSet.getString("wbs_2_code"));
				activity.setActivityTypeId(resultSet.getString("wbs_1_code"));

				activity.setWorkName(resultSet.getString("wbs_5_name"));
				activity.setContractName(resultSet.getString("wbs_4_name"));
				activity.setActivity(resultSet.getString("wbs_3_name"));
				activity.setLocation(resultSet.getString("wbs_2_name"));
				activity.setActivityType(resultSet.getString("wbs_1_name"));
				
				activities.add(activity);
			}			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return activities;
	}
	
	
	/**
	 * This method get the current status activities
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getCurrentStatusActivities(Activity obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Activity> objsList = new ArrayList<Activity>();		
		Activity rObj = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			String qry = "SELECT activity_id,p6_task_code,activity_name,DATE_FORMAT(base_start_date,'%d-%m-%Y') AS base_start_date,DATE_FORMAT(base_finish_date,'%d-%m-%Y') AS base_finish_date,"
					+ "DATE_FORMAT(early_start_date,'%d-%m-%Y') AS early_start_date,DATE_FORMAT(early_finish_date,'%d-%m-%Y') AS early_finish_date,DATE_FORMAT(act_start_date,'%d-%m-%Y') AS act_start_date,DATE_FORMAT(act_finish_date,'%d-%m-%Y') AS act_finish_date,"
					+ "activity_weight,activity_status_id,activity_status,"
					+ "complete_pct,boq_total,boq_completed,boq_units,wbs_1_code as activityTypeId,wbs_2_code as locationId,wbs_3_code as activityId,wbs_4_code as contractId,wbs_5_code as workId,"
					+ "wbs_1_name,wbs_2_name,wbs_3_name,wbs_4_name,wbs_5_name "
					+ "FROM mrvc_activity_view_2 where activity_id is not null ";
			
			if(!StringUtils.isEmpty(obj.getActivityStatusId())) {
				qry = qry + "and activity_status_id = ? ";
			}
			
			if(!StringUtils.isEmpty(obj.getActivityTypeId())) {
				qry = qry + "and wbs_1_code = ? ";
			}
			if(!StringUtils.isEmpty(obj.getLocationId())) {
				qry = qry + "and wbs_2_code = ? ";
			}
			if(!StringUtils.isEmpty(obj.getActivityId())) {
				qry = qry + "and wbs_3_code = ? ";
			}
			if(!StringUtils.isEmpty(obj.getContractId())) {
				qry = qry + "and wbs_4_code = ? ";
			}
			if(!StringUtils.isEmpty(obj.getWorkId())) {
				qry = qry + "and wbs_5_code = ? ";
			}
			
			statement = connection.prepareStatement(qry);

			int c = 1;
			
			if(!StringUtils.isEmpty(obj.getActivityStatusId())) {
				statement.setString(c++, obj.getActivityStatusId());
			}
			
			if(!StringUtils.isEmpty(obj.getActivityTypeId())) {
				statement.setString(c++, obj.getActivityTypeId());
			}
			if(!StringUtils.isEmpty(obj.getLocationId())) {
				statement.setString(c++, obj.getLocationId());
			}
			if(!StringUtils.isEmpty(obj.getActivityId())) {
				statement.setString(c++, obj.getActivityId());
			}
			if(!StringUtils.isEmpty(obj.getContractId())) {
				statement.setString(c++, obj.getContractId());
			}
			if(!StringUtils.isEmpty(obj.getWorkId())) {
				statement.setString(c++, obj.getWorkId());
			}
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				rObj = new Activity();
				rObj.setActivityId(resultSet.getString("activity_id"));
				rObj.setTaskId(resultSet.getString("p6_task_code"));
				rObj.setTaskName(resultSet.getString("activity_name"));
				rObj.setActivityStatusId(resultSet.getString("activity_status_id"));
				rObj.setActivityStatus(resultSet.getString("activity_status"));
				rObj.setBaseStart(resultSet.getString("base_start_date"));
				rObj.setBaseEnd(resultSet.getString("base_finish_date"));
				rObj.setPlannedStart(resultSet.getString("early_start_date"));
				rObj.setPlannedFinish(resultSet.getString("early_finish_date"));
				rObj.setActualStart(resultSet.getString("act_start_date"));
				rObj.setActualFinish(resultSet.getString("act_finish_date"));
				rObj.setWeight(resultSet.getString("activity_weight"));
				rObj.setActivityWeight(resultSet.getString("activity_weight"));
				
				rObj.setBoqTotal(resultSet.getString("boq_total"));
				rObj.setBoqCompleted(resultSet.getString("boq_completed"));
				rObj.setBoqUnits(resultSet.getString("boq_units"));
				
				
				rObj.setActivityTypeId(resultSet.getString("activityTypeId"));
				rObj.setLocationId(resultSet.getString("locationId"));
				rObj.setWbs3CodeActivityId(resultSet.getString("activityId"));
				rObj.setContractId(resultSet.getString("contractId"));
				rObj.setWorkId(resultSet.getString("workId"));
				
				rObj.setWorkName(resultSet.getString("wbs_5_name"));
				rObj.setContractName(resultSet.getString("wbs_4_name"));
				rObj.setActivity(resultSet.getString("wbs_3_name"));
				rObj.setLocation(resultSet.getString("wbs_2_name"));
				rObj.setActivityType(resultSet.getString("wbs_1_name"));
				objsList.add(rObj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}

	/**
	 * This method get the WbsName
	 * 
	 * @param wbsId it is string type variable that holds the wbsId
	 * @param connection is object for the Connection Interface
	 * @return type of this method is wbsName that is String type  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private String getWbsName(String wbsId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String wbsName = null;
		try {
			String qry = "SELECT wbs_name FROM wbs WHERE wbs_id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, wbsId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				wbsName = resultSet.getString("wbs_name");
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return wbsName;
	}

	/**
	 * This method get the input file path
	 * 
	 * @param progressId it is string type variable that holds the progress id
	 * @return type of this method is filePath that is String type  
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public String getInputFilePath(String progressId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String filePath = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT attachment_url FROM progress WHERE progress_id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, progressId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				filePath = resultSet.getString("attachment_url");
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return filePath;
	}

	/**
	 * This method get the activity data history
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getActivityDataHistory() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "SELECT ad.id,DATE_FORMAT(data_date,'%d-%m-%Y') AS data_date,"
					+ "is_active,p6_file_name,DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date,created_by,"
					+ "DATE_FORMAT(modified_date,'%d-%m-%Y') AS modified_date,modified_by,bs.status,work_id_fk,work_name "
					+ "FROM activity_data ad "
					+ "left outer join binary_store bs on ad.is_active = bs.binary_id "
					+ "left outer join `work` w on ad.work_id_fk = w.work_id "
					+ "order by created_date desc";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setActivityDataId(resultSet.getString("ad.id"));
				obj.setDataDate(resultSet.getString("data_date"));
				obj.setIsActive(resultSet.getString("bs.status"));
				obj.setP6FileName(resultSet.getString("p6_file_name"));
				
				obj.setCreatedDate(resultSet.getString("created_date"));
				obj.setCreatedBy(resultSet.getString("created_by"));
				obj.setModifiedDate(resultSet.getString("modified_date"));
				obj.setModifiedBy(resultSet.getString("modified_by"));	
				
				obj.setWorkId(resultSet.getString("work_id_fk"));
				obj.setWorkName(resultSet.getString("work_name"));
				
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the works
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getWorks() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select work_id,work_name from `work` where status_id_fk <> ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setWorkId(resultSet.getString("work_id"));
				obj.setWorkName(resultSet.getString("work_name"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the projects list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getProjectsListForSearch() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity obj = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select project_id,project_description from project";
			
			statement = connection.prepareStatement(qry);
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setProjectId(resultSet.getString("project_id"));
				obj.setProjectName(resultSet.getString("project_description"));
				objsList.add(obj);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}


	/**
	 * This method get the works list for search
	 * 
	 * @param obj is object for the model class Activity
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Activity> getWorksListForSearch(Activity obj) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objsList = new ArrayList<Activity>();
		Activity work = null;
		try {
			connection = dataSource.getConnection();
			String qry = "select work_id,work_name from `work` where project_id_fk = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, obj.getProjectId());
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				work = new Activity();
				work.setWorkId(resultSet.getString("work_id"));
				work.setWorkName(resultSet.getString("work_name"));
				objsList.add(work);
			}			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objsList;
	}
	
	/**
	 * This method get the due activities
	 * 
	 * @param workId it is string type variable that holds the workId
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Notification> getDueActivities(String workId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Notification> objects = new ArrayList<Notification>();
		Notification obj = null;
		try {
			
			connection = dataSource.getConnection();
			
			/*String qry = "SELECT wbs_5_code,wbs_5_name "
					+ "FROM mrvc_activity_view_2 a where ((activity_status_id = ? and DATE(NOW()) > DATE(a.base_start_date)) or "
					+ "(activity_status_id = ? and DATE(NOW()) > DATE(a.base_finish_date))) ";
			
			if(!StringUtils.isEmpty(workId)) {
				qry = qry + "and wbs_5_code = ?";
			}	
			
			qry = qry + " group by wbs_5_code";*/
			
			String qry = "SELECT n.work_id,w.work_name FROM notifications n "
					+ "left outer join `work` w on n.work_id = w.work_id "
					+ "where close_open_status = ?";
			
			if(!StringUtils.isEmpty(workId)) {
				qry = qry + " and n.work_id = ?";
			}	
			
			qry = qry + " group by n.work_id ";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, "Open");

			if(!StringUtils.isEmpty(workId)) {
				statement.setString(2, workId);
			}
			
			/*statement.setString(1, CommonConstants.ACTIVITY_NOT_STARTED);
			statement.setString(2, CommonConstants.ACTIVITY_IN_PROGRESS);
			if(!StringUtils.isEmpty(workId)) {
				statement.setString(3, workId);
			}*/
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Notification();
				//obj.setWorkId(resultSet.getString("wbs_5_code"));
				//obj.setWorkName(resultSet.getString("wbs_5_name"));
				obj.setWorkId(resultSet.getString("n.work_id"));
				obj.setWorkName(resultSet.getString("w.work_name"));
				//obj.setDueActivities(getDueActivitiesByWork(obj.getWorkId(),connection));
				obj.setDueActivities(getDueActivitiesByWork(obj.getWorkId(),connection));
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return objects;
	}	
	
	/**
	 * This method get the due activities by work
	 * @param workId it is string type variable that holds the workId
	 * @param connection is object for the Connection Interface
	 * @return type of this method is objects that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Notification> getDueActivitiesByWork(String workId, Connection connection) throws Exception {
	//public List<Activity> getDueActivitiesByWork(String workId, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Notification> objects = new ArrayList<Notification>();
		Notification obj = null;
		try {
			/*String qry = "SELECT activity_id,p6_task_code,activity_name,DATE_FORMAT(base_start_date,'%d-%m-%Y') AS base_start_date,DATE_FORMAT(base_finish_date,'%d-%m-%Y') AS base_finish_date,"
					+ "DATE_FORMAT(early_start_date,'%d-%m-%Y') AS early_start_date,DATE_FORMAT(early_finish_date,'%d-%m-%Y') AS early_finish_date,DATE_FORMAT(act_start_date,'%d-%m-%Y') AS act_start_date,DATE_FORMAT(act_finish_date,'%d-%m-%Y') AS act_finish_date,"
					+ "activity_weight,activity_status_id,activity_status,"
					+ "complete_pct,boq_total,boq_completed,boq_units,"
					+ "wbs_1_code,wbs_2_code,wbs_3_code,wbs_4_code,wbs_5_code,wbs_1_name,wbs_2_name,wbs_3_name,wbs_4_name,wbs_5_name "
					+ "FROM mrvc_activity_view_2 a "
					+ "where ((activity_status_id = ? and DATE(NOW()) > DATE(a.base_start_date)) or "
					+ "(activity_status_id = ? and DATE(NOW()) > DATE(a.base_finish_date))) ";
			
			if(!StringUtils.isEmpty(workId)) {
				qry = qry + "and wbs_5_code = ?";
			}	
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_NOT_STARTED);
			statement.setString(2, CommonConstants.ACTIVITY_IN_PROGRESS);
			if(!StringUtils.isEmpty(workId)) {
				statement.setString(3, workId);
			}*/
			
			String qry = "SELECT id,user_id,work_id,title,description,image,link,read_unread_status,close_open_status,read_date_time,"
					+ "DATE_FORMAT(created_date,'%d-%m-%Y') AS created_date,created_date AS created_date2  "
					+ "FROM notifications where close_open_status = ?";
			
			if(!StringUtils.isEmpty(workId)) {
				qry = qry + " and work_id = ?";
			}	
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, "Open");

			if(!StringUtils.isEmpty(workId)) {
				statement.setString(2, workId);
			}
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Notification();

				obj.setNotificationId(resultSet.getString("id"));
				obj.setUserId(resultSet.getString("user_id"));
				obj.setWorkId(resultSet.getString("work_id"));
				obj.setTitle(resultSet.getString("title"));
				obj.setDescription(resultSet.getString("description"));
				obj.setImage(resultSet.getString("image"));
				obj.setNotificationLink(resultSet.getString("link")+"&notificationId="+EncryptDecrypt.base64Encode(obj.getNotificationId()));
				obj.setReadUnreadStatus(resultSet.getString("read_unread_status"));
				obj.setCloseOpenStatus(resultSet.getString("close_open_status"));
				obj.setReadDateTime(resultSet.getString("read_date_time"));
				obj.setCreatedDate(resultSet.getString("created_date"));

				String str = obj.getDescription();
				
				if(!StringUtils.isEmpty(str)) {
					String[] description  = str.split("\\$#");
					obj.setLocation(description[0].trim());
					obj.setActivityType(description[1].trim());
					obj.setActivity(description[2].trim());
					obj.setBoqCompleteTotal(description[3].trim());
					
					if(!StringUtils.isEmpty(obj.getBoqCompleteTotal())) {
						obj.setBoqCompleteTotalForMailNotification((description[3].substring(4, description[3].length())).trim());
					}
				}
				
				if(!StringUtils.isEmpty(obj.getCreatedDate())){
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        Date date = format.parse(resultSet.getString("created_date2"));
			        Date currentDate = format.parse(format.format(new Date()));
			        long currTime = currentDate.getTime();
			        long userTime = date.getTime();
			        long time =  currTime - userTime ;
			        obj.setTimeAgo(TimeAgo.toDuration(time));
				}
				
				objects.add(obj);
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return objects;
	}
	
	/**
	 * This method create notifications
	 * 
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean createNotifications() throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Activity> objects = new ArrayList<Activity>();
		Activity obj = null;
		boolean flag = false;
		try {
			
			connection = dataSource.getConnection();
			
			/*String qry = "SELECT activity_id,p6_task_code,activity_name,DATE_FORMAT(base_start_date,'%d-%m-%Y') AS base_start_date,DATE_FORMAT(base_finish_date,'%d-%m-%Y') AS base_finish_date,"
					+ "DATE_FORMAT(early_start_date,'%d-%m-%Y') AS early_start_date,DATE_FORMAT(early_finish_date,'%d-%m-%Y') AS early_finish_date,DATE_FORMAT(act_start_date,'%d-%m-%Y') AS act_start_date,DATE_FORMAT(act_finish_date,'%d-%m-%Y') AS act_finish_date,"
					+ "activity_weight,activity_status_id,activity_status,"
					+ "complete_pct,boq_total,boq_completed,boq_units,"
					+ "wbs_1_code,wbs_2_code,wbs_3_code,wbs_4_code,wbs_5_code,wbs_1_name,wbs_2_name,wbs_3_name,wbs_4_name,wbs_5_name "
					+ "FROM mrvc_activity_view_2 a "
					+ "where ((activity_status_id = ? and DATE(NOW()) > DATE(a.base_start_date)) or "
					+ "(activity_status_id = ? and DATE(NOW()) > DATE(a.base_finish_date))) and boq_total is not null and boq_total <> '' and boq_units is not null and boq_units <> ''";*/
			
			
			String qry = "SELECT activity_id,p6_task_code,activity_name,DATE_FORMAT(base_start_date,'%d-%m-%Y') AS base_start_date,DATE_FORMAT(base_finish_date,'%d-%m-%Y') AS base_finish_date,"
					+ "DATE_FORMAT(early_start_date,'%d-%m-%Y') AS early_start_date,DATE_FORMAT(early_finish_date,'%d-%m-%Y') AS early_finish_date,DATE_FORMAT(act_start_date,'%d-%m-%Y') AS act_start_date,DATE_FORMAT(act_finish_date,'%d-%m-%Y') AS act_finish_date,"
					+ "activity_weight,activity_status_id,activity_status,"
					+ "complete_pct,boq_total,boq_completed,boq_units,"
					+ "wbs_1_code,wbs_2_code,wbs_3_code,wbs_4_code,wbs_5_code,wbs_1_name,wbs_2_name,wbs_3_name,wbs_4_name,wbs_5_name "
					+ "FROM mrvc_activity_view_2 a "
					+ "where activity_status_id <> ? and DATE(NOW()) > DATE(a.base_finish_date)";
			
			
			
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, CommonConstants.ACTIVITY_COMPLETED);
			
			//statement.setString(1, CommonConstants.ACTIVITY_NOT_STARTED);
			//statement.setString(2, CommonConstants.ACTIVITY_IN_PROGRESS);
			
			resultSet = statement.executeQuery();  
			while(resultSet.next()) {
				obj = new Activity();
				obj.setTaskId(resultSet.getString("activity_id"));
				obj.setTaskCode(resultSet.getString("p6_task_code"));
				obj.setTaskName(resultSet.getString("activity_name"));
				obj.setActivityStatusId(resultSet.getString("activity_status_id"));
				obj.setActivityStatus(resultSet.getString("activity_status"));
				obj.setBaseStart(resultSet.getString("base_start_date"));
				obj.setBaseEnd(resultSet.getString("base_finish_date"));
				obj.setPlannedStart(resultSet.getString("early_start_date"));
				obj.setPlannedFinish(resultSet.getString("early_finish_date"));
				obj.setActualStart(resultSet.getString("act_start_date"));
				obj.setActualFinish(resultSet.getString("act_finish_date"));
				obj.setWeight(resultSet.getString("activity_weight"));
				obj.setActivityWeight(resultSet.getString("activity_weight"));
				obj.setPercentageComplete(resultSet.getString("complete_pct"));
				
				String boqTotal = resultSet.getString("boq_total");
				if(!StringUtils.isEmpty(boqTotal) && Float.parseFloat(boqTotal) > 0) {
					obj.setBoqTotal(resultSet.getString("boq_total"));
				}else {
					obj.setBoqTotal("");
				}
				
				obj.setBoqCompleted(!StringUtils.isEmpty(resultSet.getString("boq_completed"))?resultSet.getString("boq_completed"):"0");
				obj.setBoqUnits(!StringUtils.isEmpty(resultSet.getString("boq_units"))?resultSet.getString("boq_units"):"");

				obj.setWorkId(resultSet.getString("wbs_5_code"));
				obj.setContractId(resultSet.getString("wbs_4_code"));
				obj.setActivityId(resultSet.getString("wbs_3_code"));
				obj.setLocationId(resultSet.getString("wbs_2_code"));
				obj.setActivityTypeId(resultSet.getString("wbs_1_code"));

				obj.setWorkName(resultSet.getString("wbs_5_name"));
				obj.setContractName(resultSet.getString("wbs_4_name"));
				obj.setActivity(resultSet.getString("wbs_3_name"));
				obj.setLocation(resultSet.getString("wbs_2_name"));
				obj.setActivityType(resultSet.getString("wbs_1_name"));
				
				if(!StringUtils.isEmpty(obj.getTaskId()) && !StringUtils.isEmpty(obj.getWorkId())) {
					String baseUrl = CommonConstants.CONTEXT_PATH+CommonConstants.NOTIFICATIONS_URL+"?taskId="+EncryptDecrypt.base64Encode(obj.getTaskId())+"&workId="+EncryptDecrypt.base64Encode(obj.getWorkId())+"&workName="+EncryptDecrypt.base64Encode(obj.getWorkName());
					obj.setActivityUpdateLink(baseUrl);
				}
				
				objects.add(obj);
			}
			
			createNotifications(objects,connection);
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return flag;
	}
	
	
	/**
	 * This method create notifications
	 * 
	 * @param objects is list type object 
	 * @param connection is object for the Connection Interface
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private boolean createNotifications(List<Activity> objects, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		
		try {
			String insertQry = "insert into notifications(work_id,title,description,link,read_unread_status,close_open_status)values(?,?,?,?,?,?)";
			
			statement = connection.prepareStatement(insertQry);
			
			for (Activity obj : objects) {
				String description = null;
				
				if(!StringUtils.isEmpty(obj.getBoqTotal()) && Float.parseFloat(obj.getBoqTotal()) > 0) {
					description = obj.getLocation() + " $# "+ obj.getActivityType() + " $# "+ obj.getTaskName() + " $# BOQ(" +obj.getBoqCompleted() + "/" + obj.getBoqTotal() + ") "+obj.getBoqUnits();
				}else {
					description = obj.getLocation() + " $# "+ obj.getActivityType() + " $# "+ obj.getTaskName() + " $# "+"";
				}
				
				
				boolean tempFlag = checkNotification(obj.getWorkId(),description,connection);
				
				if(!tempFlag) {
					int c = 1;
					statement.setString(c++, obj.getWorkId());
					statement.setString(c++, "Delayed activities");
					statement.setString(c++, description);
					statement.setString(c++, obj.getActivityUpdateLink());
					statement.setString(c++, "Unread");
					statement.setString(c++, "Open");
					statement.addBatch();
				}				
			}
			
			int[] count = statement.executeBatch();
			if(count.length > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return flag;
	}
	
	/**
	 * This method check notification
	 * 
	 * @param workId it is string type variable that holds the workId
	 * @param description it is string type variable that holds the description
	 * @param connection is object for the Connection Interface
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	private boolean checkNotification(String workId, String description, Connection connection) throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			
			String qry = "SELECT work_id FROM notifications where work_id = ? and description = ? and close_open_status = ?";
			
			statement = connection.prepareStatement(qry);

			statement.setString(1, workId);
			statement.setString(2, description);
			statement.setString(3, "Open");
			
			resultSet = statement.executeQuery();  
			if(resultSet.next()) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
		}
		return flag;
	}
	
	/**
	 * This method read the notification
	 * 
	 * @param notificationId it is string type variable that holds the notification id
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean readNotification(String notificationId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			String qry = "UPDATE notifications set read_date_time = CURRENT_TIMESTAMP, read_unread_status = ? WHERE id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, "Read");
			statement.setString(2, notificationId);
			int c = statement.executeUpdate();
			if (c > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return flag;
	}
	
	/**
	 * This method close the notification
	 * 
	 * @param notificationId it is string type variable that holds the notification id
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean closeNotification(String notificationId) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			String qry = "UPDATE notifications set  close_date_time = CURRENT_TIMESTAMP,close_open_status = ? WHERE id = ?";
			
			statement = connection.prepareStatement(qry);
			statement.setString(1, "Close");
			statement.setString(2, notificationId);
			int c = statement.executeUpdate();
			if (c > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return flag;
	}
	
}
