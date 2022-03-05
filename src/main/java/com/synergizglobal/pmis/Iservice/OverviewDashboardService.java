package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;


public interface OverviewDashboardService {
	public List<OverviewDashboard> getFormsList(int ParentId) throws Exception;
	public String getTableauUrl(String name) throws Exception;
	public boolean saveLeftNavData(OverviewDashboard obj) throws Exception;
}
