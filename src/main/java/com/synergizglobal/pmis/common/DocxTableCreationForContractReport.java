package com.synergizglobal.pmis.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.DesignReport;
import com.synergizglobal.pmis.model.Issue;

public class DocxTableCreationForContractReport {

	public static void createTableForContractReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, Map<String, List<Contract>> contractsData) throws Exception {

		try {

			RPr titleRpr = getRPr(factory, "Calibri", "000000", "12", STHint.EAST_ASIA, true, false, false,
					false);

			RPr contentRpr = getRPr(factory, "Calibri", "000000", "12", STHint.EAST_ASIA, false, false, false,
					false);

			RPr contentRprParent = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false,
					false, false);

			RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
			RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
			RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false,
					false);

			for (Map.Entry<String, List<Contract>> entry : contractsData.entrySet()) {
				//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
				List<Contract> contracts = entry.getValue();
				String keyName = entry.getKey();

				addParagraph(mp, factory);
				addHeading(wordMLPackage, mp, factory, JcEnumeration.LEFT, fontRPr, "HOD : "+keyName);

				Tbl table = factory.createTbl();
				addBorders(table, "2");

				Tr titleRow = factory.createTr();
				List<String> tableHeader = new ArrayList<String>();
				tableHeader.add("S NO");
				/*tableHeader.add("HOD");
				tableHeader.add("Dy HOD");*/
				tableHeader.add("Contract Name");
				tableHeader.add("Contractor Name");
				tableHeader.add("LOA Letter No");
				tableHeader.add("LOA Date");
				/*tableHeader.add("CA No");
				tableHeader.add("CA Date");*/
				tableHeader.add("Awarded Cost (Rs. In Lakhs)");
				tableHeader.add("Contractual Date of Completion");
				/*tableHeader.add("Revision");*/
				tableHeader.add("Revised Contract Cost (Rs. In Lakhs)");
				tableHeader.add("Revised Date of Completion");
				/*tableHeader.add("Actual Date of Start");
				tableHeader.add("Status of Contract");*/
				tableHeader.add("Cumulative Expenditure till date (Rs. In Lakhs)");
				tableHeader.add("Insurance Valid till");
				tableHeader.add("PBG Valid till");
				tableHeader.add("Remarks");
				int columnNo = 1;
				for (String headerValue : tableHeader) {
					int width = 0;
					if(columnNo == 5 || columnNo == 7 || columnNo == 9 || columnNo == 11 || columnNo == 12){
						width = 800;
					}
					columnNo++;
					addTableCellAndWidth(factory, wordMLPackage, titleRow, headerValue, titleRpr, JcEnumeration.LEFT, true,
							"ecf2ff",width);
				}
				table.getContent().add(titleRow);

				int sNo = 1;
				for (Contract cObj : contracts) {
					boolean hasBgColor = false;
					String backgroundColor = null;
					Tr contentRow = factory.createTr();

					addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor);
					/*addTableCell(factory, wordMLPackage, contentRow, cObj.getHod_designation(),
							contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, cObj.getDy_hod_designation(),
							contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
					addTableCell(factory, wordMLPackage, contentRow, cObj.getContract_short_name(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, cObj.getContractor_name(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, cObj.getLoa_letter_number(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getLoa_date(), contentRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor,800);
					/*addTableCell(factory, wordMLPackage, contentRow, cObj.getCa_no(), contentRpr, JcEnumeration.LEFT,
							hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, cObj.getCa_date(), contentRpr, JcEnumeration.LEFT,
							hasBgColor, backgroundColor);*/
					addTableCell(factory, wordMLPackage, contentRow, cObj.getAwarded_cost(), contentRpr,
							JcEnumeration.RIGHT, hasBgColor, backgroundColor);
					addTableCellAndWidth(factory, wordMLPackage, contentRow,
							(!StringUtils.isEmpty(cObj.getRevised_doc()) ? cObj.getRevised_doc() : cObj.getDoc()),
							contentRpr, JcEnumeration.CENTER, hasBgColor, backgroundColor,800);
					/*addTableCell(factory, wordMLPackage, contentRow, cObj.getRevision(),
							contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
					addTableCell(factory, wordMLPackage, contentRow, cObj.getRevised_amount(), contentRpr,
							JcEnumeration.RIGHT, hasBgColor, backgroundColor);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getRevised_doc(), contentRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor,800);
					/*addTableCell(factory, wordMLPackage, contentRow, cObj.getDate_of_start(),
							contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
					addTableCell(factory, wordMLPackage, contentRow, cObj.getContract_status_fk(),
							contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/

					addTableCell(factory, wordMLPackage, contentRow, cObj.getCumulative_expenditure(), contentRpr,
							JcEnumeration.RIGHT, hasBgColor, backgroundColor);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getInsurance_valid_till(), contentRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor,800);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getPbg_valid_till(), contentRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor,800);
					addTableCell(factory, wordMLPackage, contentRow, cObj.getRemarks(), contentRpr, JcEnumeration.LEFT,
							hasBgColor, backgroundColor);

					table.getContent().add(contentRow);
				}
				if (StringUtils.isEmpty(contracts) || contracts.isEmpty()) {
					boolean hasBgColor = false;
					String backgroundColor = null;
					Tr contentRow = factory.createTr();

					List<String> noDataRow = new ArrayList<String>();
					noDataRow.add("NO CONTACTS");
					for (int i = 0; i < 13; i++) {
						noDataRow.add("");
					}

					for (String headerValue : noDataRow) {
						addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
								hasBgColor, backgroundColor);
					}
					table.getContent().add(contentRow);
					mergeCellsHorizontal(table, 1, 0, 13);
				}
				setTableAlign(factory, table, JcEnumeration.CENTER);
				mp.addObject(table);
			}

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public static void createTableForContractBGReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, Map<String, List<Contract>> list) throws Exception {
		try {

			RPr titleRpr = getRPr(factory, "Calibri", "000000", "18", STHint.EAST_ASIA, true, false, false,
					false);

			RPr contentRpr = getRPr(factory, "Calibri", "000000", "14", STHint.EAST_ASIA, false, false, false,
					false);

			RPr contentRprParent = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false,
					false, false);

			RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
			RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
			RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false,
					false);

			int temp = 1;
			for (Map.Entry<String,List<Contract>> hodEntry : list.entrySet()) {
				if(temp > 1) {
					addParagraph(mp, factory);
				}
				temp++;
				addHeading(wordMLPackage, mp, factory,JcEnumeration.LEFT,fontRPr,hodEntry.getKey());
				
				Tbl table = factory.createTbl();
				addBorders(table, "2");
				
				setTableAlign(factory, table, JcEnumeration.CENTER);
	
				Tr titleRow = factory.createTr();
				List<String> tableHeader = new ArrayList<String>();
				
				tableHeader.add("SN");
				tableHeader.add("Agency");
				tableHeader.add("Work - Contract");
				tableHeader.add("BG NO");
				tableHeader.add("Amount\n(Lacs)");
				tableHeader.add("Validity");
				
				int columnNo = 1;
				for (String headerValue : tableHeader) {
					int width = 0;
					if(1 == columnNo) {
						width = 200;
					}else if(2 == columnNo) {
						width = 1500;
					}else if(3 == columnNo) {
						width = 2200;
					}else if(4 == columnNo) {
						width = 600;
					}else if(5 == columnNo) {
						width = 200;
					}else if(6 == columnNo) {
						width = 300;
					}
					columnNo++;
					addTableCellAndWidth(factory, wordMLPackage, titleRow, headerValue, titleRpr, JcEnumeration.CENTER, true,
							"ecf2ff",width);
				}
				table.getContent().add(titleRow);
				int sNo = 1;
				for (Contract cObj : hodEntry.getValue()) {	
					boolean hasBgColor = false;
					String backgroundColor = null;
					Tr contentRow = factory.createTr();
	
					addTableCellAndWidth(factory, wordMLPackage, contentRow, String.valueOf(sNo++), contentRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor,200);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getContractor_name(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor,1500);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getWork_id() +" - "+ cObj.getContract_short_name(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor,2200);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getBg_number(), contentRpr, JcEnumeration.LEFT,
							hasBgColor, backgroundColor,600);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getBg_value(), contentRpr, JcEnumeration.RIGHT,
							hasBgColor, backgroundColor,200);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getBg_valid_upto(), contentRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor,300);					
					
					table.getContent().add(contentRow);
				}
				
				if (StringUtils.isEmpty(hodEntry.getValue()) || hodEntry.getValue().isEmpty()) {
					boolean hasBgColor = false;
					String backgroundColor = null;
					Tr contentRow = factory.createTr();

					List<String> noDataRow = new ArrayList<String>();
					noDataRow.add("NO BG");
					for (int i = 0; i < 6; i++) {
						noDataRow.add("");
					}

					for (String headerValue : noDataRow) {
						addTableCellAndWidth(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
								hasBgColor, backgroundColor,0);
					}
					table.getContent().add(contentRow);
					mergeCellsHorizontal(table, 1, 0, 5);
				}

				
				mp.addObject(table);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static void createTableForContractInsuranceReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, Map<String,List<Contract>> list) throws Exception {
		try {
			RPr titleRpr = getRPr(factory, "Calibri", "000000", "18", STHint.EAST_ASIA, true, false, false,
					false);

			RPr contentRpr = getRPr(factory, "Calibri", "000000", "14", STHint.EAST_ASIA, false, false, false,
					false);

			RPr contentRprParent = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false,
					false, false);

			RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
			RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
			RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false,false);
			int temp = 1;
			for (Map.Entry<String,List<Contract>> hodEntry : list.entrySet()) {
				if(temp > 1) {
					addParagraph(mp, factory);
				}
				temp++;
				addHeading(wordMLPackage, mp, factory,JcEnumeration.LEFT,fontRPr,hodEntry.getKey());
				
				Tbl table = factory.createTbl();
				addBorders(table, "2");
	
				Tr titleRow = factory.createTr();
				List<String> tableHeader = new ArrayList<String>();
				tableHeader.add("SN");
				tableHeader.add("Agency");
				tableHeader.add("Work - Contract");
				tableHeader.add("Insurance No");
				tableHeader.add("Amount\n(Lacs)");
				tableHeader.add("Validity");
				int columnNo = 1;
				for (String headerValue : tableHeader) {
					int width = 0;
					if(1 == columnNo) {
						width = 200;
					}else if(2 == columnNo) {
						width = 1500;
					}else if(3 == columnNo) {
						width = 2200;
					}else if(4 == columnNo) {
						width = 600;
					}else if(5 == columnNo) {
						width = 200;
					}else if(6 == columnNo) {
						width = 300;
					}
					columnNo++;
					addTableCellAndWidth(factory, wordMLPackage, titleRow, headerValue, titleRpr, JcEnumeration.CENTER, true,
							"ecf2ff",width);
				}
				table.getContent().add(titleRow);
	
				int sNo = 1;
				for (Contract cObj : hodEntry.getValue()) {
					boolean hasBgColor = false;
					String backgroundColor = null;
					Tr contentRow = factory.createTr();
	
					addTableCellAndWidth(factory, wordMLPackage, contentRow, String.valueOf(sNo++), contentRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor,200);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getContractor_name(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor,1500);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getWork_id() +" - "+ cObj.getContract_short_name(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor,2200);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getInsurance_number(), contentRpr,
							JcEnumeration.LEFT, hasBgColor, backgroundColor,600);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getInsurance_value(), contentRpr,
							JcEnumeration.RIGHT, hasBgColor, backgroundColor,200);
					addTableCellAndWidth(factory, wordMLPackage, contentRow, cObj.getInsurance_valid_upto(), contentRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor,300);
	
					table.getContent().add(contentRow);
				}
				if (StringUtils.isEmpty(hodEntry.getValue()) || hodEntry.getValue().isEmpty()) {
					boolean hasBgColor = false;
					String backgroundColor = null;
					Tr contentRow = factory.createTr();
	
					List<String> noDataRow = new ArrayList<String>();
					noDataRow.add("NO INSURANCE");
					for (int i = 0; i < 6; i++) {
						noDataRow.add("");
					}
	
					for (String headerValue : noDataRow) {
						addTableCellAndWidth(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
								hasBgColor, backgroundColor,0);
					}
					table.getContent().add(contentRow);
					mergeCellsHorizontal(table, 1, 0, 5);
				}
	
				setTableAlign(factory, table, JcEnumeration.CENTER);
				mp.addObject(table);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static void createTableForContractDetailsReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, Contract contractDetails, Contract progressDetailsAsOnDate,
			List<Contract> milestoneDetails, List<Contract> bgDetails, List<Contract> insuranceDetails,
			Contract contractClosureDetails, Contract contractorDetails, List<Contract> keyPersonnels) throws Exception {
		try {
			RPr titleRpr = getRPr(factory, "Calibri", "000000", "18", STHint.EAST_ASIA, true, false, false,
					false);
			RPr contentRpr = getRPr(factory, "Calibri", "000000", "14", STHint.EAST_ASIA, false, false, false,
					false);
			RPr contentRprParent = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false,
					false, false);
			RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
			RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
			RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false,
					false);

			/********************* Contract Details Starts *******************************************************/
			Tbl contractDetailsTable = factory.createTbl();
			addBorders(contractDetailsTable, "2");

			//addHeading(wordMLPackage, mp, factory, JcEnumeration.RIGHT, titleRpr, "Annexure-I");

			boolean hasBgColor3 = false;
			String backgroundColor3 = null;

			/************************/
			Tr contractContentRow0 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow0, "Project", titleRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow0, contractDetails.getProject_name(), titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			for (int i = 0; i < 4; i++) {
				addTableCell(factory, wordMLPackage, contractContentRow0, "", contentRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			/************************/
			Tr contractContentRow1 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow1, "Work", titleRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow1, contractDetails.getWork_name(), titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			for (int i = 0; i < 4; i++) {
				addTableCell(factory, wordMLPackage, contractContentRow1, "", contentRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			/************************/
			Tr contractContentRow2 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow2, "Contract ID", titleRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow2, contractDetails.getContract_id(), titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			for (int i = 0; i < 4; i++) {
				addTableCell(factory, wordMLPackage, contractContentRow2, "", contentRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			/************************/
			Tr contractContentRow3 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow3, "Contract Name", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow3, contractDetails.getContract_name(), titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			for (int i = 0; i < 4; i++) {
				addTableCell(factory, wordMLPackage, contractContentRow3, "", contentRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			/************************/
			Tr contractContentRow4 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow4, "Contractor Name", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow4, contractDetails.getContractor_name(), titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			for (int i = 0; i < 4; i++) {
				addTableCell(factory, wordMLPackage, contractContentRow4, "", contentRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			/************************/
			Tr contractContentRow5 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow5, "Status", titleRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow5, contractDetails.getContract_status_fk(), titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			for (int i = 0; i < 4; i++) {
				addTableCell(factory, wordMLPackage, contractContentRow5, "", contentRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			/************************/
			contractDetailsTable.getContent().add(contractContentRow0);
			contractDetailsTable.getContent().add(contractContentRow1);
			contractDetailsTable.getContent().add(contractContentRow2);
			contractDetailsTable.getContent().add(contractContentRow3);
			contractDetailsTable.getContent().add(contractContentRow4);
			contractDetailsTable.getContent().add(contractContentRow5);

			mergeCellsHorizontal(contractDetailsTable, 0, 1, 6);
			mergeCellsHorizontal(contractDetailsTable, 1, 1, 6);
			mergeCellsHorizontal(contractDetailsTable, 2, 1, 6);
			mergeCellsHorizontal(contractDetailsTable, 3, 1, 6);
			mergeCellsHorizontal(contractDetailsTable, 4, 1, 6);
			mergeCellsHorizontal(contractDetailsTable, 5, 1, 6);

			boolean contracthasBgColor = false;
			String contractBackgroundColor = null;
			Tr contractContentRow6 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow6, "HOD:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow6, contractDetails.getHod_designation(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow6, "Dy HOD:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow6, contractDetails.getDy_hod_designation(),
					contentRpr, JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow6, "Department:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow6, contractDetails.getDepartment_name(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow6);

			Tr contractContentRow7 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow7, "LOA Letter No:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow7, contractDetails.getLoa_letter_number(),
					contentRpr, JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow7, "", titleRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow7, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow7, "LOA Date:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow7, contractDetails.getLoa_date(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow7);
			mergeCellsHorizontal(contractDetailsTable, 7, 1, 3);

			Tr contractContentRow8 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow8, "CA No:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow8, contractDetails.getCa_no(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow8, "", titleRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow8, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow8, "CA Date:", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow8, contractDetails.getCa_date(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow8);
			mergeCellsHorizontal(contractDetailsTable, 8, 1, 3);

			Tr contractContentRow9 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow9, "Contract Type", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow9, contractDetails.getContract_type_fk(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow9, "", titleRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow9, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow9, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow9, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow9);
			mergeCellsHorizontal(contractDetailsTable, 9, 1, 5);

			Tr contractContentRow10 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow10, "Scope of Contract", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow10, contractDetails.getScope_of_contract(),
					contentRpr, JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow10, "", titleRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow10, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow10, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow10, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow10);
			mergeCellsHorizontal(contractDetailsTable, 10, 1, 5);

			Tr contractContentRow11 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow11, "Awarded Cost", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow11, contractDetails.getAwarded_cost(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow11, "Date of Completion", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow11, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow11, contractDetails.getDoc(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow11, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow11);
			mergeCellsHorizontal(contractDetailsTable, 11, 2, 3);
			mergeCellsHorizontal(contractDetailsTable, 11, 4, 5);

			Tr contractContentRow12 = factory.createTr();
			addTableCell(factory, wordMLPackage, contractContentRow12, "Date of Start", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractContentRow12, contractDetails.getDate_of_start(), contentRpr,
					JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow12, "", titleRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow12, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow12, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			addTableCell(factory, wordMLPackage, contractContentRow12, "", contentRpr, JcEnumeration.LEFT,
					contracthasBgColor, contractBackgroundColor);
			contractDetailsTable.getContent().add(contractContentRow12);
			mergeCellsHorizontal(contractDetailsTable, 12, 2, 3);
			mergeCellsHorizontal(contractDetailsTable, 12, 4, 5);
			
			setTableAlign(factory, contractDetailsTable, JcEnumeration.CENTER);
			mp.addObject(contractDetailsTable);
			
			/**************************************************************/
			Tbl revisionDetailsTable = factory.createTbl();
			addBorders(revisionDetailsTable, "2");

			addParagraph(mp, factory);
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Revision Details");
			
			Tr revisionContentRow0 = factory.createTr();
			addTableCell(factory, wordMLPackage, revisionContentRow0, "Revision Number", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, revisionContentRow0, "Revised Amount", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, revisionContentRow0, "Revised DOC", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, revisionContentRow0, "Remark", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			revisionDetailsTable.getContent().add(revisionContentRow0);
			for (Contract revision : contractDetails.getContract_revision()) {
				Tr contractRevisionContentRow = factory.createTr();
				addTableCell(factory, wordMLPackage, contractRevisionContentRow, revision.getRevision_number(),
						titleRpr, JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
				addTableCell(factory, wordMLPackage, contractRevisionContentRow, revision.getRevised_amount(),
						contentRpr, JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
				addTableCell(factory, wordMLPackage, contractRevisionContentRow, revision.getRevised_doc(), titleRpr,
						JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
				addTableCell(factory, wordMLPackage, contractRevisionContentRow, revision.getRemarks(), contentRpr,
						JcEnumeration.LEFT, contracthasBgColor, contractBackgroundColor);
				revisionDetailsTable.getContent().add(contractRevisionContentRow);
			}
			
			if (StringUtils.isEmpty(contractDetails.getContract_revision()) || contractDetails.getContract_revision().isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO REVISION DATA");
				for (int i = 0; i < 3; i++) {
					noDataRow.add("");
				}

				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor);
				}
				revisionDetailsTable.getContent().add(contentRow);
				mergeCellsHorizontal(revisionDetailsTable, 1, 0, 3);
			}

			setTableAlign(factory, revisionDetailsTable, JcEnumeration.CENTER);
			mp.addObject(revisionDetailsTable);

			/********************* Contract Details ends *******************************************************/

			/********************* Progress Details as on date Details Starts *******************************************************/
			Tbl progressDetailsTable = factory.createTbl();
			addBorders(progressDetailsTable, "2");

			addParagraph(mp, factory);
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Progress Details as on date");

			boolean hasBgColor0 = false;
			String backgroundColor0 = null;
			Tr progressContentRow0 = factory.createTr();

			addTableCell(factory, wordMLPackage, progressContentRow0, "Actual Date of Start", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, progressContentRow0, "", contentRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, progressContentRow0, progressDetailsAsOnDate.getDate_of_start(),
					contentRpr, JcEnumeration.LEFT, hasBgColor0, backgroundColor0);

			addTableCell(factory, wordMLPackage, progressContentRow0, "Expected date of completion", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, progressContentRow0, "", contentRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, progressContentRow0, progressDetailsAsOnDate.getDoc(), contentRpr,
					JcEnumeration.LEFT, hasBgColor0, backgroundColor0);

			progressDetailsTable.getContent().add(progressContentRow0);

			mergeCellsHorizontal(progressDetailsTable, 0, 0, 1);
			mergeCellsHorizontal(progressDetailsTable, 0, 3, 4);

			Tr progressDetailsTitleRow = factory.createTr();
			List<String> progressDetailsTableHeader = new ArrayList<String>();
			progressDetailsTableHeader.add("Actual Physical progress");
			progressDetailsTableHeader.add("");
			progressDetailsTableHeader.add("Actual Financial Progress ");
			progressDetailsTableHeader.add("");
			progressDetailsTableHeader.add("Payment made");
			progressDetailsTableHeader.add("");

			for (String headerValue : progressDetailsTableHeader) {
				addTableCell(factory, wordMLPackage, progressDetailsTitleRow, headerValue, titleRpr, JcEnumeration.LEFT,
						true, "ecf2ff");
			}
			progressDetailsTable.getContent().add(progressDetailsTitleRow);
			mergeCellsHorizontal(progressDetailsTable, 1, 0, 1);
			mergeCellsHorizontal(progressDetailsTable, 1, 2, 3);
			mergeCellsHorizontal(progressDetailsTable, 1, 4, 5);

			boolean hasBgColor1 = false;
			String backgroundColor1 = null;
			Tr progressContentRow1 = factory.createTr();

			/*addTableCell(factory, wordMLPackage, progressContentRow1,
					progressDetailsAsOnDate.getActual_physical_progress(), contentRpr, JcEnumeration.LEFT, hasBgColor1,
					backgroundColor1);*/
			addTableCell(factory, wordMLPackage, progressContentRow1,
					"", contentRpr, JcEnumeration.LEFT, hasBgColor1,
					backgroundColor1);
			addTableCell(factory, wordMLPackage, progressContentRow1, "", contentRpr, JcEnumeration.LEFT, hasBgColor1,
					backgroundColor1);
			addTableCell(factory, wordMLPackage, progressContentRow1,
					progressDetailsAsOnDate.getActual_financial_progress(), contentRpr, JcEnumeration.LEFT, hasBgColor1,
					backgroundColor1);
			addTableCell(factory, wordMLPackage, progressContentRow1, "", contentRpr, JcEnumeration.LEFT, hasBgColor1,
					backgroundColor1);
			addTableCell(factory, wordMLPackage, progressContentRow1, progressDetailsAsOnDate.getPayment_made(),
					contentRpr, JcEnumeration.LEFT, hasBgColor1, backgroundColor1);
			addTableCell(factory, wordMLPackage, progressContentRow1, "", contentRpr, JcEnumeration.LEFT, hasBgColor1,
					backgroundColor1);

			progressDetailsTable.getContent().add(progressContentRow1);

			mergeCellsHorizontal(progressDetailsTable, 2, 0, 1);
			mergeCellsHorizontal(progressDetailsTable, 2, 2, 3);
			mergeCellsHorizontal(progressDetailsTable, 2, 4, 5);

			setTableAlign(factory, progressDetailsTable, JcEnumeration.CENTER);
			mp.addObject(progressDetailsTable);

			/********************* Progress Details as on date Details ends *******************************************************/

			/********************* Milestone Details Starts *******************************************************/
			Tbl milestoneTable = factory.createTbl();
			addBorders(milestoneTable, "2");

			addParagraph(mp, factory);
			//addPageBreak(mp);
			//addHeading(wordMLPackage, mp, factory, JcEnumeration.RIGHT, titleRpr, "Annexure-II");
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Milestone Details");

			Tr milestoneTitleRow = factory.createTr();
			List<String> milestoneTableHeader = new ArrayList<String>();
			milestoneTableHeader.add("Mile stone ID");
			milestoneTableHeader.add("Milestone Name");
			milestoneTableHeader.add("Milestone Date");
			milestoneTableHeader.add("Actual Date");
			milestoneTableHeader.add("Remarks");

			for (String headerValue : milestoneTableHeader) {
				addTableCell(factory, wordMLPackage, milestoneTitleRow, headerValue, titleRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			milestoneTable.getContent().add(milestoneTitleRow);

			for (Contract cObj : milestoneDetails) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				addTableCell(factory, wordMLPackage, contentRow, cObj.getMilestone_id(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getMilestone_name(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getMilestone_date(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getActual_date(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getRemarks(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);

				milestoneTable.getContent().add(contentRow);
			}
			if (StringUtils.isEmpty(milestoneDetails) || milestoneDetails.isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO MILESTONE DATA");
				for (int i = 0; i < 4; i++) {
					noDataRow.add("");
				}

				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor);
				}
				milestoneTable.getContent().add(contentRow);
				mergeCellsHorizontal(milestoneTable, 1, 0, 4);
			}

			setTableAlign(factory, milestoneTable, JcEnumeration.CENTER);
			mp.addObject(milestoneTable);

			/********************* Milestone Details ends *******************************************************/

			/********************* BG Details Starts *******************************************************/
			Tbl bgTable = factory.createTbl();
			addBorders(bgTable, "2");

			addParagraph(mp, factory);
			//addPageBreak(mp);
			//addHeading(wordMLPackage, mp, factory, JcEnumeration.RIGHT, titleRpr, "Annexure-III");
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Bank Guarantee Details");

			Tr bgTitleRow = factory.createTr();
			List<String> bgTableHeader = new ArrayList<String>();
			bgTableHeader.add("BG Type");
			bgTableHeader.add("Issuing Bank");
			bgTableHeader.add("BG/FDR Number");
			bgTableHeader.add("Amount");
			bgTableHeader.add("BG/FDR Date");
			bgTableHeader.add("Expiry Date");
			bgTableHeader.add("Release Date");

			for (String headerValue : bgTableHeader) {
				addTableCell(factory, wordMLPackage, bgTitleRow, headerValue, titleRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			bgTable.getContent().add(bgTitleRow);

			for (Contract cObj : bgDetails) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_type_fk(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getIssuing_bank(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_number(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_value(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_date(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_valid_upto(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getRelease_date(), contentRpr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor);

				bgTable.getContent().add(contentRow);
			}
			if (StringUtils.isEmpty(bgDetails) || bgDetails.isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO BG DATA");
				for (int i = 0; i < 7; i++) {
					noDataRow.add("");
				}

				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor);
				}
				bgTable.getContent().add(contentRow);
				mergeCellsHorizontal(bgTable, 1, 0, 7);
			}

			setTableAlign(factory, bgTable, JcEnumeration.CENTER);
			mp.addObject(bgTable);

			/********************* BG Details ends *******************************************************/

			/********************* Insurance Details Starts *******************************************************/
			Tbl insuranceTable = factory.createTbl();
			addBorders(insuranceTable, "2");

			addParagraph(mp, factory);
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Insurance Details");

			Tr insurancetitleRow = factory.createTr();
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("Insurance Type");
			tableHeader.add("Issuing Agency");
			tableHeader.add("Insurance Number");
			tableHeader.add("Insurance Value");
			tableHeader.add("Valid Upto");
			tableHeader.add("Release");

			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, insurancetitleRow, headerValue, titleRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			insuranceTable.getContent().add(insurancetitleRow);

			for (Contract cObj : insuranceDetails) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_type_fk(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getIssuing_agency(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_number(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_value(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_valid_upto(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_status(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);

				insuranceTable.getContent().add(contentRow);
			}
			if (StringUtils.isEmpty(insuranceDetails) || insuranceDetails.isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO INSURANCE DATA");
				for (int i = 0; i < 6; i++) {
					noDataRow.add("");
				}

				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor);
				}
				insuranceTable.getContent().add(contentRow);
				mergeCellsHorizontal(insuranceTable, 1, 0, 6);
			}

			setTableAlign(factory, insuranceTable, JcEnumeration.CENTER);
			mp.addObject(insuranceTable);

			/********************* Insurance Details ends *******************************************************/

			/********************* Contract Closure Details Starts *******************************************************/
			Tbl cloureDetailsTable = factory.createTbl();
			addBorders(cloureDetailsTable, "2");

			addParagraph(mp, factory);
			//addPageBreak(mp);
			//addHeading(wordMLPackage, mp, factory, JcEnumeration.RIGHT, titleRpr, "Annexure-IV");
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Contract Closure Details");

			boolean hasBgColor = false;
			String backgroundColor = null;

			/************************************************/
			Tr cloureContentRow0 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow0, "Actual Completion date", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow0, contractClosureDetails.getActual_completion_date(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);

			addTableCell(factory, wordMLPackage, cloureContentRow0, "Actual Completion Cost", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow0, contractClosureDetails.getCompleted_cost(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow0);
			/**********************************************************/
			Tr cloureContentRow1 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow1, "Final Taking over by Client", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow1, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow1, contractClosureDetails.getFinal_takeover(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow1, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow1);
			mergeCellsHorizontal(cloureDetailsTable, 1, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 1, 2, 3);
			/**************************************************************/
			Tr cloureContentRow2 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow2, "Release of Completion Certificate", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow2, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow2,
					contractClosureDetails.getCompletion_certificate_release(), contentRpr, JcEnumeration.LEFT,
					hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow2, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow2);
			mergeCellsHorizontal(cloureDetailsTable, 2, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 2, 2, 3);
			/**************************************************************/
			Tr cloureContentRow3 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow3, "Release of Final bill", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow3, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow3, contractClosureDetails.getFinal_bill_release(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow3, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow3);
			mergeCellsHorizontal(cloureDetailsTable, 3, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 3, 2, 3);
			/**************************************************************/
			Tr cloureContentRow4 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow4, "Defect Liability Period completed on ", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow4, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow4, contractClosureDetails.getDefect_liability_period(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow4, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow4);
			mergeCellsHorizontal(cloureDetailsTable, 4, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 4, 2, 3);
			/**************************************************************/
			Tr cloureContentRow5 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow5, "Release of Final Retention amount/BG", titleRpr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow5, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow5, contractClosureDetails.getRetention_money_release(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow5, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow5);
			mergeCellsHorizontal(cloureDetailsTable, 5, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 5, 2, 3);
			/**************************************************************/
			Tr cloureContentRow6 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow6, "Release of PBG", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow6, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow6, contractClosureDetails.getPbg_release(), contentRpr,
					JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow6, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow6);
			mergeCellsHorizontal(cloureDetailsTable, 6, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 6, 2, 3);
			/**************************************************************/
			Tr cloureContentRow7 = factory.createTr();

			addTableCell(factory, wordMLPackage, cloureContentRow7, "Contract Closure on", titleRpr, JcEnumeration.LEFT,
					true, "ecf2ff");
			addTableCell(factory, wordMLPackage, cloureContentRow7, "", titleRpr, JcEnumeration.LEFT, true, "ecf2ff");

			addTableCell(factory, wordMLPackage, cloureContentRow7, contractClosureDetails.getContract_closure_date(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
			addTableCell(factory, wordMLPackage, cloureContentRow7, "", contentRpr, JcEnumeration.LEFT, hasBgColor,
					backgroundColor);

			cloureDetailsTable.getContent().add(cloureContentRow7);
			mergeCellsHorizontal(cloureDetailsTable, 7, 0, 1);
			mergeCellsHorizontal(cloureDetailsTable, 7, 2, 3);
			/**************************************************************/

			setTableAlign(factory, cloureDetailsTable, JcEnumeration.CENTER);
			mp.addObject(cloureDetailsTable);

			/********************* Contract Closure Details ends *******************************************************/

			/********************* Contractor Details Starts *******************************************************/
			Tbl contractorDetailsTable = factory.createTbl();
			addBorders(contractorDetailsTable, "2");

			addParagraph(mp, factory);
			//addPageBreak(mp);
			//addHeading(wordMLPackage, mp, factory, JcEnumeration.RIGHT, titleRpr, "Annexure-V");
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Contractor Details");

			/************************************************/
			Tr contractorRow0 = factory.createTr();

			addTableCell(factory, wordMLPackage, contractorRow0, "Company name", titleRpr, JcEnumeration.LEFT, true,
					"ecf2ff");
			addTableCell(factory, wordMLPackage, contractorRow0, contractorDetails.getContractor_name(), contentRpr,
					JcEnumeration.LEFT, hasBgColor, backgroundColor);

			contractorDetailsTable.getContent().add(contractorRow0);
			/**********************************************************/
			Tr contractorRow1 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow1, "Specialization",
					titleRpr, JcEnumeration.LEFT, true, "ecf2ff");	
			addTableCell(factory, wordMLPackage, contractorRow1, contractorDetails.getContractor_specilization_fk(),
					contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
			
			contractorDetailsTable.getContent().add(contractorRow1);
			/**************************************************************/			
			Tr contractorRow2 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow2, "Address",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");	
			addTableCell(factory, wordMLPackage, contractorRow2, contractorDetails.getAddress(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
			
			contractorDetailsTable.getContent().add(contractorRow2);
			/**************************************************************/
			
			Tr contractorRow3 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow3, "Primary Contact",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");	
			addTableCell(factory, wordMLPackage, contractorRow3, contractorDetails.getPrimary_contact_name(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
			
			contractorDetailsTable.getContent().add(contractorRow3);
			/**************************************************************/
			
			Tr contractorRow4 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow4, "Phone Number",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, contractorRow4, contractorDetails.getPhone_number(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
			
			contractorDetailsTable.getContent().add(contractorRow4);
			/**************************************************************/
			
			Tr contractorRow5 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow5, "Email ID",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");		
			addTableCell(factory, wordMLPackage, contractorRow5, contractorDetails.getEmail_id(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);			
			
			contractorDetailsTable.getContent().add(contractorRow5);
			/**************************************************************/
			
			Tr contractorRow6 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow6, "GST Number",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");		
			addTableCell(factory, wordMLPackage, contractorRow6, contractorDetails.getGst_number(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);		
			
			contractorDetailsTable.getContent().add(contractorRow6);
			/**************************************************************/
			
			Tr contractorRow7 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow7, "Pan Number",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");		
			addTableCell(factory, wordMLPackage, contractorRow7, contractorDetails.getPan_number(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);		
			
			contractorDetailsTable.getContent().add(contractorRow7);
			/**************************************************************/
			Tr contractorRow8 = factory.createTr();	
			
			addTableCell(factory, wordMLPackage, contractorRow8, "Remarks",
			titleRpr, JcEnumeration.LEFT, true, "ecf2ff");		
			addTableCell(factory, wordMLPackage, contractorRow8, contractorDetails.getRemarks(),
			contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);		
			
			contractorDetailsTable.getContent().add(contractorRow8);
			/**************************************************************/
			setTableAlign(factory, contractorDetailsTable, JcEnumeration.CENTER);
			mp.addObject(contractorDetailsTable);

			/********************* Contractor Details ends *******************************************************/
			/********************* Key Personnel Details Starts *******************************************************/
			Tbl keyPersonnelTable = factory.createTbl();
			addBorders(keyPersonnelTable, "2");

			addParagraph(mp, factory);
			addHeading(wordMLPackage, mp, factory, JcEnumeration.CENTER, fontRPr, "Key Personnel Details");

			Tr keyPersonnelTitleRow = factory.createTr();
			List<String> keyPersonnelTableHeader = new ArrayList<String>();
			keyPersonnelTableHeader.add("Name");
			keyPersonnelTableHeader.add("Mobile NO");
			keyPersonnelTableHeader.add("Email ID");
			keyPersonnelTableHeader.add("Designation");

			for (String headerValue : keyPersonnelTableHeader) {
				addTableCell(factory, wordMLPackage, keyPersonnelTitleRow, headerValue, titleRpr, JcEnumeration.LEFT, true,
						"ecf2ff");
			}
			keyPersonnelTable.getContent().add(keyPersonnelTitleRow);

			for (Contract cObj : keyPersonnels) {
				hasBgColor = false;
				backgroundColor = null;
				Tr contentRow = factory.createTr();

				addTableCell(factory, wordMLPackage, contentRow, cObj.getName(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getMobile_no(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getEmail_id(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getDesignation(), contentRpr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor);
				

				keyPersonnelTable.getContent().add(contentRow);
			}
			if (StringUtils.isEmpty(keyPersonnels) || keyPersonnels.isEmpty()) {
				hasBgColor = false;
				backgroundColor = null;
				Tr contentRow = factory.createTr();

				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO KEY PERSONNEL DATA");
				for (int i = 0; i < 3; i++) {
					noDataRow.add("");
				}

				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr, JcEnumeration.CENTER,
							hasBgColor, backgroundColor);
				}
				keyPersonnelTable.getContent().add(contentRow);
				mergeCellsHorizontal(keyPersonnelTable, 1, 0, 4);
			}

			setTableAlign(factory, keyPersonnelTable, JcEnumeration.CENTER);
			mp.addObject(keyPersonnelTable);

			/********************* Insurance Details ends *******************************************************/

		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**************************************************************************************************************/

	private static void addParagraph(MainDocumentPart mp, ObjectFactory factory) {
		P p = factory.createP();
		R r = factory.createR();
		//Br br = factory.createBr();
		//r.getContent().add(br);
		p.getContent().add(r);

		mp.addObject(p);
	}

	/**
	 * @param titleRpr 
	 * @param alignment *********************************************************************************************************************/

	public static void addHeading(WordprocessingMLPackage wordMLPackage, MainDocumentPart t, ObjectFactory factory,
			JcEnumeration alignment, RPr titleRPr, String contentValue) throws Exception {
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

	public static void addTableCellRemarks(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
			String content, RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor) {
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

	public static RPr getRPr(ObjectFactory factory, String fontFamily, String colorVal, String fontSize, STHint sTHint,
			boolean isBlod, boolean isUnderLine, boolean isItalic, boolean isStrike) {
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

	public static void setParagraphAlign(ObjectFactory factory, P p, JcEnumeration jcEnumeration) {
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

	public static void setTableAlign(ObjectFactory factory, Tbl table, JcEnumeration jcEnumeration) {
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
		tblwidth.setW(BigInteger.valueOf(5000)); // 5000 = 100%
		tblwidth.setType("pct");
		tablePr.setTblW(tblwidth);

		table.setTblPr(tablePr);
	}

	public static void addTableCell(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
			String content, RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor) {
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
				run.getContent().add(br);
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
	
	public static void addTableCellAndWidth(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
			String content, RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor,int columnWidth) {
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
				run.getContent().add(br);
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
		
		/********************************/		
		if (columnWidth > 0) {
            TblWidth tableWidth = new TblWidth();
            tableWidth.setW(BigInteger.valueOf(columnWidth));
            tcPr.setTcW(tableWidth);
        }		
		/********************************/

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
		for (int cellIndex = fromCell, len = Math.min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {
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

	public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
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
