package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.YesOrNoStatusDao;
import com.synergizglobal.pmis.reference.Iservice.YesOrNoStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class YesOrNoStatusServiceImpl implements YesOrNoStatusService{

	@Autowired
	YesOrNoStatusDao dao;

	@Override
	public TrainingType getYesOrNoStatusDetails(TrainingType obj) throws Exception {
		return dao.getYesOrNoStatusDetails(obj);
	}

	@Override
	public boolean addYesOrNoStatus(TrainingType obj) throws Exception {
		return dao.addYesOrNoStatus(obj);
	}

	@Override
	public boolean updateYesOrNoStatus(TrainingType obj) throws Exception {
		return dao.updateYesOrNoStatus(obj);
	}

	@Override
	public boolean deleteYesOrNoStatus(TrainingType obj) throws Exception {
		return dao.deleteYesOrNoStatus(obj);
	}
}
