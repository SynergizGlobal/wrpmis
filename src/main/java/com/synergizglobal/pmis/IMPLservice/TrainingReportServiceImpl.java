package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.TrainingReportDao;
import com.synergizglobal.pmis.Iservice.TrainingReportService;
import com.synergizglobal.pmis.model.Training;
@Service
public class TrainingReportServiceImpl implements TrainingReportService{
	
	@Autowired
	TrainingReportDao dao;
	
	@Override
	public List<Training> getEmployeesInTraining(Training obj) throws Exception {
		return dao.getEmployeesInTraining(obj);
	}

	@Override
	public List<Training> getScheduledTrainingTitles(Training obj) throws Exception {
		return dao.getScheduledTrainingTitles(obj);
	}

	@Override
	public List<Training> getCompletedTrainingTitles(Training obj) throws Exception {
		return dao.getCompletedTrainingTitles(obj);
	}

	@Override
	public List<Training> getScheduledTrainings(Training obj) throws Exception {
		return dao.getScheduledTrainings(obj);
	}

	@Override
	public List<Training> getEmployeeTrainings(Training obj) throws Exception {
		return dao.getEmployeeTrainings(obj);
	}

	@Override
	public List<Training> getCompletedTrainings(Training obj) throws Exception {
		return dao.getCompletedTrainings(obj);
	}

	@Override
	public Training getEmployeeTrainingWithStatus(Training obj) throws Exception {
		return dao.getEmployeeTrainingWithStatus(obj);
	}

}
