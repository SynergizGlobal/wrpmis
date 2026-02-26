package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ZonalRailwayFundDao {

	public TrainingType getZonalRailwayDetails(TrainingType obj) throws Exception;

	public boolean addZonalRailwayFund(TrainingType obj) throws Exception;

	public boolean updateZonalRailwayFund(TrainingType obj) throws Exception;

	public boolean deleteZonalRailwayFund(TrainingType obj) throws Exception;

}
