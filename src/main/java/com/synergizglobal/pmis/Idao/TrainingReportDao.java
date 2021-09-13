package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Training;

public interface TrainingReportDao {

	List<Training> getEmployeesInTraining(Training obj) throws Exception;

	List<Training> getScheduledTrainingTitles(Training obj) throws Exception;

	List<Training> getCompletedTrainingTitles(Training obj) throws Exception;

	List<Training> getScheduledTrainings(Training obj) throws Exception;

	List<Training> getEmployeeTrainings(Training obj) throws Exception;

	List<Training> getCompletedTrainings(Training obj) throws Exception;

	Training getEmployeeTrainingWithStatus(Training obj) throws Exception;

}
