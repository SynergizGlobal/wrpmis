package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.UserActivityReport;

public interface UserActivityReportService {

	List<UserActivityReport> getContractsFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getWorksFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getHODsFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getModulessFilterListInUserActivityReport(UserActivityReport obj) throws Exception;

	UserActivityReport getUserActivityReportData(UserActivityReport obj) throws Exception;

	List<UserActivityReport> getUserActivityReportFormData(UserActivityReport obj) throws Exception;

}
