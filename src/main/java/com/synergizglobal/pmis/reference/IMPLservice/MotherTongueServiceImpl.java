package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.DesignFileTypeDao;
import com.synergizglobal.pmis.reference.Idao.MotherTongueDao;
import com.synergizglobal.pmis.reference.Iservice.MotherTongueService;
import com.synergizglobal.pmis.reference.model.TrainingType;
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
