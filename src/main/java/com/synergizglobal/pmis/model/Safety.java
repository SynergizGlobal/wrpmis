package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class Safety {
	private String safety_id,contract_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,
	category_fk,impact_fk,root_cause_fk,status_fk,closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,
	investigation_completed,corrective_measure_short_term,corrective_measure_long_term,compensation,payment_date,remarks,
	category,impact,root_cause,status,contract_name,work_id_fk,work_name,project_id_fk,project_name;

	private MultipartFile safetyFile;
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
	
}
