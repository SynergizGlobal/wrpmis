package com.synergizglobal.pmis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.common.EncryptDecrypt;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.service.ActivityService;


@Controller
public class ActivityController {

	Logger logger = Logger.getLogger(ActivityController.class);
	@Autowired
	ActivityService service;
	
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
	
	/**
	 * This method activityProgress() will execute when user click on the Activity module.
	 * 
	 * @param obj is object for the model class Activity.
	 * @param session it will check the session of the user.
	 * @param model is object for Model.
	 * @return type of this method is view.
	 */
	
	@RequestMapping(value = "/activity-progress", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView activityProgress(@ModelAttribute Activity obj,HttpSession session,Model model){
		ModelAndView view = new ModelAndView();
		Activity activity = new Activity();
		try{
			view.setViewName(PageConstants.activity);
			
			view.addObject("tabActive", "input-forms");
			String notificationActivityId = null;
			String notificationWorkId = null;
			String notificationWorkName = null;
			String notificationId = null;
			if(StringUtils.isEmpty(obj.getNotificationActivityId()) && StringUtils.isEmpty(obj.getNotificationWorkId())) {
				notificationActivityId = (String) model.asMap().get("notificationActivityId");
				notificationWorkId = (String) model.asMap().get("notificationWorkId");
				notificationWorkName = (String) model.asMap().get("notificationWorkName");
				notificationId = (String) model.asMap().get("notificationId");
			}else {
				notificationActivityId = obj.getNotificationActivityId();
				notificationWorkId = obj.getNotificationWorkId();
				notificationWorkName = obj.getNotificationWorkName();
				notificationId = (String) model.asMap().get("notificationId");
			}
			
			view.addObject("notificationActivityId", notificationActivityId);
			view.addObject("notificationWorkId", notificationWorkId);
			view.addObject("notificationWorkName", notificationWorkName);
			view.addObject("notificationId", notificationId);
			
			List<Activity> works = service.getWorksList(activity);
			view.addObject("works", works);
			
		    String workId = (String) session.getAttribute("globalWorkId");			 
			obj.setWorkId(workId);	
			 
			List<Activity> progressHistory = service.getProgressHistory(obj);
			view.addObject("progressHistory", progressHistory);
				
			/*List<Activity> currentStatusActivities = service.getCurrentStatusActivities(obj);
			view.addObject("currentStatusActivities", currentStatusActivities);*/
			
			if(!StringUtils.isEmpty(notificationId)) {
				boolean flag = service.readNotification(notificationId);
			}
			
			 
			if (!StringUtils.isEmpty(obj.getActivityStatusId())) {	
				 view.addObject("currentActivityStatus", "active"+obj.getActivityStatusId());
			}else {
				 view.addObject("currentActivityStatus", "active4");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("activityProgress() : "+e.getMessage());
		}
		return view;
	}
	
	
	/**
	 * This method getCurrentStatusActivities() will call the ajax request.
	 * 
	 * @param obj is object for the model class Activity.
	 * @return type of this method is currentStatusActivities. 
	 */
	@RequestMapping(value = "/ajax/getCurrentStatusActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getCurrentStatusActivities(@ModelAttribute Activity obj){
		List<Activity> currentStatusActivities = null;
		try{
			currentStatusActivities = service.getCurrentStatusActivities(obj);	
		}catch(Exception e){
			logger.error("getCurrentStatusActivities() : "+e.getMessage());
		}
		return currentStatusActivities;
	}
	
	/**
	 * This method getActivityUpdateFormFromEmail() will execute when user update the Activity form
	 * 
	 * @param obj is object for the model class Activity
	 * @param attributes will show the flash message on the current request.
	 * @return type of this method is view.
	 */
	@RequestMapping(value = "/activity-update-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getActivityUpdateFormFromEmail(@ModelAttribute Activity obj,RedirectAttributes attributes){
	//public ModelAndView getActivityUpdateFormFromEmail(@RequestParam("taskId") String taskId,@RequestParam("workId") String workId){
		ModelAndView view = new ModelAndView();
		Activity activity = new Activity();
		try{
			//view.setViewName(PageConstants.activity);
			//view.addObject("tabActive", "input-forms");
			
			String taskId = EncryptDecrypt.base64Decode(obj.getTaskId());
			String workId = EncryptDecrypt.base64Decode(obj.getWorkId());
			String workName = EncryptDecrypt.base64Decode(obj.getWorkName());
			String notificationId = EncryptDecrypt.base64Decode(obj.getNotificationId());
			
			attributes.addFlashAttribute("notificationActivityId", taskId);
			attributes.addFlashAttribute("notificationWorkId", workId);
			attributes.addFlashAttribute("notificationWorkName", workName);
			attributes.addFlashAttribute("notificationId", notificationId);
			
			view.setViewName("redirect:/activity-progress");
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivityUpdateFormFromEmail() : "+e.getMessage());
		}
		return view;
	}
	
	/**
	 * This method will call the ajax request and fetch the contract list.
	 * 
	 * @param activity is object for the model class Activity.
	 * @param session it will check the session of the user. 
	 * @return type of this method is contracts
	 */
	@RequestMapping(value = "/ajax/getContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getContractsList(@ModelAttribute Activity activity,HttpSession session){
		List<Activity> contracts = null;
		try{
			contracts = service.getContractsList(activity);			
		}catch(Exception e){
			logger.error("getContractsList() : "+e.getMessage());
		}
		return contracts;
	}	
	
	/**
	 * This method will call the ajax request and fetch the activities list.
	 * 
	 * @param activity is object for the model class Activity.
	 * @param session it will check the session of the user. 
	 * @return type of this method is activities
	 */
	@RequestMapping(value = "/ajax/getActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getActivitiesList(@ModelAttribute Activity activity,HttpSession session){
		List<Activity> activities = null;
		try{
			activities = service.getActivitiesList(activity);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivitiesList() : "+e.getMessage());
		}
		return activities;
	}	
	
	/**
	 * This method will call the ajax request and fetch the locations list.
	 * 
	 * @param activity is object for the model class Activity.
	 * @param session it will check the session of the user. 
	 * @return type of this method is locations.
	 */
	@RequestMapping(value = "/ajax/getLocationsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getLocationsList(@ModelAttribute Activity activity,HttpSession session){
		List<Activity> locations = null;
		try{
			locations = service.getLocationsList(activity);			
		}catch(Exception e){
			logger.error("getLocationsList() : "+e.getMessage());
		}
		return locations;
	}
	
	
	/**
	 * This method will call the ajax request and fetch the activityTypes list.
	 * 
	 * @param activity is object for the model class Activity.
	 * @param session it will check the session of the user. 
	 * @return type of this method is activityTypes.
	 */
	
	@RequestMapping(value = "/ajax/getActivityTypesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getActivityTypesList(@ModelAttribute Activity activity,HttpSession session){
		List<Activity> activityTypes = null;
		try{
			activityTypes = service.getActivityTypesList(activity);			
		}catch(Exception e){
			logger.error("getActivityTypesList() : "+e.getMessage());
		}
		return activityTypes;
	}
	
	/**
	 * This method will call the ajax request and fetch the tasks list.
	 * 
	 * @param activity is object for the model class Activity.
	 * @return type of this method is tasks.
	 */
	@RequestMapping(value = "/ajax/getTasksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getTasksList(@ModelAttribute Activity activity){
		List<Activity> tasks = null;
		try{
			tasks = service.getTasksList(activity);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getTasksList() : "+e.getMessage());
		}
		return tasks;
	}
	/**
	 * This method will call the ajax request and fetch the quantities list.
	 * 
	 * @param activity is object for the model class Activity.
	 * @return type of this method is obj.
	 */
	
	@RequestMapping(value = "/ajax/getQuantities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Activity getQuantities(@ModelAttribute Activity activity){
		Activity obj = null;
		try{
			obj = service.getQuantities(activity);
		}catch(Exception e){
			logger.error("getQuantities() : "+e.getMessage());
		}
		return obj;
	}
	
	/**
	 * This method will call the ajax request and fetch the IssueCategories list.
	 * 
	 * @return type of this method is issueCategories.
	 */
	@RequestMapping(value = "/ajax/getIssueCategories", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getIssueCategories(){
		List<Activity> issueCategories = null;
		try{
			issueCategories = service.getIssueCategories();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getIssueCategories() : "+e.getMessage());
		}
		return issueCategories;
	}
	
	/**
	 * This method updateActivityProgress() will execute when user update the Activity form
	 * 
	 * @param session it will check the session of the user.
	 * @param activity is object for the model class Activity.
	 * @param attributes will show the flash message on the current request.
	 * @return type of this method is view.
	 */
	@RequestMapping(value = "/update-activity-progress", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateActivityProgress(HttpSession session,@ModelAttribute("activity") Activity activity,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		boolean flag = false;
		String currentDate = null;
		User user = null;
		String user_Id = null;String userName = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/activity-progress");
			user = (User) session.getAttribute("user");
			String createdBy = user.getUserName();
			activity.setCreatedBy(createdBy);
			activity.setCreatedById(user.getId());
			if(!StringUtils.isEmpty(activity.getReportingDate())){
				Date convertedDate = sdf.parse(activity.getReportingDate());
				currentDate = sqlDate.format(convertedDate);
				activity.setReportingDate(currentDate);
			}
			
			if(!StringUtils.isEmpty(activity.getActualStart())){
				Date convertedDate = sdf.parse(activity.getActualStart());
				currentDate = sqlDate.format(convertedDate);
				activity.setActualStart(currentDate);
			}
			
			if(!StringUtils.isEmpty(activity.getActualFinish())){
				Date convertedDate = sdf.parse(activity.getActualFinish());
				currentDate = sqlDate.format(convertedDate);
				activity.setActualFinish(currentDate);
			}
	        
	        
			//List<MultipartFile> files = activity.getMediaFile();
			MultipartFile file = activity.getActivityFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.ACTIVITY_IMAGES+activity.getTaskId()+File.separator+currentDate+File.separator;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				saveDirectory = saveDirectory + fileName;
				activity.setFilesPath(saveDirectory);
			}
			
	        /*if (null != files && files.size() > 0){
	            for (MultipartFile multipartFile : files) {
	            	//String saveDirectory = "F:\\FileUploads\\";
	            	String saveDirectory = CommonConstants.ACTIVITY_IMAGES;
	                String fileName = multipartFile.getOriginalFilename();
	                if (!"".equalsIgnoreCase(fileName)) {
		                saveDirectory = saveDirectory+activity.getTaskId()+"/"+currentDate;
		                AmazonS3Storage.saveFileInS3Bucket(saveDirectory,multipartFile);
		                activity.setFilesPath(saveDirectory);
	                }
	            }	        
	        }*/
	        
			
			flag = service.saveProgress(activity);
			if(flag){
				if(!StringUtils.isEmpty(activity.getNotificationId())) {
					boolean notificationFlag = service.closeNotification(activity.getNotificationId());
				}
				if(activity.getMarkAsComplete().equalsIgnoreCase("true")){
					attributes.addFlashAttribute("message", taskComplete);
				}else{
					attributes.addFlashAttribute("message", taskUpdate);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("updateActivityProgress() : User Id - "+user_Id+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);
		}
		return view;
	}
	
	
	/**
	 * This method activityData() will show the activity data.
	 *  
	 * @param session it will check the session of the user.
	 * @return type of this method is view.
	 */
	@RequestMapping(value = "/activity-data", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView activityData(HttpSession session){
		ModelAndView view = new ModelAndView();
		try{
			view.setViewName(PageConstants.primaveraData);
			view.addObject("tabActive", "input-forms");
			
			List<Activity> works = service.getWorks();
			view.addObject("works", works);
			
			//Activity activityData = service.getActivityData();
			//view.addObject("activityData", activityData);
			
			List<Activity> activityDataHistory = service.getActivityDataHistory();
			view.addObject("activityDataHistory", activityDataHistory);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("activityProgress() : "+e.getMessage());
		}
		return view;
	}
	
	/**
	 * This method will call the ajax request and fetch the Activity data list. 
	 * 
	 * @param workId is string type variable that takes the Activity work id as input
	 * @return type of this method is activityData.
	 */
	@RequestMapping(value = "/ajax/getActivityData", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Activity getActivityData(@RequestParam String workId){
		Activity activityData = null;
		try{
			activityData = service.getActivityData(workId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivityData() : "+e.getMessage());
		}
		return activityData;
	}
	
    /**
     * This method updateDataDate() will execute when user update Activity form and it will display the success and error message.
     *  
     * @param activity is object for the model class Activity.
     * @param attributes will show the flash message on the current request.
     * @param session it will check the session of the user.
     * @return of this method is model
     */
	@RequestMapping(value = "/update-activities", method = {RequestMethod.POST})
	public ModelAndView updateDataDate(@ModelAttribute Activity activity,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String activityDataId = null;
		String fileName = null;
		String userId = null;String userName = null;
		XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/activity-data");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
			
			activity.setCreatedBy(userName);
			
			if(!StringUtils.isEmpty(activity.getDataDate())){
				MultipartFile multipartFile = activity.getP6File();
				// Creates a workbook object from the uploaded excelfile
				if (null != multipartFile && multipartFile.getSize() > 0){					
					workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					if(workbook != null && !"".equals(workbook)) {
						int sheetsCount = workbook.getNumberOfSheets();
						if(sheetsCount > 0) {
							uploadFilesSheet = workbook.getSheetAt(0);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							XSSFRow headerRow = uploadFilesSheet.getRow(1);
							//checking given file format
							if(headerRow != null){
								List<String> fileFormat = FileFormatModel.getP6FileFormat();;	
								int noOfColumns = headerRow.getLastCellNum();
								if(noOfColumns == fileFormat.size()){
									for (int i = 0; i < fileFormat.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
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
		                activity.setP6FileName(fileName);
	                }
					
				}
                
                Date convertedDate = sdf.parse(activity.getDataDate());
				activity.setDataDate(sqlDate.format(convertedDate));
				
				activityDataId = service.updateDataDate(activity);
				
				String workId = activity.getWorkId();
				
				if(!StringUtils.isEmpty(activityDataId) && !StringUtils.isEmpty(fileName)) {
					/*String saveDirectory = CommonConstants.P6_DATA_FILES;	                
					saveDirectory = saveDirectory+activityDataId;
					AmazonS3Storage.saveFileInS3Bucket(saveDirectory,multipartFile);*/		
					String saveDirectory = CommonConstants.P6_DATA_FILES+activityDataId+File.separator;
					FileUploads.singleFileSaving(multipartFile,saveDirectory,fileName);
		            
					int count = updateActivities(activity,userId,userName,workbook);
					if(count > 0) {
						attributes.addFlashAttribute("message", "Data date and " + count + " activitie(s) updated successfully.");			            
					} else {
						attributes.addFlashAttribute("message", "Data date is updated. But " + count + " activitie(s) updated.");
					}
					boolean flag = service.updateWorkTable(workId,userName);
				} else if(!StringUtils.isEmpty(activityDataId)) {
					boolean flag = service.updateWorkTable(workId,userName);
					attributes.addFlashAttribute("message", "Data date has been updated successfully.");
				} else {
					 attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
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
	 * This method updateActivities() is called when user upload the file
	 * 
	 * @param obj is object for the model class Activity.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @return type of this method is count.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	public int updateActivities(Activity obj, String userId, String userName, XSSFWorkbook workbook) throws Exception {
		logger.info("AirportController  >> uploadActivities() >> start");
		Activity activity = null;
		List<Activity> activitiesList = new ArrayList<Activity>();
		
		//XSSFWorkbook workbook = null;
		XSSFSheet uploadFilesSheet = null;
		Writer w = null;
		int count = 0;
		try {	
			/*List<String> fileFormat = null;				
			fileFormat = FileFormatModel.getActivityFileFormat();*/
			
			MultipartFile excelfile = obj.getP6File();
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
							uploadFilesSheet = workbook.getSheetAt(0);
							//System.out.println(uploadFilesSheet.getSheetName());
							//header row
							//XSSFRow headerRow = uploadFilesSheet.getRow(0);
							
							//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
							//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							
							//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
							
							SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
							
							for(int i = 2; i<= uploadFilesSheet.getLastRowNum();i++){
								XSSFRow row = uploadFilesSheet.getRow(i);
								// Sets the Read data to the model class
								DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
								// Cell cell = row.getCell(0);
								// String j_username = formatter.formatCellValue(row.getCell(0));
								
								activity = new Activity();
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(0)).trim()))
									activity.setTaskCode(formatter.formatCellValue(row.getCell(0)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(1)).trim()))
									activity.setActivityStatus(formatter.formatCellValue(row.getCell(1)).trim());
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(2)).trim()))
									activity.setWbsId(formatter.formatCellValue(row.getCell(2)).trim());
								
								/*if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									activity.setBaseStart(formatter.formatCellValue(row.getCell(3)).trim());											
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									activity.setBaseEnd(formatter.formatCellValue(row.getCell(4)).trim());*/
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(3)).trim()))
									activity.setPlannedStart(formatter.formatCellValue(row.getCell(3)).trim());											
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(4)).trim()))
									activity.setPlannedFinish(formatter.formatCellValue(row.getCell(4)).trim());
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(5)).trim()))
									activity.setActualStart(formatter.formatCellValue(row.getCell(5)).trim());										
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(6)).trim()))
									activity.setActualFinish(formatter.formatCellValue(row.getCell(6)).trim());
								
								/*if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(7)).trim()))
									activity.setPercentageComplete(formatter.formatCellValue(row.getCell(7)).trim());*/
								
								if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(7)).trim()))
									activity.setFloatValue(formatter.formatCellValue(row.getCell(7)).trim());
								
								/*if(!StringUtils.isEmpty(formatter.formatCellValue(row.getCell(8)).trim()))
									activity.setBoqCompleted(formatter.formatCellValue(row.getCell(8)).trim());*/
								
								/*if(!StringUtils.isEmpty(activity.getBaseStart())){
									Date convertedDate = sdf.parse(activity.getBaseStart());
									String currentDate = sqlDate.format(convertedDate);
									activity.setBaseStart(currentDate);
								}
								
								if(!StringUtils.isEmpty(activity.getBaseEnd())){
									Date convertedDate = sdf.parse(activity.getBaseEnd());
									String currentDate = sqlDate.format(convertedDate);
									activity.setBaseEnd(currentDate);
								}*/
								
								if(!StringUtils.isEmpty(activity.getActualStart())){
									Date convertedDate = sdf.parse(activity.getActualStart());
									String currentDate = sqlDate.format(convertedDate);
									activity.setActualStart(currentDate);
								}
								
								if(!StringUtils.isEmpty(activity.getActualFinish())){
									Date convertedDate = sdf.parse(activity.getActualFinish());
									String currentDate = sqlDate.format(convertedDate);
									activity.setActualFinish(currentDate);
								}
								
								if(!StringUtils.isEmpty(activity.getPlannedStart())){
									Date convertedDate = sdf.parse(activity.getPlannedStart());
									String currentDate = sqlDate.format(convertedDate);
									activity.setPlannedStart(currentDate);
								}
								
								if(!StringUtils.isEmpty(activity.getPlannedFinish())){
									Date convertedDate = sdf.parse(activity.getPlannedFinish());
									String currentDate = sqlDate.format(convertedDate);
									activity.setPlannedFinish(currentDate);
								}							
																
								activity.setCreatedBy(userName);
								
								if(!StringUtils.isEmpty(activity) && !StringUtils.isEmpty(activity.getTaskCode()))
									activitiesList.add(activity);
							}
							
							if(!activitiesList.isEmpty() && activitiesList != null){
								count  = service.updateActivities(activitiesList,obj.getWorkId());
							}
						}
						workbook.close();
					}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateActivities() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("updateActivities() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
	/**
	 * This method will execute when user get the excel sheet.
	 * 
	 * @param activityDataId is type of string variable that holds the activity data id.
	 * @param response it process the response.
	 * @param fileName it is string type variable that hold the filename
	 */
	@RequestMapping("/getP6File/{fileName:.+}/{activityDataId}")
	public void getP6File(@PathVariable("activityDataId") String activityDataId, HttpServletResponse response,@PathVariable("fileName") String fileName) {
		  try {
			  //String filePath = CommonConstants.P6_DATA_FILES+activityDataId;
	    	  //InputStream objectContent = AmazonS3Storage.getFileFromS3Bucket(filePath, fileName);
			  
			  String filePath = CommonConstants.P6_DATA_FILES+activityDataId+File.separator+fileName;
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
			  logger.error("getP6File() : "+e.getMessage());
		  }
	 }
	
	
	/*@RequestMapping("/getInputFile/{fileName:.+}/{progressId}")
	public void getInputFile(@PathVariable("progressId") String progressId, HttpServletResponse response,@PathVariable("fileName") String fileName) {
		  try {
	    	  String filePath = service.getInputFilePath(progressId);
	    	  
	    	  //InputStream objectContent = AmazonS3Storage.getFileFromS3Bucket(filePath, fileName);
	    	  
	    	  filePath = filePath+fileName;
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
			  logger.error("getInputFile() : "+e.getMessage());
		  }
	}*/
	
	/**
	 * 
	 * @param progressId is string type variable that holds the progress id
	 * @param response it process the response.
	 */
	@RequestMapping("/getInputFile/{progressId}")
	public void getInputFile(@PathVariable("progressId") String progressId, HttpServletResponse response) {
		  try {
	    	  String filePath = service.getInputFilePath(progressId);
	    	  
	    	  //InputStream objectContent = AmazonS3Storage.getFileFromS3Bucket(filePath, fileName);
			  File initialFile = new File(filePath);
			  InputStream objectContent = new FileInputStream(initialFile);
			  
			  //create object of Path 
		      Path path = Paths.get(filePath); 
		  
		      // call getFileName() and get FileName path object 
		      Path fileName = path.getFileName(); 
		        
			  
			  response.setContentType("application/octet-stream");
			  // add response header
			  response.addHeader("Content-Disposition", "attachment; filename=" + fileName.toString());
			  //copies all bytes from a file to an output stream
			  IOUtils.copy(objectContent, response.getOutputStream());
			  //flushes output stream
			  response.getOutputStream().flush();
			  response.flushBuffer();
		  } catch (Exception e) {
			  logger.error("getInputFile() : "+e.getMessage());
		  }
	}
	
	/**
	 * This method will call the ajax request and fetch the Project list
	 * @return of this method is projects
	 */
	@RequestMapping(value = "/ajax/getProjectsListForSearch", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getProjectsListForSearch(){
		List<Activity> projects = null;
		try{
			projects = service.getProjectsListForSearch();			
		}catch(Exception e){
			logger.error("getProjectsListForSearch() : "+e.getMessage());
		}
		return projects;
	}
	
	/**
	 * This method will call the ajax request and fetch the Work list
	 * @param obj is object for the model class Activity.
	 * @return type of this method is works
	 */
	@RequestMapping(value = "/ajax/getWorksListForSearch", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Activity> getWorksListForSearch(@ModelAttribute Activity obj){
		List<Activity> works = null;
		try{
			works = service.getWorksListForSearch(obj);			
		}catch(Exception e){
			logger.error("getWorksListForSearch() : "+e.getMessage());
		}
		return works;
	}
	
}
