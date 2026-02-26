package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ProjectTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.ProjectTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService{
	
	@Autowired
	 ProjectTypeDao dao;

	@Override
	public List<TrainingType> getProjectType(TrainingType obj) throws Exception {
		return dao.getProjectType(obj);
	}

	@Override
	public boolean addProjectType(TrainingType obj) throws Exception {
		return dao.addProjectType(obj);
	}

	@Override
	public boolean updateProjectType(TrainingType obj) throws Exception {
		return dao.updateProjectType(obj);
	}

	@Override
	public boolean deleteProjectType(TrainingType obj) throws Exception {
		return dao.deleteProjectType(obj);
	}
}
