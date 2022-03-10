package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.OverviewDashboardDaoNew;
import com.synergizglobal.pmis.Iservice.OverviewDashboardServiceNew;
import com.synergizglobal.pmis.model.OverviewDashboardNew;

@Service
public class OverviewDashboardServiceImplNew implements OverviewDashboardServiceNew {
	@Autowired
	OverviewDashboardDaoNew dao;

	@Override
	public List<OverviewDashboardNew> getLeftNavNodes(OverviewDashboardNew obj) throws Exception {
		return dao.getLeftNavNodes(obj);
	}
	
	@Override
	public OverviewDashboardNew getTableauUrl(String dashboardId) throws Exception {
		return dao.getTableauUrl(dashboardId);
	}

	@Override
	public List<OverviewDashboardNew> getFilters(OverviewDashboardNew obj) throws Exception {
		return dao.getFilters(obj);
	}	
}
