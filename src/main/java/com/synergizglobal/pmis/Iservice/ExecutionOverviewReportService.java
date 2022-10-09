package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ExecutionOverviewReportService {

	public List<StripChart> getWorksFilterListInEOR(StripChart obj) throws Exception;

	public List<StripChart> getDepartmentFilterListInEOR(StripChart obj) throws Exception;

	public List<StripChart> getContractIdFilterListInEOR(StripChart obj) throws Exception;
	public List<StripChart> getExecutionOverviewReportList(StripChart obj) throws Exception;
	public List<StripChart> getStructureTypesbyWorkId(StripChart obj) throws Exception;
	public List<StripChart> getStructuresByWorkId(StripChart obj) throws Exception;
	public List<StripChart> getComponentsByWorkId(StripChart obj) throws Exception;
	public List<StripChart> getComponentIDsByWorkId(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesByWorkId(StripChart obj) throws Exception;
	
	
}
