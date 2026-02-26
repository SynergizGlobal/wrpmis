package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DataGatheringsDao;
import com.synergizglobal.wrpmis.reference.Iservice.DataGatheringsService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
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
