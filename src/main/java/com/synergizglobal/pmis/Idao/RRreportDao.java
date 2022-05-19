package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.RandRMain;

public interface RRreportDao {

	List<RandRMain> getWorksFilterListInRRReport(RandRMain obj) throws Exception;

	List<RandRMain> getLocationListInRRReport(RandRMain obj) throws Exception;

	List<RandRMain> getTypeOfStructuresFilterListInRRReport(RandRMain obj) throws Exception;

	RandRMain getRandRMainData(RandRMain obj) throws Exception;

	List<RandRMain> getProjectsFilterListInRRReport(RandRMain obj) throws Exception;

}
