package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.WebDocumentCategoryDao;
import com.synergizglobal.wrpmis.reference.Iservice.WebDocumentCategoryService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class WebDocumentCategoryServiceImpl implements WebDocumentCategoryService{
	@Autowired
	WebDocumentCategoryDao dao;

	@Override
	public TrainingType getWebDocumentsCategoryDetails(TrainingType obj) throws Exception {
		return dao.getWebDocumentsCategoryDetails(obj);
	}

	@Override 
	public boolean addWebDocumentsCategory(Risk obj) throws Exception {
		return dao.addWebDocumentsCategory(obj);
	}

	@Override
	public boolean updateWebDocumentsCategory(TrainingType obj) throws Exception {
		return dao.updateWebDocumentsCategory(obj);
	}

	@Override
	public boolean deleteWebDocumentsCategory(TrainingType obj) throws Exception {
		return dao.deleteWebDocumentsCategory(obj);
	}

	@Override
	public List<TrainingType> getDocumentType(TrainingType obj) throws Exception {
		return dao.getDocumentType(obj);
	}

	@Override
	public List<TrainingType> getWebcategory(TrainingType obj) throws Exception {
		return dao.getWebcategory(obj);
	}
}
