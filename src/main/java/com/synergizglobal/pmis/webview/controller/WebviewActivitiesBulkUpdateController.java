package com.synergizglobal.pmis.webview.controller;

import java.io.IOException;
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

import com.synergizglobal.pmis.Iservice.ActivitiesBulkUpdateService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.controller.HomeController;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewActivitiesBulkUpdateController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewActivitiesBulkUpdateController.class);
	
	@Autowired
	ActivitiesBulkUpdateService activitiesBulkUpdateService;


	@RequestMapping(value="/activities-bulk-update",method=RequestMethod.GET)
	public ModelAndView AcivitiesBulkUpload(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(MobilePageConstants2.activitiesBulkUpdate);
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<StripChart> projectsList = activitiesBulkUpdateService.getAcivitiesBulkUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = activitiesBulkUpdateService.getAcivitiesBulkUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			List<StripChart> contractsList = activitiesBulkUpdateService.getAcivitiesBulkUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AcivitiesBulkUpload : " + e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateProjectsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateProjectsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> projects = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			projects = activitiesBulkUpdateService.getAcivitiesBulkUpdateProjectsList(obj);			
		}catch(Exception e){
			logger.error("getAcivitiesBulkUpdateProjectsList() : "+e.getMessage());
		}
		return projects;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateWorksList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = activitiesBulkUpdateService.getAcivitiesBulkUpdateWorksList(obj);			
		}catch(Exception e){
			logger.error("getAcivitiesBulkUpdateWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateContractsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> contracts = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			contracts = activitiesBulkUpdateService.getAcivitiesBulkUpdateContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkUpdateContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = activitiesBulkUpdateService.getAcivitiesBulkUpdateStructures(obj);			
		}catch(Exception e){
			logger.error("getAcivitiesBulkUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateLines", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateLines(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = activitiesBulkUpdateService.getAcivitiesBulkUpdateLines(obj);			
		}catch(Exception e){
			logger.error("getAcivitiesBulkUpdateLines() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateSections", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateSections(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = activitiesBulkUpdateService.getAcivitiesBulkUpdateSections(obj);			
		}catch(Exception e){
			logger.error("getAcivitiesBulkUpdateSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateComponentsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateComponentsList(@ModelAttribute StripChart obj){
		List<StripChart> components = null;
		try{
			components = activitiesBulkUpdateService.getAcivitiesBulkUpdateComponentsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkUpdateComponentsList() : "+e.getMessage());
		}
		return components;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = activitiesBulkUpdateService.getAcivitiesBulkUpdateComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkUpdateComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkActivitiesList(@ModelAttribute StripChart obj){
		List<StripChart> activities = null;
		try{
			activities = activitiesBulkUpdateService.getAcivitiesBulkActivitiesList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StripChart getAcivitiesBulkUpdateDetails(@ModelAttribute StripChart obj){
		StripChart data = null;
		try{
			data = activitiesBulkUpdateService.getAcivitiesBulkUpdateDetails(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkUpdateDetails() : "+e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/ajax/getActivitiesfiltersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getActivitiesfiltersList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> fileterData = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			fileterData = activitiesBulkUpdateService.getActivitiesfiltersList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getActivitiesfiltersList() : "+e.getMessage());
		}
		return fileterData;
	}
	

	@RequestMapping(value = "/update-activities-bulk", method = {RequestMethod.POST})
	public ModelAndView updateAcivitiesBulk(@ModelAttribute StripChart obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;
		try{
			model.setViewName("redirect:/mobileappwebview/activities-bulk-update");
			userId = (String) session.getAttribute("USER_ID");
			obj.setCreated_by_user_id_fk(userId);
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			//obj.setProgress_date(DateParser.parse(obj.getProgress_date()));
			boolean flag =  activitiesBulkUpdateService.updateAcivitiesBulk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Acivities are Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Acivities are failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Acivities are failed. Try again.");
			logger.error("updateAcivitiesBulk : " + e.getMessage());
		}
		return model;
	}
}


