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

	@RequestMapping(value = "/user-activity-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView UserActivityReport(@ModelAttribute UserActivityReport obj, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView(PageConstants.userActivityReport);
		try {
			List<UserActivityReport> contarctsList = service.getContractsFilterListInUserActivityReport(obj);
			model.addObject("contarctsList", contarctsList);

			List<UserActivityReport> worksList = service.getWorksFilterListInUserActivityReport(obj);
			model.addObject("worksList", worksList);

			List<UserActivityReport> UsersList = service.getHODsFilterListInUserActivityReport(obj);
			model.addObject("UsersList", UsersList);

			List<UserActivityReport> modulelist = service.getModulessFilterListInUserActivityReport(obj);
			model.addObject("modulelist", modulelist);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UserActivityReport : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getContractsListForUserActivityReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getContractsFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getContractsFilterListInUserActivityReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getWorksListForUserActivityReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getWorksFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getWorksFilterListInUserActivityReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getUsersListForUserActivityReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getHODsFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getHODsFilterListInUserActivityReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODsFilterListInUserActivityReport : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getUserActivityReportFormData", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getUserActivityReportFormData(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			obj.setFrom_date(DateParser.parse(obj.getFrom_date()));
			obj.setTo_date(DateParser.parse(obj.getTo_date()));
			objList = service.getUserActivityReportFormData(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserActivityReportFormData : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getModulesListForUserActivityReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getModulessFilterListInUserActivityReport(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getModulessFilterListInUserActivityReport(obj);
		} catch (Exception e) {
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
	        byte[] greyRGB = new byte[]{(byte)211, (byte)211, (byte)211};
	        
	        
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
	        
	        CellStyle indexShadedStyle = cellFormating(workBook,greyRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,true,isItalicText,11,fontName);


	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        int len  = 0;
	        int rowNum=8;
	        int rowNum1=8;
	        
	        String dates = null;
	        String UserName = null;
	        if(!(StringUtils.isEmpty(reportData))) 
	        {
	        	
       		 	XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Updated by PMIS Users"));
       		 	
		        XSSFRow dateRow = dprSheet.createRow(0);
		        
		        Cell cell = dateRow.createCell(0);
		        
		        
				cell.setCellStyle(whiteStyle);
				cell.setCellValue("");
				for (int i = 0; i < 7; i++) {		        	
			        cell = dateRow.createCell(i);
			        cell.setCellStyle(whiteStyle);
					cell.setCellValue("User Activity Report On :" + report_created_date);
				}
			   dprSheet.addMergedRegion(new CellRangeAddress(0,0, 0,6));	
			   
			   
			   XSSFRow mainHeadingRow = dprSheet.createRow(2);
		        
		        cell = mainHeadingRow.createCell(0);
		        cell.setCellStyle(greenStyle1);
				//cell.setCellValue("Activities Progress Report ");
		        cell.setCellValue("User Activity Report");
		        
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
				cell.setCellValue("Report for the Period ");
				cell = deatilsRow.createCell(1);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
				dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 0,1));
				
				cell = deatilsRow.createCell(2);
		        cell.setCellStyle(indexWhiteStyle);
		        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
		        	cell.setCellValue(from_date + " to " + to_date);
		        }else {
		        	cell.setCellValue(from_date);
		        }
				
				
				for (int i = 3; i < 7; i++) {		        	
			        cell = deatilsRow.createCell(i);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("");
				}	
				dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 2,6));
				/********************************************************/
		        
				/********************************************************/	
		        deatilsRow = dprSheet.createRow(4);
		        
		        cell = deatilsRow.createCell(0);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("Work ");
				cell = deatilsRow.createCell(1);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
				dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 0,1));
				String work = reportData.getWork();
				if(StringUtils.isEmpty(work)) {
					work = "All";
				}
				cell = deatilsRow.createCell(2);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue(work);
				
				for (int i = 3; i < 7; i++) {		        	
			        cell = deatilsRow.createCell(i);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("");
				}	
				dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 2,6));
		        
				/********************************************************/
		        
				/********************************************************/	
		        deatilsRow = dprSheet.createRow(5);
		        
		        cell = deatilsRow.createCell(0);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("Contract ");
				cell = deatilsRow.createCell(1);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
				dprSheet.addMergedRegion(new CellRangeAddress(5, 5, 0,1));
				
				cell = deatilsRow.createCell(2);
		        cell.setCellStyle(indexWhiteStyle);
		        String contract = reportData.getContract();
				if(StringUtils.isEmpty(contract)) {
					contract = "All";
				}
				cell.setCellValue(contract);
		        
				for (int i = 3; i < 7; i++) {		        	
			        cell = deatilsRow.createCell(i);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("");
				}	
				dprSheet.addMergedRegion(new CellRangeAddress(5,5, 2,6));
				
				/********************************************************/
				
				/********************************************************/	
		        deatilsRow = dprSheet.createRow(6);
		        
		        cell = deatilsRow.createCell(0);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("User");
				
				cell = deatilsRow.createCell(1);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
				dprSheet.addMergedRegion(new CellRangeAddress(6, 6, 0,1));
				String user = reportData.getUser();
				if(StringUtils.isEmpty(user)) {
					user = "All";
				}
				cell = deatilsRow.createCell(2);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue(user);
				
				for (int i = 3; i < 7; i++) {		        	
			        cell = deatilsRow.createCell(i);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("");
				}	
				dprSheet.addMergedRegion(new CellRangeAddress(6,6, 2,6));	
				
				
				
		        deatilsRow = dprSheet.createRow(7);
		        
		        cell = deatilsRow.createCell(0);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("Update Form");
				
				cell = deatilsRow.createCell(1);
				cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue("");
				dprSheet.addMergedRegion(new CellRangeAddress(7, 7, 0,1));
				String updateform = reportData.getModule_name_fk();
				if(StringUtils.isEmpty(updateform)) {
					updateform = "All";
				}
				cell = deatilsRow.createCell(2);
		        cell.setCellStyle(indexWhiteStyle);
				cell.setCellValue(updateform);
				
				for (int i = 3; i < 7; i++) {		        	
			        cell = deatilsRow.createCell(i);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue("");
				}	
				dprSheet.addMergedRegion(new CellRangeAddress(7,7, 2,6));					
			   
			   
       		 	
			    // workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);	        	
	        
	        	 for (UserActivityReport zObj : reportData.getUsersList()) {  
	        		 UserName = zObj.getUser();
					/*************************************************************************/	
						int rowNo = 8;
						
						if(rowNum==8)
						{
							rowNo=rowNum+2;
						}
						else
						{
							rowNo=rowNum+1;
						}
						
				            int tempRowNo = rowNo;
				            XSSFRow structureRow = dprSheet.createRow(rowNo);
					
				            /**********************************************************************/
							String headerString = "Date^Update Form^Work^Contarct^Action Type^Details^Time";
					        String[] headerStringArr = headerString.split("\\^");

				            
				            if(rowNo==10)
				            {
						        
						        XSSFRow headingRow = dprSheet.createRow(rowNo);
						        for (int i = 0; i < headerStringArr.length ; i++) {	
					        	
					        			 cell = headingRow.createCell(i);
					        	    	 cell.setCellStyle(greenStyle1);
										 cell.setCellValue(headerStringArr[i]);
								}	
						        rowNo++;
				            }
				            
				            int c = 0;
				            if(zObj.getUserActivitiesList() != null && zObj.getUserActivitiesList().size() > 0)
				            {
				            	if(!UserName.contains("Synergiz")) 
				            	{
						            XSSFRow UserRow = dprSheet.createRow(rowNo);
					        						        	
	
						    	    cell = UserRow.createCell(c++);
									cell.setCellStyle(indexShadedStyle);
									cell.setCellValue("User ");
									
							        cell = UserRow.createCell(c++);
									cell.setCellStyle(indexShadedStyle);
									cell.setCellValue(UserName);
									
									for (int i = 2; i < 7; i++) {		        	
								        cell = UserRow.createCell(i);
								        cell.setCellStyle(indexShadedStyle);
										cell.setCellValue("");
									}
									dprSheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 1,6));
									
									rowNo++;
				            	}
				            }
		            
				            
				            
					     if(zObj.getUserActivitiesList() != null && zObj.getUserActivitiesList().size() > 0){
					    	 int x = 0,z=0;
					        /***********************************************************************/
						    for (UserActivityReport dObj : zObj.getUserActivitiesList()) {
						    	if(dObj.getCreated_by_user_id_fk().contains("PMIS")) {
								        XSSFRow row = dprSheet.createRow(rowNo);
								        c=0;

								        row = dprSheet.createRow(rowNo);
								        
								        cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										
						        		 dates = dObj.getDate();
						        		 DateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
										 Date datesVal = inputFormat2.parse(dates);
										 String dateFields = outputFormat.format(datesVal);
										 
										 
										cell.setCellValue(dateFields);								        
							
										cell = row.createCell(c++);
										cell.setCellStyle(activityNameStyle);
										cell.setCellValue(dObj.getForm_name());
									
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
						    rowNo--;
						    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
						    	//dprSheet.setColumnWidth(0, 25 * 30);
						    	//dprSheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex, 35 * 165);
							}
						    dprSheet.setColumnWidth(0, 25 * 120);
						    dprSheet.setColumnWidth(1, 35 * 135);
						    dprSheet.setColumnWidth(2, 40 * 145);
						    dprSheet.setColumnWidth(3, 45 * 165);
						    dprSheet.setColumnWidth(4, 29 * 135);
						    dprSheet.setColumnWidth(5, 45 * 165);
						    dprSheet.setColumnWidth(6, 25 * 120);
						}
			        	 rowNum=rowNo;
	        	 }
	        	 
	        	 XSSFSheet dprSheet1 = workBook.createSheet(WorkbookUtil.createSafeSheetName("Updated by Synergiz"));
	       		 	
			        XSSFRow dateRow1 = dprSheet1.createRow(0);
			        
			        Cell cell1 = dateRow1.createCell(0);
			        
			        
					cell1.setCellStyle(whiteStyle);
					cell1.setCellValue("");
					for (int i = 0; i < 7; i++) {		        	
				        cell1 = dateRow1.createCell(i);
				        cell1.setCellStyle(whiteStyle);
					cell1.setCellValue("User Activity Report On :" + report_created_date);
					}
				   dprSheet1.addMergedRegion(new CellRangeAddress(0,0, 0,6));	
				   
				   
				   XSSFRow mainHeadingRow1 = dprSheet1.createRow(2);
			        
			        cell1 = mainHeadingRow1.createCell(0);
			        cell1.setCellStyle(greenStyle1);
					//cell1.setCellValue("Activities Progress Report ");
			        cell1.setCellValue("User Activity Report");
			        
			        for (int i = 1; i < 7; i++) {		        	
				        cell1 = mainHeadingRow1.createCell(i);
				        cell1.setCellStyle(greenStyle);
						cell1.setCellValue("");
					}	
			        dprSheet1.addMergedRegion(new CellRangeAddress(2, 2, 0,6));
					/********************************************************/	
			        
			        /********************************************************/	
			        XSSFRow deatilsRow1 = dprSheet1.createRow(3);
			        
			        cell1 = deatilsRow1.createCell(0);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("Report for the Period ");
					cell1 = deatilsRow1.createCell(1);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("");
					dprSheet1.addMergedRegion(new CellRangeAddress(3, 3, 0,1));
					
					cell1 = deatilsRow1.createCell(2);
			        cell1.setCellStyle(indexWhiteStyle);
			        if(!StringUtils.isEmpty(from_date) && !StringUtils.isEmpty(to_date)) {
			        	cell1.setCellValue(from_date + " to " + to_date);
			        }else {
			        	cell1.setCellValue(from_date);
			        }
					
					
					for (int i = 3; i < 7; i++) {		        	
				        cell1 = deatilsRow1.createCell(i);
				        cell1.setCellStyle(indexWhiteStyle);
						cell1.setCellValue("");
					}	
					dprSheet1.addMergedRegion(new CellRangeAddress(3, 3, 2,6));
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow1 = dprSheet1.createRow(4);
			        
			        cell1 = deatilsRow1.createCell(0);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("Work ");
					cell1 = deatilsRow1.createCell(1);
					cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("");
					dprSheet1.addMergedRegion(new CellRangeAddress(4, 4, 0,1));
					String work1 = reportData.getWork();
					if(StringUtils.isEmpty(work1)) {
						work1 = "All";
					}
					cell1 = deatilsRow1.createCell(2);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue(work1);
					
					for (int i = 3; i < 7; i++) {		        	
				        cell1 = deatilsRow1.createCell(i);
				        cell1.setCellStyle(indexWhiteStyle);
						cell1.setCellValue("");
					}	
					dprSheet1.addMergedRegion(new CellRangeAddress(4, 4, 2,6));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow1 = dprSheet1.createRow(5);
			        
			        cell1 = deatilsRow1.createCell(0);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("Contract ");
					cell1 = deatilsRow1.createCell(1);
					cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("");
					dprSheet1.addMergedRegion(new CellRangeAddress(5, 5, 0,1));
					
					cell1 = deatilsRow1.createCell(2);
			        cell1.setCellStyle(indexWhiteStyle);
			        String contract1 = reportData.getContract();
					if(StringUtils.isEmpty(contract1)) {
						contract1 = "All";
					}
					cell1.setCellValue(contract1);
			        
					for (int i = 3; i < 7; i++) {		        	
				        cell1 = deatilsRow1.createCell(i);
				        cell1.setCellStyle(indexWhiteStyle);
						cell1.setCellValue("");
					}	
					dprSheet1.addMergedRegion(new CellRangeAddress(5,5, 2,6));
					
					/********************************************************/
					
					/********************************************************/	
			        deatilsRow1 = dprSheet1.createRow(6);
			        
			        cell1 = deatilsRow1.createCell(0);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("User");
					
					cell1 = deatilsRow1.createCell(1);
					cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("");
					dprSheet1.addMergedRegion(new CellRangeAddress(6, 6, 0,1));
					String user1 = reportData.getUser();
					if(StringUtils.isEmpty(user1)) {
						user1 = "All";
					}
					cell1 = deatilsRow1.createCell(2);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue(user1);
					
					for (int i = 3; i < 7; i++) {		        	
				        cell1 = deatilsRow1.createCell(i);
				        cell1.setCellStyle(indexWhiteStyle);
						cell1.setCellValue("");
					}	
					dprSheet1.addMergedRegion(new CellRangeAddress(6,6, 2,6));	
					
					
					
			        deatilsRow1 = dprSheet1.createRow(7);
			        
			        cell1 = deatilsRow1.createCell(0);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("Update Form");
					
					cell1 = deatilsRow1.createCell(1);
					cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue("");
					dprSheet1.addMergedRegion(new CellRangeAddress(7, 7, 0,1));
					String updateform1 = reportData.getForm_name();
					if(StringUtils.isEmpty(updateform1)) {
						updateform1 = "All";
					}
					cell1 = deatilsRow1.createCell(2);
			        cell1.setCellStyle(indexWhiteStyle);
					cell1.setCellValue(updateform1);
					
					for (int i = 3; i < 7; i++) {		        	
				        cell1 = deatilsRow1.createCell(i);
				        cell1.setCellStyle(indexWhiteStyle);
						cell1.setCellValue("");
					}	
					dprSheet1.addMergedRegion(new CellRangeAddress(7,7, 2,6));					   
				   
	       		 	
				    // workBook.setSheetOrder(dprSheet1.getSheetName(), sheetNo++);	        	
		        
		        	 for (UserActivityReport zObj : reportData.getUsersList()) {  
		        		 UserName = zObj.getUser();
			        
				        
			
						/*************************************************************************/		
						 	
						
							int rowNo1 = 8;
							
							if(rowNum1==8)
							{
								rowNo1=rowNum1+2;
							}
							else
							{
								rowNo1=rowNum1+1;
							}
							
					            int tempRowNo = rowNo1;
					            XSSFRow structureRow = dprSheet1.createRow(rowNo1);
						
					            /**********************************************************************/
								String headerString = "Date^Update Form^Work^Contarct^Action Type^Details^Time";
						        String[] headerStringArr = headerString.split("\\^");

					            
					            if(rowNo1==10)
					            {
							        
							        XSSFRow headingRow = dprSheet1.createRow(rowNo1);
							        for (int i = 0; i < headerStringArr.length ; i++) {	
						        	
						        			 cell1 = headingRow.createCell(i);
						        	    	 cell1.setCellStyle(greenStyle1);
											 cell1.setCellValue(headerStringArr[i]);
									}	
							        rowNo1++;
					            }
					            
					            int c = 0;
					            if(zObj.getUserActivitiesList() != null && zObj.getUserActivitiesList().size() > 0)
					            {
					            	if(UserName.contains("Synergiz")) 
					            	{
							            XSSFRow UserRow = dprSheet1.createRow(rowNo1);
						        						        	
		
							    	    cell1 = UserRow.createCell(c++);
										cell1.setCellStyle(indexShadedStyle);
										cell1.setCellValue("User ");
										
								        cell1 = UserRow.createCell(c++);
										cell1.setCellStyle(indexShadedStyle);
										cell1.setCellValue(UserName);
										
										for (int i = 2; i < 7; i++) {		        	
									        cell1 = UserRow.createCell(i);
									        cell1.setCellStyle(indexShadedStyle);
											cell1.setCellValue("");
										}
										dprSheet1.addMergedRegion(new CellRangeAddress(rowNo1, rowNo1, 1,6));
										
										rowNo1++;
					            	}
					            }
			            
					            
					            
						     if(zObj.getUserActivitiesList() != null && zObj.getUserActivitiesList().size() > 0){
						    	 int x = 0,z=0;
						        /***********************************************************************/
							    for (UserActivityReport dObj : zObj.getUserActivitiesList()) {
							    	if(!dObj.getCreated_by_user_id_fk().contains("PMIS")) {
									        XSSFRow row = dprSheet1.createRow(rowNo1);
									        c=0;

									        row = dprSheet1.createRow(rowNo1);
									        
									        cell1 = row.createCell(c++);
											cell1.setCellStyle(activityNameStyle);
											
							        		 dates = dObj.getDate();
							        		 DateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
											 Date datesVal = inputFormat2.parse(dates);
											 String dateFields = outputFormat.format(datesVal);
											 
											 
											cell1.setCellValue(dateFields);								        
									        
								
											cell1 = row.createCell(c++);
											cell1.setCellStyle(activityNameStyle);
											cell1.setCellValue(dObj.getForm_name());
										
										    cell1 = row.createCell(c++);
											cell1.setCellStyle(activityNameStyle);
											cell1.setCellValue(dObj.getWork());
											
											cell1 = row.createCell(c++);
											cell1.setCellStyle(activityNameStyle);
											cell1.setCellValue(dObj.getContract());
								
											cell1 = row.createCell(c++);
											cell1.setCellStyle(activityNameStyle);
											cell1.setCellValue(dObj.getForm_action_type());
											
											cell1 = row.createCell(c++);
											cell1.setCellStyle(activityNameStyle);
											cell1.setCellValue(dObj.getForm_details());
											
											cell1 = row.createCell(c++);
											cell1.setCellStyle(numberStyle);
											cell1.setCellValue(dObj.getTime());
											
									        rowNo1++;
							    	}
							    	 
							   
							    }
							    rowNo1--;
							    for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
							    	//dprSheet1.setColumnWidth(0, 25 * 30);
							    	//dprSheet1.autoSizeColumn(columnIndex);
					            	dprSheet1.setColumnWidth(columnIndex, 35 * 165);
								}
							    dprSheet1.setColumnWidth(0, 25 * 120);
							    dprSheet1.setColumnWidth(1, 35 * 135);
							    dprSheet1.setColumnWidth(2, 40 * 145);
							    dprSheet1.setColumnWidth(3, 45 * 165);
							    dprSheet1.setColumnWidth(4, 29 * 135);
							    dprSheet1.setColumnWidth(5, 45 * 165);
							    dprSheet1.setColumnWidth(6, 25 * 120);
							}
				        	 rowNum1=rowNo1;

						
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
	
	/******************************************************************************/
	
	@RequestMapping(value = "/user-inactive-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView userInactiveReport(@ModelAttribute UserActivityReport obj, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView(PageConstants.userInactiveReport);
		try {
			List<UserActivityReport> worksList = service.getWorksListForUserInactiveReportForm(obj);
			model.addObject("worksList", worksList);

			List<UserActivityReport> modulelist = service.getModulesListForUserInactiveReportForm(obj);
			model.addObject("modulelist", modulelist);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userInactiveReport : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksListForUserInactiveReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getWorksListForUserInactiveReportForm(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getWorksListForUserInactiveReportForm(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForUserInactiveReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getModulesListForUserInactiveReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserActivityReport> getModulesListForUserInactiveReportForm(@ModelAttribute UserActivityReport obj) {
		List<UserActivityReport> objList = null;
		try {
			objList = service.getModulesListForUserInactiveReportForm(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getModulesListForUserInactiveReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/checkInactiveUsersExistsOrNot", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> checkInactiveUsersExistsOrNot(@ModelAttribute UserActivityReport obj) {
		List<String> objList = null;
		try {
			objList = service.checkInactiveUsersExistsOrNot(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("checkInactiveUsersExistsOrNot : " + e.getMessage());
		}
		return objList;
	}
}
