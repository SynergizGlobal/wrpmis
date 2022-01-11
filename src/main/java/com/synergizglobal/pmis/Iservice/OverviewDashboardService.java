package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;


public interface OverviewDashboardService {
	public List<OverviewDashboard> getFormsList() throws Exception;
	public String getTableauUrl(String name) throws Exception;
}
