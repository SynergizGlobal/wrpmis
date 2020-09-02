package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Activity {
	
	private String activityId,activity,activityTypeId,activityType,taskId,taskName,categoryId,category,reportingDate,actualStart,actualFinish,
	location,locationId,issueCategory,issueDesc,remarks,issue,createdById,markAsComplete,
	activityWork,fileName,activityStatusId,activityStatus,userId,userName,createdBy,createdDate,
	baseStart,baseEnd,percentageComplete,percentageRemaining,percentageWorkDone,wbsId,wbsName,
	parentWbsId,taskCode,floatValue,weight,plannedStart,plannedFinish,activityComplete,progressId,
	activityWeight,statusId,latitude,longitude,filesPath,inputFilesPath,boqCompleted,boqTotal,boqUnits,boqCompletedOnThisDate,boqRemaining,moduleId,moduleName,workModuleStatus;
	
	private List<MultipartFile> mediaFile;
	private List<MultipartFile> attach_file;
	private MultipartFile p6File;
	private List<String> inputFiles;
	private List<Activity> dueActivities;
	private MultipartFile activityFile;
	
	private String wbsWeight,wbsCategoryId,wbsCategory,contractId,contractName,issueCategoryId,issuePriorityId;
	
	private String activityDataId,dataDate,isActive,p6FileName,modifiedDate,modifiedBy,baseCost;
	
	private String workId,workName,projectId,projectName,globalWorkId,globalWorkName,wbs3CodeActivityId;
	
	private String activityUpdateLink;
	private String notificationActivityId,notificationWorkId,notificationWorkName;
	
	private String cumulativeBoqCompleted,notificationId;
	
	/**
	 *This method get the BoqCompletedonThisDate. 
	 * @return type of this method is boqCompletedOnThisDate.
	 */
	public String getBoqCompletedOnThisDate() {
		return boqCompletedOnThisDate;
	}
	/**
	 * This method set the BoqCompletedOnThisDate
	 * @param boqCompletedOnThisDate it is string type variable that hold the boqCompletedThisDate
	 */
	public void setBoqCompletedOnThisDate(String boqCompletedOnThisDate) {
		this.boqCompletedOnThisDate = boqCompletedOnThisDate;
	}
	/**
	 * This method get the BoqRemainig
	 * @return type of this method is boqRemaining
	 */
	public String getBoqRemaining() {
		return boqRemaining;
	}
	/**
	 * This method set the BoqRemaining
	 * @param boqRemaining it is string type variable that hold the boqRemaining.
	 */
	public void setBoqRemaining(String boqRemaining) {
		this.boqRemaining = boqRemaining;
	}
	/**
	 * This method get the BoqTotal
	 * @return type of this method is boqTotal
	 */
	public String getBoqTotal() {
		return boqTotal;
	}
	/**
	 * This method set the BoqTotal
	 * @param boqTotal it is string type variable that hold the boqTotal.
	 */
	public void setBoqTotal(String boqTotal) {
		this.boqTotal = boqTotal;
	}
	/**
	 * This method get the BoqUnits
	 * @return type of this method is boqUnits
	 */
	public String getBoqUnits() {
		return boqUnits;
	}
	/**
	 * This method set the BoqUnits
	 * @param boqUnits it is string type variable that hold the boqUnits
	 */
	public void setBoqUnits(String boqUnits) {
		this.boqUnits = boqUnits;
	}
	/**
	 * This method get the Project id
	 * @return type of this method is projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * This method set the Project id
	 * @param projectId it is string type variable that hold the project id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * This method get the Project name
	 * @return type of this method is projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * This method set the project name
	 * @param projectName it is string type variable that hold the project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * This method get the work id
	 * @return type of this method is workId
	 */
	public String getWorkId() {
		return workId;
	}
	/**
	 * This method set the work id
	 * @param workId it is string type variable that hold the work id
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	/**
	 * This method get the work name
	 * @return type of this method is work name
	 */
	public String getWorkName() {
		return workName;
	}
	/**
	 * This method set the work name
	 * @param workName it is string type variable that hold the work name
	 */
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	/**
	 * This method get the activity id
	 * @return of this method is activityId
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * This method set the activity id
	 * @param activityId it is string type variable that hold the activity id
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * This method get the activity
	 * @return type of this method is activity
	 */
	public String getActivity() {
		return activity;
	}
	/**
	 * This method set the activity
	 * @param activity it is string type variable that hold the activity
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}
	/**
	 * This method get the activity type id
	 * @return type of this method is activityTypeId
	 */
	public String getActivityTypeId() {
		return activityTypeId;
	}
	/**
	 * This method set the activity type id
	 * @param activityTypeId it is string type variable that hold the activity type id
	 */
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	/**
	 * This method get the activity type
	 * @return type of this method is activity type
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * This method set the activity type 
	 * @param activityType it is string type variable that hold the activity type
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * This method get the category id
	 * @return type of this method is categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * This method set the category id
	 * @param categoryId it is string type variable that hold the category id
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * This method get the category
	 * @return type of this method is category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * This method set the category
	 * @param category it is string type variable that hold the category.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * This method get the reporting date
	 * @return type of this method is reporting date
	 */
	public String getReportingDate() {
		return reportingDate;
	}
	/**
	 * This method set the reporting date
	 * @param reportingDate it is string type variable that hold the reporting date
	 */
	public void setReportingDate(String reportingDate) {
		this.reportingDate = reportingDate;
	}
	/**
	 * This method get the actual start
	 * @return type of this method is actualStart
	 */
	public String getActualStart() {
		return actualStart;
	}
	/**
	 * This method is set the actual start
	 * @param actualStart it is string type variable that hold the actual start
	 */
	public void setActualStart(String actualStart) {
		this.actualStart = actualStart;
	}
	/**
	 * This method get the actual finish
	 * @return type of this method is actual finish.
	 */
	public String getActualFinish() {
		return actualFinish;
	}
	/**
	 * This method set the actual finish
	 * @param actualFinish it is string type variable that hold the actual finish
	 */
	public void setActualFinish(String actualFinish) {
		this.actualFinish = actualFinish;
	}
	/**
	 * This method get the location
	 * @return type of this method is location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * This method set the location
	 * @param location it is string type variable that hold the location.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * This method get the location id
	 * @return type of this method is locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * This method set the location id
	 * @param locationId it is string type variable that hold the location id
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * This method get the issue category
	 * @return type of this method is issueCategory
	 */
	public String getIssueCategory() {
		return issueCategory;
	}
	/**
	 * This method set the issue category
	 * @param issueCategory it is string type variable that hold the issue category
	 */
	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}
	/**
	 * This method get the issueDesc
	 * @return type of this method is issueDesc.
	 */
	public String getIssueDesc() {
		return issueDesc;
	}
	/**
	 * This method set the isseueDesc
	 * @param issueDesc it is string type variable that hold the issueDesc.
	 */
	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}
	/**
	 * This method get the remarks
	 * @return type of this method is remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * This method set the remarks
	 * @param remarks it is string type variable that hold the remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * This method get the issue
	 * @return type of this method is issue
	 */
	public String getIssue() {
		return issue;
	}
	/**
	 * This method set the issue
	 * @param issue it is string type variable that hold the issue
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}
	/**
	 * This method get the created By id
	 * @return type of this method is createdById
	 */
	public String getCreatedById() {
		return createdById;
	}
	/**
	 * This method set the createdById
	 * @param createdById it is string type variable that hold the created by id
	 */
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	/**
	 * This method get the mark as complete
	 * @return type of this method is markAsComplete
	 */
	public String getMarkAsComplete() {
		return markAsComplete;
	}
	/**
	 * This method set the MarkAsComplete
	 * @param markAsComplete it is string type variable that hold the markAsComplete
	 */
	public void setMarkAsComplete(String markAsComplete) {
		this.markAsComplete = markAsComplete;
	}
	/**
	 * This method get the file name
	 * @return type of this method is filename
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * This method set the file name
	 * @param fileName it is string type variable that hold the filename
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * This method get the activity status id
	 * @return type of this method is activityStatusId
	 */
	public String getActivityStatusId() {
		return activityStatusId;
	}
	/**
	 * This method set the activity status id
	 * @param activityStatusId it is string type variable that hold the activity status id
	 */
	public void setActivityStatusId(String activityStatusId) {
		this.activityStatusId = activityStatusId;
	}
	/**
	 * This method get the activity status
	 * @return type of this method is activity status
	 */
	public String getActivityStatus() {
		return activityStatus;
	}
	/**
	 * This method set the activity status
	 * @param activityStatus it is string type variable that hold the activity status
	 */
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	/**
	 * This method get the base start
	 * @return type of this method is baseStart.
	 */
	public String getBaseStart() {
		return baseStart;
	}
	/**
	 * This method set the Base Start
	 * @param baseStart it is string type variable that hold the base start
	 */
	public void setBaseStart(String baseStart) {
		this.baseStart = baseStart;
	}
	/**
	 * This method get the base end
	 * @return type of this method is baseEnd
	 */
	public String getBaseEnd() {
		return baseEnd;
	}
	/**
	 * This method set the base end
	 * @param baseEnd it is string type variable that hold the base end
	 */
	public void setBaseEnd(String baseEnd) {
		this.baseEnd = baseEnd;
	}
	/**
	 * This method get the percentage complete
	 * @return type of this method is percentageComplete
	 */
	public String getPercentageComplete() {
		return percentageComplete;
	}
	/**
	 * This method set the percentage complete
	 * @param percentageComplete it is string type variable that hold the percentage complete
	 */
	public void setPercentageComplete(String percentageComplete) {
		this.percentageComplete = percentageComplete;
	}
	/**
	 * This method get the percentage remaining
	 * @return type of this method is percentageRemaining
	 */
	public String getPercentageRemaining() {
		return percentageRemaining;
	}
	/**
	 * This method set the percentage remaining
	 * @param percentageRemaining it is string type variable that hold the percentage remaining
	 */
	public void setPercentageRemaining(String percentageRemaining) {
		this.percentageRemaining = percentageRemaining;
	}
	/**
	 * This method get the percentage work done
	 * @return type of this method is percentageWorkDone
	 */
	public String getPercentageWorkDone() {
		return percentageWorkDone;
	}
	/**
	 * This method set the percentage work done
	 * @param percentageWorkDone it is string type variable that hold the percentage work done
	 */
	public void setPercentageWorkDone(String percentageWorkDone) {
		this.percentageWorkDone = percentageWorkDone;
	}
	/**
	 * This method get the Wbs Id
	 * @return type of this method is wbsId
	 */
	public String getWbsId() {
		return wbsId;
	}
	/**
	 * This method set the Wbs Id
	 * @param wbsId it is string type variable that hold the wbsId
	 */
	public void setWbsId(String wbsId) {
		this.wbsId = wbsId;
	}
	/**
	 * This method get the wbs name
	 * @return type of this method is wbs name
	 */
	public String getWbsName() {
		return wbsName;
	}
	/**
	 * This method set the wbs name
	 * @param wbsName it is string type variable that hold the wbs name
	 */
	public void setWbsName(String wbsName) {
		this.wbsName = wbsName;
	}
	/**
	 * This method get the parent wbs id
	 * @return type of this method is parentWbsId
	 */
	public String getParentWbsId() {
		return parentWbsId;
	}
	/**
	 * This method set the parent wbs id
	 * @param parentWbsId it is string type variable that hold the parent wbs id
	 */
	public void setParentWbsId(String parentWbsId) {
		this.parentWbsId = parentWbsId;
	}
	/**
	 * This method get the task code
	 * @return type of this method is taskCode
	 */
	public String getTaskCode() {
		return taskCode;
	}
	/**
	 * This method set the task code
	 * @param taskCode it is string type variable that hold the task code
	 */
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	/**
	 * This method get the float value
	 * @return type of this method is floatValue.
	 */
	public String getFloatValue() {
		return floatValue;
	}
	/**
	 * This method  set the float value
	 * @param floatValue it is string type variable that hold the float value
	 */
	public void setFloatValue(String floatValue) {
		this.floatValue = floatValue;
	}
	/**
	 * This method get the weight
	 * @return type of this method is weight
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * This method set the weight
	 * @param weight it is string type variable that hold the weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * This method get the planned start
	 * @return type of this method is plannedStart
	 */
	public String getPlannedStart() {
		return plannedStart;
	}
	/**
	 * This method set the plannedStart
	 * @param plannedStart it is string type variable that hold the plannedStart
	 */
	public void setPlannedStart(String plannedStart) {
		this.plannedStart = plannedStart;
	}
	/**
	 * This method get the planned finish
	 * @return type of this method is plannedFinish
	 */
	public String getPlannedFinish() {
		return plannedFinish;
	}
	/**
	 * This method set the planned finish
	 * @param plannedFinish it is string type variable that hold the planned finish
	 */
	public void setPlannedFinish(String plannedFinish) {
		this.plannedFinish = plannedFinish;
	}
	/**
	 * This method get the activity complete
	 * @return type of this method is activity complete
	 */
	public String getActivityComplete() {
		return activityComplete;
	}
	/**
	 * This method set the activity complete
	 * @param activityComplete it is string type variable that hold the activity complete
	 */
	public void setActivityComplete(String activityComplete) {
		this.activityComplete = activityComplete;
	}
	/**
	 * This method get the progress id
	 * @return type of this method is progressId
	 */
	public String getProgressId() {
		return progressId;
	}
	/**
	 * This method set the progress id
	 * @param progressId it is string type variable that hold the progress id 
	 */
	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}
	/**
	 * This method get the activity weight
	 * @return type of this method is activityWeight.
	 */
	public String getActivityWeight() {
		return activityWeight;
	}
	/**
	 * This method set the activity weight
	 * @param activityWeight it is string type variable that hold the activity weight
	 */
	public void setActivityWeight(String activityWeight) {
		this.activityWeight = activityWeight;
	}
	/**
	 * This method get the status id
	 * @return type of this method is statusId
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * This method set the status id
	 * @param statusId it is string type variable that hold the status id
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * This method get the latitude
	 * @return type of this method is latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * This method set the latitude
	 * @param latitude it is string type variable that hold the latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * This method get the longitude
	 * @return type of this method is longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * This method set the longitude
	 * @param longitude it is string type variable that hold the longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * This method get the media file
	 * @return type of this method is mediaFile
	 */
	public List<MultipartFile> getMediaFile() {
		return mediaFile;
	}
	/**
	 * This method set the media file
	 * @param mediaFile it is List type object that hold the media file
	 */
	public void setMediaFile(List<MultipartFile> mediaFile) {
		this.mediaFile = mediaFile;
	}
	/**
	 * This method get the attach file
	 * @return type of this method is attach file
	 */
	public List<MultipartFile> getAttach_file() {
		return attach_file;
	}
	/**
	 * This method set the attach file
	 * @param attach_file it is List type object that hold the attach file
	 */
	public void setAttach_file(List<MultipartFile> attach_file) {
		this.attach_file = attach_file;
	}
	/**
	 * This method get the P6File
	 * @return type of this method is p6File
	 */
	public MultipartFile getP6File() {
		return p6File;
	}
	/**
	 * This method set the P6File
	 * @param p6File it is MultipartFile type object that hold the p6File
	 */
	public void setP6File(MultipartFile p6File) {
		this.p6File = p6File;
	}
	/**
	 * This method get the input files
	 * @return type of this method is input files
	 */
	public List<String> getInputFiles() {
		return inputFiles;
	}
	/**
	 * This method set the input files
	 * @param inputFiles it is List type object that hold the input files
	 */
	public void setInputFiles(List<String> inputFiles) {
		this.inputFiles = inputFiles;
	}
	/**
	 * This method get the task name
	 * @return type of this method taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * This method set the task name
	 * @param taskName it is string type variable that hold the task name
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * This method get the wbs weight
	 * @return type of this method is wbsWeight
	 */
	public String getWbsWeight() {
		return wbsWeight;
	}
	/**
	 * This method set the WbsWeight
	 * @param wbsWeight it is string type variable that hold the wbsWeight
	 */
	public void setWbsWeight(String wbsWeight) {
		this.wbsWeight = wbsWeight;
	}
	/**
	 * This method get the wbs category id
	 * @return type of this method is wbsCategoryId
	 */
	public String getWbsCategoryId() {
		return wbsCategoryId;
	}
	/**
	 * This method set the wbs category id
	 * @param wbsCategoryId it is string type variable that hold the wbs category id
	 */
	public void setWbsCategoryId(String wbsCategoryId) {
		this.wbsCategoryId = wbsCategoryId;
	}
	/**
	 * This method get the wbs category
	 * @return type of this method is wbs category
	 */
	public String getWbsCategory() {
		return wbsCategory;
	}
	/**
	 * This method set the wbs category
	 * @param wbsCategory it is string type variable that hold the wbs category
	 */
	public void setWbsCategory(String wbsCategory) {
		this.wbsCategory = wbsCategory;
	}
	/**
	 * This method get the contract id
	 * @return type of this method is contract id
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * This method is set the contract id
	 * @param contractId it is string type variable that hold the contract id
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * This method get the contract name
	 * @return type of this method is contract name
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * This method is set the contract name
	 * @param contractName it is string type variable that hold the contract name
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * This method get the issue category id
	 * @return type of this method is issueCategoryId
	 */
	public String getIssueCategoryId() {
		return issueCategoryId;
	}
	/**
	 * This method set the issueCategoryId
	 * @param issueCategoryId it is string type variable that hold the issueCategoryId
	 */
	public void setIssueCategoryId(String issueCategoryId) {
		this.issueCategoryId = issueCategoryId;
	}
	/**
	 * This method get the Issue priority id
	 * @return type of this method is issuePriorityId
	 */
	public String getIssuePriorityId() {
		return issuePriorityId;
	}
	/**
	 * This method set the issue priority id
	 * @param issuePriorityId it is string type variable that hold the issue priority id
	 */
	public void setIssuePriorityId(String issuePriorityId) {
		this.issuePriorityId = issuePriorityId;
	}
	/**
	 * This method get the activity data id
	 * @return type of this method is activityDataId
	 */
	public String getActivityDataId() {
		return activityDataId;
	}
	/**
	 * This method set the activity data id
	 * @param activityDataId it is string type variable that hold the activity data id
	 */
	public void setActivityDataId(String activityDataId) {
		this.activityDataId = activityDataId;
	}
	/**
	 * This method get the data date
	 * @return type of this method is dataDate
	 */
	public String getDataDate() {
		return dataDate;
	}
	/**
	 * This method set the Data date
	 * @param dataDate it is string type variable that hold the dataDate
	 */
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	/**
	 * This method get the is active
	 * @return type of this method is isActive
	 */
	public String getIsActive() {
		return isActive;
	}
	/**
	 * This method set the is active
	 * @param isActive it is string type variable that hold the is active
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * This method get the P6FileName
	 * @return type of this method is p6FileName
	 */
	public String getP6FileName() {
		return p6FileName;
	}
	/**
	 * This method set the P6FileName
	 * @param p6FileName it is string type variable that hold the p6FileName
	 */
	public void setP6FileName(String p6FileName) {
		this.p6FileName = p6FileName;
	}
	/**
	 * This method get the modified date
	 * @return type of this method is modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}
	/**
	 * This method set the ModifiedDate
	 * @param modifiedDate it is string type variable that hold the modified date
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	/**
	 * This method get the modified by
	 * @return type of this method is modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * This method set the modified By
	 * @param modifiedBy it is string type variable that hold the modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * This method get the Base cost
	 * @return type of this method is baseCost
	 */
	public String getBaseCost() {
		return baseCost;
	}
	/**
	 * This method set the base cost
	 * @param baseCost it is string type variable that hold the base cost
	 */
	public void setBaseCost(String baseCost) {
		this.baseCost = baseCost;
	}
	/**
	 * This method get the activity work
	 * @return type of this method is activity work
	 */
	public String getActivityWork() {
		return activityWork;
	}
	/**
	 * This method set the activity work
	 * @param activityWork it is string type variable that hold the activity work
	 */
	public void setActivityWork(String activityWork) {
		this.activityWork = activityWork;
	}
	/**
	 * This method get the user id
	 * @return type of this method is userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * This method set the userId
	 * @param userId it is string type variable that hold the user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * This method get the user name
	 * @return type of this method is userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * This method set the user name
	 * @param userName it is string type variable that hold the user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * This method get the created by
	 * @return type of this method is createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * This method set the created by
	 * @param createdBy it is string type variable that hold the created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * This method get the created date
	 * @return type of this method is created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * This method set the created date
	 * @param createdDate it is string type variable that hold the created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * This method get the task id
	 * @return type of this method is taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * This method set the task id
	 * @param taskId it is string type variable that hold the task id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * This method get the files path
	 * @return type of this method is filesPath
	 */
	public String getFilesPath() {
		return filesPath;
	}
	/**
	 * This method set the files path
	 * @param filesPath it is string type variable that hold the files path
	 */
	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}
	/**
	 * This method get the input files path
	 * @return type of this method is inouFilesPath
	 */
	public String getInputFilesPath() {
		return inputFilesPath;
	}
	/**
	 * This method set the input file path
	 * @param inputFilesPath it is string type variable that hold the input file path
	 */
	public void setInputFilesPath(String inputFilesPath) {
		this.inputFilesPath = inputFilesPath;
	}
	/**
	 * This method get the BoqCompleted
	 * @return type of this method is boqCompleted
	 */
	public String getBoqCompleted() {
		return boqCompleted;
	}
	/**
	 * This method set the BoqCompleted
	 * @param boqCompleted it is string type variable that hold the boqCompleted
	 */
	public void setBoqCompleted(String boqCompleted) {
		this.boqCompleted = boqCompleted;
	}
	/**
	 * This method get the module id
	 * @return type of this method is moduleId.
	 */
	public String getModuleId() {
		return moduleId;
	}
	/**
	 * This method set the module id
	 * @param moduleId it is string type variable that hold the module id
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * This method get the module name
	 * @return type of this method is moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * This method set the module name
	 * @param moduleName it is string type variable that hold the module name
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * This method get the work  module status
	 * @return type of this method is workModuleStatus
	 */
	public String getWorkModuleStatus() {
		return workModuleStatus;
	}
	/**
	 * This method set the WorkModuleStatus
	 * @param workModuleStatus it is string type variable that hold the work module status
	 */
	public void setWorkModuleStatus(String workModuleStatus) {
		this.workModuleStatus = workModuleStatus;
	}
	/**
	 * This method get the global work id
	 * @return type of this method is globalWorkId
	 */
	public String getGlobalWorkId() {
		return globalWorkId;
	}
	/**
	 * This method set the global work id
	 * @param globalWorkId it is string type variable that hold the global work id
	 */
	public void setGlobalWorkId(String globalWorkId) {
		this.globalWorkId = globalWorkId;
	}
	/**
	 * This method get the global work name
	 * @return type of this method is globalWorkName
	 */
	public String getGlobalWorkName() {
		return globalWorkName;
	}
	/**
	 * This method set the global work name
	 * @param globalWorkName it is string type variable that hold the globla work name
	 */
	public void setGlobalWorkName(String globalWorkName) {
		this.globalWorkName = globalWorkName;
	}
	/**
	 * This method get the wbs2CodeActivityId
	 * @return type of this method id wbs3CodeActivityId
	 */
	public String getWbs3CodeActivityId() {
		return wbs3CodeActivityId;
	}
	/**
	 * This method set the Wbs3CodeActivity
	 * @param wbs3CodeActivityId it is string type variable that hold the wbs3CodeActivityId
	 */
	public void setWbs3CodeActivityId(String wbs3CodeActivityId) {
		this.wbs3CodeActivityId = wbs3CodeActivityId;
	}
	/**
	 * This method get the activity update link
	 * @return type of this method is activityUpdateLink
	 */
	public String getActivityUpdateLink() {
		return activityUpdateLink;
	}
	/**
	 * This method set the activity update link
	 * @param activityUpdateLink it is string type variable that hold the activity update link
	 */
	public void setActivityUpdateLink(String activityUpdateLink) {
		this.activityUpdateLink = activityUpdateLink;
	}
	/**
	 * This method get the notification work id
	 * @return type of this method is notification work id
	 */
	public String getNotificationWorkId() {
		return notificationWorkId;
	}
	/**
	 * This method set the notification work id
	 * @param notificationWorkId it is string type variable that hold the notification work id
	 */
	public void setNotificationWorkId(String notificationWorkId) {
		this.notificationWorkId = notificationWorkId;
	}
	/**
	 * This method get the notification activity id
	 * @return type of this method is notificationActivityId
	 */
	public String getNotificationActivityId() {
		return notificationActivityId;
	}
	/**
	 * This method set the NotificationActivity id
	 * @param notificationActivityId it is string type variable that hold the notification activity id
	 */
	public void setNotificationActivityId(String notificationActivityId) {
		this.notificationActivityId = notificationActivityId;
	}
	/**
	 * This method get the due activities
	 * @return type of this method is dueActivities
	 */
	public List<Activity> getDueActivities() {
		return dueActivities;
	}
	/**
	 * This method set the due activities
	 * @param dueActivities it is list type object that hold the due activities
	 */
	public void setDueActivities(List<Activity> dueActivities) {
		this.dueActivities = dueActivities;
	}
	/**
	 * This method get the cumulativeBoqCompleted
	 * @return type of this method is cumulativeBoqCompleted
	 */
	public String getCumulativeBoqCompleted() {
		return cumulativeBoqCompleted;
	}
	/**
	 * This method set the CumulativeBoqCompleted
	 * @param cumulativeBoqCompleted it is string type variable that hold the cumulativeBoqCompleted
	 */
	public void setCumulativeBoqCompleted(String cumulativeBoqCompleted) {
		this.cumulativeBoqCompleted = cumulativeBoqCompleted;
	}
	/**
	 * This method get the notification work name
	 * @return type of this method is notificationWorkName
	 */
	public String getNotificationWorkName() {
		return notificationWorkName;
	}
	/**
	 * This method set the notification work name
	 * @param notificationWorkName it is string type variable that hold the notification work name
	 */
	public void setNotificationWorkName(String notificationWorkName) {
		this.notificationWorkName = notificationWorkName;
	}
	/**
	 * This method get the notification id
	 * @return type of this method is notificationId
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * This method set the Notification id
	 * @param notificationId it is string type variable that hold the notification id
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	/**
	 * This method get the activity file
	 * @return type of this method is activityFile
	 */
	public MultipartFile getActivityFile() {
		return activityFile;
	}
	/**
	 * This method set the activity file
	 * @param activityFile it is MultipartFile type object that hold the activity file.
	 */
	public void setActivityFile(MultipartFile activityFile) {
		this.activityFile = activityFile;
	}
	
}
