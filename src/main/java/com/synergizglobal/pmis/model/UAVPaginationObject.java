package com.synergizglobal.pmis.model;

import java.util.List;

public class UAVPaginationObject {
		private int iTotalDisplayRecords; 
		private int iTotalRecords;
		private List<UAV> aaData;
		
		public int getiTotalRecords() {
			return iTotalRecords;
		}
		public void setiTotalRecords(int iTotalRecords) {
			this.iTotalRecords = iTotalRecords;
		}
		public List<UAV> getAaData() {
			return aaData;
		}
		public void setAaData(List<UAV> aaData) {
			this.aaData = aaData;
		}
		public int getiTotalDisplayRecords() {
			return iTotalDisplayRecords;
		}
		public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}
}
