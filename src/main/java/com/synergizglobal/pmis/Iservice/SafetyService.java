package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Safety;

public interface SafetyService {
	
	List<Safety> getSafetyList(Safety obj) throws Exception;

	List<Safety> getSafetyStatusList() throws Exception;

	List<Safety> getSafetyImpactList() throws Exception;

	List<Safety> getSafetyCategoryList() throws Exception;

	List<Safety> getSafetyRootCauseList() throws Exception;

	boolean addSafety(Safety obj) throws Exception;

	Safety getSafety(Safety obj) throws Exception;

	boolean updateSafety(Safety obj) throws Exception;

	boolean deleteSafety(Safety obj) throws Exception;

	List<Safety> getDepartmentList() throws Exception;

	List<Safety> getContractsListFilter(Safety obj) throws Exception;

	List<Safety> getDepartmentsListFilter(Safety obj) throws Exception;

	List<Safety> getCategoryListFilter(Safety obj) throws Exception;

	List<Safety> getStatusListFilter(Safety obj) throws Exception;

	List<Safety> getWorksListFilter(Safety obj) throws Exception;

	List<Safety> getProjectsListForSafetyForm(Safety obj) throws Exception;

	List<Safety> getWorkListForSafetyForm(Safety obj) throws Exception;

	List<Safety> getContractsListForSafetyForm(Safety obj) throws Exception;

	List<Safety> getHODListForSafetyForm(Safety obj) throws Exception;

	List<Safety> getHODListFilterInSafety(Safety obj) throws Exception;

	int getTotalRecords(Safety obj, String searchParameter) throws Exception;

	List<Safety> getSafetyList(Safety obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<Safety> getUnitsList(Safety obj) throws Exception;

	List<Safety> getResponsiblePersonsListForSafetyForm(Safety obj) throws Exception;
}
