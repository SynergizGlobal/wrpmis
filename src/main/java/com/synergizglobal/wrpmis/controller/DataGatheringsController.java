package com.synergizglobal.wrpmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
import com.synergizglobal.wrpmis.Iservice.BudgetService;
import com.synergizglobal.wrpmis.Iservice.DataGatheringsService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Budget;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.ContractPaginationObject;
import com.synergizglobal.wrpmis.model.DataGathering;
import com.synergizglobal.wrpmis.model.DataGatheringPaginationObject;
import com.synergizglobal.wrpmis.model.Document;

@Controller
public class DataGatheringsController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DataGatheringsController.class);
	
	@Autowired
	DataGatheringsService dataGatheringsService;
	

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
	
	@RequestMapping(value="/data-gathering",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  dataGatherings(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.dataGatheringGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("dataGatherings : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/get-data-gathering-list", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute DataGathering obj, HttpServletRequest request,
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

			List<DataGathering> dataGatheringList = new ArrayList<DataGathering>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				dataGatheringList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				dataGatheringList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//dataGatheringList = getListBasedOnSearchParameter(searchParameter,dataGatheringList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			DataGatheringPaginationObject personJsonObject = new DataGatheringPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(dataGatheringList);

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
	public int getTotalRecords(DataGathering obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = dataGatheringsService.getTotalRecords(obj, searchParameter);
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
	public List<DataGathering> createPaginationData(int startIndex, int offset,DataGathering obj, String searchParameter) {
		List<DataGathering> objList = null;
		try {
			objList = dataGatheringsService.getDataGatheringList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getStatusFilterListInDataGathering", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering> getStatusList(@ModelAttribute DataGathering obj) {
		List<DataGathering> statusList = null;
		try {
			statusList = dataGatheringsService.getDataGatherigsStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDataGatherigsStatusList : " + e.getMessage());
		}
		return statusList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInDataGathering", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering>getProjectsList(@ModelAttribute DataGathering obj) {
		List<DataGathering> projectsList = null;
		try {
			projectsList = dataGatheringsService.getDataGatherigsProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDataGatherigsProjectPriorityList : " + e.getMessage());
		}
		return projectsList;
	}
	

	
	@RequestMapping(value = "/ajax/getContractsFilterListInDataGathering", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering>getContractsList(@ModelAttribute DataGathering obj) {
		List<DataGathering> contractsList = null;
		try {
			contractsList = dataGatheringsService.getDataGatherigsContractsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDataGatherigsContractsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/add-data-gathering-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDataGatherigForm(@ModelAttribute DataGathering obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDataGatheringForm);
			model.addObject("action", "add");
			List<DataGathering> statusList = dataGatheringsService.getStatusList();
			
			model.addObject("statusList", statusList);
			List<DataGathering> priorityList = dataGatheringsService.getPriorityList();
			model.addObject("priorityList", priorityList);
			
			List<DataGathering> projectsList = dataGatheringsService.getProjectsListForDataGatheringForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<DataGathering> formWorksList = dataGatheringsService.getWorkListForDataGatheringForm(obj);
			model.addObject("formWorksList", formWorksList);
			
			List<DataGathering> contractsList = dataGatheringsService.getContractsListForDataGatheringForm(obj);
			model.addObject("contractsList", contractsList);
			
		}catch (Exception e) {
				logger.error("addDataGatherigForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/getProjectsListForDataGatheringForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering> getProjectsListForDataGatheringForm(@ModelAttribute DataGathering obj) {
		List<DataGathering> objsList = null;
		try {
			objsList = dataGatheringsService.getProjectsListForDataGatheringForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForDataGatheringForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForDataGatheringForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering> getWorkListForDataGatheringForm(@ModelAttribute DataGathering obj) {
		List<DataGathering> objsList = null;
		try {
			objsList = dataGatheringsService.getWorkListForDataGatheringForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForDataGatheringForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForDataGatheringForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering> getContractsListForDataGatheringForm(@ModelAttribute DataGathering obj) {
		List<DataGathering> objsList = null;
		try {
			objsList = dataGatheringsService.getContractsListForDataGatheringForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDataGatheringForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/get-data-gathering", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDataGatherigForm(@ModelAttribute DataGathering obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDataGatheringForm);
			model.addObject("action", "edit");
			List<DataGathering> statusList = dataGatheringsService.getStatusList();
			model.addObject("statusList", statusList);
			
			List<DataGathering> priorityList = dataGatheringsService.getPriorityList();
			model.addObject("priorityList", priorityList);
			
			DataGathering dataGatheringDetails = dataGatheringsService.getDataGathering(obj);
			model.addObject("dataGatheringDetails", dataGatheringDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDataGathering : " + e.getMessage());
		}
		return model;
	 }

	@RequestMapping(value = "/get-data-gathering/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDataGatherigFormWithId(@ModelAttribute DataGathering obj ,@PathVariable("id") String id,HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDataGatheringForm);
			model.addObject("action", "edit");
			List<DataGathering> statusList = dataGatheringsService.getStatusList();
			model.addObject("statusList", statusList);
			
			List<DataGathering> priorityList = dataGatheringsService.getPriorityList();
			model.addObject("priorityList", priorityList);
			
			DataGathering dataGatheringDetails = dataGatheringsService.getDataGathering(obj);
			model.addObject("dataGatheringDetails", dataGatheringDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDataGathering : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-data-gathering", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDataGathering(@ModelAttribute DataGathering obj,RedirectAttributes attributes,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			//obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));
			obj.setStart_date(DateParser.parse(obj.getStart_date()));
			obj.setFinish_date(DateParser.parse(obj.getFinish_date()));

			boolean flag =  dataGatheringsService.addDataGathering(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "DataGathering Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding DataGathering is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding DataGathering is failed. Try again.");
			logger.error("addDataGathering : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/update-data-gathering", method = {RequestMethod.POST})
	public ModelAndView updateDataGathering(@ModelAttribute DataGathering obj,RedirectAttributes attributes,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			//obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));
			obj.setStart_date(DateParser.parse(obj.getStart_date()));
			obj.setFinish_date(DateParser.parse(obj.getFinish_date()));

			boolean flag =  dataGatheringsService.updateDataGathering(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "DataGathering Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating DataGathering is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating DataGathering is failed. Try again.");
			logger.error("updateDataGathering : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-data-gathering", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteDataGathering(@ModelAttribute DataGathering obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering");
			boolean flag =  dataGatheringsService.deleteDataGathering(obj);
		}catch (Exception e) {
			logger.error("deleteDataGathering : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-data-gathering", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDataGatherig(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute DataGathering dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.dataGatheringGrid);
		List<DataGathering> dataList = new ArrayList<DataGathering>();
		try {
			view.setViewName("redirect:/data-gathering");
			dataList =  dataGatheringsService.getDataGatheringsList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Data_Gathering"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
		        headingRow.createCell((short)0).setCellValue("ID");
		        headingRow.createCell((short)1).setCellValue("Project");
	            headingRow.createCell((short)2).setCellValue("Work");
	            headingRow.createCell((short)3).setCellValue("Contract");
	            headingRow.createCell((short)4).setCellValue("Target Date");
	            headingRow.createCell((short)5).setCellValue("Start Date");
	            headingRow.createCell((short)6).setCellValue("Finish Date");
	            headingRow.createCell((short)7).setCellValue("Status");
	            headingRow.createCell((short)8).setCellValue("Remarks");

	            short rowNo = 1;
	            for (DataGathering obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getId());
	                row.createCell((short)1).setCellValue(obj.getProject_id_fk() +" - "+ obj.getProject_name());
	                row.createCell((short)2).setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_short_name());
	                row.createCell((short)3).setCellValue(obj.getContract_id_fk()+" - "+ obj.getContract_short_name());
	                row.createCell((short)4).setCellValue(obj.getTarget_date());
	                row.createCell((short)5).setCellValue(obj.getStart_date());
	                row.createCell((short)6).setCellValue(obj.getFinish_date());
	                row.createCell((short)7).setCellValue(obj.getStatus_fk());
	                row.createCell((short)8).setCellValue(obj.getRemarks());
	          
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//sheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Data_Gathering_"+dateFormat.format(date);
                
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
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
