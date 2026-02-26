package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.StructureDao;
import com.synergizglobal.wrpmis.Iservice.StructureService;
import com.synergizglobal.wrpmis.model.Structure;

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
	public List<Structure> getProjectsListForStructureForm(Structure obj) throws Exception {
		return dao.getProjectsListForStructureForm(obj);
	}

	@Override
	public List<Structure> getWorkListForStructureForm(Structure obj) throws Exception {
		return dao.getWorkListForStructureForm(obj);
	}

	@Override
	public List<Structure> getContractListForStructureFrom(Structure obj) throws Exception {
		return dao.getContractListForStructureFrom(obj);
	}

	@Override
	public List<Structure> getStructuresListForStructureFrom(Structure obj) throws Exception {
		return dao.getStructuresListForStructureFrom(obj);
	}

	@Override
	public List<Structure> getDepartmentsListForStructureFrom(Structure obj) throws Exception {
		return dao.getDepartmentsListForStructureFrom(obj);
	}

	@Override
	public Structure getStructuresListDetails(Structure obj) throws Exception {
		return dao.getStructuresListDetails(obj);
	}

	@Override
	public String addStructure(Structure obj) throws Exception {
	    return dao.addStructure(obj);
	}
	@Override
	public boolean checkForDuplicateStructure(Structure obj) throws Exception {
	    return dao.checkForDuplicateStructure(obj);
	}

	@Override
	public String updateStructure(Structure obj) throws Exception {
		return dao.updateStructure(obj);
	}

	@Override
	public boolean saveStructureDataUploadFile(Structure obj) throws Exception {
		return dao.saveStructureDataUploadFile(obj);
	}

	@Override
	public int uploadStructures(List<Structure> structuresList) throws Exception {
		return dao.uploadStructures(structuresList);
	}

	@Override
	public List<Structure> getStructureUploadsList(Structure obj) throws Exception {
		return dao.getStructureUploadsList(obj);
	}

	@Override
	public List<Structure> getStructureExportList(Structure structure) throws Exception {
		return dao.getStructureExportList(structure);
	}

	@Override
	public List<Structure> getResponsiblePeopleListForStructureForm(Structure obj) throws Exception {
		return dao.getResponsiblePeopleListForStructureForm(obj);
	}

	@Override
	public List<Structure> getWorkStatusListForStructureForm(Structure obj) throws Exception {
		return dao.getWorkStatusListForStructureForm(obj);
	}

	@Override
	public List<Structure> getUnitsListForStructureForm(Structure obj) throws Exception {
		return dao.getUnitsListForStructureForm(obj);
	}

	@Override
	public List<Structure> getFileTypeForStructureForm(Structure obj) throws Exception {
		return dao.getFileTypeForStructureForm(obj);
	}
	
	@Override
	public boolean deleteStructure(Structure obj) throws Exception {
		return dao.deleteStructure(obj);
	}
	@Override
	public List<Structure> getStructureCount(Structure obj) throws Exception {
		return dao.getStructureCount(obj);
	}
	@Override
	public List<Structure> getStructureTypeCount(Structure obj) throws Exception {
		return dao.getStructureTypeCount(obj);
	}	
}
