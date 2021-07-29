package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FOB;

public interface FOBService {

	List<FOB> getFOBList(FOB obj) throws Exception;

	boolean addFOB(FOB obj) throws Exception;

	FOB getFOB(FOB obj) throws Exception;

	boolean updateFOB(FOB obj) throws Exception;

	boolean deleteFOB(FOB obj) throws Exception;

	/*
	 * List<FOB> contractListFromFOB() throws Exception;
	 */
	List<FOB> getWorkStatusList(FOB obj)throws Exception;

	public List<FOB> getWorksListForFilter(FOB obj) throws Exception;
	
	List<FOB> getContractsListForFilter(FOB obj)throws Exception;

	List<FOB> getProjectsListForFOBForm(FOB obj) throws Exception;

	List<FOB> getWorkListForFOBForm(FOB obj) throws Exception;

	List<FOB> getContractsListForFOBForm(FOB obj) throws Exception;
	/**
	List<FOB> getFOBsList(FOB obj, int startIndex, int offset, String searchParameter) throws Exception;

	int getTotalRecords(FOB obj, String searchParameter) throws Exception;*/

	List<FOB> getFobDetailsList(FOB obj) throws Exception;

	List<FOB> getFOBDetails(FOB fob) throws Exception;

	List<FOB> getFobFileTypesList(FOB obj) throws Exception;

	List<FOB> getFobIdCheck(FOB obj) throws Exception;

	List<FOB> getFobDetailsLocations(FOB obj) throws Exception;

	List<FOB> getFobDetailsTypes(FOB obj) throws Exception;

	List<FOB> getResponsiblePeopleListForFOBForm(FOB obj) throws Exception;

	List<FOB> getUnitsList(FOB obj) throws Exception;

}
