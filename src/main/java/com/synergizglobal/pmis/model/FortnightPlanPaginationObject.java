package com.synergizglobal.pmis.model;

import java.util.List;

public class FortnightPlanPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<FortnightPlan> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<FortnightPlan> getAaData() {
		return aaData;
	}
	public void setAaData(List<FortnightPlan> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
