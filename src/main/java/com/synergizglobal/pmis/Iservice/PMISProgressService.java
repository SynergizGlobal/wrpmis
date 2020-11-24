package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Document;
import com.synergizglobal.pmis.model.StripChart;

public interface PMISProgressService {

	public List<StripChart> getMileStoneFilterList(StripChart obj) throws Exception;

	public List<StripChart> getMileStonesFilterList(StripChart obj) throws Exception;

	public boolean updateProgressForm(StripChart obj) throws Exception;

	public List<StripChart> getMileStoneList(StripChart obj) throws Exception;

	public List<StripChart> getContractsFilterList(StripChart obj) throws Exception;

}
