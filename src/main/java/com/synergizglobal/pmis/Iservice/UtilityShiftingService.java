package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.UtilityShifting;

public interface UtilityShiftingService {

	List<UtilityShifting> getWorksListFilter(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getLocationListFilter(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getUtilityCategoryListFilter(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getUtilityTypeListFilter(UtilityShifting obj) throws Exception;
	
	List<UtilityShifting> getUtilityStatusListFilter(UtilityShifting obj) throws Exception;
	
	List<UtilityShifting> getProjectsListForUtilityShifting(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getWorkListForUtilityShifting(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getContractsListForUtilityShifting(UtilityShifting obj) throws Exception;	
	
	List<UtilityShifting> getUtilityTypeList(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getUtilityCategoryList(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getUtilityExecutionAgencyList(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getImpactedContractList(UtilityShifting obj) throws Exception;
	
	List<UtilityShifting> getRequirementStageList(UtilityShifting obj) throws Exception;
	
	List<UtilityShifting> getUnitListForUtilityShifting(UtilityShifting obj) throws Exception;
	List<UtilityShifting> getUtilityTypeListForUtilityShifting(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getStatusListForUtilityShifting(UtilityShifting obj) throws Exception;
	boolean addUtilityShifting(UtilityShifting obj) throws Exception;
	
	UtilityShifting getUtilityShifting(UtilityShifting obj) throws Exception;

	boolean updateUtilityShifting(UtilityShifting obj) throws Exception;
	
	int getTotalRecords(UtilityShifting obj, String searchParameter) throws Exception;

	List<UtilityShifting> getUtilityShiftingList(UtilityShifting obj, int startIndex, int offset, String searchParameter) throws Exception;	
	
}
