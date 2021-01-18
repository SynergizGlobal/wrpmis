package com.synergizglobal.pmis.controller;

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

import com.synergizglobal.pmis.Iservice.LandAcquisitionService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.LandAcquisition;

@Controller
public class LandAcquisitionController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(LandAcquisitionController.class);
	
	@Autowired
	LandAcquisitionService service;
	
	@RequestMapping(value="/land-acquisition",method={RequestMethod.GET})
	public ModelAndView landAcquisition(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("landAcquisition : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/get-land-acquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getLandAcquisitionList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> landAcquisitionList = null;
		try {
			landAcquisitionList = service.getLandAcquisitionList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getLandAcquisitionList : " + e.getMessage());
		}
		return landAcquisitionList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getWorksList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> worksList = null;
		try {
			worksList = service.getLandAcquisitionWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getVillagesFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getVillagesList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> villagesList = null;
		try {
			villagesList = service.getLandAcquisitionVillagesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getVillagesList : " + e.getMessage());
		}
		return villagesList;
	}
	
	@RequestMapping(value = "/ajax/getTypesOfLandsFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getTypesOfLandsList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> typesOfLandsList = null;
		try {
			typesOfLandsList = service.getLandAcquisitionTypesOfLandsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTypesOfLandsList : " + e.getMessage());
		}
		return typesOfLandsList;
	}
	
	@RequestMapping(value = "/ajax/getSubCategoryFilterListInLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<LandAcquisition> getSubCategoryList(@ModelAttribute LandAcquisition obj) {
		List<LandAcquisition> subCategoryList = null;
		try {
			subCategoryList = service.getLandAcquisitionSubCategoryList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubCategoryList : " + e.getMessage());
		}
		return subCategoryList;
	}
}
