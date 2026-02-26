package com.synergizglobal.wrpmis.reference.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface DashboardTypeDao {

	public List<TrainingType> getDashboardTypesList() throws Exception;

	public boolean addDashboardType(TrainingType obj) throws Exception;

	public TrainingType getDashBoardTypeDetails(TrainingType obj) throws Exception;

	public boolean updateDashBoardType(TrainingType obj) throws Exception;

	public boolean deleteDashBoardType(TrainingType obj) throws Exception;
}
