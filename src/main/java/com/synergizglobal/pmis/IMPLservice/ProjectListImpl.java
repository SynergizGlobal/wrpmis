package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ProjectDao;
import com.synergizglobal.pmis.Iservice.ProjectService;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

@Service
public class ProjectListImpl implements ProjectService{
	@Autowired
	ProjectDao projectDao;
	
	
	@Override
	public List<Project> getProjectList()throws Exception{
	return projectDao.getProjectList();}
	
	
	@Override
	public Project editProject(String projectId, Project project)throws Exception{
		return projectDao.editProject(projectId,project);}

	@Override
	public boolean updateProject(Project project)throws Exception{
		return projectDao.updateProject(project);
	}

	@Override
	public boolean addProject(Project project)throws Exception{
		return projectDao.addProject(project);
	}
	@Override
	public boolean deleteProjectRow(String projectId, Project project)throws Exception{
		return projectDao.deleteProjectRow(projectId,project);
	}
	@Override
	public List<Project> getSafetyList(Project project)throws Exception{
		return projectDao.getSafetyList(project);

	}

	}


