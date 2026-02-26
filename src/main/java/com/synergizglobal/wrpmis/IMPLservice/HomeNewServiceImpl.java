package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.HomeNewDao;
import com.synergizglobal.wrpmis.Iservice.HomeNewService;
import com.synergizglobal.wrpmis.model.Project;

@Service
public class HomeNewServiceImpl implements HomeNewService {

    private final HomeNewDao dao;

    @Autowired
    public HomeNewServiceImpl(HomeNewDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Project> getProjects() throws Exception {
        return dao.getProjects();
    }

    @Override
    public Project getProjectOverview(Project obj) throws Exception {
        return dao.getProjectOverview(obj);
    }

    @Override
    public List<Project> getprojectTypes() throws Exception {
        return dao.getprojectTypes();
    }

    @Override
    public List<Project> getProjectStatsByType() throws Exception {
        return dao.getProjectStatsByType();
    }

	@Override
	public List<Project> getAllLengthsByProjectTypes() throws Exception {
		return dao.getAllLengthsByProjectTypes();
	}

	@Override
	public List<Project> getprojectTypeswithProject() throws Exception {
		return dao.getprojectTypeswithProject();
	}

	@Override
	public List<Project> getProjectsforHeader() throws Exception {
		return dao.getProjectsforHeader();
	}
}

