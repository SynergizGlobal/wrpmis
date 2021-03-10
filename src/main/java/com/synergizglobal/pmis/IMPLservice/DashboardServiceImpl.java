package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DashboardDao;
import com.synergizglobal.pmis.Iservice.DashboardService;
import com.synergizglobal.pmis.model.Dashboard;
@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	DashboardDao dao;

	@Override
	public List<Dashboard> getDashboardsList(Dashboard obj) throws Exception {
		return dao.getDashboardsList(obj);
	}

	@Override
	public List<Dashboard> getModulesFilterListInDashboard(Dashboard obj) throws Exception {
		return dao.getModulesFilterListInDashboard(obj);
	}

	@Override
	public List<Dashboard> getDashboardTypesFilterListInDashboard(Dashboard obj) throws Exception {
		return dao.getDashboardTypesFilterListInDashboard(obj);
	}

	@Override
	public List<Dashboard> getStatusFilterListInDashboard(Dashboard obj) throws Exception {
		return dao.getStatusFilterListInDashboard(obj);
	}

	@Override
	public List<Dashboard> getWorkListForDashboardForm(Dashboard obj) throws Exception {
		return dao.getWorkListForDashboardForm(obj);
	}

	@Override
	public List<Dashboard> getContractsListForDashboardForm(Dashboard obj) throws Exception {
		return dao.getContractsListForDashboardForm(obj);
	}

	@Override
	public List<Dashboard> getDashboardTypesListForDashboardForm(Dashboard obj) throws Exception {
		return dao.getDashboardTypesListForDashboardForm(obj);
	}

	@Override
	public List<Dashboard> getModulesListForDashboardForm(Dashboard obj) throws Exception {
		return dao.getModulesListForDashboardForm(obj);
	}

	@Override
	public List<Dashboard> getFolderssListForDashboardForm(Dashboard obj) throws Exception {
		return dao.getFolderssListForDashboardForm(obj);
	}

	@Override
	public List<Dashboard> getStatusListForDashboardForm(Dashboard obj) throws Exception {
		return dao.getStatusListForDashboardForm(obj);
	}

	@Override
	public Dashboard getDashboardForm(Dashboard obj) throws Exception {
		return dao.getDashboardForm(obj);
	}

	@Override
	public boolean addDashboard(Dashboard obj) throws Exception {
		return dao.addDashboard(obj);
	}

	@Override
	public boolean updateDashboard(Dashboard obj) throws Exception {
		return dao.updateDashboard(obj);
	}
}
