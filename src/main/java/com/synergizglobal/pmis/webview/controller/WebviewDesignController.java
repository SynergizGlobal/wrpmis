package com.synergizglobal.pmis.webview.controller;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.DesignService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.controller.DesignController;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.DesignsPaginationObject;
import com.synergizglobal.pmis.model.Issue;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewDesignController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewDesignController.class);

	@Autowired
	ContractService contractservice;
	@Autowired
	SafetyService safetyService;
	@Autowired
	DesignService designService;
	@Autowired
	HomeService homeService;
	@Autowired
	WorkService workService;
	@Autowired
	IssueService issueService;
	
	
	
	@RequestMapping(value="/design",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView design(@ModelAttribute Design obj,HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.designGrid);
		
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("design : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getHodListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getHodListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getHodListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getHodListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDepartmentListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDepartmentListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getWorksListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getWorksListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return design;
	}
	
	
	@RequestMapping(value = "/ajax/getContractListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getContractListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getContractListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getStructureListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getStructureListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getStructureListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getDrawingTypeListFilterInDesign", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDrawingTypeListFilter(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDrawingTypeListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDrawingTypeListFilter : " + e.getMessage());
		}
		return design;
	}
	
	@RequestMapping(value = "/ajax/getDesigns", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getDesigns(@ModelAttribute Design obj) {
		List<Design> design = null;
		try {
			design = designService.getDesigns(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesigns : " + e.getMessage());
		}
		return design;
	}
	
	
	@RequestMapping(value = "/ajax/getDesignsList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Design obj, HttpServletRequest request,
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

			List<Design> designsList = new ArrayList<Design>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				designsList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				designsList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//designsList = getListBasedOnSearchParameter(searchParameter,designsList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			DesignsPaginationObject personJsonObject = new DesignsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(designsList);

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
	public int getTotalRecords(Design obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = designService.getTotalRecords(obj, searchParameter);
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
	public List<Design> createPaginationData(int startIndex, int offset, Design obj, String searchParameter) {
		List<Design> earthWorkList = null;
		try {
			earthWorkList = designService.getDesignsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/get-design", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getDesign(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addEditDesignForm);
			model.addObject("action", "edit");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList();	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);

			Design designDetails = designService.getDesignDetails(obj);
			model.addObject("designDetails", designDetails);
			 
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesign : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-design-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addDesignForm(@ModelAttribute Design obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addEditDesignForm);	
			model.addObject("action", "add");
			
			List<Design> projectsList = designService.getProjectsListForDesignForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Design> worksList = designService.getWorkListForDesignForm(obj);
			model.addObject("worksList", worksList);
			
			List<Design> contractsList = designService.getContractsListForDesignForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Contract> departmentList = contractservice.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<Design> contractList = designService.getContractList();
			model.addObject("contractList", contractList);
			
			List<Design> preparedBy = designService.getPreparedByList();
			model.addObject("preparedBy", preparedBy);
			
			List<Design> structureTypeList = designService.structureList();
			model.addObject("structureTypeList", structureTypeList);
			
			List<Design> drawingTypeList = designService.drawingTypeList();
			model.addObject("drawingTypeList", drawingTypeList);
			
			List<Design> revisionStatuses = designService.getRevisionStatuses();
			model.addObject("revisionStatuses", revisionStatuses);
			
			List<Design> asBuiltStatuses = designService.getAsBuiltStatuses();
			model.addObject("asBuiltStatuses", asBuiltStatuses);
			
			List<Issue> issueCategoryList = issueService.getIssuesCategoryList();	
			model.addObject("issueCategoryList", issueCategoryList);
			
			List<Issue> issuePriorityList = issueService.getIssuesPriorityList();
			model.addObject("issuePriorityList", issuePriorityList);
			
		}catch (Exception e) {
			logger.error("addDesignForm : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/ajax/getProjectsListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getProjectsListForDesignForm(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getProjectsListForDesignForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getWorkListForDesignForm(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getWorkListForDesignForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForDesignForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Design> getContractsListForDesignForm(@ModelAttribute Design obj) {
		List<Design> objsList = null;
		try {
			objsList = designService.getContractsListForDesignForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForDesignForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/mobileappwebview/design");
			
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));
			obj.setSubmitted_to_division(DateParser.parse(obj.getSubmitted_to_division()));
			obj.setSubmitted_to_hq(DateParser.parse(obj.getSubmitted_to_hq()));
			
			boolean flag =  designService.addDesign(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Design Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Design is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Design is failed. Try again.");
			logger.error("addDesign : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/update-design", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDesign(@ModelAttribute Design obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/mobileappwebview/design");
			
			obj.setPlanned_start(DateParser.parse(obj.getPlanned_start()));
			obj.setPlanned_finish(DateParser.parse(obj.getPlanned_finish()));
			obj.setConsultant_submission(DateParser.parse(obj.getConsultant_submission()));
			obj.setMrvc_reviewed(DateParser.parse(obj.getMrvc_reviewed()));
			obj.setDivisional_approval(DateParser.parse(obj.getDivisional_approval()));
			obj.setHq_approval(DateParser.parse(obj.getHq_approval()));
			obj.setGfc_released(DateParser.parse(obj.getGfc_released()));
			obj.setAs_built_date(DateParser.parse(obj.getAs_built_date()));
			obj.setSubmitted_to_division(DateParser.parse(obj.getSubmitted_to_division()));
			obj.setSubmitted_to_hq(DateParser.parse(obj.getSubmitted_to_hq()));

			boolean flag =  designService.updateDesign(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Design Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Design is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Design is failed. Try again.");
			logger.error("updateDesign : " + e.getMessage());
		}
		return model;
	}
	
}
