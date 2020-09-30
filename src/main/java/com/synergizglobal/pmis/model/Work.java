package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Work {
	private String work_id,work_name,project_id_fk,sanctioned_year,sanctioned_estimated_cost,completeion_period_months,
	sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,weight,remarks,project_name,railway_name,
	railway_id_fk,executed_by_id_fk,financial_year_id,financial_year,pink_book_item_number,latest_revised_cost,
	year_of_revision,revision_number,wys_renarks,attachment;

	private List<Work> workRevisions;
	private MultipartFile workFile;

	
	public MultipartFile getWorkFile() {
		return workFile;
	}

	public void setWorkFile(MultipartFile workFile) {
		this.workFile = workFile;
	}

	public String getWys_renarks() {
		return wys_renarks;
	}

	public void setWys_renarks(String wys_renarks) {
		this.wys_renarks = wys_renarks;
	}

	private String[] financial_years,pink_book_item_numbers,latest_revised_costs,
	year_of_revisions,revision_numbers,remarkss;


	public String[] getFinancial_years() {
		return financial_years;
	}

	public void setFinancial_years(String[] financial_years) {
		this.financial_years = financial_years;
	}

	public String[] getPink_book_item_numbers() {
		return pink_book_item_numbers;
	}

	public void setPink_book_item_numbers(String[] pink_book_item_numbers) {
		this.pink_book_item_numbers = pink_book_item_numbers;
	}

	public String[] getLatest_revised_costs() {
		return latest_revised_costs;
	}

	public void setLatest_revised_costs(String[] latest_revised_costs) {
		this.latest_revised_costs = latest_revised_costs;
	}

	public String[] getYear_of_revisions() {
		return year_of_revisions;
	}

	public void setYear_of_revisions(String[] year_of_revisions) {
		this.year_of_revisions = year_of_revisions;
	}

	public String[] getRevision_numbers() {
		return revision_numbers;
	}

	public void setRevision_numbers(String[] revision_numbers) {
		this.revision_numbers = revision_numbers;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
	}

	public String getPink_book_item_number() {
		return pink_book_item_number;
	}

	public void setPink_book_item_number(String pink_book_item_number) {
		this.pink_book_item_number = pink_book_item_number;
	}

	public String getYear_of_revision() {
		return year_of_revision;
	}

	public void setYear_of_revision(String year_of_revision) {
		this.year_of_revision = year_of_revision;
	}

	public String getLatest_revised_cost() {
		return latest_revised_cost;
	}

	public void setLatest_revised_cost(String latest_revised_cost) {
		this.latest_revised_cost = latest_revised_cost;
	}

	public String getRevision_number() {
		return revision_number;
	}

	public void setRevision_number(String revision_number) {
		this.revision_number = revision_number;
	}

	public String getFinancial_year_id() {
		return financial_year_id;
	}

	public void setFinancial_year_id(String financial_year_id) {
		this.financial_year_id = financial_year_id;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getRailway_name() {
		return railway_name;
	}

	public String getExecuted_by_id_fk() {
		return executed_by_id_fk;
	}

	public void setExecuted_by_id_fk(String executed_by_id_fk) {
		this.executed_by_id_fk = executed_by_id_fk;
	}

	public void setRailway_name(String railway_name) {
		this.railway_name = railway_name;
	}

	public String getRailway_id_fk() {
		return railway_id_fk;
	}

	public void setRailway_id_fk(String railway_id_fk) {
		this.railway_id_fk = railway_id_fk;
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

	public List<Work> getWorkRevisions() {
		return workRevisions;
	}

	public void setWorkRevisions(List<Work> workRevisions) {
		this.workRevisions = workRevisions;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
}
