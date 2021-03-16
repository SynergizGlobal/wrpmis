package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRDocumentTypeDao;
import com.synergizglobal.pmis.reference.Iservice.RRDocumentTypeService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RRDocumentTypeServiceImpl implements RRDocumentTypeService{

	@Autowired
	RRDocumentTypeDao dao;

	@Override
	public List<Safety> getRRDocumentTypeList() throws Exception {
		return dao.getRRDocumentTypeList();
	}

	@Override
	public boolean addRRDocumentType(Safety obj) throws Exception {
		return dao.addRRDocumentType(obj);
	}

	@Override
	public TrainingType getRRDocumentTypeDetails(TrainingType obj) throws Exception {
		return dao.getRRDocumentTypeDetails(obj);
	}

	@Override
	public boolean updateRRDocumentType(TrainingType obj) throws Exception {
		return dao.updateRRDocumentType(obj);
	}

	@Override
	public boolean deleteRRDocumentType(TrainingType obj) throws Exception {
		return dao.deleteRRDocumentType(obj);
	}
}
