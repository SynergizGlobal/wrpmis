package com.synergizglobal.wrpmis.model;

import java.util.List;

public class LandAquisationPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<LandAcquisition> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<LandAcquisition> getAaData() {
		return aaData;
	}
	public void setAaData(List<LandAcquisition> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
