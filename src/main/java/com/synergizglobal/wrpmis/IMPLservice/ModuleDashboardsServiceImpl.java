package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ModuleDashboardsDao;
import com.synergizglobal.wrpmis.Iservice.ModuleDashboardsService;
import com.synergizglobal.wrpmis.model.OverviewDashboard;

@Service
public class ModuleDashboardsServiceImpl implements ModuleDashboardsService{
	@Autowired
	ModuleDashboardsDao dao;

	@Override
	public List<OverviewDashboard> getLeftNavNodes(OverviewDashboard obj) throws Exception {
		return dao.getLeftNavNodes(obj);
	}
	
	@Override
	public OverviewDashboard getTableauUrl(String dashboardId) throws Exception {
		return dao.getTableauUrl(dashboardId);
	}

	@Override
	public List<OverviewDashboard> getFilters(OverviewDashboard obj) throws Exception {
		return dao.getFilters(obj);
	}

	@Override
	public List<OverviewDashboard> getFilteredOptions(OverviewDashboard dObj) throws Exception {
		return dao.getFilteredOptions(dObj);
	}
	
	public boolean getDashboardLeftMenuAccess(OverviewDashboard dObj) throws Exception{
		return dao.getDashboardLeftMenuAccess(dObj);
	}

	@Override
	public List<OverviewDashboard> getTALeftNavNodes(OverviewDashboard obj) throws Exception {
		return dao.getTALeftNavNodes(obj);
	}

	@Override
	public List<OverviewDashboard> getRollingStockLeftNavNodes(OverviewDashboard obj) throws Exception {
		return dao.getRollingStockLeftNavNodes(obj);
	}

	@Override
	public boolean getRollingDashboardLeftMenuAccess(OverviewDashboard obj) throws Exception {
		return dao.getRollingDashboardLeftMenuAccess(obj);
	}
}
