package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class User  {
	private String user_id,user_name,password,email_id,department_fk,contract_id_code,designation,reporting_to_id_srfk,hod_user_id_fk,dy_hod_user_id_fk,user_role_name_fk,mobile_number,personal_contact_number,landline,extension,pmis_key_fk,remarks,user_image,keyAvailability;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String is_password_encrypted,is_test_env_enabled,current_url,decrypted_password;
	
	private String reporting_to_name,reporting_to_designation,department,department_name,user_access_type,user_access_table,user_id_fk,user_type_fk,loginCount,
	last_login,number_of_logins,user_role_name,user_access_type_fk,access_value,contract_id,contract_name,module_name,work_id,work_id_fk,work_name,
	access_value_id,access_value_name,user_role_code,last7DaysLogins,last30DaysLogins,login_event_date,login_event_type,login_event_type_fk,single_login_session_id,OTP,
	from_date,to_date,Created_by_user_id_fk,contract_short_name,contract_id_fk,contract_permission_checkbox,execution_permission_checkbox,risk_permission_checkbox
	,la_permission_checkbox,department_id_fk,us_permission_checkbox,rr_permission_checkbox,executive_user_id_fk,land_work,us_work,rr_work,id, module_fk, executive_id_fk, soft_delete_status;
	long user_leave_id;
	
	private List<User> departmentList,DesignationsList,UserLoginList;
	
	private List<User> userPermissions,reportingToPersonsList;
	
	private List<User>  contractExecutivesList, structureExecutivesList, riskExecutivesList, landExecutivesList, utilityExecutivesList, rrExecutivesList;
	
	private MultipartFile fileName;
	
	private MultipartFile userImageFile;
	
	private String[] user_access_types,user_access_values;
	
	private String[] modules,responsible_persons;
	

	private String system_ipa,public_ipa;
	
	private String user_login_details_id;


	public String getDepartment_id_fk() {
		return department_id_fk;
	}


	public void setDepartment_id_fk(String department_id_fk) {
		this.department_id_fk = department_id_fk;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getModule_fk() {
		return module_fk;
	}


	public void setModule_fk(String module_fk) {
		this.module_fk = module_fk;
	}


	public String getExecutive_id_fk() {
		return executive_id_fk;
	}


	public void setExecutive_id_fk(String executive_id_fk) {
		this.executive_id_fk = executive_id_fk;
	}


	public String getSoft_delete_status() {
		return soft_delete_status;
	}


	public void setSoft_delete_status(String soft_delete_status) {
		this.soft_delete_status = soft_delete_status;
	}


	public String getLand_work() {
		return land_work;
	}


	public void setLand_work(String land_work) {
		this.land_work = land_work;
	}


	public String getUs_work() {
		return us_work;
	}


	public void setUs_work(String us_work) {
		this.us_work = us_work;
	}


	public String getRr_work() {
		return rr_work;
	}


	public void setRr_work(String rr_work) {
		this.rr_work = rr_work;
	}


	public String getExecutive_user_id_fk() {
		return executive_user_id_fk;
	}


	public void setExecutive_user_id_fk(String executive_user_id_fk) {
		this.executive_user_id_fk = executive_user_id_fk;
	}


	public String getContract_permission_checkbox() {
		return contract_permission_checkbox;
	}


	public void setContract_permission_checkbox(String contract_permission_checkbox) {
		this.contract_permission_checkbox = contract_permission_checkbox;
	}


	public String getExecution_permission_checkbox() {
		return execution_permission_checkbox;
	}


	public void setExecution_permission_checkbox(String execution_permission_checkbox) {
		this.execution_permission_checkbox = execution_permission_checkbox;
	}


	public String getRisk_permission_checkbox() {
		return risk_permission_checkbox;
	}


	public void setRisk_permission_checkbox(String risk_permission_checkbox) {
		this.risk_permission_checkbox = risk_permission_checkbox;
	}


	public String getLa_permission_checkbox() {
		return la_permission_checkbox;
	}


	public void setLa_permission_checkbox(String la_permission_checkbox) {
		this.la_permission_checkbox = la_permission_checkbox;
	}


	public String getUs_permission_checkbox() {
		return us_permission_checkbox;
	}


	public void setUs_permission_checkbox(String us_permission_checkbox) {
		this.us_permission_checkbox = us_permission_checkbox;
	}


	public String getRr_permission_checkbox() {
		return rr_permission_checkbox;
	}


	public void setRr_permission_checkbox(String rr_permission_checkbox) {
		this.rr_permission_checkbox = rr_permission_checkbox;
	}


	public String getContract_id_fk() {
		return contract_id_fk;
	}


	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}


	public String getWork_id_fk() {
		return work_id_fk;
	}


	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}


	public String getContract_short_name() {
		return contract_short_name;
	}


	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}


	public List<User> getContractExecutivesList() {
		return contractExecutivesList;
	}


	public void setContractExecutivesList(List<User> contractExecutivesList) {
		this.contractExecutivesList = contractExecutivesList;
	}


	public List<User> getStructureExecutivesList() {
		return structureExecutivesList;
	}


	public void setStructureExecutivesList(List<User> structureExecutivesList) {
		this.structureExecutivesList = structureExecutivesList;
	}


	public List<User> getRiskExecutivesList() {
		return riskExecutivesList;
	}


	public void setRiskExecutivesList(List<User> riskExecutivesList) {
		this.riskExecutivesList = riskExecutivesList;
	}


	public List<User> getLandExecutivesList() {
		return landExecutivesList;
	}


	public void setLandExecutivesList(List<User> landExecutivesList) {
		this.landExecutivesList = landExecutivesList;
	}


	public List<User> getUtilityExecutivesList() {
		return utilityExecutivesList;
	}


	public void setUtilityExecutivesList(List<User> utilityExecutivesList) {
		this.utilityExecutivesList = utilityExecutivesList;
	}


	public List<User> getRrExecutivesList() {
		return rrExecutivesList;
	}


	public void setRrExecutivesList(List<User> rrExecutivesList) {
		this.rrExecutivesList = rrExecutivesList;
	}


	public String getContract_id_code() {
		return contract_id_code;
	}


	public void setContract_id_code(String contract_id_code) {
		this.contract_id_code = contract_id_code;
	}


	public List<User> getDepartmentList() {
		return departmentList;
	}


	public void setDepartmentList(List<User> departmentList) {
		this.departmentList = departmentList;
	}


	public List<User> getDesignationsList() {
		return DesignationsList;
	}


	public void setDesignationsList(List<User> designationsList) {
		DesignationsList = designationsList;
	}


	public List<User> getUserLoginList() {
		return UserLoginList;
	}


	public void setUserLoginList(List<User> userLoginList) {
		UserLoginList = userLoginList;
	}


	public String getLoginCount() {
		return loginCount;
	}


	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}


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


	public String getSingle_login_session_id() {
		return single_login_session_id;
	}


	public void setSingle_login_session_id(String single_login_session_id) {
		this.single_login_session_id = single_login_session_id;
	}


	public List<User> getReportingToPersonsList() {
		return reportingToPersonsList;
	}


	public void setReportingToPersonsList(List<User> reportingToPersonsList) {
		this.reportingToPersonsList = reportingToPersonsList;
	}


	public String getOTP() {
		return OTP;
	}


	public void setOTP(String oTP) {
		OTP = oTP;
	}


	public String[] getModules() {
		return modules;
	}


	public void setModules(String[] modules) {
		this.modules = modules;
	}


	public String[] getResponsible_persons() {
		return responsible_persons;
	}


	public void setResponsible_persons(String[] responsible_persons) {
		this.responsible_persons = responsible_persons;
	}


	public String getFrom_date() {
		return from_date;
	}


	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}


	public String getTo_date() {
		return to_date;
	}


	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}


	public long getUser_leave_id() {
		return user_leave_id;
	}


	public void setUser_leave_id(long user_leave_id) {
		this.user_leave_id = user_leave_id;
	}


	public String getCreated_by_user_id_fk() {
		return Created_by_user_id_fk;
	}


	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		Created_by_user_id_fk = created_by_user_id_fk;
	}


	public String getIs_password_encrypted() {
		return is_password_encrypted;
	}


	public void setIs_password_encrypted(String is_password_encrypted) {
		this.is_password_encrypted = is_password_encrypted;
	}


	public String getIs_test_env_enabled() {
		return is_test_env_enabled;
	}


	public void setIs_test_env_enabled(String is_test_env_enabled) {
		this.is_test_env_enabled = is_test_env_enabled;
	}


	public String getCurrent_url() {
		return current_url;
	}


	public void setCurrent_url(String current_url) {
		this.current_url = current_url;
	}


	public String getDecrypted_password() {
		return decrypted_password;
	}


	public void setDecrypted_password(String decrypted_password) {
		this.decrypted_password = decrypted_password;
	}
	
	
}
