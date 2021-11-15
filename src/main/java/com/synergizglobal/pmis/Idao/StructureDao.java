package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Structure;

public interface StructureDao {
	List<Structure> getProjectsListFilter(Structure obj) throws Exception;

	List<Structure> getWorksListFilter(Structure obj) throws Exception;

	List<Structure> getContractsListFilter(Structure obj) throws Exception;

	List<Structure> getDepartmentsListFilter(Structure obj) throws Exception;
}
