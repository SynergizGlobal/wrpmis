package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.RandRMainService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.RRPaginationObject;
import com.synergizglobal.pmis.model.RandRMain;

@Controller
public class RandRMainController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RandRMainController.class);
	

	@Autowired
	RandRMainService service;
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
	@RequestMapping(value="/randr-main",method={RequestMethod.GET})
	public ModelAndView RandRMain(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.RandRMain);
		try {
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("RandRMain : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getWorksFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getWorksList(@ModelAttribute RandRMain obj) {
		List<RandRMain> worksList = null;
		try {
			worksList = service.getWorksFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getPhasesFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getPhasesFilterListInRR(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getPhasesFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPhasesFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStructuresFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getStructuresFilterListInRR(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getStructuresFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructuresFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getTypeofUseFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getTypeofUseFilterListInRR(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getTypeofUseFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTypeofUseFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getLocationsFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getLocationsFilterListInRR(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getLocationsFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLocationsFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getStatusFilterListInRR(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getStatusFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/get-rAndr", method = { RequestMethod.POST, RequestMethod.GET })
	public void getRRList(@ModelAttribute RandRMain obj, HttpServletRequest request,
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

			List<RandRMain> budgetList = new ArrayList<RandRMain>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//budgetList = getListBasedOnSearchParameter(searchParameter,budgetList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			RRPaginationObject personJsonObject = new RRPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(budgetList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getBudgetsList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(RandRMain obj, String searchParameter) {
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
	public List<RandRMain> createPaginationData(int startIndex, int offset, RandRMain obj, String searchParameter) {
		List<RandRMain> objList = null;
		try {
			objList = service.getRRList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getRRIdListForRRForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getRRIdListForRRForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getRRIdListForRRForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getLocationListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getLocationListForRRForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getLocationListForRRForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLocationListForRRForm : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getSubLocationListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getSubLocationListForRRForm(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getSubLocationListForRRForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubLocationListForRRForm : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/add-randr-main", method = {RequestMethod.GET})
	public ModelAndView addRRForm(@ModelAttribute RandRMain obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRMain);
			model.addObject("action", "add");
			List<RandRMain> projectsList = service.getProjectsListForRRForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<RandRMain> worksList = service.getWorkListForRRForm(obj);
			model.addObject("worksList", worksList);
			
			List<RandRMain> rrId = service.getRRIdListForRRForm(obj);
			model.addObject("rrId", rrId);
			
			List<RandRMain> docType = service.getDocTypeListForRRForm(obj);
			model.addObject("docType", docType);
			
			List<RandRMain> phase = service.getPhaseListForRRForm(obj);
			model.addObject("phase", phase);
			
			List<RandRMain> structure = service.getStructureListForRRForm(obj);
			model.addObject("structure", structure);
			
			List<RandRMain> location = service.getLocationListForRRForm(obj);
			model.addObject("location", location);
			
			List<RandRMain> subLocation = service.getSubLocationListForRRForm(obj);
			model.addObject("subLocation", subLocation);
			
			List<RandRMain> typeofUse = service.getTypeofUseListForRRForm(obj);
			model.addObject("typeofUse", typeofUse);
			
			List<RandRMain> verificationBy = service.getVerificationByListForRRForm(obj);
			model.addObject("verificationBy", verificationBy);
			
			List<RandRMain> units = service.getUnitsListForRRForm(obj);
			model.addObject("units", units);
			
			List<RandRMain> status = service.getStatusListForRRForm(obj);
			model.addObject("status", status);
			
			List<RandRMain> occupancyStatus = service.getOccupancyStatusListForRRForm(obj);
			model.addObject("occupancyStatus", occupancyStatus);
			
			List<RandRMain> gender = service.getGenderListForRRForm(obj);
			model.addObject("gender", gender);
			
			List<RandRMain> tenureStatus = service.getTenureStatusListForRRForm(obj);
			model.addObject("tenureStatus", tenureStatus);
			
			List<RandRMain> caste = service.getCasteListForRRForm(obj);
			model.addObject("caste", caste);
			
			List<RandRMain> motherTongue = service.getMotherTongueListForRRForm(obj);
			model.addObject("motherTongue", motherTongue);
			
			List<RandRMain> typeofFamily = service.getTypeofFamilyListForRRForm(obj);
			model.addObject("typeofFamily", typeofFamily);
			
			List<RandRMain> maritualStatus = service.getMaritualStatusListForRRForm(obj);
			model.addObject("maritualStatus", maritualStatus);
		
		}catch (Exception e) {
			e.printStackTrace();
				logger.error("addBudgetForm : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/get-rr", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRandRMainForm(@ModelAttribute RandRMain rr ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRMain);
			model.addObject("action", "edit");
			
			List<RandRMain> docType = service.getDocTypeListForRRForm(rr);
			model.addObject("docType", docType);
			
			List<RandRMain> phase = service.getPhaseListForRRForm(rr);
			model.addObject("phase", phase);
			
			List<RandRMain> structure = service.getStructureListForRRForm(rr);
			model.addObject("structure", structure);
			
			List<RandRMain> location = service.getLocationListForRRForm(rr);
			model.addObject("location", location);
			
			List<RandRMain> subLocation = service.getSubLocationListForRRForm(rr);
			model.addObject("subLocation", subLocation);
			
			List<RandRMain> typeofUse = service.getTypeofUseListForRRForm(rr);
			model.addObject("typeofUse", typeofUse);
			
			List<RandRMain> verificationBy = service.getVerificationByListForRRForm(rr);
			model.addObject("verificationBy", verificationBy);
			
			List<RandRMain> units = service.getUnitsListForRRForm(rr);
			model.addObject("units", units);
			
			List<RandRMain> status = service.getStatusListForRRForm(rr);
			model.addObject("status", status);
			
			List<RandRMain> occupancyStatus = service.getOccupancyStatusListForRRForm(rr);
			model.addObject("occupancyStatus", occupancyStatus);
			
			List<RandRMain> gender = service.getGenderListForRRForm(rr);
			model.addObject("gender", gender);
			
			List<RandRMain> tenureStatus = service.getTenureStatusListForRRForm(rr);
			model.addObject("tenureStatus", tenureStatus);
			
			List<RandRMain> caste = service.getCasteListForRRForm(rr);
			model.addObject("caste", caste);
			
			List<RandRMain> motherTongue = service.getMotherTongueListForRRForm(rr);
			model.addObject("motherTongue", motherTongue);
			
			List<RandRMain> typeofFamily = service.getTypeofFamilyListForRRForm(rr);
			model.addObject("typeofFamily", typeofFamily);
			
			List<RandRMain> maritualStatus = service.getMaritualStatusListForRRForm(rr);
			model.addObject("maritualStatus", maritualStatus);
			
			RandRMain rrDetails = service.getRandRMainForm(rr);
			model.addObject("rrDetails", rrDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getRandRMainForm : " + e.getMessage());
		}
		return model;
	 }
	@RequestMapping(value = "/get-rr/{rr_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRandRMainFormForId(@ModelAttribute RandRMain rr,@PathVariable("rr_id") String rr_id  ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRMain);
			model.addObject("action", "edit");
			
			List<RandRMain> docType = service.getDocTypeListForRRForm(rr);
			model.addObject("docType", docType);
			
			List<RandRMain> phase = service.getPhaseListForRRForm(rr);
			model.addObject("phase", phase);
			
			List<RandRMain> structure = service.getStructureListForRRForm(rr);
			model.addObject("structure", structure);
			
			List<RandRMain> location = service.getLocationListForRRForm(rr);
			model.addObject("location", location);
			
			List<RandRMain> subLocation = service.getSubLocationListForRRForm(rr);
			model.addObject("subLocation", subLocation);
			
			List<RandRMain> typeofUse = service.getTypeofUseListForRRForm(rr);
			model.addObject("typeofUse", typeofUse);
			
			List<RandRMain> verificationBy = service.getVerificationByListForRRForm(rr);
			model.addObject("verificationBy", verificationBy);
			
			List<RandRMain> units = service.getUnitsListForRRForm(rr);
			model.addObject("units", units);
			
			List<RandRMain> status = service.getStatusListForRRForm(rr);
			model.addObject("status", status);
			
			List<RandRMain> occupancyStatus = service.getOccupancyStatusListForRRForm(rr);
			model.addObject("occupancyStatus", occupancyStatus);
			
			List<RandRMain> gender = service.getGenderListForRRForm(rr);
			model.addObject("gender", gender);
			
			List<RandRMain> tenureStatus = service.getTenureStatusListForRRForm(rr);
			model.addObject("tenureStatus", tenureStatus);
			
			List<RandRMain> caste = service.getCasteListForRRForm(rr);
			model.addObject("caste", caste);
			
			List<RandRMain> motherTongue = service.getMotherTongueListForRRForm(rr);
			model.addObject("motherTongue", motherTongue);
			
			List<RandRMain> typeofFamily = service.getTypeofFamilyListForRRForm(rr);
			model.addObject("typeofFamily", typeofFamily);
			
			List<RandRMain> maritualStatus = service.getMaritualStatusListForRRForm(rr);
			model.addObject("maritualStatus", maritualStatus);
			
			RandRMain rrDetails = service.getRandRMainForm(rr);
			model.addObject("rrDetails", rrDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getRandRMainFormForId : " + e.getMessage());
		}
		return model;
	 }
	@RequestMapping(value = "/add-rr", method = {RequestMethod.POST})
	public ModelAndView addRR(@ModelAttribute RandRMain obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/randr-main");
			obj.setPhysical_verification(DateParser.parse(obj.getPhysical_verification()));
			obj.setApproval_by_committee(DateParser.parse(obj.getApproval_by_committee()));
			obj.setRr_approval_status_by_mrvc(DateParser.parse(obj.getRr_approval_status_by_mrvc()));
			obj.setEstimate_approval_date(DateParser.parse(obj.getEstimate_approval_date()));
			obj.setLetter_to_mmrda(DateParser.parse(obj.getLetter_to_mmrda()));
			obj.setPayment_to_mmrda(DateParser.parse(obj.getPayment_to_mmrda()));
			obj.setAlternate_housing_allotment(DateParser.parse(obj.getAlternate_housing_allotment()));
			obj.setEncroachment_removal(DateParser.parse(obj.getEncroachment_removal()));
			obj.setBoundary_wall_doc(DateParser.parse(obj.getBoundary_wall_doc()));
			obj.setHanded_over_to_execution(DateParser.parse(obj.getHanded_over_to_execution()));
			obj.setYear_of_construction(DateParser.parse(obj.getYear_of_construction()));
			obj.setRelocation(DateParser.parse(obj.getRelocation()));
			boolean flag =  service.addRR(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "R and R Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding R and R is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding R and R is failed. Try again.");
			logger.error("addRR : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-rr", method = {RequestMethod.POST})
	public ModelAndView updateRR(@ModelAttribute RandRMain obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/randr-main");
			obj.setPhysical_verification(DateParser.parse(obj.getPhysical_verification()));
			obj.setApproval_by_committee(DateParser.parse(obj.getApproval_by_committee()));
			obj.setRr_approval_status_by_mrvc(DateParser.parse(obj.getRr_approval_status_by_mrvc()));
			obj.setEstimate_approval_date(DateParser.parse(obj.getEstimate_approval_date()));
			obj.setLetter_to_mmrda(DateParser.parse(obj.getLetter_to_mmrda()));
			obj.setPayment_to_mmrda(DateParser.parse(obj.getPayment_to_mmrda()));
			obj.setAlternate_housing_allotment(DateParser.parse(obj.getAlternate_housing_allotment()));
			obj.setEncroachment_removal(DateParser.parse(obj.getEncroachment_removal()));
			obj.setBoundary_wall_doc(DateParser.parse(obj.getBoundary_wall_doc()));
			obj.setHanded_over_to_execution(DateParser.parse(obj.getHanded_over_to_execution()));
			obj.setYear_of_construction(DateParser.parse(obj.getYear_of_construction()));
			obj.setRelocation(DateParser.parse(obj.getRelocation()));
			String user_Id = (String) session.getAttribute("USER_ID");
		
			obj.setCreated_by_user_id_fk(user_Id);
			boolean flag =  service.updateRR(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "R and R Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating R and R is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating R and R is failed. Try again.");
			logger.error("updateRR : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/export-randr-main", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportRandRMain(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute RandRMain dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.RandRMain);
		List<RandRMain> dataList = new ArrayList<RandRMain>();
		
		List<RandRMain> commercialLists = new ArrayList<RandRMain>();
		List<RandRMain> comDetailsList = new ArrayList<RandRMain>();
		
		List<RandRMain> residentialList = new ArrayList<RandRMain>();
		List<RandRMain> rDetailsList = new ArrayList<RandRMain>();
		try {
			view.setViewName("redirect:/randr-main");
			dataList =   service.getRandRMainList(dObj);
		   
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet RRSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("R&R Main"));
				XSSFSheet commercialSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Commercial Details"));
				XSSFSheet comDetailsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Commercial Family Details"));
				XSSFSheet residentialSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Residential Details"));
				XSSFSheet rDetailsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Residential Family Details"));
				
		        workBook.setSheetOrder(RRSheet.getSheetName(), 0);
				workBook.setSheetOrder(commercialSheet.getSheetName(), 1);
				workBook.setSheetOrder(comDetailsSheet.getSheetName(), 2);
				workBook.setSheetOrder(residentialSheet.getSheetName(), 3);
				workBook.setSheetOrder(rDetailsSheet.getSheetName(), 4);
		        
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
		        
		        
		        
	            XSSFRow headingRow = RRSheet.createRow(0);
	            String headerString = "Project ^Work ^R&R Id^Id No^Map S.No^Phase^Structure^Location^Sub Location^Type of Use^Carpet Area (sft)"
	            		+ "^Year of Construction^Owner Name^Occupier Name^Document Type^Document No^Physical Verification Date^Verification By^Approval By Committee"
	            		+ "^Approval by MRVC^Estimate Approval^"
	            		+ "Estimation Amount^Letter to MMRDA^Estimate by MMRDA^Payment to MMRDA^Alternate Housing Allotment^Relocation^Encroachment Removal^Boundary Wall Status"
	            		+ "^Boundary Wall Doc^Handover to Execution^Remarks";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				XSSFRow headingRow1 = commercialSheet.createRow(0);
	            String headerString1 = "R&R Id^Name of Activity^Year of Establishment^Carpet Area (in sqft)^Monthly Turnover^"
	            		+ "Number of Employees^Remarks";
	            
	            String[] secondHeaderStringArr = headerString1.split("\\^");
	            
	            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow1.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(secondHeaderStringArr[i]);
				}
				
				XSSFRow headingRow2 = comDetailsSheet.createRow(0);
	            String headerString2 = "R&R Id^Name^Age^Gender^Literate^Class Attended^"
	            		+ "Travel Time From Residence (In Minutes)^Monthly Salary ^Nature Of Work";
	            
	            String[] thirdHeaderStringArr = headerString2.split("\\^");
	            
	            for (int i = 0; i < thirdHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow2.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(thirdHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow3 = residentialSheet.createRow(0);
	            String headerString3 = "R&R Id^Occupancy Status^Gender^Tenure Status^Vulnerable Category^Caste^Mother Tongue^Type of Family^"
	            		+ "Family Size^No of Married Couple^Family Income";
	            
	            String[] fourthHeaderStringArr = headerString3.split("\\^");
	            
	            for (int i = 0; i < fourthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow3.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fourthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow4 = rDetailsSheet.createRow(0);
	            String headerString4 = "R&R Id^Name^Relation With Head^Age^Gender^Maritual Status^Education^"
	            		+ "Employment^Monthly Salary ";
	            
	            String[] fifthHeaderStringArr = headerString4.split("\\^");
	            
	            for (int i = 0; i < fifthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow4.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fifthHeaderStringArr[i]);
				}
	  
	            
			short rowNo3 = 1;
        	for (RandRMain commercialList : dataList) { 
        		String rr_id = commercialList.getRr_id();
        		commercialLists = service.gecommercialList(rr_id);
					/*	if(commercialLists.size()< 1) {
							 int a = 0;
							XSSFRow row = commercialSheet.createRow(rowNo3);
							for(int k = 0;k < secondHeaderStringArr.length;k++) {
								Cell cell2 = row.createCell(a++);
								cell2.setCellStyle(whiteStyle);
								cell2.setCellValue("No Data");
							}
							commercialSheet.addMergedRegion(new CellRangeAddress(1,1,0,(secondHeaderStringArr.length - 1 )));
							break;
						}*/
				
				 for (RandRMain obj : commercialLists) {
	                XSSFRow row = commercialSheet.createRow(rowNo3);
	                int a = 0;
	                
	                Cell cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getRr_id_fk());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getName_of_activity());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getYear_of_establishment());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getCom_carpet_area());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					String amount = "";
					String unit = "";
					if(!StringUtils.isEmpty(obj.getMonthly_turnover_amount())) {
						amount = obj.getMonthly_turnover_amount();
						if(!StringUtils.isEmpty(obj.getMonthly_turnover_amount_units())) {
							unit = obj.getMonthly_turnover_amount_units() ;
							amount = amount+" "+unit;
						}
					}
					cell2.setCellValue(amount);					
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getNumber_of_employees());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getCom_remarks());
					
					
					rowNo3++;
				 }
			 }
			 
	         short rowNo2 = 1;
			 for (RandRMain comDetails : dataList) { 
				String rr_id = comDetails.getRr_id();
				comDetailsList = service.getComDetailsListList(rr_id);
					
				 for (RandRMain obj : comDetailsList) {
		                XSSFRow row = comDetailsSheet.createRow(rowNo2);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRr_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_name());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_age());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_gender());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_literacy());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_attended());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_travel_time());
						
						String amount = "";
						String unit = "";
						if(!StringUtils.isEmpty(obj.getEmployee_salary())) {
							amount = obj.getEmployee_salary();
							if(!StringUtils.isEmpty(obj.getEmployee_salary_units())) {
								unit = obj.getEmployee_salary_units() ;
								amount = amount+" "+unit;
							}
						}
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(amount);

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getEmployee_nature_of_work());
						
						rowNo2++;
				    }
		       }
			 
			 short rowNo4 = 1;
			 for (RandRMain residential : dataList) { 
				String rr_id = residential.getRr_id();
				residentialList = service.getResidentialList(rr_id);
					
				 for (RandRMain obj : residentialList) {
		                XSSFRow row = residentialSheet.createRow(rowNo4);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRr_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getOccupancy_status());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getGender());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getTenure_status());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getVulnerable_category());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getCaste());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getMother_tongue());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getType_of_family());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getFamily_size());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getNumber_of_married_couple());

						String amount = "";
						String unit = "";
						if(!StringUtils.isEmpty(obj.getFamily_income_amount())) {
							amount = obj.getFamily_income_amount();
							if(!StringUtils.isEmpty(obj.getFamily_income_amount_units())) {
								unit = obj.getFamily_income_amount_units() ;
								amount = amount+" "+unit;
							}
						}
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(amount);
						rowNo4++;
				    }
		       }
			 
			 short rowNo5 = 1;
			 for (RandRMain rDetails : dataList) { 
				String rr_id = rDetails.getRr_id();
				rDetailsList = service.getRDetailsList(rr_id);
					
				 for (RandRMain obj : rDetailsList) {
		                XSSFRow row = rDetailsSheet.createRow(rowNo5);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRr_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_name());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_relation_with_head());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_age());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_gender());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_maritual_status());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_education());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getResidential_employment());

						String amount = "";
						String unit = "";
						if(!StringUtils.isEmpty(obj.getResidential_salary())) {
							amount = obj.getResidential_salary();
							if(!StringUtils.isEmpty(obj.getResidential_salary_units())) {
								unit = obj.getResidential_salary_units() ;
								amount = amount+" "+unit;
							}
						}
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(amount);
						
						rowNo5++;
				    }
		       }
			 short rowNo = 1;
	         for (RandRMain obj : dataList) {
					
	                XSSFRow row = RRSheet.createRow(rowNo);
	                int c = 0;
	               
	                Cell cell = row.createCell(c++);
	                cell.setCellStyle(sectionStyle);
					cell.setCellValue(/*obj.getProject_id_fk() + " - "+*/obj.getProject_name());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(/*obj.getWork_id_fk() + " - "+*/obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRr_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIdentification_no());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getMap_sr_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPhase());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getStructure_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLocation_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSub_location_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getType_of_use());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCarpet_area());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getYear_of_construction());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getName_of_the_owner());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getOccupier_name_during_verification());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDocument_type());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDocument_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPhysical_verification());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getVerification_by());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getApproval_by_committee());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRr_approval_status_by_mrvc());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getEstimate_approval_date());
					
					String amount = "";
					String unit = "";
					if(!StringUtils.isEmpty(obj.getEstimation_amount())) {
						amount = obj.getEstimation_amount();
						if(!StringUtils.isEmpty(obj.getEstimation_amount_units())) {
							unit = obj.getEstimation_amount_units() ;
							amount = amount+" "+unit;
						}
					}
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);     
					cell.setCellValue(amount);
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLetter_to_mmrda());
					
					amount = "";
					unit = "";
					if(!StringUtils.isEmpty(obj.getEstimates_by_mmrda())) {
						amount = obj.getEstimates_by_mmrda();
						if(!StringUtils.isEmpty(obj.getEstimated_by_mmrda_amount_units())) {
							unit = obj.getEstimated_by_mmrda_amount_units() ;
							amount = amount+" "+unit;
						}
					}
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(amount);
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPayment_to_mmrda());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAlternate_housing_allotment());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRelocation());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getEncroachment_removal());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getBoundary_wall_status());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getBoundary_wall_doc());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getHanded_over_to_execution());
				
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
					
	                rowNo++;
	            }
				
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		RRSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 38; columnIndex++) {
	        		commercialSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex <16; columnIndex++) {
	        		comDetailsSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 21; columnIndex++) {
	        		residentialSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 21; columnIndex++) {
	        		rDetailsSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	
	        
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	            Date date = new Date();
	            String fileName = "RR_Main"+dateFormat.format(date);
	            
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
}
