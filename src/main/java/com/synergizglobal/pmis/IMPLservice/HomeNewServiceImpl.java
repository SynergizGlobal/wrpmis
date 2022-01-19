package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.HomeNewDao;
import com.synergizglobal.pmis.Iservice.HomeNewService;
import com.synergizglobal.pmis.model.Project;

@Service
public class HomeNewServiceImpl implements HomeNewService{
	
	@Autowired
	HomeNewDao dao;
	
	@Override
	public List<Project> getProjects() throws Exception {
		return dao.getProjects();
	}
	@Override
	public Project getProjectOverview(Project obj) throws Exception {
		return dao.getProjectOverview(obj);
	}
}
