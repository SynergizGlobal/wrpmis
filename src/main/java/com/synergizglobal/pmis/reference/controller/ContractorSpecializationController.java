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

import com.synergizglobal.pmis.reference.Iservice.ContractorSpecializationService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class ContractorSpecializationController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ContractorSpecializationController.class);
	
	@Autowired
	ContractorSpecializationService service;
	
	@RequestMapping(value="/contractor-specialization",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView contractorSpecialization(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.contractorSpecialization);
		try {
			List<TrainingType> contractorSpecializationList = service.getContractorSpecializationsList();
			model.addObject("contractorSpecializationList", contractorSpecializationList);
			TrainingType contractorSpecializationDetails = service.getContractorSpecializationDetails(obj);
			model.addObject("contractorSpecializationDetails", contractorSpecializationDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("contractorSpecialization : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-contractor-specialization", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addContractorSpecialization(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor-specialization");
			boolean flag =  service.addContractorSpecialization(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Specialization Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Contractor Specialization is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Contractor Specialization is failed. Try again.");
			logger.error("addContractorSpecialization : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-contractor-specialization", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateContractorSpecialization(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor-specialization");
			boolean flag =  service.updateContractorSpecialization(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Specialization Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Contractor Specialization is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Contractor Specialization is failed. Try again.");
			logger.error("updateContractorSpecialization : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-contractor-specialization", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteContractorSpecialization(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/contractor-specialization");
			boolean flag =  service.deleteContractorSpecialization(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Contractor Specialization Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteContractorSpecialization : " + e.getMessage());
		}
		return model;
	}
	
}





