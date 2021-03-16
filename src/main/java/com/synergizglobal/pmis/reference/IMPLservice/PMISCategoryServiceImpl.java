package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.PMISCategoryDao;
import com.synergizglobal.pmis.reference.Iservice.PMISCategoryService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class PMISCategoryServiceImpl implements PMISCategoryService {

	@Autowired
	PMISCategoryDao dao;

	@Override
	public List<Safety> getCategoryList() throws Exception {
		return dao.getCategoryList();
	}

	@Override
	public boolean addCategory(Safety obj) throws Exception {
		return dao.addCategory(obj);
	}

	@Override
	public TrainingType getPmisCategoryDetails(TrainingType obj) throws Exception {
		return dao.getPmisCategoryDetails(obj);
	}

	@Override
	public boolean updatePmisCategory(TrainingType obj) throws Exception {
		return dao.updatePmisCategory(obj);
	}

	@Override
	public boolean deletePmisCategory(TrainingType obj) throws Exception {
		return dao.deletePmisCategory(obj);
	}
}
