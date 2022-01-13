package com.synergizglobal.pmis.model;

import java.util.List;

public class ModulePermission {

	private String module_name,module_name_fk,form_id,form_name,form_url,priority,soft_delete_status_fk,
	form_access_id,form_id_fk,access_type,access_value,access_value_id,access_value_name,user_role_access,user_type_access,user_access,url_type;
	
	private String user_type_fk,user_role_code,user_id,incharge_user_id_fk,incharge_user_name;
	
	private List<String> access_user_types,access_user_roles,access_users,form_ids,form_names,form_urls;
	
	private List<ModulePermission> accessPermissions,urlTypes;

	

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getUser_type_fk() {
		return user_type_fk;
	}

	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getForm_url() {
		return form_url;
	}

	public void setForm_url(String form_url) {
		this.form_url = form_url;
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

	public List<ModulePermission> getAccessPermissions() {
		return accessPermissions;
	}

	public void setAccessPermissions(List<ModulePermission> accessPermissions) {
		this.accessPermissions = accessPermissions;
	}

	public String getIncharge_user_id_fk() {
		return incharge_user_id_fk;
	}

	public void setIncharge_user_id_fk(String incharge_user_id_fk) {
		this.incharge_user_id_fk = incharge_user_id_fk;
	}

	public String getIncharge_user_name() {
		return incharge_user_name;
	}

	public void setIncharge_user_name(String incharge_user_name) {
		this.incharge_user_name = incharge_user_name;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public List<String> getAccess_user_types() {
		return access_user_types;
	}

	public void setAccess_user_types(List<String> access_user_types) {
		this.access_user_types = access_user_types;
	}

	public List<String> getAccess_user_roles() {
		return access_user_roles;
	}

	public void setAccess_user_roles(List<String> access_user_roles) {
		this.access_user_roles = access_user_roles;
	}

	public List<String> getAccess_users() {
		return access_users;
	}

	public void setAccess_users(List<String> access_users) {
		this.access_users = access_users;
	}

	public List<String> getForm_ids() {
		return form_ids;
	}

	public void setForm_ids(List<String> form_ids) {
		this.form_ids = form_ids;
	}

	public List<String> getForm_names() {
		return form_names;
	}

	public void setForm_names(List<String> form_names) {
		this.form_names = form_names;
	}

	public List<String> getForm_urls() {
		return form_urls;
	}

	public void setForm_urls(List<String> form_urls) {
		this.form_urls = form_urls;
	}

	public List<ModulePermission> getUrlTypes() {
		return urlTypes;
	}

	public void setUrlTypes(List<ModulePermission> urlTypes) {
		this.urlTypes = urlTypes;
	}

	public String getUrl_type() {
		return url_type;
	}

	public void setUrl_type(String url_type) {
		this.url_type = url_type;
	}
	
}
