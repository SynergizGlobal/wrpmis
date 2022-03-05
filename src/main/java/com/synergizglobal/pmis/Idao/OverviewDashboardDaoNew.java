package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboardNew;

public interface OverviewDashboardDaoNew {

	public List<OverviewDashboardNew> getLeftNavNodes(OverviewDashboardNew obj) throws Exception;

	public OverviewDashboardNew getTableauUrl(String dashboardId) throws Exception;

	public List<OverviewDashboardNew> getFilters(String dashboardId) throws Exception;
}
