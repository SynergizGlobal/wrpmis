package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.UAV;

public interface UAVService {

	public String updateP6Activities(List<UAV> UAVList1,UAV obj) throws Exception;

	public List<UAV> getActivityDataList(UAV obj) throws Exception;

	public String uploadP6WBSActivities(List<UAV> wbsList, List<UAV> activitiesList, UAV UAV) throws Exception;


	public List<UAV> getWorksList(UAV obj) throws Exception;
	
	public List<UAV> getWorksFilterListInUAV(UAV obj) throws Exception;
	
	public List<UAV> getSurveyDatesFilterList(UAV obj) throws Exception;
	
	public List<UAV> getStructuresFilterList(UAV obj) throws Exception;
	
	public List<UAV> getProjectsList(UAV obj) throws Exception;
	
	public List<UAV> getStationList(UAV obj) throws Exception;
	
	public int getTotalRecords(UAV obj, String searchParameter) throws Exception;
	
	public List<UAV> getUAVList(UAV obj, int startIndex, int offset, String searchParameter) throws Exception;
	
	public List<UAV> getUavVideoDataStructure(UAV obj) throws Exception;
	
	public List<UAV> getSurveyDateVideoSpecifications(UAV obj) throws Exception;
	
	public List<UAV> getStructureSurvey_Date(UAV obj) throws Exception;
	
	public UAV getUav(UAV obj)throws Exception;
	
	public int uploadMP4Data(UAV obj) throws Exception;
	public int uploadSRTData(UAV obj) throws Exception;
	public int uploadAnnotationData(UAV obj) throws Exception;
	
	
}
