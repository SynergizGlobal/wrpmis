package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DepartmentDao {

	public List<TrainingType> getDepartmentsList() throws Exception;

	public boolean addDepartment(TrainingType obj) throws Exception;

	public TrainingType getDpartmentDetails(TrainingType obj) throws Exception;

	public boolean updateDepartment(TrainingType obj) throws Exception;

	public boolean deleteDepartment(TrainingType obj) throws Exception;
}
