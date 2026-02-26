package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.AlertTypeDao;
import com.synergizglobal.wrpmis.reference.Idao.ApprovalAuthorityDao;
import com.synergizglobal.wrpmis.reference.Iservice.ApprovalAuthorityService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class ApprovalAuthorityServiceImpl implements ApprovalAuthorityService{
	
	@Autowired
	ApprovalAuthorityDao dao;

	@Override
	public TrainingType getApprovalAuthorityDetails(TrainingType obj) throws Exception {
		return dao.getApprovalAuthorityDetails(obj);
	}

	@Override
	public boolean addApprovalAuthority(TrainingType obj) throws Exception {
		return dao.addApprovalAuthority(obj);
	}

	@Override
	public boolean updateApprovalAuthority(TrainingType obj) throws Exception {
		return dao.updateApprovalAuthority(obj);
	}

	@Override
	public boolean deleteApprovalAuthority(TrainingType obj) throws Exception {
		return dao.deleteApprovalAuthority(obj);
	}
}
