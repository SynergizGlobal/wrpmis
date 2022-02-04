package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.BinaryValuesDao;
import com.synergizglobal.pmis.reference.Idao.CasteDao;
import com.synergizglobal.pmis.reference.Iservice.CasteService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class CasteServiceImpl implements CasteService{
	@Autowired
	CasteDao dao;

	@Override
	public TrainingType getCasteDetails(TrainingType obj) throws Exception {
		return dao.getCasteDetails(obj);
	}

	@Override
	public boolean addCaste(TrainingType obj) throws Exception {
		return dao.addCaste(obj);
	}

	@Override
	public boolean updateCaste(TrainingType obj) throws Exception {
		return dao.updateCaste(obj);
	}

	@Override
	public boolean deleteCaste(TrainingType obj) throws Exception {
		return dao.deleteCaste(obj);
	}
}
