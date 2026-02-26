package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.OverviewDashboard;


public interface OverviewDashboardService {
	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard obj) throws Exception;
	
	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception;
	
	public List<OverviewDashboard> getFilters(OverviewDashboard obj) throws Exception;
	
	public List<OverviewDashboard> getArchiveDates(OverviewDashboard obj) throws Exception;

	public List<OverviewDashboard> getFilteredOptions(OverviewDashboard dObj) throws Exception;
	public boolean getDashboardLeftMenuAccess(OverviewDashboard dObj) throws Exception;


	int getWorkDroneSurveyCount(String work_id) throws Exception;

	public List<OverviewDashboard> getContractorRoleWorks(String user_role_name) throws Exception;

	public List<OverviewDashboard> getContractorRoleWorkContracts(String user_name)  throws Exception;

	public String getDashboardURL(String dashboardId, String projectId);

}
