package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class Issue {
	private String issue_id,contract_id_fk,activity,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,user_name,
	priority_fk,category_fk,status_fk,corrective_measure,resolved_date,escalated_to,remarks,priority,category,status,contract_id,contract_name,work_id_fk,work_name,work_short_name,
	project_id_fk,project_name,activity_name,strip_chart_component_fk,department,department_name,attachment,railway_id,railway_name,zonal_railway_fk,contract_short_name;

	private MultipartFile issueFile;
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
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

	public MultipartFile getIssueFile() {
		return issueFile;
	}

	public void setIssueFile(MultipartFile issueFile) {
		this.issueFile = issueFile;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
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

	public String getPriority_fk() {
		return priority_fk;
	}

	public void setPriority_fk(String priority_fk) {
		this.priority_fk = priority_fk;
	}

	public String getCategory_fk() {
		return category_fk;
	}

	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
	}

	public String getCorrective_measure() {
		return corrective_measure;
	}

	public void setCorrective_measure(String corrective_measure) {
		this.corrective_measure = corrective_measure;
	}

	public String getResolved_date() {
		return resolved_date;
	}

	public void setResolved_date(String resolved_date) {
		this.resolved_date = resolved_date;
	}

	public String getEscalated_to() {
		return escalated_to;
	}

	public void setEscalated_to(String escalated_to) {
		this.escalated_to = escalated_to;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getStrip_chart_component_fk() {
		return strip_chart_component_fk;
	}

	public void setStrip_chart_component_fk(String strip_chart_component_fk) {
		this.strip_chart_component_fk = strip_chart_component_fk;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getRailway_name() {
		return railway_name;
	}

	public void setRailway_name(String railway_name) {
		this.railway_name = railway_name;
	}

	public String getZonal_railway_fk() {
		return zonal_railway_fk;
	}

	public void setZonal_railway_fk(String zonal_railway_fk) {
		this.zonal_railway_fk = zonal_railway_fk;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}
	
}
