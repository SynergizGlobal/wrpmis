package com.synergizglobal.wrpmis.model;

import java.util.List;

public class Form {
	private String form_id,module_name_fk,form_name,parent_form_id_sr_fk,web_form_url,mobile_form_url,priority,soft_delete_status_fk,
	folder_name,form_access_id,form_id_fk,access_type,access_value,access_value_id,access_value_name,display_in_mobile,
	user_role_access,user_type_access,user_access,url_type;
	
	private String[] access_types,access_values;
	
	private List<Form> accessPermissions;

	public String getUser_role_access() {
		return user_role_access;
	}

	public void setUser_role_access(String user_role_access) {
		this.user_role_access = user_role_access;
	}

	public String getUser_type_access() {
		return user_type_access;
	}

	public void setUser_type_access(String user_type_access) {
		this.user_type_access = user_type_access;
	}

	public String getUser_access() {
		return user_access;
	}

	public void setUser_access(String user_access) {
		this.user_access = user_access;
	}

	public String getAccess_value_id() {
		return access_value_id;
	}

	public void setAccess_value_id(String access_value_id) {
		this.access_value_id = access_value_id;
	}

	public String getAccess_value_name() {
		return access_value_name;
	}

	public void setAccess_value_name(String access_value_name) {
		this.access_value_name = access_value_name;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

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

	public String getParent_form_id_sr_fk() {
		return parent_form_id_sr_fk;
	}

	public void setParent_form_id_sr_fk(String parent_form_id_sr_fk) {
		this.parent_form_id_sr_fk = parent_form_id_sr_fk;
	}

	public String getWeb_form_url() {
		return web_form_url;
	}

	public void setWeb_form_url(String web_form_url) {
		this.web_form_url = web_form_url;
	}

	public String getMobile_form_url() {
		return mobile_form_url;
	}

	public void setMobile_form_url(String mobile_form_url) {
		this.mobile_form_url = mobile_form_url;
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

	public String getForm_access_id() {
		return form_access_id;
	}

	public void setForm_access_id(String form_access_id) {
		this.form_access_id = form_access_id;
	}

	public String getForm_id_fk() {
		return form_id_fk;
	}

	public void setForm_id_fk(String form_id_fk) {
		this.form_id_fk = form_id_fk;
	}

	public String getAccess_type() {
		return access_type;
	}

	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getAccess_value() {
		return access_value;
	}

	public void setAccess_value(String access_value) {
		this.access_value = access_value;
	}

	public String[] getAccess_types() {
		return access_types;
	}

	public void setAccess_types(String[] access_types) {
		this.access_types = access_types;
	}

	public String[] getAccess_values() {
		return access_values;
	}

	public void setAccess_values(String[] access_values) {
		this.access_values = access_values;
	}

	public List<Form> getAccessPermissions() {
		return accessPermissions;
	}

	public void setAccessPermissions(List<Form> accessPermissions) {
		this.accessPermissions = accessPermissions;
	}

	public String getFolder_name() {
		return folder_name;
	}

	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}

	public String getDisplay_in_mobile() {
		return display_in_mobile;
	}

	public void setDisplay_in_mobile(String display_in_mobile) {
		this.display_in_mobile = display_in_mobile;
	}

	public String getUrl_type() {
		return url_type;
	}

	public void setUrl_type(String url_type) {
		this.url_type = url_type;
	}
	
	
}
