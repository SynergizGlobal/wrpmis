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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.WorkDetailsService;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.WorkFeatures;

@Controller
public class WorkDetailsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WorkDetailsController.class);
	
	@Autowired
	WorkDetailsService service;
	
	@RequestMapping(value="/work-details",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workDetails(HttpSession session,@ModelAttribute WorkFeatures obj){
		ModelAndView model = new ModelAndView(PageConstants2.workDetails);
		try {
			List<WorkFeatures> worksList = service.getWorksListFilter(obj);
			model.addObject("worksList",worksList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("workDetails :" + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorkDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkFeatures> getWorkDetailsList(@ModelAttribute WorkFeatures obj) {
		List<WorkFeatures> objList = null;
		try {
			objList = service.getWorkDetailsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkDetailsList : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value = "/add-work-details-form", method = {RequestMethod.GET})
	public ModelAndView addWorkDetailsForm(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants2.addEditWorkDetails);
		try{
			
			model.addObject("action","add");
			
			List<WorkFeatures> worksList = service.getWorksList(obj);
			model.addObject("worksList",worksList);
			
			List<WorkFeatures> titlesList = service.getTitlesList(obj);
			model.addObject("titlesList",titlesList);
			
			List<WorkFeatures> statusList = service.getStatusList(obj);
			model.addObject("statusList",statusList);
			
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Failed. Try again.");
			logger.error("addWorkDetailsForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-work-details", method = {RequestMethod.POST})
	public ModelAndView addWorkDetails(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-details");
			boolean flag =  service.addWorkDetails(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "WorkDetails Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding WorkDetails is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding WorkDetails is failed. Try again.");
			logger.error("addWorkDetails : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value="/get-work-details",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkInfo(HttpSession session,@ModelAttribute WorkFeatures obj){
		ModelAndView model = new ModelAndView(PageConstants2.addEditWorkDetails);
		try {
			
			model.addObject("action","edit");
			
			List<WorkFeatures> worksList = service.getWorksList(obj);
			model.addObject("worksList",worksList);
			
			List<WorkFeatures> titlesList = service.getTitlesList(obj);
			model.addObject("titlesList",titlesList);
			
			List<WorkFeatures> statusList = service.getStatusList(obj);
			model.addObject("statusList",statusList);
			
			List<WorkFeatures> workDetails = service.getWorkDetails(obj);
			model.addObject("workDetails",workDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkInfo :" + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-work-details", method = {RequestMethod.POST})
	public ModelAndView updateWorkDetails(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-details");
			boolean flag =  service.updateWorkDetails(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "WorkDetails Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating WorkDetails is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating WorkDetails is failed. Try again.");
			logger.error("updateWorkDetails : " + e.getMessage());
		}
		return model;
	}
	
	/****************************************************************************************************************/
	
	@RequestMapping(value="/work-salient-features",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workSalientFeatures(HttpSession session,@ModelAttribute WorkFeatures obj){
		ModelAndView model = new ModelAndView(PageConstants2.workSalientFeatures);
		try {
			List<WorkFeatures> worksList = service.getWorksListFilterForSalientFeatures(obj);
			model.addObject("worksList",worksList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("workSalientFeatures :" + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorkSalientFeatures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WorkFeatures> getWorkSalientFeatures(@ModelAttribute WorkFeatures obj) {
		List<WorkFeatures> objList = null;
		try {
			objList = service.getWorkSalientFeatures(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkSalientFeatures : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value = "/add-work-salient-features-form", method = {RequestMethod.GET})
	public ModelAndView addWorkSalientFeaturesForm(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView(PageConstants2.addEditWorkSalientFeatures);
		try{
			
			model.addObject("action","add");
			
			List<WorkFeatures> worksList = service.getWorksList(obj);
			model.addObject("worksList",worksList);
			
			List<WorkFeatures> categoryList = service.getCategoryList(obj);
			model.addObject("categoryList",categoryList);
			
			List<WorkFeatures> statusList = service.getStatusList(obj);
			model.addObject("statusList",statusList);
			
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Failed. Try again.");
			logger.error("addWorkSalientFeaturesForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-work-salient-features", method = {RequestMethod.POST})
	public ModelAndView addWorkSalientFeatures(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-salient-features");
			boolean flag =  service.addWorkSalientFeatures(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "WorkSalientFeatures Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding WorkSalientFeatures is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding WorkSalientFeatures is failed. Try again.");
			logger.error("addWorkSalientFeatures : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value="/get-work-salient-features",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWorkSalientFeatures(HttpSession session,@ModelAttribute WorkFeatures obj){
		ModelAndView model = new ModelAndView(PageConstants2.addEditWorkSalientFeatures);
		try {
			
			model.addObject("action","edit");
			
			List<WorkFeatures> worksList = service.getWorksList(obj);
			model.addObject("worksList",worksList);
			
			List<WorkFeatures> categoryList = service.getCategoryList(obj);
			model.addObject("categoryList",categoryList);
			
			List<WorkFeatures> statusList = service.getStatusList(obj);
			model.addObject("statusList",statusList);
			
			List<WorkFeatures> workSalientFeatures = service.getWorkSalientFeatures(obj);
			model.addObject("workSalientFeatures",workSalientFeatures);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkSalientFeatures :" + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-work-salient-features", method = {RequestMethod.POST})
	public ModelAndView updateWorkSalientFeatures(@ModelAttribute WorkFeatures obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work-salient-features");
			boolean flag =  service.updateWorkSalientFeatures(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "WorkSalientFeatures Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating WorkSalientFeatures is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating WorkSalientFeatures is failed. Try again.");
			logger.error("updateWorkSalientFeatures : " + e.getMessage());
		}
		return model;
	}
	
	/****************************************************************************************************************************************/
}
