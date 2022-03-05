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
	public List<OverviewDashboard> getFormsList(int ParentId) throws Exception {
		return dao.getFormsList(ParentId);
	}
	
	@Override
	public String getTableauUrl(String name) throws Exception {
		return dao.getTableauUrl(name);
	}
	
	@Override
	public boolean saveLeftNavData(OverviewDashboard obj)  throws Exception {
		return dao.saveLeftNavData(obj);
	}	
}
