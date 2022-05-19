package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.RandRMain;

public interface RRreportService {

	List<RandRMain> getWorksFilterListInRRReport(RandRMain obj) throws Exception;

	List<RandRMain> getLocationListInRRReport(RandRMain obj) throws Exception;

	List<RandRMain> getTypeOfStructuresFilterListInRRReport(RandRMain obj) throws Exception;

	RandRMain getRandRMainData(RandRMain obj) throws Exception;

	List<RandRMain> getProjectsFilterListInRRReport(RandRMain obj) throws Exception;

}
