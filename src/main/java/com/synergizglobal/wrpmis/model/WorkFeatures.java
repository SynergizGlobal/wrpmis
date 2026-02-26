package com.synergizglobal.wrpmis.model;

import java.util.List;

public class WorkFeatures {
	private String id,work_id_fk,category_fk,description,soft_delete_status_fk,category,title_fk,dashboard_url,title,work_id,work_name,work_short_name,status;
	
	private String Table_name,column_name,constraint_name,referenced_table_name,referenced_column_name,count,tName,value_old,value_new;
	
	private List<WorkFeatures> tablesList;
	private List<WorkFeatures> countList;
	private List<WorkFeatures> dList;
	private List<WorkFeatures> dList1;
	
	private String[] title_fks,dashboard_urls,statuses,category_fks,descriptions;

	public String[] getTitle_fks() {
		return title_fks;
	}

	public void setTitle_fks(String[] title_fks) {
		this.title_fks = title_fks;
	}

	public String[] getDashboard_urls() {
		return dashboard_urls;
	}

	public void setDashboard_urls(String[] dashboard_urls) {
		this.dashboard_urls = dashboard_urls;
	}

	public String[] getStatuses() {
		return statuses;
	}

	public void setStatuses(String[] statuses) {
		this.statuses = statuses;
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

	public String getValue_old() {
		return value_old;
	}

	public void setValue_old(String value_old) {
		this.value_old = value_old;
	}

	public String getValue_new() {
		return value_new;
	}

	public void setValue_new(String value_new) {
		this.value_new = value_new;
	}

	public String getReferenced_table_name() {
		return referenced_table_name;
	}

	public void setReferenced_table_name(String referenced_table_name) {
		this.referenced_table_name = referenced_table_name;
	}

	public String getReferenced_column_name() {
		return referenced_column_name;
	}

	public void setReferenced_column_name(String referenced_column_name) {
		this.referenced_column_name = referenced_column_name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
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

	public String getConstraint_name() {
		return constraint_name;
	}

	public void setConstraint_name(String constraint_name) {
		this.constraint_name = constraint_name;
	}

	public List<WorkFeatures> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<WorkFeatures> tablesList) {
		this.tablesList = tablesList;
	}

	public List<WorkFeatures> getCountList() {
		return countList;
	}

	public void setCountList(List<WorkFeatures> countList) {
		this.countList = countList;
	}

	public List<WorkFeatures> getdList() {
		return dList;
	}

	public void setdList(List<WorkFeatures> dList) {
		this.dList = dList;
	}

	public List<WorkFeatures> getdList1() {
		return dList1;
	}

	public void setdList1(List<WorkFeatures> dList1) {
		this.dList1 = dList1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWork_id_fk() {
		return work_id_fk;
	}

	public void setWork_id_fk(String work_id_fk) {
		this.work_id_fk = work_id_fk;
	}

	public String getCategory_fk() {
		return category_fk;
	}

	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSoft_delete_status_fk() {
		return soft_delete_status_fk;
	}

	public void setSoft_delete_status_fk(String soft_delete_status_fk) {
		this.soft_delete_status_fk = soft_delete_status_fk;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle_fk() {
		return title_fk;
	}

	public void setTitle_fk(String title_fk) {
		this.title_fk = title_fk;
	}

	public String getDashboard_url() {
		return dashboard_url;
	}

	public void setDashboard_url(String dashboard_url) {
		this.dashboard_url = dashboard_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getCategory_fks() {
		return category_fks;
	}

	public void setCategory_fks(String[] category_fks) {
		this.category_fks = category_fks;
	}

	public String[] getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String[] descriptions) {
		this.descriptions = descriptions;
	}
	
}
