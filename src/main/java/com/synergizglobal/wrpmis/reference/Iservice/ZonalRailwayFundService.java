package com.synergizglobal.wrpmis.reference.Iservice;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface ZonalRailwayFundService {

	public TrainingType getZonalRailwayDetails(TrainingType obj) throws Exception;
	
	public boolean addZonalRailwayFund(TrainingType obj) throws Exception;

	public boolean updateZonalRailwayFund(TrainingType obj) throws Exception;

	public boolean deleteZonalRailwayFund(TrainingType obj) throws Exception;


}


