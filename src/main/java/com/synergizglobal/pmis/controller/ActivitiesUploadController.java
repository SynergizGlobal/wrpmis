package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.ActivitiesUploadService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.ActivityObject;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.StripChart;

@Controller
public class ActivitiesUploadController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	Logger logger = Logger.getLogger(ActivitiesUploadController.class);

	@Autowired
	ActivitiesUploadService service;

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

	@RequestMapping(value = "/activities-upload", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView activitiesUpload(@ModelAttribute StripChart obj, HttpSession session) {
		ModelAndView model = new ModelAndView(PageConstants2.progressUploadGrid);
		try {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("activitiesUpload : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getWorksListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getWorksListFilter(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getContractsListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getContractsListFilter(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilter : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getStructureListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStructureListFilter(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getStructureListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureListFilter : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getComponentIdsListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getComponentIdsListFilter(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getComponentIdsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getComponentIdsListFilter : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getComponentsListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getComponentsListFilter(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getComponentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getComponentsListFilter : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getActivitiesList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute StripChart obj, HttpServletRequest request,
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

			List<StripChart> activityList = new ArrayList<StripChart>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				activityList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				activityList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//activityList = getListBasedOnSearchParameter(searchParameter,activityList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ActivityObject personJsonObject = new ActivityObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(activityList);

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
	public int getTotalRecords(StripChart obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = service.getTotalRecords(obj, searchParameter);
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
	public List<StripChart> createPaginationData(int startIndex, int offset, StripChart obj, String searchParameter) {
		List<StripChart> earthWorkList = null;
		try {
			earthWorkList = service.getActivitiesList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}

	@RequestMapping(value = "/export-activities", method = { RequestMethod.GET, RequestMethod.POST })
	public void exportActivities(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute StripChart activity, RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView(PageConstants2.progressUploadGrid);
		List<StripChart> dataList = new ArrayList<StripChart>();
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/activities-upload");
			dataList = service.getActivitiesList(activity, null, null, null);
			if (dataList != null && dataList.size() > 0) {
				XSSFWorkbook workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet();
				XSSFRow headingRow = sheet.createRow(0);
				headingRow.createCell((short) 0).setCellValue("Contract");
				headingRow.createCell((short) 1).setCellValue("Structure");
				headingRow.createCell((short) 2).setCellValue("Component Id");
				headingRow.createCell((short) 3).setCellValue("Component");
				headingRow.createCell((short) 5).setCellValue("Activity");
				headingRow.createCell((short) 6).setCellValue("Planned Start");
				headingRow.createCell((short) 7).setCellValue("Planned Finish");
				headingRow.createCell((short) 8).setCellValue("Scope");
				headingRow.createCell((short) 9).setCellValue("Completed");
				headingRow.createCell((short) 10).setCellValue("Weightage");

				short rowNo = 1;
				for (StripChart obj : dataList) {
					XSSFRow row = sheet.createRow(rowNo);
					String contract_short_name = "";
					if (!StringUtils.isEmpty(obj.getContract_short_name())) {
						contract_short_name = " - " + obj.getContract_short_name();
					}
					row.createCell((short) 0).setCellValue(obj.getContract_id() + contract_short_name);
					row.createCell((short) 1).setCellValue(obj.getStrip_chart_structure());
					row.createCell((short) 2).setCellValue(obj.getStrip_chart_component_id_name());
					row.createCell((short) 3).setCellValue(obj.getStrip_chart_component());
					row.createCell((short) 5).setCellValue(obj.getStrip_chart_activity_name());
					row.createCell((short) 6).setCellValue(obj.getPlanned_start());
					row.createCell((short) 7).setCellValue(obj.getPlanned_finish());
					row.createCell((short) 8).setCellValue(obj.getScope());
					row.createCell((short) 9).setCellValue(obj.getCompleted());
					row.createCell((short) 10).setCellValue(obj.getWeight());
					rowNo++;
				}

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
				Date date = new Date();
				String fileName = "Activities_" + dateFormat.format(date);

				try {
					/*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
					workBook.write(fos);
					fos.flush();*/

					response.setContentType("application/.csv");
					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setContentType("application/vnd.ms-excel");
					// add response header
					response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");

					//copies all bytes from a file to an output stream
					workBook.write(response.getOutputStream()); // Write workbook to response.
					workBook.close();
					//flushes output stream
					response.getOutputStream().flush();

					attributes.addFlashAttribute("success", dataExportSucess);
					//response.setContentType("application/vnd.ms-excel");
					//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
					//XSSFWorkbook  workbook = new XSSFWorkbook ();
					// ...
					// Now populate workbook the usual way.
					// ...
					//workbook.write(response.getOutputStream()); // Write workbook to response.
					//workbook.close();
				} catch (FileNotFoundException e) {
					//e.printStackTrace();
					attributes.addFlashAttribute("error", dataExportInvalid);
				} catch (IOException e) {
					//e.printStackTrace();
					attributes.addFlashAttribute("error", dataExportError);
				}
			} else {
				attributes.addFlashAttribute("error", dataExportNoData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"exportActivities : : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
			attributes.addFlashAttribute("error", commonError);
		}
		//return view;
	}

	@RequestMapping(value = "/upload-activities", method = { RequestMethod.POST })
	public ModelAndView uploadActivities(@ModelAttribute StripChart activity, RedirectAttributes attributes,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		String fileName = null;
		String userId = null;
		String userName = null;
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet3 = null;
		XSSFSheet uploadFilesSheet4 = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/activities-upload");

			if (!StringUtils.isEmpty(activity.getStripChartFile())) {
				MultipartFile multipartFile = activity.getStripChartFile();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0) {
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if (workbook != null) {
						int sheetsCount = workbook.getNumberOfSheets();
						if (sheetsCount > 0) {

							uploadFilesSheet3 = workbook.getSheetAt(2);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet3.getRow(1);
							//checking given file format
							if (headerRow != null) {
								List<String> fileFormat = FileFormatModel.getStripChartActivitiesSheet_3_FileFormat();
								;
								int noOfColumns = headerRow.getLastCellNum();
								if (noOfColumns == fileFormat.size()) {
									for (int i = 0; i < fileFormat.size(); i++) {
										//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
										//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
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

							uploadFilesSheet4 = workbook.getSheetAt(3);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow2 = uploadFilesSheet4.getRow(1);
							//checking given file format
							if (headerRow2 != null) {
								List<String> fileFormat = FileFormatModel.getStripChartActivitiesSheet_4_FileFormat();
								;
								int noOfColumns = headerRow2.getLastCellNum();
								if (noOfColumns == fileFormat.size()) {
									for (int i = 0; i < fileFormat.size(); i++) {
										//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
										//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow2.getCell(i).getStringCellValue().trim();
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

							int count = uploadActivities(activity, userId, userName, workbook);
							attributes.addFlashAttribute("success", count + " Activities added successfully.");
						}
					}

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

	/**
	 * This method uploadActivities() is called when user upload the file
	 * 
	 * @param obj is object for the model class User.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @return type of this method is count.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */

	public int uploadActivities(StripChart obj, String userId, String userName, XSSFWorkbook workbook)
			throws Exception {
		StripChart activity = null;
		List<StripChart> activitiesList = new ArrayList<StripChart>();

		//XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {
			/*List<String> fileFormat = null;				
			fileFormat = FileFormatModel.getActivityFileFormat();*/

			MultipartFile excelfile = obj.getStripChartFile();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile) {
				String fileName = excelfile.getOriginalFilename();
				String fileType = FilenameUtils.getExtension(fileName);

				if (excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if (workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						/*if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(1);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
							
							for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								activity = new StripChart();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									activity.setWork_id_fk(formatter.formatCellValue(row.getCell(0)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									activity.setContract_id_fk(formatter.formatCellValue(row.getCell(1)).trim());
								
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									activity.setDepartment_id_fk(formatter.formatCellValue(row.getCell(2)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									activity.setHod(formatter.formatCellValue(row.getCell(3)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									activity.setDy_hod(formatter.formatCellValue(row.getCell(4)).trim());											
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									activity.setPrepared_by_id_fk(formatter.formatCellValue(row.getCell(5)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
									activity.setConsultant_contract_id_fk(formatter.formatCellValue(row.getCell(6)).trim());										
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(7)).trim()))
									activity.setProof_consultant_contract_id_fk(formatter.formatCellValue(row.getCell(7)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(8)).trim()))
									activity.setSubmited_to_proof_consultant_fk(formatter.formatCellValue(row.getCell(8)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(9)).trim()))
									activity.setApproval_by_proof_consultant_fk(formatter.formatCellValue(row.getCell(9)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(10)).trim()))
									activity.setStructure_type_fk(formatter.formatCellValue(row.getCell(10)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(11)).trim()))
									activity.setComponent(formatter.formatCellValue(row.getCell(11)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(12)).trim()))
									activity.setDrawing_type_fk(formatter.formatCellValue(row.getCell(12)).trim());
								
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(13)).trim()))
									activity.setContractor_drawing_no(formatter.formatCellValue(row.getCell(13)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(14)).trim()))
									activity.setMrvc_drawing_no(formatter.formatCellValue(row.getCell(14)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(15)).trim()))
									activity.setDivision_drawing_no(formatter.formatCellValue(row.getCell(15)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(16)).trim()))
									activity.setHq_drawing_no(formatter.formatCellValue(row.getCell(16)).trim());											
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(17)).trim()))
									activity.setDrawing_title(formatter.formatCellValue(row.getCell(17)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(18)).trim()))
									activity.setPlanned_start(formatter.formatCellValue(row.getCell(18)).trim());										
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(19)).trim()))
									activity.setPlanned_finish(formatter.formatCellValue(row.getCell(19)).trim());
								
								
								 * if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(20)).trim()))
								 * activity.setRevision(formatter.formatCellValue(row.getCell(20)).trim());
								 
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(20)).trim()))
									activity.setConsultant_submission(formatter.formatCellValue(row.getCell(20)).trim());
								
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(21)).trim()))
									activity.setMrvc_reviewed(formatter.formatCellValue(row.getCell(21)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(22)).trim()))
									activity.setDivisional_submission_fk(formatter.formatCellValue(row.getCell(22)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(23)).trim()))
									activity.setDivisional_approval(formatter.formatCellValue(row.getCell(23)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(24)).trim()))
									activity.setHq_submission_fk(formatter.formatCellValue(row.getCell(24)).trim());										
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(25)).trim()))
									activity.setHq_approval(formatter.formatCellValue(row.getCell(25)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(26)).trim()))
									activity.setGfc_released(formatter.formatCellValue(row.getCell(26)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(27)).trim()))
									activity.setAs_built_status(formatter.formatCellValue(row.getCell(27)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(28)).trim()))
									activity.setAs_built_date(formatter.formatCellValue(row.getCell(28)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(29)).trim()))
									activity.setRemarks(formatter.formatCellValue(row.getCell(29)).trim());
								
								activity.setPlanned_start(DateParser.parse(activity.getPlanned_start()));
								activity.setPlanned_finish(DateParser.parse(activity.getPlanned_finish()));
								activity.setConsultant_submission(DateParser.parse(activity.getConsultant_submission()));
								activity.setMrvc_reviewed(DateParser.parse(activity.getMrvc_reviewed()));
								activity.setDivisional_approval(DateParser.parse(activity.getDivisional_approval()));
								activity.setHq_approval(DateParser.parse(activity.getHq_approval()));
								activity.setGfc_released(DateParser.parse(activity.getGfc_released()));
								activity.setAs_built_date(DateParser.parse(activity.getAs_built_date()));
						
								List<StripChart> pObjList = new ArrayList<StripChart>();
								if(!StringUtils.isEmpty(activity.getMrvc_drawing_no())) {
									XSSFSheet uploadFilesSheet2 = workbook.getSheetAt(2);
									for(int j = 2; j<= uploadFilesSheet2.getLastRowNum();j++){
										XSSFRow row2 = uploadFilesSheet2.getRow(j);
										// Sets the Read data to the model class
										DataFormatter formatter2 = new DataFormatter(); //creating formatter using the default locale
										
										StripChart pObj = new StripChart();
										
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(0)).trim()))
											pObj.setMrvc_drawing_no(formatter2.formatCellValue(row2.getCell(0)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(1)).trim()))
											pObj.setRevision(formatter2.formatCellValue(row2.getCell(1)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(2)).trim()))
											pObj.setConsultant_submission(formatter2.formatCellValue(row2.getCell(2)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(3)).trim()))
											pObj.setMrvc_reviewed(formatter2.formatCellValue(row2.getCell(3)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(4)).trim()))
											pObj.setDivisional_approval(formatter2.formatCellValue(row2.getCell(4)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(5)).trim()))
											pObj.setHq_approval(formatter2.formatCellValue(row2.getCell(5)).trim());	
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(6)).trim()))
											pObj.setRevision_status_fk(formatter2.formatCellValue(row2.getCell(6)).trim());	
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(7)).trim()))
											pObj.setRemarks(formatter2.formatCellValue(row2.getCell(7)).trim());	
										
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(0)).trim()))
											pObj.setMrvc_drawing_no(formatter2.formatCellValue(row2.getCell(0)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(1)).trim()))
											pObj.setRevision(formatter2.formatCellValue(row2.getCell(1)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(2)).trim()))
											pObj.setRevision_date(formatter2.formatCellValue(row2.getCell(2)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(3)).trim()))
											pObj.setRevision_status_fk(formatter2.formatCellValue(row2.getCell(3)).trim());
										if(!StringUtils.isEmpty(formatter2.formatCellValue(row2.getCell(4)).trim()))
											pObj.setRemarks(formatter2.formatCellValue(row2.getCell(4)).trim());
										
										pObj.setRevision_date(DateParser.parse(pObj.getRevision_date()));
										pObj.setConsultant_submission(DateParser.parse(pObj.getConsultant_submission()));
										pObj.setMrvc_reviewed(DateParser.parse(pObj.getMrvc_reviewed()));
										pObj.setDivisional_approval(DateParser.parse(pObj.getDivisional_approval()));
										pObj.setHq_approval(DateParser.parse(pObj.getHq_approval()));
										
										if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getMrvc_drawing_no())
												&& pObj.getMrvc_drawing_no().equals(activity.getMrvc_drawing_no()))
										pObjList.add(pObj);
									}
									activity.setStripChartRevisions(pObjList);
								}
								
								
								if(!StringUtils.isEmpty(activity) && !StringUtils.isEmpty(activity.getContract_id_fk()) && !StringUtils.isEmpty(activity.getDepartment_id_fk())) {
									activitiesList.add(activity);
								}
							}
							
							if(!activitiesList.isEmpty() && activitiesList != null){
								count  = service.uploadActivities(activitiesList);
							}
						}*/
						workbook.close();
					}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadActivities() : " + e.getMessage());
			throw new Exception(e);
		} finally {
			try {
				if (w != null)
					w.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("uploadActivities() : " + e.getMessage());
				throw new Exception(e);
			}
		}

		return count;
	}

}
