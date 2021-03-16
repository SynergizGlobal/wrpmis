package com.synergizglobal.pmis.reference.controller;

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

import com.synergizglobal.pmis.reference.Iservice.RRLandTypeService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class RRLandTypeController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRLandTypeController.class);
	
	@Autowired
	RRLandTypeService service;
	
	@RequestMapping(value="/rr-land-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrLandType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrLandType);
		try {
			List<Safety> rrLandTypeList = service.getRRLandTypeList();
			model.addObject("rrLandTypeList", rrLandTypeList);
			TrainingType rrLandTypeDetails = service.getRRLandTypeDetails(obj);
			model.addObject("rrLandTypeDetails",rrLandTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("rrLandType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-land-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRLandType(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-land-type");
			boolean flag =  service.addRRLandType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Land Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Land Type is failed. Try again.");
			logger.error("addRRLandType : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-land-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRLandType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-land-type");
			boolean flag =  service.updateRRLandType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Land Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating  Land Type is failed. Try again.");
			logger.error("updateRRLandType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-land-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRLandType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-land-type");
			boolean flag =  service.deleteRRLandType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", " Land Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRLandType : " + e.getMessage());
		}
		return model;
	}
	
}



