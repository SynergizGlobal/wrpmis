package com.synergizglobal.pmis.model;

import java.util.List;

public class SafetyPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Safety> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Safety> getAaData() {
		return aaData;
	}
	public void setAaData(List<Safety> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
