package com.synergizglobal.wrpmis.model;

import java.util.List;

public class DocumentsPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Document> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Document> getAaData() {
		return aaData;
	}
	public void setAaData(List<Document> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
