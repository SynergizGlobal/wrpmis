package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Dashboard;
import com.synergizglobal.pmis.reference.model.DashboardAccessForm;

public interface DashboardAccessFormService {

	public List<DashboardAccessForm> getDashboardAccessFormsList() throws Exception;

	public boolean addDashboardAccessForm(DashboardAccessForm obj) throws Exception;

	public boolean updateDashboardAccessForm(DashboardAccessForm obj) throws Exception;

	public DashboardAccessForm getDashboardAccessFormDetails(DashboardAccessForm obj) throws Exception;

	public boolean deleteDashboardAccessForm(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getProjectsList(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getWorksList(DashboardAccessForm obj) throws Exception;
	
	public boolean addDashboardUserAccess(DashboardAccessForm obj) throws Exception;
	
	public boolean updateWorkAccess(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getDashboardUserAccess(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getOverviewDashboardUserAccess(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getUsersDetails(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getUserRolesInDashboardAccess(DashboardAccessForm obj) throws Exception;

	public List<DashboardAccessForm> getUserTypesInDashboardAccess(DashboardAccessForm obj) throws Exception;

	public List<DashboardAccessForm> getUsersInDashboardAccess(DashboardAccessForm obj) throws Exception;	
	
	public List<DashboardAccessForm> getdashboardNames(DashboardAccessForm obj) throws Exception;
	
	public List<DashboardAccessForm> getLeftmenuDashboardNames(DashboardAccessForm obj) throws Exception;

	
}
