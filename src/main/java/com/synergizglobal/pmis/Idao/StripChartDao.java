package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.StripChart;

public interface StripChartDao {
	
	public List<StripChart> getProjectsList() throws Exception;
	
	
	public List<StripChart> getWorksList(StripChart obj) throws Exception;
	

}
