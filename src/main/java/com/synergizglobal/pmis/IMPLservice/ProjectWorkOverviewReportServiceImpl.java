package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.ProjectWorkOverviewReportDao;
import com.synergizglobal.pmis.Iservice.ProjectWorkOverviewReportService;
import java.sql.ResultSet;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Work;
@Service
public class ProjectWorkOverviewReportServiceImpl implements ProjectWorkOverviewReportService{

	@Autowired
	ProjectWorkOverviewReportDao dao;

	@Override
	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception {
		return dao.getWorksFilterListInPOR(obj);
	}

	@Override
	public List<Contract> getProjectsFilterListInPOR(Contract obj) throws Exception {
		return dao.getProjectsFilterListInPOR(obj);
	}
	@Override
	public List<Work> getProjectOverviewReportList(Work obj) throws Exception {
		return dao.getProjectOverviewReportList(obj);
	}
	public List<Contract> getProjectPhotos(Contract obj) throws Exception{
		return dao.getProjectPhotos(obj);
	}
	public List<Contract> getStructuralProgress(Contract obj) throws Exception{
		return dao.getStructuralProgress(obj);
	}
	public List<Contract> getListOfContracts(Contract obj) throws Exception{
		return dao.getListOfContracts(obj);
	}
	public List<Contract> getProcurementStatus(Contract obj) throws Exception{
		return dao.getProcurementStatus(obj);
	}
	public List<LandAcquisition> getLandAcquisitionStatus(LandAcquisition obj) throws Exception{
		return dao.getLandAcquisitionStatus(obj);
	}
	public List<Contract> getFinanceReportContracts(Contract obj) throws Exception
	{
		return dao.getFinanceReportContracts(obj);
	}
	public ResultSet getFinanceSummaryReport()  throws Exception
	{
		return dao.getFinanceSummaryReport();
	}
	
	public List<Contract> getFinanceSummaryReportByProjectWorks()  throws Exception
	{
		return dao.getFinanceSummaryReportByProjectWorks();
	}
}
