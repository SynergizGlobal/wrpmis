package com.synergizglobal.pmis.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.TemplateUploadService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class TemplateUploadController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TemplateUploadController.class);
	
	@Autowired
	TemplateUploadService service;
	
	@RequestMapping(value="/template-upload",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView TemplateUpload(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.templateUploadGrid);
		try {
			List<TrainingType> templatesList = service.getTemplatesList();
			model.addObject("templatesList", templatesList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("TemplateUpload : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/upload-template", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView uploadTemplate(@ModelAttribute TrainingType obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		try{
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/template-upload");
			obj.setUploaded_by(userId);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			obj.setUploaded_on(timeStamp);
			String fileDirectory = CommonConstants.TEMPLATE_FILEPATH ;
			MultipartFile file = obj.getTemplateFile();
			if (null != file && !file.isEmpty()){
				String fileName = file.getOriginalFilename();
				String fileName_new = obj.getTemplate_name()+"."+ fileName.split("\\.")[1];
				obj.setAttachment(fileName_new);
				FileUploads.singleFileSaving(file, fileDirectory, fileName_new);
			}
			
			boolean flag =  service.uploadTemplate(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Template Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","uploading Template is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","uploading Template is failed. Try again.");
			logger.error("uploadTemplate : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-template", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTemplate(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/template-upload");
			boolean flag =  service.deleteTemplate(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Template Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRisk : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getTemplateHistoryList", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TrainingType> getTemplateHistoryList(@ModelAttribute TrainingType obj) {
		List<TrainingType> dataList = null;  
		try {
			dataList = service.getTemplateHistoryList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTemplateHistoryList : " + e.getMessage());
		}
		return dataList;
	}
}
