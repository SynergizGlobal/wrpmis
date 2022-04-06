package com.synergizglobal.pmis.Iservice;

import java.util.List;

import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Work;

public interface ProjectWorkOverviewReportService {

	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception;

	public List<Contract> getProjectsFilterListInPOR(Contract obj) throws Exception;

	public List<Work> getProjectOverviewReportList(Work obj) throws Exception;
	public List<Contract> getProjectPhotos(Contract obj) throws Exception;
	public List<Contract> getStructuralProgress(Contract obj) throws Exception;
	public List<Contract> getListOfContracts(Contract obj) throws Exception;
	public List<Contract> getProcurementStatus(Contract obj) throws Exception;
	public List<LandAcquisition> getLandAcquisitionStatus(LandAcquisition obj) throws Exception;
	
	public List<Contract> getFinanceReportContracts(Contract obj) throws Exception;
	
}
