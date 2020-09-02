package com.synergizglobal.pmis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.dao.LandAcquisitionDao;
import com.synergizglobal.pmis.model.LandAcquisition;

@Service
public class LandAcquisitionService {
	@Autowired
	LandAcquisitionDao dao;
	
	/**
	 * This method get the land acquisition list
	 * @return type of this method is objsList that is List type object   
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisition> getLandAcquisitionList() throws Exception {
		return dao.getLandAcquisitionList();
	}
	
	/**
	 * This method get the work list
	 *
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisition> getWorksList() throws Exception {
		return dao.getWorksList();
	}
	/**
	 * This method get the land acquisition category list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisition> getLandAcquisitionCategoryList() throws Exception {
		return dao.getLandAcquisitionCategoryList();
	}

	/**
	 * This method get the land acquisition sub category list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisition> getLandAcquisitionSubCategoryList() throws Exception {
		return dao.getLandAcquisitionSubCategoryList();
	}
	
	/**
	 * This method get the land acquisition status list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<LandAcquisition> getLandAcquisitionSatausList() throws Exception {
		return dao.getLandAcquisitionSatausList();
	}
	/**
	 * This method get the land acquisition
	 * 
	 * @param landAcquisitionId it is string type variable that holds the land acquisition id
	 * @return type of this method is obj that is model class Land acquisition type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public LandAcquisition getLandAcquisition(String landAcquisitionId) throws Exception {
		return dao.getLandAcquisition(landAcquisitionId);
	}
	/**
	 * This method update the land acquisition
	 * 
	 * @param obj is object for the model class LandAcquisition
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateLandAcquisition(LandAcquisition obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getPaymentDate())){
				Date convertedDate = sdf.parse(obj.getPaymentDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setPaymentDate(currentDate);
			}
			
			flag = dao.updateLandAcquisition(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	/**
	 * This method update the land acquisition1
	
	 * @param obj is object for the model class LandAcquisition
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateLandAcquisition1(LandAcquisition obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getPaymentDate())){
				Date convertedDate = sdf.parse(obj.getPaymentDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setPaymentDate(currentDate);
			}
			
			flag = dao.updateLandAcquisition1(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	/**
	 * This method update the land acquisition status
	 * 
	 * @param obj is object for the model class LandAcquisition
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateLandAcquisitionStatus(LandAcquisition obj) throws Exception {
		return dao.updateLandAcquisitionStatus(obj);
	}
}
