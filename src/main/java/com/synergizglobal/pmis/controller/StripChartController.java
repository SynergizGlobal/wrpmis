package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Issue;
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
	
	@Autowired
	IssueService issueService;
	
	
	@RequestMapping(value="/strip-chart",method=RequestMethod.GET)
	public ModelAndView stripChart(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.stripChart);
		try {
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@RequestMapping(value = "/ajax/getStripChartContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartContractsList(@ModelAttribute StripChart obj){
		List<StripChart> contracts = null;
		try{
			contracts = stripChartService.getStripChartContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getStripChartContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getStripChartStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartStructures(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = stripChartService.getStripChartStructures(obj);			
		}catch(Exception e){
			logger.error("getStripChartStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getStripChartLines", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartLines(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = stripChartService.getStripChartLines(obj);			
		}catch(Exception e){
			logger.error("getStripChartLines() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getStripChartSections", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartSections(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = stripChartService.getStripChartSections(obj);			
		}catch(Exception e){
			logger.error("getStripChartSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = stripChartService.getStripChartComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
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
			e.printStackTrace();
			logger.error("getStripChartDetails() : "+e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/ajax/getIssuesCategoryList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesCategoryList(){
		List<Issue> issueCategoryList = null;
		try{
			issueCategoryList = issueService.getIssuesCategoryList();	
		}catch(Exception e){
			logger.error("getIssuesCategoryList() : "+e.getMessage());
		}
		return issueCategoryList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesPriorityList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesPriorityList(){
		List<Issue> issuePriorityList = null;
		try{
			issuePriorityList = issueService.getIssuesPriorityList();	
		}catch(Exception e){
			logger.error("getIssuesPriorityList() : "+e.getMessage());
		}
		return issuePriorityList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesStatusList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesStatusList(){
		List<Issue> issueStatusList = null;
		try{
			issueStatusList = issueService.getIssuesStatusList();	
		}catch(Exception e){
			logger.error("getIssuesStatusList() : "+e.getMessage());
		}
		return issueStatusList;
	}
	
	@RequestMapping(value="/update-stripchart",method=RequestMethod.POST)
	public ModelAndView updateStripChart(@ModelAttribute StripChart obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/strip-chart");
		String user_Id = null;String userName = null;
		try {			
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			
			obj.setProgress_date(DateParser.parse(obj.getProgress_date())); 
			
			MultipartFile file = obj.getStripChartFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants.STRIPCHART_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				saveDirectory = saveDirectory + fileName;
				obj.setAttachment_url(saveDirectory);
			}
			
			obj.setCreated_by_user_id_fk(user_Id);
			
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
