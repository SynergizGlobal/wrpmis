package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Deliverables {
	
	private String id, project_priority_fk,project_name, work_id_fk,work_name,work_short_name,project_id_fk,contract_name,contract_short_name, contract_id_fk, deliverable_type_fk, deliverable_description, target_date, start_date,
	finish_date, status_fk, remarks, attachment;

	private MultipartFile deliverablesFile;

	private List<MultipartFile> deliverableFiles;
	private List<Deliverables> deliverableFilesList;
	private String[] deliverableFileNames;
	
	public List<MultipartFile> getDeliverableFiles() {
		return deliverableFiles;
	}

	public void setDeliverableFiles(List<MultipartFile> deliverableFiles) {
		this.deliverableFiles = deliverableFiles;
	}

	public List<Deliverables> getDeliverableFilesList() {
		return deliverableFilesList;
	}

	public void setDeliverableFilesList(List<Deliverables> deliverableFilesList) {
		this.deliverableFilesList = deliverableFilesList;
	}

	public String[] getDeliverableFileNames() {
		return deliverableFileNames;
	}

	public void setDeliverableFileNames(String[] deliverableFileNames) {
		this.deliverableFileNames = deliverableFileNames;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDeliverable_type_fk() {
		return deliverable_type_fk;
	}

	public void setDeliverable_type_fk(String deliverable_type_fk) {
		this.deliverable_type_fk = deliverable_type_fk;
	}

	public String getDeliverable_description() {
		return deliverable_description;
	}

	public void setDeliverable_description(String deliverable_description) {
		this.deliverable_description = deliverable_description;
	}

	public String getTarget_date() {
		return target_date;
	}

	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getFinish_date() {
		return finish_date;
	}

	public void setFinish_date(String finish_date) {
		this.finish_date = finish_date;
	}

	public String getStatus_fk() {
		return status_fk;
	}

	public void setStatus_fk(String status_fk) {
		this.status_fk = status_fk;
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

	public MultipartFile getDeliverablesFile() {
		return deliverablesFile;
	}

	public void setDeliverablesFile(MultipartFile deliverablesFile) {
		this.deliverablesFile = deliverablesFile;
	}
	
	
	
}
