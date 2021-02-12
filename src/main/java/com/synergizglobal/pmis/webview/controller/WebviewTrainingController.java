package com.synergizglobal.pmis.webview.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.TrainingService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.MobilePageConstants2;
import com.synergizglobal.pmis.model.Training;

@Controller
@RequestMapping("/mobileappwebview")
public class WebviewTrainingController {


	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(WebviewTrainingController.class);
	
	@Autowired
	TrainingService trainingService;


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

	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	
	@RequestMapping(value="/training",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView training(HttpSession session){
		ModelAndView model = new ModelAndView(MobilePageConstants2.trainingGrid);
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("training : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/get-training", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingList(@ModelAttribute Training obj) {
		List<Training> trainingList = null;
		try {
			trainingList = trainingService.getTrainingList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingList : " + e.getMessage());
		}
		return trainingList;
	}
	

	@RequestMapping(value = "/ajax/getTrainingTypesFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingTypesList(@ModelAttribute Training obj) {
		List<Training> trainingTypesList = null;
		try {
			trainingTypesList = trainingService.getTrainingTypesList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingTypesList : " + e.getMessage());
		}
		return trainingTypesList;
	}
	

	@RequestMapping(value = "/ajax/getTrainingCategorysFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getTrainingCategorysList(@ModelAttribute Training obj) {
		List<Training> trainingCategorysList = null;
		try {
			trainingCategorysList = trainingService.getTrainingCategorysList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTrainingCategorysList : " + e.getMessage());
		}
		return trainingCategorysList;
	}
	

	@RequestMapping(value = "/ajax/getStatusFilterListInTraining", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Training> getStatusList(@ModelAttribute Training obj) {
		List<Training> statusList = null;
		try {
			statusList = trainingService.getStatusList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStatusList : " + e.getMessage());
		}
		return statusList;
	}

	
	@RequestMapping(value = "/add-training-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addTrainingForm(){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addEditTrainingForm);
			model.addObject("action", "add");
			
			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);
			
			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);
			
			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);
			
			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);
			
			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			
			List<Training> usersList = trainingService.getUsersList();
			model.addObject("usersList", usersList);
			
		}catch (Exception e) {
				logger.error("addTrainingForm : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-training", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getTraining(@ModelAttribute Training obj ){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(MobilePageConstants2.addEditTrainingForm);
			model.addObject("action", "edit");
			
			List<Training> statusList = trainingService.getStatusList();
			model.addObject("statusList", statusList);
			
			List<Training> categoriesList = trainingService.getCategoriesList();
			model.addObject("categoriesList", categoriesList);
			
			List<Training> trainingTypesList = trainingService.getTrainingTypesList();
			model.addObject("trainingTypesList", trainingTypesList);
			
			List<Training> departmentsList = trainingService.getDepartmentsList();
			model.addObject("departmentsList", departmentsList);
			
			List<Training> issueCatogoriesList = trainingService.getIssueCatogoriesList();
			model.addObject("issueCatogoriesList", issueCatogoriesList);
			
			Training trainingDetails = trainingService.getTraining(obj);
			model.addObject("trainingDetails", trainingDetails);
			
			List<Training> usersList = trainingService.getUsersList();
			model.addObject("usersList", usersList);
		
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getTraining : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-training", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView addTraining(@ModelAttribute Training obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {			
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/mobileappwebview/training");
			
			obj.setStart_time(DateParser.parseDateTime(obj.getStart_time()));
			obj.setEnd_time(DateParser.parseDateTime(obj.getEnd_time()));
			
			boolean flag =  trainingService.addTraining(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Training is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Training is failed. Try again.");
			logger.error("addTraining : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-training", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ModelAndView updateTraining(@ModelAttribute Training obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			String user_Id = (String) session.getAttribute("USER_ID");String userName = (String) session.getAttribute("USER_NAME");
			obj.setCreated_by_user_id_fk(user_Id);
			model.setViewName("redirect:/mobileappwebview/training");
			obj.setStart_time(DateParser.parseDateTime(obj.getStart_time()));
			obj.setEnd_time(DateParser.parseDateTime(obj.getEnd_time()));

			boolean flag =  trainingService.updateTraining(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Training Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Training is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","Updating Training is failed. Try again.");
			logger.error("updateTraining : " + e.getMessage());
		}
		return model;
	}
	
	
}
