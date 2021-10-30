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
import org.docx4j.wml.CTTblLayoutType;
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
import org.docx4j.wml.STTblLayoutType;
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

import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.Training;

public class DocxTableCreationForActivitiesReport {

	public static void createTableForProgressUpdateReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, ActivitiesProgressReport obj) throws Exception {
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
		
		RPr garamondBoldRPr = getRPr(factory, "Garamond", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr garamondBoldRPr1 = getRPr(factory, "Garamond", "000000", "20", STHint.EAST_ASIA,
				true, false, false, false);
		RPr garamondBoldRPr2 = getRPr(factory, "Garamond", "000000", "21", STHint.EAST_ASIA,
				true, false, false, false);
		RPr garamondRPr = getRPr(factory, "Garamond", "000000", "21", STHint.EAST_ASIA,
				false, false, false, false);
		RPr garamondDateRPr = getRPr(factory, "Garamond", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);
		
		String work = "-",fromDate = null,toDate = null, HOD = "-",dates = null;
			
		for (ActivitiesProgressReport aObj : obj.getProgressUpdateList()) {
			if(!StringUtils.isEmpty(obj.getWork_id()) || !StringUtils.isEmpty(obj.getContract_id())) {
				work = aObj.getWork_id_fk()+"-"+aObj.getWork_short_name();
				
			}
			if(!StringUtils.isEmpty(obj.getHod())|| !StringUtils.isEmpty(obj.getContract_id()) || !StringUtils.isEmpty(obj.getContractor_id())||!StringUtils.isEmpty(obj.getDyhod())) {
				HOD = aObj.getHod_designation();
				}

			break;
		}
		
		fromDate = obj.getFrom_date();
		toDate = obj.getTo_date();
		dates = fromDate;
		if(!(StringUtils.isEmpty(toDate))) {
			dates = fromDate +" to "+toDate;
		}		
	
		/*===========================================================*/
			
			 Tbl titleTable = factory.createTbl();
		

		    addBorders(titleTable, "0");
			Tr titleTableRow = factory.createTr();	
			addTableCell(factory, wordMLPackage, titleTableRow, "Progress Entry Report", garamondBoldRPr,
					JcEnumeration.CENTER, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow,"", garamondRPr,
					JcEnumeration.LEFT, false, null);
			titleTable.getContent().add(titleTableRow);		
			mergeCellsHorizontal(titleTable, 0, 0, 8);
			/*===========================================================*/
			titleTableRow = factory.createTr();	
			addTableCell(factory, wordMLPackage, titleTableRow, "Period ", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow, dates, garamondBoldRPr2,
					JcEnumeration.LEFT, false, null);
			
		  	titleTable.getContent().add(titleTableRow);		
			mergeCellsHorizontal(titleTable, 1, 1, 8);
			/*===========================================================*/
			titleTableRow = factory.createTr();	
			addTableCell(factory, wordMLPackage, titleTableRow, "Work ", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow, work, garamondBoldRPr2,
					JcEnumeration.LEFT, false, null);
			
		
		  	titleTable.getContent().add(titleTableRow);	
		  	mergeCellsHorizontal(titleTable, 2, 1, 8);
			/*===========================================================*/
			titleTableRow = factory.createTr();	
			addTableCell(factory, wordMLPackage, titleTableRow, "HOD", garamondBoldRPr,
					JcEnumeration.LEFT, true, "ecf2ff");
			addTableCell(factory, wordMLPackage, titleTableRow, HOD, garamondBoldRPr2,
					JcEnumeration.LEFT, false, null);
			
		
		  	titleTable.getContent().add(titleTableRow);	
		  	mergeCellsHorizontal(titleTable, 3, 1, 8);
			/*===========================================================*/
			
			
			setTableAlign(factory, titleTable, JcEnumeration.CENTER);
			mp.addObject(titleTable);
			addHeading(wordMLPackage, mp, factory,JcEnumeration.CENTER,calibriBoldRPr,"");
			/****************************************************************/
			
			for(int m=0;m<2;m++)
			{
				
				
				
				
				
				
				/**************************************************************************/
				Tbl tableHead = factory.createTbl();
				Tr hodRow = factory.createTr();
				if(m==0)
				{
					addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "PMIS Employees", calibriBoldRPr, JcEnumeration.LEFT, true,"ffffff");
				}
				else
				{
					addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "Synergiz Employees", calibriBoldRPr, JcEnumeration.LEFT, true,"ffffff");
				}
				addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "", calibriBoldRPr, JcEnumeration.LEFT, true,"ffffff");
				addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "", calibriBoldRPr, JcEnumeration.LEFT, true,"ffffff");
				addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "", calibriBoldRPr, JcEnumeration.LEFT, true,"ffffff");
				addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "", calibriBoldDateRPr, JcEnumeration.RIGHT, true,"ffffff");
				addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "", calibriBoldDateRPr, JcEnumeration.RIGHT, true,"ffffff");
				addTableCellWithTopBottomSpace(factory, wordMLPackage, hodRow, "", calibriBoldDateRPr, JcEnumeration.RIGHT, true,"ffffff");
				
				tableHead.getContent().add(hodRow);
				mergeCellsHorizontal(tableHead, 0, 0, 3);
				mergeCellsHorizontal(tableHead, 0, 4, 6);
				mp.addObject(tableHead);
				
				
				
			Tbl table = factory.createTbl();
			addBorders(table, "2");
			
			Tr titleRow = factory.createTr();		
			List<String> tableHeader = new ArrayList<String>();
			
			
			tableHeader.add("S.No");
			tableHeader.add("Employee");
			tableHeader.add("Department");
			tableHeader.add("Contract"); 
			tableHeader.add("Structure");
			tableHeader.add("Progress update Date");
			//tableHeader.add("Data Dates");
			tableHeader.add("No of Activities");
			tableHeader.add("");
			tableHeader.add("");
			
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, garamondBoldRPr,
						JcEnumeration.CENTER, true, "ecf2ff");
			}		
			
			table.getContent().add(titleRow);
			titleRow = factory.createTr();
			tableHeader = new ArrayList<String>();
			tableHeader.add("");
			tableHeader.add("");
			tableHeader.add("");
			tableHeader.add(""); 
			tableHeader.add("");
			tableHeader.add("");
			//tableHeader.add("");
			tableHeader.add("Updated");
			tableHeader.add("Approved");
			tableHeader.add("Rejected");
			
			
			for (String headerValue : tableHeader) {
				addTableCell(factory, wordMLPackage, titleRow, headerValue, garamondBoldRPr1,
						JcEnumeration.CENTER, true, "ecf2ff");
			}
			table.getContent().add(titleRow);
			mergeCellsHorizontal(table, 0, 6, 9);
			mergeCellsVertically(table, 0, 0, 1);
			mergeCellsVertically(table, 1, 0, 1);
			mergeCellsVertically(table, 2, 0, 1);
			mergeCellsVertically(table, 3, 0, 1);
			mergeCellsVertically(table, 4, 0, 1);
			mergeCellsVertically(table, 5, 0, 1);
			//mergeCellsVertically(table, 6, 0, 1);
			//mergeCellsVertically(table, 7, 0, 1);
			int sNo=1;
			for (ActivitiesProgressReport aObj : obj.getProgressUpdateList()) 
			{
				if(m==0)
				{
					if(aObj.getUser_id().contains("PMIS")) 
					{
						boolean hasBgColor = false;
						String backgroundColor = null, designation = "";
						Tr contentRow = factory.createTr();	
					if(!StringUtils.isEmpty(aObj.getDesignation())) { designation = aObj.getDesignation()+" - "; }
						addTableCell(factory, wordMLPackage, contentRow, Integer.toString(sNo++),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, designation + aObj.getUser_name(),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getDepartment_name(),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getContract_short_name(),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getStructure_type_fk(),
								garamondDateRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getProgress_date(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						
						/*addTableCell(factory, wordMLPackage, contentRow, aObj.getProgress_dates(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);*/
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getUpdated(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getApproved(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getRejected(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						table.getContent().add(contentRow);
					}
				}
				else
				{
					if(!aObj.getUser_id().contains("PMIS")) 
					{
						boolean hasBgColor = false;
						String backgroundColor = null, designation = "";
						Tr contentRow = factory.createTr();	
					if(!StringUtils.isEmpty(aObj.getDesignation())) { designation = aObj.getDesignation()+" - "; }
						addTableCell(factory, wordMLPackage, contentRow, Integer.toString(sNo++),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, designation + aObj.getUser_name(),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getDepartment_name(),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						addTableCell(factory, wordMLPackage, contentRow, aObj.getContract_short_name(),
								garamondRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getStructure_type_fk(),
								garamondDateRPr, JcEnumeration.LEFT, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getProgress_date(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						
						/*addTableCell(factory, wordMLPackage, contentRow, aObj.getProgress_dates(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);*/
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getUpdated(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getApproved(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						
						addTableCell(factory, wordMLPackage, contentRow, aObj.getRejected(),
								garamondRPr, JcEnumeration.CENTER, hasBgColor, backgroundColor);
						table.getContent().add(contentRow);
					}					
				}
			}
			if(StringUtils.isEmpty(obj.getProgressUpdateList()) || obj.getProgressUpdateList().isEmpty()) {
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();	
				
				List<String> noDataRow = new ArrayList<String>();
				noDataRow.add("NILL");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				noDataRow.add("");
				int columnNo1 = 1;
				for (String headerValue : tableHeader) {
					int width = 0;
					if(1 == columnNo1) {
						width = 100;
					}else if(2 == columnNo1) {
						width = 1100;
					}else if(3 == columnNo1) {
						width = 1100;
					}else if(4 == columnNo1) {
						width = 1100;
					}else if(5 == columnNo1) {
						width = 1100;
					}else if(6 == columnNo1) {
						width = 650;
					}
					else if(7 == columnNo1) 
					{
						width = 650;
					}
					else if(8 == columnNo1) 
					{
						width = 650;
					}				
					
					columnNo1++;
					addTableCellAndWidth(factory, wordMLPackage, contentRow, headerValue, garamondRPr,
							JcEnumeration.CENTER, hasBgColor, backgroundColor,width);
				}	
				table.getContent().add(contentRow);	
				mergeCellsHorizontal(table, 2, 0, 10);
			}
			
			setTableAlign(factory, table, JcEnumeration.LEFT);
			mp.addObject(table);
			}
			
	}

	
	
	/**************************************************************************************************************/
	
	public static void addTableCellWithTopBottomSpace(ObjectFactory factory,
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
		spacing.setBefore(BigInteger.ONE);
		spacing.setAfter(BigInteger.ONE);
		spacing.setAfterLines(BigInteger.valueOf(20));
		spacing.setBeforeLines(BigInteger.TEN);
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
	        //P p = objectFactory.createP();
	        R r = objectFactory.createR();
	        //p.getContent().add(r);
	        Br br = objectFactory.createBr();
	        r.getContent().add(br);
	        br.setType(org.docx4j.wml.STBrType.PAGE);
	        documentPart.addObject(br);
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
	
	public static void setTableAlignFixed(ObjectFactory factory, Tbl table,
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
        
        CTTblLayoutType tblLayoutType = new CTTblLayoutType();
        STTblLayoutType stTblLayoutType = STTblLayoutType.FIXED;
        tblLayoutType.setType(stTblLayoutType);
        tablePr.setTblLayout(tblLayoutType);
        
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
            tableWidth.setType("dxa");
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
