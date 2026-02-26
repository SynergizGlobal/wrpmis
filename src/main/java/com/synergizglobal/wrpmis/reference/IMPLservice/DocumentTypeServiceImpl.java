package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DocumentTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.DocumentTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{

	@Autowired
	DocumentTypeDao dao;

	@Override
	public List<TrainingType> getDocumentTypesList() throws Exception {
		return dao.getDocumentTypesList();
	}

	@Override
	public boolean addDocumentType(TrainingType obj) throws Exception {
		return dao.addDocumentType(obj);
	}

	@Override
	public TrainingType getDocumentTypeDetails(TrainingType obj) throws Exception {
		return dao.getDocumentTypeDetails(obj);
	}

	@Override
	public boolean updateDocumentType(TrainingType obj) throws Exception {
		return dao.updateDocumentType(obj);
	}

	@Override
	public boolean deleteDocumentType(TrainingType obj) throws Exception {
		return dao.deleteDocumentType(obj);
	}
}
