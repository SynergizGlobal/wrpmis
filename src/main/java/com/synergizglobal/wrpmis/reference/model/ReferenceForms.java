package com.synergizglobal.wrpmis.reference.model;

public class ReferenceForms {
	
	private String reference_forms_id, name, form_url, module_fk,module_name,url_type,soft_delete_status_fk;

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getReference_forms_id() {
		return reference_forms_id;
	}

	public void setReference_forms_id(String reference_forms_id) {
		this.reference_forms_id = reference_forms_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getForm_url() {
		return form_url;
	}

	public void setForm_url(String form_url) {
		this.form_url = form_url;
	}

	public String getModule_fk() {
		return module_fk;
	}

	public void setModule_fk(String module_fk) {
		this.module_fk = module_fk;
	}

	public String getUrl_type() {
		return url_type;
	}

	public void setUrl_type(String url_type) {
		this.url_type = url_type;
	}

	public String getSoft_delete_status_fk() {
		return soft_delete_status_fk;
	}

	public void setSoft_delete_status_fk(String soft_delete_status_fk) {
		this.soft_delete_status_fk = soft_delete_status_fk;
	}

}
