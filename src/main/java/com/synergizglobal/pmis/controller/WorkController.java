package com.synergizglobal.pmis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.Iservice.WorkService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;

@Controller
public class WorkController {
	
	Logger logger = Logger.getLogger(WorkController.class);
	
	@Autowired
	WorkService workService;
	
	@Autowired
	HomeService homeService;
	
	
	
	@RequestMapping(value="/work",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Work(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.work);
		try {
			List<Work> workList = workService.getworkList();
			model.addObject("workList", workList);	
		}catch (Exception e) {
			e.printStackTrace();
			
			logger.info("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/edit-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getUser(@ModelAttribute Work work,Railway rail){
		ModelAndView model = new ModelAndView();
		String workId = null;
		String railId = null;
		String exId = null;
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);

			workId= work.getWork_id();
			exId = work.getExecuted_by_id_fk();
			railId = work.getRailway_id_fk();
		Work workDeatils = workService.editWork(workId, work);
			model.addObject("workDeatils", workDeatils);
			
		}catch (Exception e) {
			logger.info("Work : " + e.getMessage());
		}
		return model;

	}
	
	
	@RequestMapping(value = "/add-work", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWork(){
		ModelAndView model = new ModelAndView();
		
		try{
			model.setViewName(PageConstants.addEditWork);
			model.addObject("action", "add");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);	
			List<Railway> railwaysList = workService.getRailwayList();
			model.addObject("railwaysList", railwaysList);
		}catch (Exception e) {
				logger.info("Work : " + e.getMessage());
			}
			return model;
	 }
	
	
	
	@RequestMapping(value = "/updateWork-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView workAdded(@ModelAttribute Work work){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			boolean flag =  workService.updateWork(work);
			if(flag == true) {
				System.out.println("success");
			}
			else {
				System.out.println("failed");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Work : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/addWork-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addWorkForm(@ModelAttribute Work work){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/work");
			boolean flag =  workService.addWork(work);
			if(flag == true) {
				System.out.println("success");
			}
			else {
				System.out.println("failed");
			}
		}catch (Exception e) {
			logger.info("Work : " + e.getMessage());
		}
		return model;
	
	}
	
	
	
	
}
	
	
	
	
	
	
	
	
	