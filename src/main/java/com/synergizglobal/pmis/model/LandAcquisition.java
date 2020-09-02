package com.synergizglobal.pmis.model;

public class LandAcquisition {
	private String landAcquisitionId,workId,drawingNo,mapNo,plotNo,location,subLocation,latitude,longitude,chainageFrom,chainageTo,laSubCategoryId,area,areaUnits,
	surveyStatusId,valuationStatusId,approvalStatusId,paymentStatusId,acquisitionStatusId,
	surveyStatus,valuationStatus,approvalStatus,paymentStatus,acquisitionStatus,
	paymentInCr,paymentDate,remarks;
	private String laSubCategoryName,laCategoryId;
	private String laCategoryName;	
	private String workName;
	private String statusId,statusName,activeStatus;
	
	/**
	 * This method get the survey status
	 * @return type of this method is surveyStatus
	 */
	public String getSurveyStatus() {
		return surveyStatus;
	}
	/**
	 * This method set the Survey status
	 * @param surveyStatus it is string type variable that hold the survey status
	 */
	public void setSurveyStatus(String surveyStatus) {
		this.surveyStatus = surveyStatus;
	}
	/**
	 * This method get the valuation status
	 * @return type of this method is valuationStatus
	 */
	public String getValuationStatus() {
		return valuationStatus;
	}
	/**
	 * This method set the valuation status
	 * @param valuationStatus it is string type variable that hold the valuation status
	 */
	public void setValuationStatus(String valuationStatus) {
		this.valuationStatus = valuationStatus;
	}
	/**
	 * This method get the approval status
	 * @return type of this method is approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * This method set the approval status
	 * @param approvalStatus it is string type variable that hold the approval status
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	/**
	 * This method get the payment status
	 * @return type of this method is paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * This method set the payment status
	 * @param paymentStatus it is string type variable that hold the payment status
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * This method get the acquisition status
	 * @return type of this method is acquisitionStatus
	 */
	public String getAcquisitionStatus() {
		return acquisitionStatus;
	}
	/**
	 * This method set the acquisition status
	 * @param acquisitionStatus it is string type variable that hold the acquisition status
	 */
	public void setAcquisitionStatus(String acquisitionStatus) {
		this.acquisitionStatus = acquisitionStatus;
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
	 * This method get the drawing no
	 * @return type of this method is drawingNo
	 */
	public String getDrawingNo() {
		return drawingNo;
	}
	/**
	 * This method set the Drawing No
	 * @param drawingNo it is string type variable that hold the drawing no
	 */
	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	/**
	 * This method get the Map no
	 * @return type of this method is map no
	 */
	public String getMapNo() {
		return mapNo;
	}
	/**
	 * This method set the map no
	 * @param mapNo it is string type variable that hold the map no
	 */
	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}
	/**
	 * This method get the plot no
	 * @return type of this method is plot no
	 */
	public String getPlotNo() {
		return plotNo;
	}
	/**
	 * This method set the plot no
	 * @param plotNo it is string type variable that hold the plot no
	 */
	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
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
	 * @param location it is string type variable that hold the location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * This method get the sub location
	 * @return type of this method is subLocation
	 */
	public String getSubLocation() {
		return subLocation;
	}
	/**
	 * This method set the sub location
	 * @param subLocation it is string type variable that hold the sub location
	 */
	public void setSubLocation(String subLocation) {
		this.subLocation = subLocation;
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
	 * This method get the ChainageFrom
	 * @return type of this method is chainageFrom
	 */
	public String getChainageFrom() {
		return chainageFrom;
	}
	/**
	 * This method set the ChainageFrom
	 * @param chainageFrom it is string type variable that hold the chainageFrom
	 */
	public void setChainageFrom(String chainageFrom) {
		this.chainageFrom = chainageFrom;
	}
	/**
	 * This method get the chainageTo
	 * @return type of this method is chainageTo
	 */
	public String getChainageTo() {
		return chainageTo;
	}
	/**
	 * This method set the chainageTo
	 * @param chainageTo it is string type variable that hold the chainageTo
	 */
	public void setChainageTo(String chainageTo) {
		this.chainageTo = chainageTo;
	}
	/**
	 * This method get the LaSubCategoryId
	 * @return type of this method is laSubCategoryId
	 */
	public String getLaSubCategoryId() {
		return laSubCategoryId;
	}
	/**
	 * This method set the LaSubCategoryId
	 * @param laSubCategoryId it is string type variable that hold the LaSubCategoryId
	 */
	public void setLaSubCategoryId(String laSubCategoryId) {
		this.laSubCategoryId = laSubCategoryId;
	}
	/**
	 * This method get the area
	 * @return type of this method is area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * This method set the area
	 * @param area it is string type variable that hold the area
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * This method get the area units
	 * @return type of this method is areaUnits
	 */
	public String getAreaUnits() {
		return areaUnits;
	}
	/**
	 * This method set the area units
	 * @param areaUnits it is string type variable that hold the area units
	 */
	public void setAreaUnits(String areaUnits) {
		this.areaUnits = areaUnits;
	}
	/**
	 * This method get the survey status id
	 * @return type of this method is survey status id
	 */
	public String getSurveyStatusId() {
		return surveyStatusId;
	}
	/**
	 * This method set the survey status id
	 * @param surveyStatusId it is string type variable that hold the survey status id
	 */
	public void setSurveyStatusId(String surveyStatusId) {
		this.surveyStatusId = surveyStatusId;
	}
	/**
	 * This method get the valuation status id
	 * @return type of this method is valuationStatusId
	 */
	public String getValuationStatusId() {
		return valuationStatusId;
	}
	/**
	 * This method set the valuationStatusId
	 * @param valuationStatusId it is string type variable that hold the valuation status id
	 */
	public void setValuationStatusId(String valuationStatusId) {
		this.valuationStatusId = valuationStatusId;
	}
	/**
	 * This method get the approval status id
	 * @return type of this method is approvalStatusId
	 */
	public String getApprovalStatusId() {
		return approvalStatusId;
	}
    /**
     * This method set the approval status id
     * @param approvalStatusId it is string type variable that hold the approval status id
     */
	public void setApprovalStatusId(String approvalStatusId) {
		this.approvalStatusId = approvalStatusId;
	}
	/**
	 * This method get the payment status id
	 * @return type of this method is paymentStatusId
	 */
	public String getPaymentStatusId() {
		return paymentStatusId;
	}
	/**
	 * This method set the payment status id
	 * @param paymentStatusId it is string type variable that hold the payment status id
	 */
	public void setPaymentStatusId(String paymentStatusId) {
		this.paymentStatusId = paymentStatusId;
	}
	/**
	 * This method get the acquisition status id
	 * @return type of this method is acquisitionStatusId
	 */
	public String getAcquisitionStatusId() {
		return acquisitionStatusId;
	}
	/**
	 * This method set the acquisitionStatusId 
	 * @param acquisitionStatusId it is string type variable that hold the acquisition status id
	 */
	public void setAcquisitionStatusId(String acquisitionStatusId) {
		this.acquisitionStatusId = acquisitionStatusId;
	}
	/**
	 * This method get the paymentInCr
	 * @return type of this method is paymentInCr
	 */
	public String getPaymentInCr() {
		return paymentInCr;
	}
	/**
	 * This method set the PaymentInCr
	 * @param paymentInCr it is string type variable that hold the paymentInCr
	 */
	public void setPaymentInCr(String paymentInCr) {
		this.paymentInCr = paymentInCr;
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
	 * This method get the LaSubCategoryName
	 * @return type of this method is laSubCategoryName
	 */
	public String getLaSubCategoryName() {
		return laSubCategoryName;
	}
	/**
	 * This method set the LaSubCategoryName
	 * @param laSubCategoryName it is string type variable that hold the laSubCategoryName
	 */
	public void setLaSubCategoryName(String laSubCategoryName) {
		this.laSubCategoryName = laSubCategoryName;
	}
	/**
	 * This method get the LaCategoryId
	 * @return type of this method is laCategoryId
	 */
	public String getLaCategoryId() {
		return laCategoryId;
	}
	/**
	 * This method set LaCategoryId
	 * @param laCategoryId it is string type variable that hold the laCategoryId
	 */
	public void setLaCategoryId(String laCategoryId) {
		this.laCategoryId = laCategoryId;
	}
	/**
	 * This method get the LaCategoryName
	 * @return type of this method is laCategoryName
	 */
	public String getLaCategoryName() {
		return laCategoryName;
	}
	/**
	 * This method set LaCateogryName
	 * @param laCategoryName it is string type variable that hold the laCategoryName
	 */
	public void setLaCategoryName(String laCategoryName) {
		this.laCategoryName = laCategoryName;
	}
	/**
	 * This method get the work name
	 * @return type of this method is workName
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
	 * This method get the status id
	 * @return type of this method is status id
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
	 * This method get the status name
	 * @return type of this method is statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * This method set status name
	 * @param statusName it is string type variable that hold the status name
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * This method get the active status
	 * @return type of this method is activeStatus
	 */
	public String getActiveStatus() {
		return activeStatus;
	}
	/**
	 * This method set the active status
	 * @param activeStatus it is string type variable that hold the active status
	 */
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	/**
	 * This method get the land acquisition id
	 * @return type of this method is landAcquisionId
	 */
	public String getLandAcquisitionId() {
		return landAcquisitionId;
	}
	/**
	 * This method set the land acquisition id
	 * @param landAcquisitionId it is string type variable that hold the land acquisition id
	 */
	public void setLandAcquisitionId(String landAcquisitionId) {
		this.landAcquisitionId = landAcquisitionId;
	}
	/**
	 * This method get the payment date
	 * @return type of this method is paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	/**
	 * This method set the payment date
	 * @param paymentDate it is string type variable that hold the payment date
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
