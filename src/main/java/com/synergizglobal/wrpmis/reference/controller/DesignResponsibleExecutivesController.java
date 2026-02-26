package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.User;
import com.synergizglobal.wrpmis.reference.Iservice.DesignResponsibleExecutivesService;
import com.synergizglobal.wrpmis.reference.Iservice.RrResponsibleExecutivesService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Controller
public class DesignResponsibleExecutivesController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(DesignResponsibleExecutivesController.class);
	@Autowired
	RrResponsibleExecutivesService service;
	
	
	@Autowired
	DesignResponsibleExecutivesService mainService;
	
	@RequestMapping(value="/design-executives",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView executives(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.designExecutives);
		try {
			
			List<TrainingType> executivesDetails = mainService.getExecutivesDetails(obj);
			model.addObject("executivesDetails",executivesDetails);
			
			List<TrainingType> usersDetails = mainService.getUsersDetails(obj);
			model.addObject("usersDetails",usersDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("executives : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorkWiseDesignResponsibleUsers", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getWorkWiseDesignResponsibleUsers(@ModelAttribute TrainingType obj,HttpSession session) {
		List<TrainingType> contractorsFilterList = null;  
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			contractorsFilterList = mainService.getUsersDetails(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorsFilterList : " + e.getMessage());
		}
		return contractorsFilterList;
	}	

	
	@RequestMapping(value = "/add-design-executives", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addDesignExecutives(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/design-executives");
			boolean flag =  mainService.addDesignExecutives(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Executives Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Executives is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Executives is failed. Try again.");
			logger.error("addDesignExecutives : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-design-executives", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateDesignExecutives(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/design-executives");
			boolean flag =  mainService.updateDesignExecutives(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Executives Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Executives is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Executives is failed. Try again.");
			logger.error("updateDesignExecutives : " + e.getMessage());
		}
		return model;
	}
}
