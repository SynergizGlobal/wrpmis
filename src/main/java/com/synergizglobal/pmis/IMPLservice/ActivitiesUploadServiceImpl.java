package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesUploadDao;
import com.synergizglobal.pmis.Iservice.ActivitiesUploadService;
import com.synergizglobal.pmis.model.Activity;

@Service
public class ActivitiesUploadServiceImpl implements ActivitiesUploadService{
	@Autowired
	ActivitiesUploadDao dao;


	@Override
	public int[] uploadActivities(List<Activity> activityList) throws Exception {
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

	@Override
	public boolean addFileInActivitiesDataTable(String data_remarks, Activity activity) throws Exception {
		return dao.addFileInActivitiesDataTable(data_remarks,activity);
	}

	@Override
	public List<Activity> getWorksListFilter(Activity obj) throws Exception {
		return dao.getWorksListFilter(obj);
	}

	@Override
	public List<Activity> getContractsListFilter(Activity obj) throws Exception {
		return dao.getContractsListFilter(obj);
	}

	@Override
	public List<Activity> getStructureTypesListFilter(Activity obj) throws Exception {
		return dao.getStructureTypesListFilter(obj);
	}

	@Override
	public List<Activity> getActivitiesUploadFilesList(Activity obj) throws Exception {
		return dao.getActivitiesUploadFilesList(obj);
	}

}
