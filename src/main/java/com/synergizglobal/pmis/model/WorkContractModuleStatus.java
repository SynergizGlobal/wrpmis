package com.synergizglobal.pmis.model;

import java.util.List;

public class WorkContractModuleStatus {
	
	private String work_status_id, work_id_fk,work_name,contract_name,project_id_fk,project_name, contract_id_fk, module_name_fk, month, status_as_on_month;

	private List<WorkContractModuleStatus> workContractStatus;
	
	private String [] status_as_on_months;
	
	
	public String[] getStatus_as_on_months() {
		return status_as_on_months;
	}

	public void setStatus_as_on_months(String[] status_as_on_months) {
		this.status_as_on_months = status_as_on_months;
	}

	public List<WorkContractModuleStatus> getWorkContractStatus() {
		return workContractStatus;
	}

	public void setWorkContractStatus(List<WorkContractModuleStatus> objsList) {
		this.workContractStatus = objsList;
	}

	public String getWork_status_id() {
		return work_status_id;
	}

	public void setWork_status_id(String work_status_id) {
		this.work_status_id = work_status_id;
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

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
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

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
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

}
