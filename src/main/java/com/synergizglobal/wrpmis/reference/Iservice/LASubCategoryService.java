package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface LASubCategoryService {

	public List<TrainingType> getLASubCategoryList() throws Exception;

	public boolean addLASubCategory(TrainingType obj) throws Exception;

	public TrainingType getLandAcquisitionSubCategoryDetails(TrainingType obj) throws Exception;

	public boolean updateLandAcquisitionSubCategory(TrainingType obj) throws Exception;

	public boolean deleteLandAcquisitionSubCategory(TrainingType obj) throws Exception;

	public List<TrainingType> getLASubCategory(TrainingType obj) throws Exception;

}
