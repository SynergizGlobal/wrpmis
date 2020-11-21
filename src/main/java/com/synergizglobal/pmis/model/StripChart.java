package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class StripChart {
	private String strip_chart_id,strip_chart_component_id_fk,strip_chart_activity_id_fk,planned_start,planned_finish,actual_start,actual_finish,unit_fk,scope,completed,component_details,remarks,
	strip_chart_activity_id,strip_chart_activity_name, strip_chart_component,strip_chart_structure, strip_chart_component_id,strip_chart_component_id_name,contract_id_fk,
	strip_chart_line_id_fk,strip_chart_component_fk,strip_chart_section_id_fk,strip_chart_component_name,order,latitude,longtitude,activity_description,milestone_name,
	strip_chart_line, strip_chart_section_id,strip_chart_section_name, strip_chart_type,project_id,work_id_fk,weight,strip_chart_structure_id_fk,id,total_scope,
	progress,progress_date,component_id_color,remaining,issue_description,issue_priority_id,issue_category_id,is_there_issue,attachment_url,created_by_user_id_fk,
	project_id_fk,project_name,work_id,work_name,contract_id,contract_name,strip_chart_structure_id,structure_type,status_name,work_short_name,contract_short_name;
	
	
	private MultipartFile stripChartFile,uploadFile;
	
	private String [] actualScopes,strip_chart_ids,ids,completedScopes,totalScopes,actual_starts,actual_finishs;
	
	
	public String[] getActual_starts() {
		return actual_starts;
	}

	public void setActual_starts(String[] actual_starts) {
		this.actual_starts = actual_starts;
	}

	public String[] getActual_finishs() {
		return actual_finishs;
	}

	public void setActual_finishs(String[] actual_finishs) {
		this.actual_finishs = actual_finishs;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotal_scope() {
		return total_scope;
	}

	public void setTotal_scope(String total_scope) {
		this.total_scope = total_scope;
	}

	public String getMilestone_name() {
		return milestone_name;
	}

	public void setMilestone_name(String milestone_name) {
		this.milestone_name = milestone_name;
	}

	public String getActivity_description() {
		return activity_description;
	}

	public void setActivity_description(String activity_description) {
		this.activity_description = activity_description;
	}

	public String[] getTotalScopes() {
		return totalScopes;
	}

	public void setTotalScopes(String[] totalScopes) {
		this.totalScopes = totalScopes;
	}

	public String[] getCompletedScopes() {
		return completedScopes;
	}

	public void setCompletedScopes(String[] completedScopes) {
		this.completedScopes = completedScopes;
	}

	public String[] getStrip_chart_ids() {
		return strip_chart_ids;
	}

	public void setStrip_chart_ids(String[] strip_chart_ids) {
		this.strip_chart_ids = strip_chart_ids;
	}

	public String[] getActualScopes() {
		return actualScopes;
	}

	public void setActualScopes(String[] actualScopes) {
		this.actualScopes = actualScopes;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public String getIssue_description() {
		return issue_description;
	}

	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}

	public String getIssue_priority_id() {
		return issue_priority_id;
	}

	public void setIssue_priority_id(String issue_priority_id) {
		this.issue_priority_id = issue_priority_id;
	}

	public String getIssue_category_id() {
		return issue_category_id;
	}

	public void setIssue_category_id(String issue_category_id) {
		this.issue_category_id = issue_category_id;
	}

	public String getIs_there_issue() {
		return is_there_issue;
	}

	public void setIs_there_issue(String is_there_issue) {
		this.is_there_issue = is_there_issue;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getProgress_date() {
		return progress_date;
	}

	public void setProgress_date(String progress_date) {
		this.progress_date = progress_date;
	}

	public String getStrip_chart_id() {
		return strip_chart_id;
	}

	public void setStrip_chart_id(String strip_chart_id) {
		this.strip_chart_id = strip_chart_id;
	}

	public String getStrip_chart_component_id_fk() {
		return strip_chart_component_id_fk;
	}

	public void setStrip_chart_component_id_fk(String strip_chart_component_id_fk) {
		this.strip_chart_component_id_fk = strip_chart_component_id_fk;
	}

	public String getStrip_chart_activity_id_fk() {
		return strip_chart_activity_id_fk;
	}

	public void setStrip_chart_activity_id_fk(String strip_chart_activity_id_fk) {
		this.strip_chart_activity_id_fk = strip_chart_activity_id_fk;
	}

	public String getPlanned_finish() {
		return planned_finish;
	}

	public void setPlanned_finish(String planned_finish) {
		this.planned_finish = planned_finish;
	}

	public String getActual_start() {
		return actual_start;
	}

	public void setActual_start(String actual_start) {
		this.actual_start = actual_start;
	}

	public String getActual_finish() {
		return actual_finish;
	}

	public void setActual_finish(String actual_finish) {
		this.actual_finish = actual_finish;
	}

	public String getUnit_fk() {
		return unit_fk;
	}

	public void setUnit_fk(String unit_fk) {
		this.unit_fk = unit_fk;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getComponent_details() {
		return component_details;
	}

	public void setComponent_details(String component_details) {
		this.component_details = component_details;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStrip_chart_activity_id() {
		return strip_chart_activity_id;
	}

	public void setStrip_chart_activity_id(String strip_chart_activity_id) {
		this.strip_chart_activity_id = strip_chart_activity_id;
	}

	public String getStrip_chart_activity_name() {
		return strip_chart_activity_name;
	}

	public void setStrip_chart_activity_name(String strip_chart_activity_name) {
		this.strip_chart_activity_name = strip_chart_activity_name;
	}

	public String getStrip_chart_component() {
		return strip_chart_component;
	}

	public void setStrip_chart_component(String strip_chart_component) {
		this.strip_chart_component = strip_chart_component;
	}

	public String getStrip_chart_structure() {
		return strip_chart_structure;
	}

	public void setStrip_chart_structure(String strip_chart_structure) {
		this.strip_chart_structure = strip_chart_structure;
	}

	public String getStrip_chart_component_id() {
		return strip_chart_component_id;
	}

	public void setStrip_chart_component_id(String strip_chart_component_id) {
		this.strip_chart_component_id = strip_chart_component_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getStrip_chart_line_id_fk() {
		return strip_chart_line_id_fk;
	}

	public void setStrip_chart_line_id_fk(String strip_chart_line_id_fk) {
		this.strip_chart_line_id_fk = strip_chart_line_id_fk;
	}

	public String getStrip_chart_component_fk() {
		return strip_chart_component_fk;
	}

	public void setStrip_chart_component_fk(String strip_chart_component_fk) {
		this.strip_chart_component_fk = strip_chart_component_fk;
	}

	public String getStrip_chart_section_id_fk() {
		return strip_chart_section_id_fk;
	}

	public void setStrip_chart_section_id_fk(String strip_chart_section_id_fk) {
		this.strip_chart_section_id_fk = strip_chart_section_id_fk;
	}

	public String getStrip_chart_component_name() {
		return strip_chart_component_name;
	}

	public void setStrip_chart_component_name(String strip_chart_component_name) {
		this.strip_chart_component_name = strip_chart_component_name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public String getStrip_chart_line() {
		return strip_chart_line;
	}

	public void setStrip_chart_line(String strip_chart_line) {
		this.strip_chart_line = strip_chart_line;
	}

	public String getStrip_chart_section_id() {
		return strip_chart_section_id;
	}

	public void setStrip_chart_section_id(String strip_chart_section_id) {
		this.strip_chart_section_id = strip_chart_section_id;
	}

	public String getStrip_chart_section_name() {
		return strip_chart_section_name;
	}

	public void setStrip_chart_section_name(String strip_chart_section_name) {
		this.strip_chart_section_name = strip_chart_section_name;
	}

	public String getStrip_chart_type() {
		return strip_chart_type;
	}

	public void setStrip_chart_type(String strip_chart_type) {
		this.strip_chart_type = strip_chart_type;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getStrip_chart_structure_id_fk() {
		return strip_chart_structure_id_fk;
	}

	public void setStrip_chart_structure_id_fk(String strip_chart_structure_id_fk) {
		this.strip_chart_structure_id_fk = strip_chart_structure_id_fk;
	}

	public MultipartFile getStripChartFile() {
		return stripChartFile;
	}

	public void setStripChartFile(MultipartFile stripChartFile) {
		this.stripChartFile = stripChartFile;
	}

	public String getStrip_chart_component_id_name() {
		return strip_chart_component_id_name;
	}

	public void setStrip_chart_component_id_name(String strip_chart_component_id_name) {
		this.strip_chart_component_id_name = strip_chart_component_id_name;
	}

	public String getPlanned_start() {
		return planned_start;
	}

	public void setPlanned_start(String planned_start) {
		this.planned_start = planned_start;
	}

	public String getComponent_id_color() {
		return component_id_color;
	}

	public void setComponent_id_color(String component_id_color) {
		this.component_id_color = component_id_color;
	}

	public String getRemaining() {
		return remaining;
	}

	public void setRemaining(String remaining) {
		this.remaining = remaining;
	}

	public String getAttachment_url() {
		return attachment_url;
	}

	public void setAttachment_url(String attachment_url) {
		this.attachment_url = attachment_url;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
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

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getStrip_chart_structure_id() {
		return strip_chart_structure_id;
	}

	public void setStrip_chart_structure_id(String strip_chart_structure_id) {
		this.strip_chart_structure_id = strip_chart_structure_id;
	}

	public String getStructure_type() {
		return structure_type;
	}

	public void setStructure_type(String structure_type) {
		this.structure_type = structure_type;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
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
	
}
