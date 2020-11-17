package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Document {
	
	private String document_no, project_priority_fk,work_id,project_id, project_id_fk, work_id_fk,project_name, contract_id_fk, document_type_fk, document_name, responsible_for_approval,
	id, document_no_fk,designation, revision_no, status_fk, submission_date, approval_date,status,document_type,project_priority,user_id,user_name, remarks, document_attachment,contract_short_name,work_short_name,work_name,contract_name;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getProject_priority() {
		return project_priority;
	}

	public void setProject_priority(String project_priority) {
		this.project_priority = project_priority;
	}

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

	private String[] document_no_fks,document_nos, revision_nos, status_fks, submission_dates, approval_dates, remarkss, document_attachments,documentsFileNames,ids;
	

	public String[] getDocument_nos() {
		return document_nos;
	}

	public void setDocument_nos(String[] document_nos) {
		this.document_nos = document_nos;
	}

	private List<Document> documents;


	private MultipartFile[] documentsFile;
	
	
	
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

	
	

	public String[] getDocumentsFileNames() {
		return documentsFileNames;
	}

	public void setDocumentsFileNames(String[] documentsFileNames) {
		this.documentsFileNames = documentsFileNames;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
	}

	public String[] getDocument_no_fks() {
		return document_no_fks;
	}

	public void setDocument_no_fks(String[] document_no_fks) {
		this.document_no_fks = document_no_fks;
	}

	public String[] getRevision_nos() {
		return revision_nos;
	}

	public void setRevision_nos(String[] revision_nos) {
		this.revision_nos = revision_nos;
	}

	public String[] getStatus_fks() {
		return status_fks;
	}

	public void setStatus_fks(String[] status_fks) {
		this.status_fks = status_fks;
	}

	public String[] getSubmission_dates() {
		return submission_dates;
	}

	public void setSubmission_dates(String[] submission_dates) {
		this.submission_dates = submission_dates;
	}

	public String[] getApproval_dates() {
		return approval_dates;
	}

	public void setApproval_dates(String[] approval_dates) {
		this.approval_dates = approval_dates;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
	}

	public String[] getDocument_attachments() {
		return document_attachments;
	}

	public void setDocument_attachments(String[] document_attachments) {
		this.document_attachments = document_attachments;
	}

	
	
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public MultipartFile[] getDocumentsFile() {
		return documentsFile;
	}

	public void setDocumentsFile(MultipartFile[] documentsFile) {
		this.documentsFile = documentsFile;
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

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getDocument_no() {
		return document_no;
	}

	public void setDocument_no(String document_no) {
		this.document_no = document_no;
	}

	public String getProject_priority_fk() {
		return project_priority_fk;
	}

	public void setProject_priority_fk(String project_priority_fk) {
		this.project_priority_fk = project_priority_fk;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
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

	public String getDocument_type_fk() {
		return document_type_fk;
	}

	public void setDocument_type_fk(String document_type_fk) {
		this.document_type_fk = document_type_fk;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public String getResponsible_for_approval() {
		return responsible_for_approval;
	}

	public void setResponsible_for_approval(String responsible_for_approval) {
		this.responsible_for_approval = responsible_for_approval;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocument_no_fk() {
		return document_no_fk;
	}

	public void setDocument_no_fk(String document_no_fk) {
		this.document_no_fk = document_no_fk;
	}

	public String getRevision_no() {
		return revision_no;
	}

	public void setRevision_no(String revision_no) {
		this.revision_no = revision_no;
	}


	public String getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(String submission_date) {
		this.submission_date = submission_date;
	}

	public String getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(String approval_date) {
		this.approval_date = approval_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocument_attachment() {
		return document_attachment;
	}

	public void setDocument_attachment(String document_attachment) {
		this.document_attachment = document_attachment;
	}

}
