package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.LandAcquisitionService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.LandAquisationPaginationObject;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.TAFinancials;
import com.synergizglobal.pmis.model.Training;

@Controller
public class LandAcquisitionController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LandAcquisitionController.class);
	
	@Autowired
	LandAcquisitionService service;
	
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
				dataList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				dataList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//lasList = getListBasedOnSearchParameter(searchParameter,lasList);

			int totalRecords = getTotalRecords(obj, searchParameter);

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
	public int getTotalRecords(LandAcquisition obj, String searchParameter) {
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
	public List<LandAcquisition> createPaginationData(int startIndex, int offset, LandAcquisition obj, String searchParameter) {
		List<LandAcquisition> earthWorkList = null;
		try {
			earthWorkList = service.getLandAcquisitionList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getProjectsList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> projectsList = null;
		try {
			projectsList = service.getLandAcquisitionProjectsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsList : " + e.getMessage());
		}
		return projectsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getWorksList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> worksList = null;
		try {
			worksList = service.getLandAcquisitionWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getVillagesFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getVillagesList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> villagesList = null;
		try {
			villagesList = service.getLandAcquisitionVillagesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getVillagesList : " + e.getMessage());
		}
		return villagesList;
	}
	
	@RequestMapping(value = "/ajax/getTypesOfLandsFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getTypesOfLandsList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> typesOfLandsList = null;
		try {
			typesOfLandsList = service.getLandAcquisitionTypesOfLandsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTypesOfLandsList : " + e.getMessage());
		}
		return typesOfLandsList;
	}
	
	@RequestMapping(value = "/ajax/getSubCategoryFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getSubCategorysList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> subCategoryList = null;
		try {
			subCategoryList = service.getLandAcquisitionSubCategoryList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubCategorysList : " + e.getMessage());
		}
		return subCategoryList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForLAForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getWorkListForLAForm(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> objsList = null;
		try {
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
	
	@RequestMapping(value = "/add-land-acquisition-form", method = {RequestMethod.GET})
	public ModelAndView addLandAcquisitionForm(@ModelAttribute LandAcquisition obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.landAcquisitionForm);
			model.addObject("action", "add");
			List<LandAcquisition> statusList = service.getStatusList();
			model.addObject("statusList", statusList);
			
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
	public ModelAndView getLandAcquisitionForm(@ModelAttribute LandAcquisition obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.landAcquisitionForm);
			model.addObject("action", "edit");
			
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
	
	@RequestMapping(value = "/add-land-acquisition", method = {RequestMethod.POST})
	public ModelAndView addLandAcquisition(@ModelAttribute LandAcquisition obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/land-acquisition");
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
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/land-acquisition");
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
		String userId = null;String userName = null;
		String msg = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/land-acquisition");
			
			if(!StringUtils.isEmpty(obj.getLaUploadFile())){
				MultipartFile multipartFile = obj.getLaUploadFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						int sheetNo = 0;
						if(sheetsCount > 7) {sheetNo = 2;}
						XSSFSheet laSheet = workbook.getSheetAt(sheetNo);
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
						
						int count = uploadLA(obj,userId,userName);
						if(count > 0) {
							attributes.addFlashAttribute("success", count + " Land Acquisition added successfully.");	
							msg = count + " Land Acquisition added successfully.";
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

	private int uploadLA(LandAcquisition obj, String userId, String userName) throws Exception {
		LandAcquisition la = null;
		List<LandAcquisition> lasList = new ArrayList<LandAcquisition>();
		
		Writer w = null;
		int count = 0;
		try {	
			MultipartFile excelfile = obj.getLaUploadFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					int sheetNo = 0;
					if(sheetsCount > 7) {sheetNo = 2;}
					XSSFSheet laSheet = workbook.getSheetAt(sheetNo);
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
						String val = null;
						if(!StringUtils.isEmpty(row)) {								
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { la.setWork_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLa_id(val);}
							
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSurvey_number(val);}
							
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { la.setType_of_land(val);}
							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { la.setLa_sub_category_fk(val);}	
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { la.setVillage_id(val);}					
							
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { la.setArea_of_plot(val);}	
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_from(val);}	
							
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { la.setChainage_to(val);}								
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { la.setVillage(val);}										
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { la.setTaluka(val);}
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { la.setDy_slr(val);}
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { la.setSdo(val);}	
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { la.setCollector(val);}
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								la.setProposal_submission_date_to_collector(val);}
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								la.setJm_fee_letter_received_date(val);}
							
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { la.setJm_fee_amount(val);}
						
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								la.setJm_fee_paid_date(val);}								
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								la.setJm_start_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								la.setJm_completion_date(val);}	
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { 
								if(val.contains("/")) {
									LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
									val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
								}
								la.setJm_sheet_date_to_sdo(val);}				
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { la.setJm_remarks(val);}										
						
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { la.setJm_approval(val);}
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
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
						
						if(!StringUtils.isEmpty(la.getLa_id())) {
									XSSFSheet laPrivateSheet = workbook.getSheetAt(sheetNo++);
									XSSFSheet laPrivateLVSheet = workbook.getSheetAt(sheetNo++);
									XSSFSheet laprivateLASheet = workbook.getSheetAt(sheetNo++);
									XSSFSheet GovSheet = workbook.getSheetAt(sheetNo++);
									XSSFSheet forestSheet = workbook.getSheetAt(sheetNo++);
									XSSFSheet railwaySheet = workbook.getSheetAt(sheetNo++);

									XSSFRow privateIRA = laPrivateSheet.getRow(1);
									XSSFRow privateLV = laPrivateLVSheet.getRow(1);
									XSSFRow privateLA = laprivateLASheet.getRow(1);
									XSSFRow Gov = GovSheet.getRow(1);
									XSSFRow forest = forestSheet.getRow(1);
									XSSFRow railway = railwaySheet.getRow(1);
									if(privateIRA != null){
										for(int j = 2; j <= laPrivateSheet.getLastRowNum();j++){
											XSSFRow row2 = laPrivateSheet.getRow(j);
											LandAcquisition pObj = new LandAcquisition();
											if(!StringUtils.isEmpty(row2)) {
												val = formatter.formatCellValue(row2.getCell(0)).trim();
												if(!StringUtils.isEmpty(val)) { pObj.setLa_id(val);}
												
												val = formatter.formatCellValue(row2.getCell(1)).trim();
												if(!StringUtils.isEmpty(val)) { pObj.setCollector(val);}
												//Declaration of Special Railway project 
												val = formatter.formatCellValue(row.getCell(2)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setSubmission_of_proposal_to_GM(val);
												}	
												val = formatter.formatCellValue(row.getCell(3)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setApproval_of_GM(val);
												}
												val = formatter.formatCellValue(row.getCell(4)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDraft_letter_to_con_for_approval_rp(val);
												}
												val = formatter.formatCellValue(row.getCell(5)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_approval_of_construction_rp(val);
												}
												val = formatter.formatCellValue(row.getCell(6)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_uploading_of_gazette_notification_rp(val);
												}
												val = formatter.formatCellValue(row.getCell(7)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_in_gazette_rp(val);
												}
												//Nomination of competent Authority	
												val = formatter.formatCellValue(row.getCell(8)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_proposal_to_DC_for_nomination(val);
												}
												val = formatter.formatCellValue(row.getCell(9)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_nomination_of_competenta_authority(val);
												}
												val = formatter.formatCellValue(row.getCell(10)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDraft_letter_to_con_for_approval_ca(val);
												}
												val = formatter.formatCellValue(row.getCell(11)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_approval_of_construction_ca(val);
												}
												val = formatter.formatCellValue(row.getCell(12)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_uploading_of_gazette_notification_ca(val);
												}
												val = formatter.formatCellValue(row.getCell(13)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_in_gazette_ca(val);
												}
											//Publication of notification under 20 A 
												val = formatter.formatCellValue(row.getCell(14)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_submission_of_draft_notification_to_CALA(val);
												}
												val = formatter.formatCellValue(row.getCell(15)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setApproval_of_CALA_20a(val);
												}
												val = formatter.formatCellValue(row.getCell(16)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDraft_letter_to_con_for_approval_20a(val);
												}
												val = formatter.formatCellValue(row.getCell(17)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_approval_of_construction_20a(val);
												}
												val = formatter.formatCellValue(row.getCell(18)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_uploading_of_gazette_notification_20a(val);
												}
												val = formatter.formatCellValue(row.getCell(19)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_in_gazette_20a(val);
												}
												val = formatter.formatCellValue(row.getCell(20)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_in_2_local_news_papers_20a(val);
												}
												val = formatter.formatCellValue(row.getCell(21)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPasting_of_notification_in_villages_20a(val);
												}
										    //Grievances Redressal	
												val = formatter.formatCellValue(row.getCell(22)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setReceipt_of_grievances(val);
												}
												val = formatter.formatCellValue(row.getCell(23)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDisposal_of_grievances(val);
												}
											//Acquisition notice under 20E	
												val = formatter.formatCellValue(row.getCell(24)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_submission_of_draft_notification_to_CALA_20e(val);
												}
												val = formatter.formatCellValue(row.getCell(25)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setApproval_of_CALA_20e(val);
												}
												val = formatter.formatCellValue(row.getCell(26)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDraft_letter_to_con_for_approval_20e(val);
												}
												val = formatter.formatCellValue(row.getCell(27)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_approval_of_construction_20e(val);
												}
												val = formatter.formatCellValue(row.getCell(28)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_uploading_of_gazette_notification_20e(val);
												}
												val = formatter.formatCellValue(row.getCell(29)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_in_gazette_20e(val);
												}
												val = formatter.formatCellValue(row.getCell(30)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_of_notice_in_2_local_news_papers_20e(val);
												}
											//Acquisition notice under 20F						
												val = formatter.formatCellValue(row.getCell(31)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_submission_of_draft_notification_to_CALA_20f(val);
												}
												val = formatter.formatCellValue(row.getCell(32)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setApproval_of_CALA_20f(val);
												}
												val = formatter.formatCellValue(row.getCell(33)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDraft_letter_to_con_for_approval_20f(val);
												}
												val = formatter.formatCellValue(row.getCell(34)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_approval_of_construction_20f(val);
												}
												val = formatter.formatCellValue(row.getCell(35)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setDate_of_uploading_of_gazette_notification_20f(val);
												}
												val = formatter.formatCellValue(row.getCell(36)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_in_gazette_20f(val);
												}
												val = formatter.formatCellValue(row.getCell(37)).trim();
												if(!StringUtils.isEmpty(val)) { 
													if(val.contains("/")) {
														LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
														val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
													}
													pObj.setPublication_of_notice_in_2_local_news_papers_20f(val);
												}
											}
											if(!StringUtils.isEmpty(pObj) && !StringUtils.isEmpty(pObj.getLa_id())
													&& pObj.getLa_id().equals(la.getLa_id()))
											pObjList.add(pObj);
										}
								la.setPrivateIRAList(pObjList);
								}
								if(privateLA != null){
									for(int j = 2; j <= laprivateLASheet.getLastRowNum();j++){
										XSSFRow row2 = laprivateLASheet.getRow(j);
										LandAcquisition pObj1 = new LandAcquisition();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setSurvey_number(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setType_of_land(val);}

											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setSub_category_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setVillage_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setArea_to_be_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setArea_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setName_of_the_owner(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setBasic_rate(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setHundred_percent_Solatium(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setExtra_25_percent(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setTotal_rate_divide_m2(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setLand_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setAgriculture_tree_nos(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setAgriculture_tree_rate(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setAgriculture_tree_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setForest_tree_nos(val);}
											
											val = formatter.formatCellValue(row2.getCell(16)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setForest_tree_rate(val);}
											
											val = formatter.formatCellValue(row2.getCell(17)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setForest_tree_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(18)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setStructure_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(19)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setBorewell_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { pObj1.setTotal_compensation(val);}
											
											val = formatter.formatCellValue(row2.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj1.setConsent_from_owner(val);}
											val = formatter.formatCellValue(row2.getCell(22)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj1.setLegal_search_report(val);}
											
											val = formatter.formatCellValue(row2.getCell(23)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj1.setDate_of_registration(val);}
											val = formatter.formatCellValue(row2.getCell(24)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj1.setDate_of_possession(val);}
											
											val = formatter.formatCellValue(row.getCell(25)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj1.setPrivate_possession_status_fk(val);}
											
											val = formatter.formatCellValue(row.getCell(26)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj1.setPrivate_special_feature(val);}
											
											val = formatter.formatCellValue(row.getCell(27)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj1.setRemarks(val);}
											
											val = formatter.formatCellValue(row.getCell(28)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj1.setPrivate_attachment_no(val);}
											
											val = formatter.formatCellValue(row.getCell(28)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj1.setIssues(val);}
											
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
												&& pObj1.getLa_id().equals(la.getLa_id()))
											pObjList1.add(pObj1);
									}
							la.setPrivateLAList(pObjList1);
							}	
								if(privateLV != null){
									for(int j = 2; j <= laPrivateLVSheet.getLastRowNum();j++){
										XSSFRow row2 = laPrivateLVSheet.getRow(j);
										LandAcquisition pObj2 = new LandAcquisition();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setSurvey_number(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setType_of_land(val);}

											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setSub_category_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setVillage_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setForest_tree_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setForest_tree_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setHorticulture_tree_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setHorticulture_tree_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setStructure_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setStructure_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setBorewell_survey(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setBorewell_valuation(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setDate_of_rfp_to_adtp(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setRfp_to_adtp_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setDate_of_rate_fixation_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(16)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setSdo_demand_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(17)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setDate_of_approval_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(18)).trim();
											if(!StringUtils.isEmpty(val)) { pObj2.setPayment_amount(val);}
											
											val = formatter.formatCellValue(row2.getCell(19)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												pObj2.setPayment_date(val);}
											
											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj2.setRemarks(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											pObj2.setIssues(val);}
											
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
												&& pObj2.getLa_id().equals(la.getLa_id()))
											pObjList2.add(pObj2);
									}
							la.setPrivateLVList(pObjList2);
							}
								if(Gov != null){
									for(int j = 2; j <= GovSheet.getLastRowNum();j++){
										XSSFRow row2 = GovSheet.getRow(j);
										LandAcquisition gov = new LandAcquisition();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setSurvey_number(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setType_of_land(val);}

											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setSub_category_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setVillage_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setArea_to_be_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setArea_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												gov.setProposal_submission(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setProposal_submission_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												gov.setValuation_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												gov.setLetter_for_payment(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setAmount_demanded(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setLfp_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												gov.setPossession_date(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) { gov.setPossession_status_fk(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) {gov.setSpecial_feature(val);}
											
											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											gov.setRemarks(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											gov.setIssues(val);}
											
											gov.setProposal_submission(DateParser.parse(gov.getProposal_submission()));
											gov.setLetter_for_payment(DateParser.parse(gov.getLetter_for_payment()));
											gov.setApproval_for_payment(DateParser.parse(gov.getApproval_for_payment()));
											gov.setPayment_date(DateParser.parse(gov.getPayment_date()));
											gov.setPossession_date(DateParser.parse(gov.getPossession_date()));
											gov.setValuation_date(DateParser.parse(gov.getValuation_date()));
										}
										if(!StringUtils.isEmpty(gov) && !StringUtils.isEmpty(gov.getLa_id())
												&& gov.getLa_id().equals(la.getLa_id()))
											gObjList.add(gov);
									}
							la.setGovList(gObjList);
							}
								if(forest != null){
									for(int j = 2; j <= forestSheet.getLastRowNum();j++){
										XSSFRow row2 = forestSheet.getRow(j);
										LandAcquisition fObj = new LandAcquisition();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setSurvey_number(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setType_of_land(val);}

											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setSub_category_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { fObj.setVillage_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setArea_to_be_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setArea_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_online_submission(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_submission_date_to_dycfo(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_submission_date_to_ccf_thane(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_submission_date_to_nodal_officer(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_submission_date_to_revenue_secretary_mantralaya(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_submission_date_to_regional_office_nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_date_of_approval_by_regional_office_nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												fObj.setForest_valuation_by_dycfo(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) {fObj.setForest_demanded_amount(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
											fObj.setApproval_for_payment(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
											fObj.setForest_payment_date(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setForest_payment_amount(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setForest_payment_status_fk(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
											fObj.setForest_possession_date(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setForest_possession_status_fk(val);}

											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setForest_special_feature(val);}
											
											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setRemarks(val);}
											
											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setForest_attachment_No(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											fObj.setIssues(val);}
											
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
												&& fObj.getLa_id().equals(la.getLa_id()))
											fObjList.add(fObj);
									}
							la.setForestList(fObjList);
							}
								if(railway != null){
									for(int j = 2; j <= railwaySheet.getLastRowNum();j++){
										XSSFRow row2 = railwaySheet.getRow(j);
										LandAcquisition railways = new LandAcquisition();
										if(!StringUtils.isEmpty(row2)) {
											val = formatter.formatCellValue(row2.getCell(0)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setLa_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(1)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setSurvey_number(val);}
											
											val = formatter.formatCellValue(row2.getCell(2)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setType_of_land(val);}

											val = formatter.formatCellValue(row2.getCell(3)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setSub_category_of_land(val);}
											
											val = formatter.formatCellValue(row2.getCell(4)).trim();
											if(!StringUtils.isEmpty(val)) { railways.setVillage_id(val);}
											
											val = formatter.formatCellValue(row2.getCell(5)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setArea_to_be_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(6)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setArea_acquired(val);}
											
											val = formatter.formatCellValue(row2.getCell(7)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_online_submission(val);}
											
											val = formatter.formatCellValue(row2.getCell(8)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_submission_date_to_DyCFO(val);}
											
											val = formatter.formatCellValue(row2.getCell(9)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_submission_date_to_CCF_Thane(val);}
											
											val = formatter.formatCellValue(row2.getCell(10)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_submission_date_to_nodal_officer_CCF_Nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(11)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_submission_date_to_revenue_secretary_mantralaya(val);}
											
											val = formatter.formatCellValue(row2.getCell(12)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_submission_date_to_regional_office_nagpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(13)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_date_of_approval_by_Rregional_Office_agpur(val);}
											
											val = formatter.formatCellValue(row2.getCell(14)).trim();
											if(!StringUtils.isEmpty(val)) {
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
												railways.setRailway_valuation_by_DyCFO(val);}
											
											val = formatter.formatCellValue(row2.getCell(15)).trim();
											if(!StringUtils.isEmpty(val)) {railways.setRailway_demanded_amount(val);}
											
											val = formatter.formatCellValue(row.getCell(20)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setRailway_approval_for_payment(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
											railways.setRailway_payment_date(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setRailway_payment_amount(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setRailway_payment_status(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
												if(val.contains("/")) {
													LocalDate receivedDate = LocalDate.parse(val, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
													val = receivedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
												}
											railways.setRailway_possession_date(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setRailway_possession_status(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setRailway_special_feature(val);}
											
											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setRemarks(val);}
											

											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setAttachment_no(val);}

											val = formatter.formatCellValue(row.getCell(21)).trim();
											if(!StringUtils.isEmpty(val)) { 
											railways.setIssues(val);}
											
											
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
												&& railways.getLa_id().equals(la.getLa_id()))
											rObjList.add(railways);
								
									}
							la.setRailwayList(rObjList);
							}
						}
						boolean flag = la.checkNullOrEmpty();
						
						if(!flag) {
							lasList.add(la);
						}
					}
					
					if(!lasList.isEmpty() && lasList != null){
						//count  = laService.uploadLAs(lasList);
						count  = service.uploadLAData(lasList,la);
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
		
		return count;
	}
}
