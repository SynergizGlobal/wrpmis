package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

public interface ProjectDao {

	public List<Project> getProjectList()throws Exception;

	public Project editProject(String projectId, Project project)throws Exception;

	public boolean updateProject(Project project)throws Exception;

	public boolean addProject(Project project)throws Exception;

	public boolean deleteProject(String projectId, Project project)throws Exception;


}
