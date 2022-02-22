package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UtilityResponsibleExecutivesDao;
import com.synergizglobal.pmis.reference.Iservice.UtilityResponsibleExecutivesService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class UtilityResponsibleExecutivesServiceImpl implements UtilityResponsibleExecutivesService{
	@Autowired
	 UtilityResponsibleExecutivesDao dao;

	@Override
	public List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception {
		return dao.getExecutivesDetails(obj);
	}

	@Override
	public boolean addUtilityShiftingExecutives(TrainingType obj) throws Exception {
		return dao.addUtilityShiftingExecutives(obj);
	}

	@Override
	public boolean updateUtilityShiftingExecutives(TrainingType obj) throws Exception {
		return dao.updateUtilityShiftingExecutives(obj);
	}
}
