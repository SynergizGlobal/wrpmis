package com.synergizglobal.pmis.controller;

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

import com.synergizglobal.pmis.Iservice.TrainingService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Training;

@Controller
public class TrainingController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TrainingController.class);
	
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
	
	
	@RequestMapping(value="/training",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView training(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.trainingGrid);
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
	
}
