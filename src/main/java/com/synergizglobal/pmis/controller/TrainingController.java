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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.TrainingService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.Training;

@Controller
public class TrainingController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TrainingController.class);
	
	@Autowired
	TrainingService trainingService;


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
	
	
	@RequestMapping(value="/training",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView training(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.trainingGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("training : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-training", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingList(@ModelAttribute Training obj) {
		List<Training> trainingList = null;
		try {
			trainingList = trainingService.getTrainingList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingList : " + e.getMessage());
		}
		return trainingList;
	}
	

	@RequestMapping(value = "/ajax/getTrainingTypesFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingTypesList(@ModelAttribute Training obj) {
		List<Training> trainingTypesList = null;
		try {
			trainingTypesList = trainingService.getTrainingTypesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingTypesList : " + e.getMessage());
		}
		return trainingTypesList;
	}
	

	@RequestMapping(value = "/ajax/getTrainingCategorysFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingCategorysList(@ModelAttribute Training obj) {
		List<Training> trainingCategorysList = null;
		try {
			trainingCategorysList = trainingService.getTrainingCategorysList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingCategorysList : " + e.getMessage());
		}
		return trainingCategorysList;
	}
	

	@RequestMapping(value = "/ajax/getStatusFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getStatusList(@ModelAttribute Training obj) {
		List<Training> statusList = null;
		try {
			statusList = trainingService.getStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return statusList;
	}

	
	@RequestMapping(value = "/add-training-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addTrainingForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTrainingForm);
			model.addObject("action", "add");
			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);
			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);
			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);
			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);
			
		}catch (Exception e) {
				logger.error("addTrainingForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-training", method = {RequestMethod.POST})
	public ModelAndView getTraining(@ModelAttribute Training obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTrainingForm);
			model.addObject("action", "edit");
			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);
			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);
			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);
			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);
			Training trainingDetails = trainingService.getTraining(obj);
			model.addObject("trainingDetails", trainingDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getTraining : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/export-training", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportTraining(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Training dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.trainingGrid);
		List<Training> dataList = new ArrayList<Training>();
		try {
			view.setViewName("redirect:/documents");
			dataList =   trainingService.getTrainingList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("ID");
	            headingRow.createCell((short)1).setCellValue("Type");
	         	headingRow.createCell((short)2).setCellValue("Category");
	            headingRow.createCell((short)3).setCellValue("Title");
	            headingRow.createCell((short)4).setCellValue("Faculty");
	            headingRow.createCell((short)5).setCellValue("Start Date");
	            headingRow.createCell((short)6).setCellValue("End Date");
	            headingRow.createCell((short)7).setCellValue("Hours");
	            headingRow.createCell((short)8).setCellValue("Status");


	            short rowNo = 1;
	            for (Training obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getTraining_id());
	                row.createCell((short)1).setCellValue(obj.getTraining_type_fk());
	                row.createCell((short)2).setCellValue(obj.getTraining_category_fk());
	                row.createCell((short)3).setCellValue(obj.getTitle());
	                row.createCell((short)4).setCellValue(obj.getFaculty_name());
	                row.createCell((short)5).setCellValue(obj.getStart_time());
	                row.createCell((short)6).setCellValue(obj.getEnd_time());
	                row.createCell((short)7).setCellValue(obj.getHours());
	                row.createCell((short)8).setCellValue(obj.getStatus_fk());
	          
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Training_"+dateFormat.format(date);
                
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
