package com.synergizglobal.wrpmis.model;

import java.util.List;

public class DeliverablesPaginationObject {

	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Deliverables> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Deliverables> getAaData() {
		return aaData;
	}
	public void setAaData(List<Deliverables> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
