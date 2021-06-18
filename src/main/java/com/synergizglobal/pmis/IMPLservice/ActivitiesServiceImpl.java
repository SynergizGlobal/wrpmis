package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesDao;
import com.synergizglobal.pmis.Iservice.ActivitiesService;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.StripChart;
@Service
public class ActivitiesServiceImpl implements ActivitiesService{
	
	@Autowired
	ActivitiesDao dao;

	@Override
	public List<StripChart> getActivitiesProjectsList(StripChart obj) throws Exception {
		return dao.getActivitiesProjectsList(obj);
	}

	@Override
	public List<StripChart> getActivitiesWorksList(StripChart obj) throws Exception {
		return dao.getActivitiesWorksList(obj);
	}

	@Override
	public List<StripChart> getActivitiesContractsList(StripChart obj) throws Exception {
		return dao.getActivitiesContractsList(obj);
	}

	@Override
	public List<StripChart> getActivitiesActivities(StripChart obj) throws Exception {
		return dao.getActivitiesActivities(obj);
	}

	@Override
	public List<StripChart> getActivitiesComponents(StripChart obj) throws Exception {
		return dao.getActivitiesComponents(obj);
	}

	@Override
	public List<StripChart> getActivityComponentsList(StripChart obj) throws Exception {
		return dao.getActivityComponentsList(obj);
	}
	
	@Override
	public List<StripChart> getActivitiesComponentIds(StripChart obj) throws Exception {
		return dao.getActivitiesComponentIds(obj);
	}

	@Override
	public List<StripChart> getActivitiesLines(StripChart obj) throws Exception {
		return dao.getActivitiesLines(obj);
	}

	@Override
	public List<StripChart> getActivitiesSections(StripChart obj) throws Exception {
		return dao.getActivitiesSections(obj);
	}

	@Override
	public List<StripChart> getActivitiesStructures(StripChart obj) throws Exception {
		return dao.getActivitiesStructures(obj);
	}

	@Override
	public List<StripChart> getActivitiesTypes() throws Exception {
		return dao.getActivitiesTypes();
	}

	@Override
	public List<StripChart> getActivitiesStructureTypes() throws Exception {
		return dao.getActivitiesStructureTypes();
	}

	@Override
	public StripChart getActivitiesDetails(StripChart obj) throws Exception {
		return dao.getActivitiesDetails(obj);
	}

	@Override
	public boolean updateActivities(StripChart obj) throws Exception {
		return dao.updateActivities(obj);
	}

	@Override
	public StripChart getActivitiesData(StripChart obj) throws Exception {
		return dao.getActivitiesData(obj);
	}

	@Override
	public List<Issue> getIssuesCategoryList() throws Exception {
		return dao.getIssuesCategoryList();
	}

	@Override
	public List<Issue> getIssuesPriorityList() throws Exception {
		return dao.getIssuesPriorityList();
	}

	@Override
	public List<Issue> getIssuesStatusList() throws Exception {
		return dao.getIssuesStatusList();
	}
	
}

