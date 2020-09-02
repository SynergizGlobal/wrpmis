package com.synergizglobal.pmis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.dao.TableauDashboardDao;
import com.synergizglobal.pmis.model.TableauDashboard;

@Service
public class TableauDashboardService {
	@Autowired
	TableauDashboardDao dao;
	
	/**
	 * This method get the TableauUrl
	 * @param activityWork is string type variable that holds the activityWork
	 * @return type of this method is tableauUrl
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public TableauDashboard getTableauUrl(String activityWork) throws Exception {
		return dao.getTableauUrl(activityWork);
	}
	
	/**
	 * This method get the tableau dashboard
	 * @return type of this method is dashboard
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public TableauDashboard getTableauDashBoard() throws Exception {
		return dao.getTableauDashBoard();
	}
	
	/**
	 * getting Tableau dashboard Url For Mobile
	 * @param getTableauUrlForMobile
	 * @return
	 * @throws Exception
	 */
	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception {
		return dao.getTableauUrlForMobile(infovizId);
	}
}
