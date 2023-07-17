package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.OverviewDashboardDao;
import com.synergizglobal.pmis.Iservice.OverviewDashboardService;
import com.synergizglobal.pmis.model.OverviewDashboard;

@Service
public class OverviewDashboardServiceImpl implements OverviewDashboardService {
	@Autowired
	OverviewDashboardDao dao;

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
	public List<OverviewDashboard> getArchiveDates(OverviewDashboard obj) throws Exception {
		return dao.getArchiveDates(obj);
	}	

	@Override
	public List<OverviewDashboard> getFilteredOptions(OverviewDashboard dObj) throws Exception {
		return dao.getFilteredOptions(dObj);
	}
	
	public boolean getDashboardLeftMenuAccess(OverviewDashboard dObj) throws Exception{
		return dao.getDashboardLeftMenuAccess(dObj);
	}

	@Override
	public int getWorkDroneSurveyCount(String work_id) throws Exception {
		return dao.getWorkDroneSurveyCount(work_id);
	}
}
