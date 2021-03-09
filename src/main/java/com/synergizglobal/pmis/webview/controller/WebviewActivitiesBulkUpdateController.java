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
import com.synergizglobal.pmis.controller.HomeController;
import com.synergizglobal.pmis.model.StripChart;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewActivitiesBulkUpdateController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	ActivitiesBulkUpdateService activitiesBulkUpdateervice;
	
	
	@RequestMapping(value="/activities-bulk-update",method=RequestMethod.GET)
	public ModelAndView activitiesBulkUpload(@ModelAttribute  StripChart obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(MobilePageConstants2.activitiesBulkUpdate);
		try {
			List<StripChart> projectsList = activitiesBulkUpdateervice.getAcivitiesBulkUpdateProjectsList(obj);
			model.addObject("projectsList", projectsList);
			
			List<StripChart> worksList = activitiesBulkUpdateervice.getAcivitiesBulkUpdateWorksList(obj);
			model.addObject("worksList", worksList);
			List<StripChart> contractsList = activitiesBulkUpdateervice.getAcivitiesBulkUpdateContractsList(obj);
			model.addObject("contractsList", contractsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("activitiesBulkUpload : " + e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateWorksList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateWorksList(@ModelAttribute StripChart obj){
		List<StripChart> works = null;
		try{
			works = activitiesBulkUpdateervice.getAcivitiesBulkUpdateWorksList(obj);			
		}catch(Exception e){
			logger.error("geStripCharttWorksList() : "+e.getMessage());
		}
		return works;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateContractsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateContractsList(@ModelAttribute StripChart obj){
		List<StripChart> contracts = null;
		try{
			contracts = activitiesBulkUpdateervice.getAcivitiesBulkUpdateContractsList(obj);			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkUpdateContractsList() : "+e.getMessage());
		}
		return contracts;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateStructures", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateStructures(@ModelAttribute StripChart obj){
		List<StripChart> structures = null;
		try{
			structures = activitiesBulkUpdateervice.getAcivitiesBulkUpdateStructures(obj);			
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
			structures = activitiesBulkUpdateervice.getAcivitiesBulkUpdateLines(obj);			
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
			structures = activitiesBulkUpdateervice.getAcivitiesBulkUpdateSections(obj);			
		}catch(Exception e){
			logger.error("getAcivitiesBulkUpdateSections() : "+e.getMessage());
		}
		return structures;
	}
	
	@RequestMapping(value = "/ajax/getAcivitiesBulkUpdateComponentIdsList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getAcivitiesBulkUpdateComponentIds(@ModelAttribute StripChart obj){
		List<StripChart> componentIds = null;
		try{
			componentIds = activitiesBulkUpdateervice.getAcivitiesBulkUpdateComponentIds(obj);			
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
			activities = activitiesBulkUpdateervice.getAcivitiesBulkActivitiesList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getAcivitiesBulkActivitiesList() : "+e.getMessage());
		}
		return activities;
	}
	
	@RequestMapping(value = "/ajax/getStripChartfilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getStripChartfilterList(@ModelAttribute StripChart obj){
		List<StripChart> fileterData = null;
		try{
			fileterData = activitiesBulkUpdateervice.getstripChartfilterList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getStripChartfilterList() : "+e.getMessage());
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
			obj.setProgress_date(DateParser.parse(obj.getProgress_date()));
			boolean flag =  activitiesBulkUpdateervice.updateAcivitiesBulk(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Acivities are Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went wrong. Please try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went wrong. Please try again.");
			logger.error("updateAcivitiesBulk : " + e.getMessage());
		}
		return model;
	}
}


