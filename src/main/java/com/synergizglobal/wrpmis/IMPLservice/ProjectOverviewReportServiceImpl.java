package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ProjectOverviewReportDao;
import com.synergizglobal.wrpmis.Iservice.ProjectOverviewReportService;
import com.synergizglobal.wrpmis.model.Contract;
@Service
public class ProjectOverviewReportServiceImpl implements ProjectOverviewReportService{

	@Autowired
	ProjectOverviewReportDao dao;

	@Override
	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception {
		return dao.getWorksFilterListInPOR(obj);
	}

	@Override
	public List<Contract> getDepartmentFilterListInPOR(Contract obj) throws Exception {
		return dao.getDepartmentFilterListInPOR(obj);
	}
	@Override
	public List<Contract> getProjectOverviewReportList(Contract obj) throws Exception {
		return dao.getProjectOverviewReportList(obj);
	}

}
