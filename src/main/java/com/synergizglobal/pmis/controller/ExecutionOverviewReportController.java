package com.synergizglobal.pmis.controller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

import com.synergizglobal.pmis.Iservice.ExecutionOverviewReportService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Controller
public class ExecutionOverviewReportController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ExecutionOverviewReportController.class);
	
	
	@Autowired
	ExecutionOverviewReportService executionOverviewReportService;	
	
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
	
		
	@RequestMapping(value="/execution-overview-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView ExecutionOverviewReport(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.ExecutionOverviewReport);
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInEOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getWorksFilterListInEOR(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> worksFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksFilterList = executionOverviewReportService.getWorksFilterListInEOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInEOR : " + e.getMessage());
		}
		return worksFilterList;
	}
	@RequestMapping(value = "/ajax/getDepartmentFilterListInEOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDepartmentFilterListInEOR(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> departmentFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			departmentFilterList = executionOverviewReportService.getDepartmentFilterListInEOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentFilterListInEOR : " + e.getMessage());
		}
		return departmentFilterList;
	}
	@RequestMapping(value = "/ajax/getContractIdFilterListInEOR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getContractIdFilterListInEOR(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> contractFilterList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractFilterList = executionOverviewReportService.getContractIdFilterListInEOR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractIdFilterList : " + e.getMessage());
		}
		return contractFilterList;
	}
	
	@RequestMapping(value = "/ajax/getExecutionOverviewReportList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getExecutionOverviewReportList(@ModelAttribute StripChart obj,HttpSession session) {
		List<StripChart> contracts = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contracts = executionOverviewReportService.getExecutionOverviewReportList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutionOverviewReportList : " + e.getMessage());
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
	
	@RequestMapping(value = "/export-execution-overview-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDetailsOfContracts(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute StripChart obj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.ExecutionOverviewReport);
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userRoleCode = (String) session.getAttribute("USER_ROLE_CODE");
			obj.setUser_id(userId);
			obj.setUser_role_code(userRoleCode);
			view.setViewName("redirect:/execution-overview-report");
			
			List<StripChart> dataList = executionOverviewReportService.getExecutionOverviewReportList(obj);  
			
			
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet executionOverviewReportSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Execution Overview Report"));
	            
		        workBook.setSheetOrder(executionOverviewReportSheet.getSheetName(), 0);
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        byte[] grayRGB = new byte[]{(byte)217, (byte)217, (byte)217};
		        
		        
		        boolean isWrapText = true;boolean isBoldText = false;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle grayStyle = cellFormating(workBook,grayRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isBoldText = true;fontSize = 12;
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle sectioncostStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle sectionunitsStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle centerStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle rightStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
	            XSSFRow headingRow = executionOverviewReportSheet.createRow(0);
	            String headerString = "S No^Structure Type^Description^Unit^Scope^Completed^Pending^Last Updated on";
	            
	            String[] headerStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < headerStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(whiteStyle);
					cell.setCellValue(headerStringArr[i]);
				}
            
	            short rowNo = 1;
	            int sNo = 1;
	            ArrayList<String> list = new ArrayList<String>();
	            ArrayList<String> startArray = new ArrayList<String>();
	            ArrayList<String> endArray = new ArrayList<String>();
	            int start=0;
	            int end=0;
	            int loop=0;
	            int Incre=0;
	            for (StripChart obj1 : dataList) 
	            {
	                XSSFRow row = executionOverviewReportSheet.createRow(rowNo);
	                int c = 0;
                
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(sNo++);
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					if(list.contains(obj1.getStructure_type_fk())==false)
					{
						cell.setCellValue(obj1.getStructure_type_fk());
						list.add(obj1.getStructure_type_fk());
					}
					else
					{
						cell.setCellValue("");
					}
					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj1.getStrip_chart_structure_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj1.getUnit_fk());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj1.getScope());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj1.getCompleted());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj1.getPending());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj1.getModified_date());	
					
					/*if(list.contains(obj1.getStructure_type_fk())==false)
					{
						list.add(obj1.getStructure_type_fk());
						start=rowNo;
						end=rowNo+1;
						endArray.add(Integer.toString(end));
						
					}
					else
					{
						if(loop==0)
						{
							start=rowNo;
							
							startArray.add(Integer.toString(start));
							loop++;
						}
					}*/
	                rowNo++;
	            }
				/*for(int i=0; i<startArray.size(); i++)
				{
						executionOverviewReportSheet.addMergedRegion(new CellRangeAddress(Integer.parseInt(startArray.get(i)), Integer.parseInt(endArray.get(i)), 1,1));
				}*/
				  
	            for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
	            	executionOverviewReportSheet.setColumnWidth(columnIndex, 25 * 250);
				}
	            
	            /*******************************************************************************************/
	            
	            
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Execution_Overview_Report"+dateFormat.format(date);
                
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
			logger.error("exportContract : "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
	}	
}
