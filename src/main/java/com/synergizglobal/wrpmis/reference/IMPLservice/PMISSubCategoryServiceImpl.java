package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.PMISSubCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.PMISSubCategoryService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class PMISSubCategoryServiceImpl implements PMISSubCategoryService {

	@Autowired
	PMISSubCategoryDao dao;

	@Override
	public List<Safety> getSubCategoryList() throws Exception {
		return dao.getSubCategoryList();
	}

	@Override
	public boolean addSubCategory(Safety obj) throws Exception {
		return dao.addSubCategory(obj);
	}

	@Override
	public TrainingType getPmisSubCategoryDetails(TrainingType obj) throws Exception {
		return dao.getPmisSubCategoryDetails(obj);
	}

	@Override
	public boolean updatePmisSubCategory(TrainingType obj) throws Exception {
		return dao.updatePmisSubCategory(obj);
	}

	@Override
	public boolean deletePmisSubCategory(TrainingType obj) throws Exception {
		return dao.deletePmisSubCategory(obj);
	}
}
