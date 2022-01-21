package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.model.WorkFeatures;

public interface WorkDetailsTitleDao {
	public WorkFeatures getTitleDetails(WorkFeatures obj) throws Exception;

	public boolean addTitle(WorkFeatures obj) throws Exception;

	public boolean updateTitle(WorkFeatures obj) throws Exception;

	public boolean deleteTitle(WorkFeatures obj) throws Exception;
}
