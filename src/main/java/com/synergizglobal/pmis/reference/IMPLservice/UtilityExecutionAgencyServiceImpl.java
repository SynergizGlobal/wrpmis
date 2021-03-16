package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UtilityExecutionAgencyDao;
import com.synergizglobal.pmis.reference.Iservice.UtilityExecutionAgencyService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class UtilityExecutionAgencyServiceImpl implements UtilityExecutionAgencyService{

	@Autowired
	UtilityExecutionAgencyDao dao;

	@Override
	public List<TrainingType> getUtilityExecutionAgencysList() throws Exception {
		return dao.getUtilityExecutionAgencysList();
	}

	@Override
	public boolean addUtilityExecutionAgency(TrainingType obj) throws Exception {
		return dao.addUtilityExecutionAgency(obj);
	}
}
