package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ProgressBulkUpdateDao {

	public StripChart getProgressBulkData(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateProjectsList(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateWorksList(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateContractsList(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateStructures(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateSections(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateComponentIds(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkUpdateLines(StripChart obj)throws Exception;

	public List<StripChart> getProgressBulkActivitiesList(StripChart obj)throws Exception;

	public StripChart getProgressBulkUpdateDetails(StripChart obj)throws Exception;

	public List<StripChart> getstripChartfilterList(StripChart obj)throws Exception;

	public boolean updateProgressBulk(StripChart obj)throws Exception;

}
