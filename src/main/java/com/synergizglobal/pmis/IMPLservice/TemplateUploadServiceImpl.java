package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.TemplateUploadDao;
import com.synergizglobal.pmis.Iservice.TemplateUploadService;
import com.synergizglobal.pmis.reference.model.TrainingType;
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

	@Override
	public List<TrainingType> getTemplateHistoryList(TrainingType obj) throws Exception {
		return dao.getTemplateHistoryList(obj);
	}

	
}
