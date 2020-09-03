package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class User  {
	private String user_id,user_name,password,email_id,employee_type_fk,department_fk,designation,reporting_to_id_srfk,user_role_name_fk,secondary_email_id,mobile_number,alternate_mobile_number,landline,extension,pmis_key_fk,remarks;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String passwordExpiredTime;
	
	
	private MultipartFile fileName;


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


	public String getEmployee_type_fk() {
		return employee_type_fk;
	}


	public void setEmployee_type_fk(String employee_type_fk) {
		this.employee_type_fk = employee_type_fk;
	}


	public String getDepartment_fk() {
		return department_fk;
	}


	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getReporting_to_id_srfk() {
		return reporting_to_id_srfk;
	}


	public void setReporting_to_id_srfk(String reporting_to_id_srfk) {
		this.reporting_to_id_srfk = reporting_to_id_srfk;
	}


	public String getUser_role_name_fk() {
		return user_role_name_fk;
	}


	public void setUser_role_name_fk(String user_role_name_fk) {
		this.user_role_name_fk = user_role_name_fk;
	}


	public String getSecondary_email_id() {
		return secondary_email_id;
	}


	public void setSecondary_email_id(String secondary_email_id) {
		this.secondary_email_id = secondary_email_id;
	}


	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}


	public String getAlternate_mobile_number() {
		return alternate_mobile_number;
	}


	public void setAlternate_mobile_number(String alternate_mobile_number) {
		this.alternate_mobile_number = alternate_mobile_number;
	}


	public String getLandline() {
		return landline;
	}


	public void setLandline(String landline) {
		this.landline = landline;
	}


	public String getExtension() {
		return extension;
	}


	public void setExtension(String extension) {
		this.extension = extension;
	}


	public String getPmis_key_fk() {
		return pmis_key_fk;
	}


	public void setPmis_key_fk(String pmis_key_fk) {
		this.pmis_key_fk = pmis_key_fk;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getPasswordExpiredTime() {
		return passwordExpiredTime;
	}


	public void setPasswordExpiredTime(String passwordExpiredTime) {
		this.passwordExpiredTime = passwordExpiredTime;
	}


	public MultipartFile getFileName() {
		return fileName;
	}


	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}
	
}
