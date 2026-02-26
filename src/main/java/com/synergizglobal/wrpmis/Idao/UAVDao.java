package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.UAV;

public interface UAVDao {
 
	
	public String updateP6Activities(List<UAV> p6dataList,UAV p6Data) throws Exception;

	public List<UAV> getActivityDataList(UAV obj) throws Exception;

	public String uploadP6WBSActivities(List<UAV> wbsList, List<UAV> activitiesList, UAV p6data) throws Exception;
	
	public List<UAV> getWorksList(UAV obj) throws Exception;

	public List<UAV> getProjectsList(UAV obj) throws Exception;

	public int getTotalRecords(UAV obj) throws Exception;

	public List<UAV> getUAVList(UAV obj, int startIndex, int offset, String searchParameter) throws Exception;

	public List<UAV> getUavVideoDataStructure(UAV obj) throws Exception;

	public List<UAV> getWorksFilterListInUAV(UAV obj) throws Exception;

	public List<UAV> getSurveyDatesFilterList(UAV obj) throws Exception;

	public List<UAV> getSurveyDateVideoSpecifications(UAV obj) throws Exception;
	
	public List<UAV> getStructuresFilterList(UAV obj) throws Exception;

	public List<UAV> getStructureSurvey_Date(UAV obj) throws Exception;

	public UAV getUav(UAV obj) throws Exception;

	public int uploadMP4Data(UAV obj) throws Exception;
	
	public int uploadSRTData(UAV obj) throws Exception;
	public int uploadAnnotationData(UAV obj) throws Exception;

	public List<UAV> getStationList(UAV obj) throws Exception;
	boolean checkDataAvailable(String id,String work_id_fk, String survey_date, String from_station, String to_station) throws Exception;



}
