package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ExecutionOverviewReportDao;
import com.synergizglobal.pmis.Iservice.ExecutionOverviewReportService;
import com.synergizglobal.pmis.model.StripChart;
@Service
public class ExecutionOverviewReportServiceImpl implements ExecutionOverviewReportService{

	@Autowired
	ExecutionOverviewReportDao dao;

	@Override
	public List<StripChart> getWorksFilterListInEOR(StripChart obj) throws Exception {
		return dao.getWorksFilterListInEOR(obj);
	}

	@Override
	public List<StripChart> getDepartmentFilterListInEOR(StripChart obj) throws Exception {
		return dao.getDepartmentFilterListInEOR(obj);
	}

	@Override
	public List<StripChart> getContractIdFilterListInEOR(StripChart obj) throws Exception {
		return dao.getContractIdFilterListInEOR(obj);
	}
	
	@Override
	public List<StripChart> getExecutionOverviewReportList(StripChart obj) throws Exception {
		return dao.getExecutionOverviewReportList(obj);
	}	
}
