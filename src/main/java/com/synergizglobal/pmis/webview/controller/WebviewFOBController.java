package com.synergizglobal.pmis.webview.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.ContractService;
import com.synergizglobal.pmis.Iservice.FOBService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.User;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewFOBController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewFOBController.class);	
	
	@Autowired
	FOBService fobService;
	
	@Autowired
	ActivitiesService activitiesService;
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	ContractService contractService;
	
	@Autowired
	IssueService issueService;
	
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
	
	@RequestMapping(value="/fob",method=RequestMethod.GET)
	public ModelAndView fob(@ModelAttribute FOB obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.fobGrid);
		} catch (Exception e) {
			logger.error("fob : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/ajax/getFOBList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getFOBList(@ModelAttribute FOB obj,HttpSession session) {
		List<FOB> fobs = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			fobs = fobService.getFOBList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFOBList : " + e.getMessage());
		}
		return fobs;
	}
		
	@RequestMapping(value = "/ajax/getWorkStatusFilterListInFOB", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getWorkStatusList(@ModelAttribute FOB obj,HttpSession session) {
		List<FOB> workStatusList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			workStatusList = fobService.getWorkStatusList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkStatusList : " + e.getMessage());
		}
		return workStatusList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInFOB", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getWorksListForFilter(@ModelAttribute FOB obj,HttpSession session) {
		List<FOB> worksList = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			worksList = fobService.getWorksListForFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListForFilter : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInFOB", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getContractsListForFilter(@ModelAttribute FOB obj) {
		List<FOB> contractsList = null;
		try {
			contractsList = fobService.getContractsListForFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForFilter : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value="/add-fob-form",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addFOBForm(@ModelAttribute FOB obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			String user_id = (String) session.getAttribute("USER_ID"),user_type = (String) session.getAttribute("USER_TYPE");
			if(!StringUtils.isEmpty(user_type) 
					&& (user_type.equals(CommonConstants.USER_TYPE_HOD) 
					|| user_type.equals(CommonConstants.USER_TYPE_DYHOD)) ) {
				obj.setUser_id(user_id);
			}
			
			model.setViewName(MobilePageConstants2.fobForm);
			
			model.addObject("action", "add");
			
			List<FOB> fobIdCheck = fobService.getFobIdCheck(obj);
			model.addObject("fobIdCheck",fobIdCheck);
			
			List<FOB> projectsList = fobService.getProjectsListForFOBForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<FOB> fobFileTypesList = fobService.getFobFileTypesList(obj);
			model.addObject("fobFileTypesList", fobFileTypesList);
			
			List<FOB> worksList = fobService.getWorkListForFOBForm(obj);
			model.addObject("worksList", worksList);
			
			List<FOB> contractsList = fobService.getContractsListForFOBForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<FOB> responsiblePeopleList = fobService.getResponsiblePeopleListForFOBForm(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			/*List<FOB> fobDetailsList = fobService.getFobDetailsList(obj);
			model.addObject("fobDetailsList", fobDetailsList);*/
			
			List<String> executionStatusList = homeService.getExecutionStatusList();
			model.addObject("executionStatusList", executionStatusList);
			
			List<FOB> unitsList = fobService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<FOB> fobFileTypeList = fobService.getFobFileTypeList(obj);
			model.addObject("fobFileTypeList", fobFileTypeList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addFOBForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getProjectsListForFOBForm(@ModelAttribute FOB obj) {
		List<FOB> objsList = null;
		try {
			objsList = fobService.getProjectsListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForFOBForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getWorkListForFOBForm(@ModelAttribute FOB obj) {
		List<FOB> objsList = null;
		try {
			objsList = fobService.getWorkListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForFOBForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePeopleFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getResponsiblePeopleFOBForm(@ModelAttribute FOB obj) {
		List<FOB> objsList = null;
		try {
			objsList = fobService.getResponsiblePeopleListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getResponsiblePeopleFOBForm : " + e.getMessage());
		}
		return objsList;
	}	
	
	@RequestMapping(value = "/ajax/getContractsListForFOBForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FOB> getContractsListForFOBForm(@ModelAttribute FOB obj,HttpSession session) {
		List<FOB> objsList = null;
		try {
			String user_id = (String) session.getAttribute("USER_ID"),
					userName = (String) session.getAttribute("USER_NAME"),
							user_type = (String) session.getAttribute("USER_TYPE");
			if(!StringUtils.isEmpty(user_type) 
					&& (user_type.equals(CommonConstants.USER_TYPE_HOD) 
					|| user_type.equals(CommonConstants.USER_TYPE_DYHOD)) ) {
				obj.setUser_id(user_id);
			}
			
			objsList = fobService.getContractsListForFOBForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForFOBForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-fob",method=RequestMethod.POST)
	public ModelAndView addFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/mobileappwebview/fob");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));			
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));			
			obj.setCommissioning_date(DateParser.parse(obj.getCommissioning_date()));			
			obj.setActual_completion_date(DateParser.parse(obj.getActual_completion_date()));
			obj.setRevised_completion(DateParser.parse(obj.getRevised_completion()));
		
			boolean flag = fobService.addFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding fob is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-fob",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			String user_id = (String) session.getAttribute("USER_ID"),user_type = (String) session.getAttribute("USER_TYPE");
			if(!StringUtils.isEmpty(user_type) 
					&& (user_type.equals(CommonConstants.USER_TYPE_HOD) 
					|| user_type.equals(CommonConstants.USER_TYPE_DYHOD)) ) {
				obj.setUser_id(user_id);
			}
			
			model.setViewName(MobilePageConstants2.fobForm);
			
			model.addObject("action", "edit");
			
			List<FOB> projectsList = fobService.getProjectsListForFOBForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<FOB> contractsList = fobService.getContractsListForFOBForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<FOB> responsiblePeopleList = fobService.getResponsiblePeopleListForFOBForm(obj);
			model.addObject("responsiblePeopleList", responsiblePeopleList);
			
			List<FOB> fobFileTypesList = fobService.getFobFileTypesList(obj);
			model.addObject("fobFileTypesList", fobFileTypesList);
			
			List<String> executionStatusList = homeService.getExecutionStatusList();
			model.addObject("executionStatusList", executionStatusList);
			
			/*List<FOB> fobDetailsList = fobService.getFobDetailsList(obj);
			model.addObject("fobDetailsList", fobDetailsList);*/
			
			List<FOB> fobDetailsLocations = fobService.getFobDetailsLocations(obj);
			model.addObject("fobDetailsLocations", fobDetailsLocations);
			
			List<FOB> fobDetailsTypes = fobService.getFobDetailsTypes(obj);
			model.addObject("fobDetailsTypes", fobDetailsTypes);
			
			List<FOB> unitsList = fobService.getUnitsList(obj);
			model.addObject("unitsList", unitsList);
			
			List<FOB> fobFileTypeList = fobService.getFobFileTypeList(obj);
			model.addObject("fobFileTypeList", fobFileTypeList);
			
			FOB fob = fobService.getFOB(obj);
			model.addObject("fob", fob);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getMessage_id())) {
				boolean flag = issueService.readIssueMessage(obj.getMessage_id());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-fob",method=RequestMethod.POST)
	public ModelAndView updateFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {
			model.setViewName("redirect:/mobileappwebview/fob");
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setTarget_date(DateParser.parse(obj.getTarget_date()));			
			obj.setConstruction_start_date(DateParser.parse(obj.getConstruction_start_date()));			
			obj.setCommissioning_date(DateParser.parse(obj.getCommissioning_date()));			
			obj.setActual_completion_date(DateParser.parse(obj.getActual_completion_date()));
			obj.setRevised_completion(DateParser.parse(obj.getRevised_completion()));
		
			boolean flag = fobService.updateFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating fob is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateFOB : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-fob",method=RequestMethod.POST)
	public ModelAndView deleteFOB(@ModelAttribute FOB obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/mobileappwebview/fob");
			boolean flag = fobService.deleteFOB(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FOB deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting fob is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("deleteFOB : " + e.getMessage());
		}
		return model;
	}
}
