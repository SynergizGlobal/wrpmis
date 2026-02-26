package com.synergizglobal.wrpmis.model;

import java.util.List;

public class ProjectPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Project> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Project> getAaData() {
		return aaData;
	}
	public void setAaData(List<Project> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
