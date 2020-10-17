package com.synergizglobal.pmis.model;

import org.springframework.web.multipart.MultipartFile;

public class Budget {
	
	private String budget_id, work_id_fk, financial_year_fk, budget_estimate, august_review_estimate, revised_estimate, final_estimate, budget_grant, revised_grant,
					final_grant, remarks, attachment,project_id_fk,work_name;

	private MultipartFile budgetFile;
	
	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	
	public MultipartFile getBudgetFile() {
		return budgetFile;
	}

	public void setBudgetFile(MultipartFile budgetFile) {
		this.budgetFile = budgetFile;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getBudget_id() {
		return budget_id;
	}

	public void setBudget_id(String budget_id) {
		this.budget_id = budget_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getFinancial_year_fk() {
		return financial_year_fk;
	}

	public void setFinancial_year_fk(String financial_year_fk) {
		this.financial_year_fk = financial_year_fk;
	}

	public String getBudget_estimate() {
		return budget_estimate;
	}

	public void setBudget_estimate(String budget_estimate) {
		this.budget_estimate = budget_estimate;
	}

	public String getAugust_review_estimate() {
		return august_review_estimate;
	}

	public void setAugust_review_estimate(String august_review_estimate) {
		this.august_review_estimate = august_review_estimate;
	}

	public String getRevised_estimate() {
		return revised_estimate;
	}

	public void setRevised_estimate(String revised_estimate) {
		this.revised_estimate = revised_estimate;
	}

	public String getFinal_estimate() {
		return final_estimate;
	}

	public void setFinal_estimate(String final_estimate) {
		this.final_estimate = final_estimate;
	}

	public String getBudget_grant() {
		return budget_grant;
	}

	public void setBudget_grant(String budget_grant) {
		this.budget_grant = budget_grant;
	}

	public String getRevised_grant() {
		return revised_grant;
	}

	public void setRevised_grant(String revised_grant) {
		this.revised_grant = revised_grant;
	}

	public String getFinal_grant() {
		return final_grant;
	}

	public void setFinal_grant(String final_grant) {
		this.final_grant = final_grant;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}
