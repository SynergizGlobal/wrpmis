package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.FortnightPlanService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.FortnightPlanPaginationObject;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.User;

@Controller
public class FortnightPlanController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FortnightPlanController.class);	
	
	@Autowired
	FortnightPlanService FortnightPlanService;
	
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
	
	@RequestMapping(value="/FortnightPlan",method=RequestMethod.GET)
	public ModelAndView FortnightPlan(@ModelAttribute FortnightPlan obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.fortnightPlanGrid);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FortnightPlan : " + e.getMessage());
		}
		return model;
	}
	 
	@RequestMapping(value = "/ajax/getWorksListFilterInFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getWorksListFilterInFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getWorksListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/ajax/getContractListFilterInFortnight", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getContractListFilterInFortnight(@ModelAttribute FortnightPlan obj) {
		List<FortnightPlan> fortnight = null;
		try {
			fortnight = FortnightPlanService.getContractListFilter(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksListFilter : " + e.getMessage());
		}
		return fortnight;
	}
	
	@RequestMapping(value = "/ajax/getFortnightPlanList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FortnightPlan> getFortnightPlanList(@ModelAttribute FortnightPlan obj,HttpSession session) {
		List<FortnightPlan> FortnightPlans = null;
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());			
			FortnightPlans = FortnightPlanService.getFortnightPlanList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFortnightPlanList : " + e.getMessage());
		}
		return FortnightPlans;
	}	

	


	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(FortnightPlan obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = FortnightPlanService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	public List<FortnightPlan> createPaginationData(HttpSession session, int startIndex, int offset, FortnightPlan obj, String searchParameter) {
		List<FortnightPlan> objList = null;
		try {
			objList = FortnightPlanService.getFortnightPlanList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return objList;
	}
	

	@RequestMapping(value="/get-FortnightPlan",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFortnightPlan(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateFortnightPlan);

			
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanContractList = FortnightPlanService.getFortnightPlanContractList(obj);
			model.addObject("FortnightPlanContractList", FortnightPlanContractList);
			
			List<FortnightPlan> FortnightPlanCategoryList = FortnightPlanService.getFortnightPlanCategoryList();
			model.addObject("FortnightPlanCategoryList", FortnightPlanCategoryList);
			
			List<FortnightPlan> FortnightPlanCriticalItemList = FortnightPlanService.getFortnightPlanCriticalItemList();
			model.addObject("FortnightPlanCriticalItemList", FortnightPlanCriticalItemList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);			
			
			
			FortnightPlan FortnightPlan = FortnightPlanService.getFortnightPlan(obj);
			model.addObject("FortnightPlan", FortnightPlan);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFortnightPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-FortnightPlan/{FortnightPlan_id}",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getFortnightPlan(@ModelAttribute FortnightPlan obj,@PathVariable("FortnightPlan_id") String FortnightPlan_id,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateFortnightPlan);
			
			List<FortnightPlan> FortnightPlanWorkList = FortnightPlanService.getFortnightPlanWorkList();
			model.addObject("FortnightPlanWorkList", FortnightPlanWorkList);
			
			List<FortnightPlan> FortnightPlanContractList = FortnightPlanService.getFortnightPlanContractList(obj);
			model.addObject("FortnightPlanContractList", FortnightPlanContractList);
			
			List<FortnightPlan> FortnightPlanCategoryList = FortnightPlanService.getFortnightPlanCategoryList();
			model.addObject("FortnightPlanCategoryList", FortnightPlanCategoryList);
			
			List<FortnightPlan> FortnightPlanCriticalItemList = FortnightPlanService.getFortnightPlanCriticalItemList();
			model.addObject("FortnightPlanCriticalItemList", FortnightPlanCriticalItemList);
			
			List<FortnightPlan> FortnightPlanPeriodList = FortnightPlanService.getFortnightPlanPeriodList();
			model.addObject("FortnightPlanPeriodList", FortnightPlanPeriodList);
			
			obj.setFortnightly_plan_id(FortnightPlan_id);
			FortnightPlan FortnightPlan = FortnightPlanService.getFortnightPlan(obj);
			model.addObject("FortnightPlan", FortnightPlan);
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("getFortnightPlan : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-FortnightPlan",method=RequestMethod.POST)
	public ModelAndView updateFortnightPlan(@ModelAttribute FortnightPlan obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/FortnightPlan");

			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");

			
			boolean flag = FortnightPlanService.updateFortnightPlan(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "FortnightPlan updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating FortnightPlan is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateFortnightPlan : " + e.getMessage());
		}
		return model;
	}
	

	
}
