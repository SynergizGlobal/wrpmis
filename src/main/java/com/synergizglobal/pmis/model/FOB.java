package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FOB {
	private String fob_id,fob_name,id,contract_id_fk,date_of_approval,target_date,construction_start_date,actual_completion_date,
	commissioning_date,estimated_cost,completion_cost,work_status_fk,latitude,longitude,remarks,attachment,fob_detail_id,fob_id_fk,
	detail_name,value,work_id_fk,project_id_fk,revised_completion,project_name,contract_id,contract_name,contract_short_name,last_sanctioned_cost,
	project_id,work_id,work_name,work_short_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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

	public String getWork_short_name() {
		return work_short_name;
	}

	public void setWork_short_name(String work_short_name) {
		this.work_short_name = work_short_name;
	}

	public String getContract_short_name() {
		return contract_short_name;
	}

	public void setContract_short_name(String contract_short_name) {
		this.contract_short_name = contract_short_name;
	}

	private MultipartFile [] fobFile;
	private List<FOB> fobDetails;
	private List<FOB> fobImages;
	private String[] fob_id_fks,fobFileNames;
	private String[] fob_detail_names,fob_detail_values;
	


	public String[] getFobFileNames() {
		return fobFileNames;
	}

	public void setFobFileNames(String[] fobFileNames) {
		this.fobFileNames = fobFileNames;
	}

	public String[] getFob_id_fks() {
		return fob_id_fks;
	}

	public void setFob_id_fks(String[] fob_id_fks) {
		this.fob_id_fks = fob_id_fks;
	}

	public MultipartFile[] getFobFile() {
		return fobFile;
	}

	public void setFobFile(MultipartFile[] fobFile) {
		this.fobFile = fobFile;
	}

	public List<FOB> getFobImages() {
		return fobImages;
	}

	public void setFobImages(List<FOB> fobImages) {
		this.fobImages = fobImages;
	}

	public String getRevised_completion() {
		return revised_completion;
	}

	public void setRevised_completion(String revised_completion) {
		this.revised_completion = revised_completion;
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

	public String getFob_detail_id() {
		return fob_detail_id;
	}

	public void setFob_detail_id(String fob_detail_id) {
		this.fob_detail_id = fob_detail_id;
	}

	public String getFob_id_fk() {
		return fob_id_fk;
	}

	public void setFob_id_fk(String fob_id_fk) {
		this.fob_id_fk = fob_id_fk;
	}

	public String getDetail_name() {
		return detail_name;
	}

	public void setDetail_name(String detail_name) {
		this.detail_name = detail_name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getFob_id() {
		return fob_id;
	}

	public void setFob_id(String fob_id) {
		this.fob_id = fob_id;
	}

	public String getFob_name() {
		return fob_name;
	}

	public void setFob_name(String fob_name) {
		this.fob_name = fob_name;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getDate_of_approval() {
		return date_of_approval;
	}

	public void setDate_of_approval(String date_of_approval) {
		this.date_of_approval = date_of_approval;
	}

	public String getTarget_date() {
		return target_date;
	}

	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}

	public String getConstruction_start_date() {
		return construction_start_date;
	}

	public void setConstruction_start_date(String construction_start_date) {
		this.construction_start_date = construction_start_date;
	}

	public String getActual_completion_date() {
		return actual_completion_date;
	}

	public void setActual_completion_date(String actual_completion_date) {
		this.actual_completion_date = actual_completion_date;
	}

	public String getCommissioning_date() {
		return commissioning_date;
	}

	public void setCommissioning_date(String commissioning_date) {
		this.commissioning_date = commissioning_date;
	}

	public String getEstimated_cost() {
		return estimated_cost;
	}

	public void setEstimated_cost(String estimated_cost) {
		this.estimated_cost = estimated_cost;
	}

	public String getCompletion_cost() {
		return completion_cost;
	}

	public void setCompletion_cost(String completion_cost) {
		this.completion_cost = completion_cost;
	}

	public String getWork_status_fk() {
		return work_status_fk;
	}

	public void setWork_status_fk(String work_status_fk) {
		this.work_status_fk = work_status_fk;
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

	public List<FOB> getFobDetails() {
		return fobDetails;
	}

	public void setFobDetails(List<FOB> fobDetails) {
		this.fobDetails = fobDetails;
	}

	public String[] getFob_detail_names() {
		return fob_detail_names;
	}

	public void setFob_detail_names(String[] fob_detail_names) {
		this.fob_detail_names = fob_detail_names;
	}

	public String[] getFob_detail_values() {
		return fob_detail_values;
	}

	public void setFob_detail_values(String[] fob_detail_values) {
		this.fob_detail_values = fob_detail_values;
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

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getLast_sanctioned_cost() {
		return last_sanctioned_cost;
	}

	public void setLast_sanctioned_cost(String last_sanctioned_cost) {
		this.last_sanctioned_cost = last_sanctioned_cost;
	}
	
}
