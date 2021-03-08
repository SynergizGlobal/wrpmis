package com.synergizglobal.pmis.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.CTVerticalJc;
import org.docx4j.wml.Color;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase.Spacing;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STHint;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.TcBorders;
import org.docx4j.wml.TcPrInner.VMerge;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.RiskReport;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.Training;


public class DocxTableCreation {	
	public static void createTableForRiskAnalysisReport(WordprocessingMLPackage wordMLPackage,
			MainDocumentPart t, ObjectFactory factory,RiskReport obj, List<RiskReport> prioritizationOfRisks, List<RiskReport> reductionPlanRisks) throws Exception {
		RPr titleRpr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "ralewaymedium", "000000", "18",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "ralewaymedium", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);
		
		addHeading(wordMLPackage, t, factory,JcEnumeration.RIGHT,fontRPr,"Annexure-I");
		
		Tbl table = factory.createTbl();
		addBorders(table, "2");
		
		/****************************************************************************/
		
		if(!StringUtils.isEmpty(obj)) {
			
			Tbl reportTable = factory.createTbl();
			addBorders(reportTable, "2");
			/******************************* Headers *********************************************/
			Tr titleRow1 = factory.createTr();		
			List<String> tableHeader1 = new ArrayList<String>();
		  	//tableHeader1.add("Name of Project: "+obj.getWork_short_name()+"("+obj.getProject_name()+")\nHOD - "+obj.getOwner()+"\nDate of Risk Assessment - "+obj.getAssessment_date());
			//tableHeader1.add("Name of Project: "+obj.getWork_short_name()+"("+obj.getProject_name()+")\nHOD - "+obj.getOwner()
			tableHeader1.add("Name of Project: "+obj.getSub_work()+"\nHOD - "+obj.getOwner()
			+"\nSanction Details: "
			+ "\nEstimated/Revised Cost : "+obj.getEstimatedOrRevisedCost() + " Cr"
			+ "\nEstimated/Revised Sanction Year : "+obj.getEstimatedOrRevisedDate()
			);
			tableHeader1.add("");
		  	tableHeader1.add("");
		  	tableHeader1.add("");
		  	tableHeader1.add("");
		  	tableHeader1.add("");
			
			for (String headerValue : tableHeader1) {
				addTableCell(factory, wordMLPackage, titleRow1, headerValue, titleRpr,
						JcEnumeration.LEFT, true, "ecf2ff");
			}		
			reportTable.getContent().add(titleRow1);
			
			mergeCellsHorizontal(reportTable, 0, 0, 5);
			
			/****************************************************************************/
			
			/****************************************************************************/
			Tr titleRow2 = factory.createTr();		
			List<String> tableHeader2 = new ArrayList<String>();
		  	tableHeader2.add("RISK ANALYSIS               " + "Date of assessment: "+obj.getAssessment_date() );
		  	tableHeader2.add("");
		  	tableHeader2.add("");
		  	tableHeader2.add("");
		  	tableHeader2.add("");
		  	tableHeader2.add("");
			
			for (String headerValue : tableHeader2) {
				addTableCell(factory, wordMLPackage, titleRow2, headerValue, titleRpr,
						JcEnumeration.RIGHT, true, "ecf2ff");
			}		
			reportTable.getContent().add(titleRow2);	
			mergeCellsHorizontal(reportTable, 1, 0, 5);
			
			/****************************************************************************/
			
			Tr titleRow3 = factory.createTr();		
			List<String> tableHeader3 = new ArrayList<String>();
			tableHeader3.add("Item No.");
			tableHeader3.add("Risk Identified");
			tableHeader3.add("Probability of Occurrence of Identified Risk \n (A)");
			tableHeader3.add("Likely Impact on Cost/Time Over Run \n (B)");
			tableHeader3.add("Risk Rating \n (A x B)");
			tableHeader3.add("Priority");
			
			for (String headerValue : tableHeader3) {
				addTableCell(factory, wordMLPackage, titleRow3, headerValue, titleRpr,
						JcEnumeration.CENTER, true, "ecf2ff");
			}		
			reportTable.getContent().add(titleRow3);
			
			for (RiskReport area : obj.getAreaList()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				addTableCell(factory, wordMLPackage, contentRow, area.getArea_item_no(),
						contentRprParent, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, area.getArea(),
						contentRprParent, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, "",
						contentRprParent, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, "",
						contentRprParent, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, "",
						contentRprParent, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, "",
						contentRprParent, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				
				reportTable.getContent().add(contentRow);
				
				for (RiskReport subArea : area.getSubAreaList()) {
					hasBgColor = false;
					backgroundColor = null;
					contentRow = factory.createTr();	
					addTableCell(factory, wordMLPackage, contentRow, subArea.getArea_item_no()+"."+subArea.getSub_area_item_no(),
							contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, subArea.getSub_area(),
							contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, subArea.getProbability(),
							contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, subArea.getImpact(),
							contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, subArea.getRisk_rating(),
							contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, subArea.getPriority(),
							contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
					
					reportTable.getContent().add(contentRow);
				}				
				
			}
			
			setTableAlign(factory, reportTable, JcEnumeration.CENTER);
			t.addObject(reportTable);
			
		}	
		
		
		/****************************************************************************/
		
		if(!StringUtils.isEmpty(prioritizationOfRisks)) {			
			/*P p = factory.createP();
			R r = factory.createR();		        
			Br br = factory.createBr(); 
			r.getContent().add( br); 
			p.getContent().add(r);
			
			t.addObject(p);*/
	        
	        addPageBreak(t);
	        addHeading(wordMLPackage, t, factory,JcEnumeration.RIGHT,fontRPr,"Annexure-II");
	        addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"PRIORITIZATION OF RISKS & ITS MITIGATION/REDUCTION PLAN");
	        //addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"Name of Project: "+obj.getWork_short_name()+"("+obj.getProject_name()+")");
	        addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"Name of Project: "+obj.getWork_short_name());
	        addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"Date of Risk Assessment : "+obj.getAssessment_date());
			
			Tbl reportTable = factory.createTbl();
			addBorders(reportTable, "2");
			
			
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("PRIORITY\n" + 
					"NO.");
			tableHeader.add("ITEM NO(of Ann-I)");
			tableHeader.add("RISK\n" + 
					"RATING");
			tableHeader.add("RISK\n" + 
					"CLASSIFICATION");
			tableHeader.add("MITIGATION/REDUCTION\n" + 
					"PLAN");			
			tableHeader.add("ACTION BY");
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.CENTER, true, "ecf2ff");
			}		
			reportTable.getContent().add(titleRow);
			
			for (RiskReport pObj : prioritizationOfRisks) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				addTableCell(factory, wordMLPackage, contentRow, pObj.getPriority(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getArea_item_no()+"."+pObj.getSub_area_item_no(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getRisk_rating(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getClassification(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getMitigation_plan(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getResponsible_person(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				
				reportTable.getContent().add(contentRow);
			}			
			/****************************************************************************************/			
			
			setTableAlign(factory, reportTable, JcEnumeration.CENTER);
			t.addObject(reportTable);
			
		}
		
/****************************************************************************/
		
		if(!StringUtils.isEmpty(reductionPlanRisks)) {			
			/*P p = factory.createP();
			R r = factory.createR();		        
			Br br = factory.createBr(); 
			r.getContent().add( br); 
			p.getContent().add(r);
			
			t.addObject(p);*/
			
			addPageBreak(t);
			addHeading(wordMLPackage, t, factory,JcEnumeration.RIGHT,fontRPr,"Annexure-III");
			addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"ATR ON MITIGATION/REDUCTION PLAN");
			//addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"Name of Project: "+obj.getWork_short_name()+"("+obj.getProject_name()+")");
			addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"Name of Project: "+obj.getWork_short_name());
			addHeading(wordMLPackage, t, factory,JcEnumeration.CENTER,titleRPr,"Date of Risk Assessment : "+obj.getAssessment_date());
			
			Tbl reportTable = factory.createTbl();
			addBorders(reportTable, "2");
			
			
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("PRIORITY\n" + 
					"NO.");
			tableHeader.add("ITEM NO(of Ann-I)");
			tableHeader.add("RISK\n" + 
					"RATING");
			tableHeader.add("RISK\n" + 
					"CLASSIFICATION");
			tableHeader.add("MITIGATION/REDUCTION\n" + 
					"PLAN");			
			tableHeader.add("ACTION TAKEN");
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.CENTER, true, "ecf2ff");
			}		
			reportTable.getContent().add(titleRow);
			
			for (RiskReport pObj : reductionPlanRisks) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				addTableCell(factory, wordMLPackage, contentRow, pObj.getPriority(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getArea_item_no()+"."+pObj.getSub_area_item_no(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getRisk_rating(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getClassification(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getMitigation_plan(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getAction_taken(),
						contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
				
				reportTable.getContent().add(contentRow);
			}			
			/****************************************************************************************/			
			
			setTableAlign(factory, reportTable, JcEnumeration.CENTER);
			t.addObject(reportTable);
			
		}
		
		
	}
	
	
	/***************************** ISSUES REPORT ****************************************************************/
	
	public static void createTableForPendingIssuesReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart t,
			ObjectFactory factory, List<Issue> pendingIssues) {
		RPr titleRpr = getRPr(factory, "ralewaymedium", "000000", "18", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "ralewaymedium", "000000", "14",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "ralewaymedium", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		Tbl table = factory.createTbl();
		addBorders(table, "2");
		
		/****************************************************************************/
		
		if(!StringUtils.isEmpty(pendingIssues)) {
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("S\nNo");
			tableHeader.add("Name of\nWork");
			tableHeader.add("HOD");
			tableHeader.add("Name of\ncontractor");
			tableHeader.add("Location\n/Station\n/KM");
			/*tableHeader.add("Issue Discription");*/
			tableHeader.add("Reported\nBy");
			tableHeader.add("Pending\nWith");
			/*tableHeader.add("Pending Since(Days)");*/
			tableHeader.add("Pending\nSince\nDate");
			/*tableHeader.add("Action Taken");*/
			tableHeader.add("Issue / Action Taken / Remarks"); 
			/*tableHeader.add("Current Status");*/
			tableHeader.add("Person\nResponsible\nin MRVC\n(Assigned to)");  
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.LEFT, true, "ecf2ff");
			}		
			table.getContent().add(titleRow);
			
			int sNo = 1;
			for (Issue pObj : pendingIssues) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getWork_id_fk()+" - "+pObj.getWork_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getDesignation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getContractor_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);	
				addTableCell(factory, wordMLPackage, contentRow, pObj.getLocation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);	
				/*addTableCell(factory, wordMLPackage, contentRow, pObj.getDescription(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);	*/		
				addTableCell(factory, wordMLPackage, contentRow, pObj.getReported_by(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);		
				addTableCell(factory, wordMLPackage, contentRow, pObj.getOther_organization(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
				/*addTableCell(factory, wordMLPackage, contentRow, pObj.getPending_since(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
				addTableCell(factory, wordMLPackage, contentRow, pObj.getDate(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
				addTableCell(factory, wordMLPackage, contentRow, pObj.getCorrective_measure(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				/*addTableCell(factory, wordMLPackage, contentRow, pObj.getStatus_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
				addTableCell(factory, wordMLPackage, contentRow, pObj.getResponsible_person(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				
				table.getContent().add(contentRow);
			}			
			/****************************************************************************************/			
			
			setTableAlign(factory, table, JcEnumeration.CENTER);
			t.addObject(table);
			
		}
	} 
	
	/***************************** Safety REPORT ****************************************************************/
	



	public static void createTableForSafetyReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart t,
			ObjectFactory factory, List<Safety> safetyData) {
		RPr titleRpr = getRPr(factory, "ralewaymedium", "000000", "18", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "ralewaymedium", "000000", "14",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "ralewaymedium", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		Tbl table = factory.createTbl();
		addBorders(table, "2");
		
		/****************************************************************************/
		
		if(!StringUtils.isEmpty(safetyData)) {
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("S\nNo");
			tableHeader.add("Name\nof\nWork");
			tableHeader.add("HOD");
			tableHeader.add("Name of\nContractor"); 
			tableHeader.add("Location\n/Station\n/KM.");
			tableHeader.add("Description");
			tableHeader.add("Date of\nIncident");
			tableHeader.add("Impact");			
			tableHeader.add("Category");			
			tableHeader.add("Root\nCause");
			tableHeader.add("Committee\n(Y/N)");			
			tableHeader.add("Incident Status");
			tableHeader.add("Short Term\nCorrective\nMeasure");			
			tableHeader.add("Long Term\nCorrective\nMeasure");
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.LEFT, true, "ecf2ff");
			}		
			table.getContent().add(titleRow);
			
			int sNo = 1;
			for (Safety pObj : safetyData) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getWork_id_fk()+" - "+pObj.getWork_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getDesignation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getContractor_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getLocation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getDescription(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getDate(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);				
				addTableCell(factory, wordMLPackage, contentRow, pObj.getImpact_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
				addTableCell(factory, wordMLPackage, contentRow, pObj.getCategory_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getRoot_cause_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);					
				addTableCell(factory, wordMLPackage, contentRow, pObj.getCommittee_formed_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getStatus_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);				
				addTableCell(factory, wordMLPackage, contentRow, pObj.getCorrective_measure_short_term(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, pObj.getCorrective_measure_long_term(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				
				table.getContent().add(contentRow);
			}			
			/****************************************************************************************/			
			
			setTableAlign(factory, table, JcEnumeration.CENTER);
			t.addObject(table);
			
		}
	}	 
	
	
	/************************* Training Reports *************************************************************************************/


	public static void createTableForScheduledTrainingReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Training> scheduledTrainings) {
		RPr titleRpr = getRPr(factory, "ralewaymedium", "000000", "18", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "ralewaymedium", "000000", "14",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "ralewaymedium", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		
		
		/****************************************************************************/
		
		if(!StringUtils.isEmpty(scheduledTrainings) && scheduledTrainings.size() > 0) {
			
			for (Training tObj : scheduledTrainings) {				
				
				for (Training sObj : tObj.getTrainingSessions()) {	
					
					Tbl titleTable = factory.createTbl();
					addBorders(titleTable, "0");
					
					/*===========================================================*/
					Tr titleTableRow = factory.createTr();		
					addTableCell(factory, wordMLPackage, titleTableRow, "Training ID", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_id(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Title", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTitle(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
					
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 0, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Session NO", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getSession_no(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Description", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getDescription(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
					
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 1, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Training Type", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_type_fk(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Date", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getDate(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
		
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 2, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Category", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_category_fk(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Start Time", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getStart_time(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
			
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 3, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Training Centre", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_center(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "End Time", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getEnd_time(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
				
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 4, 3, 4);
					/*===========================================================*/
					
					setTableAlign(factory, titleTable, JcEnumeration.CENTER);
					mp.addObject(titleTable);
					
					/****************************************************************/
					Tbl table = factory.createTbl();
					addBorders(table, "2");
					
					Tr titleRow = factory.createTr();		
					List<String> tableHeader = new ArrayList<String>();
					tableHeader.add("SNo.");
					tableHeader.add("Name of Attendee");
					tableHeader.add("Department");
					tableHeader.add("Mobile");
					tableHeader.add("Reporting to");
					
					for (String headerValue : tableHeader) {
						addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
								JcEnumeration.LEFT, true, "ecf2ff");
					}		
					table.getContent().add(titleRow);
					
					
					int sNo = 1;
					for (Training aObj : sObj.getTrainingAttendees()) {
						boolean hasBgColor = false;
						String backgroundColor = null;
						Tr contentRow = factory.createTr();	
						
						addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getAttendee(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getDepartment_name(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getMobile_no(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getReporting_to(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						
						table.getContent().add(contentRow);
					}
					
					if(StringUtils.isEmpty(sObj.getTrainingAttendees()) || sObj.getTrainingAttendees().isEmpty()) {
						boolean hasBgColor = false;
						String backgroundColor = null;
						Tr contentRow = factory.createTr();	
						
						List<String> noDataRow = new ArrayList<String>();
						noDataRow.add("NO ATTENDEES");
						noDataRow.add("");
						noDataRow.add("");
						noDataRow.add("");
						noDataRow.add("");
						
						for (String headerValue : noDataRow) {
							addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
									JcEnumeration.CENTER, hasBgColor, backgroundColor);
						}		
						table.getContent().add(contentRow);	
						mergeCellsHorizontal(table, 1, 0, 4);
					}
					
					setTableAlign(factory, table, JcEnumeration.CENTER);
					mp.addObject(table);
					
					addParagraph(mp,factory);
				}
				
			}			
			/****************************************************************************************/				
		} else {
			boolean hasBgColor = false;
			String backgroundColor = null;
			Tbl titleTable = factory.createTbl();
			addBorders(titleTable, "0");
			Tr contentRow = factory.createTr();	
			
			List<String> noDataRow = new ArrayList<String>();
			noDataRow.add("NO SCHEDULED TRAININGS");
			noDataRow.add("");
			noDataRow.add("");
			noDataRow.add("");
			noDataRow.add("");
			
			for (String headerValue : noDataRow) {
				addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
						JcEnumeration.CENTER, hasBgColor, backgroundColor);
			}		
			titleTable.getContent().add(contentRow);	
			mergeCellsHorizontal(titleTable, 0, 0, 4);
			
			setTableAlign(factory, titleTable, JcEnumeration.CENTER);
			mp.addObject(titleTable);
		}
	}	

	public static void createTableForEmployeeTrainingReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Training> employeeTrainings) {
		RPr titleRpr = getRPr(factory, "ralewaymedium", "000000", "18", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "ralewaymedium", "000000", "14",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "ralewaymedium", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		String employeeName = null,trainee_designation = null,department = null, reportingTo = null,nominated = null, attended = null;
		for (Training aObj : employeeTrainings) {
			employeeName = aObj.getAttendee();
			department = aObj.getDepartment_name();
			reportingTo = aObj.getReporting_to();
			nominated = aObj.getNominated();
			attended = aObj.getAttended();
			trainee_designation = aObj.getTrainee_designation();
			break;
		}
		
		
		/****************************************************************************/
				
					
		Tbl titleTable = factory.createTbl();
		addBorders(titleTable, "0");
		
		/*===========================================================*/
		Tr titleTableRow = factory.createTr();		
		addTableCell(factory, wordMLPackage, titleTableRow, "Name of Employee", titleRpr,
				JcEnumeration.LEFT, true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, employeeName, titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
				JcEnumeration.LEFT, false, null);
	  	
	  	addTableCell(factory, wordMLPackage, titleTableRow, "Designation", titleRpr,
				JcEnumeration.LEFT, true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, trainee_designation, titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
				JcEnumeration.LEFT, false, null);
		
	  	titleTable.getContent().add(titleTableRow);		
		mergeCellsHorizontal(titleTable, 0, 1, 2);
		mergeCellsHorizontal(titleTable, 0, 4, 5);	
		/*===========================================================*/
		titleTableRow = factory.createTr();	
		addTableCell(factory, wordMLPackage, titleTableRow, "Department", titleRpr,
				JcEnumeration.LEFT, true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, department, titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "Reporting To", titleRpr,
				JcEnumeration.LEFT, true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, reportingTo, titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
				JcEnumeration.LEFT, false, null);
		
		
	  	titleTable.getContent().add(titleTableRow);	
		mergeCellsHorizontal(titleTable, 1, 1, 2);
		mergeCellsHorizontal(titleTable, 1, 4, 5);
		/*===========================================================*/
		titleTableRow = factory.createTr();	
		addTableCell(factory, wordMLPackage, titleTableRow, "Nominated", titleRpr,
				JcEnumeration.LEFT, true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, nominated, titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
				JcEnumeration.LEFT, false, null);
		
		addTableCell(factory, wordMLPackage, titleTableRow, "Attended", titleRpr,
				JcEnumeration.LEFT,  true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, attended, titleRpr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
				JcEnumeration.LEFT, false, null);
		
	  	titleTable.getContent().add(titleTableRow);	
	  	mergeCellsHorizontal(titleTable, 2, 1, 2);
		mergeCellsHorizontal(titleTable, 2, 4, 5);
		/*===========================================================*/
		
		
		setTableAlign(factory, titleTable, JcEnumeration.CENTER);
		mp.addObject(titleTable);
		
		/****************************************************************/
		Tbl table = factory.createTbl();
		addBorders(table, "2");
		
		Tr titleRow = factory.createTr();		
		List<String> tableHeader = new ArrayList<String>();
		tableHeader.add("Training ID");
		tableHeader.add("Session NO");
		tableHeader.add("Title");
		tableHeader.add("Description");
		tableHeader.add("Training Centre");
		tableHeader.add("Training Date");
		tableHeader.add("Nominated");
		tableHeader.add("Attended");
		
		for (String headerValue : tableHeader) {
			addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
		}		
		table.getContent().add(titleRow);
		
		for (Training aObj : employeeTrainings) {
			boolean hasBgColor = false;
			String backgroundColor = null;
			Tr contentRow = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contentRow, aObj.getTraining_id_fk(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getSession_no(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getTitle(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getDescription(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getTraining_center(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getDate(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getRequired_fk(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, contentRow, aObj.getParticipated_fk(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			
			table.getContent().add(contentRow);
		}
		if(StringUtils.isEmpty(employeeTrainings) || employeeTrainings.isEmpty()) {
			boolean hasBgColor = false;
			String backgroundColor = null;
			Tr contentRow = factory.createTr();	
			
			List<String> noDataRow = new ArrayList<String>();
			noDataRow.add("NO TRAINING");
			noDataRow.add("");
			noDataRow.add("");
			noDataRow.add("");
			noDataRow.add("");
			
			for (String headerValue : noDataRow) {
				addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
						JcEnumeration.CENTER, hasBgColor, backgroundColor);
			}		
			table.getContent().add(contentRow);	
			mergeCellsHorizontal(table, 1, 0, 4);
		}
		
		setTableAlign(factory, table, JcEnumeration.CENTER);
		mp.addObject(table);
		
	}


	public static void createTableForCompletedTrainingReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Training> completedTrainings) {
		RPr titleRpr = getRPr(factory, "ralewaymedium", "000000", "18", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "ralewaymedium", "000000", "14",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "ralewaymedium", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		
		
		/****************************************************************************/
		
		if(!StringUtils.isEmpty(completedTrainings) && completedTrainings.size() > 0) {
			
			for (Training tObj : completedTrainings) {				
				
				for (Training sObj : tObj.getTrainingSessions()) {	
					
					Tbl titleTable = factory.createTbl();
					addBorders(titleTable, "0");
					
					/*===========================================================*/
					Tr titleTableRow = factory.createTr();		
					addTableCell(factory, wordMLPackage, titleTableRow, "Training ID", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_id(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Title", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTitle(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
					
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 0, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Session NO", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getSession_no(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Description", boldRPr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getDescription(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
					
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 1, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Training Type", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_type_fk(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Date", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getDate(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
		
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 2, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Category", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_category_fk(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Start Time", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getStart_time(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
			
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 3, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Training Centre", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, tObj.getTraining_center(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "End Time", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getEnd_time(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
				
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 4, 3, 4);
					/*===========================================================*/
					titleTableRow = factory.createTr();	
					
					addTableCell(factory, wordMLPackage, titleTableRow, "Nominated", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getNominated(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "Attended", titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, sObj.getAttended(), titleRpr,
							JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", titleRpr,
							JcEnumeration.LEFT, false, null);
				
				  	titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, 5, 3, 4);
					/*===========================================================*/
					
					setTableAlign(factory, titleTable, JcEnumeration.CENTER);
					mp.addObject(titleTable);
					
					/****************************************************************/
					Tbl table = factory.createTbl();
					addBorders(table, "2");
					
					Tr titleRow = factory.createTr();		
					List<String> tableHeader = new ArrayList<String>();
					tableHeader.add("SNo.");
					tableHeader.add("Name of Attendee");
					tableHeader.add("Department");
					tableHeader.add("Mobile");
					tableHeader.add("Reporting to");
					tableHeader.add("Nominated");
					tableHeader.add("Attended");
					
					for (String headerValue : tableHeader) {
						addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
								JcEnumeration.LEFT, true, "ecf2ff");
					}		
					table.getContent().add(titleRow);
					
					
					int sNo = 1;
					for (Training aObj : sObj.getTrainingAttendees()) {
						boolean hasBgColor = false;
						String backgroundColor = null;
						Tr contentRow = factory.createTr();	
						
						addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getAttendee(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getDepartment_name(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getMobile_no(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getReporting_to(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getRequired_fk(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getParticipated_fk(),
								contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						
						table.getContent().add(contentRow);
					}
					if(StringUtils.isEmpty(sObj.getTrainingAttendees()) || sObj.getTrainingAttendees().isEmpty()) {
						boolean hasBgColor = false;
						String backgroundColor = null;
						Tr contentRow = factory.createTr();	
						
						List<String> noDataRow = new ArrayList<String>();
						noDataRow.add("NO ATTENDEES");
						noDataRow.add("");
						noDataRow.add("");
						noDataRow.add("");
						noDataRow.add("");
						
						for (String headerValue : noDataRow) {
							addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
									JcEnumeration.CENTER, hasBgColor, backgroundColor);
						}		
						table.getContent().add(contentRow);	
						mergeCellsHorizontal(table, 1, 0, 4);
					}
					
					setTableAlign(factory, table, JcEnumeration.CENTER);
					mp.addObject(table);
					
					addParagraph(mp,factory);
				}
				
			}			
			/****************************************************************************************/				
		} else {
			boolean hasBgColor = false;
			String backgroundColor = null;
			Tbl titleTable = factory.createTbl();
			addBorders(titleTable, "0");
			Tr contentRow = factory.createTr();	
			
			List<String> noDataRow = new ArrayList<String>();
			noDataRow.add("NO COMPLETED TRAININGS");
			noDataRow.add("");
			noDataRow.add("");
			noDataRow.add("");
			noDataRow.add("");
			
			for (String headerValue : noDataRow) {
				addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
						JcEnumeration.CENTER, hasBgColor, backgroundColor);
			}		
			titleTable.getContent().add(contentRow);	
			mergeCellsHorizontal(titleTable, 0, 0, 4);
			
			setTableAlign(factory, titleTable, JcEnumeration.CENTER);
			mp.addObject(titleTable);
		}			
	}
	
	
	/**************************************************************************************************************/
	
	
	private static void addParagraph(MainDocumentPart mp, ObjectFactory factory) {
		P p = factory.createP();
		R r = factory.createR();		        
		Br br = factory.createBr(); 
		r.getContent().add( br); 
		p.getContent().add(r);
		
		mp.addObject(p);
	}

	
	/**
	 * @param titleRpr 
	 * @param alignment *********************************************************************************************************************/
	
	
	public static void addHeading(WordprocessingMLPackage wordMLPackage,
			MainDocumentPart t, ObjectFactory factory,JcEnumeration alignment, RPr titleRPr, String contentValue) throws Exception {
		P paragraph = factory.createP();
		setParagraphAlign(factory, paragraph, alignment);
		Text txt = factory.createText();
		txt.setValue(contentValue);
		R run = factory.createR();
		run.getContent().add(txt);
		run.setRPr(titleRPr);
		paragraph.getContent().add(run);
		t.addObject(paragraph);		
	}
	
	public static void addTableCellRemarks(ObjectFactory factory,
			WordprocessingMLPackage wordMLPackage, Tr tableRow, String content,
			RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor,
			String backgroudColor) {
		Tc tableCell = factory.createTc();
		P p = factory.createP();
		setParagraphAlign(factory, p, jcEnumeration);
		Text t = factory.createText();
		t.setValue(content);
		R run = factory.createR();
		run.setRPr(rpr);

		TcPr tcPr = tableCell.getTcPr();
		if (tcPr == null) {
			tcPr = factory.createTcPr();
		}
		

		run.getContent().add(t);
		p.getContent().add(run);
		
		//Removing space in cells
		PPr pPr = factory.createPPr();
		Spacing spacing = new Spacing();
		spacing.setAfter(BigInteger.ZERO);
		pPr.setSpacing(spacing);
		p.setPPr(pPr);
		
		tableCell.getContent().add(p);
		if (hasBgColor) {
			CTShd shd = tcPr.getShd();
			if (shd == null) {
				shd = factory.createCTShd();
			}
			shd.setColor("auto");
			shd.setFill(backgroudColor);
			tcPr.setShd(shd);
		}
		
		tableCell.setTcPr(tcPr);
		
		tableRow.getContent().add(tableCell);
	}
	
	
	private static void addPageBreak(MainDocumentPart documentPart) {
		 ObjectFactory objectFactory = new ObjectFactory();
	        //P paragraph = objectFactory.createP();
	        //R run = objectFactory.createR();
	        P p = objectFactory.createP();
	        // Create object for r
	        R r = objectFactory.createR();
	        p.getContent().add(r);
	        // Create object for br
	        Br br = objectFactory.createBr();
	        r.getContent().add(br);
	        br.setType(org.docx4j.wml.STBrType.PAGE);
	        documentPart.addObject(p);
	}
	
	public static RPr getRPr(ObjectFactory factory, String fontFamily,
			String colorVal, String fontSize, STHint sTHint, boolean isBlod,
			boolean isUnderLine, boolean isItalic, boolean isStrike) {
		RPr rPr = factory.createRPr();
		RFonts rf = new RFonts();
		rf.setHint(sTHint);
		rf.setAscii(fontFamily);
		rf.setHAnsi(fontFamily);
		rPr.setRFonts(rf);

		BooleanDefaultTrue bdt = factory.createBooleanDefaultTrue();
		rPr.setBCs(bdt);
		if (isBlod) {
			rPr.setB(bdt);
		}
		if (isItalic) {
			rPr.setI(bdt);
		}
		if (isStrike) {
			rPr.setStrike(bdt);
		}
		if (isUnderLine) {
			U underline = new U();
			underline.setVal(UnderlineEnumeration.SINGLE);
			rPr.setU(underline);
		}

		Color color = new Color();
		color.setVal(colorVal);
		rPr.setColor(color);
		

		HpsMeasure sz = new HpsMeasure();
		sz.setVal(new BigInteger(fontSize));
		rPr.setSz(sz);
		rPr.setSzCs(sz);
		
		return rPr;
	}

	
	public static void addBorders(Tbl table, String borderSize) {
		table.setTblPr(new TblPr());
		CTBorder border = new CTBorder();
		border.setColor("auto");
		border.setSz(new BigInteger(borderSize));
		border.setSpace(new BigInteger("0"));
		border.setVal(STBorder.SINGLE);
		TblBorders borders = new TblBorders();
		borders.setBottom(border);
		borders.setLeft(border);
		borders.setRight(border);
		borders.setTop(border);
		borders.setInsideH(border);
		borders.setInsideV(border);
		table.getTblPr().setTblBorders(borders);
	}

	
	public static void setParagraphAlign(ObjectFactory factory, P p,
			JcEnumeration jcEnumeration) {
		PPr pPr = p.getPPr();
		if (pPr == null) {
			pPr = factory.createPPr();
		}
		Jc jc = pPr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(jcEnumeration);
		pPr.setJc(jc);
		p.setPPr(pPr);
	}

	public static void setTableAlign(ObjectFactory factory, Tbl table,
			JcEnumeration jcEnumeration) {
		TblPr tablePr = table.getTblPr();
		if (tablePr == null) {
			tablePr = factory.createTblPr();
		}
		Jc jc = tablePr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(jcEnumeration);
		tablePr.setJc(jc);
		
		
		TblWidth tblwidth = factory.createTblWidth();
        tblwidth.setW( BigInteger.valueOf( 5000) ); // 5000 = 100%
        tblwidth.setType("pct");
        tablePr.setTblW(tblwidth);
        
		table.setTblPr(tablePr);
	}

	
	public static void addTableCell(ObjectFactory factory,
			WordprocessingMLPackage wordMLPackage, Tr tableRow, String content,
			RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor,
			String backgroudColor) {
		Tc tableCell = factory.createTc();
		P p = factory.createP();
		setParagraphAlign(factory, p, jcEnumeration);
		//Text t = factory.createText();
		//t.setValue(content);
		R run = factory.createR();
		run.setRPr(rpr);
		
		//run.getContent().add(t);
		
	    p.getContent().add(run);  
	    if (content != null) {  
	        String[] contentArr = content.split("\n");  
	        Text text = factory.createText();  
	        text.setSpace("preserve");  
	        text.setValue(contentArr[0]);  
	        run.getContent().add(text);  
	  
	        for (int i = 1, len = contentArr.length; i < len; i++) {  
	            Br br = factory.createBr();  
	            run.getContent().add(br);// 换行  
	            text = factory.createText();  
	            text.setSpace("preserve");  
	            text.setValue(contentArr[i]);  
	            run.getContent().add(text);  
	        }  
	    }  
		
		

		TcPr tcPr = tableCell.getTcPr();
		if (tcPr == null) {
			tcPr = factory.createTcPr();
		}
		
		CTVerticalJc valign = factory.createCTVerticalJc();
		valign.setVal(STVerticalJc.CENTER);
		tcPr.setVAlign(valign);		
		
		//Removing space in cells
		PPr pPr = factory.createPPr();
		Spacing spacing = new Spacing();
		spacing.setBefore(BigInteger.TWO);
		spacing.setAfter(BigInteger.TWO);
		//spacing.setAfterLines(BigInteger.TEN);
		//spacing.setBeforeLines(BigInteger.TEN);
		pPr.setSpacing(spacing);
		
		Jc justification = factory.createJc();
		justification.setVal(jcEnumeration);
		pPr.setJc(justification);

		
		p.setPPr(pPr);
		
		tableCell.getContent().add(p);
		if (hasBgColor) {
			CTShd shd = tcPr.getShd();
			if (shd == null) {
				shd = factory.createCTShd();
			}
			shd.setColor("auto");
			shd.setFill(backgroudColor);
			tcPr.setShd(shd);
		}
		
		TcBorders tcb = factory.createTcPrInnerTcBorders();
		CTBorder ctb = factory.createCTBorder();
		STBorder stb = STBorder.NONE;
		ctb.setVal(stb);
		tcb.setBottom(ctb);
		tcb.setRight(ctb);
		tcb.setLeft(ctb);
		tcb.setTop(ctb);
		tcPr.setTcBorders(tcb);
		
		tableCell.setTcPr(tcPr);
		
		tableRow.getContent().add(tableCell);
	}
	
	public static void mergeCellsHorizontal(Tbl tbl, int row, int fromCell, int toCell) {
	    if (row < 0 || fromCell < 0 || toCell < 0) {
	        return;
	    }
	    List<Tr> trList = getTblAllTr(tbl);
	    if (row > trList.size()) {
	        return;
	    }
	    Tr tr = trList.get(row);
	    List<Tc> tcList = getTrAllCell(tr);
	    for (int cellIndex = fromCell, len = Math
	            .min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {
	        Tc tc = tcList.get(cellIndex);
	        TcPr tcPr = getTcPr(tc);
	        HMerge hMerge = tcPr.getHMerge();
	        if (hMerge == null) {
	            hMerge = new HMerge();
	            tcPr.setHMerge(hMerge);
	        }
	        if (cellIndex == fromCell) {
	            hMerge.setVal("restart");
	        } else {
	            hMerge.setVal("continue");
	        }
	    }
	}
	
	public static Tc getTc(Tbl tbl, int row, int cell) {  
        if (row < 0 || cell < 0) {  
            return null;  
        }  
        List<Tr> trList = getTblAllTr(tbl);  
        if (row >= trList.size()) {  
            return null;  
        }  
        List<Tc> tcList = getTrAllCell(trList.get(row));  
        if (cell >= tcList.size()) {  
            return null;  
        }  
        return tcList.get(cell);  
    }  
  
   
    public static List<Tc> getTrAllCell(Tr tr) {  
        List<Object> objList = getAllElementFromObject(tr, Tc.class);  
        List<Tc> tcList = new ArrayList<Tc>();  
        if (objList == null) {  
            return tcList;  
        }  
        for (Object tcObj : objList) {  
            if (tcObj instanceof Tc) {  
                Tc objTc = (Tc) tcObj;  
                tcList.add(objTc);  
            }  
        }  
        return tcList;  
    }  
  
    public static TcPr getTcPr(Tc tc) {  
        TcPr tcPr = tc.getTcPr();  
        if (tcPr == null) {  
            tcPr = new TcPr();  
            tc.setTcPr(tcPr);  
        }  
        return tcPr;  
    }  
  
   
    public static List<Tr> getTblAllTr(Tbl tbl) {  
        List<Object> objList = getAllElementFromObject(tbl, Tr.class);  
        List<Tr> trList = new ArrayList<Tr>();  
        if (objList == null) {  
            return trList;  
        }  
        for (Object obj : objList) {  
            if (obj instanceof Tr) {  
                Tr tr = (Tr) obj;  
                trList.add(tr);  
            }  
        }  
        return trList;  
    }  
  
   
    public static List<Object> getAllElementFromObject(Object obj,  
            Class<?> toSearch) {  
        List<Object> result = new ArrayList<Object>();  
        if (obj instanceof JAXBElement)  
            obj = ((JAXBElement<?>) obj).getValue();  
        if (obj.getClass().equals(toSearch))  
            result.add(obj);  
        else if (obj instanceof ContentAccessor) {  
            List<?> children = ((ContentAccessor) obj).getContent();  
            for (Object child : children) {  
                result.addAll(getAllElementFromObject(child, toSearch));  
            }  
        }  
        return result;  
    }  
    
    
    public static void mergeCellsVertically(Tbl tbl, int col, int fromRow, int toRow) {  
        if (col < 0 || fromRow < 0 || toRow < 0) {  
            return;  
        }  
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
            Tc tc = getTc(tbl, rowIndex, col);  
            if (tc == null) {  
                break;  
            }  
            TcPr tcPr = getTcPr(tc);  
            VMerge vMerge = tcPr.getVMerge();  
            if (vMerge == null) {  
                vMerge = new VMerge();  
                tcPr.setVMerge(vMerge);  
            }  
            if (rowIndex == fromRow) {  
                vMerge.setVal("restart");  
            } else {  
                vMerge.setVal("continue");  
            }  
        }  
    }
    
    

}
