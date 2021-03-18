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

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.ZonalRailwayFundService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class ZonalRailwayFundController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ZonalRailwayFundController.class);
	
	@Autowired
	ZonalRailwayFundService service;
	
	@RequestMapping(value="/zonal-railway-funds",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView zonalRailwayFund(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.zonalRailwayFunds);
		try {
			
			TrainingType zonalRailwayDetails = service.getZonalRailwayDetails(obj);
			model.addObject("zonalRailwayDetails",zonalRailwayDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("zonalRailwayFund : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-zonal-railway-funds", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addZonalRailwayFund(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway-funds");
			boolean flag =  service.addZonalRailwayFund(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway fund Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("addZonalRailwayFund : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-zonal-railway-funds", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateZonalRailwayFund(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway-funds");
			boolean flag =  service.updateZonalRailwayFund(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway fund Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("updateZonalRailwayFund : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-zonal-railway-funds", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteZonalRailwayFund(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/zonal-railway-funds");
			boolean flag =  service.deleteZonalRailwayFund(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Zonal Railway fund Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteZonalRailwayFund : " + e.getMessage());
		}
		return model;
	}
	
}










