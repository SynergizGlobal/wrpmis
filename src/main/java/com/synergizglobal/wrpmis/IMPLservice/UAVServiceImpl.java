package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.UAVDao;
import com.synergizglobal.wrpmis.Iservice.UAVService;
import com.synergizglobal.wrpmis.model.UAV;

@Service
public class UAVServiceImpl implements UAVService{
	
	@Autowired
	UAVDao dao;

 
	@Override
	public String updateP6Activities(List<UAV> UAVList,UAV UAV) throws Exception {
		return dao.updateP6Activities(UAVList,UAV);
	}


	@Override
	public List<UAV> getActivityDataList(UAV obj) throws Exception {
		return dao.getActivityDataList(obj);
	}


	@Override
	public String uploadP6WBSActivities(List<UAV> wbsList, List<UAV> activitiesList, UAV UAV)
			throws Exception {
		return dao.uploadP6WBSActivities(wbsList,activitiesList,UAV);
	}
	public List<UAV> getProjectsList(UAV obj) throws Exception
	{
		return dao.getProjectsList(obj);
	}


	@Override
	public List<UAV> getWorksList(UAV obj) throws Exception {
		return dao.getWorksList(obj);
	}
	
	@Override
	public List<UAV> getWorksFilterListInUAV(UAV obj) throws Exception {
		return dao.getWorksFilterListInUAV(obj);
	}
	
	@Override
	public List<UAV> getSurveyDatesFilterList(UAV obj) throws Exception
	{
		return dao.getSurveyDatesFilterList(obj);
	}


	@Override
	public int getTotalRecords(UAV obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj);
	}


	@Override
	public List<UAV> getUAVList(UAV obj, int startIndex, int offset, String searchParameter) throws Exception {
		return dao.getUAVList(obj, startIndex, offset, searchParameter);
	}
	
	@Override
	public List<UAV> getUavVideoDataStructure(UAV obj) throws Exception
	{
		return dao.getUavVideoDataStructure(obj);
	}
	
	@Override
	public List<UAV> getSurveyDateVideoSpecifications(UAV obj) throws Exception
	{
		return dao.getSurveyDateVideoSpecifications(obj);
	}


	@Override
	public List<UAV> getStructuresFilterList(UAV obj) throws Exception {
		return dao.getStructuresFilterList(obj);
	}	
	
	@Override
	public List<UAV> getStructureSurvey_Date(UAV obj) throws Exception
	{
		return dao.getStructureSurvey_Date(obj);
	}
	
	@Override
	public UAV getUav(UAV obj)throws Exception
	{
		return dao.getUav(obj);
	}
	
	@Override
	public int uploadMP4Data(UAV obj) throws Exception
	{
		return dao.uploadMP4Data(obj);
	}


	@Override
	public int uploadSRTData(UAV obj) throws Exception {
		return dao.uploadSRTData(obj);
	}


	@Override
	public int uploadAnnotationData(UAV obj) throws Exception {
		return dao.uploadAnnotationData(obj);
	}


	@Override
	public List<UAV> getStationList(UAV obj) throws Exception {
		return dao.getStationList(obj);
	}


	@Override
	public boolean checkDataAvailable(String id,String work_id_fk, String survey_date, String from_station, String to_station)
			throws Exception {
		return dao.checkDataAvailable(id,work_id_fk, survey_date, from_station, to_station);
	}
	
	

}
