package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ReportsAccessDao;
import com.synergizglobal.wrpmis.Iservice.ReportsAccessService;
import com.synergizglobal.wrpmis.model.Report;

@Service
public class ReportsAccessServiceImpl implements ReportsAccessService{
	@Autowired
	ReportsAccessDao dao;

	@Override
	public List<Report> getReportsList(Report obj) throws Exception {
		return dao.getReportsList(obj);
	}
	
	@Override
	public Report getReport(Report obj) throws Exception {
		return dao.getReport(obj);
	}

	@Override
	public List<Report> getModulesListForReportAccess(Report obj) throws Exception {
		return dao.getModulesListForReportAccess(obj);
	}

	@Override
	public List<Report> getFolderssListForReportAccess(Report obj) throws Exception {
		return dao.getFolderssListForReportAccess(obj);
	}

	@Override
	public List<Report> getStatusListForReportAccess(Report obj) throws Exception {
		return dao.getStatusListForReportAccess(obj);
	}

	@Override
	public List<Report> getUserRolesInReportAccess(Report obj) throws Exception {
		return dao.getUserRolesInReportAccess(obj);
	}

	@Override
	public List<Report> getUserTypesInReportAccess(Report obj) throws Exception {
		return dao.getUserTypesInReportAccess(obj);
	}

	@Override
	public List<Report> getUsersInReportAccess(Report obj) throws Exception {
		return dao.getUsersInReportAccess(obj);
	}

	@Override
	public boolean updateAccessReport(Report obj) throws Exception {
		return dao.updateAccessReport(obj);
	}

	@Override
	public List<Report> getModulesFilterListInReport(Report obj) throws Exception {
		return dao.getModulesFilterListInReport(obj);
	}

	@Override
	public List<Report> getStatusFilterListInReport(Report obj) throws Exception {
		return dao.getStatusFilterListInReport(obj);
	}

}
