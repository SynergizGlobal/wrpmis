package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ProgressUpdateReportDao;
import com.synergizglobal.wrpmis.Iservice.ProgressUpdateReportService;
import com.synergizglobal.wrpmis.model.ActivitiesProgressReport;
@Service
public class ProgressUpdateReportServiceImpl implements ProgressUpdateReportService{
	@Autowired
	ProgressUpdateReportDao dao;

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getContractsFilterListInProgressReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getWorksFilterListInProgressReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractorsFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getContractorsFilterListInProgressReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getHodFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getHodFilterListInProgressReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getDyhodFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getDyhodFilterListInProgressReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getProjectsFilterListInProgressReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInProgressReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getFobFilterListInProgressReport(obj);
	}

	@Override
	public ActivitiesProgressReport getActivitiesProgressUpdate(ActivitiesProgressReport obj) throws Exception {
		return dao.getActivitiesProgressUpdate(obj);
	}
}
