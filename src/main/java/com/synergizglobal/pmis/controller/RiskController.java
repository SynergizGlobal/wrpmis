package com.synergizglobal.pmis.controller;

import java.io.File;
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

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.RandomGenerator;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.service.RiskService;

@Controller
public class RiskController {
	Logger logger = Logger.getLogger(RiskController.class);
	@Autowired
	RiskService service;
	
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${input.sheet.task.update}")
	public String taskUpdate;
	
	@Value("${input.sheet.task.complete}")
	public String taskComplete;
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@Value("${record.dataexport.success}")
	public String dataExportSucess;
	
	@Value("${record.dataexport.invalid.directory}")
	public String dataExportInvalid;
	
	@Value("${record.dataexport.error}")
	public String dataExportError;
	
	@Value("${record.dataexport.nodata}")
	public String dataExportNoData;
	
	
	
	/**
	 * This method risk() will execute when user request the for Risk module.
	 * 
	 * @param obj is object for the model class Risk. 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/risk",method=RequestMethod.GET)
	public ModelAndView risk(@ModelAttribute Risk obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.risk);
		try {
			List<Risk> riskList = service.getRiskList(obj);
			model.addObject("riskList", riskList);			
			
			
			List<Risk> areaList = service.getRiskAreaListForSearch();
			model.addObject("areaList", areaList);
			
			List<Risk> subAreaList = service.getRiskSubAreaListForSearch();
			model.addObject("subAreaList", subAreaList);
			
			List<Risk> categoryList = service.getRiskCategoryListForSearch();
			model.addObject("categoryList", categoryList);
			
			List<Risk> impactList = service.getRiskImpactListForSearch();
			model.addObject("impactList", impactList);
			
			List<Risk> probabilityList = service.getRiskProbabilityListForSearch();
			model.addObject("probabilityList", probabilityList);
			
			List<Risk> statusList = service.getRiskSatausListForSearch();
			model.addObject("statusList", statusList);
			
			List<Risk> classificationList = service.getRiskClassificationListForSearch();
			model.addObject("classificationList", classificationList);
			
			List<Risk> priorityList = service.getRiskPriorityListForSearch();
			model.addObject("priorityList", priorityList);
			
			List<Risk> responsiblePersonsList = service.getResponsiblePersonsListForSearch();
			model.addObject("responsiblePersonsList", responsiblePersonsList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("risk : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getRisks() will call the ajax request.
	 * 
	 * @param obj is object for the model class Risk. 
	 * @return type of this method is risks
	 */
			
	@RequestMapping(value = "/ajax/getRisks", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRisks(@ModelAttribute Risk obj){
		List<Risk> risks = null;
		try{
			risks = service.getRiskList(obj);			
		}catch(Exception e){
			logger.error("getRisks() : "+e.getMessage());
		}
		return risks;
	}
	
	/**
	 * This method getRisk() when user click on the edit button
	 * 
	 * @param riskId it takes the risk work id as input parameter.
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/get-risk",method=RequestMethod.POST)
	public ModelAndView getRisk(@RequestParam String riskId, HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.updateRisk);
		try {
			model.addObject("homeHeader", "yes");
			
			Risk risk = service.getRisk(riskId);
			model.addObject("risk", risk);
			
			List<Risk> projectsList = service.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Risk> worksList = service.getWorksList(null);
			model.addObject("worksList", worksList);
			
			List<Risk> riskAreaList = service.getRiskAreaList();
			model.addObject("riskAreaList", riskAreaList);
			
			List<Risk> riskSubAreaList = service.getRiskSubAreaList(null);
			model.addObject("riskSubAreaList", riskSubAreaList);
			
			List<Risk> riskCategoryList = service.getRiskCategoryList();
			model.addObject("riskCategoryList", riskCategoryList);
			
			List<Risk> riskImpactList = service.getRiskImpactList();
			model.addObject("riskImpactList", riskImpactList);
			
			List<Risk> riskProbabilityList = service.getRiskProbabilityList();
			model.addObject("riskProbabilityList", riskProbabilityList);
			
			List<Risk> riskStatusList = service.getRiskSatausList();
			model.addObject("riskStatusList", riskStatusList);
			
		} catch (Exception e) {
			logger.info("getRisk : " + e.getMessage());
		}
		
		
		return model;
	}
	
	/**
	 * This method getRisk() will call the ajax request.
	 * 
	 * @param riskId it takes the risk work id as input parameter. 
	 * @return of this method is obj.
	 */
	@RequestMapping(value = "/ajax/getRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Risk getRisk(@RequestParam String riskId){
		Risk obj = null;
		try{
			obj = service.getRisk(riskId);			
		}catch(Exception e){
			logger.error("getRisk() : "+e.getMessage());
		}
		return obj;
	}	
	
	
	/**
	 * This method getWorksList() call the ajax request
	 * 
	 * @param obj is object for the model class Risk. 
	 * @return of this method is works
	 */
	@RequestMapping(value = "/ajax/getWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getWorksList(@ModelAttribute Risk obj){
		List<Risk> works = null;
		try{
			works = service.getWorksList(obj);			
		}catch(Exception e){
			logger.error("getWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	/**
	 * This method getRiskSubAreaList() will call the ajax request.
	 * 
	 * @param obj is object for the model class Risk. 
	 * @return of this method is subAreas
	 */
	@RequestMapping(value = "/ajax/getRiskSubAreaList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskSubAreaList(@ModelAttribute Risk obj){
		List<Risk> subAreas = null;
		try{
			subAreas = service.getRiskSubAreaList(obj);			
		}catch(Exception e){
			logger.error("getRiskSubAreaList() : "+e.getMessage());
		}
		return subAreas;
	}
	
	/**
	 * This method updateRisk() will execute when user update Risk form and it will display the success and error message.
	 * 
	 * @param obj is object for the model class Risk.
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/update-risk",method=RequestMethod.POST)
	public ModelAndView updateRisk(@ModelAttribute Risk obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/risk");
		try {
			
			String riskId = null;
			if(StringUtils.isEmpty(org.apache.commons.lang.StringUtils.trimToNull(obj.getRiskId()))) {
				riskId = obj.getWorkId() + "-" + RandomGenerator.generateNumericRandom(CommonConstants.RANDOM_NUMERIC_NUMBER_LENGTH);
			} else {
				riskId = org.apache.commons.lang.StringUtils.trimToNull(obj.getRiskId());
			}
			
			obj.setRiskId(riskId);
			
			MultipartFile file = obj.getRiskAnalysisReportFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.RISK_ANALYSIS_REPORTS_SAVING_PATH + File.separator + riskId + File.separator;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				String filePath = CommonConstants.RISK_ANALYSIS_REPORTS_GET_PATH + riskId + "/" + fileName;
				obj.setRiskAnalysisReportLink(filePath);
			}
			
			boolean flag = service.updateRisk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateRisk : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateRiskStatus() will update the risk status
	 *  
	 * @param obj is object for the model class Risk.
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/update-risk-status",method=RequestMethod.POST)
	public ModelAndView updateRiskStatus(@ModelAttribute Risk obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/risk");
		try {
			boolean flag = service.updateRiskStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk has been closed successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateRiskStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	
	/**
	 * This method updateDataDate() will call when user update Data using UpdateDate button
	 * 
	 * @param risk is object for the model class Risk.
	 * @param attributes will show the flash message on the current request.
	 * @param session it will check the session of the user.
	 * @return of this method is model.
	 */
	
	@RequestMapping(value = "/upload-risks", method = {RequestMethod.POST})
	public ModelAndView updateDataDate(@ModelAttribute Risk risk,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String fileName = null;
		String userId = null;String userName = null;
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/risk");
			
			risk.setCreatedBy(userName);
			
			MultipartFile multipartFile = risk.getRisksBulkUploadTemplate();
			// Creates a workbook object from the uploaded excelfile
			if (null != multipartFile && multipartFile.getSize() > 0){					
				workbook = new XSSFWorkbook(multipartFile.getInputStream());
				// Creates a worksheet object representing the first sheet
				if(workbook != null && !"".equals(workbook)) {
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						//uploadFilesSheet = workbook.getSheetAt(0);
						uploadFilesSheet = workbook.getSheetAt(1);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = uploadFilesSheet.getRow(1);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getRisksUploadTemplateFormat();;	
							int noOfColumns = headerRow.getLastCellNum();
							if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
					}
				}
				
				fileName = multipartFile.getOriginalFilename();
                if (!StringUtils.isEmpty(fileName)) {
	                risk.setRisksBulkUploadFileName(fileName);
                }
				
			}            
            
			if(!StringUtils.isEmpty(fileName)) {
				/*String saveDirectory = CommonConstants.P6_DATA_FILES;	                
				saveDirectory = saveDirectory+activityDataId;
				AmazonS3Storage.saveFileInS3Bucket(saveDirectory,multipartFile);*/		
				String saveDirectory = CommonConstants.RISKS_BULK_UPLOAD_FILES_SAVING_PATH+userId+File.separator;
				FileUploads.singleFileSaving(multipartFile,saveDirectory,fileName);
	            try {
	            	int count = uploadRisks(risk,userId,userName,workbook);
					if(count > 0) {
						attributes.addFlashAttribute("message",  count + " risk(s) uploaded successfully.");			            
					}
				} catch (Exception e) {
					if(e.getMessage().contains("DD/MM/YYYY")) {
						attributes.addFlashAttribute("dateFormatError", e.getMessage());
					}else {
						attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
					}
				}
				
			} else {
				 attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method uploadRisks() is called when user upload the file
	 * 
	 * @param obj is object for the model class Risk.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @return type of this method is count.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	public int uploadRisks(Risk obj, String userId, String userName, XSSFWorkbook workbook) throws Exception {
		logger.info("RiskController  >> uploadRisks() >> start");
		Risk risk = null;
		List<Risk> risksList = new ArrayList<Risk>();		
		//XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getRisksBulkUploadTemplate();
			// Creates a workbook object from the uploaded excelfile
			if (null != excelfile){
				 String fileName = excelfile.getOriginalFilename();
				 String fileType = FilenameUtils.getExtension(fileName);
				 
				 if(excelfile.getSize() > 0)
					//workbook = new XSSFWorkbook(excelfile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							//uploadFilesSheet = workbook.getSheetAt(0);
							uploadFilesSheet = workbook.getSheetAt(1);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);
							
							//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
							//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							
							//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
							
							SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
							
							for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								risk = new Risk();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									risk.setWorkId(formatter.formatCellValue(row.getCell(0)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									risk.setRiskId(formatter.formatCellValue(row.getCell(1)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									risk.setRiskOwner(formatter.formatCellValue(row.getCell(2)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									risk.setRiskDescription(formatter.formatCellValue(row.getCell(3)).trim());											
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									risk.setRiskArea(formatter.formatCellValue(row.getCell(4)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									risk.setRiskSubArea(formatter.formatCellValue(row.getCell(5)).trim());										
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
									risk.setRiskProbability(formatter.formatCellValue(row.getCell(6)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(7)).trim()))
									risk.setRiskImpact(formatter.formatCellValue(row.getCell(7)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(8)).trim()))
									risk.setRiskCategory(formatter.formatCellValue(row.getCell(8)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(9)).trim()))
									risk.setRiskPriorityNumber(formatter.formatCellValue(row.getCell(9)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(10)).trim()))
									risk.setDateOfIdentification(formatter.formatCellValue(row.getCell(10)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(11)).trim()))
									risk.setResponsiblePersonName(formatter.formatCellValue(row.getCell(11)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(12)).trim()))
									risk.setRiskMitigationPlan(formatter.formatCellValue(row.getCell(12)).trim());											
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(13)).trim()))
									risk.setRemarks(formatter.formatCellValue(row.getCell(13)).trim());								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(14)).trim()))
									risk.setTargetDateForMitigation(formatter.formatCellValue(row.getCell(14)).trim());										
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(15)).trim()))
									risk.setRiskMitigatedOn(formatter.formatCellValue(row.getCell(15)).trim());	
								
								try {
									if(!StringUtils.isEmpty(risk.getDateOfIdentification())){
										Date convertedDate = sdf.parse(risk.getDateOfIdentification());
										String currentDate = sqlDate.format(convertedDate);
										risk.setDateOfIdentification(currentDate);
									}
									
									if(!StringUtils.isEmpty(risk.getTargetDateForMitigation())){
										Date convertedDate = sdf.parse(risk.getTargetDateForMitigation());
										String currentDate = sqlDate.format(convertedDate);
										risk.setTargetDateForMitigation(currentDate);
									}
									
									if(!StringUtils.isEmpty(risk.getRiskMitigatedOn())){
										Date convertedDate = sdf.parse(risk.getRiskMitigatedOn());
										String currentDate = sqlDate.format(convertedDate);
										risk.setRiskMitigatedOn(currentDate);
									}
								} catch (Exception e) {
									logger.error("uploadRisks() : "+e.getMessage());
									throw new Exception("All dates should be in DD/MM/YYYY - Ex. 14/02/2020.");	
								}
								
								
								if(!StringUtils.isEmpty(risk) && !StringUtils.isEmpty(risk.getWorkId())) {
									risk.setCreatedBy(userName);
									risksList.add(risk);
								}
							}
							
							if(!risksList.isEmpty() && risksList != null){
								count  = service.uploadRisks(risksList);
							}
						}
						workbook.close();
					}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadRisks() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRisks() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
	
	/*@RequestMapping("/getRiskUploadFileTemplate/{fileName:.+}")
	public void getRiskUploadFileTemplate(HttpServletResponse response,@PathVariable("fileName") String fileName) {
		  try {
			  String filePath = CommonConstants.RISKS_BULK_UPLOAD_TEMPLATE+fileName;
			  File initialFile = new File(filePath);
			  InputStream objectContent = new FileInputStream(initialFile);
			    
			  response.setContentType("application/octet-stream");
			  // add response header
			  response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			  //copies all bytes from a file to an output stream
			  IOUtils.copy(objectContent, response.getOutputStream());
			  //flushes output stream
			  response.getOutputStream().flush();
			  response.flushBuffer();
		  } catch (Exception e) {
			  e.printStackTrace();
			  logger.error("getRiskUploadFileTemplate() : "+e.getMessage());
		  }
	 }*/
	
	/**
	 * This method riskAddForm() will execute when user click on the Add Risk button.
	 * 
	 * @param session it will check the session of the user.
	 * @return of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/add-risk-form",method=RequestMethod.GET)
	public ModelAndView riskAddForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditRisk);
		try {
			List<Risk> projectsList = service.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Risk> worksList = service.getWorksList(null);
			model.addObject("worksList", worksList);
			
			List<Risk> riskAreaList = service.getRiskAreaList();
			model.addObject("riskAreaList", riskAreaList);
			
			List<Risk> riskSubAreaList = service.getRiskSubAreaList(null);
			model.addObject("riskSubAreaList", riskSubAreaList);
			
			List<Risk> riskCategoryList = service.getRiskCategoryList();
			model.addObject("riskCategoryList", riskCategoryList);
			
			List<Risk> riskImpactList = service.getRiskImpactList();
			model.addObject("riskImpactList", riskImpactList);
			
			List<Risk> riskProbabilityList = service.getRiskProbabilityList();
			model.addObject("riskProbabilityList", riskProbabilityList);
			
			List<Risk> riskStatusList = service.getRiskSatausList();
			model.addObject("riskStatusList", riskStatusList);
		} catch (Exception e) {
			logger.info("riskAddForm : " + e.getMessage());
		}
		return model;
	}
	
	
	/**
	 * This method addRisk() is executed when submit the Add Risk form and it will display the success and error message.
	 * 
	 * @param obj is object for the model class Risk.
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/add-risk",method=RequestMethod.POST)
	public ModelAndView addRisk(@ModelAttribute Risk obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/risk");
		try {
			String riskId = null;
			if(StringUtils.isEmpty(org.apache.commons.lang.StringUtils.trimToNull(obj.getRiskId()))) {
				riskId = obj.getWorkId() + "-" + RandomGenerator.generateNumericRandom(CommonConstants.RANDOM_NUMERIC_NUMBER_LENGTH);
			} else {
				riskId = org.apache.commons.lang.StringUtils.trimToNull(obj.getRiskId());
			}
			
			obj.setRiskId(riskId);
			
			boolean flag = service.addRisk(obj);
			if(flag) {
				
				MultipartFile file = obj.getRiskAnalysisReportFile();
				if (null != file && !file.isEmpty()){
					String saveDirectory = CommonConstants.RISK_ANALYSIS_REPORTS_SAVING_PATH + File.separator + riskId + File.separator;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					String filePath = CommonConstants.RISK_ANALYSIS_REPORTS_GET_PATH + riskId + "/" + fileName;
					obj.setRiskAnalysisReportLink(filePath);
				}
				
				attributes.addFlashAttribute("success", "Risk added successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("addRisk : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method exportRisks() will execute when user click on the Export Data Button. It will generate the excel sheet.
	 * 
	 * @param request it receives the request from the server with header information.
	 * @param response it process the response.
	 * @param session it will check the session of the user.
	 * @param risk is object for the model class Risk.
	 * @param attributes will show the flash message on the current request.
	 */
	
	@RequestMapping(value = "/export-risks", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportRisks(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Risk risk,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.risk);
		List<Risk> dataList = new ArrayList<Risk>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/risk");
			dataList = service.getRiskList(risk);  
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet();
			            XSSFRow headingRow = sheet.createRow(0);
			            headingRow.createCell((short)0).setCellValue("WORK ID");
			            headingRow.createCell((short)1).setCellValue("RISK ID");
			            headingRow.createCell((short)2).setCellValue("OWNER");
			            headingRow.createCell((short)3).setCellValue("DESCRIPTION");
			            headingRow.createCell((short)4).setCellValue("AREA");
			            headingRow.createCell((short)5).setCellValue("SUB AREA");
			            headingRow.createCell((short)6).setCellValue("PROBABILITY");
			            headingRow.createCell((short)7).setCellValue("IMPACT");
			            headingRow.createCell((short)8).setCellValue("CATEGORY");
			            headingRow.createCell((short)9).setCellValue("CLASSIFICATION");
			            headingRow.createCell((short)10).setCellValue("PRIORITY");
			            headingRow.createCell((short)11).setCellValue("DATE OF IDENTIFICATION");
			            headingRow.createCell((short)12).setCellValue("RESPONSIBLE PERSON");
			            headingRow.createCell((short)13).setCellValue("MITIGATION PLAN");
			            headingRow.createCell((short)14).setCellValue("ACTION TAKEN/REMARKS");
			            headingRow.createCell((short)15).setCellValue("DUE DATE");
			            headingRow.createCell((short)16).setCellValue("MITIGATED DATE");
			            headingRow.createCell((short)17).setCellValue("STATUS");
			            short rowNo = 1;
			            for (Risk obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                row.createCell((short)0).setCellValue(obj.getWorkId());
			                row.createCell((short)1).setCellValue(obj.getRiskId());
			                row.createCell((short)2).setCellValue(obj.getRiskOwner());
			                row.createCell((short)3).setCellValue(obj.getRiskDescription());
			                row.createCell((short)4).setCellValue(obj.getRiskArea());
			                row.createCell((short)5).setCellValue(obj.getRiskSubArea());
			                row.createCell((short)6).setCellValue(obj.getRiskProbability());
			                row.createCell((short)7).setCellValue(obj.getRiskImpact());
			                row.createCell((short)8).setCellValue(obj.getRiskCategory());
			                row.createCell((short)9).setCellValue(obj.getRiskClassificationName());
			                row.createCell((short)10).setCellValue(obj.getRiskPriorityNumber());
			                row.createCell((short)11).setCellValue(obj.getDateOfIdentification());
			                row.createCell((short)12).setCellValue(obj.getResponsiblePersonName());
			                row.createCell((short)13).setCellValue(obj.getRiskMitigationPlan());
			                row.createCell((short)14).setCellValue(obj.getRemarks());
			                row.createCell((short)15).setCellValue(obj.getTargetDateForMitigation());
			                row.createCell((short)16).setCellValue(obj.getRiskMitigatedOn());
			                row.createCell((short)17).setCellValue(obj.getRiskStatus());
			                
			                rowNo++;
			            }
		                
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Risks_"+dateFormat.format(date);
		                
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
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportRisks : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
	/**************************************************************************************************************/
	

	/**
	 * This method riskEditForm() will execute when user click on the edit button
	 * 
	 * @param session it will check the session of the user.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	
	@RequestMapping(value="/risk-edit-form",method=RequestMethod.POST)
	public ModelAndView riskEditForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditRisk);
		try {
			
		} catch (Exception e) {
			logger.info("riskEditForm : " + e.getMessage());
		}
		return model;
	}
	
	
	
}
