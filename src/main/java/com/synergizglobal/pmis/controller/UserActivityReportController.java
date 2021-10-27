package com.synergizglobal.pmis.controller;

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

import com.synergizglobal.pmis.Iservice.UserActivityReportService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.UserActivityReport;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.UserActivityReport;
import com.synergizglobal.pmis.model.UserActivityReport;

@Controller
public class UserActivityReportController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(UserActivityReportController.class);
	
	@Autowired
	UserActivityReportService service;
	
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
	
	
	@RequestMapping(value = "/user-activity-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView UserActivityReport(@ModelAttribute UserActivityReport obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.userActivityReport);
		try{
			List<UserActivityReport> contarctsList = service.getContractsFilterListInUserActivityReport(obj);
			model.addObject("contarctsList", contarctsList);
			
			List<UserActivityReport> worksList = service.getWorksFilterListInUserActivityReport(obj);
			model.addObject("worksList", worksList);
			
			List<UserActivityReport> UsersList = service.getHODsFilterListInUserActivityReport(obj);
			model.addObject("UsersList", UsersList);
			
			List<UserActivityReport> modulelist = service.getModulessFilterListInUserActivityReport(obj);
			model.addObject("modulelist", modulelist);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("UserActivityReport : " + e.getMessage());
		}
		return model;
    }
	@RequestMapping(value = "/ajax/getContractsListForUserActivityReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getContractsFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getContractsFilterListInUserActivityReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorksListForUserActivityReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getWorksFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getWorksFilterListInUserActivityReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getUsersListForUserActivityReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getHODsFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getHODsFilterListInUserActivityReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODsFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getUserActivityReportFormData", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getUserActivityReportFormData(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			obj.setFrom_date(DateParser.parse(obj.getFrom_date()));
			obj.setTo_date(DateParser.parse(obj.getTo_date()));
			objList = service.getUserActivityReportFormData(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserActivityReportFormData : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getModulesListForUserActivityReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getModulessFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getModulessFilterListInUserActivityReport(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getModulessFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/generate-user-activity-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateUserActivityReport(@ModelAttribute UserActivityReport obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		//ModelAndView model = new ModelAndView("redirect:/activities-progress-report");
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date());
		
			DateFormat outputFormat = new SimpleDateFormat("d-MMM-YY", Locale.US);
			DateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

			String from = obj.getFrom_date();
			String to = obj.getTo_date();
			
			Date date1 = inputFormat.parse(from);
			Date date2 = inputFormat.parse(to);
			String from_date = outputFormat.format(date1);
			String to_date = outputFormat.format(date2);
			
			
			SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-YY");
			
			
			obj.setFrom_date(DateParser.parse(obj.getFrom_date()));
			obj.setTo_date(DateParser.parse(obj.getTo_date()));
		
			//List<UserActivityReport> structuresList = service.getStructuresList(obj);
			UserActivityReport reportData = service.getUserActivityReportData(obj);
			
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
	        CellStyle greenStyle1 = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

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
	        int rowNum=0;
	        String dates = null;
	        if(!(StringUtils.isEmpty(reportData))) {
	        	
       		 	XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("User Activity Report"));
       		 	
		        XSSFRow dateRow = dprSheet.createRow(rowNum);
		        
		        Cell cell = dateRow.createCell(0);
		        
		        
				cell.setCellStyle(whiteStyle);
				cell.setCellValue("");
				for (int i = 0; i < 9; i++) {		        	
			        cell = dateRow.createCell(i);
			        cell.setCellStyle(whiteStyle);
					cell.setCellValue("User Activity Report On :" + report_created_date);
				}
			   dprSheet.addMergedRegion(new CellRangeAddress(0,0, 0,8));	     		 	
       		 	
			    // workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);	        	
	        
	        	 for (UserActivityReport zObj : reportData.getDatesList()) {  
	        		 dates = zObj.getDate();
	        		 DateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
					 Date datesVal = inputFormat2.parse(dates);
					 String dateFields = outputFormat.format(datesVal);

				        
			        
		
					/*************************************************************************/		

					
						int rowNo = rowNum+2;
				            int tempRowNo = rowNo;
				            XSSFRow structureRow = dprSheet.createRow(rowNo);
					
				            /**********************************************************************/
							String headerString = "Date^User ID^User^Module^Work^Contarct^Action Type^Details^Time";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        int HeaderSize = obj.getDatesList().size();
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
					        for (int i = 0; i < headerStringArr.length ; i++) {	
				        	
				        			 cell = headingRow.createCell(i);
				        	    	 cell.setCellStyle(greenStyle1);
									 cell.setCellValue(headerStringArr[i]);
							}				
					     if(zObj.getUserActivitiesList() != null && zObj.getUserActivitiesList().size() > 0){
					    	 int x = 0,z=0;
					        /***********************************************************************/
						    for (UserActivityReport dObj : zObj.getUserActivitiesList()) {
						    	if(dObj.getCreated_by_user_id_fk().contains("PMIS")) {
								        int c = 0;
								        XSSFRow row = dprSheet.createRow(rowNo);
								        //XSSFRow componentRow = dprSheet.createRow(rowNo);
								       
								        if(x < 1) {
								  		    cell = row.createCell(c++);
											cell.setCellStyle(componentStyle);
											cell.setCellValue("");
											for (int i = 0; i < 9; i++) {		        	
										        cell = row.createCell(i);
										        cell.setCellStyle(componentStyle);
										        cell.setCellValue("Updated by PMIS Users");
											}
										   dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 0,8));
										   rowNo++;
										   x++;
								        }
								        row = dprSheet.createRow(rowNo);c = 0;
								        
								        cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dateFields);								        
								        
								        cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getCreated_by_user_id_fk());
										
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getUser());
							
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getModule_name());
									
									    cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getWork());
										
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getContract());
							
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getForm_action_type());
										
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getForm_details());
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(dObj.getTime());
										
								        rowNo++;
						    	}
						   
						    }
						    for (UserActivityReport dObj : zObj.getUserActivitiesList()) {
						    	if(!dObj.getCreated_by_user_id_fk().contains("PMIS")) {
						    		
						    	     XSSFRow row = dprSheet.createRow(rowNo);
								        int c = 0;
								        if(z < 1) {
							        	    cell = row.createCell(c++);
											cell.setCellStyle(componentStyle);
											cell.setCellValue("");
											for (int i = 0; i < 9; i++) {		        	
										        cell = row.createCell(i);
										        cell.setCellStyle(componentStyle);
										        cell.setCellValue("Updated by Synergiz");
											}
										   dprSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo, 0,8));
										   rowNo++;
										   z++;
								        }
								        //XSSFRow componentRow = dprSheet.createRow(rowNo);
								        row = dprSheet.createRow(rowNo);
								        c = 0;
								        
								        cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dateFields);
										
										
								        cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getCreated_by_user_id_fk());
										
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getUser());
							
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getModule_name());
									
									    cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getWork());
										
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getContract());
							
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getForm_action_type());
										
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getForm_details());
										
										cell = row.createCell(c++);
										cell.setCellStyle(numberStyle);
										cell.setCellValue(dObj.getTime());
										
								        rowNo++;
						    	}
						   
						    }
						    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
						    	//dprSheet.setColumnWidth(0, 25 * 30);
						    	//dprSheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex, 35 * 165);
							}
						}
					     else {
							    XSSFRow row = dprSheet.createRow(rowNo);
						        for (int i = 0; i < 9; i++) {		        	
							        cell = row.createCell(i);
							        cell.setCellStyle(whiteStyle);
									cell.setCellValue("No updates in this period");
								}	
								dprSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0,8));
				        }
			        	 rowNum=rowNo;

					
	        	 }
	        }
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "User_Activity_Report_"+dateFormat.format(date);
            
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
                logger.error("generateUserActivityReport : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                logger.error("generateUserActivityReport : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportError);
            }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateUserActivityReport : " + e.getMessage());
		}
		//return model;
    }
	
	private ModelAndView noDataAlertCall(RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			attributes.addFlashAttribute("error", "No updates in this period");
		}catch (Exception e) {
			
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
