package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.LandAcquisitionService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Risk;
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
	
	
	@RequestMapping(value = "/ajax/get-land-acquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getLandAcquisitionList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> landAcquisitionList = null;
		try {
			landAcquisitionList = service.getLandAcquisitionList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLandAcquisitionList : " + e.getMessage());
		}
		return landAcquisitionList;
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
			
			MultipartFile file = obj.getLandAcquisitionFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.LAND_ACQUISITION_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment(fileName);
			}
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
	
}
