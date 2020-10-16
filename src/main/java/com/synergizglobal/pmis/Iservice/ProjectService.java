package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

public interface ProjectService {

	public List<Project> getProjectList()throws Exception;

	public Project editProject(String projectId, Project project)throws Exception;

	public boolean updateProject(Project project)throws Exception;

	public boolean addProject(Project project)throws Exception;

	public boolean deleteProject(String projectId, Project project)throws Exception;


}
