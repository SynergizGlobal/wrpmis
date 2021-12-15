package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.Iservice.ModulePermissionService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.ModulePermission;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.ModulePermission;
import com.synergizglobal.pmis.model.User;

@Controller
public class ModulePermissionController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ModulePermissionController.class);
	@Autowired
	ModulePermissionService modulePermissionService;
	
	@RequestMapping(value="/module-permissions",method=RequestMethod.GET)
	public ModelAndView modulePermission(@ModelAttribute ModulePermission obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.modulePermission);
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<ModulePermission> modulePermissionList = modulePermissionService.getModulePermissionList(obj);
			model.addObject("modulePermissionList", modulePermissionList);	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modulePermission : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-module-permission", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getModulePermissionForm(@ModelAttribute ModulePermission obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.updateModulePermission);
			/*List<ModulePermission> financialYearList = modulePermissionService.getFinancialYearList();
			model.addObject("financialYearList", financialYearList);
			*/
			ModulePermission modulePermissions = modulePermissionService.getModulePermission(obj);
			model.addObject("modulePermissions", modulePermissions);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getModulePermission : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/update-module-permission-form", method = {RequestMethod.POST})
	public ModelAndView updateModulePermission(@ModelAttribute ModulePermission obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/module-permissions");
			boolean flag =  modulePermissionService.updateModulePermission(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Module Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Module is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Module is failed. Try again.");
			logger.error("updateModulePermission : " + e.getMessage());
		}
		return model;
	}
}
