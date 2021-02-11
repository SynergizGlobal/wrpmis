package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.HomeDao;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.model.Forms;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.TableauDashboard;
import com.synergizglobal.pmis.model.Work;

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
	public List<TableauDashboard> getDashboardsList(String dashboardType,String base) throws Exception {
		return dao.getDashboardsList(dashboardType,base);
	}
	/**
	 * This method get the forms list
	 * @param base it is string type variable that holds the base
	 * * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public List<Forms> getFormsList(String base) throws Exception {
		return dao.getFormsList(base);
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
	public List<String> getGeneralStatusList() throws Exception {
		return dao.getGeneralStatusList();
	}
	@Override
	public List<Project> getProjectsInformation(Project obj) throws Exception {
		return dao.getProjectsInformation(obj);
	}
	@Override
	public List<Forms> getReportFormsList(String base) throws Exception {
		return dao.getReportFormsList(base);
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
}
