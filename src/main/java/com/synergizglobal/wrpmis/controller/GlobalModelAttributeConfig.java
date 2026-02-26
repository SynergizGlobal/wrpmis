package com.synergizglobal.wrpmis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.synergizglobal.wrpmis.Iservice.HomeNewService;
import com.synergizglobal.wrpmis.model.Project;

@ControllerAdvice
public class GlobalModelAttributeConfig {

    @Autowired
    private HomeNewService homeService;

    // Project list
    @ModelAttribute("projectTypeswithProject")
    public List<Project> populateProjects() throws Exception {
        return homeService.getprojectTypeswithProject();
    }

    // Project Types list
    @ModelAttribute("projectTypes")
    public List<Project> populateProjectTypes() throws Exception {
        return homeService.getprojectTypes();
    }
}
