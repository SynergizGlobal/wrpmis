package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Safety;

public interface SafetyReportService {
	List<Safety> getWorksListInSafetyReport(Safety obj) throws Exception;

	List<Safety> getContractsListInSafetyReport(Safety obj) throws Exception;

	List<Safety> getHODListInSafetyReport(Safety obj) throws Exception;

	List<Safety> getSafetyReportData(Safety obj) throws Exception;
}
