package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Safety;

public interface SafetyDetailsReportDao {

	List<Safety> getContractsListInSafetyDetailsReport(Safety obj) throws Exception;

	List<Safety> getHODListInSafetyDetailsReport(Safety obj) throws Exception;

	List<Safety> getWorksListInSafetyDetailsReport(Safety obj) throws Exception;

	List<Safety> getStatusListInSafetyDetailsReport(Safety obj) throws Exception;

	List<Safety> getLocationsListInSafetyDetailsReport(Safety obj) throws Exception;

	List<Safety> getTitlesListInSafetyDetailsReport(Safety obj) throws Exception;

	List<Safety> getCategoriesListInSafetyDetailsReport(Safety obj) throws Exception;

	Safety getSafetyDetails(Safety obj) throws Exception;

}
