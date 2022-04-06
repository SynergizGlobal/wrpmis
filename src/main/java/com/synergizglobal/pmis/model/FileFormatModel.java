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
		String columns = "Work ID,Contract ID,Approving Railway,Department,HOD,Dy HOD,Structure Type,Structure ID,Prepared By,Consultant  Contract ID,"
				+ "Proof Consultant Contract ID,Drawing Type,Drawing Title,Approval Authority,Required Date,Contractor Drawing No,MRVC Drawing No,Division Drawing No,"
				+ "HQ Drawing No,Stage,Submitted By,Submitted To,Purpose of Submission,Submission Date,GFC Released,Remarks" + 
				"";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	public static List<String> getLAFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Work ID,LA_ID,Survey Number,Type of Land,Sub Category of Land,Village ID,Area,Area to be Acquired,Area Acquired,Land Status" + 
				",Chainage From,Chainage To,Village,"
				+ "Taluka,Dy SLR,SDO,Collector,Proposal submission Date to collector,JM Fee Letter received Date,JM Fee Amount,JM Fee Paid Date,"
				+ "JM Start Date,JM Completion Date,JM Sheet Date to SDO,JM Remarks,JM Approval,Special Feature,Remarks,Issues";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	public static List<String> getRRFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Work,R&R Id,Id No,Map S.No,Phase,Structure,Location,Sub Location,Type of Use,Carpet Area (sft),Year of Construction,Owner Name,"
				+ "Occupier Name,Document Type,Document No,Physical Verification Date,Verification By,Approval By Committee,Approval by MRVC,Estimate Approval,"
				+ "Estimation Amount,Letter to MMRDA,Estimate by MMRDA,Payment to MMRDA,Alternate Housing Allotment,Relocation,Encroachment Removal,Boundary Wall Status,"
				+ "Boundary Wall Doc,Handover to Execution,Remarks";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}
		return fileFormat;
	}
	
	
	public static List<String> getP6WbsFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		//String columns = "Contract ID,FOB ID,WBS Code, WBS Name, Parent WBS Code, WBS Category";
		String columns = "Contract ID,WBS Code, WBS Name, Parent WBS Code, WBS Category";
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
	
	public static List<String> getActivityRefetenceDataStructure_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Structure ID^Order";
		String[] convertedColumnsArray = columns.split("\\^");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}

	public static List<String> getActivityRefetenceDataComponent_FileFormat() {
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
	
	public static List<String> getActivityData_new_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Section^From Structure ID^To Structure ID^Line^Structure Type^Structure ID^Component^Component ID^Activity^Unit^Total Scope^Completed^Weightage Point^Planned Start Date^Planned Finish Date^Actual Start Date^Actual Finish Date^Components Detail^Remarks";
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
		String columns = "Contract ID,Contractor Name,Ledger Account,Date,Voucher Type,Voucher No.,Narration,Net Paid,Gross Work Done,SD Payable,"
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
