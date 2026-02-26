package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.DataGathering;

public interface DataGatheringsService {

	public List<DataGathering> getDataGatheringsList(DataGathering obj) throws Exception;

	public List<DataGathering> getDataGatherigsStatusList(DataGathering obj) throws Exception;

	public List<DataGathering> getDataGatherigsProjectsList(DataGathering obj) throws Exception;

	public List<DataGathering> getStatusList() throws Exception;

	public List<DataGathering> getPriorityList() throws Exception;

	public DataGathering getDataGathering(DataGathering obj) throws Exception;

	public List<DataGathering> getWorksList() throws Exception;

	public boolean addDataGathering(DataGathering obj) throws Exception;

	public boolean updateDataGathering(DataGathering obj) throws Exception;

	public boolean deleteDataGathering(DataGathering obj) throws Exception;

	public List<DataGathering> getDataGatherigsWorksList(DataGathering obj) throws Exception;

	public List<DataGathering> getDataGatherigsContractsList(DataGathering obj) throws Exception;

	public List<DataGathering> getProjectsListForDataGatheringForm(DataGathering obj) throws Exception;

	public List<DataGathering> getWorkListForDataGatheringForm(DataGathering obj) throws Exception;

	public List<DataGathering> getContractsListForDataGatheringForm(DataGathering obj) throws Exception;

	public int getTotalRecords(DataGathering obj, String searchParameter) throws Exception;

	public List<DataGathering> getDataGatheringList(DataGathering obj, int startIndex, int offset,
			String searchParameter) throws Exception;

}
