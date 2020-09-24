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
}
