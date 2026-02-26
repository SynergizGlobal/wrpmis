package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.model.Dashboard;
import com.synergizglobal.wrpmis.reference.Idao.DashboardAccessFormDao;
import com.synergizglobal.wrpmis.reference.Iservice.DashboardAccessFormService;
import com.synergizglobal.wrpmis.reference.model.DashboardAccessForm;
@Service
public class DashboardAccessFormServiceImpl implements DashboardAccessFormService{

	@Autowired
	DashboardAccessFormDao dao;

	@Override
	public List<DashboardAccessForm> getDashboardAccessFormsList() throws Exception {
		return dao.getDashboardAccessFormsList();
	}

	@Override
	public boolean addDashboardAccessForm(DashboardAccessForm obj) throws Exception {
		return dao.addDashboardAccessForm(obj);
	}

	@Override
	public boolean updateDashboardAccessForm(DashboardAccessForm obj) throws Exception {
		return dao.updateDashboardAccessForm(obj);
	}

	@Override
	public DashboardAccessForm getDashboardAccessFormDetails(DashboardAccessForm obj) throws Exception {
		return dao.getDashboardAccessFormDetails(obj);
	}

	@Override
	public boolean deleteDashboardAccessForm(DashboardAccessForm obj) throws Exception {
		return dao.deleteDashboardAccessForm(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getProjectsList(DashboardAccessForm obj) throws Exception
	{
		return dao.getProjectsList(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getWorksList(DashboardAccessForm obj) throws Exception
	{
		return dao.getWorksList(obj);
	}
	
	@Override
	public boolean addDashboardUserAccess(DashboardAccessForm obj) throws Exception
	{
		return dao.addDashboardUserAccess(obj);
	}
	@Override
	public boolean updateWorkAccess(DashboardAccessForm obj) throws Exception
	{
		return dao.updateWorkAccess(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getDashboardUserAccess(DashboardAccessForm obj) throws Exception
	{
		return dao.getDashboardUserAccess(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getUsersDetails(DashboardAccessForm obj) throws Exception
	{
		return dao.getUsersDetails(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getUserRolesInDashboardAccess(DashboardAccessForm obj) throws Exception {
		return dao.getUserRolesInDashboardAccess(obj);
	}

	@Override
	public List<DashboardAccessForm> getUserTypesInDashboardAccess(DashboardAccessForm obj) throws Exception {
		return dao.getUserTypesInDashboardAccess(obj);
	}

	@Override
	public List<DashboardAccessForm> getUsersInDashboardAccess(DashboardAccessForm obj) throws Exception {
		return dao.getUsersInDashboardAccess(obj);
	}	
	
	@Override
	public List<DashboardAccessForm> getdashboardNames(DashboardAccessForm obj) throws Exception
	{
		return dao.getdashboardNames(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getLeftmenuDashboardNames(DashboardAccessForm obj) throws Exception
	{
		return dao.getLeftmenuDashboardNames(obj);
	}
	
	@Override
	public List<DashboardAccessForm> getOverviewDashboardUserAccess(DashboardAccessForm obj) throws Exception
	{
		return dao.getOverviewDashboardUserAccess(obj);
	}
	
}
