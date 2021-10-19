package com.synergizglobal.pmis.model;

import java.util.List;

public class ZonalRailway {

	private String contract_id, project_id,work_id_fk,work_name,railway_name, work_short_name,project_id_fk,project_name, execution_agency_railway_fk, source_of_funds, sanction_cost, latest_revised_cost, 
	cumulative_expenditure_upto_last_finacial_year, work_id,actual_start, expected_finish, actual_finish, completion_cost,sub_work, 
	status_fk, as_on_date, progress_id, contract_id_fk, month, cum_actual_expenditure_fy_cr, cum_planned_expenditure_per, 
	cum_actual_expenditure, cum_actual_expenditure_per, cum_planned_physical_progress_per, cum_actual_physical_progress_per, 
	progress, issue, assistance_required,user_id, user_name, designation, department_fk, reporting_to_id_srfk,responsible_person_user_fk,id, unit, value,
	sanction_cost_units,latest_revised_cost_units,cumilative_expenditure_units,completion_cost_units,sanction_unit,revised_cost_unit,cumilative_unit,completion_unit,cum_actual_expenditure_units;
	
	private String[] progress_ids, contract_id_fks, months, cum_actual_expenditure_fy_crs, cum_planned_expenditure_pers, 
	cum_actual_expenditure_crs, cum_actual_expenditure_pers, cum_planned_physical_progress_pers, cum_actual_physical_progress_pers, 
	progresss, issues, assistance_requireds;
	
	public String getCum_actual_expenditure_units() {
		return cum_actual_expenditure_units;
	}

	public void setCum_actual_expenditure_units(String cum_actual_expenditure_units) {
		this.cum_actual_expenditure_units = cum_actual_expenditure_units;
	}

	public String getSanction_unit() {
		return sanction_unit;
	}

	public void setSanction_unit(String sanction_unit) {
		this.sanction_unit = sanction_unit;
	}

	public String getRevised_cost_unit() {
		return revised_cost_unit;
	}

	public void setRevised_cost_unit(String revised_cost_unit) {
		this.revised_cost_unit = revised_cost_unit;
	}

	public String getCumilative_unit() {
		return cumilative_unit;
	}

	public void setCumilative_unit(String cumilative_unit) {
		this.cumilative_unit = cumilative_unit;
	}

	public String getCompletion_unit() {
		return completion_unit;
	}

	public void setCompletion_unit(String completion_unit) {
		this.completion_unit = completion_unit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSanction_cost_units() {
		return sanction_cost_units;
	}

	public void setSanction_cost_units(String sanction_cost_units) {
		this.sanction_cost_units = sanction_cost_units;
	}

	public String getLatest_revised_cost_units() {
		return latest_revised_cost_units;
	}

	public void setLatest_revised_cost_units(String latest_revised_cost_units) {
		this.latest_revised_cost_units = latest_revised_cost_units;
	}

	public String getCumilative_expenditure_units() {
		return cumilative_expenditure_units;
	}

	public void setCumilative_expenditure_units(String cumilative_expenditure_units) {
		this.cumilative_expenditure_units = cumilative_expenditure_units;
	}

	public String getCompletion_cost_units() {
		return completion_cost_units;
	}

	public void setCompletion_cost_units(String completion_cost_units) {
		this.completion_cost_units = completion_cost_units;
	}

	public String getSub_work() {
		return sub_work;
	}

	public void setSub_work(String sub_work) {
		this.sub_work = sub_work;
	}

	public String getResponsible_person_user_fk() {
		return responsible_person_user_fk;
	}

	public void setResponsible_person_user_fk(String responsible_person_user_fk) {
		this.responsible_person_user_fk = responsible_person_user_fk;
	}

	private List<ZonalRailway> zonalRailway;
			
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getDepartment_fk() {
		return department_fk;
	}

	public void setDepartment_fk(String department_fk) {
		this.department_fk = department_fk;
	}

	public String getReporting_to_id_srfk() {
		return reporting_to_id_srfk;
	}

	public void setReporting_to_id_srfk(String reporting_to_id_srfk) {
		this.reporting_to_id_srfk = reporting_to_id_srfk;
	}

	public String getRailway_name() {
		return railway_name;
	}

	public void setRailway_name(String railway_name) {
		this.railway_name = railway_name;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public List<ZonalRailway> getZonalRailway() {
		return zonalRailway;
	}

	public void setZonalRailway(List<ZonalRailway> zonalRailway) {
		this.zonalRailway = zonalRailway;
	}

	public String[] getProgress_ids() {
		return progress_ids;
	}

	public void setProgress_ids(String[] progress_ids) {
		this.progress_ids = progress_ids;
	}

	public String[] getContract_id_fks() {
		return contract_id_fks;
	}

	public void setContract_id_fks(String[] contract_id_fks) {
		this.contract_id_fks = contract_id_fks;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public String[] getCum_actual_expenditure_fy_crs() {
		return cum_actual_expenditure_fy_crs;
	}

	public void setCum_actual_expenditure_fy_crs(String[] cum_actual_expenditure_fy_crs) {
		this.cum_actual_expenditure_fy_crs = cum_actual_expenditure_fy_crs;
	}

	public String[] getCum_planned_expenditure_pers() {
		return cum_planned_expenditure_pers;
	}

	public void setCum_planned_expenditure_pers(String[] cum_planned_expenditure_pers) {
		this.cum_planned_expenditure_pers = cum_planned_expenditure_pers;
	}

	public String[] getCum_actual_expenditure_crs() {
		return cum_actual_expenditure_crs;
	}

	public void setCum_actual_expenditure_crs(String[] cum_actual_expenditure_crs) {
		this.cum_actual_expenditure_crs = cum_actual_expenditure_crs;
	}

	public String[] getCum_actual_expenditure_pers() {
		return cum_actual_expenditure_pers;
	}

	public void setCum_actual_expenditure_pers(String[] cum_actual_expenditure_pers) {
		this.cum_actual_expenditure_pers = cum_actual_expenditure_pers;
	}

	public String[] getCum_planned_physical_progress_pers() {
		return cum_planned_physical_progress_pers;
	}

	public void setCum_planned_physical_progress_pers(String[] cum_planned_physical_progress_pers) {
		this.cum_planned_physical_progress_pers = cum_planned_physical_progress_pers;
	}

	public String[] getCum_actual_physical_progress_pers() {
		return cum_actual_physical_progress_pers;
	}

	public void setCum_actual_physical_progress_pers(String[] cum_actual_physical_progress_pers) {
		this.cum_actual_physical_progress_pers = cum_actual_physical_progress_pers;
	}

	public String[] getProgresss() {
		return progresss;
	}

	public void setProgresss(String[] progresss) {
		this.progresss = progresss;
	}

	public String[] getIssues() {
		return issues;
	}

	public void setIssues(String[] issues) {
		this.issues = issues;
	}

	public String[] getAssistance_requireds() {
		return assistance_requireds;
	}

	public void setAssistance_requireds(String[] assistance_requireds) {
		this.assistance_requireds = assistance_requireds;
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

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getExecution_agency_railway_fk() {
		return execution_agency_railway_fk;
	}

	public void setExecution_agency_railway_fk(String execution_agency_railway_fk) {
		this.execution_agency_railway_fk = execution_agency_railway_fk;
	}

	public String getSource_of_funds() {
		return source_of_funds;
	}

	public void setSource_of_funds(String source_of_funds) {
		this.source_of_funds = source_of_funds;
	}

	public String getSanction_cost() {
		return sanction_cost;
	}

	public void setSanction_cost(String sanction_cost) {
		this.sanction_cost = sanction_cost;
	}

	public String getLatest_revised_cost() {
		return latest_revised_cost;
	}

	public void setLatest_revised_cost(String latest_revised_cost) {
		this.latest_revised_cost = latest_revised_cost;
	}

	public String getCumulative_expenditure_upto_last_finacial_year() {
		return cumulative_expenditure_upto_last_finacial_year;
	}

	public void setCumulative_expenditure_upto_last_finacial_year(String cumulative_expenditure_upto_last_finacial_year) {
		this.cumulative_expenditure_upto_last_finacial_year = cumulative_expenditure_upto_last_finacial_year;
	}

	public String getActual_start() {
		return actual_start;
	}

	public void setActual_start(String actual_start) {
		this.actual_start = actual_start;
	}

	public String getExpected_finish() {
		return expected_finish;
	}

	public void setExpected_finish(String expected_finish) {
		this.expected_finish = expected_finish;
	}

	public String getActual_finish() {
		return actual_finish;
	}

	public void setActual_finish(String actual_finish) {
		this.actual_finish = actual_finish;
	}

	public String getCompletion_cost() {
		return completion_cost;
	}

	public void setCompletion_cost(String completion_cost) {
		this.completion_cost = completion_cost;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
	}

	public String getAs_on_date() {
		return as_on_date;
	}

	public void setAs_on_date(String as_on_date) {
		this.as_on_date = as_on_date;
	}

	public String getProgress_id() {
		return progress_id;
	}

	public void setProgress_id(String progress_id) {
		this.progress_id = progress_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCum_actual_expenditure_fy_cr() {
		return cum_actual_expenditure_fy_cr;
	}

	public void setCum_actual_expenditure_fy_cr(String cum_actual_expenditure_fy_cr) {
		this.cum_actual_expenditure_fy_cr = cum_actual_expenditure_fy_cr;
	}

	public String getCum_planned_expenditure_per() {
		return cum_planned_expenditure_per;
	}

	public void setCum_planned_expenditure_per(String cum_planned_expenditure_per) {
		this.cum_planned_expenditure_per = cum_planned_expenditure_per;
	}



	public String getCum_actual_expenditure() {
		return cum_actual_expenditure;
	}

	public void setCum_actual_expenditure(String cum_actual_expenditure) {
		this.cum_actual_expenditure = cum_actual_expenditure;
	}

	public String getCum_actual_expenditure_per() {
		return cum_actual_expenditure_per;
	}

	public void setCum_actual_expenditure_per(String cum_actual_expenditure_per) {
		this.cum_actual_expenditure_per = cum_actual_expenditure_per;
	}

	public String getCum_planned_physical_progress_per() {
		return cum_planned_physical_progress_per;
	}

	public void setCum_planned_physical_progress_per(String cum_planned_physical_progress_per) {
		this.cum_planned_physical_progress_per = cum_planned_physical_progress_per;
	}

	public String getCum_actual_physical_progress_per() {
		return cum_actual_physical_progress_per;
	}

	public void setCum_actual_physical_progress_per(String cum_actual_physical_progress_per) {
		this.cum_actual_physical_progress_per = cum_actual_physical_progress_per;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getAssistance_required() {
		return assistance_required;
	}

	public void setAssistance_required(String assistance_required) {
		this.assistance_required = assistance_required;
	}
	
	
}
