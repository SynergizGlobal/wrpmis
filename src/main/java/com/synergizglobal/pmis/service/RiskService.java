package com.synergizglobal.pmis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.common.EncryptDecrypt;
import com.synergizglobal.pmis.dao.RiskDao;
import com.synergizglobal.pmis.model.Risk;

@Service
public class RiskService {
	@Autowired
	RiskDao dao;

	
	/**
	 * This method get the Risk list
	 * 
	 * @param riskObj is object for the model class Risk
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskList(Risk obj) throws Exception {
		return dao.getRiskList(obj);
	}
	/**
	 * This method get the projects list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getProjectsList() throws Exception {
		return dao.getProjectsList();
	}	
	/**
	 * This method get the works list
	 * 
	 * @param risk is object for the model class Risk
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getWorksList(Risk obj) throws Exception {
		return dao.getWorksList(obj);
	}
	/**
	 * This method get the risk impact list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskImpactList() throws Exception {
		return dao.getRiskImpactList();
	}
	/**
	 * This method get the risk area list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskAreaList() throws Exception {
		return dao.getRiskAreaList();
	}
	public List<Risk> getRiskSubAreaList(Risk obj) throws Exception {
		return dao.getRiskSubAreaList(obj);
	}
	/**
	 * This method get the risk sub area list
	 * 
	 * @param risk is object for the model class Risk
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskProbabilityList() throws Exception {
		return dao.getRiskProbabilityList();
	}
	/**
	 * This method get the risk category list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskCategoryList() throws Exception {
		return dao.getRiskCategoryList();
	}

	/**
	 * This method get the risk status list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSatausList() throws Exception {
		return dao.getRiskSatausList();
	}
	
	/**
	 * This method get the risk
	 * 
	 * @param riskId it is string type variable that holds the riskId
	 * @return type of this method is obj that is model class Risk type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public Risk getRisk(String riskId) throws Exception {
		//return (Risk) EncryptDecrypt.encryptObject(dao.getRisk(riskId));
		return dao.getRisk(riskId);
	}
	
	/**
	 * This method update the risk
	 * 
	 * @param obj is object for the model class Risk
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRisk(Risk obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getDateOfIdentification()) && !obj.getDateOfIdentification().equals("null")){
				Date convertedDate = sdf.parse(obj.getDateOfIdentification());
				String currentDate = sqlDate.format(convertedDate);
				obj.setDateOfIdentification(currentDate);
			}else {
				obj.setDateOfIdentification(null);
			}
			if(!StringUtils.isEmpty(obj.getTargetDateForMitigation()) && !obj.getTargetDateForMitigation().equals("null")){
				Date convertedDate = sdf.parse(obj.getTargetDateForMitigation());
				String currentDate = sqlDate.format(convertedDate);
				obj.setTargetDateForMitigation(currentDate);
			}else {
				obj.setTargetDateForMitigation(null);
			}
			if(!StringUtils.isEmpty(obj.getRiskMitigatedOn()) && !obj.getRiskMitigatedOn().equals("null")){
				Date convertedDate = sdf.parse(obj.getRiskMitigatedOn());
				String currentDate = sqlDate.format(convertedDate);
				obj.setRiskMitigatedOn(currentDate);
			}else {
				obj.setRiskMitigatedOn(null);
			}
			flag = dao.updateRisk(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	
	/**
	 * This method update risk status
	 * 
	 * @param obj is object for the model class Risk
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRiskStatus(Risk obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getRiskMitigatedOn())){
				Date convertedDate = sdf.parse(obj.getRiskMitigatedOn());
				String currentDate = sqlDate.format(convertedDate);
				obj.setRiskMitigatedOn(currentDate);
			}
			flag = dao.updateRiskStatus(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	/**
	 * This method upload the risks
	 * 
	 * @param risksList is list type object
	 * @return type of this method is count that is integer type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public int uploadRisks(List<Risk> risksList) throws Exception {
		return dao.uploadRisks(risksList);
	}
	
	/**
	 * This method add the risk
	 * 
	 * @param obj is object for the model class Risk
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean addRisk(Risk obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getDateOfIdentification())){
				Date convertedDate = sdf.parse(obj.getDateOfIdentification());
				String currentDate = sqlDate.format(convertedDate);
				obj.setDateOfIdentification(currentDate);
			}
			if(!StringUtils.isEmpty(obj.getTargetDateForMitigation())){
				Date convertedDate = sdf.parse(obj.getTargetDateForMitigation());
				String currentDate = sqlDate.format(convertedDate);
				obj.setTargetDateForMitigation(currentDate);
			}
			if(!StringUtils.isEmpty(obj.getRiskMitigatedOn())){
				Date convertedDate = sdf.parse(obj.getRiskMitigatedOn());
				String currentDate = sqlDate.format(convertedDate);
				obj.setRiskMitigatedOn(currentDate);
			}
			flag = dao.addRisk(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	
	/**
	 * This method get the risk area list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskAreaListForSearch() throws Exception {
		return dao.getRiskAreaListForSearch();
	}
	/**
	 * This method get the risk sub area list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSubAreaListForSearch() throws Exception {
		return dao.getRiskSubAreaListForSearch();
	}
	/**
	 * This method get the risk category list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskCategoryListForSearch() throws Exception {
		return dao.getRiskCategoryListForSearch();
	}
	/**
	 * This method get the risk impact list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskImpactListForSearch() throws Exception {
		return dao.getRiskImpactListForSearch();
	}
	/**
	 * This method get the risk probability list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskProbabilityListForSearch() throws Exception {
		return dao.getRiskProbabilityListForSearch();
	}
	/**
	 * This method get the risk status list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskSatausListForSearch() throws Exception {
		return dao.getRiskSatausListForSearch();
	}
	/**
	 * This method get the risk classification list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskClassificationListForSearch() throws Exception {
		return dao.getRiskClassificationListForSearch();
	}
	/**
	 * This method get the risk priority list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getRiskPriorityListForSearch() throws Exception {
		return dao.getRiskPriorityListForSearch();
	}
	/**
	 * This method get the responsible person list for search
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<Risk> getResponsiblePersonsListForSearch() throws Exception {
		return dao.getResponsiblePersonsListForSearch();
	}
	
}
