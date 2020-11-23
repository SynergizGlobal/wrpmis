package com.synergizglobal.pmis.Idao;

import java.util.List;
import java.util.Set;

import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesUploadDao {

	List<StripChart> getWorksListFilter(StripChart obj) throws Exception;

	List<StripChart> getContractsListFilter(StripChart obj) throws Exception;

	List<StripChart> getStructureListFilter(StripChart obj) throws Exception;

	List<StripChart> getComponentIdsListFilter(StripChart obj) throws Exception;

	List<StripChart> getComponentsListFilter(StripChart obj) throws Exception;

	int getTotalRecords(StripChart obj, String searchParameter) throws Exception;

	List<StripChart> getActivitiesList(StripChart obj, Integer startIndex, Integer offset, String searchParameter) throws Exception;

	int uploadActivities(Set<String> contractList, Set<String> componentList, Set<String> structureList,
			Set<String> lineList, Set<String> sectionList, Set<String> scTypeList, Set<String> orderList,
			Set<String> latitudeList, Set<String> longitudeList, List<StripChart> activityList) throws Exception;

}
