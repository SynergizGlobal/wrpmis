package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.SubLocationDao;
import com.synergizglobal.pmis.reference.Iservice.SubLocationService;
import com.synergizglobal.pmis.reference.model.TrainingType;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class SubLocationServiceImpl implements SubLocationService{
	@Autowired
	SubLocationDao dao;

	@Override
	public List<TrainingType> getSubLocationsList() throws Exception {
		return dao.getSubLocationsList();
	}

	@Override
	public List<TrainingType> getLocationList() throws Exception {
		return dao.getLocationList();
	}

	@Override
	public TrainingType getSubLocationDetails(TrainingType obj) throws Exception {
		return dao.getSubLocationDetails(obj);
	}

	@Override
	public boolean addSubLocation(TrainingType obj) throws Exception {
		return dao.addSubLocation(obj);
	}

	@Override
	public boolean updateSubLocation(TrainingType obj) throws Exception {
		return dao.updateSubLocation(obj);
	}

	@Override
	public boolean deleteSubLocation(TrainingType obj) throws Exception {
		return dao.deleteSubLocation(obj);
	}

	@Override
	public List<TrainingType> getSubLocations(TrainingType obj) throws Exception {
		return dao.getSubLocations(obj);
	}
}
