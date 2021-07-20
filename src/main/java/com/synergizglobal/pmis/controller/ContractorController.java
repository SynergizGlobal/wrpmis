package com.synergizglobal.pmis.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.utils.BufferUtil;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Color;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HdrFtrRef;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.STHint;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.ContractorService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.DocxTableCreationForContractReport;
import com.synergizglobal.pmis.common.DocxTableCreationForContractorReport;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.ContractPaginationObject;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.ContractorPaginationObject;


@Controller
public class ContractorController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ContractorController.class);

	@Autowired
	ContractorService contractorService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${record.dataexport.success}")
	public String dataExportSucess;
	
	@Value("${record.dataexport.invalid.directory}")
	public String dataExportInvalid;
	
	@Value("${record.dataexport.error}")
	public String dataExportError;
	
	@Value("${record.dataexport.nodata}")
	public String dataExportNoData;

	@RequestMapping(value="/contractor",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Contractor(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.contractorGrid);
		try {
			List<Contractor> contractorsList = contractorService.getContractorsList();
			model.addObject("contractorsList", contractorsList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/contractorslist",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView ContractorsList(HttpServletRequest request,HttpServletResponse response,HttpSession session, RedirectAttributes attributes){
		ModelAndView model = new ModelAndView("redirect:/contract-report");
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
            String currentDate = sqlDate.format(date);
            
	           
			boolean flag = generatContractorsListReport(response,currentDate);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generatContractReport : " + e.getMessage());
		}		
		return model;
	}
	
	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage,
			ObjectFactory factory, HeaderPart sourcePart,String headerText) throws Exception {
		Hdr hdr = factory.createHdr();
		
		P p = factory.createP();
		R r = factory.createR();		
		
		RPr boldRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA,
				true, false, false, false);
		
		
		if(!StringUtils.isEmpty(headerText)) {			
			PPr pPr = p.getPPr();
			if (pPr == null) {
				pPr = factory.createPPr();
			}
			Jc jc = pPr.getJc();
			if (jc == null) {
				jc = new Jc();
			}
			jc.setVal(JcEnumeration.CENTER);
			pPr.setJc(jc);
			p.setPPr(pPr);

			Text txt = factory.createText();
			txt.setValue(headerText);
			r = factory.createR();
			r.getContent().add(txt);
			r.setRPr(boldRPr);
			p.getContent().add(r);
		}
		
		
		
		hdr.getContent().add(p);	
		
		return hdr;
	}

	
	public Relationship createHeaderPart(
			WordprocessingMLPackage wordprocessingMLPackage,
			MainDocumentPart t, ObjectFactory factory,
			String imagePath, JcEnumeration imageAlignment, String headerTextMiddle, String headerTextRight,int tabs1,int tabs2) throws Exception {
		HeaderPart headerPart = new HeaderPart();
		Relationship rel = t.addTargetPart(headerPart);
		// After addTargetPart, so image can be added properly
		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, factory,
				headerPart,imagePath,imageAlignment,headerTextMiddle,headerTextRight,tabs1,tabs2));
		return rel;
	}
	
	public Relationship createHeaderPart(
			WordprocessingMLPackage wordprocessingMLPackage,
			MainDocumentPart t, ObjectFactory factory,String headerText) throws Exception {
		HeaderPart headerPart = new HeaderPart();
		Relationship rel = t.addTargetPart(headerPart);
		// After addTargetPart, so image can be added properly
		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, factory,
				headerPart,headerText));
		return rel;
	}
	
	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage,
			ObjectFactory factory, HeaderPart sourcePart,
			String imagePath, JcEnumeration imageAlignment, String headerTextMiddle, String headerTextRight,int tabs1,int tabs2) throws Exception {
		Hdr hdr = factory.createHdr();
		//String path = CommonConstants.DOCX_LOGO+"/docx-logo.png";
		//String path = CommonConstants.DOCX_LOGO+"/"+"ircon-report-header.png";
		P p = factory.createP();
		R r = factory.createR();
		if(!StringUtils.isEmpty(imagePath)) {
			File file = new File(imagePath);
			java.io.InputStream is = new java.io.FileInputStream(file);
			
			String filenameHint = null;
	        String altText = null;

	        int id1 = 0;
	        int id2 = 1;
			p = newImage(wordprocessingMLPackage, factory, sourcePart,
					BufferUtil.getBytesFromInputStream(is), altText,
					filenameHint, id1, id2, imageAlignment);
		}
		hdr.getContent().add(p);
		
		/********************************************/
		
		/*p = factory.createP();
		r = factory.createR();
		
		RPr boldRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA,
				true, false, false, false);
		
		
		if(!StringUtils.isEmpty(headerTextMiddle)) {
			for (int i = 0; i < tabs1; i++) {
				R.Tab rtab = factory.createRTab();
		        JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped = factory.createRTab(rtab);
		        r.getContent().add( rtabWrapped);
			}			
			p.getContent().add(r);
		
			Text txt = factory.createText();
			txt.setValue(headerTextMiddle);
			r = factory.createR();
			r.getContent().add(txt);
			r.setRPr(boldRPr);
			p.getContent().add(r);
		}
		
		if(!StringUtils.isEmpty(headerTextRight)) {
			for (int i = 0; i < tabs2; i++) {
				R.Tab rtab = factory.createRTab();
		        JAXBElement<org.docx4j.wml.R.Tab> rtabWrapped = factory.createRTab(rtab);
		        r.getContent().add( rtabWrapped);
			}
			Text txt = factory.createText();
			txt.setValue(headerTextRight);
			r = factory.createR();
			r.getContent().add(txt);
			r.setRPr(boldRPr);
			p.getContent().add(r);
		}
		
		hdr.getContent().add(p);*/	
		
		/*******************************************************************************/

		/*RPr titleRpr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
		
		Tbl table = factory.createTbl();
		
		TblPr tableProps = new TblPr();
		CTTblLayoutType tblLayoutType = new CTTblLayoutType();
		STTblLayoutType stTblLayoutType = STTblLayoutType.FIXED;
		tblLayoutType.setType(stTblLayoutType);
		tableProps.setTblLayout(tblLayoutType);
		table.setTblPr(tableProps);
		
		Tr titleRow = factory.createTr();
		addTableCell(factory, wordprocessingMLPackage, titleRow, "", titleRpr, JcEnumeration.CENTER, true, "ffffff");
		addTableCell(factory, wordprocessingMLPackage, titleRow, headerTextMiddle, titleRpr, JcEnumeration.CENTER, true,
				"ffffff");
		addTableCell(factory, wordprocessingMLPackage, titleRow, headerTextRight, titleRpr, JcEnumeration.RIGHT, true,
				"ffffff");
		table.getContent().add(titleRow);
		setTableAlign(factory, table, JcEnumeration.CENTER);
		
		hdr.getContent().add(table);*/
		
		/*******************************************************************************/
		
		p = factory.createP();
		r = factory.createR();
		RPr garamondBoldRPr = getRPr(factory, "Garamond", "000000", "28", STHint.EAST_ASIA,
				true, false, false, false);
		
		
		if(!StringUtils.isEmpty(headerTextMiddle)) {
			PPr pPr = p.getPPr();
			if (pPr == null) {
				pPr = factory.createPPr();
			}
			Jc jc = pPr.getJc();
			if (jc == null) {
				jc = new Jc();
			}
			jc.setVal(JcEnumeration.CENTER);
			pPr.setJc(jc);
			p.setPPr(pPr);
			
			Text txt = factory.createText();
			txt.setValue(headerTextMiddle);
			r = factory.createR();
			r.getContent().add(txt);
			r.setRPr(garamondBoldRPr);
			p.getContent().add(r);
		}		
		hdr.getContent().add(p);
		/**************************************************************/
		
		RPr calibriBoldRPr = getRPr(factory, "Calibri (Body)", "000000", "20", STHint.EAST_ASIA,
				true, false, false, false);
		p = factory.createP();
		r = factory.createR();
		if(!StringUtils.isEmpty(headerTextRight)) {
			PPr pPr = p.getPPr();
			if (pPr == null) {
				pPr = factory.createPPr();
			}
			Jc jc = pPr.getJc();
			if (jc == null) {
				jc = new Jc();
			}
			jc.setVal(JcEnumeration.RIGHT);
			pPr.setJc(jc);
			p.setPPr(pPr);
			
			Text txt = factory.createText();
			txt.setValue(headerTextRight);
			r = factory.createR();
			r.getContent().add(txt);
			r.setRPr(calibriBoldRPr);
			p.getContent().add(r);
		}
		hdr.getContent().add(p);
		
		/*******************************************************************************/
		
		return hdr;
	}
	
	public P newImage(WordprocessingMLPackage wordMLPackage,
			ObjectFactory factory, HeaderPart sourcePart, byte[] bytes,
			String filenameHint, String altText, int id1, int id2,
			JcEnumeration jcEnumeration) throws Exception {
		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage,sourcePart, bytes);
		Inline inline = imagePart.createImageInline(filenameHint, altText, id1,
				id2, false);
		P p = factory.createP();
		R run = factory.createR();
		p.getContent().add(run);
		Drawing drawing = factory.createDrawing();
		run.getContent().add(drawing);
		drawing.getAnchorOrInline().add(inline);
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
		return p;
	}
	
    public static P newImage( WordprocessingMLPackage wordMLPackage, byte[] bytes, 
            String filenameHint, String altText, int id1, int id2) throws Exception {
    	
    	Integer HEADER_TOP_OFFSET = 0;
    	
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
        Inline inline = imagePart.createImageInline( filenameHint, altText, id1, id2,8500,false);
        
        /*String anchorXml = XmlUtils.marshaltoString(inline, true, false, Context.jc, Namespaces.NS_WORD12, "anchor",
                Inline.class);
           
             org.docx4j.dml.ObjectFactory dmlFactory = new org.docx4j.dml.ObjectFactory();
            org.docx4j.dml.wordprocessingDrawing.ObjectFactory wordDmlFactory = new org.docx4j.dml.wordprocessingDrawing.ObjectFactory();

        
        Anchor anchor = (Anchor) XmlUtils.unmarshalString(anchorXml, Context.jc, Anchor.class);
        
        anchor.setSimplePos(dmlFactory.createCTPoint2D());
       anchor.getSimplePos().setX(20L);
       anchor.getSimplePos().setY(50L);
       anchor.setSimplePosAttr(false);
       anchor.setPositionH(wordDmlFactory.createCTPosH());
       anchor.getPositionH().setAlign(STAlignH.CENTER);
       anchor.getPositionH().setRelativeFrom(STRelFromH.MARGIN);
       anchor.setPositionV(wordDmlFactory.createCTPosV());
       anchor.getPositionV().setPosOffset(HEADER_TOP_OFFSET);
       anchor.getPositionV().setRelativeFrom(STRelFromV.PAGE);
       anchor.setWrapNone(wordDmlFactory.createCTWrapNone());*/
       
        
        ObjectFactory factory = new ObjectFactory();
        P  p = factory.createP();
        R  run = factory.createR();
        p.getContent().add(run);
        Drawing drawing = factory.createDrawing();      
        run.getContent().add(drawing);       
        drawing.getAnchorOrInline().add(inline);
        
        PPr pPr = p.getPPr();
		if (pPr == null) {
			pPr = factory.createPPr();
		}
		Jc jc = pPr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(JcEnumeration.CENTER);
		pPr.setJc(jc);
		p.setPPr(pPr);

        return p;
    } 
	public void createHeaderReference(
			WordprocessingMLPackage wordprocessingMLPackage,
			MainDocumentPart t, ObjectFactory factory, Relationship relationship)
			throws InvalidFormatException {
		List<SectionWrapper> sections = wordprocessingMLPackage
				.getDocumentModel().getSections();
		SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
		// There is always a section wrapper, but it might not contain a sectPr
		if (sectPr == null) {
			sectPr = factory.createSectPr();
			t.addObject(sectPr);
			sections.get(sections.size() - 1).setSectPr(sectPr);
		}
		/*HeaderReference headerReference = factory.createHeaderReference();
		headerReference.setId(relationship.getId());
		headerReference.setType(HdrFtrRef.FIRST);
		sectPr.getEGHdrFtrReferences().add(headerReference);*/
		
		HeaderReference headerReference = factory.createHeaderReference();
		headerReference = factory.createHeaderReference();
		headerReference.setId(relationship.getId());
		//headerReference.setType(HdrFtrRef.DEFAULT);
		headerReference.setType(HdrFtrRef.FIRST);
		sectPr.getEGHdrFtrReferences().add(headerReference);
		
		/*headerReference = factory.createHeaderReference();
		headerReference.setId(relationship.getId());
		//headerReference.setType(HdrFtrRef.DEFAULT);
		headerReference.setType(HdrFtrRef.FIRST);
		sectPr.getEGHdrFtrReferences().add(headerReference);*/
		
		BooleanDefaultTrue value = new BooleanDefaultTrue();
        value.setVal(Boolean.TRUE);
        sectPr.setTitlePg(value);        
	}
	
	public Relationship createFooterPageNumPart(
			WordprocessingMLPackage wordprocessingMLPackage,
			MainDocumentPart t, ObjectFactory factory) throws Exception {
		FooterPart footerPart = new FooterPart();
		footerPart.setPackage(wordprocessingMLPackage);
		footerPart.setJaxbElement(createFooterWithPageNr(factory));
		return t.addTargetPart(footerPart);
	}
	
	public Ftr createFooterWithPageNr(ObjectFactory factory) {
		Ftr ftr = factory.createFtr();
		P paragraph = factory.createP();
		RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);
		R run = factory.createR();
		run.setRPr(fontRPr);
		paragraph.getContent().add(run);

		addPageTextField(factory, paragraph, "Page ");
		addFieldBegin(factory, paragraph);
		addPageNumberField(factory, paragraph);
		addFieldEnd(factory, paragraph);
		addPageTextField(factory, paragraph, " of ");

		//addPageTextField(factory, paragraph, "Test");
		addFieldBegin(factory, paragraph);
		addTotalPageNumberField(factory, paragraph);
		addFieldEnd(factory, paragraph);
		//addPageTextField(factory, paragraph, "Test");
		setParagraphAlign(factory, paragraph, JcEnumeration.CENTER);
		ftr.getContent().add(paragraph);
		return ftr;
	}
	
	public void setParagraphAlign(ObjectFactory factory, P p,
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
	
	
	
	public void addFieldBegin(ObjectFactory factory, P paragraph) {
		R run = factory.createR();
		FldChar fldchar = factory.createFldChar();
		fldchar.setFldCharType(STFldCharType.BEGIN);
		run.getContent().add(fldchar);
		paragraph.getContent().add(run);
	}

	public void addFieldEnd(ObjectFactory factory, P paragraph) {
		FldChar fldcharend = factory.createFldChar();
		fldcharend.setFldCharType(STFldCharType.END);
		R run3 = factory.createR();
		run3.getContent().add(fldcharend);
		paragraph.getContent().add(run3);
	}

	public void addPageNumberField(ObjectFactory factory, P paragraph) {
		R run = factory.createR();
		Text txt = new Text();
		txt.setSpace("preserve");
		txt.setValue("PAGE  \\* MERGEFORMAT ");
		run.getContent().add(factory.createRInstrText(txt));
		paragraph.getContent().add(run);
	}

	public void addTotalPageNumberField(ObjectFactory factory, P paragraph) {
		R run = factory.createR();
		Text txt = new Text();
		txt.setSpace("preserve");
		txt.setValue("NUMPAGES  \\* MERGEFORMAT ");
		run.getContent().add(factory.createRInstrText(txt));
		paragraph.getContent().add(run);
	}

	private void addPageTextField(ObjectFactory factory, P paragraph,
			String value) {
		R run = factory.createR();
		Text txt = new Text();
		txt.setSpace("preserve");
		txt.setValue(value);
		run.getContent().add(txt);
		paragraph.getContent().add(run);
	}
	
	public RPr getRPr(ObjectFactory factory, String fontFamily,
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
	
	public void createFooterReference(
			WordprocessingMLPackage wordprocessingMLPackage,
			MainDocumentPart t, ObjectFactory factory, Relationship relationship)
			throws InvalidFormatException {
		List<SectionWrapper> sections = wordprocessingMLPackage
				.getDocumentModel().getSections();
		SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
		// There is always a section wrapper, but it might not contain a sectPr
		if (sectPr == null) {
			sectPr = factory.createSectPr();
			t.addObject(sectPr);
			sections.get(sections.size() - 1).setSectPr(sectPr);
		}
		FooterReference footerReference = factory.createFooterReference();
		footerReference.setId(relationship.getId());
		footerReference.setType(HdrFtrRef.FIRST);
		sectPr.getEGHdrFtrReferences().add(footerReference);
		
		footerReference = factory.createFooterReference();
		footerReference.setId(relationship.getId());
		footerReference.setType(HdrFtrRef.DEFAULT);
		sectPr.getEGHdrFtrReferences().add(footerReference);
	}
	
	private boolean generatContractorsListReport(HttpServletResponse response,String currentDate) {
		//XWPFDocument document = new XWPFDocument(); 
		//StringBuilder repositoryExcerpts = new StringBuilder(); 
		byte[] byteArray;        
        //ObjectFactory objectFactory = new ObjectFactory();
		boolean flag = false;
		try{			
			//DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
			String report_created_date = df.format(new Date()); 
			
			List<Contractor> list = contractorService.getContractorsList();
			
			boolean landscape = true;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);
			
			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();
			
			
			
			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";

			JcEnumeration imageAlignment = JcEnumeration.CENTER;
			
			String headerTextMiddle = "List of Contractors";
			
			//String headerTextRight = report_created_date;
			String headerTextRight = null;
			
			//String headerText = "PMIS Report - Contract Details";
			
			int tabs1 = 8;int tabs2 = 5;
			
			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,imagePath,imageAlignment,headerTextMiddle,headerTextRight,tabs1,tabs2);
			//Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 
			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);
			 			  
			
			DocxTableCreationForContractorReport.createTableForContractorListReport(wordMLPackage, mp, factory, list, report_created_date);
	    	  
						
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){	
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
				InputStream targetStream = new ByteArrayInputStream(byteArray);
				String FILE_EXTENSION = ".docx";
				String fileName = "List of Contractors Report - " + currentDate + FILE_EXTENSION;
				
				response.setContentType("application/.csv");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setContentType("application/vnd.ms-excel");
				response.setContentType("application/pdf");
				response.setContentType("application/msword");
				response.setContentType("application/vnd.ms-word");
				// add response header
				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
				//copies all bytes from a file to an output stream
				IOUtils.copy(targetStream, response.getOutputStream());
				//flushes output stream
				response.getOutputStream().flush();
				
				flag = true;
		    }catch (Exception e) {
				e.printStackTrace();
				logger.error("generatContractorListReport >> FileNotFoundException occurs.." + e.getMessage());
				flag = false;
		    }	
		 	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generatContractorListReport >> " + e.getMessage());
			flag = false;
		}
		
		return flag;
	}	
	
	
		
	@RequestMapping(value = "/ajax/get-contractor", method = { RequestMethod.POST, RequestMethod.GET }) 
	public void getActivitiesList(@ModelAttribute Contractor obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");

			pw = response.getWriter();
			//Fetch the page number from client
			Integer pageNumber = 0;
			Integer pageDisplayLength = 0;
			if (null != request.getParameter("iDisplayStart")) {
				pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			}
			//Fetch search parameter
			String searchParameter = request.getParameter("sSearch");

			//Fetch Page display length
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			List<Contractor> contractorList = new ArrayList<Contractor>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				contractorList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				contractorList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//contractorList = getListBasedOnSearchParameter(searchParameter,contractorList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ContractorPaginationObject personJsonObject = new ContractorPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(contractorList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Contractor obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = contractorService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	public List<Contractor> createPaginationData(int startIndex, int offset,Contractor obj, String searchParameter) {
		List<Contractor> objList = null;
		try {
			objList = contractorService.getContractorsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getPanNumberListFormContactor", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contractor> getPanNumberList(@ModelAttribute Contractor obj) {
		List<Contractor> objList = null;
		try {
			objList = contractorService.getPanNumberList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPanNumberList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/add-contractor-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractorForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditContractor);
			model.addObject("action", "add");
			List<Contractor> Specialization = contractorService.getContractorSpecialization();
			model.addObject("Specialization", Specialization);
		}catch (Exception e) {
				logger.error("Contractor : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getContractorForm(@ModelAttribute Contractor obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditContractor);
			model.addObject("action", "edit");
			Contractor contractorDetails = contractorService.getContractor(obj);
			model.addObject("contractorDetails", contractorDetails);
			List<Contractor> Specialization = contractorService.getContractorSpecialization();
			model.addObject("Specialization", Specialization);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("Contractor : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addContractor(@ModelAttribute Contractor obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor");
			boolean flag =  contractorService.addContractor(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding Contractor is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contractor is failed. Try again.");
			logger.error("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateContractor(@ModelAttribute Contractor obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor");
			boolean flag =  contractorService.updateContractor(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Contractor is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Contractor is failed. Try again.");
			logger.error("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteContractor(@ModelAttribute Contractor obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor");
			boolean flag =  contractorService.deleteContractorRow(obj);
		}catch (Exception e) {
			logger.error("Contractor : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-contractor", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportContractor(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Contractor contractor,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.contractorGrid);
		List<Contractor> dataList = new ArrayList<Contractor>();
		try {
			view.setViewName("redirect:/contractor");
			dataList =  contractorService.getContractorsList();
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Contractor ID");
	            headingRow.createCell((short)1).setCellValue("Contractor Name");
	         	headingRow.createCell((short)2).setCellValue("Specialization");
	            headingRow.createCell((short)3).setCellValue("Address");
	            headingRow.createCell((short)4).setCellValue("Remarks");
	            short rowNo = 1;
	            for (Contractor obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getContractor_id());
	                row.createCell((short)1).setCellValue(obj.getContractor_name());
	                row.createCell((short)2).setCellValue(obj.getContractor_specilization_fk());
	                row.createCell((short)3).setCellValue(obj.getAddress());
	                row.createCell((short)4).setCellValue(obj.getRemarks());
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Contractor_"+dateFormat.format(date);
                
                try{
	                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
	                workBook.write(fos);
	                fos.flush();*/
	            	
	               response.setContentType("application/.csv");
	 			   response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	 			   response.setContentType("application/vnd.ms-excel");
	 			   // add response header
	 			   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".xlsx");
	 			   
	 			    //copies all bytes from a file to an output stream
	 			   workBook.write(response.getOutputStream()); // Write workbook to response.
		           workBook.close();
	 			    //flushes output stream
	 			    response.getOutputStream().flush();
	            	
	                
	                attributes.addFlashAttribute("success",dataExportSucess);
	            	//response.setContentType("application/vnd.ms-excel");
	            	//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
	            	//XSSFWorkbook  workbook = new XSSFWorkbook ();
	            	// ...
	            	// Now populate workbook the usual way.
	            	// ...
	            	//workbook.write(response.getOutputStream()); // Write workbook to response.
	            	//workbook.close();
	            }catch(FileNotFoundException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportInvalid);
	            }catch(IOException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportError);
	            }
			}else{
				attributes.addFlashAttribute("error",dataExportNoData);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}

