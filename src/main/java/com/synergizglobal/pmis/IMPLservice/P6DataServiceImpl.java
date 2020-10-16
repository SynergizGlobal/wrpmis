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
	public int p6WbsUpload(List<P6Data> p6dataList) throws Exception {
		return dao.p6WbsUpload(p6dataList);
	}


	@Override
	public int p6ActivitiesUpload(List<P6Data> p6dataList) throws Exception {
		return dao.p6ActivitiesUpload(p6dataList);
	}

}
