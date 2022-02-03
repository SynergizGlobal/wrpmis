package com.synergizglobal.pmis.reference.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.Iservice.RRBoundarywallStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RRBoundarywallStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRBoundarywallStatusController.class);
	
	@Autowired
	RRBoundarywallStatusService service;
	
	@RequestMapping(value="/rr-boundary-wall-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrBoundarywallStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrBoundarywallStatus);
		try {
			
			TrainingType rrBoundarywallStatusDetails = service.getRRBoundarywallStatusDetails(obj);
			model.addObject("rrBoundarywallStatusDetails",rrBoundarywallStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("RRBoundarywallStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-boundary-wall-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRBoundarywallStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-boundary-wall-status");
			boolean flag =  service.addRRBoundarywallStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Boundary Wall Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Boundary Wall Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Boundary Wall Status is failed. Try again.");
			logger.error("addRRBoundarywallStatus : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/update-rr-boundary-wall-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRBoundarywallStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-boundary-wall-status");
			boolean flag =  service.updateRRBoundarywallStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Boundary Wall Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Boundary Wall Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Boundary Wall Status is failed. Try again.");
			logger.error("updateRRBoundarywallStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-boundary-wall-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRBoundarywallStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-boundary-wall-status");
			boolean flag =  service.deleteRRBoundarywallStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Boundary Wall Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRRBoundarywallStatus : " + e.getMessage());
		}
		return model;
	}
	
}







