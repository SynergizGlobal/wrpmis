package com.synergizglobal.pmis.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.IssueService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Controller
public class ActivitiesController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	ActivitiesService activitiesService;
	
	@Autowired
	IssueService issueService;
	
	
	@RequestMapping(value="/activity-progress",method=RequestMethod.GET)
	public ModelAndView activities(@ModelAttribute StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.activitiesProgress);
		try {
			List<StripChart> projectsList = activitiesService.getActivitiesProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = activitiesService.getActivitiesWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<StripChart> contractsList = activitiesService.getActivitiesContractsList(obj);
			model.addObject("contractsList", contractsList);
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStrip_chart_id())) {
				StripChart activitiesData = activitiesService.getActivitiesData(obj);
				model.addObject("activitiesData", activitiesData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("activities : " + e.getMessage());
		}
		return model;
	}
	

	
	@RequestMapping(value="/activity-progress/{activityId}",method=RequestMethod.GET)
	public ModelAndView getActivitiesData(@PathVariable("activityId")String activityId,@ModelAttribute StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.activitiesProgress);
		try {
			
			List<StripChart> projectsList = activitiesService.getActivitiesProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			/*List<StripChart> worksList = activitiesService.getActivitiesWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<StripChart> contractsList = activitiesService.getActivitiesContractsList(obj);
			model.addObject("contractsList", contractsList);*/
			
			obj.setActivity_id(activityId);
			StripChart activitiesData = activitiesService.getActivitiesData(obj);
			model.addObject("activitiesData", activitiesData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getActivitiesData : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesProjectsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesProjectsList(@ModelAttribute StripChart obj){
		List<StripChart> projects = null;
		try{
			projects = activitiesService.getActivitiesProjectsList(obj);			
		}catch(Exception e){
			logger.error("getActivitiesProjectsList() : "+e.getMessage());
		}
		return projects;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesWorksList(@ModelAttribute StripChart obj){
		List<StripChart> works = null;
		try{
			works = activitiesService.getActivitiesWorksList(obj);			
		}catch(Exception e){
			logger.error("geStripCharttWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesContractsList(@ModelAttribute StripChart obj){
		List<StripChart> contracts = null;
		try{
			contracts = activitiesService.getActivitiesContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivitiesContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesStructures(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = activitiesService.getActivitiesStructures(obj);			
		}catch(Exception e){
			logger.error("getActivitiesStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesLines", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesLines(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = activitiesService.getActivitiesLines(obj);			
		}catch(Exception e){
			logger.error("getActivitiesLines() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesSections", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesSections(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = activitiesService.getActivitiesSections(obj);			
		}catch(Exception e){
			logger.error("getActivitiesSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getActivityComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = activitiesService.getActivitiesComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivitiesComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesActivitiesList(@ModelAttribute StripChart obj){
		List<StripChart> activities = null;
		try{
			activities = activitiesService.getActivitiesActivities(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivitiesActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StripChart getActivitiesDetails(@ModelAttribute StripChart obj){
		StripChart data = null;
		try{
			data = activitiesService.getActivitiesDetails(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivitiesDetails() : "+e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/ajax/getIssuesCategoryListForActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesCategoryList(){
		List<Issue> issueCategoryList = null;
		try{
			issueCategoryList = activitiesService.getIssuesCategoryList();	
		}catch(Exception e){
			logger.error("getIssuesCategoryList() : "+e.getMessage());
		}
		return issueCategoryList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesPriorityListForActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesPriorityList(){
		List<Issue> issuePriorityList = null;
		try{
			issuePriorityList = activitiesService.getIssuesPriorityList();	
		}catch(Exception e){
			logger.error("getIssuesPriorityList() : "+e.getMessage());
		}
		return issuePriorityList;
	}
	
	@RequestMapping(value = "/ajax/getIssuesStatusListForActivities", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Issue> getIssuesStatusList(){
		List<Issue> issueStatusList = null;
		try{
			issueStatusList = activitiesService.getIssuesStatusList();	
		}catch(Exception e){
			logger.error("getIssuesStatusList() : "+e.getMessage());
		}
		return issueStatusList;
	}
	
	@RequestMapping(value="/update-activity-progress",method=RequestMethod.POST)
	public ModelAndView updateActivities(@ModelAttribute StripChart obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/activity-progress");
		String user_Id = null;String userName = null;
		try {			
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			User uObj = (User) session.getAttribute("user");
			
			obj.setProgress_date(DateParser.parse(obj.getProgress_date())); 
			
			MultipartFile file = obj.getStripChartFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH + "ACTIVITIES_PROGRESS_"+obj.getActivity_id() + File.separator;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setAttachment_url(fileName);
			}
			
			obj.setCreated_by_user_id_fk(user_Id);
			
			if(!StringUtils.isEmpty(uObj)) {
				obj.setReported_by(uObj.getDesignation());
			}
			
			boolean flag = activitiesService.updateActivities(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Activities are updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.error("updateStripChart : " + e.getMessage());
		}
		return model;
	}
	

}

