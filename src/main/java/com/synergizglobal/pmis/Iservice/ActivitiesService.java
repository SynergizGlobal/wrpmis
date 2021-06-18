package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.StripChart;

public interface ActivitiesService {

	public List<StripChart> getActivitiesProjectsList(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesWorksList(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesContractsList(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesActivities(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesComponents(StripChart obj) throws Exception;
	public List<StripChart> getActivityComponentsList(StripChart obj)  throws Exception;
	public List<StripChart> getActivitiesComponentIds(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesLines(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesSections(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesStructures(StripChart obj) throws Exception;
	public List<StripChart> getActivitiesTypes() throws Exception;
	public List<StripChart> getActivitiesStructureTypes() throws Exception;
	public StripChart getActivitiesDetails(StripChart obj) throws Exception;
	public boolean updateActivities(StripChart obj) throws Exception;
	public StripChart getActivitiesData(StripChart obj) throws Exception;
	public List<Issue> getIssuesCategoryList() throws Exception;
	public List<Issue> getIssuesPriorityList() throws Exception;
	public List<Issue> getIssuesStatusList() throws Exception;
}
