package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;

public interface OverviewDashboardDao {

	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard obj) throws Exception;

	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception;

	public List<OverviewDashboard> getFilters(String dashboardId) throws Exception;
}
