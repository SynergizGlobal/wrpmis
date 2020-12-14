package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.Work;


public interface HomeDao {	
	public List<TableauDashboard> getDashboardsList(String dashboardType) throws Exception;
	public List<Forms> getFormsList(String base) throws Exception;
	public List<Project> getProjectsList() throws Exception;
	public List<Work> getWorksList(Work obj) throws Exception;
	public List<String> getGeneralStatusList() throws Exception;
	public List<Project> getProjectsInformation(Project obj) throws Exception;
}
