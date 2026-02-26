package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.LandAcquisition;
import com.synergizglobal.wrpmis.model.RandRMain;

public interface LandReportDao {

	List<LandAcquisition> getProjectsFilterListInLandReport(LandAcquisition obj) throws Exception;

	List<LandAcquisition> getTypeOfLandListInLandReport(LandAcquisition obj) throws Exception;

	List<LandAcquisition> getSubCategoryOfLandFilterListInLandReport(LandAcquisition obj) throws Exception;

	List<LandAcquisition> getWorksFilterListInLandReport(LandAcquisition obj) throws Exception;

	LandAcquisition getLandAcquisitionData(LandAcquisition obj) throws Exception;

}
