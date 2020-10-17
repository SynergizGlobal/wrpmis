package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.P6DataDao;
import com.synergizglobal.pmis.Iservice.P6DataService;
import com.synergizglobal.pmis.model.P6Data;

@Service
public class P6DataServiceImpl implements P6DataService{
	
	@Autowired
	P6DataDao dao;


	@Override
	public List<P6Data> getFobList(P6Data obj) throws Exception {
		return dao.getFobList(obj);
	}

	@Override
	public int updateP6Activities(List<P6Data> p6dataList,P6Data p6Data) throws Exception {
		return dao.updateP6Activities(p6dataList,p6Data);
	}


	@Override
	public List<P6Data> getActivityDataList() throws Exception {
		return dao.getActivityDataList();
	}


	@Override
	public String uploadP6WBSActivities(List<P6Data> wbsList, List<P6Data> activitiesList, P6Data p6data)
			throws Exception {
		return dao.uploadP6WBSActivities(wbsList,activitiesList,p6data);
	}

}
