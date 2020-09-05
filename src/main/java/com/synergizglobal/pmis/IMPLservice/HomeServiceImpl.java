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
	public List<TableauDashboard> getDashboardsList() throws Exception {
		return dao.getDashboardsList();
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
	public List<Project> getProjectsListForSearch() throws Exception {
		return dao.getProjectsListForSearch();
	}
	
	@Override
	public List<Work> getWorksListForSearch(Work obj) throws Exception {
		return dao.getWorksListForSearch(obj);
	}
}
