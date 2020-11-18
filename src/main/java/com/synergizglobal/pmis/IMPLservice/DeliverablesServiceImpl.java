package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.DataGatheringsDao;
import com.synergizglobal.pmis.Idao.DeliverablesDao;
import com.synergizglobal.pmis.Iservice.DeliverablesService;
import com.synergizglobal.pmis.model.DataGathering;

@Service
public class DeliverablesServiceImpl implements DeliverablesService{
	
	@Autowired
	DeliverablesDao dao;

	@Override
	public List<DataGathering> getDeliverablesList(DataGathering obj) throws Exception {
		return dao.getDeliverablesList(obj);
	}

	@Override
	public List<DataGathering> getDeliverablesStatusList(DataGathering obj) throws Exception {
		return dao.getDeliverablesStatusList(obj);
	}

	@Override
	public List<DataGathering> getDeliverablesProjectsList(DataGathering obj) throws Exception {
		return dao.getDeliverablesProjectsList(obj);
	}

	@Override
	public List<DataGathering> getDeliverablesWorksList(DataGathering obj) throws Exception {
		return dao.getDeliverablesWorksList(obj);
	}

	@Override
	public List<DataGathering> getDeliverablesContarctsList(DataGathering obj) throws Exception {
		return dao.getDeliverablesContarctsList(obj);
	}

	@Override
	public List<DataGathering> getStatusList() throws Exception {
		return dao.getStatusList();
	}

	@Override
	public List<DataGathering> getDeliverableTypeList() throws Exception {
		return dao.getDeliverableTypeList();
	}

	@Override
	public List<DataGathering> getPriorityList() throws Exception {
		return dao.getPriorityList();
	}

	@Override
	public List<DataGathering> getProjectsList() throws Exception {
		return dao.getProjectsList();
	}

	@Override
	public DataGathering getDeliverables(DataGathering obj) throws Exception {
		return dao.getDeliverables(obj);
	}

	@Override
	public boolean addDeliverables(DataGathering obj) throws Exception {
		return dao.addDeliverables(obj);
	}

	@Override
	public boolean updateDeliverables(DataGathering obj) throws Exception {
		return dao.updateDeliverables(obj);
	}

	@Override
	public boolean deleteDeliverables(DataGathering obj) throws Exception {
		return dao.deleteDeliverables(obj);
	}

}
