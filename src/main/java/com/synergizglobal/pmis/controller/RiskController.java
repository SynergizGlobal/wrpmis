package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.synergizglobal.pmis.Iservice.RiskService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.Work;

@Controller
public class RiskController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(RiskController.class);
	
	@Autowired
	RiskService riskService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${record.dataexport.success}")
	public String dataExportSucess;
	
	@Value("${record.dataexport.invalid.directory}")
	public String dataExportInvalid;
	
	@Value("${record.dataexport.error}")
	public String dataExportError;
	
	@Value("${record.dataexport.nodata}")
	public String dataExportNoData;
	
	
	@RequestMapping(value="/risk",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView risk(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.riskGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("risk : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-risk-list", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getRiskList(@ModelAttribute Risk obj) {
		List<Risk> riskList = null;
		try {
			riskList = riskService.getRiskList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRiskList : " + e.getMessage());
		}
		return riskList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getWorksList(@ModelAttribute Risk obj) {
		List<Risk> worksList = null;
		try {
			worksList = riskService.getRisktWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getAreasFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAreasList(@ModelAttribute Risk obj) {
		List<Risk> areaList = null;
		try {
			areaList = riskService.getRiskAreasList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAreasList : " + e.getMessage());
		}
		return areaList;
	}
	
	@RequestMapping(value = "/ajax/getPrioritiesFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getPrioritiesList(@ModelAttribute Risk obj) {
		List<Risk> priotityList = null;
		try {
			priotityList = riskService.getRiskPriotityList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getPrioritiesList : " + e.getMessage());
		}
		return priotityList;
	}
	
	@RequestMapping(value = "/ajax/getClassificationsFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getClassificationsList(@ModelAttribute Risk obj) {
		List<Risk> clasificationList = null;
		try {
			clasificationList = riskService.getRiskClassificationsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getClassificationsList : " + e.getMessage());
		}
		return clasificationList;
	}
	
	@RequestMapping(value = "/ajax/getResponsiblePersonsFilterListInRisk", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getResponsiblePersonsList(@ModelAttribute Risk obj) {
		List<Risk> responsiblePersons = null;
		try {
			responsiblePersons = riskService.getRiskResponsiblePersonsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getClassificationsList : " + e.getMessage());
		}
		return responsiblePersons;
	}
	
	
	@RequestMapping(value = "/add-risk-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addRiskForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRiskForm);
			model.addObject("action", "add");
		
			List<Risk> projectsList = riskService.getProjectList();
			model.addObject("projectsList", projectsList);
			List<Risk> prioritiesList = riskService.getPrioritiesList();
			model.addObject("prioritiesList", prioritiesList);
			List<Risk> areasList = riskService.getAreasList();
			model.addObject("areasList", areasList);
			List<Risk> subAreasList = riskService.getSubAreasList();
			model.addObject("subAreasList", subAreasList);
			
		}catch (Exception e) {
				logger.error("addRiskForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/ajax/getSubAreasList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getSubAreasList(@ModelAttribute Risk obj){
		List<Risk> subAreas = null;
		try{
			subAreas = riskService.getSubAreasList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getSubAreasList() : "+e.getMessage());
		}
		return subAreas;
	}
	
	@RequestMapping(value = "/ajax/getAreasList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Risk> getAreaList(@ModelAttribute Risk obj){
		List<Risk> areasList = null;
		try{
			areasList = riskService.getAreaList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAreasList() : "+e.getMessage());
		}
		return areasList;
	}
	
	@RequestMapping(value = "/get-risk", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getRisk(@ModelAttribute Risk obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditRiskForm);
			model.addObject("action", "edit");
			List<Risk> projectsList = riskService.getProjectList();
			model.addObject("projectsList", projectsList);
			List<Risk> prioritiesList = riskService.getPrioritiesList();
			model.addObject("prioritiesList", prioritiesList);
			List<Risk> areasList = riskService.getAreasList();
			model.addObject("areasList", areasList);
			List<Risk> subAreasList = riskService.getSubAreasList();
			model.addObject("subAreasList", subAreasList);
			Risk riskDetails = riskService.getRiskDetails(obj);
			model.addObject("riskDetails", riskDetails);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getRisk : " + e.getMessage());
		}
		return model;
     }
	
	@RequestMapping(value = "/add-risk", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addRisk(@ModelAttribute Risk obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			model.setViewName("redirect:/risk");
			
			obj.setDate_of_identification(DateParser.parseDateTime(obj.getDate_of_identification()));
			obj.setDate(DateParser.parse(obj.getDate()));
			obj.setAtr_date(DateParser.parse(obj.getAtr_date()));
			
			boolean flag =  riskService.addRisk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Risk is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Risk is failed. Try again.");
			logger.error("addRisk : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-risk", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateRisk(@ModelAttribute Risk obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/risk");
			obj.setDate_of_identification(DateParser.parseDateTime(obj.getDate_of_identification()));
			obj.setDate(DateParser.parse(obj.getDate()));
			obj.setAtr_date(DateParser.parse(obj.getAtr_date()));
			
			boolean flag =  riskService.updateRisk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Risk Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Risk is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Risk is failed. Try again.");
			logger.error("updateRisk : " + e.getMessage());
		}
		return model;
	}
}
