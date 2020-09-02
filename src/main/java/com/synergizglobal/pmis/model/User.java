package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class User  {
	private String id;
	private String userId;
	private String pmisKey;
	private String password;
	private String mobileNumber;
	private String emailId;
	private String userName;
	private String address1;
	private String address2;
	private String city;
	private String stateId;
	private String stateName;
	private String zipCode;
	private String roleId;
	private String roleName;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String role;
	private String keepMeLoggedIn; 
	private String currentDate;
	private String companyId;
	
	private String contractId;
	private String contractTitle;
	private String companyName;
	private String statusId; 
	private String status;
	private String rating;
	private String clientId;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String passwordExpiredTime;
	private String taskId;
	private String workId,workName,notificationId;
	
	/**
	 * This method will get the task id
	 * @return type of this method is taskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * This method will set the task id
	 * @param taskId it is string type variable that hold the task id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * This method will get the work id
	 * @return type of this method is workId
	 */
	public String getWorkId() {
		return workId;
	}
	
	/**
	 * This method will set the work id
	 * @param workId it is string type variable that hold the work id
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	private MultipartFile fileName;
	
	/**
	 * This method get the old password
	 * @return type of this method is oldPassword.
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * This method will set the old password
	 * @param oldPassword it is string type variable that hold the old password.
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * This method will get the new password
	 * @return type of this method is newPassword.
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * This method will set the new password
	 * @param newPassword it is string type variable that hold the new password
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * This method will get the confirm password
	 * @return type of this method is confirmPassword.
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * This method will set the confirm password.
	 * @param confirmPassword it is string type variable that hold the confirm password.
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/**
	 * This method will get the contract id
	 * @return type of this method is contractId
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * This method will set the contract id
	 * @param contractId it is string type variable that hold the contract id
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * This method will get the contract title
	 * @return type of this method is contractTitle.
	 */
	public String getContractTitle() {
		return contractTitle;
	}
	/**
	 * This method will set the contract title
	 * @param contractTitle it is string type variable that hold the contract title
	 */
	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}
	/**
	 * This method will get the company name
	 * @return type of this method is companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * This method will set the company name
	 * @param companyName it is string type variable that hold the company name.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * This method will get the status id
	 * @return type of this method is statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * This method will set the status id
	 * @param statusId it is string type variable that hold the status id
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	/**
	 * This method will get the status
	 * @return type of this method is status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * This method will set the status
	 * @param status it is string type variable that hold the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * This method will get the rating
	 * @return type of this method is rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * This method will set the rating
	 * @param rating it is string type variable that hold the rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	/**
	 * This method will get the client id
	 * @return type of this method is clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * This method will set the client id
	 * @param clientId it is string type variable that hold the client id
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * This method will get the id
	 * @return type of this method is id.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * This method will set the id
	 * @param id it is string type variable that hold the id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * This method will get the user id
	 * @return type of this method is user id.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * This method will set the user id
	 * @param userId it is string type variable that hold the user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * This method will get the password
	 * @return type of this method is password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * This method will set the password
	 * @param password it is string type variable that hold the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * This method will get the mobile number
	 * @return type of this method is mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * This method will set the mobile number 
	 * @param mobileNumber it is string type variable that hold the mobile number
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * This method will get the email id
	 * @return type of this method is emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * This method will set the email id
	 * @param emailId it is string type variable that hold the email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * This method will get the user name
	 * @return type of this method is userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * This method will set the user name
	 * @param userName it is string type variable that hold the user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * This method will get the address1
	 * @return type of this method is address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * This method will set the address1
	 * @param address1 it is string type variable that hold the address1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * This method will get the address2
	 * @return type of this method is address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * This method will set the address2
	 * @param address2 it is string type variable that hold the address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * This method will get the city
	 * @return type of this method is city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * This method will set the city
	 * @param city it is string type variable that hold the city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * This method will set the state id
	 * @return type of this method is sateId
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * This method will set the sate id
	 * @param stateId it is string type variable that hold the state id
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * This method will get the state name
	 * @return type of this method is stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * This method will set the state name
	 * @param stateName it is string type variable that hold the state name
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * This method will get the zip code
	 * @return type of this method is zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * This method will set the zip code
	 * @param zipCode it is string type variable that hold the zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * This method will get the role id 
	 * @return type of this method is roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * This method will set the role id
	 * @param roleId it is string type variable that hold the role id
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * This method will get the role name
	 * @return type of this method will roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * This method will set the role name
	 * @param roleName it is string type variable that hold the role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * This method will get the created by
	 * @return type of this method is createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * This method will set the created by
	 * @param createdBy it is string type variable that hold the created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * This method will get the created date
	 * @return type of this method is createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * This method will set the created date
	 * @param createdDate it is string type variable that hold the created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * This method will get the modified by
	 * @return type of this method is modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * This method will set the modified by
	 * @param modifiedBy it is string type variable that hold the modifiedBy
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * This method will get the modified date
	 * @return type of this method is modifiedDate.
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}
	/** 
	 * This method will set the modified date
	 * @param modifiedDate it is string type variable that hold the modified date
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	/**
	 * This method will get the role
	 * @return type of this method is role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * This method will set the role
	 * @param role it is string type variable that hold the role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * This method will get the keepMeloggedIn
	 * @return type of thie method is keepMeLoggedIn
	 */
	public String getKeepMeLoggedIn() {
		return keepMeLoggedIn;
	}
	/**
	 * This method will set the keepMeLoggedIn
	 * @param keepMeLoggedIn it is string type variable that hold the keepMeLoggedIn
	 */
	public void setKeepMeLoggedIn(String keepMeLoggedIn) {
		this.keepMeLoggedIn = keepMeLoggedIn;
	}
	/**
	 * This method will get the current date
	 * @return type of this method is currentDate
	 */
	public String getCurrentDate() {
		return currentDate;
	}
	/**
	 * This method will set the current date
	 * @param currentDate it is string type variable that hold the current date
	 */
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	/**
	 * This method will get the company id
	 * @return type of this method is companyId
	 */
	public String getCompanyId() {
		return companyId;
	}
	/**
	 * This method will set the company id
	 * @param companyId it is string type variable that hold the company id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * This method will get the password expired time
	 * @return type of this method is passwordExpiredTime
	 */
	public String getPasswordExpiredTime() {
		return passwordExpiredTime;
	}
	/**
	 * This method will set the password expired time
	 * @param passwordExpiredTime it is string type variable that hold the password expired time
	 */
	public void setPasswordExpiredTime(String passwordExpiredTime) {
		this.passwordExpiredTime = passwordExpiredTime;
	}
	/**
	 * This method will get the file name
	 * @return type of this method is fileName
	 */
	public MultipartFile getFileName() {
		return fileName;
	}
	/**
	 * This method will set the file name
	 * @param fileName it is MultipartFile class type object that hold the file name
	 */
	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}
	/**
	 * This method will get the work name
	 * @return type of this method is workName
	 */
	public String getWorkName() {
		return workName;
	}
	/**
	 * This method will set the work name
	 * @param workName it is string type variable that hold the work name
	 */
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	/**
	 * This method will get the notification id
	 * @return type of this method is notificationId
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * This method will set the notification id
	 * @param notificationId it is string type variable that hold the notification id
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + "]";
	}
	public String getPmisKey() {
		return pmisKey;
	}
	public void setPmisKey(String pmisKey) {
		this.pmisKey = pmisKey;
	}
	
	
	
}
