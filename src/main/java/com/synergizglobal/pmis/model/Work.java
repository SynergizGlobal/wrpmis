package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Work {
	private String work_id,work_name,work_short_name,project_id_fk,sanctioned_year,sanctioned_year_fk,sanctioned_estimated_cost,completeion_period_months,
	sanctioned_completion_cost,anticipated_cost,year_of_completion,completion_cost,remarks,project_name,railway_name,work_code,
	railway_id_fk,executed_by_id_fk,financial_year_id,financial_year,financial_year_fk,latest_revised_cost,
	year_of_revision,revision_number,wys_renarks,attachment,work_attachment,railway,executed_by,projected_completion,created_date,
	projected_completion_year,railwayAgency,executedBy,work_id_fk,dashboard_name,parent_dashboard_id_sr_fk,dashboard_id,subLink,id,
	work_yearly_sanction_id,  pink_book_item_number,projected_completion_date,work_file_id,work_file_type,work_file_type_fk,work_status_fk,existing_work_status_fk,
	sanctioned_estimated_cost_unit,sanctioned_completion_cost_unit,anticipated_cost_unit,completion_cost_unit,latest_revised_cost_unit,unit,value,
	estimated_cost_unit,sanctioned_cost_unit,anticipated_unit,completion_unti,revision_unit,user_name,designation,user_id,work_type_fk,
	budget_grant_current_fy,expenditure_end_of_fy,expenditure_current_fy,cumulative_total_expenditure,financial_progress,physical_progress,target_completion_date;



	private String[] financial_years,latest_revised_costs,latest_revised_costs_units,
	year_of_revisions,revision_numbers,remarkss,workFileNames,work_file_ids,work_file_types,latest_revised_cost_units;

	private List<Work> workRevisions;
	//private MultipartFile workFile;

	private List<Work> railwayAgencyList;
	private List<Work> executedByList,workDocs;

	private List<MultipartFile> workFile;
	private List<Work> workFilesList;
	private MultipartFile[] workFiles;
	
	private List<WorkFeatures> workDetails;
	private List<WorkFeatures> workSalientFeatures;
	
	private List<Work> workDetailsList;
	private List<Work> workSalientFeaturesList;
	
	private String category_fk,description,soft_delete_status_fk,category,title_fk,dashboard_url,title,status;
	
	private String[] title_fks,dashboard_urls,statuses,category_fks,descriptions,salient_features_statuses;
	

	public String getWork_code() {
		return work_code;
	}

	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}

	public String getWork_type_fk() {
		return work_type_fk;
	}

	public void setWork_type_fk(String work_type_fk) {
		this.work_type_fk = work_type_fk;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<WorkFeatures> getWorkDetails() {
		return workDetails;
	}

	public void setWorkDetails(List<WorkFeatures> workDetails) {
		this.workDetails = workDetails;
	}

	public List<WorkFeatures> getWorkSalientFeatures() {
		return workSalientFeatures;
	}

	public void setWorkSalientFeatures(List<WorkFeatures> workSalientFeatures) {
		this.workSalientFeatures = workSalientFeatures;
	}

	public List<Work> getWorkDetailsList() {
		return workDetailsList;
	}

	public void setWorkDetailsList(List<Work> workDetailsList) {
		this.workDetailsList = workDetailsList;
	}

	public List<Work> getWorkSalientFeaturesList() {
		return workSalientFeaturesList;
	}

	public void setWorkSalientFeaturesList(List<Work> workSalientFeaturesList) {
		this.workSalientFeaturesList = workSalientFeaturesList;
	}

	public String getCategory_fk() {
		return category_fk;
	}

	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSoft_delete_status_fk() {
		return soft_delete_status_fk;
	}

	public void setSoft_delete_status_fk(String soft_delete_status_fk) {
		this.soft_delete_status_fk = soft_delete_status_fk;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle_fk() {
		return title_fk;
	}

	public void setTitle_fk(String title_fk) {
		this.title_fk = title_fk;
	}

	public String getDashboard_url() {
		return dashboard_url;
	}

	public void setDashboard_url(String dashboard_url) {
		this.dashboard_url = dashboard_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getTitle_fks() {
		return title_fks;
	}

	public void setTitle_fks(String[] title_fks) {
		this.title_fks = title_fks;
	}

	public String[] getDashboard_urls() {
		return dashboard_urls;
	}

	public void setDashboard_urls(String[] dashboard_urls) {
		this.dashboard_urls = dashboard_urls;
	}

	public String[] getStatuses() {
		return statuses;
	}

	public void setStatuses(String[] statuses) {
		this.statuses = statuses;
	}

	public String[] getCategory_fks() {
		return category_fks;
	}

	public void setCategory_fks(String[] category_fks) {
		this.category_fks = category_fks;
	}

	public String[] getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String[] descriptions) {
		this.descriptions = descriptions;
	}

	public String[] getSalient_features_statuses() {
		return salient_features_statuses;
	}

	public void setSalient_features_statuses(String[] salient_features_statuses) {
		this.salient_features_statuses = salient_features_statuses;
	}

	public String getEstimated_cost_unit() {
		return estimated_cost_unit;
	}

	public void setEstimated_cost_unit(String estimated_cost_unit) {
		this.estimated_cost_unit = estimated_cost_unit;
	}

	public String getSanctioned_cost_unit() {
		return sanctioned_cost_unit;
	}

	public void setSanctioned_cost_unit(String sanctioned_cost_unit) {
		this.sanctioned_cost_unit = sanctioned_cost_unit;
	}

	public String getAnticipated_unit() {
		return anticipated_unit;
	}

	public void setAnticipated_unit(String anticipated_unit) {
		this.anticipated_unit = anticipated_unit;
	}

	public String getCompletion_unti() {
		return completion_unti;
	}

	public void setCompletion_unti(String completion_unti) {
		this.completion_unti = completion_unti;
	}

	public String getRevision_unit() {
		return revision_unit;
	}

	public void setRevision_unit(String revision_unit) {
		this.revision_unit = revision_unit;
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

	public String getLatest_revised_cost_unit() {
		return latest_revised_cost_unit;
	}

	public void setLatest_revised_cost_unit(String latest_revised_cost_unit) {
		this.latest_revised_cost_unit = latest_revised_cost_unit;
	}

	public String[] getLatest_revised_cost_units() {
		return latest_revised_cost_units;
	}

	public void setLatest_revised_cost_units(String[] latest_revised_cost_units) {
		this.latest_revised_cost_units = latest_revised_cost_units;
	}

	public String getSanctioned_estimated_cost_unit() {
		return sanctioned_estimated_cost_unit;
	}

	public void setSanctioned_estimated_cost_unit(String sanctioned_estimated_cost_unit) {
		this.sanctioned_estimated_cost_unit = sanctioned_estimated_cost_unit;
	}

	public String getSanctioned_completion_cost_unit() {
		return sanctioned_completion_cost_unit;
	}

	public void setSanctioned_completion_cost_unit(String sanctioned_completion_cost_unit) {
		this.sanctioned_completion_cost_unit = sanctioned_completion_cost_unit;
	}

	public String getAnticipated_cost_unit() {
		return anticipated_cost_unit;
	}

	public void setAnticipated_cost_unit(String anticipated_cost_unit) {
		this.anticipated_cost_unit = anticipated_cost_unit;
	}

	public String getCompletion_cost_unit() {
		return completion_cost_unit;
	}

	public void setCompletion_cost_unit(String completion_cost_unit) {
		this.completion_cost_unit = completion_cost_unit;
	}

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

	public String getBudget_grant_current_fy() {
		return budget_grant_current_fy;
	}

	public void setBudget_grant_current_fy(String budget_grant_current_fy) {
		this.budget_grant_current_fy = budget_grant_current_fy;
	}

	public String getExpenditure_end_of_fy() {
		return expenditure_end_of_fy;
	}

	public void setExpenditure_end_of_fy(String expenditure_end_of_fy) {
		this.expenditure_end_of_fy = expenditure_end_of_fy;
	}

	public String getExpenditure_current_fy() {
		return expenditure_current_fy;
	}

	public void setExpenditure_current_fy(String expenditure_current_fy) {
		this.expenditure_current_fy = expenditure_current_fy;
	}

	public String getCumulative_total_expenditure() {
		return cumulative_total_expenditure;
	}

	public void setCumulative_total_expenditure(String cumulative_total_expenditure) {
		this.cumulative_total_expenditure = cumulative_total_expenditure;
	}

	public String getFinancial_progress() {
		return financial_progress;
	}

	public void setFinancial_progress(String financial_progress) {
		this.financial_progress = financial_progress;
	}

	public String getPhysical_progress() {
		return physical_progress;
	}

	public void setPhysical_progress(String physical_progress) {
		this.physical_progress = physical_progress;
	}

	public String getTarget_completion_date() {
		return target_completion_date;
	}

	public void setTarget_completion_date(String target_completion_date) {
		this.target_completion_date = target_completion_date;
	}
	
}
