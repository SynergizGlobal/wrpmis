package com.synergizglobal.pmis.webview.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Risk;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewRiskAssessmentController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewRiskAssessmentController.class);
	
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
	
	@RequestMapping(value="/risk-assessment",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskAssessment(@ModelAttribute Risk obj,HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.riskATRGrid);
		try {
			List<Risk> worksList = riskService.getWorksList(obj);
			model.addObject("worksList", worksList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskAssessment : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/upload-risk-assessment", method = {RequestMethod.POST})
	public ModelAndView uploadRiskAssessment(@ModelAttribute Risk risk,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			//model.setViewName("redirect:/risk");
			model.setViewName("redirect:/mobileappwebview/risk-assessment");
			if(!StringUtils.isEmpty(risk.getRiskAssessmentFile())){
				MultipartFile multipartFile = risk.getRiskAssessmentFile();
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
							//if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									System.out.println(columnName + " = " + fileFormat.get(i));
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
										
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		return model;
				                	}
								}
							/*}else{
								attributes.addFlashAttribute("error",uploadformatError);
								return model;
							}*/
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						
						int[] arr = uploadRiskAssessment(risk,userId,userName);
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

	private int[] uploadRiskAssessment(Risk obj, String userId, String userName)  throws Exception{
		Risk risk = null;
		List<Risk> risksList = new ArrayList<Risk>();
		
		Writer w = null;
		int[] arr = null ;
		try {	
			MultipartFile excelfile = obj.getRiskAssessmentFile();
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
					
					String sub_work = null,item_no = null,risk_id = null,owner = null,risk_area_fk = null,risk_sub_area_fk = null,
							date = null,probability = null,impact = null,mitigation_plan = null,priority = null,
							responsible_person = null;
					String risk_base_text = "";
					for(int i = 2; i <= risksDrawingsSheet.getLastRowNum();i++){
						
						XSSFRow row = risksDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						risk = new Risk();
						String val = null;
						
						if(!StringUtils.isEmpty(row)) {	
							String item_no_temp = null;
							Cell cell = row.getCell(1);
							if(!StringUtils.isEmpty(cell)) {
								item_no_temp = formatter.formatCellValue(cell).trim();
							}
							
							if(!StringUtils.isEmpty(item_no_temp) && !item_no_temp.equals("null")) {
								
								risk.setWork_id_fk(obj.getWork_id_fk());
								//System.out.println(i + " = "+ val);
								
								String tempVal = formatter.formatCellValue(row.getCell(0)).trim();
								int count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									sub_work = getCellDataType2(workbook,row.getCell(0));
								}								
								if(!StringUtils.isEmpty(sub_work)) { 
									String tempSubWork = sub_work.replaceAll("\\&", "and");
									risk.setSub_work(tempSubWork);
								}else if(!StringUtils.isEmpty(obj.getWork_short_name())){
									String tempSubWork = obj.getWork_short_name().replaceAll("\\&", "and");
									risk.setSub_work(tempSubWork);
								}	
								
								//val = getCellDataType2(workbook,row.getCell(1));
								tempVal = formatter.formatCellValue(row.getCell(1)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									item_no = getCellDataType2(workbook,row.getCell(1));
								}
								if(!StringUtils.isEmpty(item_no)) { risk.setItem_no(item_no);}								
								
								//val = getCellDataType2(workbook,row.getCell(2));
								tempVal = formatter.formatCellValue(row.getCell(2)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									owner = getCellDataType2(workbook,row.getCell(2));
								}
								if(!StringUtils.isEmpty(owner)) { risk.setOwner(owner);}
								
								//val = getCellDataType2(workbook,row.getCell(3));
								tempVal = formatter.formatCellValue(row.getCell(3)).trim();	
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									risk_area_fk = getCellDataType2(workbook,row.getCell(3));
								}
								if(!StringUtils.isEmpty(risk_area_fk)) { risk.setRisk_area_fk(risk_area_fk);}	
								
								//val = getCellDataType2(workbook,row.getCell(4));
								tempVal = formatter.formatCellValue(row.getCell(4)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									risk_sub_area_fk = getCellDataType2(workbook,row.getCell(4));
								}
								if(!StringUtils.isEmpty(risk_sub_area_fk)) { risk.setSub_area_fk(risk_sub_area_fk);}					
								
								//val = getCellDataType2(workbook,row.getCell(5));
								tempVal = formatter.formatCellValue(row.getCell(5)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									date = getCellDataType2(workbook,row.getCell(5));
								}
								if(!StringUtils.isEmpty(date)) { risk.setDate(date);}								
								
								//val = getCellDataType2(workbook,row.getCell(6));
								tempVal = formatter.formatCellValue(row.getCell(6)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									probability = getCellDataType2(workbook,row.getCell(6));
								}
								if(!StringUtils.isEmpty(probability)) { risk.setProbability(probability);}										
								
								//val = getCellDataType2(workbook,row.getCell(7));
								tempVal = formatter.formatCellValue(row.getCell(7)).trim();	
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									impact = getCellDataType2(workbook,row.getCell(7));
								}
								if(!StringUtils.isEmpty(impact)) { risk.setImpact(impact);}
								
								val = getCellDataType2(workbook,row.getCell(8));
								if(!StringUtils.isEmpty(val)) { risk.setRisk_rating(val);}
								
								val = getCellDataType2(workbook,row.getCell(9));
								if(!StringUtils.isEmpty(val)) { risk.setClassification(val);}
								
								val = getCellDataType2(workbook,row.getCell(10));
								if(!StringUtils.isEmpty(val)) { risk.setStatus(val);}
								

								//val = getCellDataType2(workbook,row.getCell(11));
								tempVal = formatter.formatCellValue(row.getCell(11)).trim();								
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									priority = getCellDataType2(workbook,row.getCell(11));
								}
								if(!StringUtils.isEmpty(priority)) { risk.setPriority_fk(priority);}
								
								//val = getCellDataType2(workbook,row.getCell(12));
								tempVal = formatter.formatCellValue(row.getCell(12)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									mitigation_plan = getCellDataType2(workbook,row.getCell(12));
								}
								if(!StringUtils.isEmpty(mitigation_plan)) { risk.setMitigation_plan(mitigation_plan);}
								
								
								//val = getCellDataType2(workbook,row.getCell(13));
								tempVal = formatter.formatCellValue(row.getCell(13)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									responsible_person = getCellDataType2(workbook,row.getCell(13));
								}								
								if(!StringUtils.isEmpty(responsible_person)) { risk.setResponsible_person(responsible_person);}									
								
								//val = getCellDataType2(workbook,row.getCell(16));
								tempVal = formatter.formatCellValue(row.getCell(16)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									if(!StringUtils.isEmpty(tempVal)) {
										risk_base_text = getCellDataType2(workbook,row.getCell(16));
										risk_id = risk_base_text + "-Risk-"+(i-1);
									}else {
										risk_id = risk_base_text + "-Risk-"+(i-1);
									}
								}
								if(!StringUtils.isEmpty(risk_id)) { risk.setRisk_id(risk_id);}
								
								
								risk.setDate(DateParser.parse(risk.getDate()));
								
								boolean flag = risk.checkNullOrEmpty();
								
								if(!flag) {
									risksList.add(risk);
								}
								
							}
						}						
						
					}
					if(!risksList.isEmpty()){
						arr  = riskService.uploadRiskAssessments(risksList);
					}
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadRiskAssessment() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRiskAssessment() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return arr;
	}
	
	private String getCellDataType2(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup

		if (!StringUtils.isEmpty(cell) && cell.getCellType() == CellType.FORMULA) {
		    switch (evaluator.evaluateFormulaCell(cell)) {
		        case BOOLEAN:
		            val = String.valueOf(cell.getBooleanCellValue());
		            break;
		        case NUMERIC:
		        	val = String.valueOf(cell.getNumericCellValue());
		            break;
		        case STRING:
		            val = cell.getStringCellValue();
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
		return val;
	}
	
	
	@RequestMapping(value = "/get-risk-assessment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRiskAssessment(@ModelAttribute Risk obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addEditRiskATRForm);
			
			List<Risk> assessmentDates = riskService.getRiskAssessmentDates(obj);
			model.addObject("assessmentDates", assessmentDates);
			
			Risk risk = riskService.getRiskAssessment(obj);
			model.addObject("risk", risk);
			
		}catch (Exception e) {
				logger.error("getRiskAssessment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-risk-assessment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateRiskAssessment(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/mobileappwebview/risk-atr-update");
			
			boolean flag = riskService.updateRiskAssessment(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk is failed. Try again.");
			}			
		}catch (Exception e) {
			attributes.addFlashAttribute("error",commonError);
			logger.error("updateRiskAssessment : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value="/risk-atr-update",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskATRUpdate(@ModelAttribute Risk obj,HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.riskATRUpdateGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskATRUpdate : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/ajax/getRiskAssessmentList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskAssessmentList(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			riskList = riskService.getRiskAssessmentList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRiskAssessmentList : " + e.getMessage());
		}
		return riskList;
	}
	
	@RequestMapping(value = "/ajax/getSubWorksFilterListInRiskAssessmnt", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getSubWorksFilterListInRiskAssessmnt(@ModelAttribute Risk obj) {
		List<Risk> worksList = null;
		try {
			worksList = riskService.getSubWorksFilterListInRiskAssessmnt(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubWorksFilterListInRiskAssessmnt : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getAreasFilterListInRiskAssessment", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAreasFilterListInRiskAssessment(@ModelAttribute Risk obj) {
		List<Risk> areaList = null;
		try {
			areaList = riskService.getAreasFilterListInRiskAssessment(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAreasFilterListInRiskAssessment : " + e.getMessage());
		}
		return areaList;
	}

	@RequestMapping(value = "/ajax/getAssessmentDatesFilterListInRiskAssessment", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAssessmentDatesFilterListInRiskAssessment(@ModelAttribute Risk obj) {
		List<Risk> assesmentDates = null;
		try {
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
			assesmentDates = riskService.getAssessmentDatesFilterListInRiskAssessment(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAssessmentDatesFilterListInRiskAssessment : " + e.getMessage());
		}
		return assesmentDates;
	}
}
