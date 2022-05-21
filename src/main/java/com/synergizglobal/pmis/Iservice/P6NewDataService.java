package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.P6Data;

public interface P6NewDataService {

	public int updateP6Activities(List<P6Data> p6dataList1,P6Data obj) throws Exception;

	public List<P6Data> getActivityDataList(P6Data obj) throws Exception;

	public String uploadP6WBSActivities(List<P6Data> wbsList, List<P6Data> activitiesList, P6Data p6data) throws Exception;

	public List<P6Data> getFobList(P6Data obj) throws Exception;

	public List<P6Data> getContractsList(P6Data obj) throws Exception;

	public List<P6Data> getFobListFilter(P6Data obj) throws Exception;

	public List<P6Data> getContractsListFilter(P6Data obj) throws Exception;

	public List<P6Data> getUploadTypesFilter(P6Data obj) throws Exception;

	public List<P6Data> getStatusListFilter(P6Data obj) throws Exception;

	public List<P6Data> getWorksList(P6Data obj) throws Exception;

}
