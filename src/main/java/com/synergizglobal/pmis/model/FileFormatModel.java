package com.synergizglobal.pmis.model;

import java.util.ArrayList;
import java.util.List;

public class FileFormatModel {
	
	/**
	 * This method will get the P6FileFormat
	 * @return type of this method fileformat
	 */
	public static List<String> getP6FileFormat() {
		List<String> fileFormat = new ArrayList<String>();
		fileFormat.add("Activity ID");
		fileFormat.add("Activity Status");
		fileFormat.add("WBS Code");
		//fileFormat.add("(*)BL Project Start");
		//fileFormat.add("(*)BL Project Finish");
		fileFormat.add("(*)Early Start");
		fileFormat.add("(*)Early Finish");
		fileFormat.add("Actual Start");
		fileFormat.add("Actual Finish");
		//fileFormat.add("Activity % Complete(%)");
		fileFormat.add("(*)Total Float");
		//fileFormat.add("BOQ Completed");
		fileFormat.add("Delete This Row");
		return fileFormat;
	}
	
	/**
	 * This method will get the risk upload template
	 * @return type of this method is fileFormat
	 */
	public static List<String> getRisksUploadTemplateFormat() {
		List<String> fileFormat = new ArrayList<String>();
		fileFormat.add("Work ID");
		fileFormat.add("Risk ID");
		fileFormat.add("Owner");
		fileFormat.add("Description");
		fileFormat.add("Area");
		fileFormat.add("Sub-Area");
		fileFormat.add("Probability");
		fileFormat.add("Impact");
		fileFormat.add("Category");
		fileFormat.add("Priority");
		fileFormat.add("Date of Identification");
		fileFormat.add("Responsible Person");
		fileFormat.add("Mitigation Plan");
		fileFormat.add("Action Taken/Remarks");
		fileFormat.add("Due Date");
		fileFormat.add("Mitigated Date");
		fileFormat.add("Status");
		return fileFormat;
	}
	
}
