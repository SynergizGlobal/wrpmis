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

import com.synergizglobal.wrpmis.reference.Iservice.DivisionService;
import com.synergizglobal.wrpmis.reference.Iservice.SectionService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class SectionController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(SectionController.class);
	
	@Autowired
	SectionService service;
	
	@Autowired
	DivisionService dservice;	
	
	@RequestMapping(value="/section",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView section(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.section);
		try {
			List<TrainingType> sectionList = service.getSectionsList();
			model.addObject("SectionList", sectionList);
			TrainingType sectionDetails = service.getSectionDetails(obj);
			model.addObject("SectionDetails", sectionDetails);
			List<TrainingType> divisionList = dservice.getDivisionsList();
			model.addObject("DivisionList", divisionList);			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Section : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-section", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addsection(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/section");
			boolean flag =  service.addSection(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Section Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding section is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding section is failed. Try again.");
			logger.error("addsection : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-section", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updatesection(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/section");
			boolean flag =  service.updateSection(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Section Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating section is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating section is failed. Try again.");
			logger.error("updatesection : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-section", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deletesection(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/section");
			boolean flag =  service.deleteSection(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Section Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deletesection : " + e.getMessage());
		}
		return model;
	}
	
}



