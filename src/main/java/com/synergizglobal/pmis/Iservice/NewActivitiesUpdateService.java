package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface NewActivitiesUpdateService {

	public StripChart getAcivitiesBulkData(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateProjectsList(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateWorksList(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateContractsList(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateStructures(StripChart obj) throws Exception;
	public List<StripChart> getNewActivitiesUpdateInProgressStructures(StripChart obj) throws Exception;


	public List<StripChart> getNewActivitiesUpdateLines(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateSections(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateComponentsList(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesUpdateComponentIds(StripChart obj) throws Exception;

	public List<StripChart> getAcivitiesBulkActivitiesList(StripChart obj) throws Exception;

	public StripChart getNewActivitiesUpdateDetails(StripChart obj) throws Exception;

	public List<StripChart> getNewActivitiesfiltersList(StripChart obj) throws Exception;
	List<StripChart> getStructureTypesInActivitiesUpdate(StripChart obj) throws Exception;

	public boolean updateNewAcivitiesBulk(StripChart obj) throws Exception;
	
	public boolean insertFOBDailyUpdate(StripChart obj) throws Exception;

	public StripChart getNewAcivitiesUpdateData(StripChart obj) throws Exception;
}
