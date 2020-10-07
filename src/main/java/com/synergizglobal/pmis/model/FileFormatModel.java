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
}
