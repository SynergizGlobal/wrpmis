package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
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
import org.apache.poi.util.StringUtil;
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

import com.synergizglobal.pmis.Iservice.ActivitiesStatusReportService;
import com.synergizglobal.pmis.Iservice.ContractResourceReportService;
import com.synergizglobal.pmis.Iservice.ContractResourceService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
import com.synergizglobal.pmis.model.ContractResource;
import com.synergizglobal.pmis.model.User;

@Controller
public class ContractResourceReportController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	public static Logger logger = Logger.getLogger(ContractResourceReportController.class);
	
	@Autowired
	ContractResourceReportService reportService;
	
	@Autowired
	ContractResourceService service;

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
	
	@RequestMapping(value = "/contract-resource-report", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView contractResourceReport(@ModelAttribute ContractResource obj,HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants.resourceReport);
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<ContractResource> projectsList = reportService.getProjectsListForContractResourceForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<ContractResource> worksList = reportService.getWorkListForContractResourceForm(obj);
			model.addObject("worksList", worksList);
			
			List<ContractResource> HODsList = reportService.getHODsListForContractResourceForm(obj);
			model.addObject("HODsList", HODsList);
			
			
			List<ContractResource> contractsList = reportService.getContractsListForContractResourceForm(obj);
			model.addObject("contractsList", contractsList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("contractResourceReport : " + e.getMessage());
		}
		return model;
    }
	
	
	@RequestMapping(value = "/ajax/getProjectsListForContractResourceReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getProjectsListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = reportService.getProjectsListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForContractResourceReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getWorkListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = reportService.getWorkListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForContractResourceReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getContractsListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = reportService.getContractsListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	@RequestMapping(value = "/ajax/getHODSListForContractResourceReportForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getHODSListForContractResourceReportForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = reportService.getHODsListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHODSListForContractResourceReportForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/generate-contract-resource-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateContractResourceReport(@ModelAttribute ContractResource obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
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
		
			//List<ContractResource> structuresList = service.getStructuresList(obj);
			ContractResource reportData = reportService.getContarctResourceReportData(obj);
			
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
	        CellStyle numberStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle activityNameStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        /********************************************************/

            /********************************************************/
	        int sheetNo = 0;
	        int len  = 0;
	        String contarct_id = null;
	        if(!(StringUtils.isEmpty(reportData))) {
	        
	        	 for (ContractResource zObj : reportData.getContarctsList()) {  
	        		contarct_id = zObj.getContract_id_fk();
			        XSSFSheet dprSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(contarct_id));
			        workBook.setSheetOrder(dprSheet.getSheetName(), sheetNo++);
			        
			        XSSFRow dateRow = dprSheet.createRow(2);
			        
			        Cell cell = dateRow.createCell(0);
		
					/********************************************************/	
			        
			        /********************************************************/	
			        XSSFRow deatilsRow = dprSheet.createRow(2);
			   
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue("Daily Resource Report for Period : ");
					cell = deatilsRow.createCell(3);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue("From");
					
					cell = deatilsRow.createCell(4);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(from_date);
					
					cell = deatilsRow.createCell(5);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue("To");
					
					cell = deatilsRow.createCell(6);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue(to_date);
					
					for (int i = 1; i < 3; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 0,2));

					
					//dprSheet.addMergedRegion(new CellRangeAddress(2, 2, 1,5));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(3);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue("Work ");
					for (int i = 1; i < 3; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 0,2));
					cell = deatilsRow.createCell(3);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue( (!StringUtils.isEmpty(zObj.getWork_short_name())?zObj.getWork_short_name():zObj.getWork_name()));
					
					for (int i = 4; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(3, 3, 3,6));
			        
					/********************************************************/
			        
					/********************************************************/	
			        deatilsRow = dprSheet.createRow(4);
			        
			        cell = deatilsRow.createCell(0);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue("Contract ");
					for (int i = 1; i < 3; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(greenStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4, 4, 0,2));
					cell = deatilsRow.createCell(3);
			        cell.setCellStyle(indexWhiteStyle);
					cell.setCellValue((!StringUtils.isEmpty(zObj.getContract_short_name())?zObj.getContract_short_name():zObj.getContract_name()));
			        
					for (int i = 4; i < 7; i++) {		        	
				        cell = deatilsRow.createCell(i);
				        cell.setCellStyle(indexWhiteStyle);
						cell.setCellValue("");
					}	
					dprSheet.addMergedRegion(new CellRangeAddress(4,4, 3,6));
				
					/********************************************************/
			        
					/*************************************************************************/		
					Object [] actualDates = new Object[obj.getDatesList().size()];
					int x= 0,a=0;
					for(ContractResource actDates : obj.getDatesList()) {
						actualDates[x++] = actDates.getDate();
					}	
					int dSiz = obj.getDatesList().size();

					if(zObj.getDataList() != null && zObj.getDataList().size() > 0){
						int rowNo = 5;
				            int tempRowNo = rowNo;
				            XSSFRow structureRow = dprSheet.createRow(rowNo);
					
				            /**********************************************************************/
							String headerString = "^Contract^Resource Type^dates^Average Daily deployment";
					        
					        String[] headerStringArr = headerString.split("\\^");
					        int HeaderSize = obj.getDatesList().size();
					        HeaderSize = HeaderSize + 3;
					        XSSFRow headingRow = dprSheet.createRow(rowNo++);
				        	int j = 0;
					        for (int i = 0; i < headerStringArr.length ; i++) {	
					        	if(headerStringArr[i].equalsIgnoreCase( "dates")) {
					        		for(ContractResource date : obj.getDatesList()) {
					        			 cell = headingRow.createCell(j);
				 						 cell.setCellStyle(greenStyle1);
				 						 DateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
				 						 String dateValue = date.getDate();
				 						 Date dates = inputFormat2.parse(dateValue);
				 						 String dateFields = outputFormat.format(dates);
										 cell.setCellValue(dateFields);
										 j++;
					        		}
					        	}else {
					        			 cell = headingRow.createCell(j);
					        	    	 cell.setCellStyle(greenStyle1);
										 cell.setCellValue(headerStringArr[i]);
										 j++; 
					        	}
							}				
								//rowNo++;
						        int sNo = 1;
					        /***********************************************************************/
						    for (ContractResource dObj : zObj.getDataList()) {
						        XSSFRow row = dprSheet.createRow(rowNo);
						        int c = 0;
						        cell = row.createCell(c++);
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(sNo++);
								
						        cell = row.createCell(c++);
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(dObj.getResource_name());
								
								cell = row.createCell(c++);
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(dObj.getResource_type());
								String stored = null;
								int v = 0;
								breakLoop:
								for(ContractResource actDates : obj.getDatesList()) {
									for(ContractResource qtyObj : dObj.getQuantityList()) {
										stored = qtyObj.getDate();
											if(!(v == (dSiz))){
												if( (actualDates[v]).equals(stored)) {
													if(!StringUtils.isEmpty(qtyObj.getQuantity())) {
														cell = row.createCell(c++);
														cell.setCellStyle(numberStyle);
														cell.setCellValue(qtyObj.getQuantity());
													}else {
														cell = row.createCell(c++);
														cell.setCellStyle(numberStyle);
														cell.setCellValue("0");
													}
												}else {
														cell = row.createCell(c++);
														cell.setCellStyle(numberStyle);
														cell.setCellValue("0");
												}
												v++;
											}else {
												break breakLoop;
											}
										}
								}
								cell = row.createCell(c++);
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(dObj.getAverage());
								
						        rowNo++;
						    }
						    
						    for(int columnIndex = 1; columnIndex < HeaderSize; columnIndex++) {
						    	dprSheet.setColumnWidth(0, 25 * 30);
						    	//dprSheet.autoSizeColumn(columnIndex);
				            	dprSheet.setColumnWidth(columnIndex, 25 * 150);
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
            String fileName = "Contract_Resource_Report_"+dateFormat.format(date);
            
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
                logger.error("generateContractResourceReport : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                logger.error("generateContractResourceReport : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportError);
            }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateContractResourceReport : " + e.getMessage());
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

