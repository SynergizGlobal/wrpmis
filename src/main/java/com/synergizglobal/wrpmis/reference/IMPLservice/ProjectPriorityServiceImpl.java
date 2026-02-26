package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ProjectPriorityDao;
import com.synergizglobal.wrpmis.reference.Iservice.ProjectPriorityService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class ProjectPriorityServiceImpl implements ProjectPriorityService{

	@Autowired
	ProjectPriorityDao dao;

	@Override
	public List<Safety> getProjectPriorityList() throws Exception {
		return dao.getProjectPriorityList();
	}

	@Override
	public boolean addProjectPriority(Safety obj) throws Exception {
		return dao.addProjectPriority(obj);
	}

	@Override
	public TrainingType getProjectPriorityDetails(TrainingType obj) throws Exception {
		return dao.getProjectPriorityDetails(obj);
	}

	@Override
	public boolean updateProjectPriority(TrainingType obj) throws Exception {
		return dao.updateProjectPriority(obj);
	}

	@Override
	public boolean deleteProjectPriority(TrainingType obj) throws Exception {
		return dao.deleteProjectPriority(obj);
	}
}
