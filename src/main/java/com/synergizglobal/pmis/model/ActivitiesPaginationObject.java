package com.synergizglobal.pmis.model;

import java.util.List;

public class ActivitiesPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<StripChart> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<StripChart> getAaData() {
		return aaData;
	}
	public void setAaData(List<StripChart> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
}
