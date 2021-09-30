package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.LandAcquisitionTemp;

public interface LandAcquisitionTempDao {
	public List<LandAcquisitionTemp> getLandAcquisitionList() throws Exception;
	public List<LandAcquisitionTemp> getWorksList() throws Exception;
	public List<LandAcquisitionTemp> getLandAcquisitionCategoryList() throws Exception;
	public List<LandAcquisitionTemp> getLandAcquisitionSubCategoryList() throws Exception;
	public List<LandAcquisitionTemp> getLandAcquisitionSatausList() throws Exception;
	public LandAcquisitionTemp getLandAcquisition(String landAcquisitionId) throws Exception;
	public boolean updateLandAcquisition(LandAcquisitionTemp obj) throws Exception;
	public boolean updateLandAcquisition1(LandAcquisitionTemp obj) throws Exception;
	public boolean updateLandAcquisitionStatus(LandAcquisitionTemp obj) throws Exception;
}
