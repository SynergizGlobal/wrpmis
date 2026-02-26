package com.synergizglobal.wrpmis.controller;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.DesignReportService;
import com.synergizglobal.wrpmis.constants.PageConstants2;
import com.synergizglobal.wrpmis.model.ActivitiesProgressReport;
import com.synergizglobal.wrpmis.model.DesignReport;

@Controller
public class DesignReportController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(DesignReportController.class);
	
	@Autowired
	DesignReportService service;
	
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
	
	
	@RequestMapping(value = "/design-drawing-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView designDrawingReport(@ModelAttribute DesignReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants2.designAndDrawingReport);
		try{
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("designDrawingReport : " + e.getMessage());
		}
		return model;
     }
	

	
	@RequestMapping(value = "/ajax/getHodListInDesignReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DesignReport> getHodListInDesignReport(@ModelAttribute DesignReport obj) {
		List<DesignReport> worksList = null;
		try {
			worksList = service.getHodListInDesignReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodListInDesignReport : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListInDesignReport", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DesignReport> getDepartmentsListInDesignReport(@ModelAttribute DesignReport obj) {
		List<DesignReport> worksList = null;
		try {
			worksList = service.getDepartmentsListInDesignReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListInDesignReport : " + e.getMessage());
		}
		return worksList;
	}
	
	
	@RequestMapping(value = "/generate-design-drawing-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView generateDesignDrawingReport(@ModelAttribute DesignReport obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView("redirect:/design-drawing-report");
		try{
			
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date()); 
			
			List<DesignReport> reportData = service.getDesignReportData(obj);			
			
			XSSFWorkbook  workBook = new XSSFWorkbook();
			
			/***************************************************************************/
	        
			byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
	        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Garamond";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,14,fontName);
	        
	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 11;fontName = "Garamond";
	        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	       
	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        if(!reportData.isEmpty()) {
	        	XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Design Status"));
	        	XSSFSheet sheet1 = workBook.createSheet(WorkbookUtil.createSafeSheetName("Drawing Register"));
		        workBook.setSheetOrder(sheet.getSheetName(), sheetNo++);
		        workBook.setSheetOrder(sheet1.getSheetName(), sheetNo++);
		        
		        XSSFRow dateRow = sheet.createRow(0);
		        
		        Cell cell = dateRow.createCell(2);
		        
		        int rowNo = 0;

					String headerString = "Department^HOD^Dy. HOD^Structure Type^Structure^Component^Consultant^Planned Approval Date^Total No. of Drawings^Approval by Division^Approval by HQ^Approval by MRVC^Approved Date";
			        
			        String[] headerStringArr = headerString.split("\\^");
			        
			        XSSFRow headingRow = sheet.createRow(rowNo++);
			        for (int i = 0; i < headerStringArr.length; i++) {		        	
				        cell = headingRow.createCell(i);
				        cell.setCellStyle(whiteStyle);
						cell.setCellValue(headerStringArr[i]);
					}				
					
			        /***********************************************************************/
					for (DesignReport dObj : reportData) {
				        XSSFRow row = sheet.createRow(rowNo++);
				        int c = 0;
				        
				        cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getDepartment());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getHod());
						
				        cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getDyhod());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getStructure_type_fk());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getStructure_id_fk());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getComponent());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getConsultant_contract_id_fk()+" - "+dObj.getConsult_contract());
						
						
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getPlanned_approval_date());
						
				        cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getTotal_no_of_drawings());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getApproval_by_division());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getApproval_by_hq());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getApproval_by_mrvc());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(dObj.getApproved_date());						
						
				    }
					
					rowNo = rowNo + 3;
				    
				    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
					     //sheet.autoSizeColumn(columnIndex);
		            	sheet.setColumnWidth(columnIndex+2, 25 * 200);
					}
				    
				    
				    
				    
				    
				    
				    
				    
			        XSSFRow dateRow1 = sheet1.createRow(0);
			        
			        Cell cell1 = dateRow1.createCell(0);
			        
			        int rowNo1 = 0;

						String headerString1 = "Drawing ID^Department^HOD^Dy. HOD^Structure Type^Structure^Component^Consultant^Proof Consultant^Prepared By^Drawing Type^Approval Authority^Required Date^Drawing Name^WR Drawing No.^Divisional Drawing No.^HQ Drawing No.^Current Stage";
				        
				        String[] headerStringArr1 = headerString1.split("\\^");
				        
				        XSSFRow headingRow1 = sheet1.createRow(rowNo1++);
				        for (int i = 0; i < headerStringArr1.length; i++) {		        	
					        cell1 = headingRow1.createCell(i);
					        cell1.setCellStyle(whiteStyle);
							cell1.setCellValue(headerStringArr1[i]);
						}				
						
				        /***********************************************************************/
						for (DesignReport dObj : reportData) {
					        XSSFRow row = sheet1.createRow(rowNo1++);
					        int c = 0;
					        
					        cell1 = row.createCell(c++);
							cell1.setCellStyle(sectionStyle);
							cell1.setCellValue(dObj.getDrawing_id());					        
					        
				        cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getDepartment());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getHod());
						
				        cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getDyhod());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getStructure_type_fk());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getStructure_id_fk());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getComponent());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getConsultant_contract_id_fk()+" - "+dObj.getConsult_contract());
						
						
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getProof_consultant_contract_id_fk()+" - "+dObj.getProof_consultant());
						
				        cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getPrepared_by());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getDrawing_type());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getApproval_authority());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getRequired_date());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getDrawing_name());
						
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getMrvc_drawing_no());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getDivisional_drawing_no());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getHq_drawing_no());
						
						cell1 = row.createCell(c++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(dObj.getCurrent_stage());						
							
					    }
						
						rowNo1 = rowNo1 + 3;
					    
					    for(int columnIndex = 0; columnIndex < headerStringArr1.length; columnIndex++) {
						     //sheet.autoSizeColumn(columnIndex);
			            	sheet1.setColumnWidth(columnIndex+2, 25 * 200);
						}				    
				    
				    
				    
			    
		        }
			    
			    

            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Design_Drrawing_report_"+dateFormat.format(date);
            
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
                e.printStackTrace();
                attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                attributes.addFlashAttribute("error",dataExportError);
            }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateDesignDrawingReport : " + e.getMessage());
		}
		return model;
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
