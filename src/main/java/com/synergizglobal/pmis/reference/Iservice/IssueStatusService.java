package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Safety;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface IssueStatusService {

	public List<Safety> getIssueStatusList() throws Exception;

	public boolean addIssueStatus(Safety obj) throws Exception;

	public TrainingType getIssueStatusDetails(TrainingType obj) throws Exception;

	public boolean updateIssueStatus(TrainingType obj) throws Exception;

	public boolean deleteIssueStatus(TrainingType obj) throws Exception;
}
