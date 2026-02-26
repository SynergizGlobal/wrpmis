package com.synergizglobal.wrpmis.common;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.CTTblLayoutType;
import org.docx4j.wml.CTVerticalJc;
import org.docx4j.wml.Color;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Drawing;
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
import org.docx4j.wml.STTblLayoutType;
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

import com.synergizglobal.wrpmis.constants.CommonConstants2;
import com.synergizglobal.wrpmis.model.Training;

public class DocxTableCreationForTrainingReport {
	public static void createTableForTrainingListReport(WordprocessingMLPackage wordMLPackage, MainDocumentPart mp,
			ObjectFactory factory, List<Training> reportsData, String report_created_date) throws Exception {

		try {

			RPr titleRpr = getRPr(factory, "Calibri", "000000", "12", STHint.EAST_ASIA, true, false, false, false);

			RPr contentRpr = getRPr(factory, "Calibri", "000000", "12", STHint.EAST_ASIA, false, false, false, false);

			RPr contentRprParent = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false, false,
					false);

			RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
			RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
			RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false, false);

			RPr calibriBoldRPr = getRPr(factory, "Calibri", "000000", "24", STHint.EAST_ASIA, true, false, false,
					false);
			RPr calibriBoldDateRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false,
					false);

			RPr garamondBoldRPr = getRPr(factory, "Garamond", "000000", "20", STHint.EAST_ASIA, true, false, false,
					false);
			RPr garamondRPr = getRPr(factory, "Garamond", "000000", "22", STHint.EAST_ASIA, false, false, false, false);

			RPr timesNewRomanRPr = getRPr(factory, "Times New Roman", "000000", "25", STHint.EAST_ASIA, false, false,
					false, false);

			int temp = 1;

			Tbl tableHead = factory.createTbl();
			setLandscapeTableAlign(factory, tableHead, JcEnumeration.CENTER);
			// addBorders(tableHead, "0");

			/**************************************************************************/

			String date = "";
			if (temp == 1) {
				date = report_created_date;
			} else {
				addParagraph(mp, factory);
			}
			temp++;

			mp.addObject(tableHead);

			/***************************************************************/

			Tbl table = factory.createTbl();
			addBorders(table, "2");

			/************************************************************************/
			Tr titleRow0 = factory.createTr();
			List<String> tableHeader0 = new ArrayList<String>();

			tableHeader0.add("Start Date");
			tableHeader0.add("Title");
			tableHeader0.add("Location");
			tableHeader0.add("Contractor");
			tableHeader0.add("Faculty");
			tableHeader0.add("Period");
			tableHeader0.add("Remarks");
			tableHeader0.add("Image");

			int columnNo = 1;
			for (String headerValue : tableHeader0) {
				int width = 0;
				if (1 == columnNo) {
					width = 1000;
				} else if (2 == columnNo) {
					width = 1000;
				} else if (3 == columnNo) {
					width = 850;
				} else if (4 == columnNo) {
					width = 900;
				} else if (5 == columnNo) {
					width = 700;
				} else if (6 == columnNo) {
					width = 600;
				} else if (7 == columnNo) {
					width = 750;
				} else if (8 == columnNo) {
					width = 3000;
				}
				columnNo++;
				addTableCellAndWidth(factory, wordMLPackage, titleRow0, headerValue, garamondBoldRPr,
						JcEnumeration.CENTER, true, "ecf2ff", width);
			}
			table.getContent().add(titleRow0);

			/*******************************************************************************/

			int sNo = 1;
			for (Training cObj1 : reportsData) {
				// Add image to the last column
				String imageFilePath = CommonConstants2.TRAINING_GALLERY_FILE_SAVING_PATH + "/" + cObj1.getTraining_id()
						+ "/" + cObj1.getFile_name();
				String trainingIdFolderPath = CommonConstants2.TRAINING_GALLERY_FILE_SAVING_PATH + "/"
						+ cObj1.getTraining_id();
				boolean hasBgColor = false;
				String backgroundColor = null;
				Tr contentRow = factory.createTr();

				tableHeader0.add("Start Date");
				tableHeader0.add("Title");
				tableHeader0.add("Location");
				tableHeader0.add("Contractor");
				tableHeader0.add("Faculty");
				tableHeader0.add("Period");
				tableHeader0.add("Remarks");
				tableHeader0.add("Image");

//				addTableCell(factory, wordMLPackage, contentRow, cObj1.getTraining_id(), garamondRPr,
//						JcEnumeration.CENTER, hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getStart_time(), timesNewRomanRPr,
						JcEnumeration.CENTER, hasBgColor, backgroundColor, backgroundColor);
//				addTableCell(factory, wordMLPackage, contentRow, cObj1.getEnd_time(), garamondRPr, JcEnumeration.CENTER,
//						hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getTitle(), timesNewRomanRPr, JcEnumeration.LEFT,
						hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getTraining_center(), timesNewRomanRPr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getContractor_name(), timesNewRomanRPr,
						JcEnumeration.CENTER, hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getFaculty_name(), timesNewRomanRPr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getPeriod_fk(), timesNewRomanRPr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor, backgroundColor);
//				addTableCell(factory, wordMLPackage, contentRow, cObj1.getSession_no(), garamondRPr, JcEnumeration.LEFT,
//						hasBgColor, backgroundColor, backgroundColor);
				addTableCell(factory, wordMLPackage, contentRow, cObj1.getRemarks(), timesNewRomanRPr,
						JcEnumeration.LEFT, hasBgColor, backgroundColor, backgroundColor);
				addTableCellWithImage(factory, wordMLPackage, contentRow, trainingIdFolderPath, imageFilePath, cObj1);

				table.getContent().add(contentRow);
			}

			setTableAlign(factory, table, JcEnumeration.CENTER);
			mp.addObject(table);

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**************************************************************************************************************/

	private static void addTableCellWithImage(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
			String trainingIdFolderPath, String imageFilePath, Training cObj1) throws Exception {
		Tc tableCell = factory.createTc();

		if (cObj1.getFile_name() != null) {
			tableCell.getContent().add(createImageParagraph(factory, wordMLPackage, imageFilePath));
		}
		// else condition for when the image file does not exist
		else {
			// Add an empty paragraph to keep the cell empty
			tableCell.getContent().add(factory.createP());
		}

		tableRow.getContent().add(tableCell);
	}

	/**************************************************************************************************************/

//	private static void addTableCellWithImage(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
//			String imageFilePath) throws Exception {
//		Tc tableCell = factory.createTc();
//		tableCell.getContent().add(createImageParagraph(factory, wordMLPackage, imageFilePath));
//		tableRow.getContent().add(tableCell);
//	}

	private static P createImageParagraph(ObjectFactory factory, WordprocessingMLPackage wordMLPackage,
			String imageFilePath) throws Exception {
		Inline inline = createInlineImage(wordMLPackage, imageFilePath);

		// Create paragraph properties and set the alignment to center
		PPr ppr = factory.createPPr();
		Jc jc = factory.createJc();
		jc.setVal(JcEnumeration.CENTER);
		ppr.setJc(jc);

		// Create the paragraph and set its properties
		P paragraph = factory.createP();
		paragraph.setPPr(ppr);

		// Create drawing and add inline image
		ObjectFactory drawingFactory = new ObjectFactory();
		Drawing drawing = drawingFactory.createDrawing();
		drawing.getAnchorOrInline().add(inline);

		paragraph.getContent().add(drawing);

		return paragraph;
	}

	private static Inline createInlineImage(WordprocessingMLPackage wordMLPackage, String imageFilePath)
			throws Exception {
		byte[] imageBytes = Files.readAllBytes(Paths.get(imageFilePath));
		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, imageBytes);

		Inline inline = imagePart.createImageInline(null, null, 0, 1, false);

		// Set image dimensions
		int defaultImageWidth = 250;
		int defaultImageHeight = 210;
		inline.getExtent().setCx(defaultImageWidth * 9525L);
		inline.getExtent().setCy(defaultImageHeight * 9525L);

		return inline;
	}

	/**************************************************************************************************************/

	public static void addImageToWord(WordprocessingMLPackage wordMLPackage, String imageFilePath) throws Exception {
		MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage,
				new File(imageFilePath));

		ObjectFactory factory = Context.getWmlObjectFactory();

		Inline inline = imagePart.createImageInline(null, null, 0, 1, false);

		// Reduce the image size
		int defaultImageWidth = 100;
		int defaultImageHeight = 50;
		inline.getExtent().setCx(defaultImageWidth * 9525L);
		inline.getExtent().setCy(defaultImageHeight * 9525L);

		Drawing drawing = factory.createDrawing();
		drawing.getAnchorOrInline().add(inline);

		P paragraph = factory.createP();
		paragraph.getContent().add(drawing);

		mainDocumentPart.getContent().add(paragraph);
	}

	/**************************************************************************************************************/

	private static void addParagraph(MainDocumentPart mp, ObjectFactory factory) {
		P p = factory.createP();
		R r = factory.createR();
		// Br br = factory.createBr();
		// r.getContent().add(br);
		p.getContent().add(r);

		mp.addObject(p);
	}

	/**
	 * @param titleRpr
	 * @param alignment
	 *********************************************************************************************************************/

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

		// Removing space in cells
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
		// P paragraph = objectFactory.createP();
		// R run = objectFactory.createR();
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
		tblwidth.setW(BigInteger.valueOf(5200)); // 5000 = 100%
		tblwidth.setType("pct");
		tablePr.setTblW(tblwidth);

		CTTblLayoutType tblLayoutType = new CTTblLayoutType();
		STTblLayoutType stTblLayoutType = STTblLayoutType.FIXED;
		tblLayoutType.setType(stTblLayoutType);
		tablePr.setTblLayout(tblLayoutType);

		table.setTblPr(tablePr);
	}

	public static void setLandscapeTableAlign(ObjectFactory factory, Tbl table, JcEnumeration jcEnumeration) {
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
		tblwidth.setW(BigInteger.valueOf(5400)); // 5000 = 100%
		tblwidth.setType("pct");
		tablePr.setTblW(tblwidth);

		CTTblLayoutType tblLayoutType = new CTTblLayoutType();
		STTblLayoutType stTblLayoutType = STTblLayoutType.FIXED;
		tblLayoutType.setType(stTblLayoutType);
		tablePr.setTblLayout(tblLayoutType);

		table.setTblPr(tablePr);
	}

	public static void addTableCell(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
			String content, RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor,
			String imageFilePath) {
		Tc tableCell = factory.createTc();
		P p = factory.createP();
		setParagraphAlign(factory, p, jcEnumeration);
		// Text t = factory.createText();
		// t.setValue(content);
		R run = factory.createR();
		run.setRPr(rpr);

		// run.getContent().add(t);

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

		// Removing space in cells
		PPr pPr = factory.createPPr();
		Spacing spacing = new Spacing();
		spacing.setBefore(BigInteger.ONE);
		spacing.setAfter(BigInteger.ONE);
		// spacing.setAfterLines(BigInteger.TEN);
		// spacing.setBeforeLines(BigInteger.TEN);
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

//		// Add the image to the last column
//		if (imageFilePath != null && !imageFilePath.isEmpty()) {
//			try {
//				addImageToWord(wordMLPackage, imageFilePath);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		tableCell.setTcPr(tcPr);
		tableRow.getContent().add(tableCell);
	}

	public static void addTableCellAndWidth(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow,
			String content, RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor,
			int columnWidth) {
		Tc tableCell = factory.createTc();

		P p = factory.createP();
		setParagraphAlign(factory, p, jcEnumeration);
		// Text t = factory.createText();
		// t.setValue(content);
		R run = factory.createR();
		run.setRPr(rpr);

		// run.getContent().add(t);

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

		// Removing space in cells
		PPr pPr = factory.createPPr();
		Spacing spacing = new Spacing();
		spacing.setBefore(BigInteger.ONE);
		spacing.setAfter(BigInteger.ONE);
		// spacing.setAfterLines(BigInteger.TEN);
		// spacing.setBeforeLines(BigInteger.TEN);
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

	public static void addTableCellWithTopBottomSpace(ObjectFactory factory, WordprocessingMLPackage wordMLPackage,
			Tr tableRow, String content, RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor,
			String backgroudColor) {
		Tc tableCell = factory.createTc();
		P p = factory.createP();
		setParagraphAlign(factory, p, jcEnumeration);
		// Text t = factory.createText();
		// t.setValue(content);
		R run = factory.createR();
		run.setRPr(rpr);

		// run.getContent().add(t);

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

		// Removing space in cells
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
