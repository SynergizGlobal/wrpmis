package com.synergizglobal.pmis.webview.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.synergizglobal.pmis.Iservice.WebDocumentsService;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.WebDocuments;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewWebDocumentsController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewWebDocumentsController.class);

	@Autowired
	WebDocumentsService service;
	
	private String capitalize(final String line) {
	   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	@RequestMapping(value="/web-documents/{document_type}",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView webDocuments(@ModelAttribute WebDocuments obj,@PathVariable("document_type") String document_type,HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.webDocuments);
		String title = "";
		try {
			String documentType = null;
			if(!StringUtils.isEmpty(document_type)){
				documentType = document_type.replaceAll("-", " ").toLowerCase();
				title = title + capitalize(documentType) + " - ";
			}
			model.addObject("title", title+"PMIS - Syntrack");

			model.addObject("documentType", capitalize(documentType));
			model.addObject("document_type", document_type);			
			
			obj.setType(documentType);
			
			List<WebDocuments> webDocuments = service.getWebDocuments(obj);
			model.addObject("webDocuments", webDocuments);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("webDocuments : " + e.getMessage());
		}
		return model;
	}
}
