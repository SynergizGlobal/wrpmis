package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.GeneralStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.GeneralStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class GeneralStatusServiceImpl implements GeneralStatusService{

	@Autowired
	GeneralStatusDao dao;

	@Override
	public List<Safety> getGeneralStatusList() throws Exception {
		return dao.getGeneralStatusList();
	}

	@Override
	public boolean addGeneralStatus(Safety obj) throws Exception {
		return dao.addGeneralStatus(obj);
	}

	@Override
	public TrainingType getGeneralStatusDetails(TrainingType obj) throws Exception {
		return dao.getGeneralStatusDetails(obj);
	}

	@Override
	public boolean updateGeneralStatus(TrainingType obj) throws Exception {
		return dao.updateGeneralStatus(obj);
	}

	@Override
	public boolean deleteGeneralStatus(TrainingType obj) throws Exception {
		return dao.deleteGeneralStatus(obj);
	}
}
