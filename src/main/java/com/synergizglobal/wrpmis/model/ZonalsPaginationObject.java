package com.synergizglobal.wrpmis.model;
import java.util.List;

public class ZonalsPaginationObject {
	private int iTotalDisplayRecords; 
	private int iTotalRecords;
	private List<ZonalRailway> aaData;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public List<ZonalRailway> getAaData() {
		return aaData;
	}
	public void setAaData(List<ZonalRailway> aaData) {
		this.aaData = aaData;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
}
