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
		String columns = "Contract ID,	Department,	HOD,	Dy HOD,	Prepared By,	Consultant Contract ID,	Proof Consultant Contract ID,	Structure,	Component,	Drawing Type,	Contractor Drawing No,	MRVC Drawing No,	Division Drawing No,	HQ Drawing No,	Drawing Title,	Planned Start,	Planned Finish,	Revision,	Consultant Submission,	MRVC Reviewed,	Divisional Submission,	Divisional Approval,	HQ Submission,	HQ Approval,	GFC Released,	As Built Status,	As Built Date,	Remarks";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}

		return fileFormat;
	}
	
	public static List<String> getP6WbsFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "Contract ID,	FOB ID,	  WBS Code,	 WBS Name,	 Parent WBS Code,	 WBS Category";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}

		return fileFormat;
	}
	public static List<String> getP6ActivitiesFileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		String columns = "	WBS Code,	Task Code,	Activity Name,	Activity Status,	Baseline Start,	Baseline Finish,    Start,	 Finish,	Float	";
		String[] convertedColumnsArray = columns.split(",");
		for (String column : convertedColumnsArray) {
			fileFormat.add(column.trim());
		}

		return fileFormat;
	}
}
