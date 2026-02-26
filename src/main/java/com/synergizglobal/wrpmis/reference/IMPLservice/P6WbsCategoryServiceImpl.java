package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.P6WbsCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.P6WbsCategoryService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class P6WbsCategoryServiceImpl implements P6WbsCategoryService{

	@Autowired
	P6WbsCategoryDao dao;

	@Override
	public List<Safety> getP6WbsCategoryList() throws Exception {
		return dao.getP6WbsCategoryList();
	}

	@Override
	public boolean addP6WbsCategory(Safety obj) throws Exception {
		return dao.addP6WbsCategory(obj);
	}

	@Override
	public TrainingType getP6WbsCategoryDetails(TrainingType obj) throws Exception {
		return dao.getP6WbsCategoryDetails(obj);
	}

	@Override
	public boolean updateP6WbsCategory(TrainingType obj) throws Exception {
		return dao.updateP6WbsCategory(obj);
	}

	@Override
	public boolean deleteP6WbsCategory(TrainingType obj) throws Exception {
		return dao.deleteP6WbsCategory(obj);
	}
}

