package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.TemplateUploadDao;
import com.synergizglobal.wrpmis.Iservice.TemplateUploadService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class TemplateUploadServiceImpl implements TemplateUploadService{

	@Autowired
	TemplateUploadDao dao;

	@Override
	public List<TrainingType> getTemplatesList() throws Exception {
		return dao.getTemplatesList();
	}

	@Override
	public boolean uploadTemplate(TrainingType obj) throws Exception {
		return dao.uploadTemplate(obj);
	}

	@Override
	public boolean deleteTemplate(TrainingType obj) throws Exception {
		return dao.deleteTemplate(obj);
	}

}
