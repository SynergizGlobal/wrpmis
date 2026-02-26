package com.synergizglobal.wrpmis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.wrpmis.Iservice.BudgetService;
import com.synergizglobal.wrpmis.Iservice.RRBSESService;
import com.synergizglobal.wrpmis.Iservice.WorkService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.RRBsesPaginationObject;
import com.synergizglobal.wrpmis.model.RandRMain;
import com.synergizglobal.wrpmis.model.User;

@Controller
public class RRBSESContriller {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRBSESContriller.class); 
	
	
	@Autowired
	RRBSESService service;
	

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
	
	
	@RequestMapping(value="/rr-bses",method={RequestMethod.GET})
	public ModelAndView rrBSES(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.rAndRBsesGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("rrBSES : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getWorkFilterListInRRBSES", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getWorkFilterListInRRBSES(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getWorkFilterListInRRBSES(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkFilterListInRRBSES : " + e.getMessage());
		}
		return objList;
	}
	

	@RequestMapping(value = "/ajax/getResponsibleListInRRBSES", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getResponsibleListInRRBSES(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getPeopleListForRRForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPeopleListForRRForm : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getHodFilterListInRRBSES", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RandRMain> getHodFilterListInRRBSES(@ModelAttribute RandRMain obj, HttpSession session) {
		List<RandRMain> objList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			objList = service.getHodFilterListInRRBSES(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodFilterListInRRBSES : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/rr-bses", method = { RequestMethod.POST, RequestMethod.GET })
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

			RRBsesPaginationObject personJsonObject = new RRBsesPaginationObject();
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
			objList = service.getRRBSESList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/add-rr-bses", method = {RequestMethod.GET})
	public ModelAndView addRRForm(HttpSession session,@ModelAttribute RandRMain rr){
		ModelAndView model = new ModelAndView();
		try{

			User uObj = (User) session.getAttribute("user");
			rr.setUser_type_fk(uObj.getUser_type_fk());
			
			rr.setUser_role_code(uObj.getUser_role_code());
			rr.setUser_id(uObj.getUser_id());
			
			model.setViewName(PageConstants.addEditRandRBses);
			model.addObject("action", "add");
			List<RandRMain> worksList = service.getWorkFilterList(rr);
			model.addObject("worksList", worksList);
			
			List<RandRMain> hodList = service.getHodFilterList(rr);
			model.addObject("hodList", hodList);
			
			List<RandRMain> responsibleList = service.getPeopleListForRRForm(rr);
			model.addObject("responsibleList", responsibleList);
			
		}catch (Exception e) {
			e.printStackTrace();
				logger.error("addBudgetForm : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/get-rr-bses", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRRBSES(@ModelAttribute RandRMain rr  ,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRBses);
			model.addObject("action", "edit");
			
			User uObj = (User) session.getAttribute("user");
			rr.setUser_type_fk(uObj.getUser_type_fk());
			rr.setUser_role_code(uObj.getUser_role_code());
			rr.setUser_id(uObj.getUser_id());
			
			List<RandRMain> worksList = service.getWorkFilterList(rr);
			model.addObject("worksList", worksList);
			
			List<RandRMain> hodList = service.getHodFilterList(rr);
			model.addObject("hodList", hodList);
			
			List<RandRMain> responsibleList = service.getPeopleListForRRForm(rr);
			model.addObject("responsibleList", responsibleList);
			
		
			
			RandRMain rrDetails = service.getRRBSES(rr);
			rrDetails.setAgency_id(rr.getAgency_id());
			model.addObject("rrDetails", rrDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getRRBSES : " + e.getMessage());
		}
		return model;
	 }
	@RequestMapping(value = "/get-rr-bses/{rr_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRRBSESForId(@ModelAttribute RandRMain rr,@PathVariable("rr_id") String rr_id  ,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRandRBses);
			model.addObject("action", "edit");
			
			User uObj = (User) session.getAttribute("user");
			rr.setUser_type_fk(uObj.getUser_type_fk());
			rr.setUser_role_code(uObj.getUser_role_code());
			rr.setUser_id(uObj.getUser_id());
			
			List<RandRMain> worksList = service.getWorkFilterList(rr);
			model.addObject("worksList", worksList);
			
			List<RandRMain> hodList = service.getHodFilterList(rr);
			model.addObject("hodList", hodList);
			
			List<RandRMain> responsibleList = service.getPeopleListForRRForm(rr);
			model.addObject("responsibleList", responsibleList);
			
			RandRMain rrDetails = service.getRRBSES(rr);
			model.addObject("rrDetails", rrDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getRRBSESForId : " + e.getMessage());
		}
		return model;
	 }
	@RequestMapping(value = "/add-rr-bses", method = {RequestMethod.POST})
	public ModelAndView addRRBSES(@ModelAttribute RandRMain obj,HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-bses");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			obj.setSubmission_date_report_ca(DateParser.parse(obj.getSubmission_date_report_ca()));
			obj.setActual_submission_date_bses_report_to_mrvc(DateParser.parse(obj.getActual_submission_date_bses_report_to_mrvc()));
			obj.setReport_submission_date_to_mrvc(DateParser.parse(obj.getReport_submission_date_to_mrvc()));
			obj.setApproval_date_by_mrvc(DateParser.parse(obj.getApproval_date_by_mrvc()));
			
			String bsesid =  service.addRRBSES(obj);
			if(!StringUtils.isEmpty(bsesid)) {
				attributes.addFlashAttribute("success", "R&R Agency "+bsesid+" Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding R&R Agency is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding R&R Agency is failed. Try again.");
			logger.error("addRR : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-rr-bses", method = {RequestMethod.POST})
	public ModelAndView updateRRBSES(@ModelAttribute RandRMain obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-bses");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			obj.setSubmission_date_report_ca(DateParser.parse(obj.getSubmission_date_report_ca()));
			obj.setActual_submission_date_bses_report_to_mrvc(DateParser.parse(obj.getActual_submission_date_bses_report_to_mrvc()));
			obj.setReport_submission_date_to_mrvc(DateParser.parse(obj.getReport_submission_date_to_mrvc()));
			obj.setApproval_date_by_mrvc(DateParser.parse(obj.getApproval_date_by_mrvc()));
			
			
			String bsesid =  service.updateRRBSES(obj);
			if(!StringUtils.isEmpty(bsesid)) {
				attributes.addFlashAttribute("success", "R&R Agency "+bsesid+" Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating RR BSES is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating RR BSES is failed. Try again.");
			logger.error("updateRR : " + e.getMessage());
		}
		return model;
	}
}
