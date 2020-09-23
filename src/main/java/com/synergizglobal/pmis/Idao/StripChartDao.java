package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.StripChart;

public interface StripChartDao {
	public List<StripChart> getStripChartActivities(StripChart obj) throws Exception;
	public List<StripChart> getStripChartComponents(StripChart obj) throws Exception;
	public List<StripChart> getStripChartComponentIds(StripChart obj) throws Exception;
	public List<StripChart> getStripChartLines(StripChart obj) throws Exception;
	public List<StripChart> getStripChartSections(StripChart obj) throws Exception;
	public List<StripChart> getStripChartStructures(StripChart obj) throws Exception;
	public List<StripChart> getStripChartTypes() throws Exception;
	public List<StripChart> getStripChartStructureTypes() throws Exception;
	public List<Contract> getContractsList(StripChart obj) throws Exception;
	public StripChart getStripChartDetails(StripChart obj) throws Exception;
	public boolean updateStripChart(StripChart obj) throws Exception;
}
