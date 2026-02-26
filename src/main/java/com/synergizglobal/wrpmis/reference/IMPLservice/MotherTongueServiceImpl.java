package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DesignFileTypeDao;
import com.synergizglobal.wrpmis.reference.Idao.MotherTongueDao;
import com.synergizglobal.wrpmis.reference.Iservice.MotherTongueService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class MotherTongueServiceImpl implements MotherTongueService{
	@Autowired
	MotherTongueDao dao;

	@Override
	public TrainingType getMotherTongueDetails(TrainingType obj) throws Exception {
		return dao.getMotherTongueDetails(obj);
	}

	@Override
	public boolean addMotherTongue(TrainingType obj) throws Exception {
		return dao.addMotherTongue(obj);
	}

	@Override
	public boolean updateMotherTongue(TrainingType obj) throws Exception {
		return dao.updateMotherTongue(obj);
	}

	@Override
	public boolean deleteMotherTongue(TrainingType obj) throws Exception {
		return dao.deleteMotherTongue(obj);
	}
}
