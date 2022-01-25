package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;

public interface OverviewDashboardDao {

	public List<OverviewDashboard> getFormsList(int ParentId) throws Exception;

	public String getTableauUrl(String name) throws Exception;

	public boolean saveLeftNavData(OverviewDashboard obj) throws Exception;
}
