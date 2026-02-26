package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.ContractDao;
import com.synergizglobal.wrpmis.Idao.DataGatheringsDao;
import com.synergizglobal.wrpmis.Iservice.DataGatheringsService;
import com.synergizglobal.wrpmis.model.DataGathering;

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
	public List<DataGathering> getDataGatherigsProjectsList(DataGathering obj) throws Exception {
		return dao.getDataGatherigsProjectsList(obj);
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

	@Override
	public List<DataGathering> getDataGatherigsWorksList(DataGathering obj) throws Exception {
		return dao.getDataGatherigsWorksList(obj);
	}

	@Override
	public List<DataGathering> getDataGatherigsContractsList(DataGathering obj) throws Exception {
		return dao.getDataGatherigsContractsList(obj);
	}

	@Override
	public List<DataGathering> getProjectsListForDataGatheringForm(DataGathering obj) throws Exception {
		return dao.getProjectsListForDataGatheringForm(obj);
	}

	@Override
	public List<DataGathering> getWorkListForDataGatheringForm(DataGathering obj) throws Exception {
		return dao.getWorkListForDataGatheringForm(obj);
	}

	@Override
	public List<DataGathering> getContractsListForDataGatheringForm(DataGathering obj) throws Exception {
		return dao.getContractsListForDataGatheringForm(obj);
	}

	@Override
	public int getTotalRecords(DataGathering obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<DataGathering> getDataGatheringList(DataGathering obj, int startIndex, int offset,
			String searchParameter) throws Exception {
		return dao.getDataGatheringList(obj,startIndex,offset,searchParameter);
	}
	
}
