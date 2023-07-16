package com.synergizglobal.pmis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STPageOrientation;
import org.docx4j.wml.SectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.TrainingReportService;
import com.synergizglobal.pmis.common.CTPageSz;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Training;

@Controller
public class TrainingReportController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public static Logger logger = Logger.getLogger(TrainingReportController.class);

	@Autowired
	TrainingReportService service;

	@Value("${common.error.message}")
	public String commonError;

	@RequestMapping(value = "/training-report", method = RequestMethod.GET)
	public ModelAndView trainingReport(@ModelAttribute Training obj, RedirectAttributes attributes, HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {

			model.setViewName(PageConstants2.trainingReport);

			List<Training> worklist = service.getWorkShortNameList();
			model.addObject("worklist", worklist);

			obj.setStatus_fk("Completed");
//			List<Training> completedTrainingTitles = service.getCompletedTrainingTitles(obj);
//			model.addObject("completedTrainingTitles", completedTrainingTitles);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("trainingReport : " + e.getMessage());
		}
		return model;
	}

	private SectPr createSectPr() {
		ObjectFactory factory = Context.getWmlObjectFactory();
		SectPr sectPr = factory.createSectPr();
		return sectPr;
	}

	private void setLandscapePage(SectPr sectPr) {
		SectPr.PgSz pgSz = sectPr.getPgSz();
		if (pgSz == null) {
			pgSz = Context.getWmlObjectFactory().createSectPrPgSz();
			sectPr.setPgSz(pgSz);
		}
		pgSz.setW(BigInteger.valueOf(15840)); // Landscape width in twentieths of a point
		pgSz.setH(BigInteger.valueOf(12240)); // Landscape height in twentieths of a point
		pgSz.setOrient(STPageOrientation.LANDSCAPE);
		pgSz.setCode("9");
	}

	@RequestMapping(value = "/generate-training-report", method = RequestMethod.GET)
	public ResponseEntity<byte[]> generatetrainingReport(@ModelAttribute Training obj, RedirectAttributes attributes,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		try {
			// Convert start_time and end_time to desired format (yyyy-MM-dd)
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDate = LocalDate.parse(obj.getStart_time(), inputFormatter);
			LocalDate endDate = LocalDate.parse(obj.getEnd_time(), inputFormatter);
			obj.setStart_time(outputFormatter.format(startDate));
			obj.setEnd_time(outputFormatter.format(endDate));

			List<Training> reportList = service.geTrainingReportList(obj);

			// Create Word document
			XWPFDocument document = new XWPFDocument();

			// Create landscape page size
			CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
			CTPageSz pageSize = sectPr.addNewPgSz();
			pageSize.setW(BigInteger.valueOf(15840)); // Landscape width in twentieths of a point
			pageSize.setH(BigInteger.valueOf(12240)); // Landscape height in twentieths of a point
			pageSize.setOrient(STPageOrientation.LANDSCAPE);

			// Create landscape page size
//			CTDocument1 ctDocument = document.getDocument();
//			CTBody body = ctDocument.getBody();
//			CTSectPr sectPr = body.isSetSectPr() ? body.getSectPr() : body.addNewSectPr();
//			CTPageSz pageSize = sectPr.isSetPgSz() ? sectPr.getPgSz() : sectPr.addNewPgSz();
//			pageSize.setW(BigInteger.valueOf(15840)); // Landscape width in twentieths of a point
//			pageSize.setH(BigInteger.valueOf(12240)); // Landscape height in twentieths of a point
//			pageSize.setOrient(STPageOrientation.LANDSCAPE);

			// Set landscape page size indirectly
//			POIXMLProperties props = document.getProperties();
//			if (props != null) {
//			    CustomProperties customProps = props.getCustomProperties();
//			    if (customProps == null) {
//			        customProps = props.getCoreProperties().getCustomProperties();
//			    }
//			    if (customProps != null) {
//			        customProps.addProperty("PageSize", "A4_LANDSCAPE");
//			    }

//		// Create Word document using docx4j
//			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
//
//			// Get the Main Document Part
//			MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
//
//			// Create landscape page size
//			SectPr sectPr = mainDocumentPart.getContents().getBody().getSectPr();
//			if (sectPr == null) {
//				sectPr = createSectPr();
//				mainDocumentPart.getContents().getBody().setSectPr(sectPr);
//			}
//			setLandscapePage(sectPr);

			// Add logo at the beginning of the document
			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";
			XWPFParagraph logoParagraph = document.createParagraph();
			XWPFRun logoRun = logoParagraph.createRun();
			logoRun.addPicture(new FileInputStream(imagePath), XWPFDocument.PICTURE_TYPE_PNG, "Logo", Units.toEMU(70),
					Units.toEMU(70));
			logoParagraph.setAlignment(ParagraphAlignment.CENTER);

			// Create paragraph and add text
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText("Training Report For: " + obj.getWork_short_name());
			run.setFontSize(16);
			run.setBold(true);

			// Create table
			XWPFTable table = document.createTable(reportList.size() + 1, 12); // Add an additional column for images

			List<XWPFTableRow> rows = table.getRows();

			// Create table headers
			XWPFTableRow headerRow = rows.get(0);
			headerRow.getCell(0).setText("Training ID");
			headerRow.getCell(1).setText("Start Time");
			headerRow.getCell(2).setText("End Time");
			headerRow.getCell(3).setText("Title");
			headerRow.getCell(4).setText("Location");
			headerRow.getCell(5).setText("Contract Short Name");
			headerRow.getCell(6).setText("Conduct By");
			headerRow.getCell(7).setText("Period");
			headerRow.getCell(8).setText("Session No");
			headerRow.getCell(9).setText("File Name");
			headerRow.getCell(10).setText("Remarks");
			headerRow.getCell(11).setText("Image"); // Add header for the image column

			// Set header row background color
			for (XWPFTableCell headerCell : headerRow.getTableCells()) {
				CTShd headerShading = headerCell.getCTTc().addNewTcPr().addNewShd();
				headerShading.setFill("C0C0C0"); // Set the desired background color
			}

			// Populate table with data and add image to the last column
			for (int i = 0; i < reportList.size(); i++) {
				XWPFTableRow row = rows.get(i + 1);
				Training training = reportList.get(i);
				row.getCell(0).setText(training.getTraining_id());
				row.getCell(1).setText(training.getStart_time());
				row.getCell(2).setText(training.getEnd_time());
				row.getCell(3).setText(training.getTitle());
				row.getCell(4).setText(training.getTraining_center());
				row.getCell(5).setText(training.getContract_short_name_fk());
				row.getCell(6).setText(training.getConduct_by_fk());
				row.getCell(7).setText(training.getPeriod_fk());
				row.getCell(8).setText(training.getSession_no());
				row.getCell(9).setText(training.getFile_name());
				row.getCell(10).setText(training.getRemarks());

				// Add image to the last column
				String imageFilePath = CommonConstants2.TRAINING_GALLERY_FILE_SAVING_PATH + "/"
						+ training.getTraining_id() + "/" + training.getFile_name();
				if (new File(imageFilePath).exists()) {
					XWPFParagraph imageParagraph = row.getCell(11).addParagraph(); // Add the image to the 12th column
					XWPFRun imageRun = imageParagraph.createRun();
					imageRun.addPicture(new FileInputStream(imageFilePath), Document.PICTURE_TYPE_PNG, "Image",
							Units.toEMU(70), Units.toEMU(70));
				}
			}

			// Generate file name with timestamp
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			String fileName = "Training_Report_" + timestamp + ".docx";
			String filePath = "C:/Users/Synergiz/Downloads/" + fileName;

			// Save the document to file
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);
			document.write(fileOutputStream);
			fileOutputStream.close();

			// Prepare the response
			byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData(fileName, fileName);
			headers.setContentLength(fileContent.length);

			// Delete the temporary file
			Files.deleteIfExists(Paths.get(filePath));

			return ResponseEntity.ok().headers(headers).body(fileContent);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("trainingReport: " + e.getMessage());
			// Handle any exceptions and return an appropriate response
		}

		return null;
	}

//	@RequestMapping(value = "/ajax/getScheduledListInTrainingReport", method = { RequestMethod.GET,
//			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<Training> getScheduledListInTrainingReport(@ModelAttribute Training obj, HttpSession session) {
//		List<Training> scheduledTrainingTitles = null;
//		try {
//			obj.setStatus_fk("Scheduled");
//			scheduledTrainingTitles = service.getScheduledTrainingTitles(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("getScheduledListInTrainingReport : " + e.getMessage());
//		}
//		return scheduledTrainingTitles;
//	}
//
//	@RequestMapping(value = "/ajax/getEmployeesListInTrainingReport", method = { RequestMethod.GET,
//			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<Training> getEmployeesListInTrainingReport(@ModelAttribute Training obj, HttpSession session) {
//		List<Training> employees = null;
//		try {
//			employees = service.getEmployeesInTraining(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("getEmployeesListInTrainingReport : " + e.getMessage());
//		}
//		return employees;
//	}
//
//	@RequestMapping(value = "/ajax/getCompletedListInTrainingReport", method = { RequestMethod.GET,
//			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public List<Training> getCompletedListInTrainingReport(@ModelAttribute Training obj, HttpSession session) {
//		List<Training> completedTrainingTitles = null;
//		try {
//			obj.setStatus_fk("Completed");
//			completedTrainingTitles = service.getCompletedTrainingTitles(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("getCompletedListInTrainingReporta : " + e.getMessage());
//		}
//		return completedTrainingTitles;
//	}
//
//	@RequestMapping(value = "/generate-scheduled-training-report", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView generateScheduledTrainingsReport(@ModelAttribute Training obj, HttpServletRequest request,
//			HttpServletResponse response, HttpSession session, RedirectAttributes attributes) {
//		ModelAndView model = new ModelAndView("redirect:/training-report");
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = new Date();
//			String currentDate = sqlDate.format(date);
//
//			boolean flag = generateScheduledTrainingsReport(response, currentDate, obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("generateScheduledTrainingsReport : " + e.getMessage());
//		}
//		return model;
//	}
//
//	@RequestMapping(value = "/generate-employee-training-report", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView generateEmployeeTrainingsReport(@ModelAttribute Training obj, HttpServletRequest request,
//			HttpServletResponse response, HttpSession session, RedirectAttributes attributes) {
//		ModelAndView model = new ModelAndView("redirect:/training-report");
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = new Date();
//			String currentDate = sqlDate.format(date);
//
//			boolean flag = generateEmployeeTrainingsReport(response, currentDate, obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("generateEmployeeTrainingsReport : " + e.getMessage());
//		}
//		return model;
//	}
//
//	@RequestMapping(value = "/generate-completed-training-report", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView generateCompletedTrainingsReport(@ModelAttribute Training obj, HttpServletRequest request,
//			HttpServletResponse response, HttpSession session, RedirectAttributes attributes) {
//		ModelAndView model = new ModelAndView("redirect:/training-report");
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = new Date();
//			String currentDate = sqlDate.format(date);
//
//			boolean flag = generateCompletedTrainingsReport(response, currentDate, obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("generateCompletedTrainingsReport : " + e.getMessage());
//		}
//		return model;
//	}
//
//	private boolean generateScheduledTrainingsReport(HttpServletResponse response, String currentDate, Training obj) {
//		// XWPFDocument document = new XWPFDocument();
//		// StringBuilder repositoryExcerpts = new StringBuilder();
//		byte[] byteArray;
//		// ObjectFactory objectFactory = new ObjectFactory();
//		boolean flag = false;
//		try {
//			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
//			String report_created_date = df.format(new Date());
//
//			obj.setStatus_fk("Scheduled");
//			List<Training> scheduledTrainings = service.getScheduledTrainings(obj);
//
//			boolean landscape = false;
//			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);
//
//			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
//			ObjectFactory factory = Context.getWmlObjectFactory();
//
//			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";
//
//			JcEnumeration imageAlignment = JcEnumeration.CENTER;
//
//			String headerTextMiddle = "Scheduled Training";
//
//			String headerTextRight = report_created_date;
//
//			// String headerText = "Scheduled Training";
//
//			int tabs1 = 4;
//			int tabs2 = 1;
//
//			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
//					headerTextMiddle, headerTextRight, tabs1, tabs2);
//			// Relationship relationship = createHeaderPart(wordMLPackage, mp,
//			// factory,headerText);
//			createHeaderReference(wordMLPackage, mp, factory, relationship);
//			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
//			createFooterReference(wordMLPackage, mp, factory, relationship);
//
//			DocxTableCreation.createTableForScheduledTrainingReport(wordMLPackage, mp, factory, scheduledTrainings);
//
//			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//				wordMLPackage.save(bos);
//				byteArray = bos.toByteArray();
//				InputStream targetStream = new ByteArrayInputStream(byteArray);
//				String FILE_EXTENSION = ".docx";
//				String fileName = "Scheduled Training - " + currentDate + FILE_EXTENSION;
//
//				response.setContentType("application/.csv");
//				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				response.setContentType("application/vnd.ms-excel");
//				response.setContentType("application/pdf");
//				response.setContentType("application/msword");
//				response.setContentType("application/vnd.ms-word");
//				// add response header
//				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//				// copies all bytes from a file to an output stream
//				IOUtils.copy(targetStream, response.getOutputStream());
//				// flushes output stream
//				response.getOutputStream().flush();
//
//				flag = true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("generateScheduledTrainingsReport >> FileNotFoundException occurs.." + e.getMessage());
//				flag = false;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("generateScheduledTrainingsReport >> " + e.getMessage());
//			flag = false;
//		}
//
//		return flag;
//	}
//
//	private boolean generateEmployeeTrainingsReport(HttpServletResponse response, String currentDate, Training obj) {
//		// XWPFDocument document = new XWPFDocument();
//		// StringBuilder repositoryExcerpts = new StringBuilder();
//		byte[] byteArray;
//		// ObjectFactory objectFactory = new ObjectFactory();
//		boolean flag = false;
//		try {
//			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
//			String report_created_date = df.format(new Date());
//
//			List<Training> employeeTrainings = service.getEmployeeTrainings(obj);
//			Training employeeTraining = service.getEmployeeTrainingWithStatus(obj);
//
//			boolean landscape = true;
//			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);
//
//			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
//			ObjectFactory factory = Context.getWmlObjectFactory();
//
//			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";
//
//			JcEnumeration imageAlignment = JcEnumeration.CENTER;
//
//			String headerTextMiddle = "Employee Training";
//
//			String headerTextRight = report_created_date;
//
//			// String headerText = "Employee Training";
//
//			int tabs1 = 8;
//			int tabs2 = 4;
//
//			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
//					headerTextMiddle, headerTextRight, tabs1, tabs2);
//			// Relationship relationship = createHeaderPart(wordMLPackage, mp,
//			// factory,headerText);
//			createHeaderReference(wordMLPackage, mp, factory, relationship);
//			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
//			createFooterReference(wordMLPackage, mp, factory, relationship);
//
//			DocxTableCreation.createTableForEmployeeTrainingReport(wordMLPackage, mp, factory, employeeTrainings,
//					employeeTraining);
//
//			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//				wordMLPackage.save(bos);
//				byteArray = bos.toByteArray();
//				InputStream targetStream = new ByteArrayInputStream(byteArray);
//				String FILE_EXTENSION = ".docx";
//				String fileName = "Employee Training - " + currentDate + FILE_EXTENSION;
//
//				response.setContentType("application/.csv");
//				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				response.setContentType("application/vnd.ms-excel");
//				response.setContentType("application/pdf");
//				response.setContentType("application/msword");
//				response.setContentType("application/vnd.ms-word");
//				// add response header
//				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//				// copies all bytes from a file to an output stream
//				IOUtils.copy(targetStream, response.getOutputStream());
//				// flushes output stream
//				response.getOutputStream().flush();
//
//				flag = true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("generateEmployeeTrainingsReport >> FileNotFoundException occurs.." + e.getMessage());
//				flag = false;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("generateEmployeeTrainingsReport >> " + e.getMessage());
//			flag = false;
//		}
//
//		return flag;
//	}
//
//	private boolean generateCompletedTrainingsReport(HttpServletResponse response, String currentDate, Training obj) {
//		// XWPFDocument document = new XWPFDocument();
//		// StringBuilder repositoryExcerpts = new StringBuilder();
//		byte[] byteArray;
//		// ObjectFactory objectFactory = new ObjectFactory();
//		boolean flag = false;
//		try {
//			DateFormat df = new SimpleDateFormat("dd-MM-YYYY hh:mm aa");
//			String report_created_date = df.format(new Date());
//
//			obj.setStatus_fk("Completed");
//			List<Training> completedTrainings = service.getCompletedTrainings(obj);
//
//			boolean landscape = false;
//			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage(PageSizePaper.A4, landscape);
//
//			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
//			ObjectFactory factory = Context.getWmlObjectFactory();
//
//			String imagePath = CommonConstants2.DOCX_LOGO + "/" + "report_logo_mrvc.png";
//
//			JcEnumeration imageAlignment = JcEnumeration.CENTER;
//
//			String headerTextMiddle = "Completed Training";
//
//			String headerTextRight = report_created_date;
//
//			// String headerText = "Completed Training";
//
//			int tabs1 = 4;
//			int tabs2 = 1;
//
//			Relationship relationship = createHeaderPart(wordMLPackage, mp, factory, imagePath, imageAlignment,
//					headerTextMiddle, headerTextRight, tabs1, tabs2);
//			// Relationship relationship = createHeaderPart(wordMLPackage, mp,
//			// factory,headerText);
//			createHeaderReference(wordMLPackage, mp, factory, relationship);
//			relationship = createFooterPageNumPart(wordMLPackage, mp, factory);
//			createFooterReference(wordMLPackage, mp, factory, relationship);
//
//			DocxTableCreation.createTableForCompletedTrainingReport(wordMLPackage, mp, factory, completedTrainings);
//
//			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//				wordMLPackage.save(bos);
//				byteArray = bos.toByteArray();
//				InputStream targetStream = new ByteArrayInputStream(byteArray);
//				String FILE_EXTENSION = ".docx";
//				String fileName = "Completed Training - " + currentDate + FILE_EXTENSION;
//
//				response.setContentType("application/.csv");
//				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//				response.setContentType("application/vnd.ms-excel");
//				response.setContentType("application/pdf");
//				response.setContentType("application/msword");
//				response.setContentType("application/vnd.ms-word");
//				// add response header
//				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//				// copies all bytes from a file to an output stream
//				IOUtils.copy(targetStream, response.getOutputStream());
//				// flushes output stream
//				response.getOutputStream().flush();
//
//				flag = true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("generateCompletedTrainingsReport >> FileNotFoundException occurs.." + e.getMessage());
//				flag = false;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("generateCompletedTrainingsReport >> " + e.getMessage());
//			flag = false;
//		}
//
//		return flag;
//	}
//
//	/**
//	 * Creates a WordprocessingMLPackage, using default page size and orientation.
//	 * From 2.7.1, these are read from docx4j.properties, or if not found, default
//	 * to A4 portrait.
//	 * 
//	 * The WordprocessingMLPackage contains a MainDocumentPart (with content),
//	 * Styles part, DocPropsCorePart part, and DocPropsExtendedPart.
//	 */
//	public static WordprocessingMLPackage createPackage() throws InvalidFormatException {
//
//		String papersize = Docx4jProperties.getProperties().getProperty("docx4j.PageSize", "A4");
//		logger.info("Using paper size: " + papersize);
//
//		String landscapeString = Docx4jProperties.getProperties().getProperty("docx4j.PageOrientationLandscape",
//				"false");
//		boolean landscape = Boolean.parseBoolean(landscapeString);
//		logger.info("Landscape orientation: " + landscape);
//
//		return createPackage(PageSizePaper.valueOf(papersize), landscape);
//	}
//
//	/**
//	 * Creates a WordprocessingMLPackage, containing a MainDocumentPart (with
//	 * content), Styles part, DocPropsCorePart part, and DocPropsExtendedPart.
//	 * 
//	 * The content contains sectPr specifying paper size and orientation.
//	 * 
//	 * @param sz
//	 * @param landscape
//	 * @return
//	 * @throws InvalidFormatException
//	 */
//	public static WordprocessingMLPackage createPackage(PageSizePaper sz, boolean landscape)
//			throws InvalidFormatException {
//
//		// Create a package
//		WordprocessingMLPackage wmlPack = new WordprocessingMLPackage();
//
//		// Create main document part
//		MainDocumentPart wordDocumentPart = new MainDocumentPart();
//
//		// Create main document part content
//		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
//		org.docx4j.wml.Body body = factory.createBody();
//		org.docx4j.wml.Document wmlDocumentEl = factory.createDocument();
//
//		wmlDocumentEl.setBody(body);
//
//		// Create a basic sectPr using our Page model
//		PageDimensions page = new PageDimensions();
//		page.setPgSize(sz, landscape);
//
//		SectPr sectPr = factory.createSectPr();
//		body.setSectPr(sectPr);
//		sectPr.setPgSz(page.getPgSz());
//		sectPr.setPgMar(page.getPgMar());
//
//		// Put the content in the part
//		wordDocumentPart.setJaxbElement(wmlDocumentEl);
//
//		// Add the main document part to the package relationships
//		// (creating it if necessary)
//		wmlPack.addTargetPart(wordDocumentPart);
//
//		// Create a styles part
//		Part stylesPart = new org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart();
//		try {
//			((org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart) stylesPart)
//					.unmarshalDefaultStyles();
//
//			// Add the styles part to the main document part relationships
//			// (creating it if necessary)
//			wordDocumentPart.addTargetPart(stylesPart); // NB - add it to main doc part, not package!
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			// e.printStackTrace();
//			logger.error(e.getMessage(), e);
//		}
//
//		// Metadata: docx4j 2.7.1 can populate some of this from docx4j.properties
//		// See SaveToZipFile
//		DocPropsCorePart core = new DocPropsCorePart();
//		org.docx4j.docProps.core.ObjectFactory coreFactory = new org.docx4j.docProps.core.ObjectFactory();
//		core.setJaxbElement(coreFactory.createCoreProperties());
//		wmlPack.addTargetPart(core);
//
//		DocPropsExtendedPart app = new DocPropsExtendedPart();
//		org.docx4j.docProps.extended.ObjectFactory extFactory = new org.docx4j.docProps.extended.ObjectFactory();
//		app.setJaxbElement(extFactory.createProperties());
//		wmlPack.addTargetPart(app);
//
//		// DocumentSettingsPart (/word/settings.xml)
//		// Set overrideTableStyleFontSizeAndJustification to true,
//		// like Word 2010/2013/2016. Since v3.3.0
//		DocumentSettingsPart dsp = new DocumentSettingsPart();
//		wmlPack.getMainDocumentPart().addTargetPart(dsp);
//		dsp.setJaxbElement(new CTSettings());
//		dsp.setOverrideTableStyleFontSizeAndJustification(true);
//
//		// Return the new package
//		return wmlPack;
//
//	}
//
//	public void setDocSectionBreak(WordprocessingMLPackage wordPackage, String orientation) {
//		try {
//			SectPr sectPr = null;
//			ObjectFactory objectFactory = new ObjectFactory();
//			sectPr = objectFactory.createSectPr();
//
//			PgSz landscape = new PgSz();
//
//			if ("landscape".equalsIgnoreCase(orientation)) {
//				landscape.setH(BigInteger.valueOf(11906));
//				landscape.setW(BigInteger.valueOf(16383));
//				/*
//				 * sectPr = (org.docx4j.wml.SectPr)org.docx4j.XmlUtils.
//				 * unmarshalString("<w:sectPr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
//				 * + "<w:pgSz w:w=\"16839\" w:h=\"11907\" w:orient=\""+temp.orientation+"\"/>" +
//				 * "</w:sectPr>");
//				 */
//				landscape.setOrient(STPageOrientation.LANDSCAPE);
//			} else {
//				/*
//				 * sectPr = (org.docx4j.wml.SectPr)org.docx4j.XmlUtils.
//				 * unmarshalString("<w:sectPr xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\">"
//				 * + "<w:pgSz w:w=\"16839\" w:h=\"11907\" />" + "</w:sectPr>");
//				 */
//				landscape.setOrient(STPageOrientation.PORTRAIT);
//			}
//			sectPr.setPgSz(landscape);
//			P p = objectFactory.createP();
//			PPr createPPr = objectFactory.createPPr();
//			createPPr.setSectPr(sectPr);
//			p.setPPr(createPPr);
//
//			/*
//			 * wordPackage.getMainDocumentPart().addAltChunk(
//			 * org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType.Html,
//			 * sectionContent.toString().getBytes());
//			 */
//			/*
//			 * org.docx4j.wml.Br objBr = new org.docx4j.wml.Br();
//			 * objBr.setType(org.docx4j.wml.STBrType.PAGE);
//			 * wordPackage.getMainDocumentPart().addObject(objBr);
//			 */
//
//			wordPackage.getMainDocumentPart().addObject(p);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private void changeOrientation(MainDocumentPart mdp, STPageOrientation stPageOrientation) {
//		ObjectFactory objectFactory = new ObjectFactory();
//		SectPr sectionLandscape = objectFactory.createSectPr();
//		SectPr.PgSz landscape = new SectPr.PgSz();
//		landscape.setOrient(stPageOrientation);
//		landscape.setH(BigInteger.valueOf(11906));
//		landscape.setW(BigInteger.valueOf(16383));
//		sectionLandscape.setPgSz(landscape);
//		org.docx4j.wml.P p = objectFactory.createP();
//		PPr createPPr = objectFactory.createPPr();
//		createPPr.setSectPr(sectionLandscape);
//		p.setPPr(createPPr);
//		mdp.addObject(p);
//	}
//
//	public static P newImage(WordprocessingMLPackage wordMLPackage, byte[] bytes, String filenameHint, String altText,
//			int id1, int id2) throws Exception {
//
//		Integer HEADER_TOP_OFFSET = 0;
//
//		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
//		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, 8500, false);
//
//		/*
//		 * String anchorXml = XmlUtils.marshaltoString(inline, true, false, Context.jc,
//		 * Namespaces.NS_WORD12, "anchor", Inline.class);
//		 * 
//		 * org.docx4j.dml.ObjectFactory dmlFactory = new org.docx4j.dml.ObjectFactory();
//		 * org.docx4j.dml.wordprocessingDrawing.ObjectFactory wordDmlFactory = new
//		 * org.docx4j.dml.wordprocessingDrawing.ObjectFactory();
//		 * 
//		 * 
//		 * Anchor anchor = (Anchor) XmlUtils.unmarshalString(anchorXml, Context.jc,
//		 * Anchor.class);
//		 * 
//		 * anchor.setSimplePos(dmlFactory.createCTPoint2D());
//		 * anchor.getSimplePos().setX(20L); anchor.getSimplePos().setY(50L);
//		 * anchor.setSimplePosAttr(false);
//		 * anchor.setPositionH(wordDmlFactory.createCTPosH());
//		 * anchor.getPositionH().setAlign(STAlignH.CENTER);
//		 * anchor.getPositionH().setRelativeFrom(STRelFromH.MARGIN);
//		 * anchor.setPositionV(wordDmlFactory.createCTPosV());
//		 * anchor.getPositionV().setPosOffset(HEADER_TOP_OFFSET);
//		 * anchor.getPositionV().setRelativeFrom(STRelFromV.PAGE);
//		 * anchor.setWrapNone(wordDmlFactory.createCTWrapNone());
//		 */
//
//		ObjectFactory factory = new ObjectFactory();
//		P p = factory.createP();
//		R run = factory.createR();
//		p.getContent().add(run);
//		Drawing drawing = factory.createDrawing();
//		run.getContent().add(drawing);
//		drawing.getAnchorOrInline().add(inline);
//
//		PPr pPr = p.getPPr();
//		if (pPr == null) {
//			pPr = factory.createPPr();
//		}
//		Jc jc = pPr.getJc();
//		if (jc == null) {
//			jc = new Jc();
//		}
//		jc.setVal(JcEnumeration.CENTER);
//		pPr.setJc(jc);
//		p.setPPr(pPr);
//
//		return p;
//	}
//
//	private static void addPageBreak(MainDocumentPart documentPart) {
//		ObjectFactory objectFactory = new ObjectFactory();
//		P paragraph = objectFactory.createP();
//		R run = objectFactory.createR();
//		P p = objectFactory.createP();
//		// Create object for r
//		R r = objectFactory.createR();
//		p.getContent().add(r);
//		// Create object for br
//		Br br = objectFactory.createBr();
//		r.getContent().add(br);
//		br.setType(org.docx4j.wml.STBrType.PAGE);
//		documentPart.addObject(p);
//	}
//
//	private String getAlphabet(int i) {
//		return i < 0 ? "" : getAlphabet((i / 26) - 1) + (char) (65 + i % 26);
//	}
//
//	public Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
//			ObjectFactory factory, String imagePath, JcEnumeration imageAlignment, String headerTextMiddle,
//			String headerTextRight, int tabs1, int tabs2) throws Exception {
//		HeaderPart headerPart = new HeaderPart();
//		Relationship rel = t.addTargetPart(headerPart);
//		// After addTargetPart, so image can be added properly
//		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, factory, headerPart, imagePath, imageAlignment,
//				headerTextMiddle, headerTextRight, tabs1, tabs2));
//		return rel;
//	}
//
//	public Relationship createHeaderPart(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
//			ObjectFactory factory, String headerText) throws Exception {
//		HeaderPart headerPart = new HeaderPart();
//		Relationship rel = t.addTargetPart(headerPart);
//		// After addTargetPart, so image can be added properly
//		headerPart.setJaxbElement(getHdr(wordprocessingMLPackage, factory, headerPart, headerText));
//		return rel;
//	}
//
//	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, HeaderPart sourcePart,
//			String imagePath, JcEnumeration imageAlignment, String headerTextMiddle, String headerTextRight, int tabs1,
//			int tabs2) throws Exception {
//		Hdr hdr = factory.createHdr();
//		// String path = CommonConstants.DOCX_LOGO+"/docx-logo.png";
//		// String path = CommonConstants.DOCX_LOGO+"/"+"ircon-report-header.png";
//		P p = factory.createP();
//		R r = factory.createR();
//		if (!StringUtils.isEmpty(imagePath)) {
//			File file = new File(imagePath);
//			java.io.InputStream is = new java.io.FileInputStream(file);
//
//			String filenameHint = null;
//			String altText = null;
//
//			int id1 = 0;
//			int id2 = 1;
//			p = newImage(wordprocessingMLPackage, factory, sourcePart, BufferUtil.getBytesFromInputStream(is), altText,
//					filenameHint, id1, id2, imageAlignment);
//		}
//		hdr.getContent().add(p);
//
//		/*
//		 * RPr boldRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA,
//		 * true, false, false, false);
//		 * 
//		 * 
//		 * if(!StringUtils.isEmpty(headerTextMiddle)) { for (int i = 0; i < tabs1; i++)
//		 * { R.Tab rtab = factory.createRTab(); JAXBElement<org.docx4j.wml.R.Tab>
//		 * rtabWrapped = factory.createRTab(rtab); r.getContent().add( rtabWrapped); }
//		 * p.getContent().add(r);
//		 * 
//		 * Text txt = factory.createText(); txt.setValue(headerTextMiddle); r =
//		 * factory.createR(); r.getContent().add(txt); r.setRPr(boldRPr);
//		 * p.getContent().add(r); }
//		 * 
//		 * if(!StringUtils.isEmpty(headerTextRight)) { for (int i = 0; i < tabs2; i++) {
//		 * R.Tab rtab = factory.createRTab(); JAXBElement<org.docx4j.wml.R.Tab>
//		 * rtabWrapped = factory.createRTab(rtab); r.getContent().add( rtabWrapped); }
//		 * Text txt = factory.createText(); txt.setValue(headerTextRight); r =
//		 * factory.createR(); r.getContent().add(txt); r.setRPr(boldRPr);
//		 * p.getContent().add(r); }
//		 * 
//		 * hdr.getContent().add(p);
//		 */
//
//		/*******************************************************************************/
//
//		RPr titleRpr = getRPr(factory, "Calibri", "000000", "24", STHint.EAST_ASIA, true, false, false, false);
//
//		Tbl table = factory.createTbl();
//
//		TblPr tableProps = new TblPr();
//		CTTblLayoutType tblLayoutType = new CTTblLayoutType();
//		STTblLayoutType stTblLayoutType = STTblLayoutType.FIXED;
//		tblLayoutType.setType(stTblLayoutType);
//		tableProps.setTblLayout(tblLayoutType);
//		table.setTblPr(tableProps);
//
//		Tr titleRow = factory.createTr();
//		addTableCell(factory, wordprocessingMLPackage, titleRow, "", titleRpr, JcEnumeration.CENTER, true, "ffffff");
//		addTableCell(factory, wordprocessingMLPackage, titleRow, headerTextMiddle, titleRpr, JcEnumeration.CENTER, true,
//				"ffffff");
//		addTableCell(factory, wordprocessingMLPackage, titleRow, headerTextRight, titleRpr, JcEnumeration.RIGHT, true,
//				"ffffff");
//		table.getContent().add(titleRow);
//		setTableAlign(factory, table, JcEnumeration.CENTER);
//
//		hdr.getContent().add(table);
//
//		return hdr;
//	}
//
//	public void addTableCell(ObjectFactory factory, WordprocessingMLPackage wordMLPackage, Tr tableRow, String content,
//			RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor, String backgroudColor) {
//		Tc tableCell = factory.createTc();
//		P p = factory.createP();
//		setParagraphAlign(factory, p, jcEnumeration);
//		// Text t = factory.createText();
//		// t.setValue(content);
//		R run = factory.createR();
//		run.setRPr(rpr);
//
//		// run.getContent().add(t);
//
//		p.getContent().add(run);
//		if (content != null) {
//			String[] contentArr = content.split("\n");
//			Text text = factory.createText();
//			text.setSpace("preserve");
//			text.setValue(contentArr[0]);
//			run.getContent().add(text);
//
//			for (int i = 1, len = contentArr.length; i < len; i++) {
//				Br br = factory.createBr();
//				run.getContent().add(br);
//				text = factory.createText();
//				text.setSpace("preserve");
//				text.setValue(contentArr[i]);
//				run.getContent().add(text);
//			}
//		}
//
//		TcPr tcPr = tableCell.getTcPr();
//		if (tcPr == null) {
//			tcPr = factory.createTcPr();
//		}
//
//		CTVerticalJc valign = factory.createCTVerticalJc();
//		valign.setVal(STVerticalJc.CENTER);
//		tcPr.setVAlign(valign);
//
//		// Removing space in cells
//		PPr pPr = factory.createPPr();
//		Spacing spacing = new Spacing();
//		spacing.setBefore(BigInteger.TWO);
//		spacing.setAfter(BigInteger.TWO);
//		// spacing.setAfterLines(BigInteger.TEN);
//		// spacing.setBeforeLines(BigInteger.TEN);
//		pPr.setSpacing(spacing);
//
//		Jc justification = factory.createJc();
//		justification.setVal(jcEnumeration);
//		pPr.setJc(justification);
//
//		p.setPPr(pPr);
//
//		tableCell.getContent().add(p);
//		if (hasBgColor) {
//			CTShd shd = tcPr.getShd();
//			if (shd == null) {
//				shd = factory.createCTShd();
//			}
//			shd.setColor("auto");
//			shd.setFill(backgroudColor);
//			tcPr.setShd(shd);
//		}
//
//		TcBorders tcb = factory.createTcPrInnerTcBorders();
//		CTBorder ctb = factory.createCTBorder();
//		STBorder stb = STBorder.NONE;
//		ctb.setVal(stb);
//		tcb.setBottom(ctb);
//		tcb.setRight(ctb);
//		tcb.setLeft(ctb);
//		tcb.setTop(ctb);
//		tcPr.setTcBorders(tcb);
//
//		tableCell.setTcPr(tcPr);
//
//		tableRow.getContent().add(tableCell);
//	}
//
//	public static void setTableAlign(ObjectFactory factory, Tbl table, JcEnumeration jcEnumeration) {
//		TblPr tablePr = table.getTblPr();
//		if (tablePr == null) {
//			tablePr = factory.createTblPr();
//		}
//		Jc jc = tablePr.getJc();
//		if (jc == null) {
//			jc = new Jc();
//		}
//		jc.setVal(jcEnumeration);
//		tablePr.setJc(jc);
//
//		TblWidth tblwidth = factory.createTblWidth();
//		tblwidth.setW(BigInteger.valueOf(5000)); // 5000 = 100%
//		tblwidth.setType("pct");
//		tablePr.setTblW(tblwidth);
//
//		table.setTblPr(tablePr);
//	}
//
//	public Hdr getHdr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, HeaderPart sourcePart,
//			String headerText) throws Exception {
//		Hdr hdr = factory.createHdr();
//
//		P p = factory.createP();
//		R r = factory.createR();
//
//		RPr boldRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, true, false, false, false);
//
//		if (!StringUtils.isEmpty(headerText)) {
//			PPr pPr = p.getPPr();
//			if (pPr == null) {
//				pPr = factory.createPPr();
//			}
//			Jc jc = pPr.getJc();
//			if (jc == null) {
//				jc = new Jc();
//			}
//			jc.setVal(JcEnumeration.CENTER);
//			pPr.setJc(jc);
//			p.setPPr(pPr);
//
//			Text txt = factory.createText();
//			txt.setValue(headerText);
//			r = factory.createR();
//			r.getContent().add(txt);
//			r.setRPr(boldRPr);
//			p.getContent().add(r);
//		}
//
//		hdr.getContent().add(p);
//
//		return hdr;
//	}
//
//	public P newImage(WordprocessingMLPackage wordMLPackage, ObjectFactory factory, HeaderPart sourcePart, byte[] bytes,
//			String filenameHint, String altText, int id1, int id2, JcEnumeration jcEnumeration) throws Exception {
//		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, sourcePart, bytes);
//		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, false);
//		P p = factory.createP();
//		R run = factory.createR();
//		p.getContent().add(run);
//		Drawing drawing = factory.createDrawing();
//		run.getContent().add(drawing);
//		drawing.getAnchorOrInline().add(inline);
//		PPr pPr = p.getPPr();
//		if (pPr == null) {
//			pPr = factory.createPPr();
//		}
//		Jc jc = pPr.getJc();
//		if (jc == null) {
//			jc = new Jc();
//		}
//		jc.setVal(jcEnumeration);
//		pPr.setJc(jc);
//		p.setPPr(pPr);
//		return p;
//	}
//
//	public void createHeaderReference(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
//			ObjectFactory factory, Relationship relationship) throws InvalidFormatException {
//		List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();
//		SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
//		// There is always a section wrapper, but it might not contain a sectPr
//		if (sectPr == null) {
//			sectPr = factory.createSectPr();
//			t.addObject(sectPr);
//			sections.get(sections.size() - 1).setSectPr(sectPr);
//		}
//		/*
//		 * HeaderReference headerReference = factory.createHeaderReference();
//		 * headerReference.setId(relationship.getId());
//		 * headerReference.setType(HdrFtrRef.FIRST);
//		 * sectPr.getEGHdrFtrReferences().add(headerReference);
//		 */
//
//		HeaderReference headerReference = factory.createHeaderReference();
//		headerReference = factory.createHeaderReference();
//		headerReference.setId(relationship.getId());
//		// headerReference.setType(HdrFtrRef.DEFAULT);
//		headerReference.setType(HdrFtrRef.FIRST);
//		sectPr.getEGHdrFtrReferences().add(headerReference);
//
//		/*
//		 * headerReference = factory.createHeaderReference();
//		 * headerReference.setId(relationship.getId());
//		 * //headerReference.setType(HdrFtrRef.DEFAULT);
//		 * headerReference.setType(HdrFtrRef.FIRST);
//		 * sectPr.getEGHdrFtrReferences().add(headerReference);
//		 */
//
//		BooleanDefaultTrue value = new BooleanDefaultTrue();
//		value.setVal(Boolean.TRUE);
//		sectPr.setTitlePg(value);
//	}
//
//	public void addPageBreak(WordprocessingMLPackage wordMLPackage, ObjectFactory factory) {
//		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
//		Br breakObj = new Br();
//		breakObj.setType(STBrType.PAGE);
//		P paragraph = factory.createP();
//		paragraph.getContent().add(breakObj);
//		documentPart.addObject(paragraph);
//	}
//
//	public void addEmptyParagraph(WordprocessingMLPackage wordMLPackage, MainDocumentPart t, ObjectFactory factory)
//			throws Exception {
//		RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, false, false, false);
//		P paragraph = factory.createP();
//		setParagraphAlign(factory, paragraph, JcEnumeration.CENTER);
//		Text txt = factory.createText();
//		txt.setValue("");
//		R run = factory.createR();
//		run.getContent().add(txt);
//		run.setRPr(titleRPr);
//		paragraph.getContent().add(run);
//		t.addObject(paragraph);
//	}
//
//	public void addHeading(WordprocessingMLPackage wordMLPackage, MainDocumentPart t, ObjectFactory factory,
//			String contentValue) throws Exception {
//		RPr titleRPr = getRPr(factory, "Calibri", "000000", "28", STHint.EAST_ASIA, true, true, false, false);
//		RPr boldRPr = getRPr(factory, "Calibri", "000000", "22", STHint.EAST_ASIA, true, false, false, false);
//		RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false, false);
//
//		P paragraph = factory.createP();
//		setParagraphAlign(factory, paragraph, JcEnumeration.CENTER);
//		Text txt = factory.createText();
//		txt.setValue(contentValue);
//		R run = factory.createR();
//		run.getContent().add(txt);
//		run.setRPr(titleRPr);
//		paragraph.getContent().add(run);
//		t.addObject(paragraph);
//	}
//
//	public RPr getRPr(ObjectFactory factory, String fontFamily, String colorVal, String fontSize, STHint sTHint,
//			boolean isBlod, boolean isUnderLine, boolean isItalic, boolean isStrike) {
//		RPr rPr = factory.createRPr();
//		RFonts rf = new RFonts();
//		rf.setHint(sTHint);
//		rf.setAscii(fontFamily);
//		rf.setHAnsi(fontFamily);
//		rPr.setRFonts(rf);
//
//		BooleanDefaultTrue bdt = factory.createBooleanDefaultTrue();
//		rPr.setBCs(bdt);
//		if (isBlod) {
//			rPr.setB(bdt);
//		}
//		if (isItalic) {
//			rPr.setI(bdt);
//		}
//		if (isStrike) {
//			rPr.setStrike(bdt);
//		}
//		if (isUnderLine) {
//			U underline = new U();
//			underline.setVal(UnderlineEnumeration.SINGLE);
//			rPr.setU(underline);
//		}
//
//		Color color = new Color();
//		color.setVal(colorVal);
//		rPr.setColor(color);
//
//		HpsMeasure sz = new HpsMeasure();
//		sz.setVal(new BigInteger(fontSize));
//		rPr.setSz(sz);
//		rPr.setSzCs(sz);
//		return rPr;
//	}
//
//	public void setParagraphAlign(ObjectFactory factory, P p, JcEnumeration jcEnumeration) {
//		PPr pPr = p.getPPr();
//		if (pPr == null) {
//			pPr = factory.createPPr();
//		}
//		Jc jc = pPr.getJc();
//		if (jc == null) {
//			jc = new Jc();
//		}
//		jc.setVal(jcEnumeration);
//		pPr.setJc(jc);
//		p.setPPr(pPr);
//	}
//
//	public Relationship createFooterPageNumPart(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
//			ObjectFactory factory) throws Exception {
//		FooterPart footerPart = new FooterPart();
//		footerPart.setPackage(wordprocessingMLPackage);
//		footerPart.setJaxbElement(createFooterWithPageNr(factory));
//		return t.addTargetPart(footerPart);
//	}
//
//	public Ftr createFooterWithPageNr(ObjectFactory factory) {
//		Ftr ftr = factory.createFtr();
//		P paragraph = factory.createP();
//		RPr fontRPr = getRPr(factory, "Calibri", "000000", "20", STHint.EAST_ASIA, false, false, false, false);
//		R run = factory.createR();
//		run.setRPr(fontRPr);
//		paragraph.getContent().add(run);
//
//		addPageTextField(factory, paragraph, "Page ");
//		addFieldBegin(factory, paragraph);
//		addPageNumberField(factory, paragraph);
//		addFieldEnd(factory, paragraph);
//		addPageTextField(factory, paragraph, " of ");
//
//		// addPageTextField(factory, paragraph, "Test");
//		addFieldBegin(factory, paragraph);
//		addTotalPageNumberField(factory, paragraph);
//		addFieldEnd(factory, paragraph);
//		// addPageTextField(factory, paragraph, "Test");
//		setParagraphAlign(factory, paragraph, JcEnumeration.CENTER);
//		ftr.getContent().add(paragraph);
//		return ftr;
//	}
//
//	public void addFieldBegin(ObjectFactory factory, P paragraph) {
//		R run = factory.createR();
//		FldChar fldchar = factory.createFldChar();
//		fldchar.setFldCharType(STFldCharType.BEGIN);
//		run.getContent().add(fldchar);
//		paragraph.getContent().add(run);
//	}
//
//	public void addFieldEnd(ObjectFactory factory, P paragraph) {
//		FldChar fldcharend = factory.createFldChar();
//		fldcharend.setFldCharType(STFldCharType.END);
//		R run3 = factory.createR();
//		run3.getContent().add(fldcharend);
//		paragraph.getContent().add(run3);
//	}
//
//	public void addPageNumberField(ObjectFactory factory, P paragraph) {
//		R run = factory.createR();
//		Text txt = new Text();
//		txt.setSpace("preserve");
//		txt.setValue("PAGE  \\* MERGEFORMAT ");
//		run.getContent().add(factory.createRInstrText(txt));
//		paragraph.getContent().add(run);
//	}
//
//	public void addTotalPageNumberField(ObjectFactory factory, P paragraph) {
//		R run = factory.createR();
//		Text txt = new Text();
//		txt.setSpace("preserve");
//		txt.setValue("NUMPAGES  \\* MERGEFORMAT ");
//		run.getContent().add(factory.createRInstrText(txt));
//		paragraph.getContent().add(run);
//	}
//
//	private void addPageTextField(ObjectFactory factory, P paragraph, String value) {
//		R run = factory.createR();
//		Text txt = new Text();
//		txt.setSpace("preserve");
//		txt.setValue(value);
//		run.getContent().add(txt);
//		paragraph.getContent().add(run);
//	}
//
//	public void createFooterReference(WordprocessingMLPackage wordprocessingMLPackage, MainDocumentPart t,
//			ObjectFactory factory, Relationship relationship) throws InvalidFormatException {
//		List<SectionWrapper> sections = wordprocessingMLPackage.getDocumentModel().getSections();
//		SectPr sectPr = sections.get(sections.size() - 1).getSectPr();
//		// There is always a section wrapper, but it might not contain a sectPr
//		if (sectPr == null) {
//			sectPr = factory.createSectPr();
//			t.addObject(sectPr);
//			sections.get(sections.size() - 1).setSectPr(sectPr);
//		}
//		FooterReference footerReference = factory.createFooterReference();
//		footerReference.setId(relationship.getId());
//		footerReference.setType(HdrFtrRef.FIRST);
//		sectPr.getEGHdrFtrReferences().add(footerReference);
//
//		footerReference = factory.createFooterReference();
//		footerReference.setId(relationship.getId());
//		footerReference.setType(HdrFtrRef.DEFAULT);
//		sectPr.getEGHdrFtrReferences().add(footerReference);
//	}
//
//	public Hdr getTextHdr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, Part sourcePart,
//			String content, JcEnumeration jcEnumeration) throws Exception {
//		Hdr hdr = factory.createHdr();
//		P headP = factory.createP();
//		Text text = factory.createText();
//		text.setValue(content);
//		R run = factory.createR();
//		run.getContent().add(text);
//		headP.getContent().add(run);
//
//		PPr pPr = headP.getPPr();
//		if (pPr == null) {
//			pPr = factory.createPPr();
//		}
//		Jc jc = pPr.getJc();
//		if (jc == null) {
//			jc = new Jc();
//		}
//		jc.setVal(jcEnumeration);
//		pPr.setJc(jc);
//		headP.setPPr(pPr);
//		hdr.getContent().add(headP);
//		return hdr;
//	}
//
//	public Ftr getTextFtr(WordprocessingMLPackage wordprocessingMLPackage, ObjectFactory factory, Part sourcePart,
//			String content, JcEnumeration jcEnumeration) throws Exception {
//		Ftr ftr = factory.createFtr();
//		P footerP = factory.createP();
//		Text text = factory.createText();
//		text.setValue(content);
//		R run = factory.createR();
//		run.getContent().add(text);
//		footerP.getContent().add(run);
//
//		PPr pPr = footerP.getPPr();
//		if (pPr == null) {
//			pPr = factory.createPPr();
//		}
//		Jc jc = pPr.getJc();
//		if (jc == null) {
//			jc = new Jc();
//		}
//		jc.setVal(jcEnumeration);
//		pPr.setJc(jc);
//		footerP.setPPr(pPr);
//		ftr.getContent().add(footerP);
//		return ftr;
//	}
}
