package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Work {
	private String work_id,work_name,work_short_name,project_id_fk,sanctioned_year,sanctioned_year_fk,sanctioned_estimated_cost,completeion_period_months,
	sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,remarks,project_name,railway_name,
	railway_id_fk,executed_by_id_fk,financial_year_id,financial_year,financial_year_fk,latest_revised_cost,
	year_of_revision,revision_number,wys_renarks,attachment,work_attachment,railway,executed_by,projected_completion,created_date,
	projected_completion_year,railwayAgency,executedBy,work_id_fk,dashboard_name,parent_dashboard_id_sr_fk,dashboard_id,subLink,id,
	work_yearly_sanction_id,  pink_book_item_number,projected_completion_date,work_file_id,work_file_type,work_file_type_fk,work_status_fk,existing_work_status_fk;



	private String[] financial_years,latest_revised_costs,latest_revised_costs_units,
	year_of_revisions,revision_numbers,remarkss,workFileNames,work_file_ids,work_file_types;

	private List<Work> workRevisions;
	//private MultipartFile workFile;

	private List<Work> railwayAgencyList;
	private List<Work> executedByList,workDocs;

	private List<MultipartFile> workFile;
	private List<Work> workFilesList;
	private MultipartFile[] workFiles;
	
	


	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public List<Work> getWorkDocs() {
		return workDocs;
	}

	public void setWorkDocs(List<Work> workDocs) {
		this.workDocs = workDocs;
	}

	public String getWork_file_id() {
		return work_file_id;
	}

	public void setWork_file_id(String work_file_id) {
		this.work_file_id = work_file_id;
	}

	public String getWork_file_type() {
		return work_file_type;
	}

	public void setWork_file_type(String work_file_type) {
		this.work_file_type = work_file_type;
	}

	public String getWork_file_type_fk() {
		return work_file_type_fk;
	}

	public void setWork_file_type_fk(String work_file_type_fk) {
		this.work_file_type_fk = work_file_type_fk;
	}

	public String getWork_yearly_sanction_id() {
		return work_yearly_sanction_id;
	}

	public void setWork_yearly_sanction_id(String work_yearly_sanction_id) {
		this.work_yearly_sanction_id = work_yearly_sanction_id;
	}

	public String getPink_book_item_number() {
		return pink_book_item_number;
	}

	public void setPink_book_item_number(String pink_book_item_number) {
		this.pink_book_item_number = pink_book_item_number;
	}

	public String getDashboard_id() {
		return dashboard_id;
	}

	public void setDashboard_id(String dashboard_id) {
		this.dashboard_id = dashboard_id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MultipartFile> getWorkFile() {
		return workFile;
	}

	public List<Work> getWorkFilesList() {
		return workFilesList;
	}

	public void setWorkFilesList(List<Work> workFilesList) {
		this.workFilesList = workFilesList;
	}

	public String[] getWorkFileNames() {
		return workFileNames;
	}

	public void setWorkFileNames(String[] workFileNames) {
		this.workFileNames = workFileNames;
	}

	public void setWorkFile(List<MultipartFile> workFile) {
		this.workFile = workFile;
	}

	public String getSubLink() {
		return subLink;
	}

	public void setSubLink(String subLink) {
		this.subLink = subLink;
	}

	public String getParent_dashboard_id_sr_fk() {
		return parent_dashboard_id_sr_fk;
	}

	public void setParent_dashboard_id_sr_fk(String parent_dashboard_id_sr_fk) {
		this.parent_dashboard_id_sr_fk = parent_dashboard_id_sr_fk;
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

	public String getWork_attachment() {
		return work_attachment;
	}

	public void setWork_attachment(String work_attachment) {
		this.work_attachment = work_attachment;
	}

	public String getRailwayAgency() {
		return railwayAgency;
	}

	public void setRailwayAgency(String railwayAgency) {
		this.railwayAgency = railwayAgency;
	}

	public String getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	public String getFinancial_year_fk() {
		return financial_year_fk;
	}

	public void setFinancial_year_fk(String financial_year_fk) {
		this.financial_year_fk = financial_year_fk;
	}
	public List<Work> getRailwayAgencyList() {
		return railwayAgencyList;
	}

	public void setRailwayAgencyList(List<Work> railwayAgencyList) {
		this.railwayAgencyList = railwayAgencyList;
	}

	public List<Work> getExecutedByList() {
		return executedByList;
	}

	public void setExecutedByList(List<Work> executedByList) {
		this.executedByList = executedByList;
	}

	public String getWys_renarks() {
		return wys_renarks;
	}

	public void setWys_renarks(String wys_renarks) {
		this.wys_renarks = wys_renarks;
	}


	public String[] getFinancial_years() {
		return financial_years;
	}

	public void setFinancial_years(String[] financial_years) {
		this.financial_years = financial_years;
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

	public String getRailway() {
		return railway;
	}

	public void setRailway(String railway) {
		this.railway = railway;
	}

	public String getExecuted_by() {
		return executed_by;
	}

	public void setExecuted_by(String executed_by) {
		this.executed_by = executed_by;
	}

	public String getSanctioned_year_fk() {
		return sanctioned_year_fk;
	}

	public void setSanctioned_year_fk(String sanctioned_year_fk) {
		this.sanctioned_year_fk = sanctioned_year_fk;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getProjected_completion() {
		return projected_completion;
	}

	public void setProjected_completion(String projected_completion) {
		this.projected_completion = projected_completion;
	}

	public String getProjected_completion_year() {
		return projected_completion_year;
	}

	public void setProjected_completion_year(String projected_completion_year) {
		this.projected_completion_year = projected_completion_year;
	}

	public String getProjected_completion_date() {
		return projected_completion_date;
	}

	public void setProjected_completion_date(String projected_completion_date) {
		this.projected_completion_date = projected_completion_date;
	}

	public MultipartFile[] getWorkFiles() {
		return workFiles;
	}

	public void setWorkFiles(MultipartFile[] workFiles) {
		this.workFiles = workFiles;
	}

	public String[] getWork_file_ids() {
		return work_file_ids;
	}

	public void setWork_file_ids(String[] work_file_ids) {
		this.work_file_ids = work_file_ids;
	}

	public String[] getWork_file_types() {
		return work_file_types;
	}

	public void setWork_file_types(String[] work_file_types) {
		this.work_file_types = work_file_types;
	}

	public String getWork_status_fk() {
		return work_status_fk;
	}

	public void setWork_status_fk(String work_status_fk) {
		this.work_status_fk = work_status_fk;
	}

	public String[] getLatest_revised_costs_units() {
		return latest_revised_costs_units;
	}

	public void setLatest_revised_costs_units(String[] latest_revised_costs_units) {
		this.latest_revised_costs_units = latest_revised_costs_units;
	}

	public String getExisting_work_status_fk() {
		return existing_work_status_fk;
	}

	public void setExisting_work_status_fk(String existing_work_status_fk) {
		this.existing_work_status_fk = existing_work_status_fk;
	}
	
}
