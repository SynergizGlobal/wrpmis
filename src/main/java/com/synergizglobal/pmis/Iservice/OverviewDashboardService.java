package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;


public interface OverviewDashboardService {
	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard obj) throws Exception;
	
	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception;
	
	public List<OverviewDashboard> getFilters(String dashboardId) throws Exception;
}
