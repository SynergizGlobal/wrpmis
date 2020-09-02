package com.synergizglobal.pmis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.dao.RandRDao;
import com.synergizglobal.pmis.model.RandR;

@Service
public class RandRService {
	@Autowired
	RandRDao dao;
	
	/**
	 * This method get RandR list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<RandR> getRandRList() throws Exception {
		return dao.getRandRList();
	}
	/**
	 * This method get RandR status list
	 * 
	 * @return type of this method is objsList that is List type object 
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public List<RandR> getRandRSatausList() throws Exception {
		return dao.getRandRSatausList();
	}
	/**
	 * This method get RadnR
	 * 
	 * @param randRId it is string type variable that holds the randRId
	 * @return type of this method is obj that is model class RandR type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public RandR getRandR(String randRId) throws Exception {
		return dao.getRandR(randRId);
	}
	
	/**
	 * This method update the RandR
	 * 
	 * @param obj is object for the model class RandR
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRandR(RandR obj) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(obj.getPaymentDate())){
				Date convertedDate = sdf.parse(obj.getPaymentDate());
				String currentDate = sqlDate.format(convertedDate);
				obj.setPaymentDate(currentDate);
			}
			
			flag = dao.updateRandR(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}
	
	/**
	 * This method update RandR status
	 * 
	 * @param obj is object for the model class RandR
	 * @return type of this method is flag that is boolean type
	 * @throws Exception will raise an exception when abnormal termination occur
	 */
	public boolean updateRandRStatus(RandR obj) throws Exception {
		return dao.updateRandRStatus(obj);
	}
}
