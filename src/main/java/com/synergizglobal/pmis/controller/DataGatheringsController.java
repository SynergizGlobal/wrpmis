package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.DataGatheringsService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.DataGathering;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.DataGathering;
import com.synergizglobal.pmis.model.DataGathering;

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
	
	@RequestMapping(value = "/ajax/get-data-gathering-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering> getDataGatherings(@ModelAttribute DataGathering obj) {
		List<DataGathering> dataGatheringsList = null;
		try {
			dataGatheringsList = dataGatheringsService.getDataGatheringsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDataGatheringsList : " + e.getMessage());
		}
		return dataGatheringsList;
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
	
	@RequestMapping(value = "/ajax/getProjectPriorityFilterListInDataGathering", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DataGathering>getProjectPriorityList(@ModelAttribute DataGathering obj) {
		List<DataGathering> projectPriorityList = null;
		try {
			projectPriorityList = dataGatheringsService.getDataGatherigsProjectPriorityList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDataGatherigsProjectPriorityList : " + e.getMessage());
		}
		return projectPriorityList;
	}
	
	@RequestMapping(value = "/add-data-gathering-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDataGatherigForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDataGatheringForm);
			model.addObject("action", "add");
			List<DataGathering> statusList = dataGatheringsService.getStatusList();
			model.addObject("statusList", statusList);
			List<DataGathering> priorityList = dataGatheringsService.getPriorityList();
			model.addObject("priorityList", priorityList);
			List<DataGathering> worksList = dataGatheringsService.getWorksList();
			model.addObject("worksList", worksList);
			
		}catch (Exception e) {
				logger.error("addDataGatherigForm : " + e.getMessage());
		}
		return model;
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
			List<DataGathering> worksList = dataGatheringsService.getWorksList();
			model.addObject("worksList", worksList);
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
	public ModelAndView addDataGathering(@ModelAttribute DataGathering obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering");
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
	public ModelAndView updateDataGathering(@ModelAttribute DataGathering obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/data-gathering");
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
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
		        headingRow.createCell((short)0).setCellValue("Project Priority");
	            headingRow.createCell((short)1).setCellValue("Work");
	            headingRow.createCell((short)2).setCellValue("Target Date");
	            headingRow.createCell((short)3).setCellValue("Start Date");
	            headingRow.createCell((short)4).setCellValue("Finish Date");
	            headingRow.createCell((short)5).setCellValue("Status");

	            short rowNo = 1;
	            for (DataGathering obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getProject_priority_fk());
	                row.createCell((short)1).setCellValue(obj.getWork_name());
	                row.createCell((short)2).setCellValue(obj.getTarget_date());
	                row.createCell((short)3).setCellValue(obj.getStart_date());
	                row.createCell((short)4).setCellValue(obj.getFinish_date());
	                row.createCell((short)5).setCellValue(obj.getStatus_fk());
	          
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
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
