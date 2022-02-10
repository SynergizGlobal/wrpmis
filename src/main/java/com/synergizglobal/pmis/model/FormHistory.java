package com.synergizglobal.pmis.model;

public class FormHistory {
	private String form_history_id,module_name_fk,form_name,module_name,work,contract,form_action_type,form_details,created_by_user_id_fk,user,created_date,sub_work;

	public String getModule_name_fk() {
		return module_name_fk;
	}

	public void setModule_name_fk(String module_name_fk) {
		this.module_name_fk = module_name_fk;
	}

	public String getForm_name() {
		return form_name;
	}

	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	public String getForm_history_id() {
		return form_history_id;
	}

	public void setForm_history_id(String form_history_id) {
		this.form_history_id = form_history_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getForm_action_type() {
		return form_action_type;
	}

	public void setForm_action_type(String form_action_type) {
		this.form_action_type = form_action_type;
	}

	public String getForm_details() {
		return form_details;
	}

	public void setForm_details(String form_details) {
		this.form_details = form_details;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getSub_work() {
		return sub_work;
	}

	public void setSub_work(String sub_work) {
		this.sub_work = sub_work;
	}
	
}
