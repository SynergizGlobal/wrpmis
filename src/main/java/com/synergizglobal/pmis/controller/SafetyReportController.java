package com.synergizglobal.pmis.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.docx4j.Docx4jProperties;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.model.structure.PageDimensions;
import org.docx4j.model.structure.PageSizePaper;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.DocPropsCorePart;
import org.docx4j.openpackaging.parts.DocPropsExtendedPart;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.DocumentSettingsPart;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTSettings;
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
import org.docx4j.wml.STBrType;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.STHint;
import org.docx4j.wml.STPageOrientation;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.SectPr.PgSz;
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

import com.synergizglobal.pmis.Iservice.SafetyReportService;
import com.synergizglobal.pmis.common.DocxTableCreation;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Safety;

@Controller
public class SafetyReportController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	public static Logger logger = Logger.getLogger(SafetyReportController.class);	
	
	@Autowired
	SafetyReportService safetyService;
	
	
	@Value("${common.error.message}")
	public String commonError;
	

	@RequestMapping(value="/safety-report",method=RequestMethod.GET)
	public ModelAndView safetysReport(@ModelAttribute Safety obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.safetyReport);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("safetysReport : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksListInSafetyReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getWorksListInSafetyReport(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getWorksListInSafetyReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListInSafetyReport : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListInSafetyReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getContractsListInSafetyReport(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getContractsListInSafetyReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListInSafetyReport : " + e.getMessage());
		}
		return objsList;
	}
	
	
	@RequestMapping(value = "/generate-safety-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generatePendingSafetyReport(@ModelAttribute Safety obj ,HttpServletRequest request,HttpServletResponse response,HttpSession session, RedirectAttributes attributes){
		ModelAndView model = new ModelAndView("redirect:/risk-analysis-report");
		try{            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
            String currentDate = sqlDate.format(date);
           
			boolean flag = generatePendingSafetyReport(response,currentDate,obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generatePendingSafetyReport : " + e.getMessage());
		}
		return model;
     }

	private boolean generatePendingSafetyReport(HttpServletResponse response, String currentDate, Safety obj) {
		//XWPFDocument document = new XWPFDocument(); 
		//StringBuilder repositoryExcerpts = new StringBuilder(); 
		byte[] byteArray;        
        //ObjectFactory objectFactory = new ObjectFactory();
		boolean flag = false;
		try{			
			
			//obj.setStatus_fk("Closed");
			List<Safety> safetyData = safetyService.getSafetyReportData(obj);
			
			boolean landscape = true;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);
			
			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();
			
			String headerText = "Pending Safety Incidents";
			
			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 
			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);
			 			  
			DocxTableCreation.createTableForSafetyReport(wordMLPackage, mp, factory,safetyData);
	    	  
						
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){	
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
				InputStream targetStream = new ByteArrayInputStream(byteArray);
				String FILE_EXTENSION = ".docx";
				String fileName = "Safety Report - " + currentDate + FILE_EXTENSION;
				
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
				logger.error("generatePendingSafetyReport >> FileNotFoundException occurs.." + e.getMessage());
				flag = false;
		    }	
		 	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generatePendingSafetyReport >> " + e.getMessage());
			flag = false;
		}
		
		return flag;
	}
	
	/**
	 * Creates a WordprocessingMLPackage, using default page size and orientation.
	 * From 2.7.1, these are read from docx4j.properties, or if not found, default
	 * to A4 portrait.
	 * 
	 * The WordprocessingMLPackage contains a MainDocumentPart (with content), 
	 * Styles part, DocPropsCorePart part, and DocPropsExtendedPart.
	 */
	public static WordprocessingMLPackage createPackage() throws InvalidFormatException {
		
		String papersize= Docx4jProperties.getProperties().getProperty("docx4j.PageSize", "A4");
		logger.info("Using paper size: " + papersize);
		
		String landscapeString = Docx4jProperties.getProperties().getProperty("docx4j.PageOrientationLandscape", "false");
		boolean landscape= Boolean.parseBoolean(landscapeString);
		logger.info("Landscape orientation: " + landscape);
		
		return createPackage(
				PageSizePaper.valueOf(papersize), landscape); 
	}
	
	/**
	 * Creates a WordprocessingMLPackage, containing a MainDocumentPart (with content), 
	 * Styles part, DocPropsCorePart part, and DocPropsExtendedPart.
	 * 
	 * The content contains sectPr specifying paper size and orientation.
	 * 
	 * @param sz
	 * @param landscape
	 * @return
	 * @throws InvalidFormatException
	 */
	public static WordprocessingMLPackage createPackage(PageSizePaper sz, boolean landscape ) throws InvalidFormatException {
		
				
		// Create a package
		WordprocessingMLPackage wmlPack = new WordprocessingMLPackage();

		// Create main document part
		MainDocumentPart wordDocumentPart = new MainDocumentPart();		
		
		// Create main document part content
		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
		org.docx4j.wml.Body  body = factory.createBody();		
		org.docx4j.wml.Document wmlDocumentEl = factory.createDocument();
		
		wmlDocumentEl.setBody(body);
		
		// Create a basic sectPr using our Page model
		PageDimensions page = new PageDimensions();
		page.setPgSize(sz, landscape);
		
		SectPr sectPr = factory.createSectPr();
		body.setSectPr(sectPr);
		sectPr.setPgSz(  page.getPgSz() );
		sectPr.setPgMar( page.getPgMar() );
				
		// Put the content in the part
		wordDocumentPart.setJaxbElement(wmlDocumentEl);
						
		// Add the main document part to the package relationships
		// (creating it if necessary)
		wmlPack.addTargetPart(wordDocumentPart);
				
		// Create a styles part
		Part stylesPart = new org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart();
		try {
			((org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart) stylesPart)
					.unmarshalDefaultStyles();
			
			// Add the styles part to the main document part relationships
			// (creating it if necessary)
			wordDocumentPart.addTargetPart(stylesPart); // NB - add it to main doc part, not package!			
			
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();	
			logger.error(e.getMessage(), e);
		}
		
		// Metadata: docx4j 2.7.1 can populate some of this from docx4j.properties
		// See SaveToZipFile
		DocPropsCorePart core = new DocPropsCorePart();
		org.docx4j.docProps.core.ObjectFactory coreFactory = new org.docx4j.docProps.core.ObjectFactory();
		core.setJaxbElement(coreFactory.createCoreProperties() );
		wmlPack.addTargetPart(core);
			
		
		DocPropsExtendedPart app = new DocPropsExtendedPart();
		org.docx4j.docProps.extended.ObjectFactory extFactory = new org.docx4j.docProps.extended.ObjectFactory();
		app.setJaxbElement(extFactory.createProperties() );
		wmlPack.addTargetPart(app);	
		
    	// DocumentSettingsPart (/word/settings.xml)
        // Set overrideTableStyleFontSizeAndJustification to true,
        // like Word 2010/2013/2016.  Since v3.3.0
    	DocumentSettingsPart dsp = new DocumentSettingsPart();
    	wmlPack.getMainDocumentPart().addTargetPart(dsp);
    	dsp.setJaxbElement(new CTSettings());
    	dsp.setOverrideTableStyleFontSizeAndJustification(true);
				
		// Return the new package
		return wmlPack;
		
	}
	

	public void setDocSectionBreak(WordprocessingMLPackage wordPackage,String orientation){
	      try{
	         SectPr sectPr=null;
	         ObjectFactory objectFactory = new ObjectFactory();
	         sectPr = objectFactory.createSectPr();
	    	  
	    	  
	         PgSz landscape = new PgSz(); 
	          
	         if("landscape".equalsIgnoreCase(orientation)){
	        	 landscape.setH(BigInteger.valueOf(11906));
		         landscape.setW(BigInteger.valueOf(16383));		          
		         /*sectPr = (org.docx4j.wml.SectPr)org.docx4j.XmlUtils.unmarshalString("<w:sectPr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
	                    + "<w:pgSz w:w=\"16839\" w:h=\"11907\" w:orient=\""+temp.orientation+"\"/>"
	                    + "</w:sectPr>");*/
	             landscape.setOrient(STPageOrientation.LANDSCAPE);   
	         }else{
	             /*sectPr = (org.docx4j.wml.SectPr)org.docx4j.XmlUtils.unmarshalString("<w:sectPr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
	                          + "<w:pgSz w:w=\"16839\" w:h=\"11907\" />"                          + "</w:sectPr>");*/
	             landscape.setOrient(STPageOrientation.PORTRAIT);
	         }
	         sectPr.setPgSz(landscape);
	         P p = objectFactory.createP();
	         PPr createPPr = objectFactory.createPPr();
	         createPPr.setSectPr(sectPr);
	         p.setPPr(createPPr);
	         
	         /*wordPackage.getMainDocumentPart().addAltChunk(
	               org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType.Html,
	               sectionContent.toString().getBytes());*/
	         /*org.docx4j.wml.Br objBr = new org.docx4j.wml.Br();
	         objBr.setType(org.docx4j.wml.STBrType.PAGE);
	         wordPackage.getMainDocumentPart().addObject(objBr);*/
	         
	         wordPackage.getMainDocumentPart().addObject(p);
	    }catch (Exception e) {
			e.printStackTrace();
		}
	      
	}
	
	 private void changeOrientation(MainDocumentPart mdp, STPageOrientation stPageOrientation) {
          ObjectFactory objectFactory = new ObjectFactory();
          SectPr sectionLandscape = objectFactory.createSectPr();
          SectPr.PgSz landscape = new SectPr.PgSz();
          landscape.setOrient(stPageOrientation);
          landscape.setH(BigInteger.valueOf(11906));
          landscape.setW(BigInteger.valueOf(16383));
          sectionLandscape.setPgSz(landscape);
          org.docx4j.wml.P p = objectFactory.createP();
          PPr createPPr = objectFactory.createPPr();
          createPPr.setSectPr(sectionLandscape);
          p.setPPr(createPPr);
          mdp.addObject(p);
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
    
    private static void addPageBreak(MainDocumentPart documentPart) {
		 ObjectFactory objectFactory = new ObjectFactory();
	        P paragraph = objectFactory.createP();
	        R run = objectFactory.createR();
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

	private String getAlphabet(int i) {
		 return i < 0 ? "" : getAlphabet((i / 26) - 1) + (char)(65 + i % 26);
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
			ObjectFactory factory, HeaderPart sourcePart,String headerText) throws Exception {
		Hdr hdr = factory.createHdr();
		
		P p = factory.createP();
		R r = factory.createR();		
		
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
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
	
	public void addPageBreak(WordprocessingMLPackage wordMLPackage,
			ObjectFactory factory) {
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		Br breakObj = new Br();
		breakObj.setType(STBrType.PAGE);
		P paragraph = factory.createP();
		paragraph.getContent().add(breakObj);
		documentPart.addObject(paragraph);
	}
	
	
	public void addEmptyParagraph(WordprocessingMLPackage wordMLPackage,
			MainDocumentPart t, ObjectFactory factory) throws Exception {
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, false, false, false);
		P paragraph = factory.createP();
		setParagraphAlign(factory, paragraph, JcEnumeration.CENTER);
		Text txt = factory.createText();
		txt.setValue("");
		R run = factory.createR();
		run.getContent().add(txt);
		run.setRPr(titleRPr);
		paragraph.getContent().add(run);
		t.addObject(paragraph);
	}
	
	
	public void addHeading(WordprocessingMLPackage wordMLPackage,
			MainDocumentPart t, ObjectFactory factory,String contentValue) throws Exception {
		RPr titleRPr = getRPr(factory, "ralewaymedium", "000000", "28", STHint.EAST_ASIA,
				true, true, false, false);
		RPr boldRPr = getRPr(factory, "ralewaymedium", "000000", "22", STHint.EAST_ASIA,
				true, false, false, false);
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
				false, false, false, false);		
		
		P paragraph = factory.createP();
		setParagraphAlign(factory, paragraph, JcEnumeration.CENTER);
		Text txt = factory.createText();
		txt.setValue(contentValue);
		R run = factory.createR();
		run.getContent().add(txt);
		run.setRPr(titleRPr);
		paragraph.getContent().add(run);
		t.addObject(paragraph);		
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
		RPr fontRPr = getRPr(factory, "ralewaymedium", "000000", "20", STHint.EAST_ASIA,
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

	public Hdr getTextHdr(WordprocessingMLPackage wordprocessingMLPackage,
			ObjectFactory factory, Part sourcePart, String content,
			JcEnumeration jcEnumeration) throws Exception {
		Hdr hdr = factory.createHdr();
		P headP = factory.createP();
		Text text = factory.createText();
		text.setValue(content);
		R run = factory.createR();
		run.getContent().add(text);
		headP.getContent().add(run);

		PPr pPr = headP.getPPr();
		if (pPr == null) {
			pPr = factory.createPPr();
		}
		Jc jc = pPr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(jcEnumeration);
		pPr.setJc(jc);
		headP.setPPr(pPr);
		hdr.getContent().add(headP);
		return hdr;
	}

	public Ftr getTextFtr(WordprocessingMLPackage wordprocessingMLPackage,
			ObjectFactory factory, Part sourcePart, String content,
			JcEnumeration jcEnumeration) throws Exception {
		Ftr ftr = factory.createFtr();
		P footerP = factory.createP();
		Text text = factory.createText();
		text.setValue(content);
		R run = factory.createR();
		run.getContent().add(text);
		footerP.getContent().add(run);

		PPr pPr = footerP.getPPr();
		if (pPr == null) {
			pPr = factory.createPPr();
		}
		Jc jc = pPr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(jcEnumeration);
		pPr.setJc(jc);
		footerP.setPPr(pPr);
		ftr.getContent().add(footerP);
		return ftr;
	}
}
