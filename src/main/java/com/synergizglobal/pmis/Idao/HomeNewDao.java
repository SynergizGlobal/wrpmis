package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Project;

public interface HomeNewDao {
	public List<Project> getProjects() throws Exception;

	public Project getProjectOverview(Project obj) throws Exception;
}
