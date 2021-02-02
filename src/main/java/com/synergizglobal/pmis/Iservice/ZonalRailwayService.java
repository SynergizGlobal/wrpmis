package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.ZonalRailway;

public interface ZonalRailwayService {

	public List<ZonalRailway> getZonalRailwayWorksList(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getZonalRailwayProjectsList(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getZonalRailwayExecutionAgencyRailwayList(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getZonalRailwaySourceOfFundsList(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getZonalRailwayStatusList(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getZonalRailwayList(ZonalRailway obj) throws Exception;

}
