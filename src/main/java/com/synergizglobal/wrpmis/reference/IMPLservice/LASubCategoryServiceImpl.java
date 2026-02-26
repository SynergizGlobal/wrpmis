package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.LASubCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.LASubCategoryService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class LASubCategoryServiceImpl implements LASubCategoryService{
	
	@Autowired
	LASubCategoryDao dao;

	@Override
	public List<TrainingType> getLASubCategoryList() throws Exception {
		return dao.getLASubCategoryList();
	}

	@Override
	public boolean addLASubCategory(TrainingType obj) throws Exception {
		return dao.addLASubCategory(obj);
	}

	@Override
	public TrainingType getLandAcquisitionSubCategoryDetails(TrainingType obj) throws Exception {
		return dao.getLandAcquisitionSubCategoryDetails(obj);
	}

	@Override
	public boolean updateLandAcquisitionSubCategory(TrainingType obj) throws Exception {
		return dao.updateLandAcquisitionSubCategory(obj);
	}

	@Override
	public boolean deleteLandAcquisitionSubCategory(TrainingType obj) throws Exception {
		return dao.deleteLandAcquisitionSubCategory(obj);
	}

	@Override
	public List<TrainingType> getLASubCategory(TrainingType obj) throws Exception {
		return dao.getLASubCategory(obj);
	}

}
