package com.synergizglobal.wrpmis.Idao;

import java.sql.ResultSet;
import java.util.List;

import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.LandAcquisition;
import com.synergizglobal.wrpmis.model.Work;

public interface ProjectWorkOverviewReportDao {


	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception;

	public List<Contract> getProjectsFilterListInPOR(Contract obj) throws Exception;
	public List<Work> getProjectOverviewReportList(Work obj) throws Exception;

	public List<Contract> getProjectPhotos(Contract obj) throws Exception;

	public List<Contract> getStructuralProgress(Contract obj) throws Exception;

	public List<Contract> getListOfContracts(Contract obj) throws Exception;

	public List<Contract> getProcurementStatus(Contract obj) throws Exception;
	public List<LandAcquisition> getLandAcquisitionStatus(LandAcquisition obj) throws Exception;

	public List<Contract> getFinanceReportContracts(Contract obj) throws Exception;
	
	public ResultSet getFinanceSummaryReport() throws Exception;

	public List<Contract> getFinanceSummaryReportByProjectWorks() throws Exception;

}
