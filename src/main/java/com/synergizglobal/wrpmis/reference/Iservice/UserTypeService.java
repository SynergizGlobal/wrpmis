package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface UserTypeService {

	public TrainingType getUserTypeDetails(TrainingType obj) throws Exception;

	public boolean addUserType(TrainingType obj) throws Exception;

	public boolean updateUserType(TrainingType obj) throws Exception;

	public boolean deleteUserType(TrainingType obj) throws Exception;

}
