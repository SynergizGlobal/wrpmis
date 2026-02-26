package com.synergizglobal.wrpmis.model;

import java.util.List;

public class SourceOfFundPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<SourceOfFund> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<SourceOfFund> getAaData() {
		return aaData;
	}
	public void setAaData(List<SourceOfFund> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
