package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;
public class Risk {
	private String riskId,projectId,projectName,workId,workName,riskOwner,riskAreaId,riskArea,riskSubAreaId,riskSubArea,riskDescription,riskCategoryId,riskCategory,riskProbabilityId,
	riskProbability,riskImpactId,riskImpact,riskPriorityNumber,dateOfIdentification,riskMitigationPlan,targetDateForMitigation,
	responsiblePersonName,riskStatusId,riskStatus,riskAnalysisReportLink,remarks,riskMitigatedOn,statusId,status,createdBy,createdDate,risksBulkUploadFileName;
	
	private String riskClassificationId,riskClassificationName,riskRatingLow,riskRatingHigh,riskRankingId,riskRanking;
	
	private MultipartFile riskAnalysisReportFile;
	
	private MultipartFile risksBulkUploadTemplate;
	
	/**
	 * This method get the project id
	 * @return type of this method is projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * This method set the project id
	 * @param projectId it is string type variable that hold the project id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * This method get the project name
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
	 * This method get the Risk Analysis Report File
	 * @return type of this method is riskAnalysisRepoertFile
	 */
	public MultipartFile getRiskAnalysisReportFile() {
		return riskAnalysisReportFile;
	}
	/**
	 * This method set the Risk Analysis Report File
	 * @param riskAnalysisReportFile it is MultipartFile type object that hold the riskAnalysisReportFile
	 */
	public void setRiskAnalysisReportFile(MultipartFile riskAnalysisReportFile) {
		this.riskAnalysisReportFile = riskAnalysisReportFile;
	}
	
	/**
	 * This method get the risk id
	 * @return type of this method is risk id
	 */
	public String getRiskId() {
		return riskId;
	}
	/**
	 * This method set the risk id
	 * @param riskId it is string type variable that hold the risk id
	 */
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	/**
	 * This method get the work id
	 * @return type of this method is work id
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
	 * This method get the risk owner
	 * @return type of this method is riskOwner
	 */
	public String getRiskOwner() {
		return riskOwner;
	}
	/**
	 * This method set the risk woner
	 * @param riskOwner it is string type variable that hold the risk owner
	 */
	public void setRiskOwner(String riskOwner) {
		this.riskOwner = riskOwner;
	}
	/**
	 * This method get the risk area id
	 * @return type of this method is riskAreaId
	 */
	public String getRiskAreaId() {
		return riskAreaId;
	}
	/**
	 * This method set the risk area id
	 * @param riskAreaId it is string type variable that hold the risk area id
	 */
	public void setRiskAreaId(String riskAreaId) {
		this.riskAreaId = riskAreaId;
	}
	/**
	 * This method get the risk area
	 * @return type of this method is risk area
	 */
	public String getRiskArea() {
		return riskArea;
	}
	/**
	 * This method set the risk area
	 * @param riskArea it is string type variable that hold the risk area
	 */
	public void setRiskArea(String riskArea) {
		this.riskArea = riskArea;
	}
	/**
	 * This method get the risk sub area id
	 * @return type of this method is riskSubAreaId
	 */
	public String getRiskSubAreaId() {
		return riskSubAreaId;
	}
	/**
	 * This method set the risk sub area id
	 * @param riskSubAreaId
	 */
	public void setRiskSubAreaId(String riskSubAreaId) {
		this.riskSubAreaId = riskSubAreaId;
	}
	/**
	 * This method get the risk sub area
	 * @return type of this method is riskSubArea
	 */
	public String getRiskSubArea() {
		return riskSubArea;
	}
	/**
	 * This method set the risk sub area
	 * @param riskSubArea it is string type variable that hold the risk sub area
	 */
	public void setRiskSubArea(String riskSubArea) {
		this.riskSubArea = riskSubArea;
	}
	/**
	 * This method get the risk description
	 * @return type of this method is riskDescription
	 */
	public String getRiskDescription() {
		return riskDescription;
	}
	/**
	 * This method set the RiskDescription
	 * @param riskDescription it is string type variable that hold the riskDescription
	 */
	public void setRiskDescription(String riskDescription) {
		this.riskDescription = riskDescription;
	}
	/**
	 * This method get the risk category id
	 * @return type of this method is riskCategoryId
	 */
	public String getRiskCategoryId() {
		return riskCategoryId;
	}
	/**
	 * This method set the risk category id
	 * @param riskCategoryId it is string type variable that hold the risk category id
	 */
	public void setRiskCategoryId(String riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}
	/**
	 * This method get the risk category
	 * @return type of this method is riskCategory.
	 */
	public String getRiskCategory() {
		return riskCategory;
	}
	/**
	 * This method set the Risk category
	 * @param riskCategory it is string type variable that hold the risk category
	 */
	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}
	/**
	 * This method get the risk probability id
	 * @return type of this method is riskProbabilityId
	 */
	public String getRiskProbabilityId() {
		return riskProbabilityId;
	}
	/**
	 * This method set the Risk probability
	 * @param riskProbabilityId it is string type variable that hold the risk probability
	 */
	public void setRiskProbabilityId(String riskProbabilityId) {
		this.riskProbabilityId = riskProbabilityId;
	}
	/**
	 * This method get the Risk probability
	 * @return type of this method is riskProbability
	 */
	public String getRiskProbability() {
		return riskProbability;
	}
	/**
	 * This method set the Risk Probability
	 * @param riskProbability it is string type variable that hold the risk probability
	 */
	public void setRiskProbability(String riskProbability) {
		this.riskProbability = riskProbability;
	}
	/**
	 * This method get the risk impact id
	 * @return type of this method is riskImapctId
	 */
	public String getRiskImpactId() {
		return riskImpactId;
	}
	/**
	 * This method set the Risk Impact Id
	 * @param riskImpactId it is string type variable that hold the risk impact id
	 */
	public void setRiskImpactId(String riskImpactId) {
		this.riskImpactId = riskImpactId;
	}
	/**
	 * This method get the Risk Impact
	 * @return type of this method is riskImpact
	 */
	public String getRiskImpact() {
		return riskImpact;
	}
	/**
	 * This method set the riskImpact
	 * @param riskImpact it is string type variable that hold the riskImpact
	 */
	public void setRiskImpact(String riskImpact) {
		this.riskImpact = riskImpact;
	}
	/**
	 * This method get the RiskPriorityNumber
	 * @return type of this method is riskPriorityNumber
	 */
	public String getRiskPriorityNumber() {
		return riskPriorityNumber;
	}
	/**
	 * This method set the RiskPriorityNumber
	 * @param riskPriorityNumber it is string type variable that hold the risk priority number
	 */
	public void setRiskPriorityNumber(String riskPriorityNumber) {
		this.riskPriorityNumber = riskPriorityNumber;
	}
	/**
	 * This method get the date of identification
	 * @return type of this method is dateOfIdentification
	 */
	public String getDateOfIdentification() {
		return dateOfIdentification;
	}
	/**
	 * This method set the date of identification
	 * @param dateOfIdentification it is string type variable that hold the date of identification
	 */
	public void setDateOfIdentification(String dateOfIdentification) {
		this.dateOfIdentification = dateOfIdentification;
	}
	/**
	 * This method get the Risk mitigation plan
	 * @return type of this method is riskMitigationPlan
	 */
	public String getRiskMitigationPlan() {
		return riskMitigationPlan;
	}
	/**
	 * This method set the Risk mitigation plan
	 * @param riskMitigationPlan it is string type variable that hold the risk mitigation plan
	 */
	public void setRiskMitigationPlan(String riskMitigationPlan) {
		this.riskMitigationPlan = riskMitigationPlan;
	}
	/**
	 * This method get the target date for mitigation
	 * @return type of this method is targetDateForMitigation
	 */
	public String getTargetDateForMitigation() {
		return targetDateForMitigation;
	}
	/**
	 * This method set the target date for mitigation
	 * @param targetDateForMitigation it is string type variable that hold the target date for mitigation
	 */
	public void setTargetDateForMitigation(String targetDateForMitigation) {
		this.targetDateForMitigation = targetDateForMitigation;
	}
	/**
	 * This method get the responsible person name
	 * @return type of this method is responsiblePersonName
	 */
	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}
	/**
	 * This method set the Responsible person name
	 * @param responsiblePersonName it is string type variable that hold the responsible person name
	 */
	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}
	/**
	 * This method get the risk status id
	 * @return type of this method is riskStatusId
	 */
	public String getRiskStatusId() {
		return riskStatusId;
	}
	/**
	 * This method set the Risk Status id 
	 * @param riskStatusId it is string type variable that hold the risk status id
	 */
	public void setRiskStatusId(String riskStatusId) {
		this.riskStatusId = riskStatusId;
	}
	/**
	 * This method get the risk status
	 * @return type of this method is riskStatus
	 */
	public String getRiskStatus() {
		return riskStatus;
	}
	/**
	 * This method set the risk status
	 * @param riskStatus it is string type variable that hold the risk status
	 */
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	/**
	 * This method get the Risk Analysis Report Link
	 * @return type of this method is riskAnalysisReportLink
	 */
	public String getRiskAnalysisReportLink() {
		return riskAnalysisReportLink;
	}
	/**
	 * This method set the Risk analysis report link
	 * @param riskAnalysisReportLink it is string type variable that hold the risk analysis report link
	 */
	public void setRiskAnalysisReportLink(String riskAnalysisReportLink) {
		this.riskAnalysisReportLink = riskAnalysisReportLink;
	}
	/**
	 * This method get the remarks
	 * @return type of this method is remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * This method set the Remarks
	 * @param remarks it is string type variable that hold the remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * This method get the risk mitigated on
	 * @return type of this method is riskMitigatedOn
	 */
	public String getRiskMitigatedOn() {
		return riskMitigatedOn;
	}
	/**
	 * This method set risk mitigated on
	 * @param riskMitigatedOn it is string type variable that hold the riskMitigationOn
	 */
	public void setRiskMitigatedOn(String riskMitigatedOn) {
		this.riskMitigatedOn = riskMitigatedOn;
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
	 * This method get the status
	 * @return type of this method is status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * This method set the status
	 * @param status it is string type variable that hold the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * This method get the created by
	 * @return type of this method is createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * This method set the created By
	 * @param createdBy it is string type variable that hold the createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * This method get the CreatedDate
	 * @return type of this method is createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * This method set the created date
	 * @param createdDate it is string type variable that hold the createdDate
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * This method get the risk classification id
	 * @return type of this method is riskClassificationId
	 */
	public String getRiskClassificationId() {
		return riskClassificationId;
	}
	/**
	 * This method set the risk classification id
	 * @param riskClassificationId it is string type variable that hold the risk classification id
	 */
	public void setRiskClassificationId(String riskClassificationId) {
		this.riskClassificationId = riskClassificationId;
	}
	/**
	 * This method get the risk classification name
	 * @return type of this method is riskClassificationName
	 */
	public String getRiskClassificationName() {
		return riskClassificationName;
	}
	/**
	 * This method set the risk classification name
	 * @param riskClassificationName it is string type variable that hold the risk classification name
	 */
	public void setRiskClassificationName(String riskClassificationName) {
		this.riskClassificationName = riskClassificationName;
	}
	/**
	 * This method get the Risk Rating low 
	 * @return type of this method is riskRatingLow
	 */
	public String getRiskRatingLow() {
		return riskRatingLow;
	}
	/**
	 * This method is set the Risk Rating Low
	 * @param riskRatingLow it is string type variable that hold the risk rating low
	 */
	public void setRiskRatingLow(String riskRatingLow) {
		this.riskRatingLow = riskRatingLow;
	}
	/**
	 * This method is get the risk rating high
	 * @return type of this method is riskRatingHigh
	 */
	public String getRiskRatingHigh() {
		return riskRatingHigh;
	}
	/**
	 * This method set the risk rating high
	 * @param riskRatingHigh it is string type variable that hold the risk rating high
	 */
	public void setRiskRatingHigh(String riskRatingHigh) {
		this.riskRatingHigh = riskRatingHigh;
	}
	/**
	 * This method get the risk ranking id
	 * @return type of this method is riskRankingId
	 */
	public String getRiskRankingId() {
		return riskRankingId;
	}
	/**
	 * This method set the Risk ranking id
	 * @param riskRankingId it is string type variable that hold the risk ranking id
	 */
	public void setRiskRankingId(String riskRankingId) {
		this.riskRankingId = riskRankingId;
	}
	/**
	 * This method get the risk ranking
	 * @return type of this method is riskRanking
	 */
	public String getRiskRanking() {
		return riskRanking;
	}
	/**
	 * This method set the Risk ranking
	 * @param riskRanking it is string type variable that hold the risk ranking
	 */
	public void setRiskRanking(String riskRanking) {
		this.riskRanking = riskRanking;
	}
	/**
	 * This method get the risks bulk upload file name
	 * @return type of this method is riskBulkUploadFileName
	 */
	public String getRisksBulkUploadFileName() {
		return risksBulkUploadFileName;
	}
	/**
	 * This method set the Risk Bulk Upload File Name
	 * @param risksBulkUploadFileName it is string type variable that hold the risk bulk upload file name
	 */
	public void setRisksBulkUploadFileName(String risksBulkUploadFileName) {
		this.risksBulkUploadFileName = risksBulkUploadFileName;
	}
	/**
	 * This method get the risk bulk upload template
	 * @return type of this method is riskBulkUploadTemplate
	 */
	public MultipartFile getRisksBulkUploadTemplate() {
		return risksBulkUploadTemplate;
	}
	/**
	 * This method set the risk bulk upload template
	 * @param risksBulkUploadTemplate it is string type variable that hold the risk bulk upload template
	 */
	public void setRisksBulkUploadTemplate(MultipartFile risksBulkUploadTemplate) {
		this.risksBulkUploadTemplate = risksBulkUploadTemplate;
	}
	
	
}
