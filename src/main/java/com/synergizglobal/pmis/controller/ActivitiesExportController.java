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

import com.synergizglobal.pmis.Iservice.ActivitiesExportService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.StripChart;

@Controller
public class ActivitiesExportController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public static Logger logger = Logger.getLogger(ActivitiesExportController.class);

	@Autowired
	ActivitiesExportService service;

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
	
	@RequestMapping(value = "/activities-export-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView activitiesExportReport(@ModelAttribute StripChart obj, RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView(PageConstants.activitiesExportReport);
		try {
			List<StripChart> projectsList = service.getProjectsFilterListInActivitiesExportReport(obj);
			model.addObject("projectsList", projectsList);

			List<StripChart> worksList = service.getWorksFilterListInActivitiesExportReport(obj);
			model.addObject("worksList", worksList);

			List<StripChart> contractList = service.getContractListInActivitiesExportReport(obj);
			model.addObject("contractList", contractList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ActivitiesExportReport : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getProjectListForActivitiesExportReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProjectListForActivitiesExportReportForm(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getProjectsFilterListInActivitiesExportReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectListForActivitiesExportReportForm : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getWorksListForActivitiesExportReportForm", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getWorksListForActivitiesExportReportForm(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getWorksFilterListInActivitiesExportReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForActivitiesExportReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/ajax/getContractListInActivitiesExportReport", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getLocationsListForActivitiesExportReportForm(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = service.getContractListInActivitiesExportReport(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getLocationsListForActivitiesExportReportForm : " + e.getMessage());
		}
		return objList;
	}

	@RequestMapping(value = "/generate-activities-export-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateActivitiesExportReport(@ModelAttribute StripChart obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		//ModelAndView model = new ModelAndView("redirect:/activities-progress-report");
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date());
			
			SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-YY");
		
			//List<StripChart> structuresList = service.getStructuresList(obj);
			
			StripChart reportData = service.generateActivitiesExportReport(obj);
			
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
	        CellStyle activityNameStyle3 = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        
	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 11;fontName = "Calibri";
	        CellStyle numberStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle activityNameStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle activityNameStyle1 = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle activityNameStyle2 = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

	        CellStyle indexShadedStyle = cellFormating(workBook,greyRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,true,isItalicText,11,fontName);


	        /********************************************************/
          
	        if(!(StringUtils.isEmpty(reportData))){
	        	
       		 	XSSFSheet rrSheet1 = workBook.createSheet(WorkbookUtil.createSafeSheetName(obj.getContract_id_fk() +" - Activities Report"));
		        XSSFRow headRow = rrSheet1.createRow(0);
		        
		        Cell cell = headRow.createCell(0);
		        
		        XSSFRow mainHeadingRow = rrSheet1.createRow(1);
		        
		        cell = mainHeadingRow.createCell(0);
		        cell.setCellStyle(bluetyle);
		        String contract_d = "";
				if(!StringUtils.isEmpty(obj.getContract_id_fk())) {
					contract_d = reportData.getReport1List().get(0).getContract_short_name()+" - " ;
				}
		        cell.setCellValue(contract_d+"Activities Report");
		        
		        for (int i = 1; i < 5; i++) {		        	
			        cell = mainHeadingRow.createCell(i);
			        cell.setCellStyle(bluetyle);
					cell.setCellValue("");
				}	
		        rrSheet1.addMergedRegion(new CellRangeAddress(1, 1, 0,4));
		int rowNo = 3;

        XSSFRow structureRow = rrSheet1.createRow(rowNo);

        /**********************************************************************/
		String headerString = "Primavera Activity Code^Type of Structure^Unique Structure ID " + 
				"(X axis in Strip Chart) ^Type of Component" + 
				"(Y axis in Strip Chart) ^Unique Component ID (Structure to Structure ID)" + 
				" ^Name of the Activity ^Unit of scope^Scope Quantity^Completed Quantity^"
				+ "Weightage points of the Activity in structure^Expected start date of the activity (dd-mm-yyyy)^"
				+ "Expected finish date of the activity (dd-mm-yyyy)^Actual start date of the activity (dd-mm-yyyy)^"
				+ "Actual finish date of the activity (dd-mm-yyyy)^Detail of component if Any^for filtering of Section of Line  on Map	^^^"
				+ "Line of the activity (eg Up Line Down Line)^Any Additional Remarks";
        String[] headerStringArr = headerString.split("\\^");
        
        XSSFRow headingRow = rrSheet1.createRow(rowNo);
        for (int i = 0; i < headerStringArr.length ; i++) {	
        	
    			 cell = headingRow.createCell(i);
    	    	 cell.setCellStyle(whiteStyle);
				 cell.setCellValue(headerStringArr[i]);
		}	

        rowNo++;
        structureRow = rrSheet1.createRow(rowNo);
        
        String headerString1 = "P6 Task Code^Structure Type^Structure ID^Component^Component ID^Activity^Unit^"
        		+ "Total Scope^Completed^Weightage Point^Planned Start Date^Planned Finish Date^Actual Start Date^Actual Finish Date^Components Detail^"
        		+ "Section^From Structure ID^To Structure ID^Line^Remarks";
        String[] headerStringArr1 = headerString1.split("\\^");
        
        XSSFRow headingRow1 = rrSheet1.createRow(rowNo);
        for (int i = 0; i < headerStringArr1.length ; i++) {	
        	
    			 cell = headingRow1.createCell(i);
    	    	 cell.setCellStyle(greenStyle1);
				 cell.setCellValue(headerStringArr1[i]);
		}	
        rowNo++;
        int x = 0,z=0;
	        	 for (StripChart zObj : reportData.getReport1List()) {  
	        		  XSSFRow row = rrSheet1.createRow(rowNo);
				            int c = 0;
				       
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getP6_task_code());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getStructure_type_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getStructure());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getComponent());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getComponent_id());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getActivity_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getUnit());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getScope());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getCompleted());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getWeightage());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getPlanned_start());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getPlanned_finish());
							
							cell = row.createCell(c++);
							if(StringUtils.isEmpty(zObj.getActual_start())) {
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(zObj.getPlanned_start());
							}else {
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(zObj.getActual_start());
							}
							
							cell = row.createCell(c++);
						    if(StringUtils.isEmpty(zObj.getActual_finish())) {
					        	cell.setCellStyle(activityNameStyle);
								cell.setCellValue(zObj.getPlanned_finish());
					        }else {
								cell.setCellStyle(activityNameStyle);
								cell.setCellValue(zObj.getActual_finish());
							}
						
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getComponent_details());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getSection());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getFrom_structure_id());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getTo_structure_id());
						
						    cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getLine());
							
							cell = row.createCell(c++);
							cell.setCellStyle(activityNameStyle);
							cell.setCellValue(zObj.getRemarks());
						
					        rowNo++;
	        	 }
				  for(int columnIndex = 1; columnIndex < headerStringArr.length; columnIndex++) {
				  	rrSheet1.setColumnWidth(columnIndex, 25 * 150);
				  	//rrSheet1.autoSizeColumn(columnIndex);
				  	rrSheet1.setColumnWidth(4, 35 * 235);
					rrSheet1.setColumnWidth(5, 35 * 235);
				   }

            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Activities_Export_Report_"+dateFormat.format(date);
            
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
                logger.error("generateActivitiesExportMain : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                logger.error("generateActivitiesExportMain : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportError);
            }
	      }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateActivitiesExportMain : " + e.getMessage());
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
