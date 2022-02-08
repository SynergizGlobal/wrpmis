package com.synergizglobal.pmis.model;

import java.util.List;

public class RRBsesPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<RRBses> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<RRBses> getAaData() {
		return aaData;
	}
	public void setAaData(List<RRBses> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}

