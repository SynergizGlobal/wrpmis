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

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import com.synergizglobal.pmis.model.ActivitiesPaginationObject;
import com.synergizglobal.pmis.model.Activity;
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
	public ModelAndView activitiesUpload(@ModelAttribute Activity obj, HttpSession session) {
		ModelAndView model = new ModelAndView(PageConstants2.progressUploadGrid);
		try {

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("activitiesUpload : " + e.getMessage());
		}
		return model;
	}
	
	
	/***************************************************************/
	
	@RequestMapping(value = "/ajax/getWorksInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getWorksInActivitiesUpload(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getWorksInActivitiesUpload(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getContractsInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getContractsInActivitiesUpload(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getContractsInActivitiesUpload(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getStructureTypesInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getStructureTypesInActivitiesUpload(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getStructureTypesInActivitiesUpload(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureTypesInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}
	
	/***************************************************************/

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

			ActivitiesPaginationObject personJsonObject = new ActivitiesPaginationObject();
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
	public ModelAndView uploadActivities(@ModelAttribute Activity activity, RedirectAttributes attributes,
			HttpSession session) {
		ModelAndView model = new ModelAndView();
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/activities-upload");

			if (!StringUtils.isEmpty(activity.getUploadFile())) {
				MultipartFile multipartFile = activity.getUploadFile();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0) {
					
					try (XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream())) {					
						// Creates a worksheet object representing the first sheet
						int sheetsCount = workbook.getNumberOfSheets();
						if (sheetsCount > 0) {
							XSSFSheet referenceDataSheet = workbook.getSheetAt(1);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = referenceDataSheet.getRow(1);
							//checking given file format
							if (headerRow != null) {
								List<String> fileFormat = FileFormatModel.getActivityRefetenceData_FileFormat();
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
							
							
							/********************************************************************************************************************/
							XSSFSheet stripChartSheet = workbook.getSheetAt(2);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							headerRow = stripChartSheet.getRow(1);
							//checking given file format
							if (headerRow != null) {
								List<String> fileFormat = FileFormatModel.getActivityData_FileFormat();
								
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
							/********************************************************************************************************************/
							/*int[] counts = uploadActivities(activity, userId, userName, workbook);
							attributes.addFlashAttribute("success", counts[0] + " activities added and "+counts[1]+" activities updated successfully.");*/
							String message = uploadActivities(activity, userId, userName, workbook);
							attributes.addFlashAttribute("success", message);
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

	public String uploadActivities(Activity obj, String userId, String userName, XSSFWorkbook workbook)
			throws Exception {
		Writer w = null;
		int[] counts = null;
		String message = "";
		try {
			MultipartFile excelfile = obj.getUploadFile();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile) {
				if (excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if (workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							XSSFSheet referenceDataSheet = workbook.getSheetAt(1);
							
							DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
							
							List<Activity> componentIdList = new ArrayList<Activity>();
							
							String component_id = null,order = null;
									
							for(int i = 2; i<= referenceDataSheet.getLastRowNum();i++){
								XSSFRow row = referenceDataSheet.getRow(i);	
								if(!StringUtils.isEmpty(row)) {
									
									String componentId_temp = null;
									Cell cell = row.getCell(0);
									if(!StringUtils.isEmpty(cell)) {
										componentId_temp = formatter.formatCellValue(cell).trim();
									}
									
									if(!StringUtils.isEmpty(componentId_temp) && !componentId_temp.equals("null")) {									
										Activity componentIdObj = new Activity();
										
										String tempVal = formatter.formatCellValue(row.getCell(0)).trim();
										int count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											component_id = getCellDataType(workbook,row.getCell(0));
										}	
										if(!StringUtils.isEmpty(component_id)) { componentIdObj.setComponent_id(component_id);}
										
										tempVal = formatter.formatCellValue(row.getCell(1)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											order = getCellDataType(workbook,row.getCell(1));
										}	
										if(!StringUtils.isEmpty(order)) { componentIdObj.setOrder(order);}
										
										componentIdList.add(componentIdObj);
									}
								}
							}

							
							XSSFSheet activityDataSheet = workbook.getSheetAt(2);
							
							List<Activity> activityList = new ArrayList<Activity>();
							
							String activity_id = null,contract_id_fk = null,struture_type_fk = null,section = null,line = null,structure = null,component = null,activity_name = null,planned_start = null,planned_finish = null,actual_start = null,actual_finish = null,unit = null,scope = null,completed = null,weightage = null,component_details = null,remarks = null;
							String zero_total_scope = "",zero_completed_scope = "",null_actual_start_date = "",completedScope_gt_total_scope = "";
							for(int j = 2; j<= activityDataSheet.getLastRowNum();j++){
								XSSFRow row = activityDataSheet.getRow(j);
								if(!StringUtils.isEmpty(row)) {
									String componentId_temp = null;
									Cell cell = row.getCell(4);
									if(!StringUtils.isEmpty(cell)) {
										componentId_temp = formatter.formatCellValue(cell).trim();
									}
									
									if(!StringUtils.isEmpty(componentId_temp) && !componentId_temp.equals("null")) {									
										Activity activityObj = new Activity();
										
										activityObj.setWork_id(obj.getWork_id());
										activityObj.setContract_id_fk(obj.getContract_id_fk());
										activityObj.setStructure_type_fk(obj.getStructure_type_fk());
										activityObj.setCreated_by_user_id_fk(userId);
										activityObj.setModified_by_user_id_fk(userId);
										
										String tempVal = formatter.formatCellValue(row.getCell(0)).trim();
										int count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											section = getCellDataType(workbook,row.getCell(0));
										}	
										if(!StringUtils.isEmpty(section)) { activityObj.setSection(section);}
										
										tempVal = formatter.formatCellValue(row.getCell(1)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											line = getCellDataType(workbook,row.getCell(1));
										}	
										if(!StringUtils.isEmpty(line)) { activityObj.setLine(line);}
										
										tempVal = formatter.formatCellValue(row.getCell(2)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											structure = getCellDataType(workbook,row.getCell(2));
										}	
										if(!StringUtils.isEmpty(structure)) { activityObj.setStructure(structure);}
										
										tempVal = formatter.formatCellValue(row.getCell(3)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											component = getCellDataType(workbook,row.getCell(3));
										}	
										if(!StringUtils.isEmpty(component)) { activityObj.setComponent(component);}
										
										tempVal = formatter.formatCellValue(row.getCell(4)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											component_id = getCellDataType(workbook,row.getCell(4));
										}	
										if(!StringUtils.isEmpty(component_id)) { activityObj.setComponent_id(component_id);}
										
										for (Activity cObj : componentIdList) {
											if(cObj.getComponent_id().equals(activityObj.getComponent_id())) {
												activityObj.setOrder(cObj.getOrder());
											}
										}
										
										tempVal = formatter.formatCellValue(row.getCell(5)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											activity_name = getCellDataType(workbook,row.getCell(5));
										}	
										if(!StringUtils.isEmpty(activity_name)) { activityObj.setActivity_name(activity_name);}
										
										tempVal = formatter.formatCellValue(row.getCell(6)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											planned_start = getCellDataType(workbook,row.getCell(6));
										}	
										if(!StringUtils.isEmpty(planned_start)) { activityObj.setPlanned_start(DateParser.parse(planned_start));}
										
										tempVal = formatter.formatCellValue(row.getCell(7)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											planned_finish = getCellDataType(workbook,row.getCell(7));
										}	
										if(!StringUtils.isEmpty(planned_finish)) { activityObj.setPlanned_finish(DateParser.parse(planned_finish));}
										
										tempVal = formatter.formatCellValue(row.getCell(8)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											actual_start = getCellDataType(workbook,row.getCell(8));
										}	
										if(!StringUtils.isEmpty(actual_start)) { activityObj.setActual_start(DateParser.parse(actual_start));}
										
										tempVal = formatter.formatCellValue(row.getCell(9)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											actual_finish = getCellDataType(workbook,row.getCell(9));
										}	
										if(!StringUtils.isEmpty(actual_finish)) { activityObj.setActual_finish(DateParser.parse(actual_finish));}
										
										tempVal = formatter.formatCellValue(row.getCell(10)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											unit = getCellDataType(workbook,row.getCell(10));
										}	
										if(!StringUtils.isEmpty(unit)) { activityObj.setUnit(unit);}
										
										tempVal = formatter.formatCellValue(row.getCell(11)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											scope = getCellDataType(workbook,row.getCell(11));
										}	
										if(!StringUtils.isEmpty(scope)) { activityObj.setScope(scope);}
										
										tempVal = formatter.formatCellValue(row.getCell(12)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											completed = getCellDataType(workbook,row.getCell(12));
										}	
										if(!StringUtils.isEmpty(completed)) { activityObj.setCompleted(completed);}
										
										tempVal = formatter.formatCellValue(row.getCell(13)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											weightage = getCellDataType(workbook,row.getCell(13));
										}	
										if(!StringUtils.isEmpty(weightage)) { activityObj.setWeightage(weightage);}
										
										tempVal = formatter.formatCellValue(row.getCell(14)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											component_details = getCellDataType(workbook,row.getCell(14));
										}	
										if(!StringUtils.isEmpty(component_details)) { activityObj.setComponent_details(component_details);}
										
										tempVal = formatter.formatCellValue(row.getCell(15)).trim();
										count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
										if(count != 2) {
											remarks = getCellDataType(workbook,row.getCell(15));
										}	
										if(!StringUtils.isEmpty(remarks)) { activityObj.setRemarks(remarks);}
										
										
										double totalScope = 0;
										double completedScope = 0;
										if(!StringUtils.isEmpty(activityObj.getScope())) {
											totalScope = Double.parseDouble(activityObj.getScope());
										}
										if(!StringUtils.isEmpty(activityObj.getCompleted())) {
											completedScope = Double.parseDouble(activityObj.getCompleted());
										}
										
										if(completedScope <= totalScope) {
											activityList.add(activityObj);
										}
										
										if (completedScope > totalScope) {
											completedScope_gt_total_scope = completedScope_gt_total_scope + (!StringUtils.isEmpty(completedScope_gt_total_scope)?",":"") + (j+1);
										}
										
										/*String actual_start_date = activityObj.getActual_start();
										if(totalScope != 0 && completedScope != 0 && completedScope <= totalScope && !StringUtils.isEmpty(actual_start_date) ) {
											activityList.add(activityObj);
										}
										if(totalScope == 0) {
											zero_total_scope = zero_total_scope + (!StringUtils.isEmpty(zero_total_scope)?",":"") + (j+1);
										} else if (completedScope == 0) {
											zero_completed_scope = zero_completed_scope + (!StringUtils.isEmpty(zero_completed_scope)?",":"") + (j+1);
										} else if (completedScope > totalScope) {
											completedScope_gt_total_scope = completedScope_gt_total_scope + (!StringUtils.isEmpty(completedScope_gt_total_scope)?",":"") + (j+1);
										} else if (StringUtils.isEmpty(actual_start_date)){
											null_actual_start_date = null_actual_start_date + (!StringUtils.isEmpty(null_actual_start_date)?",":"") + (j+1);
										}*/
										
									}
								}
							}
							
							if(!StringUtils.isEmpty(activityList) && activityList.size() > 0){
								counts  = service.uploadActivities(activityList);
								if(counts[0] > 0) {
									message = message + "<p style='color:green;'>" + counts[0] + " activities added successfully.</p>";
								}
								if(counts[1] > 0) {
									message = message + "<p style='color:green;'>" + counts[1] + " activities updated successfully.</p>";
								}
								
								if(!StringUtils.isEmpty(completedScope_gt_total_scope)) {
									message = message + "<p style='color:red;'> Row no(s) " + completedScope_gt_total_scope + " are not inserted (Reason : Completed > Total Scope).</p>";
								}
								
								/*if(!StringUtils.isEmpty(zero_total_scope)) {
									message = message + "<p style='color:red;'>" + zero_total_scope + " rows are not inserted (Reason : Total Scope is 0 or empty).</p>";
								}
								if(!StringUtils.isEmpty(zero_completed_scope)) {
									message = message + "<p style='color:red;'>" + zero_completed_scope + " rows are not inserted (Reason : Completed is 0 or empty).</p>";
								}
								if(!StringUtils.isEmpty(completedScope_gt_total_scope)) {
									message = message + "<p style='color:red;'>" + completedScope_gt_total_scope + " rows are not inserted (Reason : Completed > Total Scope).</p>";
								}
								if(!StringUtils.isEmpty(null_actual_start_date)) {
									message = message + "<p style='color:red;'>" + null_actual_start_date + " rows are not inserted (Reason : Actual Start Date is empty).</p>";
								}*/
								
								message = message + "<br><br>";
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

		return message;
	}
	
	private String getCellDataType(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup

		if (!StringUtils.isEmpty(cell) && cell.getCellType() == CellType.FORMULA) {
		    switch (evaluator.evaluateFormulaCell(cell)) {
		        case BOOLEAN:
		            val = String.valueOf(cell.getBooleanCellValue());
		            break;
		        case NUMERIC:
		        	val = String.valueOf(cell.getNumericCellValue());
		            break;
		        case STRING:
		            val = cell.getStringCellValue();
		            break;
		        case BLANK:
		        	val = cell.getStringCellValue();
		            break;
		        case ERROR:
		            val = cell.getStringCellValue();
		            break;
		        case _NONE:
		            val = cell.getStringCellValue();
		            break;
				default:
					break;
		    }
		}else if (!StringUtils.isEmpty(cell)) {
			DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
			val = formatter.formatCellValue(cell).trim();
		}
		return val;
	}

}
