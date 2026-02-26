package com.synergizglobal.wrpmis.reference.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.DashboardTypeDao;
import com.synergizglobal.wrpmis.reference.Iservice.DashboardTypeService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;
@Service
public class DashboardTypeServiceImpl implements DashboardTypeService{

	@Autowired
	DashboardTypeDao dao;

	@Override
	public List<TrainingType> getDashboardTypesList() throws Exception {
		return dao.getDashboardTypesList();
	}

	@Override
	public boolean addDashboardType(TrainingType obj) throws Exception {
		return dao.addDashboardType(obj);
	}

	@Override
	public TrainingType getDashBoardTypeDetails(TrainingType obj) throws Exception {
		return dao.getDashBoardTypeDetails(obj);
	}

	@Override
	public boolean updateDashBoardType(TrainingType obj) throws Exception {
		return dao.updateDashBoardType(obj);
	}

	@Override
	public boolean deleteDashBoardType(TrainingType obj) throws Exception {
		return dao.deleteDashBoardType(obj);
	}
}
