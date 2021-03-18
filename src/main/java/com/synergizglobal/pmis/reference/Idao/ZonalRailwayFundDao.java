package com.synergizglobal.pmis.reference.Idao;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface ZonalRailwayFundDao {

	public TrainingType getZonalRailwayDetails(TrainingType obj) throws Exception;

	public boolean addZonalRailwayFund(TrainingType obj) throws Exception;

	public boolean updateZonalRailwayFund(TrainingType obj) throws Exception;

	public boolean deleteZonalRailwayFund(TrainingType obj) throws Exception;

}
