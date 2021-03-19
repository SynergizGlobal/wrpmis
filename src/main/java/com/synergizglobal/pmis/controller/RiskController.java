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
						XSSFSheet risksDrawingsSheet = workbook.getSheetAt(1);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = risksDrawingsSheet.getRow(2);
						
						DataFormatter formatter = new DataFormatter();
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getRiskFileFormat();	
							int noOfColumns = headerRow.getLastCellNum();
							//if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = formatter.formatCellValue(headerRow.getCell(i));
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
						
						msg = uploadRiskAssessment(risk,userId,userName);
						attributes.addFlashAttribute("success", msg);
						/*if(arr[0] > 0) {
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
						}*/
						
						risk.setUploaded_by_user_id_fk(userId);
						if(msg.contains("File is not uploaded")) {
							risk.setStatus("Fail");
						}else {
							risk.setStatus("Success");
						}
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

	private String uploadRiskAssessment(Risk obj, String userId, String userName)  throws Exception{
		Risk risk = null;
		List<Risk> risksList = new ArrayList<Risk>();
		
		Writer w = null;
		//int[] arr = null;
		String msg = "";
		try {	
			MultipartFile excelfile = obj.getRiskAssessmentFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet risksDrawingsSheet = workbook.getSheetAt(1);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					
					String sub_work = null,item_no = null,risk_id = null,owner = null,risk_area_fk = null,risk_sub_area_fk = null,
							date = null,probability = null,impact = null,mitigation_plan = null,priority = null,
							responsible_person = null;
					String risk_base_text = "";

					String risk_rows_error = "";
					int rowNo = 0;
					for(int j = 3; j <= risksDrawingsSheet.getLastRowNum();j++){						
						rowNo = j+1;
						XSSFRow row = risksDrawingsSheet.getRow(j);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						risk = new Risk();
						String val = null;
						
						if(!StringUtils.isEmpty(row)) {	
								
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
							
							/*boolean flag = risk.checkNullOrEmpty();
							
							if(!flag) {
								risksList.add(risk);
							}*/
							
							if(!StringUtils.isEmpty(risk.getSub_work()) && !StringUtils.isEmpty(risk.getOwner()) 
									&& !StringUtils.isEmpty(risk.getDate()) && !StringUtils.isEmpty(risk.getProbability()) && !StringUtils.isEmpty(risk.getImpact()) 
									&& !StringUtils.isEmpty(risk.getPriority_fk())  && !StringUtils.isEmpty(risk.getResponsible_person()) 
									&& (risk.getProbability().equals("1") || risk.getProbability().equals("3") || risk.getProbability().equals("5")) && (risk.getImpact().equals("1") || risk.getImpact().equals("3") || risk.getImpact().equals("5"))) {
								risksList.add(risk);
							} else {
								risk_rows_error = risk_rows_error + (!StringUtils.isEmpty(risk_rows_error)?",":"") + rowNo;
							}
						}						
						
					}
					if(!risksList.isEmpty() && StringUtils.isEmpty(risk_rows_error)){
						int[] arr  = riskService.uploadRiskAssessments(risksList);
						
						if(arr[0] > 0) {
							msg = msg + arr[0] + " Risk updated successfully. ";
						}
						if(arr[1] > 0) {
							msg = msg + arr[1] + " Risk added successfully. ";
						}
						
						msg =  "<span style='color:green;'> "+msg+"</span>";
					}
					if(!StringUtils.isEmpty(risk_rows_error)) {
						risk_rows_error = "<br><span style='color:red;'>File is not uploaded. Row no(s) " + risk_rows_error + " are not inserted (Reason : Owner, Date of Assessment,Probability,Impact,Priority of Open Risks,Action By Should not empty. And Probability and impact should have values 1 or 3 or 5 ).</span> ";
					}
					
					msg = msg + risk_rows_error;
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
		
		return msg;
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
			
			/*List<Risk> assessmentDates = riskService.getRiskAssessmentDates(obj);
			model.addObject("assessmentDates", assessmentDates);*/
			
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
	
	
}
