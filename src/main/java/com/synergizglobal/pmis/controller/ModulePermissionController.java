package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.FormsAccessService;
import com.synergizglobal.pmis.Iservice.ModulePermissionService;
import com.synergizglobal.pmis.constants.PageConstants;
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
	ModulePermissionService service;
	
	@Autowired
	FormsAccessService formService;
	
	@RequestMapping(value="/module-permissions",method=RequestMethod.GET)
	public ModelAndView modulePermission(@ModelAttribute ModulePermission obj,HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.modulePermission);
		try {
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			/*List<ModulePermission> modulesList = service.getModulesList(obj);
			model.addObject("modulesList", modulesList);	*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("modulePermission : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/ajax/getModulesList", method = {RequestMethod.POST})
	@ResponseBody
	public List<ModulePermission> getModulesList(@ModelAttribute ModulePermission obj,HttpSession session){
		List<ModulePermission> objList = new ArrayList<ModulePermission>();
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			objList =  service.getModulesList(obj);
		}catch (Exception e) {
			logger.error("getModulesList : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/get-module-permission", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getModulePermissionForm(@ModelAttribute ModulePermission obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.updateModulePermission);
			
			List<ModulePermission> modulesList = service.getModulesList(obj);
			model.addObject("modulesList", modulesList);	
			
			List<ModulePermission> user_roles = service.getUserRoles(obj);
			model.addObject("user_roles", user_roles);
			
			List<ModulePermission> user_types = service.getUserTypes(obj);
			model.addObject("user_types", user_types);
			
			List<ModulePermission> users = service.getUsers(obj);
			model.addObject("users", users);
			
			ModulePermission modulePermissions = service.getModulePermission(obj);
			model.addObject("modulePermissions", modulePermissions);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getModulePermission : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/update-module-permissions", method = {RequestMethod.POST})
	public ModelAndView updateModulePermission(@ModelAttribute ModulePermission obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/module-permissions");
			boolean flag =  service.updateModulePermission(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Module Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Module failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Module failed. Try again.");
			logger.error("updateModulePermission : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/update-url-permissions", method = {RequestMethod.POST})
	@ResponseBody
	public boolean updateUrlPermissions(@ModelAttribute ModulePermission obj){
		boolean flag = false;
		try{
			flag =  service.updateUrlPermissions(obj);
		}catch (Exception e) {
			//attributes.addFlashAttribute("error","Updating Module failed. Try again.");
			logger.error("updateUrlPermissions : " + e.getMessage());
		}
		return flag;
	}
	
	@RequestMapping(value = "/ajax/getModulesForFilter", method = {RequestMethod.POST})
	@ResponseBody
	public List<ModulePermission> getModulesForFilter(@ModelAttribute ModulePermission obj){
		List<ModulePermission> objList = new ArrayList<ModulePermission>();
		try{
			objList =  service.getModulesForFilter(obj);
		}catch (Exception e) {
			logger.error("getModulesForFilter : " + e.getMessage());
		}
		return objList;
	}
	
	@RequestMapping(value = "/ajax/getModuleStatusListForFilter", method = {RequestMethod.POST})
	@ResponseBody
	public List<ModulePermission> getModuleStatusListForFilter(@ModelAttribute ModulePermission obj){
		List<ModulePermission> objList = new ArrayList<ModulePermission>();
		try{
			objList =  service.getModuleStatusListForFilter(obj);
		}catch (Exception e) {
			logger.error("getModuleStatusListForFilter : " + e.getMessage());
		}
		return objList;
	}
	
	/*@RequestMapping(value = "/update-url-permissions", method = {RequestMethod.POST})
	public ModelAndView updateUrlPermissions(@ModelAttribute ModulePermission obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/module-permissions");
			boolean flag =  service.updateUrlPermissions(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "URL Permissions Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating URL Permissions failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Module failed. Try again.");
			logger.error("updateUrlPermissions : " + e.getMessage());
		}
		return model;
	}*/
}
