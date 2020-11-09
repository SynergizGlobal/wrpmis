package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ProgressBulkUpdateDao;
import com.synergizglobal.pmis.Iservice.ProgressBulkUpdateService;
import com.synergizglobal.pmis.model.StripChart;

@Service
public class ProgressBulkUpdateServiceImpl implements ProgressBulkUpdateService{
	
	@Autowired
	ProgressBulkUpdateDao dao;

	@Override
	public StripChart getProgressBulkData(StripChart obj) throws Exception {
		return dao.getProgressBulkData(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateProjectsList(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateProjectsList(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateWorksList(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateWorksList(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateContractsList(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateContractsList(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateStructures(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateStructures(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateLines(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateLines(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateSections(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateSections(obj);
	}

	@Override
	public List<StripChart> getProgressBulkUpdateComponentIds(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateComponentIds(obj);
	}

	@Override
	public List<StripChart> getProgressBulkActivitiesList(StripChart obj) throws Exception {
		return dao.getProgressBulkActivitiesList(obj);
	}

	@Override
	public StripChart getProgressBulkUpdateDetails(StripChart obj) throws Exception {
		return dao.getProgressBulkUpdateDetails(obj);
	}

}
