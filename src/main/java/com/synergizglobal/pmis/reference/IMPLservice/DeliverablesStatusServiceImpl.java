package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.DeliverablesStatusDao;
import com.synergizglobal.pmis.reference.Iservice.DeliverablesStatusService;
import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class DeliverablesStatusServiceImpl implements DeliverablesStatusService{

	@Autowired
	DeliverablesStatusDao dao;

	@Override
	public List<Safety> getDeliverablesStatusList() throws Exception {
		return dao.getDeliverablesStatusList();
	}

	@Override
	public boolean addDeliverablesStatus(Safety obj) throws Exception {
		return dao.addDeliverablesStatus(obj);
	}

	@Override
	public TrainingType getDeliverablesStatusDetails(TrainingType obj) throws Exception {
		return dao.getDeliverablesStatusDetails(obj);
	}

	@Override
	public boolean updateDeliverablesStatus(TrainingType obj) throws Exception {
		return dao.updateDeliverablesStatus(obj);
	}

	@Override
	public boolean deleteDeliverablesStatus(TrainingType obj) throws Exception {
		return dao.deleteDeliverablesStatus(obj);
	}
}
