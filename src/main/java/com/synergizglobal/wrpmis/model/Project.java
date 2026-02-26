package com.synergizglobal.wrpmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Project {
	private String project_id,project_code,project_name,plan_head_number,pink_book_item_number,remarks,project_description,project_status,attachment
	,sanctioned_estimated_cost,sanctioned_year_fk,sanctioned_completion_cost,year_of_completion,projected_completion_year,latest_revised_cost,
	completion_cost,work_short_name,completion_date,benefits,galleryFileNames,financial_year_fk,pb_item_no,project_pinkbook_id,user_id,created_by_user_id_fk,designation,user_name;
	
	private String id,file_name,division_id,section_id,project_count,division_name,upload_kmz_file,section_name,project_type_id_fk,date_of_sanction,pb_item_number,project_id_fk,actual_completion_cost,actual_completion_date,entry_date,estimated_completion_cost,revised_completion_date,project_type_id,work_type,project_type_name,railway_zone,railway_id,sanctioned_year,sanctioned_amount,sanctioned_commissioning_date,
	revised_target_date,latest_sanctioned_cost,division,sections,structure_type,scope,completed,
	
	created_date,created_by,railway,commissioned_length,length,project_file_type_fk,project_file_type,project_file_id,financial_progress,physical_progress,
	uploaded_by_user_id_fk,chainages,latitude,longitude,status,srno,uploaded_file,project_data_id,	ongoing_projects,	total_length,	total_earthwork,	completed_track,	completed_major_bridges,	total_major_bridges,	completed_minor_bridges,total_minor_bridges,	completed_rob,	total_rob,	completed_rub,	total_rub
	,commission_fromchainage,commission_tochainage,commission_completedlength,commissioned_id,proposed_length,project_commissioned_total,proposed_length_by_type;
	
	
	private MultipartFile[] projectGalleryFiles,projectFiles;
	private List<Project> projectFilesList,projectGalleryFilesList,projectPinkBooks,projectGallery,projectDocs,projectCommissionedLengthList;
	private String[] projectFileNames,attachemnts,project_file_types,project_file_ids,projectGalleryFileNames,created_dates;
	private String[] financial_years,pink_book_item_numbers,railways;
	private String[] completion_dates,estimated_completion_costs,revised_completion_dates,	commission_from_chainage,commission_to_chainage,commission_completed_length;
	
	private MultipartFile ProjectChainagesFile;
	
	
	private List<Work> worksInfo,workDocs;

	
	
	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
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

	public List<Work> getWorkDocs() {
		return workDocs;
	}

	public void setWorkDocs(List<Work> workDocs) {
		this.workDocs = workDocs;
	}

	public List<Project> getProjectDocs() {
		return projectDocs;
	}

	public void setProjectDocs(List<Project> projectDocs) {
		this.projectDocs = projectDocs;
	}

	public String[] getCreated_dates() {
		return created_dates;
	}

	public void setCreated_dates(String[] created_dates) {
		this.created_dates = created_dates;
	}

	public MultipartFile[] getProjectGalleryFiles() {
		return projectGalleryFiles;
	}

	public void setProjectGalleryFiles(MultipartFile[] projectGalleryFiles) {
		this.projectGalleryFiles = projectGalleryFiles;
	}

	public List<Project> getProjectGalleryFilesList() {
		return projectGalleryFilesList;
	}

	public void setProjectGalleryFilesList(List<Project> projectGalleryFilesList) {
		this.projectGalleryFilesList = projectGalleryFilesList;
	}

	public String getProject_file_type_fk() {
		return project_file_type_fk;
	}

	public void setProject_file_type_fk(String project_file_type_fk) {
		this.project_file_type_fk = project_file_type_fk;
	}

	public String getProject_file_type() {
		return project_file_type;
	}

	public void setProject_file_type(String project_file_type) {
		this.project_file_type = project_file_type;
	}

	public String getProject_file_id() {
		return project_file_id;
	}

	public void setProject_file_id(String project_file_id) {
		this.project_file_id = project_file_id;
	}

	public String[] getAttachemnts() {
		return attachemnts;
	}

	public void setAttachemnts(String[] attachemnts) {
		this.attachemnts = attachemnts;
	}

	public String[] getProject_file_types() {
		return project_file_types;
	}

	public void setProject_file_types(String[] project_file_types) {
		this.project_file_types = project_file_types;
	}

	public String[] getProject_file_ids() {
		return project_file_ids;
	}

	public void setProject_file_ids(String[] project_file_ids) {
		this.project_file_ids = project_file_ids;
	}

	public List<Project> getProjectFilesList() {
		return projectFilesList;
	}

	public void setProjectFilesList(List<Project> projectFilesList) {
		this.projectFilesList = projectFilesList;
	}

	public String[] getProjectFileNames() {
		return projectFileNames;
	}

	public void setProjectFileNames(String[] projectFileNames) {
		this.projectFileNames = projectFileNames;
	}

	public String getFinancial_year_fk() {
		return financial_year_fk;
	}

	public void setFinancial_year_fk(String financial_year_fk) {
		this.financial_year_fk = financial_year_fk;
	}

	public String getPb_item_no() {
		return pb_item_no;
	}

	public void setPb_item_no(String pb_item_no) {
		this.pb_item_no = pb_item_no;
	}

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

	public String[] getProjectGalleryFileNames() {
		return projectGalleryFileNames;
	}

	public void setProjectGalleryFileNames(String[] projectGalleryFileNames) {
		this.projectGalleryFileNames = projectGalleryFileNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getGalleryFileNames() {
		return galleryFileNames;
	}

	public void setGalleryFileNames(String galleryFileNames) {
		this.galleryFileNames = galleryFileNames;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getSanctioned_estimated_cost() {
		return sanctioned_estimated_cost;
	}

	public void setSanctioned_estimated_cost(String sanctioned_estimated_cost) {
		this.sanctioned_estimated_cost = sanctioned_estimated_cost;
	}

	public String getSanctioned_year_fk() {
		return sanctioned_year_fk;
	}

	public void setSanctioned_year_fk(String sanctioned_year_fk) {
		this.sanctioned_year_fk = sanctioned_year_fk;
	}

	public String getSanctioned_completion_cost() {
		return sanctioned_completion_cost;
	}

	public void setSanctioned_completion_cost(String sanctioned_completion_cost) {
		this.sanctioned_completion_cost = sanctioned_completion_cost;
	}

	public String getYear_of_completion() {
		return year_of_completion;
	}

	public void setYear_of_completion(String year_of_completion) {
		this.year_of_completion = year_of_completion;
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

	public String getPlan_head_number() {
		return plan_head_number;
	}

	public void setPlan_head_number(String plan_head_number) {
		this.plan_head_number = plan_head_number;
	}

	public String getPink_book_item_number() {
		return pink_book_item_number;
	}

	public void setPink_book_item_number(String pink_book_item_number) {
		this.pink_book_item_number = pink_book_item_number;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public String getProject_status() {
		return project_status;
	}

	public void setProject_status(String project_status) {
		this.project_status = project_status;
	}

	public String getProjected_completion_year() {
		return projected_completion_year;
	}

	public void setProjected_completion_year(String projected_completion_year) {
		this.projected_completion_year = projected_completion_year;
	}

	public String getLatest_revised_cost() {
		return latest_revised_cost;
	}

	public void setLatest_revised_cost(String latest_revised_cost) {
		this.latest_revised_cost = latest_revised_cost;
	}

	public String getCompletion_cost() {
		return completion_cost;
	}

	public void setCompletion_cost(String completion_cost) {
		this.completion_cost = completion_cost;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public List<Work> getWorksInfo() {
		return worksInfo;
	}

	public void setWorksInfo(List<Work> worksInfo) {
		this.worksInfo = worksInfo;
	}

	public List<Project> getProjectPinkBooks() {
		return projectPinkBooks;
	}

	public void setProjectPinkBooks(List<Project> projectPinkBooks) {
		this.projectPinkBooks = projectPinkBooks;
	}

	public String getProject_pinkbook_id() {
		return project_pinkbook_id;
	}

	public void setProject_pinkbook_id(String project_pinkbook_id) {
		this.project_pinkbook_id = project_pinkbook_id;
	}

	public String getRailway() {
		return railway;
	}

	public void setRailway(String railway) {
		this.railway = railway;
	}

	public String[] getRailways() {
		return railways;
	}

	public void setRailways(String[] railways) {
		this.railways = railways;
	}

	public MultipartFile[] getProjectFiles() {
		return projectFiles;
	}

	public void setProjectFiles(MultipartFile[] projectFiles) {
		this.projectFiles = projectFiles;
	}

	public List<Project> getProjectGallery() {
		return projectGallery;
	}

	public void setProjectGallery(List<Project> projectGallery) {
		this.projectGallery = projectGallery;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getProject_type_id() {
		return project_type_id;
	}

	public void setProject_type_id(String project_type_id) {
		this.project_type_id = project_type_id;
	}

	public String getProject_type_name() {
		return project_type_name;
	}

	public void setProject_type_name(String project_type_name) {
		this.project_type_name = project_type_name;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getRailway_zone() {
		return railway_zone;
	}

	public void setRailway_zone(String railway_zone) {
		this.railway_zone = railway_zone;
	}

	public String getSanctioned_year() {
		return sanctioned_year;
	}

	public void setSanctioned_year(String sanctioned_year) {
		this.sanctioned_year = sanctioned_year;
	}

	public String getSanctioned_amount() {
		return sanctioned_amount;
	}

	public void setSanctioned_amount(String sanctioned_amount) {
		this.sanctioned_amount = sanctioned_amount;
	}

	public String getSanctioned_commissioning_date() {
		return sanctioned_commissioning_date;
	}

	public void setSanctioned_commissioning_date(String sanctioned_commissioning_date) {
		this.sanctioned_commissioning_date = sanctioned_commissioning_date;
	}

	public String getRevised_target_date() {
		return revised_target_date;
	}

	public void setRevised_target_date(String revised_target_date) {
		this.revised_target_date = revised_target_date;
	}

	public String getLatest_sanctioned_cost() {
		return latest_sanctioned_cost;
	}

	public void setLatest_sanctioned_cost(String latest_sanctioned_cost) {
		this.latest_sanctioned_cost = latest_sanctioned_cost;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSections() {
		return sections;
	}

	public void setSections(String sections) {
		this.sections = sections;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getActual_completion_cost() {
		return actual_completion_cost;
	}

	public void setActual_completion_cost(String actual_completion_cost) {
		this.actual_completion_cost = actual_completion_cost;
	}

	public String getActual_completion_date() {
		return actual_completion_date;
	}

	public void setActual_completion_date(String actual_completion_date) {
		this.actual_completion_date = actual_completion_date;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}

	public String getEstimated_completion_cost() {
		return estimated_completion_cost;
	}

	public void setEstimated_completion_cost(String estimated_completion_cost) {
		this.estimated_completion_cost = estimated_completion_cost;
	}

	public String getRevised_completion_date() {
		return revised_completion_date;
	}

	public void setRevised_completion_date(String revised_completion_date) {
		this.revised_completion_date = revised_completion_date;
	}

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getDivision_id() {
		return division_id;
	}

	public void setDivision_id(String division_id) {
		this.division_id = division_id;
	}

	public String getSection_id() {
		return section_id;
	}

	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public String getProject_type_id_fk() {
		return project_type_id_fk;
	}

	public void setProject_type_id_fk(String project_type_id_fk) {
		this.project_type_id_fk = project_type_id_fk;
	}

	public String getDate_of_sanction() {
		return date_of_sanction;
	}

	public void setDate_of_sanction(String date_of_sanction) {
		this.date_of_sanction = date_of_sanction;
	}

	public String getPb_item_number() {
		return pb_item_number;
	}

	public void setPb_item_number(String pb_item_number) {
		this.pb_item_number = pb_item_number;
	}

	public String getUpload_kmz_file() {
		return upload_kmz_file;
	}

	public void setUpload_kmz_file(String upload_kmz_file) {
		this.upload_kmz_file = upload_kmz_file;
	}

	public String getCompletion_date() {
		return completion_date;
	}

	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}

	public String[] getCompletion_dates() {
		return completion_dates;
	}

	public void setCompletion_dates(String[] completion_dates) {
		this.completion_dates = completion_dates;
	}

	public String[] getEstimated_completion_costs() {
		return estimated_completion_costs;
	}

	public void setEstimated_completion_costs(String[] estimated_completion_costs) {
		this.estimated_completion_costs = estimated_completion_costs;
	}

	public String[] getRevised_completion_dates() {
		return revised_completion_dates;
	}

	public void setRevised_completion_dates(String[] revised_completion_dates) {
		this.revised_completion_dates = revised_completion_dates;
	}

	public MultipartFile getProjectChainagesFile() {
		return ProjectChainagesFile;
	}

	public void setProjectChainagesFile(MultipartFile ProjectChainagesFile) {
		this.ProjectChainagesFile = ProjectChainagesFile;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getChainages() {
		return chainages;
	}

	public void setChainages(String chainages) {
		this.chainages = chainages;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}

	public String getUploaded_file() {
		return uploaded_file;
	}

	public void setUploaded_file(String uploaded_file) {
		this.uploaded_file = uploaded_file;
	}

	public String getProject_data_id() {
		return project_data_id;
	}

	public void setProject_data_id(String project_data_id) {
		this.project_data_id = project_data_id;
	}

	public String getCommissioned_length() {
		return commissioned_length;
	}

	public void setCommissioned_length(String commissioned_length) {
		this.commissioned_length = commissioned_length;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getOngoing_projects() {
		return ongoing_projects;
	}

	public void setOngoing_projects(String ongoing_projects) {
		this.ongoing_projects = ongoing_projects;
	}

	public String getTotal_length() {
		return total_length;
	}

	public void setTotal_length(String total_length) {
		this.total_length = total_length;
	}

	public String getTotal_earthwork() {
		return total_earthwork;
	}

	public void setTotal_earthwork(String total_earthwork) {
		this.total_earthwork = total_earthwork;
	}

	public String getCompleted_track() {
		return completed_track;
	}

	public void setCompleted_track(String completed_track) {
		this.completed_track = completed_track;
	}

	public String getCompleted_major_bridges() {
		return completed_major_bridges;
	}

	public void setCompleted_major_bridges(String completed_major_bridges) {
		this.completed_major_bridges = completed_major_bridges;
	}

	public String getTotal_major_bridges() {
		return total_major_bridges;
	}

	public void setTotal_major_bridges(String total_major_bridges) {
		this.total_major_bridges = total_major_bridges;
	}

	public String getCompleted_minor_bridges() {
		return completed_minor_bridges;
	}

	public void setCompleted_minor_bridges(String completed_minor_bridges) {
		this.completed_minor_bridges = completed_minor_bridges;
	}

	public String getTotal_minor_bridges() {
		return total_minor_bridges;
	}

	public void setTotal_minor_bridges(String total_minor_bridges) {
		this.total_minor_bridges = total_minor_bridges;
	}

	public String getCompleted_rob() {
		return completed_rob;
	}

	public void setCompleted_rob(String completed_rob) {
		this.completed_rob = completed_rob;
	}

	public String getTotal_rob() {
		return total_rob;
	}

	public void setTotal_rob(String total_rob) {
		this.total_rob = total_rob;
	}

	public String getCompleted_rub() {
		return completed_rub;
	}

	public void setCompleted_rub(String completed_rub) {
		this.completed_rub = completed_rub;
	}

	public String getTotal_rub() {
		return total_rub;
	}

	public void setTotal_rub(String total_rub) {
		this.total_rub = total_rub;
	}

	public String[] getCommission_from_chainage() {
		return commission_from_chainage;
	}

	public void setCommission_from_chainage(String[] commission_from_chainage) {
		this.commission_from_chainage = commission_from_chainage;
	}

	public String[] getCommission_to_chainage() {
		return commission_to_chainage;
	}

	public void setCommission_to_chainage(String[] commission_to_chainage) {
		this.commission_to_chainage = commission_to_chainage;
	}

	public String[] getCommission_completed_length() {
		return commission_completed_length;
	}

	public void setCommission_completed_length(String[] commission_completed_length) {
		this.commission_completed_length = commission_completed_length;
	}

	public String getCommission_fromchainage() {
		return commission_fromchainage;
	}

	public void setCommission_fromchainage(String commission_fromchainage) {
		this.commission_fromchainage = commission_fromchainage;
	}

	public String getCommission_tochainage() {
		return commission_tochainage;
	}

	public void setCommission_tochainage(String commission_tochainage) {
		this.commission_tochainage = commission_tochainage;
	}

	public String getCommission_completedlength() {
		return commission_completedlength;
	}

	public void setCommission_completedlength(String commission_completedlength) {
		this.commission_completedlength = commission_completedlength;
	}

	public String getCommissioned_id() {
		return commissioned_id;
	}

	public void setCommissioned_id(String commissioned_id) {
		this.commissioned_id = commissioned_id;
	}

	public List<Project> getProjectCommissionedLengthList() {
		return projectCommissionedLengthList;
	}

	public void setProjectCommissionedLengthList(List<Project> projectCommissionedLengthList) {
		this.projectCommissionedLengthList = projectCommissionedLengthList;
	}

	public String getProposed_length() {
		return proposed_length;
	}

	public void setProposed_length(String proposed_length) {
		this.proposed_length = proposed_length;
	}

	public String getProject_commissioned_total() {
		return project_commissioned_total;
	}

	public void setProject_commissioned_total(String project_commissioned_total) {
		this.project_commissioned_total = project_commissioned_total;
	}

	public String getProposed_length_by_type() {
		return proposed_length_by_type;
	}

	public void setProposed_length_by_type(String proposed_length_by_type) {
		this.proposed_length_by_type = proposed_length_by_type;
	}

	public String getStructure_type() {
		return structure_type;
	}

	public void setStructure_type(String structure_type) {
		this.structure_type = structure_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getProject_count() {
		return project_count;
	}

	public void setProject_count(String project_count) {
		this.project_count = project_count;
	}

	
}
