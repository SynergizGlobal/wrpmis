package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SafetyEquipment {
	private String safety_equipment_id, safety_equipment_number, safety_equipment_detail, validity_date,contract_id_fk,
	project_id_fk,work_id_fk,project_name,work_name,contract_name,attachment,remarks,work_id,project_id,contract_id,
	contract_short_name,inspecting_official,last_inspection_date,next_inspection_due,safety_instructions_id, document_name, document_url,work_short_name;
	
	private String[] safety_equipment_ids,safety_equipment_numbers, safety_equipment_details, validity_dates,contract_id_fks,
	attachments,remarkss,safetyEquipmentFileNames,inspecting_officials,last_inspection_dates,next_inspection_dues,safety_instructions_ids, document_names, document_urls;
	

	private List<SafetyEquipment> safetyEquipments;


	private MultipartFile[] safetyEquipmentFile;
	
	private MultipartFile[] safetyFile;
	
	
	
	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public MultipartFile[] getSafetyFile() {
		return safetyFile;
	}

	public void setSafetyFile(MultipartFile[] safetyFile) {
		this.safetyFile = safetyFile;
	}

	public String getSafety_instructions_id() {
		return safety_instructions_id;
	}

	public void setSafety_instructions_id(String safety_instructions_id) {
		this.safety_instructions_id = safety_instructions_id;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public String getDocument_url() {
		return document_url;
	}

	public void setDocument_url(String document_url) {
		this.document_url = document_url;
	}

	public String[] getSafety_instructions_ids() {
		return safety_instructions_ids;
	}

	public void setSafety_instructions_ids(String[] safety_instructions_ids) {
		this.safety_instructions_ids = safety_instructions_ids;
	}

	public String[] getDocument_names() {
		return document_names;
	}

	public void setDocument_names(String[] document_names) {
		this.document_names = document_names;
	}

	public String[] getDocument_urls() {
		return document_urls;
	}

	public void setDocument_urls(String[] document_urls) {
		this.document_urls = document_urls;
	}

	public String[] getInspecting_officials() {
		return inspecting_officials;
	}

	public void setInspecting_officials(String[] inspecting_officials) {
		this.inspecting_officials = inspecting_officials;
	}

	public String[] getLast_inspection_dates() {
		return last_inspection_dates;
	}

	public void setLast_inspection_dates(String[] last_inspection_dates) {
		this.last_inspection_dates = last_inspection_dates;
	}

	public String[] getNext_inspection_dues() {
		return next_inspection_dues;
	}

	public void setNext_inspection_dues(String[] next_inspection_dues) {
		this.next_inspection_dues = next_inspection_dues;
	}

	public String getInspecting_official() {
		return inspecting_official;
	}

	public void setInspecting_official(String inspecting_official) {
		this.inspecting_official = inspecting_official;
	}

	public String getLast_inspection_date() {
		return last_inspection_date;
	}

	public void setLast_inspection_date(String last_inspection_date) {
		this.last_inspection_date = last_inspection_date;
	}

	public String getNext_inspection_due() {
		return next_inspection_due;
	}

	public void setNext_inspection_due(String next_inspection_due) {
		this.next_inspection_due = next_inspection_due;
	}

	public String[] getSafetyEquipmentFileNames() {
		return safetyEquipmentFileNames;
	}

	public void setSafetyEquipmentFileNames(String[] safetyEquipmentFileNames) {
		this.safetyEquipmentFileNames = safetyEquipmentFileNames;
	}

	
	public List<SafetyEquipment> getSafetyEquipments() {
		return safetyEquipments;
	}

	public void setSafetyEquipments(List<SafetyEquipment> safetyEquipments) {
		this.safetyEquipments = safetyEquipments;
	}
	
	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
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

	
	


	public String[] getSafety_equipment_numbers() {
		return safety_equipment_numbers;
	}

	public void setSafety_equipment_numbers(String[] safety_equipment_numbers) {
		this.safety_equipment_numbers = safety_equipment_numbers;
	}

	public String[] getSafety_equipment_details() {
		return safety_equipment_details;
	}

	public void setSafety_equipment_details(String[] safety_equipment_details) {
		this.safety_equipment_details = safety_equipment_details;
	}

	public String[] getValidity_dates() {
		return validity_dates;
	}

	public void setValidity_dates(String[] validity_dates) {
		this.validity_dates = validity_dates;
	}

	public String[] getContract_id_fks() {
		return contract_id_fks;
	}

	public void setContract_id_fks(String[] contract_id_fks) {
		this.contract_id_fks = contract_id_fks;
	}

	public String[] getRemarkss() {
		return remarkss;
	}

	public void setRemarkss(String[] remarkss) {
		this.remarkss = remarkss;
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

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSafety_equipment_id() {
		return safety_equipment_id;
	}

	public void setSafety_equipment_id(String safety_equipment_id) {
		this.safety_equipment_id = safety_equipment_id;
	}

	public String getSafety_equipment_number() {
		return safety_equipment_number;
	}

	public void setSafety_equipment_number(String safety_equipment_number) {
		this.safety_equipment_number = safety_equipment_number;
	}

	public String getSafety_equipment_detail() {
		return safety_equipment_detail;
	}

	public void setSafety_equipment_detail(String safety_equipment_detail) {
		this.safety_equipment_detail = safety_equipment_detail;
	}

	public String getValidity_date() {
		return validity_date;
	}

	public void setValidity_date(String validity_date) {
		this.validity_date = validity_date;
	}

	public String[] getSafety_equipment_ids() {
		return safety_equipment_ids;
	}

	public void setSafety_equipment_ids(String[] safety_equipment_ids) {
		this.safety_equipment_ids = safety_equipment_ids;
	}

	public MultipartFile[] getSafetyEquipmentFile() {
		return safetyEquipmentFile;
	}

	public void setSafetyEquipmentFile(MultipartFile[] safetyEquipmentFile) {
		this.safetyEquipmentFile = safetyEquipmentFile;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

}
