package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.RandRBsesDao;
import com.synergizglobal.pmis.Iservice.RandRBsesService;
import com.synergizglobal.pmis.model.RRBses;
@Service
public class RandRBsesServiceImpl implements RandRBsesService{
	@Autowired
	RandRBsesDao dao;

	@Override
	public List<RRBses> getWorksFilterListInRRBses(RRBses obj) throws Exception {
		return dao.getWorksFilterListInRRBses(obj);
	}

	@Override
	public List<RRBses> getAgencyNameFilterListInRRBses(RRBses obj) throws Exception {
		return dao.getAgencyNameFilterListInRRBses(obj);
	}

	@Override
	public int getTotalRecords(RRBses obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<RRBses> getRRBsesList(RRBses obj, int startIndex, int offset, String searchParameter) throws Exception {
		return dao.getRRBsesList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<RRBses> getProjectsListForRRBsesForm(RRBses obj) throws Exception {
		return dao.getProjectsListForRRBsesForm(obj);
	}

	@Override
	public List<RRBses> getWorkListForRRBsesForm(RRBses obj) throws Exception {
		return dao.getWorkListForRRBsesForm(obj);
	}

	@Override
	public List<RRBses> getContractsListForRRBsesForm(RRBses obj) throws Exception {
		return dao.getContractsListForRRBsesForm(obj);
	}

	@Override
	public List<RRBses> getFileTypeListForRRBsesForm(RRBses obj) throws Exception {
		return dao.getFileTypeListForRRBsesForm(obj);
	}

	@Override
	public RRBses getRRBsesDeatils(RRBses obj) throws Exception {
		return dao.getRRBsesDeatils(obj);
	}

	@Override
	public boolean addRRBses(RRBses obj) throws Exception {
		return dao.addRRBses(obj);
	}

	@Override
	public boolean updateRRBses(RRBses obj) throws Exception {
		return dao.updateRRBses(obj);
	}

}
