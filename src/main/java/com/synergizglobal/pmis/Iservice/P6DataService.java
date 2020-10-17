package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.P6Data;

public interface P6DataService {

	public List<P6Data> getFobList(P6Data obj) throws Exception;

	public int updateP6Activities(List<P6Data> p6dataList1,P6Data obj) throws Exception;

	public List<P6Data> getActivityDataList() throws Exception;

	public String uploadP6WBSActivities(List<P6Data> wbsList, List<P6Data> activitiesList, P6Data p6data) throws Exception;

}
