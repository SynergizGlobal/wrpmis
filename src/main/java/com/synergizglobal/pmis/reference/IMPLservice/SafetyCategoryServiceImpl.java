package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SafetyCategoryDao;
import com.synergizglobal.pmis.reference.Iservice.SafetyCategoryService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class SafetyCategoryServiceImpl implements SafetyCategoryService{

	@Autowired
	SafetyCategoryDao dao;

	@Override
	public List<Safety> getSafetyCategoryList() throws Exception {
		return dao.getSafetyCategoryList();
	}

	@Override
	public boolean addSafetyCategory(Safety obj) throws Exception {
		return dao.addSafetyCategory(obj);
	}

	@Override
	public TrainingType getSafetyCategoryDetails(TrainingType obj) throws Exception {
		return dao.getSafetyCategoryDetails(obj);
	}

	@Override
	public boolean updateSafetyCategory(TrainingType obj) throws Exception {
		return dao.updateSafetyCategory(obj);
	}

	@Override
	public boolean deleteSafetyCategory(TrainingType obj) throws Exception {
		return dao.deleteSafetyCategory(obj);
	}
}
