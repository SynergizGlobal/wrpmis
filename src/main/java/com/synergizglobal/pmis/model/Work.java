package com.synergizglobal.pmis.model;

public class Work {
	private String work_id,work_name,project_id_fk,sanctioned_year,sanctioned_estimated_cost,completeion_period_months,
	sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,weight,remarks,project_name;

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

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getSanctioned_year() {
		return sanctioned_year;
	}

	public void setSanctioned_year(String sanctioned_year) {
		this.sanctioned_year = sanctioned_year;
	}

	public String getSanctioned_estimated_cost() {
		return sanctioned_estimated_cost;
	}

	public void setSanctioned_estimated_cost(String sanctioned_estimated_cost) {
		this.sanctioned_estimated_cost = sanctioned_estimated_cost;
	}

	public String getCompleteion_period_months() {
		return completeion_period_months;
	}

	public void setCompleteion_period_months(String completeion_period_months) {
		this.completeion_period_months = completeion_period_months;
	}

	public String getSanctioned_completion_cost() {
		return sanctioned_completion_cost;
	}

	public void setSanctioned_completion_cost(String sanctioned_completion_cost) {
		this.sanctioned_completion_cost = sanctioned_completion_cost;
	}

	public String getAnticipated_cost() {
		return anticipated_cost;
	}

	public void setAnticipated_cost(String anticipated_cost) {
		this.anticipated_cost = anticipated_cost;
	}

	public String getYear_of_completion() {
		return year_of_completion;
	}

	public void setYear_of_completion(String year_of_completion) {
		this.year_of_completion = year_of_completion;
	}

	public String getCompletion_cost() {
		return completion_cost;
	}

	public void setCompletion_cost(String completion_cost) {
		this.completion_cost = completion_cost;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
}
