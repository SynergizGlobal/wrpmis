package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.DeliverablesDao;
import com.synergizglobal.wrpmis.Iservice.DeliverablesService;
import com.synergizglobal.wrpmis.model.Deliverables;

@Service
public class DeliverablesServiceImpl implements DeliverablesService{
	
	@Autowired
	DeliverablesDao dao;

	@Override
	public List<Deliverables> getDeliverablesList(Deliverables obj) throws Exception {
		return dao.getDeliverablesList(obj);
	}

	@Override
	public List<Deliverables> getDeliverablesStatusList(Deliverables obj) throws Exception {
		return dao.getDeliverablesStatusList(obj);
	}

	@Override
	public List<Deliverables> getDeliverablesProjectsList(Deliverables obj) throws Exception {
		return dao.getDeliverablesProjectsList(obj);
	}

	@Override
	public List<Deliverables> getDeliverablesWorksList(Deliverables obj) throws Exception {
		return dao.getDeliverablesWorksList(obj);
	}

	@Override
	public List<Deliverables> getDeliverablesContarctsList(Deliverables obj) throws Exception {
		return dao.getDeliverablesContarctsList(obj);
	}

	@Override
	public List<Deliverables> getStatusList() throws Exception {
		return dao.getStatusList();
	}

	@Override
	public List<Deliverables> getDeliverableTypeList() throws Exception {
		return dao.getDeliverableTypeList();
	}

	@Override
	public List<Deliverables> getPriorityList() throws Exception {
		return dao.getPriorityList();
	}

	@Override
	public List<Deliverables> getProjectsListForDeliverablesForm(Deliverables obj) throws Exception {
		return dao.getProjectsListForDeliverablesForm(obj);
	}

	@Override
	public Deliverables getDeliverables(Deliverables obj) throws Exception {
		return dao.getDeliverables(obj);
	}

	@Override
	public boolean addDeliverables(Deliverables obj) throws Exception {
		return dao.addDeliverables(obj);
	}

	@Override
	public boolean updateDeliverables(Deliverables obj) throws Exception {
		return dao.updateDeliverables(obj);
	}

	@Override
	public boolean deleteDeliverables(Deliverables obj) throws Exception {
		return dao.deleteDeliverables(obj);
	}

	@Override
	public List<Deliverables> getWorkListForDeliverablesForm(Deliverables obj) throws Exception {
		return dao.getWorkListForDeliverablesForm(obj);
	}

	@Override
	public List<Deliverables> getContractsListForDeliverablesForm(Deliverables obj) throws Exception {
		return dao.getContractsListForDeliverablesForm(obj);
	}

	@Override
	public int getTotalRecords(Deliverables obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Deliverables> getDeliverablesList(Deliverables obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return dao.getDeliverablesList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<Deliverables> getContractMilestonesListForDeliverablesForm(Deliverables obj) throws Exception {
		return dao.getContractMilestonesListForDeliverablesForm(obj);
	}

	@Override
	public List<Deliverables> getDeliverablesConractMilestonesList(Deliverables dObj) throws Exception {
		return dao.getDeliverablesConractMilestonesList(dObj);
	}

	@Override
	public String[] uploadDeliverablesData(List<Deliverables> deliverablesList, Deliverables deliverableObj)
			throws Exception {
		return dao.uploadDeliverablesData(deliverablesList, deliverableObj);
	}

}
