package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ProgressApprovalDao;
import com.synergizglobal.wrpmis.Iservice.ProgressApprovalService;
import com.synergizglobal.wrpmis.model.Activity;

@Service
public class ProgressApprovalServiceImpl implements ProgressApprovalService{
	
	@Autowired
	ProgressApprovalDao dao;
	
	@Override
	public List<Activity> getApprovableActivities(Activity obj) throws Exception {
		return dao.getApprovableActivities(obj);
	}

	@Override
	public List<Activity> getWorksInApprovableActivities(Activity obj) throws Exception {
		return dao.getWorksInApprovableActivities(obj);
	}

	@Override
	public List<Activity> getContractsInApprovableActivities(Activity obj) throws Exception {
		return dao.getContractsInApprovableActivities(obj);
	}

	@Override
	public List<Activity> getStructuresInApprovableActivities(Activity obj) throws Exception {
		return dao.getStructuresInApprovableActivities(obj);
	}

	@Override
	public List<Activity> getDepartmentsInApprovableActivities(Activity obj) throws Exception {
		return dao.getDepartmentsInApprovableActivities(obj);
	}

	@Override
	public List<Activity> getUpdatedByListInApprovableActivities(Activity obj) throws Exception {
		return dao.getUpdatedByListInApprovableActivities(obj);
	}

	@Override
	public Activity approveActivityProgress(Activity obj) throws Exception {
		return dao.approveActivityProgress(obj);
	}

	@Override
	public Activity rejectActivityProgress(Activity obj) throws Exception {
		return dao.rejectActivityProgress(obj);
	}

	@Override
	public Activity approveMultipleActivityProgress(Activity obj) throws Exception {
		return dao.approveMultipleActivityProgress(obj);
	}

	@Override
	public Activity rejectMultipleActivityProgress(Activity obj) throws Exception {
		return dao.rejectMultipleActivityProgress(obj);
	}

}
