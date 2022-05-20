package com.synergizglobal.pmis.controller;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
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
import org.xlsx4j.sml.Worksheet;

import com.synergizglobal.pmis.Iservice.RRreportService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.User;
import java.awt.*;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

@Controller
public class RRreportController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public static Logger logger = Logger.getLogger(RRreportController.class);

	@Autowired
	RRreportService service;

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
	
	@RequestMapping(value = "/rr-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView RRReport(@ModelAttribute RandRMain obj, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView(PageConstants.rrReport);
		try {
			List<RandRMain> projectsList = service.getProjectsFilterListInRRReport(obj);
			model.addObject("projectsList", projectsList);

			List<RandRMain> worksList = service.getWorksFilterListInRRReport(obj);
			model.addObject("worksList", worksList);

			List<RandRMain> location = service.getLocationListInRRReport(obj);
			model.addObject("location", location);

			List<RandRMain> structure = service.getTypeOfStructuresFilterListInRRReport(obj);
			model.addObject("structure", structure);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("RRReport : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getProjectListForRRReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getProjectListForRRReportForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getProjectsFilterListInRRReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectListForRRReportForm : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getWorksListForRRReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getWorksListForRRReportForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getWorksFilterListInRRReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForRRReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getLocationsListForRRReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getLocationsListForRRReportForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getLocationListInRRReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLocationsListForRRReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getTypeOfUsesListForRRReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getTypeOfUsesListForRRReportForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getTypeOfStructuresFilterListInRRReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTypeOfUsesListForRRReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/generate-rr-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateRandRReport(@ModelAttribute RandRMain obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		//ModelAndView model = new ModelAndView("redirect:/activities-progress-report");
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date());
			
			SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-YY");
		
			//List<RandRMain> structuresList = service.getStructuresList(obj);
			
			RandRMain reportData = service.getRandRMainData(obj);
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
			/***************************************************************************/
	        
			byte[] blueRGB = new byte[]{(byte)214, (byte)255, (byte)255};
			byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)153};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        byte[] greyRGB = new byte[]{(byte)211, (byte)211, (byte)211};
	        
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Calibri";
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle1 = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle bluetyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	       
	        CellStyle indexWhiteStyle1 = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle indexWhiteStyle2 = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        
	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 11;fontName = "Calibri";
	        CellStyle numberStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle activityNameStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle activityNameStyle1 = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle activityNameStyle2 = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle indexShadedStyle = cellFormating(workBook,greyRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,true,isItalicText,11,fontName);


	        /********************************************************/
          
	        if(!(StringUtils.isEmpty(reportData))){
	        	
       		 	XSSFSheet rrSheet1 = workBook.createSheet(WorkbookUtil.createSafeSheetName("RR Summary Report"));
		        XSSFRow headRow = rrSheet1.createRow(0);
		        
		        Cell cell = headRow.createCell(0);
		        
		        XSSFRow mainHeadingRow = rrSheet1.createRow(1);
		        
		        cell = mainHeadingRow.createCell(0);
		        cell.setCellStyle(bluetyle);
		        String work_d = "";
				if(!StringUtils.isEmpty(obj.getWork_id())) {
					 work_d = reportData.getReport1List().get(0).getWork_short_name()+" - " ;
		        }
		        cell.setCellValue(work_d+" R&R Summary Report");
		        
		        for (int i = 1; i < 5; i++) {		        	
			        cell = mainHeadingRow.createCell(i);
			        cell.setCellStyle(bluetyle);
					cell.setCellValue("");
				}	
		        rrSheet1.addMergedRegion(new CellRangeAddress(1, 1, 0,4));
		int rowNo = 3;

        XSSFRow structureRow = rrSheet1.createRow(rowNo);

        /**********************************************************************/
		String headerString = "Sr No.^Location^Type of Use^Total Structures (No's)^Physical Verification"
				+ "^Removal of Encroachment^Boundry Wall Construction completed^Hand over to construction";
        String[] headerStringArr = headerString.split("\\^");
        
        XSSFRow headingRow = rrSheet1.createRow(rowNo);
        for (int i = 0; i < headerStringArr.length ; i++) {	
        	
    			 cell = headingRow.createCell(i);
    	    	 cell.setCellStyle(bluetyle);
				 cell.setCellValue(headerStringArr[i]);
		}	

        
        
        
        int totalStructures = 0,totalPV = 0,totalRE = 0,totalHC = 0,totalBC = 0;
        rowNo++; int rownum = 5;
        String locName = null;
        int x = 0,z=0,sNo = 1;
	        	 for (RandRMain zObj : reportData.getReport1List()) {  
				            int c = 0;
				            
						   	if(!StringUtils.isEmpty(locName) &&  !locName.equals(zObj.getLocation_name())) {
						   		System.out.println(locName);
						   		 sNo++;
							   	 int firstRow = rownum;
					             int lastRow = rowNo;
					             System.out.println("B"+firstRow+":B"+lastRow);
					             if(firstRow != lastRow) {
									 rrSheet1.addMergedRegion(CellRangeAddress.valueOf("B"+firstRow+":B"+lastRow));
									 rrSheet1.addMergedRegion(CellRangeAddress.valueOf("A"+firstRow+":A"+lastRow));
					             }
								 rownum = lastRow +1;
							}
					    	locName = zObj.getLocation_name();
			  /***********************************************************************/
					        XSSFRow row = rrSheet1.createRow(rowNo);
					        c=0;
					        row = rrSheet1.createRow(rowNo);
					        
					        cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle1);
							cell.setCellValue(sNo);
				
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle1);
							cell.setCellValue(zObj.getLocation_name());
						
							
					
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getType_of_use());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle2);
							cell.setCellValue(zObj.getStructure_id());
							totalStructures = totalStructures + Integer.parseInt(zObj.getStructure_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle2);
							cell.setCellValue(zObj.getPhysical_verification());
							totalPV = totalPV + Integer.parseInt(zObj.getPhysical_verification());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle2);
							cell.setCellValue(zObj.getEncroachment_removal());
							totalRE = totalRE + Integer.parseInt(zObj.getEncroachment_removal());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle2);
							cell.setCellValue(zObj.getBoundary_wall_doc());
							totalBC = totalBC + Integer.parseInt(zObj.getBoundary_wall_doc());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle2);
							cell.setCellValue(zObj.getHanded_over_to_execution());
							totalHC = totalHC + Integer.parseInt(zObj.getHanded_over_to_execution());
			
					        rowNo++;
								        
	        	 }
	        		 int firstRow = rownum;
		             int lastRow = rowNo;
		             System.out.println("B"+firstRow+":B"+lastRow);
		             if(firstRow != lastRow) {
						 rrSheet1.addMergedRegion(CellRangeAddress.valueOf("B"+firstRow+":B"+lastRow));
						 rrSheet1.addMergedRegion(CellRangeAddress.valueOf("A"+firstRow+":A"+lastRow));
		             }
	        	 XSSFRow row = rrSheet1.createRow(rowNo);
			       int c=0;
			        row = rrSheet1.createRow(rowNo);
			        
			        cell = row.createCell(c++);
					cell.setCellStyle(activityNameStyle);
					cell.setCellValue("");
		
					cell = row.createCell(c++);
					cell.setCellStyle(activityNameStyle);
					cell.setCellValue("");
			
				    cell = row.createCell(c++);
					cell.setCellStyle(indexWhiteStyle1);
					cell.setCellValue("Total");
					
					cell = row.createCell(c++);
					cell.setCellStyle(indexWhiteStyle2);
					cell.setCellValue(totalStructures);
		
					cell = row.createCell(c++);
					cell.setCellStyle(indexWhiteStyle2);
					cell.setCellValue(totalPV);
					
					cell = row.createCell(c++);
					cell.setCellStyle(indexWhiteStyle2);
					cell.setCellValue(totalRE);
					
					cell = row.createCell(c++);
					cell.setCellStyle(indexWhiteStyle2);
					cell.setCellValue(totalBC);
					
					cell = row.createCell(c++);
					cell.setCellStyle(indexWhiteStyle2);
					cell.setCellValue(totalHC);
					
				  for(int columnIndex = 1; columnIndex < headerStringArr.length; columnIndex++) {
				  	rrSheet1.setColumnWidth(0, 25 * 60);
				  	//rrSheet1.autoSizeColumn(columnIndex);
				  	rrSheet1.setColumnWidth(columnIndex, 35 * 125);
				   }
						   // rrSheet1.setColumnWidth(0, 25 * 120);
	       		 	XSSFSheet rrSheet2 = workBook.createSheet(WorkbookUtil.createSafeSheetName("RR Detail Report"));
	       		 	
			        XSSFRow headRow2 = rrSheet2.createRow(0);
			        
			        Cell cell2 = headRow2.createCell(0);
			        
			        XSSFRow mainHeadingRow2 = rrSheet2.createRow(1);
			        
			        cell2 = mainHeadingRow2.createCell(0);
			        cell2.setCellStyle(bluetyle);
			        cell2.setCellValue(work_d+" R&R Detail Report");
			        
			        for (int i = 1; i < 5; i++) {		        	
				        cell2 = mainHeadingRow2.createCell(i);
				        cell2.setCellStyle(bluetyle);
						cell2.setCellValue("");
					}	
			        rrSheet2.addMergedRegion(new CellRangeAddress(1, 1, 0,4));
			        int rowNo2 = 3;
				    // workBook.setSheetOrder(rrSheet1.getSheetName(), sheetNo++);	        	

	        XSSFRow structureRow2 = rrSheet2.createRow(rowNo2);

	        /**********************************************************************/
			String headerString2 = "Sr No.^R&R ID^Location^Sublocation^Type of Use^Physical Verification"
					+ "^Removal of Encroachment^Boundry Wall Construction completed^Hand over to construction";
	        String[] headerStringArr2 = headerString2.split("\\^");
	        
	        XSSFRow headingRow2 = rrSheet2.createRow(rowNo2);
	        for (int i = 0; i < headerStringArr2.length ; i++) {	
	        	
	    			 cell2 = headingRow2.createCell(i);
	    	    	 cell2.setCellStyle(bluetyle);
					 cell2.setCellValue(headerStringArr2[i]);
			}	
	        rowNo2++; int sno2 = 1;
	        for (RandRMain zObj : reportData.getReport2List()) {  
	            int d = 0;
	            XSSFRow row1 = rrSheet2.createRow(rowNo2);
		        c=0;
		        row1 = rrSheet2.createRow(rowNo2);
		        
		        cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle1);
				cell2.setCellValue(sno2++);
	
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle1);
				cell2.setCellValue(zObj.getRr_id());
				
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle1);
				cell2.setCellValue(zObj.getLocation_name());
				
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle1);
				cell2.setCellValue(zObj.getSub_location_name());
				
			    cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle);
				cell2.setCellValue(zObj.getType_of_use());
				
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle2);
				cell2.setCellValue(zObj.getPhysical_verification());
				
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle2);
				cell2.setCellValue(zObj.getEncroachment_removal());
				
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle2);
				cell2.setCellValue(zObj.getBoundary_wall_doc());
				
				cell2 = row1.createCell(d++);
				cell2.setCellStyle(activityNameStyle2);
				cell2.setCellValue(zObj.getHanded_over_to_execution());

		        rowNo2++;
			}
	        for(int columnIndex = 1; columnIndex < headerStringArr2.length; columnIndex++) {
			  	rrSheet2.setColumnWidth(0, 25 * 60);
			  	//rrSheet2.autoSizeColumn(columnIndex);
			  	rrSheet2.setColumnWidth(columnIndex, 35 * 125);
			   }    
			        
			        
			        
			        
	        }
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "RR_Report_"+dateFormat.format(date);
            
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
                logger.error("generateRandRMain : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                logger.error("generateRandRMain : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportError);
            }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateRandRMain : " + e.getMessage());
		}
		//return model;
    }

	private ModelAndView noDataAlertCall(RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			attributes.addFlashAttribute("error", "No updates in this period");
		} catch (Exception e) {

		}
		return model;
	}

	private CellStyle cellFormating(XSSFWorkbook workBook, byte[] rgb, HorizontalAlignment hAllign,
			VerticalAlignment vAllign, boolean isWrapText, boolean isBoldText, boolean isItalicText, int fontSize,
			String fontName) {
		CellStyle style = workBook.createCellStyle();
		// Setting Background color
		// style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		if (style instanceof XSSFCellStyle) {
			XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle) style;
			xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		// style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);

		Font font = workBook.createFont();
		// font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName(fontName); // "Calibri"

		font.setItalic(isItalicText);
		font.setBold(isBoldText);
		// Applying font to the style
		style.setFont(font);

		return style;
	}
	
	
}
