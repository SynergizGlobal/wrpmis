package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.NumberUtils;
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
import org.apache.poi.ss.util.CellRangeAddress;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.RiskService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;

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
	
	@RequestMapping(value="/risk-assessment",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskAssessment(@ModelAttribute Risk obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.riskAssessmentGrid);
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
		String msg = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
			//model.setViewName("redirect:/risk");
			model.setViewName("redirect:/risk-assessment");
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
						XSSFRow headerRow = risksDrawingsSheet.getRow(2);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getRiskFileFormat();	
							int noOfColumns = headerRow.getLastCellNum();
							//if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									columnName = columnName.replaceAll("[\r\n]", "");
									String tempName = fileFormat.get(i).replaceAll("[\r\n]", "");
									//System.out.println(columnName + " = " + tempName);									
									if(!columnName.equals(tempName.trim()) && !columnName.contains(tempName.trim())){
										
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		
				                		msg = uploadformatError;
										risk.setUploaded_by_user_id_fk(userId);
										risk.setStatus("Fail");
										risk.setRemarks(msg);
										boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
										
				                		return model;
				                	}
								}
							/*}else{
								attributes.addFlashAttribute("error",uploadformatError);
								return model;
							}*/
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
							
							msg = uploadformatError;
							risk.setUploaded_by_user_id_fk(userId);
							risk.setStatus("Fail");
							risk.setRemarks(msg);
							boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
							
	                		return model;
						}
						
						int[] arr = uploadRiskAssessment(risk,userId,userName);
						
						if(arr[0] > 0) {
							attributes.addFlashAttribute("updateSuccess", arr[0] + " Risk updated successfully. ");
							msg = msg + arr[0] + " Risk updated successfully. ";
						}
						if(arr[1] > 0) {
							attributes.addFlashAttribute("success", arr[1] + " Risk added successfully. ");
							msg = msg + arr[1] + " Risk added successfully. ";
						}
						
						if(arr[0] == 0 && arr[1] == 0) {
							attributes.addFlashAttribute("success", "No risks found in file.");
							msg = msg + " No risks found in file. ";
						}
						
						risk.setUploaded_by_user_id_fk(userId);
						risk.setStatus("Success");
						risk.setRemarks(msg);
						boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				
				msg = "No file exists";
				risk.setUploaded_by_user_id_fk(userId);
				risk.setStatus("Fail");
				risk.setRemarks(msg);
				boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
			
			msg = "Something went wrong. Please try after some time";
			risk.setUploaded_by_user_id_fk(userId);
			risk.setStatus("Fail");
			risk.setRemarks(msg);
			try {
				boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveRiskAssessmentUploadFile() : "+e.getMessage());
			}
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
					for(int i = 3; i <= risksDrawingsSheet.getLastRowNum();i++){
						
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
									owner = getCellDataType2(workbook,row.getCell(1));
								}
								if(!StringUtils.isEmpty(owner)) { risk.setOwner(owner);}
								
								//val = getCellDataType2(workbook,row.getCell(2));
								tempVal = formatter.formatCellValue(row.getCell(2)).trim();
								count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
								if(count != 2) {
									item_no = getCellDataType2(workbook,row.getCell(2));
								}
								if(!StringUtils.isEmpty(item_no)) { risk.setItem_no(item_no);}
								
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
								if(!StringUtils.isEmpty(responsible_person) && !responsible_person.equals("0.0")) { risk.setResponsible_person(responsible_person);}									
								
								/*//val = getCellDataType2(workbook,row.getCell(16));
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
								if(!StringUtils.isEmpty(risk_id)) { risk.setRisk_id(risk_id);}*/
								
								
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
	
	
	@RequestMapping(value="/risk-atr-update",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskATRUpdate(@ModelAttribute Risk obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.riskATRUpdateGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskATRUpdate : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/get-risk-assessment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRiskAssessment(@ModelAttribute Risk obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.riskAssessmentForm);
			
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
			model.setViewName("redirect:/risk-atr-update");
			
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
	
	
	@RequestMapping(value = "/ajax/getRiskAssessmentList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskAssessmentList(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
	
	
	@RequestMapping(value = "/ajax/getRiskAssessmentUploadsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskAssessmentUploadsList(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			riskList = riskService.getRiskAssessmentUploadsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRiskAssessmentUploadsList : " + e.getMessage());
		}
		return riskList;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFromRiskUploads", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getWorksListFromRiskUploads(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			riskList = riskService.getWorksListFromRiskUploads(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFromRiskUploads : " + e.getMessage());
		}
		return riskList;
	}
	
	
	
	/****************************************************************************/
	
	
	@RequestMapping(value="/risk",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView risk(@ModelAttribute Risk obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.riskGrid);
		try {
			List<Risk> worksList = riskService.getWorksList(obj);
			model.addObject("worksList", worksList);
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
			obj.setAssessment_date(DateParser.parse(obj.getAssessment_date()));
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
	
	@RequestMapping(value="/upload-download-risk",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView uploadDownloadRisk(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.uploadDownloadRiskGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadDownloadRisk : " + e.getMessage());
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
			//model.setViewName("redirect:/risk");
			model.setViewName("redirect:/upload-download-risk");
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
							Cell cell = row.getCell(0);
							if(!StringUtils.isEmpty(cell)) {
								val = cell.getStringCellValue().trim();
							}
							
							if(!StringUtils.isEmpty(val) && !val.equals("null")) {
								//System.out.println(i + " = "+ val);
								val = formatter.formatCellValue(row.getCell(0)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setWork_id_fk(val);}
								
								val = formatter.formatCellValue(row.getCell(1)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setItem_no(val);}
								
								val = formatter.formatCellValue(row.getCell(2)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setRisk_id(val);}
								
								val = formatter.formatCellValue(row.getCell(3)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setOwner(val);}
								
								val = formatter.formatCellValue(row.getCell(4)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setRisk_area_fk(val);}	
								
								val = formatter.formatCellValue(row.getCell(5)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setSub_area_fk(val);}					
								
								val = formatter.formatCellValue(row.getCell(6)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setDate(val);}								
								
								val = formatter.formatCellValue(row.getCell(7)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setProbability(val);}										
								
								val = formatter.formatCellValue(row.getCell(8)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setImpact(val);}
								
								val = getCellData(workbook,row.getCell(9));
								//val = formatter.formatCellValue(row.getCell(9)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setRisk_rating(val);}
								
								val = getCellData(workbook,row.getCell(10));
								//val = formatter.formatCellValue(row.getCell(10)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setClassification(val);}
								
								
								val = formatter.formatCellValue(row.getCell(11)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setMitigation_plan(val);}
								
								val = formatter.formatCellValue(row.getCell(12)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setPriority_fk(val);}
								
								val = formatter.formatCellValue(row.getCell(13)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setResponsible_person(val);}	
								
								val = formatter.formatCellValue(row.getCell(14)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setAtr_date(val);}
								
								val = formatter.formatCellValue(row.getCell(15)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setAction_taken(val);}
								
								val = formatter.formatCellValue(row.getCell(15)).trim();
								if(!StringUtils.isEmpty(val)) { risk.setStatus(val);}
								
								risk.setDate(DateParser.parse(risk.getDate()));
								
								boolean flag = risk.checkNullOrEmpty();
								
								if(!flag) {
									risksList.add(risk);
								}
								
							}
						}						
						
					}
					
					List<Risk> revisionList = new ArrayList<Risk>();
					
					XSSFSheet riskRevisionsSheet = workbook.getSheetAt(3);
					for(int j = 1; j <= riskRevisionsSheet.getLastRowNum();j++){
						XSSFRow row2 = riskRevisionsSheet.getRow(j);
						// Sets the Read data to the model class
						Risk pObj = new Risk();
						String val = null;
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
						boolean flag = pObj.checkNullOrEmpty();
						
						if(!flag) {
							revisionList.add(pObj);
						}
					}
					
					if(!risksList.isEmpty() || !revisionList.isEmpty()){
						arr  = riskService.uploadRisks(risksList,revisionList);
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
	
	
	private String getCellData(XSSFWorkbook workbook, XSSFCell cell) {
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
	
	@RequestMapping(value = "/export-risks", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportRisks(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Risk risk,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView();
		List<RiskReport> riskDataList = new ArrayList<RiskReport>();
		List<RiskReport> atrRevisionDataList = new ArrayList<RiskReport>();
		try {
			view.setViewName("redirect:/upload-download-risk");
			risk.setAssessment_date(DateParser.parse(risk.getAssessment_date()));
			riskDataList =   riskService.getExportRiskList(risk);
			atrRevisionDataList =   riskService.getATRRevisionDataList(risk);
				
			XSSFWorkbook  workBook = new XSSFWorkbook();

            /********************************************************/
	        
	        XSSFSheet indexSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Index"));
	        workBook.setSheetOrder(indexSheet.getSheetName(), 0);
	        workBook.setSheetHidden(0, true);
	        
	        XSSFSheet referenceDataSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Reference Data"));
	        workBook.setSheetOrder(referenceDataSheet.getSheetName(), 1);
	        workBook.setSheetHidden(1, true);
	        
	        XSSFSheet riskSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Risk"));
	        workBook.setSheetOrder(riskSheet.getSheetName(), 2);
	        workBook.setActiveSheet(2);
	        riskSheet.protectSheet("password");
	        
	        XSSFSheet revisionSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("ATR Revision"));
	        workBook.setSheetOrder(revisionSheet.getSheetName(), 3);
	        
	       
	        
	        /***************************************************************************/
	        
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
	        sectionStyle.setLocked(false);
	        CellStyle lockedSectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        lockedSectionStyle.setLocked(true);
	        
	        /***************************************************************/
	        
	        XSSFRow indexRow = indexSheet.createRow(1);
	        Cell cell = indexRow.createCell(1);
			cell.setCellStyle(blueStyle);
			cell.setCellValue("");
			
			cell = indexRow.createCell(2);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Columns coming from Dropdowns");
			indexSheet.autoSizeColumn(2);				
			/********************************************************/
			
			/********************************************************/
			indexRow = indexSheet.createRow(2);
	        cell = indexRow.createCell(1);
			cell.setCellStyle(yellowStyle);
			cell.setCellValue("");
			
			cell = indexRow.createCell(2);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Format to be followed as per Guidelines");
			indexSheet.autoSizeColumn(2);
			/********************************************************/
			
			/********************************************************/
			indexRow = indexSheet.createRow(3);
	        cell = indexRow.createCell(1);
			cell.setCellStyle(greenStyle);
			cell.setCellValue("");
			
			cell = indexRow.createCell(2);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("To be filled by MRVC");
			/********************************************************/
			
			/********************************************************/
			indexRow = indexSheet.createRow(5);
	        cell = indexRow.createCell(1);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("*Note: Reference Data should not be altered");	
			
			for (int i = 2; i < 7; i++) {
				cell = indexRow.createCell(i);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
			}	
			indexSheet.addMergedRegion(new CellRangeAddress(5, 5, 1,6));
			
			indexRow = indexSheet.createRow(6);
	        cell = indexRow.createCell(1);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("**All dates should be in \"DD/MM/YYYY\"");
			
			for (int i = 2; i < 7; i++) {
				cell = indexRow.createCell(i);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
			}	
			indexSheet.addMergedRegion(new CellRangeAddress(6, 6, 1,6));
			/********************************************************/
	        
	        
	        /********************************************************/
	        XSSFRow referenceDataRow = referenceDataSheet.createRow(0);
	        cell = referenceDataRow.createCell(1);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Different Probabilities of Risk");
			
	        cell = referenceDataRow.createCell(3);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Different Impacts of Risk");
			
			cell = referenceDataRow.createCell(5);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Status of Risk");
			
			cell = referenceDataRow.createCell(7);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("");
			
			referenceDataRow = referenceDataSheet.createRow(1);
	        cell = referenceDataRow.createCell(1);
			cell.setCellStyle(greenStyle);
			cell.setCellValue("Probability");
			
	        cell = referenceDataRow.createCell(3);
			cell.setCellStyle(greenStyle);
			cell.setCellValue("Impact");
			
			cell = referenceDataRow.createCell(5);
			cell.setCellStyle(greenStyle);
			cell.setCellValue("Status");
			
			cell = referenceDataRow.createCell(7);
			cell.setCellStyle(greenStyle);
			cell.setCellValue("Priority");
			
			referenceDataRow = referenceDataSheet.createRow(2);
	        cell = referenceDataRow.createCell(1);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue(1);
			
	        cell = referenceDataRow.createCell(3);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue(1);
			
			cell = referenceDataRow.createCell(5);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Open");
			
			cell = referenceDataRow.createCell(7);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("P1");
			
			referenceDataRow = referenceDataSheet.createRow(3);
	        cell = referenceDataRow.createCell(1);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue(3);
			
	        cell = referenceDataRow.createCell(3);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue(3);
			
			cell = referenceDataRow.createCell(5);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Closed");
			
			cell = referenceDataRow.createCell(7);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("P2");
			
			referenceDataRow = referenceDataSheet.createRow(4);
	        cell = referenceDataRow.createCell(1);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue(5);
			
	        cell = referenceDataRow.createCell(3);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue(5);
			
			cell = referenceDataRow.createCell(7);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("P3");
			
			for (int i = 5; i < 11; i++) {
				referenceDataRow = referenceDataSheet.createRow(i);
		        cell = referenceDataRow.createCell(7);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("P"+(i-1));
			}			
			
			referenceDataRow = referenceDataSheet.createRow(11);
	        cell = referenceDataRow.createCell(7);
			cell.setCellStyle(indexWhiteStyle);
			cell.setCellValue("Accepted");
			
			for(int columnIndex = 0; columnIndex < 8; columnIndex++) {
				referenceDataSheet.autoSizeColumn(columnIndex);
				//referenceDataSheet.setColumnWidth(columnIndex, 25 * 150);
			}
			
			/********************************************************/		        
	        
	        String firstHeaderString = "Unique ID of the Work^To be confirmed if it's reqd. in input sheet^Unique ID of the Risk^Name of the person owning the Risk^Area of the Risk (dorpdown chosen from Column B of Reference sheet)^Sub Area of the Risk (dorpdown chosen from Column C of Reference sheet)^Date the Risk Review in \"DD/MM/YYYY\"^"
	        		+ "Probability of the Risk (dorpdown chosen from Column D of Reference sheet)^Impact of the Risk (dorpdown chosen from Column E of Reference sheet)^Calculated field by System  \r\n" + 
	        		"Not part of Input Form^^Mitigation Plan for the Risk^Type of Priority^Name of the person responsible to mitigate the Risk^Calculated field - If risk classification is low, then it is to be treated as \"Closed\" else \"Open\"";
	       
	        String secongHeaderString = "Work ID^Item No.^Risk ID^Owner^Area^Sub-Area^Date of Assessment^Probability (A)^Impact (B)^RISK RATING\n" + 
	        		"A x B^RISK CLASSIFICATION^Mitigation Plan^Priority^Responsible Person^Status";
	        
	        String[] firstHeaderStringArr = firstHeaderString.split("\\^");
	        
	        XSSFRow firstHeadingRow = riskSheet.createRow(0);
	        for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        cell = firstHeadingRow.createCell(i);
		        if(i == 1 || i == 9 || i == 10 || i == 14) {
					cell.setCellStyle(redStyle);
		        }else {
					cell.setCellStyle(indexWhiteStyle);
		        }
				cell.setCellValue(firstHeaderStringArr[i]);
			}
	        
	        riskSheet.addMergedRegion(new CellRangeAddress(0, 0, 9,10));
	        
	        String[] secongHeaderStringArr = secongHeaderString.split("\\^");
	        
	        XSSFRow secondHeadingRow = riskSheet.createRow(1);
	        for (int i = 0; i < secongHeaderStringArr.length; i++) {		        	
		        cell = secondHeadingRow.createCell(i);
		        if(i == 1 || i == 9 || i == 10 || i == 14) {
					cell.setCellStyle(yellowStyle);
		        }else if(i > 5) {
					cell.setCellStyle(greenStyle);
		        }else {
		        	cell.setCellStyle(blueStyle);
		        }
				cell.setCellValue(secongHeaderStringArr[i]);
			}				
			
			/*************************************************************************/		        
	        if(riskDataList != null && riskDataList.size() > 0){

	        	int rowNo = 2;
	            for (RiskReport obj : riskDataList) {
	                XSSFRow row = riskSheet.createRow(rowNo);
	                int c = 0;
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_id_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_item_no()+"."+obj.getSub_area_item_no());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRisk_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getOwner());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSub_area());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAssessment_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(NumberUtils.createInteger(obj.getProbability()));
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(NumberUtils.createInteger(obj.getImpact()));
					
					/*cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(NumberUtils.createInteger(obj.getRisk_rating()));*/
					
					cell = row.createCell(c++);
					String formulae1 = "H"+(row.getRowNum()+1)+"*I"+(row.getRowNum()+1);
					cell.setCellFormula(formulae1);
					cell.setCellType(CellType.FORMULA);
					cell.setCellStyle(lockedSectionStyle);
					
					/*cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getClassification());*/
					
					cell = row.createCell(c++);
					String formulae2 = "IF(J"+(row.getRowNum()+1)+"<4,\"Low\",IF(J"+(row.getRowNum()+1)+"<9,\"Moderate\",IF(J"+(row.getRowNum()+1)+"<19,\"Substantial\",\"High\")))";
					cell.setCellFormula(formulae2);
					cell.setCellType(CellType.FORMULA);
					cell.setCellStyle(lockedSectionStyle);

					cell = row.createCell(c++);
					cell.setCellValue(obj.getMitigation_plan());
					cell.setCellStyle(sectionStyle);
					
					cell = row.createCell(c++);
					cell.setCellValue(obj.getPriority());
					cell.setCellStyle(sectionStyle);
					
					cell = row.createCell(c++);
					cell.setCellValue(obj.getResponsible_person());
					cell.setCellStyle(sectionStyle);
										
					/*cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue("");*/
					
					cell = row.createCell(c++);
					String formulae3 = "IF(K"+(row.getRowNum()+1)+"=\"Low\",\"Accepted\",\"Open\")";
					cell.setCellFormula(formulae3);
					cell.setCellType(CellType.FORMULA);
					cell.setCellStyle(lockedSectionStyle);
					
	                rowNo++;
	            }
            
	        }

            
            for(int columnIndex = 0; columnIndex < secongHeaderStringArr.length; columnIndex++) {
			     //sheet.autoSizeColumn(columnIndex);
            	riskSheet.setColumnWidth(columnIndex, 25 * 200);
			}
            
            /*******************************************************************************/
            
            String revisionHeaderString = "Risk ID^Date of Assessment^ATR Date^Action Taken";
	        
	        String[] revisionHeaderStringArr = revisionHeaderString.split("\\^");
	        
	        XSSFRow revisionHeadingRow = revisionSheet.createRow(0);
	        for (int i = 0; i < revisionHeaderStringArr.length; i++) {		        	
		        cell = revisionHeadingRow.createCell(i);
		        if(i == 0 || i == 1) {
					cell.setCellStyle(blueStyle);
		        }else {
					cell.setCellStyle(greenStyle);
		        }
				cell.setCellValue(revisionHeaderStringArr[i]);
			}
	        if(atrRevisionDataList != null && atrRevisionDataList.size() > 0){
	        	int rowNo = 1;
				for (RiskReport obj : atrRevisionDataList) {
				    XSSFRow row = revisionSheet.createRow(rowNo);
				    int c = 0;
				    
				    cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRisk_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAssessment_date());
					
				    cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAtr_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAction_taken());
					rowNo++;
				}
	        }
            
	        for(int columnIndex = 0; columnIndex < revisionHeaderStringArr.length; columnIndex++) {
			     //sheet.autoSizeColumn(columnIndex);
	        	revisionSheet.setColumnWidth(columnIndex, 25 * 200);
			}
            
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Risks_"+dateFormat.format(date);
            
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
			
		}catch (Exception e) {
			e.printStackTrace();
			 attributes.addFlashAttribute("error",dataExportError);
		}
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
