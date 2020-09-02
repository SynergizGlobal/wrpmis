package com.synergizglobal.pmis.model;

import java.util.List;

public class TableauDashboard {

	private List<TableauDashboard> tableauSubList;
	private String tableauUrl,tableauDashboardId,tableauDashboardName,imagePath,priority,tableauTrustedToken;
	
	/**
	 * This method will get the tableau sub list
	 * @return type of this method is tableauSubList it is list type
	 */
	public List<TableauDashboard> getTableauSubList() {
		return tableauSubList;
	}
	/**
	 * This method will set the tableau sub list
	 * @param tableauSubList it is List type object that hold the tableau sub list
	 */
	public void setTableauSubList(List<TableauDashboard> tableauSubList) {
		this.tableauSubList = tableauSubList;
	}
	
	/**
	 * This method will get the tableau dashboard id
	 * @return type of this method is tableauDashboardId
	 */
	public String getTableauDashboardId() {
		return tableauDashboardId;
	}
	
	/**
	 * This method will set the tableau dashboard id
	 * @param tableauDashboardId it is string type variable that hold the tableau dashboard id
	 */
	public void setTableauDashboardId(String tableauDashboardId) {
		this.tableauDashboardId = tableauDashboardId;
	}

	/**
	 * This method will get the tableau dashboard name
	 * @return type of this method will tableauDashboardName
	 */
	public String getTableauDashboardName() {
		return tableauDashboardName;
	}
	
	/**
	 * This method will set the tableau dashboard name
	 * @param tableauDashboardName it is string type variable that hold the tableau dashboard name
	 */
	public void setTableauDashboardName(String tableauDashboardName) {
		this.tableauDashboardName = tableauDashboardName;
	}
	
	/** 
	 * This method will get the image path
	 * @return type of this method is imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/**
	 * This method will set the image path
	 * @param imagePath it is string type variable that hold the image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/**
	 * This method will get the tableau url
	 * @return type of this method is tableauUrl
	 */
	public String getTableauUrl() {
		return tableauUrl;
	}
	
	/** 
	 * This method will set the tableau url
	 * @param tableauUrl it is string type variable that hold the tableau url
	 */
	public void setTableauUrl(String tableauUrl) {
		this.tableauUrl = tableauUrl;
	}
	
	/**
	 * This method will get the priority
	 * @return type of this method is priority
	 */
	public String getPriority() {
		return priority;
	}
	
	/**
	 * This method will set the priority
	 * @param priority it is string type variable that hold the priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTableauTrustedToken() {
		return tableauTrustedToken;
	}
	public void setTableauTrustedToken(String tableauTrustedToken) {
		this.tableauTrustedToken = tableauTrustedToken;
	}
	
}
