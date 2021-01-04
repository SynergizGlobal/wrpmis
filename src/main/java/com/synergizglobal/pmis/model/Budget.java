package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Budget {
	
	private String budget_id, work_id_fk,work_id, financial_year_fk, budget_estimate, august_review_estimate, revised_estimate, final_estimate, budget_grant, revised_grant,
					final_grant, remarks, attachment,project_id_fk,work_name,project_name,project_id,financial_year,work_short_name;

	
	private String[] financial_year_fks,budget_ids, budget_estimates, august_review_estimates,budgetFileNames, revised_estimates, final_estimates, budget_grants, revised_grants, final_grants;
	
	private List<Budget> budget;

	private MultipartFile[] budgetFile;
	
	public String[] getBudget_ids() {
		return budget_ids;
	}

	public void setBudget_ids(String[] budget_ids) {
		this.budget_ids = budget_ids;
	}

	public String[] getFinancial_year_fks() {
		return financial_year_fks;
	}

	public void setFinancial_year_fks(String[] financial_year_fks) {
		this.financial_year_fks = financial_year_fks;
	}

	public String[] getBudget_estimates() {
		return budget_estimates;
	}

	public void setBudget_estimates(String[] budget_estimates) {
		this.budget_estimates = budget_estimates;
	}

	public String[] getAugust_review_estimates() {
		return august_review_estimates;
	}

	public void setAugust_review_estimates(String[] august_review_estimates) {
		this.august_review_estimates = august_review_estimates;
	}

	public String[] getBudgetFileNames() {
		return budgetFileNames;
	}

	public void setBudgetFileNames(String[] budgetFileNames) {
		this.budgetFileNames = budgetFileNames;
	}

	public String[] getRevised_estimates() {
		return revised_estimates;
	}

	public void setRevised_estimates(String[] revised_estimates) {
		this.revised_estimates = revised_estimates;
	}

	public String[] getFinal_estimates() {
		return final_estimates;
	}

	public void setFinal_estimates(String[] final_estimates) {
		this.final_estimates = final_estimates;
	}

	public String[] getBudget_grants() {
		return budget_grants;
	}

	public void setBudget_grants(String[] budget_grants) {
		this.budget_grants = budget_grants;
	}

	public String[] getRevised_grants() {
		return revised_grants;
	}

	public void setRevised_grants(String[] revised_grants) {
		this.revised_grants = revised_grants;
	}

	public String[] getFinal_grants() {
		return final_grants;
	}

	public void setFinal_grants(String[] final_grants) {
		this.final_grants = final_grants;
	}


	public MultipartFile[] getBudgetFile() {
		return budgetFile;
	}

	public void setBudgetFile(MultipartFile[] budgetFile) {
		this.budgetFile = budgetFile;
	}

	public List<Budget> getBudget() {
		return budget;
	}

	public void setBudget(List<Budget> budget) {
		this.budget = budget;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;  
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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
