package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ReportsAccessDao;
import com.synergizglobal.pmis.Iservice.ReportsAccessService;
import com.synergizglobal.pmis.model.Report;

@Service
public class ReportsAccessServiceImpl implements ReportsAccessService{
	@Autowired
	ReportsAccessDao dao;

	@Override
	public List<Report> getReportsList(Report obj) throws Exception {
		return dao.getReportsList(obj);
	}

	@Override
	public List<Report> getModulesFilterListInReport(Report obj) throws Exception {
		return dao.getModulesFilterListInReport(obj);
	}

	@Override
	public List<Report> getStatusFilterListInReport(Report obj) throws Exception {
		return dao.getStatusFilterListInReport(obj);
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
	public Report getReport(Report obj) throws Exception {
		return dao.getReport(obj);
	}

	@Override
	public boolean addReport(Report obj) throws Exception {
		return dao.addReport(obj);
	}

	@Override
	public boolean updateReport(Report obj) throws Exception {
		return dao.updateReport(obj);
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

}
