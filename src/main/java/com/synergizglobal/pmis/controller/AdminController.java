package com.synergizglobal.pmis.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.AdminService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Admin;

@Controller
public class AdminController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	AdminService service;
	
	@RequestMapping(value="/admin",method={RequestMethod.GET})
	public ModelAndView admin(HttpSession session,@ModelAttribute Admin obj){
		ModelAndView model = new ModelAndView(PageConstants.adminGrid);
		try {
			List<Admin> adminList = service.getAdminList();
			model.addObject("adminList", adminList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("admin : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/add-admin-form", method = {RequestMethod.GET})
	public ModelAndView addAdminForm(@ModelAttribute Admin obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditAdminForm);
			model.addObject("action", "add");
	
			List<Admin> statusList = service.getStatus(obj);
			model.addObject("statusList", statusList);
			
		}catch (Exception e) {
				logger.error("addAdminForm : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/get-admin", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getAdminForm(@ModelAttribute Admin obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(PageConstants.addEditAdminForm);
			model.addObject("action", "edit");

			List<Admin> statusList = service.getStatus(obj);
			model.addObject("statusList", statusList);
			
			Admin adminDetails = service.getAdminDetails(obj);
			model.addObject("adminDetails", adminDetails);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getAdminForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-admin", method = {RequestMethod.POST})
	public ModelAndView addAdmin(@ModelAttribute Admin admin,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/admin");
			boolean flag =  service.addAdmin(admin);
			if(flag) {
				attributes.addFlashAttribute("success", "Form Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went wrong. Try again.");
			logger.error("addAdmin : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-admin", method = {RequestMethod.POST})
	public ModelAndView updateAdmin(@ModelAttribute Admin admin,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/admin");
			boolean flag =  service.updateAdmin(admin);
			if(flag) {
				attributes.addFlashAttribute("success", "Form Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went wrong. Try again.");
			logger.error("updateAdmin : " + e.getMessage());
		}
		return model;
	}
}
