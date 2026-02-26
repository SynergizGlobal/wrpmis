package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.TypeOfFamilyDao;
import com.synergizglobal.wrpmis.reference.Iservice.TypeOfFamilyService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class TypeOfFamilyServiceImpl implements TypeOfFamilyService{
	@Autowired
	TypeOfFamilyDao dao;

	@Override
	public TrainingType getTypeOfFamilyDetails(TrainingType obj) throws Exception {
		return dao.getTypeOfFamilyDetails(obj);
	}

	@Override
	public boolean addTypeOfFamily(TrainingType obj) throws Exception {
		return dao.addTypeOfFamily(obj);
	}

	@Override
	public boolean updateTypeOfFamily(TrainingType obj) throws Exception {
		return dao.updateTypeOfFamily(obj);
	}

	@Override
	public boolean deleteTypeOfFamily(TrainingType obj) throws Exception {
		return dao.deleteTypeOfFamily(obj);
	}

}
