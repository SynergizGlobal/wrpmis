package com.synergizglobal.pmis.model;

public class Alerts {
	private String alert_id,alert_level,alert_type_fk,alert_type_id,alert_type,contract_id,created_date,alert_status,alert_value,count,
	hod,work_short_name,contract_short_name,hod_email,dy_hod_email,contract_id_fk,contractor_id_fk,contractor_id,contractor_name,remarks,
	email_id,user_id,work_id,work_id_fk,contract_name,work_name,user_name,designation,user_role_name,alerts_user_id,read_time,
	condition_value,bg_condition_value,insure_condition_value,cp_condition_value,cv_condition_value;

	private String redirect_url,department_name,responsible_person,escalated_to,hod_user_id_fk,dy_hod_user_id_fk,created_by_user_id_fk,
	user_id_fk,alert_level_fk,sub_work,reporting_to_user_id,reporting_to_email_id,alert_type_image,details,validity;
	
	private String status,uploaded_by_user_id_fk,corrective_measure,user_role_name_fk,user_type_fk,owner,owner_user_id,
	responsible_person_user_id,assessment_date,module_name,incharge_user_id_fk,amendment_not_required_in_contract,user_role_code,rowspan;
	

	public String getBg_condition_value() {
		return bg_condition_value;
	}

	public void setBg_condition_value(String bg_condition_value) {
		this.bg_condition_value = bg_condition_value;
	}

	public String getInsure_condition_value() {
		return insure_condition_value;
	}

	public void setInsure_condition_value(String insure_condition_value) {
		this.insure_condition_value = insure_condition_value;
	}

	public String getCp_condition_value() {
		return cp_condition_value;
	}

	public void setCp_condition_value(String cp_condition_value) {
		this.cp_condition_value = cp_condition_value;
	}

	public String getCv_condition_value() {
		return cv_condition_value;
	}

	public void setCv_condition_value(String cv_condition_value) {
		this.cv_condition_value = cv_condition_value;
	}

	public String getRead_time() {
		return read_time;
	}

	public void setRead_time(String read_time) {
		this.read_time = read_time;
	}

	public String getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(String owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public String getResponsible_person_user_id() {
		return responsible_person_user_id;
	}

	public void setResponsible_person_user_id(String responsible_person_user_id) {
		this.responsible_person_user_id = responsible_person_user_id;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getResponsible_person() {
		return responsible_person;
	}

	public void setResponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
	}

	public String getEscalated_to() {
		return escalated_to;
	}

	public void setEscalated_to(String escalated_to) {
		this.escalated_to = escalated_to;
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

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getContractor_id_fk() {
		return contractor_id_fk;
	}

	public void setContractor_id_fk(String contractor_id_fk) {
		this.contractor_id_fk = contractor_id_fk;
	}

	public String getContractor_id() {
		return contractor_id;
	}

	public void setContractor_id(String contractor_id) {
		this.contractor_id = contractor_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(String alert_id) {
		this.alert_id = alert_id;
	}

	public String getAlert_level() {
		return alert_level;
	}

	public void setAlert_level(String alert_level) {
		this.alert_level = alert_level;
	}

	public String getAlert_type_fk() {
		return alert_type_fk;
	}

	public void setAlert_type_fk(String alert_type_fk) {
		this.alert_type_fk = alert_type_fk;
	}

	public String getAlert_type_id() {
		return alert_type_id;
	}

	public void setAlert_type_id(String alert_type_id) {
		this.alert_type_id = alert_type_id;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getAlert_status() {
		return alert_status;
	}

	public void setAlert_status(String alert_status) {
		this.alert_status = alert_status;
	}

	public String getAlert_value() {
		return alert_value;
	}

	public void setAlert_value(String alert_value) {
		this.alert_value = alert_value;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getHod_email() {
		return hod_email;
	}

	public void setHod_email(String hod_email) {
		this.hod_email = hod_email;
	}

	public String getDy_hod_email() {
		return dy_hod_email;
	}

	public void setDy_hod_email(String dy_hod_email) {
		this.dy_hod_email = dy_hod_email;
	}

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getUser_role_name() {
		return user_role_name;
	}

	public void setUser_role_name(String user_role_name) {
		this.user_role_name = user_role_name;
	}

	public String getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public String getAlert_level_fk() {
		return alert_level_fk;
	}

	public void setAlert_level_fk(String alert_level_fk) {
		this.alert_level_fk = alert_level_fk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getCorrective_measure() {
		return corrective_measure;
	}

	public void setCorrective_measure(String corrective_measure) {
		this.corrective_measure = corrective_measure;
	}

	public String getSub_work() {
		return sub_work;
	}

	public void setSub_work(String sub_work) {
		this.sub_work = sub_work;
	}

	public String getReporting_to_user_id() {
		return reporting_to_user_id;
	}

	public void setReporting_to_user_id(String reporting_to_user_id) {
		this.reporting_to_user_id = reporting_to_user_id;
	}

	public String getReporting_to_email_id() {
		return reporting_to_email_id;
	}

	public void setReporting_to_email_id(String reporting_to_email_id) {
		this.reporting_to_email_id = reporting_to_email_id;
	}

	public String getAlert_type_image() {
		return alert_type_image;
	}

	public void setAlert_type_image(String alert_type_image) {
		this.alert_type_image = alert_type_image;
	}

	public String getUser_role_name_fk() {
		return user_role_name_fk;
	}

	public void setUser_role_name_fk(String user_role_name_fk) {
		this.user_role_name_fk = user_role_name_fk;
	}

	public String getUser_type_fk() {
		return user_type_fk;
	}

	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssessment_date() {
		return assessment_date;
	}

	public void setAssessment_date(String assessment_date) {
		this.assessment_date = assessment_date;
	}

	public String getIncharge_user_id_fk() {
		return incharge_user_id_fk;
	}

	public void setIncharge_user_id_fk(String incharge_user_id_fk) {
		this.incharge_user_id_fk = incharge_user_id_fk;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getAmendment_not_required_in_contract() {
		return amendment_not_required_in_contract;
	}

	public void setAmendment_not_required_in_contract(String amendment_not_required_in_contract) {
		this.amendment_not_required_in_contract = amendment_not_required_in_contract;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getAlerts_user_id() {
		return alerts_user_id;
	}

	public void setAlerts_user_id(String alerts_user_id) {
		this.alerts_user_id = alerts_user_id;
	}

	public String getCondition_value() {
		return condition_value;
	}

	public void setCondition_value(String condition_value) {
		this.condition_value = condition_value;
	}
	
}
