package com.synergizglobal.wrpmis.model;

import java.util.List;

public class BudgetPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<Budget> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<Budget> getAaData() {
		return aaData;
	}
	public void setAaData(List<Budget> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
