
package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.ProjectPaginationObject;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.WorkPaginationObject;
import com.synergizglobal.pmis.model.Year;

@Controller
public class WorkController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkController.class);
	
	@Autowired
	WorkService workService;
	
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
	
	@RequestMapping(value="/work",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Work(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.work);
		try {
			List<Work> workList = workService.getWorkList(null);
			model.addObject("workList", workList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/get-WorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksList(@ModelAttribute Work obj) {
		List<Work> worksList = null;
		try {
			worksList = workService.getWorksList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getWorkStatusList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorkStatusList(@ModelAttribute Work obj) {
		List<Work> workStatusList = null;
		try {
			workStatusList = workService.getWorkStatusList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusList : " + e.getMessage());
		}
		return workStatusList;
	}
	
	@RequestMapping(value = "/ajax/getworkCodeList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getworkCodeList(@ModelAttribute Work obj) {
		List<Work> workCodeList = null;
		try {
			workCodeList = workService.getworkCodeList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getworkCodeList : " + e.getMessage());
		}
		return workCodeList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInWork", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getProjectsList(@ModelAttribute Work obj) {
		List<Work> projectsList = null;
		try {
			projectsList = workService.getWorktProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	/**
	@RequestMapping(value = "/ajax/get-works", method = { RequestMethod.POST, RequestMethod.GET }) 
	public void getWorksList(@ModelAttribute Work obj, HttpServletRequest request,
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

			List<Work> workList = new ArrayList<Work>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				workList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				workList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//workList = getListBasedOnSearchParameter(searchParameter,workList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			WorkPaginationObject personJsonObject = new WorkPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(workList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}
*/
	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 *//**
	public int getTotalRecords(Work obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = workService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}*/

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 *//**
	public List<Work> createPaginationData(int startIndex, int offset,Work obj, String searchParameter) {
		List<Work> objList = null;
		try {
			objList = workService.getWorksList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	*/
	@RequestMapping(value = "/get-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkDetails(@ModelAttribute Work work,Railway rail){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "edit");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);
			
			List<Work> unitsList = workService.getUnitsList();
			model.addObject("unitsList", unitsList);
			
			List<Work> workTypeList = workService.getWorkTypeList();
			model.addObject("workTypeList", workTypeList);
			
			workId= work.getWork_id();
			Work workDetails = workService.getWork(workId, work);
			model.addObject("workDetails", workDetails);
		}catch (Exception e) {
			logger.error("getWorkDetails : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-work/{work_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkDetailsWithId(@ModelAttribute Work work,Railway rail,@PathVariable("work_id") String work_id ){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "edit");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);
			
			List<Work> unitsList = workService.getUnitsList();
			model.addObject("unitsList", unitsList);

			List<Work> workTypeList = workService.getWorkTypeList();
			model.addObject("workTypeList", workTypeList);
			
			workId= work.getWork_id();
			Work workDetails = workService.getWork(workId, work);
			model.addObject("workDetails", workDetails);
		}catch (Exception e) {
			logger.error("getWorkDetailsWithId : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-work-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWorkForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "add");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
			List<Railway> excecuteList = workService.getExcecuteList();
			model.addObject("excecuteList", excecuteList);
			List<Year> yearList = workService.getYearList();
			model.addObject("yearList", yearList);
			List<Work> unitsList = workService.getUnitsList();
			model.addObject("unitsList", unitsList);
			List<Work> workFileTypes = workService.getWorkFileTypes();
			model.addObject("workFileTypes", workFileTypes);

			List<Work> workTypeList = workService.getWorkTypeList();
			model.addObject("workTypeList", workTypeList);
			
		}catch (Exception e) {
				logger.error("Work : " + e.getMessage());
			}
			return model;
	 }
	
	
	@RequestMapping(value = "/update-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWork(@ModelAttribute Work work,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			//obj.setCreated_by_user_id_fk(user_Id);
			work.setUser_id(user_Id);
			work.setUser_name(userName);
			work.setDesignation(userDesignation);
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			/*
			 * MultipartFile file = work.getWorkFile(); if (null != file &&
			 * !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.WORK_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); work.setAttachment(fileName); }
			 */			
			boolean flag =  workService.updateWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Work is failed. Try again.");
			logger.error("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-work", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWork(@ModelAttribute Work work,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			//obj.setCreated_by_user_id_fk(user_Id);
			work.setUser_id(user_Id);
			work.setUser_name(userName);
			work.setDesignation(userDesignation);
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			work.setProjected_completion(DateParser.parse(work.getProjected_completion()));
			work.setProjected_completion_date(DateParser.parse(work.getProjected_completion_date()));
			/*MultipartFile file = work.getWorkFile();
			
			 * if (null != file && !file.isEmpty()){ String saveDirectory =
			 * CommonConstants.WORK_FILE_SAVING_PATH ; String fileName =
			 * file.getOriginalFilename(); FileUploads.singleFileSaving(file, saveDirectory,
			 * fileName); work.setAttachment(fileName); }
			 */
			boolean flag =  workService.addWork(work);
			if(flag) {
				attributes.addFlashAttribute("success", "Work Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Work is failed. Try again.");
			logger.error("addWork : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteRow", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteRow(@ModelAttribute Work work){
		ModelAndView model = new ModelAndView();
		String workId = null;
		try{
			model.setViewName("redirect:/work");
			workId = work.getWork_id();
			boolean flag =  workService.deleteRow(workId,work);
		}catch (Exception e) {
			logger.error("Work : " + e.getMessage());
		}
		return model;
	
	}
	
	/*
	@RequestMapping(value = "/export-work", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportWork(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.work);
		List<Work> dataList = new ArrayList<Work>();
		try {
			view.setViewName("redirect:/work");
			dataList = workService.getWorkList(work); 
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Work"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Project ID");
	            headingRow.createCell((short)1).setCellValue("Project Name");
	         	headingRow.createCell((short)2).setCellValue("Work ID");
	            headingRow.createCell((short)3).setCellValue("Work Name");
	            headingRow.createCell((short)4).setCellValue("Work Short Name");
	            headingRow.createCell((short)5).setCellValue("Sanctioned Year");
	            headingRow.createCell((short)6).setCellValue("Railway Agency");
	            headingRow.createCell((short)7).setCellValue("Executed By");
	            headingRow.createCell((short)8).setCellValue("Sanctioned Estimated Cost");
	            headingRow.createCell((short)9).setCellValue("Sanctioned Completion Cost");
	            headingRow.createCell((short)10).setCellValue("Completeion Period Months");
	            headingRow.createCell((short)11).setCellValue("Anticipated Cost");
	            headingRow.createCell((short)12).setCellValue("Year of Completion");
	            headingRow.createCell((short)13).setCellValue("Projected Completion");
	            headingRow.createCell((short)14).setCellValue("Completion Cost");
	            headingRow.createCell((short)15).setCellValue("Remarks");
	            short rowNo = 1;
	            for (Work obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getProject_id_fk());
	                row.createCell((short)1).setCellValue(obj.getProject_name());
	                row.createCell((short)2).setCellValue(obj.getWork_id());
	                row.createCell((short)3).setCellValue(obj.getWork_name());
	                row.createCell((short)4).setCellValue(obj.getWork_short_name());
	                row.createCell((short)5).setCellValue(obj.getSanctioned_year_fk());
	                row.createCell((short)6).setCellValue(obj.getRailway());
	                row.createCell((short)7).setCellValue(obj.getExecuted_by());
	                row.createCell((short)8).setCellValue(obj.getSanctioned_estimated_cost());
	                row.createCell((short)9).setCellValue(obj.getSanctioned_completion_cost());
	                row.createCell((short)10).setCellValue(obj.getCompleteion_period_months());
	                row.createCell((short)11).setCellValue(obj.getAnticipated_cost());
	                row.createCell((short)12).setCellValue(obj.getYear_of_completion());
	                row.createCell((short)13).setCellValue(obj.getProjected_completion());
	                row.createCell((short)14).setCellValue(obj.getCompletion_cost());
	                row.createCell((short)15).setCellValue(obj.getRemarks());
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//Budgetsheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Work_"+dateFormat.format(date);
                
	            try{
	                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
	                workBook.write(fos);
	                fos.flush();*/
	           /* 	
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
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);			
		}
		
	}
*/
	
	@RequestMapping(value = "/upload-chainages", method = {RequestMethod.POST})
	public ModelAndView uploadChainages(@ModelAttribute Work obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";String userId = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute	("USER_DESIGNATION");
			String userRole = (String) session.getAttribute("USER_ROLE_CODE");
			
			model.setViewName("redirect:/get-work/"+obj.getWork_id());
			
			obj.setUser_id(userId);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			if(!StringUtils.isEmpty(obj.getWorkChainagesFile())){
				MultipartFile multipartFile = obj.getWorkChainagesFile();
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
										boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
								msg = uploadformatError;
		                		obj.setUploaded_by_user_id_fk(userId);
		                		obj.setStatus("Fail");
		                		obj.setRemarks(msg);
								boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
							msg = uploadformatError;
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
	                		boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
	                		return model;
						}
						String[]  result = uploadWorkChainages(obj,userId,userName);
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
							boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
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
						boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				msg = "No file exists";
				obj.setUploaded_by_user_id_fk(userId);
        		obj.setStatus("Fail");
        		obj.setRemarks(msg);
				boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
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
				boolean flag = workService.saveWorkChainagesDataUploadFile(obj);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveDesignDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	private  String[]  uploadWorkChainages(Work obj, String userId, String userName) throws Exception {
		Work work = null;
		List<Work> workChainagesList = new ArrayList<Work>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getWorkChainagesFile();
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					
					XSSFSheet laSheet = workbook.getSheetAt(0);
					
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					for(int i = 1; i <= laSheet.getLastRowNum();i++){
						int v = laSheet.getLastRowNum();
						XSSFRow row = laSheet.getRow(i);
						work = new Work();
						String val = null;
						if(!StringUtils.isEmpty(row)) 
						{		
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { work.setWork_id(val);}
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { work.setChainage_from(val);}
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { work.setChainage_to(val);}
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { work.setLatitude(val);}
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { work.setLongitude(val);}							
						}
				
						workChainagesList.add(work);
					}
					
					if(!workChainagesList.isEmpty() && workChainagesList != null){
						String[] arr  = workService.uploadWorkChainagesData(workChainagesList,work);
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
	@RequestMapping(value = "/export-work", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportWork(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Work work,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.work);
		List<Work> dataList = new ArrayList<Work>();
		List<Work> revisionList = new ArrayList<Work>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/work");
			dataList = workService.getWorksList(work); 
			revisionList = workService.getWorkRevisionsList(work);
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Work"));
			            XSSFSheet revisonSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Revision Details"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
				        workBook.setSheetOrder(revisonSheet.getSheetName(), 1);
				        
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
			            String headerString = "Project ID^Project Name^Work ID^Work Short Name^Sanctioned Year^Railway Agency^Executed By^Sanctioned Estimated  Cost (Cr)^Sanctioned Completion  Cost (Cr)^Completeion Period Months^"
			            		+ "Anticipated  Cost (Cr)^Projected Completion Date^Completion  Cost (Cr)^Year of Completion^Remarks";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            XSSFRow headingRow1 = revisonSheet.createRow(0);
			            String headerString1 = "Work ID^Financial Year^PB Item No^Latest Revised  Cost (Cr)^Year Of Revision^Revision Number";
			            
			            String[] secondHeaderStringArr = headerString1.split("\\^");
			            
			            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow1.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(secondHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (Work obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_id_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_name());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSanctioned_year_fk());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRailway());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getExecuted_by());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSanctioned_estimated_cost());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getSanctioned_completion_cost());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCompleteion_period_months());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getAnticipated_cost());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProjected_completion());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCompletion_cost());

							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getYear_of_completion());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
						
			                rowNo++;
			            }
			            short rowNo2 = 1;
						
							 for (Work obj : revisionList) {
					                XSSFRow row = revisonSheet.createRow(rowNo2);
					                int b = 0;
					                
					                Cell cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getWork_id_fk());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getFinancial_year());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getPink_book_item_number());
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getLatest_revised_cost());
									
					/*cell1 = row.createCell(b++);
					cell1.setCellStyle(sectionStyle);
					cell1.setCellValue(obj.getRevision_unit());
						*/
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getYear_of_revision());
									
									
					                cell1 = row.createCell(b++);
									cell1.setCellStyle(sectionStyle);
									cell1.setCellValue(obj.getRevision_number());
									
									rowNo2++;
							    }
					       
						 
			            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
			            for(int columnIndex = 0; columnIndex < secondHeaderStringArr.length; columnIndex++) {
			            	revisonSheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "Work_"+dateFormat.format(date);
		                
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
			logger.error("exportWork : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	private String getCellDataType(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup
		//workbook.setForceFormulaRecalculation(true);
		try {
			CellType type = cell.getCellType();
			if (!StringUtils.isEmpty(cell)) {
			    switch (type) {
			        case BOOLEAN:
			            val = String.valueOf(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			        	val = String.valueOf(cell.getNumericCellValue());
			        	if(val.contains("E")){
			        		val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        	}
			       
			            break;
			        case STRING:
			        	try {  
			        		val = cell.getStringCellValue();
			        		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			        		Number number = format.parse(val);
			        		int d = number.intValue();
			        		val = String.valueOf(d);
			        		if(val.contains("E")){
			        			val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        		}
			        	  } catch(NumberFormatException e){  
			        		  val = cell.getStringCellValue();
			        	  }  
			            
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
		}catch(Exception e) {
			try {
				 val = cell.getStringCellValue();
			}catch(Exception e1) {
				val = String.valueOf(cell.getNumericCellValue());
			}
			
		}
	
		return val;
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
	
	
	
	
	
	
	
	
	