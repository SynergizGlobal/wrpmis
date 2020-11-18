package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.DataGathering;

public interface DeliverablesService {

	public List<DataGathering> getDeliverablesList(DataGathering obj) throws Exception;

	public List<DataGathering> getDeliverablesStatusList(DataGathering obj) throws Exception;

	public List<DataGathering> getDeliverablesProjectsList(DataGathering obj) throws Exception;

	public List<DataGathering> getDeliverablesWorksList(DataGathering obj) throws Exception;

	public List<DataGathering> getDeliverablesContarctsList(DataGathering obj) throws Exception;

	public List<DataGathering> getStatusList() throws Exception;

	public List<DataGathering> getDeliverableTypeList() throws Exception;

	public List<DataGathering> getPriorityList() throws Exception;

	public List<DataGathering> getProjectsList() throws Exception;

	public DataGathering getDeliverables(DataGathering obj) throws Exception;

	public boolean addDeliverables(DataGathering obj) throws Exception;

	public boolean updateDeliverables(DataGathering obj) throws Exception;

	public boolean deleteDeliverables(DataGathering obj) throws Exception;

}
