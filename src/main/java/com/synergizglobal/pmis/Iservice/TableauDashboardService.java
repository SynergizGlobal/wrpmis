package com.synergizglobal.pmis.Iservice;

import com.synergizglobal.pmis.model.TableauDashboard;


public interface TableauDashboardService {
	public TableauDashboard getTableauUrl(String activityWork) throws Exception;
	
	public TableauDashboard getTableauDashBoard() throws Exception;

	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception;
}
