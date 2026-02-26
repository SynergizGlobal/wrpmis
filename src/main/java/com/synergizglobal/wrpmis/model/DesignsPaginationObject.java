package com.synergizglobal.wrpmis.model;

import java.util.List;

public class DesignsPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Design> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Design> getAaData() {
		return aaData;
	}
	public void setAaData(List<Design> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
