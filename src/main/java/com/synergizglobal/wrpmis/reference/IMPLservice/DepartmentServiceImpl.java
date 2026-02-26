package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DepartmentDao;
import com.synergizglobal.wrpmis.reference.Iservice.DepartmentService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentDao dao;

	@Override
	public List<TrainingType> getDepartmentsList() throws Exception {
		return dao.getDepartmentsList();
	}

	@Override
	public boolean addDepartment(TrainingType obj) throws Exception {
		return dao.addDepartment(obj);
	}

	@Override
	public TrainingType getDpartmentDetails(TrainingType obj) throws Exception {
		return dao.getDpartmentDetails(obj);
	}

	@Override
	public boolean updateDepartment(TrainingType obj) throws Exception {
		return dao.updateDepartment(obj);
	}

	@Override
	public boolean deleteDepartment(TrainingType obj) throws Exception {
		return dao.deleteDepartment(obj);
	}
}
