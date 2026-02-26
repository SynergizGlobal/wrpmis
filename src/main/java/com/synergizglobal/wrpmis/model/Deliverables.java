package com.synergizglobal.wrpmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Deliverables {
	
	private String id,deliverable_id, project_name, work_id_fk,work_name,work_short_name,project_id_fk,contract_name,contract_short_name, contract_id_fk, 
	deliverable_type, deliverable_description, status_fk, user_id, user_name, designation,contract_milestones_id_fk,milestone_id,milestone_name,
	milestone_payment,deliverable_document_id,deliverable_document_name,original_due_date,revised_due_date,submission_date,approval_date,payment,
	deliverable_document_file_name,remarks,created_date,created_by,updated_date,updated_by,deliverable_id_fk,contract_id,work_id,project_id,due_date,
	contractor_id_fk,contractor_id,contractor_name,uploaded_by_user_id_fk,status,created_by_user_id_fk;

	private String []  deliverable_document_ids,deliverable_document_names,original_due_dates,revised_due_dates,submission_dates,approval_dates,payments,
	deliverable_document_file_names,remarkss;
	
	private MultipartFile[] deliverableDocumentFiles;

	private List<Deliverables> deliverableDocuments;
	
	private MultipartFile uploadFile;
	
	

	public String getContractor_id_fk() {
		return contractor_id_fk;
	}

	public void setContractor_id_fk(String contractor_id_fk) {
		this.contractor_id_fk = contractor_id_fk;
	}

	public String getContractor_id() {
		return contractor_id;
	}

	public void setContractor_id(String contractor_id) {
		this.contractor_id = contractor_id;
	}

	public String getContractor_name() {
		return contractor_name;
	}

	public void setContractor_name(String contractor_name) {
		this.contractor_name = contractor_name;
	}

	public MultipartFile[] getDeliverableDocumentFiles() {
		return deliverableDocumentFiles;
	}

	public void setDeliverableDocumentFiles(MultipartFile[] deliverableDocumentFiles) {
		this.deliverableDocumentFiles = deliverableDocumentFiles;
	}

	public String[] getDeliverable_document_ids() {
		return deliverable_document_ids;
	}

	public void setDeliverable_document_ids(String[] deliverable_document_ids) {
		this.deliverable_document_ids = deliverable_document_ids;
	}

	public String[] getDeliverable_document_names() {
		return deliverable_document_names;
	}

	public void setDeliverable_document_names(String[] deliverable_document_names) {
		this.deliverable_document_names = deliverable_document_names;
	}

	public String[] getOriginal_due_dates() {
		return original_due_dates;
	}

	public void setOriginal_due_dates(String[] original_due_dates) {
		this.original_due_dates = original_due_dates;
	}

	public String[] getRevised_due_dates() {
		return revised_due_dates;
	}

	public void setRevised_due_dates(String[] revised_due_dates) {
		this.revised_due_dates = revised_due_dates;
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

	public String[] getPayments() {
		return payments;
	}

	public void setPayments(String[] payments) {
		this.payments = payments;
	}

	public String[] getDeliverable_document_file_names() {
		return deliverable_document_file_names;
	}

	public void setDeliverable_document_file_names(String[] deliverable_document_file_names) {
		this.deliverable_document_file_names = deliverable_document_file_names;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
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

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getDeliverable_document_id() {
		return deliverable_document_id;
	}

	public void setDeliverable_document_id(String deliverable_document_id) {
		this.deliverable_document_id = deliverable_document_id;
	}

	public String getDeliverable_document_name() {
		return deliverable_document_name;
	}

	public void setDeliverable_document_name(String deliverable_document_name) {
		this.deliverable_document_name = deliverable_document_name;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getProject_id_fk() {
		return project_id_fk;
	}

	public void setProject_id_fk(String project_id_fk) {
		this.project_id_fk = project_id_fk;
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

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getDeliverable_type() {
		return deliverable_type;
	}

	public void setDeliverable_type(String deliverable_type) {
		this.deliverable_type = deliverable_type;
	}

	public String getDeliverable_description() {
		return deliverable_description;
	}

	public void setDeliverable_description(String deliverable_description) {
		this.deliverable_description = deliverable_description;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContract_milestones_id_fk() {
		return contract_milestones_id_fk;
	}

	public void setContract_milestones_id_fk(String contract_milestones_id_fk) {
		this.contract_milestones_id_fk = contract_milestones_id_fk;
	}

	public String getMilestone_id() {
		return milestone_id;
	}

	public void setMilestone_id(String milestone_id) {
		this.milestone_id = milestone_id;
	}

	public String getMilestone_name() {
		return milestone_name;
	}

	public void setMilestone_name(String milestone_name) {
		this.milestone_name = milestone_name;
	}

	public String getMilestone_payment() {
		return milestone_payment;
	}

	public void setMilestone_payment(String milestone_payment) {
		this.milestone_payment = milestone_payment;
	}

	public String getDeliverable_id() {
		return deliverable_id;
	}

	public void setDeliverable_id(String deliverable_id) {
		this.deliverable_id = deliverable_id;
	}

	public String getOriginal_due_date() {
		return original_due_date;
	}

	public void setOriginal_due_date(String original_due_date) {
		this.original_due_date = original_due_date;
	}

	public String getRevised_due_date() {
		return revised_due_date;
	}

	public void setRevised_due_date(String revised_due_date) {
		this.revised_due_date = revised_due_date;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDeliverable_document_file_name() {
		return deliverable_document_file_name;
	}

	public void setDeliverable_document_file_name(String deliverable_document_file_name) {
		this.deliverable_document_file_name = deliverable_document_file_name;
	}

	public List<Deliverables> getDeliverableDocuments() {
		return deliverableDocuments;
	}

	public void setDeliverableDocuments(List<Deliverables> deliverableDocuments) {
		this.deliverableDocuments = deliverableDocuments;
	}

	public String getDeliverable_id_fk() {
		return deliverable_id_fk;
	}

	public void setDeliverable_id_fk(String deliverable_id_fk) {
		this.deliverable_id_fk = deliverable_id_fk;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

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

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploaded_by_user_id_fk() {
		return uploaded_by_user_id_fk;
	}

	public void setUploaded_by_user_id_fk(String uploaded_by_user_id_fk) {
		this.uploaded_by_user_id_fk = uploaded_by_user_id_fk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}
	
	
	
}
