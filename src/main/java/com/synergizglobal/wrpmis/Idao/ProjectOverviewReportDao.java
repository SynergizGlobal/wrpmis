package com.synergizglobal.wrpmis.Idao;

import java.util.List;

import com.synergizglobal.wrpmis.model.Contract;

public interface ProjectOverviewReportDao {


	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception;

	public List<Contract> getDepartmentFilterListInPOR(Contract obj) throws Exception;
	public List<Contract> getProjectOverviewReportList(Contract obj) throws Exception;

}
