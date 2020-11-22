package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface PMISProgressDao {

	public List<StripChart> getMileStoneFilterList(StripChart obj) throws Exception;

	public List<StripChart> getContractMileStonesFilterList(StripChart obj) throws Exception;

	public boolean updateProgressForm(StripChart obj) throws Exception;

	public List<StripChart> getMileStoneList(StripChart obj) throws Exception;

}
