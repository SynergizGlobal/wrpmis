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
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.NewActivitiesUpdateService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.StripChart;

@Controller
public class NewActivitiesUpdateController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	NewActivitiesUpdateService newActivitiesUpdateService;


	@RequestMapping(value="/new-activities-update",method=RequestMethod.GET)
	public ModelAndView newAcivitiesUpdate(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.newActivitiesUpdate);
		try {
			
			User uObj = (User) session.getAttribute("user");
			if(!StringUtils.isEmpty(uObj)) {
				obj.setUser_type_fk(uObj.getUser_type_fk());
				obj.setUser_role_code(uObj.getUser_role_code());
				obj.setUser_id(uObj.getUser_id());
				obj.setDepartment_fk(uObj.getDepartment_fk());
			}
			
			List<StripChart> projectsList = newActivitiesUpdateService.getNewActivitiesUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = newActivitiesUpdateService.getNewActivitiesUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			List<StripChart> contractsList = newActivitiesUpdateService.getNewActivitiesUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AcivitiesBulkUpload : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/new-activities-update/{activity_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView newAcivitiesBulkUpdate(@PathVariable("activity_id")String activity_id,@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.newActivitiesUpdate);
		try {
			User uObj = (User) session.getAttribute("user");
			if(!StringUtils.isEmpty(uObj)) {
				obj.setUser_type_fk(uObj.getUser_type_fk());
				obj.setUser_role_code(uObj.getUser_role_code());
				obj.setUser_id(uObj.getUser_id());
				obj.setDepartment_fk(uObj.getDepartment_fk());
			}
			
			List<StripChart> projectsList = newActivitiesUpdateService.getNewActivitiesUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			/*List<StripChart> worksList = newActivitiesUpdateService.getNewActivitiesUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<StripChart> contractsList = newActivitiesUpdateService.getNewActivitiesUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);*/
			
			obj.setActivity_id(activity_id);
			StripChart activitiesData = newActivitiesUpdateService.getNewAcivitiesUpdateData(obj);
			model.addObject("activitiesData", activitiesData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AcivitiesBulkUpload : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateProjectsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateProjectsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> projects = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			projects = newActivitiesUpdateService.getNewActivitiesUpdateProjectsList(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateProjectsList() : "+e.getMessage());
		}
		return projects;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateWorksList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = newActivitiesUpdateService.getNewActivitiesUpdateWorksList(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesUpdateWorksList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = newActivitiesUpdateService.getWorksList(obj);			
		}catch(Exception e){
			logger.error("getDeleteActivitiesUpdateWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesUpdateContractsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = newActivitiesUpdateService.getContractsList(obj);			
		}catch(Exception e){
			logger.error("getDeleteActivitiesUpdateContractsList() : "+e.getMessage());
		}
		return works;
	}
	
	
	/*
	 * @RequestMapping(value = "/ajax/exportActivitiesbyContract", method =
	 * {RequestMethod.GET,RequestMethod.POST},produces=MediaType.
	 * APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public void exportActivitiesbyContract(HttpServletRequest
	 * request, HttpServletResponse response,HttpSession session,@ModelAttribute
	 * StripChart dObj,RedirectAttributes attributes){ List<StripChart> dataList =
	 * new ArrayList<StripChart>();
	 * 
	 * List<StripChart> subList = new ArrayList<StripChart>();
	 * 
	 * try { User uObj = (User) session.getAttribute("user");
	 * dObj.setUser_type_fk(uObj.getUser_type_fk());
	 * dObj.setUser_role_code(uObj.getUser_role_code());
	 * dObj.setUser_id(uObj.getUser_id()); dataList =
	 * newActivitiesUpdateService.getExportActivitiesbyContract(dObj);
	 * 
	 * 
	 * if(dataList != null && dataList.size() > 0){ XSSFWorkbook workBook = new
	 * XSSFWorkbook (); XSSFSheet StripChartSheet =
	 * workBook.createSheet(WorkbookUtil.createSafeSheetName("Utility Shifting"));
	 * XSSFSheet subSheet =
	 * workBook.createSheet(WorkbookUtil.createSafeSheetName("Progress Details"));
	 * XSSFSheet refSheet =
	 * workBook.createSheet(WorkbookUtil.createSafeSheetName("Reference Data"));
	 * 
	 * 
	 * workBook.setSheetOrder(StripChartSheet.getSheetName(), 0);
	 * workBook.setSheetOrder(subSheet.getSheetName(), 1);
	 * workBook.setSheetOrder(refSheet.getSheetName(), 2);
	 * 
	 * 
	 * byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240}; byte[] yellowRGB
	 * = new byte[]{(byte)255, (byte)192, (byte)0}; byte[] greenRGB = new
	 * byte[]{(byte)146, (byte)208, (byte)80}; byte[] redRGB = new byte[]{(byte)255,
	 * (byte)0, (byte)0}; byte[] whiteRGB = new byte[]{(byte)255, (byte)255,
	 * (byte)255};
	 * 
	 * boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText =
	 * false; int fontSize = 11;String fontName = "Times New Roman"; CellStyle
	 * blueStyle =
	 * cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.
	 * CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName); CellStyle
	 * yellowStyle =
	 * cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment
	 * .CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName); CellStyle
	 * greenStyle =
	 * cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.
	 * CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName); CellStyle
	 * redStyle =
	 * cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.
	 * CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName); CellStyle
	 * whiteStyle =
	 * cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.
	 * CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	 * 
	 * CellStyle indexWhiteStyle =
	 * cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.
	 * CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	 * 
	 * isWrapText = true;isBoldText = false;isItalicText = false; fontSize =
	 * 9;fontName = "Times New Roman"; CellStyle sectionStyle =
	 * cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.
	 * CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	 * 
	 * 
	 * 
	 * XSSFRow headingRow = StripChartSheet.createRow(0); String headerString =
	 * "Utility ID ^Project ^Work ^Execution Agency ^HOD ^Utility Type ^Utility Description ^Location ^Custodian ^Identification Date ^Reference No. "
	 * + "^Chainage ^Executed By ^Impacted Contract  ^Requirement stage " +
	 * "^Impacted Element ^Affected Structures  ^Target Date ^Scope ^Completed ^Unit ^Start Date ^Status ^Completetion Date ^Remarks"
	 * ;
	 * 
	 * String[] firstHeaderStringArr = headerString.split("\\^");
	 * StripChartSheet.createFreezePane(0,1); for (int i = 0; i <
	 * firstHeaderStringArr.length; i++) { Cell cell = headingRow.createCell(i);
	 * cell.setCellStyle(greenStyle); cell.setCellValue(firstHeaderStringArr[i]); }
	 * 
	 * short rowNo = 1; for (StripChart obj : dataList) {
	 * 
	 * XSSFRow row = StripChartSheet.createRow(rowNo); int c = 0;
	 * 
	 * Cell cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getUtility_shifting_id());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getProject_name());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getWork_short_name());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getExecution_agency_fk());
	 * 
	 * 
	 * String hod = ""; if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {hod =
	 * obj.getHod_user_id_fk() +" - "+obj.getUser_name();}
	 * 
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getDesignation());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getUtility_type_fk());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getUtility_description());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getLocation_name());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getCustodian());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getIdentification());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getReference_number());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getChainage());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getExecuted_by());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getContract_short_name());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getRequirement_stage_fk());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getImpacted_element());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getAffected_structures());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getPlanned_completion_date());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getScope());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getCompleted());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getUnit_fk());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getStart_date());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getShifting_status_fk());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getShifting_completion_date());
	 * 
	 * cell = row.createCell(c++); cell.setCellStyle(sectionStyle);
	 * cell.setCellValue(obj.getRemarks());
	 * 
	 * rowNo++; }
	 * 
	 * 
	 * 
	 *//*************************************************************//*
																		 * 
																		 * 
																		 * for(int columnIndex = 0; columnIndex < 29;
																		 * columnIndex++) {
																		 * StripChartSheet.setColumnWidth(columnIndex,
																		 * 25 * 200); }
																		 * 
																		 * DateFormat dateFormat = new
																		 * SimpleDateFormat("yyyy-MM-dd-HHmmss"); Date
																		 * date = new Date(); String fileName =
																		 * "Utility_Shifting_"+dateFormat.format(date);
																		 * 
																		 * try{ FileOutputStream fos = new
																		 * FileOutputStream(fileDirectory
																		 * +fileName+".xls"); workBook.write(fos);
																		 * fos.flush();
																		 * 
																		 * response.setContentType("application/.csv");
																		 * response.setContentType(
																		 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
																		 * ); response.setContentType(
																		 * "application/vnd.ms-excel"); // add response
																		 * header
																		 * response.addHeader("Content-Disposition",
																		 * "attachment; filename=" + fileName+".xlsx");
																		 * 
																		 * //copies all bytes from a file to an output
																		 * stream
																		 * workBook.write(response.getOutputStream());
																		 * // Write workbook to response.
																		 * workBook.close(); //flushes output stream
																		 * response.getOutputStream().flush();
																		 * 
																		 * 
																		 * attributes.addFlashAttribute("success",
																		 * dataExportSucess); //response.setContentType(
																		 * "application/vnd.ms-excel");
																		 * //response.setHeader("Content-Disposition",
																		 * "attachment; filename=filename.xls");
																		 * //XSSFWorkbook workbook = new XSSFWorkbook
																		 * (); // ... // Now populate workbook the usual
																		 * way. // ...
																		 * //workbook.write(response.getOutputStream());
																		 * // Write workbook to response.
																		 * //workbook.close();
																		 * }catch(FileNotFoundException e){
																		 * //e.printStackTrace();
																		 * attributes.addFlashAttribute("error",
																		 * dataExportInvalid); }catch(IOException e){
																		 * //e.printStackTrace();
																		 * attributes.addFlashAttribute("error",
																		 * dataExportError); } }else{
																		 * attributes.addFlashAttribute("error",
																		 * dataExportNoData); } }catch (Exception e) {
																		 * e.printStackTrace(); } }
																		 */	
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
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateContractsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> contracts = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			obj.setDepartment_fk(uObj.getDepartment_fk());
			
			contracts = newActivitiesUpdateService.getNewActivitiesUpdateContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = newActivitiesUpdateService.getNewActivitiesUpdateStructures(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = newActivitiesUpdateService.getDeleteActivitiesStructures(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}	
	
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateInProgressStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateInProgressStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = newActivitiesUpdateService.getNewActivitiesUpdateInProgressStructures(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateLines", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateLines(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = newActivitiesUpdateService.getNewActivitiesUpdateLines(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateLines() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateSections", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateSections(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = newActivitiesUpdateService.getNewActivitiesUpdateSections(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateComponentsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateComponentsList(@ModelAttribute StripChart obj){
		List<StripChart> components = null;
		try{
			components = newActivitiesUpdateService.getNewActivitiesUpdateComponentsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentsList() : "+e.getMessage());
		}
		return components;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = newActivitiesUpdateService.getNewActivitiesUpdateComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesComponentsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesComponentsList(@ModelAttribute StripChart obj){
		List<StripChart> components = null;
		try{
			components = newActivitiesUpdateService.getDeleteActivitiesComponentsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentsList() : "+e.getMessage());
		}
		return components;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesComponentIdsList(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = newActivitiesUpdateService.getDeleteActivitiesComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}	
	
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkActivitiesList(@ModelAttribute StripChart obj){
		List<StripChart> activities = null;
		try{
			activities = newActivitiesUpdateService.getAcivitiesBulkActivitiesList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StripChart getNewActivitiesUpdateDetails(@ModelAttribute StripChart obj){
		StripChart data = null;
		try{
			data = newActivitiesUpdateService.getNewActivitiesUpdateDetails(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateDetails() : "+e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesfiltersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesfiltersList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> fileterData = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			fileterData = newActivitiesUpdateService.getNewActivitiesfiltersList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesfiltersList() : "+e.getMessage());
		}
		return fileterData;
	}
	
	
	@RequestMapping(value = "/ajax/getStructureTypesInActivitiesUpdate", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStructureTypesInActivitiesUpdate(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = newActivitiesUpdateService.getStructureTypesInActivitiesUpdate(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureTypesInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value = "/ajax/getStructureTypesInDeleteActivities", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStructureTypesInDeleteActivities(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = newActivitiesUpdateService.getStructureTypesInDeleteActivities(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureTypesInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}		
	

	@RequestMapping(value = "/update-new-activities-bulk", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateNewAcivitiesBulk(@ModelAttribute StripChart obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/new-activities-update");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			obj.setCreated_by_user_id_fk(user_Id);
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			//obj.setProgress_date(DateParser.parse(obj.getProgress_date()));
			boolean flag =  newActivitiesUpdateService.updateNewAcivitiesBulk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Acivities Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Acivities are failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Acivities are failed. Try again.");
			logger.error("updateAcivitiesBulk : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/delete-activities-bulk", method = {RequestMethod.POST})
	public ModelAndView deleteActivitiesBulk(@ModelAttribute StripChart obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/delete-activities");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			obj.setCreated_by_user_id_fk(user_Id);
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			//obj.setProgress_date(DateParser.parse(obj.getProgress_date()));
			boolean flag =  newActivitiesUpdateService.deleteAcivitiesBulk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Acivities Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Deleting Acivities failed.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Deleting Acivities failed.");
			logger.error("deleteActivitiesBulk : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/delete-activities",method=RequestMethod.GET)
	public ModelAndView DeleteActivities(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.deleteActivities);
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			obj.setDepartment_fk(uObj.getDepartment_fk());
			
			List<StripChart> projectsList = newActivitiesUpdateService.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = newActivitiesUpdateService.getWorksList(obj);
			model.addObject("worksList", worksList);
			List<StripChart> contractsList = newActivitiesUpdateService.getContractsList(obj);
			model.addObject("contractsList", contractsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DeleteActivities : " + e.getMessage());
		}
		return model;
	}
	
	

	@RequestMapping(value = "/ajax/getDeleteActivitiesfiltersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesfiltersList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> fileterData = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			fileterData = newActivitiesUpdateService.getDeleteActivitiesfiltersList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesfiltersList() : "+e.getMessage());
		}
		return fileterData;
	}	
	
}

