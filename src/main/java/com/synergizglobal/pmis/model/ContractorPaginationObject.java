package com.synergizglobal.pmis.model;

import java.util.List;

public class ContractorPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Contractor> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Contractor> getAaData() {
		return aaData;
	}
	public void setAaData(List<Contractor> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
