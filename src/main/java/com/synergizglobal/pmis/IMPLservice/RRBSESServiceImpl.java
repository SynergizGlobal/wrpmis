package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao. RRBSESDao;
import com.synergizglobal.pmis.Iservice.RRBSESService;
import com.synergizglobal.pmis.model.RandRMain;
@Service
public class RRBSESServiceImpl implements RRBSESService{
	@Autowired
	 RRBSESDao dao;

	@Override
	public List<RandRMain> getWorkFilterListInRRBSES(RandRMain obj) throws Exception {
		return dao.getWorkFilterListInRRBSES(obj);
	}

	@Override
	public List<RandRMain> getHodFilterListInRRBSES(RandRMain obj) throws Exception {
		return dao.getHodFilterListInRRBSES(obj);
	}

	@Override
	public int getTotalRecords(RandRMain obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<RandRMain> getRRBSESList(RandRMain obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return dao.getRRBSESList(obj,startIndex,offset,searchParameter);
	}
 
	@Override
	public List<RandRMain> getPeopleListForRRForm(RandRMain obj) throws Exception {
		return dao.getPeopleListForRRForm(obj);
	}

	@Override
	public RandRMain getRRBSES(RandRMain obj) throws Exception {
		return dao.getRRBSES(obj);
	}

	@Override
	public String addRRBSES(RandRMain obj) throws Exception {
		return dao.addRRBSES(obj);
	}

	@Override
	public String updateRRBSES(RandRMain obj) throws Exception {
		return dao.updateRRBSES(obj);
	}

	@Override
	public List<RandRMain> getWorkFilterList(RandRMain obj) throws Exception {
		return dao.getWorkFilterList(obj);
	}

	@Override
	public List<RandRMain> getHodFilterList(RandRMain obj) throws Exception {
		return dao.getHodFilterList(obj);
	}
}
