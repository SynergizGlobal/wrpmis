package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.RandRMain;

public interface RRBSESDao {

	List<RandRMain> getWorkFilterListInRRBSES(RandRMain obj) throws Exception;

	List<RandRMain> getHodFilterListInRRBSES(RandRMain obj) throws Exception;

	int getTotalRecords(RandRMain obj, String searchParameter) throws Exception;

	List<RandRMain> getRRBSESList(RandRMain obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<RandRMain> getPeopleListForRRForm(RandRMain obj) throws Exception;
 
	RandRMain getRRBSES(RandRMain obj) throws Exception;

	String addRRBSES(RandRMain obj) throws Exception;

	String updateRRBSES(RandRMain obj) throws Exception;

	List<RandRMain> getWorkFilterList(RandRMain obj) throws Exception;

	List<RandRMain> getHodFilterList(RandRMain obj) throws Exception;

}
