package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.P6Data;

public interface P6DataDao {

	public List<P6Data> getFobList(P6Data obj) throws Exception;

	public int updateP6Activities(List<P6Data> p6dataList,P6Data p6Data) throws Exception;

	public List<P6Data> getActivityDataList() throws Exception;

	public String uploadP6WBSActivities(List<P6Data> wbsList, List<P6Data> activitiesList, P6Data p6data) throws Exception;


}
