package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UtilityTypeDao;
import com.synergizglobal.pmis.reference.Iservice.UtilityTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class UtilityTypeServiceImpl implements UtilityTypeService{

	@Autowired
	UtilityTypeDao dao;

	@Override
	public List<TrainingType> getUtilityTypesList() throws Exception {
		return dao.getUtilityTypesList();
	}

	@Override
	public boolean addUtilityType(TrainingType obj) throws Exception {
		return dao.addUtilityType(obj);
	}
}
