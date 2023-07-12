package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.StationNamesDao;
import com.synergizglobal.pmis.reference.Iservice.StationNamesService;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Service
public class StationNamesServiceImpl implements StationNamesService{

	@Autowired
	StationNamesDao dao;

	@Override
	public List<TrainingType> getStationNamesList() throws Exception {
		return dao.getStationNamesList();
	}

	@Override
	public TrainingType getStationNamesDetails(TrainingType obj) throws Exception {
		return dao.getStationNamesDetails(obj);
	}

	@Override
	public boolean addStationNames(TrainingType obj) throws Exception {
		return dao.addStationNames(obj);
	}

	@Override
	public boolean updateStationNames(TrainingType obj) throws Exception {
		return dao.updateStationNames(obj);
	}

	@Override
	public boolean deleteStationNames(TrainingType obj) throws Exception {
		return dao.deleteStationNames(obj);
	}

}
