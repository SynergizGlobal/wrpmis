package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesBulkUpdateService {

	public StripChart getAcivitiesBulkData(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateProjectsList(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateWorksList(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateContractsList(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateStructures(StripChart obj) throws Exception;
	public List<StripChart> getAcivitiesBulkUpdateInProgressStructures(StripChart obj) throws Exception;


	public List<StripChart> getAcivitiesBulkUpdateLines(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateSections(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateComponentsList(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkUpdateComponentIds(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkActivitiesList(StripChart obj) throws Exception;

	public StripChart getAcivitiesBulkUpdateDetails(StripChart obj) throws Exception;

	public List<StripChart> getActivitiesfiltersList(StripChart obj) throws Exception;

	public boolean updateAcivitiesBulk(StripChart obj) throws Exception;
	
	public boolean insertFOBDailyUpdate(StripChart obj) throws Exception;

	public StripChart getAcivitiesBulkUpdateData(StripChart obj) throws Exception;
}
