package com.synergizglobal.pmis.webview.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Safety;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewSafetyController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewSafetyController.class);	
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	HomeService homeService;
	
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
	
	@RequestMapping(value="/safety",method=RequestMethod.GET)
	public ModelAndView safety(@ModelAttribute Safety obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.safetyGrid);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("safety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getWorksListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getWorksListFilterInSafety(@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			objList = safetyService.getWorksListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getContractsListFilterInSafety(@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			objList = safetyService.getContractsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getDepartmentsListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getDepartmentsListFilterInSafety(@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			objList = safetyService.getDepartmentsListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getDepartmentsListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getCategoryListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getCategoryListFilterInSafety(@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			objList = safetyService.getCategoryListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getStatusListFilterInSafety", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getStatusListFilterInSafety(@ModelAttribute Safety obj) {
		List<Safety> objList = null;
		try {
			objList = safetyService.getStatusListFilter(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusListFilterInSafety : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getSafetyList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getSafetyList(@ModelAttribute Safety obj) {
		List<Safety> safetyList = null;
		try {
			safetyList = safetyService.getSafetyList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSafetyList : " + e.getMessage());
		}
		return safetyList;
	}
	
	
	
	@RequestMapping(value="/add-safety-form",method=RequestMethod.GET)
	public ModelAndView addSafetyForm(HttpSession session,@ModelAttribute Safety obj) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.safetyForm);
			model.addObject("action", "add");
			
			List<Safety> projectsList = safetyService.getProjectsListForSafetyForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Safety> worksList = safetyService.getWorkListForSafetyForm(obj);
			model.addObject("worksList", worksList);
			
			List<Safety> contractsList = safetyService.getContractsListForSafetyForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addSafetyForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getProjectsListForSafetyForm(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getProjectsListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getWorkListForSafetyForm(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getWorkListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForSafetyForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getContractsListForSafetyForm(@ModelAttribute Safety obj) {
		List<Safety> objsList = null;
		try {
			objsList = safetyService.getContractsListForSafetyForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForSafetyForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value="/add-safety",method=RequestMethod.POST)
	public ModelAndView addSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/mobileappwebview/safety");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setClosure_date(DateParser.parse(obj.getClosure_date()));
			obj.setInvestigation_completed(DateParser.parse(obj.getInvestigation_completed()));			
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			boolean flag = safetyService.addSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding safety is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("addSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.safetyForm);
			model.addObject("action", "edit");
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety/{safety_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSafety(@ModelAttribute Safety obj,@PathVariable("safety_id") String safety_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(MobilePageConstants2.safetyForm);
			
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			
			List<Safety> safetyStatusList = safetyService.getSafetyStatusList();
			model.addObject("safetyStatusList", safetyStatusList);
			
			List<Safety> safetyImpactList = safetyService.getSafetyImpactList();
			model.addObject("safetyImpactList", safetyImpactList);
			
			List<Safety> safetyCategoryList = safetyService.getSafetyCategoryList();
			model.addObject("safetyCategoryList", safetyCategoryList);
			
			List<Safety> safetyRootCauseList = safetyService.getSafetyRootCauseList();
			model.addObject("safetyRootCauseList", safetyRootCauseList);
			
			List<Safety> departmentList = safetyService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			obj.setSafety_id(safety_id);
			Safety safety = safetyService.getSafety(obj);
			model.addObject("safety", safety);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-safety",method=RequestMethod.POST)
	public ModelAndView updateSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/mobileappwebview/safety");
			
			obj.setDate(DateParser.parse(obj.getDate()));			
			obj.setClosure_date(DateParser.parse(obj.getClosure_date()));
			obj.setInvestigation_completed(DateParser.parse(obj.getInvestigation_completed()));			
			obj.setPayment_date(DateParser.parse(obj.getPayment_date()));
			boolean flag = safetyService.updateSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-safety",method=RequestMethod.POST)
	public ModelAndView deleteSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/mobileappwebview/safety");
			boolean flag = safetyService.deleteSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("deleteSafety : " + e.getMessage());
		}
		return model;
	}
	
}
