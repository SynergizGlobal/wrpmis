package com.synergizglobal.wrpmis.reference.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DepartmentService {
	
	public List<TrainingType> getDepartmentsList() throws Exception;

	public boolean addDepartment(TrainingType obj) throws Exception;

	public TrainingType getDpartmentDetails(TrainingType obj) throws Exception;

	public boolean updateDepartment(TrainingType obj) throws Exception;

	public boolean deleteDepartment(TrainingType obj) throws Exception;

}
