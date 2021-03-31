package com.synergizglobal.pmis.model;

import java.util.List;

public class AlertsPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Alerts> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Alerts> getAaData() {
		return aaData;
	}
	public void setAaData(List<Alerts> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
