package com.synergizglobal.wrpmis.IMPLservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.CustomReportDao;
import com.synergizglobal.wrpmis.Iservice.CustomReportService;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.CustomReportColumns;

@Service
public class CustomReportServiceImpl implements CustomReportService{
	@Autowired
	CustomReportDao dao;

	@Override
	public List<Contract> getAllContractList(Contract obj) throws Exception {
		return dao.getAllContractList(obj);
	}
	
	@Override
	public List<CustomReportColumns> getModuleGroups(CustomReportColumns obj) throws Exception {
		return dao.getModuleGroups(obj);
	}
	@Override
	public List<CustomReportColumns> getModuleFilters(CustomReportColumns obj) throws Exception {
		return dao.getModuleFilters(obj);
	}	
	@Override
	public List<CustomReportColumns> getModuleFiltersOptionValues(CustomReportColumns obj) throws Exception {
		return dao.getModuleFiltersOptionValues(obj);
	}	
	@Override
	public List<CustomReportColumns> getModuleGroupColumns(CustomReportColumns obj) throws Exception {
		return dao.getModuleGroupColumns(obj);
	}
	public ArrayList<String[]> getTableColumns(CustomReportColumns obj) throws Exception
	{
		return dao.getTableColumns(obj);
	}
	public boolean saveCustomReportLayout(CustomReportColumns obj) throws Exception
	{
		return dao.saveCustomReportLayout(obj);
	}
	public boolean checkLayoutName(CustomReportColumns obj) throws Exception
	{
		return dao.checkLayoutName(obj);
	}
	public boolean removeLayout(CustomReportColumns obj) throws Exception
	{
		return dao.removeLayout(obj);
	}	
	@Override
	public List<CustomReportColumns> getLayouts(CustomReportColumns obj) throws Exception {
		return dao.getLayouts(obj);
	}
	
	
}
