package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.model.WorkFeatures;

public interface WorkDetailsTitleService {
	public WorkFeatures getTitleDetails(WorkFeatures obj) throws Exception;

	public boolean addTitle(WorkFeatures obj) throws Exception;

	public boolean updateTitle(WorkFeatures obj) throws Exception;

	public boolean deleteTitle(WorkFeatures obj) throws Exception;
}
