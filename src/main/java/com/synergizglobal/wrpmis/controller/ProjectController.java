package com.synergizglobal.wrpmis.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.wrpmis.model.FileFormatModel;
import com.synergizglobal.wrpmis.model.Work;
import com.synergizglobal.wrpmis.Iservice.HomeService;
import com.synergizglobal.wrpmis.Iservice.ProjectService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Contractor;
import com.synergizglobal.wrpmis.model.ContractorPaginationObject;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.ProjectPaginationObject;
import com.synergizglobal.wrpmis.model.Safety;
import com.synergizglobal.wrpmis.model.Training;
import com.synergizglobal.wrpmis.model.Year;
import com.synergizglobal.wrpmis.reference.Iservice.ProjectTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Controller
public class ProjectController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

	Logger logger = Logger.getLogger(ProjectController.class);
	
	@Autowired
	ProjectTypeService service;	
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	HomeService homeService;
	
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
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	
	@RequestMapping(value="/project",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Project(HttpSession session,@ModelAttribute Project project){
		ModelAndView model = new ModelAndView(PageConstants.project);
		try {
			List<Project> projectList = projectService.getProjectList(project);
			model.addObject("projectList", projectList);	
		}catch (Exception e) {
			e.printStackTrace();
			
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/get-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getProjectDetails(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "edit");
			projectId= project.getProject_id();
			Project projectDetails = projectService.getProject(projectId, project);
			model.addObject("projectDetails", projectDetails);
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<TrainingType> projectTypes = projectService.getProjectTypeDetails();
			model.addObject("projectTypes", projectTypes);
			
			List<TrainingType> railwayZones = projectService.getRailwayZones();
			model.addObject("railwayZones", railwayZones);			
	
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
			
	        List<Project> divisions = projectService.getAllDivisions();
	        model.addObject("divisions", divisions);

	        List<Project> sections = projectService.getAllSections();
	        model.addObject("sections", sections);	
	        
			
		}catch (Exception e) {
			logger.error("getProjectDetails : " + e.getMessage());
		}
		return model;

	}
	
	@RequestMapping(value = "/get-project/{project_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getProjectDetailsForId(@ModelAttribute Project project,@PathVariable("project_id") String project_id  ){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "edit");
			projectId= project.getProject_id();
			Project projectDetails = projectService.getProject(projectId, project);
			model.addObject("projectDetails", projectDetails);
			
			/*List <Project> fileNames = projectService.getFileNames(projectId);
			model.addObject("fileNames", fileNames);*/	
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<TrainingType> projectTypes = projectService.getProjectTypeDetails();
			model.addObject("projectTypes", projectTypes);
			
			List<TrainingType> railwayZones = projectService.getRailwayZones();
			model.addObject("railwayZones", railwayZones);			
	
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
			
	        List<Project> divisions = projectService.getAllDivisions();
	        model.addObject("divisions", divisions);

	        List<Project> sections = projectService.getAllSections();
	        model.addObject("sections", sections);				
			
			
		}catch (Exception e) {
			logger.error("getProjectDetailsForId : " + e.getMessage());
		}
		return model;

	}
	
	@RequestMapping(value = "/update-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateProject(@ModelAttribute Project project,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			project.setCreated_by_user_id_fk(user_Id);
			project.setUser_name(userName);
			project.setDesignation(userDesignation);
			
			project.setCreated_by(userName);
			
			model.setViewName("redirect:/project");
			/*
			 * MultipartFile file = project.getProjectFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.PROJECT_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); project.setAttachment(fileName); }
			 */	
			boolean flag =  projectService.updateProject(project);
			if(flag == true) {
				attributes.addFlashAttribute("success", "Project Updated Succesfully.");

			}
			else {
				attributes.addFlashAttribute("error","Updating Project is failed. Try again.");

			}
		
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Project is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/upload-chainages", method = {RequestMethod.POST})
	public ModelAndView uploadChainages(@ModelAttribute Project obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";String userId = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute	("USER_DESIGNATION");
			String userRole = (String) session.getAttribute("USER_ROLE_CODE");
			
			model.setViewName("redirect:/get-project/"+obj.getProject_id_fk());
			obj.setProject_id(obj.getProject_id_fk());
			
			obj.setUser_id(userId);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			if(!StringUtils.isEmpty(obj.getProjectChainagesFile())){
				MultipartFile multipartFile = obj.getProjectChainagesFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet laSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = laSheet.getRow(0);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getWorkChainagesFileFormat();	
							int noOfColumns = headerRow.getLastCellNum();
							if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		msg = uploadformatError;
				                		obj.setUploaded_by_user_id_fk(userId);
				                		obj.setStatus("Fail");
				                		obj.setRemarks(msg);
										boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
								msg = uploadformatError;
		                		obj.setUploaded_by_user_id_fk(userId);
		                		obj.setStatus("Fail");
		                		obj.setRemarks(msg);
								boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
							msg = uploadformatError;
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
	                		boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
	                		return model;
						}
						String[]  result = uploadProjectChainages(obj,userId,userName);
						String errMsg = result[0];String actualVal = "";
						int count = 0,row = 0,sheet = 0,subRow = 0;
						List<String> fileFormat = FileFormatModel.getRRFileFormat();
						if(!StringUtils.isEmpty(result[1])){count = Integer.parseInt(result[1]);}
						if(!StringUtils.isEmpty(result[2])){row = Integer.parseInt(result[2]);}
						if(!StringUtils.isEmpty(result[3])){sheet = Integer.parseInt(result[3]);}
						if(!StringUtils.isEmpty(result[4])){subRow = Integer.parseInt(result[4]);}
						//System.out.println(errMsg);
						if(!StringUtils.isEmpty(errMsg)) {
							if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Duplicate entry")) {
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;<b>Work and RR Id Mismatch at row: ("+row+")</b> please check and Upload again.</span>");
								msg = "Work and R & R Id Mismatch at row: "+row;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Data truncated")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row; 
									String error = "Data truncated";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								} 
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect Value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Cannot add or update a child row")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Cannot add or update a child row";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect Value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect date value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Incorrect date value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect date value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect date value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect integer value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row; 
									String error = "Incorrect integer value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect integer value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect integer value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect decimal value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Incorrect decimal value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect decimal value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect decimal value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Data too long for column")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Data too long for column";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Data too long for value in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect decimal value identified in Sheet: "+sheet+" at row: "+actualVal;
							}
						
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
							boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
	                		return model;
						}
					
						if(count > 0) {
							attributes.addFlashAttribute("success","<i class='fa fa-check'></i>&nbsp;"+ count + "<span style='color:green;'> records Uploaded successfully.</span>");	
							msg = count + " records Uploaded successfully.";
							
						}else {
							attributes.addFlashAttribute("success"," No records found.");	
							msg = " No records found.";
						}
						obj.setUploaded_by_user_id_fk(userId);
                		obj.setStatus("Success");
                		obj.setRemarks(msg);
						boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				msg = "No file exists";
				obj.setUploaded_by_user_id_fk(userId);
        		obj.setStatus("Fail");
        		obj.setRemarks(msg);
				boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
			msg = "Something went wrong. Please try after some time";
			obj.setUploaded_by_user_id_fk(userId);
    		obj.setStatus("Fail");
    		obj.setRemarks(msg);
		
			try {
				boolean flag = projectService.saveProjectChainagesDataUploadFile(obj);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveDesignDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	private  String[]  uploadProjectChainages(Project obj, String userId, String userName) throws Exception {
		Project project = null;
		List<Project> workChainagesList = new ArrayList<Project>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getProjectChainagesFile();
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					
					XSSFSheet laSheet = workbook.getSheetAt(0);
					
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					for(int i = 1; i <= laSheet.getLastRowNum();i++){
						int v = laSheet.getLastRowNum();
						XSSFRow row = laSheet.getRow(i);
						project = new Project();
						
						String val = null;
						if(!StringUtils.isEmpty(row)) 
						{	
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { project.setSrno(val);}							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { project.setProject_id_fk(val);project.setProject_id(val);}
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { project.setChainages(val);}
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { project.setLatitude(val);}
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { project.setLongitude(val);}							
						}
				
						workChainagesList.add(project);
					}
					
					if(!workChainagesList.isEmpty() && workChainagesList != null){
						String[] arr  = projectService.uploadProjectChainagesData(workChainagesList,project);
						result[0] = arr[0];
						result[1] = arr[1];
						result[2] = arr[2];
						result[3] = arr[3];
						result[4] = arr[4];
					}
					
				}
				workbook.close();
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadChainages() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadChainages() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return result;
	}	
	
	@RequestMapping(value = "/add-project-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProjectForm(){
		ModelAndView model = new ModelAndView();
		
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "add");
			
			List<Year> yearList = projectService.getYearList();
			model.addObject("yearList", yearList);
			
			List<TrainingType> projectTypes = projectService.getProjectTypeDetails();
			model.addObject("projectTypes", projectTypes);
			
			List<TrainingType> railwayZones = projectService.getRailwayZones();
			model.addObject("railwayZones", railwayZones);	
			
	        List<Project> divisions = projectService.getAllDivisions();
	        model.addObject("divisions", divisions);

	        List<Project> sections = projectService.getAllSections();
	        model.addObject("sections", sections);			
	
			
			List<Project> projectFileTypes = projectService.getProjectFileTypes();
			model.addObject("projectFileTypes", projectFileTypes);
			
		}catch (Exception e) {
				logger.error("Work : " + e.getMessage());
			}
			return model;
	 }
	
	@RequestMapping(value = "/add-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProject(@ModelAttribute  Project project,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			project.setCreated_by_user_id_fk(user_Id);
			project.setUser_name(userName);
			project.setDesignation(userDesignation);
			
			project.setCreated_by(userName);
			
			model.setViewName("redirect:/project");
			/*
			 * MultipartFile file = project.getProjectFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.PROJECT_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); project.setAttachment(fileName); }
			*/	
			boolean flag =  projectService.addProject(project);
			if(flag == true) {
				attributes.addFlashAttribute("success", "Project Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Project is failed. Try again.");

			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Project is failed. Try again.");
			logger.error("Project : " + e.getMessage());
		}
		return model;
	
	}
	
	@RequestMapping(value = "/delete-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteProjectRow(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName("redirect:/project");
			projectId= project.getProject_id();
			boolean flag =  projectService.deleteProject(projectId,project);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	
	}
	
	
	@RequestMapping(value = "/export-project", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportProject(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Project project,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.project);
		List<Project> dataList = new ArrayList<Project>();
		List<Project> pinkBookList = new ArrayList<Project>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/project");
			dataList = projectService.getProjectList(project);
			pinkBookList = projectService.getProjectPinkBookList();
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Project"));
			            XSSFSheet pinkBookSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Project Pink Book"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
				        workBook.setSheetOrder(pinkBookSheet.getSheetName(), 1);
				        
				        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
				        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
				        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
				        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
				        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
				        
				        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
				        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        
				        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        
				        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
				        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
				        
				        
				        
			            XSSFRow headingRow = sheet.createRow(0);
			            String headerString = "Project ID^Project Name^Plan Head Number^Financial Year^Railway^PB No^Benefits^Remarks^Project Status";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            XSSFRow headingRow1 = pinkBookSheet.createRow(0);
			            String headerString1 = "Project ID^Financial Year^PB Item No";
			            
			            String[] secondHeaderStringArr = headerString1.split("\\^");
			            
			            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow1.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(secondHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (Project obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_name());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getPlan_head_number());
							
							
							
							cell = row.createCell(c++); 
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getFinancial_year_fk());
							
							String railway = null;
							String pbItemNo = null;
							String string = obj.getPb_item_no();
							if(!(StringUtils.isEmpty(string)) && string.contains("-")) {
								String[] parts = string.split("-");
								int p = parts.length;
								int y =0;
								if(p>1) {
									railway = parts[y++]; 
									pbItemNo = parts[y++];
								}else {
									railway = parts[y++]; 
								}
								 
							}else {
								pbItemNo = string;
							}
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(railway);
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(pbItemNo);
							
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getBenefits());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
			                
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_status());
							
			                rowNo++;
			            }
			            short rowNo2 = 1;
						
							 for (Project obj : pinkBookList) {
					                XSSFRow row = pinkBookSheet.createRow(rowNo2);
					                int b = 0;
					                
					                Cell cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getProject_id_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getFinancial_year_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getPb_item_no());
									
									rowNo2++;
							    }
					       
						 
			            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
			            for(int columnIndex = 0; columnIndex < secondHeaderStringArr.length; columnIndex++) {
			            	pinkBookSheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Project_"+dateFormat.format(date);
		                
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
			logger.error("exportProject : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
	private CellStyle cellFormating(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
		CellStyle style = workBook.createCellStyle();
		//Setting Background color  
		//style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (style instanceof XSSFCellStyle) {
		   XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle)style;
		   xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		//style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);
		
		Font font = workBook.createFont();
        //font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontHeightInPoints((short)fontSize);  
        font.setFontName(fontName);  //"Times New Roman"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
	
}
