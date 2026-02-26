package com.synergizglobal.wrpmis.Iservice;

import java.util.List;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.Year;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ProjectService {

	public List<Project> getProjectList(Project project)throws Exception;

	public Project getProject(String projectId, Project project)throws Exception;

	public boolean updateProject(Project project)throws Exception;

	public boolean addProject(Project project)throws Exception;

	public boolean deleteProject(String projectId, Project project)throws Exception;

	public List<Project> getFileNames(String projectId) throws Exception;

	public List<Year> getYearList() throws Exception;

	public List<Project> getProjectPinkBookList() throws Exception;

	public List<Project> getProjectFileTypes() throws Exception;

	public List<TrainingType> getProjectTypeDetails() throws Exception;

	public List<TrainingType> getRailwayZones() throws Exception;

	public List<Project> getAllDivisions() throws Exception;

	public List<Project> getAllSections() throws Exception;

	public String[] uploadProjectChainagesData(List<Project> projectChainagesList, Project project) throws Exception;
	public boolean saveProjectChainagesDataUploadFile(Project obj) throws Exception;

}
