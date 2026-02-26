package com.synergizglobal.wrpmis.controller;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.ProjectOverviewReportService;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.User;

@Controller
public class ProjectOverviewReportController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ProjectOverviewReportController.class);
	
	@Autowired
	ProjectOverviewReportService projectOverviewReportService;	
	
	
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
			
	@RequestMapping(value="/project-overview-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView ProjectOverviewReport(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.ProjectOverviewReport);
		return model;
	}
	@RequestMapping(value = "/ajax/getWorksFilterListInPOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getWorksFilterListInPOR(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> worksFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksFilterList = projectOverviewReportService.getWorksFilterListInPOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInPOR : " + e.getMessage());
		}
		return worksFilterList;
	}
	@RequestMapping(value = "/ajax/getDepartmentFilterListInPOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getDepartmentFilterListInPOR(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> departmentFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			departmentFilterList = projectOverviewReportService.getDepartmentFilterListInPOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentFilterListInPOR : " + e.getMessage());
		}
		return departmentFilterList;
	}

	@RequestMapping(value = "/ajax/getProjectOverviewReportList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getProjectOverviewReportList(@ModelAttribute Contract obj,HttpSession session) {
		List<Contract> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = projectOverviewReportService.getProjectOverviewReportList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectOverviewReportList : " + e.getMessage());
		}
		return contracts;
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
	
	@RequestMapping(value = "/export-project-overview-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDetailsOfProjectOverviewReport(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Contract obj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.ProjectOverviewReport);
		try {
			
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userRoleCode = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setUser_id(userId);
			obj.setUser_role_code(userRoleCode);
			view.setViewName("redirect:/project-overview-report");
			
			List<Contract> dataList = projectOverviewReportService.getProjectOverviewReportList(obj);  
			
			
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet ProjectOverviewReportSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Project Overview Report"));
	            
		        workBook.setSheetOrder(ProjectOverviewReportSheet.getSheetName(), 0);
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        byte[] grayRGB = new byte[]{(byte)217, (byte)217, (byte)217};
		        
		        
		        boolean isWrapText = true;boolean isBoldText = false;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,true,isItalicText,12,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle grayStyle = cellFormating(workBook,grayRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isBoldText = true;fontSize = 12;
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle sectionPercentStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle sectioncostStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle sectionunitsStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle centerStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle rightStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
	            XSSFRow headingRow = ProjectOverviewReportSheet.createRow(0);
	            String headerString = "S No^Particular^Awarded Cost^Expenditure till Date (CR)^Expenditure this FY (CR)^Pending Amount (CR)^";
	            
	            String[] headerStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < headerStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(headerStringArr[i]);
				}
            
	            short rowNo = 1;
	            int sNo = 1;

		            
		            for (Contract obj1 : dataList) 
		            {

			                XSSFRow row = ProjectOverviewReportSheet.createRow(rowNo);
			                int c = 0;
		                
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(sNo++);
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj1.getContract_short_name());
							
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj1.getAwarded_cost());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionPercentStyle);
							cell.setCellValue(obj1.getCumulative_expenditure());					
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionPercentStyle);
							cell.setCellValue(obj1.getActual_financial_progress());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionPercentStyle);
							cell.setCellValue(obj1.getActual_physical_progress());
							
			                rowNo++;
		           }
		            
		            for(int columnIndex = 1; columnIndex < headerStringArr.length; columnIndex++) {
		            	ProjectOverviewReportSheet.setColumnWidth(columnIndex, 25 * 250);
					}		            
				
			  

	            
	            /*******************************************************************************************/
	            
	            
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Project_Overview_Report"+dateFormat.format(date);
                
	            try{
            	
	               response.setContentType("application/.csv");
	 			   response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	 			   response.setContentType("application/vnd.ms-excel");
	 			   // add response header
	 			   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".xlsx");
	 			   
	 			   workBook.write(response.getOutputStream()); // Write workbook to response.
		           workBook.close();
	 			    response.getOutputStream().flush();
	            	
	                
	                attributes.addFlashAttribute("success",dataExportSucess);

	            }catch(FileNotFoundException e){
	                attributes.addFlashAttribute("error",dataExportInvalid);
	            }catch(IOException e){
	                attributes.addFlashAttribute("error",dataExportError);
	            }
	         }else{
	        	 attributes.addFlashAttribute("error",dataExportNoData);
	         }
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportProjectOverviewReport : "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
	}	
	
}
