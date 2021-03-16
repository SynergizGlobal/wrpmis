package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UtilityRequirementStageDao;
import com.synergizglobal.pmis.reference.Iservice.UtilityRequirementStageService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class UtilityRequirementStageServiceImpl implements UtilityRequirementStageService{

	@Autowired
	UtilityRequirementStageDao dao;

	@Override
	public List<TrainingType> getUtilityRequirementStagesList() throws Exception {
		return dao.getUtilityRequirementStagesList();
	}

	@Override
	public boolean addUtilityRequirementStage(TrainingType obj) throws Exception {
		return dao.addUtilityRequirementStage(obj);
	}
}
