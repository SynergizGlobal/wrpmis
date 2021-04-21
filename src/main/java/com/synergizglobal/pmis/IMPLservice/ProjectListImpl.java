package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ProjectDao;
import com.synergizglobal.pmis.Iservice.ProjectService;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Year;

@Service
public class ProjectListImpl implements ProjectService{
	@Autowired
	ProjectDao projectDao;
	
	
	@Override
	public List<Project> getProjectList()throws Exception{
	return projectDao.getProjectList();}
	
	
	@Override
	public Project getProject(String projectId, Project project)throws Exception{
		return projectDao.getProject(projectId,project);}

	@Override
	public boolean updateProject(Project project)throws Exception{
		return projectDao.updateProject(project);
	}

	@Override
	public boolean addProject(Project project)throws Exception{
		return projectDao.addProject(project);
	}
	@Override
	public boolean deleteProject(String projectId, Project project)throws Exception{
		return projectDao.deleteProject(projectId,project);
	}


	@Override
	public List<Project> getFileNames(String projectId) throws Exception {
		return projectDao.getFileNames(projectId);
	}


	@Override
	public List<Year> getYearList() throws Exception {
		return projectDao.getYearList();
	}


	@Override
	public List<Project> getProjectPinkBookList() throws Exception {
		return projectDao.getProjectPinkBookList();
	}

	/**
	@Override
	public int getTotalRecords(Project obj, String searchParameter) throws Exception {
		return projectDao.getTotalRecords(obj,searchParameter);
	}


	@Override
	public List<Project> getProjectsList(Project obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return projectDao.getProjectsList(obj,startIndex,offset,searchParameter);
	}
*/
}


