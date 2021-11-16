package com.synergizglobal.pmis.model;

import java.util.List;

public class StructurePaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Structure> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Structure> getAaData() {
		return aaData;
	}
	public void setAaData(List<Structure> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
