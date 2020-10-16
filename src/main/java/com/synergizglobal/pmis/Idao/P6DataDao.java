package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.P6Data;

public interface P6DataDao {

	public List<P6Data> getFobList(P6Data obj)throws Exception;

	public int p6WbsUpload(List<P6Data> p6dataList)throws Exception;

	public int p6ActivitiesUpload(List<P6Data> p6dataList)throws Exception;


}
