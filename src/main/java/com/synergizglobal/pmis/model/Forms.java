package com.synergizglobal.pmis.model;

import java.util.List;

public class Forms {
	private String formId,formName,webFormUrl,mobileFormUrl,priority,statusId,parentId,parentName;
	
	private List<Forms> formsSubMenu;
	
	/**
	 * This method get the form id
	 * @return type of this method is formId
	 */
	public String getFormId() {
		return formId;
	}
	
	/**
	 * This method set the form id
	 * @param formId it is string type variable that hold the form id
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	/** 
	 * This method will get the form name
	 * @return type of this method is formName
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * This method will set the form name
	 * @param formName it is string type variable that hold the form name
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	/** 
	 * This method get the web form url
	 * @return type of this method is webFormUrl
	 */
	public String getWebFormUrl() {
		return webFormUrl;
	}
	/**
	 * This method will set the web form url
	 * @param webFormUrl it is string type variable that hold the web form url
	 */
	public void setWebFormUrl(String webFormUrl) {
		this.webFormUrl = webFormUrl;
	}
	/**
	 * This method will get the mobile form url
	 * @return type of this method is mobileFormUrl
	 */
	public String getMobileFormUrl() {
		return mobileFormUrl;
	}
	
	/**
	 * This method will set the mobile form url
	 * @param mobileFormUrl it is string type variable that hold the mobile form url
	 */
	public void setMobileFormUrl(String mobileFormUrl) {
		this.mobileFormUrl = mobileFormUrl;
	}
	
	/**
	 * This method will get the priority
	 * @return type of this method will priority
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
	/**
	 * This method will get the status id
	 * @return type of this method is statusId
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * This method will set the status id
	 * @param statusId it is string type variable that hold the status id
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * This method will get the parent id
	 * @return type of this parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * This method will set the parent id
	 * @param parentId it is string type variable that hold the parent id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/** 
	 * This method will get the parent name
	 * @return type of this method is parentName
	 */
	public String getParentName() {
		return parentName;
	}
	/**
	 * This method will set the parent name
	 * @param parentName it is string type variable that hold the parent name
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	/**
	 * This method will get the forms sub menu
	 * @return type of this method is formsSubMenu
	 */
	public List<Forms> getFormsSubMenu() {
		return formsSubMenu;
	}
	/**
	 * This method will set the Forms sub menu
	 * @param formsSubMenu it is list type object that hold the forms sub menu
	 */
	public void setFormsSubMenu(List<Forms> formsSubMenu) {
		this.formsSubMenu = formsSubMenu;
	}
	
}
