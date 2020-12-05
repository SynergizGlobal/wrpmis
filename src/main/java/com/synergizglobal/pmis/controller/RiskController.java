package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.RiskService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.Work;

@Controller
public class RiskController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskController.class);
	
	@Autowired
	RiskService riskService;
	
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
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/risk",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView risk(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.riskGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("risk : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-risk-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskList(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			riskList = riskService.getRiskList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRiskList : " + e.getMessage());
		}
		return riskList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getWorksList(@ModelAttribute Risk obj) {
		List<Risk> worksList = null;
		try {
			worksList = riskService.getRisktWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getAreasFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAreasList(@ModelAttribute Risk obj) {
		List<Risk> areaList = null;
		try {
			areaList = riskService.getRiskAreasList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAreasList : " + e.getMessage());
		}
		return areaList;
	}
	
	@RequestMapping(value = "/ajax/getPrioritiesFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getPrioritiesList(@ModelAttribute Risk obj) {
		List<Risk> priotityList = null;
		try {
			priotityList = riskService.getRiskPriotityList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPrioritiesList : " + e.getMessage());
		}
		return priotityList;
	}
	
	@RequestMapping(value = "/ajax/getClassificationsFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getClassificationsList(@ModelAttribute Risk obj) {
		List<Risk> clasificationList = null;
		try {
			clasificationList = riskService.getRiskClassificationsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getClassificationsList : " + e.getMessage());
		}
		return clasificationList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getResponsiblePersonsList(@ModelAttribute Risk obj) {
		List<Risk> responsiblePersons = null;
		try {
			responsiblePersons = riskService.getRiskResponsiblePersonsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePersonsList : " + e.getMessage());
		}
		return responsiblePersons;
	}
	@RequestMapping(value = "/ajax/getAssessmentDatesFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAssessmentDatesFilterList(@ModelAttribute Risk obj) {
		List<Risk> assesmentDates = null;
		try {
			assesmentDates = riskService.getAssessmentDatesFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAssessmentDatesFilterList : " + e.getMessage());
		}
		return assesmentDates;
	}
	
	@RequestMapping(value = "/add-risk-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addRiskForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRiskForm);
			model.addObject("action", "add");
		
			List<Risk> projectsList = riskService.getProjectList();
			model.addObject("projectsList", projectsList);
			List<Risk> prioritiesList = riskService.getPrioritiesList();
			model.addObject("prioritiesList", prioritiesList);
			List<Risk> areasList = riskService.getAreasList();
			model.addObject("areasList", areasList);
			List<Risk> subAreasList = riskService.getSubAreasList();
			model.addObject("subAreasList", subAreasList);
			
		}catch (Exception e) {
				logger.error("addRiskForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/getSubAreasList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getSubAreasList(@ModelAttribute Risk obj){
		List<Risk> subAreas = null;
		try{
			subAreas = riskService.getSubAreasList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getSubAreasList() : "+e.getMessage());
		}
		return subAreas;
	}
	
	@RequestMapping(value = "/ajax/getAreasList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAreaList(@ModelAttribute Risk obj){
		List<Risk> areasList = null;
		try{
			areasList = riskService.getAreaList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAreasList() : "+e.getMessage());
		}
		return areasList;
	}
	
	@RequestMapping(value = "/get-risk", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRisk(@ModelAttribute Risk obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRiskForm);
			model.addObject("action", "edit");
			List<Risk> projectsList = riskService.getProjectList();
			model.addObject("projectsList", projectsList);
			List<Risk> prioritiesList = riskService.getPrioritiesList();
			model.addObject("prioritiesList", prioritiesList);
			List<Risk> areasList = riskService.getAreasList();
			model.addObject("areasList", areasList);
			List<Risk> subAreasList = riskService.getSubAreasList();
			model.addObject("subAreasList", subAreasList);
			Risk riskDetails = riskService.getRiskDetails(obj);
			model.addObject("riskDetails", riskDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRisk : " + e.getMessage());
		}
		return model;
     }
	
	@RequestMapping(value = "/add-risk", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRisk(@ModelAttribute Risk obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			model.setViewName("redirect:/risk");
			
			obj.setDate_of_identification(DateParser.parseDateTime(obj.getDate_of_identification()));
			obj.setDate(DateParser.parse(obj.getDate()));
			obj.setAtr_date(DateParser.parse(obj.getAtr_date()));
			
			boolean flag =  riskService.addRisk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Risk is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Risk is failed. Try again.");
			logger.error("addRisk : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-risk", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRisk(@ModelAttribute Risk obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk");
			obj.setDate_of_identification(DateParser.parseDateTime(obj.getDate_of_identification()));
			obj.setDate(DateParser.parse(obj.getDate()));
			obj.setAtr_date(DateParser.parse(obj.getAtr_date()));
			
			boolean flag =  riskService.updateRisk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Risk is failed. Try again.");
			logger.error("updateRisk : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/upload-risk", method = {RequestMethod.POST})
	public ModelAndView uploadRisk(@ModelAttribute Risk risk,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/risk");
			
			if(!StringUtils.isEmpty(risk.getRiskFile())){
				MultipartFile multipartFile = risk.getRiskFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet risksDrawingsSheet = workbook.getSheetAt(2);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = risksDrawingsSheet.getRow(1);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getRiskFileFormat();	
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
						
						int[] arr = uploadRisks(risk,userId,userName);
						if(arr[0] == 1) {
							attributes.addFlashAttribute("updateSuccess", arr[0] + " Risk updated successfully.");
						}else {
							attributes.addFlashAttribute("updateSuccess", arr[0] + " Risks updated successfully.");

						}
						if(arr[1] == 1) {
							attributes.addFlashAttribute("success", arr[1] + " Risk added successfully.");
						}else {
							attributes.addFlashAttribute("success", arr[1] + " Risks added successfully.");
						}
					}
					workbook.close();
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

	private int[] uploadRisks(Risk obj, String userId, String userName)  throws Exception{
		Risk risk = null;
		List<Risk> risksList = new ArrayList<Risk>();
		
		Writer w = null;
		int[] arr = null ;
		try {	
			MultipartFile excelfile = obj.getRiskFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet risksDrawingsSheet = workbook.getSheetAt(2);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 2; i <= risksDrawingsSheet.getLastRowNum();i++){
						XSSFRow row = risksDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						risk = new Risk();
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setWork_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setRisk_id(val);}
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setOwner(val);}
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setArea(val);}	
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setSub_area_fk(val);}					
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setDate(val);}								
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setProbability(val);}										
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setImpact(val);}
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setMitigation_plan(val);}
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setPriority_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { risk.setResponsible_person(val);}
							
							
							
							risk.setDate(DateParser.parse(risk.getDate()));
						
						}
						List<Risk> pObjList = new ArrayList<Risk>();
						
							XSSFSheet riskRevisionsSheet = workbook.getSheetAt(3);
							for(int j = 1; j <= riskRevisionsSheet.getLastRowNum();j++){
								XSSFRow row2 = riskRevisionsSheet.getRow(j);
								// Sets the Read data to the model class
								Risk pObj = new Risk();
								if(!StringUtils.isEmpty(row2)) {
									val = formatter.formatCellValue(row2.getCell(0)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setRisk_id(val);}
									
									val = formatter.formatCellValue(row2.getCell(1)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setDate(val);}
									
									val = formatter.formatCellValue(row2.getCell(2)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setAtr_date(val);}
									
									val = formatter.formatCellValue(row2.getCell(3)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setAction_taken(val);}
									
									pObj.setDate(DateParser.parse(pObj.getDate()));
									pObj.setAtr_date(DateParser.parse(pObj.getAtr_date()));
									
								}
								if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getRisk_id())
										&& pObj.getRisk_id().equals(risk.getRisk_id()))
								pObjList.add(pObj);
							}
							risk.setRisks(pObjList);
						
						
						boolean flag = risk.checkNullOrEmpty();
						
						if(!flag) {
							risksList.add(risk);
						}
					}
					
					if(!risksList.isEmpty() && risksList != null){
						arr  = riskService.uploadRisks(risksList);
					}
				}
				workbook.close();
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
		
		return arr;
	}
}
