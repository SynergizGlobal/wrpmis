package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.FOBGalleryService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.FOBGallery;

@Controller
public class FOBGalleryController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(FOBGalleryController.class);
	
	@Autowired
	FOBGalleryService service;
	
	
	
	@RequestMapping(value="/fob-gallery/{fob_id}",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView fobGallery(HttpSession session,@PathVariable("fob_id") String fob_id){
		ModelAndView model = new ModelAndView(PageConstants.fobGallery);
		try {
			List<FOBGallery> objsList = service.getFOBGalleryList(fob_id);
			model.addObject("fobGalleryList", objsList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("fobGallery : " + e.getMessage());
		}
		return model;
	}
	@RequestMapping(value="/fobmulti-gallery/{fob_id}",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView fobMultiGallery(HttpSession session,@PathVariable("fob_id") String fob_id){
		ModelAndView model = new ModelAndView(PageConstants.fobMultiGallery);
		try {
			List<FOBGallery> objsList = service.getFOBGalleryList(fob_id);
			model.addObject("fobGalleryList", objsList);	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("fobGallery : " + e.getMessage());
		}
		return model;
	}	
	
}
