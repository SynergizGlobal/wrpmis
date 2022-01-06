package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.OverviewDashboard;

public interface OverviewDashboardDao {

	public List<OverviewDashboard> getFormsList() throws Exception;
}
