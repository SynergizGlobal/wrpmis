package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.BinaryValuesDao;
import com.synergizglobal.pmis.reference.Iservice.BinaryValuesService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class BinaryValuesServiceImpl implements BinaryValuesService{

	@Autowired
	BinaryValuesDao dao;

	@Override
	public List<TrainingType> getBinaryValuesList() throws Exception {
		return dao.getBinaryValuesList();
	}

	@Override
	public boolean addBinaryValues(TrainingType obj) throws Exception {
		return dao.addBinaryValues(obj);
	}

	@Override
	public TrainingType getBinaryValueDetails(TrainingType obj) throws Exception {
		return dao.getBinaryValueDetails(obj);
	}

	@Override
	public boolean updateBinaryValues(TrainingType obj) throws Exception {
		return dao.updateBinaryValues(obj);
	}

	@Override
	public boolean deleteBinaryValues(TrainingType obj) throws Exception {
		return dao.deleteBinaryValues(obj);
	}
}