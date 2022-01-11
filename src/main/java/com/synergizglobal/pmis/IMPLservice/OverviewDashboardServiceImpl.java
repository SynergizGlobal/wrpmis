package com.synergizglobal.pmis.IMPLservice;

import java.util.ArrayList;
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
	public List<OverviewDashboard> getFormsList() throws Exception {
		return dao.getFormsList();
	}
	
	@Override
	public String getTableauUrl(String name) throws Exception {
		return dao.getTableauUrl(name);
	}	
}
