package com.synergizglobal.wrpmis.reference.controller;

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

import com.synergizglobal.wrpmis.reference.Iservice.RiskAreaService;
import com.synergizglobal.wrpmis.reference.Iservice.RiskSubAreaService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
import com.synergizglobal.wrpmis.constants.PageConstants;

@Controller
public class RiskSubAreaController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskSubAreaController.class);
	
	@Autowired
	RiskSubAreaService riskAreaSubService;
	
	@Autowired
	RiskAreaService riskAreaService;
	
	@RequestMapping(value="/risk-sub-area",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView riskSubArea(HttpSession session,@ModelAttribute TrainingType obj,Risk obj1){
		ModelAndView model = new ModelAndView(PageConstants.riskSubArea);
		try {
			List<Risk> riskSubAreaList = riskAreaSubService.getRiskSubAreasList();
			model.addObject("riskSubAreaList", riskSubAreaList);
			List<Risk> riskAreaList = riskAreaService.getRiskAreasList();
			model.addObject("riskAreaList", riskAreaList);
			TrainingType riskAreaDetails = riskAreaSubService.getRiskSubAreaDetails(obj);
			model.addObject("riskAreaDetails",riskAreaDetails);
			List<TrainingType> areaDetails = riskAreaSubService.getRiskAreaList(obj);
			model.addObject("areaDetails", areaDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("riskSubArea : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-risk-sub-area", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRiskSubArea(@ModelAttribute Risk obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-sub-area");
			boolean flag =  riskAreaSubService.addRiskSubArea(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Sub-Area Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Risk Sub-Area is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Risk Sub-Area is failed. Try again.");
			logger.error("addRiskSubArea : " + e.getMessage());
		}
		return model;
	}
	 
	@RequestMapping(value = "/ajax/getSubAreaDetails", method = { RequestMethod.GET,RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getSubAreaDetails(@ModelAttribute TrainingType obj) {
		List<TrainingType> objList = null;
		try {
			objList = riskAreaSubService.getSubAreaDetails(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubAreaDetails : " + e.getMessage());
		}
		return objList;
	}
	@RequestMapping(value = "/update-risk-sub-area", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRiskSubArea(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-sub-area");
			boolean flag =  riskAreaSubService.updateRiskSubArea(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Sub-Area Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk Sub-Area is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Risk Sub-Area is failed. Try again.");
			logger.error("updateRiskSubArea : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-risk-sub-area", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteRiskSubArea(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk-sub-area");
			boolean flag =  riskAreaSubService.deleteRiskSubArea(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Sub-Area Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRiskSubArea : " + e.getMessage());
		}
		return model;
	}
	
}










