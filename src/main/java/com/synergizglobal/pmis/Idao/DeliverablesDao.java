package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Deliverables;
import com.synergizglobal.pmis.model.Safety;

public interface DeliverablesDao {

	public List<Deliverables> getDeliverablesList(Deliverables obj) throws Exception;

	public List<Deliverables> getDeliverablesStatusList(Deliverables obj) throws Exception;

	public List<Deliverables> getDeliverablesProjectsList(Deliverables obj) throws Exception;

	public List<Deliverables> getDeliverablesWorksList(Deliverables obj) throws Exception;

	public List<Deliverables> getDeliverablesContarctsList(Deliverables obj) throws Exception;

	public List<Deliverables> getStatusList() throws Exception;

	public List<Deliverables> getDeliverableTypeList() throws Exception;

	public List<Deliverables> getPriorityList() throws Exception;

	public List<Deliverables> getProjectsListForDeliverablesForm(Deliverables obj) throws Exception;

	public Deliverables getDeliverables(Deliverables obj) throws Exception;

	public boolean addDeliverables(Deliverables obj) throws Exception;

	public boolean updateDeliverables(Deliverables obj) throws Exception;

	public boolean deleteDeliverables(Deliverables obj) throws Exception;

	public List<Deliverables> getWorkListForDeliverablesForm(Deliverables obj) throws Exception;

	public List<Deliverables> getContractsListForDeliverablesForm(Deliverables obj) throws Exception;

	public int getTotalRecords(Deliverables obj, String searchParameter) throws Exception;

	public List<Deliverables> getDeliverablesList(Deliverables obj, int startIndex, int offset, String searchParameter) throws Exception;

	public List<Deliverables> getContractMilestonesListForDeliverablesForm(Deliverables obj) throws Exception;

	public List<Deliverables> getDeliverablesConractMilestonesList(Deliverables dObj) throws Exception;

}
