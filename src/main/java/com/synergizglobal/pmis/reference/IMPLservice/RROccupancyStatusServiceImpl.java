package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.RROccupancyStatusDao;
import com.synergizglobal.pmis.reference.Iservice.RROccupancyStatusService;
import com.synergizglobal.pmis.reference.model.TrainingType;

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
