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

	@Override
	public List<Risk> getRiskAssessmentList(Risk obj) throws Exception {
		return dao.getRiskAssessmentList(obj);
	}

	@Override
	public List<Risk> getSubWorksFilterListInRiskAssessmnt(Risk obj) throws Exception {
		return dao.getSubWorksFilterListInRiskAssessmnt(obj);
	}

	@Override
	public List<Risk> getAreasFilterListInRiskAssessment(Risk obj) throws Exception {
		return dao.getAreasFilterListInRiskAssessment(obj);
	}

	@Override
	public List<Risk> getAssessmentDatesFilterListInRiskAssessment(Risk obj) throws Exception {
		return dao.getAssessmentDatesFilterListInRiskAssessment(obj);
	}

	@Override
	public List<Risk> getRiskAssessmentDates(Risk obj) throws Exception {
		return dao.getRiskAssessmentDates(obj);
	}

	@Override
	public List<Risk> getRiskAssessmentUploadsList(Risk obj) throws Exception {
		return dao.getRiskAssessmentUploadsList(obj);
	}

	@Override
	public List<Risk> getWorksListFromRiskUploads(Risk obj) throws Exception {
		return dao.getWorksListFromRiskUploads(obj);
	}

	@Override
	public boolean saveRiskAssessmentUploadFile(Risk risk) throws Exception {
		return dao.saveRiskAssessmentUploadFile(risk);
	}
	
}
