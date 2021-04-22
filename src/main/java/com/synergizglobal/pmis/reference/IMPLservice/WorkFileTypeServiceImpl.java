package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SafetyCategoryDao;
import com.synergizglobal.pmis.reference.Idao.WorkFileTypeDao;
import com.synergizglobal.pmis.reference.Iservice.WorkFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service

public class WorkFileTypeServiceImpl implements WorkFileTypeService{
	@Autowired
	 WorkFileTypeDao dao;

	@Override
	public List<TrainingType> getWorkFileType(TrainingType obj) throws Exception {
		return dao.getWorkFileType(obj);
	}

	@Override
	public boolean addWorkFileType(TrainingType obj) throws Exception {
		return dao.addWorkFileType(obj);
	}

	@Override
	public boolean updateWorkFileType(TrainingType obj) throws Exception {
		return dao.updateWorkFileType(obj);
	}

	@Override
	public boolean deleteWorkFileType(TrainingType obj) throws Exception {
		return dao.deleteWorkFileType(obj);
	}

}
