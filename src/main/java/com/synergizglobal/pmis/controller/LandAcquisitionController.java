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
						XSSFSheet laSheet = workbook.getSheetAt(2);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = laSheet.getRow(1);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getLAFileFormat();	
							int noOfColumns = headerRow.getLastCellNum();
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
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						
						String[]  result = uploadLA(obj,userId,userName);
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
					
					XSSFSheet laSheet = workbook.getSheetAt(2);
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
							if(!StringUtils.isEmpty(val)) { la.setVillage_id(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(6));
								}
							
								la.setArea_of_plot(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(7));
								}
								la.setArea_to_be_acquired(val);}	
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(8));
								}
								la.setArea_acquired(val);}	
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { 
								int c = org.apache.commons.lang3.StringUtils.countMatches(val, "$");
								if(c != 2) {
									val = getCellDataType(workbook,row.getCell(9));
								}
								la.setLa_land_status_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_from(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_to(val);}								
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { la.setVillage(val);}										
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { la.setTaluka(val);}
							
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
											
												pObj1.setAgriculture_tree_nos(val);}
											
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
												pObj1.setForest_tree_nos(val);}
											
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
	            String headerString = "Work ID^LA_ID^Survey Number^Type of Land^Sub Category of Land^Village ID^Area^Area to be Acquired^Area Acquired^Land Status^Chainage From"
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
	            		+ "Structure Valuation^Borewell Survey^Borewell Valuation^Date of RFP to ADTP^RFP to ADTP status^Date of Rate Fixation of Land^"
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
	            		+ "Borewell compensation^Total Compensation^Consent from Owner^Legal Search Report^Date of Registration^Date of Possession^Possession Status";
	            
	            String[] fourthHeaderStringArr = headerString3.split("\\^");
	            
	            for (int i = 0; i < fourthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow3.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fourthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow4 = govSheet.createRow(0);
	            String headerString4 = "LA_ID^Proposal Submission^Proposal Submission Status^Valuation Date^Letter for Payment^Amount Demanded^LFP Status^"
	            		+ "Approval for Payment^Payment date^Amount Paid^Payment Status^Possession Date^Possession Status";
	            
	            String[] fifthHeaderStringArr = headerString4.split("\\^");
	            
	            for (int i = 0; i < fifthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow4.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(fifthHeaderStringArr[i]);
				}
	            
	        	XSSFRow headingRow5 = forestSheet.createRow(0);
	            String headerString5 = "LA_ID^On line Submission^Submission Date to DyCFO^Submission Date to CCF Thane^Submission Date to Nodal Officer/CCF Nagpur^"
	            		+ "Submission Date to Revenue Secretary Mantralaya^Submission Date to Regional Office Nagpur^Date of Approval by Regional Office Nagpur^"
	            		+ "Valuation by DyCFO^Demanded Amount^Approval for Payment^Payment Date^Payment Amount^Payment Status^Possession Date^Possession Status";
	            
	            String[] sixthHeaderStringArr = headerString5.split("\\^");
	            
	            for (int i = 0; i < sixthHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow5.createCell(i);
			        cell.setCellStyle(greenStyle);
					cell.setCellValue(sixthHeaderStringArr[i]);
				}
	            
	            XSSFRow headingRow6 = railwaySheet.createRow(0);
	            String headerString6 = "LA_ID^On line Submission^Submission Date to DyCFO^Submission Date to CCF Thane^Submission Date to Nodal Officer/CCF Nagpur^"
	            		+ "Submission Date to Revenue Secretary Mantralaya^Submission Date to Regional Office Nagpur^Date of Approval by Regional Office Nagpur^"
	            		+ "Valuation by DyCFO^Demanded Amount^Approval for Payment^Payment Date^Payment Amount^Payment Status^Possession Date^Possession Status";
	            
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
						cell1.setCellValue(obj.getRfp_to_adtp_status_fk());
						
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
						cell1.setCellValue(obj.getAgriculture_tree_nos());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAgriculture_tree_rate());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getAgriculture_tree_compensation());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_tree_nos());
						
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
						
						 cell1 = row.createCell(b++);
							cell1.setCellStyle(sectionStyle);
							cell1.setCellValue(obj.getPrivate_possession_status_fk());
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
						cell1.setCellValue(obj.getProposal_submission_status_fk());
						
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
						cell1.setCellValue(obj.getLfp_status_fk());
						
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
						cell1.setCellValue(obj.getPayment_status_fk());
						
		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPossession_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getPossession_status_fk());
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
						cell1.setCellValue(obj.getForest_payment_status_fk());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_possession_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getForest_possession_status_fk());

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
						cell1.setCellValue(obj.getRailway_payment_status());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_possession_date());

		                cell1 = row.createCell(b++);
						cell1.setCellStyle(sectionStyle);
						cell1.setCellValue(obj.getRailway_possession_status());

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
					cell.setCellValue(obj.getVillage_id());
					
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
