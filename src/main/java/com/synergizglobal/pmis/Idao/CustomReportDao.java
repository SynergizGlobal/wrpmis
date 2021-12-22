package com.synergizglobal.pmis.Idao;

import java.util.ArrayList;
import java.util.List;

import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.CustomReportColumns;

public interface CustomReportDao {

	public List<Contract> getAllContractList(Contract contract) throws Exception;

	public List<CustomReportColumns> getModuleGroups(CustomReportColumns obj) throws Exception;
	public List<CustomReportColumns> getModuleGroupColumns(CustomReportColumns obj) throws Exception;

	public ArrayList<String[]> getTableColumns(CustomReportColumns obj) throws Exception;

	public List<CustomReportColumns> getModuleFilters(CustomReportColumns obj) throws Exception;

	public List<CustomReportColumns> getModuleFiltersOptionValues(CustomReportColumns obj) throws Exception;

}
