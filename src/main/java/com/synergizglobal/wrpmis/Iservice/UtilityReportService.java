package com.synergizglobal.wrpmis.Iservice;

import java.util.List;
import java.util.Map;

import com.synergizglobal.wrpmis.model.UtilityShifting;

public interface UtilityReportService {

	List<UtilityShifting> getProjectsFilterListInutilityReport(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getWorksFilterListInutilityReport(UtilityShifting obj) throws Exception;

	List<UtilityShifting> getExecutionAgencyListInutilityReport(UtilityShifting obj) throws Exception;

	UtilityShifting getUtilityShiftingData(UtilityShifting obj) throws Exception;

	Map<String, List<UtilityShifting>> getUtilityShiftingReportData(UtilityShifting obj) throws Exception;


}
