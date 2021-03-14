package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Report;

public interface ReportsAccessService {
	
	public List<Report> getReportsList(Report obj) throws Exception;

	public List<Report> getModulesFilterListInReport(Report obj) throws Exception;

	public List<Report> getStatusFilterListInReport(Report obj) throws Exception;

	public Report getReport(Report obj) throws Exception;

	public boolean addReport(Report obj) throws Exception;

	public boolean updateReport(Report obj) throws Exception;

	public List<Report> getUserRolesInReportAccess(Report obj) throws Exception;

	public List<Report> getUserTypesInReportAccess(Report obj) throws Exception;

	public List<Report> getUsersInReportAccess(Report obj) throws Exception;

	public List<Report> getModulesListForReportAccess(Report obj) throws Exception;

	public List<Report> getFolderssListForReportAccess(Report obj) throws Exception;

	public List<Report> getStatusListForReportAccess(Report obj) throws Exception;

}
