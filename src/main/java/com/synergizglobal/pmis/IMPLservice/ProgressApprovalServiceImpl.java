package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ProgressApprovalDao;
import com.synergizglobal.pmis.Iservice.ProgressApprovalService;
import com.synergizglobal.pmis.model.Activity;

@Service
public class ProgressApprovalServiceImpl implements ProgressApprovalService{
	
	@Autowired
	ProgressApprovalDao dao;
	
	@Override
	public List<Activity> getApprovableActivities(Activity obj) throws Exception {
		return dao.getApprovableActivities(obj);
	}

}
