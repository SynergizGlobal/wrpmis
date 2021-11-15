package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.StructureDao;
import com.synergizglobal.pmis.Iservice.StructureService;
import com.synergizglobal.pmis.model.Structure;

@Service
public class StructureServiceImpl implements StructureService{
	
	@Autowired
	StructureDao dao;

	@Override
	public List<Structure> getProjectsListFilter(Structure obj) throws Exception {
		return dao.getProjectsListFilter(obj);
	}

	@Override
	public List<Structure> getWorksListFilter(Structure obj) throws Exception {
		return dao.getWorksListFilter(obj);
	}

	@Override
	public List<Structure> getContractsListFilter(Structure obj) throws Exception {
		return dao.getContractsListFilter(obj);
	}

	@Override
	public List<Structure> getDepartmentsListFilter(Structure obj) throws Exception {
		return dao.getDepartmentsListFilter(obj);
	}

}
