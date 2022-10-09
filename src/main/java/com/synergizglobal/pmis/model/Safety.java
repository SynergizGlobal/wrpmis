package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Safety {
	private String safety_id,contract_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,
	category_fk,impact_fk,root_cause_fk,status_fk,closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,
	investigation_completed,corrective_measure_short_term,corrective_measure_long_term,compensation,payment_date,remarks,short_description,work_short_name,
	category,impact,root_cause,status,contract_id,contract_name,work_id_fk,work_name,project_id_fk,project_name,status_remark_fk,
	department,department_name,attachment,contract_short_name,hod_user_id_fk,dy_hod_user_id_fk,committee_member_name,designation,hod_name,reporting_to_id_srfk,user_name,user_id,
	contractor_name,safety_id_fk,compensation_units,id, unit, value,compensation_unit,hod_designation,dyhod_designation,reported_by_email_id,responsible_person_email_id,existing_status_fk,
	existing_responsible_person,existing_escalated_to,contract_hod_user_id,contract_dyhod_user_id,created_by_user_id_fk,responsible_person_user_id,contract_hod_email_id,
	contract_dyhod_email_id,created_by_email_id,user_role_code,modified_by,modified_date,reported_by_user_id,committe_members,existing_committe_members,nominated_authority,nominated_authority_email_id,
	approve_corrective_measure,safety_incident,work_code,safety_seq_id;

	private MultipartFile safetyFile;
	
	private List<MultipartFile> safetyFiles;
	private String[] safetyFileNames,committee_member_names;
	
	private List<Safety> safetyFilesList;
	private List<Safety> safetyCommitteeMembersList;

	private String mail_body_header;
	
	public String getHod_designation() {
		return hod_designation;
	}

	public void setHod_designation(String hod_designation) {
		this.hod_designation = hod_designation;
	}

	public String getDyhod_designation() {
		return dyhod_designation;
	}

	public void setDyhod_designation(String dyhod_designation) {
		this.dyhod_designation = dyhod_designation;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCommittee_member_name() {
		return committee_member_name;
	}

	public void setCommittee_member_name(String committee_member_name) {
		this.committee_member_name = committee_member_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReporting_to_id_srfk() {
		return reporting_to_id_srfk;
	}

	public void setReporting_to_id_srfk(String reporting_to_id_srfk) {
		this.reporting_to_id_srfk = reporting_to_id_srfk;
	}

	public String getCompensation_units() {
		return compensation_units;
	}

	public void setCompensation_units(String compensation_units) {
		this.compensation_units = compensation_units;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCompensation_unit() {
		return compensation_unit;
	}

	public void setCompensation_unit(String compensation_unit) {
		this.compensation_unit = compensation_unit;
	}

	public String getSafety_id_fk() {
		return safety_id_fk;
	}

	public void setSafety_id_fk(String safety_id_fk) {
		this.safety_id_fk = safety_id_fk;
	}

	public List<MultipartFile> getSafetyFiles() {
		return safetyFiles;
	}

	public void setSafetyFiles(List<MultipartFile> safetyFiles) {
		this.safetyFiles = safetyFiles;
	}

	public String[] getSafetyFileNames() {
		return safetyFileNames;
	}

	public void setSafetyFileNames(String[] safetyFileNames) {
		this.safetyFileNames = safetyFileNames;
	}

	public List<Safety> getSafetyFilesList() {
		return safetyFilesList;
	}

	public void setSafetyFilesList(List<Safety> safetyFilesList) {
		this.safetyFilesList = safetyFilesList;
	}

	public String getHod_user_id_fk() {
		return hod_user_id_fk;
	}

	public void setHod_user_id_fk(String hod_user_id_fk) {
		this.hod_user_id_fk = hod_user_id_fk;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getHod_name() {
		return hod_name;
	}

	public void setHod_name(String hod_name) {
		this.hod_name = hod_name;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getStatus_remark_fk() {
		return status_remark_fk;
	}

	public void setStatus_remark_fk(String status_remark_fk) {
		this.status_remark_fk = status_remark_fk;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getSafety_id() {
		return safety_id;
	}

	public void setSafety_id(String safety_id) {
		this.safety_id = safety_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getReported_by() {
		return reported_by;
	}

	public void setReported_by(String reported_by) {
		this.reported_by = reported_by;
	}

	public String getResponsible_person() {
		return responsible_person;
	}

	public void setResponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
	}

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

	public String getCategory_fk() {
		return category_fk;
	}

	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}

	public String getImpact_fk() {
		return impact_fk;
	}

	public void setImpact_fk(String impact_fk) {
		this.impact_fk = impact_fk;
	}

	public String getRoot_cause_fk() {
		return root_cause_fk;
	}

	public void setRoot_cause_fk(String root_cause_fk) {
		this.root_cause_fk = root_cause_fk;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
	}

	public String getClosure_date() {
		return closure_date;
	}

	public void setClosure_date(String closure_date) {
		this.closure_date = closure_date;
	}

	public String getLti_hours() {
		return lti_hours;
	}

	public void setLti_hours(String lti_hours) {
		this.lti_hours = lti_hours;
	}

	public String getEquipment_impact() {
		return equipment_impact;
	}

	public void setEquipment_impact(String equipment_impact) {
		this.equipment_impact = equipment_impact;
	}

	public String getPeople_impact() {
		return people_impact;
	}

	public void setPeople_impact(String people_impact) {
		this.people_impact = people_impact;
	}

	public String getWork_impact() {
		return work_impact;
	}

	public void setWork_impact(String work_impact) {
		this.work_impact = work_impact;
	}

	public String getCommittee_formed_fk() {
		return committee_formed_fk;
	}

	public void setCommittee_formed_fk(String committee_formed_fk) {
		this.committee_formed_fk = committee_formed_fk;
	}

	public String getInvestigation_completed() {
		return investigation_completed;
	}

	public void setInvestigation_completed(String investigation_completed) {
		this.investigation_completed = investigation_completed;
	}

	public String getCorrective_measure_short_term() {
		return corrective_measure_short_term;
	}

	public void setCorrective_measure_short_term(String corrective_measure_short_term) {
		this.corrective_measure_short_term = corrective_measure_short_term;
	}

	public String getCorrective_measure_long_term() {
		return corrective_measure_long_term;
	}

	public void setCorrective_measure_long_term(String corrective_measure_long_term) {
		this.corrective_measure_long_term = corrective_measure_long_term;
	}

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getRoot_cause() {
		return root_cause;
	}

	public void setRoot_cause(String root_cause) {
		this.root_cause = root_cause;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MultipartFile getSafetyFile() {
		return safetyFile;
	}

	public void setSafetyFile(MultipartFile safetyFile) {
		this.safetyFile = safetyFile;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getCommittee_required_fk() {
		return committee_required_fk;
	}

	public void setCommittee_required_fk(String committee_required_fk) {
		this.committee_required_fk = committee_required_fk;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
	}

	public String[] getCommittee_member_names() {
		return committee_member_names;
	}

	public void setCommittee_member_names(String[] committee_member_names) {
		this.committee_member_names = committee_member_names;
	}

	public List<Safety> getSafetyCommitteeMembersList() {
		return safetyCommitteeMembersList;
	}

	public void setSafetyCommitteeMembersList(List<Safety> safetyCommitteeMembersList) {
		this.safetyCommitteeMembersList = safetyCommitteeMembersList;
	}

	public String getReported_by_email_id() {
		return reported_by_email_id;
	}

	public void setReported_by_email_id(String reported_by_email_id) {
		this.reported_by_email_id = reported_by_email_id;
	}

	public String getResponsible_person_email_id() {
		return responsible_person_email_id;
	}

	public void setResponsible_person_email_id(String responsible_person_email_id) {
		this.responsible_person_email_id = responsible_person_email_id;
	}

	public String getExisting_status_fk() {
		return existing_status_fk;
	}

	public void setExisting_status_fk(String existing_status_fk) {
		this.existing_status_fk = existing_status_fk;
	}

	public String getExisting_responsible_person() {
		return existing_responsible_person;
	}

	public void setExisting_responsible_person(String existing_responsible_person) {
		this.existing_responsible_person = existing_responsible_person;
	}

	public String getExisting_escalated_to() {
		return existing_escalated_to;
	}

	public void setExisting_escalated_to(String existing_escalated_to) {
		this.existing_escalated_to = existing_escalated_to;
	}

	public String getDy_hod_user_id_fk() {
		return dy_hod_user_id_fk;
	}

	public void setDy_hod_user_id_fk(String dy_hod_user_id_fk) {
		this.dy_hod_user_id_fk = dy_hod_user_id_fk;
	}

	public String getContract_hod_user_id() {
		return contract_hod_user_id;
	}

	public void setContract_hod_user_id(String contract_hod_user_id) {
		this.contract_hod_user_id = contract_hod_user_id;
	}

	public String getContract_dyhod_user_id() {
		return contract_dyhod_user_id;
	}

	public void setContract_dyhod_user_id(String contract_dyhod_user_id) {
		this.contract_dyhod_user_id = contract_dyhod_user_id;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getResponsible_person_user_id() {
		return responsible_person_user_id;
	}

	public void setResponsible_person_user_id(String responsible_person_user_id) {
		this.responsible_person_user_id = responsible_person_user_id;
	}

	public String getContract_hod_email_id() {
		return contract_hod_email_id;
	}

	public void setContract_hod_email_id(String contract_hod_email_id) {
		this.contract_hod_email_id = contract_hod_email_id;
	}

	public String getContract_dyhod_email_id() {
		return contract_dyhod_email_id;
	}

	public void setContract_dyhod_email_id(String contract_dyhod_email_id) {
		this.contract_dyhod_email_id = contract_dyhod_email_id;
	}

	public String getCreated_by_email_id() {
		return created_by_email_id;
	}

	public void setCreated_by_email_id(String created_by_email_id) {
		this.created_by_email_id = created_by_email_id;
	}

	public String getMail_body_header() {
		return mail_body_header;
	}

	public void setMail_body_header(String mail_body_header) {
		this.mail_body_header = mail_body_header;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	public String getReported_by_user_id() {
		return reported_by_user_id;
	}

	public void setReported_by_user_id(String reported_by_user_id) {
		this.reported_by_user_id = reported_by_user_id;
	}

	public String getCommitte_members() {
		return committe_members;
	}

	public void setCommitte_members(String committe_members) {
		this.committe_members = committe_members;
	}

	public String getExisting_committe_members() {
		return existing_committe_members;
	}

	public void setExisting_committe_members(String existing_committe_members) {
		this.existing_committe_members = existing_committe_members;
	}

	public String getNominated_authority() {
		return nominated_authority;
	}

	public void setNominated_authority(String nominated_authority) {
		this.nominated_authority = nominated_authority;
	}

	public String getApprove_corrective_measure() {
		return approve_corrective_measure;
	}

	public void setApprove_corrective_measure(String approve_corrective_measure) {
		this.approve_corrective_measure = approve_corrective_measure;
	}

	public String getSafety_incident() {
		return safety_incident;
	}

	public void setSafety_incident(String safety_incident) {
		this.safety_incident = safety_incident;
	}

	public String getNominated_authority_email_id() {
		return nominated_authority_email_id;
	}

	public void setNominated_authority_email_id(String nominated_authority_email_id) {
		this.nominated_authority_email_id = nominated_authority_email_id;
	}

	public String getWork_code() {
		return work_code;
	}

	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}

	public String getSafety_seq_id() {
		return safety_seq_id;
	}

	public void setSafety_seq_id(String safety_seq_id) {
		this.safety_seq_id = safety_seq_id;
	}
	
}
