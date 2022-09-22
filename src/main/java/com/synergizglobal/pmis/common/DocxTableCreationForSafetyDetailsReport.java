package com.synergizglobal.pmis.common;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.docx4j.wml.PPrBase.Spacing;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.TcBorders;
import org.docx4j.wml.TcPrInner.VMerge;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Safety;

public class DocxTableCreationForSafetyDetailsReport {
	/***************************** Issue Details REPORT 
	 * @param issueDeailsHistoryReport 
	 * @throws Exception ****************************************************************/
	
	public static void createTableForSafetysDetailsReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, Safety safetyDeailsReport) throws Exception {
		
		DateFormat df = new SimpleDateFormat("dd-MM-YYYY"); 
		String date = df.format(new Date()); 
		
		
		RPr titleRpr = getRPr(factory, "Calibri", "000000", "18", STHint.EAST_ASIA,
				true, false, false, false);
		
		RPr contentRpr = getRPr(factory, "Calibri", "000000", "14",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr titleContentRpr = getRPr(factory, "Calibri", "000000", "16",
				STHint.EAST_ASIA, false, false, false, false);
		
		RPr contentRprParent = getRPr(factory, "Calibri", "000000", "20",
				STHint.EAST_ASIA, true, false, false, false);	
		
		RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		RPr calibriBoldRPr = getRPr(factory, "Calibri", "000000", "24", STHint.EAST_ASIA,
				true, false, false, false);
		RPr calibriBoldDateRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);	
		
		RPr garamondBoldRPr = getRPr(factory, "Garamond", "000000", "20", STHint.EAST_ASIA,
				true, false, false, false);
		RPr garamondRPr = getRPr(factory, "Garamond", "000000", "22", STHint.EAST_ASIA,
				false, false, false, false);
		
		
		/****************************************************************************/		
			
			Tbl titleTable = factory.createTbl();
			addBorders(titleTable, "2");
			int count = 0;
			Tr titleTableRow = factory.createTr();		
			addTableCell(factory, wordMLPackage, titleTableRow, "Description", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			
			addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getTitle(), garamondRPr,
					JcEnumeration.LEFT, false, null);
			
			
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			
			
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			
			titleTable.getContent().add(titleTableRow);	
			mergeCellsHorizontal(titleTable, count, 1, 8);
		  	count++;
		  	/****************************************************************************/		
		
		  	 titleTableRow = factory.createTr();		
		  	addTableCell(factory, wordMLPackage, titleTableRow, "Work", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			
			addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getWork_short_name(), garamondRPr,
					JcEnumeration.LEFT, false, null);
			
			addTableCell(factory, wordMLPackage, titleTableRow, "Contract Name", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getContract_short_name(), garamondRPr,
					JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
					JcEnumeration.LEFT, false, null);
			
			addTableCell(factory, wordMLPackage, titleTableRow, "Status", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getStatus_fk(), garamondRPr,
					JcEnumeration.LEFT, false, null);
			
		  	titleTable.getContent().add(titleTableRow);	
		  	mergeCellsHorizontal(titleTable, count, 2, 3);
		  	mergeCellsHorizontal(titleTable, count, 4, 5);
		  	mergeCellsHorizontal(titleTable, count, 6, 7);
		  	count++;
			/*===========================================================*/
		  	 titleTableRow = factory.createTr();	
		  	
		 	addTableCell(factory, wordMLPackage, titleTableRow, "Contractor", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			
			addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getContractor_name(), garamondRPr,
					JcEnumeration.LEFT, false, null);
			
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Department", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getDepartment_name(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Reported By", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getReported_by(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 2, 3);
			  	mergeCellsHorizontal(titleTable, count, 4, 5);
			  	mergeCellsHorizontal(titleTable, count, 6, 7);
			  	count++;

		  	/*===========================================================*/
		  	
		  
		  	
		  	
		    titleTableRow = factory.createTr();		
				addTableCell(factory, wordMLPackage, titleTableRow, "HOD", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getHod_designation(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				
			
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Dy HOD", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getDyhod_designation(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
			
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Responsible Person", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getResponsible_person(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 2, 3);
			  	mergeCellsHorizontal(titleTable, count, 4, 5);
				mergeCellsHorizontal(titleTable, count, 6, 7);
				
			  	count++;
		     /*===========================================================*/
			  	
			titleTableRow = factory.createTr();		
			if(safetyDeailsReport.getStatus_fk().equalsIgnoreCase("Closed")) {
				addTableCell(factory, wordMLPackage, titleTableRow, "Incident Date", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getDate(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Location", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getLocation(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
			
				addTableCell(factory, wordMLPackage, titleTableRow, "Closure Date", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getClosure_date(), garamondRPr,
						JcEnumeration.LEFT, false, null);
					
				titleTable.getContent().add(titleTableRow);	
				
				mergeCellsHorizontal(titleTable, count, 2, 3);
			  	mergeCellsHorizontal(titleTable, count, 4, 5);
			  	mergeCellsHorizontal(titleTable, count, 6, 7);
			}else {
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Incident Date", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getDate(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "Location", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
			
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getLocation(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 2, 4);
			  	mergeCellsHorizontal(titleTable, count, 5, 8);
			}
		  	count++;
		  	
		  	/*===========================================================*/
		  	
		  	titleTableRow = factory.createTr();		
				
		  	addTableCell(factory, wordMLPackage, titleTableRow, "Committee Requried", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getCommittee_required_fk(), garamondRPr,
					JcEnumeration.LEFT, false, null);
			
			if(safetyDeailsReport.getCommittee_required_fk().equalsIgnoreCase("Yes")) {
				addTableCell(factory, wordMLPackage, titleTableRow, "Responsible Person", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "e", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getResponsible_person(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getCommittee_required_fk(), garamondRPr,JcEnumeration.LEFT, false, null);
				if(safetyDeailsReport.getCommittee_required_fk().equalsIgnoreCase("Yes")) {
					addTableCell(factory, wordMLPackage, titleTableRow, "Committee Members Name", garamondBoldRPr,
							JcEnumeration.LEFT, true, "ecf2ff");
					addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getCommittee_member_name(), garamondRPr,
							JcEnumeration.LEFT, false, null);
					titleTable.getContent().add(titleTableRow);	
				  	mergeCellsHorizontal(titleTable, count, 2, 3);
				  	mergeCellsHorizontal(titleTable, count, 4, 5);
				  	mergeCellsHorizontal(titleTable, count, 6, 7);
				}else {
					addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
					addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
					titleTable.getContent().add(titleTableRow);	
					mergeCellsHorizontal(titleTable, count, 2, 4);
				  	mergeCellsHorizontal(titleTable, count, 5, 8);
				}
				
			}else {
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 1, 8);
				
			}
			
		  	
		  	count++;
		  /*===========================================================*/

	  	 titleTableRow = factory.createTr();		
	 	addTableCell(factory, wordMLPackage, titleTableRow, "Impact", garamondBoldRPr,
				JcEnumeration.LEFT, true, "ecf2ff");
		
		addTableCell(factory, wordMLPackage, titleTableRow, "Equipment Impact", garamondBoldRPr,
				JcEnumeration.LEFT, true, "ecf2ff");
	
		addTableCell(factory, wordMLPackage, titleTableRow, "People Impact", garamondBoldRPr,
				JcEnumeration.LEFT,  true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
				JcEnumeration.LEFT,  false, null);
		
		addTableCell(factory, wordMLPackage, titleTableRow, "LTI Hours", garamondBoldRPr,JcEnumeration.LEFT,  true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
				JcEnumeration.LEFT, false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "Work Impact", garamondBoldRPr,JcEnumeration.LEFT,  true, "ecf2ff");
		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
				JcEnumeration.LEFT,  false, null);
		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
	
		  	titleTable.getContent().add(titleTableRow);	
		  	mergeCellsHorizontal(titleTable, count, 2, 3);
		  	mergeCellsHorizontal(titleTable, count, 4, 5);
		  	mergeCellsHorizontal(titleTable, count, 6, 8);
		  	count++;
	
		  	
		  /*========================================= ==================*/
		  		titleTableRow = factory.createTr();		
				
		  		addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getImpact_fk(), garamondRPr,
						JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getEquipment_impact(), garamondRPr,
						JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getPeople_impact(), garamondRPr,JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getLti_hours(), garamondRPr,JcEnumeration.LEFT, false, null);
		  		
		  		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getWork_impact(), garamondRPr,JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
		  		addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
		  		
			  	titleTable.getContent().add(titleTableRow);
			  	mergeCellsHorizontal(titleTable, count, 2, 3);
			  	mergeCellsHorizontal(titleTable, count, 4, 5);
			  	mergeCellsHorizontal(titleTable, count, 6, 8);
			  	count++;
		  	
	  
			  	/*===========================================================*/
			  	 titleTableRow = factory.createTr();		
			  	 
				addTableCell(factory, wordMLPackage, titleTableRow, "Investigation Completed", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getInvestigation_completed(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "Payment Date", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getPayment_date(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				addTableCell(factory, wordMLPackage, titleTableRow, "Compensation paid", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getCompensation(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 2, 3);
			  	mergeCellsHorizontal(titleTable, count, 4, 5);
				mergeCellsHorizontal(titleTable, count, 6, 7);
				
			  	count++;
				 /*===========================================================*/
			  
			  	 titleTableRow = factory.createTr();	
				addTableCell(factory, wordMLPackage, titleTableRow, "Short term CM ", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getCorrective_measure_short_term(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 1, 8);
				count++;
				 /*===========================================================*/
				
				/*===========================================================*/
			  	 titleTableRow = factory.createTr();	
			 	
				addTableCell(factory, wordMLPackage, titleTableRow, "Long term CM", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getCorrective_measure_long_term(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 1, 8);
				count++;
				 /*===========================================================*/
				/*===========================================================*/
			  	 titleTableRow = factory.createTr();	
			 	addTableCell(factory, wordMLPackage, titleTableRow, "Remarks", garamondBoldRPr,
						JcEnumeration.LEFT, true, "ecf2ff");
				addTableCell(factory, wordMLPackage, titleTableRow, safetyDeailsReport.getRemarks(), garamondRPr,
						JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				addTableCell(factory, wordMLPackage, titleTableRow, "", garamondRPr,JcEnumeration.LEFT, false, null);
				
				titleTable.getContent().add(titleTableRow);	
				mergeCellsHorizontal(titleTable, count, 1, 8);
				count++;
				 /*===========================================================*/
				
				 /*===========================================================*/
			setTableAlign(factory, titleTable, JcEnumeration.CENTER);
			mp.addObject(titleTable);
			
			/****************************************************************/

	} 
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
	/*****************************
	 * 
	 * 
	 */
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
		border.setColor("a0a3bb");
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
			shd.setColor("#ecf2ff");
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
