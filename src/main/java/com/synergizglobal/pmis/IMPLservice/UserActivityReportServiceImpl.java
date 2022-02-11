package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.UserActivityReportDao;
import com.synergizglobal.pmis.Iservice.UserActivityReportService;
import com.synergizglobal.pmis.model.UserActivityReport;
@Service
public class UserActivityReportServiceImpl implements UserActivityReportService{
	@Autowired
	 UserActivityReportDao dao;

	@Override
	public List<UserActivityReport> getContractsFilterListInUserActivityReport(UserActivityReport obj)
			throws Exception {
		return dao.getContractsFilterListInUserActivityReport(obj);
	}

	@Override
	public List<UserActivityReport> getWorksFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		return dao.getWorksFilterListInUserActivityReport(obj);
	}

	@Override
	public List<UserActivityReport> getHODsFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		return dao.getHODsFilterListInUserActivityReport(obj);
	}

	@Override
	public List<UserActivityReport> getModulessFilterListInUserActivityReport(UserActivityReport obj) throws Exception {
		return dao.getModulessFilterListInUserActivityReport(obj);
	}

	@Override
	public UserActivityReport getUserActivityReportData(UserActivityReport obj) throws Exception {
		return dao.getUserActivityReportData(obj);
	}

	@Override
	public List<UserActivityReport> getUserActivityReportFormData(UserActivityReport obj) throws Exception {
		return dao.getUserActivityReportFormData(obj);
	}

	@Override
	public List<UserActivityReport> getWorksListForUserInactiveReportForm(UserActivityReport obj) throws Exception {
		return dao.getWorksListForUserInactiveReportForm(obj);
	}

	@Override
	public List<UserActivityReport> getModulesListForUserInactiveReportForm(UserActivityReport obj) throws Exception {
		return dao.getModulesListForUserInactiveReportForm(obj);
	}

	@Override
	public int checkInactiveUsersExistsOrNot(UserActivityReport obj) throws Exception {
		return dao.checkInactiveUsersExistsOrNot(obj);
	}
}
