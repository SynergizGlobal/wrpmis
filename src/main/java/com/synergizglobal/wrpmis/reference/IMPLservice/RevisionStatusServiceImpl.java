package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.RevisionStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.RevisionStatusService;
import com.synergizglobal.wrpmis.reference.model.Risk;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class RevisionStatusServiceImpl implements RevisionStatusService{

	@Autowired
	RevisionStatusDao dao;

	@Override
	public List<Risk> getRevisionStatusList() throws Exception {
		return dao.getRevisionStatusList();
	}

	@Override
	public boolean addRevisionStatus(Risk obj) throws Exception {
		return dao.addRevisionStatus(obj);
	}

	@Override
	public TrainingType getRevisionStatusDetails(TrainingType obj) throws Exception {
		return dao.getRevisionStatusDetails(obj);
	}

	@Override
	public boolean updateRevisionStatus(TrainingType obj) throws Exception {
		return dao.updateRevisionStatus(obj);
	}

	@Override
	public boolean deleteRevisionStatus(TrainingType obj) throws Exception {
		return dao.deleteRevisionStatus(obj);
	}
}
