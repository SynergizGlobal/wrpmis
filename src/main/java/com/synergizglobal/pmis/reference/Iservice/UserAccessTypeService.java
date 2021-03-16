package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UserAccessTypeService {

	public List<TrainingType> getUserRolesList() throws Exception;

	public boolean addUserAccessType(TrainingType obj) throws Exception;

	public TrainingType getUserAccessTypeDetails(TrainingType obj) throws Exception;

	public boolean updateUserAccessType(TrainingType obj) throws Exception;

	public boolean deleteUserAccessType(TrainingType obj) throws Exception;
}
