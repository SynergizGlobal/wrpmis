package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ActivitiesBulkUpdateDao;
import com.synergizglobal.pmis.Iservice.ActivitiesBulkUpdateService;
import com.synergizglobal.pmis.model.StripChart;
@Service
public class ActivitiesBulkUpdateServiceImpl implements ActivitiesBulkUpdateService{

	@Autowired
	ActivitiesBulkUpdateDao dao;
	
	@Override
	public StripChart getAcivitiesBulkData(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkData(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateProjectsList(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateProjectsList(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateWorksList(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateWorksList(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateContractsList(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateContractsList(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateStructures(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateStructures(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateLines(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateLines(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateSections(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateSections(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkUpdateComponentIds(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateComponentIds(obj);
	}

	@Override
	public List<StripChart> getAcivitiesBulkActivitiesList(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkActivitiesList(obj);
	}

	@Override
	public StripChart getAcivitiesBulkUpdateDetails(StripChart obj) throws Exception {
		return dao.getAcivitiesBulkUpdateDetails(obj);
	}

	@Override
	public List<StripChart> getstripChartfilterList(StripChart obj) throws Exception {
		return dao.getstripChartfilterList(obj);
	}

	@Override
	public boolean updateAcivitiesBulk(StripChart obj) throws Exception {
		return dao.updateAcivitiesBulk(obj);
	}

}
