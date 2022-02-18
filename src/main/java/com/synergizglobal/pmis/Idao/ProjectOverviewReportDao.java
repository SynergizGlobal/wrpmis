package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Contract;

public interface ProjectOverviewReportDao {


	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception;

	public List<Contract> getDepartmentFilterListInPOR(Contract obj) throws Exception;
	public List<Contract> getProjectOverviewReportList(Contract obj) throws Exception;

}
