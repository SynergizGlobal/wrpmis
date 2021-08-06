package com.synergizglobal.pmis.model;

import java.util.List;

public class ContractResource {
	
	private String resource_id, contract_id_fk, structure_fk, date, resource_type, resource_name, quantity,
	created_by_user_id, project_id,created_date,contract_name,contract_short_name,work_id,work_id_fk,work_short_name,work_name,project_id_fk,project_name;

	private String []  resource_ids, resource_types, resource_names, quantitys, created_by_user_ids, created_dates;
	List<ContractResource> resourceList;
	
	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public List<ContractResource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ContractResource> resourceList) {
		this.resourceList = resourceList;
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

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
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

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getStructure_fk() {
		return structure_fk;
	}

	public void setStructure_fk(String structure_fk) {
		this.structure_fk = structure_fk;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCreated_by_user_id() {
		return created_by_user_id;
	}

	public void setCreated_by_user_id(String created_by_user_id) {
		this.created_by_user_id = created_by_user_id;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String[] getResource_ids() {
		return resource_ids;
	}

	public void setResource_ids(String[] resource_ids) {
		this.resource_ids = resource_ids;
	}

	public String[] getResource_types() {
		return resource_types;
	}

	public void setResource_types(String[] resource_types) {
		this.resource_types = resource_types;
	}

	public String[] getResource_names() {
		return resource_names;
	}

	public void setResource_names(String[] resource_names) {
		this.resource_names = resource_names;
	}

	public String[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(String[] quantitys) {
		this.quantitys = quantitys;
	}

	public String[] getCreated_by_user_ids() {
		return created_by_user_ids;
	}

	public void setCreated_by_user_ids(String[] created_by_user_ids) {
		this.created_by_user_ids = created_by_user_ids;
	}

	public String[] getCreated_dates() {
		return created_dates;
	}

	public void setCreated_dates(String[] created_dates) {
		this.created_dates = created_dates;
	}

}
