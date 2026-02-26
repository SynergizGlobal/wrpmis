package com.synergizglobal.wrpmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.TrainingService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.FileFormatModel;
import com.synergizglobal.wrpmis.model.Training;

@Controller
public class TrainingController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	Logger logger = Logger.getLogger(TrainingController.class);

	@Autowired
	TrainingService trainingService;

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

	@Value("${template.upload.common.error}")
	public String uploadCommonError;

	@Value("${template.upload.formatError}")
	public String uploadformatError;

	@RequestMapping(value = "/training", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView training(HttpSession session) {
		ModelAndView model = new ModelAndView(PageConstants.trainingGrid);
		try {
			List<Training> trainingList = trainingService.getTrainingsList(null);
			model.addObject("trainingList", trainingList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("training : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/get-training-List", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingsList(@ModelAttribute Training obj) {
		List<Training> trainingList = null;
		try {
			trainingList = trainingService.getTrainingsList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingsList : " + e.getMessage());
		}
		return trainingList;
	}

	/**
	 * @RequestMapping(value = "/ajax/get-training", method = { RequestMethod.POST,
	 *                       RequestMethod.GET }) public void
	 *                       getTrainingsList(@ModelAttribute Training obj,
	 *                       HttpServletRequest request, HttpServletResponse
	 *                       response, HttpSession session) throws IOException {
	 *                       PrintWriter pw = null; // JSONObject json = new
	 *                       JSONObject(); String json2 = null; String userId =
	 *                       null; String userName = null; try { userId = (String)
	 *                       session.getAttribute("USER_ID"); userName = (String)
	 *                       session.getAttribute("USER_NAME");
	 * 
	 *                       pw = response.getWriter(); // Fetch the page number
	 *                       from client Integer pageNumber = 0; Integer
	 *                       pageDisplayLength = 0; if (null !=
	 *                       request.getParameter("iDisplayStart")) {
	 *                       pageDisplayLength =
	 *                       Integer.valueOf(request.getParameter("iDisplayLength"));
	 *                       pageNumber =
	 *                       (Integer.valueOf(request.getParameter("iDisplayStart"))
	 *                       / pageDisplayLength) + 1; } // Fetch search parameter
	 *                       String searchParameter =
	 *                       request.getParameter("sSearch");
	 * 
	 *                       // Fetch Page display length pageDisplayLength =
	 *                       Integer.valueOf(request.getParameter("iDisplayLength"));
	 * 
	 *                       List<Training> designsList = new ArrayList<Training>();
	 * 
	 *                       // Here is server side pagination logic. Based on the
	 *                       page number you could make // call // to the data base
	 *                       create new list and send back to the client. For demo I
	 *                       am // shuffling // the same list to show data randomly
	 *                       int startIndex = 0; int offset = pageDisplayLength;
	 * 
	 *                       if (pageNumber == 1) { startIndex = 0; offset =
	 *                       pageDisplayLength; designsList =
	 *                       createPaginationData(startIndex, offset, obj,
	 *                       searchParameter); } else { startIndex = (pageNumber *
	 *                       offset) - offset; offset = pageDisplayLength;
	 *                       designsList = createPaginationData(startIndex, offset,
	 *                       obj, searchParameter); }
	 * 
	 *                       // Search functionality: Returns filtered list based on
	 *                       search parameter // designsList =
	 *                       getListBasedOnSearchParameter(searchParameter,designsList);
	 * 
	 *                       int totalRecords = getTotalRecords(obj,
	 *                       searchParameter);
	 * 
	 *                       TrainingPaginationObject personJsonObject = new
	 *                       TrainingPaginationObject(); // Set Total display record
	 *                       personJsonObject.setiTotalDisplayRecords(totalRecords);
	 *                       // Set Total record
	 *                       personJsonObject.setiTotalRecords(totalRecords);
	 *                       personJsonObject.setAaData(designsList);
	 * 
	 *                       Gson gson = new
	 *                       GsonBuilder().setPrettyPrinting().create(); json2 =
	 *                       gson.toJson(personJsonObject); } catch (Exception e) {
	 *                       e.printStackTrace(); logger.error( "getTrainingsList :
	 *                       User Id - " + userId + " - User Name - " + userName + "
	 *                       - " + e.getMessage()); }
	 * 
	 *                       pw.println(json2); }
	 * 
	 *                       /**
	 * @param searchParameter
	 * @param activity
	 * @return
	 */
	/**
	 * public int getTotalRecords(Training obj, String searchParameter) { int
	 * totalRecords = 0; try { totalRecords = trainingService.getTotalRecords(obj,
	 * searchParameter); } catch (Exception e) { logger.error("getTotalRecords : " +
	 * e.getMessage()); } return totalRecords; }
	 */
	/**
	 * @param pageDisplayLength
	 * @param offset
	 * @param activity
	 * @param clientId
	 * @return
	 */
	/**
	 * public List<Training> createPaginationData(int startIndex, int offset,
	 * Training obj, String searchParameter) { List<Training> objList = null; try {
	 * objList = trainingService.getTrainingsList(obj, startIndex, offset,
	 * searchParameter); } catch (Exception e) { logger.error("createPaginationData
	 * : " + e.getMessage()); } return objList; }
	 */
	@RequestMapping(value = "/ajax/getTrainingTypesFilterListInTraining", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingTypesList(@ModelAttribute Training obj) {
		List<Training> trainingTypesList = null;
		try {
			trainingTypesList = trainingService.getTrainingTypesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingTypesList : " + e.getMessage());
		}
		return trainingTypesList;
	}

	@RequestMapping(value = "/ajax/getTrainingCategorysFilterListInTraining", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingCategorysList(@ModelAttribute Training obj) {
		List<Training> trainingCategorysList = null;
		try {
			trainingCategorysList = trainingService.getTrainingCategorysList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingCategorysList : " + e.getMessage());
		}
		return trainingCategorysList;
	}

	@RequestMapping(value = "/ajax/getTrainingTitlesFilterListInTraining", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingTitlesList(@ModelAttribute Training obj) {
		List<Training> trainingTitlesList = null;
		try {
			trainingTitlesList = trainingService.getTrainingTitlesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingTitlesList : " + e.getMessage());
		}
		return trainingTitlesList;
	}

	@RequestMapping(value = "/ajax/getStatusFilterListInTraining", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getStatusList(@ModelAttribute Training obj) {
		List<Training> statusList = null;
		try {
			statusList = trainingService.getStatusList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return statusList;
	}

	@RequestMapping(value = "/ajax/getAttendeesListForTrainingForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getAttendeesList(@ModelAttribute Training obj) {
		List<Training> statusList = null;
		try {
			statusList = trainingService.getAttendeesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getAttendeesList : " + e.getMessage());
		}
		return statusList;
	}

	@RequestMapping(value = "/ajax/getHODsListForTrainingForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getHODsList(@ModelAttribute Training obj) {
		List<Training> statusList = null;
		try {
			statusList = trainingService.getUsersList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODsList : " + e.getMessage());
		}
		return statusList;
	}

	@RequestMapping(value = "/add-training-form", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addTrainingForm(@ModelAttribute Training obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addEditTrainingForm);
			model.addObject("action", "add");

			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);

			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);

			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);

			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);

			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);

			List<Training> usersList = trainingService.getUsersList(obj);
			model.addObject("usersList", usersList);

			List<Training> attendeesList = trainingService.getAttendeesList(obj);
			model.addObject("attendeesList", attendeesList);

			List<Training> periodicityList = trainingService.getperiodicityList();
			model.addObject("periodicityList", periodicityList);

			List<Training> providedList = trainingService.getprovidedList();
			model.addObject("providedList", providedList);

			List<Training> training_ConductedList = trainingService.gettraining_ConductedList();
			model.addObject("training_ConductedList", training_ConductedList);

			List<Training> contract_short_nameList = trainingService.getcontract_short_nameList();
			model.addObject("contract_short_nameList", contract_short_nameList);

		} catch (Exception e) {
			logger.error("addTrainingForm : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/create-training", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView createTraining(@ModelAttribute Training obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.createUpdateTrainingForm);
			model.addObject("action", "add");

			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);

			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);

			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);

			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);

			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);

			List<Training> usersList = trainingService.getUsersList(obj);
			model.addObject("usersList", usersList);

			List<Training> periodicityList = trainingService.getperiodicityList();
			model.addObject("periodicityList", periodicityList);

			List<Training> providedList = trainingService.getprovidedList();
			model.addObject("providedList", providedList);

			List<Training> training_ConductedList = trainingService.gettraining_ConductedList();
			model.addObject("training_ConductedList", training_ConductedList);

			List<Training> contract_short_nameList = trainingService.getcontract_short_nameList();
			model.addObject("contract_short_nameList", contract_short_nameList);

			List<Training> attendeesList = trainingService.getAttendeesList(obj);
			model.addObject("attendeesList", attendeesList);
		} catch (Exception e) {
			logger.error("addTrainingForm : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/get-training", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getTraining(@ModelAttribute Training obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.createUpdateTrainingForm);
			model.addObject("action", "edit");

			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);

			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);

			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);

			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);

			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);

			Training trainingDetails = trainingService.getTraining(obj);
			model.addObject("trainingDetails", trainingDetails);

			List<Training> usersList = trainingService.getUsersList(obj);
			model.addObject("usersList", usersList);

			List<Training> periodicityList = trainingService.getperiodicityList();
			model.addObject("periodicityList", periodicityList);

			List<Training> providedList = trainingService.getprovidedList();
			model.addObject("providedList", providedList);

			List<Training> training_ConductedList = trainingService.gettraining_ConductedList();
			model.addObject("training_ConductedList", training_ConductedList);

			List<Training> contract_short_nameList = trainingService.getcontract_short_nameList();
			model.addObject("contract_short_nameList", contract_short_nameList);

			List<Training> attendeesList = trainingService.getAttendeesList(obj);
			model.addObject("attendeesList", attendeesList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTraining : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/get-training/{training_id}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getTraining(@ModelAttribute Training obj, @PathVariable("training_id") String training_id,
			HttpSession session, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.createUpdateTrainingForm);
			model.addObject("action", "edit");

			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);

			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);

			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);

			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);

			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);

			Training trainingDetails = trainingService.getTraining(obj);
			model.addObject("trainingDetails", trainingDetails);

			List<Training> usersList = trainingService.getUsersList(obj);
			model.addObject("usersList", usersList);

			List<Training> periodicityList = trainingService.getperiodicityList();
			model.addObject("periodicityList", periodicityList);

			List<Training> providedList = trainingService.getprovidedList();
			model.addObject("providedList", providedList);

			List<Training> training_ConductedList = trainingService.gettraining_ConductedList();
			model.addObject("training_ConductedList", training_ConductedList);

			List<Training> contract_short_nameList = trainingService.getcontract_short_nameList();
			model.addObject("contract_short_nameList", contract_short_nameList);

			List<Training> attendeesList = trainingService.getAttendeesList(obj);
			model.addObject("attendeesList", attendeesList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTraining : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/add-training", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView addTraining(@ModelAttribute Training obj, RedirectAttributes attributes, HttpSession session,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {

			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setForm_designation(userDesignation);

			model.setViewName("redirect:/training");

			obj.setStart_time(DateParser.parseDateTime(obj.getStart_time()));
			obj.setEnd_time(DateParser.parseDateTime(obj.getEnd_time()));

			boolean flag = trainingService.addTraining(obj);
			if (flag) {
				attributes.addFlashAttribute("success", "Training Added Succesfully.");
			} else {
				attributes.addFlashAttribute("error", "Adding Training is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", "Adding Training is failed. Try again.");
			logger.error("addTraining : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-training", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ModelAndView updateTraining(@ModelAttribute Training obj, RedirectAttributes attributes,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setForm_designation(userDesignation);
			model.setViewName("redirect:/training");
			obj.setStart_time(DateParser.parseDateTime(obj.getStart_time()));
			obj.setEnd_time(DateParser.parseDateTime(obj.getEnd_time()));

			boolean flag = trainingService.updateTraining(obj);
			if (flag) {
				attributes.addFlashAttribute("success", "Training Updated Succesfully.");
			} else {
				attributes.addFlashAttribute("error", "Updating Training is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Updating Training is failed. Try again.");
			logger.error("updateTraining : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/export-training", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportTraining(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute Training dObj, RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView(PageConstants.trainingGrid);
		List<Training> dataList = new ArrayList<Training>();
		List<Training> sessionsList = new ArrayList<Training>();
		List<Training> attendeesList = new ArrayList<Training>();
		try {
			view.setViewName("redirect:/training");
			dataList = trainingService.getTrainingsList(dObj);

			if (dataList != null && dataList.size() > 0) {
				XSSFWorkbook workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Training"));
				XSSFSheet sessionsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Sessions"));
				XSSFSheet attendeesSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Attendees"));
				workBook.setSheetOrder(sheet.getSheetName(), 0);
				workBook.setSheetOrder(sessionsSheet.getSheetName(), 1);
				workBook.setSheetOrder(attendeesSheet.getSheetName(), 2);

				byte[] blueRGB = new byte[] { (byte) 0, (byte) 176, (byte) 240 };
				byte[] yellowRGB = new byte[] { (byte) 255, (byte) 192, (byte) 0 };
				byte[] greenRGB = new byte[] { (byte) 146, (byte) 208, (byte) 80 };
				byte[] redRGB = new byte[] { (byte) 255, (byte) 0, (byte) 0 };
				byte[] whiteRGB = new byte[] { (byte) 255, (byte) 255, (byte) 255 };

				boolean isWrapText = true;
				boolean isBoldText = true;
				boolean isItalicText = false;
				int fontSize = 11;
				String fontName = "Times New Roman";
				CellStyle blueStyle = cellFormating(workBook, blueRGB, HorizontalAlignment.CENTER,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);
				CellStyle yellowStyle = cellFormating(workBook, yellowRGB, HorizontalAlignment.CENTER,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);
				CellStyle greenStyle = cellFormating(workBook, greenRGB, HorizontalAlignment.CENTER,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);
				CellStyle redStyle = cellFormating(workBook, redRGB, HorizontalAlignment.CENTER,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);
				CellStyle whiteStyle = cellFormating(workBook, whiteRGB, HorizontalAlignment.CENTER,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);

				CellStyle indexWhiteStyle = cellFormating(workBook, whiteRGB, HorizontalAlignment.LEFT,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);

				isWrapText = true;
				isBoldText = false;
				isItalicText = false;
				fontSize = 9;
				fontName = "Times New Roman";
				CellStyle sectionStyle = cellFormating(workBook, whiteRGB, HorizontalAlignment.LEFT,
						VerticalAlignment.CENTER, isWrapText, isBoldText, isItalicText, fontSize, fontName);

				XSSFRow headingRow = sheet.createRow(0);
				String headerString = "Training^Training Type^Category^Faculty Name^Designation^Title^Description^Training Center^Status^Remarks";

				String[] firstHeaderStringArr = headerString.split("\\^");

				for (int i = 0; i < firstHeaderStringArr.length; i++) {
					Cell cell = headingRow.createCell(i);
					cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}

				XSSFRow headingRow1 = sessionsSheet.createRow(0);
				String headerString1 = "Training^Session No^Date^Start Time^End Time^Remark^Nominated^Attended";

				String[] secondHeaderStringArr = headerString1.split("\\^");

				for (int i = 0; i < secondHeaderStringArr.length; i++) {
					Cell cell = headingRow1.createCell(i);
					cell.setCellStyle(greenStyle);
					cell.setCellValue(secondHeaderStringArr[i]);
				}

				XSSFRow headingRow2 = attendeesSheet.createRow(0);
				String headerString2 = "Training^Session No^Department^Name of attendee in the meenting^Designation^HOD^Mobile No^E-mail^Required (Yes / No)^Participated (Yes / No)";

				String[] thirdHeaderStringArr = headerString2.split("\\^");

				for (int i = 0; i < thirdHeaderStringArr.length; i++) {
					Cell cell = headingRow2.createCell(i);
					cell.setCellStyle(greenStyle);
					cell.setCellValue(thirdHeaderStringArr[i]);
				}

				short rowNo3 = 1;
				for (Training tariningAttendees : dataList) {
					String trainingId = tariningAttendees.getTraining_id();
					attendeesList = trainingService.getTrainingAttendeesList(trainingId);

					for (Training obj : attendeesList) {
						XSSFRow row = attendeesSheet.createRow(rowNo3);
						int a = 0;

						Cell cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getDescription());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getSession_no());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getDepartment_name());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getAttendee());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getDesignation());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getHod_user_id_fk());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getMobile_no());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getEmail());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getRequired_fk());

						cell2 = row.createCell(a++);
						cell2.setCellStyle(sectionStyle);
						cell2.setCellValue(obj.getParticipated_fk());

						rowNo3++;
					}
				}

				short rowNo2 = 1;

				for (Training trainingSessions : dataList) {
					String id = trainingSessions.getTraining_id();

					sessionsList = trainingService.getTrainingSessionsList(id);

					for (Training obj : sessionsList) {
						XSSFRow row = sessionsSheet.createRow(rowNo2);
						int b = 0;

						Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDescription());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getSession_no());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStart_time());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEnd_time());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getSession_remarks());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getNominated());

						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAttended());

						rowNo2++;
					}
				}
				short rowNo = 1;
				for (Training obj : dataList) {
					XSSFRow row = sheet.createRow(rowNo);
					int c = 0;

					Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDescription());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTraining_type_fk());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTraining_category_fk());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getFaculty_name());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDesignation());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTitle());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDescription());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTraining_center());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStatus_fk());

					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());

					rowNo++;
				}

				for (int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
					sheet.setColumnWidth(columnIndex, 25 * 200);
					// trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
				for (int columnIndex = 0; columnIndex < sessionsList.size(); columnIndex++) {
					sessionsSheet.setColumnWidth(columnIndex, 25 * 200);
					// trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
				for (int columnIndex = 0; columnIndex < attendeesList.size(); columnIndex++) {
					attendeesSheet.setColumnWidth(columnIndex, 25 * 200);
					// trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
				Date date = new Date();
				String fileName = "Training_" + dateFormat.format(date);

				try {
					/*
					 * FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
					 * workBook.write(fos); fos.flush();
					 */

					response.setContentType("application/.csv");
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setContentType("application/vnd.ms-excel");
					// add response header
					response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

					// copies all bytes from a file to an output stream
					workBook.write(response.getOutputStream()); // Write workbook to response.
					workBook.close();
					// flushes output stream
					response.getOutputStream().flush();

					attributes.addFlashAttribute("success", dataExportSucess);
					// response.setContentType("application/vnd.ms-excel");
					// response.setHeader("Content-Disposition", "attachment;
					// filename=filename.xls");
					// XSSFWorkbook workbook = new XSSFWorkbook ();
					// ...
					// Now populate workbook the usual way.
					// ...
					// workbook.write(response.getOutputStream()); // Write workbook to response.
					// workbook.close();
				} catch (FileNotFoundException e) {
					// e.printStackTrace();
					attributes.addFlashAttribute("error", dataExportInvalid);
				} catch (IOException e) {
					// e.printStackTrace();
					attributes.addFlashAttribute("error", dataExportError);
				}
			} else {
				attributes.addFlashAttribute("error", dataExportNoData);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private CellStyle cellFormating(XSSFWorkbook workBook, byte[] rgb, HorizontalAlignment hAllign,
			VerticalAlignment vAllign, boolean isWrapText, boolean isBoldText, boolean isItalicText, int fontSize,
			String fontName) {
		CellStyle style = workBook.createCellStyle();
		// Setting Background color
		// style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		if (style instanceof XSSFCellStyle) {
			XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle) style;
			xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		// style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);

		Font font = workBook.createFont();
		// font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName(fontName); // "Times New Roman"

		font.setItalic(isItalicText);
		font.setBold(isBoldText);
		// Applying font to the style
		style.setFont(font);

		return style;
	}

	@RequestMapping(value = "/export-training-details", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportTrainingDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute Training dObj, RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView(PageConstants.trainingGrid);
		List<Training> sessionsList = new ArrayList<Training>();
		List<Training> attendeesList = new ArrayList<Training>();
		try {
			view.setViewName("redirect:/training");
			String id = dObj.getTraining_id();
			sessionsList = trainingService.getTrainingSessionsList(id);
			XSSFWorkbook workBook = new XSSFWorkbook();

			if (sessionsList != null && sessionsList.size() > 0) {
				// XSSFWorkbook workBook = new XSSFWorkbook ();
				XSSFSheet sessionsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Sessions"));
				workBook.setSheetOrder(sessionsSheet.getSheetName(), 0);
				XSSFRow headingRow1 = sessionsSheet.createRow(0);
				headingRow1.createCell((short) 0).setCellValue("Training");
				headingRow1.createCell((short) 1).setCellValue("Session No");
				headingRow1.createCell((short) 2).setCellValue("Date");
				headingRow1.createCell((short) 3).setCellValue("Start Time");
				headingRow1.createCell((short) 4).setCellValue("End Time");
				headingRow1.createCell((short) 5).setCellValue("Remark");
				headingRow1.createCell((short) 6).setCellValue("Nominated");
				headingRow1.createCell((short) 7).setCellValue("Attended");

				short rowNo = 1;
				for (Training sObj : sessionsList) {
					XSSFRow row = sessionsSheet.createRow(rowNo);
					row.createCell((short) 0).setCellValue(sObj.getDescription());
					row.createCell((short) 1).setCellValue(sObj.getSession_no());
					row.createCell((short) 2).setCellValue(sObj.getDate());
					row.createCell((short) 3).setCellValue(sObj.getStart_time());
					row.createCell((short) 4).setCellValue(sObj.getEnd_time());
					row.createCell((short) 5).setCellValue(sObj.getSession_remarks());
					row.createCell((short) 6).setCellValue(sObj.getNominated());
					row.createCell((short) 7).setCellValue(sObj.getAttended());

					rowNo++;
				}

				XSSFSheet attendeesSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Attendees"));
				workBook.setSheetOrder(attendeesSheet.getSheetName(), 1);
				XSSFRow headingRow2 = attendeesSheet.createRow(0);
				headingRow2.createCell((short) 0).setCellValue("Training");
				headingRow2.createCell((short) 1).setCellValue("Session No");
				headingRow2.createCell((short) 2).setCellValue("Department");
				headingRow2.createCell((short) 3).setCellValue("Name of attendee in the meenting");
				headingRow2.createCell((short) 4).setCellValue("HOD");
				headingRow2.createCell((short) 5).setCellValue("Mobile No");
				headingRow2.createCell((short) 6).setCellValue("E-mail");
				headingRow2.createCell((short) 7).setCellValue("Required (Yes / No)");
				headingRow2.createCell((short) 8).setCellValue("Participated (Yes / No)");

				short rowNo1 = 1;
				for (Training tariningAttendees : sessionsList) {
					String trainingId = tariningAttendees.getTraining_id();
					attendeesList = trainingService.getTrainingAttendeesList(trainingId);

					for (Training aObj : attendeesList) {
						XSSFRow row3 = attendeesSheet.createRow(rowNo1);
						row3.createCell((short) 0).setCellValue(aObj.getDescription());
						row3.createCell((short) 1).setCellValue(aObj.getSession_no());
						row3.createCell((short) 2).setCellValue(aObj.getDepartment_fk());
						row3.createCell((short) 3).setCellValue(aObj.getAttendee());
						row3.createCell((short) 4).setCellValue(aObj.getDesignation());
						row3.createCell((short) 5).setCellValue(aObj.getMobile_no());
						row3.createCell((short) 6).setCellValue(aObj.getEmail());
						row3.createCell((short) 7).setCellValue(aObj.getRequired_fk());
						row3.createCell((short) 8).setCellValue(aObj.getParticipated_fk());

						rowNo1++;
					}
				}
				for (int columnIndex = 0; columnIndex < sessionsList.size(); columnIndex++) {
					sessionsSheet.autoSizeColumn(columnIndex);
					// trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
				for (int columnIndex = 0; columnIndex < attendeesList.size(); columnIndex++) {
					attendeesSheet.autoSizeColumn(columnIndex);
					// trainingSheet.setColumnWidth(columnIndex, 25 * 200);
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
				Date date = new Date();
				String fileName = "Training_" + dateFormat.format(date);

				try {
					/*
					 * FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
					 * workBook.write(fos); fos.flush();
					 */

					response.setContentType("application/.csv");
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setContentType("application/vnd.ms-excel");
					// add response header
					response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

					// copies all bytes from a file to an output stream
					workBook.write(response.getOutputStream()); // Write workbook to response.
					workBook.close();
					// flushes output stream
					response.getOutputStream().flush();

					attributes.addFlashAttribute("success", dataExportSucess);
					// response.setContentType("application/vnd.ms-excel");
					// response.setHeader("Content-Disposition", "attachment;
					// filename=filename.xls");
					// XSSFWorkbook workbook = new XSSFWorkbook ();
					// ...
					// Now populate workbook the usual way.
					// ...
					// workbook.write(response.getOutputStream()); // Write workbook to response.
					// workbook.close();
				} catch (FileNotFoundException e) {
					// e.printStackTrace();
					attributes.addFlashAttribute("error", dataExportInvalid);
				} catch (IOException e) {
					// e.printStackTrace();
					attributes.addFlashAttribute("error", dataExportError);
				}
			} else {
				attributes.addFlashAttribute("error", dataExportNoData);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping(value = "/upload-training", method = { RequestMethod.POST })
	public ModelAndView uploadTraining(@ModelAttribute Training training, RedirectAttributes attributes,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/training");

			if (!StringUtils.isEmpty(training.getTrainingFile())) {
				MultipartFile multipartFile = training.getTrainingFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0) {
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if (sheetsCount > 0) {
						XSSFSheet trainingsSheet = workbook.getSheetAt(2);
						// System.out.println(uploadFilesSheet.getSheetName());
						// header row
						XSSFRow headerRow = trainingsSheet.getRow(1);
						int list = trainingsSheet.getLastRowNum();
						// checking given file format
						if (headerRow != null) {
							List<String> fileFormat = FileFormatModel.getTrainingFileFormat();
							int noOfColumns = headerRow.getLastCellNum();
							if (noOfColumns == fileFormat.size()) {
								for (int i = 0; i < fileFormat.size(); i++) {
									// System.out.println(headerRow.getCell(i).getStringCellValue().trim());
									// if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if (!columnName.equals(fileFormat.get(i).trim())
											&& !columnName.contains(fileFormat.get(i).trim())) {
										attributes.addFlashAttribute("error", uploadformatError);
										return model;
									}
								}
							} else {
								attributes.addFlashAttribute("error", uploadformatError);
								return model;
							}
						} else {
							attributes.addFlashAttribute("error", uploadformatError);
							return model;
						}

						int count = uploadTrainings(training, userId, userName);

						attributes.addFlashAttribute("success", count + " Trainings added successfully.");
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			}

		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : " + e.getMessage());
		}
		return model;
	}

	private int uploadTrainings(Training obj, String userId, String userName) throws Exception {
		Training training = null;
		List<Training> trainingsList = new ArrayList<Training>();
		Writer w = null;
		int j = 0;
		int k = 0;
		int i = 0;
		int count = 0;
		try {
			MultipartFile excelfile = obj.getTrainingFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0) {
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if (sheetsCount > 0) {
					XSSFSheet trainingsSheet = workbook.getSheetAt(2);
					// System.out.println(uploadFilesSheet.getSheetName());
					// header row
					// XSSFRow headerRow = uploadFilesSheet.getRow(0);
					DataFormatter formatter = new DataFormatter(); // creating formatter using the default locale
					// System.out.println(uploadFilesSheet.getLastRowNum());
					int list = trainingsSheet.getLastRowNum();
					for (i = 2; i <= trainingsSheet.getLastRowNum(); i++) {
						XSSFRow row = trainingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						// System.out.println(i);
						training = new Training();
						String val = null;
						if (!StringUtils.isEmpty(row)) {
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setTraining_id(val);
							}

							val = formatter.formatCellValue(row.getCell(1)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setTraining_type_fk(val);
							}

							val = formatter.formatCellValue(row.getCell(2)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setTraining_category_fk(val);
							}

							val = formatter.formatCellValue(row.getCell(3)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setFaculty_name(val);
							}

							val = formatter.formatCellValue(row.getCell(4)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setDesignation(val);
							}

							val = formatter.formatCellValue(row.getCell(5)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setTitle(val);
							}

							val = formatter.formatCellValue(row.getCell(6)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setDescription(val);
							}

							val = formatter.formatCellValue(row.getCell(7)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setTraining_center(val);
							}

							val = formatter.formatCellValue(row.getCell(8)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setStatus_fk(val);
							}

							val = formatter.formatCellValue(row.getCell(9)).trim();
							if (!StringUtils.isEmpty(val)) {
								training.setRemarks(val);
							}

						}
						List<Training> sObjList = new ArrayList<Training>();

						XSSFSheet trainingSessionsSheet = workbook.getSheetAt(3);
						int list1 = trainingSessionsSheet.getLastRowNum();
						for (j = 2; j <= trainingSessionsSheet.getLastRowNum(); j++) {
							XSSFRow row2 = trainingSessionsSheet.getRow(j);
							// Sets the Read data to the model class
							Training sObj = new Training();
							if (!StringUtils.isEmpty(row2)) {
								val = formatter.formatCellValue(row2.getCell(0)).trim();
								if (!StringUtils.isEmpty(val)) {
									sObj.setTraining_id(val);
								}

								val = formatter.formatCellValue(row2.getCell(1)).trim();
								if (!StringUtils.isEmpty(val)) {
									sObj.setSession_no(val);
								}

								val = formatter.formatCellValue(row2.getCell(2)).trim();
								if (!StringUtils.isEmpty(val)) {
									sObj.setDate(val);
								}

								val = formatter.formatCellValue(row2.getCell(3)).trim();
								if (!StringUtils.isEmpty(val)) {
									sObj.setStart_time(val);
								}

								val = formatter.formatCellValue(row2.getCell(4)).trim();
								if (!StringUtils.isEmpty(val)) {
									sObj.setEnd_time(val);
								}

								val = formatter.formatCellValue(row2.getCell(5)).trim();
								if (!StringUtils.isEmpty(val)) {
									sObj.setRemarks(val);
								}

								if (!StringUtils.isEmpty(sObj.getDate())) {
									String date = (sObj.getDate() + " " + sObj.getStart_time());
									String date2 = (sObj.getDate() + " " + sObj.getEnd_time());

									DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
									DateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
									Date startDate = null;
									startDate = df.parse(date);
									Date EndDate = null;
									EndDate = df.parse(date2);
									sObj.setStart_time((outputformat.format(startDate)));
									sObj.setEnd_time((outputformat.format(EndDate)));
								}
							}
							if (!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getTraining_id())
									&& sObj.getTraining_id().equals(training.getTraining_id()))
								sObjList.add(sObj);
							training.setTrainingSessions(sObjList);

							List<Training> aObjList = new ArrayList<Training>();
							XSSFSheet trainingAttendeesSheet = workbook.getSheetAt(4);
							int list2 = trainingAttendeesSheet.getLastRowNum();
							for (k = 1; k <= trainingAttendeesSheet.getLastRowNum(); k++) {
								XSSFRow row3 = trainingAttendeesSheet.getRow(k);
								Training aObj = new Training();
								if (!StringUtils.isEmpty(row3)) {

									val = formatter.formatCellValue(row3.getCell(0)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setTraining_id(val);
									}

									val = formatter.formatCellValue(row3.getCell(1)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setSession_no(val);
									}

									val = formatter.formatCellValue(row3.getCell(2)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setDepartment_fk(val);
									}

									val = formatter.formatCellValue(row3.getCell(3)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setAttendee(val);
									}

									val = formatter.formatCellValue(row3.getCell(4)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setHod_user_id_fk(val);
									}

									val = formatter.formatCellValue(row3.getCell(5)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setMobile_no(val);
									}

									val = formatter.formatCellValue(row3.getCell(6)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setRequired_fk(val);
									}

									val = formatter.formatCellValue(row3.getCell(7)).trim();
									if (!StringUtils.isEmpty(val)) {
										aObj.setParticipated_fk(val);
									}
								}
								if (!StringUtils.isEmpty(aObj) && !StringUtils.isEmpty(aObj.getTraining_id())
										&& aObj.getTraining_id().equals(training.getTraining_id()))
									aObjList.add(aObj);
							}
							training.setTrainingAttendees(aObjList);
						}
						boolean flag = training.checkNullOrEmpty();

						if (!flag) {
							trainingsList.add(training);
						}
					}

					if (!trainingsList.isEmpty() && trainingsList != null) {
						count = trainingService.uploadTraining(trainingsList);
					}
				}
				workbook.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadTrainings() : " + e.getMessage());
			throw new Exception(e);
		} finally {
			try {
				if (w != null)
					w.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("uploadTrainings() : " + e.getMessage());
				throw new Exception(e);
			}
		}

		return count;
	}

}
