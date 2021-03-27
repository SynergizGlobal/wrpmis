package com.synergizglobal.pmis.model;

import java.util.List;

public class WorkPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Work> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Work> getAaData() {
		return aaData;
	}
	public void setAaData(List<Work> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
