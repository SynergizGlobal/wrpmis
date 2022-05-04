package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class P6Data {
	
	private String p6_activity_id, p6_task_code,p6_wbs_code_fk, p6_activity_name, status_fk, baseline_start, baseline_finish, 
	start, finish, p6_float,p6_wbs_code, contract_id_fk, fob_id_fk, p6_wbs_name, p6_wbs_parent_code,contract_short_name, p6_wbs_category_fk,
	p6_activity_data_id, data_date, soft_delete_status_fk, p6_file_path, uploaded_by_user_id_fk, uploaded_date,upload_type,contract_id,
	contract_name,fob_id,fob_name,created_by_user_id_fk,user_name,designation;

	
	private MultipartFile p6dataFile;
	

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
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

	public String getFob_id() {
		return fob_id;
	}

	public void setFob_id(String fob_id) {
		this.fob_id = fob_id;
	}

	public String getFob_name() {
		return fob_name;
	}

	public void setFob_name(String fob_name) {
		this.fob_name = fob_name;
	}

	public MultipartFile getP6dataFile() {
		return p6dataFile;
	}

	public void setP6dataFile(MultipartFile p6dataFile) {
		this.p6dataFile = p6dataFile;
	}

	public String getP6_activity_id() {
		return p6_activity_id;
	}

	public void setP6_activity_id(String p6_activity_id) {
		this.p6_activity_id = p6_activity_id;
	}

	public String getP6_task_code() {
		return p6_task_code;
	}

	public void setP6_task_code(String p6_task_code) {
		this.p6_task_code = p6_task_code;
	}

	public String getP6_wbs_code_fk() {
		return p6_wbs_code_fk;
	}

	public void setP6_wbs_code_fk(String p6_wbs_code_fk) {
		this.p6_wbs_code_fk = p6_wbs_code_fk;
	}

	public String getP6_activity_name() {
		return p6_activity_name;
	}

	public void setP6_activity_name(String p6_activity_name) {
		this.p6_activity_name = p6_activity_name;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
	}

	public String getBaseline_start() {
		return baseline_start;
	}

	public void setBaseline_start(String baseline_start) {
		this.baseline_start = baseline_start;
	}

	public String getBaseline_finish() {
		return baseline_finish;
	}

	public void setBaseline_finish(String baseline_finish) {
		this.baseline_finish = baseline_finish;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getP6_float() {
		return p6_float;
	}

	public void setP6_float(String p6_float) {
		this.p6_float = p6_float;
	}

	public String getP6_wbs_code() {
		return p6_wbs_code;
	}

	public void setP6_wbs_code(String p6_wbs_code) {
		this.p6_wbs_code = p6_wbs_code;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getFob_id_fk() {
		return fob_id_fk;
	}

	public void setFob_id_fk(String fob_id_fk) {
		this.fob_id_fk = fob_id_fk;
	}

	public String getP6_wbs_name() {
		return p6_wbs_name;
	}

	public void setP6_wbs_name(String p6_wbs_name) {
		this.p6_wbs_name = p6_wbs_name;
	}

	public String getP6_wbs_parent_code() {
		return p6_wbs_parent_code;
	}

	public void setP6_wbs_parent_code(String p6_wbs_parent_code) {
		this.p6_wbs_parent_code = p6_wbs_parent_code;
	}

	public String getP6_wbs_category_fk() {
		return p6_wbs_category_fk;
	}

	public void setP6_wbs_category_fk(String p6_wbs_category_fk) {
		this.p6_wbs_category_fk = p6_wbs_category_fk;
	}

	public String getP6_activity_data_id() {
		return p6_activity_data_id;
	}

	public void setP6_activity_data_id(String p6_activity_data_id) {
		this.p6_activity_data_id = p6_activity_data_id;
	}

	public String getData_date() {
		return data_date;
	}

	public void setData_date(String data_date) {
		this.data_date = data_date;
	}

	public String getSoft_delete_status_fk() {
		return soft_delete_status_fk;
	}

	public void setSoft_delete_status_fk(String soft_delete_status_fk) {
		this.soft_delete_status_fk = soft_delete_status_fk;
	}

	public String getP6_file_path() {
		return p6_file_path;
	}

	public void setP6_file_path(String p6_file_path) {
		this.p6_file_path = p6_file_path;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getUploaded_date() {
		return uploaded_date;
	}

	public void setUploaded_date(String uploaded_date) {
		this.uploaded_date = uploaded_date;
	}

	public String getUpload_type() {
		return upload_type;
	}

	public void setUpload_type(String upload_type) {
		this.upload_type = upload_type;
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

}
