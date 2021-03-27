package com.synergizglobal.pmis.model;

import java.util.List;

public class FOBPaginationObject {

	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<FOB> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<FOB> getAaData() {
		return aaData;
	}
	public void setAaData(List<FOB> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
