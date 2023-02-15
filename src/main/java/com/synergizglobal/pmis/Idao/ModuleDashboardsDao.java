package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;

public interface ModuleDashboardsDao {
	
	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard obj) throws Exception;
	
	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception;
	
	public List<OverviewDashboard> getFilters(OverviewDashboard obj) throws Exception;

	public List<OverviewDashboard> getFilteredOptions(OverviewDashboard dObj) throws Exception;
	
	public boolean getDashboardLeftMenuAccess(OverviewDashboard dObj) throws Exception;

	public List<OverviewDashboard> getTALeftNavNodes(OverviewDashboard obj) throws Exception;
}
