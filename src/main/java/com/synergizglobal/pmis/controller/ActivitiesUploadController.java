package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.ActivityObject;
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
								List<String> fileFormat = FileFormatModel.getStripChartRefetenceData_FileFormat();
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
								List<String> fileFormat = FileFormatModel.getStripChartData_FileFormat();
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
						if(sheetsCount > 0) {
							XSSFSheet referenceDataSheet = workbook.getSheetAt(2);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);
							
							Set<String> scTypeList = new HashSet<String>(); 
							Set<String> orderList = new HashSet<String>(); 
							Set<String> latitudeList = new HashSet<String>(); 
							Set<String> longitudeList = new HashSet<String>(); 
							
							DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
							for(int i = 2; i<= referenceDataSheet.getLastRowNum();i++){
								XSSFRow row = referenceDataSheet.getRow(i);	
								
								/**********************************************************************************/
								int p = 0;
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(p)).trim())) {
									scTypeList.add(formatter.formatCellValue(row.getCell(p)).trim());
								}
								/**********************************************************************************/
								p = 13;
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(p)).trim())) {
									orderList.add(formatter.formatCellValue(row.getCell(p)).trim());
								}
								/**********************************************************************************/
								p = 15;
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(p)).trim())) {
									latitudeList.add(formatter.formatCellValue(row.getCell(p)).trim());
								}
								/**********************************************************************************/
								p = 16;
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(p)).trim())) {
									longitudeList.add(formatter.formatCellValue(row.getCell(p)).trim());
								}
								/**********************************************************************************/
							}
							
							StripChart activityObj = null;
							List<StripChart> activityList = new ArrayList<StripChart>();
							
							Set<String> contractList = new HashSet<String>();
							Set<String> componentList = new HashSet<String>(); 
							Set<String> structureList = new HashSet<String>();
							Set<String> lineList = new HashSet<String>();
							Set<String> sectionList = new HashSet<String>();
							
							XSSFSheet stripChartDataSheet = workbook.getSheetAt(3);
							for(int j = 2; j<= stripChartDataSheet.getLastRowNum();j++){
								XSSFRow stripChartRow = stripChartDataSheet.getRow(j);
								activityObj = new StripChart();
								int p = 0;
								String contract_id_fk = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(contract_id_fk)) {
									activityObj.setContract_id_fk(contract_id_fk);
									contractList.add(contract_id_fk);
								}p++;
								String strip_chart_structure = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_structure)) {
									activityObj.setStrip_chart_structure(strip_chart_structure);
									structureList.add(strip_chart_structure);
								}p++;
								String strip_chart_component = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_component)) {
									activityObj.setStrip_chart_component(strip_chart_component);
									componentList.add(strip_chart_component);
								}p++;
								String strip_chart_component_id_name = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_component_id_name)) {
									activityObj.setStrip_chart_component_id_name(strip_chart_component_id_name);									
								}p++;
								String strip_chart_activity_name = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_activity_name)) {
									activityObj.setStrip_chart_activity_name(strip_chart_activity_name);									
								}p++;
								String strip_chart_line = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_line)) {
									activityObj.setStrip_chart_line(strip_chart_line);
									lineList.add(strip_chart_line);
								}p++;
								String planned_start = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(planned_start)) {
									activityObj.setPlanned_start(planned_start);									
								}p++;
								String planned_finish = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(planned_finish)) {
									activityObj.setPlanned_finish(planned_finish);									
								}p++;
								String actual_start = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(actual_start)) {
									activityObj.setActual_start(actual_start);									
								}p++;
								String actual_finish = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(actual_finish)) {
									activityObj.setActual_finish(actual_finish);									
								}p++;
								String unit_fk = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(unit_fk)) {
									activityObj.setUnit_fk(unit_fk);									
								}p++;
								String scope = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(scope)) {
									activityObj.setScope(scope);									
								}p++;
								String completed = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(completed)) {
									activityObj.setCompleted(completed);									
								}p++;
								String weight = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(weight)) {
									activityObj.setWeight(weight);									
								}p++;
								String component_details = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(component_details)) {
									activityObj.setComponent_details(component_details);									
								}p++;
								String strip_chart_section_name = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_section_name)) {
									activityObj.setStrip_chart_section_name(strip_chart_section_name);
									sectionList.add(strip_chart_section_name);
								}p++;
								String remarks = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(remarks)) {
									activityObj.setRemarks(remarks);									
								}p++;
								String strip_chart_id = formatter.formatCellValue(stripChartRow.getCell(p)).trim();
								if(!StringUtils.isEmpty(strip_chart_id)) {
									activityObj.setStrip_chart_id(strip_chart_id);									
								}
								
								activityObj.setPlanned_start(DateParser.parse(activityObj.getPlanned_start()));
								activityObj.setPlanned_finish(DateParser.parse(activityObj.getPlanned_finish()));
								activityObj.setActual_start(DateParser.parse(activityObj.getActual_start()));
								activityObj.setActual_finish(DateParser.parse(activityObj.getActual_finish()));
								
								if(!StringUtils.isEmpty(activityObj)) {
									activityList.add(activityObj);
								}
							}
							
							if(!StringUtils.isEmpty(activityList) && activityList.size() > 0){
								count  = service.uploadActivities(contractList,componentList,structureList,lineList,sectionList,
																	scTypeList,orderList,latitudeList,longitudeList,activityList);
							}
							
						}
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
