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
	ContractService contractservice;
	
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
			List<Contract> contractList = contractservice.contractList(obj);
			model.addObject("contractList", contractList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-safetyequipment", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<SafetyEquipment> getDesigns(@ModelAttribute SafetyEquipment obj) {
		List<SafetyEquipment> safetyEquipment = null;
		try {
			safetyEquipment = service.getSafetyEquipment(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return safetyEquipment;
	}
	
	@RequestMapping(value = "/add-safetyequipment-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addSafetyEquipmentForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSafetyEquipment);
			model.addObject("action", "add");
			List<Project> projectsList = service.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
		}catch (Exception e) {
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return model;
     }

	@RequestMapping(value = "/get-safetyEquipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafetyEquipmentForm(@ModelAttribute SafetyEquipment obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditSafetyEquipment);
			model.addObject("action", "edit");
			List<Project> projectsList = service.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			SafetyEquipment safetyDetails = service.getSafetyDetails(obj);
			model.addObject("safetyDetails", safetyDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return model;
     }
	
	@RequestMapping(value = "/add-safetyequipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addSafetyEquipment(@ModelAttribute SafetyEquipment obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-equipment");
			obj.setValidity_date(DateParser.parse(obj.getValidity_date()));
			boolean flag =  service.addSafetyEquipment(obj);
			if(flag == true) {
				
				attributes.addFlashAttribute("success", "SafetyEquipment Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding SafetyEquipment is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding SafetyEquipment is failed. Try again.");
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-safetyequipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateSafetyEquipment(@ModelAttribute SafetyEquipment obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-equipment");
			boolean flag =  service.updateSafetyEquipment(obj);
			if(flag == true) {
				attributes.addFlashAttribute("success", "SafetyEquipment Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating SafetyEquipment is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating SafetyEquipment is failed. Try again.");
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-safetyequipment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteSafetyEquipment(@ModelAttribute SafetyEquipment obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/safety-equipment");
			boolean flag =  service.deleteSafetyEquipment(obj);
		}catch (Exception e) {
			logger.info("SafetyEquipment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-safetyequipment", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafetyEquipment(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute SafetyEquipment sObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.safetyEquipmentGrid);
		List<SafetyEquipment> dataList = new ArrayList<SafetyEquipment>();
		try {
			view.setViewName("redirect:/contractor");
			dataList =  service.getSafetyEquipment(sObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet = workBook.createSheet();
		        XSSFRow headingRow = sheet.createRow(0);
	            headingRow.createCell((short)0).setCellValue("Contract");
	            headingRow.createCell((short)1).setCellValue("Equipment No");
	         	headingRow.createCell((short)2).setCellValue("Equipment Details");
	            headingRow.createCell((short)3).setCellValue("Validity of Equipm");
	            headingRow.createCell((short)4).setCellValue("Remarks");
	            short rowNo = 1;
	            for (SafetyEquipment obj : dataList) {
	                XSSFRow row = sheet.createRow(rowNo);
	                row.createCell((short)0).setCellValue(obj.getContract_id_fk());
	                row.createCell((short)1).setCellValue(obj.getSafety_equipment_number());
	                row.createCell((short)2).setCellValue(obj.getSafety_equipment_detail());
	                row.createCell((short)3).setCellValue(obj.getValidity_date());
	                row.createCell((short)4).setCellValue(obj.getRemarks());
	                rowNo++;
	            }DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
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
			// TODO: handle exception
		}
	}
}
