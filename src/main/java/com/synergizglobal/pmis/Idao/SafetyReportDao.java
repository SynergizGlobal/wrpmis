package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Safety;

public interface SafetyReportDao {
	List<Safety> getWorksListInSafetyReport(Safety obj) throws Exception;

	List<Safety> getContractsListInSafetyReport(Safety obj) throws Exception;

	List<Safety> getSafetyReportData(Safety obj) throws Exception;
}
