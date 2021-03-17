package com.synergizglobal.pmis.model;

public class Admin {
	
	private String admin_form_id, form_name, url, priority, soft_delete_status_fk;

	public String getAdmin_form_id() {
		return admin_form_id;
	}

	public void setAdmin_form_id(String admin_form_id) {
		this.admin_form_id = admin_form_id;
	}

	public String getForm_name() {
		return form_name;
	}

	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSoft_delete_status_fk() {
		return soft_delete_status_fk;
	}

	public void setSoft_delete_status_fk(String soft_delete_status_fk) {
		this.soft_delete_status_fk = soft_delete_status_fk;
	}

}
