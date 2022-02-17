package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ExecutionOverviewReportService {

	public List<StripChart> getWorksFilterListInEOR(StripChart obj) throws Exception;

	public List<StripChart> getDepartmentFilterListInEOR(StripChart obj) throws Exception;

	public List<StripChart> getContractIdFilterListInEOR(StripChart obj) throws Exception;
	public List<StripChart> getExecutionOverviewReportList(StripChart obj) throws Exception;
	
}
