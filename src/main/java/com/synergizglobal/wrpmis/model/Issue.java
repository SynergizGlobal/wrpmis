package com.synergizglobal.wrpmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Issue {
	private String issue_id,contract_id_fk,activity,title,short_description,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,user_name,
	priority_fk,category_fk,status_fk,corrective_measure,resolved_date,escalated_to,remarks,priority,category,status,contract_id,contract_name,work_id_fk,work_name,work_short_name,
	project_id_fk,project_name,activity_name,strip_chart_component_fk,department,department_name,attachments,railway_id,railway_name,zonal_railway_fk,contract_short_name,
	other_organization,escalation_date,contractor_id_fk,contractor_id,contractor_name,hod_user_id_fk,designation,hod_name,pending_since,hod,
	reported_by_user_id,responsible_person_user_id,escalated_to_user_id,reported_by_designation,responsible_person_designation,escalated_to_designation,
	reported_by_email_id,responsible_person_email_id,escalated_to_email_id,assigned_date,contract_hod_email_id,contract_dyhod_email_id,contract_type_fk,
	dy_hod_user_id_fk,user_type,user_role_code,user_id,file_name,message_id,remarks_old,remarks_new,hod_designation,comment,existingAssignedPerson, 
	dyHod_designation,pending_Since,other_org_resposible_person_name,other_org_resposible_person_designation,created_by,alerts_user_id,modified_by,modified_date,structure,component;
	
	private String contract_hod_user_id,contract_dyhod_user_id,created_by_user_id_fk,created_date,created_by_email_id,
	existing_status_fk,mail_body_header,existing_responsible_person,existing_escalated_to,issue_file_type_fk,issue_file_type,
	issue_file_id,assigned_person_user_id_fk,total_issues,closed_issues,open_issues,la_id,cmd_email,dp_email,dt_email,contractor_email,sse_email,pe_email,aen_mail,
	cmd_name,dp_name,dt_name,dyhod_name,pe_name,aen_name,Sse_name,curdate,issues_related_to,action,ass_email,ass_name,assr_email,assr_name,rs_email,rs_name,pm_email,pm_name,
	ae_email,ae_name,spe_email,spe_name,actionremarks,title1,issue_other_organization
	;

	private boolean readonlyForm = true;
	private MultipartFile issueFile;
	
	//private List<MultipartFile> issueFiles;
	private MultipartFile[] issueFiles;
	private String[] issueFileNames,attachemnts,issue_file_types,issue_file_ids;
	private List<Issue> issueFilesList;
	
	
	
	public String getTotal_issues() {
		return total_issues;
	}

	public void setTotal_issues(String total_issues) {
		this.total_issues = total_issues;
	}

	public String getClosed_issues() {
		return closed_issues;
	}

	public void setClosed_issues(String closed_issues) {
		this.closed_issues = closed_issues;
	}

	public String getOpen_issues() {
		return open_issues;
	}

	public void setOpen_issues(String open_issues) {
		this.open_issues = open_issues;
	}

	public String getRemarks_old() {
		return remarks_old;
	}

	public void setRemarks_old(String remarks_old) {
		this.remarks_old = remarks_old;
	}

	public String getRemarks_new() {
		return remarks_new;
	}

	public void setRemarks_new(String remarks_new) {
		this.remarks_new = remarks_new;
	}

	

	public String getExistingAssignedPerson() {
		return existingAssignedPerson;
	}

	public void setExistingAssignedPerson(String existingAssignedPerson) {
		this.existingAssignedPerson = existingAssignedPerson;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPending_Since() {
		return pending_Since;
	}

	public void setPending_Since(String pending_Since) {
		this.pending_Since = pending_Since;
	}

	public String getHod_designation() {
		return hod_designation;
	}

	public void setHod_designation(String hod_designation) {
		this.hod_designation = hod_designation;
	}

	public String getDyHod_designation() {
		return dyHod_designation;
	}

	public void setDyHod_designation(String dyHod_designation) {
		this.dyHod_designation = dyHod_designation;
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

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by_email_id() {
		return created_by_email_id;
	}

	public void setCreated_by_email_id(String created_by_email_id) {
		this.created_by_email_id = created_by_email_id;
	}

	public String getExisting_status_fk() {
		return existing_status_fk;
	}

	public void setExisting_status_fk(String existing_status_fk) {
		this.existing_status_fk = existing_status_fk;
	}

	public String getMail_body_header() {
		return mail_body_header;
	}

	public void setMail_body_header(String mail_body_header) {
		this.mail_body_header = mail_body_header;
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

	public String getOther_org_resposible_person_name() {
		return other_org_resposible_person_name;
	}

	public void setOther_org_resposible_person_name(String other_org_resposible_person_name) {
		this.other_org_resposible_person_name = other_org_resposible_person_name;
	}

	public String getOther_org_resposible_person_designation() {
		return other_org_resposible_person_designation;
	}

	public void setOther_org_resposible_person_designation(String other_org_resposible_person_designation) {
		this.other_org_resposible_person_designation = other_org_resposible_person_designation;
	}

	public String getContract_type_fk() {
		return contract_type_fk;
	}

	public void setContract_type_fk(String contract_type_fk) {
		this.contract_type_fk = contract_type_fk;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String[] getAttachemnts() {
		return attachemnts;
	}

	public void setAttachemnts(String[] attachemnts) {
		this.attachemnts = attachemnts;
	}

	public String getIssue_file_type_fk() {
		return issue_file_type_fk;
	}

	public void setIssue_file_type_fk(String issue_file_type_fk) {
		this.issue_file_type_fk = issue_file_type_fk;
	}

	public String getIssue_file_type() {
		return issue_file_type;
	}

	public void setIssue_file_type(String issue_file_type) {
		this.issue_file_type = issue_file_type;
	}

	public String[] getIssue_file_types() {
		return issue_file_types;
	}

	public void setIssue_file_types(String[] issue_file_types) {
		this.issue_file_types = issue_file_types;
	}

	public MultipartFile[] getIssueFiles() {
		return issueFiles;
	}

	public void setIssueFiles(MultipartFile[] issueFiles) {
		this.issueFiles = issueFiles;
	}

	public String getIssue_file_id() {
		return issue_file_id;
	}

	public void setIssue_file_id(String issue_file_id) {
		this.issue_file_id = issue_file_id;
	}

	public String[] getIssue_file_ids() {
		return issue_file_ids;
	}

	public void setIssue_file_ids(String[] issue_file_ids) {
		this.issue_file_ids = issue_file_ids;
	}

	public String getAssigned_person_user_id_fk() {
		return assigned_person_user_id_fk;
	}

	public void setAssigned_person_user_id_fk(String assigned_person_user_id_fk) {
		this.assigned_person_user_id_fk = assigned_person_user_id_fk;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getAlerts_user_id() {
		return alerts_user_id;
	}

	public void setAlerts_user_id(String alerts_user_id) {
		this.alerts_user_id = alerts_user_id;
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

	public String getLa_id() {
		return la_id;
	}

	public void setLa_id(String la_id) {
		this.la_id = la_id;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getCmd_email() {
		return cmd_email;
	}

	public void setCmd_email(String cmd_email) {
		this.cmd_email = cmd_email;
	}

	public String getDp_email() {
		return dp_email;
	}

	public void setDp_email(String dp_email) {
		this.dp_email = dp_email;
	}

	public String getDt_email() {
		return dt_email;
	}

	public void setDt_email(String dt_email) {
		this.dt_email = dt_email;
	}

	public String getContractor_email() {
		return contractor_email;
	}

	public void setContractor_email(String contractor_email) {
		this.contractor_email = contractor_email;
	}

	public String getCmd_name() {
		return cmd_name;
	}

	public void setCmd_name(String cmd_name) {
		this.cmd_name = cmd_name;
	}

	public String getDp_name() {
		return dp_name;
	}

	public void setDp_name(String dp_name) {
		this.dp_name = dp_name;
	}

	public String getDt_name() {
		return dt_name;
	}

	public void setDt_name(String dt_name) {
		this.dt_name = dt_name;
	}

	public String getDyhod_name() {
		return dyhod_name;
	}

	public void setDyhod_name(String dyhod_name) {
		this.dyhod_name = dyhod_name;
	}

	public String getPe_name() {
		return pe_name;
	}

	public void setPe_name(String pe_name) {
		this.pe_name = pe_name;
	}

	public String getAen_name() {
		return aen_name;
	}

	public void setAen_name(String aen_name) {
		this.aen_name = aen_name;
	}

	public String getSse_name() {
		return Sse_name;
	}

	public void setSse_name(String sse_name) {
		Sse_name = sse_name;
	}

	public String getSse_email() {
		return sse_email;
	}

	public void setSse_email(String sse_email) {
		this.sse_email = sse_email;
	}

	public String getPe_email() {
		return pe_email;
	}

	public void setPe_email(String pe_email) {
		this.pe_email = pe_email;
	}

	public String getAen_mail() {
		return aen_mail;
	}

	public void setAen_mail(String aen_mail) {
		this.aen_mail = aen_mail;
	}

	public String getCurdate() {
		return curdate;
	}

	public void setCurdate(String curdate) {
		this.curdate = curdate;
	}

	public String getIssues_related_to() {
		return issues_related_to;
	}

	public void setIssues_related_to(String issues_related_to) {
		this.issues_related_to = issues_related_to;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAss_email() {
		return ass_email;
	}

	public void setAss_email(String ass_email) {
		this.ass_email = ass_email;
	}

	public String getAss_name() {
		return ass_name;
	}

	public void setAss_name(String ass_name) {
		this.ass_name = ass_name;
	}

	public String getAssr_email() {
		return assr_email;
	}

	public void setAssr_email(String assr_email) {
		this.assr_email = assr_email;
	}

	public String getAssr_name() {
		return assr_name;
	}

	public void setAssr_name(String assr_name) {
		this.assr_name = assr_name;
	}

	public String getRs_email() {
		return rs_email;
	}

	public void setRs_email(String rs_email) {
		this.rs_email = rs_email;
	}

	public String getRs_name() {
		return rs_name;
	}

	public void setRs_name(String rs_name) {
		this.rs_name = rs_name;
	}

	public String getPm_email() {
		return pm_email;
	}

	public void setPm_email(String pm_email) {
		this.pm_email = pm_email;
	}

	public String getPm_name() {
		return pm_name;
	}

	public void setPm_name(String pm_name) {
		this.pm_name = pm_name;
	}

	public String getAe_email() {
		return ae_email;
	}

	public void setAe_email(String ae_email) {
		this.ae_email = ae_email;
	}

	public String getAe_name() {
		return ae_name;
	}

	public void setAe_name(String ae_name) {
		this.ae_name = ae_name;
	}

	public String getSpe_email() {
		return spe_email;
	}

	public void setSpe_email(String spe_email) {
		this.spe_email = spe_email;
	}

	public String getSpe_name() {
		return spe_name;
	}

	public void setSpe_name(String spe_name) {
		this.spe_name = spe_name;
	}

	public String getActionremarks() {
		return actionremarks;
	}

	public void setActionremarks(String actionremarks) {
		this.actionremarks = actionremarks;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getIssue_other_organization() {
		return issue_other_organization;
	}

	public void setIssue_other_organization(String issue_other_organization) {
		this.issue_other_organization = issue_other_organization;
	}
	
}
