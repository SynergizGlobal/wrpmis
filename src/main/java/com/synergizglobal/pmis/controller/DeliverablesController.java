package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Iservice.DeliverablesService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Deliverables;
import com.synergizglobal.pmis.model.DeliverablesPaginationObject;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;


@Controller
public class DeliverablesController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DeliverablesController.class);
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Autowired
	DeliverablesService deliverablesService;

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
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	@RequestMapping(value="/deliverables",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  deliverables(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.deliverablesGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("deliverables : " + e.getMessage());
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "/ajax/get-deliverables-list", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Deliverables obj, HttpServletRequest request,
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

			List<Deliverables> deliverablesList = new ArrayList<Deliverables>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				deliverablesList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				deliverablesList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//deliverablesList = getListBasedOnSearchParameter(searchParameter,deliverablesList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			DeliverablesPaginationObject personJsonObject = new DeliverablesPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(deliverablesList);

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
	public int getTotalRecords(Deliverables obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = deliverablesService.getTotalRecords(obj, searchParameter);
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
	public List<Deliverables> createPaginationData(int startIndex, int offset,Deliverables obj, String searchParameter) {
		List<Deliverables> objList = null;
		try {
			objList = deliverablesService.getDeliverablesList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	
	@RequestMapping(value = "/ajax/getStatusFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getStatusList(@ModelAttribute Deliverables obj) {
		List<Deliverables> statusList = null;
		try {
			statusList = deliverablesService.getDeliverablesStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesStatusList : " + e.getMessage());
		}
		return statusList;
	}
	
	@RequestMapping(value = "/ajax/getProjectFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getProjectsList(@ModelAttribute Deliverables obj) {
		List<Deliverables> projectsList = null;
		try {
			projectsList = deliverablesService.getDeliverablesProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getWorksList(@ModelAttribute Deliverables obj) {
		List<Deliverables> worksList = null;
		try {
			worksList = deliverablesService.getDeliverablesWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContarctFilterListInDeliverables", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getContarctsList(@ModelAttribute Deliverables obj) {
		List<Deliverables> contractsList = null;
		try {
			contractsList = deliverablesService.getDeliverablesContarctsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDeliverablesContarctsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/add-deliverables-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDeliverablesForm(@ModelAttribute Deliverables obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDeliverablesForm);
			model.addObject("action", "add");
			List<Deliverables> statusList = deliverablesService.getStatusList();
			model.addObject("statusList", statusList);
			
			List<Deliverables> deliverablesTypeList = deliverablesService.getDeliverableTypeList();
			model.addObject("deliverablesTypeList", deliverablesTypeList);
			
			List<Deliverables> milestonesList = deliverablesService.getContractMilestonesListForDeliverablesForm(obj);
			model.addObject("milestonesList", milestonesList);
			
			List<Deliverables> worksList = deliverablesService.getWorkListForDeliverablesForm(obj);
			model.addObject("worksList", worksList);
			
			List<Deliverables> contractsList = deliverablesService.getContractsListForDeliverablesForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
			model.addObject("projectsList", projectsList);
			
		}catch (Exception e) {
				logger.error("addDeliverablesForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/getProjectsListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getProjectsListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getWorkListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getWorkListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getContractsListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getContractsListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractMilestonesListForDeliverablesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Deliverables> getContractMilestonesListForDeliverablesForm(@ModelAttribute Deliverables obj) {
		List<Deliverables> objsList = null;
		try {
			objsList = deliverablesService.getContractMilestonesListForDeliverablesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractMilestonesListForDeliverablesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	
	@RequestMapping(value = "/get-deliverables", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDeliverablesForm(@ModelAttribute Deliverables obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDeliverablesForm);
			model.addObject("action", "edit");
			List<Deliverables> statusList = deliverablesService.getStatusList();
			model.addObject("statusList", statusList);
			List<Deliverables> deliverablesTypeList = deliverablesService.getDeliverableTypeList();
			model.addObject("deliverablesTypeList", deliverablesTypeList);
			List<Deliverables> milestonesList = deliverablesService.getContractMilestonesListForDeliverablesForm(obj);
			model.addObject("milestonesList", milestonesList);
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
			model.addObject("projectsList", projectsList);
			Deliverables deliverablesDetails = deliverablesService.getDeliverables(obj);
			model.addObject("deliverablesDetails", deliverablesDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDeliverables : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-deliverables/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDeliverablesFormWithId(@ModelAttribute Deliverables obj,@PathVariable("id") String id,HttpSession session,RedirectAttributes attributes ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditDeliverablesForm);
			model.addObject("action", "edit");
			List<Deliverables> statusList = deliverablesService.getStatusList();
			model.addObject("statusList", statusList);
			List<Deliverables> deliverablesTypeList = deliverablesService.getDeliverableTypeList();
			model.addObject("deliverablesTypeList", deliverablesTypeList);
			List<Deliverables> milestonesList = deliverablesService.getContractMilestonesListForDeliverablesForm(obj);
			model.addObject("milestonesList", milestonesList);
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(obj);
			model.addObject("projectsList", projectsList);
			Deliverables deliverablesDetails = deliverablesService.getDeliverables(obj);
			model.addObject("deliverablesDetails", deliverablesDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDeliverables : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-deliverables", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDeliverables(@ModelAttribute Deliverables obj,RedirectAttributes attributes,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			//obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			boolean flag =  deliverablesService.addDeliverables(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverables Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Deliverables is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Deliverables is failed. Try again.");
			logger.error("addDeliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-deliverables", method = {RequestMethod.POST})
	public ModelAndView updateDeliverables(@ModelAttribute Deliverables obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			boolean flag =  deliverablesService.updateDeliverables(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Deliverables Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Deliverables is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Deliverables is failed. Try again.");
			logger.error("updateDeliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-deliverables", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteDeliverables(@ModelAttribute Deliverables obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/deliverables");
			boolean flag =  deliverablesService.deleteDeliverables(obj);
		}catch (Exception e) {
			logger.error("deleteDeliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-deliverables", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportDeliverables(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Deliverables dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.deliverablesGrid);
		List<Deliverables> dataList = new ArrayList<Deliverables>();
		try {
			view.setViewName("redirect:/deliverables");
			dataList =  deliverablesService.getDeliverablesList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet dataSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Deliverables"));
				XSSFSheet refSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Reference Data"));
				
		        workBook.setSheetOrder(dataSheet.getSheetName(), 0);
				workBook.setSheetOrder(refSheet.getSheetName(), 1);
			
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
		        
	            XSSFRow headingRow = dataSheet.createRow(0);
	            String headerString = "Deliverable Id^Project^Work^Contract^Milestones^Deliverable type^Deliverable Description^Status^Milestone Payment^Document Name^Original Due Date^Revised Due Date^Submission Date^Approval Date^Payment %^Remarks";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            dataSheet.createFreezePane(0,1);
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				int rowNo = 1;
		        for (Deliverables obj : dataList) {
		        	if(!StringUtils.isEmpty(obj.getDeliverableDocuments()) && obj.getDeliverableDocuments().size() > 0) {
			        	for (Deliverables docObj : obj.getDeliverableDocuments()) {
			                XSSFRow row = dataSheet.createRow(rowNo);
			                int c = 0;	
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getDeliverable_document_id());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_short_name());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getContract_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							String milestone = "";
							if(!StringUtils.isEmpty(obj.getMilestone_id()) && !StringUtils.isEmpty(obj.getMilestone_name())) {
								milestone = obj.getMilestone_id() +"_"+ obj.getMilestone_name();
							} else {
								milestone = obj.getMilestone_name();
							}
							cell.setCellValue(milestone);
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDeliverable_type());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDeliverable_description());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getStatus_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getMilestone_payment());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getDeliverable_document_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getOriginal_due_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getRevised_due_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getSubmission_date());	
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getApproval_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getPayment());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getRemarks());
			                rowNo++;
			        	}
		        	}else {
		        		XSSFRow row = dataSheet.createRow(rowNo);
		                int c = 0;	
		                
		                Cell cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_document_id());
						
		                cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getProject_name());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getWork_short_name());
						
		                cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getContract_short_name());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						String milestone = "";
						if(!StringUtils.isEmpty(obj.getMilestone_id()) && !StringUtils.isEmpty(obj.getMilestone_name())) {
							milestone = obj.getMilestone_id() +"_"+ obj.getMilestone_name();
						} else {
							milestone = obj.getMilestone_name();
						}
						cell.setCellValue(milestone);
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_type());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_description());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getStatus_fk());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getMilestone_payment());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_document_name());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getOriginal_due_date());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getRevised_due_date());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getSubmission_date());	
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getApproval_date());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getPayment());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getRemarks());
		                rowNo++;
		        	}
	            }
		         
		        /*************************************************************/ 
		         
		        List<Deliverables> statusList = deliverablesService.getStatusList();
				
					
		        XSSFRow headerRow = refSheet.createRow(0);
	            refSheet.createFreezePane(0,1);
	            
	            int b = 1;	
	            Cell cell = headerRow.createCell(b);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue("Status");
				int rowNoRef = 1;			
				XSSFRow row = null;
				for (Deliverables obj : statusList) {
	                row = refSheet.createRow(rowNoRef++);	                	                
	                cell = row.createCell(b);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStatus_fk());
				}
				refSheet.setColumnWidth(b, 25 * 200);
				
				
				/*************************************************************/
				
				
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		dataSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	            Date date = new Date();
	            String fileName = "Deliverables_"+dateFormat.format(date);
	            
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
	
	@RequestMapping(value="/deliverables-template",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  deliverablesTemplate(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.downloadDeliverablesTemplate);
		try {
			List<Deliverables> worksList = deliverablesService.getWorkListForDeliverablesForm(null);
			model.addObject("worksList", worksList);
			
			List<Deliverables> contractsList = deliverablesService.getContractsListForDeliverablesForm(null);
			model.addObject("contractsList", contractsList);
			
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(null);
			model.addObject("projectsList", projectsList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("deliverables : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/download-deliverables-template",method={RequestMethod.GET,RequestMethod.POST})
	public void downloadDeliverablesTemplateTemplate(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@ModelAttribute Deliverables dObj,RedirectAttributes attributes){
		try {
			XSSFWorkbook  workBook = new XSSFWorkbook ();
            XSSFSheet dataSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Deliverables"));
			XSSFSheet refSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Reference Data"));
			
	        workBook.setSheetOrder(dataSheet.getSheetName(), 0);
			workBook.setSheetOrder(refSheet.getSheetName(), 1);
		
	        
	        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
	        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        
	        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
	        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
	        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        
	        
            XSSFRow headingRow = dataSheet.createRow(0);
            String headerString = "Project^Work^Contract^Milestones^Deliverable type^Deliverable Description^Status^Milestone Payment^Document Name^Original Due Date^Revised Due Date^Submission Date^Approval Date^Payment %^Remarks";
            
            String[] firstHeaderStringArr = headerString.split("\\^");
            dataSheet.createFreezePane(0,1);
            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
	        	Cell cell = headingRow.createCell(i);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue(firstHeaderStringArr[i]);
			}
            List<Deliverables> dataList =  deliverablesService.getDeliverablesConractMilestonesList(dObj);
			int rowNo = 1;
	        for (Deliverables obj : dataList) {
                XSSFRow row = dataSheet.createRow(rowNo);
                int c = 0;		       
                
                Cell cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getProject_name());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getWork_short_name());
				
                cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getContract_short_name());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				String milestone = "";
				if(!StringUtils.isEmpty(obj.getMilestone_id()) && !StringUtils.isEmpty(obj.getMilestone_name())) {
					milestone = obj.getMilestone_id() +"_"+ obj.getMilestone_name();
				} else {
					milestone = obj.getMilestone_name();
				}
				cell.setCellValue(milestone);
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getDeliverable_type());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getDeliverable_description());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getStatus_fk());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getMilestone_payment());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getDeliverable_document_name());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getOriginal_due_date());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getRevised_due_date());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getSubmission_date());	
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getApproval_date());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getPayment());
				
				cell = row.createCell(c++);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getRemarks());
                rowNo++;
            }
	         
	        /*************************************************************/ 
	         
	        List<Deliverables> statusList = deliverablesService.getStatusList();
			
				
	        XSSFRow headerRow = refSheet.createRow(0);
            refSheet.createFreezePane(0,1);
            
            int b = 1;	
            Cell cell = headerRow.createCell(b);
	        cell.setCellStyle(greenStyle);
			cell.setCellValue("Status");
			int rowNoRef = 1;			
			XSSFRow row = null;
			for (Deliverables obj : statusList) {
                row = refSheet.createRow(rowNoRef++);	                	                
                cell = row.createCell(b);
				cell.setCellStyle(sectionStyle);
				cell.setCellValue(obj.getStatus_fk());
			}
			refSheet.setColumnWidth(b, 25 * 200);
			
			
			/*************************************************************/
			
			
        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
        		dataSheet.setColumnWidth(columnIndex, 25 * 200);
			}
        	
        	String fileName = "Deliverables_Template";
            try{
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
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("deliverables : " + e.getMessage());
		}
	}
	
	@RequestMapping(value="/deliverables-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView  deliverablesReport(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.deliverablesReport);
		try {
			
			List<Deliverables> worksList = deliverablesService.getWorkListForDeliverablesForm(null);
			model.addObject("worksList", worksList);
			
			List<Deliverables> contractsList = deliverablesService.getContractsListForDeliverablesForm(null);
			model.addObject("contractsList", contractsList);
			
			List<Deliverables> projectsList = deliverablesService.getProjectsListForDeliverablesForm(null);
			model.addObject("projectsList", projectsList);
			
			List<Deliverables> statusList = deliverablesService.getStatusList();
			model.addObject("statusList", statusList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("deliverablesReport : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/generate-deliverables-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateDeliverablesReport(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Deliverables dObj,RedirectAttributes attributes){
		List<Deliverables> dataList = new ArrayList<Deliverables>();
		try {
			dataList =  deliverablesService.getDeliverablesList(dObj);
			if(dataList != null && dataList.size() > 0){
				XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet summarySheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Summary Report"));
				XSSFSheet detailedSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Detailed Report"));
				
		        workBook.setSheetOrder(summarySheet.getSheetName(), 0);
				workBook.setSheetOrder(detailedSheet.getSheetName(), 1);
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
		        
	            XSSFRow headingRow = summarySheet.createRow(0);
	            String headerString = "Project^Work^Contract^Consultant Name^Milestones^Milestone Payment^Status^Due Date^Approval Date";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            summarySheet.createFreezePane(0,1);
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				int rowNo = 1;
		        for (Deliverables obj : dataList) {
	                XSSFRow row = summarySheet.createRow(rowNo);
	                int c = 0;		               
	                Cell cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProject_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getWork_short_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContract_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getContractor_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getMilestone_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getMilestone_payment());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStatus_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDue_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getApproval_date());
					
	                rowNo++;
		        }
		        /*************************************************************/ 
					
		        headingRow = detailedSheet.createRow(0);
	            headerString = "Deliverable Id^Project^Work^Contract^Milestones^Deliverable type^Deliverable Description^Status^Milestone Payment^Document Name^Original Due Date^Revised Due Date^Submission Date^Approval Date^Payment %^Remarks";
	            
	            firstHeaderStringArr = headerString.split("\\^");
	            detailedSheet.createFreezePane(0,1);
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				rowNo = 1;
		        for (Deliverables obj : dataList) {
		        	if(!StringUtils.isEmpty(obj.getDeliverableDocuments()) && obj.getDeliverableDocuments().size() > 0) {
			        	for (Deliverables docObj : obj.getDeliverableDocuments()) {
			                XSSFRow row = detailedSheet.createRow(rowNo);
			                int c = 0;	
			                Cell cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getDeliverable_document_id());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getProject_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getWork_short_name());
							
			                cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getContract_short_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							String milestone = "";
							if(!StringUtils.isEmpty(obj.getMilestone_id()) && !StringUtils.isEmpty(obj.getMilestone_name())) {
								milestone = obj.getMilestone_id() +"_"+ obj.getMilestone_name();
							} else {
								milestone = obj.getMilestone_name();
							}
							cell.setCellValue(milestone);
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDeliverable_type());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getDeliverable_description());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getStatus_fk());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(obj.getMilestone_payment());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getDeliverable_document_name());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getOriginal_due_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getRevised_due_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getSubmission_date());	
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getApproval_date());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getPayment());
							
							cell = row.createCell(c++);
							cell.setCellStyle(sectionStyle);
							cell.setCellValue(docObj.getRemarks());
			                rowNo++;
			        	}
		        	}else {
		        		XSSFRow row = detailedSheet.createRow(rowNo);
		                int c = 0;		
		                Cell cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_document_id());
						
		                cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getProject_name());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getWork_short_name());
						
		                cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getContract_short_name());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						String milestone = "";
						if(!StringUtils.isEmpty(obj.getMilestone_id()) && !StringUtils.isEmpty(obj.getMilestone_name())) {
							milestone = obj.getMilestone_id() +"_"+ obj.getMilestone_name();
						} else {
							milestone = obj.getMilestone_name();
						}
						cell.setCellValue(milestone);
						
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_type());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_description());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getStatus_fk());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getMilestone_payment());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getDeliverable_document_name());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getOriginal_due_date());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getRevised_due_date());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getSubmission_date());	
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getApproval_date());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getPayment());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(obj.getRemarks());
		                rowNo++;
		        	}
	            }
				
				
				/*************************************************************/
				
				
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		summarySheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		detailedSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	            Date date = new Date();
	            String fileName = "Deliverables_Report_"+dateFormat.format(date);
	            
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
			logger.error(e);
		}
	}
	
	/**************************************************************************************/
	
	@RequestMapping(value = "/upload-deliverables", method = {RequestMethod.POST})
	public ModelAndView uploadDeliverables(@ModelAttribute Deliverables obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";String userId = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setUser_id(userId);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			model.setViewName("redirect:/deliverables");
			
			if(!StringUtils.isEmpty(obj.getUploadFile())){
				MultipartFile multipartFile = obj.getUploadFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet laSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = laSheet.getRow(0);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getDeliverablesFileFormat();	
							int columnSize = fileFormat.size();
							int noOfColumns = headerRow.getLastCellNum();
							String columnName = headerRow.getCell(0).getStringCellValue().trim();
							if(!columnName.equalsIgnoreCase(fileFormat.get(0).trim()) &&  columnName.equals("Project")) {
								columnSize = columnSize - 1;
							}
							if(noOfColumns == columnSize){
								boolean tempFlag = false;
								for (int i = 0; i < columnSize;i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(i == 0 && "Deliverable Id".equalsIgnoreCase(fileFormat.get(i).trim()) && columnName.equals("Project") && !columnName.equals(fileFormat.get(i).trim())) {
										tempFlag = true;
									}
									if(tempFlag) {i++;}
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		msg = uploadformatError;
				                		obj.setUploaded_by_user_id_fk(userId);
				                		obj.setStatus("Fail");
				                		obj.setRemarks(msg);
										//boolean flag = deliverablesService.saveUploadedFile(obj);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
								msg = uploadformatError;
		                		obj.setUploaded_by_user_id_fk(userId);
		                		obj.setStatus("Fail");
		                		obj.setRemarks(msg);
								//boolean flag = deliverablesService.saveUploadedFile(obj);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
							msg = uploadformatError;
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
							//boolean flag = deliverablesService.saveUploadedFile(obj);
	                		return model;
						}
						String[]  result = uploadDeliverables(obj,userId,userName);
						String errMsg = result[0];String actualVal = "";
						int count = 0,row = 0,sheet = 0,subRow = 0;
						List<String> fileFormat = FileFormatModel.getDeliverablesFileFormat();	
						if(!StringUtils.isEmpty(result[1])){count = Integer.parseInt(result[1]);}
						if(!StringUtils.isEmpty(result[2])){row = Integer.parseInt(result[2]);}
						if(!StringUtils.isEmpty(result[3])){sheet = Integer.parseInt(result[3]);}
						if(!StringUtils.isEmpty(result[4])){subRow = Integer.parseInt(result[4]);}
						if(!StringUtils.isEmpty(errMsg)){
							if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Duplicate entry")) {
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;<b>Work and Deliverables Id Mismatch at row: ("+row+")</b> please check and Upload again.</span>");
								msg = "Work and Deliverables Id Mismatch at row: "+row;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Data truncated")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row; 
									String error = "Data truncated";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								} 
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect Value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Cannot add or update a child row")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Cannot add or update a child row";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect Value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect date value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Incorrect date value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect date value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect date value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect integer value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row; 
									String error = "Incorrect integer value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect integer value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect integer value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Incorrect decimal value")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Incorrect decimal value";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Incorrect decimal value identified in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect decimal value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Data too long for column")) {
								actualVal = Integer.toString(subRow);
								if(sheet == 1) {subRow = row;
									String error = "Data too long for column";
									actualVal = FileFormatModel.getActualValue(error,errMsg,subRow,fileFormat);
								}
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;Data too long for value in <b>Sheet: ["+sheet+"]</b> at <b>row: ["+actualVal+"]</b> please check and Upload again.</span>");
								msg = "Incorrect decimal value identified in Sheet: "+sheet+" at row: "+actualVal;
							}else if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Invalid Rows")) {
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;"+errMsg+"</span>");
								msg = errMsg;
							}else {
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;"+errMsg+"</span>");
								msg = errMsg;
							}
						
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
							//boolean flag = deliverablesService.saveUploadedFile(obj);
	                		return model;
						}
						
						if(count > 0) {
							attributes.addFlashAttribute("success","<i class='fa fa-check'></i>&nbsp;"+ count + "<span style='color:green;'> records Uploaded successfully.</span>");	
							msg = count + " records Uploaded successfully.";
							
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
							formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
							formHistory.setModule_name_fk("Deliverables");
							formHistory.setForm_name("Upload Deliverables");
							formHistory.setForm_action_type("Upload");
							formHistory.setForm_details( msg);
							formHistory.setWork(obj.getWork_id_fk());
							//formHistory.setContract(obj.getContract_id_fk());
							
							boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
							/********************************************************************************/
						}else {
							attributes.addFlashAttribute("success"," No records found.");	
							msg = " No records found.";
						}
                		obj.setUploaded_by_user_id_fk(userId);
                		obj.setStatus("Success");
                		obj.setRemarks(msg);
						//boolean flag = deliverablesService.saveUploadedFile(obj);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				msg = "No file exists";
				obj.setUploaded_by_user_id_fk(userId);
        		obj.setStatus("Fail");
        		obj.setRemarks(msg);
				//boolean flag = deliverablesService.saveUploadedFile(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("saveDeliverableDataUploadFile() : "+e.getMessage());
			msg = "Something went wrong. Please try after some time";
			obj.setUploaded_by_user_id_fk(userId);
    		obj.setStatus("Fail");
    		obj.setRemarks(msg);
			try {
				//boolean flag = deliverablesService.saveUploadedFile(obj);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveDeliverableDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	
	private  String[]  uploadDeliverables(Deliverables obj, String userId, String userName) throws Exception {
		Deliverables deliverableObj = null;
		
		List<Deliverables> deliverablesList = new ArrayList<Deliverables>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		String invalidRows = "";
		try {	
			MultipartFile excelfile = obj.getUploadFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {					
					XSSFSheet laSheet = workbook.getSheetAt(0);
					
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 1; i <= laSheet.getLastRowNum();i++){
						int v = laSheet.getLastRowNum();
						XSSFRow headerRow = laSheet.getRow(0);
						String columnName = headerRow.getCell(0).getStringCellValue();
						XSSFRow row = laSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						deliverableObj = new Deliverables();
						String val = null;
						if(!StringUtils.isEmpty(row)) {		
							int p = 0;
							if("Deliverable Id".equalsIgnoreCase(columnName.trim())) {
								val = formatter.formatCellValue(row.getCell(p++)).trim();
								if(!StringUtils.isEmpty(val)) { deliverableObj.setDeliverable_document_id(val);}
							}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setProject_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setWork_short_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setContract_short_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setMilestone_name(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setDeliverable_type(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setDeliverable_description(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setStatus_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(p-1));
								}
								deliverableObj.setMilestone_payment(val);
							}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setDeliverable_document_name(val);}
							
							val = getCellTypeDateValue(row.getCell(p++));
							if(!StringUtils.isEmpty(val)) { deliverableObj.setOriginal_due_date(val);}
							
							val = getCellTypeDateValue(row.getCell(p++));
							if(!StringUtils.isEmpty(val)) { deliverableObj.setRevised_due_date(val);}							
							
							val = getCellTypeDateValue(row.getCell(p++));
							if(!StringUtils.isEmpty(val)) { deliverableObj.setSubmission_date(val);}							
							
							val = getCellTypeDateValue(row.getCell(p++));
							if(!StringUtils.isEmpty(val)) { deliverableObj.setApproval_date(val);}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(p-1));
								}
								deliverableObj.setPayment(val);
							}
							
							val = formatter.formatCellValue(row.getCell(p++)).trim();
							if(!StringUtils.isEmpty(val)) { deliverableObj.setRemarks(val);}							
						
							deliverableObj.setOriginal_due_date(DateParser.parse(deliverableObj.getOriginal_due_date()));
							deliverableObj.setRevised_due_date(DateParser.parse(deliverableObj.getRevised_due_date()));
							deliverableObj.setSubmission_date(DateParser.parse(deliverableObj.getSubmission_date()));
							deliverableObj.setApproval_date(DateParser.parse(deliverableObj.getApproval_date()));
							
							deliverableObj.setCreated_by_user_id_fk(userId);
							deliverableObj.setUser_name(userName);
				
						if(!StringUtils.isEmpty(deliverableObj.getWork_short_name()) 
								 	&& !StringUtils.isEmpty(deliverableObj.getContract_short_name())
								 		&& !StringUtils.isEmpty(deliverableObj.getMilestone_name())
								 			&& !StringUtils.isEmpty(deliverableObj.getDeliverable_type())
								 				&& !StringUtils.isEmpty(deliverableObj.getMilestone_payment())
								 					&& !StringUtils.isEmpty(deliverableObj.getDeliverable_document_name())
								 						) {
							deliverablesList.add(deliverableObj);
						}else {						
							invalidRows = invalidRows + (i+1) +",";
						}
					}
				}
				if(!deliverablesList.isEmpty() && deliverablesList != null && StringUtils.isEmpty(invalidRows)){
					String[] arr  = deliverablesService.uploadDeliverablesData(deliverablesList,deliverableObj);
					result[0] = arr[0];
					result[1] = arr[1];
					result[2] = arr[2];
					result[3] = arr[3];
					result[4] = arr[4];
				}
				if(!StringUtils.isEmpty(invalidRows)){					
					String invalidRowsList = invalidRows.substring(0, invalidRows.length() - 1);
					result[0] = "Invalid Rows :"+ invalidRowsList + " <br>Required fields: Work, Execution Agency, HOD, Utility Type, Utility Description, Impacted Contract,Requirement stage ";
				}
				workbook.close();
			}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadDeliverables() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRDeliverables() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return result;
	}
	
	private String getCellTypeDateValue(XSSFCell cell) {
		String val = null;
		try {
			if (!StringUtils.isEmpty(cell) && !StringUtils.isEmpty(cell.getRawValue())) {
				CellType type = cell.getCellType();
			    switch (type) {
			        case BOOLEAN:
			            val = String.valueOf(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			        	if (DateUtil.isCellDateFormatted(cell)) {
			        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			        		Date date = cell.getDateCellValue();
			        		val = df.format(date);
	                    } else {
	                    	val = String.valueOf(cell.getNumericCellValue());
				        	if(val.contains("E")){
				        		val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
				        	}
	                    }
			            break;
			        case STRING:
			        	val = cell.getStringCellValue();
			            break;
			        case BLANK:
			        	val = cell.getStringCellValue();
			            break;
			        case ERROR:
			            val = cell.getStringCellValue();
			            break;
			        case _NONE:
			            val = cell.getStringCellValue();
			            break;
					default:
						break;
			    }
			}
		}catch(Exception e) {
			val = null;
		}
	
		return val;
	}
	
	private String getCellDataType(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup
		//workbook.setForceFormulaRecalculation(true);
		try {
			if (!StringUtils.isEmpty(cell) && !StringUtils.isEmpty(cell.getRawValue())) {
				CellType type = cell.getCellType();
			    switch (type) {
			        case BOOLEAN:
			            val = String.valueOf(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			        	if (DateUtil.isCellDateFormatted(cell)) {
			        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			        		Date date = cell.getDateCellValue();
			        		val = df.format(date);
	                    } else {
	                    	val = String.valueOf(cell.getNumericCellValue());
				        	if(val.contains("E")){
				        		val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
				        	}
	                    }
			        	
			       
			            break;
			        case STRING:
			        	try {  
			        		val = cell.getStringCellValue();
			        		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			        		Number number = format.parse(val);
			        		int d = number.intValue();
			        		val = String.valueOf(d);
			        		if(val.contains("E")){
			        			val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
			        		}
			        	  } catch(NumberFormatException e){  
			        		  val = cell.getStringCellValue();
			        	  }  
			            
			            break;
			        case BLANK:
			        	val = cell.getStringCellValue();
			            break;
			        case ERROR:
			            val = cell.getStringCellValue();
			            break;
			        case _NONE:
			            val = cell.getStringCellValue();
			            break;
					default:
						break;
			    }
			}else if (!StringUtils.isEmpty(cell)) {
				DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
				val = formatter.formatCellValue(cell).trim();
			}
		}catch(Exception e) {
			try {
				 val = cell.getStringCellValue();
			}catch(Exception e1) {
				val = String.valueOf(cell.getNumericCellValue());
			}
			
		}
	
		return val;
	}
	
}
