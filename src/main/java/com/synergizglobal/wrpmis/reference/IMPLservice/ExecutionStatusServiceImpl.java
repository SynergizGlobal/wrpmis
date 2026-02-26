package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.ExecutionStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.ExecutionStatusService;
import com.synergizglobal.wrpmis.reference.model.Safety;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class ExecutionStatusServiceImpl implements ExecutionStatusService{
	@Autowired
	ExecutionStatusDao dao;

	@Override
	public List<Safety> getExecutionStatusList() throws Exception {
		return dao.getExecutionStatusList();
	}

	@Override
	public boolean addExecutionStatus(TrainingType obj) throws Exception {
		return dao.addExecutionStatus(obj);
	}

	@Override
	public TrainingType getExecutionStatusDetails(TrainingType obj) throws Exception {
		return dao.getExecutionStatusDetails(obj);
	}

	@Override
	public boolean updateExecutionStatus(TrainingType obj) throws Exception {
		return dao.updateExecutionStatus(obj);
	}

	@Override
	public boolean deleteExecutionStatus(TrainingType obj) throws Exception {
		return dao.deleteExecutionStatus(obj);
	}
}
