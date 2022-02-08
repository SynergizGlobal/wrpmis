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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.RandRBsesService;
import com.synergizglobal.pmis.Iservice.RandRService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.RRPaginationObject;
import com.synergizglobal.pmis.model.RandR;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBses;
import com.synergizglobal.pmis.model.RRBsesPaginationObject;

@Controller
public class RandRBsesController {
	Logger logger = Logger.getLogger(RandRBsesController.class);
	@Autowired
	RandRBsesService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	
	/**
	 * This method rAndR() will execute when user request the for R & R module.
	 * 
	 * @param session it will check the session of the user.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/rr-bses",method=RequestMethod.GET)
	public ModelAndView rAndR(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.bsesGrid);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("rAndR : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInRRBses", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RRBses> getWorksList(@ModelAttribute RRBses obj) {
		List<RRBses> worksList = null;
		try {
			worksList = service.getWorksFilterListInRRBses(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getAgencyNameFilterListInRRBses", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RRBses> getAgencyNameFilterListInRRBses(@ModelAttribute RRBses obj) {
		List<RRBses> objList = null;
		try {
			objList = service.getAgencyNameFilterListInRRBses(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAgencyNameFilterListInRRBses : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/get-rr-bses", method = { RequestMethod.POST, RequestMethod.GET })
	public void getRRBsesList(@ModelAttribute RRBses obj, HttpServletRequest request,
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

			List<RRBses> budgetList = new ArrayList<RRBses>();

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
					"getRRBsesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(RRBses obj, String searchParameter) {
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
	public List<RRBses> createPaginationData(int startIndex, int offset, RRBses obj, String searchParameter) {
		List<RRBses> objList = null;
		try {
			objList = service.getRRBsesList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getProjectsListForRRBsesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RRBses> getProjectsListForRRBsesForm(@ModelAttribute RRBses obj) {
		List<RRBses> objsList = null;
		try {
			objsList = service.getProjectsListForRRBsesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForRRBsesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForRRBsesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RRBses> getWorkListForRRBsesForm(@ModelAttribute RRBses obj) {
		List<RRBses> objsList = null;
		try {
			objsList = service.getWorkListForRRBsesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForRRBsesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForRRBsesForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RRBses> getContractsListForRRBsesForm(@ModelAttribute RRBses obj) {
		List<RRBses> objsList = null;
		try {
			objsList = service.getContractsListForRRBsesForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForRRBsesForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/bses-add-form",method=RequestMethod.GET)
	public ModelAndView rAndRAddForm(HttpSession session,@ModelAttribute RRBses obj) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditBses);
		model.addObject("action", "add");
		try {
			List<RRBses> projectsList = service.getProjectsListForRRBsesForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<RRBses> workList = service.getWorkListForRRBsesForm(obj);
			model.addObject("workList", workList);
			
			List<RRBses> contractList = service.getContractsListForRRBsesForm(obj);
			model.addObject("contractList", contractList);
			
			List<RRBses> fileTypeList = service.getFileTypeListForRRBsesForm(obj);
			model.addObject("fileTypeList", fileTypeList);
			
		} catch (Exception e) {
			logger.info("rAndRAddForm : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/get-rr-bses", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRRBsesForm(@ModelAttribute RRBses obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBses);
			model.addObject("action", "edit");
			
			List<RRBses> projectsList = service.getProjectsListForRRBsesForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<RRBses> workList = service.getWorkListForRRBsesForm(obj);
			model.addObject("workList", workList);
			
			List<RRBses> contractList = service.getContractsListForRRBsesForm(obj);
			model.addObject("contractList", contractList);
			
			List<RRBses> fileTypeList = service.getFileTypeListForRRBsesForm(obj);
			model.addObject("fileTypeList", fileTypeList);
			
			RRBses rrBsesDeatils = service.getRRBsesDeatils(obj);
			model.addObject("rrBsesDeatils", rrBsesDeatils);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getRRBsesFormW : " + e.getMessage());
		}
		return model;
	 }
	@RequestMapping(value = "/get-rr-bses/{bses_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRRBsesFormWithId(@ModelAttribute RRBses obj,@PathVariable("bses_id") String bses_id ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditBses);
			model.addObject("action", "edit");
			
			List<RRBses> projectsList = service.getProjectsListForRRBsesForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<RRBses> workList = service.getWorkListForRRBsesForm(obj);
			model.addObject("workList", workList);
			
			List<RRBses> contractList = service.getContractsListForRRBsesForm(obj);
			model.addObject("contractList", contractList);
			
			List<RRBses> fileTypeList = service.getFileTypeListForRRBsesForm(obj);
			model.addObject("fileTypeList", fileTypeList);
			
			RRBses rrBsesDeatils = service.getRRBsesDeatils(obj);
			model.addObject("rrBsesDeatils", rrBsesDeatils);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getRRBsesFormWithId : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-rr-bses", method = {RequestMethod.POST})
	public ModelAndView addRRBses(@ModelAttribute RRBses obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-bses");
			obj.setDate_of_nomination(DateParser.parse(obj.getDate_of_nomination()));
			boolean flag =  service.addRRBses(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Bses Added Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Adding RR Bses is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding RR Bses is failed. Try again.");
			logger.error("RRBses : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-rr-bses", method = {RequestMethod.POST})
	public ModelAndView updateRRBses(@ModelAttribute RRBses obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-bses");
			obj.setDate_of_nomination(DateParser.parse(obj.getDate_of_nomination()));
			boolean flag =  service.updateRRBses(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Bses Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating RR Bses is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating RR Bses is failed. Try again.");
			logger.error("RRBses : " + e.getMessage());
		}
		return model;
	}
	
}
