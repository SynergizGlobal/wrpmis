package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesUploadService {
	
	List<StripChart> getWorksListFilter(StripChart obj) throws Exception;

	List<StripChart> getContractsListFilter(StripChart obj) throws Exception;

	List<StripChart> getStructureListFilter(StripChart obj) throws Exception;

	List<StripChart> getComponentIdsListFilter(StripChart obj) throws Exception;

	List<StripChart> getComponentsListFilter(StripChart obj) throws Exception;

	int getTotalRecords(StripChart obj, String searchParameter) throws Exception;

	List<StripChart> getActivitiesList(StripChart obj, Integer startIndex, Integer offset, String searchParameter) throws Exception;

}
