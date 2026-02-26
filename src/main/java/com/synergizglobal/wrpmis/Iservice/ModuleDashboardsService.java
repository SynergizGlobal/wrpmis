package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.OverviewDashboard;

public interface ModuleDashboardsService {
	
	public List<OverviewDashboard> getTALeftNavNodes(OverviewDashboard obj) throws Exception;
	
	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard obj) throws Exception;
	
	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception;
	
	public List<OverviewDashboard> getFilters(OverviewDashboard obj) throws Exception;

	public List<OverviewDashboard> getFilteredOptions(OverviewDashboard dObj) throws Exception;
	
	public boolean getDashboardLeftMenuAccess(OverviewDashboard dObj) throws Exception;

	public List<OverviewDashboard> getRollingStockLeftNavNodes(OverviewDashboard obj) throws Exception;

	public boolean getRollingDashboardLeftMenuAccess(OverviewDashboard obj) throws Exception;
}
