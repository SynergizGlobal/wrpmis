package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.LandAcquisition;

public interface LandAcquisitionDao {

	public List<LandAcquisition> getLandAcquisitionList(LandAcquisition obj, int startIndex, int offset, String searchParameter) throws Exception;

	public List<LandAcquisition> getLandAcquisitionWorksList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionVillagesList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionTypesOfLandsList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionSubCategoryList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getStatusList() throws Exception;

	public LandAcquisition getLandAcquisitionForm(LandAcquisition obj) throws Exception;

	public boolean addLandAcquisition(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getWorkListForLAForm(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getProjectsList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandsListForLAForm(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getSubCategorysListForLAForm(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getSubCategoryList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandsList(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getIssueCatogoriesList() throws Exception;

	public boolean updateLandAcquisition(LandAcquisition obj) throws Exception;

	public List<LandAcquisition> getLandAcquisitionProjectsList(LandAcquisition obj) throws Exception;

	public int getTotalRecords(LandAcquisition obj, String searchParameter) throws Exception;

	public List<LandAcquisition> getUnitsList() throws Exception;

	public List<LandAcquisition> getLaFileType() throws Exception;


}
