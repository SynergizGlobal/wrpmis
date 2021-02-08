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

	@Override
	public List<ZonalRailway> getProjectsListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		return dao.getProjectsListForZonalRailwayForm(obj);
	}

	@Override
	public List<ZonalRailway> getWorkListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		return dao.getWorkListForZonalRailwayForm(obj);

	}

	@Override
	public List<ZonalRailway> getRailwayListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		return dao.getRailwayListForZonalRailwayForm(obj);
	}

	@Override
	public List<ZonalRailway> getSourceOfFundListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		return dao.getSourceOfFundListForZonalRailwayForm(obj);
	}

	@Override
	public List<ZonalRailway> getStatusListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		return dao.getStatusListForZonalRailwayForm(obj);
	}

	@Override
	public ZonalRailway getZonalRailway(ZonalRailway obj) throws Exception {
		return dao.getZonalRailway(obj);
	}

	@Override
	public boolean addZonalRailway(ZonalRailway obj) throws Exception {
		return dao.addZonalRailway(obj);
	}

	@Override
	public boolean updateZonalRailway(ZonalRailway obj) throws Exception {
		return dao.updateZonalRailway(obj);
	}

	@Override
	public List<ZonalRailway> getUserListForZonalRailwayForm(ZonalRailway obj) throws Exception {
		return dao.getUserListForZonalRailwayForm(obj);
	}

}
