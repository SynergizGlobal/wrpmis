package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface IssueContractCategoryService {

	List<TrainingType> getContractTypeDetails(TrainingType obj) throws Exception;

	List<TrainingType> gtIssueCategoryDetails(TrainingType obj) throws Exception;

	List<TrainingType> getIssueContractCategory(TrainingType obj) throws Exception;

	boolean addIssueContractCategory(TrainingType obj) throws Exception;

	boolean updateIssueContractCategory(TrainingType obj) throws Exception;

	boolean deleteIssueContractCategory(TrainingType obj) throws Exception;

	List<TrainingType> getContarctCategory(TrainingType obj) throws Exception;

}
