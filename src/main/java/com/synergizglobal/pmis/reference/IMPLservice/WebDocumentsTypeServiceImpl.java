package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.WebDocumentsTypeDao;
import com.synergizglobal.pmis.reference.Iservice.WebDocumentsTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class WebDocumentsTypeServiceImpl implements WebDocumentsTypeService{

	@Autowired
	WebDocumentsTypeDao dao;

	@Override
	public TrainingType getWebDocumentsTypeDetails(TrainingType obj) throws Exception {
		return dao.getWebDocumentsTypeDetails(obj);
	}

	@Override
	public boolean addWebDocumentsType(TrainingType obj) throws Exception {
		return dao.addWebDocumentsType(obj);
	}

	@Override
	public boolean updateWebDocumentsType(TrainingType obj) throws Exception {
		return dao.updateWebDocumentsType(obj);
	}

	@Override
	public boolean deleteWebDocumentsType(TrainingType obj) throws Exception {
		return dao.deleteWebDocumentsType(obj);
	}
}
