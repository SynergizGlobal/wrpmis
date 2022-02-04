package com.synergizglobal.pmis.reference.Iservice;

import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RROccupancyStatusService {

	TrainingType getRROccupancyStatusDetails(TrainingType obj) throws Exception;

	boolean addRROccupancyStatus(TrainingType obj) throws Exception;

	boolean updateRROccupancyStatus(TrainingType obj) throws Exception;

	boolean deleteRROccupancyStatus(TrainingType obj) throws Exception;

}
