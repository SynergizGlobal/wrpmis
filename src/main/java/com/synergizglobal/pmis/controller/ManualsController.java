package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.ManualsService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Manuals;

@Controller
public class ManualsController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ManualsController.class);

	@Autowired
	 ManualsService service;
	
	
	@RequestMapping(value="/manuals",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView manuals(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.manuals);
		try {
			String Admin = "No";
			String userRole = (String) session.getAttribute("USER_ROLE_NAME");
			model.addObject("action", Admin);
			if(userRole.contains("Admin")) {
				Admin= "Yes";
				model.addObject("action", Admin);
			}
			List<Manuals> foldersList = service.getFoldersList();
			model.addObject("foldersList", foldersList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("manuals : " + e.getMessage());
		}
		return model;
	}
	
}
