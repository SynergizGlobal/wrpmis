package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.Dashboard;

public interface DashboardsAccessService {

	public List<Dashboard> getDashboardsList(Dashboard obj) throws Exception;

	public List<Dashboard> getModulesFilterListInDashboard(Dashboard obj) throws Exception;

	public List<Dashboard> getDashboardTypesFilterListInDashboard(Dashboard obj) throws Exception;

	public List<Dashboard> getStatusFilterListInDashboard(Dashboard obj) throws Exception;

	public List<Dashboard> getWorkListForDashboardForm(Dashboard obj) throws Exception;

	public List<Dashboard> getContractsListForDashboardForm(Dashboard obj) throws Exception;

	public List<Dashboard> getDashboardTypesListForDashboardForm(Dashboard obj) throws Exception;

	public List<Dashboard> getModulesListForDashboardForm(Dashboard obj) throws Exception;

	public List<Dashboard> getFolderssListForDashboardForm(Dashboard obj) throws Exception;

	public List<Dashboard> getStatusListForDashboardForm(Dashboard obj) throws Exception;

	public Dashboard getDashboardForm(Dashboard obj) throws Exception;

	public List<Dashboard> getUserRolesInDashboardAccess(Dashboard obj) throws Exception;

	public List<Dashboard> getUserTypesInDashboardAccess(Dashboard obj) throws Exception;

	public List<Dashboard> getUsersInDashboardAccess(Dashboard obj) throws Exception;

	public boolean updateTableauDashboard(Dashboard obj) throws Exception;

}
