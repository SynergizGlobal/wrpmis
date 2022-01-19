package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Project;

public interface HomeNewService {
	public List<Project> getProjects() throws Exception;

	public Project getProjectOverview(Project obj) throws Exception;
}
