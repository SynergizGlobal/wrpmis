package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ContractDao;
import com.synergizglobal.pmis.Idao.DataGatheringsDao;
import com.synergizglobal.pmis.Iservice.DataGatheringsService;
import com.synergizglobal.pmis.model.DataGathering;

@Service
public class DataGatheringsServiceImpl implements DataGatheringsService {

	@Autowired
	DataGatheringsDao dao;

	@Override
	public List<DataGathering> getDataGatheringsList(DataGathering obj) throws Exception {
		return dao.getDataGatheringsList(obj);
	}

	@Override
	public List<DataGathering> getDataGatherigsStatusList(DataGathering obj) throws Exception {
		return dao.getDataGatherigsStatusList(obj);
	}

	@Override
	public List<DataGathering> getDataGatherigsProjectPriorityList(DataGathering obj) throws Exception {
		return dao.getDataGatherigsProjectPriorityList(obj);
	}

	@Override
	public List<DataGathering> getStatusList() throws Exception {
		return dao.getStatusList();
	}

	@Override
	public List<DataGathering> getPriorityList() throws Exception {
		return dao.getPriorityList();
	}

	@Override
	public DataGathering getDataGathering(DataGathering obj) throws Exception {
		return dao.getDataGathering(obj);
	}

	@Override
	public List<DataGathering> getWorksList() throws Exception {
		return dao.getWorksList();
	}

	@Override
	public boolean addDataGathering(DataGathering obj) throws Exception {
		return dao.addDataGathering(obj);
	}

	@Override
	public boolean updateDataGathering(DataGathering obj) throws Exception {
		return dao.updateDataGathering(obj);
	}

	@Override
	public boolean deleteDataGathering(DataGathering obj) throws Exception {
		return dao.deleteDataGathering(obj);
	}
	
}
