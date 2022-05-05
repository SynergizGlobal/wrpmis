package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface LASubCategoryDao {

	public List<TrainingType> getLASubCategoryList() throws Exception;

	public boolean addLASubCategory(TrainingType obj) throws Exception;

	public TrainingType getLandAcquisitionSubCategoryDetails(TrainingType obj) throws Exception;

	public boolean updateLandAcquisitionSubCategory(TrainingType obj) throws Exception;

	public boolean deleteLandAcquisitionSubCategory(TrainingType obj) throws Exception;

	public List<TrainingType> getLASubCategory(TrainingType obj) throws Exception;

}
