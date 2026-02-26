package com.synergizglobal.wrpmis.model;

import java.util.List;

public class DataGatheringPaginationObject {

	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<DataGathering> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<DataGathering> getAaData() {
		return aaData;
	}
	public void setAaData(List<DataGathering> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
