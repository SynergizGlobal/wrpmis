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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.TAFinancialsService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.DocumentsPaginationObject;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.TAFinancials;
import com.synergizglobal.pmis.model.TAFinancialsPaginationObject;


@Controller
public class TAFinancialsController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TAFinancialsController.class);
	
	@Autowired
	TAFinancialsService service;
	
	@RequestMapping(value="/ta-financials",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView taFinancials(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.taFinancialsGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("taFinancials : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/get-ta-financials", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute TAFinancials obj, HttpServletRequest request,
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

			List<TAFinancials> taFinancialsList = new ArrayList<TAFinancials>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				taFinancialsList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				taFinancialsList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//taFinancialsList = getListBasedOnSearchParameter(searchParameter,taFinancialsList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			TAFinancialsPaginationObject personJsonObject = new TAFinancialsPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(taFinancialsList);

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
	public int getTotalRecords(TAFinancials obj, String searchParameter) {
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
	public List<TAFinancials> createPaginationData(int startIndex, int offset, TAFinancials obj, String searchParameter) {
		List<TAFinancials> earthWorkList = null;
		try {
			earthWorkList = service.getTAFinancialsList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	
	@RequestMapping(value = "/ajax/getWorksFilterListInTAFinancials", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getWorksList(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> worksList = null;
		try {
			worksList = service.getTAFinancialsWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInTAFinancials", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getContractsList(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> contractsList = null;
		try {
			contractsList = service.getTAFinancialsContractsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForFinancialsForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getWorkListForFinancialsForm(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> objsList = null;
		try {
			objsList = service.getWorkListForFinancialsForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForFinancialsForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForFinancialsForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getContractsListForFinancialsForm(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> objsList = null;
		try {
			objsList = service.getContractsListForFinancialsForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForFinancialsForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-ta-financials-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addTAFinancialsForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTaFinancialsForm);
			model.addObject("action", "add");
			List<TAFinancials> worksList = service.getWorksList();
			model.addObject("worksList", worksList);
			List<TAFinancials> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			List<TAFinancials> unitsList = service.getUnitsList();
			model.addObject("unitsList", unitsList);
			
		}catch (Exception e) {
				logger.error("addTAFinancialsForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-ta-financials", method = {RequestMethod.POST})
	public ModelAndView getTAFinancialsForm(@ModelAttribute TAFinancials obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTaFinancialsForm);
			model.addObject("action", "edit");
			List<TAFinancials> worksList = service.getWorksList();
			model.addObject("worksList", worksList);
			List<TAFinancials> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			List<TAFinancials> unitsList = service.getUnitsList();
			model.addObject("unitsList", unitsList);
			TAFinancials taFinancialDetails = service.getTAFinancials(obj);
			model.addObject("taFinancialDetails", taFinancialDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getTAFinancials : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-ta-financials", method = {RequestMethod.POST})
	public ModelAndView addTAFinancials(@ModelAttribute TAFinancials obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/ta-financials");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag = service.addTAFinancials(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "TA Financials Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding TA Financials is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding TA Financials is failed. Try again.");
			logger.error("addTAFinancials : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-ta-financials", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateTAFinancials(@ModelAttribute TAFinancials obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/ta-financials");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.updateTAFinancials(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "TA Financials Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating TA Financials is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating TA Financials is failed. Try again.");
			logger.error("updateTAFinancials : " + e.getMessage());
		}
		return model;
	}
}
