package com.synergizglobal.wrpmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.WebLinksService;
import com.synergizglobal.wrpmis.constants.PageConstants2;
import com.synergizglobal.wrpmis.model.WebLinks;

@Controller
public class WebLinksController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 
	Logger logger = Logger.getLogger(WebLinksController.class);
	
	@Autowired
	WebLinksService service;	
	
	@Value("${common.error.message}")
	public String commonError;
	
	@RequestMapping(value="/web-links",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView webLinks(@ModelAttribute WebLinks obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants2.webLinks);
		try {
			List<WebLinks> webLinksList = service.getWebLinks(obj);
			model.addObject("webLinksList", webLinksList);
			
		}catch (Exception e) {
			e.printStackTrace();
			model.addObject("error", commonError);
			logger.error("webLinks : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-web-links", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateWebLinks(@ModelAttribute WebLinks obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/web-links");
			boolean flag =  service.updateWebLinks(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web links Updated Succesfully.");
			}else {
				attributes.addFlashAttribute("error","Updating Web links is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", commonError);
			logger.error("updateWebLinks : " + e.getMessage());
		}
		return model;
	}
	
}
