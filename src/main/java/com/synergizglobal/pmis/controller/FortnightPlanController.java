package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Iservice.FortnightPlanService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.FortnightPlanPaginationObject;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Controller
public class FortnightPlanController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FortnightPlanController.class);
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Autowired
	FortnightPlanService FortnightPlanService;
	
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
	
	@RequestMapping(value="/FortnightPlan",method=RequestMethod.GET)
	public ModelAndView FortnightPlan(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.fortnightPlanGrid);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FortnightPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/FortnightQuarterlyPlan",method=RequestMethod.GET)
	public ModelAndView FortnightQuarterlyPlan(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.quarterlyPlanGrid);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FortnightQuarterlyPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/fortnight-report",method=RequestMethod.GET)
	public ModelAndView fortnightReport(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.fortnightReport);

			List<FortnightPlan> FortnightPlanProjectList = FortnightPlanService.getFortnightPlanProjectList();
			model.addObject("FortnightPlanProjectList", FortnightPlanProjectList);
			
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);

			List<FortnightPlan> FortnightPlanContractList = FortnightPlanService.getFortnightPlanContractList();
			model.addObject("FortnightPlanContractList", FortnightPlanContractList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FortnightReport : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value = "/upload-fortnight-remarks", method = {RequestMethod.POST})
	public ModelAndView uploadRisk(@ModelAttribute FortnightPlan fortnightPlan,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			fortnightPlan.setCreated_by_user_id_fk(user_Id);
			fortnightPlan.setUser_id(user_Id);
			fortnightPlan.setUser_name(userName);
			fortnightPlan.setDesignation(userDesignation);
			model.setViewName("redirect:/fortnight-upload-list");
			
			if(!StringUtils.isEmpty(fortnightPlan.getFortnightPlanFile())){
				MultipartFile multipartFile = fortnightPlan.getFortnightPlanFile();
				String fileName = multipartFile.getOriginalFilename();
				fortnightPlan.setFilename(fileName);
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet risksDrawingsSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = risksDrawingsSheet.getRow(0);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getFortnightPlanFileFormat();	
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
						
						String saveDirectory = CommonConstants2.FORTNIGHT_PLAN_UPLOAD_REMARKS_PATH ;
						FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);						
						
						int count = uploadFortnightPlan(fortnightPlan,user_Id,userName);
						if(count > 0) {
							attributes.addFlashAttribute("success", + count + " FortnightPlan Remarks uploaded successfully.");
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(fortnightPlan.getCreated_by_user_id_fk());
							formHistory.setUser(fortnightPlan.getDesignation()+" - "+fortnightPlan.getUser_name());
							formHistory.setModule_name_fk("FortnightPlan");
							formHistory.setForm_name("Upload FortnightPlan");
							formHistory.setForm_action_type("Upload");
							formHistory.setForm_details( count + " FortnightPlan uploaded successfully.");
							formHistory.setWork(fortnightPlan.getWork_id_fk());
							formHistory.setContract(fortnightPlan.getContract_id_fk());
							
							boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
							/********************************************************************************/
							
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

	private int uploadFortnightPlan(FortnightPlan obj, String userId,String userName) throws Exception {
		FortnightPlan fortnightPlan = null;
		List<FortnightPlan> fortnightPlanList = new ArrayList<FortnightPlan>();
		
		Writer w = null;
		int count = 0 ;
		try {	
			MultipartFile excelfile = obj.getFortnightPlanFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet risksDrawingsSheet = workbook.getSheetAt(0);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 1; i <= risksDrawingsSheet.getLastRowNum();i++){
						XSSFRow row = risksDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						fortnightPlan = new FortnightPlan();
						fortnightPlan.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
						fortnightPlan.setFilename(obj.getFilename());
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
						  
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setFortnight_date(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setContract_short_name(val);}
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setStructure_type_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setStructure(val);}					
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setComponent(val);}								
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setUnit(val);}										
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setScope(val);}
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setTarget_till_lfn(val);}
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setActual_till_lfn(val);}
							
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setTarget_this_fn(val);}
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setActual_this_fn(val);}
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setCum_target(val);}	
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setCum_actual(val);}
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setCritical(val);}
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { fortnightPlan.setRemarks(val);}
							

						
						}						
						boolean flag = fortnightPlan.checkNullOrEmpty();
						
						if(!flag) {
							fortnightPlanList.add(fortnightPlan);
						}
					}
					
					if(!fortnightPlanList.isEmpty()){
						count  = FortnightPlanService.uploadFortnightPlans(fortnightPlanList);
					}
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadFortnightPlans() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadFortnightPlan() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}
	
	@RequestMapping(value="/fortnight-upload-remarks",method=RequestMethod.GET)
	public ModelAndView fortnightUploadRemarks(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		
			model.setViewName(PageConstants2.fortnightUploadRemarks);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FortnightReport : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value="/fortnight-upload-list",method=RequestMethod.GET)
	public ModelAndView fortnightUploadList(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
		
			model.setViewName(PageConstants2.fortnightUploadList);
			
			List<FortnightPlan> FortnightUploadList = FortnightPlanService.getFortnightUploadList();
			model.addObject("FortnightUploadList", FortnightUploadList);			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FortnightUploadList : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/generate-fortnight-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateFortnightReport(@ModelAttribute FortnightPlan obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		try{

			
			FortnightPlan reportData = FortnightPlanService.generateFortnightReport(obj);
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
			/***************************************************************************/
	        
			byte[] blueRGB = new byte[]{(byte)180, (byte)198, (byte)231};
			byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)153};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        byte[] orangeLightRGB = new byte[]{(byte)255, (byte)201, (byte)163};
	        
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Garamond";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle structureStyle = cellFormating(workBook,orangeLightRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle cellStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle centerStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle componentStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 11;fontName = "Garamond";
	        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle numberStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle activityNameStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        if(!(StringUtils.isEmpty(reportData))) {
	        	
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Fortnight Report"));
			        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
			        
			        
			        XSSFRow dateRow = dprSheet.createRow(2);
			        
			        
			        Cell cell = dateRow.createCell(0);
			      
			        	
			        XSSFRow mainHeadingRow = dprSheet.createRow(1);
			        
			        cell = mainHeadingRow.createCell(0);
			        cell.setCellStyle(centerStyle);
					cell.setCellValue("Fortnight Report ");
			        for (int i = 1; i < 7; i++) {		        	
				        cell = mainHeadingRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(1, 1, 0,6));
					/********************************************************/	
			        
			        /********************************************************/	
			        XSSFRow deatilsRow = dprSheet.createRow(2);
			   
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Work ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(reportData.getWork_id_fk() + " - " + (!StringUtils.isEmpty(reportData.getWork_short_name())?reportData.getWork_short_name():reportData.getWork_name()));
					
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 1,6));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(3);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contract ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(reportData.getContract_id_fk() + " - " + (!StringUtils.isEmpty(reportData.getContract_short_name())));
			        
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3,3, 1,6));
					
					/********************************************************/
					
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(4);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contractor ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(reportData.getContractor_name());
					
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4,4, 1,6));
			        

							
 
	        		 
				
				
	        }else {
	        	XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("No Data"));
		        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
	        }
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Structure_Status_Report_"+dateFormat.format(date);
            
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
            	
                
                //attributes.addFlashAttribute("success",dataExportSucess);
            	//response.setContentType("application/vnd.ms-excel");
            	//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
            	//XSSFWorkbook  workbook = new XSSFWorkbook ();
            	// ...
            	// Now populate workbook the usual way.
            	// ...
            	//workbook.write(response.getOutputStream()); // Write workbook to response.
            	//workbook.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
                logger.error("generateStripChartDPRReport : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                logger.error("generateStripChartDPRReport : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportError);
            }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateStripChartDPRReport : " + e.getMessage());
		}
		//return model;
    }	
	
	@RequestMapping(value="/update-quarterly-plan",method=RequestMethod.GET)
	public ModelAndView updateQuarterlyPlan(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateQuarterlyPlan);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateQuarterlyPlan : " + e.getMessage());
		}
		return model;
	}		
	
	@RequestMapping(value = "/add-quarterly-plan", method = {RequestMethod.GET})
	public ModelAndView addQuarterlyPlan(@ModelAttribute Budget obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants2.quarterlyPlanForm);
			model.addObject("action", "add");
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightQuarterlyPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);
			
			List<FortnightPlan> FortnightPlanItemList = FortnightPlanService.getFortnightQuarterlyPlanItemList();
			model.addObject("FortnightPlanItemList", FortnightPlanItemList);			
			
		}catch (Exception e) {
				logger.error("addFortnightlyPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-quarterly-plan/{FortnightPlan_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addQuarterlyPlan(@ModelAttribute FortnightPlan obj,@PathVariable("FortnightPlan_id") String FortnightPlan_id,HttpSession session,RedirectAttributes attributes) {	
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants2.quarterlyPlanForm);
			model.addObject("action", "add");
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightQuarterlyPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);
			
			List<FortnightPlan> FortnightPlanItemList = FortnightPlanService.getFortnightQuarterlyPlanItemList();
			model.addObject("FortnightPlanItemList", FortnightPlanItemList);
			
			obj.setFortnightly_plan_id(FortnightPlan_id);
			
			List<FortnightPlan> FortnightPlan = FortnightPlanService.getQuarterlyPlanManual(obj);
			model.addObject("FortnightPlan", FortnightPlan);
			
			
		}catch (Exception e) {
				logger.error("addFortnightlyPlan : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/add-fortnightly-plan", method = {RequestMethod.GET})
	public ModelAndView addBudgetForm(@ModelAttribute Budget obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants2.addFortnightlyPlan);
			model.addObject("action", "add");
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanCategoryList = FortnightPlanService.getFortnightPlanModuleCategoryList();
			model.addObject("FortnightPlanCategoryList", FortnightPlanCategoryList);
			
			List<FortnightPlan> FortnightPlanCriticalItemList = FortnightPlanService.getFortnightPlanCriticalItemList();
			model.addObject("FortnightPlanCriticalItemList", FortnightPlanCriticalItemList);			
			
		}catch (Exception e) {
				logger.error("addFortnightlyPlan : " + e.getMessage());
		}
		return model;
	}	
	 
	@RequestMapping(value = "/ajax/getWorksListFilterInFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getWorksListFilterInFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getWorksListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/ajax/getTDCRevisions", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getTDCRevisions(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getTDCRevisions(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}	
	
	
	
	@RequestMapping(value = "/ajax/getPeriodsListFilterInFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getPeriodsListFilterInFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getPeriodListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/ajax/getContractListFilterInFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getContractListFilterInFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getContractListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	
	@RequestMapping(value = "/ajax/getWorksListFilterInQuarterlyFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getWorksListFilterInQuarterlyFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getWorksListQuarterlyFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListQuarterlyFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/ajax/getFortnightListFilterInQuarterlyFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getFortnightListFilterInQuarterlyFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getFortnightListQuarterlyFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPeriodListQuarterlyFilter : " + e.getMessage());
		}
		return fortnight;
	}	
	
	@RequestMapping(value = "/ajax/getPeriodsListFilterInQuarterlyFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getPeriodsListFilterInQuarterlyFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getPeriodListQuarterlyFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPeriodListQuarterlyFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/ajax/getItemListFilterInQuarterlyFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getItemListFilterInQuarterlyFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getItemListQuarterlyFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getItemListQuarterlyFilter : " + e.getMessage());
		}
		return fortnight;
	}		
	
	
	
	
	
	@RequestMapping(value = "/ajax/getCategoryListFilterInFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getCategoryListFilterInFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getFortnightPlanCategoryList();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/refresh-execution-activities", method = {RequestMethod.GET})
	public ModelAndView refreshExecutionActivities(RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView("redirect:/");
		try {
			User uObj = (User) session.getAttribute("user");
			boolean flag = FortnightPlanService.refreshExecutionActivities(uObj.getUser_id());
			logger.error("refreshExecutionActivities() : "+flag);
			if(flag) {
				attributes.addFlashAttribute("procedureResult", "Refreshed Execution Activities successfully");
			}else {
				attributes.addFlashAttribute("procedureResult", "Procedure not executed");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("procedureResult", "Oops! Something went wrong");
			logger.error("refreshExecutionActivities() : "+e.getMessage());
		}
		return model;
	}	
	@RequestMapping(value = "/export-fortnightplans", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportFortnightplans(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute FortnightPlan FortnightPlan,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants2.fortnightPlanGrid);
		List<FortnightPlan> dataList = new ArrayList<FortnightPlan>();
		String userId = null;String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			view.setViewName("redirect:/FortnightPlan");
			
			User uObj = (User) session.getAttribute("user");
			FortnightPlan.setUser_id(uObj.getUser_id());
			FortnightPlan.setUser_role_code(uObj.getUser_role_code());			
			
			dataList = FortnightPlanService.getFortnightPlanList(FortnightPlan);
			if(dataList != null && dataList.size() > 0){
			            XSSFWorkbook  workBook = new XSSFWorkbook ();
			            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("FortnightPlan"));
				        workBook.setSheetOrder(sheet.getSheetName(), 0);
				        
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
				        
				        
			            XSSFRow headingRow = sheet.createRow(0);
			            String headerString = "S. No^Category^Contract^Structure^Structure ID^Cum Planned\r\n" + 
			            		"Last Fortnight^Cum Actual\r\n" + 
			            		"Last Fortnight^Plan for\r\n" + 
			            		"Current Fortnight^Actual\r\n" + 
			            		"progress^FortnightPlan ID^Remarks";
			            
			            String[] firstHeaderStringArr = headerString.split("\\^");
			            
			            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
				        	Cell cell = headingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(firstHeaderStringArr[i]);
						}
			            
			            short rowNo = 1;
			            for (FortnightPlan obj : dataList) {
			                XSSFRow row = sheet.createRow(rowNo);
			                int c = 0;
			                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(rowNo);
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCategory());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getContract_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getStructure_type_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getStructure());								
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCum_planned_last_st());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getCum_actual_last_st());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getPlanned_current_st());
						
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getActual_current_st());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getRemarks());
			                
			                rowNo++;
			            }
			            for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
			        		sheet.setColumnWidth(columnIndex, 25 * 200);
						}
		                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		                Date date = new Date();
		                String fileName = "FortnightPlan_"+dateFormat.format(date);
		                
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
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportFortnightPlan : : User Id - "+userId+" - User Name - "+userName+" - "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
	}
	
	
	@RequestMapping(value = "/ajax/getfortnightActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getfortnightActivities(@ModelAttribute FortnightPlan obj,HttpSession session) {
		List<FortnightPlan> FortnightPlans = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			FortnightPlans = FortnightPlanService.getfortnightActivities(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getfortnightActivities : " + e.getMessage());
		}
		return FortnightPlans;
	}
	
	@RequestMapping(value = "/ajax/getFortnightQuarterlyPlanList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getFortnightQuarterlyPlanList(@ModelAttribute FortnightPlan obj,HttpSession session) {
		List<FortnightPlan> FortnightPlans = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			FortnightPlans = FortnightPlanService.getFortnightQuarterlyPlanList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFortnightQuarterlyPlanList : " + e.getMessage());
		}
		return FortnightPlans;
	}	
	
	
	@RequestMapping(value = "/ajax/getFortnightPlanList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getFortnightPlanList(@ModelAttribute FortnightPlan obj,HttpSession session) {
		List<FortnightPlan> FortnightPlans = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			FortnightPlans = FortnightPlanService.getFortnightPlanList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFortnightPlanList : " + e.getMessage());
		}
		return FortnightPlans;
	}	

	


	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(FortnightPlan obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = FortnightPlanService.getTotalRecords(obj, searchParameter);
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
	public List<FortnightPlan> createPaginationData(HttpSession session, int startIndex, int offset, FortnightPlan obj, String searchParameter) {
		List<FortnightPlan> objList = null;
		try {
			objList = FortnightPlanService.getFortnightPlanList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	

	@RequestMapping(value="/get-FortnightPlan",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFortnightPlan(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateFortnightPlan);

			
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanContractList = FortnightPlanService.getFortnightPlanContractList(obj);
			model.addObject("FortnightPlanContractList", FortnightPlanContractList);
			
			List<FortnightPlan> FortnightPlanCategoryList = FortnightPlanService.getFortnightPlanCategoryList();
			model.addObject("FortnightPlanCategoryList", FortnightPlanCategoryList);
			
			List<FortnightPlan> FortnightPlanCriticalItemList = FortnightPlanService.getFortnightPlanCriticalItemList();
			model.addObject("FortnightPlanCriticalItemList", FortnightPlanCriticalItemList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);			
			
			
			List<FortnightPlan> FortnightPlan = FortnightPlanService.getFortnightPlan(obj);
			model.addObject("FortnightPlan", FortnightPlan);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFortnightPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/updateFortnighlytPlanManual/{FortnightPlan_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateFortnighlytPlanManual(@ModelAttribute FortnightPlan obj,@PathVariable("FortnightPlan_id") String FortnightPlan_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateFortnighlytPlanManual);
			
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanContractList = FortnightPlanService.getFortnightPlanContractList(obj);
			model.addObject("FortnightPlanContractList", FortnightPlanContractList);
			
			List<FortnightPlan> FortnightPlanCategoryList = FortnightPlanService.getFortnightPlanModuleCategoryList();
			model.addObject("FortnightPlanCategoryList", FortnightPlanCategoryList);
			
			List<FortnightPlan> FortnightPlanCriticalItemList = FortnightPlanService.getFortnightPlanCriticalItemList();
			model.addObject("FortnightPlanCriticalItemList", FortnightPlanCriticalItemList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);

			obj.setFortnightly_plan_id(FortnightPlan_id);
			
			List<FortnightPlan> FortnightPlan = FortnightPlanService.getFortnightPlanManual(obj);
			model.addObject("FortnightPlan", FortnightPlan);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFortnightPlan : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/get-FortnightPlan/{FortnightPlan_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFortnightPlan(@ModelAttribute FortnightPlan obj,@PathVariable("FortnightPlan_id") String FortnightPlan_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateFortnightPlan);
			
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanContractList = FortnightPlanService.getFortnightPlanContractList(obj);
			model.addObject("FortnightPlanContractList", FortnightPlanContractList);
			
			List<FortnightPlan> FortnightPlanCategoryList = FortnightPlanService.getFortnightPlanCategoryList();
			model.addObject("FortnightPlanCategoryList", FortnightPlanCategoryList);
			
			List<FortnightPlan> FortnightPlanCriticalItemList = FortnightPlanService.getFortnightPlanCriticalItemList();
			model.addObject("FortnightPlanCriticalItemList", FortnightPlanCriticalItemList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);

			obj.setFortnightly_plan_id(FortnightPlan_id);
			
			List<FortnightPlan> FortnightPlan = FortnightPlanService.getFortnightPlan(obj);
			model.addObject("FortnightPlan", FortnightPlan);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFortnightPlan : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value="/update-fortnightly-plan",method=RequestMethod.POST)
	public ModelAndView updateFortnightlyPlan(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/FortnightPlan");

			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			
			boolean flag = FortnightPlanService.updateFortnightlyPlan(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FortnightPlan added successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating FortnightPlan is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateFortnightPlan : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value="/update-quarterly_plan_activities",method=RequestMethod.POST)
	public ModelAndView updateQuarterlyPlanActivities(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/update-quarterly-plan");

			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			
			boolean flag = FortnightPlanService.updateQuarterlyPlanActivities(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Update quarterly Fortnight Plan added successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating quarterly Fortnight Plan is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateQuarterlyPlanActivities : " + e.getMessage());
		}
		return model;
	}
	
	

	@RequestMapping(value="/insert-fortnightly-plan",method=RequestMethod.POST)
	public ModelAndView insertQuarterlyPlan(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {

		    model.setViewName("redirect:/FortnightQuarterlyPlan");
			
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			boolean flag = FortnightPlanService.insertQuarterlyPlan(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Fortnight Quarterly Plan added successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating Fortnight Quarterly Plan is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("insertQuarterlyPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-FortnightPlan",method=RequestMethod.POST)
	public ModelAndView updateFortnightPlan(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/FortnightPlan");

			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			
			boolean flag = FortnightPlanService.updateFortnightPlan(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FortnightPlan updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating FortnightPlan is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateFortnightPlan : " + e.getMessage());
		}
		return model;
	}
	
	
	private String getCellDataType(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup
		//workbook.setForceFormulaRecalculation(true);
		try {
			CellType type = cell.getCellType();
			if (!StringUtils.isEmpty(cell)) {
			    switch (type) {
			        case BOOLEAN:
			            val = String.valueOf(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			        	val = String.valueOf(cell.getNumericCellValue());
			        	if(val.contains("E")){
			        		val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        	}
			       
			            break;
			        case STRING:
			        	try {  
			        		val = cell.getStringCellValue();
			        		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			        		Number number = format.parse(val);
			        		int d = number.intValue();
			        		val = String.valueOf(d);
			        		if(val.contains("E")){
			        			val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        		}
			        	  } catch(NumberFormatException e){  
			        		  val = cell.getStringCellValue();
			        	  }  
			            
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
		}catch(Exception e) {
			try {
				 val = cell.getStringCellValue();
			}catch(Exception e1) {
				val = String.valueOf(cell.getNumericCellValue());
			}
			
		}
	
		return val;
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
