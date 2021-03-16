package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UnitTypeDao;
import com.synergizglobal.pmis.reference.Iservice.UnitTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class UnitTypeServiceImpl implements UnitTypeService{

	@Autowired
	UnitTypeDao dao;

	@Override
	public List<TrainingType> getUnitTypesList() throws Exception {
		return dao.getUnitTypesList();
	}

	@Override
	public boolean addUnitType(TrainingType obj) throws Exception {
		return dao.addUnitType(obj);
	}

	

}
