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

import com.synergizglobal.pmis.Iservice.TAFinancialsService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.TAFinancials;


@Controller
public class TAFinancialsController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TAFinancialsController.class);
	
	@Autowired
	TAFinancialsService service;
	
	@RequestMapping(value="/ta-financials",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView taFinancials(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.taFinancialsGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("taFinancials : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-ta-financials", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getTAFinancialsList(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> taFinancialsList = null;
		try {
			taFinancialsList = service.taFinancialsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTAFinancialsList : " + e.getMessage());
		}
		return taFinancialsList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInTAFinancials", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getWorksList(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> worksList = null;
		try {
			worksList = service.getTAFinancialsWorksList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksList : " + e.getMessage());
		}
		return worksList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInTAFinancials", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getContractsList(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> contractsList = null;
		try {
			contractsList = service.getTAFinancialsContractsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsList : " + e.getMessage());
		}
		return contractsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForFinancialsForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getWorkListForFinancialsForm(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> objsList = null;
		try {
			objsList = service.getWorkListForFinancialsForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForFinancialsForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForFinancialsForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TAFinancials> getContractsListForFinancialsForm(@ModelAttribute TAFinancials obj) {
		List<TAFinancials> objsList = null;
		try {
			objsList = service.getContractsListForFinancialsForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForFinancialsForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/add-ta-financials-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addTAFinancialsForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTaFinancialsForm);
			model.addObject("action", "add");
			List<TAFinancials> worksList = service.getWorksList();
			model.addObject("worksList", worksList);
			List<TAFinancials> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			
		}catch (Exception e) {
				logger.error("addTAFinancialsForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-ta-financials", method = {RequestMethod.POST})
	public ModelAndView getTAFinancialsForm(@ModelAttribute TAFinancials obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditTaFinancialsForm);
			model.addObject("action", "edit");
			List<TAFinancials> worksList = service.getWorksList();
			model.addObject("worksList", worksList);
			List<TAFinancials> contractsList = service.getContractsList();
			model.addObject("contractsList", contractsList);
			TAFinancials taFinancialDetails = service.getTAFinancials(obj);
			model.addObject("taFinancialDetails", taFinancialDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getTAFinancials : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-ta-financials", method = {RequestMethod.POST})
	public ModelAndView addTAFinancials(@ModelAttribute TAFinancials obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/ta-financials");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag = service.addTAFinancials(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "TA Financials Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding TA Financials is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding TA Financials is failed. Try again.");
			logger.error("addTAFinancials : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-ta-financials", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateTAFinancials(@ModelAttribute TAFinancials obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/ta-financials");
			obj.setMonth(DateParser.parse(obj.getMonth()));
			boolean flag =  service.updateTAFinancials(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "TA Financials Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating TA Financials is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating TA Financials is failed. Try again.");
			logger.error("updateTAFinancials : " + e.getMessage());
		}
		return model;
	}
}
