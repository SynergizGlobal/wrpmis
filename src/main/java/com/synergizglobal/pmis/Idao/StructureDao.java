package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Structure;

public interface StructureDao {
	
	List<Structure> getProjectsListFilter(Structure obj) throws Exception;

	List<Structure> getWorksListFilter(Structure obj) throws Exception;

	List<Structure> getContractsListFilter(Structure obj) throws Exception;

	List<Structure> getDepartmentsListFilter(Structure obj) throws Exception;

	int getTotalRecords(Structure obj, String searchParameter) throws Exception;

	List<Structure> getStructuresList(Structure obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<Structure> getProjectsListForStructureForm(Structure obj) throws Exception;

	List<Structure> getWorkListForStructureForm(Structure obj) throws Exception;

	List<Structure> getContractListForStructureFrom(Structure obj) throws Exception;

	List<Structure> getStructuresListForStructureFrom(Structure obj) throws Exception;

	List<Structure> getDepartmentsListForStructureFrom(Structure obj) throws Exception;

	Structure getStructuresListDetails(Structure obj) throws Exception;

	boolean addStructure(Structure obj) throws Exception;

	boolean updateStructure(Structure obj) throws Exception;

	boolean saveStructureDataUploadFile(Structure obj) throws Exception;

	int uploadStructures(List<Structure> structuresList) throws Exception;

	List<Structure> getStructureUploadsList(Structure obj) throws Exception;

	List<Structure> getStructureExportList(Structure structure) throws Exception;

	List<Structure> getResponsiblePeopleListForStructureForm(Structure obj) throws Exception;

	List<Structure> getWorkStatusListForStructureForm(Structure obj) throws Exception;

	List<Structure> getUnitsListForStructureForm(Structure obj) throws Exception;

	List<Structure> getFileTypeForStructureForm(Structure obj) throws Exception;
}
