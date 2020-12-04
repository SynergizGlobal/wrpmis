package com.synergizglobal.pmis.model;

import java.util.ArrayList;
import java.util.List;

public class FileFormatModel {
	public static List<String> getUserFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		fileFormat.add("Name");
		fileFormat.add("Email ID");
		fileFormat.add("Department");
		fileFormat.add("Designation");
		fileFormat.add("Reporting To");
		fileFormat.add("User Role");
		fileFormat.add("Mobile Number");
		fileFormat.add("Landline");
		fileFormat.add("Extension");
		fileFormat.add("Remarks");
		return fileFormat;
	}
	
	public static List<String> getDesignFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Work ID, Contract ID,Department,HOD,Dy HOD,Prepared By,Consultant,Proof Consultant ID ,Submitted to Proof Consultant,approval by Proof Consultant,Structure,Structure ID,Drawing Type,Contractor Drawing No,MRVC Drawing No,Division Drawing No,HQ Drawing No,Drawing Title,Planned Start,Planned Finish,Revision, Consultant Submission,MRVC Reviewed/ approval,Submission to Division,Submitted to Division ,Divisional Approval,Submission to HQ,Submitted to HQ,HQ Approval,GFC Released,As Built Drawing Status,As Built Drawing Date,Remarks";
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
	
	public static List<String> getStripChartRefetenceData_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Strip Chart Type, ,Component ID,Order,Latitude,Longitude";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}
	
	public static List<String> getStripChartContractStructure_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Contract ID,Structure,Structure Name";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}
	
	public static List<String> getStripChartData_FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Contract ID,Structure,Component,Component ID,Activity,Line,Planned Start Date,Planned Finish Date,Actual Start Date,Actual Finish Date,Unit,Total Scope,Completed,Weightage Point,Components Detail,Section,Remarks,Strip Chart ID";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}

	public static List<String> getRiskFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Work ID,Risk ID,Owner,Area,Sub-Area,Date of Assessment,Probability (A),Impact (B),Mitigation Plan,Priority,Responsible Person";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}		
		return fileFormat;
	}
}
