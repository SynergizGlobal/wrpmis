package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.LandAcquisition;

public interface LandAcquisitionService {

	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionWorksList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionVillagesList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionTypesOfLandsList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionSubCategoryList(LandAcquisition obj) throws Exception;

	

}
