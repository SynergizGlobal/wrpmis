package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SafetyEquipment {
	private String safety_equipment_id, safety_equipment_number, safety_equipment_detail, validity_date,contract_id_fk,
	project_id_fk,work_id_fk,project_name,work_name,contract_name,attachment,remarks,work_id,project_id,contract_id;

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

	private String[] safety_equipment_numbers, safety_equipment_details, validity_dates,contract_id_fks,attachments,remarkss;
	
	public String[] getAttachments() {
		return attachments;
	}

	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}

	private List<SafetyEquipment> safetyEquipments;
	public List<SafetyEquipment> getSafetyEquipments() {
		return safetyEquipments;
	}

	public void setSafetyEquipments(List<SafetyEquipment> safetyEquipments) {
		this.safetyEquipments = safetyEquipments;
	}

	private MultipartFile SafetyEquipmentFile;
	


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

	

	public MultipartFile getSafetyEquipmentFile() {
		return SafetyEquipmentFile;
	}

	public void setSafetyEquipmentFile(MultipartFile safetyEquipmentFile) {
		SafetyEquipmentFile = safetyEquipmentFile;
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

}
