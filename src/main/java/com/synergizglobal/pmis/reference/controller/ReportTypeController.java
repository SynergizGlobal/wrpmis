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

import com.synergizglobal.pmis.reference.Iservice.ReportTypeService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class ReportTypeController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ReportTypeController.class);
	
	@Autowired
	ReportTypeService service;
	
	@RequestMapping(value="/report-type",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView reportType(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.reportType);
		try {
			List<Risk> reportTypeList = service.getReportTypeList();
			model.addObject("reportTypeList", reportTypeList);
			TrainingType reportTypeDetails = service.getReportTypeDetails(obj);
			model.addObject("reportTypeDetails",reportTypeDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("reportType : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/add-report-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addReportType(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/report-type");
			boolean flag =  service.addReportType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Report Type Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Report Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Report Type is failed. Try again.");
			logger.error("addReportType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-report-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateReportType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/report-type");
			boolean flag =  service.updateReportType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Report Type Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Report Type is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Report Type is failed. Try again.");
			logger.error("updateReportType : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-report-type", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteReportType(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/report-type");
			boolean flag =  service.deleteReportType(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Report Type Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteReportType : " + e.getMessage());
		}
		return model;
	}
	
}














