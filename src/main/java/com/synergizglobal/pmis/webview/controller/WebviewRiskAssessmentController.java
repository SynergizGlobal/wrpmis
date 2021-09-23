package com.synergizglobal.pmis.webview.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.ui.Model;
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
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.controller.RiskController;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.User;

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
	public ModelAndView riskAssessment(@ModelAttribute Risk obj,HttpSession session,Model modelObj){
		ModelAndView model = new ModelAndView(MobilePageConstants2.riskATRGrid);
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_id(uObj.getUser_id()); 
			
			/*List<Risk> worksList = riskService.getWorksList(obj);
			model.addObject("worksList", worksList);*/
			
			List<Risk> subWorksList = riskService.getSubWorksList(obj);
			model.addObject("subWorksList", subWorksList);
			
			List<Risk> workHodList = riskService.getSubWorkHodFilterListInRiskAssessmnt(obj);
			model.addObject("workHodList", workHodList);
			
			/*String sub_work = (String) modelObj.asMap().get("sub_work");
			if(!StringUtils.isEmpty(sub_work)) {
				model.addObject("sub_work", sub_work);
			}else {
				model.addObject("sub_work", obj.getSub_work());
			}*/
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskAssessment : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/checkRiskAssessment", method = {RequestMethod.POST})
	@ResponseBody
	public String[] checkRiskAssessment(@ModelAttribute Risk obj,RedirectAttributes attributes,HttpSession session)
	{
		Risk risk = null;
		List<Risk> risksList = new ArrayList<Risk>();
		String userId = null;String userName = null;
		Writer w = null;
		String[] result = new String[2];
		String msg = "";
		String assessment_date = null;
		try {		           
			MultipartFile excelfile = obj.getRiskAssessmentFile();
			
			
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation());			
			obj.setUploaded_by_user_id_fk(uObj.getUser_id());
			
			
			if(!StringUtils.isEmpty(obj.getRiskAssessmentFile())){
				MultipartFile multipartFile = obj.getRiskAssessmentFile();
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
						if(headerRow != null)
						{
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
				                		msg = uploadformatError;
				                		obj.setUploaded_by_user_id_fk(userId);
				                		obj.setStatus("Fail");
				                		obj.setRemarks(result[0]);
				                		attributes.addFlashAttribute("error",msg);
										
				                	}
								}
						}
					
					}
					workbook.close();
				}
			}			

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
					
					String logged_in_user_designation = obj.getUser_designation();

					String risk_owner_error = "";
					String risk_rows_error = "";
					String risk_cols_error = "";
					int rowNo = 0;
					String work_mismatch = null;
					String assessment_date_error = null;
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
							String tempVal = formatter.formatCellValue(row.getCell(0)).trim();
							int count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
							if(count != 2) {
								sub_work = getCellDataType2(workbook,row.getCell(0));
							}								
							if(!StringUtils.isEmpty(sub_work)) { 
								String tempSubWork = sub_work.replaceAll("\\&", "and");
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
							
							risk.setDate(DateParser.parse(risk.getDate()));	
							
							assessment_date = risk.getDate();
							
							if(StringUtils.isEmpty(risk.getSub_work())) { 
								risk_cols_error = "A";
							}
							if(StringUtils.isEmpty(risk.getOwner())) { 
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "B";
							}
							if(StringUtils.isEmpty(risk.getDate())) { 
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "F";
							}
							if(!"Accepted".equals(priority) && StringUtils.isEmpty(mitigation_plan)) {
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "M";
							}
							if(StringUtils.isEmpty(risk.getResponsible_person())) { 
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "N";
							}
							
							
							if(!StringUtils.isEmpty(risk_cols_error)) { 
								break;
							}
							
							
							if(!StringUtils.isEmpty(obj.getSub_work()) && !obj.getSub_work().equals(risk.getSub_work())) {
								work_mismatch = "Work selected from the dropdown and on the assessment form do not match.";
								break;
							}
							
							if(!StringUtils.isEmpty(risk.getDate()) ) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								
								String startDate = risk.getDate();
								String endDate = sdf.format(new Date());
							    Date start = sdf.parse(startDate);
					            Date end = sdf.parse(endDate);
					            if (start.compareTo(end) > 0) {
					            	assessment_date_error = "Assessment date on input form cannot be later than current date.";
									break;
					            }    
								
							}
							
							
							if(!StringUtils.isEmpty(obj.getSub_work()) && obj.getSub_work().equals(risk.getSub_work())
									&& !StringUtils.isEmpty(risk.getSub_work()) && !StringUtils.isEmpty(risk.getOwner()) 
									&& !StringUtils.isEmpty(risk.getDate()) && !StringUtils.isEmpty(risk.getProbability()) && !StringUtils.isEmpty(risk.getImpact()) 
									&& !StringUtils.isEmpty(risk.getPriority_fk())  && !StringUtils.isEmpty(risk.getResponsible_person()) 
									&& (risk.getProbability().equals("1") || risk.getProbability().equals("3") || risk.getProbability().equals("5")) && (risk.getImpact().equals("1") || risk.getImpact().equals("3") || risk.getImpact().equals("5"))) {
								
								if(risk.getOwner().equals(logged_in_user_designation)) {
									risksList.add(risk);
								}else {
									risk_owner_error = "1";
									break;
								}
								
							}else {
								risk_rows_error = risk_rows_error + (!StringUtils.isEmpty(risk_rows_error)?",":"") + rowNo;
							}
							
						}						
					}
					
					if(!StringUtils.isEmpty(risk_owner_error)) {
						risk_owner_error = "<br><span style='color:red;'>PMIS user and work owner on the assessment form do not match.</span> ";
					}
					if(!StringUtils.isEmpty(risk_cols_error)) {
						risk_cols_error = "<br><span style='color:red;'>Your assessment is incomplete! Column no(s) " + risk_cols_error + " of the assessment form requires attention.</span> ";
					}
					if(!StringUtils.isEmpty(risk_rows_error)) {
						risk_rows_error = "<br><span style='color:red;'>Your assessment is incomplete! Row no(s) " + risk_rows_error + " of the assessment form requires attention.</span> ";
					}
					
					if(!StringUtils.isEmpty(work_mismatch)) {
						risk_rows_error = "<br><span style='color:red;'>" + work_mismatch + "</span> ";
					}
					
					if(!StringUtils.isEmpty(assessment_date_error)) {
						risk_rows_error = "<br><span style='color:red;'>" + assessment_date_error + "</span> ";
					}
					
					if(!risksList.isEmpty() && StringUtils.isEmpty(risk_rows_error) && StringUtils.isEmpty(work_mismatch)
							&& StringUtils.isEmpty(assessment_date_error) && StringUtils.isEmpty(risk_cols_error))
					{
						
						boolean CheckAssessmentDate=riskService.checkRiskAssessment(risk.getSub_work(),risk.getDate());
						if(CheckAssessmentDate==true)
						{
							msg = "<br><span style='color:red;' id='checkEntryError'>The date of assessment on the Risk Assessment form is same as that of the last assessment date. </span> ";
						}
					}				
					
					msg = msg + risk_owner_error + risk_rows_error + risk_cols_error;
					
					result[0] = msg;
					result[1] = assessment_date;
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadRiskAssessment() : "+e.getMessage());
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRiskAssessment() : "+e.getMessage());
		    }
		}
		
		return result;

	}
	
	
	@RequestMapping(value = "/upload-risk-assessment", method = {RequestMethod.POST})
	public ModelAndView uploadRiskAssessment(@ModelAttribute Risk risk,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		String msg = "";
		String[] result = new String[2];
		try {			
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
			User uObj = (User) session.getAttribute("user");
			risk.setUser_type(uObj.getUser_type_fk());
			risk.setUser_role_code(uObj.getUser_role_code());
			risk.setUser_id(uObj.getUser_id()); 
			risk.setUser_designation(uObj.getDesignation());
			
			//model.setViewName("redirect:/mobileappwebview/risk");
			model.setViewName("redirect:/mobileappwebview/risk-assessment");
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
						if(headerRow != null)
						{
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
				                		msg = uploadformatError;
										risk.setUploaded_by_user_id_fk(userId);
										risk.setStatus("Fail");
										risk.setRemarks(result[0]);
										boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
										/*Risk tempRisk = riskService.getLastUpdatedRiskAssessmentFile(risk);
										if(!StringUtils.isEmpty(tempRisk) && !StringUtils.isEmpty(tempRisk.getAttachment())) {
											msg = "You are attempting to submit the assessment done on an outdated Risk Assessment Form. Please download a blank assessment form at Step 2 and re-submit.";
										}*/
				                		attributes.addFlashAttribute("error",msg);
										
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
							risk.setRemarks(result[0]);
							boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
							
	                		return model;
						}
						
						result = uploadRiskAssessment(risk,userId,userName);
						attributes.addFlashAttribute("success", result[0]);
						attributes.addFlashAttribute("assessment_date", result[1]);
						attributes.addFlashAttribute("sub_work", risk.getSub_work());
						
						risk.setUploaded_by_user_id_fk(userId);
						if(result[0].contains("Risk Assessment uploaded successfully.")) {
							risk.setStatus("Success");
						}else{
							risk.setStatus("Fail");
						}
						risk.setRemarks(result[0]);
						risk.setAssessment_date(result[1]);
						boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Your assessment is incomplete!");
				
				msg = "Your assessment is incomplete!";
				risk.setUploaded_by_user_id_fk(userId);
				risk.setStatus("Fail");
				risk.setRemarks(result[0]);
				risk.setAssessment_date(result[1]);
				boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Your assessment is incomplete");
			logger.fatal("updateDataDate() : "+e.getMessage());
			
			msg = "Your assessment is incomplete!";
			risk.setUploaded_by_user_id_fk(userId);
			risk.setStatus("Fail");
			risk.setRemarks(result[0]);
			risk.setAssessment_date(result[1]);
			try {
				boolean flag = riskService.saveRiskAssessmentUploadFile(risk);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Your assessment is incomplete!");
				logger.fatal("saveRiskAssessmentUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}

	private String[] uploadRiskAssessment(Risk obj, String userId, String userName)  throws Exception{
		Risk risk = null;
		List<Risk> risksList = new ArrayList<Risk>();
		
		Writer w = null;
		String[] result = new String[2];
		String msg = "";
		String assessment_date = null;
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
					
					String logged_in_user_designation = obj.getUser_designation();

					String risk_owner_error = "";
					String risk_rows_error = "";
					String risk_cols_error = "";
					int rowNo = 0;
					String work_mismatch = null;
					String assessment_date_error = null;
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
							String tempVal = formatter.formatCellValue(row.getCell(0)).trim();
							int count = org.apache.commons.lang3.StringUtils.countMatches(tempVal, "$");
							if(count != 2) {
								sub_work = getCellDataType2(workbook,row.getCell(0));
							}								
							if(!StringUtils.isEmpty(sub_work)) { 
								String tempSubWork = sub_work.replaceAll("\\&", "and");
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
							
							risk.setDate(DateParser.parse(risk.getDate()));	
							
							assessment_date = risk.getDate();
							
							if(StringUtils.isEmpty(risk.getSub_work())) { 
								risk_cols_error = "A";
							}
							if(StringUtils.isEmpty(risk.getOwner())) { 
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "B";
							}
							if(StringUtils.isEmpty(risk.getDate())) { 
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "F";
							}
							if(!"Accepted".equals(priority) && StringUtils.isEmpty(mitigation_plan)) {
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "M";
							}
							if(StringUtils.isEmpty(risk.getResponsible_person())) { 
								risk_cols_error = risk_cols_error + (!StringUtils.isEmpty(risk_cols_error)?",":"") + "N";
							}
							
							
							if(!StringUtils.isEmpty(risk_cols_error)) { 
								break;
							}
							
							
							if(!StringUtils.isEmpty(obj.getSub_work()) && !obj.getSub_work().equals(risk.getSub_work())) {
								work_mismatch = "Work selected from the dropdown and on the assessment form do not match.";
								break;
							}
							
							if(!StringUtils.isEmpty(risk.getDate()) ) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								
								String startDate = risk.getDate();
								String endDate = sdf.format(new Date());
							    Date start = sdf.parse(startDate);
					            Date end = sdf.parse(endDate);
					            if (start.compareTo(end) > 0) {
					            	assessment_date_error = "Assessment date on input form cannot be later than current date.";
									break;
					            }    
								
							}
							
							
							if(!StringUtils.isEmpty(obj.getSub_work()) && obj.getSub_work().equals(risk.getSub_work())
									&& !StringUtils.isEmpty(risk.getSub_work()) && !StringUtils.isEmpty(risk.getOwner()) 
									&& !StringUtils.isEmpty(risk.getDate()) && !StringUtils.isEmpty(risk.getProbability()) && !StringUtils.isEmpty(risk.getImpact()) 
									&& !StringUtils.isEmpty(risk.getPriority_fk())  && !StringUtils.isEmpty(risk.getResponsible_person()) 
									&& (risk.getProbability().equals("1") || risk.getProbability().equals("3") || risk.getProbability().equals("5")) && (risk.getImpact().equals("1") || risk.getImpact().equals("3") || risk.getImpact().equals("5"))) {
								
								if(risk.getOwner().equals(logged_in_user_designation)) {
									risksList.add(risk);
								}else {
									risk_owner_error = "1";
									break;
								}
								
							}else {
								risk_rows_error = risk_rows_error + (!StringUtils.isEmpty(risk_rows_error)?",":"") + rowNo;
							}
							
						}						
					}
					
					if(!StringUtils.isEmpty(risk_owner_error)) {
						risk_owner_error = "<br><span style='color:red;'>PMIS user and work owner on the assessment form do not match.</span> ";
					}
					if(!StringUtils.isEmpty(risk_cols_error)) {
						risk_cols_error = "<br><span style='color:red;'>Your assessment is incomplete! Column no(s) " + risk_cols_error + " of the assessment form requires attention.</span> ";
					}
					if(!StringUtils.isEmpty(risk_rows_error)) {
						risk_rows_error = "<br><span style='color:red;'>Your assessment is incomplete! Row no(s) " + risk_rows_error + " of the assessment form requires attention.</span> ";
					}
					
					if(!StringUtils.isEmpty(work_mismatch)) {
						risk_rows_error = "<br><span style='color:red;'>" + work_mismatch + "</span> ";
					}
					
					if(!StringUtils.isEmpty(assessment_date_error)) {
						risk_rows_error = "<br><span style='color:red;'>" + assessment_date_error + "</span> ";
					}
					
					if(!risksList.isEmpty() && StringUtils.isEmpty(risk_rows_error) && StringUtils.isEmpty(work_mismatch)
							&& StringUtils.isEmpty(assessment_date_error) && StringUtils.isEmpty(risk_cols_error))
					{
						

						
						int[] arr  = riskService.uploadRiskAssessments(risksList);
						
						/*
						 * if(arr[0] > 0) { msg = msg + arr[0] + " Risk updated successfully. "; }
						 * if(arr[1] > 0) { msg = msg + arr[1] + " Risk added successfully. "; }
						 */
						
						msg =  "<span style='color:green;'>Risk Assessment uploaded successfully.</span>";
						
					}				
					
					msg = msg + risk_owner_error + risk_rows_error + risk_cols_error;
					
					result[0] = msg;
					result[1] = assessment_date;
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
		
		return result;
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
		ModelAndView model = new ModelAndView(MobilePageConstants2.riskATRUpdateGrid);
		try {
			model.addObject("sub_work", obj.getSub_work());
			model.addObject("assessment_date", DateParser.parseToDDMMMYYYYFormat(obj.getAssessment_date()));
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskATRUpdate : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/get-risk-assessment", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRiskAssessment(@ModelAttribute Risk obj,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addEditRiskATRForm);
			
			/*List<Risk> assessmentDates = riskService.getRiskAssessmentDates(obj);
			model.addObject("assessmentDates", assessmentDates);*/
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation());
			
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
				attributes.addFlashAttribute("success", "ATR Updated Successfully.");
			} else {
				attributes.addFlashAttribute("error","Updating ATR failed.");
			}			
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error",commonError);
			logger.error("updateRiskAssessment : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getRiskAssessmentList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskAssessmentList(@ModelAttribute Risk obj,HttpSession session) {
		List<Risk> riskList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation()); 
			
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
	public List<Risk> getSubWorksFilterListInRiskAssessmnt(@ModelAttribute Risk obj,HttpSession session) {
		List<Risk> worksList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation());
			
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
	public List<Risk> getAreasFilterListInRiskAssessment(@ModelAttribute Risk obj,HttpSession session) {
		List<Risk> areaList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation());
			
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
	public List<Risk> getAssessmentDatesFilterListInRiskAssessment(@ModelAttribute Risk obj,HttpSession session) {
		List<Risk> assesmentDates = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id()); 
			obj.setUser_designation(uObj.getDesignation());
			
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
	
	@RequestMapping(value = "/ajax/getSubWorksListFromRiskUploads", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getSubWorksListFromRiskUploads(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			riskList = riskService.getSubWorksListFromRiskUploads(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubWorksListFromRiskUploads : " + e.getMessage());
		}
		return riskList;
	}
	
	@RequestMapping(value = "/ajax/getLastUpdatedRiskAssessmentFile", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Risk getLastUpdatedRiskAssessmentFile(@ModelAttribute Risk obj) {
		Risk risk = null;
		try {
			risk = riskService.getLastUpdatedRiskAssessmentFile(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLastUpdatedRiskAssessmentFile : " + e.getMessage());
		}
		return risk;
	}
	
}
