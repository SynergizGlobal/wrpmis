package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.synergizglobal.pmis.model.LandAcquisition;
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
	public ModelAndView updateRR(@ModelAttribute RandRMain obj,RedirectAttributes attributes){
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
}
