package com.synergizglobal.wrpmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.reference.Iservice.StructureTypeService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class StructureTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(StructureTypeController.class);
	
	@Autowired
	StructureTypeService service;
	
	@RequestMapping(value="/structure-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView structureType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.structureType);
		try {
			List<Safety> structureType = service.getStructureTypesList();
			model.addObject("structureType", structureType);
			TrainingType structureTypeDetails = service.getStructureTypeDetails(obj);
			model.addObject("structureTypeDetails",structureTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("structureType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-structure-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addStructureType(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-type");
			boolean flag =  service.addStructureType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Structure Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Structure Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Structure Type is failed. Try again.");
			logger.error("addStructureType : " + e.getMessage());
		}
		return model;
	}


	@RequestMapping(value = "/update-structure-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateStructureType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-type");
			boolean flag =  service.updateStructureType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Structure Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Structure Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Structure Type is failed. Try again.");
			logger.error("updateStructureType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-structure-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteStructureType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/structure-type");
			boolean flag =  service.deleteStructureType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Structure Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteStructureType : " + e.getMessage());
		}
		return model;
	}
	
}




