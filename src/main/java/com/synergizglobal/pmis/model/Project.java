package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Project {
	private String project_id,project_name,plan_head_number,pink_book_item_number,remarks,project_description,project_status,attachment
	,sanctioned_estimated_cost,sanctioned_year_fk,sanctioned_completion_cost,year_of_completion,projected_completion_year,latest_revised_cost,
	completion_cost,work_short_name,benefits,galleryFileNames,financial_year_fk,pb_item_no,project_pinkbook_id;
	
	private String id,file_name,project_id_fk,created_date,created_by,railway;
	
	
	private List<Project> projectFiles;
	private List<MultipartFile> projectGalleryFiles;
	private String[] projectGalleryFileNames;
	private List<MultipartFile> projectFile;
	private List<Project> projectFilesList;
	private String[] projectFileNames;
	private String[] financial_years,pink_book_item_numbers,railways;
	
	private List<Work> worksInfo;
	
	private List<Project> projectGallery;

	private List<Project> projectPinkBooks;

	public List<Project> getProjectFiles() {
		return projectFiles;
	}

	public void setProjectFiles(List<Project> projectFiles) {
		this.projectFiles = projectFiles;
	}

	public List<MultipartFile> getProjectFile() {
		return projectFile;
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

	public void setProjectFile(List<MultipartFile> projectFile) {
		this.projectFile = projectFile;
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

	public List<MultipartFile> getProjectGalleryFiles() {
		return projectGalleryFiles;
	}

	public void setProjectGalleryFiles(List<MultipartFile> projectGalleryFiles) {
		this.projectGalleryFiles = projectGalleryFiles;
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

	public List<Project> getProjectGallery() {
		return projectGallery;
	}

	public void setProjectGallery(List<Project> projectGallery) {
		this.projectGallery = projectGallery;
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
	
}
