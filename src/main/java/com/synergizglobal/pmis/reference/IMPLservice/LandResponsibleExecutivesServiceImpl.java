package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.LandResponsibleExecutivesDao;
import com.synergizglobal.pmis.reference.Iservice.LandResponsibleExecutivesService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class LandResponsibleExecutivesServiceImpl implements LandResponsibleExecutivesService{


	@Autowired
	LandResponsibleExecutivesDao dao;

	@Override
	public List<TrainingType> getExecutivesDetails(TrainingType obj) throws Exception {
		return dao.getExecutivesDetails(obj);
	}

	@Override
	public boolean addLandAcquisitionExecutives(TrainingType obj) throws Exception {
		return dao.addLandAcquisitionExecutives(obj);
	}

	@Override
	public boolean updateLandAcquisitionExecutives(TrainingType obj) throws Exception {
		return dao.updateLandAcquisitionExecutives(obj);
	}

	@Override
	public List<TrainingType> getWorkDetails(TrainingType obj) throws Exception {
		return dao.getWorkDetails(obj);
	}
}
