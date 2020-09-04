package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface StripChartService{
	
	public List<StripChart> getProjectsList() throws Exception;
	
	public List<StripChart> getWorksList(StripChart obj) throws Exception;
	
	
	
}
