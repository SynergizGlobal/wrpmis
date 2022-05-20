package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.RandRMain;

public interface LandReportDao {

	List<LandAcquisition> getProjectsFilterListInLandReport(LandAcquisition obj) throws Exception;

	List<LandAcquisition> getTypeOfLandListInLandReport(LandAcquisition obj) throws Exception;

	List<LandAcquisition> getSubCategoryOfLandFilterListInLandReport(LandAcquisition obj) throws Exception;

	List<LandAcquisition> getWorksFilterListInLandReport(LandAcquisition obj) throws Exception;

	LandAcquisition getLandAcquisitionData(LandAcquisition obj) throws Exception;

}
