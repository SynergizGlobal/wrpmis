package com.synergizglobal.pmis.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.Work;

@Controller
public class StripChartController {
	
	Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	StripChartService stripChartService;
	
	@Autowired
	HomeService homeService;
	
	
	@RequestMapping(value="/strip-chart",method=RequestMethod.GET)
	public ModelAndView stripChart(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.stripChart);
		try {
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			
			/*List<Contract> contractsList = stripChartService.getContractsList(null);
			model.addObject("contractsList", contractsList);*/
			
			List<StripChart> structuresList = stripChartService.getStripChartStructures();
			model.addObject("structuresList", structuresList);
			
			List<StripChart> linesList = stripChartService.getStripChartLines();
			model.addObject("linesList", linesList);
			
			List<StripChart> sectionsList = stripChartService.getStripChartSections();
			model.addObject("sectionsList", sectionsList);	
			
			/*List<StripChart> componentsList = stripChartService.getStripChartComponents(null);
			model.addObject("componentsList", componentsList);
			
			List<StripChart> activitiesList = stripChartService.getStripChartActivities(null);
			model.addObject("activitiesList", activitiesList);*/
			
		} catch (Exception e) {
			logger.info("StripChart : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Work> getWorksList(@ModelAttribute Work obj){
		List<Work> works = null;
		try{
			works = homeService.getWorksList(obj);			
		}catch(Exception e){
			logger.error("getWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Contract> getContractsList(@ModelAttribute StripChart obj){
		List<Contract> contracts = null;
		try{
			contracts = stripChartService.getContractsList(obj);			
		}catch(Exception e){
			logger.error("getContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = stripChartService.getStripChartComponentIds(obj);			
		}catch(Exception e){
			logger.error("getStripChartComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}
	
	@RequestMapping(value = "/ajax/getStripChartActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartActivitiesList(@ModelAttribute StripChart obj){
		List<StripChart> activities = null;
		try{
			activities = stripChartService.getStripChartActivities(obj);	
		}catch(Exception e){
			logger.error("getStripChartActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getStripChartDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StripChart getStripChartDetails(@ModelAttribute StripChart obj){
		StripChart data = null;
		try{
			data = stripChartService.getStripChartDetails(obj);	
		}catch(Exception e){
			logger.error("getStripChartDetails() : "+e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value="/update-stripchart",method=RequestMethod.POST)
	public ModelAndView updateStripChart(@ModelAttribute StripChart obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/risk");
		try {			
			MultipartFile file = obj.getStripChartFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.STRIPCHART_FILE_SAVING_PATH + File.separator ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
			}
			
			boolean flag = stripChartService.updateStripChart(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Strip Chart updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateStripChart : " + e.getMessage());
		}
		return model;
	}

}
