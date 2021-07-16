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
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.reference.Iservice.RiskDeleteService;
import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class RiskDeleteController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskDeleteController.class);
	
	@Autowired
	RiskDeleteService service;
	
	@RequestMapping(value="/risk-delete",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskDelete(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.riskDelete);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskDelete : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getRisksListInRiskDelete", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getRisksList(@ModelAttribute TrainingType obj) {
		List<TrainingType> dataList = null;  
		try {
			dataList = service.getRisksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRisksList : " + e.getMessage());
		}
		return dataList;
	}
	@RequestMapping(value = "/ajax/getSubWorkFilterListInRiskDelete", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getSubWorkFilterList(@ModelAttribute TrainingType obj) {
		List<TrainingType> dataList = null;  
		try {
			dataList = service.getSubWorkFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubWorkFilterList : " + e.getMessage());
		}
		return dataList;
	}
	
	@RequestMapping(value = "/delete-risk", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRisk(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-delete");
			boolean flag =  service.deleteRisk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRisk : " + e.getMessage());
		}
		return model;
	}
	
}
