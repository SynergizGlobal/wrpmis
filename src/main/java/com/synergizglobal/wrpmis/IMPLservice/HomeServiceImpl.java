package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.HomeDao;
import com.synergizglobal.wrpmis.Iservice.HomeService;
import com.synergizglobal.wrpmis.model.Admin;
import com.synergizglobal.wrpmis.model.Forms;
import com.synergizglobal.wrpmis.model.Messages;
import com.synergizglobal.wrpmis.model.Project;
import com.synergizglobal.wrpmis.model.TableauDashboard;
import com.synergizglobal.wrpmis.model.User;
import com.synergizglobal.wrpmis.model.Work;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	HomeDao dao;
	
	/**
	 * This method get the menu list
	 * @return type of this method is  dashboardsList that is List type object
	 * @throws Exception will raise an exception when abnormal termination occur.
	 */
	@Override
	public List<TableauDashboard> getDashboardsList(String dashboardType,String base, User uObj) throws Exception {
		return dao.getDashboardsList(dashboardType,base,uObj);
	}
	/**
	 * This method get the forms list
	 * @param base it is string type variable that holds the base
	 * * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public List<Forms> getFormsList(String base, User uObj) throws Exception {
		return dao.getFormsList(base,uObj);
	}
	
	@Override
	public List<Project> getProjectsList() throws Exception {
		return dao.getProjectsList();
	}
	
	@Override
	public List<Work> getWorksList(Work obj) throws Exception {
		return dao.getWorksList(obj);
	}
	
	@Override
	public List<String> getExecutionStatusList() throws Exception {
		return dao.getExecutionStatusList();
	}
	@Override
	public List<Project> getProjectsInformation(Project obj) throws Exception {
		return dao.getProjectsInformation(obj);
	}
	@Override
	public List<Forms> getReportFormsList(String base, User uObj) throws Exception {
		return dao.getReportFormsList(base,uObj);
	}
	@Override
	public List<Work> getWorkDetails(Work obj) throws Exception {
		return dao.getWorkDetails(obj);
	}
	@Override
	public List<Work> getDashBoardNames(Work work) throws Exception {
		return dao.getDashBoardNames(work);
	}
	@Override
	public List<Work> getSubLink(Work obj) throws Exception {
		return dao.getSubLink(obj);
	}
	@Override
	public List<Admin> getAdminList(Admin admin) throws Exception {
		return dao.getAdminList(admin);
	}
	@Override
	public boolean addUserLastActiveDateTime(User uObj) throws Exception {
		return dao.addUserLastActiveDateTime(uObj);
	}
	@Override
	public boolean userLoginTimeout() throws Exception {
		return dao.userLoginTimeout();
	}
	@Override
	public List<Messages> getMessages(Messages mObj) throws Exception {
		return dao.getMessages(mObj);
	}
	@Override
	public List<Messages> changeMessagesReadStatus(Messages mObj) throws Exception {
		return dao.changeMessagesReadStatus(mObj);
	}	
	@Override
	public List<Messages> getMessageTypes(Messages mObj) throws Exception {
		return dao.getMessageTypes(mObj);
	}
	@Override
	public boolean checkURLAccessPermission(User obj, String requestURI) throws Exception {
		return dao.checkURLAccessPermission(obj,requestURI);
	}
	
	@Override
	public boolean doFlushHosts() throws Exception{
		return dao.doFlushHosts();
	}
}
