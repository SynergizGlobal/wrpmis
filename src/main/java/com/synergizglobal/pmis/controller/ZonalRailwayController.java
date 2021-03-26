package com.synergizglobal.pmis.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.ZonalRailwayService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.ZonalRailway;
import com.synergizglobal.pmis.model.ZonalsPaginationObject;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.ZonalRailway;

@Controller
public class ZonalRailwayController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ZonalRailwayController.class);
	
	@Autowired
	ZonalRailwayService service;
	


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
	@RequestMapping(value="/zonal-railway",method={RequestMethod.GET})
	public ModelAndView ZonalRailway(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.zonalRailwayGrid);
		try {
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("ZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-zonal-railway", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute ZonalRailway obj, HttpServletRequest request,
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

			List<ZonalRailway> zonalList = new ArrayList<ZonalRailway>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				zonalList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				zonalList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//zonalList = getListBasedOnSearchParameter(searchParameter,zonalList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ZonalsPaginationObject personJsonObject = new ZonalsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(zonalList);

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
	public int getTotalRecords(ZonalRailway obj, String searchParameter) {
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
	public List<ZonalRailway> createPaginationData(int startIndex, int offset,ZonalRailway obj, String searchParameter) {
		List<ZonalRailway> earthWorkList = null;
		try {
			earthWorkList = service.getZonalsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getWorksList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getProjectsList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getExecutionAgencyRailwayFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getExecutionAgencyRailwayList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayExecutionAgencyRailwayList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutionAgencyRailwayList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getSourceOfFundsFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getSourceOfFundsList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwaySourceOfFundsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSourceOfFundsList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getStatusList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForZonalRailwayForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getWorkListForZonalRailwayForm(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objsList = null;
		try {
			objsList = service.getWorkListForZonalRailwayForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForZonalRailwayForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getRailwayListForZonalRailwayForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getRailwayListForZonalRailwaysForm(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objsList = null;
		try {
			objsList = service.getRailwayListForZonalRailwaysForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRailwayListForZonalRailwaysForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-zonal-railway-form", method = {RequestMethod.GET})
	public ModelAndView addZonalRailwaytForm(@ModelAttribute ZonalRailway obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "add");
			
			List<ZonalRailway> projectsList = service.getProjectsListForZonalRailwayForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<ZonalRailway> worksList = service.getWorkListForZonalRailwayForm(obj);
			model.addObject("worksList", worksList);
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
			
		}catch (Exception e) {
				logger.error("addZonalRailwaytForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-zonal-railway", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getZonalRailwayForm(@ModelAttribute ZonalRailway obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "edit");
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			ZonalRailway zonalRailwayDetails = service.getZonalRailway(obj);
			model.addObject("zonalRailwayDetails", zonalRailwayDetails);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getZonalRailwayForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-zonal-railway", method = {RequestMethod.POST})
	public ModelAndView addZonalRailway(@ModelAttribute ZonalRailway obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway");
			obj.setAs_on_date(DateParser.parse(obj.getAs_on_date()));
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setExpected_finish(DateParser.parse(obj.getExpected_finish()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.addZonalRailway(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Zonal Railway is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Zonal Railway is failed. Try again.");
			logger.error("addZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-zonal-railway", method = {RequestMethod.POST})
	public ModelAndView updateZonalRailway(@ModelAttribute ZonalRailway obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway");
			obj.setAs_on_date(DateParser.parse(obj.getAs_on_date()));
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setExpected_finish(DateParser.parse(obj.getExpected_finish()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.updateZonalRailway(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Zonal Railway is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Zonal Railway is failed. Try again.");
			logger.error("updateZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-zonal-railway", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportZonalRailway(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute ZonalRailway zObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.zonalRailwayGrid);
		List<ZonalRailway> dataList = new ArrayList<ZonalRailway>();
		List<ZonalRailway> progressList = new ArrayList<ZonalRailway>();
		try {
			view.setViewName("redirect:/zonal-railway");
			dataList =   service.getZonalRailwayList(zObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet =  workBook.createSheet(WorkbookUtil.createSafeSheetName("Zonal_Contracts"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
		        headingRow.createCell((short)0).setCellValue("Contract ID");
		        headingRow.createCell((short)1).setCellValue("Work");
		        headingRow.createCell((short)2).setCellValue("Execution Agency");
	            headingRow.createCell((short)3).setCellValue("Sub Work");
	            headingRow.createCell((short)4).setCellValue("Source Of Funds");
	            headingRow.createCell((short)5).setCellValue("Sanction Cost");
	            headingRow.createCell((short)6).setCellValue("Latest Revised Cost");
	            headingRow.createCell((short)7).setCellValue("Cumulative Expenditure(Finacial Year)");
	            headingRow.createCell((short)8).setCellValue("Actual Start");
	            headingRow.createCell((short)9).setCellValue("Expected Finish");
	            headingRow.createCell((short)10).setCellValue("Actual Finish");
	            headingRow.createCell((short)11).setCellValue("Completion Cost");
	            headingRow.createCell((short)12).setCellValue("Status");
	            headingRow.createCell((short)13).setCellValue("As On Date");
	            headingRow.createCell((short)14).setCellValue("Responsible Person");

	            short rowNo = 1;
	            for (ZonalRailway obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getContract_id());
	                row.createCell((short)1).setCellValue(obj.getWork_id_fk()+"-"+obj.getWork_short_name());
	                row.createCell((short)2).setCellValue(obj.getExecution_agency_railway_fk()+"-"+obj.getRailway_name());
	                row.createCell((short)3).setCellValue(obj.getSub_work());
	                row.createCell((short)4).setCellValue(obj.getSource_of_funds());
	                row.createCell((short)5).setCellValue(obj.getSanction_cost());
	                row.createCell((short)6).setCellValue(obj.getLatest_revised_cost());
	                row.createCell((short)7).setCellValue(obj.getCumulative_expenditure_upto_last_finacial_year());
	                row.createCell((short)8).setCellValue(obj.getActual_start());
	                row.createCell((short)9).setCellValue(obj.getExpected_finish());
	                row.createCell((short)10).setCellValue(obj.getActual_finish());
	                row.createCell((short)11).setCellValue(obj.getCompletion_cost());
	                row.createCell((short)12).setCellValue(obj.getStatus_fk());
	                row.createCell((short)13).setCellValue(obj.getAs_on_date());
	                row.createCell((short)14).setCellValue(obj.getDesignation());
	          
	                rowNo++;
	            }
	           
	            XSSFSheet progressSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Zonal_Progress"));
		        workBook.setSheetOrder(progressSheet.getSheetName(), 1);
		        XSSFRow headingRow1 = progressSheet.createRow(0);
		        headingRow1.createCell((short)0).setCellValue("Contract ID");
		        headingRow1.createCell((short)1).setCellValue("Month");
	            headingRow1.createCell((short)2).setCellValue("Cum Actual Expenditure(cr)");
	         	headingRow1.createCell((short)3).setCellValue("Cum Planned Expenditure(%)");
	            headingRow1.createCell((short)4).setCellValue("Cum Actual Expenditure(cr)");
	            headingRow1.createCell((short)5).setCellValue("Cum Actual Expenditure(%)");
	            headingRow1.createCell((short)6).setCellValue("Cum Planned Physical Progress(%)");
	            headingRow1.createCell((short)7).setCellValue("Cum Actual Physical Progress(%)");
	            headingRow1.createCell((short)8).setCellValue("Progress");
	            headingRow1.createCell((short)9).setCellValue("Issue");
	            headingRow1.createCell((short)10).setCellValue("Assistance Required");
	            short rowNo2 = 1;
	        	for (ZonalRailway progress : dataList) { 
	        		String id = progress.getContract_id();
	        		progressList = service.getProgressList(id);
		           
		            for (ZonalRailway sObj : progressList) {
		                XSSFRow row2 = progressSheet.createRow(rowNo2);
		                row2.createCell((short)0).setCellValue(sObj.getContract_id());
		                row2.createCell((short)1).setCellValue(sObj.getMonth());
		                row2.createCell((short)2).setCellValue(sObj.getCum_actual_expenditure_fy_cr());
		                row2.createCell((short)3).setCellValue(sObj.getCum_planned_expenditure_per());
		                row2.createCell((short)4).setCellValue(sObj.getCum_actual_expenditure_cr());
		                row2.createCell((short)5).setCellValue(sObj.getCum_actual_expenditure_per());
		                row2.createCell((short)6).setCellValue(sObj.getCum_planned_physical_progress_per());
		                row2.createCell((short)7).setCellValue(sObj.getCum_actual_physical_progress_per());
		                row2.createCell((short)8).setCellValue(sObj.getProgress());
		                row2.createCell((short)9).setCellValue(sObj.getIssue());
		                row2.createCell((short)10).setCellValue(sObj.getAssistance_required());
		          
		                rowNo2++;
		            }
	        	}
	        	for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
		            	//sheet.autoSizeColumn(columnIndex);
		        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < progressList.size(); columnIndex++) {
	        		//revisionSheet.autoSizeColumn(columnIndex);
	        		progressSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Zonal_Railway_"+dateFormat.format(date);
                
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