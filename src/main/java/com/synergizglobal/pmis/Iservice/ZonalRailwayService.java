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

	public List<ZonalRailway> getProjectsListForZonalRailwayForm(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getWorkListForZonalRailwayForm(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getRailwayListForZonalRailwayForm(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getSourceOfFundListForZonalRailwayForm(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getStatusListForZonalRailwayForm(ZonalRailway obj) throws Exception;

	public ZonalRailway getZonalRailway(ZonalRailway obj) throws Exception;

	public boolean addZonalRailway(ZonalRailway obj) throws Exception;

	public boolean updateZonalRailway(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getUserListForZonalRailwayForm(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getRailwayListForZonalRailwaysForm(ZonalRailway obj) throws Exception;

	public List<ZonalRailway> getProgressList(String id) throws Exception;

	public int getTotalRecords(ZonalRailway obj, String searchParameter) throws Exception;

	public List<ZonalRailway> getZonalsList(ZonalRailway obj, int startIndex, int offset, String searchParameter) throws Exception;

}
