package com.synergizglobal.pmis.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.WebDocumentsService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants2;
import com.synergizglobal.pmis.model.WebDocuments;

@Controller
public class WebDocumentsController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebDocumentsController.class);

	@Autowired
	WebDocumentsService service;
	
	private String capitalize(final String line) {
	   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	@RequestMapping(value="/web-document-types",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getWebDocumentTypes(@ModelAttribute WebDocuments obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants2.webDocuments);
		try {
			List<WebDocuments> webDocumentTypes = service.getWebDocumentTypes(obj);
			model.addObject("webDocumentTypes", webDocumentTypes);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWebDocumentTypes : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value="/web-documents/{document_type}",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView webDocuments(@ModelAttribute WebDocuments obj,@PathVariable("document_type") String document_type,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants2.webDocuments);
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
			
			List<WebDocuments> webDocCategoriesList = service.getWebDocumentCategoriesList(obj);
			model.addObject("webDocCategoriesList", webDocCategoriesList);
			
			List<WebDocuments> webDocuments = service.getWebDocuments(obj);
			model.addObject("webDocuments", webDocuments);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("webDocuments : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/upload-web-document/{document_type}", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addWebDocument(@ModelAttribute WebDocuments obj,@PathVariable("document_type") String document_type,HttpSession session,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		String user_Id = null;String userName = null;
		try {			
			user_Id = (String) session.getAttribute("USER_ID");userName = (String) session.getAttribute("USER_NAME");
			obj.setUploaded_by(userName);
			
			obj.setDate_of_issue(DateParser.parse(obj.getDate_of_issue()));
			
			model.setViewName("redirect:/web-documents/"+document_type);
			
			String documentType = null;
			if(!StringUtils.isEmpty(document_type)){
				documentType = capitalize(document_type.replaceAll("-", " ").toLowerCase());
			}
			
			MultipartFile file = obj.getWebDocument(); 
			if (null != file && !file.isEmpty()){
				String saveDirectory = CommonConstants2.WEB_DOCUMENTS_FILE_SAVING_PATH ;
				saveDirectory = saveDirectory + documentType + "/" + obj.getCategory() + "/";
				String fileName = file.getOriginalFilename();
				FileUploads.singleFileSaving(file, saveDirectory, fileName);
				obj.setFile_name(fileName);
			}
			boolean flag =  service.addWebDocument(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Web Document Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Web Document is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Web Document is failed. Try again.");
			logger.error("addWebDocument : " + e.getMessage());
		}
		return model;
	}
}
