package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface UserTypeDao {

	public TrainingType getUserTypeDetails(TrainingType obj) throws Exception;

	public boolean addUserType(TrainingType obj) throws Exception;

	public boolean updateUserType(TrainingType obj) throws Exception;

	public boolean deleteUserType(TrainingType obj) throws Exception;

}
