package com.synergizglobal.pmis.reference.controller;

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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.SubResourceTypeService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class SubResourceTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SubResourceTypeController.class);
	
	@Autowired
	SubResourceTypeService subResourceTypeService;
	

	@RequestMapping(value="/sub-resource-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subResourceType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.subResourceType);
		try {
			List<TrainingType> resourceTypeList = subResourceTypeService.getRiskResourceType();
			model.addObject("resourceTypeList", resourceTypeList);
			List<TrainingType> subResourceTypeList = subResourceTypeService.getRiskSubResourceType();
			model.addObject("subResourceTypeList", subResourceTypeList);
			TrainingType subResourceTypeDetails = subResourceTypeService.getSubResourceTypeDetails(obj);
			model.addObject("subResourceTypeDetails",subResourceTypeDetails);
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("subResourceType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getResourceType", method = { RequestMethod.GET,RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getResourceType(@ModelAttribute TrainingType obj) {
		List<TrainingType> objList = null;
		try {
			objList = subResourceTypeService.getResourceType(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getResourceType : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/add-sub-resource-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addsubResourceType(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/sub-resource-type");
			boolean flag =  subResourceTypeService.addsubResourceType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Sub resource Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Sub resource Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Sub resource Type is failed. Try again.");
			logger.error("addsubResourceType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-sub-resource-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updatesubResourceType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/sub-resource-type");
			boolean flag =  subResourceTypeService.updatesubResourceType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Sub resource Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Sub resource Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Sub resource Type is failed. Try again.");
			logger.error("updatesubResourceType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-sub-resource-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deletesubResourceType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/sub-resource-type");
			boolean flag =  subResourceTypeService.deletesubResourceType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Sub resource Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deletesubResourceType : " + e.getMessage());
		}
		return model;
	}
	
}
