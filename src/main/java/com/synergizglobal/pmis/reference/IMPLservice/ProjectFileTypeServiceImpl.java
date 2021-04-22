package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.ProjectFileTypeDao;
import com.synergizglobal.pmis.reference.Iservice.ProjectFileTypeService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class ProjectFileTypeServiceImpl implements ProjectFileTypeService{
	
	@Autowired
	 ProjectFileTypeDao dao;

	@Override
	public List<TrainingType> getProjectFileType(TrainingType obj) throws Exception {
		return dao.getProjectFileType(obj);
	}

	@Override
	public boolean addProjectFileType(TrainingType obj) throws Exception {
		return dao.addProjectFileType(obj);
	}

	@Override
	public boolean updateProjectFileType(TrainingType obj) throws Exception {
		return dao.updateProjectFileType(obj);
	}

	@Override
	public boolean deleteProjectFileType(TrainingType obj) throws Exception {
		return dao.deleteProjectFileType(obj);
	}
}
