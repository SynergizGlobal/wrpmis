package com.synergizglobal.pmis.model;

import java.util.List;

public class FOB {
	private String fob_id,fob_name,contract_id_fk,date_of_approval,target_date,construction_start_date,actual_completion_date,commissioning_date,estimated_cost,
	completion_cost,work_status_fk,latitude,longitude,weight,remark,contract_name,work_id_fk,work_name,module_name_fk,month,status_as_on_month,fob_detail_name,fob_detail_value;

	private List<FOB> fobDetails;
	
	private String[] fob_detail_names,fob_detail_values;
	
	
	
	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getModule_name_fk() {
		return module_name_fk;
	}

	public void setModule_name_fk(String module_name_fk) {
		this.module_name_fk = module_name_fk;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStatus_as_on_month() {
		return status_as_on_month;
	}

	public void setStatus_as_on_month(String status_as_on_month) {
		this.status_as_on_month = status_as_on_month;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public String getFob_detail_name() {
		return fob_detail_name;
	}

	public void setFob_detail_name(String fob_detail_name) {
		this.fob_detail_name = fob_detail_name;
	}

	public String getFob_detail_value() {
		return fob_detail_value;
	}

	public void setFob_detail_value(String fob_detail_value) {
		this.fob_detail_value = fob_detail_value;
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
	
}
