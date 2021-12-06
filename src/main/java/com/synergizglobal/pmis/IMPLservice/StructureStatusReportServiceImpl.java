package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.StructureStatusReportDao;
import com.synergizglobal.pmis.Iservice.StructureStatusReportService;
import com.synergizglobal.pmis.model.ActivitiesProgressReport;
@Service
public class StructureStatusReportServiceImpl implements StructureStatusReportService{

	@Autowired
	StructureStatusReportDao dao;

	@Override
	public ActivitiesProgressReport getActivitiesStatusReportData(
			ActivitiesProgressReport obj) throws Exception {
		return dao.getActivitiesStatusReportData(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getStructuresList(ActivitiesProgressReport obj) throws Exception {
		return dao.getStructuresList(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getProjectsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getProjectsFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getWorksFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getWorksFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getContractsFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getFobFilterListInActivitiesStatusReport(ActivitiesProgressReport obj)
			throws Exception {
		return dao.getFobFilterListInActivitiesStatusReport(obj);
	}

	@Override
	public List<ActivitiesProgressReport> getContractsListInActivities() throws Exception {
		return dao.getContractsListInActivities();
	}
	
}
