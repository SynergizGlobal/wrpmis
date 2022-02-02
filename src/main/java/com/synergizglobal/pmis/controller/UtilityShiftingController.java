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
import com.synergizglobal.pmis.Iservice.UtilityShiftingService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.UtilityShifting;
import com.synergizglobal.pmis.model.UtilityShiftingPaginationObject;
import com.synergizglobal.pmis.model.User;



@Controller
public class UtilityShiftingController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(UtilityShiftingController.class);
	
	@Autowired
	UtilityShiftingService utilityShiftingService;
	
	@Value("${common.error.message}")
	public String commonError;

	
	
	@RequestMapping(value="/utilityshifting",method=RequestMethod.GET)
	public ModelAndView users(@ModelAttribute UtilityShifting obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.utilityShifting);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getWorksListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}	
	@RequestMapping(value = "/ajax/getLocationListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getLocationListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getLocationListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getUtilityCategoryListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getUtilityCategoryListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getUtilityCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getUtilityTypeListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getUtilityTypeListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getUtilityTypeListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/ajax/getStatusListFilter", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UtilityShifting> getStatusListFilter(HttpSession session,@ModelAttribute UtilityShifting obj) {
		List<UtilityShifting> objList = null;
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getUtilityStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInUtilityShifting : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value="/add-utility-shifting",method=RequestMethod.GET)
	public ModelAndView addUtilityShiftingForm(HttpSession session,@ModelAttribute UtilityShifting obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "add");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
			List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(obj);
			model.addObject("contractsList", contractsList);
			
			
			List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(obj);
			model.addObject("utilityTypeList", utilityTypeList);
			
			List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(obj);
			model.addObject("utilityCategoryList", utilityCategoryList);
			
			List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(obj);
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
			
			List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(obj);
			model.addObject("impactedContractList", impactedContractList);
			
			List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(obj);
			model.addObject("requirementStageList", requirementStageList);
			
			List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(obj);
			model.addObject("unitList", unitList);
			
			List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(obj);
			model.addObject("utilityshiftingfiletypeList", utilityshiftingfiletypeList);			
			
			List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(obj);
			model.addObject("statusList", statusList);			
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addUtilityShiftingForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/addUtilityShifting",method=RequestMethod.POST)
	public ModelAndView addUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/utilityshifting");
			
			user_Id = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
			User user = (User)session.getAttribute("user");
			
			obj.setStart_date(DateParser.parse(obj.getStart_date()));			
			obj.setShifting_completion_date(DateParser.parse(obj.getShifting_completion_date()));
			obj.setPlanned_completion_date(DateParser.parse(obj.getPlanned_completion_date()));
			obj.setIdentification(DateParser.parse(obj.getIdentification()));
			
			
			boolean flag = utilityShiftingService.addUtilityShifting(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Utility Shifting added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding Utility Shifting is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addUtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getUtilityShiftingList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getUtilityShiftingList(@ModelAttribute UtilityShifting obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null,user_role_name=null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			user_role_name = (String) session.getAttribute("USER_ROLE_NAME");
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

			List<UtilityShifting> utilityShiftingList = new ArrayList<UtilityShifting>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				utilityShiftingList = createPaginationData(session,startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				utilityShiftingList = createPaginationData(session,startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//UtilityShiftingList = getListBasedOnSearchParameter(searchParameter,UtilityShiftingList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			UtilityShiftingPaginationObject personJsonObject = new UtilityShiftingPaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(utilityShiftingList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getUtilityShiftingList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(UtilityShifting obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = utilityShiftingService.getTotalRecords(obj, searchParameter);
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
	public List<UtilityShifting> createPaginationData(HttpSession session, int startIndex, int offset, UtilityShifting obj, String searchParameter) {
		List<UtilityShifting> objList = null;
		try {

			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			
			objList = utilityShiftingService.getUtilityShiftingList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value="/get-utility-shifting",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
		
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "edit");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
			List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(obj);
			model.addObject("contractsList", contractsList);
			
			
			List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(obj);
			model.addObject("utilityTypeList", utilityTypeList);
			
			List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(obj);
			model.addObject("utilityCategoryList", utilityCategoryList);
			
			List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(obj);
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
			
			List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(obj);
			model.addObject("impactedContractList", impactedContractList);
			
			List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(obj);
			model.addObject("requirementStageList", requirementStageList);
			
			List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(obj);
			model.addObject("unitList", unitList);
			
			List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(obj);
			model.addObject("utilityshiftingfiletypeList", utilityshiftingfiletypeList);			
			
			List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(obj);
			model.addObject("statusList", statusList);	
			
			UtilityShifting utilityShifting = utilityShiftingService.getUtilityShifting(obj);
			model.addObject("utilityShifting", utilityShifting);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getUtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-utility-shifting/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getUtilityShifting(@ModelAttribute UtilityShifting obj,@PathVariable("id") String id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
		
			model.setViewName(PageConstants.addEditUtilityShifting);
			model.addObject("action", "edit");
			
			List<UtilityShifting> projectsList = utilityShiftingService.getProjectsListForUtilityShifting(obj);
			model.addObject("projectsList", projectsList);
			
			List<UtilityShifting> worksList = utilityShiftingService.getWorkListForUtilityShifting(obj);
			model.addObject("worksList", worksList);
			
			User uObj = (User) session.getAttribute("user");
			obj.setDepartment_fk(uObj.getDepartment_fk());			
			
			List<UtilityShifting> contractsList = utilityShiftingService.getContractsListForUtilityShifting(obj);
			model.addObject("contractsList", contractsList);
			
			
			List<UtilityShifting> utilityTypeList = utilityShiftingService.getUtilityTypeList(obj);
			model.addObject("utilityTypeList", utilityTypeList);
			
			List<UtilityShifting> utilityCategoryList = utilityShiftingService.getUtilityCategoryList(obj);
			model.addObject("utilityCategoryList", utilityCategoryList);
			
			List<UtilityShifting> utilityExecutionAgencyList = utilityShiftingService.getUtilityExecutionAgencyList(obj);
			model.addObject("utilityExecutionAgencyList", utilityExecutionAgencyList);
			
			List<UtilityShifting> impactedContractList = utilityShiftingService.getImpactedContractList(obj);
			model.addObject("impactedContractList", impactedContractList);
			
			List<UtilityShifting> requirementStageList = utilityShiftingService.getRequirementStageList(obj);
			model.addObject("requirementStageList", requirementStageList);
			
			List<UtilityShifting> unitList = utilityShiftingService.getUnitListForUtilityShifting(obj);
			model.addObject("unitList", unitList);
			
			List<UtilityShifting> utilityshiftingfiletypeList = utilityShiftingService.getUtilityTypeListForUtilityShifting(obj);
			model.addObject("utilityshiftingfiletypeList", utilityshiftingfiletypeList);			
			
			List<UtilityShifting> statusList = utilityShiftingService.getStatusListForUtilityShifting(obj);
			model.addObject("statusList", statusList);	
			
			UtilityShifting utilityShifting = utilityShiftingService.getUtilityShifting(obj);
			model.addObject("utilityShifting", utilityShifting);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getUtilityShifting : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/updateUtilityShifting",method=RequestMethod.POST)
	public ModelAndView updateUtilityShifting(@ModelAttribute UtilityShifting obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/utilityshifting");

			user_Id = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			
			User user = (User)session.getAttribute("user");
			
	
			obj.setStart_date(DateParser.parse(obj.getStart_date()));			
			obj.setShifting_completion_date(DateParser.parse(obj.getShifting_completion_date()));
			obj.setPlanned_completion_date(DateParser.parse(obj.getPlanned_completion_date()));
			obj.setIdentification(DateParser.parse(obj.getIdentification()));

			
			boolean flag = utilityShiftingService.updateUtilityShifting(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "UtilityShifting updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating UtilityShifting is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateUtilityShifting : " + e.getMessage());
		}
		return model;
	}
	
}
