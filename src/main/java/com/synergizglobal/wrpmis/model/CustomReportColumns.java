package com.synergizglobal.wrpmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CustomReportColumns {
	private String module_name_fk,column_name,name,table_name,grpHead,grpHeadColumns,filterColumns,filter_name,option_id,option_value,created_by_user_id_fk,layout_name;
	
	private String option_ids,option_values;
	

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getModule_name_fk() {
		return module_name_fk;
	}

	public void setModule_name_fk(String module_name_fk) {
		this.module_name_fk = module_name_fk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getGrpHead() {
		return grpHead;
	}

	public void setGrpHead(String grpHead) {
		this.grpHead = grpHead;
	}

	public String getGrpHeadColumns() {
		return grpHeadColumns;
	}

	public void setGrpHeadColumns(String grpHeadColumns) {
		this.grpHeadColumns = grpHeadColumns;
	}

	public String getFilterColumns() {
		return filterColumns;
	}

	public void setFilterColumns(String filterColumns) {
		this.filterColumns = filterColumns;
	}

	public String getFilter_name() {
		return filter_name;
	}

	public void setFilter_name(String filter_name) {
		this.filter_name = filter_name;
	}

	public String getOption_id() {
		return option_id;
	}

	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}

	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}

	public String getOption_ids() {
		return option_ids;
	}

	public void setOption_ids(String option_ids) {
		this.option_ids = option_ids;
	}

	public String getOption_values() {
		return option_values;
	}

	public void setOption_values(String option_values) {
		this.option_values = option_values;
	}

	public String getCreated_by_user_id_fk() {
		return created_by_user_id_fk;
	}

	public void setCreated_by_user_id_fk(String created_by_user_id_fk) {
		this.created_by_user_id_fk = created_by_user_id_fk;
	}

	public String getLayout_name() {
		return layout_name;
	}

	public void setLayout_name(String layout_name) {
		this.layout_name = layout_name;
	}


	
	
	
}
