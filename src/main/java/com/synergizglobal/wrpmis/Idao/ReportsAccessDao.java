package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Report;

public interface ReportsAccessDao {

	public List<Report> getReportsList(Report obj) throws Exception;
	
	public Report getReport(Report obj) throws Exception;

	public List<Report> getUserRolesInReportAccess(Report obj) throws Exception;

	public List<Report> getUserTypesInReportAccess(Report obj) throws Exception;

	public List<Report> getUsersInReportAccess(Report obj) throws Exception;

	public List<Report> getModulesListForReportAccess(Report obj) throws Exception;

	public List<Report> getFolderssListForReportAccess(Report obj) throws Exception;

	public List<Report> getStatusListForReportAccess(Report obj) throws Exception;

	public boolean updateAccessReport(Report obj) throws Exception;
	
	public List<Report> getModulesFilterListInReport(Report obj) throws Exception;

	public List<Report> getStatusFilterListInReport(Report obj) throws Exception;

}
