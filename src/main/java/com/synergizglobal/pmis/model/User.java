package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class User  {
	private String user_id,user_name,password,email_id,department_fk,designation,reporting_to_id_srfk,hod_user_id_fk,dy_hod_user_id_fk,user_role_name_fk,mobile_number,personal_contact_number,landline,extension,pmis_key_fk,remarks,user_image,keyAvailability;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String passwordExpiredTime;
	
	private String reporting_to_name,reporting_to_designation,department,department_name,user_access_type,user_access_table,user_id_fk,user_type_fk,
	last_login,number_of_logins,user_role_name,user_access_type_fk,access_value,contract_id,contract_name,module_name,work_id,work_name,
	access_value_id,access_value_name,user_role_code,last7DaysLogins,last30DaysLogins,login_event_date,login_event_type,login_event_type_fk;
	
	private List<User> userPermissions;
	
	private MultipartFile fileName;
	
	private MultipartFile userImageFile;
	
	private String[] user_access_types,user_access_values;

	private String system_ipa,public_ipa;
	
	private String user_login_details_id;

	public String getLogin_event_date() {
		return login_event_date;
	}


	public void setLogin_event_date(String login_event_date) {
		this.login_event_date = login_event_date;
	}


	public String getLogin_event_type() {
		return login_event_type;
	}


	public void setLogin_event_type(String login_event_type) {
		this.login_event_type = login_event_type;
	}


	public String getLogin_event_type_fk() {
		return login_event_type_fk;
	}


	public void setLogin_event_type_fk(String login_event_type_fk) {
		this.login_event_type_fk = login_event_type_fk;
	}


	public String getHod_user_id_fk() {
		return hod_user_id_fk;
	}


	public void setHod_user_id_fk(String hod_user_id_fk) {
		this.hod_user_id_fk = hod_user_id_fk;
	}


	public String getDy_hod_user_id_fk() {
		return dy_hod_user_id_fk;
	}


	public void setDy_hod_user_id_fk(String dy_hod_user_id_fk) {
		this.dy_hod_user_id_fk = dy_hod_user_id_fk;
	}


	public String getUser_type_fk() {
		return user_type_fk;
	}


	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}


	public MultipartFile getUserImageFile() {
		return userImageFile;
	}


	public void setUserImageFile(MultipartFile userImageFile) {
		this.userImageFile = userImageFile;
	}


	public String getContract_id() {
		return contract_id;
	}


	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}


	public String getContract_name() {
		return contract_name;
	}


	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}


	public String getModule_name() {
		return module_name;
	}


	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}


	public String getWork_id() {
		return work_id;
	}


	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}


	public String getWork_name() {
		return work_name;
	}


	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}


	public String getUser_access_type() {
		return user_access_type;
	}


	public void setUser_access_type(String user_access_type) {
		this.user_access_type = user_access_type;
	}


	public String getUser_access_table() {
		return user_access_table;
	}


	public void setUser_access_table(String user_access_table) {
		this.user_access_table = user_access_table;
	}


	public String getUser_id_fk() {
		return user_id_fk;
	}


	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}


	public String getLast_login() {
		return last_login;
	}


	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}


	public String getNumber_of_logins() {
		return number_of_logins;
	}


	public void setNumber_of_logins(String number_of_logins) {
		this.number_of_logins = number_of_logins;
	}


	public String getUser_role_name() {
		return user_role_name;
	}


	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}


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


	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
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

	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getDepartment_name() {
		return department_name;
	}


	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}


	public String getReporting_to_name() {
		return reporting_to_name;
	}


	public void setReporting_to_name(String reporting_to_name) {
		this.reporting_to_name = reporting_to_name;
	}


	public List<User> getUserPermissions() {
		return userPermissions;
	}


	public void setUserPermissions(List<User> userPermissions) {
		this.userPermissions = userPermissions;
	}

	public String[] getUser_access_types() {
		return user_access_types;
	}


	public void setUser_access_types(String[] user_access_types) {
		this.user_access_types = user_access_types;
	}


	public String[] getUser_access_values() {
		return user_access_values;
	}


	public void setUser_access_values(String[] user_access_values) {
		this.user_access_values = user_access_values;
	}


	public String getUser_access_type_fk() {
		return user_access_type_fk;
	}


	public void setUser_access_type_fk(String user_access_type_fk) {
		this.user_access_type_fk = user_access_type_fk;
	}


	public String getAccess_value() {
		return access_value;
	}


	public void setAccess_value(String access_value) {
		this.access_value = access_value;
	}


	public String getAccess_value_id() {
		return access_value_id;
	}


	public void setAccess_value_id(String access_value_id) {
		this.access_value_id = access_value_id;
	}


	public String getAccess_value_name() {
		return access_value_name;
	}


	public void setAccess_value_name(String access_value_name) {
		this.access_value_name = access_value_name;
	}


	public String getUser_role_code() {
		return user_role_code;
	}


	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}


	public String getUser_image() {
		return user_image;
	}


	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}


	public String getKeyAvailability() {
		return keyAvailability;
	}


	public void setKeyAvailability(String keyAvailability) {
		this.keyAvailability = keyAvailability;
	}


	public String getReporting_to_designation() {
		return reporting_to_designation;
	}


	public void setReporting_to_designation(String reporting_to_designation) {
		this.reporting_to_designation = reporting_to_designation;
	}


	public String getPersonal_contact_number() {
		return personal_contact_number;
	}


	public void setPersonal_contact_number(String personal_contact_number) {
		this.personal_contact_number = personal_contact_number;
	}


	public String getLast7DaysLogins() {
		return last7DaysLogins;
	}


	public void setLast7DaysLogins(String last7DaysLogins) {
		this.last7DaysLogins = last7DaysLogins;
	}


	public String getLast30DaysLogins() {
		return last30DaysLogins;
	}


	public void setLast30DaysLogins(String last30DaysLogins) {
		this.last30DaysLogins = last30DaysLogins;
	}


	public String getSystem_ipa() {
		return system_ipa;
	}


	public void setSystem_ipa(String system_ipa) {
		this.system_ipa = system_ipa;
	}


	public String getPublic_ipa() {
		return public_ipa;
	}


	public void setPublic_ipa(String public_ipa) {
		this.public_ipa = public_ipa;
	}


	public String getUser_login_details_id() {
		return user_login_details_id;
	}


	public void setUser_login_details_id(String user_login_details_id) {
		this.user_login_details_id = user_login_details_id;
	}
	
	
}
