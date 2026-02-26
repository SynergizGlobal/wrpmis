package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.PMISCategoryDao;
import com.synergizglobal.wrpmis.reference.Idao.RrResponsibleExecutivesDao;
import com.synergizglobal.wrpmis.reference.Iservice.RrResponsibleExecutivesService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RrResponsibleExecutivesServiceImpl implements RrResponsibleExecutivesService{
	@Autowired
	RrResponsibleExecutivesDao dao;

	@Override
	public List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception {
		return dao.getExecutivesDetails(obj);
	}

	@Override
	public boolean addRRExecutives(TrainingType obj) throws Exception {
		return dao.addRRExecutives(obj);
	}

	@Override
	public boolean updateRRExecutives(TrainingType obj) throws Exception {
		return dao.updateRRExecutives(obj);
	}

	@Override
	public List<TrainingType> executivesList(TrainingType obj) throws Exception {
		return dao.executivesList(obj);
	}

	@Override
	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception {
		return dao.getWorkDetails(obj);
	}

	@Override
	public List<TrainingType> getUsersDetails(TrainingType obj) throws Exception {
		return dao.getUsersDetails(obj);
	}

}
