package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Year;

public interface ProjectDao {

	public List<Project> getProjectList()throws Exception;

	public Project getProject(String projectId, Project project)throws Exception;

	public boolean updateProject(Project project)throws Exception;

	public boolean addProject(Project project)throws Exception;

	public boolean deleteProject(String projectId, Project project)throws Exception;

	public List<Project> getFileNames(String projectId) throws Exception;

	public List<Year> getYearList() throws Exception;

	public int getTotalRecords(Project obj, String searchParameter) throws Exception;

	public List<Project> getProjectsList(Project obj, int startIndex, int offset, String searchParameter) throws Exception;


}
