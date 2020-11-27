package com.synergizglobal.pmis.model;

import java.util.List;

public class Training {

	private String training_id, training_type_fk, training_category_fk, faculty_name, designation,
	title, description, training_center, status_fk, remarks,training_attendees_id, training_id_fk, training_session_id_fk, 
	department_fk, attendee, mobile_no, required_fk, participated_fk,training_session_id, session_no, start_time, end_time,hours,department_name,
	is_there_issue,category,issue_description,created_by_user_id_fk,issue_priority_id,issue_category_id ;

	private List<Training> trainingSessions;
	private List<Training> trainingAttendees;
	
	private String[] training_attendees_ids, training_id_fks, training_session_id_fks, department_fks, attendees, mobile_nos, required_fks, participated_fks,
	training_session_ids, session_nos, start_times, end_times, remarkss;


	public String getIssue_description() {
		return issue_description;
	}

	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getIs_there_issue() {
		return is_there_issue;
	}

	public void setIs_there_issue(String is_there_issue) {
		this.is_there_issue = is_there_issue;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public List<Training> getTrainingAttendees() {
		return trainingAttendees;
	}

	public void setTrainingAttendees(List<Training> trainingAttendees) {
		this.trainingAttendees = trainingAttendees;
	}



	public List<Training> getTrainingSessions() {
		return trainingSessions;
	}

	public void setTrainingSessions(List<Training> trainingSessions) {
		this.trainingSessions = trainingSessions;
	}

	public String[] getTraining_attendees_ids() {
		return training_attendees_ids;
	}

	public void setTraining_attendees_ids(String[] training_attendees_ids) {
		this.training_attendees_ids = training_attendees_ids;
	}

	public String[] getTraining_id_fks() {
		return training_id_fks;
	}

	public void setTraining_id_fks(String[] training_id_fks) {
		this.training_id_fks = training_id_fks;
	}

	public String[] getTraining_session_id_fks() {
		return training_session_id_fks;
	}

	public void setTraining_session_id_fks(String[] training_session_id_fks) {
		this.training_session_id_fks = training_session_id_fks;
	}

	public String[] getDepartment_fks() {
		return department_fks;
	}

	public void setDepartment_fks(String[] department_fks) {
		this.department_fks = department_fks;
	}

	public String[] getAttendees() {
		return attendees;
	}

	public void setAttendees(String[] attendees) {
		this.attendees = attendees;
	}

	public String[] getMobile_nos() {
		return mobile_nos;
	}

	public void setMobile_nos(String[] mobile_nos) {
		this.mobile_nos = mobile_nos;
	}

	public String[] getRequired_fks() {
		return required_fks;
	}

	public void setRequired_fks(String[] required_fks) {
		this.required_fks = required_fks;
	}

	public String[] getParticipated_fks() {
		return participated_fks;
	}

	public void setParticipated_fks(String[] participated_fks) {
		this.participated_fks = participated_fks;
	}

	public String[] getTraining_session_ids() {
		return training_session_ids;
	}

	public void setTraining_session_ids(String[] training_session_ids) {
		this.training_session_ids = training_session_ids;
	}

	public String[] getSession_nos() {
		return session_nos;
	}

	public void setSession_nos(String[] session_nos) {
		this.session_nos = session_nos;
	}

	public String[] getStart_times() {
		return start_times;
	}

	public void setStart_times(String[] start_times) {
		this.start_times = start_times;
	}

	public String[] getEnd_times() {
		return end_times;
	}

	public void setEnd_times(String[] end_times) {
		this.end_times = end_times;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getTraining_id() {
		return training_id;
	}

	public void setTraining_id(String training_id) {
		this.training_id = training_id;
	}

	public String getTraining_type_fk() {
		return training_type_fk;
	}

	public void setTraining_type_fk(String training_type_fk) {
		this.training_type_fk = training_type_fk;
	}

	public String getTraining_category_fk() {
		return training_category_fk;
	}

	public void setTraining_category_fk(String training_category_fk) {
		this.training_category_fk = training_category_fk;
	}

	public String getFaculty_name() {
		return faculty_name;
	}

	public void setFaculty_name(String faculty_name) {
		this.faculty_name = faculty_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getTraining_center() {
		return training_center;
	}

	public void setTraining_center(String training_center) {
		this.training_center = training_center;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTraining_attendees_id() {
		return training_attendees_id;
	}

	public void setTraining_attendees_id(String training_attendees_id) {
		this.training_attendees_id = training_attendees_id;
	}

	public String getTraining_id_fk() {
		return training_id_fk;
	}

	public void setTraining_id_fk(String training_id_fk) {
		this.training_id_fk = training_id_fk;
	}

	public String getTraining_session_id_fk() {
		return training_session_id_fk;
	}

	public void setTraining_session_id_fk(String training_session_id_fk) {
		this.training_session_id_fk = training_session_id_fk;
	}

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getRequired_fk() {
		return required_fk;
	}

	public void setRequired_fk(String required_fk) {
		this.required_fk = required_fk;
	}

	public String getParticipated_fk() {
		return participated_fk;
	}

	public void setParticipated_fk(String participated_fk) {
		this.participated_fk = participated_fk;
	}

	public String getTraining_session_id() {
		return training_session_id;
	}

	public void setTraining_session_id(String training_session_id) {
		this.training_session_id = training_session_id;
	}

	public String getSession_no() {
		return session_no;
	}

	public void setSession_no(String session_no) {
		this.session_no = session_no;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
