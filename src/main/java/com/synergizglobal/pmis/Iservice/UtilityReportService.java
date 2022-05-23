package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.UtilityShifting;

public interface UtilityReportService {

	List<UtilityShifting> getProjectsFilterListInutilityReport(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getWorksFilterListInutilityReport(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getExecutionAgencyListInutilityReport(UtilityShifting obj) throws Exception;

	UtilityShifting getUtilityShiftingData(UtilityShifting obj) throws Exception;


}
