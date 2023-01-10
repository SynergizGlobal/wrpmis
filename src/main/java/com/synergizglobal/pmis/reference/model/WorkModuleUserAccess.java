package com.synergizglobal.pmis.reference.model;

import java.util.List;

import com.synergizglobal.pmis.model.Structure;

public class WorkModuleUserAccess {
	
	private String bank_name_old,bank_name_fk,bank_name,bank_name_new,Table_name,column_name,captiliszedTableName,tName,project_id_fk,project_name,work_id_fk,work_name,work_short_name,module_name_fk,
	user_id,user_name,designation,contract_id_fk,structure_type_fk,form_name,form1_users,form2_users,form3_users;
	private List<WorkModuleUserAccess> workModuleUserAccessList;
	private List<WorkModuleUserAccess> workModuleUserAccessList1;
	private List<WorkModuleUserAccess> tablesList;
	private List<WorkModuleUserAccess> countList;
	private List<WorkModuleUserAccess> dList;
	private List<WorkModuleUserAccess> dList1;	
	
	private List<WorkModuleUserAccess> responsiblePeopleLists;

	public String getBank_name_old() {
		return bank_name_old;
	}

	public void setBank_name_old(String bank_name_old) {
		this.bank_name_old = bank_name_old;
	}

	public String getBank_name_fk() {
		return bank_name_fk;
	}

	public void setBank_name_fk(String bank_name_fk) {
		this.bank_name_fk = bank_name_fk;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_name_new() {
		return bank_name_new;
	}

	public void setBank_name_new(String bank_name_new) {
		this.bank_name_new = bank_name_new;
	}

	public List<WorkModuleUserAccess> getWorkModuleUserAccessList() {
		return workModuleUserAccessList;
	}

	public void setWorkModuleUserAccessList(List<WorkModuleUserAccess> WorkModuleUserAccessList) {
		this.workModuleUserAccessList = WorkModuleUserAccessList;
	}

	public List<WorkModuleUserAccess> getWorkModuleUserAccessList1() {
		return workModuleUserAccessList1;
	}

	public void setWorkModuleUserAccessList1(List<WorkModuleUserAccess> WorkModuleUserAccessList1) {
		this.workModuleUserAccessList1 = WorkModuleUserAccessList1;
	}

	public String getTable_name() {
		return Table_name;
	}

	public void setTable_name(String table_name) {
		Table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getCaptiliszedTableName() {
		return captiliszedTableName;
	}

	public void setCaptiliszedTableName(String captiliszedTableName) {
		this.captiliszedTableName = captiliszedTableName;
	}

	public List<WorkModuleUserAccess> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<WorkModuleUserAccess> tablesList) {
		this.tablesList = tablesList;
	}

	public List<WorkModuleUserAccess> getCountList() {
		return countList;
	}

	public void setCountList(List<WorkModuleUserAccess> countList) {
		this.countList = countList;
	}

	public List<WorkModuleUserAccess> getdList() {
		return dList;
	}

	public void setdList(List<WorkModuleUserAccess> dList) {
		this.dList = dList;
	}

	public List<WorkModuleUserAccess> getdList1() {
		return dList1;
	}

	public void setdList1(List<WorkModuleUserAccess> dList1) {
		this.dList1 = dList1;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
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

	public String getModule_name_fk() {
		return module_name_fk;
	}

	public void setModule_name_fk(String module_name_fk) {
		this.module_name_fk = module_name_fk;
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

	public List<WorkModuleUserAccess> getResponsiblePeopleLists() {
		return responsiblePeopleLists;
	}

	public void setResponsiblePeopleLists(List<WorkModuleUserAccess> responsiblePeopleLists) {
		this.responsiblePeopleLists = responsiblePeopleLists;
	}

	public String getContract_id_fk() {
		return contract_id_fk;
	}

	public void setContract_id_fk(String contract_id_fk) {
		this.contract_id_fk = contract_id_fk;
	}

	public String getStructure_type_fk() {
		return structure_type_fk;
	}

	public void setStructure_type_fk(String structure_type_fk) {
		this.structure_type_fk = structure_type_fk;
	}

	public String getForm_name() {
		return form_name;
	}

	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	public String getForm1_users() {
		return form1_users;
	}

	public void setForm1_users(String form1_users) {
		this.form1_users = form1_users;
	}

	public String getForm2_users() {
		return form2_users;
	}

	public void setForm2_users(String form2_users) {
		this.form2_users = form2_users;
	}

	public String getForm3_users() {
		return form3_users;
	}

	public void setForm3_users(String form3_users) {
		this.form3_users = form3_users;
	}
	

}
