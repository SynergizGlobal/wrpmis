package com.synergizglobal.pmis.Idao;

import com.synergizglobal.pmis.model.TableauDashboard;


public interface TableauDashboardDao {
	
	public TableauDashboard getTableauUrl(String dashboardName) throws Exception;
	
	public TableauDashboard getTableauDashBoard() throws Exception;
	
	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception;
}
