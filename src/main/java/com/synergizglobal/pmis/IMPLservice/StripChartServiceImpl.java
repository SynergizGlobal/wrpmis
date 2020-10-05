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
	public List<StripChart> getStripChartActivities(StripChart obj) throws Exception {
		return stripChartDao.getStripChartActivities(obj);
	}

	@Override
	public List<StripChart> getStripChartComponents(StripChart obj) throws Exception {
		return stripChartDao.getStripChartComponents(obj);
	}

	@Override
	public List<StripChart> getStripChartComponentIds(StripChart obj) throws Exception {
		return stripChartDao.getStripChartComponentIds(obj);
	}

	@Override
	public List<StripChart> getStripChartLines(StripChart obj) throws Exception {
		return stripChartDao.getStripChartLines(obj);
	}

	@Override
	public List<StripChart> getStripChartSections(StripChart obj) throws Exception {
		return stripChartDao.getStripChartSections(obj);
	}

	@Override
	public List<StripChart> getStripChartStructures(StripChart obj) throws Exception {
		return stripChartDao.getStripChartStructures(obj);
	}

	@Override
	public List<StripChart> getStripChartTypes() throws Exception {
		return stripChartDao.getStripChartTypes();
	}

	@Override
	public List<StripChart> getStripChartStructureTypes() throws Exception {
		return stripChartDao.getStripChartStructureTypes();
	}

	@Override
	public List<StripChart> getStripChartContractsList(StripChart obj) throws Exception {
		return stripChartDao.getStripChartContractsList(obj);
	}

	@Override
	public StripChart getStripChartDetails(StripChart obj) throws Exception {
		return stripChartDao.getStripChartDetails(obj);
	}

	@Override
	public boolean updateStripChart(StripChart obj) throws Exception {
		return stripChartDao.updateStripChart(obj);
	}
	
}
