package com.synergizglobal.pmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.DataGatheringsDao;
import com.synergizglobal.pmis.reference.Iservice.DataGatheringsService;
import com.synergizglobal.pmis.reference.model.TrainingType;
@Service
public class DataGatheringsServiceReferenceImpl implements DataGatheringsService{
	
	@Autowired
	DataGatheringsDao dao;

	@Override
	public List<TrainingType> getDataGatheringsList() throws Exception {
		return dao.getDataGatheringsList();
	}

	@Override
	public boolean addDataGatherings(TrainingType obj) throws Exception {
		return dao.addDataGatherings(obj);
	}

	@Override
	public TrainingType getDataGatheringStatusDetails(TrainingType obj) throws Exception {
		return dao.getDataGatheringStatusDetails(obj);
	}

	@Override
	public boolean updateDataGatheringStatus(TrainingType obj) throws Exception {
		return dao.updateDataGatheringStatus(obj);
	}

	@Override
	public boolean deleteDataGatheringStatus(TrainingType obj) throws Exception {
		return dao.deleteDataGatheringStatus(obj);
	}
}
