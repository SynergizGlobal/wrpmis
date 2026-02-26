package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Structure;

public interface StructureFormDao {

	List<Structure> getWorkStatusList(Structure obj) throws Exception;

	List<Structure> getWorksListForFilter(Structure obj) throws Exception;

	List<Structure> getContractsListForFilter(Structure obj) throws Exception;

	int getTotalRecords(Structure obj, String searchParameter) throws Exception;

	List<Structure> getStructuresList(Structure obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<Structure> getStructureDetailsLocations(Structure obj) throws Exception;

	List<Structure> getStructureDetailsTypes(Structure obj) throws Exception;

	Structure getStructuresFormDetails(Structure obj) throws Exception;

	boolean addStructureForm(Structure obj) throws Exception;

	boolean updateStructureForm(Structure obj) throws Exception;

	List<Structure> getStructureTypeListForFilter(Structure obj) throws Exception;

	List<Structure> getWorkStatusListForFilter(Structure obj) throws Exception;

}
