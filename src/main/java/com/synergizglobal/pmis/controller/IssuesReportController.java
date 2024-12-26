package com.synergizglobal.pmis.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.docx4j.utils.BufferUtil;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTSettings;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.CTTblLayoutType;
import org.docx4j.wml.CTVerticalJc;
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
import org.docx4j.wml.PPrBase.Spacing;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.STFldCharType;
import org.docx4j.wml.STHint;
import org.docx4j.wml.STPageOrientation;
import org.docx4j.wml.STTblLayoutType;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.SectPr.PgSz;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.TcPrInner.TcBorders;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.IssuesReportService;
import com.synergizglobal.pmis.common.DocxTableCreation;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Issue;

@Controller
public class IssuesReportController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public static Logger logger = Logger.getLogger(IssuesReportController.class);

	@Autowired
	IssuesReportService issueService;

	@Value("${common.error.message}")
	public String commonError;

	@RequestMapping(value = "/issues-report", method = RequestMethod.GET)
	public ModelAndView issuesReport(@ModelAttribute Issue obj, HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.issuesReport);
			obj.setStatus_fk("Closed");
			List<Issue> contractsList = issueService.getContractsListInIssuesReport(obj);
			model.addObject("contractsList", contractsList);
			List<Issue> worksList = issueService.getWorksListInIssuesReport(obj);
			model.addObject("worksList", worksList);
			List<Issue> hodsList = issueService.getHODListInIssuesReport(obj);
			model.addObject("hodsList", hodsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("issuesReport : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getWorksListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getWorksListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getWorksListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}

	@RequestMapping(value = "/ajax/getContractsListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getContractsListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getContractsListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}

	@RequestMapping(value = "/ajax/getHODListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getHODListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getHODListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getStatusListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getStatusListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getStatusListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getLocationsListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getLocationsListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getLocationsListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLocationsListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getCategoriesListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getCategoriesListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getCategoriesListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoriesListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getTitlesListInIssuesReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getTitlesListInIssuesReport(@ModelAttribute Issue obj) {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getTitlesListInIssuesReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTitlesListInIssuesReport : " + e.getMessage());
		}
		return objsList;
	}

	@RequestMapping(value = "/generate-pending-issues-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView generatePendingIssuesReport(@ModelAttribute Issue obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView("redirect:/issues-report");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String currentDate = sqlDate.format(date);

			boolean flag = generatePendingIssuesReport(response, currentDate, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generatePendingIssuesReport : " + e.getMessage());
		}
		return model;
	}

	private boolean generatePendingIssuesReport(HttpServletResponse response, String currentDate, Issue obj) {
		//XWPFDocument document = new XWPFDocument(); 
		//StringBuilder repositoryExcerpts = new StringBuilder(); 
		byte[] byteArray;
		//ObjectFactory objectFactory = new ObjectFactory();
		boolean flag = false;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY, hh.mm aa");
			String report_created_date = df.format(new Date());

			obj.setStatus_fk("Closed");
			Map<String,Map<String,List<Issue>>> pendingIssues = issueService.getPendingIssues(obj);
			
			boolean landscape = true;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);

			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();

			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";

			JcEnumeration imageAlignment = JcEnumeration.CENTER;

			int issue_count = 0;
			if (pendingIssues != null) {
				for (Map.Entry<String,Map<String, List<Issue>>> entry : pendingIssues.entrySet()) {
					for (Map.Entry<String, List<Issue>> lObj : entry.getValue().entrySet()) {
						issue_count += lObj.getValue().size();
					}
				}
			}
			
			//String headerTextMiddle = "PMIS Report - Pending Issues ("+issue_count+" Nos)";
			String headerTextMiddle = "Project Issues";

			String headerTextRight = report_created_date;

			//String headerText = "PMIS Report - Pending Issues";

			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
					headerTextMiddle, headerTextRight);
			//Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 

			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);

			DocxTableCreation.createTableForPendingIssuesReport(wordMLPackage, mp, factory, pendingIssues);

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
				InputStream targetStream = new ByteArrayInputStream(byteArray);
				String FILE_EXTENSION = ".docx";
				String fileName = "Pending Issues Report - " + currentDate + FILE_EXTENSION;

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
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("generatePendingIssuesReport >> FileNotFoundException occurs.." + e.getMessage());
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generatePendingIssuesReport >> " + e.getMessage());
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

		String papersize = Docx4jProperties.getProperties().getProperty("docx4j.PageSize", "A4");
		logger.info("Using paper size: " + papersize);

		String landscapeString = Docx4jProperties.getProperties().getProperty("docx4j.PageOrientationLandscape",
				"false");
		boolean landscape = Boolean.parseBoolean(landscapeString);
		logger.info("Landscape orientation: " + landscape);

		return createPackage(PageSizePaper.valueOf(papersize), landscape);
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
	public static WordprocessingMLPackage createPackage(PageSizePaper sz, boolean landscape)
			throws InvalidFormatException {

		// Create a package
		WordprocessingMLPackage wmlPack = new WordprocessingMLPackage();

		// Create main document part
		MainDocumentPart wordDocumentPart = new MainDocumentPart();

		// Create main document part content
		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
		org.docx4j.wml.Body body = factory.createBody();
		org.docx4j.wml.Document wmlDocumentEl = factory.createDocument();

		wmlDocumentEl.setBody(body);

		// Create a basic sectPr using our Page model
		PageDimensions page = new PageDimensions();
		page.setPgSize(sz, landscape);

		SectPr sectPr = factory.createSectPr();
		body.setSectPr(sectPr);
		sectPr.setPgSz(page.getPgSz());
		sectPr.setPgMar(page.getPgMar());

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
		core.setJaxbElement(coreFactory.createCoreProperties());
		wmlPack.addTargetPart(core);

		DocPropsExtendedPart app = new DocPropsExtendedPart();
		org.docx4j.docProps.extended.ObjectFactory extFactory = new org.docx4j.docProps.extended.ObjectFactory();
		app.setJaxbElement(extFactory.createProperties());
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

	public void setDocSectionBreak(WordprocessingMLPackage wordPackage, String orientation) {
		try {
			SectPr sectPr = null;
			ObjectFactory objectFactory = new ObjectFactory();
			sectPr = objectFactory.createSectPr();

			PgSz landscape = new PgSz();

			if ("landscape".equalsIgnoreCase(orientation)) {
				landscape.setH(BigInteger.valueOf(11906));
				landscape.setW(BigInteger.valueOf(16383));
				/*sectPr = (org.docx4j.wml.SectPr)org.docx4j.XmlUtils.unmarshalString("<w:sectPr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
				       + "<w:pgSz w:w=\"16839\" w:h=\"11907\" w:orient=\""+temp.orientation+"\"/>"
				       + "</w:sectPr>");*/
				landscape.setOrient(STPageOrientation.LANDSCAPE);
			} else {
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
		} catch (Exception e) {
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

	public static P newImage(WordprocessingMLPackage wordMLPackage, byte[] bytes, String filenameHint, String altText,
			int id1, int id2) throws Exception {

		Integer HEADER_TOP_OFFSET = 0;

		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, 8500, false);

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
		return i < 0 ? "" : getAlphabet((i / 26) - 1) + (char) (65 + i % 26);
	}

	public Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
			ObjectFactory factory, String imagePath, JcEnumeration imageAlignment, String headerTextMiddle,
			String headerTextRight) throws Exception {
		HeaderPart headerPart = new HeaderPart();
		Relationship rel = t.addTargetPart(headerPart);
		// After addTargetPart, so image can be added properly
		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, factory, headerPart, imagePath, imageAlignment,
				headerTextMiddle, headerTextRight));
		return rel;
	}

	public Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
			ObjectFactory factory, String headerText) throws Exception {
		HeaderPart headerPart = new HeaderPart();
		Relationship rel = t.addTargetPart(headerPart);
		// After addTargetPart, so image can be added properly
		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, factory, headerPart, headerText));
		return rel;
	}

	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, HeaderPart sourcePart,
			String imagePath, JcEnumeration imageAlignment, String headerTextMiddle, String headerTextRight)
			throws Exception {
		Hdr hdr = factory.createHdr();
		//String path = CommonConstants.DOCX_LOGO+"/docx-logo.png";
		//String path = CommonConstants.DOCX_LOGO+"/"+"ircon-report-header.png";
		P p = factory.createP();
		R r = factory.createR();
		if (!StringUtils.isEmpty(imagePath)) {
			File file = new File(imagePath);
			java.io.InputStream is = new java.io.FileInputStream(file);

			String filenameHint = null;
			String altText = null;

			int id1 = 0;
			int id2 = 1;
			p = newImage(wordprocessingMLPackage, factory, sourcePart, BufferUtil.getBytesFromInputStream(is), altText,
					filenameHint, id1, id2, imageAlignment);
		}

		hdr.getContent().add(p);

		/*RPr boldRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA,
				true, false, false, false);
		
		p = factory.createP();
		r = factory.createR();
		
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
		
		if(!StringUtils.isEmpty(headerTextMiddle)) {
			for (int i = 0; i < 9; i++) {
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
			for (int i = 0; i < 4; i++) {
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

		RPr titleRpr = getRPr(factory, "Calibri", "000000", "24", STHint.EAST_ASIA, true, false, false, false);

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

		hdr.getContent().add(table);

		return hdr;
	}

	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, HeaderPart sourcePart,
			String headerText) throws Exception {
		Hdr hdr = factory.createHdr();

		P p = factory.createP();
		R r = factory.createR();

		RPr boldRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false, false, false);

		if (!StringUtils.isEmpty(headerText)) {
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

	public P newImage(WordprocessingMLPackage wordMLPackage, ObjectFactory factory, HeaderPart sourcePart, byte[] bytes,
			String filenameHint, String altText, int id1, int id2, JcEnumeration jcEnumeration) throws Exception {
		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, sourcePart, bytes);
		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, false);
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

	public void createHeaderReference(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
			ObjectFactory factory, Relationship relationship) throws InvalidFormatException {
		List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();
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

	public void addPageBreak(WordprocessingMLPackage wordMLPackage, ObjectFactory factory) {
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		Br breakObj = new Br();
		breakObj.setType(STBrType.PAGE);
		P paragraph = factory.createP();
		paragraph.getContent().add(breakObj);
		documentPart.addObject(paragraph);
	}

	public void addEmptyParagraph(WordprocessingMLPackage wordMLPackage, MainDocumentPart t, ObjectFactory factory)
			throws Exception {
		RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, false, false, false);
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

	public void addHeading(WordprocessingMLPackage wordMLPackage, MainDocumentPart t, ObjectFactory factory,
			String contentValue) throws Exception {
		RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
		RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
		RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false, false);

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

	public RPr getRPr(ObjectFactory factory, String fontFamily, String colorVal, String fontSize, STHint sTHint,
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

	public void setParagraphAlign(ObjectFactory factory, P p, JcEnumeration jcEnumeration) {
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

	public Relationship createFooterPageNumPart(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
			ObjectFactory factory) throws Exception {
		FooterPart footerPart = new FooterPart();
		footerPart.setPackage(wordprocessingMLPackage);
		footerPart.setJaxbElement(createFooterWithPageNr(factory));
		return t.addTargetPart(footerPart);
	}

	public Ftr createFooterWithPageNr(ObjectFactory factory) {
		Ftr ftr = factory.createFtr();
		P paragraph = factory.createP();
		RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false, false);
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

	private void addPageTextField(ObjectFactory factory, P paragraph, String value) {
		R run = factory.createR();
		Text txt = new Text();
		txt.setSpace("preserve");
		txt.setValue(value);
		run.getContent().add(txt);
		paragraph.getContent().add(run);
	}

	public void createFooterReference(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
			ObjectFactory factory, Relationship relationship) throws InvalidFormatException {
		List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();
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

	public Hdr getTextHdr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, Part sourcePart,
			String content, JcEnumeration jcEnumeration) throws Exception {
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

	public Ftr getTextFtr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, Part sourcePart,
			String content, JcEnumeration jcEnumeration) throws Exception {
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

	public void addTableCell(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow, String content,
			RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor) {
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

	/***********************************************************************************/

	public boolean sendMailWithOpenIssues(Issue obj) {
		//XWPFDocument document = new XWPFDocument(); 
		//StringBuilder repositoryExcerpts = new StringBuilder(); 
		byte[] byteArray;
		//ObjectFactory objectFactory = new ObjectFactory();
		boolean flag = false;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
			String report_created_date = df.format(new Date());

			obj.setStatus_fk("Closed");
			Map<String,Map<String,List<Issue>>> pendingIssues = issueService.getPendingIssues(obj);

			boolean landscape = true;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);

			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();

			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";

			JcEnumeration imageAlignment = JcEnumeration.CENTER;

			int issue_count = 0;
			if (pendingIssues != null) {
				for (Map.Entry<String,Map<String, List<Issue>>> entry : pendingIssues.entrySet()) {
					for (Map.Entry<String, List<Issue>> lObj : entry.getValue().entrySet()) {
						issue_count += lObj.getValue().size();
					}
				}
			}
			String headerTextMiddle = "Project Issues (" + issue_count + " Nos)";

			//String headerTextRight = "Date : " + report_created_date;

			String headerTextRight = report_created_date;

			//String headerText = "PMIS Report - Pending Issues";

			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
					headerTextMiddle, headerTextRight);
			//Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 

			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);

			DocxTableCreation.createTableForPendingIssuesReport(wordMLPackage, mp, factory, pendingIssues);

			byte[] issues_summary_byte_array = sendMailWithIssuesSummary(obj);

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
				String file_extention = "docx";
				String pending_issues_file_name = "Pending-Issues-Report";
				String issues_summary_file_name = "Issues-Summary-Report";

				String recipients = "", cc = "", bcc = CommonConstants.BCC_MAIL,
						subject = "PMIS Open Issues and Issues Summary Reports", body = "";

				recipients = issueService.getEmailIdsOfHodDyHodManagement();

				if (!StringUtils.isEmpty(recipients)) {
					EMailSender emailSender = new EMailSender();
					emailSender.sendEmailWithIssuesReportsAttachment(recipients, cc, bcc, subject, body,
							pending_issues_file_name, issues_summary_file_name, file_extention, byteArray,
							issues_summary_byte_array);
				}

				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("sendMailWithOpenIssues >> FileNotFoundException occurs.." + e.getMessage());
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sendMailWithOpenIssues >> " + e.getMessage());
			flag = false;
		}

		return flag;
	}

	public byte[] sendMailWithIssuesSummary(Issue obj) {
		byte[] byteArray = null;
		;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
			String report_created_date = df.format(new Date());

			List<Issue> issuesCounts = issueService.getIssuesSummaryData(obj);

			boolean landscape = true;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);

			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();

			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";

			JcEnumeration imageAlignment = JcEnumeration.CENTER;

			String headerTextMiddle = "Issues Summary";

			String headerTextRight = report_created_date;

			//String headerText = "PMIS Report - Pending Issues";

			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
					headerTextMiddle, headerTextRight);
			//Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 

			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);

			DocxTableCreation.createTableForIssuesSummaryReport(wordMLPackage, mp, factory, issuesCounts);

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
			} catch (Exception e) {
				logger.error("sendMailWithIssuesSummary >> FileNotFoundException occurs.." + e.getMessage());
			}

		} catch (Exception e) {
			logger.error("sendMailWithIssuesSummary >> " + e.getMessage());
		}

		return byteArray;
	}

	/**********************************************************************************************************************/

	@RequestMapping(value = "/generate-issues-summary-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView generateIssuesSummaryReport(@ModelAttribute Issue obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView("redirect:/issues-report");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String currentDate = sqlDate.format(date);

			boolean flag = generateIssuesSummaryReport(response, currentDate, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generateIssuesSummaryReport : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/issues-summary-report/{work_id_fk}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView IssuesSummaryReport(@ModelAttribute Issue obj,@PathVariable("work_id_fk") String work_id_fk, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView("redirect:/issues-report");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String currentDate = sqlDate.format(date);
			
			if (!StringUtils.isEmpty(work_id_fk)) {
				obj.setWork_id_fk(work_id_fk);
			}
			
			boolean flag = IssuesSummaryReport(response, currentDate, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("IssuesSummaryReport : " + e.getMessage());
		}
		return model;
	}	
	
	
	private boolean IssuesSummaryReport(HttpServletResponse response, String currentDate, Issue obj) {
		//XWPFDocument document = new XWPFDocument(); 
		//StringBuilder repositoryExcerpts = new StringBuilder(); 
		byte[] byteArray;
		//ObjectFactory objectFactory = new ObjectFactory();
		boolean flag = false;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
			String report_created_date = df.format(new Date());

			List<Issue> issuesCounts = issueService.IssuesSummaryData(obj);

			boolean landscape = false;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);

			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();

			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";

			JcEnumeration imageAlignment = JcEnumeration.CENTER;

			String headerTextMiddle = "Project Issues Summary";

			String headerTextRight = report_created_date;

			//String headerText = "PMIS Report - Pending Issues";

			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
					headerTextMiddle, headerTextRight);
			//Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 

			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);

			DocxTableCreation.createTableForIssuesSummaryReport(wordMLPackage, mp, factory, issuesCounts);

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
				InputStream targetStream = new ByteArrayInputStream(byteArray);
				String FILE_EXTENSION = ".docx";
				String fileName = "Issues Summary Report - " + currentDate + FILE_EXTENSION;

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
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("generateIssuesSummaryReport >> FileNotFoundException occurs.." + e.getMessage());
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generateIssuesSummaryReport >> " + e.getMessage());
			flag = false;
		}

		return flag;
	}	

	private boolean generateIssuesSummaryReport(HttpServletResponse response, String currentDate, Issue obj) {
		//XWPFDocument document = new XWPFDocument(); 
		//StringBuilder repositoryExcerpts = new StringBuilder(); 
		byte[] byteArray;
		//ObjectFactory objectFactory = new ObjectFactory();
		boolean flag = false;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
			String report_created_date = df.format(new Date());

			List<Issue> issuesCounts = issueService.getIssuesSummaryData(obj);

			boolean landscape = false;
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);

			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			ObjectFactory factory = Context.getWmlObjectFactory();

			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";

			JcEnumeration imageAlignment = JcEnumeration.CENTER;

			String headerTextMiddle = "Project Issues Summary";

			String headerTextRight = report_created_date;

			//String headerText = "PMIS Report - Pending Issues";

			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
					headerTextMiddle, headerTextRight);
			//Relationship relationship = createHeaderPart(wordMLPackage, mp, factory,headerText);			 

			createHeaderReference(wordMLPackage, mp, factory, relationship);
			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
			createFooterReference(wordMLPackage, mp, factory, relationship);

			DocxTableCreation.createTableForIssuesSummaryReport(wordMLPackage, mp, factory, issuesCounts);

			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				wordMLPackage.save(bos);
				byteArray = bos.toByteArray();
				InputStream targetStream = new ByteArrayInputStream(byteArray);
				String FILE_EXTENSION = ".docx";
				String fileName = "Issues Summary Report - " + currentDate + FILE_EXTENSION;

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
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("generateIssuesSummaryReport >> FileNotFoundException occurs.." + e.getMessage());
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generateIssuesSummaryReport >> " + e.getMessage());
			flag = false;
		}

		return flag;
	}

	public boolean sendReminderEmail(Issue issue, int daysPending) {
	    try {
	        // Determine the email details based on the daysPending
	        String subject = getEmailSubject(daysPending, issue);
	        String body = getEmailBody(daysPending, issue);
	        String to = getRecipientEmail(daysPending, issue);
	        String cc = getCCEmail(daysPending, issue);
	        
			Mail mail = new Mail();
			mail.setMailTo(to);
			mail.setMailCc(cc);
			mail.setMailBcc(CommonConstants.BCC_MAIL);
			mail.setMailSubject(subject);
			mail.setTemplateName("IssueStatusAlert.vm");

			SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
			String today_date = monthFormat.format(new Date()).toUpperCase();

			SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
			String current_year = yearFormat.format(new Date()).toUpperCase();

			if (!StringUtils.isEmpty(to)) {
				EMailSender emailSender = new EMailSender();
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail to " + to + ": Start ");
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail CC " + cc + ": Start ");
				emailSender.sendEmailWithIssueStatusAlert(mail, issue, today_date, current_year);
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail to " + to + ": end ");
				logger.error("sendEmailWithIssueStatusAlert() >> Sending mail CC " + cc + ": end ");
			}	        
	        return true;
	    } catch (Exception e) {
	        logger.error("Failed to send reminder email for Issue ID: {}, Days Pending: {}");
	        return false;
	    }
	}
	
	private String getEmailSubject(int daysPending, Issue issue) {
	    switch (daysPending) {
	        case 7:
	            return "Reminder-1: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 7 days of reporting";
	        case 14:
	            return "Reminder-2: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 14 days of reporting";
	        case 21:
	            return "Reminder-3: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 21 days of reporting";
	        case 28:
	            return "Reminder-4: Notification of Unresolved Issue in " + issue.getContract_short_name() + ", pending since 28 days of reporting";
	        default:
	            return "Reminder: Notification of Unresolved Issue in " + issue.getContract_short_name();
	    }
	}

	private String getEmailBody(int daysPending, Issue issue) {
	    String workDetails = issue.getWork_name();
	    String contractDetails = issue.getContract_name();
	    String reportedBy = issue.getReported_by();
	    String description = issue.getDescription();
	    String category = issue.getCategory();
	    String priority = issue.getPriority();
	    String responsibleOrg = issue.getZonal_railway_fk();
	    String actionTaken = issue.getCorrective_measure();
	    String targetDate = issue.getDate();

	    String bodyTemplate = 
	        "Respected %s,\n\n" +
	        "This is a reminder that the following issue, reported on %s, has not been resolved till date.\n\n" +
	        "Work Details: %s\n" +
	        "Contract Details: %s\n" +
	        "Reported By: %s\n" +
	        "Issue Description: %s\n" +
	        "Category: %s; Priority: %s\n" +
	        "Organization Responsible for Issue: %s\n" +
	        "Action Taken: %s\n" +
	        "Target Date of Resolution: %s\n\n\n\\n" +
	        "We kindly request your immediate attention to this matter to ensure timely resolution.\n" +
	        "Click Here for more details on the issue.\n\n" +
	        "Thank you for your prompt action.\n\n" +
	        "Regards,\n" +
	        "MRVC-PMIS Team.\n" +
	        "[Company Logo]\n";

	    String recipientName = getRecipientName(daysPending, issue);
	    String reportingDate = issue.getDate().toString();  // Assuming issue.getReportingDate() returns a LocalDate

	    return String.format(bodyTemplate, recipientName, reportingDate, workDetails, contractDetails, reportedBy, description,
	            category, priority, responsibleOrg, actionTaken, targetDate);
	}


	private String getRecipientEmail(int daysPending, Issue issue) {
	    // Depending on daysPending, return the recipient's email
	    switch (daysPending) {
	        case 7:
	            return issue.getContract_dyhod_email_id();  // Assuming dyHodEmail is a field in Issue object
	        case 14:
	            return issue.getContract_hod_email_id();  // Assuming hodEmail is a field in Issue object
	        case 21:
	            return issue.getDp_email();  // Assuming dpEmail is a field in Issue object
	        case 28:
	            return issue.getCmd_email();  // Assuming cmdEmail is a field in Issue object
	        default:
	            return issue.getContract_dyhod_email_id();  // Default case, could be adjusted
	    }
	}

	private String getCCEmail(int daysPending, Issue issue) {
	    // Depending on daysPending, return the CC emails
	    switch (daysPending) {
	        case 7:
	            return issue.getContract_hod_email_id() + ", " + issue.getContractor_email();  // Example: HOD and Contractor
	        case 14:
	            return issue.getDp_email() + ", " + issue.getContractor_email();  // DP and Contractor
	        case 21:
	            return issue.getCmd_email() + ", " + issue.getContractor_email();  // CMD and Contractor
	        case 28:
	            return issue.getContractor_email();  // Only Contractor
	        default:
	            return "";  // No CC by default
	    }
	}

	private String getRecipientName(int daysPending, Issue issue) {
	    // Get the recipient's name based on daysPending
	    switch (daysPending) {
	        case 7:
	            return issue.getDyhod_name();  // Assuming DyHodName is a field in Issue object
	        case 14:
	            return issue.getHod_name();  // Assuming HodName is a field in Issue object
	        case 21:
	            return issue.getDp_name();  // Assuming DpName is a field in Issue object
	        case 28:
	            return issue.getCmd_name();  // Assuming CmdName is a field in Issue object
	        default:
	            return issue.getDyhod_name();  // Default to DyHodName
	    }
	}

	public List<Issue> getUnresolvedIssues() {
		List<Issue> objsList = null;
		try {
			objsList = issueService.getUnresolvedIssues();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUnresolvedIssues : " + e.getMessage());
		}
		return objsList;
	}
}
