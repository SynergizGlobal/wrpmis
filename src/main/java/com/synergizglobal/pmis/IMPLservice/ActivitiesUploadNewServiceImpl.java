package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesUploadNewDao;
import com.synergizglobal.pmis.Iservice.ActivitiesUploadNewService;
import com.synergizglobal.pmis.model.Activity;

@Service
public class ActivitiesUploadNewServiceImpl implements ActivitiesUploadNewService{
	@Autowired
	ActivitiesUploadNewDao dao;


	@Override
	public int[] uploadActivities(List<Activity> activityList, Activity obj) throws Exception {
		return dao.uploadActivities(activityList,obj);
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

	@Override
	public List<Activity> getExistingStructures(String contract_id) throws Exception {
		return dao.getExistingStructures(contract_id);
	}
}
