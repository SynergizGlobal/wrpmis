package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ActivitiesProgressReportService;
import com.synergizglobal.pmis.Iservice.ActivitiesStatusReportService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;

@Controller
public class ActivitiesProgressReportController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(ActivitiesProgressReportController.class);
	
	@Autowired
	ActivitiesProgressReportService service;
	
	@Autowired
	ActivitiesStatusReportService service1;
	
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
	
	@RequestMapping(value = "/activities-progress-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView stripChartDPRReport(@ModelAttribute ActivitiesProgressReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.activitiesReport);
		try{
			List<ActivitiesProgressReport> contarctsList = service1.getContractsFilterListInActivitiesStatusReport(obj);
			model.addObject("contarctsList", contarctsList);
			
			List<ActivitiesProgressReport> worksList = service1.getWorksFilterListInActivitiesStatusReport(obj);
			model.addObject("worksList", worksList);
			
			List<ActivitiesProgressReport> contractorsList = service.getContractorsFilterListInActivitiesReport(obj);
			model.addObject("contractorsList", contractorsList);
			
			List<ActivitiesProgressReport> hodList = service.getHodFilterListInActivitiesReport(obj);
			model.addObject("hodList", hodList);
			
			List<ActivitiesProgressReport> dyhodList = service.getDyhodFilterListInActivitiesReport(obj);
			model.addObject("dyhodList", dyhodList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("stripChartDPRReport : " + e.getMessage());
		}
		return model;
    }
	
	@RequestMapping(value = "/daily-progress-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView dailyProgressReport(@ModelAttribute ActivitiesProgressReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.dailyProgressReport);
		try{
			List<ActivitiesProgressReport> contarctsList = service1.getContractsFilterListInActivitiesStatusReport(obj);
			model.addObject("contarctsList", contarctsList);
			
			List<ActivitiesProgressReport> worksList = service1.getWorksFilterListInActivitiesStatusReport(obj);
			model.addObject("worksList", worksList);
			
			List<ActivitiesProgressReport> contractorsList = service.getContractorsFilterListInActivitiesReport(obj);
			model.addObject("contractorsList", contractorsList);
			
			List<ActivitiesProgressReport> hodList = service.getHodFilterListInActivitiesReport(obj);
			model.addObject("hodList", hodList);
			
			List<ActivitiesProgressReport> dyhodList = service.getDyhodFilterListInActivitiesReport(obj);
			model.addObject("dyhodList", dyhodList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("stripChartDPRReport : " + e.getMessage());
		}
		return model;
    }	
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> projectsList = null;
		try {
			projectsList = service.getProjectsFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsFilterListInActivitiesReport : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> worksList = null;
		try {
			worksList = service.getWorksFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInActivitiesReport : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> contractsList = null;
		try {
			contractsList = service.getContractsFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInActivitiesReport : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getFobFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> fobList = null;
		try {
			fobList = service.getFobFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFobFilterListInActivitiesReport : " + e.getMessage());
		}
		return fobList;
	}
	
	@RequestMapping(value = "/ajax/getContractorsFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getContractorsFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> contractorsList = null;
		try {
			contractorsList = service.getContractorsFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterListInActivitiesReport : " + e.getMessage());
		}
		return contractorsList;
	}
	
	@RequestMapping(value = "/ajax/getHodFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getHodFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> hodList = null;
		try {
			hodList = service.getHodFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodFilterListInActivitiesReport : " + e.getMessage());
		}
		return hodList;
	}
	
	@RequestMapping(value = "/ajax/getDyhodFilterListInActivitiesReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getDyhodFilterListInActivitiesReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> dyhodList = null;
		try {
			dyhodList = service.getDyhodFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDyhodFilterListInActivitiesReport : " + e.getMessage());
		}
		return dyhodList;
	}
	
	
	
	
	@RequestMapping(value = "/generate-daily-progress-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateDailyProgressReport(@ModelAttribute ActivitiesProgressReport obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		//ModelAndView model = new ModelAndView("redirect:/activities-progress-report");
		try{
			
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date());
			
			String reporting_date = obj.getReporting_date();
			//obj.setReporting_date(DateParser.parse(obj.getReporting_date()));
			
			String from_date = obj.getFrom_date();
			String to_date = obj.getTo_date();
			obj.setFrom_date(DateParser.parse(obj.getFrom_date()));
			obj.setTo_date(DateParser.parse(obj.getFrom_date()));
			
			//ActivitiesReport details = service.getStripChartDPRReportDetails(obj);
			//List<ActivitiesReport> dprDataList = service.getStripChartDPRReportData(obj);
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
			/***************************************************************************/
	        
			byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
	        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        byte[] greyRGB = new byte[]{(byte)211, (byte)211, (byte)211};
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Garamond";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle indexShadedStyle = cellFormating(workBook,greyRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 11;fontName = "Garamond";
	        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle numberStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle remarkWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        

	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        
	        if(StringUtils.isEmpty(obj.getProject_id()) && StringUtils.isEmpty(obj.getWork_id()) && StringUtils.isEmpty(obj.getContract_id())){
	        	List<ActivitiesProgressReport> contractsDetails = service.getContarctDetaisl(obj);
	        	for(ActivitiesProgressReport cObj : contractsDetails) {
	            cObj.setFrom_date(DateParser.parse(from_date)); cObj.setTo_date(DateParser.parse(to_date));
		        Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> reportData = service.getActivitiesReportData(cObj);
		        if(!reportData.isEmpty()) {
			        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) {  
			            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
			            ActivitiesProgressReport details = entry.getKey();
			            
			            
				        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
				        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
				        
				        XSSFRow dateRow = dprSheet.createRow(0);
				        
				        Cell cell = dateRow.createCell(2);
				        cell.setCellStyle(whiteStyle);
						cell.setCellValue("Date : " + report_created_date);
				        for (int i = 3; i < 8; i++) {		        	
					        cell = dateRow.createCell(i);
					        cell.setCellStyle(whiteStyle);
							cell.setCellValue("");
						}	
				        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 2,7));
				        
				        	
				        XSSFRow mainHeadingRow = dprSheet.createRow(2);
				        
				        cell = mainHeadingRow.createCell(2);
				        cell.setCellStyle(greenStyle);
						//cell.setCellValue("Activities Progress Report ");
				        cell.setCellValue("Daily Progress Report");
				        
				        for (int i = 3; i < 8; i++) {		        	
					        cell = mainHeadingRow.createCell(i);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue("");
						}	
				        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 2,7));
						/********************************************************/	
				        
				        /********************************************************/	
				        XSSFRow deatilsRow = dprSheet.createRow(3);
				        
				        cell = deatilsRow.createCell(2);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("Progress on ");
						
						cell = deatilsRow.createCell(3);
				        cell.setCellStyle(indexWhiteStyle);
				        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
				        	cell.setCellValue(from_date);
				        }else {
				        	cell.setCellValue(from_date);
				        }
						
						
						for (int i = 4; i < 8; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 3,7));
						/********************************************************/
				        
						/********************************************************/	
				        deatilsRow = dprSheet.createRow(4);
				        
				        cell = deatilsRow.createCell(2);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("Work ");
						
						cell = deatilsRow.createCell(3);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue(details.getWork_id_fk() + " - " + (!StringUtils.isEmpty(details.getWork_short_name())?details.getWork_short_name():details.getWork_name()));
						
						for (int i = 4; i < 8; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 3,7));
				        
						/********************************************************/
				        
						/********************************************************/	
				        deatilsRow = dprSheet.createRow(5);
				        
				        cell = deatilsRow.createCell(2);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("Contract ");
						
						cell = deatilsRow.createCell(3);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue(details.getContract_id() + " - " + (!StringUtils.isEmpty(details.getContract_short_name())?details.getContract_short_name():details.getContract_name()));
				        
						for (int i = 4; i < 8; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(5,5, 3,7));
						
						/********************************************************/
						
						/********************************************************/	
				        deatilsRow = dprSheet.createRow(6);
				        
				        cell = deatilsRow.createCell(2);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("Contractor ");
						
						cell = deatilsRow.createCell(3);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue(details.getContractor_name());
						
						for (int i = 4; i < 8; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(6,6, 3,7));
						
						
				        deatilsRow = dprSheet.createRow(7);
				        
				        cell = deatilsRow.createCell(2);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("HOD ");
						
						cell = deatilsRow.createCell(3);
				        cell.setCellStyle(indexWhiteStyle);
				        String HOD=cObj.getHod_designation();
				        if(HOD==null || HOD=="")
				        {
				        	HOD="All";
				        }
						cell.setCellValue(HOD);
						
						for (int i = 4; i < 5; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(7,7, 3,4));
						
						
				        cell = deatilsRow.createCell(5);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("DyHOD ");
						
						cell = deatilsRow.createCell(6);
				        cell.setCellStyle(indexWhiteStyle);
				        String DyHOD=cObj.getDyhod_designation();
				        if(DyHOD==null || DyHOD=="")
				        {
				        	DyHOD="All";
				        }			        
						cell.setCellValue(DyHOD);
						
						for (int i = 7; i < 8; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(7,7, 6,7));						
						
				        
						/********************************************************/
						int rowNo = 9;
						String headerString = "Component^Component ID^Activity^Total Scope^Daily Progress^Cumulative Completed";
				        
				        String[] headerStringArr = headerString.split("\\^");
				        
				        XSSFRow headingRow = dprSheet.createRow(rowNo++);
				        for (int i = 0; i < headerStringArr.length; i++) {		        	
					        cell = headingRow.createCell(i+2);
					        cell.setCellStyle(greenStyle);
							cell.setCellValue(headerStringArr[i]);
						}				
						
						/*************************************************************************/		
						
						if(dprDataList != null && dprDataList.size() > 0){
							
							for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) {  
					            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
					        	List<ActivitiesProgressReport> dataList = entry2.getValue();
					            String structure = entry2.getKey();
					            int repeat = 0;
								
							    for (ActivitiesProgressReport dObj : dataList) {
							        XSSFRow row = dprSheet.createRow(rowNo);
							        int c = 2;
							        
							        if(repeat == 0) {
								    	   cell = row.createCell(c++);
											cell.setCellStyle(indexShadedStyle);
											cell.setCellValue("Structure ");
											
									        cell = row.createCell(c++);
											cell.setCellStyle(indexShadedStyle);
											cell.setCellValue(dObj.getStructure());
											
											for (int i = 4; i < 7; i++) {		        	
										        cell = row.createCell(i);
										        cell.setCellStyle(indexShadedStyle);
												cell.setCellValue("");
											}	
											dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 3,7));
											structure = dObj.getStructure();
											rowNo++;repeat++;
											row = dprSheet.createRow(rowNo);
											c = 2;
								       }
								
									cell = row.createCell(c++);
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getComponent());
									
							        cell = row.createCell(c++);
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getComponent_id());
									
									cell = row.createCell(c++);
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getActivity_name());
									
									cell = row.createCell(c++);
									cell.setCellStyle(numberStyle);
									cell.setCellValue(Double.parseDouble(dObj.getScope()));
									
									cell = row.createCell(c++);
									cell.setCellStyle(numberStyle);
									cell.setCellValue(!StringUtils.isEmpty(dObj.getCompleted_scope())?Double.parseDouble(dObj.getCompleted_scope()):0);
									
									cell = row.createCell(c++);
									cell.setCellStyle(numberStyle);
									cell.setCellValue(!StringUtils.isEmpty(dObj.getCumulative_completed())?Double.parseDouble(dObj.getCumulative_completed()):0);
									
							        rowNo++;
							    }
							    String remarks=service.getActivitiesRemarks(structure,obj.getFrom_date());
							    if(remarks!=null && remarks!="" && !remarks.isEmpty()) {
							    	int tempRowNoRemarks = rowNo;
								    
								    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
							        
							        cell = remarksRow.createCell(2);
							        cell.setCellStyle(indexWhiteStyle);
							        
									cell.setCellValue("Remark");
									
							        cell = remarksRow.createCell(3);
							        cell.setCellStyle(remarkWhiteStyle);
							        
									cell.setCellValue(remarks);	 
									for (int i = 4; i < 8; i++) {		        	
								        cell = remarksRow.createCell(i);
								        cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 3,7));
								    
								    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
									     //sheet.autoSizeColumn(columnIndex);
						            	dprSheet.setColumnWidth(columnIndex+2, 25 * 200);
									}
							    }
						
							}
							
							
						}else {
							
							List<ActivitiesProgressReport> dataList = service.getStructureRemarks(cObj);					        	
				        	
					        for (ActivitiesProgressReport dObj : dataList) 
					        {
					        	int c=0;
						        		XSSFRow row = dprSheet.createRow(rowNo++);

							    	   cell = row.createCell(c++);
										cell.setCellStyle(indexShadedStyle);
										cell.setCellValue("Structure ");
										
								        cell = row.createCell(c++);
										cell.setCellStyle(indexShadedStyle);
										cell.setCellValue(dObj.getStructure());
										
										for (int i = 2; i < 6; i++) {		        	
									        cell = row.createCell(i);
									        cell.setCellStyle(indexShadedStyle);
											cell.setCellValue("");
										}	
										dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,5));

										
						        String remarks=dObj.getRemarks();
								if(remarks!=null && remarks!="" && !remarks.isEmpty())
								{
								    
								    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
							        
							        cell = remarksRow.createCell(0);
							        cell.setCellStyle(indexWhiteStyle);
							        
									cell.setCellValue("Remark");
									
							        cell = remarksRow.createCell(1);
							        cell.setCellStyle(remarkWhiteStyle);
							        
									cell.setCellValue(remarks);												

								
									for (int i = 2; i < 6; i++) {		        	
								        cell = remarksRow.createCell(i);
								        cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,5));	
								}
								else
								{					        	
					        	
							    int tempRowNoRemarks = rowNo;
							    
							    XSSFRow remarksRow = dprSheet.createRow(tempRowNoRemarks);
						        
						        cell = remarksRow.createCell(0);
						        cell.setCellStyle(indexWhiteStyle);
						        
						        String remarks1="No Progress for the day";
								cell.setCellValue(remarks1);
								
								for (int i = 1; i < 6; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 0,5));
								}
								rowNo=rowNo+1;	
					        }
						}
			            
			        }
		        }else {
		        		 XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
					     workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
					     XSSFRow dateRow = dprSheet.createRow(0);
					        
					        Cell cell = dateRow.createCell(2);
					        cell.setCellStyle(whiteStyle);
							cell.setCellValue("Date : " + report_created_date);
					        for (int i = 3; i < 8; i++) {		        	
						        cell = dateRow.createCell(i);
						        cell.setCellStyle(whiteStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 2,7));
					        
					        	
					        XSSFRow mainHeadingRow = dprSheet.createRow(2);
					        
					        cell = mainHeadingRow.createCell(2);
					        cell.setCellStyle(greenStyle);
							//cell.setCellValue("Activities Progress Report ");
					        cell.setCellValue("Daily Progress Report");
					        
					        for (int i = 3; i < 8; i++) {		        	
						        cell = mainHeadingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 2,7));
							/********************************************************/	
					        
					        /********************************************************/	
					        XSSFRow deatilsRow = dprSheet.createRow(3);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Progress on ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
					        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
					        	cell.setCellValue(from_date);
					        }else {
					        	cell.setCellValue(from_date);
					        }
							
							
							for (int i = 4; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 3,7));
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(4);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Work ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getWork_id_fk() + " - " + (!StringUtils.isEmpty(cObj.getWork_short_name())?cObj.getWork_short_name():cObj.getWork_name()));
							
							for (int i = 4; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 3,7));
					        
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(5);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contract ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getContract_id() + " - " + (!StringUtils.isEmpty(cObj.getContract_short_name())?cObj.getContract_short_name():cObj.getContract_name()));
					        
							for (int i = 4; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 3,7));
							
							/********************************************************/
							
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(6);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contractor ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getContractor_name());
							
							for (int i = 4; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(6,6, 3,7));
							
							
					        deatilsRow = dprSheet.createRow(7);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("HOD ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
					        String HOD=cObj.getHod_designation();
					        if(HOD==null || HOD=="")
					        {
					        	HOD="All";
					        }
							cell.setCellValue(HOD);
							
							for (int i = 5; i < 6; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(7,7, 3,4));
							
							
					        cell = deatilsRow.createCell(5);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("DyHOD ");
							
							cell = deatilsRow.createCell(6);
					        cell.setCellStyle(indexWhiteStyle);
					        String DyHOD=cObj.getDyhod_designation();
					        
					        if(DyHOD==null || DyHOD=="")
					        {
					        	DyHOD="All";
					        }			        
							cell.setCellValue(DyHOD);
							
							for (int i = 7; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(7,7, 6,7));								
						    
							String structure = cObj.getStructure();
							String remarks=service.getActivitiesRemarks(structure,obj.getFrom_date());
							if(remarks!=null && remarks!="" && !remarks.isEmpty())
							{
							    
							    XSSFRow remarksRow = dprSheet.createRow(8);
						        
						        cell = remarksRow.createCell(0);
						        cell.setCellStyle(indexWhiteStyle);
						        
								cell.setCellValue("Remark");
								
						        cell = remarksRow.createCell(1);
						        cell.setCellStyle(remarkWhiteStyle);
						        
								cell.setCellValue(remarks);												

							
								for (int i = 2; i < 6; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(8,8, 2,7));	
							}
							else
							{							
							    XSSFRow remarksRow = dprSheet.createRow(8);
						        
						        cell = remarksRow.createCell(2);
						        cell.setCellStyle(indexWhiteStyle);
						        
						        String remarks1="No Progress for the day";
								cell.setCellValue(remarks1);
								
								for (int i = 3; i < 8; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(8,8, 2,7));
							}
		        }
	        }
	       }else
	        {
	        	List<ActivitiesProgressReport> contractsDetails = service.getContarctDetaisl(obj);
	        	for(ActivitiesProgressReport cObj : contractsDetails) {
			        //String contractname=service.getContractName(obj.getContract_id());
	        		String contractname = cObj.getContract_short_name();
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(contractname));
			        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
			        cObj.setFrom_date(DateParser.parse(from_date)); cObj.setTo_date(DateParser.parse(to_date));
			        XSSFRow dateRow = dprSheet.createRow(0);
			        
			        Cell cell = dateRow.createCell(0);
			        cell.setCellStyle(whiteStyle);
					cell.setCellValue("Date : " + report_created_date);
			        for (int i = 1; i < 6; i++) {		        	
				        cell = dateRow.createCell(i);
				        cell.setCellStyle(whiteStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 0,5));
			        
			        	
			        XSSFRow mainHeadingRow = dprSheet.createRow(2);
			        
			        cell = mainHeadingRow.createCell(0);
			        cell.setCellStyle(greenStyle);
					//cell.setCellValue("Activities Progress Report ");
			        cell.setCellValue("Daily Progress Report");
			        
			        for (int i = 1; i < 6; i++) {		        	
				        cell = mainHeadingRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 0,5));
					/********************************************************/	
			        
			        /********************************************************/	
			        XSSFRow deatilsRow = dprSheet.createRow(3);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Progress on ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
			        	cell.setCellValue(from_date);
			        }else {
			        	cell.setCellValue(from_date);
			        }
					
					
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 1,5));
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(4);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Work ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        String workname=service.getWorkName(cObj.getWork_id());
					cell.setCellValue(cObj.getWork_id()+" - "+workname);
					
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 1,5));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(5);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contract ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        
					cell.setCellValue(cObj.getContract_id() + " - " + contractname);
			        
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,5));
					
					/********************************************************/
					
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(6);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contractor ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        String contractorname=service.getContractorName(cObj.getContractor_id());
					cell.setCellValue(contractorname);
					
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(6,6, 1,5));
					
					
			        deatilsRow = dprSheet.createRow(7);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("HOD ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        String HOD=cObj.getHod_designation();
			        if(HOD==null || HOD=="")
			        {
			        	HOD="All";
			        }
					cell.setCellValue(HOD);
					
					for (int i = 2; i < 3; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(7,7, 1,2));
					
					
			        cell = deatilsRow.createCell(3);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("DyHOD ");
					
					cell = deatilsRow.createCell(4);
			        cell.setCellStyle(indexWhiteStyle);
			        String DyHOD=cObj.getDyhod_designation();
			        if(DyHOD==null || DyHOD=="")
			        {
			        	DyHOD="All";
			        }			        
					cell.setCellValue(DyHOD);
					
					for (int i = 5; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(7,7, 4,5));	
					
			        
					/********************************************************/
			        
					/*************************************************************************/				        
						int rowNo = 9;
				            String structure = obj.getFob_id_fk();
					/* if(!StringUtils.isEmpty(structure)) {
					 	 	rowNo++;
					         int tempRowNo = rowNo;
					         XSSFRow structureRow = dprSheet.createRow(rowNo++);
					        cObj.setFob_id_fk(structure);
					        cell = structureRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Structure ");
							
							cell = structureRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(structure);
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = structureRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(tempRowNo,tempRowNo, 3,8));
					 }*/
							
				            /**********************************************************************/
							String headerString = "Component^Component ID^Activity^Total Scope^Daily Progress^Cumulative Completed";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue(headerStringArr[i]);
							}
					        
					        Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>>   reportData = service.getActivitiesReportData(cObj);
					        if(!reportData.isEmpty()) 
					        {
						        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) 
						        {  
						        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
						        	if(dprDataList != null && dprDataList.size() > 0)
						        	{
										for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) 
										{  int repeat = 0;
								        	List<ActivitiesProgressReport> dataList = entry2.getValue();					        	
						        	
									        for (ActivitiesProgressReport dObj : dataList) {
										        XSSFRow row = dprSheet.createRow(rowNo);
										        int c = 0;
										        if(repeat == 0) {
											    	   cell = row.createCell(c++);
														cell.setCellStyle(indexShadedStyle);
														cell.setCellValue("Structure ");
														
												        cell = row.createCell(c++);
														cell.setCellStyle(indexShadedStyle);
														cell.setCellValue(dObj.getStructure());
														
														for (int i = 2; i < 6; i++) {		        	
													        cell = row.createCell(i);
													        cell.setCellStyle(indexShadedStyle);
															cell.setCellValue("");
														}	
														dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,5));
														 structure = dObj.getStructure();
														rowNo++;repeat++;
														row = dprSheet.createRow(rowNo);
														c = 0;
											    }
												structure = dObj.getStructure();
												cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getComponent());
												
										        cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getComponent_id());
												
												cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getActivity_name());
												
												cell = row.createCell(c++);
												cell.setCellStyle(numberStyle);
												cell.setCellValue(Double.parseDouble(dObj.getScope()));
												
												cell = row.createCell(c++);
												cell.setCellStyle(numberStyle);
												cell.setCellValue(Double.parseDouble(dObj.getCompleted_scope()==null?"0":dObj.getCompleted_scope()));
												
												cell = row.createCell(c++);
												cell.setCellStyle(numberStyle);
												cell.setCellValue(!StringUtils.isEmpty(dObj.getCumulative_completed())?Double.parseDouble(dObj.getCumulative_completed()):0);
												
										        rowNo++;
									        }
									        String remarks=service.getActivitiesRemarks(structure,obj.getFrom_date());
											if(remarks!=null && remarks!="" && !remarks.isEmpty())
											{
											    int tempRowNoRemarks = rowNo;
											    
											    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
										        
										        cell = remarksRow.createCell(0);
										        cell.setCellStyle(indexWhiteStyle);
										        
												cell.setCellValue("Remark");
												
										        cell = remarksRow.createCell(1);
										        cell.setCellStyle(remarkWhiteStyle);
										        
												cell.setCellValue(remarks);												
	
											
												for (int i = 2; i < 6; i++) {		        	
											        cell = remarksRow.createCell(i);
											        cell.setCellStyle(indexWhiteStyle);
													cell.setCellValue("");
												}	
												dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 1,5));	
											}
										}
						        	}else {
						        		
								        String remarks=service.getActivitiesRemarks(structure,obj.getFrom_date());
										if(remarks!=null && remarks!="" && !remarks.isEmpty())
										{
										    int tempRowNoRemarks = rowNo;
										    
										    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
									        
									        cell = remarksRow.createCell(0);
									        cell.setCellStyle(indexWhiteStyle);
									        
											cell.setCellValue("Remark");
											
									        cell = remarksRow.createCell(1);
									        cell.setCellStyle(remarkWhiteStyle);
									        
											cell.setCellValue(remarks);												

										
											for (int i = 2; i < 6; i++) {		        	
										        cell = remarksRow.createCell(i);
										        cell.setCellStyle(indexWhiteStyle);
												cell.setCellValue("");
											}	
											dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 1,5));	
										}
										else
										{
							        	    int tempRowNoRemarks = rowNo;
										    
										    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
									        
									        cell = remarksRow.createCell(0);
									        cell.setCellStyle(indexWhiteStyle);
									        
									        String remarks1="No Progress for the day";
											cell.setCellValue(remarks1);
											
											for (int i = 1; i < 6; i++) {		        	
										        cell = remarksRow.createCell(i);
										        cell.setCellStyle(indexWhiteStyle);
												cell.setCellValue("");
											}	
											dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 0,5));
										}
						        	}
						        }		
					        }
					        else
					        {	
					        	List<ActivitiesProgressReport> dataList = service.getStructureRemarks(cObj);					        	
					        	
						        for (ActivitiesProgressReport dObj : dataList) 
						        {
						        	int c=0;
							        		XSSFRow row = dprSheet.createRow(rowNo++);

								    	   cell = row.createCell(c++);
											cell.setCellStyle(indexShadedStyle);
											cell.setCellValue("Structure ");
											
									        cell = row.createCell(c++);
											cell.setCellStyle(indexShadedStyle);
											cell.setCellValue(dObj.getStructure());
											
											for (int i = 2; i < 6; i++) {		        	
										        cell = row.createCell(i);
										        cell.setCellStyle(indexShadedStyle);
												cell.setCellValue("");
											}	
											dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,5));

											
							        String remarks=dObj.getRemarks();
									if(remarks!=null && remarks!="" && !remarks.isEmpty())
									{
									    
									    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
								        
								        cell = remarksRow.createCell(0);
								        cell.setCellStyle(indexWhiteStyle);
								        
										cell.setCellValue("Remark");
										
								        cell = remarksRow.createCell(1);
								        cell.setCellStyle(remarkWhiteStyle);
								        
										cell.setCellValue(remarks);												

									
										for (int i = 2; i < 6; i++) {		        	
									        cell = remarksRow.createCell(i);
									        cell.setCellStyle(indexWhiteStyle);
											cell.setCellValue("");
										}	
										dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,5));	
									}
									else
									{					        	
						        	
								    int tempRowNoRemarks = rowNo;
								    
								    XSSFRow remarksRow = dprSheet.createRow(tempRowNoRemarks);
							        
							        cell = remarksRow.createCell(0);
							        cell.setCellStyle(indexWhiteStyle);
							        
							        String remarks1="No Progress for the day";
									cell.setCellValue(remarks1);
									
									for (int i = 1; i < 6; i++) {		        	
								        cell = remarksRow.createCell(i);
								        cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 0,5));
									}
									rowNo=rowNo+1;	
						        }
					        	

					        }
						    
						    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
							     //sheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex+2, 25 * 200);
				            	dprSheet.setColumnWidth(0, 27 * 200);
				            	dprSheet.setColumnWidth(1, 25 * 200);
				            	dprSheet.setColumnWidth(2, 30 * 200);
				            	dprSheet.setColumnWidth(3, 30 * 200);				            	
							}
						}
					
	        }	
					        	
	        
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Daily_Progress_Report_"+dateFormat.format(date);
            
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
	
	@RequestMapping(value = "/generate-activities-dpr-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateStripChartDPRReport(@ModelAttribute ActivitiesProgressReport obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		//ModelAndView model = new ModelAndView("redirect:/activities-progress-report");
		try{
			
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date());
			
			String reporting_date = obj.getReporting_date();
			//obj.setReporting_date(DateParser.parse(obj.getReporting_date()));
			
			String from_date = obj.getFrom_date();
			String to_date = obj.getTo_date();
			obj.setFrom_date(DateParser.parse(obj.getFrom_date()));
			obj.setTo_date(DateParser.parse(obj.getTo_date()));
			
			//ActivitiesReport details = service.getStripChartDPRReportDetails(obj);
			//List<ActivitiesReport> dprDataList = service.getStripChartDPRReportData(obj);
			
			
			
			
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
			/***************************************************************************/
	        
			byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
	        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        byte[] greyRGB = new byte[]{(byte)211, (byte)211, (byte)211};
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Garamond";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle indexShadedStyle = cellFormating(workBook,greyRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 11;fontName = "Garamond";
	        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle numberStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle remarkWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        if(StringUtils.isEmpty(obj.getProject_id()) && StringUtils.isEmpty(obj.getWork_id()) && StringUtils.isEmpty(obj.getContract_id()))
	        {
	        	List<ActivitiesProgressReport> contractsDetails = service.getContarctDetaisl(obj);
	        	for(ActivitiesProgressReport cObj : contractsDetails) {
				    cObj.setFrom_date(DateParser.parse(from_date)); cObj.setTo_date(DateParser.parse(to_date));
				    Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> reportData = service.getActivitiesReportData(cObj);
			        if(!reportData.isEmpty()) {
				        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) {  
				            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
				        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
				            ActivitiesProgressReport details = entry.getKey();
				            
				            
					        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
					        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
					        
					        XSSFRow dateRow = dprSheet.createRow(0);
					        
					        Cell cell = dateRow.createCell(2);
					        cell.setCellStyle(whiteStyle);
							cell.setCellValue("Date : " + report_created_date);
					        for (int i = 3; i < 9; i++) {		        	
						        cell = dateRow.createCell(i);
						        cell.setCellStyle(whiteStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 2,8));
					        
					        	
					        XSSFRow mainHeadingRow = dprSheet.createRow(2);
					        
					        cell = mainHeadingRow.createCell(2);
					        cell.setCellStyle(greenStyle);
							//cell.setCellValue("Activities Progress Report ");
					        cell.setCellValue("Daily Report For the Period");
					        
					        for (int i = 3; i < 9; i++) {		        	
						        cell = mainHeadingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 2,8));
							/********************************************************/	
					        
					        /********************************************************/	
					        XSSFRow deatilsRow = dprSheet.createRow(3);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Period ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
					        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
					        	cell.setCellValue(from_date + " to " + to_date);
					        }else {
					        	cell.setCellValue(from_date);
					        }
							
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 3,8));
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(4);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Work ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(details.getWork_id_fk() + " - " + (!StringUtils.isEmpty(details.getWork_short_name())?details.getWork_short_name():details.getWork_name()));
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 3,8));
					        
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(5);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contract ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(details.getContract_id() + " - " + (!StringUtils.isEmpty(details.getContract_short_name())?details.getContract_short_name():details.getContract_name()));
					        
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 3,8));
							
							/********************************************************/
							
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(6);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contractor ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(details.getContractor_name());
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(6,6, 3,8));
					        
							/********************************************************/
							int rowNo = 8;
							String headerString = "Structure^component^Component ID^Activity^Total Scope^Progress For The Period^Cumulative Completed";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i+2);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue(headerStringArr[i]);
							}	
							/*************************************************************************/				        
							if(dprDataList != null && dprDataList.size() > 0){
								
								for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) {  
									int repeat = 0;
						            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
						        	List<ActivitiesProgressReport> dataList = entry2.getValue();
						            String structure = entry2.getKey();
									/* int tempRowNo = rowNo;
									XSSFRow structureRow = dprSheet.createRow(rowNo++);
									
									cell = structureRow.createCell(2);
									cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("Structure ");
									
									cell = structureRow.createCell(3);
									cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue(structure);
									
									for (int i = 4; i < 9; i++) {		        	
									    cell = structureRow.createCell(i);
									    cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(tempRowNo,tempRowNo, 3,8));*/
						            /**********************************************************************/
										
									
							        /***********************************************************************/
									
								    for (ActivitiesProgressReport dObj : dataList) {
								        XSSFRow row = dprSheet.createRow(rowNo);
								        int c = 0;
								        if(repeat == 0) {
									    	   cell = row.createCell(c++);
												cell.setCellStyle(indexShadedStyle);
												cell.setCellValue("Structure ");
												
										        cell = row.createCell(c++);
												cell.setCellStyle(indexShadedStyle);
												cell.setCellValue(dObj.getStructure());
												
												for (int i = 4; i < 9; i++) {		        	
											        cell = row.createCell(i);
											        cell.setCellStyle(indexShadedStyle);
													cell.setCellValue("");
												}	
												dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 3,8));
												 structure = dObj.getStructure();
												rowNo++;repeat++;
												row = dprSheet.createRow(rowNo);
												c = 0;
									    }
								        cell = row.createCell(c++);
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(dObj.getStructure());
										
										cell = row.createCell(c++);
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(dObj.getComponent());
										
								        cell = row.createCell(c++);
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(dObj.getComponent_id());
										
										cell = row.createCell(c++);
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(dObj.getActivity_name());
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(Double.parseDouble(dObj.getScope()));
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(Double.parseDouble(dObj.getCompleted_scope()==null?"0":dObj.getCompleted_scope()));
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(!StringUtils.isEmpty(dObj.getCumulative_completed())?Double.parseDouble(dObj.getCumulative_completed()):0);
										
								        rowNo++;
								    }
								    
								    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
									     //sheet.autoSizeColumn(columnIndex);
						            	dprSheet.setColumnWidth(columnIndex+2, 25 * 200);
									}
								}
								
								
							}
				            
				        }
			        }else {
			        	 XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
					     workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
					     XSSFRow dateRow = dprSheet.createRow(0);
					        
					        Cell cell = dateRow.createCell(2);
					        cell.setCellStyle(whiteStyle);
							cell.setCellValue("Date : " + report_created_date);
					        for (int i = 3; i < 9; i++) {		        	
						        cell = dateRow.createCell(i);
						        cell.setCellStyle(whiteStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 2,8));
					        
					        	
					        XSSFRow mainHeadingRow = dprSheet.createRow(2);
					        
					        cell = mainHeadingRow.createCell(2);
					        cell.setCellStyle(greenStyle);
							//cell.setCellValue("Activities Progress Report ");
					        cell.setCellValue("Daily Report For the Period");
					        
					        for (int i = 3; i < 9; i++) {		        	
						        cell = mainHeadingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 2,8));
							/********************************************************/	
					        
					        /********************************************************/	
					        XSSFRow deatilsRow = dprSheet.createRow(3);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Progress on ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
					        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
					        	cell.setCellValue(from_date);
					        }else {
					        	cell.setCellValue(from_date);
					        }
							
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 3,8));
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(4);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Work ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getWork_id_fk() + " - " + (!StringUtils.isEmpty(cObj.getWork_short_name())?cObj.getWork_short_name():cObj.getWork_name()));
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 3,8));
					        
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(5);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contract ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getContract_id() + " - " + (!StringUtils.isEmpty(cObj.getContract_short_name())?cObj.getContract_short_name():cObj.getContract_name()));
					        
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 3,8));
							
							/********************************************************/
							
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(6);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contractor ");
							
							cell = deatilsRow.createCell(3);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getContractor_name());
							
							for (int i = 4; i < 9; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(6,6, 3,8));
						    
						    XSSFRow remarksRow = dprSheet.createRow(7);
					        
					        cell = remarksRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
					        
					        String remarks="No Progress for the day";
							cell.setCellValue(remarks);
							
							for (int i = 3; i < 9; i++) {		        	
						        cell = remarksRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(7,7, 2,8));
		        }
	        }
	        }
	        else
	        {
	        	List<ActivitiesProgressReport> contractsDetails = service.getContarctDetaisl(obj);
	        	for(ActivitiesProgressReport cObj : contractsDetails) {
				    cObj.setFrom_date(DateParser.parse(from_date)); cObj.setTo_date(DateParser.parse(to_date));
			        //String contractname=service.getContractName(obj.getContract_id());
					String contractname = cObj.getContract_short_name();
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(contractname));
			        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
			        
			        XSSFRow dateRow = dprSheet.createRow(0);
			        
			        Cell cell = dateRow.createCell(0);
			        cell.setCellStyle(whiteStyle);
					cell.setCellValue("Date : " + report_created_date);
			        for (int i = 1; i < 7; i++) {		        	
				        cell = dateRow.createCell(i);
				        cell.setCellStyle(whiteStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 0,6));
			        
			        	
			        XSSFRow mainHeadingRow = dprSheet.createRow(2);
			        
			        cell = mainHeadingRow.createCell(0);
			        cell.setCellStyle(greenStyle);
					//cell.setCellValue("Activities Progress Report ");
			        cell.setCellValue("Report for the period");
			        
			        for (int i = 1; i < 7; i++) {		        	
				        cell = mainHeadingRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 0,6));
					/********************************************************/	
			        
			        /********************************************************/	
			        XSSFRow deatilsRow = dprSheet.createRow(3);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Period ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
			        	cell.setCellValue(from_date + " to " + to_date);
			        }else {
			        	cell.setCellValue(from_date);
			        }
					
					
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 1,6));
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(4);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Work ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        String workname=service.getWorkName(cObj.getWork_id());
					cell.setCellValue(cObj.getWork_id()+" - "+workname);
					
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 1,6));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(5);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contract ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        
					cell.setCellValue(cObj.getContract_id() + " - " + contractname);
			        
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,6));
					
					/********************************************************/
					
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(6);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contractor ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        String contractorname=service.getContractorName(cObj.getContractor_id());
					cell.setCellValue(contractorname);
					
					for (int i = 2; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(6,6, 1,6));
			        
					/********************************************************/
			        
					/*************************************************************************/				        
						int rowNo = 8;
					 String structure = obj.getFob_id_fk();
					 cObj.setFob_id_fk(structure);
					 cObj.setFob_id_fk(structure);
					  rowNo++;
					  /* int tempRowNo = rowNo;
					 XSSFRow structureRow = dprSheet.createRow(rowNo++);
					
					 cell = structureRow.createCell(2);
					 cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Structure ");
					
					cell = structureRow.createCell(3);
					 cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(structure);
					
					for (int i = 4; i < 9; i++) {		        	
					     cell = structureRow.createCell(i);
					     cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(tempRowNo,tempRowNo, 3,8));*/
				            /**********************************************************************/
							String headerString = "Structure^Component^Component ID^Activity^Total Scope^Progress For The Period^Cumulative Completed";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue(headerStringArr[i]);
							}	
					       
					        Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> reportData = service.getActivitiesReportData(cObj);
					        if(!reportData.isEmpty()) 
					        {
						        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) 
						        {  
						        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
						        	if(dprDataList != null && dprDataList.size() > 0)
						        	{
										for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) 
										{   int repeat = 0;
								        	List<ActivitiesProgressReport> dataList = entry2.getValue();					        	
								        	
									        for (ActivitiesProgressReport dObj : dataList) {
										        XSSFRow row = dprSheet.createRow(rowNo);
										        int c = 0;
										      if(repeat == 0) {
										    	   cell = row.createCell(c++);
													cell.setCellStyle(indexShadedStyle);
													cell.setCellValue("Structure ");
													
											        cell = row.createCell(c++);
													cell.setCellStyle(indexShadedStyle);
													cell.setCellValue(dObj.getStructure());
													
													for (int i = 2; i < 7; i++) {		        	
												        cell = row.createCell(i);
												        cell.setCellStyle(indexShadedStyle);
														cell.setCellValue("");
													}	
													dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,6));
													 structure = dObj.getStructure();
													rowNo++;repeat++;
													row = dprSheet.createRow(rowNo);
													c = 0;
										       }
										      
										        cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getStructure());
												
												cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getComponent());
												
										        cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getComponent_id());
												
												cell = row.createCell(c++);
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getActivity_name());
												
												cell = row.createCell(c++);
												cell.setCellStyle(numberStyle);
												cell.setCellValue(Double.parseDouble(dObj.getScope()));
												
												cell = row.createCell(c++);
												cell.setCellStyle(numberStyle);
												cell.setCellValue(Double.parseDouble(dObj.getCompleted_scope()==null?"0":dObj.getCompleted_scope()));
												
												cell = row.createCell(c++);
												cell.setCellStyle(numberStyle);
												cell.setCellValue(!StringUtils.isEmpty(dObj.getCumulative_completed())?Double.parseDouble(dObj.getCumulative_completed()):0);
												
										        rowNo++;
									        }
										}
						        	}
						        }		
					        }
					        else
					        {
							
								int tempRowNoRemarks = rowNo;
							    
							    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
						        
						        cell = remarksRow.createCell(0);
						        cell.setCellStyle(indexWhiteStyle);
						        
						        String remarks="No Progress for the period";
								cell.setCellValue(remarks);
								
								for (int i = 1; i < 7; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 0,6));
					        }
						    
						    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
							     //sheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex+2, 25 * 200);
				            	dprSheet.setColumnWidth(0, 27 * 200);
				            	dprSheet.setColumnWidth(1, 25 * 200);
				            	dprSheet.setColumnWidth(2, 30 * 200);
				            	dprSheet.setColumnWidth(3, 30 * 200);
							}				        
						}	        
	        }
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Report_for_the_Period_"+dateFormat.format(date);
            
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
        font.setFontName(fontName);  //"Calibri"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
}
