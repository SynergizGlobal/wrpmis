package com.synergizglobal.pmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UserRoleService {

	public List<TrainingType> getUserRolesList() throws Exception;

	public boolean addUserRole(TrainingType obj) throws Exception;

	public TrainingType getUserRoleDetails(TrainingType obj) throws Exception;

	public boolean updateUserRole(TrainingType obj) throws Exception;

	public boolean deleteUserRole(TrainingType obj) throws Exception;

}
