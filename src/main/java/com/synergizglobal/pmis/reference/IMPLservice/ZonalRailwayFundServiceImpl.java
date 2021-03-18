package com.synergizglobal.pmis.reference.IMPLservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.reference.Idao.UtilityTypeDao;
import com.synergizglobal.pmis.reference.Idao.ZonalRailwayFundDao;
import com.synergizglobal.pmis.reference.Iservice.ZonalRailwayFundService;
import com.synergizglobal.pmis.reference.model.TrainingType;

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
