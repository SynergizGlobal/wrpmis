package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.WbsTreeDao;
import com.synergizglobal.pmis.Iservice.WbsTreeService;
import com.synergizglobal.pmis.model.WbsTree;

@Service
public class WbsTreeServiceImpl implements WbsTreeService{
	@Autowired
	WbsTreeDao dao;

	
	@Override
	public List<WbsTree> getLevel4List(WbsTree obj) throws Exception {
		return dao.getLevel4List(obj);
	}


	@Override
	public List<WbsTree> getContractsList(WbsTree obj) throws Exception {
		return dao.getContractsList(obj);
	}
}
