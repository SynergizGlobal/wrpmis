package com.synergizglobal.pmis.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.synergizglobal.pmis.Iservice.SafetyService;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Safety;

@Controller
public class SafetyController {
	Logger logger = Logger.getLogger(SafetyController.class);	
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	StripChartService stripChartService;
	
	@Autowired
	HomeService homeService;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/safety",method=RequestMethod.GET)
	public ModelAndView safety(@ModelAttribute Safety obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.safetyGrid);
			/*List<Safety> safety = safetyService.getSafetyList(obj);
			model.addObject("safety", safety);*/
			List<Contract> contracts = stripChartService.getContractsList(null);
			model.addObject("contracts", contracts);
			
		} catch (Exception e) {
			logger.info("safety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getSafetyList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Safety> getSafetyList(@ModelAttribute Safety obj) {
		List<Safety> safetyList = null;
		try {
			safetyList = safetyService.getSafetyList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getSafetyList : " + e.getMessage());
		}
		return safetyList;
	}
	
	
	@RequestMapping(value="/add-safety-form",method=RequestMethod.GET)
	public ModelAndView addSafetyForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants.addSafetyForm);
			
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
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("addSafetyForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/add-safety",method=RequestMethod.POST)
	public ModelAndView addSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			model.setViewName("redirect:/safety");
			
			if(!StringUtils.isEmpty(obj.getDate())){
				Date convertedDate = sdf.parse(obj.getDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setDate(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getClosure_date())){
				Date convertedDate = sdf.parse(obj.getClosure_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setClosure_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getInvestigation_completed())){
				Date convertedDate = sdf.parse(obj.getInvestigation_completed());
				String currentDate = sqlDate.format(convertedDate);
				obj.setInvestigation_completed(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getPayment_date())){
				Date convertedDate = sdf.parse(obj.getPayment_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setPayment_date(currentDate);
			}
			
			MultipartFile file = obj.getSafetyFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
			}
			
			boolean flag = safetyService.addSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety added successfully");
			}else {
				attributes.addFlashAttribute("error", "Adding safety is failed. Try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.info("addSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/get-safety",method=RequestMethod.POST)
	public ModelAndView getSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.updateSafetyForm);
			
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
			logger.info("getSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/update-safety",method=RequestMethod.POST)
	public ModelAndView updateSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			model.setViewName("redirect:/safety");
			
			if(!StringUtils.isEmpty(obj.getDate())){
				Date convertedDate = sdf.parse(obj.getDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setDate(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getClosure_date())){
				Date convertedDate = sdf.parse(obj.getClosure_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setClosure_date(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getInvestigation_completed())){
				Date convertedDate = sdf.parse(obj.getInvestigation_completed());
				String currentDate = sqlDate.format(convertedDate);
				obj.setInvestigation_completed(currentDate);
			}
			
			if(!StringUtils.isEmpty(obj.getPayment_date())){
				Date convertedDate = sdf.parse(obj.getPayment_date());
				String currentDate = sqlDate.format(convertedDate);
				obj.setPayment_date(currentDate);
			}
			
			MultipartFile file = obj.getSafetyFile();
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH ;
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
			}
			
			boolean flag = safetyService.updateSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety updated successfully");
			}else {
				attributes.addFlashAttribute("error", "Updating safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("updateSafety : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/delete-safety",method=RequestMethod.POST)
	public ModelAndView deleteSafety(@ModelAttribute Safety obj,HttpSession session,RedirectAttributes attributes) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("redirect:/safety");
			boolean flag = safetyService.deleteSafety(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Safety deleted successfully");
			}else {
				attributes.addFlashAttribute("error", "Deleting safety is failed. Try again.");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", commonError);
			logger.info("deleteSafety : " + e.getMessage());
		}
		return model;
	}
}
