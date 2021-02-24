package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesUploadDao;
import com.synergizglobal.pmis.Iservice.ActivitiesUploadService;
import com.synergizglobal.pmis.model.Activity;
import com.synergizglobal.pmis.model.StripChart;

@Service
public class ActivitiesUploadServiceImpl implements ActivitiesUploadService{
	@Autowired
	ActivitiesUploadDao dao;

	@Override
	public List<StripChart> getWorksListFilter(StripChart obj) throws Exception {
		return dao.getWorksListFilter(obj);
	}

	@Override
	public List<StripChart> getContractsListFilter(StripChart obj) throws Exception {
		return dao.getContractsListFilter(obj);
	}

	@Override
	public List<StripChart> getStructureListFilter(StripChart obj) throws Exception {
		return dao.getStructureListFilter(obj);
	}

	@Override
	public List<StripChart> getComponentIdsListFilter(StripChart obj) throws Exception {
		return dao.getComponentIdsListFilter(obj);
	}

	@Override
	public List<StripChart> getComponentsListFilter(StripChart obj) throws Exception {
		return dao.getComponentsListFilter(obj);
	}

	@Override
	public int getTotalRecords(StripChart obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<StripChart> getActivitiesList(StripChart obj, Integer startIndex, Integer offset, String searchParameter)
			throws Exception {
		return dao.getActivitiesList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public int uploadActivities(List<Activity> activityList) throws Exception {
		return dao.uploadActivities(activityList);
	}

	@Override
	public List<Activity> getWorksInActivitiesUpload(Activity obj) throws Exception {
		return dao.getWorksInActivitiesUpload(obj);
	}

	@Override
	public List<Activity> getContractsInActivitiesUpload(Activity obj) throws Exception {
		return dao.getContractsInActivitiesUpload(obj);
	}

	@Override
	public List<Activity> getStructureTypesInActivitiesUpload(Activity obj) throws Exception {
		return dao.getStructureTypesInActivitiesUpload(obj);
	}

}
