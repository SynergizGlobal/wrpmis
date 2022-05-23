package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.UtilityShifting;

public interface UtilityReportDao {

	List<UtilityShifting> getExecutionAgencyListInutilityReport(UtilityShifting obj) throws Exception ;

	List<UtilityShifting> getProjectsFilterListInutilityReport(UtilityShifting obj) throws Exception ;

	List<UtilityShifting> getWorksFilterListInutilityReport(UtilityShifting obj) throws Exception ;

	UtilityShifting getUtilityShiftingData(UtilityShifting obj) throws Exception ;

}
