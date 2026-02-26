package com.synergizglobal.wrpmis.model;

import java.util.List;

public class TAFinancialsPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<TAFinancials> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<TAFinancials> getAaData() {
		return aaData;
	}
	public void setAaData(List<TAFinancials> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
