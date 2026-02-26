package com.synergizglobal.wrpmis.Iservice;

import com.synergizglobal.wrpmis.model.TableauDashboard;


public interface TableauDashboardService {
	public TableauDashboard getTableauUrl(String activityWork) throws Exception;
	
	public TableauDashboard getTableauDashBoard() throws Exception;

	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception;
}
