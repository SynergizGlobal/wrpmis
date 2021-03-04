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

import com.synergizglobal.pmis.model.Contract;

public class DocxTableCreationForContractReport {	

	public static void createTableForContractReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Contract> contracts) throws Exception {
		
		try {
		
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
			
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("S NO");
			/*tableHeader.add("HOD");
			tableHeader.add("Dy HOD");*/
			tableHeader.add("Contract Name");
			tableHeader.add("Contractor Name");
			tableHeader.add("LOA Letter No");
			tableHeader.add("LOA Date");
			tableHeader.add("CA No");
			tableHeader.add("CA Date");
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
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.LEFT, true, "ecf2ff");
			}		
			table.getContent().add(titleRow);
			
			
			int sNo = 1;
			for (Contract cObj : contracts) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				/*addTableCell(factory, wordMLPackage, contentRow, cObj.getHod_designation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getDy_hod_designation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContract_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContractor_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getLoa_letter_number(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getLoa_date(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getCa_no(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getCa_date(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getAwarded_cost(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, (!StringUtils.isEmpty(cObj.getRevised_doc())?cObj.getRevised_doc():cObj.getDoc()),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				/*addTableCell(factory, wordMLPackage, contentRow, cObj.getRevision(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
				addTableCell(factory, wordMLPackage, contentRow, cObj.getRevised_amount(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getRevised_doc(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				/*addTableCell(factory, wordMLPackage, contentRow, cObj.getDate_of_start(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContract_status_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
				
				addTableCell(factory, wordMLPackage, contentRow, cObj.getCumulative_expenditure(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_valid_till(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getPbg_valid_till(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getRemarks(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				
				
				table.getContent().add(contentRow);
			}
			if(StringUtils.isEmpty(contracts) || contracts.isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO CONTACTS");
				for (int i = 0; i < 15; i++) {
					noDataRow.add("");
				}
				
				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor);
				}		
				table.getContent().add(contentRow);	
				mergeCellsHorizontal(table, 1, 0, 4);
			}
			
			setTableAlign(factory, table, JcEnumeration.CENTER);
			mp.addObject(table);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	public static void createTableForContractBGReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Contract> contracts) throws Exception {
		try {
			
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
			
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("S NO");
			tableHeader.add("HOD");
			tableHeader.add("Contractor Name");
			tableHeader.add("Code");
			tableHeader.add("Work Name");
			tableHeader.add("Contract Agreement No");
			tableHeader.add("Contract Description");
			tableHeader.add("Type of BG");
			tableHeader.add("BG NO/FDR NO");
			tableHeader.add("BG/FDR Date");
			tableHeader.add("BG Expiry Date");
			tableHeader.add("Amount");
			tableHeader.add("Name of the Bank");
			tableHeader.add("Address of the Bank");
			tableHeader.add("Release Date");
			//tableHeader.add("Remarks");
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.LEFT, true, "ecf2ff");
			}		
			table.getContent().add(titleRow);
			
			
			int sNo = 1;
			for (Contract cObj : contracts) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getHod_designation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContractor_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getCode(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getWork_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getCa_no(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContract_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);				
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_type_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_number(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_date(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_valid_upto(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBg_value(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getIssuing_bank(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getBank_address(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getRelease_date(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				/*addTableCell(factory, wordMLPackage, contentRow, cObj.getRemarks(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);*/
				
				
				table.getContent().add(contentRow);
			}
			if(StringUtils.isEmpty(contracts) || contracts.isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO BG");
				for (int i = 0; i < 15; i++) {
					noDataRow.add("");
				}
				
				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor);
				}		
				table.getContent().add(contentRow);	
				mergeCellsHorizontal(table, 1, 0, 4);
			}
			
			setTableAlign(factory, table, JcEnumeration.CENTER);
			mp.addObject(table);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void createTableForContractInsuranceReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Contract> contracts) throws Exception {
		try {
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
			
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			tableHeader.add("S NO");
			tableHeader.add("HOD");
			tableHeader.add("Contractor Name");
			tableHeader.add("Work Name");
			tableHeader.add("Contract Agreement No");
			tableHeader.add("Contract Description");
			tableHeader.add("Type of Insurance");
			tableHeader.add("Insurance No");
			tableHeader.add("Insurance Value");
			tableHeader.add("Insurance Valid upto");
			tableHeader.add("Name of the Agency");
			tableHeader.add("Address of the Agency");
			tableHeader.add("Remarks");
			tableHeader.add("Released");
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, titleRpr,
						JcEnumeration.LEFT, true, "ecf2ff");
			}		
			table.getContent().add(titleRow);
			
			
			int sNo = 1;
			for (Contract cObj : contracts) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				addTableCell(factory, wordMLPackage, contentRow, String.valueOf(sNo++),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getHod_designation(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContractor_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getWork_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getCa_no(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getContract_short_name(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);				
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_type_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_number(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_value(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurance_valid_upto(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getIssuing_agency(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getAgency_address(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getInsurence_remark(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj.getReleased_fk(),
						contentRpr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
				
				
				table.getContent().add(contentRow);
			}
			if(StringUtils.isEmpty(contracts) || contracts.isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NO INSURANCE");
				for (int i = 0; i < 15; i++) {
					noDataRow.add("");
				}
				
				for (String headerValue : noDataRow) {
					addTableCell(factory, wordMLPackage, contentRow, headerValue, titleRpr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor);
				}		
				table.getContent().add(contentRow);	
				mergeCellsHorizontal(table, 1, 0, 4);
			}
			
			setTableAlign(factory, table, JcEnumeration.CENTER);
			mp.addObject(table);
		} catch (Exception e) {
			throw new Exception(e);
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
