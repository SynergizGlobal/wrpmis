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

import com.synergizglobal.pmis.reference.Iservice.RRApprovalStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class RRApprovalStatusController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RRApprovalStatusController.class);
	
	@Autowired
	RRApprovalStatusService service;
	
	@RequestMapping(value="/rr-approval-status",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rrApprovalStatus(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.rrApprovalStatus);
		try {
			List<Safety> rrApprovalStatusList = service.getRRApprovalStatusList();
			model.addObject("rrApprovalStatusList", rrApprovalStatusList);
			TrainingType rrApprovalStatusDetails = service.getRApprovalStatusDetails(obj);
			model.addObject("rrApprovalStatusDetails",rrApprovalStatusDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("rrApprovalStatus : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-rr-approval-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRRApprovalStatus(@ModelAttribute Safety obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-approval-status");
			boolean flag =  service.addRRApprovalStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Approval Status Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","RR Adding Approval Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","RR Adding Approval Status is failed. Try again.");
			logger.error("addRRApprovalStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-rr-approval-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRRApprovalStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-approval-status");
			boolean flag =  service.updateRRApprovalStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Approval Status Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating RR Approval Status is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating RR Approval Status is failed. Try again.");
			logger.error("updateRApprovalStatus : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-rr-approval-status", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRRApprovalStatus(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/rr-approval-status");
			boolean flag =  service.deleteRRApprovalStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "RR Approval Status Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRApprovalStatus : " + e.getMessage());
		}
		return model;
	}
	
}






