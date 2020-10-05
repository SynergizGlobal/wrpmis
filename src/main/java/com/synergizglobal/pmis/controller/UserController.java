package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.UserService;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.User;

@Controller
public class UserController {
	Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
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
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ModelAndView users(@ModelAttribute User obj,HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.usersGrid);
			
			List<User> roles = userService.getUserRoles();
			model.addObject("roles", roles);
			
			List<User> departments = userService.getUserDepartments();
			model.addObject("departments", departments);
			
			List<User> reportingToList = userService.getUserReportingToList();
			model.addObject("reportingToList", reportingToList);
			
		} catch (Exception e) {
			logger.info("users : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getUsersList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUsersList(@ModelAttribute User obj) {
		List<User> users = null;
		try {
			users = userService.getUsersList(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getUsersList : " + e.getMessage());
		}
		return users;
	}
	
	@RequestMapping(value="/add-user-form",method=RequestMethod.GET)
	public ModelAndView addUserForm(HttpSession session) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(PageConstants2.addEditUser);
			
			model.addObject("action", "add");
			
		} catch (Exception e) {
			logger.info("addUserForm : " + e.getMessage());
		}
		return model;
	}
	
}
