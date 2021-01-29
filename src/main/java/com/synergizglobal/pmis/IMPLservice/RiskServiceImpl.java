package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.RiskDao;
import com.synergizglobal.pmis.Iservice.RiskService;
import com.synergizglobal.pmis.model.Risk;
import com.synergizglobal.pmis.model.RiskReport;

@Service
public class RiskServiceImpl implements RiskService{

	@Autowired
	RiskDao dao;

	@Override
	public List<Risk> getWorksList(Risk obj) throws Exception {
		return dao.getWorksList(obj);
	}

	@Override
	public int[] uploadRiskAssessments(List<Risk> risksList) throws Exception {
		return dao.uploadRiskAssessments(risksList);
	}

	@Override
	public Risk getRiskAssessment(Risk obj) throws Exception {
		return dao.getRiskAssessment(obj);
	}

	@Override
	public boolean updateRiskAssessment(Risk obj) throws Exception {
		return dao.updateRiskAssessment(obj);
	}
	
	
	/*********************************************************************************/
	
	@Override
	public List<Risk> getRiskList(Risk obj) throws Exception {
		return dao.getRiskList(obj);
	}

	@Override
	public List<Risk> getRisktWorksList(Risk obj) throws Exception {
		return dao.getRisktWorksList(obj);
	}

	@Override
	public List<Risk> getRiskAreasList(Risk obj) throws Exception {
		return dao.getRiskAreasList(obj);
	}

	@Override
	public List<Risk> getRiskPriotityList(Risk obj) throws Exception {
		return dao.getRiskPriotityList(obj);
	}

	@Override
	public List<Risk> getRiskClassificationsList(Risk obj) throws Exception {
		return dao.getRiskClassificationsList(obj);
	}

	@Override
	public List<Risk> getRiskResponsiblePersonsFilterList(Risk obj) throws Exception {
		return dao.getRiskResponsiblePersonsFilterList(obj);
	}

	@Override
	public List<Risk> getPrioritiesList() throws Exception {
		return dao.getPrioritiesList();
	}

	@Override
	public List<Risk> getProjectList() throws Exception {
		return dao.getProjectList();

	}

	@Override
	public List<Risk> getSubAreasList(Risk obj) throws Exception {
		return dao.getSubAreasList(obj);
	}

	@Override
	public List<Risk> getAreasList() throws Exception {
		return dao.getAreasList();
	}

	@Override
	public List<Risk> getAreaList(Risk obj) throws Exception {
		return dao.getAreaList(obj);
	}

	@Override
	public List<Risk> getSubAreasList() throws Exception {
		return dao.getSubAreasList();
	}

	@Override
	public Risk getRiskDetails(Risk obj) throws Exception {
		 return dao.getRiskDetails(obj);
	}

	@Override
	public boolean addRisk(Risk obj) throws Exception {
		 return dao.addRisk(obj);
	}

	@Override
	public boolean updateRisk(Risk obj) throws Exception {
		 return dao.updateRisk(obj);
	}

	@Override
	public int[] uploadRisks(List<Risk> risksList, List<Risk> revisionList) throws Exception {
		 return dao.uploadRisks(risksList,revisionList);
	}

	@Override
	public List<Risk> getAssessmentDatesFilterList(Risk obj) throws Exception {
		 return dao.getAssessmentDatesFilterList(obj);
	}

	@Override
	public List<RiskReport> getExportRiskList(Risk risk) throws Exception {
		 return dao.getExportRiskList(risk);
	}

	@Override
	public List<RiskReport> getATRRevisionDataList(Risk risk) throws Exception {
		return dao.getATRRevisionDataList(risk);
	}
}
