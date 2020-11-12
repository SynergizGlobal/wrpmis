package com.synergizglobal.pmis.model;

public class Document {
	
	private String document_no, project_priority_fk, project_id_fk, work_id_fk, contract_id_fk, document_type_fk, document_name, responsible_for_approval,
	id, document_no_fk, revision_no, status, submission_date, approval_date, remarks, document_attachment,contract_short_name,work_short_name,work_name,contract_name;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
