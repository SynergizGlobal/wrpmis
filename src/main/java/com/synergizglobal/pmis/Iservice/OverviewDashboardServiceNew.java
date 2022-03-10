package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboardNew;


public interface OverviewDashboardServiceNew {
	public List<OverviewDashboardNew> getLeftNavNodes(OverviewDashboardNew obj) throws Exception;
	
	public OverviewDashboardNew getTableauUrl(String dashboardId) throws Exception;
	
	public List<OverviewDashboardNew> getFilters(OverviewDashboardNew obj) throws Exception;
}
