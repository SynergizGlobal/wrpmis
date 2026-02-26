package com.synergizglobal.wrpmis.reference.Idao;

import com.synergizglobal.wrpmis.reference.model.TrainingType;

public interface RROccupancyStatusDao {

	TrainingType getRROccupancyStatusDetails(TrainingType obj) throws Exception;

	boolean addRROccupancyStatus(TrainingType obj) throws Exception;

	boolean updateRROccupancyStatus(TrainingType obj) throws Exception;

	boolean deleteRROccupancyStatus(TrainingType obj) throws Exception;

}
