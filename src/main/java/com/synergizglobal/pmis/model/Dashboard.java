package com.synergizglobal.pmis.model;

import java.util.List;

public class Dashboard {

	private String dashboard_id, dashboard_name, work_id_fk, contract_id_fk, module_name_fk, parent_dashboard_id_sr_fk, dashboard_url, mobile_view, dashboard_type_fk, priority, icon_path, published_by_user_id_fk,
	published_on, modified_by_user_id_fk, modified_on, soft_delete_status_fk,folder,work_short_name,contract_short_name,access_type,access_value,access_value_id,access_value_name;
	
	private String[] access_types,access_values;
	
	List<Dashboard> accessPermissions;

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

	public List<Dashboard> getAccessPermissions() {
		return accessPermissions;
	}

	public void setAccessPermissions(List<Dashboard> accessPermissions) {
		this.accessPermissions = accessPermissions;
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

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getDashboard_id() {
		return dashboard_id;
	}

	public void setDashboard_id(String dashboard_id) {
		this.dashboard_id = dashboard_id;
	}

	public String getDashboard_name() {
		return dashboard_name;
	}

	public void setDashboard_name(String dashboard_name) {
		this.dashboard_name = dashboard_name;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getModule_name_fk() {
		return module_name_fk;
	}

	public void setModule_name_fk(String module_name_fk) {
		this.module_name_fk = module_name_fk;
	}

	public String getParent_dashboard_id_sr_fk() {
		return parent_dashboard_id_sr_fk;
	}

	public void setParent_dashboard_id_sr_fk(String parent_dashboard_id_sr_fk) {
		this.parent_dashboard_id_sr_fk = parent_dashboard_id_sr_fk;
	}

	public String getDashboard_url() {
		return dashboard_url;
	}

	public void setDashboard_url(String dashboard_url) {
		this.dashboard_url = dashboard_url;
	}

	public String getMobile_view() {
		return mobile_view;
	}

	public void setMobile_view(String mobile_view) {
		this.mobile_view = mobile_view;
	}

	public String getDashboard_type_fk() {
		return dashboard_type_fk;
	}

	public void setDashboard_type_fk(String dashboard_type_fk) {
		this.dashboard_type_fk = dashboard_type_fk;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getIcon_path() {
		return icon_path;
	}

	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}

	public String getPublished_by_user_id_fk() {
		return published_by_user_id_fk;
	}

	public void setPublished_by_user_id_fk(String published_by_user_id_fk) {
		this.published_by_user_id_fk = published_by_user_id_fk;
	}

	public String getPublished_on() {
		return published_on;
	}

	public void setPublished_on(String published_on) {
		this.published_on = published_on;
	}

	public String getModified_by_user_id_fk() {
		return modified_by_user_id_fk;
	}

	public void setModified_by_user_id_fk(String modified_by_user_id_fk) {
		this.modified_by_user_id_fk = modified_by_user_id_fk;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getSoft_delete_status_fk() {
		return soft_delete_status_fk;
	}

	public void setSoft_delete_status_fk(String soft_delete_status_fk) {
		this.soft_delete_status_fk = soft_delete_status_fk;
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
	
}
