package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.LandAcquisition;

public interface LandAcquisitionDao {

	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionWorksList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionVillagesList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionTypesOfLandsList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionSubCategoryList(LandAcquisition obj) throws Exception;

}
