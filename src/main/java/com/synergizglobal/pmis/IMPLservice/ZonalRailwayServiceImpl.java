package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ZonalRailwayDao;
import com.synergizglobal.pmis.Iservice.ZonalRailwayService;
import com.synergizglobal.pmis.model.ZonalRailway;
@Service
public class ZonalRailwayServiceImpl implements ZonalRailwayService{
	
	@Autowired
	 ZonalRailwayDao dao;

	@Override
	public List<ZonalRailway> getZonalRailwayWorksList(ZonalRailway obj) throws Exception {
		return dao.getZonalRailwayWorksList(obj);
	}

	@Override
	public List<ZonalRailway> getZonalRailwayProjectsList(ZonalRailway obj) throws Exception {
		return dao.getZonalRailwayProjectsList(obj);
	}

	@Override
	public List<ZonalRailway> getZonalRailwayExecutionAgencyRailwayList(ZonalRailway obj) throws Exception {
		return dao.getZonalRailwayExecutionAgencyRailwayList(obj);
	}

	@Override
	public List<ZonalRailway> getZonalRailwaySourceOfFundsList(ZonalRailway obj) throws Exception {
		return dao.getZonalRailwaySourceOfFundsList(obj);
	}

	@Override
	public List<ZonalRailway> getZonalRailwayStatusList(ZonalRailway obj) throws Exception {
		return dao.getZonalRailwayStatusList(obj);
	}

	@Override
	public List<ZonalRailway> getZonalRailwayList(ZonalRailway obj) throws Exception {
		return dao.getZonalRailwayList(obj);
	}

}
