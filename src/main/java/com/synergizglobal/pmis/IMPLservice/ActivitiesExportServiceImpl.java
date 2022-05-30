package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesExportReportDao;
import com.synergizglobal.pmis.Idao.ActivitiesProgressReportDao;
import com.synergizglobal.pmis.Iservice.ActivitiesExportService;
import com.synergizglobal.pmis.model.StripChart;
@Service
public class ActivitiesExportServiceImpl implements ActivitiesExportService{
	
	@Autowired
	ActivitiesExportReportDao dao;
	@Override
	public List<StripChart> getProjectsFilterListInActivitiesExportReport(StripChart obj) throws Exception {
		return dao.getProjectsFilterListInActivitiesExportReport(obj);
	}
	@Override
	public List<StripChart> getWorksFilterListInActivitiesExportReport(StripChart obj) throws Exception {
		return dao.getWorksFilterListInActivitiesExportReport(obj);
	}
	@Override
	public List<StripChart> getContractListInActivitiesExportReport(StripChart obj) throws Exception {
		return dao.getContractListInActivitiesExportReport(obj);
	}
	@Override
	public StripChart generateActivitiesExportReport(StripChart obj) throws Exception {
		return dao.generateActivitiesExportReport(obj);
	}

}
