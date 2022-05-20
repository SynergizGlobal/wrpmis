package com.synergizglobal.pmis.model;

import java.util.List;

public class OverviewDashboard {
	
	private String dashboard_id,dashboard_name,dashboard_icon,dashboard_url,source_table_name,source_field_name,source_field_value,show_left_menu;
	private List<OverviewDashboard> formsSubMenu;
	
	private String filter_id, left_menu_id_fk, filters_table, filter_label_name, filter_column_name,
	default_filter_column,default_filter_value, selected_value, priority, 
	filter_column_id,filters_reference_table,filter_option_id,filter_option_value,work_id,parent_id,params,
	query_for_work_search,query_for_filter_options,source_table_alias_name,filters_table_alias_name,order_by,is_first_option_selected,union_all,dashboard_type,dashboard_type_fk,
	 user_type_fk,user_role_name_fk,user_id,level,la_sub_category_fk,la_sub_category,accessibility;
	
	private List<OverviewDashboard> filter;

	private int work_exists_or_not;

	

	public String getLa_sub_category_fk() {
		return la_sub_category_fk;
	}

	public void setLa_sub_category_fk(String la_sub_category_fk) {
		this.la_sub_category_fk = la_sub_category_fk;
	}

	public String getLa_sub_category() {
		return la_sub_category;
	}

	public void setLa_sub_category(String la_sub_category) {
		this.la_sub_category = la_sub_category;
	}

	public String getDashboard_type() {
		return dashboard_type;
	}

	public void setDashboard_type(String dashboard_type) {
		this.dashboard_type = dashboard_type;
	}

	public String getDashboard_type_fk() {
		return dashboard_type_fk;
	}

	public void setDashboard_type_fk(String dashboard_type_fk) {
		this.dashboard_type_fk = dashboard_type_fk;
	}

	public String getDashboard_id() {
		return dashboard_id;
	}

	public void setDashboard_id(String dashboard_id) {
		this.dashboard_id = dashboard_id;
	}

	public String getDashboard_name() {
		return dashboard_name;
	}

	public void setDashboard_name(String dashboard_name) {
		this.dashboard_name = dashboard_name;
	}

	public String getDashboard_icon() {
		return dashboard_icon;
	}

	public void setDashboard_icon(String dashboard_icon) {
		this.dashboard_icon = dashboard_icon;
	}

	public String getDashboard_url() {
		return dashboard_url;
	}

	public void setDashboard_url(String dashboard_url) {
		this.dashboard_url = dashboard_url;
	}

	public void setFilters_reference_table(String filters_reference_table) {
		this.filters_reference_table = filters_reference_table;
	}

	public String getFilter_column_id() {
		return filter_column_id;
	}

	public void setFilter_column_id(String filter_column_id) {
		this.filter_column_id = filter_column_id;
	}

	public String getFilters_reference_table() {
		return filters_reference_table;
	}

	

	public String getDefault_filter_column() {
		return default_filter_column;
	}

	public void setDefault_filter_column(String default_filter_column) {
		this.default_filter_column = default_filter_column;
	}

	public String getDefault_filter_value() {
		return default_filter_value;
	}

	public void setDefault_filter_value(String default_filter_value) {
		this.default_filter_value = default_filter_value;
	}

	public String getSelected_value() {
		return selected_value;
	}

	public void setSelected_value(String selected_value) {
		this.selected_value = selected_value;
	}

	public String getFilter_id() {
		return filter_id;
	}

	public void setFilter_id(String filter_id) {
		this.filter_id = filter_id;
	}

	public String getLeft_menu_id_fk() {
		return left_menu_id_fk;
	}

	public void setLeft_menu_id_fk(String left_menu_id_fk) {
		this.left_menu_id_fk = left_menu_id_fk;
	}

	public String getFilters_table() {
		return filters_table;
	}

	public void setFilters_table(String filters_table) {
		this.filters_table = filters_table;
	}

	public String getFilter_label_name() {
		return filter_label_name;
	}

	public void setFilter_label_name(String filter_label_name) {
		this.filter_label_name = filter_label_name;
	}

	public String getFilter_column_name() {
		return filter_column_name;
	}

	public void setFilter_column_name(String filter_column_name) {
		this.filter_column_name = filter_column_name;
	}

	public List<OverviewDashboard> getFormsSubMenu() {
		return formsSubMenu;
	}

	public void setFormsSubMenu(List<OverviewDashboard> formsSubMenu) {
		this.formsSubMenu = formsSubMenu;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSource_table_name() {
		return source_table_name;
	}

	public void setSource_table_name(String source_table_name) {
		this.source_table_name = source_table_name;
	}

	public String getSource_field_name() {
		return source_field_name;
	}

	public void setSource_field_name(String source_field_name) {
		this.source_field_name = source_field_name;
	}

	public String getSource_field_value() {
		return source_field_value;
	}

	public void setSource_field_value(String source_field_value) {
		this.source_field_value = source_field_value;
	}

	public int getWork_exists_or_not() {
		return work_exists_or_not;
	}

	public void setWork_exists_or_not(int work_exists_or_not) {
		this.work_exists_or_not = work_exists_or_not;
	}

	public List<OverviewDashboard> getFilter() {
		return filter;
	}

	public void setFilter(List<OverviewDashboard> filter) {
		this.filter = filter;
	}

	public String getFilter_option_id() {
		return filter_option_id;
	}

	public void setFilter_option_id(String filter_option_id) {
		this.filter_option_id = filter_option_id;
	}

	public String getFilter_option_value() {
		return filter_option_value;
	}

	public void setFilter_option_value(String filter_option_value) {
		this.filter_option_value = filter_option_value;
	}

	public String getShow_left_menu() {
		return show_left_menu;
	}

	public void setShow_left_menu(String show_left_menu) {
		this.show_left_menu = show_left_menu;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getQuery_for_work_search() {
		return query_for_work_search;
	}

	public void setQuery_for_work_search(String query_for_work_search) {
		this.query_for_work_search = query_for_work_search;
	}

	public String getQuery_for_filter_options() {
		return query_for_filter_options;
	}

	public void setQuery_for_filter_options(String query_for_filter_options) {
		this.query_for_filter_options = query_for_filter_options;
	}

	public String getSource_table_alias_name() {
		return source_table_alias_name;
	}

	public void setSource_table_alias_name(String source_table_alias_name) {
		this.source_table_alias_name = source_table_alias_name;
	}

	public String getFilters_table_alias_name() {
		return filters_table_alias_name;
	}

	public void setFilters_table_alias_name(String filters_table_alias_name) {
		this.filters_table_alias_name = filters_table_alias_name;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getIs_first_option_selected() {
		return is_first_option_selected;
	}

	public void setIs_first_option_selected(String is_first_option_selected) {
		this.is_first_option_selected = is_first_option_selected;
	}

	public String getUnion_all() {
		return union_all;
	}

	public void setUnion_all(String union_all) {
		this.union_all = union_all;
	}

	public String getUser_type_fk() {
		return user_type_fk;
	}

	public void setUser_type_fk(String user_type_fk) {
		this.user_type_fk = user_type_fk;
	}

	public String getUser_role_name_fk() {
		return user_role_name_fk;
	}

	public void setUser_role_name_fk(String user_role_name_fk) {
		this.user_role_name_fk = user_role_name_fk;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAccessibility() {
		return accessibility;
	}

	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}
	
}
