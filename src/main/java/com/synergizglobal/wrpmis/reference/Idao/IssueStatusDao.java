package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface IssueStatusDao {

	public List<Safety> getIssueStatusList() throws Exception;

	public boolean addIssueStatus(Safety obj) throws Exception;

	public TrainingType getIssueStatusDetails(TrainingType obj) throws Exception;

	public boolean updateIssueStatus(TrainingType obj) throws Exception;

	public boolean deleteIssueStatus(TrainingType obj) throws Exception;
}
