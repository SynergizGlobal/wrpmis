package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DivisionDao;
import com.synergizglobal.wrpmis.reference.Iservice.DivisionService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class DivisionServiceImpl implements DivisionService{

	@Autowired
	DivisionDao dao;

	@Override
	public List<TrainingType> getDivisionsList() throws Exception {
		return dao.getDivisionsList();
	}

	@Override
	public boolean addDivision(TrainingType obj) throws Exception {
		return dao.addDivision(obj);
	}

	@Override
	public TrainingType getDivisionDetails(TrainingType obj) throws Exception {
		return dao.getDivisionDetails(obj);
	}

	@Override
	public boolean updateDivision(TrainingType obj) throws Exception {
		return dao.updateDivision(obj);
	}

	@Override
	public boolean deleteDivision(TrainingType obj) throws Exception {
		return dao.deleteDivision(obj);
	}
}
