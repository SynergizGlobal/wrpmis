package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.StructureFormDao;
import com.synergizglobal.pmis.Iservice.StructureFormService;
import com.synergizglobal.pmis.model.Structure;
@Service
public class StructureFormServiceImpl implements StructureFormService{

	@Autowired
	StructureFormDao dao;
	
	@Override
	public List<Structure> getWorkStatusList(Structure obj) throws Exception {
		return dao.getWorkStatusList(obj);
	}

	@Override
	public List<Structure> getWorksListForFilter(Structure obj) throws Exception {
		return dao.getWorksListForFilter(obj);
	}

	@Override
	public List<Structure> getContractsListForFilter(Structure obj) throws Exception {
		return dao.getContractsListForFilter(obj);
	}

	@Override
	public int getTotalRecords(Structure obj, String searchParameter) throws Exception {
		return dao.getTotalRecords(obj,searchParameter);
	}

	@Override
	public List<Structure> getStructuresList(Structure obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		return dao.getStructuresList(obj,startIndex,offset,searchParameter);
	}

	@Override
	public List<Structure> getStructureDetailsLocations(Structure obj) throws Exception {
		return dao.getStructureDetailsLocations(obj);
	}

	@Override
	public List<Structure> getStructureDetailsTypes(Structure obj) throws Exception {
		return dao.getStructureDetailsTypes(obj);
	}

	@Override
	public Structure getStructuresFormDetails(Structure obj) throws Exception {
		return dao.getStructuresFormDetails(obj);
	}

	@Override
	public boolean addStructureForm(Structure obj) throws Exception {
		return dao.addStructureForm(obj);
	}

	@Override
	public boolean updateStructureForm(Structure obj) throws Exception {
		return dao.updateStructureForm(obj);
	}

}
