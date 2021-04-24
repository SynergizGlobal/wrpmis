package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface IssueOtherOrganizationService {

	List<TrainingType> getIssueOtherOrganizationDetails(TrainingType obj) throws Exception;

	boolean addIssueOtherOrganization(TrainingType obj) throws Exception;

	boolean updateIssueOtherOrganization(TrainingType obj) throws Exception;

	boolean deleteIssueOtherOrganization(TrainingType obj) throws Exception;

}
