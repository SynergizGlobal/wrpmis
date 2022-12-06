package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.DashboardAccessForm;

public interface DashboardAccessFormDao {


	public List<DashboardAccessForm> getDashboardAccessFormsList() throws Exception;

	public boolean addDashboardAccessForm(DashboardAccessForm obj) throws Exception;

	public boolean updateDashboardAccessForm(DashboardAccessForm obj) throws Exception;

	public DashboardAccessForm getDashboardAccessFormDetails(DashboardAccessForm obj) throws Exception;

	public boolean deleteDashboardAccessForm(DashboardAccessForm obj) throws Exception;

	public List<DashboardAccessForm> getProjectsList(DashboardAccessForm obj)  throws Exception;

	public List<DashboardAccessForm> getWorksList(DashboardAccessForm obj)  throws Exception;

	public List<DashboardAccessForm> getModulesList(DashboardAccessForm obj)  throws Exception;

	public List<DashboardAccessForm> getWorkModuleWiseUsers(DashboardAccessForm obj) throws Exception;

	public List<DashboardAccessForm> getUsersDetails(DashboardAccessForm obj)  throws Exception;

	public List<DashboardAccessForm> getUserRolesInDashboardAccess(DashboardAccessForm obj) throws Exception;

	public List<DashboardAccessForm> getUserTypesInDashboardAccess(DashboardAccessForm obj) throws Exception;

	public List<DashboardAccessForm> getUsersInDashboardAccess(DashboardAccessForm obj) throws Exception;

}
