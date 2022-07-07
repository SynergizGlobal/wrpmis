package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FortnightPlan {
	private String fortnightly_plan_id,contract_id_fk,category,critical_item,fortnightly_plan_structure_id,
	component,fortnightly_plan_update_id,  period, cum_planned_last_structure, 
	cum_actual_last_structure, planned_current_structure, cum_planned_last_st, cum_actual_last_st, planned_current_st,user_id,user_role_code,remarks,work_id_fk,total_items,
	designation,user_name,created_by_user_id_fk,module_name,work_name,work_short_name,department_fk;

	public String getFortnightly_plan_id() {
		return fortnightly_plan_id;
	}

	public void setFortnightly_plan_id(String fortnightly_plan_id) {
		this.fortnightly_plan_id = fortnightly_plan_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCritical_item() {
		return critical_item;
	}

	public void setCritical_item(String critical_item) {
		this.critical_item = critical_item;
	}

	public String getFortnightly_plan_structure_id() {
		return fortnightly_plan_structure_id;
	}

	public void setFortnightly_plan_structure_id(String fortnightly_plan_structure_id) {
		this.fortnightly_plan_structure_id = fortnightly_plan_structure_id;
	}

	public String getFortnightly_plan_update_id() {
		return fortnightly_plan_update_id;
	}

	public void setFortnightly_plan_update_id(String fortnightly_plan_update_id) {
		this.fortnightly_plan_update_id = fortnightly_plan_update_id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCum_planned_last_structure() {
		return cum_planned_last_structure;
	}

	public void setCum_planned_last_structure(String cum_planned_last_structure) {
		this.cum_planned_last_structure = cum_planned_last_structure;
	}

	public String getCum_actual_last_structure() {
		return cum_actual_last_structure;
	}

	public void setCum_actual_last_structure(String cum_actual_last_structure) {
		this.cum_actual_last_structure = cum_actual_last_structure;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getPlanned_current_structure() {
		return planned_current_structure;
	}

	public void setPlanned_current_structure(String planned_current_structure) {
		this.planned_current_structure = planned_current_structure;
	}

	public String getCum_planned_last_st() {
		return cum_planned_last_st;
	}

	public void setCum_planned_last_st(String cum_planned_last_st) {
		this.cum_planned_last_st = cum_planned_last_st;
	}

	public String getCum_actual_last_st() {
		return cum_actual_last_st;
	}

	public void setCum_actual_last_st(String cum_actual_last_st) {
		this.cum_actual_last_st = cum_actual_last_st;
	}

	public String getPlanned_current_st() {
		return planned_current_st;
	}

	public void setPlanned_current_st(String planned_current_st) {
		this.planned_current_st = planned_current_st;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_role_code() {
		return user_role_code;
	}

	public void setUser_role_code(String user_role_code) {
		this.user_role_code = user_role_code;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getTotal_items() {
		return total_items;
	}

	public void setTotal_items(String total_items) {
		this.total_items = total_items;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

}
