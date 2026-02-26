package com.synergizglobal.wrpmis.Idao;

import com.synergizglobal.wrpmis.model.TableauDashboard;


public interface TableauDashboardDao {
	
	public TableauDashboard getTableauUrl(String dashboardName) throws Exception;
	
	public TableauDashboard getTableauDashBoard() throws Exception;
	
	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception;
}
