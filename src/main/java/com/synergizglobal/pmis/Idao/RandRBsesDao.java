package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.RRBses;

public interface RandRBsesDao {

	List<RRBses> getWorksFilterListInRRBses(RRBses obj) throws Exception;

	List<RRBses> getAgencyNameFilterListInRRBses(RRBses obj) throws Exception;

	int getTotalRecords(RRBses obj, String searchParameter) throws Exception;

	List<RRBses> getRRBsesList(RRBses obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<RRBses> getFileTypeListForRRBsesForm(RRBses obj) throws Exception;

	List<RRBses> getContractsListForRRBsesForm(RRBses obj) throws Exception;

	List<RRBses> getWorkListForRRBsesForm(RRBses obj) throws Exception;

	List<RRBses> getProjectsListForRRBsesForm(RRBses obj) throws Exception;

	RRBses getRRBsesDeatils(RRBses obj) throws Exception;

	boolean addRRBses(RRBses obj) throws Exception;

	boolean updateRRBses(RRBses obj) throws Exception;

}
