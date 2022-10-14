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
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
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
import com.synergizglobal.pmis.Iservice.BudgetService;
import com.synergizglobal.pmis.Iservice.RandRMainService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.RandRMain;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RRPaginationObject;

@Controller
public class RandRMainController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RandRMainController.class);
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
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
	public List<RandRMain> getWorksList(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> worksList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksList = service.getWorksFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForRRForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getWorkListForRRForm(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> worksList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksList = service.getWorkListForRRForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}	
	@RequestMapping(value = "/ajax/getPhasesFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getPhasesFilterListInRR(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getPhasesFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPhasesFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStructuresFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getStructuresFilterListInRR(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getStructuresFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructuresFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getTypeofUseFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getTypeofUseFilterListInRR(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getTypeofUseFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTypeofUseFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getLocationsFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getLocationsFilterListInRR(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getLocationsFilterListInRR(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLocationsFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getStatusFilterListInRR(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
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
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			
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
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				budgetList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			}

			//Search functionality: Returns filtered list based on search parameter
			//budgetList = getListBasedOnSearchParameter(searchParameter,budgetList);

			int totalRecords = getTotalRecords(obj, searchParameter, session);

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
	public int getTotalRecords(RandRMain obj, String searchParameter,HttpSession session) {
		int totalRecords = 0;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
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
	public List<RandRMain> createPaginationData(int startIndex, int offset, RandRMain obj, String searchParameter,HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
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
	
	@RequestMapping(value = "/ajax/geStructureIdFilterListInRR", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> geStructureIdFilterListInRR(@ModelAttribute RandRMain obj) {
		List<RandRMain> objList = null;
		try {
			objList = service.getStructureListForRRForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("geStructureIdFilterListInRR : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/add-randr-main", method = {RequestMethod.GET})
	public ModelAndView addRRForm(HttpSession session,@ModelAttribute RandRMain obj){
		ModelAndView model = new ModelAndView();
		try{

			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
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
	public ModelAndView getRandRMainForm(@ModelAttribute RandRMain rr  ,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRMain);
			model.addObject("action", "edit");
			
			User uObj = (User) session.getAttribute("user");
			rr.setUser_type_fk(uObj.getUser_type_fk());
			rr.setUser_role_code(uObj.getUser_role_code());
			rr.setUser_id(uObj.getUser_id());
			
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
	public ModelAndView getRandRMainFormForId(@ModelAttribute RandRMain rr,@PathVariable("rr_id") String rr_id  ,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRMain);
			model.addObject("action", "edit");
			
			User uObj = (User) session.getAttribute("user");
			rr.setUser_type_fk(uObj.getUser_type_fk());
			rr.setUser_role_code(uObj.getUser_role_code());
			rr.setUser_id(uObj.getUser_id());
			
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
	public ModelAndView addRR(@ModelAttribute RandRMain obj,HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/randr-main");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
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
			
			obj.setPlanned_date_of_completion(DateParser.parse(obj.getPlanned_date_of_completion()));
			
			//obj.setYear_of_construction(DateParser.parse(obj.getYear_of_construction()));
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
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
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
			obj.setPlanned_date_of_completion(DateParser.parse(obj.getPlanned_date_of_completion()));
			
			//obj.setYear_of_construction(DateParser.parse(obj.getYear_of_construction()));
			obj.setRelocation(DateParser.parse(obj.getRelocation()));
			
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
	@RequestMapping(value = "/ajax/getRRUploadsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getRRUploadsList(@ModelAttribute RandRMain obj) {
		List<RandRMain> objsList = null;
		try {
			objsList = service.getRRUploadsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRRUploadsList : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getRRCoordinates", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getRRCoordinates(@ModelAttribute RandRMain obj,HttpSession session) {
		List<RandRMain> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objsList = service.getRRCoordinates(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getCoordinates : " + e.getMessage());
		}
		return objsList;
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
			
			User uObj = (User) session.getAttribute("user");
			dObj.setUser_type_fk(uObj.getUser_type_fk());
			dObj.setUser_role_code(uObj.getUser_role_code());
			dObj.setUser_id(uObj.getUser_id());
			
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
		        
		        
		        XSSFRow row0 = RRSheet.createRow(0);
		        
	            XSSFRow headingRow = RRSheet.createRow(1);
	            String headerString = "Work^Id No^R&R ID^Phase^Structure^Location^Sub Location^Type of Use^Chainage^Latitude^Longitude^Physical Verification Date^Verification By^Letter to MMRDA^Alternate Housing Allotment^Encroachment Removal^Boundary Wall Doc^Boundary Wall Status^Handover to Execution^Planned date of completion^Carpet Area (sft)^Year of Construction^Owner Name^Occupier Name^Document Type^Document No^Map S.no^Approval By Committee^Approval by MRVC^Estimate Approval^Estimation Amount^Estimate by MMRDA^Payment to MMRDA^Relocation^Remarks";
	            
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
			 short rowNo = 2;
	         for (RandRMain obj : dataList) {
					
	                XSSFRow row = RRSheet.createRow(rowNo);
	                int c = 0;
	               
	                Cell cell = row.createCell(c++);
	                cell.setCellStyle(sectionStyle);
					cell.setCellValue(/*obj.getProject_id_fk() + " - "+*/obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIdentification_no());						
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRr_id());
					
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
					cell.setCellValue(obj.getChainage());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLatitude());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLongitude());	
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPhysical_verification());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getVerification_by());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLetter_to_mmrda());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getAlternate_housing_allotment());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getEncroachment_removal());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getBoundary_wall_doc());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getBoundary_wall_status());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getHanded_over_to_execution());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getPlanned_date_of_completion());					
					
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
					cell.setCellValue(obj.getMap_sr_no());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getApproval_by_committee());	
					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getApproval_date_by_mrvc());	
					
					
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
					cell.setCellValue(obj.getRelocation());
					
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
	
	@RequestMapping(value = "/upload-rr", method = {RequestMethod.POST})
	public ModelAndView uploadRR(@ModelAttribute RandRMain obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";String userId = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			String userRole = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setCreated_by_user_id_fk(userId);
			obj.setUser_id(userId);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setUser_role_code(userRole);
			model.setViewName("redirect:/randr-main");
			
			if(!StringUtils.isEmpty(obj.getRandRFile())){
				MultipartFile multipartFile = obj.getRandRFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet laSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = laSheet.getRow(1);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getRRFileFormat();
							List<String> fileFormatPre = FileFormatModel.getRRPreFileFormat();
							int noOfColumns = headerRow.getLastCellNum();
							if(noOfColumns == fileFormat.size())
							{
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		msg = uploadformatError;
				                		obj.setUploaded_by_user_id_fk(userId);
				                		obj.setStatus("Fail");
				                		obj.setRemarks(msg);
										boolean flag = service.saveRRDataUploadFile(obj);
				                		return model;
				                	}
								}
							}else 	if(noOfColumns == fileFormatPre.size())
							{
								for (int i = 0; i < fileFormatPre.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormatPre.get(i).trim()) && !columnName.contains(fileFormatPre.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		msg = uploadformatError;
				                		obj.setUploaded_by_user_id_fk(userId);
				                		obj.setStatus("Fail");
				                		obj.setRemarks(msg);
										boolean flag = service.saveRRDataUploadFile(obj);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
								msg = uploadformatError;
		                		obj.setUploaded_by_user_id_fk(userId);
		                		obj.setStatus("Fail");
		                		obj.setRemarks(msg);
								boolean flag = service.saveRRDataUploadFile(obj);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
							msg = uploadformatError;
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
	                		boolean flag = service.saveRRDataUploadFile(obj);
	                		return model;
						}
						
						String[]  result=null;
						if(sheetsCount>1)
						{
						  result = uploadRR(obj,userId,userName);
						}
						else
						{
						  result = uploadPreRR(obj,userId,userName);
						}
						
						String errMsg = result[0];String actualVal = "";
						int count = 0,row = 0,sheet = 0,subRow = 0;
						List<String> fileFormat = FileFormatModel.getRRFileFormat();
						if(!StringUtils.isEmpty(result[1])){count = Integer.parseInt(result[1]);}
						if(!StringUtils.isEmpty(result[2])){row = Integer.parseInt(result[2]);}
						if(!StringUtils.isEmpty(result[3])){sheet = Integer.parseInt(result[3]);}
						if(!StringUtils.isEmpty(result[4])){subRow = Integer.parseInt(result[4]);}
						//System.out.println(errMsg);
						if(!StringUtils.isEmpty(errMsg)) {
							if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Duplicate entry")) {
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;<b>Work and RR Id Mismatch at row: ("+row+")</b> please check and Upload again.</span>");
								msg = "Work and R & R Id Mismatch at row: "+row;
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
							}
						
	                		obj.setUploaded_by_user_id_fk(userId);
	                		obj.setStatus("Fail");
	                		obj.setRemarks(msg);
							boolean flag = service.saveRRDataUploadFile(obj);
	                		return model;
						}
					
						if(count > 0) {
							attributes.addFlashAttribute("success","<i class='fa fa-check'></i>&nbsp;"+ count + "<span style='color:green;'> records Uploaded successfully.</span>");	
							msg = count + " records Uploaded successfully.";
							
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
							formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
							formHistory.setModule_name_fk("R&R");
							formHistory.setForm_name("Upload R&R");
							formHistory.setForm_action_type("Upload");
							formHistory.setForm_details( msg);
							formHistory.setWork(obj.getWork_id());
							formHistory.setWork_id_fk(obj.getWork_id());
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
						boolean flag = service.saveRRDataUploadFile(obj);
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				msg = "No file exists";
				obj.setUploaded_by_user_id_fk(userId);
        		obj.setStatus("Fail");
        		obj.setRemarks(msg);
				boolean flag = service.saveRRDataUploadFile(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
			msg = "Something went wrong. Please try after some time";
			obj.setUploaded_by_user_id_fk(userId);
    		obj.setStatus("Fail");
    		obj.setRemarks(msg);
		
			try {
				boolean flag = service.saveRRDataUploadFile(obj);
			} catch (Exception e1) {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
				logger.fatal("saveDesignDataUploadFile() : "+e.getMessage());
			}
		}
		return model;
	}
	
	private  String[]  uploadRR(RandRMain obj, String userId, String userName) throws Exception {
		RandRMain rr = null;
		List<RandRMain> rrsList = new ArrayList<RandRMain>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getRandRFile();
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
					for(int i = 2; i <= laSheet.getLastRowNum();i++){
						int v = laSheet.getLastRowNum();
						XSSFRow row = laSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						rr = new RandRMain();
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setWork_id(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setRr_id(val);}
							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setPhase(val);}
							
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setStructure_id(val);}
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setLocation_name(val);}	
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setSub_location_name(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setType_of_use(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setChainage(val);}	
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setLatitude(val);}
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setLongitude(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setPhysical_verification(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setVerification_by(val);}
							
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setLetter_to_mmrda(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setAlternate_housing_allotment(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setEncroachment_removal(val);}
							
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setBoundary_wall_doc(val);}	
							
							
							
							
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setBoundary_wall_status(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setHanded_over_to_execution(val);}
							
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setPlanned_date_of_completion(val);}									
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(19));
								}
								rr.setCarpet_area(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setYear_of_construction(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setName_of_the_owner(val);}								
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setOccupier_name_during_verification(val);}										
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setDocument_type(val);}
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setDocument_no(val);}
							
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setMap_sr_no(val);}							

							
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setApproval_by_committee(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setApproval_date_by_mrvc(val);}
							
							val = formatter.formatCellValue(row.getCell(28)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setEstimate_approval_date(val);}
						
							val = formatter.formatCellValue(row.getCell(29)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(29));
								}
								rr.setEstimation_amount(val);}								

							
							val = formatter.formatCellValue(row.getCell(30)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(30));
								}
								rr.setEstimates_by_mmrda(val);}	
							
							val = formatter.formatCellValue(row.getCell(31)).trim();
							if(!StringUtils.isEmpty(val)) { 
								rr.setPayment_to_mmrda(val);}				
								
						
							val = formatter.formatCellValue(row.getCell(32)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setRelocation(val);}

							
							val = formatter.formatCellValue(row.getCell(33)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setRemarks(val);}
					
							rr.setPlanned_date_of_completion(DateParser.parse(rr.getPlanned_date_of_completion()));
							rr.setPhysical_verification(DateParser.parse(rr.getPhysical_verification()));
							rr.setEstimate_approval_date(DateParser.parse(rr.getEstimate_approval_date()));
							rr.setCreated_by_user_id_fk(userId);
							rr.setLetter_to_mmrda(DateParser.parse(rr.getLetter_to_mmrda()));
							rr.setAlternate_housing_allotment(DateParser.parse(rr.getAlternate_housing_allotment()));
							rr.setRelocation(DateParser.parse(rr.getRelocation()));
							rr.setEncroachment_removal(DateParser.parse(rr.getEncroachment_removal()));
							//rr.setBoundary_wall_doc(DateParser.parse(rr.getBoundary_wall_doc()));
							rr.setHanded_over_to_execution(DateParser.parse(rr.getHanded_over_to_execution()));
							rr.setPayment_to_mmrda(DateParser.parse(rr.getPayment_to_mmrda()));
							//rr.setRr_approval_status_by_mrvc(DateParser.parse(rr.getRr_approval_status_by_mrvc()));
							rr.setApproval_by_committee(DateParser.parse(rr.getApproval_by_committee()));
						}
				
						List<RandRMain> pObjList = new ArrayList<RandRMain>();
						List<RandRMain> pObjList1 = new ArrayList<RandRMain>();
						List<RandRMain> pObjList2 = new ArrayList<RandRMain>();
						List<RandRMain> gObjList = new ArrayList<RandRMain>();
						
						XSSFSheet laComercialDetailsSheet = workbook.getSheetAt(1);
						XSSFSheet laComEmpDetailsSheet = workbook.getSheetAt(2);
						XSSFSheet ResiDetailsSheet = workbook.getSheetAt(3);
						XSSFSheet ResFamDetailsSheet = workbook.getSheetAt(4);
						

						XSSFRow comDetails = laComercialDetailsSheet.getRow(1);
						XSSFRow ComEmpDetails = laComEmpDetailsSheet.getRow(1);
						XSSFRow resDetails = ResiDetailsSheet.getRow(1);
						XSSFRow ResFamDetails = ResFamDetailsSheet.getRow(1);
						
						//String val = null;
						if(comDetails != null){
							for(int j = 1; j <= laComercialDetailsSheet.getLastRowNum();j++){
								XSSFRow row2 = laComercialDetailsSheet.getRow(j);
								RandRMain pObj = new RandRMain();
								if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
									val = formatter.formatCellValue(row2.getCell(0)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setRr_id(val);}
									
									val = formatter.formatCellValue(row2.getCell(1)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setName_of_activity(val);}
									
									val = formatter.formatCellValue(row2.getCell(2)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setYear_of_establishment(val);}	
									
									val = formatter.formatCellValue(row2.getCell(3)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setCom_carpet_area(val);}
									
									val = formatter.formatCellValue(row2.getCell(4)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setMonthly_turnover_amount(val);}
									
									val = formatter.formatCellValue(row2.getCell(5)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setNumber_of_employees(val);}
									
									val = formatter.formatCellValue(row2.getCell(6)).trim();
									if(!StringUtils.isEmpty(val)) { pObj.setCom_remarks(val);}
							}
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								pObjList.add(pObj);
							}
									
						}
					rr.setComList(pObjList);
					}
					if(resDetails != null){
						int b  = ResiDetailsSheet.getLastRowNum();
						for(int j = 1; j <= ResiDetailsSheet.getLastRowNum();j++){
							XSSFRow row2 = ResiDetailsSheet.getRow(j);
							RandRMain pObj1 = new RandRMain();
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								val = formatter.formatCellValue(row2.getCell(0)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setRr_id(val);}
							
								val = formatter.formatCellValue(row2.getCell(1)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setOccupancy_status(val);}
								
								val = formatter.formatCellValue(row2.getCell(2)).trim();
								if(!StringUtils.isEmpty(val)) {pObj1.setGender(val);}
								
								val = formatter.formatCellValue(row2.getCell(3)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setTenure_status(val);}
								
								val = formatter.formatCellValue(row2.getCell(4)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setVulnerable_category(val);}
								
								val = formatter.formatCellValue(row2.getCell(5)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setCaste(val);}
								
								val = formatter.formatCellValue(row2.getCell(6)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setMother_tongue(val);}
								
								val = formatter.formatCellValue(row2.getCell(7)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setType_of_family(val);}
								
								val = formatter.formatCellValue(row2.getCell(8)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setFamily_size(val);}
								
								val = formatter.formatCellValue(row2.getCell(9)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setNumber_of_married_couple(val);}
								
								val = formatter.formatCellValue(row2.getCell(10)).trim();
								if(!StringUtils.isEmpty(val)) {
									int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
									if(c != 2) {
										val = getCellDataType(workbook,row2.getCell(10));
									}
									pObj1.setFamily_income_amount(val);}
							}
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								pObjList1.add(pObj1);
							}
							
						}
					rr.setResList(pObjList1);
					}	
					if(ComEmpDetails != null){
						for(int j = 1; j <= laComEmpDetailsSheet.getLastRowNum();j++){
							XSSFRow row2 = laComEmpDetailsSheet.getRow(j);
							RandRMain pObj1 = new RandRMain();
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								val = formatter.formatCellValue(row2.getCell(0)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setRr_id(val);}
							
								val = formatter.formatCellValue(row2.getCell(1)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setEmployee_name(val);}
								
								val = formatter.formatCellValue(row2.getCell(2)).trim();
								if(!StringUtils.isEmpty(val)) {
									int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
									if(c != 2) {
										val = getCellDataType(workbook,row2.getCell(2));
									}
									pObj1.setEmployee_age(val);}
								
								val = formatter.formatCellValue(row2.getCell(3)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setEmployee_gender(val);}
								
								val = formatter.formatCellValue(row2.getCell(4)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setEmployee_literacy(val);}
								
								val = formatter.formatCellValue(row2.getCell(5)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setEmployee_attended(val);}
								
								val = formatter.formatCellValue(row2.getCell(6)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setEmployee_travel_time(val);}
								
								val = formatter.formatCellValue(row2.getCell(7)).trim();
								if(!StringUtils.isEmpty(val)) { 
									int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
									if(c != 2) {
										val = getCellDataType(workbook,row2.getCell(7));
									}
									pObj1.setEmployee_salary(val);}
								
								val = formatter.formatCellValue(row2.getCell(8)).trim();
								if(!StringUtils.isEmpty(val)) { pObj1.setEmployee_nature_of_work(val);}
							}
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								pObjList2.add(pObj1);	
							}
							
						}
					rr.setComFamList(pObjList2);
					}
					if(ResFamDetails != null){
						for(int j = 1; j <= ResFamDetailsSheet.getLastRowNum();j++){
							XSSFRow row2 = ResFamDetailsSheet.getRow(j);
							RandRMain ResFamDetail = new RandRMain();
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								val = formatter.formatCellValue(row2.getCell(0)).trim();
								if(!StringUtils.isEmpty(val)) { ResFamDetail.setRr_id(val);}
						
								val = formatter.formatCellValue(row2.getCell(1)).trim();
								if(!StringUtils.isEmpty(val)) { ResFamDetail.setResidential_name(val);}
								
								val = formatter.formatCellValue(row2.getCell(2)).trim();
								if(!StringUtils.isEmpty(val)) {ResFamDetail.setResidential_relation_with_head(val);}
								
								val = formatter.formatCellValue(row2.getCell(3)).trim();
								if(!StringUtils.isEmpty(val)) {ResFamDetail.setResidential_age(val);}
								
								val = formatter.formatCellValue(row2.getCell(4)).trim();
								if(!StringUtils.isEmpty(val)) { ResFamDetail.setResidential_gender(val);}
								
								val = formatter.formatCellValue(row2.getCell(5)).trim();
								if(!StringUtils.isEmpty(val)) {ResFamDetail.setResidential_maritual_status(val);}
								
								val = formatter.formatCellValue(row2.getCell(6)).trim();
								if(!StringUtils.isEmpty(val)) {ResFamDetail.setResidential_education(val);}
								
								val = formatter.formatCellValue(row2.getCell(7)).trim();
								if(!StringUtils.isEmpty(val)) {ResFamDetail.setResidential_employment(val);}
								
								val = formatter.formatCellValue(row2.getCell(8)).trim();
								if(!StringUtils.isEmpty(val)) {
									int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
									if(c != 2) {
										val = getCellDataType(workbook,row2.getCell(8));
									}
									ResFamDetail.setResidential_salary(val);}
								}
							if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(rr.getRr_id())) {
								gObjList.add(ResFamDetail);

							}
						}
						rr.setResFamList(gObjList);
						}
					    rr.setUser_role_code(obj.getUser_role_code());
						boolean flag = rr.checkNullOrEmpty();
						if(!flag && !StringUtils.isEmpty(rr.getRr_id())) {
							rrsList.add(rr);
						}
					}
					if(!rrsList.isEmpty() && rrsList != null){
						String[] arr  = service.uploadRRData(rrsList,rr);
						result[0] = arr[0];
						result[1] = arr[1];
						result[2] = arr[2];
						result[3] = arr[3];
						result[4] = arr[4];
					}
					
				}
				workbook.close();
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadRRs() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRRs() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return result;
	}	
	private  String[]  uploadPreRR(RandRMain obj, String userId, String userName) throws Exception {
		RandRMain rr = null;
		List<RandRMain> rrsList = new ArrayList<RandRMain>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getRandRFile();
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
					for(int i = 2; i <= laSheet.getLastRowNum();i++){
						int v = laSheet.getLastRowNum();
						XSSFRow row = laSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						rr = new RandRMain();
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setWork_id(val);}
							
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setIdentification_no(val);}							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setPhase(val);}
							
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setStructure_id(val);}
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setLocation_name(val);}	
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setSub_location_name(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setType_of_use(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setChainage(val);}	
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setLatitude(val);}
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setLongitude(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setPhysical_verification(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setVerification_by(val);}
							
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setLetter_to_mmrda(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setAlternate_housing_allotment(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setEncroachment_removal(val);}
							
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setBoundary_wall_doc(val);}	
							
							
							
							
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setBoundary_wall_status(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setHanded_over_to_execution(val);}
							
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setPlanned_date_of_completion(val);}									
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(19));
								}
								rr.setCarpet_area(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setYear_of_construction(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setName_of_the_owner(val);}								
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setOccupier_name_during_verification(val);}										
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setDocument_type(val);}
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setDocument_no(val);}
							
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setMap_sr_no(val);}							

							
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setApproval_by_committee(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setApproval_date_by_mrvc(val);}
							
							val = formatter.formatCellValue(row.getCell(28)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setEstimate_approval_date(val);}
						
							val = formatter.formatCellValue(row.getCell(29)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(29));
								}
								rr.setEstimation_amount(val);}								

							
							val = formatter.formatCellValue(row.getCell(30)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(30));
								}
								rr.setEstimates_by_mmrda(val);}	
							
							val = formatter.formatCellValue(row.getCell(31)).trim();
							if(!StringUtils.isEmpty(val)) { 
								rr.setPayment_to_mmrda(val);}				
								
						
							val = formatter.formatCellValue(row.getCell(32)).trim();
							if(!StringUtils.isEmpty(val)) {rr.setRelocation(val);}

							
							val = formatter.formatCellValue(row.getCell(33)).trim();
							if(!StringUtils.isEmpty(val)) { rr.setRemarks(val);}
							
							
							rr.setBoundary_wall_doc(DateParser.parse(rr.getBoundary_wall_doc()));
							rr.setPlanned_date_of_completion(DateParser.parse(rr.getPlanned_date_of_completion()));
							rr.setPhysical_verification(DateParser.parse(rr.getPhysical_verification()));
							rr.setEstimate_approval_date(DateParser.parse(rr.getEstimate_approval_date()));
							rr.setCreated_by_user_id_fk(userId);
							rr.setLetter_to_mmrda(DateParser.parse(rr.getLetter_to_mmrda()));
							rr.setAlternate_housing_allotment(DateParser.parse(rr.getAlternate_housing_allotment()));
							rr.setRelocation(DateParser.parse(rr.getRelocation()));
							rr.setEncroachment_removal(DateParser.parse(rr.getEncroachment_removal()));
							//rr.setBoundary_wall_doc(DateParser.parse(rr.getBoundary_wall_doc()));
							rr.setHanded_over_to_execution(DateParser.parse(rr.getHanded_over_to_execution()));
							rr.setPayment_to_mmrda(DateParser.parse(rr.getPayment_to_mmrda()));
							//rr.setRr_approval_status_by_mrvc(DateParser.parse(rr.getRr_approval_status_by_mrvc()));
							rr.setApproval_by_committee(DateParser.parse(rr.getApproval_by_committee()));
						}
				

					    rr.setUser_role_code(obj.getUser_role_code());
						boolean flag = rr.checkNullOrEmpty();
						rrsList.add(rr);
						
					}
					if(!rrsList.isEmpty() && rrsList != null){
						String[] arr  = service.uploadRRData(rrsList,rr);
						result[0] = arr[0];
						result[1] = arr[1];
						result[2] = arr[2];
						result[3] = arr[3];
						result[4] = arr[4];
					}
					
				}
				workbook.close();
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadRRs() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadRRs() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return result;
	}
	
	private String getCellDataType(XSSFWorkbook workbook, XSSFCell cell) {
		String val = null;
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator(); 

		// existing Sheet, Row, and Cell setup
		//workbook.setForceFormulaRecalculation(true);
		try {
			CellType type = cell.getCellType();
			if (!StringUtils.isEmpty(cell)) {
			    switch (type) {
			        case BOOLEAN:
			            val = String.valueOf(cell.getBooleanCellValue());
			            break;
			        case NUMERIC:
			        	val = String.valueOf(cell.getNumericCellValue());
			        	if(val.contains("E")){
			        		val = BigDecimal.valueOf(Double.parseDouble(val)).toPlainString();
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
