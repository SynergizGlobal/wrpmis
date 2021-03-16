package com.synergizglobal.pmis.reference.Idao;

import java.util.List;

import com.synergizglobal.pmis.reference.model.Risk;
import com.synergizglobal.pmis.reference.model.TrainingType;

public interface RailwayDao {

	public List<Risk> getRailwayList() throws Exception;

	public boolean addRailway(Risk obj) throws Exception;

	public TrainingType getRailwayDetails(TrainingType obj) throws Exception;

	public boolean updateRailway(TrainingType obj) throws Exception;

	public boolean deleteRailway(TrainingType obj) throws Exception;
}
