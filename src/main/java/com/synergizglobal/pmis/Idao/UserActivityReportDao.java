package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.UserActivityReport;

public interface UserActivityReportDao {

	List<UserActivityReport> getContractsFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getWorksFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getHODsFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getModulessFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	UserActivityReport getUserActivityReportData(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getUserActivityReportFormData(UserActivityReport obj) throws Exception;
	
	List<UserActivityReport> getWorksListForUserInactiveReportForm(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getModulesListForUserInactiveReportForm(UserActivityReport obj) throws Exception;

	public int checkInactiveUsersExistsOrNot(UserActivityReport obj) throws Exception;

	UserActivityReport getInactiveUsersReportData(UserActivityReport obj) throws Exception;

}
