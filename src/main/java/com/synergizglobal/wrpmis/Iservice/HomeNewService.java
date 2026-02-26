package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.Project;

public interface HomeNewService {
	public List<Project> getProjects() throws Exception;

	public Project getProjectOverview(Project obj) throws Exception;

	public List<Project> getprojectTypes() throws Exception;

	public List<Project> getProjectStatsByType() throws Exception;

	public List<Project> getAllLengthsByProjectTypes() throws Exception;

	public List<Project> getprojectTypeswithProject() throws Exception;

	public List<Project> getProjectsforHeader() throws Exception;
}
