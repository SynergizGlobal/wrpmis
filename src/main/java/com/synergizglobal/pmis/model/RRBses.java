package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RRBses {

	private String bses_id, work_id_fk, contract_id_fk, agency_name,project_id,contract_id,project_id_fk,project_name,work_short_name,work_name,contract_name,contract_short_name, report_submission_to_mrvc, report_submission_to_mmrda,
	remarks,committee_details_id, bses_id_fk, date_of_nomination, committee_details_remarks,id, committee_details_id_fk,bses_file_type,
	 bses_file_type_fk, name, attachment, created_date,details;
	
	private String [] committee_details_ids, bses_id_fks, date_of_nominations, committee_details_remarkss,bses_file_types,attachmentFileNames,
	ids, bses_file_type_fks, names, attachments, created_dates,committee_details_id_fks, detailss;
	private MultipartFile[] rrFiles;
	private List<RRBses> detailsList;
	private List<RRBses> comiteDetailsList;
	private List<RRBses> filesList;

	public MultipartFile[] getRrFiles() {
		return rrFiles;
	}

	public void setRrFiles(MultipartFile[] rrFiles) {
		this.rrFiles = rrFiles;
	}

	public String[] getAttachmentFileNames() {
		return attachmentFileNames;
	}

	public void setAttachmentFileNames(String[] attachmentFileNames) {
		this.attachmentFileNames = attachmentFileNames;
	}

	public List<RRBses> getComiteDetailsList() {
		return comiteDetailsList;
	}

	public void setComiteDetailsList(List<RRBses> comiteDetailsList) {
		this.comiteDetailsList = comiteDetailsList;
	}

	public String getBses_file_type() {
		return bses_file_type;
	}

	public void setBses_file_type(String bses_file_type) {
		this.bses_file_type = bses_file_type;
	}

	public String[] getBses_file_types() {
		return bses_file_types;
	}

	public void setBses_file_types(String[] bses_file_types) {
		this.bses_file_types = bses_file_types;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	public List<RRBses> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<RRBses> filesList) {
		this.filesList = filesList;
	}

	public String getBses_id() {
		return bses_id;
	}

	public void setBses_id(String bses_id) {
		this.bses_id = bses_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getReport_submission_to_mrvc() {
		return report_submission_to_mrvc;
	}

	public void setReport_submission_to_mrvc(String report_submission_to_mrvc) {
		this.report_submission_to_mrvc = report_submission_to_mrvc;
	}

	public String getReport_submission_to_mmrda() {
		return report_submission_to_mmrda;
	}

	public void setReport_submission_to_mmrda(String report_submission_to_mmrda) {
		this.report_submission_to_mmrda = report_submission_to_mmrda;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCommittee_details_id() {
		return committee_details_id;
	}

	public void setCommittee_details_id(String committee_details_id) {
		this.committee_details_id = committee_details_id;
	}

	public String getBses_id_fk() {
		return bses_id_fk;
	}

	public void setBses_id_fk(String bses_id_fk) {
		this.bses_id_fk = bses_id_fk;
	}

	public String getDate_of_nomination() {
		return date_of_nomination;
	}

	public void setDate_of_nomination(String date_of_nomination) {
		this.date_of_nomination = date_of_nomination;
	}

	public String getCommittee_details_remarks() {
		return committee_details_remarks;
	}

	public void setCommittee_details_remarks(String committee_details_remarks) {
		this.committee_details_remarks = committee_details_remarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommittee_details_id_fk() {
		return committee_details_id_fk;
	}

	public void setCommittee_details_id_fk(String committee_details_id_fk) {
		this.committee_details_id_fk = committee_details_id_fk;
	}

	public String getBses_file_type_fk() {
		return bses_file_type_fk;
	}

	public void setBses_file_type_fk(String bses_file_type_fk) {
		this.bses_file_type_fk = bses_file_type_fk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String[] getCommittee_details_ids() {
		return committee_details_ids;
	}

	public void setCommittee_details_ids(String[] committee_details_ids) {
		this.committee_details_ids = committee_details_ids;
	}

	public String[] getBses_id_fks() {
		return bses_id_fks;
	}

	public void setBses_id_fks(String[] bses_id_fks) {
		this.bses_id_fks = bses_id_fks;
	}

	public String[] getDate_of_nominations() {
		return date_of_nominations;
	}

	public void setDate_of_nominations(String[] date_of_nominations) {
		this.date_of_nominations = date_of_nominations;
	}

	public String[] getCommittee_details_remarkss() {
		return committee_details_remarkss;
	}

	public void setCommittee_details_remarkss(String[] committee_details_remarkss) {
		this.committee_details_remarkss = committee_details_remarkss;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getBses_file_type_fks() {
		return bses_file_type_fks;
	}

	public void setBses_file_type_fks(String[] bses_file_type_fks) {
		this.bses_file_type_fks = bses_file_type_fks;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}

	public String[] getCreated_dates() {
		return created_dates;
	}

	public void setCreated_dates(String[] created_dates) {
		this.created_dates = created_dates;
	}

	public String[] getCommittee_details_id_fks() {
		return committee_details_id_fks;
	}

	public void setCommittee_details_id_fks(String[] committee_details_id_fks) {
		this.committee_details_id_fks = committee_details_id_fks;
	}

	public String[] getDetailss() {
		return detailss;
	}

	public void setDetailss(String[] detailss) {
		this.detailss = detailss;
	}

	public List<RRBses> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<RRBses> detailsList) {
		this.detailsList = detailsList;
	}
	
}
