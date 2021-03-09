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
			
			Map<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> reportData = service.getActivitiesReportData(obj);
			
			
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
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
	       
	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        if(!reportData.isEmpty()) {
		        for (Map.Entry<ActivitiesProgressReport, Map<String,List<ActivitiesProgressReport>>> entry : reportData.entrySet()) {  
		            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		        	Map<String,List<ActivitiesProgressReport>> dprDataList = entry.getValue();
		            ActivitiesProgressReport details = entry.getKey();
		            
		            
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(details.getContract_short_name()));
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
					cell.setCellValue("Activities Progress Report ");
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
			        
					/*************************************************************************/				        
					if(dprDataList != null && dprDataList.size() > 0){
						int rowNo = 8;
						for (Map.Entry<String,List<ActivitiesProgressReport>> entry2 : dprDataList.entrySet()) {  
				            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
				        	List<ActivitiesProgressReport> dataList = entry2.getValue();
				            String structure = entry2.getKey();
				            rowNo++;
				            int tempRowNo = rowNo;
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
							dprSheet.addMergedRegion(new CellRangeAddress(tempRowNo,tempRowNo, 3,8));
				            /**********************************************************************/
							String headerString = "Structure^component^Component ID^Activity^Total Scope^Progress For The Period^Cumulative Completed";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length; i++) {		        	
						        cell = headingRow.createCell(i+2);
						        cell.setCellStyle(greenStyle);
								cell.setCellValue(headerStringArr[i]);
							}				
							
					        /***********************************************************************/
							
						    for (ActivitiesProgressReport dObj : dataList) {
						        XSSFRow row = dprSheet.createRow(rowNo);
						        int c = 2;
						        
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
								cell.setCellStyle(sectionStyle);
								cell.setCellValue(Double.parseDouble(dObj.getScope()));
								
								cell = row.createCell(c++);
								cell.setCellStyle(sectionStyle);
								cell.setCellValue(Double.parseDouble(dObj.getCompleted_scope()));
								
								cell = row.createCell(c++);
								cell.setCellStyle(sectionStyle);
								cell.setCellValue(Double.parseDouble(dObj.getCumulative_completed()));
								
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
	        	XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("No Data"));
		        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
	        }
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Activities_Report_"+dateFormat.format(date);
            
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
        font.setFontName(fontName);  //"Times New Roman"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
}
