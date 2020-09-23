package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class StripChart {
	private String strip_chart_id,strip_chart_component_id_fk,strip_chart_activity_id_fk,planned_start,planned_finish,actual_start,actual_finish,unit_fk,scope,completed,component_details,remarks,
	strip_chart_activity_id,strip_chart_activity_name, strip_chart_component,strip_chart_structure, strip_chart_component_id,strip_chart_component_id_name,contract_id_fk,
	strip_chart_line_id_fk,strip_chart_component_fk,strip_chart_section_id_fk,strip_chart_component_name,order,latitude,longtitude,
	strip_chart_line, strip_chart_section_id,strip_chart_section_name, strip_chart_type,project_id,work_id_fk,weight,strip_chart_structure_id_fk,
	progress,progress_date,component_id_color,remaining,issue_description,issue_priority_id,issue_category_id,is_there_issue,attachment_url,created_by_user_id_fk;
	
	private MultipartFile stripChartFile;

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
	
}
