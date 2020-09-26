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
import com.synergizglobal.pmis.Iservice.ProjectService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Railway;
import com.synergizglobal.pmis.model.Work;

@Controller
public class ProjectController {

Logger logger = Logger.getLogger(ProjectController.class);
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	HomeService homeService;
	
	
	@RequestMapping(value="/project",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Project(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.project);
		try {
			List<Project> projectList = projectService.getProjectList();
			model.addObject("projectList", projectList);	
		}catch (Exception e) {
			e.printStackTrace();
			
			logger.info("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/edit-project", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getProjectDetails(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "edit");
			projectId= project.getProject_id();
			Project projectDeatils = projectService.editProject(projectId, project);
			model.addObject("projectDeatils", projectDeatils);
			
		}catch (Exception e) {
			logger.info("Project : " + e.getMessage());
		}
		return model;

	}
	
	@RequestMapping(value = "/updateProject", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updateProject(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project");
			boolean flag =  projectService.updateProject(project);
			if(flag == true) {
				System.out.println("success");
			}
			else {
				System.out.println("failed");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Project : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/addProject-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProjectForm(){
		ModelAndView model = new ModelAndView();
		
		try{
			model.setViewName(PageConstants.addUpdateProject);
			model.addObject("action", "add");
		}catch (Exception e) {
				logger.info("Work : " + e.getMessage());
			}
			return model;
	 }
	
	@RequestMapping(value = "/addProject", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addProject(@ModelAttribute  Project project){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/project");
			boolean flag =  projectService.addProject(project);
			if(flag == true) {
				System.out.println("success");
			}
			else {
				System.out.println("failed");
			}
		}catch (Exception e) {
			logger.info("Project : " + e.getMessage());
		}
		return model;
	
	}
	
	@RequestMapping(value = "/deleteProjectRow", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteProjectRow(@ModelAttribute Project project){
		ModelAndView model = new ModelAndView();
		String projectId = null;
		try{
			model.setViewName("redirect:/project");
			projectId= project.getProject_id();
			boolean flag =  projectService.deleteProjectRow(projectId,project);
			if(flag == true) {
				System.out.println("Delete success");
			}
			else {
				System.out.println("Delete failed");
			}
		}catch (Exception e) {
			logger.info("Work : " + e.getMessage());
		}
		return model;
	
	}
	
}
