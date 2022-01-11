package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import org.docx4j.org.xhtmlrenderer.css.parser.property.OneToFourPropertyBuilders.BorderColor;
import org.docx4j.org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.BorderBottomColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.ZonalRailwayService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.ZonalRailway;
import com.synergizglobal.pmis.model.ZonalsPaginationObject;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.ZonalRailway;

@Controller
public class ZonalRailwayController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ZonalRailwayController.class);
	
	@Autowired
	ZonalRailwayService service;
	


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
	@RequestMapping(value="/zonal-railway",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView zonalRailway(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.zonalRailwayGrid);
		try {
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("zonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-zonal-railway", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute ZonalRailway obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");

			pw = response.getWriter();
			//Fetch the page number from client
			Integer pageNumber = 0;
			Integer pageDisplayLength = 0;
			if (null != request.getParameter("iDisplayStart")) {
				pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			}
			//Fetch search parameter
			String searchParameter = request.getParameter("sSearch");

			//Fetch Page display length
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			List<ZonalRailway> zonalList = new ArrayList<ZonalRailway>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				zonalList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				zonalList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//zonalList = getListBasedOnSearchParameter(searchParameter,zonalList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ZonalsPaginationObject personJsonObject = new ZonalsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(zonalList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(ZonalRailway obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = service.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	public List<ZonalRailway> createPaginationData(int startIndex, int offset,ZonalRailway obj, String searchParameter) {
		List<ZonalRailway> earthWorkList = null;
		try {
			earthWorkList = service.getZonalsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getWorksList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getProjectsList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getExecutionAgencyRailwayFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getExecutionAgencyRailwayList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayExecutionAgencyRailwayList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getExecutionAgencyRailwayList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getSourceOfFundsFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getSourceOfFundsList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwaySourceOfFundsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSourceOfFundsList : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusFilterListInZonalRailway", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getStatusList(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objList = null;
		try {
			objList = service.getZonalRailwayStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForZonalRailwayForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getWorkListForZonalRailwayForm(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objsList = null;
		try {
			objsList = service.getWorkListForZonalRailwayForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForZonalRailwayForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getRailwayListForZonalRailwayForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ZonalRailway> getRailwayListForZonalRailwaysForm(@ModelAttribute ZonalRailway obj) {
		List<ZonalRailway> objsList = null;
		try {
			objsList = service.getRailwayListForZonalRailwaysForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRailwayListForZonalRailwaysForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-zonal-railway-form", method = {RequestMethod.GET})
	public ModelAndView addZonalRailwaytForm(@ModelAttribute ZonalRailway obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "add");
			
			List<ZonalRailway> projectsList = service.getProjectsListForZonalRailwayForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<ZonalRailway> worksList = service.getWorkListForZonalRailwayForm(obj);
			model.addObject("worksList", worksList);
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			List<ZonalRailway> unitsList = service.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
			
		}catch (Exception e) {
				logger.error("addZonalRailwaytForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-zonal-railway", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getZonalRailwayForm(@ModelAttribute ZonalRailway obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "edit");
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			List<ZonalRailway> unitsList = service.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
			
			ZonalRailway zonalRailwayDetails = service.getZonalRailway(obj);
			model.addObject("zonalRailwayDetails", zonalRailwayDetails);
			
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getZonalRailwayForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-zonal-railway/{contract_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getZonalRailwayForm(@ModelAttribute ZonalRailway obj,@PathVariable("contract_id") String contract_id,HttpSession session,RedirectAttributes attributes  ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditZonalRailway);
			model.addObject("action", "edit");
			
			List<ZonalRailway> railwayList = service.getRailwayListForZonalRailwayForm(obj);
			model.addObject("railwayList", railwayList);
			
			List<ZonalRailway> sourceOfFundList = service.getSourceOfFundListForZonalRailwayForm(obj);
			model.addObject("sourceOfFundList", sourceOfFundList);
			
			List<ZonalRailway> statusList = service.getStatusListForZonalRailwayForm(obj);
			model.addObject("statusList", statusList);
			
			List<ZonalRailway> unitsList = service.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<ZonalRailway> usersList = service.getUserListForZonalRailwayForm(obj);
			model.addObject("usersList", usersList);
			
			ZonalRailway zonalRailwayDetails = service.getZonalRailway(obj);
			model.addObject("zonalRailwayDetails", zonalRailwayDetails);
			
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getZonalRailwayForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-zonal-railway", method = {RequestMethod.POST})
	public ModelAndView addZonalRailway(@ModelAttribute ZonalRailway obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway");
			obj.setAs_on_date(DateParser.parse(obj.getAs_on_date()));
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setExpected_finish(DateParser.parse(obj.getExpected_finish()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.addZonalRailway(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Zonal Railway is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Zonal Railway is failed. Try again.");
			logger.error("addZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-zonal-railway", method = {RequestMethod.POST})
	public ModelAndView updateZonalRailway(@ModelAttribute ZonalRailway obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway");
			obj.setAs_on_date(DateParser.parse(obj.getAs_on_date()));
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setExpected_finish(DateParser.parse(obj.getExpected_finish()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.updateZonalRailway(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Zonal Railway is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Zonal Railway is failed. Try again.");
			logger.error("updateZonalRailway : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-zonal-railway", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportZonalRailway(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute ZonalRailway zObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.zonalRailwayGrid);
		List<ZonalRailway> dataList = new ArrayList<ZonalRailway>();
		List<ZonalRailway> progressList = new ArrayList<ZonalRailway>();
		try {
			view.setViewName("redirect:/zonal-railway");
			dataList =   service.getZonalRailwayList(zObj);
			if(dataList != null && dataList.size() > 0){
				int i =0;
				XSSFWorkbook  workBook = new XSSFWorkbook ();
		        XSSFSheet sheet =  workBook.createSheet(WorkbookUtil.createSafeSheetName("Zonal_Contracts"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Calibri";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormatingHeader(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Calibri";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle costSectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle dateStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);

		        XSSFRow headingRow = sheet.createRow(0);
		        String headerString = "Contract ID^Work^Execution Agency^Sub Work^Nodal Officer in MRVC^Source Of Funds^Sanction Cost in Cr ^Latest Revised Cost in Cr"
		        		+ "^Cumulative Actual Expenditure in Cr^Actual Start^Target For Completion^Actual Finish^Completion Cost in Cr^Status^As On Date";
		        String[] firstHeaderStringArr = headerString.split("\\^");
	            for (int j = 0; j < firstHeaderStringArr.length; j++) {		        	
		        	Cell cell = headingRow.createCell(j);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[j]);
				}
	            int  k =0;
	            short rowNo = 1;
	            for (ZonalRailway obj : dataList) {
	            	int j = 0;
	                XSSFRow row = sheet.createRow(rowNo);
	                int c = 0;
	                
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_id_fk() +" - "+obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getExecution_agency_railway_fk() +" - "+obj.getRailway_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSub_work());
					
	                String responsibleUser = "";
	                if(!StringUtils.isEmpty(obj.getResponsible_person_user_fk())) {
	                	String userName = "",designation="";
	                	if(!StringUtils.isEmpty(obj.getUser_name())){userName = " - "+obj.getUser_name();}
	                	if(!StringUtils.isEmpty(obj.getDesignation())){designation = obj.getDesignation();}
	                	responsibleUser = designation + userName;
	                }
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(responsibleUser);
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSource_of_funds());
					
				    DecimalFormat df = new DecimalFormat("#.#");
			        df.setMaximumFractionDigits(8);
			        double sanction_cost = 0,latest_revised_cost= 0,completion_cost= 0,cum_actual_expenditure = 0;
					if(!StringUtils.isEmpty(obj.getSanction_cost())) {
						sanction_cost = Double.parseDouble(obj.getSanction_cost());
					}
					if(!StringUtils.isEmpty(obj.getLatest_revised_cost())) {
						latest_revised_cost = Double.parseDouble(obj.getLatest_revised_cost());
					}
					if(!StringUtils.isEmpty(obj.getCompletion_cost())) {
						completion_cost = Double.parseDouble(obj.getCompletion_cost());
					}
					if(!StringUtils.isEmpty(obj.getCum_actual_expenditure())) {
						cum_actual_expenditure = Double.parseDouble(obj.getCum_actual_expenditure());
					}
					cell = row.createCell(c++);
					cell.setCellStyle(costSectionStyle);
					cell.setCellValue(df.format(sanction_cost));
					
					cell = row.createCell(c++);
					cell.setCellStyle(costSectionStyle);
					cell.setCellValue(df.format(latest_revised_cost));
					
					cell = row.createCell(c++);
					cell.setCellStyle(costSectionStyle);
					cell.setCellValue(df.format(cum_actual_expenditure));
					
					cell = row.createCell(c++);
					cell.setCellStyle(dateStyle);
					cell.setCellValue(obj.getActual_start());
					
					cell = row.createCell(c++);
					cell.setCellStyle(dateStyle);
					cell.setCellValue(obj.getExpected_finish());
					
					cell = row.createCell(c++);
					cell.setCellStyle(dateStyle);
					cell.setCellValue(obj.getActual_finish());
					
					cell = row.createCell(c++);
					cell.setCellStyle(costSectionStyle);
					cell.setCellValue(df.format(completion_cost));
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStatus_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(dateStyle);
					cell.setCellValue(obj.getAs_on_date());
					
	                //row.createCell((short)j++).setCellValue(obj.getDesignation());
	          
	                rowNo++;
	            }
	           
	            XSSFSheet progressSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Zonal_Progress"));
		        workBook.setSheetOrder(progressSheet.getSheetName(), 1);
		        XSSFRow headingRow1 = progressSheet.createRow(0);
		        String headerString2 = "Contract ID^Sub Work^Month^Cum Planned  Financial Progress(%)^Cum Actual  Financial Progress(%)^Cum Planned Physical Progress(%)"
		        		+ "^Cum Actual Physical Progress(%)^Progress^Issue^Assistance Required";
		        String[] firstHeaderStringArr2 = headerString2.split("\\^");
		        for (int j = 0; j < firstHeaderStringArr2.length; j++) {		        	
		        	Cell cell = headingRow1.createCell(j);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr2[j]);
				}
	            short rowNo2 = 1,n=0;
	        	for (ZonalRailway progress : dataList) { 
	        		String id = progress.getContract_id();
	        		progressList = service.getProgressList(id);
		           
		            for (ZonalRailway sObj : progressList) {
		                XSSFRow row2 = progressSheet.createRow(rowNo2);
		                int c = 0;
		                
		                Cell cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getContract_id());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue (sObj.getSub_work());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(dateStyle);
						cell.setCellValue(sObj.getMonth());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getCum_planned_expenditure_per());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getCum_actual_expenditure_per());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getCum_planned_physical_progress_per());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getCum_actual_physical_progress_per());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getProgress());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getIssue());
						
						cell = row2.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(sObj.getAssistance_required());
		          
		                rowNo2++;n++;
		            }
	        	}
	        	for(int columnIndex = 0; columnIndex < firstHeaderStringArr.length; columnIndex++) {
		            	//sheet.autoSizeColumn(columnIndex);
		        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < firstHeaderStringArr2.length; columnIndex++) {
	        		//revisionSheet.autoSizeColumn(columnIndex);
	        		progressSheet.setColumnWidth(columnIndex, 25 * 170);
				}
	        	progressSheet.setColumnWidth(7, 25 * 300);progressSheet.setColumnWidth(8, 25 * 300);progressSheet.setColumnWidth(9, 25 * 300);
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Zonal_Railway_"+dateFormat.format(date);
                
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
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportInvalid);
	            }catch(IOException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportError);
	            }
			}else{
				attributes.addFlashAttribute("error",dataExportNoData);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
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
		style.setBorderBottom(BorderStyle.THIN );
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
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

private CellStyle cellFormatingHeader(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
	CellStyle style = workBook.createCellStyle();
	//Setting Background color  
	//style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	if (style instanceof XSSFCellStyle) {
	   XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle)style;
	   xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
	}
	//style.setFillPattern(FillPatternType.ALT_BARS);
	style.setBorderBottom(BorderStyle.MEDIUM );
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
}