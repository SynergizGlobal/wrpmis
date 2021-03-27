package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Issue {
	private String issue_id,contract_id_fk,activity,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,user_name,
	priority_fk,category_fk,status_fk,corrective_measure,resolved_date,escalated_to,remarks,priority,category,status,contract_id,contract_name,work_id_fk,work_name,work_short_name,
	project_id_fk,project_name,activity_name,strip_chart_component_fk,department,department_name,attachments,railway_id,railway_name,zonal_railway_fk,contract_short_name,
	other_organization,escalation_date,contractor_id_fk,contractor_id,contractor_name,hod_user_id_fk,designation,hod_name,pending_since,hod,
	reported_by_user_id,responsible_person_user_id,escalated_to_user_id,reported_by_designation,responsible_person_designation,escalated_to_designation,
	reported_by_email_id,responsible_person_email_id,escalated_to_email_id,assigned_date,contract_hod_email_id,contract_dyhod_email_id,
	dy_hod_user_id_fk,user_type,user_role_code,user_id,file_name;

	private boolean readonlyForm = true;
	private MultipartFile issueFile;
	
	private List<MultipartFile> issueFiles;
	private String[] issueFileNames;
	
	private List<Issue> issueFilesList;
	
	public String getAssigned_date() {
		return assigned_date;
	}

	public void setAssigned_date(String assigned_date) {
		this.assigned_date = assigned_date;
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

	public String getEscalated_to_email_id() {
		return escalated_to_email_id;
	}

	public void setEscalated_to_email_id(String escalated_to_email_id) {
		this.escalated_to_email_id = escalated_to_email_id;
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

	public String getReported_by_designation() {
		return reported_by_designation;
	}

	public void setReported_by_designation(String reported_by_designation) {
		this.reported_by_designation = reported_by_designation;
	}

	public String getResponsible_person_designation() {
		return responsible_person_designation;
	}

	public void setResponsible_person_designation(String responsible_person_designation) {
		this.responsible_person_designation = responsible_person_designation;
	}

	public String getEscalated_to_designation() {
		return escalated_to_designation;
	}

	public void setEscalated_to_designation(String escalated_to_designation) {
		this.escalated_to_designation = escalated_to_designation;
	}

	public String getReported_by_user_id() {
		return reported_by_user_id;
	}

	public void setReported_by_user_id(String reported_by_user_id) {
		this.reported_by_user_id = reported_by_user_id;
	}

	public String getResponsible_person_user_id() {
		return responsible_person_user_id;
	}

	public void setResponsible_person_user_id(String responsible_person_user_id) {
		this.responsible_person_user_id = responsible_person_user_id;
	}

	public String getEscalated_to_user_id() {
		return escalated_to_user_id;
	}

	public void setEscalated_to_user_id(String escalated_to_user_id) {
		this.escalated_to_user_id = escalated_to_user_id;
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

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
	}

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

	public String getOther_organization() {
		return other_organization;
	}

	public void setOther_organization(String other_organization) {
		this.other_organization = other_organization;
	}

	public String getEscalation_date() {
		return escalation_date;
	}

	public void setEscalation_date(String escalation_date) {
		this.escalation_date = escalation_date;
	}

	public String getHod_name() {
		return hod_name;
	}

	public void setHod_name(String hod_name) {
		this.hod_name = hod_name;
	}

	public String getPending_since() {
		return pending_since;
	}

	public void setPending_since(String pending_since) {
		this.pending_since = pending_since;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getDy_hod_user_id_fk() {
		return dy_hod_user_id_fk;
	}

	public void setDy_hod_user_id_fk(String dy_hod_user_id_fk) {
		this.dy_hod_user_id_fk = dy_hod_user_id_fk;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public boolean isReadonlyForm() {
		return readonlyForm;
	}

	public void setReadonlyForm(boolean readonlyForm) {
		this.readonlyForm = readonlyForm;
	}

	public List<MultipartFile> getIssueFiles() {
		return issueFiles;
	}

	public void setIssueFiles(List<MultipartFile> issueFiles) {
		this.issueFiles = issueFiles;
	}

	public String[] getIssueFileNames() {
		return issueFileNames;
	}

	public void setIssueFileNames(String[] issueFileNames) {
		this.issueFileNames = issueFileNames;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public List<Issue> getIssueFilesList() {
		return issueFilesList;
	}

	public void setIssueFilesList(List<Issue> issueFilesList) {
		this.issueFilesList = issueFilesList;
	}
	
}
