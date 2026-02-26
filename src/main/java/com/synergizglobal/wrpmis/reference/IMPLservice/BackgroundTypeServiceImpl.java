package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.BackgroundTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.BackgroundTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class BackgroundTypeServiceImpl implements BackgroundTypeService{

	@Autowired
	BackgroundTypeDao dao;

	@Override
	public List<TrainingType> getBackgroundTypesList() throws Exception {
		return dao.getBackgroundTypesList();
	}

	@Override
	public boolean addBackgroundType(TrainingType obj) throws Exception {
		return dao.addBackgroundType(obj);
	}

	@Override
	public boolean updateBackgroundType(TrainingType obj) throws Exception {
		return dao.updateBackgroundType(obj);
	}

	@Override
	public TrainingType getBankGuaranteeDetails(TrainingType obj) throws Exception {
		return dao.getBankGuaranteeDetails(obj);
	}

	@Override
	public boolean deleteBankGuaranteeType(TrainingType obj) throws Exception {
		return dao.deleteBankGuaranteeType(obj);
	}
}
