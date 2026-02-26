package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ProjectDao;
import com.synergizglobal.wrpmis.Iservice.ProjectService;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.Year;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class ProjectListImpl implements ProjectService{
	@Autowired
	ProjectDao projectDao;
	
	
	@Override
	public List<Project> getProjectList(Project project)throws Exception{
	return projectDao.getProjectList(project);}
	
	
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


	@Override
	public List<Project> getProjectFileTypes() throws Exception {
		return projectDao.getProjectFileTypes();
	}


	@Override
	public List<TrainingType> getProjectTypeDetails() throws Exception {
		return projectDao.getProjectTypeDetails();
	}


	@Override
	public List<TrainingType> getRailwayZones() throws Exception {
		return projectDao.getRailwayZones();
	}
	
	@Override
	public List<Project> getAllDivisions() throws Exception
	{
		return projectDao.getAllDivisions();
	}
	
	@Override
	public List<Project> getAllSections() throws Exception
	{
		return projectDao.getAllSections();
	}


	@Override
	public String[] uploadProjectChainagesData(List<Project> projectChainagesList, Project project) throws Exception {
		return projectDao.uploadProjectChainagesData(projectChainagesList,project);
	}


	@Override
	public boolean saveProjectChainagesDataUploadFile(Project obj) throws Exception {
		return projectDao.saveProjectChainagesDataUploadFile(obj);
	}
}


