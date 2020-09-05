package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.Work;


public interface HomeDao {	
	public List<TableauDashboard> getDashboardsList() throws Exception;
	public List<Forms> getFormsList(String base) throws Exception;
	public List<Project> getProjectsListForSearch() throws Exception;
	public List<Work> getWorksListForSearch(Work obj) throws Exception;
}
