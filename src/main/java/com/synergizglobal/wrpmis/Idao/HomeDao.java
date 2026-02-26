package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Admin;
import com.synergizglobal.wrpmis.model.Forms;
import com.synergizglobal.wrpmis.model.Messages;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.TableauDashboard;
import com.synergizglobal.wrpmis.model.User;
import com.synergizglobal.wrpmis.model.Work;


public interface HomeDao {	
	public List<TableauDashboard> getDashboardsList(String dashboardType, String base, User uObj) throws Exception;
	
	public List<Forms> getFormsList(String base, User userDetails) throws Exception;
	
	public List<Project> getProjectsList() throws Exception;
	
	public List<Work> getWorksList(Work obj) throws Exception;
	
	public List<String> getExecutionStatusList() throws Exception;
	
	public List<Project> getProjectsInformation(Project obj) throws Exception;
	
	public List<Forms> getReportFormsList(String base, User uObj) throws Exception;
	
	public List<Work> getWorkDetails(Work obj) throws Exception;
	
	public List<Work> getDashBoardNames(Work work) throws Exception;
	
	public List<Work> getSubLink(Work obj) throws Exception;
	
	public List<Admin> getAdminList(Admin admin) throws Exception;
	
	public boolean addUserLastActiveDateTime(User uObj) throws Exception;
	
	public boolean userLoginTimeout() throws Exception;

	public List<Messages> getMessages(Messages mObj) throws Exception;

	public List<Messages> getMessageTypes(Messages mObj) throws Exception;

	public List<Messages> changeMessagesReadStatus(Messages mObj) throws Exception;

	public boolean checkURLAccessPermission(User obj, String requestURI) throws Exception;
	
	public boolean doFlushHosts() throws Exception;
}
