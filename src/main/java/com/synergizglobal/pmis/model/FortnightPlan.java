package com.synergizglobal.pmis.model;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FortnightPlan {
	private String fortnightly_plan_id,contract_id_fk,category,fortnightly_plan_structure_id,data_id,actual_current_st,
	component,fortnightly_plan_update_id,  period, cum_planned_last_structure,period_value, 
	cum_actual_last_structure, planned_current_structure, cum_planned_last_st, cum_actual_last_st, planned_current_st,user_id,user_role_code,work_id_fk,total_items,
	designation,user_name,created_by_user_id_fk,module_name,work_name,work_short_name,department_fk,
	contract_short_name,structure,user_type_fk,structure_type_fk,remarks,critical,activity_name,scope,status, uploaded_by_user_id_fk,item,tdc_calendar,criticality,color,
	scope_of_work_quarterly,fortnight_quarterly_plan_id,fortnight_date,unit,cum_progress,cumulative_progress,revision_no,tdc_date,contractor_name,
	target_till_lfn,actual_till_lfn,target_this_fn,actual_this_fn,cum_target,cum_actual,filename,uploaded_by,uploaded_date;
	
	
	private String [] activity,scope_of_work,critical_item,completion_status,planned_progress_on_last_fortnight, actual_progress_on_last_fortnight, plan_for_the_current_fortnight,
	chkcompletion_status,fortnight,units,pending_progress,reason_for_shortfall,Fortnight_quarterly_plan_activity_id,revisionno,tdc_revisiondate,project_id,project_name;
	

	
	private MultipartFile fortnightPlanFile;



	public String getFortnightly_plan_id() {
		return fortnightly_plan_id; 
	}

	public void setFortnightly_plan_id(String fortnightly_plan_id) {
		this.fortnightly_plan_id = fortnightly_plan_id;
	}

	public boolean checkNullOrEmpty() throws IllegalAccessException {
		boolean flag = true;
		try {
			for (Field f : getClass().getDeclaredFields())
		        if (!StringUtils.isEmpty(f.get(this)))
		        	flag = false;
		} catch (Exception e) {
			
		}
	    
	    return flag;            
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

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getUser_type_fk() {
		return user_type_fk;
	}

	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}

	public String getStructure_type_fk() {
		return structure_type_fk;
	}

	public void setStructure_type_fk(String structure_type_fk) {
		this.structure_type_fk = structure_type_fk;
	}

	public String [] getActivity() {
		return activity;
	}

	public void setActivity(String [] activity) {
		this.activity = activity;
	}

	public String [] getScope_of_work() {
		return scope_of_work;
	}

	public void setScope_of_work(String [] scope_of_work) {
		this.scope_of_work = scope_of_work;
	}

	public String [] getCompletion_status() {
		return completion_status;
	}

	public void setCompletion_status(String [] completion_status) {
		this.completion_status = completion_status;
	}

	public String [] getPlanned_progress_on_last_fortnight() {
		return planned_progress_on_last_fortnight;
	}

	public void setPlanned_progress_on_last_fortnight(String [] planned_progress_on_last_fortnight) {
		this.planned_progress_on_last_fortnight = planned_progress_on_last_fortnight;
	}

	public String [] getActual_progress_on_last_fortnight() {
		return actual_progress_on_last_fortnight;
	}

	public void setActual_progress_on_last_fortnight(String [] actual_progress_on_last_fortnight) {
		this.actual_progress_on_last_fortnight = actual_progress_on_last_fortnight;
	}

	public String [] getPlan_for_the_current_fortnight() {
		return plan_for_the_current_fortnight;
	}

	public void setPlan_for_the_current_fortnight(String [] plan_for_the_current_fortnight) {
		this.plan_for_the_current_fortnight = plan_for_the_current_fortnight;
	}

	public String [] getChkcompletion_status() {
		return chkcompletion_status;
	}

	public void setChkcompletion_status(String [] chkcompletion_status) {
		this.chkcompletion_status = chkcompletion_status;
	}

	public String [] getCritical_item() {
		return critical_item;
	}

	public void setCritical_item(String [] critical_item) {
		this.critical_item = critical_item;
	}

	public String getCritical() {
		return critical;
	}

	public void setCritical(String critical) {
		this.critical = critical;
	}

	public String getPeriod_value() {
		return period_value;
	}

	public void setPeriod_value(String period_value) {
		this.period_value = period_value;
	}

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getActual_current_st() {
		return actual_current_st;
	}

	public void setActual_current_st(String actual_current_st) {
		this.actual_current_st = actual_current_st;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCumulative_progress() {
		return cumulative_progress;
	}

	public void setCumulative_progress(String cumulative_progress) {
		this.cumulative_progress = cumulative_progress;
	}

	public String getTdc_calendar() {
		return tdc_calendar;
	}

	public void setTdc_calendar(String tdc_calendar) {
		this.tdc_calendar = tdc_calendar;
	}

	public String [] getFortnight() {
		return fortnight;
	}

	public void setFortnight(String [] fortnight) {
		this.fortnight = fortnight;
	}

	public String getScope_of_work_quarterly() {
		return scope_of_work_quarterly;
	}

	public void setScope_of_work_quarterly(String scope_of_work_quarterly) {
		this.scope_of_work_quarterly = scope_of_work_quarterly;
	}

	public String getCriticality() {
		return criticality;
	}

	public void setCriticality(String criticality) {
		this.criticality = criticality;
	}

	public String getFortnight_quarterly_plan_id() {
		return fortnight_quarterly_plan_id;
	}

	public void setFortnight_quarterly_plan_id(String fortnight_quarterly_plan_id) {
		this.fortnight_quarterly_plan_id = fortnight_quarterly_plan_id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String [] getUnits() {
		return units;
	}

	public void setUnits(String [] units) {
		this.units = units;
	}

	public String getFortnight_date() {
		return fortnight_date;
	}

	public void setFortnight_date(String fortnight_date) {
		this.fortnight_date = fortnight_date;
	}

	public String [] getPending_progress() {
		return pending_progress;
	}

	public void setPending_progress(String [] pending_progress) {
		this.pending_progress = pending_progress;
	}

	public String [] getReason_for_shortfall() {
		return reason_for_shortfall;
	}

	public void setReason_for_shortfall(String [] reason_for_shortfall) {
		this.reason_for_shortfall = reason_for_shortfall;
	}

	public String [] getFortnight_quarterly_plan_activity_id() {
		return Fortnight_quarterly_plan_activity_id;
	}

	public void setFortnight_quarterly_plan_activity_id(String [] fortnight_quarterly_plan_activity_id) {
		Fortnight_quarterly_plan_activity_id = fortnight_quarterly_plan_activity_id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCum_progress() {
		return cum_progress;
	}

	public void setCum_progress(String cum_progress) {
		this.cum_progress = cum_progress;
	}

	public String [] getRevisionno() {
		return revisionno;
	}

	public void setRevisionno(String [] revisionno) {
		this.revisionno = revisionno;
	}

	public String [] getTdc_revisiondate() {
		return tdc_revisiondate;
	}

	public void setTdc_revisiondate(String [] tdc_revisiondate) {
		this.tdc_revisiondate = tdc_revisiondate;
	}

	public String getRevision_no() {
		return revision_no;
	}

	public void setRevision_no(String revision_no) {
		this.revision_no = revision_no;
	}

	public String getTdc_date() {
		return tdc_date;
	}

	public void setTdc_date(String tdc_date) {
		this.tdc_date = tdc_date;
	}

	public String [] getProject_id() {
		return project_id;
	}

	public void setProject_id(String [] project_id) {
		this.project_id = project_id;
	}

	public String [] getProject_name() {
		return project_name;
	}

	public void setProject_name(String [] project_name) {
		this.project_name = project_name;
	}

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
	}

	public MultipartFile getFortnightPlanFile() {
		return fortnightPlanFile;
	}

	public void setFortnightPlanFile(MultipartFile fortnightPlanFile) {
		this.fortnightPlanFile = fortnightPlanFile;
	}

	public String getCum_actual() {
		return cum_actual;
	}

	public void setCum_actual(String cum_actual) {
		this.cum_actual = cum_actual;
	}

	public String getCum_target() {
		return cum_target;
	}

	public void setCum_target(String cum_target) {
		this.cum_target = cum_target;
	}

	public String getActual_this_fn() {
		return actual_this_fn;
	}

	public void setActual_this_fn(String actual_this_fn) {
		this.actual_this_fn = actual_this_fn;
	}

	public String getTarget_this_fn() {
		return target_this_fn;
	}

	public void setTarget_this_fn(String target_this_fn) {
		this.target_this_fn = target_this_fn;
	}

	public String getActual_till_lfn() {
		return actual_till_lfn;
	}

	public void setActual_till_lfn(String actual_till_lfn) {
		this.actual_till_lfn = actual_till_lfn;
	}

	public String getTarget_till_lfn() {
		return target_till_lfn;
	}

	public void setTarget_till_lfn(String target_till_lfn) {
		this.target_till_lfn = target_till_lfn;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public String getUploaded_date() {
		return uploaded_date;
	}

	public void setUploaded_date(String uploaded_date) {
		this.uploaded_date = uploaded_date;
	}


}
