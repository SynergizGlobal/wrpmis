package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.WorkFeatures;

public interface WorkDetailsDao {
	public List<WorkFeatures> getWorkDetailsList(WorkFeatures obj) throws Exception;
	
	public List<WorkFeatures> getWorkDetails(WorkFeatures obj) throws Exception;

	public boolean addWorkDetails(WorkFeatures obj) throws Exception;

	public boolean updateWorkDetails(WorkFeatures obj) throws Exception;
	
	public List<WorkFeatures> getWorksListFilter(WorkFeatures obj) throws Exception;

	public List<WorkFeatures> getTitlesList(WorkFeatures obj) throws Exception;

	public List<WorkFeatures> getStatusList(WorkFeatures obj) throws Exception;

	public List<WorkFeatures> getWorksList(WorkFeatures obj) throws Exception;
	
	public List<WorkFeatures> getWorksListFilterForSalientFeatures(WorkFeatures obj) throws Exception;

	public List<WorkFeatures> getWorkSalientFeatures(WorkFeatures obj) throws Exception;

	public List<WorkFeatures> getCategoryList(WorkFeatures obj) throws Exception;

	public boolean addWorkSalientFeatures(WorkFeatures obj) throws Exception;

	public boolean updateWorkSalientFeatures(WorkFeatures obj) throws Exception;
	
}
