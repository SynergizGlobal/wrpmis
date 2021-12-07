package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
						    for (ActivitiesProgressReport dObj : cObj.getActivitiessList()) {
						        XSSFRow row = dprSheet.createRow(rowNo);
						        int c = 0;
						        
						        cell = row.createCell(c++);
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(dObj.getComponent_id());						        
						        
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

