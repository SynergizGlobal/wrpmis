package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Training;

public interface TrainingReportService {

	List<Training> getEmployeesInTraining(Training obj) throws Exception;

	List<Training> getScheduledTrainings(Training obj) throws Exception;

	List<Training> getEmployeeTrainings(Training obj) throws Exception;

	List<Training> getCompletedTrainings(Training obj) throws Exception;

}
