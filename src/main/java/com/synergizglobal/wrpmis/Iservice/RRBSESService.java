package com.synergizglobal.wrpmis.Iservice;

import java.util.List;

import com.synergizglobal.wrpmis.model.RandRMain;

public interface RRBSESService {

	List<RandRMain> getWorkFilterListInRRBSES(RandRMain obj) throws Exception;

	List<RandRMain> getHodFilterListInRRBSES(RandRMain obj) throws Exception;

	int getTotalRecords(RandRMain obj, String searchParameter) throws Exception;

	List<RandRMain> getRRBSESList(RandRMain obj, int startIndex, int offset, String searchParameter) throws Exception;

	List<RandRMain> getPeopleListForRRForm(RandRMain rr) throws Exception;

	RandRMain getRRBSES(RandRMain rr) throws Exception;  

	String addRRBSES(RandRMain obj) throws Exception;

	String updateRRBSES(RandRMain obj) throws Exception;

	List<RandRMain> getWorkFilterList(RandRMain rr) throws Exception;

	List<RandRMain> getHodFilterList(RandRMain rr) throws Exception; 


}
