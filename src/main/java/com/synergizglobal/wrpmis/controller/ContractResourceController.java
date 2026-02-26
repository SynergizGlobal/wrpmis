package com.synergizglobal.wrpmis.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.ContractResourceService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.ContractResource;
import com.synergizglobal.wrpmis.model.User;



@Controller
public class ContractResourceController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	Logger logger = Logger.getLogger(ContractResourceController.class);
	
	@Autowired
	ContractResourceService service;
	
	
	
	@RequestMapping(value="/contract-resource-form",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView contractResourceForm(@ModelAttribute ContractResource obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView(PageConstants.contractResources);
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<ContractResource> projectsList = service.getProjectsListForContractResourceForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<ContractResource> worksList = service.getWorkListForContractResourceForm(obj);
			model.addObject("worksList", worksList);
			
			List<ContractResource> contractsList = service.getContractsListForContractResourceForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<ContractResource> resourceTypeList = service.getResourceTypeListForContractResourceForm(obj);
			model.addObject("resourceTypeList", resourceTypeList);
			
			List<ContractResource> subResourceTypeList = service.getSubResourceTypeListForContractResourceForm(obj);
			model.addObject("subResourceTypeList", subResourceTypeList);
			
			List<ContractResource> unitsList = service.getUnitsListForContractResourceForm();
			model.addObject("unitsList", unitsList);			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("contractResourceForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getgetSubResourceTypesForContractResourceForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getSubResourceTypeListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = service.getSubResourceTypeListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubResourceTypeListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForContractResourceForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getProjectsListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = service.getProjectsListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForContractResourceForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getWorkListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = service.getWorkListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForContractResourceForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ContractResource> getContractsListForContractResourceForm(@ModelAttribute ContractResource obj) {
		List<ContractResource> objsList = null;
		try {
			objsList = service.getContractsListForContractResourceForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForContractResourceForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-contract-resource",method=RequestMethod.POST)
	public ModelAndView addResource(@ModelAttribute ContractResource obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/contract-resource-form");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			
			obj.setDate(DateParser.parse(obj.getDate()));
			obj.setCreated_by_user_id(user_Id);
			boolean flag = service.addResource(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Resource added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding Resource is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Adding Resource is failed. Try again.");
			logger.error("addResource : " + e.getMessage());
		}
		return model;
	}
	
}
