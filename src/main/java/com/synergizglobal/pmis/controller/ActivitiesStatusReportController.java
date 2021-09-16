package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class ActivitiesStatusReportController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(ActivitiesStatusReportController.class);
	
	@Autowired
	ActivitiesStatusReportService service;


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

	
	@RequestMapping(value = "/activities-status-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView stripChartDPRReport(@ModelAttribute ActivitiesProgressReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.activitiesStatusReport);
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
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInActivitiesStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> projectsList = null;
		try {
			projectsList = service.getProjectsFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInActivitiesStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> worksList = null;
		try {
			worksList = service.getWorksFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInActivitiesStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> contractsList = null;
		try {
			contractsList = service.getContractsFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getFobFilterListInActivitiesStatusReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesStatusReport(@ModelAttribute ActivitiesProgressReport obj) {
		List<ActivitiesProgressReport> fobList = null;
		try {
			fobList = service.getFobFilterListInActivitiesStatusReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getFobFilterListInActivitiesStatusReport : " + e.getMessage());
		}
		return fobList;
	}
	
	@RequestMapping(value = "/generate-activities-status-report", method = {RequestMethod.GET,RequestMethod.POST})
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
			
			//StatusReport details = service.getStripChartDPRReportDetails(obj);
			//List<ActivitiesReport> dprDataList = service.getStripChartDPRReportData(obj);
			
			List<ActivitiesProgressReport> structuresList = service.getStructuresList(obj);
			ActivitiesProgressReport reportData = service.getActivitiesStatusReportData(obj);
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
			/***************************************************************************/
	        
			byte[] blueRGB = new byte[]{(byte)180, (byte)198, (byte)231};
			byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)153};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Garamond";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
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
	        int len  = 0;
	        String structure = null;
	        if(!(StringUtils.isEmpty(reportData))) {
	        
	        	 for (ActivitiesProgressReport zObj : reportData.getStructuressList()) {  
	        		structure = zObj.getFob_id_fk();
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(structure));
			        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
			        
			        XSSFRow dateRow = dprSheet.createRow(2);
			        
			        Cell cell = dateRow.createCell(0);
			      
			        	
			        XSSFRow mainHeadingRow = dprSheet.createRow(1);
			        
			        cell = mainHeadingRow.createCell(0);
			        cell.setCellStyle(centerStyle);
					cell.setCellValue("Activities Progress Report ");
			        for (int i = 1; i < 6; i++) {		        	
				        cell = mainHeadingRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
			        dprSheet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
					/********************************************************/	
			        
			        /********************************************************/	
			        XSSFRow deatilsRow = dprSheet.createRow(2);
			   
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Work ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(reportData.getWork_id_fk() + " - " + (!StringUtils.isEmpty(reportData.getWork_short_name())?reportData.getWork_short_name():reportData.getWork_name()));
					
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 1,5));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(3);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contract ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(reportData.getContract_id() + " - " + (!StringUtils.isEmpty(reportData.getContract_short_name())?reportData.getContract_short_name():reportData.getContract_name()));
			        
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3,3, 1,5));
					
					/********************************************************/
					
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(4);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("Contractor ");
					
					cell = deatilsRow.createCell(1);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(reportData.getContractor_name());
					
					for (int i = 2; i < 6; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4,4, 1,5));
			        
					/********************************************************/
			        
					/*************************************************************************/		
					if(!StringUtils.isEmpty(zObj.getFob_id_fk())) {
						 deatilsRow = dprSheet.createRow(5);
					
					        cell = deatilsRow.createCell(0);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue("Structure ");
							
							cell = deatilsRow.createCell(1);
					        cell.setCellStyle(indexWhiteStyle);
							cell.setCellValue(structure);
							
							for (int i = 2; i < 6; i++) {		        	 
						        cell = deatilsRow.createCell(i);
						        cell.setCellStyle(indexWhiteStyle);
								cell.setCellValue("");
							}	
							dprSheet.addMergedRegion(new CellRangeAddress(5,5, 1,5));
						
					}
					/********************************************************/
			        
					/*************************************************************************/		
						
					if(zObj.getComponentsList() != null && zObj.getComponentsList().size() > 0){
						int rowNo = 5;
						 rowNo++;
				            int tempRowNo = rowNo;
				            XSSFRow structureRow = dprSheet.createRow(rowNo++);
					
				            /**********************************************************************/
							String headerString = "Activity Name^Unit^Scope^Completed^ Start Date ^Finish Date";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i);
							    cell.setCellStyle(blueStyle);
								cell.setCellValue(headerStringArr[i]);
							}				
						for (ActivitiesProgressReport cObj : zObj.getComponentsList()) {  
							 XSSFRow componentRow = dprSheet.createRow(rowNo);
							 int x = 0;
							  cell = componentRow.createCell(x++);
								cell.setCellStyle(componentStyle);
								cell.setCellValue(cObj.getComponent());
								for (int i = 1; i < 6; i++) {		        	
							        cell = componentRow.createCell(i);
							        cell.setCellStyle(indexWhiteStyle);
									cell.setCellValue("");
								}
								dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 0,5));
								
								rowNo++;
					        /***********************************************************************/
						    for (ActivitiesProgressReport dObj : cObj.getActivitiessList()) {
						        XSSFRow row = dprSheet.createRow(rowNo);
						        int c = 0;
						        
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
						    	dprSheet.setColumnWidth(0, 25 * 400);
						    	//dprSheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex, 25 * 150);
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
            String fileName = "Activities_Progress_Report_"+dateFormat.format(date);
            
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

