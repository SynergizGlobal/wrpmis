package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.docx4j.wml.JcEnumeration;
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
import com.synergizglobal.pmis.Iservice.LandAcquisitionService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAquisationPaginationObject;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.TAFinancials;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.LandAcquisition;

@Controller
public class LandAcquisitionController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LandAcquisitionController.class);
	
	@Autowired
	LandAcquisitionService service;
	@Autowired
	FormsHistoryDao formsHistoryDao;
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
	@RequestMapping(value="/land-acquisition",method={RequestMethod.GET})
	public ModelAndView landAcquisition(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("landAcquisition : " + e.getMessage());
		}
		return model;
	}
	/*
	 * 
	 * @RequestMapping(value = "/ajax/get-land-acquisition", method =
	 * {RequestMethod.GET,RequestMethod.POST},produces=MediaType.
	 * APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public List<LandAcquisition>
	 * getLandAcquisitionList(@ModelAttribute LandAcquisition obj) {
	 * List<LandAcquisition> landAcquisitionList = null; try { landAcquisitionList =
	 * service.getLandAcquisitionList(obj); }catch (Exception e) {
	 * e.printStackTrace(); logger.error("getLandAcquisitionList : " +
	 * e.getMessage()); } return landAcquisitionList; }
	 */
	
	@RequestMapping(value = "/ajax/get-land-acquisition", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute LandAcquisition obj, HttpServletRequest request,
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

			List<LandAcquisition> dataList = new ArrayList<LandAcquisition>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				dataList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				dataList = createPaginationData(startIndex, offset, obj, searchParameter, session);
			}

			//Search functionality: Returns filtered list based on search parameter
			//lasList = getListBasedOnSearchParameter(searchParameter,lasList);

			int totalRecords = getTotalRecords(obj, searchParameter, session);

			LandAquisationPaginationObject personJsonObject = new LandAquisationPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(dataList);

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
	public int getTotalRecords(LandAcquisition obj, String searchParameter,HttpSession session) {
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
	public List<LandAcquisition> createPaginationData(int startIndex, int offset, LandAcquisition obj, String searchParameter,HttpSession session) {
		List<LandAcquisition> earthWorkList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			earthWorkList = service.getLandAcquisitionList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/ajax/getStatussFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getProjectsList(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> projectsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			projectsList = service.getLandAcquisitionStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getWorksList(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> worksList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			worksList = service.getLandAcquisitionWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getVillagesFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getVillagesList(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> villagesList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			villagesList = service.getLandAcquisitionVillagesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getVillagesList : " + e.getMessage());
		}
		return villagesList;
	}
	
	@RequestMapping(value = "/ajax/getTypesOfLandsFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getTypesOfLandsList(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> typesOfLandsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			typesOfLandsList = service.getLandAcquisitionTypesOfLandsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTypesOfLandsList : " + e.getMessage());
		}
		return typesOfLandsList;
	}
	
	@RequestMapping(value = "/ajax/getSubCategoryFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getSubCategorysList(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> subCategoryList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			subCategoryList = service.getLandAcquisitionSubCategoryList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubCategorysList : " + e.getMessage());
		}
		return subCategoryList;
	}
	
	@RequestMapping(value = "/ajax/getCoordinates", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getCoordinates(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objsList = service.getCoordinates(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getCoordinates : " + e.getMessage());
		}
		return objsList;
	}	
	
	@RequestMapping(value = "/ajax/getWorkListForLAForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getWorkListForLAForm(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> objsList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objsList = service.getWorkListForLAForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForLAForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getSubCategorysList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getSubCategoryList(@ModelAttribute LandAcquisition obj){
		List<LandAcquisition> objList = null;
		try{
			objList = service.getSubCategoryList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getSubCategoryList() : "+e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getLandsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getLandsList(@ModelAttribute LandAcquisition obj){
		List<LandAcquisition> objList = null;
		try{
			objList = service.getLandsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getLandsList() : "+e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/checkSurveyNumber", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean checkSurveyNumber(String survey_number,String village_id,String la_id) throws Exception {
		boolean flag = false;
		try {
			flag = service.checkSurveyNumber(survey_number,village_id,la_id);
		} catch (SQLException e) {
			logger.error("checkSurveyNumber : " + e.getMessage());
		}
		return flag;
	}	
	
	@RequestMapping(value = "/add-land-acquisition-form", method = {RequestMethod.GET})
	public ModelAndView addLandAcquisitionForm(HttpSession session,@ModelAttribute LandAcquisition obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.landAcquisitionForm);
			model.addObject("action", "add");
			List<LandAcquisition> statusList = service.getStatusList();
			model.addObject("statusList", statusList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			
			List<LandAcquisition> projectsList = service.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<LandAcquisition> worksList = service.getWorkListForLAForm(obj);
			model.addObject("worksList", worksList);
			
			List<LandAcquisition> landsList = service.getLandsListForLAForm(obj);
			model.addObject("landsList", landsList);
			
			List<LandAcquisition> issueCatogoriesList = service.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			
			List<LandAcquisition> subCategorysList = service.getSubCategorysListForLAForm(obj);
			model.addObject("subCategorysList", subCategorysList);
			
			List<LandAcquisition> unitsList = service.getUnitsList();
			model.addObject("unitsList", unitsList);
			
			List<LandAcquisition> laFileType = service.getLaFileType();
			model.addObject("laFileType", laFileType);

			List<LandAcquisition> laLandStatus = service.getLaLandStatus();
			model.addObject("laLandStatus", laLandStatus);
			
		}catch (Exception e) {
				logger.error("addLandAcquisitionForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-land-acquisition", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getLandAcquisitionForm(@ModelAttribute LandAcquisition obj ,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.landAcquisitionForm);
			model.addObject("action", "edit");
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			List<LandAcquisition> statusList = service.getStatusList();
			model.addObject("statusList", statusList);
			
			List<LandAcquisition> landsList = service.getLandsListForLAForm(obj);
			model.addObject("landsList", landsList);
			
			List<LandAcquisition> issueCatogoriesList = service.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			
			List<LandAcquisition> subCategorysList = service.getSubCategorysListForLAForm(obj);
			model.addObject("subCategorysList", subCategorysList);
			
			List<LandAcquisition> unitsList = service.getUnitsList();
			model.addObject("unitsList", unitsList);
			
			List<LandAcquisition> laFileType = service.getLaFileType();
			model.addObject("laFileType", laFileType);
			
			List<LandAcquisition> laLandStatus = service.getLaLandStatus();
			model.addObject("laLandStatus", laLandStatus);
			
			LandAcquisition LADetails = service.getLandAcquisitionForm(obj);
			model.addObject("LADetails", LADetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getLandAcquisitionForm : " + e.getMessage());
		}
		return model;
	 }
	
	
	@RequestMapping(value = "/ajax/getLADetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getLADetails(@ModelAttribute LandAcquisition obj,HttpSession session) {
		List<LandAcquisition> LADetails = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			LADetails = service.getLADetails(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLADetails : " + e.getMessage());
		}
		return LADetails;
	}
	
	
	@RequestMapping(value = "/get-land-acquisition/{la_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getLandAcquisitionFormForId(@ModelAttribute LandAcquisition obj,@PathVariable("la_id") String la_id  ,HttpSession session ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.landAcquisitionForm);
			model.addObject("action", "edit");
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<LandAcquisition> statusList = service.getStatusList();
			model.addObject("statusList", statusList);
			
			List<LandAcquisition> landsList = service.getLandsListForLAForm(obj);
			model.addObject("landsList", landsList);
			
			List<LandAcquisition> issueCatogoriesList = service.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			
			List<LandAcquisition> subCategorysList = service.getSubCategorysListForLAForm(obj);
			model.addObject("subCategorysList", subCategorysList);
			
			List<LandAcquisition> unitsList = service.getUnitsList();
			model.addObject("unitsList", unitsList);
			
			List<LandAcquisition> laFileType = service.getLaFileType();
			model.addObject("laFileType", laFileType);
			
			List<LandAcquisition> laLandStatus = service.getLaLandStatus();
			model.addObject("laLandStatus", laLandStatus);
			
			obj.setLa_id(la_id);
			LandAcquisition LADetails = service.getLandAcquisitionForm(obj);
			model.addObject("LADetails", LADetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getLandAcquisitionFormForId : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-land-acquisition", method = {RequestMethod.POST})
	public ModelAndView addLandAcquisition(@ModelAttribute LandAcquisition obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/land-acquisition");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			obj.setProposal_submission_date_to_collector(DateParser.parse(obj.getProposal_submission_date_to_collector()));
			obj.setJm_fee_letter_received_date(DateParser.parse(obj.getJm_fee_letter_received_date()));
			obj.setJm_fee_paid_date(DateParser.parse(obj.getJm_fee_paid_date()));
			obj.setJm_start_date(DateParser.parse(obj.getJm_start_date()));
			obj.setJm_completion_date(DateParser.parse(obj.getJm_completion_date()));
			obj.setJm_sheet_date_to_sdo(DateParser.parse(obj.getJm_sheet_date_to_sdo()));
			
			obj.setForest_online_submission(DateParser.parse(obj.getForest_online_submission()));
			obj.setForest_submission_date_to_dycfo(DateParser.parse(obj.getForest_submission_date_to_dycfo()));
			obj.setForest_submission_date_to_ccf_thane(DateParser.parse(obj.getForest_submission_date_to_ccf_thane()));
			obj.setForest_submission_date_to_nodal_officer(DateParser.parse(obj.getForest_submission_date_to_nodal_officer()));
			obj.setForest_submission_date_to_revenue_secretary_mantralaya(DateParser.parse(obj.getForest_submission_date_to_revenue_secretary_mantralaya()));
			obj.setForest_submission_date_to_regional_office_nagpur(DateParser.parse(obj.getForest_submission_date_to_regional_office_nagpur()));
			obj.setForest_date_of_approval_by_regional_office_nagpur(DateParser.parse(obj.getForest_date_of_approval_by_regional_office_nagpur()));
			obj.setForest_valuation_by_dycfo(DateParser.parse(obj.getForest_valuation_by_dycfo()));
			obj.setForest_approval_for_payment(DateParser.parse(obj.getForest_approval_for_payment()));
			obj.setForest_payment_date(DateParser.parse(obj.getForest_payment_date()));
			obj.setForest_possession_date(DateParser.parse(obj.getForest_possession_date()));
			
			obj.setProposal_submission(DateParser.parse(obj.getProposal_submission()));
			obj.setLetter_for_payment(DateParser.parse(obj.getLetter_for_payment()));
			obj.setApproval_for_payment(DateParser.parse(obj.getApproval_for_payment()));
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			obj.setPossession_date(DateParser.parse(obj.getPossession_date()));
			obj.setValuation_date(DateParser.parse(obj.getValuation_date()));
			
			obj.setPlanned_date_of_possession(DateParser.parse(obj.getPlanned_date_of_possession()));
			
			obj.setRailway_online_submission(DateParser.parse(obj.getRailway_online_submission()));
			obj.setRailway_submission_date_to_DyCFO(DateParser.parse(obj.getRailway_submission_date_to_DyCFO()));
			obj.setRailway_submission_date_to_CCF_Thane(DateParser.parse(obj.getRailway_submission_date_to_CCF_Thane()));
			obj.setRailway_submission_date_to_nodal_officer_CCF_Nagpur(DateParser.parse(obj.getRailway_submission_date_to_nodal_officer_CCF_Nagpur()));
			obj.setRailway_submission_date_to_revenue_secretary_mantralaya(DateParser.parse(obj.getRailway_submission_date_to_revenue_secretary_mantralaya()));
			obj.setRailway_submission_date_to_regional_office_nagpur(DateParser.parse(obj.getRailway_submission_date_to_regional_office_nagpur()));
			obj.setRailway_date_of_approval_by_Rregional_Office_agpur(DateParser.parse(obj.getRailway_date_of_approval_by_Rregional_Office_agpur()));
			obj.setRailway_valuation_by_DyCFO(DateParser.parse(obj.getRailway_valuation_by_DyCFO()));
			obj.setRailway_approval_for_payment(DateParser.parse(obj.getRailway_approval_for_payment()));
			obj.setRailway_payment_date(DateParser.parse(obj.getRailway_payment_date()));
			obj.setRailway_possession_date(DateParser.parse(obj.getRailway_possession_date()));
			
			obj.setConsent_from_owner(DateParser.parse(obj.getConsent_from_owner()));
			obj.setLegal_search_report(DateParser.parse(obj.getLegal_search_report()));
			obj.setDate_of_registration(DateParser.parse(obj.getDate_of_registration()));
			obj.setDate_of_possession(DateParser.parse(obj.getDate_of_possession()));
			obj.setForest_tree_survey(DateParser.parse(obj.getForest_tree_survey()));
			obj.setForest_tree_valuation(DateParser.parse(obj.getForest_tree_valuation()));
			obj.setHorticulture_tree_survey(DateParser.parse(obj.getHorticulture_tree_survey()));
			obj.setHorticulture_tree_valuation(DateParser.parse(obj.getHorticulture_tree_valuation()));
			obj.setStructure_survey(DateParser.parse(obj.getStructure_survey()));
			obj.setStructure_valuation(DateParser.parse(obj.getStructure_valuation()));
			obj.setBorewell_survey(DateParser.parse(obj.getBorewell_survey()));
			obj.setBorewell_valuation(DateParser.parse(obj.getBorewell_valuation()));
			obj.setDate_of_rfp_to_adtp(DateParser.parse(obj.getDate_of_rfp_to_adtp()));
			obj.setDate_of_rate_fixation_of_land(DateParser.parse(obj.getDate_of_rate_fixation_of_land()));
			obj.setSdo_demand_for_payment(DateParser.parse(obj.getSdo_demand_for_payment()));
			obj.setDate_of_approval_for_payment(DateParser.parse(obj.getDate_of_approval_for_payment()));
			obj.setPrivate_payment_date(DateParser.parse(obj.getPrivate_payment_date()));
			
			obj.setSubmission_of_proposal_to_GM(DateParser.parse(obj.getSubmission_of_proposal_to_GM()));
			obj.setApproval_of_GM(DateParser.parse(obj.getApproval_of_GM()));
			obj.setDraft_letter_to_con_for_approval_rp(DateParser.parse(obj.getDraft_letter_to_con_for_approval_rp()));
			obj.setDate_of_approval_of_construction_rp(DateParser.parse(obj.getDate_of_approval_of_construction_rp()));
			obj.setDate_of_uploading_of_gazette_notification_rp(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_rp()));
			obj.setPublication_in_gazette_rp(DateParser.parse(obj.getPublication_in_gazette_rp()));
			
			obj.setDate_of_proposal_to_DC_for_nomination(DateParser.parse(obj.getDate_of_proposal_to_DC_for_nomination()));
			obj.setDate_of_nomination_of_competenta_authority(DateParser.parse(obj.getDate_of_nomination_of_competenta_authority()));
			obj.setDraft_letter_to_con_for_approval_ca(DateParser.parse(obj.getDraft_letter_to_con_for_approval_ca()));
			obj.setDate_of_approval_of_construction_ca(DateParser.parse(obj.getDate_of_approval_of_construction_ca()));
			obj.setDate_of_uploading_of_gazette_notification_ca(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_ca()));
			obj.setPublication_in_gazette_ca(DateParser.parse(obj.getPublication_in_gazette_ca()));
			obj.setDate_of_submission_of_draft_notification_to_CALA(DateParser.parse(obj.getDate_of_submission_of_draft_notification_to_CALA()));
			obj.setApproval_of_CALA_20a(DateParser.parse(obj.getApproval_of_CALA_20a()));
			obj.setDraft_letter_to_con_for_approval_20a(DateParser.parse(obj.getDraft_letter_to_con_for_approval_20a()));
			obj.setDate_of_approval_of_construction_20a(DateParser.parse(obj.getDate_of_approval_of_construction_20a()));
			obj.setDate_of_uploading_of_gazette_notification_20a(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_20a()));
			obj.setPublication_in_gazette_20a(DateParser.parse(obj.getPublication_in_gazette_20a()));
			obj.setPublication_in_2_local_news_papers_20a(DateParser.parse(obj.getPublication_in_2_local_news_papers_20a()));
			obj.setPasting_of_notification_in_villages_20a(DateParser.parse(obj.getPasting_of_notification_in_villages_20a()));
			obj.setReceipt_of_grievances(DateParser.parse(obj.getReceipt_of_grievances()));
			obj.setDisposal_of_grievances(DateParser.parse(obj.getDisposal_of_grievances()));
			obj.setDate_of_submission_of_draft_notification_to_CALA_20e(DateParser.parse(obj.getDate_of_submission_of_draft_notification_to_CALA_20e()));
			obj.setApproval_of_CALA_20e(DateParser.parse(obj.getApproval_of_CALA_20e()));
			obj.setDraft_letter_to_con_for_approval_20e(DateParser.parse(obj.getDraft_letter_to_con_for_approval_20e()));
			obj.setDate_of_approval_of_construction_20e(DateParser.parse(obj.getDate_of_approval_of_construction_20e()));
			obj.setDate_of_uploading_of_gazette_notification_20e(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_20e()));
			obj.setPublication_in_gazette_20e(DateParser.parse(obj.getPublication_in_gazette_20e()));
			obj.setPublication_of_notice_in_2_local_news_papers_20e(DateParser.parse(obj.getPublication_of_notice_in_2_local_news_papers_20e()));
			obj.setDate_of_submission_of_draft_notification_to_CALA_20f(DateParser.parse(obj.getDate_of_submission_of_draft_notification_to_CALA_20f()));
			obj.setApproval_of_CALA_20f(DateParser.parse(obj.getApproval_of_CALA_20f()));
			obj.setDraft_letter_to_con_for_approval_20f(DateParser.parse(obj.getDraft_letter_to_con_for_approval_20f()));
			obj.setDate_of_approval_of_construction_20f(DateParser.parse(obj.getDate_of_approval_of_construction_20f()));
			obj.setDate_of_uploading_of_gazette_notification_20f(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_20f()));
			obj.setPublication_in_gazette_20f(DateParser.parse(obj.getPublication_in_gazette_20f()));
			obj.setPublication_of_notice_in_2_local_news_papers_20f(DateParser.parse(obj.getPublication_of_notice_in_2_local_news_papers_20f()));
			
			
			boolean flag =  service.addLandAcquisition(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Land Acquisition is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Land Acquisition is failed. Try again.");
			logger.error("addLandAcquisition : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-land-acquisition", method = {RequestMethod.POST})
	public ModelAndView updateLandAcquisition(@ModelAttribute LandAcquisition obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
		
			model.setViewName("redirect:/land-acquisition");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setProposal_submission_date_to_collector(DateParser.parse(obj.getProposal_submission_date_to_collector()));
			obj.setJm_fee_letter_received_date(DateParser.parse(obj.getJm_fee_letter_received_date()));
			obj.setJm_fee_paid_date(DateParser.parse(obj.getJm_fee_paid_date()));
			obj.setJm_start_date(DateParser.parse(obj.getJm_start_date()));
			obj.setJm_completion_date(DateParser.parse(obj.getJm_completion_date()));
			obj.setJm_sheet_date_to_sdo(DateParser.parse(obj.getJm_sheet_date_to_sdo()));
			
			obj.setForest_online_submission(DateParser.parse(obj.getForest_online_submission()));
			obj.setForest_submission_date_to_dycfo(DateParser.parse(obj.getForest_submission_date_to_dycfo()));
			obj.setForest_submission_date_to_ccf_thane(DateParser.parse(obj.getForest_submission_date_to_ccf_thane()));
			obj.setForest_submission_date_to_nodal_officer(DateParser.parse(obj.getForest_submission_date_to_nodal_officer()));
			obj.setForest_submission_date_to_revenue_secretary_mantralaya(DateParser.parse(obj.getForest_submission_date_to_revenue_secretary_mantralaya()));
			obj.setForest_submission_date_to_regional_office_nagpur(DateParser.parse(obj.getForest_submission_date_to_regional_office_nagpur()));
			obj.setForest_date_of_approval_by_regional_office_nagpur(DateParser.parse(obj.getForest_date_of_approval_by_regional_office_nagpur()));
			obj.setForest_valuation_by_dycfo(DateParser.parse(obj.getForest_valuation_by_dycfo()));
			obj.setForest_approval_for_payment(DateParser.parse(obj.getForest_approval_for_payment()));
			obj.setForest_payment_date(DateParser.parse(obj.getForest_payment_date()));
			obj.setForest_possession_date(DateParser.parse(obj.getForest_possession_date()));
			
			obj.setProposal_submission(DateParser.parse(obj.getProposal_submission()));
			obj.setLetter_for_payment(DateParser.parse(obj.getLetter_for_payment()));
			obj.setApproval_for_payment(DateParser.parse(obj.getApproval_for_payment()));
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			obj.setPossession_date(DateParser.parse(obj.getPossession_date()));
			obj.setValuation_date(DateParser.parse(obj.getValuation_date()));
			
			obj.setPlanned_date_of_possession(DateParser.parse(obj.getPlanned_date_of_possession()));
			
			obj.setRailway_online_submission(DateParser.parse(obj.getRailway_online_submission()));
			obj.setRailway_submission_date_to_DyCFO(DateParser.parse(obj.getRailway_submission_date_to_DyCFO()));
			obj.setRailway_submission_date_to_CCF_Thane(DateParser.parse(obj.getRailway_submission_date_to_CCF_Thane()));
			obj.setRailway_submission_date_to_nodal_officer_CCF_Nagpur(DateParser.parse(obj.getRailway_submission_date_to_nodal_officer_CCF_Nagpur()));
			obj.setRailway_submission_date_to_revenue_secretary_mantralaya(DateParser.parse(obj.getRailway_submission_date_to_revenue_secretary_mantralaya()));
			obj.setRailway_submission_date_to_regional_office_nagpur(DateParser.parse(obj.getRailway_submission_date_to_regional_office_nagpur()));
			obj.setRailway_date_of_approval_by_Rregional_Office_agpur(DateParser.parse(obj.getRailway_date_of_approval_by_Rregional_Office_agpur()));
			obj.setRailway_valuation_by_DyCFO(DateParser.parse(obj.getRailway_valuation_by_DyCFO()));
			obj.setRailway_approval_for_payment(DateParser.parse(obj.getRailway_approval_for_payment()));
			obj.setRailway_payment_date(DateParser.parse(obj.getRailway_payment_date()));
			obj.setRailway_possession_date(DateParser.parse(obj.getRailway_possession_date()));
			
			obj.setConsent_from_owner(DateParser.parse(obj.getConsent_from_owner()));
			obj.setLegal_search_report(DateParser.parse(obj.getLegal_search_report()));
			obj.setDate_of_registration(DateParser.parse(obj.getDate_of_registration()));
			obj.setDate_of_possession(DateParser.parse(obj.getDate_of_possession()));
			obj.setForest_tree_survey(DateParser.parse(obj.getForest_tree_survey()));
			obj.setForest_tree_valuation(DateParser.parse(obj.getForest_tree_valuation()));
			obj.setHorticulture_tree_survey(DateParser.parse(obj.getHorticulture_tree_survey()));
			obj.setHorticulture_tree_valuation(DateParser.parse(obj.getHorticulture_tree_valuation()));
			obj.setStructure_survey(DateParser.parse(obj.getStructure_survey()));
			obj.setStructure_valuation(DateParser.parse(obj.getStructure_valuation()));
			obj.setBorewell_survey(DateParser.parse(obj.getBorewell_survey()));
			obj.setBorewell_valuation(DateParser.parse(obj.getBorewell_valuation()));
			obj.setDate_of_rfp_to_adtp(DateParser.parse(obj.getDate_of_rfp_to_adtp()));
			obj.setDate_of_rate_fixation_of_land(DateParser.parse(obj.getDate_of_rate_fixation_of_land()));
			obj.setSdo_demand_for_payment(DateParser.parse(obj.getSdo_demand_for_payment()));
			obj.setDate_of_approval_for_payment(DateParser.parse(obj.getDate_of_approval_for_payment()));
			obj.setPrivate_payment_date(DateParser.parse(obj.getPrivate_payment_date()));
			
			obj.setSubmission_of_proposal_to_GM(DateParser.parse(obj.getSubmission_of_proposal_to_GM()));
			obj.setApproval_of_GM(DateParser.parse(obj.getApproval_of_GM()));
			obj.setDraft_letter_to_con_for_approval_rp(DateParser.parse(obj.getDraft_letter_to_con_for_approval_rp()));
			obj.setDate_of_approval_of_construction_rp(DateParser.parse(obj.getDate_of_approval_of_construction_rp()));
			obj.setDate_of_uploading_of_gazette_notification_rp(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_rp()));
			obj.setPublication_in_gazette_rp(DateParser.parse(obj.getPublication_in_gazette_rp()));
			
			obj.setDate_of_proposal_to_DC_for_nomination(DateParser.parse(obj.getDate_of_proposal_to_DC_for_nomination()));
			obj.setDate_of_nomination_of_competenta_authority(DateParser.parse(obj.getDate_of_nomination_of_competenta_authority()));
			obj.setDraft_letter_to_con_for_approval_ca(DateParser.parse(obj.getDraft_letter_to_con_for_approval_ca()));
			obj.setDate_of_approval_of_construction_ca(DateParser.parse(obj.getDate_of_approval_of_construction_ca()));
			obj.setDate_of_uploading_of_gazette_notification_ca(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_ca()));
			obj.setPublication_in_gazette_ca(DateParser.parse(obj.getPublication_in_gazette_ca()));
			obj.setDate_of_submission_of_draft_notification_to_CALA(DateParser.parse(obj.getDate_of_submission_of_draft_notification_to_CALA()));
			obj.setApproval_of_CALA_20a(DateParser.parse(obj.getApproval_of_CALA_20a()));
			obj.setDraft_letter_to_con_for_approval_20a(DateParser.parse(obj.getDraft_letter_to_con_for_approval_20a()));
			obj.setDate_of_approval_of_construction_20a(DateParser.parse(obj.getDate_of_approval_of_construction_20a()));
			obj.setDate_of_uploading_of_gazette_notification_20a(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_20a()));
			obj.setPublication_in_gazette_20a(DateParser.parse(obj.getPublication_in_gazette_20a()));
			obj.setPublication_in_2_local_news_papers_20a(DateParser.parse(obj.getPublication_in_2_local_news_papers_20a()));
			obj.setPasting_of_notification_in_villages_20a(DateParser.parse(obj.getPasting_of_notification_in_villages_20a()));
			obj.setReceipt_of_grievances(DateParser.parse(obj.getReceipt_of_grievances()));
			obj.setDisposal_of_grievances(DateParser.parse(obj.getDisposal_of_grievances()));
			obj.setDate_of_submission_of_draft_notification_to_CALA_20e(DateParser.parse(obj.getDate_of_submission_of_draft_notification_to_CALA_20e()));
			obj.setApproval_of_CALA_20e(DateParser.parse(obj.getApproval_of_CALA_20e()));
			obj.setDraft_letter_to_con_for_approval_20e(DateParser.parse(obj.getDraft_letter_to_con_for_approval_20e()));
			obj.setDate_of_approval_of_construction_20e(DateParser.parse(obj.getDate_of_approval_of_construction_20e()));
			obj.setDate_of_uploading_of_gazette_notification_20e(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_20e()));
			obj.setPublication_in_gazette_20e(DateParser.parse(obj.getPublication_in_gazette_20e()));
			obj.setPublication_of_notice_in_2_local_news_papers_20e(DateParser.parse(obj.getPublication_of_notice_in_2_local_news_papers_20e()));
			obj.setDate_of_submission_of_draft_notification_to_CALA_20f(DateParser.parse(obj.getDate_of_submission_of_draft_notification_to_CALA_20f()));
			obj.setApproval_of_CALA_20f(DateParser.parse(obj.getApproval_of_CALA_20f()));
			obj.setDraft_letter_to_con_for_approval_20f(DateParser.parse(obj.getDraft_letter_to_con_for_approval_20f()));
			obj.setDate_of_approval_of_construction_20f(DateParser.parse(obj.getDate_of_approval_of_construction_20f()));
			obj.setDate_of_uploading_of_gazette_notification_20f(DateParser.parse(obj.getDate_of_uploading_of_gazette_notification_20f()));
			obj.setPublication_in_gazette_20f(DateParser.parse(obj.getPublication_in_gazette_20f()));
			obj.setPublication_of_notice_in_2_local_news_papers_20f(DateParser.parse(obj.getPublication_of_notice_in_2_local_news_papers_20f()));
			boolean flag = service.updateLandAcquisition(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Land Acquisition is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Land Acquisition is failed. Try again.");
			logger.error("updateLandAcquisition : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/upload-la", method = {RequestMethod.POST})
	public ModelAndView uploadLA(@ModelAttribute LandAcquisition obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String msg = "";
		try {
			String userId = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			String userRoleCode = (String) session.getAttribute("USER_ROLE_CODE");
			
			obj.setCreated_by_user_id_fk(userId);
			obj.setUser_id(userId);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setUser_role_code(userRoleCode);
			model.setViewName("redirect:/land-acquisition");
			
			if(!StringUtils.isEmpty(obj.getLaUploadFile())){
				MultipartFile multipartFile = obj.getLaUploadFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet laSheet = workbook.getSheetAt(3);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = laSheet.getRow(1);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getLAFileFormat();
							List<String> fileFormatLand = FileFormatModel.getLAFileFormatOnlyLandIndentification();
							
							int noOfColumns = headerRow.getLastCellNum();
							if(sheetsCount==4)
							{
								if(noOfColumns == fileFormatLand.size()){
									for (int i = 0; i < fileFormatLand.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.equals(fileFormatLand.get(i).trim()) && !columnName.contains(fileFormatLand.get(i).trim())){
					                		attributes.addFlashAttribute("error",uploadformatError);
					                		return model;
					                	}
									}
								}else{
									attributes.addFlashAttribute("error",uploadformatError);
			                		return model;
								}								
							}
							else
							{
								if(noOfColumns == fileFormat.size()){
									for (int i = 0; i < fileFormat.size();i++) {
					                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
					                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
										String columnName = headerRow.getCell(i).getStringCellValue().trim();
										if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
					                		attributes.addFlashAttribute("error",uploadformatError);
					                		return model;
					                	}
									}
								}else{
									attributes.addFlashAttribute("error",uploadformatError);
			                		return model;
								}								
							}
							
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						String[]  result = null;
						if(sheetsCount==4)
						{
							 result = uploadLASingleSheet(obj,userId,userName);
						}
						else
						{
							result = uploadLA(obj,userId,userName);
						}
						String errMsg = result[0];String actualVal = "";
						int count = 0,row = 0,sheet = 0,subRow = 0;
						List<String> fileFormat = FileFormatModel.getLAFileFormat();
						if(!StringUtils.isEmpty(result[1])){count = Integer.parseInt(result[1]);}
						if(!StringUtils.isEmpty(result[2])){row = Integer.parseInt(result[2]);}
						if(!StringUtils.isEmpty(result[3])){sheet = Integer.parseInt(result[3]);}
						if(!StringUtils.isEmpty(result[4])){subRow = Integer.parseInt(result[4]);}
						//System.out.println(errMsg);
						if(!StringUtils.isEmpty(errMsg)) {
							if(!StringUtils.isEmpty(errMsg) && errMsg.contains("Duplicate entry")) {    
								attributes.addFlashAttribute("error","<span style='color:red;'><i class='fa fa-warning'></i>&nbsp;<b>Work and RR Id Mismatch at row: ("+row+")</b> please check and Upload again.</span>");
								msg = "Work and Utility Shifting Id Mismatch at row: "+row;
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
							}
						
	                		//obj.setUploaded_by_user_id_fk(userId);
	                		//obj.setStatus("Fail");
	                		//obj.setRemarks(msg);
							//boolean flag = service.saveRRDataUploadFile(obj);
	                		return model;
						}
						if(count > 0) {
							attributes.addFlashAttribute("success", count + " Land Acquisition added successfully.");	
							msg = count + " Land Acquisition added successfully.";
							
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
							formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
							formHistory.setModule_name_fk("Land Acquisition");
							formHistory.setForm_name("Upload Land Acquisition");
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
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
		}
		return model;
	}

	
	/**
	 * This method uploadLA() is called when user upload the file
	 * 
	 * @param obj is object for the model class User.
	 * @param userId is type of String it store the userId
	 * @param userName is type of String it store the userName
	 * @param workbook is type of XSSWorkbook variable it takes the workbook as input.
	 * @return type of this method is count.
	 * @throws Exception 
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */

	private String[] uploadLA(LandAcquisition obj, String userId, String userName) throws Exception {
		LandAcquisition la = null;
		List<LandAcquisition> lasList = new ArrayList<LandAcquisition>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getLaUploadFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					
					XSSFSheet laSheet = workbook.getSheetAt(3);
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
						la = new LandAcquisition();
						la.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
						la.setUser_role_code(obj.getUser_role_code());
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { la.setWork_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLa_id(val);}
							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSurvey_number(val);}
							
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { la.setCategory_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLa_sub_category_fk(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(5));
								}
							
								la.setArea_of_plot(val);}	
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(6));
								}
								la.setArea_to_be_acquired(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(7));
								}
								la.setArea_acquired(val);}	
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(8));
								}
								la.setLa_land_status_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_from(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_to(val);}								
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { la.setVillage(val);}										
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { la.setTaluka(val);}
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLatitude(val);}							
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLongitude(val);}
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { la.setDy_slr(val);}							
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSdo(val);}	
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { la.setCollector(val);}
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setProposal_submission_date_to_collector(val);}
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_fee_letter_received_date(val);}
							
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) {
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(20));
								}
								la.setJm_fee_amount(val);
							}
						
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_fee_paid_date(val);}								
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_start_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_completion_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_sheet_date_to_sdo(val);}				
							
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { la.setJm_remarks(val);}										
						
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(26));
								}
								la.setJm_approval(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSpecial_feature(val);}
							
							val = formatter.formatCellValue(row.getCell(28)).trim();
							if(!StringUtils.isEmpty(val)) { la.setRemarks(val);}
							
							val = formatter.formatCellValue(row.getCell(29)).trim();
							if(!StringUtils.isEmpty(val)) { la.setIssues(val);}
					
							la.setProposal_submission_date_to_collector(DateParser.parse(la.getProposal_submission_date_to_collector()));
							la.setJm_fee_letter_received_date(DateParser.parse(la.getJm_fee_letter_received_date()));
							la.setJm_fee_paid_date(DateParser.parse(la.getJm_fee_paid_date()));
							la.setJm_start_date(DateParser.parse(la.getJm_start_date()));
							la.setJm_completion_date(DateParser.parse(la.getJm_completion_date()));
							la.setJm_sheet_date_to_sdo(DateParser.parse(la.getJm_sheet_date_to_sdo()));
						}
				
						List<LandAcquisition> pObjList = new ArrayList<LandAcquisition>();
						List<LandAcquisition> pObjList1 = new ArrayList<LandAcquisition>();
						List<LandAcquisition> pObjList2 = new ArrayList<LandAcquisition>();
						List<LandAcquisition> gObjList = new ArrayList<LandAcquisition>();
						List<LandAcquisition> fObjList = new ArrayList<LandAcquisition>();
						List<LandAcquisition> rObjList = new ArrayList<LandAcquisition>();
						
									XSSFSheet laPrivateSheet = workbook.getSheetAt(3);
									XSSFSheet laPrivateLVSheet = workbook.getSheetAt(4);
									XSSFSheet laprivateLASheet = workbook.getSheetAt(5);
									XSSFSheet GovSheet = workbook.getSheetAt(6);
									XSSFSheet forestSheet = workbook.getSheetAt(7);
									XSSFSheet railwaySheet = workbook.getSheetAt(8);

									XSSFRow privateIRA = laPrivateSheet.getRow(1);
									XSSFRow privateLV = laPrivateLVSheet.getRow(1);
									XSSFRow privateLA = laprivateLASheet.getRow(1);
									XSSFRow Gov = GovSheet.getRow(1);
									XSSFRow forest = forestSheet.getRow(1);
									XSSFRow railway = railwaySheet.getRow(1);
									//String val = null;
									if(privateIRA != null){
										for(int j = 2; j <= laPrivateSheet.getLastRowNum();j++){
											XSSFRow row2 = laPrivateSheet.getRow(j);
											LandAcquisition pObj = new LandAcquisition();
											if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(la.getLa_id())) {
												val = formatter.formatCellValue(row2.getCell(0)).trim();
												if(!StringUtils.isEmpty(val)) { pObj.setLa_id(val);}
												
												val = formatter.formatCellValue(row2.getCell(1)).trim();
												if(!StringUtils.isEmpty(val)) { pObj.setCollector(val);}
												//Declaration of Special Railway project 
												val = formatter.formatCellValue(row2.getCell(2)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setSubmission_of_proposal_to_GM(val);
												}	
												val = formatter.formatCellValue(row2.getCell(3)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setApproval_of_GM(val);
												}
												val = formatter.formatCellValue(row2.getCell(4)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDraft_letter_to_con_for_approval_rp(val);
												}
												val = formatter.formatCellValue(row2.getCell(5)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_approval_of_construction_rp(val);
												}
												val = formatter.formatCellValue(row2.getCell(6)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_uploading_of_gazette_notification_rp(val);
												}
												val = formatter.formatCellValue(row2.getCell(7)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_in_gazette_rp(val);
												}
												//Nomination of competent Authority	
												val = formatter.formatCellValue(row2.getCell(8)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_proposal_to_DC_for_nomination(val);
												}
												val = formatter.formatCellValue(row2.getCell(9)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_nomination_of_competenta_authority(val);
												}
												val = formatter.formatCellValue(row2.getCell(10)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDraft_letter_to_con_for_approval_ca(val);
												}
												val = formatter.formatCellValue(row2.getCell(11)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_approval_of_construction_ca(val);
												}
												val = formatter.formatCellValue(row2.getCell(12)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_uploading_of_gazette_notification_ca(val);
												}
												val = formatter.formatCellValue(row2.getCell(13)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_in_gazette_ca(val);
												}
											//Publication of notification under 20 A 
												val = formatter.formatCellValue(row2.getCell(14)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_submission_of_draft_notification_to_CALA(val);
												}
												val = formatter.formatCellValue(row2.getCell(15)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setApproval_of_CALA_20a(val);
												}
												val = formatter.formatCellValue(row2.getCell(16)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDraft_letter_to_con_for_approval_20a(val);
												}
												val = formatter.formatCellValue(row2.getCell(17)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_approval_of_construction_20a(val);
												}
												val = formatter.formatCellValue(row2.getCell(18)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_uploading_of_gazette_notification_20a(val);
												}
												val = formatter.formatCellValue(row2.getCell(19)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_in_gazette_20a(val);
												}
												val = formatter.formatCellValue(row2.getCell(20)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_in_2_local_news_papers_20a(val);
												}
												val = formatter.formatCellValue(row2.getCell(21)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPasting_of_notification_in_villages_20a(val);
												}
										    //Grievances Redressal	
												val = formatter.formatCellValue(row2.getCell(22)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setReceipt_of_grievances(val);
												}
												val = formatter.formatCellValue(row2.getCell(23)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDisposal_of_grievances(val);
												}
											//Acquisition notice under 20E	
												val = formatter.formatCellValue(row2.getCell(24)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_submission_of_draft_notification_to_CALA_20e(val);
												}
												val = formatter.formatCellValue(row2.getCell(25)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setApproval_of_CALA_20e(val);
												}
												val = formatter.formatCellValue(row2.getCell(26)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDraft_letter_to_con_for_approval_20e(val);
												}
												val = formatter.formatCellValue(row2.getCell(27)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_approval_of_construction_20e(val);
												}
												val = formatter.formatCellValue(row2.getCell(28)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_uploading_of_gazette_notification_20e(val);
												}
												val = formatter.formatCellValue(row2.getCell(29)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_in_gazette_20e(val);
												}
												val = formatter.formatCellValue(row2.getCell(30)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_of_notice_in_2_local_news_papers_20e(val);
												}
											//Acquisition notice under 20F						
												val = formatter.formatCellValue(row2.getCell(31)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_submission_of_draft_notification_to_CALA_20f(val);
												}
												val = formatter.formatCellValue(row2.getCell(32)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setApproval_of_CALA_20f(val);
												}
												val = formatter.formatCellValue(row2.getCell(33)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDraft_letter_to_con_for_approval_20f(val);
												}
												val = formatter.formatCellValue(row2.getCell(34)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_approval_of_construction_20f(val);
												}
												val = formatter.formatCellValue(row2.getCell(35)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setDate_of_uploading_of_gazette_notification_20f(val);
												}
												val = formatter.formatCellValue(row2.getCell(36)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_in_gazette_20f(val);
												}
												val = formatter.formatCellValue(row2.getCell(37)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														
														 
													}
													pObj.setPublication_of_notice_in_2_local_news_papers_20f(val);
												}
											}
											if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getLa_id())
													&& pObj.getLa_id().equals(la.getLa_id())) {
												pObjList.add(pObj);
											}
										}
								la.setPrivateIRAList(pObjList);
								}
								if(privateLA != null){
									int b  = laprivateLASheet.getLastRowNum();
									for(int j = 2; j <= laprivateLASheet.getLastRowNum();j++){
										XSSFRow row2 = laprivateLASheet.getRow(j);
										LandAcquisition pObj1 = new LandAcquisition();
										if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(la.getLa_id())) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setLa_id(val);}
										
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setName_of_the_owner(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(2));
												}
												pObj1.setBasic_rate(val);}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(3));
												}
												pObj1.setHundred_percent_Solatium(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(4));
												}
												pObj1.setExtra_25_percent(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(5));
												}
												
												pObj1.setTotal_rate_divide_m2(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(6));
												}
										
												pObj1.setLand_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(7));
												}
											
												pObj1.setAgriculture_tree_nos(Double.parseDouble(val));}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(8));
												}
											
												pObj1.setAgriculture_tree_rate(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(9));
												}
											
												pObj1.setAgriculture_tree_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(10));
												}
												pObj1.setForest_tree_nos(Double.parseDouble(val));}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(11));
												}
												pObj1.setForest_tree_rate(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(12));
												}
												pObj1.setForest_tree_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(13));
												}
												pObj1.setStructure_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(14));
												}
												pObj1.setBorewell_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(15));
												}
												pObj1.setTotal_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(16)).trim();
											if(!StringUtils.isEmpty(val)) {pObj1.setConsent_from_owner(val);}
											val = formatter.formatCellValue(row2.getCell(17)).trim();
											if(!StringUtils.isEmpty(val)) {pObj1.setLegal_search_report(val);}
											
											val = formatter.formatCellValue(row2.getCell(18)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setDate_of_registration(val);}
											val = formatter.formatCellValue(row2.getCell(19)).trim();
											if(!StringUtils.isEmpty(val)) {pObj1.setDate_of_possession(val);}
											
											val = formatter.formatCellValue(row2.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) {pObj1.setPrivate_possession_status_fk(val);}
							
											
											pObj1.setDate_of_registration(DateParser.parse(pObj1.getDate_of_registration()));
											pObj1.setDate_of_possession(DateParser.parse(pObj1.getDate_of_possession()));
											pObj1.setConsent_from_owner(DateParser.parse(pObj1.getConsent_from_owner()));
											pObj1.setLegal_search_report(DateParser.parse(pObj1.getLegal_search_report()));
											pObj1.setForest_tree_survey(DateParser.parse(pObj1.getForest_tree_survey()));
											pObj1.setForest_tree_valuation(DateParser.parse(pObj1.getForest_tree_valuation()));
											pObj1.setHorticulture_tree_survey(DateParser.parse(pObj1.getHorticulture_tree_survey()));
											pObj1.setHorticulture_tree_valuation(DateParser.parse(pObj1.getHorticulture_tree_valuation()));
											pObj1.setStructure_survey(DateParser.parse(pObj1.getStructure_survey()));
											pObj1.setStructure_valuation(DateParser.parse(pObj1.getStructure_valuation()));
											pObj1.setBorewell_survey(DateParser.parse(pObj1.getBorewell_survey()));
											pObj1.setBorewell_valuation(DateParser.parse(pObj1.getBorewell_valuation()));
											pObj1.setDate_of_rfp_to_adtp(DateParser.parse(pObj1.getDate_of_rfp_to_adtp()));
											pObj1.setDate_of_rate_fixation_of_land(DateParser.parse(pObj1.getDate_of_rate_fixation_of_land()));
											pObj1.setSdo_demand_for_payment(DateParser.parse(pObj1.getSdo_demand_for_payment()));
											pObj1.setDate_of_approval_for_payment(DateParser.parse(pObj1.getDate_of_approval_for_payment()));
											pObj1.setPrivate_payment_date(DateParser.parse(pObj1.getPrivate_payment_date()));
										} 
										if(!StringUtils.isEmpty(pObj1) && !StringUtils.isEmpty(pObj1.getLa_id())
												&& pObj1.getLa_id().equals(la.getLa_id())) {
											pObjList1.add(pObj1);
										}
									}
							la.setPrivateLAList(pObjList1);
							}	
								if(privateLV != null){
									for(int j = 2; j <= laPrivateLVSheet.getLastRowNum();j++){
										XSSFRow row2 = laPrivateLVSheet.getRow(j);
										LandAcquisition pObj2 = new LandAcquisition();
										if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(la.getLa_id())) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setLa_id(val);}
										
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setForest_tree_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setForest_tree_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setHorticulture_tree_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setHorticulture_tree_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setStructure_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setStructure_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setBorewell_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setBorewell_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {pObj2.setDate_of_rfp_to_adtp(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(10));
												}
												pObj2.setRfp_to_adtp_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) {
												
												pObj2.setDate_of_rate_fixation_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setSdo_demand_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setDate_of_approval_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(14));
												}
												pObj2.setPayment_amount(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setPayment_date(val);}
						
											
											pObj2.setForest_tree_survey(DateParser.parse(pObj2.getForest_tree_survey()));
											pObj2.setForest_tree_valuation(DateParser.parse(pObj2.getForest_tree_valuation()));
											pObj2.setHorticulture_tree_survey(DateParser.parse(pObj2.getHorticulture_tree_survey()));
											pObj2.setHorticulture_tree_valuation(DateParser.parse(pObj2.getHorticulture_tree_valuation()));
											pObj2.setStructure_survey(DateParser.parse(pObj2.getStructure_survey()));
											pObj2.setStructure_valuation(DateParser.parse(pObj2.getStructure_valuation()));
											pObj2.setBorewell_survey(DateParser.parse(pObj2.getBorewell_survey()));
											pObj2.setBorewell_valuation(DateParser.parse(pObj2.getBorewell_valuation()));
											pObj2.setDate_of_rfp_to_adtp(DateParser.parse(pObj2.getDate_of_rfp_to_adtp()));
											pObj2.setDate_of_rate_fixation_of_land(DateParser.parse(pObj2.getDate_of_rate_fixation_of_land()));
											pObj2.setSdo_demand_for_payment(DateParser.parse(pObj2.getSdo_demand_for_payment()));
											pObj2.setDate_of_approval_for_payment(DateParser.parse(pObj2.getDate_of_approval_for_payment()));
											pObj2.setPrivate_payment_date(DateParser.parse(pObj2.getPrivate_payment_date()));
											
										}
										if(!StringUtils.isEmpty(pObj2) && !StringUtils.isEmpty(pObj2.getLa_id())
												&& pObj2.getLa_id().equals(la.getLa_id())) {
											pObjList2.add(pObj2);
										}
									}
							la.setPrivateLVList(pObjList2);
							}
								if(Gov != null){
									for(int j = 2; j <= GovSheet.getLastRowNum();j++){
										XSSFRow row2 = GovSheet.getRow(j);
										LandAcquisition gov = new LandAcquisition();
										if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(la.getLa_id())) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setLa_id(val);}
									
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setProposal_submission(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(2));
												}	
												gov.setProposal_submission_status_fk(val);
												
											}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setValuation_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setLetter_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												//System.out.println(val);
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(5));
												}
												
												gov.setAmount_demanded(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(6));
												}
												gov.setLfp_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setApproval_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setPayment_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(9));
												}
												gov.setAmount_paid(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(10));
												}
												gov.setPayment_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setPossession_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(12));
												}
												gov.setPossession_status_fk(val);}
							
											
											gov.setProposal_submission(DateParser.parse(gov.getProposal_submission()));
											gov.setLetter_for_payment(DateParser.parse(gov.getLetter_for_payment()));
											gov.setApproval_for_payment(DateParser.parse(gov.getApproval_for_payment()));
											gov.setPayment_date(DateParser.parse(gov.getPayment_date()));
											gov.setPossession_date(DateParser.parse(gov.getPossession_date()));
											gov.setValuation_date(DateParser.parse(gov.getValuation_date()));
										}
										if(!StringUtils.isEmpty(gov) && !StringUtils.isEmpty(gov.getLa_id())
												&& gov.getLa_id().equals(la.getLa_id())) {
											gObjList.add(gov);
										}
									}
							la.setGovList(gObjList);
							}
								if(forest != null){
									for(int j = 2; j <= forestSheet.getLastRowNum();j++){
										XSSFRow row2 = forestSheet.getRow(j);
										LandAcquisition fObj = new LandAcquisition();
										if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(la.getLa_id())) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setForest_online_submission(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_submission_date_to_dycfo(val);}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_submission_date_to_ccf_thane(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { 	fObj.setForest_submission_date_to_nodal_officer(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_submission_date_to_revenue_secretary_mantralaya(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_submission_date_to_regional_office_nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_date_of_approval_by_regional_office_nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_valuation_by_dycfo(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(9));
												}
												fObj.setForest_demanded_amount(val);}

											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setApproval_for_payment(val);}

											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setForest_payment_date(val);}

											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(12));
												}
										
												fObj.setForest_payment_amount(val);}

											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(13));
												}
												fObj.setForest_payment_status_fk(val);}

											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_possession_date(val);}

											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(15));
												}
												fObj.setForest_possession_status_fk(val);}

											
											fObj.setForest_online_submission(DateParser.parse(fObj.getForest_online_submission()));
											fObj.setForest_submission_date_to_dycfo(DateParser.parse(fObj.getForest_submission_date_to_dycfo()));
											fObj.setForest_submission_date_to_ccf_thane(DateParser.parse(fObj.getForest_submission_date_to_ccf_thane()));
											fObj.setForest_submission_date_to_nodal_officer(DateParser.parse(fObj.getForest_submission_date_to_nodal_officer()));
											fObj.setForest_submission_date_to_revenue_secretary_mantralaya(DateParser.parse(fObj.getForest_submission_date_to_revenue_secretary_mantralaya()));
											fObj.setForest_submission_date_to_regional_office_nagpur(DateParser.parse(fObj.getForest_submission_date_to_regional_office_nagpur()));
											fObj.setForest_date_of_approval_by_regional_office_nagpur(DateParser.parse(fObj.getForest_date_of_approval_by_regional_office_nagpur()));
											fObj.setForest_valuation_by_dycfo(DateParser.parse(fObj.getForest_valuation_by_dycfo()));
											fObj.setForest_approval_for_payment(DateParser.parse(fObj.getForest_approval_for_payment()));
											fObj.setForest_payment_date(DateParser.parse(fObj.getForest_payment_date()));
											fObj.setForest_possession_date(DateParser.parse(fObj.getForest_possession_date()));
										}
										if(!StringUtils.isEmpty(fObj) && !StringUtils.isEmpty(fObj.getLa_id())
												&& fObj.getLa_id().equals(la.getLa_id())) {
											fObjList.add(fObj);
										}
									}
							la.setForestList(fObjList);
							}
								if(railway != null){
									for(int j = 2; j <= railwaySheet.getLastRowNum();j++){
										XSSFRow row2 = railwaySheet.getRow(j);
										LandAcquisition railways = new LandAcquisition();
										if(!StringUtils.isEmpty(row2) && formatter.formatCellValue(row2.getCell(0)).trim().equals(la.getLa_id())) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_online_submission(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_submission_date_to_DyCFO(val);}
											
											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_submission_date_to_CCF_Thane(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_submission_date_to_nodal_officer_CCF_Nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_submission_date_to_revenue_secretary_mantralaya(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_submission_date_to_regional_office_nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_date_of_approval_by_Rregional_Office_agpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_valuation_by_DyCFO(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_demanded_amount(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setRailway_approval_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_payment_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_payment_amount(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(13));
												}
												railways.setRailway_payment_status(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setRailway_possession_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) { 
												int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
												if(c != 2) {
													val = getCellDataType(workbook,row2.getCell(15));
												}
												railways.setRailway_possession_status(val);}
											
											railways.setRailway_online_submission(DateParser.parse(railways.getRailway_online_submission()));
											railways.setRailway_submission_date_to_DyCFO(DateParser.parse(railways.getRailway_submission_date_to_DyCFO()));
											railways.setRailway_submission_date_to_CCF_Thane(DateParser.parse(railways.getRailway_submission_date_to_CCF_Thane()));
											railways.setRailway_submission_date_to_nodal_officer_CCF_Nagpur(DateParser.parse(railways.getRailway_submission_date_to_nodal_officer_CCF_Nagpur()));
											railways.setRailway_submission_date_to_revenue_secretary_mantralaya(DateParser.parse(railways.getRailway_submission_date_to_revenue_secretary_mantralaya()));
											railways.setRailway_submission_date_to_regional_office_nagpur(DateParser.parse(railways.getRailway_submission_date_to_regional_office_nagpur()));
											railways.setRailway_date_of_approval_by_Rregional_Office_agpur(DateParser.parse(railways.getRailway_date_of_approval_by_Rregional_Office_agpur()));
											railways.setRailway_valuation_by_DyCFO(DateParser.parse(railways.getRailway_valuation_by_DyCFO()));
											railways.setRailway_approval_for_payment(DateParser.parse(railways.getRailway_approval_for_payment()));
											railways.setRailway_payment_date(DateParser.parse(railways.getRailway_payment_date()));
											railways.setRailway_possession_date(DateParser.parse(railways.getRailway_possession_date()));
										}
										if(!StringUtils.isEmpty(railways) && !StringUtils.isEmpty(railways.getLa_id())
												&& railways.getLa_id().equals(la.getLa_id())) {
											rObjList.add(railways);
										}
								
									}
							la.setRailwayList(rObjList);
							}
						boolean flag = la.checkNullOrEmpty();

						if(!flag && !StringUtils.isEmpty(la.getLa_id())) {
							lasList.add(la);
						}

					}
					if(!lasList.isEmpty() && lasList != null){
						//count  = laService.uploadLAs(lasList);
						String[] arr  = service.uploadLAData(lasList,la);
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
			logger.error("uploadLAs() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadLAs() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return result;
	}
	
	private String[] uploadLASingleSheet(LandAcquisition obj, String userId, String userName) throws Exception {
		LandAcquisition la = null;
		List<LandAcquisition> lasList = new ArrayList<LandAcquisition>();
		String[] result = new String[5];
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getLaUploadFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					
					XSSFSheet laSheet = workbook.getSheetAt(3);
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
						la = new LandAcquisition();
						la.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
						la.setUser_role_code(obj.getUser_role_code());
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { la.setWork_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSurvey_number(val);}
							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { la.setCategory_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLa_sub_category_fk(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(4));
								}
							
								la.setArea_of_plot(val);}	
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(5));
								}
								la.setArea_to_be_acquired(val);}	
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(6));
								}
								la.setArea_acquired(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(7));
								}
								la.setLa_land_status_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_from(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_to(val);}								
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { la.setVillage(val);}										
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { la.setTaluka(val);}
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLatitude(val);}							
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLongitude(val);}
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { la.setDy_slr(val);}							
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSdo(val);}	
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { la.setCollector(val);}
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setProposal_submission_date_to_collector(val);}
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_fee_letter_received_date(val);}
							
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) {
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(19));
								}
								la.setJm_fee_amount(val);
							}
						
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_fee_paid_date(val);}								
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_start_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_completion_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									
									 
								}
								la.setJm_sheet_date_to_sdo(val);}				
							
							val = formatter.formatCellValue(row.getCell(24)).trim();
							if(!StringUtils.isEmpty(val)) { la.setJm_remarks(val);}										
						
							val = formatter.formatCellValue(row.getCell(25)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(25));
								}
								la.setJm_approval(val);}
							
							val = formatter.formatCellValue(row.getCell(26)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSpecial_feature(val);}
							
							val = formatter.formatCellValue(row.getCell(27)).trim();
							if(!StringUtils.isEmpty(val)) { la.setRemarks(val);}
							
							val = formatter.formatCellValue(row.getCell(28)).trim();
							if(!StringUtils.isEmpty(val)) { la.setIssues(val);}
					
							la.setProposal_submission_date_to_collector(DateParser.parse(la.getProposal_submission_date_to_collector()));
							la.setJm_fee_letter_received_date(DateParser.parse(la.getJm_fee_letter_received_date()));
							la.setJm_fee_paid_date(DateParser.parse(la.getJm_fee_paid_date()));
							la.setJm_start_date(DateParser.parse(la.getJm_start_date()));
							la.setJm_completion_date(DateParser.parse(la.getJm_completion_date()));
							la.setJm_sheet_date_to_sdo(DateParser.parse(la.getJm_sheet_date_to_sdo()));
						}





						boolean flag = la.checkNullOrEmpty();

						//if(!flag && !StringUtils.isEmpty(la.getLa_id())) {
							lasList.add(la);
						//}

					}
					if(!lasList.isEmpty() && lasList != null){
						//count  = laService.uploadLAs(lasList);
						String[] arr  = service.uploadLAData(lasList,la);
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
			logger.error("uploadLAs() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadLAs() : "+e.getMessage());
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
	
	
	@RequestMapping(value = "/export-land-acquisition", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportLandAcquisition(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute LandAcquisition dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.landAcquisition);
		List<LandAcquisition> dataList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> privateIRAList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> privateValList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> privateLandList = new ArrayList<LandAcquisition>();
		
		List<LandAcquisition> govList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> forestList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> railwayList = new ArrayList<LandAcquisition>();
		try {
			
			User uObj = (User) session.getAttribute("user");
			dObj.setUser_type_fk(uObj.getUser_type_fk());
			dObj.setUser_role_code(uObj.getUser_role_code());
			dObj.setUser_id(uObj.getUser_id());
			
			view.setViewName("redirect:/land-acquisition");
			dataList =   service.getLandAcquisitionList(dObj);
		   
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet Landsheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Land Acquisition"));
				XSSFSheet privateIRASheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Private (Indian Railway Act)"));
				XSSFSheet privateValSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Private Land valuation"));
				XSSFSheet privateLandSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Private Land Acquisition"));
				XSSFSheet govSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Government Land Acquisition"));
				XSSFSheet forestSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Forest Land Acquisition"));
				XSSFSheet railwaySheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Railway Land Aquisition"));
				
		        workBook.setSheetOrder(Landsheet.getSheetName(), 0);
				workBook.setSheetOrder(privateIRASheet.getSheetName(), 1);
				workBook.setSheetOrder(privateValSheet.getSheetName(), 2);
				workBook.setSheetOrder(privateLandSheet.getSheetName(), 3);
				workBook.setSheetOrder(govSheet.getSheetName(), 4);
				workBook.setSheetOrder(forestSheet.getSheetName(), 5);
				workBook.setSheetOrder(railwaySheet.getSheetName(), 6);
		        
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
		        
		        
		        
	            XSSFRow headingRow = Landsheet.createRow(0);
	            String headerString = "Work ID^LA_ID^Survey Number^Type of Land^Sub Category of Land^Area^Area to be Acquired^Area Acquired^Land Status^Chainage From"
	            		+ "^Chainage To^Village^Taluka^Latitude^Longitude^Dy SLR^SDO^Collector^Proposal submission Date to collector^JM Fee Letter received Date^JM Fee Amount^JM Fee Paid Date^"
	            		+ "JM Start Date^JM Completion Date^JM Sheet Date to SDO^JM Remarks^JM Approval^Special Feature^Remarks^Issues";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				XSSFRow headingRow1 = privateIRASheet.createRow(0);
	            String headerString1 = "LA_ID^Collector^Submission of Proposal to GM.^Approval of GM^Draft Letter to CE/Con for Approval^"
	            		+ "Date of Approval of CE/Construction^Date of Uploading of  Gazette notification^Publication in gazette^Date of Proposal to DC for nomination^"
	            		+ "Date of Nomination of competent Authority.^Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^"
	            		+ "Date of Uploading of  Gazette notification^Publication in gazette^Date of Submission of draft notification to CALA^Approval of CALA^"
	            		+ "Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^Date of Uploading of  Gazette notification^Publication in gazette^"
	            		+ "Publication in 2 Local Newspapers^Pasting of notification in villages^Receipt of Grievances^Disposal of Grievances^"
	            		+ "Date of Submission of draft notification to CALA^Approval of CALA^Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^"
	            		+ "Date of Uploading of  Gazette notification^Publication in gazette^Publication of notice in 2 Local News papers ^"
	            		+ "Date of Submission of draft notification to CALA^Approval of CALA^Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^"
	            		+ "Date of Uploading of  Gazette notification^Publication in gazette^Publication of notice in 2 Local News papers";
	            
	            String[] secondHeaderStringArr = headerString1.split("\\^");
	            
	            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow1.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(secondHeaderStringArr[i]);
				}
				
				XSSFRow headingRow2 = privateValSheet.createRow(0);
	            String headerString2 = "LA_ID^Forest Tree Survey^Forest Tree Valuation^Horticulture Tree Survey^Horticulture Tree Valuation^Structure Survey^"
	            		+ "Structure Valuation^Borewell Survey^Borewell Valuation^Date of RFP to ADTP^Date of Rate Fixation of Land^"
	            		+ "SDO demand for payment^Date of Approval for Payment^Payment Amount^Payment Date";
	            
	            String[] thirdHeaderStringArr = headerString2.split("\\^");
	            
	            for (int i = 0; i < thirdHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow2.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(thirdHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow3 = privateLandSheet.createRow(0);
	            String headerString3 = "LA_ID^Name of Owner^Basic Rate^100% Solatium^Extra 25%^Total Rate/m2^Land Compensation^Agriculture tree nos^"
	            		+ "Agriculture tree rate^Agriculture tree compensation^Forest tree nos^Forest tree rate^Forest tree compensation^Structure compensation^"
	            		+ "Borewell compensation^Total Compensation^Consent from Owner^Legal Search Report^Date of Registration^Date of Possession";
	            
	            String[] fourthHeaderStringArr = headerString3.split("\\^");
	            
	            for (int i = 0; i < fourthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow3.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fourthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow4 = govSheet.createRow(0);
	            String headerString4 = "LA_ID^Proposal Submission^Valuation Date^Letter for Payment^Amount Demanded^"
	            		+ "Approval for Payment^Payment date^Amount Paid^Possession Date";
	            
	            String[] fifthHeaderStringArr = headerString4.split("\\^");
	            
	            for (int i = 0; i < fifthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow4.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fifthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow5 = forestSheet.createRow(0);
	            String headerString5 = "LA_ID^On line Submission^Submission Date to DyCFO^Submission Date to CCF Thane^Submission Date to Nodal Officer/CCF Nagpur^"
	            		+ "Submission Date to Revenue Secretary Mantralaya^Submission Date to Regional Office Nagpur^Date of Approval by Regional Office Nagpur^"
	            		+ "Valuation by DyCFO^Demanded Amount^Approval for Payment^Payment Date^Payment Amount^Possession Date";
	            
	            String[] sixthHeaderStringArr = headerString5.split("\\^");
	            
	            for (int i = 0; i < sixthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow5.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(sixthHeaderStringArr[i]);
				}
	            
	            XSSFRow headingRow6 = railwaySheet.createRow(0);
	            String headerString6 = "LA_ID^On line Submission^Submission Date to DyCFO^Submission Date to CCF Thane^Submission Date to Nodal Officer/CCF Nagpur^"
	            		+ "Submission Date to Revenue Secretary Mantralaya^Submission Date to Regional Office Nagpur^Date of Approval by Regional Office Nagpur^"
	            		+ "Valuation by DyCFO^Demanded Amount^Approval for Payment^Payment Date^Payment Amount^Possession Date";
	            
	            String[] seventhHeaderStringArr = headerString6.split("\\^");
	            
	            for (int i = 0; i < seventhHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow6.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(seventhHeaderStringArr[i]);
				}
	            
			short rowNo3 = 1;
        	for (LandAcquisition privateIRA : dataList) { 
        		String la_id = privateIRA.getLa_id();
        		privateIRAList = service.geprivateIRAList(la_id);
					/*	if(privateIRAList.size()< 1) {
							 int a = 0;
							XSSFRow row = privateIRASheet.createRow(rowNo3);
							for(int k = 0;k < secondHeaderStringArr.length;k++) {
								Cell cell2 = row.createCell(a++);
								cell2.setCellStyle(whiteStyle);
								cell2.setCellValue("No Data");
							}
							privateIRASheet.addMergedRegion(new CellRangeAddress(1,1,0,(secondHeaderStringArr.length - 1 )));
							break;
						}*/
				
				 for (LandAcquisition obj : privateIRAList) {
	                XSSFRow row = privateIRASheet.createRow(rowNo3);
	                int a = 0;
	                
	                Cell cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getLa_id_fk());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPrivate_ira_collector());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getSubmission_of_proposal_to_GM());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_GM());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_rp());					
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_rp());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_rp());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_rp());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_proposal_to_DC_for_nomination());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_nomination_of_competenta_authority());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_ca());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_ca());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_ca());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_ca());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_submission_of_draft_notification_to_CALA());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_CALA_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_20a());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_20a());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_2_local_news_papers_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPasting_of_notification_in_villages_20a());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getReceipt_of_grievances());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDisposal_of_grievances());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_submission_of_draft_notification_to_CALA_20e());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_CALA_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_20e());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_20e());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_of_notice_in_2_local_news_papers_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_submission_of_draft_notification_to_CALA_20f());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_CALA_20f());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_20f());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_20f());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_20f());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_20f());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_of_notice_in_2_local_news_papers_20f());
					
					rowNo3++;
				 }
			 }
			 
	         short rowNo2 = 1;
			 for (LandAcquisition privateVal : dataList) { 
				String la_id = privateVal.getLa_id();
				privateValList = service.getPrivateValList(la_id);
					/*		if(privateValList.size()< 1) {
					int a = 0;
					XSSFRow row = privateValSheet.createRow(rowNo2);
								for(int k = 0;k < thirdHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								privateValSheet.addMergedRegion(new CellRangeAddress(1,1,0,(thirdHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : privateValList) {
		                XSSFRow row = privateValSheet.createRow(rowNo2);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_survey());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getHorticulture_tree_survey());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getHorticulture_tree_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStructure_survey());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStructure_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBorewell_survey());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBorewell_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_rfp_to_adtp());

					
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_rate_fixation_of_land());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getSdo_demand_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_approval_for_payment());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPayment_amount());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPrivate_payment_date());
						
						rowNo2++;
				    }
		       }
			 
			 short rowNo4 = 1;
			 for (LandAcquisition privateLand : dataList) { 
				String la_id = privateLand.getLa_id();
				privateLandList = service.getPrivateLandList(la_id);
					/*		if(privateLandList.size()< 1) {
					int a = 0;
					XSSFRow row = privateLandSheet.createRow(rowNo4);
								for(int k = 0;k < fourthHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								privateLandSheet.addMergedRegion(new CellRangeAddress(1,1,0,(fourthHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : privateLandList) {
		                XSSFRow row = privateLandSheet.createRow(rowNo4);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getName_of_the_owner());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBasic_rate());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getHundred_percent_Solatium());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getExtra_25_percent());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getTotal_rate_divide_m2());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLand_compensation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(String.valueOf(obj.getAgriculture_tree_nos()));

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAgriculture_tree_rate());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAgriculture_tree_compensation());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(String.valueOf(obj.getForest_tree_nos()));
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_rate());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_compensation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStructure_compensation());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBorewell_compensation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getTotal_compensation());
						
						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getConsent_from_owner());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLegal_search_report());
						
						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_registration());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_possession());

						rowNo4++;
				    }
		       }
			 
			 short rowNo5 = 1;
			 for (LandAcquisition gov : dataList) { 
				String la_id = gov.getLa_id();
				govList = service.getGovList(la_id);
					/*		if(govList.size()< 1) {
					int a = 0;
					XSSFRow row = govSheet.createRow(rowNo5);
								for(int k = 0;k < fifthHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								govSheet.addMergedRegion(new CellRangeAddress(1,1,0,(fifthHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : govList) {
		                XSSFRow row = govSheet.createRow(rowNo5);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getProposal_submission());
						
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getValuation_date());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLetter_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAmount_demanded());
						
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getApproval_for_payment());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPayment_date());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAmount_paid());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPossession_date());

						rowNo5++;
				    }
		       }
			 
			 short rowNo6 = 1;
			 for (LandAcquisition forest : dataList) { 
				String la_id = forest.getLa_id();
				forestList = service.getForestList(la_id);
					/*		if(forestList.size()< 1) {
					int a = 0;
					XSSFRow row = forestSheet.createRow(rowNo6);
								for(int k = 0;k < sixthHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								forestSheet.addMergedRegion(new CellRangeAddress(1,1,0,(sixthHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : forestList) {
		                XSSFRow row = forestSheet.createRow(rowNo6);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_online_submission());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_dycfo());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_ccf_thane());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_nodal_officer());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_revenue_secretary_mantralaya());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_regional_office_nagpur());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_date_of_approval_by_regional_office_nagpur());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_valuation_by_dycfo());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_demanded_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_approval_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_payment_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_payment_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_possession_date());

						rowNo6++;
				    }
		       }
			 
			 short rowNo7 = 1;
			 for (LandAcquisition railway : dataList) { 
				String la_id = railway.getLa_id();
				railwayList = service.getRailwayList(la_id);
					/*		if(railwayList.size()< 1) {
					int a = 0;
					XSSFRow row = railwaySheet.createRow(rowNo7);
								for(int k = 0;k < seventhHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								railwaySheet.addMergedRegion(new CellRangeAddress(1,1,0,(seventhHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : railwayList) {
		                XSSFRow row = railwaySheet.createRow(rowNo7);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_online_submission());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_DyCFO());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_CCF_Thane());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_nodal_officer_CCF_Nagpur());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_revenue_secretary_mantralaya());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_regional_office_nagpur());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_date_of_approval_by_Rregional_Office_agpur());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_valuation_by_DyCFO());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_demanded_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_approval_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_payment_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_payment_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_possession_date());

						rowNo7++;
				    }
		       }
	           short rowNo = 1;
	           for (LandAcquisition obj : dataList) {
					/*	   if(dataList.size()< 1) {
							    int a = 0;
							    XSSFRow row = Landsheet.createRow(rowNo);
								for(int k = 0;k < firstHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								Landsheet.addMergedRegion(new CellRangeAddress(1,1,0,(firstHeaderStringArr.length - 1 )));
								break;
							}*/
	                XSSFRow row = Landsheet.createRow(rowNo);
	                int c = 0;
	               
	                Cell cell = row.createCell(c++);
	                cell.setCellStyle(sectionStyle);
	                cell.setCellValue(obj.getWork_id_fk() + " - "+obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLa_id());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSurvey_number());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getType_of_land());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSub_category_of_land());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_of_plot());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_to_be_acquired());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_acquired());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLa_land_status_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getChainage_from());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getChainage_to());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getVillage());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTaluka());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLatitude());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLongitude());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDy_slr());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSdo());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCollector());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProposal_submission_date_to_collector());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_fee_letter_received_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_fee_amount());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_fee_paid_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_start_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_completion_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_sheet_date_to_sdo());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_remarks());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_approval());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSpecial_feature());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIssues());
					
	                rowNo++;
	            }
				
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		Landsheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 38; columnIndex++) {
	        		privateIRASheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex <16; columnIndex++) {
	        		privateValSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 21; columnIndex++) {
	        		privateLandSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 21; columnIndex++) {
	        		govSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 16; columnIndex++) {
	        		forestSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 16; columnIndex++) {
	        		railwaySheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	            Date date = new Date();
	            String fileName = "Land_Aquisition"+dateFormat.format(date);
	            
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
	
	@RequestMapping(value = "/export-land", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportLand(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute LandAcquisition dObj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.landAcquisition);
		List<LandAcquisition> dataList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> privateIRAList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> privateValList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> privateLandList = new ArrayList<LandAcquisition>();
		
		List<LandAcquisition> govList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> forestList = new ArrayList<LandAcquisition>();
		List<LandAcquisition> railwayList = new ArrayList<LandAcquisition>();
		try {
			
			User uObj = (User) session.getAttribute("user");
			dObj.setUser_type_fk(uObj.getUser_type_fk());
			dObj.setUser_role_code(uObj.getUser_role_code());
			dObj.setUser_id(uObj.getUser_id());
			
			view.setViewName("redirect:/land-acquisition");
			dataList =   service.getLandAcquisitionList(dObj);
		   
			if(dataList != null && dataList.size() > 0){
	            XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet Instruction = workBook.createSheet(WorkbookUtil.createSafeSheetName("Instruction"));
	            XSSFSheet Working = workBook.createSheet(WorkbookUtil.createSafeSheetName("Working"));
	            XSSFSheet Index = workBook.createSheet(WorkbookUtil.createSafeSheetName("Index"));

	            
	            XSSFSheet Landsheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Land Acquisition"));
				XSSFSheet privateIRASheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Private (Indian Railway Act)"));
				XSSFSheet privateValSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Private Land valuation"));
				XSSFSheet privateLandSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Private Land Acquisition"));
				XSSFSheet govSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Government Land Acquisition"));
				XSSFSheet forestSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Forest Land Acquisition"));
				XSSFSheet railwaySheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Railway Land Aquisition"));
				
				 workBook.setSheetOrder(Instruction.getSheetName(), 0);
				 workBook.setSheetOrder(Index.getSheetName(), 1);
				 workBook.setSheetOrder(Working.getSheetName(), 2);
		        workBook.setSheetOrder(Landsheet.getSheetName(), 3);
				workBook.setSheetOrder(privateIRASheet.getSheetName(), 4);
				workBook.setSheetOrder(privateValSheet.getSheetName(), 5);
				workBook.setSheetOrder(privateLandSheet.getSheetName(), 6);
				workBook.setSheetOrder(govSheet.getSheetName(), 7);
				workBook.setSheetOrder(forestSheet.getSheetName(), 8);
				workBook.setSheetOrder(railwaySheet.getSheetName(), 9);
				
	            workBook.setSheetHidden(1, true);
	            workBook.setSheetHidden(2, true);
				
				
				CellStyle lockedStyle = workBook.createCellStyle();
				lockedStyle.setLocked(true); 

		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,true,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,false,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        
		        XSSFRow headingInstructionRow = Instruction.createRow(0);
		        Cell cell5 = headingInstructionRow.createCell(0);
		        Instruction.addMergedRegion(new CellRangeAddress(0,0,0,3));
		        cell5.setCellStyle(yellowStyle);
				cell5.setCellValue("STEPS:  highlighted fields in yellow are to be filled Compulsory. (i) Add work ID at INPUT 1. (ii) Add Unique land ID at INPUT 2. (iii) Fill in type of land at INPUT 3. (iv). Enter Area to be acquired in Hectare at INPUT 4. (v). Enter Acquired Area in Hectare at INPUT 5.   (vi). Fill in land status at INPUT 6  (vii). Fill in Chainage from in meters at INPUT 7. (viii). Fill in Chainage To in meters at INPUT 8. (ix). Fill in Village Name at INPUT 9. (x). Fill in Taluka Name at INPUT 10.");
	            String headerInstructionString = "SN^Sheet Name^Column Name^Description";
	            String[] firstInstrHeaderStringArr = headerInstructionString.split("\\^");
	            
	            XSSFRow headingInstrRowHead = Instruction.createRow(1);
	            
	            for (int i = 0; i < firstInstrHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingInstrRowHead.createCell(i);
			        cell.setCellStyle(yellowStyle);
					cell.setCellValue(firstInstrHeaderStringArr[i]);
				}
		        
	            XSSFRow headingInstrRow1 = Instruction.createRow(2);
	            
	        	Cell cell11 = headingInstrRow1.createCell(0);
		        cell11.setCellStyle(whiteStyle);
				cell11.setCellValue(1);
				
	        	Cell cell21 = headingInstrRow1.createCell(1);
		        cell21.setCellStyle(whiteStyle);
				cell21.setCellValue("Land Identification");
				
	        	Cell cell3 = headingInstrRow1.createCell(2);
		        cell3.setCellStyle(whiteStyle);
				cell3.setCellValue("Work ID");
				
	        	Cell cell4 = headingInstrRow1.createCell(3);
		        cell4.setCellStyle(whiteStyle);
				cell4.setCellValue("INPUT 1 : Work ID along with work name");				
	            
	            XSSFRow headingInstrRow2 = Instruction.createRow(3);
	            
	        	Cell cell51 = headingInstrRow2.createCell(0);
	        	cell51.setCellStyle(whiteStyle);
	        	cell51.setCellValue(2);
				
	        	Cell cell6 = headingInstrRow2.createCell(1);
	        	cell6.setCellStyle(whiteStyle);
	        	cell6.setCellValue("Land Identification");
				
	        	Cell cell7 = headingInstrRow2.createCell(2);
	        	cell7.setCellStyle(whiteStyle);
	        	cell7.setCellValue("Survey Number");
				
	        	Cell cell8 = headingInstrRow2.createCell(3);
	        	cell8.setCellStyle(whiteStyle);
	        	cell8.setCellValue("Survey Number");
				
				
	            XSSFRow headingInstrRow3 = Instruction.createRow(4);
	            
	        	Cell cell9 = headingInstrRow3.createCell(0);
	        	cell9.setCellStyle(whiteStyle);
	        	cell9.setCellValue(3);
				
	        	Cell cell10 = headingInstrRow3.createCell(1);
	        	cell10.setCellStyle(whiteStyle);
	        	cell10.setCellValue("Land Identification");
				
	        	Cell cell111 = headingInstrRow3.createCell(2);
	        	cell111.setCellStyle(whiteStyle);
	        	cell111.setCellValue("Type of Land");
				
	        	Cell cell12 = headingInstrRow3.createCell(3);
	        	cell12.setCellStyle(whiteStyle);
	        	cell12.setCellValue("INPUT 3: Type of land     \r\n" + 
	        			"(i). Private\r\n" + 
	        			"(ii). Government\r\n" + 
	        			"(iii). Forest\r\n" + 
	        			"(iv). Railway");	            
	            
	            XSSFRow headingInstrRow4 = Instruction.createRow(5);
	            
	        	Cell cell13 = headingInstrRow4.createCell(0);
	        	cell13.setCellStyle(whiteStyle);
	        	cell13.setCellValue(4);
				
	        	Cell cell14 = headingInstrRow4.createCell(1);
	        	cell14.setCellStyle(whiteStyle);
	        	cell14.setCellValue("Land Identification");
				
	        	Cell cell15 = headingInstrRow4.createCell(2);
	        	cell15.setCellStyle(whiteStyle);
	        	cell15.setCellValue("Sub Category of Land");
				
	        	Cell cell16 = headingInstrRow4.createCell(3);
	        	cell16.setCellStyle(whiteStyle);
	        	cell16.setCellValue("Sub Category of Land");	            
	            
	            
	            XSSFRow headingInstrRow5 = Instruction.createRow(6);
	            
	        	Cell cell17 = headingInstrRow5.createCell(0);
	        	cell17.setCellStyle(whiteStyle);
	        	cell17.setCellValue(5);
				
	        	Cell cell18 = headingInstrRow5.createCell(1);
	        	cell18.setCellStyle(whiteStyle);
	        	cell18.setCellValue("Land Identification");
				
	        	Cell cell19 = headingInstrRow5.createCell(2);
	        	cell19.setCellStyle(whiteStyle);
	        	cell19.setCellValue("Area (Ha.)");
				
	        	Cell cell20 = headingInstrRow5.createCell(3);
	        	cell20.setCellStyle(whiteStyle);
	        	cell20.setCellValue("Total Area of Plot (Ha.) ");	            
	            
	            XSSFRow headingInstrRow6 = Instruction.createRow(7);
	            
	        	Cell cell211 = headingInstrRow6.createCell(0);
	        	cell211.setCellStyle(whiteStyle);
	        	cell211.setCellValue(6);
				
	        	Cell cell22 = headingInstrRow6.createCell(1);
	        	cell22.setCellStyle(whiteStyle);
	        	cell22.setCellValue("Land Identification");
				
	        	Cell cell23 = headingInstrRow6.createCell(2);
	        	cell23.setCellStyle(whiteStyle);
	        	cell23.setCellValue("Area to be Acquired (Ha.)");
				
	        	Cell cell24 = headingInstrRow6.createCell(3);
	        	cell24.setCellStyle(whiteStyle);
	        	cell24.setCellValue("INPUT 4: \r\n" + 
	        			"Total Area to be Acquired (Ha.)");	            
	            
	            XSSFRow headingInstrRow7 = Instruction.createRow(8);
	            
	        	Cell cell25 = headingInstrRow7.createCell(0);
	        	cell25.setCellStyle(whiteStyle);
	        	cell25.setCellValue(7);
				
	        	Cell cell26 = headingInstrRow7.createCell(1);
	        	cell26.setCellStyle(whiteStyle);
	        	cell26.setCellValue("Land Identification");
				
	        	Cell cell27 = headingInstrRow7.createCell(2);
	        	cell27.setCellStyle(whiteStyle);
	        	cell27.setCellValue("Area Acquired (Ha.)");
				
	        	Cell cell28 = headingInstrRow7.createCell(3);
	        	cell28.setCellStyle(whiteStyle);
	        	cell28.setCellValue("INPUT 5: \r\n" + 
	        			"Area Acquired (Ha.)\r\n" + 
	        			" If Land status is \"Acquired\", then it is treated as \"Area to be Acquired\", else \"enter acquired area\".");		            
	            
	            XSSFRow headingInstrRow8 = Instruction.createRow(9);
	            
	        	Cell cell29 = headingInstrRow8.createCell(0);
	        	cell29.setCellStyle(whiteStyle);
	        	cell29.setCellValue(8);
				
	        	Cell cell30 = headingInstrRow8.createCell(1);
	        	cell30.setCellStyle(whiteStyle);
	        	cell30.setCellValue("Land Identification");
				
	        	Cell cell31 = headingInstrRow8.createCell(2);
	        	cell31.setCellStyle(whiteStyle);
	        	cell31.setCellValue("Land Status");
				
	        	Cell cell32 = headingInstrRow8.createCell(3);
	        	cell32.setCellStyle(whiteStyle);
	        	cell32.setCellValue("INPUT 6: \r\n" + 
	        			"(i). Acquired\r\n" + 
	        			"(ii). Yet to be Acquired\r\n" + 
	        			"(iii). Forest Clearance Required");	            
	            
	            
	            XSSFRow headingInstrRow9 = Instruction.createRow(10);
	            
	        	Cell cell33 = headingInstrRow9.createCell(0);
	        	cell33.setCellStyle(whiteStyle);
	        	cell33.setCellValue(9);
				
	        	Cell cell34 = headingInstrRow9.createCell(1);
	        	cell34.setCellStyle(whiteStyle);
	        	cell34.setCellValue("Land Identification");
				
	        	Cell cell35 = headingInstrRow9.createCell(2);
	        	cell35.setCellStyle(whiteStyle);
	        	cell35.setCellValue("Chainage From");
				
	        	Cell cell36 = headingInstrRow9.createCell(3);
	        	cell36.setCellStyle(whiteStyle);
	        	cell36.setCellValue("INPUT 7: \r\n" + 
	        			"Chainage from (in Meter.)");	            
	            
	            XSSFRow headingInstrRow10 = Instruction.createRow(11);
	            
	        	Cell cell37 = headingInstrRow10.createCell(0);
	        	cell37.setCellStyle(whiteStyle);
	        	cell37.setCellValue(10);
				
	        	Cell cell38 = headingInstrRow10.createCell(1);
	        	cell38.setCellStyle(whiteStyle);
	        	cell38.setCellValue("Land Identification");
				
	        	Cell cell39 = headingInstrRow10.createCell(2);
	        	cell39.setCellStyle(whiteStyle);
	        	cell39.setCellValue("Chainage To");
				
	        	Cell cell40 = headingInstrRow10.createCell(3);
	        	cell40.setCellStyle(whiteStyle);
	        	cell40.setCellValue("INPUT 8: \r\n" + 
	        			"Chainage To (in Meter.)");	            
	            
	            
	            XSSFRow headingInstrRow11 = Instruction.createRow(12);
	            
	        	Cell cell41 = headingInstrRow10.createCell(0);
	        	cell41.setCellStyle(whiteStyle);
	        	cell41.setCellValue(11);
				
	        	Cell cell42 = headingInstrRow10.createCell(1);
	        	cell42.setCellStyle(whiteStyle);
	        	cell42.setCellValue("Land Identification");
				
	        	Cell cell43 = headingInstrRow10.createCell(2);
	        	cell43.setCellStyle(whiteStyle);
	        	cell43.setCellValue("Village");
				
	        	Cell cell44 = headingInstrRow10.createCell(3);
	        	cell44.setCellStyle(whiteStyle);
	        	cell44.setCellValue("INPUT 9: \r\n" + 
	        			"Village Name");			            
	            
	            XSSFRow headingInstrRow12 = Instruction.createRow(13);
	            
	        	Cell cell45 = headingInstrRow12.createCell(0);
	        	cell45.setCellStyle(whiteStyle);
	        	cell45.setCellValue(12);
				
	        	Cell cell46 = headingInstrRow12.createCell(1);
	        	cell46.setCellStyle(whiteStyle);
	        	cell46.setCellValue("Land Identification");
				
	        	Cell cell47 = headingInstrRow12.createCell(2);
	        	cell47.setCellStyle(whiteStyle);
	        	cell47.setCellValue("Taluka");
				
	        	Cell cell48 = headingInstrRow12.createCell(3);
	        	cell48.setCellStyle(whiteStyle);
	        	cell48.setCellValue("INPUT 10: \r\n" + 
	        			"Taluka Name");		            
	            
	            
	            XSSFRow headingInstrRow13 = Instruction.createRow(14);
	            
	        	Cell cell49 = headingInstrRow13.createCell(0);
	        	cell49.setCellStyle(whiteStyle);
	        	cell49.setCellValue(13);
				
	        	Cell cell50 = headingInstrRow13.createCell(1);
	        	cell50.setCellStyle(whiteStyle);
	        	cell50.setCellValue("Land Identification");
				
	        	Cell cell511 = headingInstrRow13.createCell(2);
	        	cell511.setCellStyle(whiteStyle);
	        	cell511.setCellValue("Latitude");
				
	        	Cell cell52 = headingInstrRow13.createCell(3);
	        	cell52.setCellStyle(whiteStyle);
	        	cell52.setCellValue("Latitude of Chainage");		            
	            
	            XSSFRow headingInstrRow14 = Instruction.createRow(15);
	            
	        	Cell cell53 = headingInstrRow14.createCell(0);
	        	cell53.setCellStyle(whiteStyle);
	        	cell53.setCellValue(14);
				
	        	Cell cell54 = headingInstrRow14.createCell(1);
	        	cell54.setCellStyle(whiteStyle);
	        	cell54.setCellValue("Land Identification");
				
	        	Cell cell55 = headingInstrRow14.createCell(2);
	        	cell55.setCellStyle(whiteStyle);
	        	cell55.setCellValue("Longitude");
				
	        	Cell cell56 = headingInstrRow14.createCell(3);
	        	cell56.setCellStyle(whiteStyle);
	        	cell56.setCellValue("Longitude of Chainage");	            
	            
	            XSSFRow headingInstrRow15 = Instruction.createRow(16);
	            
	        	Cell cell57 = headingInstrRow15.createCell(0);
	        	cell57.setCellStyle(whiteStyle);
	        	cell57.setCellValue(15);
				
	        	Cell cell58 = headingInstrRow15.createCell(1);
	        	cell58.setCellStyle(whiteStyle);
	        	cell58.setCellValue("Land Identification");
				
	        	Cell cell59 = headingInstrRow15.createCell(2);
	        	cell59.setCellStyle(whiteStyle);
	        	cell59.setCellValue("Dy SLR");
				
	        	Cell cell60 = headingInstrRow15.createCell(3);
	        	cell60.setCellStyle(whiteStyle);
	        	cell60.setCellValue("Dy SLR");			            
	            
	            XSSFRow headingInstrRow16 = Instruction.createRow(17);
	            
	        	Cell cell61 = headingInstrRow16.createCell(0);
	        	cell61.setCellStyle(whiteStyle);
	        	cell61.setCellValue(16);
				
	        	Cell cell62 = headingInstrRow16.createCell(1);
	        	cell62.setCellStyle(whiteStyle);
	        	cell62.setCellValue("Land Identification");
				
	        	Cell cell63 = headingInstrRow16.createCell(2);
	        	cell63.setCellStyle(whiteStyle);
	        	cell63.setCellValue("SDO");
				
	        	Cell cell64 = headingInstrRow16.createCell(3);
	        	cell64.setCellStyle(whiteStyle);
	        	cell64.setCellValue("SDO");
				
				
	            XSSFRow headingInstrRow17 = Instruction.createRow(18);
	            
	        	Cell cell65 = headingInstrRow17.createCell(0);
	        	cell65.setCellStyle(whiteStyle);
	        	cell65.setCellValue(17);
				
	        	Cell cell66 = headingInstrRow17.createCell(1);
	        	cell66.setCellStyle(whiteStyle);
	        	cell66.setCellValue("Land Identification");
				
	        	Cell cell67 = headingInstrRow17.createCell(2);
	        	cell67.setCellStyle(whiteStyle);
	        	cell67.setCellValue("Collector");
				
	        	Cell cell68 = headingInstrRow17.createCell(3);
	        	cell68.setCellStyle(whiteStyle);
	        	cell68.setCellValue("Collector");            
	            
	            XSSFRow headingInstrRow18 = Instruction.createRow(19);
	            
	        	Cell cell69 = headingInstrRow17.createCell(0);
	        	cell69.setCellStyle(whiteStyle);
	        	cell69.setCellValue(17);
				
	        	Cell cell70 = headingInstrRow17.createCell(1);
	        	cell70.setCellStyle(whiteStyle);
	        	cell70.setCellValue("Land Identification");
				
	        	Cell cell71 = headingInstrRow17.createCell(2);
	        	cell71.setCellStyle(whiteStyle);
	        	cell71.setCellValue("Proposal submission Date to collector");
				
	        	Cell cell72 = headingInstrRow17.createCell(3);
	        	cell72.setCellStyle(whiteStyle);
	        	cell72.setCellValue("Date of Proposal submission Date to collector 'DD/MM/YYYY'");               
	            
	            XSSFRow headingInstrRow19 = Instruction.createRow(20);
	            
	        	Cell cell73 = headingInstrRow19.createCell(0);
	        	cell73.setCellStyle(whiteStyle);
	        	cell73.setCellValue(19);
				
	        	Cell cell74 = headingInstrRow19.createCell(1);
	        	cell74.setCellStyle(whiteStyle);
	        	cell74.setCellValue("Land Identification");
				
	        	Cell cell75 = headingInstrRow19.createCell(2);
	        	cell75.setCellStyle(whiteStyle);
	        	cell75.setCellValue("JM Fee Letter received Date");
				
	        	Cell cell76 = headingInstrRow19.createCell(3);
	        	cell76.setCellStyle(whiteStyle);
	        	cell76.setCellValue("Date of JM Fee Letter received Date 'DD/MM/YYYY'");   	            
	            
	            
	            XSSFRow headingInstrRow20 = Instruction.createRow(21);
	            
	        	Cell cell77 = headingInstrRow20.createCell(0);
	        	cell77.setCellStyle(whiteStyle);
	        	cell77.setCellValue(20);
				
	        	Cell cell78 = headingInstrRow20.createCell(1);
	        	cell78.setCellStyle(whiteStyle);
	        	cell78.setCellValue("Land Identification");
				
	        	Cell cell79 = headingInstrRow20.createCell(2);
	        	cell79.setCellStyle(whiteStyle);
	        	cell79.setCellValue("JM Fee Amount");
				
	        	Cell cell80 = headingInstrRow20.createCell(3);
	        	cell80.setCellStyle(whiteStyle);
	        	cell80.setCellValue("Joint Measurement Fee amount (Rs)");   	            
	            
	            XSSFRow headingInstrRow21 = Instruction.createRow(22);
	            
	        	Cell cell81 = headingInstrRow21.createCell(0);
	        	cell81.setCellStyle(whiteStyle);
	        	cell81.setCellValue(21);
				
	        	Cell cell82 = headingInstrRow21.createCell(1);
	        	cell82.setCellStyle(whiteStyle);
	        	cell82.setCellValue("Land Identification");
				
	        	Cell cell83 = headingInstrRow21.createCell(2);
	        	cell83.setCellStyle(whiteStyle);
	        	cell83.setCellValue("JM Fee Paid Date");
				
	        	Cell cell84 = headingInstrRow21.createCell(3);
	        	cell84.setCellStyle(whiteStyle);
	        	cell84.setCellValue("Joint Measurement Fee Paid date 'DD/MM/YYYY'");   	            
	            
	            XSSFRow headingInstrRow22 = Instruction.createRow(23);
	            
	        	Cell cell85 = headingInstrRow22.createCell(0);
	        	cell85.setCellStyle(whiteStyle);
	        	cell85.setCellValue(22);
				
	        	Cell cell86 = headingInstrRow22.createCell(1);
	        	cell86.setCellStyle(whiteStyle);
	        	cell86.setCellValue("Land Identification");
				
	        	Cell cell87 = headingInstrRow22.createCell(2);
	        	cell87.setCellStyle(whiteStyle);
	        	cell87.setCellValue("JM Start Date");
				
	        	Cell cell88 = headingInstrRow22.createCell(3);
	        	cell88.setCellStyle(whiteStyle);
	        	cell88.setCellValue("Joint Measurement Start date 'DD/MM/YYYY'");   		            
	            
	            
	            XSSFRow headingInstrRow23 = Instruction.createRow(24);
	            
	        	Cell cell89 = headingInstrRow23.createCell(0);
	        	cell89.setCellStyle(whiteStyle);
	        	cell89.setCellValue(23);
				
	        	Cell cell90 = headingInstrRow23.createCell(1);
	        	cell90.setCellStyle(whiteStyle);
	        	cell90.setCellValue("Land Identification");
				
	        	Cell cell91 = headingInstrRow23.createCell(2);
	        	cell91.setCellStyle(whiteStyle);
	        	cell91.setCellValue("JM Completion Date");
				
	        	Cell cell92 = headingInstrRow23.createCell(3);
	        	cell92.setCellStyle(whiteStyle);
	        	cell92.setCellValue("Joint Measurement Completion date 'DD/MM/YYYY'");   		            
	            
	            
	            XSSFRow headingInstrRow24 = Instruction.createRow(25);
	            
	        	Cell cell93 = headingInstrRow24.createCell(0);
	        	cell93.setCellStyle(whiteStyle);
	        	cell93.setCellValue(24);
				
	        	Cell cell94 = headingInstrRow24.createCell(1);
	        	cell94.setCellStyle(whiteStyle);
	        	cell94.setCellValue("Land Identification");
				
	        	Cell cell95 = headingInstrRow24.createCell(2);
	        	cell95.setCellStyle(whiteStyle);
	        	cell95.setCellValue("JM Sheet Date to SDO");
				
	        	Cell cell96 = headingInstrRow24.createCell(3);
	        	cell96.setCellStyle(whiteStyle);
	        	cell96.setCellValue("Joint Measurement sheet to SDO date 'DD/MM/YYYY'");   		            
	            
	            XSSFRow headingInstrRow25 = Instruction.createRow(26);
	            
	        	Cell cell97 = headingInstrRow17.createCell(0);
	        	cell97.setCellStyle(whiteStyle);
	        	cell97.setCellValue(25);
				
	        	Cell cell98 = headingInstrRow25.createCell(1);
	        	cell98.setCellStyle(whiteStyle);
	        	cell98.setCellValue("Land Identification");
				
	        	Cell cell99 = headingInstrRow25.createCell(2);
	        	cell99.setCellStyle(whiteStyle);
	        	cell99.setCellValue("JM Remarks");
				
	        	Cell cell100 = headingInstrRow25.createCell(3);
	        	cell100.setCellStyle(whiteStyle);
	        	cell100.setCellValue("Joint Measurement Remarks");   
				
	            XSSFRow headingInstrRow26 = Instruction.createRow(27);
	            
	        	Cell cell101 = headingInstrRow26.createCell(0);
	        	cell101.setCellStyle(whiteStyle);
	        	cell101.setCellValue(26);
				
	        	Cell cell102 = headingInstrRow26.createCell(1);
	        	cell102.setCellStyle(whiteStyle);
	        	cell102.setCellValue("Land Identification");
				
	        	Cell cell103 = headingInstrRow26.createCell(2);
	        	cell103.setCellStyle(whiteStyle);
	        	cell103.setCellValue("JM Approval");
				
	        	Cell cell104 = headingInstrRow26.createCell(3);
	        	cell104.setCellStyle(whiteStyle);
	        	cell104.setCellValue("JM Approval\r\n" + 
	        			"(i). Accept\r\n" + 
	        			"(ii). Reject");   
				
				
	            XSSFRow headingInstrRow27 = Instruction.createRow(28);
	            
	        	Cell cell105 = headingInstrRow27.createCell(0);
	        	cell105.setCellStyle(whiteStyle);
	        	cell105.setCellValue(27);
				
	        	Cell cell106 = headingInstrRow27.createCell(1);
	        	cell106.setCellStyle(whiteStyle);
	        	cell106.setCellValue("Land Identification");
				
	        	Cell cell107 = headingInstrRow27.createCell(2);
	        	cell107.setCellStyle(whiteStyle);
	        	cell107.setCellValue("Special Feature");
				
	        	Cell cell108 = headingInstrRow27.createCell(3);
	        	cell108.setCellStyle(whiteStyle);
	        	cell108.setCellValue("Special Feature");   	            
	            
	            XSSFRow headingInstrRow28 = Instruction.createRow(29);
	            
	        	Cell cell109 = headingInstrRow28.createCell(0);
	        	cell109.setCellStyle(whiteStyle);
	        	cell109.setCellValue(28);
				
	        	Cell cell110 = headingInstrRow28.createCell(1);
	        	cell110.setCellStyle(whiteStyle);
	        	cell110.setCellValue("Land Identification");
				
	        	Cell cell112 = headingInstrRow28.createCell(2);
	        	cell112.setCellStyle(whiteStyle);
	        	cell112.setCellValue("Remarks");
				
	        	Cell cell113 = headingInstrRow28.createCell(3);
	        	cell113.setCellStyle(whiteStyle);
	        	cell113.setCellValue("Remarks");
	        	
	            XSSFRow headingInstrRow29 = Instruction.createRow(30);
	            
	        	Cell cell114 = headingInstrRow28.createCell(0);
	        	cell114.setCellStyle(whiteStyle);
	        	cell114.setCellValue(29);
				
	        	Cell cell115 = headingInstrRow28.createCell(1);
	        	cell115.setCellStyle(whiteStyle);
	        	cell115.setCellValue("Land Identification");
				
	        	Cell cell116 = headingInstrRow28.createCell(2);
	        	cell116.setCellStyle(whiteStyle);
	        	cell116.setCellValue("Issues");
				
	        	Cell cell117 = headingInstrRow28.createCell(3);
	        	cell117.setCellStyle(whiteStyle);
	        	cell117.setCellValue("Issues in Land Acquisition"); 	        	
            
		        
		        
	            XSSFRow headingRow = Landsheet.createRow(1);
	            String headerString = "Work ID^LA_ID^Survey Number^Type of Land^Sub Category of Land^Area^Area to be Acquired^Area Acquired^Land Status^Chainage From^Chainage To^Village^Taluka^Latitude^Longitude^Dy SLR^SDO^Collector^Proposal submission Date to collector^JM Fee Letter received Date^JM Fee Amount^JM Fee Paid Date^JM Start Date^JM Completion Date^JM Sheet Date to SDO^JM Remarks^JM Approval^Special Feature^Remarks^Issues";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            
				XSSFRow headingRow1 = privateIRASheet.createRow(1);
	            String headerString1 = "LA_ID^Collector^Submission of Proposal to GM.^Approval of GM^Draft Letter to CE/Con for Approval^"
	            		+ "Date of Approval of CE/Construction^Date of Uploading of  Gazette notification^Publication in gazette^Date of Proposal to DC for nomination^"
	            		+ "Date of Nomination of competent Authority.^Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^"
	            		+ "Date of Uploading of  Gazette notification^Publication in gazette^Date of Submission of draft notification to CALA^Approval of CALA^"
	            		+ "Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^Date of Uploading of  Gazette notification^Publication in gazette^"
	            		+ "Publication in 2 Local Newspapers^Pasting of notification in villages^Receipt of Grievances^Disposal of Grievances^"
	            		+ "Date of Submission of draft notification to CALA^Approval of CALA^Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^"
	            		+ "Date of Uploading of  Gazette notification^Publication in gazette^Publication of notice in 2 Local News papers ^"
	            		+ "Date of Submission of draft notification to CALA^Approval of CALA^Draft Letter to CE/Con for Approval^Date of Approval of CE/Construction^"
	            		+ "Date of Uploading of  Gazette notification^Publication in gazette^Publication of notice in 2 Local News papers";
	            
	            String[] secondHeaderStringArr = headerString1.split("\\^");
	            
	            for (int i = 0; i < secondHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow1.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(secondHeaderStringArr[i]);
				}
				
				XSSFRow headingRow2 = privateValSheet.createRow(1);
	            String headerString2 = "LA_ID^Forest Tree Survey^Forest Tree Valuation^Horticulture Tree Survey^Horticulture Tree Valuation^Structure Survey^"
	            		+ "Structure Valuation^Borewell Survey^Borewell Valuation^Date of RFP to ADTP^Date of Rate Fixation of Land^"
	            		+ "SDO demand for payment^Date of Approval for Payment^Payment Amount^Payment Date";
	            
	            String[] thirdHeaderStringArr = headerString2.split("\\^");
	            
	            for (int i = 0; i < thirdHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow2.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(thirdHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow3 = privateLandSheet.createRow(1);
	            String headerString3 = "LA_ID^Name of Owner^Basic Rate^100% Solatium^Extra 25%^Total Rate/m2^Land Compensation^Agriculture tree nos^"
	            		+ "Agriculture tree rate^Agriculture tree compensation^Forest tree nos^Forest tree rate^Forest tree compensation^Structure compensation^"
	            		+ "Borewell compensation^Total Compensation^Consent from Owner^Legal Search Report^Date of Registration^Date of Possession";
	            
	            String[] fourthHeaderStringArr = headerString3.split("\\^");
	            
	            for (int i = 0; i < fourthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow3.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fourthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow4 = govSheet.createRow(1);
	            String headerString4 = "LA_ID^Proposal Submission^Valuation Date^Letter for Payment^Amount Demanded^"
	            		+ "Approval for Payment^Payment date^Amount Paid^Possession Date";
	            
	            String[] fifthHeaderStringArr = headerString4.split("\\^");
	            
	            for (int i = 0; i < fifthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow4.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fifthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow5 = forestSheet.createRow(1);
	            String headerString5 = "LA_ID^On line Submission^Submission Date to DyCFO^Submission Date to CCF Thane^Submission Date to Nodal Officer/CCF Nagpur^"
	            		+ "Submission Date to Revenue Secretary Mantralaya^Submission Date to Regional Office Nagpur^Date of Approval by Regional Office Nagpur^"
	            		+ "Valuation by DyCFO^Demanded Amount^Approval for Payment^Payment Date^Payment Amount^Possession Date";
	            
	            String[] sixthHeaderStringArr = headerString5.split("\\^");
	            
	            for (int i = 0; i < sixthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow5.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(sixthHeaderStringArr[i]);
				}
	            
	            XSSFRow headingRow6 = railwaySheet.createRow(1);
	            String headerString6 = "LA_ID^On line Submission^Submission Date to DyCFO^Submission Date to CCF Thane^Submission Date to Nodal Officer/CCF Nagpur^"
	            		+ "Submission Date to Revenue Secretary Mantralaya^Submission Date to Regional Office Nagpur^Date of Approval by Regional Office Nagpur^"
	            		+ "Valuation by DyCFO^Demanded Amount^Approval for Payment^Payment Date^Payment Amount^Possession Date";
	            
	            String[] seventhHeaderStringArr = headerString6.split("\\^");
	            
	            for (int i = 0; i < seventhHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow6.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(seventhHeaderStringArr[i]);
				}
	            
			short rowNo3 = 2;
        	for (LandAcquisition privateIRA : dataList) { 
        		String la_id = privateIRA.getLa_id();
        		privateIRAList = service.geprivateIRAList(la_id);
					/*	if(privateIRAList.size()< 1) {
							 int a = 0;
							XSSFRow row = privateIRASheet.createRow(rowNo3);
							for(int k = 0;k < secondHeaderStringArr.length;k++) {
								Cell cell2 = row.createCell(a++);
								cell2.setCellStyle(whiteStyle);
								cell2.setCellValue("No Data");
							}
							privateIRASheet.addMergedRegion(new CellRangeAddress(1,1,0,(secondHeaderStringArr.length - 1 )));
							break;
						}*/
				
				 for (LandAcquisition obj : privateIRAList) {
	                XSSFRow row = privateIRASheet.createRow(rowNo3);
	                int a = 0;
	                
	                Cell cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getLa_id_fk());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPrivate_ira_collector());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getSubmission_of_proposal_to_GM());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_GM());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_rp());					
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_rp());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_rp());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_rp());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_proposal_to_DC_for_nomination());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_nomination_of_competenta_authority());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_ca());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_ca());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_ca());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_ca());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_submission_of_draft_notification_to_CALA());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_CALA_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_20a());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_20a());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_2_local_news_papers_20a());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPasting_of_notification_in_villages_20a());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getReceipt_of_grievances());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDisposal_of_grievances());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_submission_of_draft_notification_to_CALA_20e());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_CALA_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_20e());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_20e());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_of_notice_in_2_local_news_papers_20e());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_submission_of_draft_notification_to_CALA_20f());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getApproval_of_CALA_20f());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDraft_letter_to_con_for_approval_20f());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_approval_of_construction_20f());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getDate_of_uploading_of_gazette_notification_20f());
					
	                cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_in_gazette_20f());
					
					cell2 = row.createCell(a++);
					cell2.setCellStyle(sectionStyle);
					cell2.setCellValue(obj.getPublication_of_notice_in_2_local_news_papers_20f());
					
					rowNo3++;
				 }
			 }
			 
	         short rowNo2 = 2;
			 for (LandAcquisition privateVal : dataList) { 
				String la_id = privateVal.getLa_id();
				privateValList = service.getPrivateValList(la_id);
					/*		if(privateValList.size()< 1) {
					int a = 0;
					XSSFRow row = privateValSheet.createRow(rowNo2);
								for(int k = 0;k < thirdHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								privateValSheet.addMergedRegion(new CellRangeAddress(1,1,0,(thirdHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : privateValList) {
		                XSSFRow row = privateValSheet.createRow(rowNo2);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_survey());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getHorticulture_tree_survey());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getHorticulture_tree_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStructure_survey());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStructure_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBorewell_survey());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBorewell_valuation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_rfp_to_adtp());

					
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_rate_fixation_of_land());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getSdo_demand_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_approval_for_payment());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPayment_amount());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPrivate_payment_date());
						
						rowNo2++;
				    }
		       }
			 
			 short rowNo4 = 2;
			 for (LandAcquisition privateLand : dataList) { 
				String la_id = privateLand.getLa_id();
				privateLandList = service.getPrivateLandList(la_id);
					/*		if(privateLandList.size()< 1) {
					int a = 0;
					XSSFRow row = privateLandSheet.createRow(rowNo4);
								for(int k = 0;k < fourthHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								privateLandSheet.addMergedRegion(new CellRangeAddress(1,1,0,(fourthHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : privateLandList) {
		                XSSFRow row = privateLandSheet.createRow(rowNo4);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getName_of_the_owner());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBasic_rate());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getHundred_percent_Solatium());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getExtra_25_percent());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getTotal_rate_divide_m2());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLand_compensation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(String.valueOf(obj.getAgriculture_tree_nos()));

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAgriculture_tree_rate());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAgriculture_tree_compensation());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(String.valueOf(obj.getForest_tree_nos()));
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_rate());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_compensation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getStructure_compensation());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getBorewell_compensation());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getTotal_compensation());
						
						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getConsent_from_owner());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLegal_search_report());
						
						cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_registration());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getDate_of_possession());

						rowNo4++;
				    }
		       }
			 
			 short rowNo5 = 2;
			 for (LandAcquisition gov : dataList) { 
				String la_id = gov.getLa_id();
				govList = service.getGovList(la_id);
					/*		if(govList.size()< 1) {
					int a = 0;
					XSSFRow row = govSheet.createRow(rowNo5);
								for(int k = 0;k < fifthHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								govSheet.addMergedRegion(new CellRangeAddress(1,1,0,(fifthHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : govList) {
		                XSSFRow row = govSheet.createRow(rowNo5);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getProposal_submission());
						
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getValuation_date());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLetter_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAmount_demanded());
						
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getApproval_for_payment());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPayment_date());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAmount_paid());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPossession_date());

						rowNo5++;
				    }
		       }
			 
			 short rowNo6 = 2;
			 for (LandAcquisition forest : dataList) { 
				String la_id = forest.getLa_id();
				forestList = service.getForestList(la_id);
					/*		if(forestList.size()< 1) {
					int a = 0;
					XSSFRow row = forestSheet.createRow(rowNo6);
								for(int k = 0;k < sixthHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								forestSheet.addMergedRegion(new CellRangeAddress(1,1,0,(sixthHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : forestList) {
		                XSSFRow row = forestSheet.createRow(rowNo6);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_online_submission());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_dycfo());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_ccf_thane());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_nodal_officer());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_revenue_secretary_mantralaya());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_submission_date_to_regional_office_nagpur());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_date_of_approval_by_regional_office_nagpur());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_valuation_by_dycfo());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_demanded_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_approval_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_payment_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_payment_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_possession_date());

						rowNo6++;
				    }
		       }
			 
			 short rowNo7 = 2;
			 for (LandAcquisition railway : dataList) { 
				String la_id = railway.getLa_id();
				railwayList = service.getRailwayList(la_id);
					/*		if(railwayList.size()< 1) {
					int a = 0;
					XSSFRow row = railwaySheet.createRow(rowNo7);
								for(int k = 0;k < seventhHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								railwaySheet.addMergedRegion(new CellRangeAddress(1,1,0,(seventhHeaderStringArr.length - 1 )));
								break;
							}*/
				 for (LandAcquisition obj : railwayList) {
		                XSSFRow row = railwaySheet.createRow(rowNo7);
		                int b = 0;
		                
		                Cell cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getLa_id_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_online_submission());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_DyCFO());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_CCF_Thane());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_nodal_officer_CCF_Nagpur());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_revenue_secretary_mantralaya());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_submission_date_to_regional_office_nagpur());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_date_of_approval_by_Rregional_Office_agpur());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_valuation_by_DyCFO());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_demanded_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_approval_for_payment());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_payment_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_payment_amount());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_possession_date());

						rowNo7++;
				    }
		       }
	           short rowNo = 2;
	           for (LandAcquisition obj : dataList) {
					/*	   if(dataList.size()< 1) {
							    int a = 0;
							    XSSFRow row = Landsheet.createRow(rowNo);
								for(int k = 0;k < firstHeaderStringArr.length;k++) {
									Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue("No Data");
								}
								Landsheet.addMergedRegion(new CellRangeAddress(1,1,0,(firstHeaderStringArr.length - 1 )));
								break;
							}*/
	                XSSFRow row = Landsheet.createRow(rowNo);
	                int c = 0;
	               
	                Cell cell = row.createCell(c++);
	                cell.setCellStyle(sectionStyle);
	                cell.setCellValue(obj.getWork_id_fk() + " - "+obj.getWork_short_name());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLa_id());
					cell.setCellStyle(lockedStyle);

					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSurvey_number());
					
	                cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getType_of_land());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSub_category_of_land());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_of_plot());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_to_be_acquired());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getArea_acquired());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLa_land_status_fk());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getChainage_from());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getChainage_to());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getVillage());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getTaluka());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLatitude());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getLongitude());					
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getDy_slr());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSdo());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getCollector());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getProposal_submission_date_to_collector());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_fee_letter_received_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_fee_amount());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_fee_paid_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_start_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_completion_date());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_sheet_date_to_sdo());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_remarks());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getJm_approval());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getSpecial_feature());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getRemarks());
					
					cell = row.createCell(c++);
					cell.setCellStyle(sectionStyle);
					cell.setCellValue(obj.getIssues());
					
	                rowNo++;
	            }
	           
	        	for(int columnIndex = 0; columnIndex < 29; columnIndex++) {
	        		Landsheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 38; columnIndex++) {
	        		privateIRASheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex <16; columnIndex++) {
	        		privateValSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 21; columnIndex++) {
	        		privateLandSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 21; columnIndex++) {
	        		govSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 16; columnIndex++) {
	        		forestSheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        	for(int columnIndex = 0; columnIndex < 16; columnIndex++) {
	        		railwaySheet.setColumnWidth(columnIndex, 25 * 200);
				}
	        
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	            Date date = new Date();
	            String fileName = "Land_Aquisition"+dateFormat.format(date);
	            
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
