package com.synergizglobal.pmis.model;

import java.util.List;

public class ContractPaginationObject {

	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Contract> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Contract> getAaData() {
		return aaData;
	}
	public void setAaData(List<Contract> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
