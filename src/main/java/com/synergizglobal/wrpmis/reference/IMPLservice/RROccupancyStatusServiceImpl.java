package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.RROccupancyStatusDao;
import com.synergizglobal.wrpmis.reference.Iservice.RROccupancyStatusService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class RROccupancyStatusServiceImpl implements RROccupancyStatusService{
	@Autowired
	RROccupancyStatusDao dao;

	@Override
	public TrainingType getRROccupancyStatusDetails(TrainingType obj) throws Exception {
		return dao.getRROccupancyStatusDetails(obj);
	}

	@Override
	public boolean addRROccupancyStatus(TrainingType obj) throws Exception {
		return dao.addRROccupancyStatus(obj);
	}

	@Override
	public boolean updateRROccupancyStatus(TrainingType obj) throws Exception {
		return dao.updateRROccupancyStatus(obj);
	}

	@Override
	public boolean deleteRROccupancyStatus(TrainingType obj) throws Exception {
		return dao.deleteRROccupancyStatus(obj);
	}

}
