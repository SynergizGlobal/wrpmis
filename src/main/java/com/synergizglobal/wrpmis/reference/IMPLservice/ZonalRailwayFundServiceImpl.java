package com.synergizglobal.wrpmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.reference.Idao.UtilityTypeDao;
import com.synergizglobal.wrpmis.reference.Idao.ZonalRailwayFundDao;
import com.synergizglobal.wrpmis.reference.Iservice.ZonalRailwayFundService;
import com.synergizglobal.wrpmis.reference.model.TrainingType;

@Service
public class ZonalRailwayFundServiceImpl implements ZonalRailwayFundService{

	@Autowired
	ZonalRailwayFundDao dao;

	@Override
	public TrainingType getZonalRailwayDetails(TrainingType obj) throws Exception {
		return dao.getZonalRailwayDetails(obj);
	}

	@Override
	public boolean addZonalRailwayFund(TrainingType obj) throws Exception {
		return dao.addZonalRailwayFund(obj);
	}

	@Override
	public boolean updateZonalRailwayFund(TrainingType obj) throws Exception {
		return dao.updateZonalRailwayFund(obj);
	}

	@Override
	public boolean deleteZonalRailwayFund(TrainingType obj) throws Exception {
		return dao.deleteZonalRailwayFund(obj);
	}
}
