package com.synergizglobal.pmis.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.NewActivitiesUpdateService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.StripChart;
import com.synergizglobal.pmis.model.User;

@Controller
public class NewActivitiesUpdateController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	NewActivitiesUpdateService newActivitiesUpdateService;


	@RequestMapping(value="/new-activities-update",method=RequestMethod.GET)
	public ModelAndView newAcivitiesUpdate(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.newActivitiesUpdate);
		try {
			
			User uObj = (User) session.getAttribute("user");
			if(!StringUtils.isEmpty(uObj)) {
				obj.setUser_type_fk(uObj.getUser_type_fk());
				obj.setUser_role_code(uObj.getUser_role_code());
				obj.setUser_id(uObj.getUser_id());
				obj.setDepartment_fk(uObj.getDepartment_fk());
			}
			
			List<StripChart> projectsList = newActivitiesUpdateService.getNewActivitiesUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = newActivitiesUpdateService.getNewActivitiesUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			List<StripChart> contractsList = newActivitiesUpdateService.getNewActivitiesUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AcivitiesBulkUpload : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/new-activities-update/{activity_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView newAcivitiesBulkUpdate(@PathVariable("activity_id")String activity_id,@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.newActivitiesUpdate);
		try {
			User uObj = (User) session.getAttribute("user");
			if(!StringUtils.isEmpty(uObj)) {
				obj.setUser_type_fk(uObj.getUser_type_fk());
				obj.setUser_role_code(uObj.getUser_role_code());
				obj.setUser_id(uObj.getUser_id());
				obj.setDepartment_fk(uObj.getDepartment_fk());
			}
			
			List<StripChart> projectsList = newActivitiesUpdateService.getNewActivitiesUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			/*List<StripChart> worksList = newActivitiesUpdateService.getNewActivitiesUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			
			List<StripChart> contractsList = newActivitiesUpdateService.getNewActivitiesUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);*/
			
			obj.setActivity_id(activity_id);
			StripChart activitiesData = newActivitiesUpdateService.getNewAcivitiesUpdateData(obj);
			model.addObject("activitiesData", activitiesData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AcivitiesBulkUpload : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateProjectsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateProjectsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> projects = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			projects = newActivitiesUpdateService.getNewActivitiesUpdateProjectsList(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateProjectsList() : "+e.getMessage());
		}
		return projects;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateWorksList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = newActivitiesUpdateService.getNewActivitiesUpdateWorksList(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesUpdateWorksList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = newActivitiesUpdateService.getWorksList(obj);			
		}catch(Exception e){
			logger.error("getDeleteActivitiesUpdateWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesUpdateContractsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> works = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			works = newActivitiesUpdateService.getContractsList(obj);			
		}catch(Exception e){
			logger.error("getDeleteActivitiesUpdateContractsList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateContractsList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> contracts = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			obj.setDepartment_fk(uObj.getDepartment_fk());
			
			contracts = newActivitiesUpdateService.getNewActivitiesUpdateContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = newActivitiesUpdateService.getNewActivitiesUpdateStructures(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = newActivitiesUpdateService.getDeleteActivitiesStructures(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}	
	
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateInProgressStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateInProgressStructures(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> structures = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());				
			structures = newActivitiesUpdateService.getNewActivitiesUpdateInProgressStructures(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateStructures() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateLines", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateLines(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = newActivitiesUpdateService.getNewActivitiesUpdateLines(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateLines() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateSections", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateSections(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = newActivitiesUpdateService.getNewActivitiesUpdateSections(obj);			
		}catch(Exception e){
			logger.error("getNewActivitiesUpdateSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateComponentsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateComponentsList(@ModelAttribute StripChart obj){
		List<StripChart> components = null;
		try{
			components = newActivitiesUpdateService.getNewActivitiesUpdateComponentsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentsList() : "+e.getMessage());
		}
		return components;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesUpdateComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = newActivitiesUpdateService.getNewActivitiesUpdateComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesComponentsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesComponentsList(@ModelAttribute StripChart obj){
		List<StripChart> components = null;
		try{
			components = newActivitiesUpdateService.getDeleteActivitiesComponentsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentsList() : "+e.getMessage());
		}
		return components;
	}
	
	@RequestMapping(value = "/ajax/getDeleteActivitiesComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesComponentIdsList(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = newActivitiesUpdateService.getDeleteActivitiesComponentIds(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateComponentIds() : "+e.getMessage());
		}
		return componentIds;
	}	
	
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateActivitiesList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkActivitiesList(@ModelAttribute StripChart obj){
		List<StripChart> activities = null;
		try{
			activities = newActivitiesUpdateService.getAcivitiesBulkActivitiesList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesUpdateDetails", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public StripChart getNewActivitiesUpdateDetails(@ModelAttribute StripChart obj){
		StripChart data = null;
		try{
			data = newActivitiesUpdateService.getNewActivitiesUpdateDetails(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesUpdateDetails() : "+e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/ajax/getNewActivitiesfiltersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getNewActivitiesfiltersList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> fileterData = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			fileterData = newActivitiesUpdateService.getNewActivitiesfiltersList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesfiltersList() : "+e.getMessage());
		}
		return fileterData;
	}
	
	
	@RequestMapping(value = "/ajax/getStructureTypesInActivitiesUpdate", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStructureTypesInActivitiesUpdate(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = newActivitiesUpdateService.getStructureTypesInActivitiesUpdate(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureTypesInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}	
	
	@RequestMapping(value = "/ajax/getStructureTypesInDeleteActivities", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStructureTypesInDeleteActivities(@ModelAttribute StripChart obj) {
		List<StripChart> objList = null;
		try {
			objList = newActivitiesUpdateService.getStructureTypesInDeleteActivities(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStructureTypesInActivitiesUpload : " + e.getMessage());
		}
		return objList;
	}		
	

	@RequestMapping(value = "/update-new-activities-bulk", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateNewAcivitiesBulk(@ModelAttribute StripChart obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/new-activities-update");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			obj.setCreated_by_user_id_fk(user_Id);
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			//obj.setProgress_date(DateParser.parse(obj.getProgress_date()));
			boolean flag =  newActivitiesUpdateService.updateNewAcivitiesBulk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Acivities Updated Succesfully.");
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
	
	
	@RequestMapping(value = "/delete-activities-bulk", method = {RequestMethod.POST})
	public ModelAndView deleteActivitiesBulk(@ModelAttribute StripChart obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/delete-activities");
			
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			obj.setCreated_by_user_id_fk(user_Id);
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			//obj.setProgress_date(DateParser.parse(obj.getProgress_date()));
			boolean flag =  newActivitiesUpdateService.deleteAcivitiesBulk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Acivities Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Delete Acivities are failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Delete Acivities are failed. Try again.");
			logger.error("deleteActivitiesBulk : " + e.getMessage());
		}
		return model;
	}	
	
	@RequestMapping(value="/delete-activities",method=RequestMethod.GET)
	public ModelAndView DeleteActivities(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.deleteActivities);
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			obj.setDepartment_fk(uObj.getDepartment_fk());
			
			List<StripChart> projectsList = newActivitiesUpdateService.getProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = newActivitiesUpdateService.getWorksList(obj);
			model.addObject("worksList", worksList);
			List<StripChart> contractsList = newActivitiesUpdateService.getContractsList(obj);
			model.addObject("contractsList", contractsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DeleteActivities : " + e.getMessage());
		}
		return model;
	}
	
	

	@RequestMapping(value = "/ajax/getDeleteActivitiesfiltersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getDeleteActivitiesfiltersList(@ModelAttribute StripChart obj,HttpSession session){
		List<StripChart> fileterData = null;
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			fileterData = newActivitiesUpdateService.getDeleteActivitiesfiltersList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getNewActivitiesfiltersList() : "+e.getMessage());
		}
		return fileterData;
	}	
	
}

