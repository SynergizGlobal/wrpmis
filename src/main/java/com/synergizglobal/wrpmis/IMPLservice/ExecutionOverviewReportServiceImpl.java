package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ExecutionOverviewReportDao;
import com.synergizglobal.wrpmis.Iservice.ExecutionOverviewReportService;
import com.synergizglobal.wrpmis.model.StripChart;
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
	
	@Override
	public List<StripChart> getStructureTypesbyWorkId(StripChart obj) throws Exception {
		return dao.getStructureTypesbyWorkId(obj);
	}
	
	@Override
	public List<StripChart> getStructuresByWorkId(StripChart obj) throws Exception {
		return dao.getStructuresByWorkId(obj);
	}
	
	@Override
	public List<StripChart> getComponentsByWorkId(StripChart obj) throws Exception{
		return dao.getComponentsByWorkId(obj);
	}
	
	public List<StripChart> getActivitiesByWorkId(StripChart obj) throws Exception{
		return dao.getActivitiesByWorkId(obj);
	}
	public List<StripChart> getComponentIDsByWorkId(StripChart obj) throws Exception
	{
		return dao.getComponentIDsByWorkId(obj);
	}
}
