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
			List<TrainingType> risksList = service.getRisksList();
			model.addObject("risksList", risksList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskDelete : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value = "/delete-risk-delete", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRiskClassification(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
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
			logger.error("deleteRiskClassification : " + e.getMessage());
		}
		return model;
	}
	
}
