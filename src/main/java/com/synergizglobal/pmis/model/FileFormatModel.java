package com.synergizglobal.pmis.model;

import java.util.ArrayList;
import java.util.List;

public class FileFormatModel {
	public static List<String> getUserFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "User ID, User Name,Designation,Department,Reporting to,Email-Id,Mobile Number,User Type,Role";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	public static List<String> getDesignFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Work ID, Contract ID,Department,HOD,Dy HOD,Prepared By,Consultant,Proof Consultant ID ,Submitted to Proof Consultant,approval by Proof Consultant,Structure,Structure ID,Drawing Type,Contractor Drawing No,MRVC Drawing No,Division Drawing No,HQ Drawing No,Drawing Title,Planned Start,Planned Finish,Revision,Clearance to Consultant , Consultant Submission,MRVC Reviewed/ approval,Submission to Division,Submitted to Division,Query Raised By Division,Query Replyied to  Division,Divisional Approval,Submission to HQ,Submitted to HQ,Query Raised By HQ ,Query Replyied to  HQ ,HQ Approval,GFC Released,CRS Sanction Required(Yes/No),Submitted for CRS Sanction ,Query Raised for CRS  Sanction,Query Replyied for   CRS Sanction, CRS Sanction Approved ,As Built Drawing Status,As Built Drawing Date,Remarks";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	public static List<String> getP6WbsFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Contract ID,FOB ID,WBS Code, WBS Name, Parent WBS Code, WBS Category";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	public static List<String> getP6ActivitiesFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "WBS Code,Task Code,Activity Name,Activity Status,Baseline Start,Baseline Finish,Start, Finish,Float";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	public static List<String> getP6UpdateFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "WBS Code,Task Code,Activity Name,Activity Status,Start, Finish,Float";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	public static List<String> getActivityRefetenceData_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Component ID^Order";
		String[] convertedColumnsArray = columns.split("\\^");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}
	
	public static List<String> getActivityData_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Section^Line^Structure^Component^Component ID^Activity^Planned Start Date^Planned Finish Date^Actual Start Date^Actual Finish Date^Unit^Total Scope^Completed^Weightage Point^Components Detail^Remarks";
		String[] convertedColumnsArray = columns.split("\\^");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}

	public static List<String> getRiskFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		/*String columns = "Work ID^Item No.^Risk ID^Owner^Area^Sub-Area^Date of Assessment^Probability (A)^Impact (B)^RISK RATING" + 
				"^RISK CLASSIFICATION^Mitigation Plan^Priority^Responsible Person^ATR Date ^Action Taken^Status";*/
		
		/*String columns = "Sub Work^Item No.^Owner^Area^Sub-Area^Date of Assessment^Probability (A)^Impact (B)^RISK RATING" + 
				"^RISK CLASSIFICATION^Status^Priority^Mitigation Plan^Responsible Person^ATR Date^Action Taken^Code";*/
		
		String columns = "Work^Owner^Item No.^Risk Area^Sub-Area^Date of Assessment^Probability (A)^Impact \r\n" + 
				"(B)^Risk Rating^Risk Classification^Risk Status^Priority of Open Risks^Mitigation Plan for Priority Risks^Action By";
        
        String[] convertedColumnsArray = columns.split("\\^");
        
		//String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}

	public static List<String> getTrainingFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Training ID,Training Type,Category,Faculty Name,Designation,Title,Description,Training Center,Status, Remark";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}
	
	public static List<String> getExpenditureFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Project ID,Work ID,Contract ID,Contractor Name,Ledger Account,Date,Voucher Type,Voucher No.,Narration,Net Paid,Gross Work Done,SD Payable,"
				+ "Contractor Income Tax,CGST TDS,SGST TDS,IGST TDS,Mobilization Advance,Interest on Mobilization Advance,VAT WCT"
				+ ",Amount Withheld,Remarks";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}

	public static List<String> getStructureFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Work,Contract,Department,Structure Type,Structure";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
}
