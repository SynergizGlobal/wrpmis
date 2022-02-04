package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.RandRService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.RandR;

@Controller
public class RandRBsesController {
	Logger logger = Logger.getLogger(RandRBsesController.class);
	@Autowired
	RandRService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	
	/**
	 * This method rAndR() will execute when user request the for R & R module.
	 * 
	 * @param session it will check the session of the user.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/rr-bses",method=RequestMethod.GET)
	public ModelAndView rAndR(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.bsesGrid);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("rAndR : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * 
	 * @param session it will check the session of the user.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/bses-add-form",method=RequestMethod.GET)
	public ModelAndView rAndRAddForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditBses);
		model.addObject("action", "add");
		try {
			
		} catch (Exception e) {
			logger.info("rAndRAddForm : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * 
	 * @param session it will check the session of the user.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/bses-edit-form",method=RequestMethod.POST)
	public ModelAndView rAndREditForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditBses);
		model.addObject("action", "edit");
		try {
			
		} catch (Exception e) {
			logger.info("rAndREditForm : " + e.getMessage());
		}
		return model;
	}
}
