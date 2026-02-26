package com.synergizglobal.wrpmis.Idao;

import java.util.List;
import java.util.Map;

import com.synergizglobal.wrpmis.model.UtilityShifting;

public interface UtilityReportDao {

	List<UtilityShifting> getExecutionAgencyListInutilityReport(UtilityShifting obj) throws Exception ;

	List<UtilityShifting> getProjectsFilterListInutilityReport(UtilityShifting obj) throws Exception ;

	List<UtilityShifting> getWorksFilterListInutilityReport(UtilityShifting obj) throws Exception ;

	UtilityShifting getUtilityShiftingData(UtilityShifting obj) throws Exception ;

	Map<String, List<UtilityShifting>> getUtilityShiftingReportData(UtilityShifting obj) throws Exception;

}
