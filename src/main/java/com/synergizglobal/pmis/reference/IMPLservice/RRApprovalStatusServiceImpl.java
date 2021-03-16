package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RRApprovalStatusDao;
import com.synergizglobal.pmis.reference.Iservice.RRApprovalStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class RRApprovalStatusServiceImpl implements RRApprovalStatusService{

	@Autowired
	RRApprovalStatusDao dao;

	@Override
	public List<Safety> getRRApprovalStatusList() throws Exception {
		return dao.getRRApprovalStatusList();
	}

	@Override
	public boolean addRRApprovalStatus(Safety obj) throws Exception {
		return dao.addRRApprovalStatus(obj);
	}

	@Override
	public TrainingType getRApprovalStatusDetails(TrainingType obj) throws Exception {
		return dao.getRApprovalStatusDetails(obj);
	}

	@Override
	public boolean updateRRApprovalStatus(TrainingType obj) throws Exception {
		return dao.updateRRApprovalStatus(obj);
	}

	@Override
	public boolean deleteRRApprovalStatus(TrainingType obj) throws Exception {
		return dao.deleteRRApprovalStatus(obj);
	}
}
