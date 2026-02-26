package com.synergizglobal.wrpmis.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.TableauDashboardDao;
import com.synergizglobal.wrpmis.Iservice.TableauDashboardService;
import com.synergizglobal.wrpmis.model.TableauDashboard;

@Service
public class TableauDashboardServiceImpl implements TableauDashboardService {
	@Autowired
	TableauDashboardDao dao;
	
	/**
	 * This method get the TableauUrl
	 * @param activityWork is string type variable that holds the activityWork
	 * @return type of this method is tableauUrl
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public TableauDashboard getTableauUrl(String activityWork) throws Exception {
		return dao.getTableauUrl(activityWork);
	}
	
	/**
	 * This method get the tableau dashboard
	 * @return type of this method is dashboard
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	@Override
	public TableauDashboard getTableauDashBoard() throws Exception {
		return dao.getTableauDashBoard();
	}
	
	/**
	 * getting Tableau dashboard Url For Mobile
	 * @param getTableauUrlForMobile
	 * @return
	 * @throws Exception
	 */
	@Override
	public TableauDashboard getTableauUrlForMobile(String infovizId) throws Exception {
		return dao.getTableauUrlForMobile(infovizId);
	}
}
