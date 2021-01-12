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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.PMISProgressService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.StripChart;

@Controller
public class PMISProgressController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(PMISProgressController.class);
	
	@Autowired
	PMISProgressService service;
	
	@RequestMapping(value="/pmis-progress-form",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView progressForm(HttpSession session){
		StripChart obj = null;
		ModelAndView model = new ModelAndView(PageConstants.PMISprogressForm);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("progressForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getMileStoneFilterList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getMileStoneList(@ModelAttribute StripChart obj){
		List<StripChart> fileterData = null;
		try{
			fileterData = service.getMileStoneFilterList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getMileStoneFilterList() : "+e.getMessage());
		}
		return fileterData;
	}
	
	@RequestMapping(value = "/ajax/getProjectsFilterListInPMISStripChart", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getProjectsList(@ModelAttribute StripChart obj){
		List<StripChart> fileterData = null;
		try{
			fileterData = service.getProjectsFilterList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getProjectsFilterList() : "+e.getMessage());
		}
		return fileterData;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInPMISStripChart", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getWorksList(@ModelAttribute StripChart obj){
		List<StripChart> fileterData = null;
		try{
			fileterData = service.getWorksFilterList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getWorksFilterList() : "+e.getMessage());
		}
		return fileterData;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInPMISStripChart", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getContarctsList(@ModelAttribute StripChart obj){
		List<StripChart> fileterData = null;
		try{
			fileterData = service.getContractsFilterList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getContractsFilterList() : "+e.getMessage());
		}
		return fileterData;
	}
	
	@RequestMapping(value = "/ajax/getMileStonesFilterListInMilestone", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<StripChart> getContractMileStonesFilterList(@ModelAttribute StripChart obj){
		List<StripChart> ContractFileterData = null;
		try{
			ContractFileterData = service.getMileStonesFilterList(obj);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getContractMileStonesFilterList() : "+e.getMessage());
		}
		return ContractFileterData;
	}
	
	@RequestMapping(value = "/update=pmis-progrss-form", method = {RequestMethod.POST})
	public ModelAndView updateProgressForm(@ModelAttribute StripChart obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;
		try{
			model.setViewName("redirect:/pmis-progress-form");
			//userId = (String) session.getAttribute("USER_ID");
			//obj.setCreated_by_user_id_fk(userId);
			obj.setActual_start(DateParser.parse(obj.getActual_start()));
			obj.setActual_finish(DateParser.parse(obj.getActual_finish()));
			boolean flag =  service.updateProgressForm(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "ProgressForm Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating ProgressForm is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating ProgressForm is failed. Try again.");
			logger.error("updateProgressForm : " + e.getMessage());
		}
		return model;
	}
	
}
