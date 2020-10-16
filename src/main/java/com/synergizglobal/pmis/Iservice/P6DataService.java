package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.P6Data;
import com.synergizglobal.pmis.model.WorkContractModuleStatus;

public interface P6DataService {

	public List<P6Data> getFobList(P6Data obj)throws Exception;

	public int p6WbsUpload(List<P6Data> p6dataList, String userName)throws Exception;

	public int p6ActivitiesUpload(List<P6Data> p6dataList, String userName)throws Exception;

	public int p6ActivitiesUpdate(List<P6Data> p6dataList1, String userName)throws Exception;

	public List<P6Data> getActivityDataList()throws Exception;

	

}
