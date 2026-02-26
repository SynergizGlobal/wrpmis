package com.synergizglobal.wrpmis.model;

import java.util.List;

public class ExpenditurePaginationObject {
		private int iTotalDisplayRecords; 
		private int iTotalRecords;
		private List<Expenditure> aaData;
		
		public int getiTotalRecords() {
			return iTotalRecords;
		}
		public void setiTotalRecords(int iTotalRecords) {
			this.iTotalRecords = iTotalRecords;
		}
		public List<Expenditure> getAaData() {
			return aaData;
		}
		public void setAaData(List<Expenditure> aaData) {
			this.aaData = aaData;
		}
		public int getiTotalDisplayRecords() {
			return iTotalDisplayRecords;
		}
		public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
			this.iTotalDisplayRecords = iTotalDisplayRecords;
		}
}
