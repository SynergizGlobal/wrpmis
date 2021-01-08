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
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import org.springframework.http.MediaType;

import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.Iservice.SafetyEquipmentService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.SafetyEquipment;


@Controller
public class SafetyEquipmentController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SafetyEquipmentController.class);
		
	@Autowired
	SafetyEquipmentService service;
	
	
	@Autowired
	DesignService designService;
	
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
		
	@RequestMapping(value="/safety-equipment",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView safetyEquipment(HttpSession session,@ModelAttribute Contract obj){
		ModelAndView model = new ModelAndView(PageConstants.safetyEquipmentGrid);
		try {
			List<SafetyEquipment> contractList = service.contractListFilterInSafetyEquipment();
			model.addObject("contractList", contractList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("safetyEquipment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-safety-equipments", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SafetyEquipment> getSafetyEquipments(@ModelAttribute SafetyEquipment obj) {
		List<SafetyEquipment> safetyEquipment = null;
		List<SafetyEquipment> safetyEquipmentExportList = null;
		try {
			safetyEquipment = service.getSafetyEquipment(obj);
			safetyEquipmentExportList = service.getSafetyEquipmentExportList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSafetyEquipments : " + e.getMessage());
		}
		return safetyEquipment;
	}
	
	@RequestMapping(value = "/add-safety-equipment-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addSafetyEquipmentForm(@ModelAttribute SafetyEquipment obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSafetyEquipment);
			model.addObject("action", "add");
			List<SafetyEquipment> projectsList = service.getProjectsListForSafetyEquipmentForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<SafetyEquipment> worksList = service.getWorkListForSafetyEquipmentForm(obj);
			model.addObject("worksList", worksList);
			
			List<SafetyEquipment> contractsList = service.getContractsListForSafetyEquipmentForm(obj);
			model.addObject("contractsList", contractsList);
		}catch (Exception e) {
			logger.error("addSafetyEquipmentForm : " + e.getMessage());
		}
		return model;
     }
	
	@RequestMapping(value = "/ajax/getProjectsListForSafetyEquipmentForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SafetyEquipment> getProjectsListForSafetyEquipmentForm(@ModelAttribute SafetyEquipment obj) {
		List<SafetyEquipment> objsList = null;
		try {
			objsList = service.getProjectsListForSafetyEquipmentForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForSafetyEquipmentForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForSafetyEquipmentForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SafetyEquipment> getWorkListForSafetyEquipmentForm(@ModelAttribute SafetyEquipment obj) {
		List<SafetyEquipment> objsList = null;
		try {
			objsList = service.getWorkListForSafetyEquipmentForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForSafetyEquipmentForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForSafetyEquipmentForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SafetyEquipment> getContractsListForSafetyEquipmentForm(@ModelAttribute SafetyEquipment obj) {
		List<SafetyEquipment> objsList = null;
		try {
			objsList = service.getContractsListForSafetyEquipmentForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForSafetyEquipmentForm : " + e.getMessage());
		}
		return objsList;
	}

	@RequestMapping(value = "/get-safety-equipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafetyEquipment(@ModelAttribute SafetyEquipment obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSafetyEquipment);
			model.addObject("action", "edit");
			
			SafetyEquipment safetyEquipmentDetails = service.getSafetyEquipmentDetails(obj);
			model.addObject("safetyEquipmentDetails", safetyEquipmentDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSafetyEquipment : " + e.getMessage());
		}
		return model;
     }
	
	@RequestMapping(value = "/add-safety-equipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addSafetyEquipment(@ModelAttribute SafetyEquipment obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-equipment");
			obj.setValidity_date(DateParser.parse(obj.getValidity_date()));
			boolean flag =  service.addSafetyEquipment(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "SafetyEquipment Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding SafetyEquipment is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding SafetyEquipment is failed. Try again.");
			logger.error("addSafetyEquipment : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-safety-equipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateSafetyEquipment(@ModelAttribute SafetyEquipment obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-equipment");
			obj.setValidity_date(DateParser.parse(obj.getValidity_date()));
			boolean flag =  service.updateSafetyEquipment(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "SafetyEquipment Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating SafetyEquipment is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating SafetyEquipment is failed. Try again.");
			logger.error("updateSafetyEquipment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-safety-equipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteSafetyEquipment(@ModelAttribute SafetyEquipment obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-equipment");
			boolean flag =  service.deleteSafetyEquipment(obj);
		}catch (Exception e) {
			logger.error("deleteSafetyEquipment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-safety-equipment", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafetyEquipment(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute SafetyEquipment sObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.safetyEquipmentGrid);
		List<SafetyEquipment> dataList = new ArrayList<SafetyEquipment>();
		try {
			view.setViewName("redirect:/safety-equipment");
			dataList =  service.getSafetyEquipmentExportList(sObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet =  workBook.createSheet(WorkbookUtil.createSafeSheetName("Safety_Equipment"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        XSSFRow headingRow = sheet.createRow(0);
		        headingRow.createCell((short)0).setCellValue("Safety Equipment ID");
	            headingRow.createCell((short)1).setCellValue("Contract");
	            headingRow.createCell((short)2).setCellValue("Equipment No");
	         	headingRow.createCell((short)3).setCellValue("Equipment Details");
	            headingRow.createCell((short)4).setCellValue("Validity of Equipment");
	            headingRow.createCell((short)5).setCellValue("Inspecting Official");
	            headingRow.createCell((short)6).setCellValue("Last Inspection Date");
	            headingRow.createCell((short)7).setCellValue("Next Inspection Date");
	            headingRow.createCell((short)8).setCellValue("Remarks");
	            short rowNo = 1;
	            for (SafetyEquipment obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getSafety_equipment_id());
	                row.createCell((short)1).setCellValue(obj.getContract_id_fk()+" - "+ obj.getContract_name());
	                row.createCell((short)2).setCellValue(obj.getSafety_equipment_number());
	                row.createCell((short)3).setCellValue(obj.getSafety_equipment_detail());
	                row.createCell((short)4).setCellValue(obj.getValidity_date());
	                row.createCell((short)5).setCellValue(obj.getInspecting_official());
	                row.createCell((short)6).setCellValue(obj.getLast_inspection_date());
	                row.createCell((short)7).setCellValue(obj.getNext_inspection_due());
	                row.createCell((short)8).setCellValue(obj.getRemarks());
	                rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < dataList.size(); columnIndex++) {
	            	//sheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "SafetyEquipment_"+dateFormat.format(date);
                
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
			
		}
	}
}
