package com.synergizglobal.pmis.Idao;

import java.util.List;

import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;

public interface RiskDao {

	public List<Risk> getRiskList(Risk obj) throws Exception;

	public List<Risk> getRisktWorksList(Risk obj) throws Exception;

	public List<Risk> getRiskAreasList(Risk obj) throws Exception;

	public List<Risk> getRiskPriotityList(Risk obj) throws Exception;

	public List<Risk> getRiskClassificationsList(Risk obj) throws Exception;

	public List<Risk> getRiskResponsiblePersonsFilterList(Risk obj) throws Exception;

	public List<Risk> getPrioritiesList() throws Exception;

	public List<Risk> getProjectList() throws Exception;

	public List<Risk> getSubAreasList(Risk obj) throws Exception;

	public List<Risk> getAreasList() throws Exception;

	public List<Risk> getAreaList(Risk obj) throws Exception;

	public List<Risk> getSubAreasList() throws Exception;

	public Risk getRiskDetails(Risk obj) throws Exception;

	public boolean addRisk(Risk obj) throws Exception;

	public boolean updateRisk(Risk obj) throws Exception;

	public int[] uploadRisks(List<Risk> risksList) throws Exception;

	public List<Risk> getAssessmentDatesFilterList(Risk obj) throws Exception;

	public List<RiskReport> getExportRiskList(Risk risk) throws Exception;

}
