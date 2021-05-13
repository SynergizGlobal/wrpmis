package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.synergizglobal.pmis.Iservice.ActivitiesUploadService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.FileFormatModel;

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
	
	@RequestMapping(value = "/ajax/getFOBContractsListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getFOBContractsList(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getFOBContractsList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFOBContractsList : " + e.getMessage());
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
							String[] messages = uploadActivities(activity, userId, userName, workbook);
							attributes.addFlashAttribute("success", messages[0]);
							
							if(messages.length > 0) {
								String data_remarks = messages[1];
								activity.setWork_id_fk(activity.getWork_id());
								activity.setContract_id_fk(activity.getContract_id_fk());
								activity.setStructure_type_fk(activity.getStructure_type_fk());
								activity.setUploaded_by_user_id_fk(userId);
								boolean flag = service.addFileInActivitiesDataTable(data_remarks,activity);
							}
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

	public String[] uploadActivities(Activity obj, String userId, String userName, XSSFWorkbook workbook)
			throws Exception {
		Writer w = null;
		int[] counts = null;
		String[] messages = new String[2];
		String message = "";
		String contarct_and_fob_mismatch = null;
		String data_remarks = "";
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
							String completed_scope_gt_total_scope = "",
									planned_start_null = "",planned_finish_null = "",planned_start_gt_planned_finish = "",
									actual_start_null = "",actual_start_gt_actual_finish = "";
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
												activityObj.setOrder(cObj.getOrder().trim());
											}
										}
										if(StringUtils.isEmpty(activityObj.getOrder())) { activityObj.setOrder("9999");}
										
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
										DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
										Date actual_start_date = null;
										Date actual_finish_date = null;
										Date planned_start_date = null;
										Date planned_finish_date = null;
										
										if(!StringUtils.isEmpty(activityObj.getActual_start())) {
											actual_start_date = format.parse(activityObj.getActual_start());
										}
										if(!StringUtils.isEmpty(activityObj.getActual_finish())) {
											actual_finish_date = format.parse(activityObj.getActual_finish());
										}
										if(!StringUtils.isEmpty(activityObj.getPlanned_start())) {
											planned_start_date = format.parse(activityObj.getPlanned_start());
										}
										if(!StringUtils.isEmpty(activityObj.getPlanned_finish())) {
											planned_finish_date = format.parse(activityObj.getPlanned_finish());
										}
										
										if(completedScope <= totalScope 
												&& ((!StringUtils.isEmpty(actual_start_date) && !StringUtils.isEmpty(actual_finish_date) && (actual_start_date.compareTo(actual_finish_date) < 0 || actual_start_date.compareTo(actual_finish_date) == 0))
														|| (!StringUtils.isEmpty(actual_start_date) && StringUtils.isEmpty(actual_finish_date))
														|| (StringUtils.isEmpty(actual_start_date) && StringUtils.isEmpty(actual_finish_date))) 
												&& ((StringUtils.isEmpty(planned_start_date) && StringUtils.isEmpty(planned_finish_date)) || (!StringUtils.isEmpty(planned_start_date) && !StringUtils.isEmpty(planned_finish_date) && (planned_start_date.compareTo(planned_finish_date) < 0 || planned_start_date.compareTo(planned_finish_date) == 0)))
												) {
											activityList.add(activityObj);
										}
										
										if (completedScope > totalScope) {
											completed_scope_gt_total_scope = completed_scope_gt_total_scope + (!StringUtils.isEmpty(completed_scope_gt_total_scope)?",":"") + (j+1);
										}
										
										if(StringUtils.isEmpty(planned_start_date) && !StringUtils.isEmpty(planned_finish_date)) {
											planned_start_null = planned_start_null + (!StringUtils.isEmpty(planned_start_null)?",":"") + (j+1);
										}else if (!StringUtils.isEmpty(planned_start_date) && StringUtils.isEmpty(planned_finish_date)) {
											planned_finish_null = planned_finish_null + (!StringUtils.isEmpty(planned_finish_null)?",":"") + (j+1);
										}else if (!StringUtils.isEmpty(planned_start_date) && !StringUtils.isEmpty(planned_finish_date) && planned_start_date.compareTo(planned_finish_date) > 0) {
											//planned_start_date is after planned_finish_date (planned_start_date > planned_finish_date)
											planned_start_gt_planned_finish = planned_start_gt_planned_finish + (!StringUtils.isEmpty(planned_start_gt_planned_finish)?",":"") + (j+1);
										}
										
										if(StringUtils.isEmpty(actual_start_date) && !StringUtils.isEmpty(actual_finish_date)) {
											actual_start_null = actual_start_null + (!StringUtils.isEmpty(actual_start_null)?",":"") + (j+1);
										}else if (!StringUtils.isEmpty(actual_start_date) && !StringUtils.isEmpty(actual_finish_date) && actual_start_date.compareTo(actual_finish_date) > 0) {
											//actual_start_date is after actual_finish_date (actual_start_date > actual_finish_date)
											actual_start_gt_actual_finish = actual_start_gt_actual_finish + (!StringUtils.isEmpty(actual_start_gt_actual_finish)?",":"") + (j+1);
										}
										

										if(!StringUtils.isEmpty(obj.getContract_id_fk()) && !obj.getContract_id_fk().equals(activityObj.getContract_id_fk())) {
											contarct_and_fob_mismatch = "Contract selected from the dropdown and on the Activity File do not match. at Row no(s) " + (j+1);
											break;
										}
										
										if(!StringUtils.isEmpty(obj.getFob_id()) && !obj.getFob_id().equals(activityObj.getStructure())) {
											contarct_and_fob_mismatch = " FOB selected from the dropdown and on the Activity File do not match. at Row no(s) " + (j+1);
											break;
										}
										
									}
								}
								
							}
							
							if(!StringUtils.isEmpty(activityList) && activityList.size() > 0  && StringUtils.isEmpty(contarct_and_fob_mismatch)){
								counts  = service.uploadActivities(activityList);
								if(counts[0] > 0) {
									message = message + "<br><span style='color:green;'>" + counts[0] + " activities added successfully.</span>";
								}
								if(counts[1] > 0) {
									message = message + "<br><span style='color:green;'>" + counts[1] + " activities updated successfully.</span>";
								}
								
							}
								if(!StringUtils.isEmpty(completed_scope_gt_total_scope)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + completed_scope_gt_total_scope + " are not inserted (Reason : Completed should not greater than Total Scope).</span>";
									data_remarks = data_remarks + "Row no(s) " + completed_scope_gt_total_scope + " are not inserted (Reason : Completed should not greater than Total Scope). ";
								}
								
								if(!StringUtils.isEmpty(completed_scope_gt_total_scope)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + completed_scope_gt_total_scope + " are not inserted (Reason : Completed should not greater than Total Scope).</span>";
									data_remarks = data_remarks + "Row no(s) " + completed_scope_gt_total_scope + " are not inserted (Reason : Completed should not greater than Total Scope). ";
								}
								
								if(!StringUtils.isEmpty(planned_start_null)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + planned_start_null + " are not inserted (Reason : Planned Start should not empty).</span>";
									data_remarks = data_remarks + "Row no(s) " + planned_start_null + " are not inserted (Reason : Planned Start should not empty). ";
								}
								if(!StringUtils.isEmpty(planned_finish_null)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + planned_finish_null + " are not inserted (Reason : Planned Finish should not empty).</span>";
									data_remarks = data_remarks + "Row no(s) " + planned_finish_null + " are not inserted (Reason : Planned Finish should not empty). ";
								}
								if(!StringUtils.isEmpty(planned_start_gt_planned_finish)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + planned_start_gt_planned_finish + " are not inserted (Reason : Planned Start should not after Planned Finish).</span>";
									data_remarks = data_remarks + "Row no(s) " + planned_start_gt_planned_finish + " are not inserted (Reason : Planned Start should not after Planned Finish). ";
								}
								
								if(!StringUtils.isEmpty(actual_start_null)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + actual_start_null + " are not inserted (Reason : Actual Start should not empty).</span>";
									data_remarks = data_remarks + "Row no(s) " + actual_start_null + " are not inserted (Reason : Actual Start should not empty). ";
								}
								if(!StringUtils.isEmpty(actual_start_gt_actual_finish)) {
									message = message + "<br><span style='color:red;'> Row no(s) " + actual_start_gt_actual_finish + " are not inserted (Reason : Actual Start should not after Actual Finish).</span>";
									data_remarks = data_remarks + "Row no(s) " + actual_start_gt_actual_finish + " are not inserted (Reason : Actual Start should not after Actual Finish). ";
								}
								if(!StringUtils.isEmpty(contarct_and_fob_mismatch)) {
									message = "<br><span style='color:red;'>" + contarct_and_fob_mismatch + "</span> ";
								}
								
								message = message + "<br>";
								
								messages[0] = message;
								messages[1] = data_remarks;
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

		return messages;
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
	
	/***************************************************************/

	@RequestMapping(value = "/ajax/getWorksListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getWorksListFilter(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
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
	public List<Activity> getContractsListFilter(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilter : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getStructureTypesListFilterInActivitiesUpload", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getStructureTypesListFilter(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getStructureTypesListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureListFilter : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesUploadFilesList", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getActivitiesUploadFilesList(@ModelAttribute Activity obj) {
		List<Activity> objList = null;
		try {
			objList = service.getActivitiesUploadFilesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getActivitiesUploadFilesList : " + e.getMessage());
		}
		return objList;
	}

	

}
