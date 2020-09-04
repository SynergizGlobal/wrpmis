package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.StripChartDao;
import com.synergizglobal.pmis.Iservice.StripChartService;
import com.synergizglobal.pmis.model.StripChart;

@Service
public class StripChartServiceImpl implements StripChartService{
	
	@Autowired
	StripChartDao stripChartDao;
	
	@Override
	public List<StripChart> getProjectsList() throws Exception {
		return stripChartDao.getProjectsList();
	}
	
	
	public List<StripChart> getWorksList(StripChart obj) throws Exception {
		return stripChartDao.getWorksList(obj);
	}
	
	
	
	
	
	
}
