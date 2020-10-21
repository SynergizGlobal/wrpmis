package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface StripChartService{
	public List<StripChart> getStripChartProjectsList(StripChart obj) throws Exception;
	public List<StripChart> getStripChartWorksList(StripChart obj) throws Exception;
	public List<StripChart> getStripChartContractsList(StripChart obj) throws Exception;
	public List<StripChart> getStripChartActivities(StripChart obj) throws Exception;
	public List<StripChart> getStripChartComponents(StripChart obj) throws Exception;
	public List<StripChart> getStripChartComponentIds(StripChart obj) throws Exception;
	public List<StripChart> getStripChartLines(StripChart obj) throws Exception;
	public List<StripChart> getStripChartSections(StripChart obj) throws Exception;
	public List<StripChart> getStripChartStructures(StripChart obj) throws Exception;
	public List<StripChart> getStripChartTypes() throws Exception;
	public List<StripChart> getStripChartStructureTypes() throws Exception;
	public StripChart getStripChartDetails(StripChart obj) throws Exception;
	public boolean updateStripChart(StripChart obj) throws Exception;
}
