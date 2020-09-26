package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Safety;

@Controller
public class SafetyController {
	Logger logger = Logger.getLogger(SafetyController.class);	
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	StripChartService stripChartService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/safety",method=RequestMethod.GET)
	public ModelAndView safety(@ModelAttribute Safety obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.safetyGrid);
			/*List<Safety> safety = safetyService.getSafetyList(obj);
			model.addObject("safety", safety);*/
			List<Contract> contracts = stripChartService.getContractsList(null);
			model.addObject("contracts", contracts);
			
		} catch (Exception e) {
			logger.info("safety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getSafetyList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getSafetyList(@ModelAttribute Safety obj) {
		List<Safety> safetyList = null;
		try {
			safetyList = safetyService.getSafetyList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getSafetyList : " + e.getMessage());
		}
		return safetyList;
	}
	
	
	@RequestMapping(value="/add-safety-form",method=RequestMethod.GET)
	public ModelAndView addSafetyForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addSafetyForm);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
		} catch (Exception e) {
			logger.info("addSafetyForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-safety",method=RequestMethod.GET)
	public ModelAndView addSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			boolean flag = safetyService.addSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("addSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety",method=RequestMethod.GET)
	public ModelAndView getSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addSafetyForm);
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-safety",method=RequestMethod.GET)
	public ModelAndView updateSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			boolean flag = safetyService.updateSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("updateSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-safety",method=RequestMethod.GET)
	public ModelAndView deleteSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			boolean flag = safetyService.deleteSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("deleteSafety : " + e.getMessage());
		}
		return model;
	}
}
