package com.synergizglobal.pmis.model;

import java.util.List;
import java.util.Map;

public class UserActivityReport {

	private String form_history_id, module_name, work, contract, form_action_type, form_details, created_by_user_id_fk, 
	user,user_id,time,from_date,to_date, created_date,user_type_fk,user_role_code,date,inactive_since,work_id_fk,user_id_fk,user_name;

	private List<UserActivityReport> datesList;
	private List<UserActivityReport> userActivitiesList;
	private List<UserActivityReport> usersList;
	
	private Map<String,List<UserActivityReport>> inactiveUsers;
	
	private String work_id,work_name,work_short_name,module_name_fk,form_name,last_updated_date,
	contract_id,contract_id_fk,contract_short_name,contract_name,user_inactive;
	
	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
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

	public List<UserActivityReport> getDatesList() {
		return datesList;
	}

	public void setDatesList(List<UserActivityReport> datesList) {
		this.datesList = datesList;
	}

	public List<UserActivityReport> getUserActivitiesList() {
		return userActivitiesList;
	}

	public void setUserActivitiesList(List<UserActivityReport> userActivitiesList) {
		this.userActivitiesList = userActivitiesList;
	}

	public List<UserActivityReport> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserActivityReport> usersList) {
		this.usersList = usersList;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getInactive_since() {
		return inactive_since;
	}

	public void setInactive_since(String inactive_since) {
		this.inactive_since = inactive_since;
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

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Map<String,List<UserActivityReport>> getInactiveUsers() {
		return inactiveUsers;
	}

	public void setInactiveUsers(Map<String,List<UserActivityReport>> inactiveUsers) {
		this.inactiveUsers = inactiveUsers;
	}

	public String getLast_updated_date() {
		return last_updated_date;
	}

	public void setLast_updated_date(String last_updated_date) {
		this.last_updated_date = last_updated_date;
	}

	public String getUser_inactive() {
		return user_inactive;
	}

	public void setUser_inactive(String user_inactive) {
		this.user_inactive = user_inactive;
	}
	
}
