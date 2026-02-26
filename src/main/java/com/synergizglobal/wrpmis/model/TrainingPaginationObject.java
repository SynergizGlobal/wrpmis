package com.synergizglobal.wrpmis.model;

import java.util.List;

public class TrainingPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Training> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Training> getAaData() {
		return aaData;
	}
	public void setAaData(List<Training> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
