package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface IssueFileTypeService {

	List<TrainingType> getIssueFileType(TrainingType obj) throws Exception;

	boolean addIssueFileType(TrainingType obj) throws Exception;

	boolean updateIssueFileType(TrainingType obj) throws Exception;

	boolean deleteIssueFileType(TrainingType obj) throws Exception;

}
