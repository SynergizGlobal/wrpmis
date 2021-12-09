package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.synergizglobal.pmis.Iservice.StructureStatusReportService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;

@Controller
public class StructureStatusReportController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(StructureStatusReportController.class);
	
	@Autowired
	StructureStatusReportService service;


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

	
	@RequestMapping(value = "/structure-status-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView structureDPRReport(@ModelAttribute ActivitiesProgressReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.structureStatusReport);
		try{
			List<ActivitiesProgressReport> contarctsList = service.getContractsFilterListInActivitiesStatusReport(obj);
			model.addObject("contarctsList", contarctsList);
			
			List<ActivitiesProgressReport> worksList = service.getWorksFilterListInActivitiesStatusReport(obj);
			model.addObject("worksList", worksList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("stripChartDPRReport : " + e.getMessage());
		}
		return model;
    }
	
	@RequestMapping(value = "/newline-report-fortheperiod-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView newlineReportforthePeriod(@ModelAttribute ActivitiesProgressReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.newlineReportforthePeriod);
		try{
			List<ActivitiesProgressReport> contarctsList = service.getContractsFilterListInActivitiesStatusReport(obj);
			model.addObject("contarctsList", contarctsList);
			
			List<ActivitiesProgressReport> worksList = service.getWorksFilterListInActivitiesStatusReport(obj);
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
	
	@RequestMapping(value = "/newline-daily-progress-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView newlineDailyProgressReport(@ModelAttribute ActivitiesProgressReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.newlineDailyProgressReport);
		try{
			List<ActivitiesProgressReport> contarctsList = service.getContractsFilterListInActivitiesStatusReport(obj);
			model.addObject("contarctsList", contarctsList);
			
			List<ActivitiesProgressReport> worksList = service.getWorksFilterListInActivitiesStatusReport(obj);
			model.addObject("worksList", worksList);
			
			List<ActivitiesProgressReport> contractorsList = service.getContractsFilterListInActivitiesStatusReport(obj);
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
	
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInStructureStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getProjectsFilterListInStructureStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> projectsList = null;
		try {
			projectsList = service.getProjectsFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInStructureStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getWorksFilterListInStructureStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> worksList = null;
		try {
			worksList = service.getWorksFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInStructureStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getContractsFilterListInStructureStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> contractsList = null;
		try {
			contractsList = service.getContractsFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getFobFilterListInStructureStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getFobFilterListInStructureStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> fobList = null;
		try {
			fobList = service.getFobFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFobFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return fobList;
	}
	
	@RequestMapping(value = "/ajax/getContractorsFilterListInStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getContractorsFilterListInStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> contractorsList = null;
		try {
			contractorsList = service.getContractorsFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterListInActivitiesReport : " + e.getMessage());
		}
		return contractorsList;
	}	
	
	@RequestMapping(value = "/ajax/getHodFilterListInStructureReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getHodFilterListInStructureReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> hodList = null;
		try {
			hodList = service.getHodFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodFilterListInStructureReport : " + e.getMessage());
		}
		return hodList;
	}
	
	@RequestMapping(value = "/ajax/getDyhodFilterListInStructureReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getDyhodFilterListInStructureReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> dyhodList = null;
		try {
			dyhodList = service.getDyhodFilterListInActivitiesReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDyhodFilterListInStructureReport : " + e.getMessage());
		}
		return dyhodList;
	}	
	
	@RequestMapping(value = "/generate-structure-status-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateStructureDPRReport(@ModelAttribute ActivitiesProgressReport obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		//ModelAndView model = new ModelAndView("redirect:/activities-progress-report");
		try{
			
		
			//obj.setReporting_date(DateParser.parse(obj.getReporting_date()));
			

			obj.setFrom_date(DateParser.parse(obj.getFrom_date()));
			obj.setTo_date(DateParser.parse(obj.getTo_date()));
			
			//StatusReport details = service.getStripChartDPRReportDetails(obj);
			//List<ActivitiesReport> dprDataList = service.getStripChartDPRReportData(obj);
			
			ActivitiesProgressReport reportData = service.getActivitiesStatusReportData(obj);
			
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
	        	
	        	List<String> StructureTypes = new ArrayList<>();
	        	
	        	for (ActivitiesProgressReport sTObj : reportData.getStructuressList()) 
	        	{
	        		if(StructureTypes.indexOf(sTObj.getStructure_type())==-1)
	        		{
	        			StructureTypes.add(sTObj.getStructure_type());
	        		}
	        	}
				for (int i2 = 0; i2 < StructureTypes.size(); i2++) 
				{
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(StructureTypes.get(i2)));
			        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
			        
			        
			        XSSFRow dateRow = dprSheet.createRow(2);
			        
			        
			        Cell cell = dateRow.createCell(0);
			      
			        	
			        XSSFRow mainHeadingRow = dprSheet.createRow(1);
			        
			        cell = mainHeadingRow.createCell(0);
			        cell.setCellStyle(centerStyle);
					cell.setCellValue("Structure Status Report ");
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
					cell.setCellValue(reportData.getContract_id() + " - " + (!StringUtils.isEmpty(reportData.getContract_short_name())?reportData.getContract_short_name():reportData.getContract_name()));
			        
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
			        
					/********************************************************/
			        
					/*************************************************************************/		

						 	deatilsRow = dprSheet.createRow(5);
					
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Structure Type");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(StructureTypes.get(i2));
							
							for (int i = 2; i < 7; i++) {		        	 
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,6));
						
					
					/********************************************************/		
							
				int incrementRow=0;
				int loopRow=0;

	        	 for (ActivitiesProgressReport zObj : reportData.getStructuressList()) 
	        	 { 
	        		int appendRowNo=0;

	        		


	        		 if(StructureTypes.get(i2).compareTo(zObj.getStructure_type())==0)
	        		 {
	        		 
	        		
					
					/*************************************************************************/		
					if(!StringUtils.isEmpty(zObj.getFob_id_fk())) 
					{
						if(loopRow==0)
						{
							appendRowNo=7+incrementRow;
						}
						else
						{
							appendRowNo=2+incrementRow;
						}
						 deatilsRow = dprSheet.createRow(appendRowNo);
					
							
							cell = deatilsRow.createCell(0);
					        cell.setCellStyle(structureStyle);
					        if(zObj.getFob_name()!=null)
					        {
					        	cell.setCellValue(zObj.getFob_id_fk()+" ("+zObj.getFob_name()+")");
					        }
					        else
					        {
					        	cell.setCellValue(zObj.getFob_id_fk());
					        }
							
							for (int i = 1; i < 7; i++) {		        	 
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(structureStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(appendRowNo,appendRowNo, 0,6));
							appendRowNo++;
							loopRow++;
						
					}
					/********************************************************/					
		
			        
					/*************************************************************************/		
						
					if(zObj.getComponentsList() != null && zObj.getComponentsList().size() > 0){
						int rowNo = appendRowNo-1;
						 rowNo++;
					
				            /**********************************************************************/
							String headerString = "Component ID^Activity Name^Unit^Scope^Completed^ Start Date ^Finish Date";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i);
							    cell.setCellStyle(blueStyle);
								cell.setCellValue(headerStringArr[i]);
							}

						for (ActivitiesProgressReport cObj : zObj.getComponentsList()) {
							
							var GrLength=0;

							 XSSFRow componentRow = dprSheet.createRow(rowNo);
							 int x = 0;
							  cell = componentRow.createCell(x++);
								cell.setCellStyle(componentStyle);
								cell.setCellValue(cObj.getComponent());
								for (int i = 1; i < 7; i++) {		        	
							        cell = componentRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}
								dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 0,6));
								
								rowNo++;
					        /***********************************************************************/
								List<String> componentsArray = new ArrayList<>();
								int rowValue=rowNo;

						    for (ActivitiesProgressReport dObj : cObj.getActivitiessList()) {
						    	


						        XSSFRow row = dprSheet.createRow(rowNo);
						        int c = 0;
						        
						    	if(componentsArray.indexOf(dObj.getComponent_id())==-1)
						    	{
						    		componentsArray.add(dObj.getComponent_id());
							        cell = row.createCell(c++);
									cell.setCellStyle(activityNameStyle);
									cell.setCellValue(dObj.getComponent_id());							    		
									rowValue++;
						    	}
						    	else
						    	{
							        cell = row.createCell(c++);
									cell.setCellStyle(activityNameStyle);
									cell.setCellValue("");							    		
						    	}
						        
					        
						        
						        cell = row.createCell(c++);
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(dObj.getActivity_name());
								
								cell = row.createCell(c++);
								cell.setCellStyle(sectionStyle);
								cell.setCellValue(dObj.getUnit());
								
								cell = row.createCell(c++);
								cell.setCellStyle(numberStyle);
								cell.setCellValue(Double.parseDouble(dObj.getScope()));
								
								cell = row.createCell(c++);
								cell.setCellStyle(numberStyle);
								cell.setCellValue(Double.parseDouble(dObj.getCompleted()));
								
								cell = row.createCell(c++);
								if(StringUtils.isEmpty(dObj.getActual_start())) {
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getPlanned_start());
								}else {
									cell.setCellStyle(cellStyle);
									cell.setCellValue(dObj.getActual_start());
								}
								
						        cell = row.createCell(c++);
						        if(StringUtils.isEmpty(dObj.getActual_finish())) {
						        	cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getPlanned_finish());
						        }else {
									cell.setCellStyle(cellStyle);
									cell.setCellValue(dObj.getActual_finish());
								}
						       
						        rowNo++;
						    }
						    
						    for(int columnIndex = 1; columnIndex < headerStringArr.length; columnIndex++) {
						    	dprSheet.setColumnWidth(0, 20 * 300);
						    	dprSheet.setColumnWidth(1, 25 * 400);
						    	//dprSheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex, 25 * 150);
							}
						    
						}
						incrementRow=rowNo;
					}
	        	} 
	        		 
				}
				}
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
	
	@RequestMapping(value = "/generate-newline-daily-progress-report", method = {RequestMethod.GET,RequestMethod.POST})
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
	        byte[] orangeLightRGB = new byte[]{(byte)255, (byte)255, (byte)0};
	        
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
	        CellStyle structureTypeStyle = cellFormating(workBook,orangeLightRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,true,isItalicText,fontSize,fontName);

	        

	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        
	        if(StringUtils.isEmpty(obj.getProject_id()) && StringUtils.isEmpty(obj.getWork_id()) && StringUtils.isEmpty(obj.getContract_id())){
	        	List<ActivitiesProgressReport> contractsDetails = service.getContarctDetaisl(obj);
	        	for(ActivitiesProgressReport cObj : contractsDetails) {
	            cObj.setFrom_date(DateParser.parse(from_date)); cObj.setTo_date(DateParser.parse(to_date));
		        Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> reportData = service.getActivitiesReportData(cObj);
		        if(!reportData.isEmpty()) {
		        	List<String> checkStructureType = new ArrayList<>();
			        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) {  
			            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
			            ActivitiesProgressReport details = entry.getKey();
			            
			            
				        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
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
				        cell.setCellValue("Daily Progress Report");
				        
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
						cell.setCellValue("Progress on ");
						
						cell = deatilsRow.createCell(1);
				        cell.setCellStyle(indexWhiteStyle);
				        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
				        	cell.setCellValue(from_date);
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
						cell.setCellValue(details.getWork_id_fk() + " - " + (!StringUtils.isEmpty(details.getWork_short_name())?details.getWork_short_name():details.getWork_name()));
						
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
						cell.setCellValue(details.getContract_id() + " - " + (!StringUtils.isEmpty(details.getContract_short_name())?details.getContract_short_name():details.getContract_name()));
				        
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
						cell.setCellValue(details.getContractor_name());
						
						for (int i = 2; i < 7; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(6,6, 1,6));
						
						
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
						
						for (int i = 5; i < 7; i++) {		        	
					        cell = deatilsRow.createCell(i);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("");
						}	
						dprSheet.addMergedRegion(new CellRangeAddress(7,7, 4,6));						
						
				        
						/********************************************************/
						int rowNo = 9;
						String headerString = "Component^Component ID^Activity^Unit^Total Scope^Daily Progress^Cumulative Completed";
				        
				        String[] headerStringArr = headerString.split("\\^");
				        
				        XSSFRow headingRow = dprSheet.createRow(rowNo++);
				        for (int i = 0; i < headerStringArr.length; i++) {		        	
					        cell = headingRow.createCell(i);
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
					            
					            List<String> StructureTypes = new ArrayList<>();
						        for (ActivitiesProgressReport sObj : dataList) 
						        {
					        		if(StructureTypes.indexOf(sObj.getStructure_type())==-1)
					        		{
					        			StructureTypes.add(sObj.getStructure_type());
					        		}									        
						        }
						        
								for (int i2 = 0; i2 < StructureTypes.size(); i2++) 
								{
									int loopValue=0;					            
								
							    for (ActivitiesProgressReport dObj : dataList) {
							    	
					        		 if(StructureTypes.get(i2).compareTo(dObj.getStructure_type())==0)
					        		 {
					        			 	if(loopValue==0 && checkStructureType.indexOf(dObj.getStructure_type())==-1)
					        			 	{
										        XSSFRow row = dprSheet.createRow(rowNo);
										        int c = 0;									        			 
										        cell = row.createCell(c++);
												cell.setCellStyle(structureTypeStyle);
												cell.setCellValue("Structure Type");
												
										        cell = row.createCell(c++);
												cell.setCellStyle(structureTypeStyle);
												cell.setCellValue(StructureTypes.get(i2));
												
												for (int i = 2; i < 7; i++) {		        	
											        cell = row.createCell(i);
											        cell.setCellStyle(structureTypeStyle);
													cell.setCellValue("");
												}	
												dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,6));
												rowNo++;
										        loopValue++;
										        checkStructureType.add(dObj.getStructure_type());
					        			 	}
					        		 }							    	
							    	
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
									cell.setCellValue(dObj.getComponent());
									
							        cell = row.createCell(c++);
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getComponent_id());
									
									cell = row.createCell(c++);
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getActivity_name());
									
									cell = row.createCell(c++);
									cell.setCellStyle(sectionStyle);
									cell.setCellValue(dObj.getUnit());									
									
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
							        
							        cell = remarksRow.createCell(0);
							        cell.setCellStyle(indexWhiteStyle);
							        
									cell.setCellValue("Remark");
									
							        cell = remarksRow.createCell(1);
							        cell.setCellStyle(remarkWhiteStyle);
							        
									cell.setCellValue(remarks);	 
									for (int i = 2; i < 7; i++) {		        	
								        cell = remarksRow.createCell(i);
								        cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 1,6));
								    

							    }
								}
							    
						
							}
						    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
				            	dprSheet.setColumnWidth(columnIndex, 25 * 200);
				            	dprSheet.setColumnWidth(0, 27 * 200);
				            	dprSheet.setColumnWidth(1, 25 * 200);
				            	dprSheet.setColumnWidth(2, 30 * 200);
				            	dprSheet.setColumnWidth(3, 30 * 200);				            	
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
										
										for (int i = 0; i < 4; i++) {		        	
									        cell = row.createCell(i);
									        cell.setCellStyle(indexShadedStyle);
											cell.setCellValue("");
										}	
										dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 0,3));

										
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

								
									for (int i = 0; i < 4; i++) {		        	
								        cell = remarksRow.createCell(i);
								        cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 0,3));	
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
					        if(dataList.size()==0)
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
						}
			            
			        }
		        }else {
		        		 XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
					     workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
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
							cell.setCellValue(cObj.getWork_id_fk() + " - " + (!StringUtils.isEmpty(cObj.getWork_short_name())?cObj.getWork_short_name():cObj.getWork_name()));
							
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
							cell.setCellValue(cObj.getContract_id() + " - " + (!StringUtils.isEmpty(cObj.getContract_short_name())?cObj.getContract_short_name():cObj.getContract_name()));
					        
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
							cell.setCellValue(cObj.getContractor_name());
							
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
							
							for (int i = 3; i < 4; i++) {		        	
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

							
								for (int i = 0; i < 4; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(8,8, 0,5));	
							}
							else
							{							
							    XSSFRow remarksRow = dprSheet.createRow(8);
						        
						        cell = remarksRow.createCell(0);
						        cell.setCellStyle(indexWhiteStyle);
						        
						        String remarks1="No Progress for the day";
								cell.setCellValue(remarks1);
								
								for (int i = 1; i < 6; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(8,8, 0,5));
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
			        cell.setCellValue("Daily Progress Report");
			        
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
					cell.setCellValue("Progress on ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
			        	cell.setCellValue(from_date);
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
					
					for (int i = 5; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(7,7, 4,6));	
					
			        
					/********************************************************/
			        
					/*************************************************************************/				        
						int rowNo = 9;
				            String structure = obj.getStructure_type_fk();
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
							String headerString = "Component^Component ID^Activity^Unit^Total Scope^Daily Progress^Cumulative Completed";
					        
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
					        	List<String> checkStructureType = new ArrayList<>();
						        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) 
						        {  
						        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
						        	if(dprDataList != null && dprDataList.size() > 0)
						        	{
										for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) 
										{  int repeat = 0;
							        	List<ActivitiesProgressReport> dataList = entry2.getValue();					        	
							        	List<String> StructureTypes = new ArrayList<>();
								        for (ActivitiesProgressReport sObj : dataList) 
								        {
							        		if(StructureTypes.indexOf(sObj.getStructure_type())==-1)
							        		{
							        			StructureTypes.add(sObj.getStructure_type());
							        		}									        
								        }										
						        	
										for (int i2 = 0; i2 < StructureTypes.size(); i2++) 
										{
											int loopValue=0;
											for (ActivitiesProgressReport dObj : dataList) {
										        
								        		 if(StructureTypes.get(i2).compareTo(dObj.getStructure_type())==0)
								        		 {
								        			 	if(loopValue==0 && checkStructureType.indexOf(dObj.getStructure_type())==-1)
								        			 	{
													        XSSFRow row = dprSheet.createRow(rowNo);
													        int c = 0;									        			 
													        cell = row.createCell(c++);
															cell.setCellStyle(structureTypeStyle);
															cell.setCellValue("Structure Type");
															
													        cell = row.createCell(c++);
															cell.setCellStyle(structureTypeStyle);
															cell.setCellValue(StructureTypes.get(i2));
															
															for (int i = 2; i < 7; i++) {		        	
														        cell = row.createCell(i);
														        cell.setCellStyle(structureTypeStyle);
																cell.setCellValue("");
															}	
															dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,6));
															rowNo++;
													        loopValue++;
													        checkStructureType.add(dObj.getStructure_type());
								        			 	}
								        		 }												
												
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
												cell.setCellStyle(sectionStyle);
												cell.setCellValue(dObj.getUnit());												
												
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
	
											
												for (int i = 2; i < 7; i++) {		        	
											        cell = remarksRow.createCell(i);
											        cell.setCellStyle(indexWhiteStyle);
													cell.setCellValue("");
												}	
												dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 1,6));	
											}
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

										
											for (int i = 2; i < 7; i++) {		        	
										        cell = remarksRow.createCell(i);
										        cell.setCellStyle(indexWhiteStyle);
												cell.setCellValue("");
											}	
											dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 1,6));	
										}
										else
										{
							        	    int tempRowNoRemarks = rowNo;
										    
										    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
									        
									        cell = remarksRow.createCell(0);
									        cell.setCellStyle(indexWhiteStyle);
									        
									        String remarks1="No Progress for the day";
											cell.setCellValue(remarks1);
											
											for (int i = 1; i < 7; i++) {		        	
										        cell = remarksRow.createCell(i);
										        cell.setCellStyle(indexWhiteStyle);
												cell.setCellValue("");
											}	
											dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 0,6	));
										}
						        	}
						        }		
					        }
					        else
					        {
								cObj.setStructure_type_fk(structure);
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
						        
						        if(dataList.size()==0)
						        {
						        		int tempRowNoRemarks = rowNo;
								    
								    XSSFRow remarksRow = dprSheet.createRow(tempRowNoRemarks);
							        
							        cell = remarksRow.createCell(0);
							        cell.setCellStyle(indexWhiteStyle);
							        
							        String remarks1="No Progress for the day";
									cell.setCellValue(remarks1);
									
									for (int i = 1; i < 7; i++) {		        	
								        cell = remarksRow.createCell(i);
								        cell.setCellStyle(indexWhiteStyle);
										cell.setCellValue("");
									}	
									dprSheet.addMergedRegion(new CellRangeAddress(tempRowNoRemarks,tempRowNoRemarks, 0,6));						        	
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
	
	@RequestMapping(value = "/generate-newline-activities-dpr-report", method = {RequestMethod.GET,RequestMethod.POST})
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
	        byte[] orangeLightRGB = new byte[]{(byte)255, (byte)255, (byte)0};
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Garamond";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle indexShadedStyle = cellFormating(workBook,greyRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle structureTypeStyle = cellFormating(workBook,orangeLightRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        

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
			        	List<String> checkStructureType = new ArrayList<>();
				        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) {  
				            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
				        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
				            ActivitiesProgressReport details = entry.getKey();
				            
				            
					        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(cObj.getContract_short_name()));
					        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
					        
					        XSSFRow dateRow = dprSheet.createRow(0);
					        
					        Cell cell = dateRow.createCell(0);
					        cell.setCellStyle(whiteStyle);
							cell.setCellValue("Date : " + report_created_date);
					        for (int i = 1; i < 8; i++) {		        	
						        cell = dateRow.createCell(i);
						        cell.setCellStyle(whiteStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 0,7));
					        
					        	
					        XSSFRow mainHeadingRow = dprSheet.createRow(2);
					        
					        cell = mainHeadingRow.createCell(0);
					        cell.setCellStyle(greenStyle);
							//cell.setCellValue("Activities Progress Report ");
					        cell.setCellValue("Report For the Period");
					        
					        for (int i = 1; i < 8; i++) {		        	
						        cell = mainHeadingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 0,7));
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
							
							
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 1,7));
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(4);
					        
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Work ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(details.getWork_id_fk() + " - " + (!StringUtils.isEmpty(details.getWork_short_name())?details.getWork_short_name():details.getWork_name()));
							
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 1,7));
					        
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(5);
					        
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contract ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(details.getContract_id() + " - " + (!StringUtils.isEmpty(details.getContract_short_name())?details.getContract_short_name():details.getContract_name()));
					        
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,7));
							
							/********************************************************/
							
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(6);
					        
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contractor ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(details.getContractor_name());
							
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(6,6, 1,7));
					        
							/********************************************************/
							int rowNo = 8;
							String headerString = "Component^Component ID^Activity^Unit^Total Scope^Progress For The Period^Cumulative Completed^Remark";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i);
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
						            int tCount=rowNo;
						            List<String> StructureTypes = new ArrayList<>();
							        for (ActivitiesProgressReport sObj : dataList) 
							        {
						        		if(StructureTypes.indexOf(sObj.getStructure_type_fk())==-1)
						        		{
						        			StructureTypes.add(sObj.getStructure_type_fk());
						        		}									        
							        }						            
									for (int i2 = 0; i2 < StructureTypes.size(); i2++) 
									{
										int loopValue=0;						            
								    for (ActivitiesProgressReport dObj : dataList) {
								    	
								    	
						        		 if(StructureTypes.get(i2).compareTo(dObj.getStructure_type_fk())==0)
						        		 {
						        			 	if(loopValue==0 && checkStructureType.indexOf(dObj.getStructure_type_fk())==-1)
						        			 	{
											        XSSFRow row = dprSheet.createRow(rowNo);
											        int c = 0;									        			 
											        cell = row.createCell(c++);
													cell.setCellStyle(structureTypeStyle);
													cell.setCellValue("Structure Type");
													
											        cell = row.createCell(c++);
													cell.setCellStyle(structureTypeStyle);
													cell.setCellValue(StructureTypes.get(i2));
													
													for (int i = 2; i < 8; i++) {		        	
												        cell = row.createCell(i);
												        cell.setCellStyle(structureTypeStyle);
														cell.setCellValue("");
													}	
													dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,7));
													rowNo++;
											        loopValue++;
											        checkStructureType.add(dObj.getStructure_type());
						        			 	}
						        		 }								    	
								    	
							        	String remarks=service.getReportforthePeriodActivitiesRemarks(dObj.getStructure(),obj.getFrom_date(),obj.getTo_date());

								        XSSFRow row = dprSheet.createRow(rowNo);
								        int c = 0;
								        if(repeat == 0) {
									    	   cell = row.createCell(0);
												cell.setCellStyle(indexShadedStyle);
												cell.setCellValue("Structure ");
												
										        cell = row.createCell(1);
												cell.setCellStyle(indexShadedStyle);
												cell.setCellValue(dObj.getStructure());
												
												for (int i = 2; i < 8; i++) {		        	
											        cell = row.createCell(i);
											        cell.setCellStyle(indexShadedStyle);
													cell.setCellValue("");
												}	
												dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,7));
												 structure = dObj.getStructure();
												rowNo++;repeat++;
												row = dprSheet.createRow(rowNo);
												c = 0;
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
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(dObj.getUnit());										
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(Double.parseDouble(dObj.getScope()));
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(Double.parseDouble(dObj.getCompleted_scope()==null?"0":dObj.getCompleted_scope()));
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(!StringUtils.isEmpty(dObj.getCumulative_completed())?Double.parseDouble(dObj.getCumulative_completed()):0);
										
										cell = row.createCell(c++);
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(remarks);										
										
								        rowNo++;
								    }
								    if(tCount+2!=rowNo)
								    {
								    	dprSheet.addMergedRegion(new CellRangeAddress(tCount+2,rowNo, 7,7));
								    }
								    rowNo++;
								    
								    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
									     //sheet.autoSizeColumn(columnIndex);
						            	dprSheet.setColumnWidth(columnIndex, 25 * 200);
						            	dprSheet.setColumnWidth(7, 40 * 300);

									}
								    
								}
								}
								
							}
				            
				        }
			        }else {
			        	String contractshortname=cObj.getContract_short_name();
			        	 XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(contractshortname));
					     workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
					     XSSFRow dateRow = dprSheet.createRow(0);
					        
					        Cell cell = dateRow.createCell(2);
					        cell.setCellStyle(whiteStyle);
							cell.setCellValue("Date : " + report_created_date);
					        for (int i = 1; i < 8; i++) {		        	
						        cell = dateRow.createCell(i);
						        cell.setCellStyle(whiteStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 0,7));
					        
					        	
					        XSSFRow mainHeadingRow = dprSheet.createRow(2);
					        
					        cell = mainHeadingRow.createCell(0);
					        cell.setCellStyle(greenStyle);
							//cell.setCellValue("Activities Progress Report ");
					        cell.setCellValue("Report For the Period");
					        
					        for (int i = 1; i < 8; i++) {		        	
						        cell = mainHeadingRow.createCell(i);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue("");
							}	
					        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 0,7));
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
							
							
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 1,7));
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(4);
					        
					        cell = deatilsRow.createCell(2);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Work ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getWork_id_fk() + " - " + (!StringUtils.isEmpty(cObj.getWork_short_name())?cObj.getWork_short_name():cObj.getWork_name()));
							
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 1,7));
					        
							/********************************************************/
					        
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(5);
					        
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contract ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getContract_id() + " - " + (!StringUtils.isEmpty(cObj.getContract_short_name())?cObj.getContract_short_name():cObj.getContract_name()));
					        
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,7));
							
							/********************************************************/
							
							/********************************************************/	
					        deatilsRow = dprSheet.createRow(6);
					        
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Contractor ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(cObj.getContractor_name());
							
							for (int i = 2; i < 8; i++) {		        	
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(6,6, 1,7));
						    
						    XSSFRow remarksRow = dprSheet.createRow(7);
					        
					        cell = remarksRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
					        
					        String remarks="No Progress for the day";
							cell.setCellValue(remarks);
							
							for (int i = 1; i < 8; i++) {		        	
						        cell = remarksRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(7,7, 0,7));
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
			        for (int i = 1; i < 8; i++) {		        	
				        cell = dateRow.createCell(i);
				        cell.setCellStyle(whiteStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(0, 0, 0,7));
			        
			        	
			        XSSFRow mainHeadingRow = dprSheet.createRow(2);
			        
			        cell = mainHeadingRow.createCell(0);
			        cell.setCellStyle(greenStyle);
					//cell.setCellValue("Activities Progress Report ");
			        cell.setCellValue("Report for the period");
			        
			        for (int i = 1; i < 8; i++) {		        	
				        cell = mainHeadingRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 0,7));
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
					
					
					for (int i = 2; i < 8; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 1,7));
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
					
					for (int i = 2; i < 8; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 1,7));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(5);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contract ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
			        
					cell.setCellValue(cObj.getContract_id() + " - " + contractname);
			        
					for (int i = 2; i < 8; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,7));
					
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
					
					for (int i = 2; i < 8; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(6,6, 1,7));
			        
					/********************************************************/
			        
					/*************************************************************************/				        
						int rowNo = 8;
					 String structure = obj.getStructure_type_fk();
					 cObj.setStructure_type_fk(structure);
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
							String headerString = "Component^Component ID^Activity^Unit^Total Scope^Progress For The Period^Cumulative Completed^Remark";
					        
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
								List<String> checkStructureType = new ArrayList<>();
						        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) 
						        {  
						        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
						        	if(dprDataList != null && dprDataList.size() > 0)
						        	{
						        		
										for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) 
										{   int repeat = 0;
											
								        	List<ActivitiesProgressReport> dataList = entry2.getValue();
								        	int tCount=rowNo;
								        	List<String> StructureTypes = new ArrayList<>();
									        for (ActivitiesProgressReport sObj : dataList) 
									        {
								        		if(StructureTypes.indexOf(sObj.getStructure_type())==-1)
								        		{
								        			StructureTypes.add(sObj.getStructure_type());
								        		}									        
									        }
									        
											for (int i2 = 0; i2 < StructureTypes.size(); i2++) 
											{
												int loopValue=0;
										        for (ActivitiesProgressReport dObj : dataList) 
										        {
										        	
									        		 if(StructureTypes.get(i2).compareTo(dObj.getStructure_type())==0)
									        		 {
									        			 	if(loopValue==0 && checkStructureType.indexOf(dObj.getStructure_type())==-1)
									        			 	{
														        XSSFRow row = dprSheet.createRow(rowNo);
														        int c = 0;									        			 
														        cell = row.createCell(c++);
																cell.setCellStyle(structureTypeStyle);
																cell.setCellValue("Structure Type");
																
														        cell = row.createCell(c++);
																cell.setCellStyle(structureTypeStyle);
																cell.setCellValue(StructureTypes.get(i2));
																
																for (int i = 2; i < 8; i++) {		        	
															        cell = row.createCell(i);
															        cell.setCellStyle(structureTypeStyle);
																	cell.setCellValue("");
																}	
																dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,7));
																rowNo++;
														        loopValue++;
														        checkStructureType.add(dObj.getStructure_type());
									        			 	}
									        		 }
										        	
										        	String remarks=service.getReportforthePeriodActivitiesRemarks(dObj.getStructure(),obj.getFrom_date(),obj.getTo_date());
											        XSSFRow row = dprSheet.createRow(rowNo);
											        int c = 0;
											      if(repeat == 0) {
											    	   cell = row.createCell(c++);
														cell.setCellStyle(indexShadedStyle);
														cell.setCellValue("Structure ");
														
												        cell = row.createCell(c++);
														cell.setCellStyle(indexShadedStyle);
														cell.setCellValue(dObj.getStructure());
														
														for (int i = 2; i < 8; i++) {		        	
													        cell = row.createCell(i);
													        cell.setCellStyle(indexShadedStyle);
															cell.setCellValue("");
														}	
														dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 1,7));
														 structure = dObj.getStructure();
														rowNo++;repeat++;
														row = dprSheet.createRow(rowNo);
														c = 0;
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
													cell.setCellStyle(sectionStyle);
													cell.setCellValue(dObj.getUnit());												
													
													cell = row.createCell(c++);
													cell.setCellStyle(numberStyle);
													cell.setCellValue(Double.parseDouble(dObj.getScope()));
													
													cell = row.createCell(c++);
													cell.setCellStyle(numberStyle);
													cell.setCellValue(Double.parseDouble(dObj.getCompleted_scope()==null?"0":dObj.getCompleted_scope()));
													
													cell = row.createCell(c++);
													cell.setCellStyle(numberStyle);
													cell.setCellValue(!StringUtils.isEmpty(dObj.getCumulative_completed())?Double.parseDouble(dObj.getCumulative_completed()):0);
													
													cell = row.createCell(c++);
													cell.setCellStyle(sectionStyle);
													cell.setCellValue(remarks);												
													
											        rowNo++;
											       
										        }
											}
									        if(tCount+2!=rowNo)
									        {
									        	dprSheet.addMergedRegion(new CellRangeAddress(tCount+2,rowNo, 7,7));
									        }
									        rowNo++;
										}
						        	}
						        }		
					        }
					        else
					        {
							
					        	String remarks1=service.getReportforthePeriodActivitiesRemarks(structure,obj.getFrom_date(),obj.getFrom_date());
								int tempRowNoRemarks = rowNo;
							    
							    XSSFRow remarksRow = dprSheet.createRow(rowNo++);
						        
						        cell = remarksRow.createCell(0);
						        cell.setCellStyle(indexWhiteStyle);
						        
						        String remarks="No Progress for the period";
								cell.setCellValue(remarks);
								
								for (int i = 1; i < 8; i++) {		        	
							        cell = remarksRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
							        if(i==7)
							        {
							        	cell.setCellValue(remarks1);
							        }
							        else
							        {
							        	cell.setCellValue("");
							        }
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
						        dprSheet.setColumnWidth(7, 40 * 300);

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

