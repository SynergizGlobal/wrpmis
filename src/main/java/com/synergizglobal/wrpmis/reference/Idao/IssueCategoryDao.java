package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface IssueCategoryDao {
	
	public List<Safety> getIssueCategoryList() throws Exception;

	public boolean addIssueCategory(Safety obj) throws Exception;

	public TrainingType getIssueCategoryDetails(TrainingType obj) throws Exception;

	public boolean updateIssueCategory(TrainingType obj) throws Exception;

	public boolean deleteIssueCategory(TrainingType obj) throws Exception;
}
