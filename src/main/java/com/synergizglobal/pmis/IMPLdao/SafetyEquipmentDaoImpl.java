package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.SafetyEquipmentDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.Work;

@Repository
public class SafetyEquipmentDaoImpl implements SafetyEquipmentDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception{
		List<SafetyEquipment> objsList = null;
		try {
			String qry = "select safety_equipment_id,contract_id_fk, safety_equipment_number,"
					+ "safety_equipment_detail, validity_date,remarks from safety_equipment where safety_equipment_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
		objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
			return objsList;
	}

	@Override
	public SafetyEquipment getSafetyDetails(SafetyEquipment obj)throws Exception{
	SafetyEquipment sObj =null;
		
		try {
			String qry = "select w.work_id,safety_equipment_id,p.project_id,contract_id_fk from safety_equipment s "
					+"LEFT OUTER join contract c on contract_id_fk =c.contract_id " 
					+"LEFT OUTER join work w on c.work_id_fk = w.work_id "  
					+"LEFT OUTER join project p on w.project_id_fk = p.project_id "
					+"where safety_equipment_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSafety_equipment_id())) {
				qry = qry + " and safety_equipment_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSafety_equipment_id())) {
				pValues[i++] = obj.getSafety_equipment_id();
			}
			sObj = (SafetyEquipment)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(sObj.getSafety_equipment_id())) {
			
				List<SafetyEquipment> objsList = null;
			String qryDetails = "select safety_equipment_id,contract_id_fk, safety_equipment_number,"
					+"safety_equipment_detail, DATE_FORMAT(validity_date,'%d-%m-%Y') AS validity_date,remarks,attachment from safety_equipment "
					+"where safety_equipment_id is not null and safety_equipment_id = ? ";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getSafety_equipment_id()}, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));	
			sObj.setSafetyEquipments(objsList);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sObj;
	}
	
	@Override
	public boolean addSafetyEquipment(SafetyEquipment obj)throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		int count = 0;
		int[] c = {};
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO safety_equipment"
					+ "(contract_id_fk, safety_equipment_number, safety_equipment_detail, validity_date, remarks, attachment)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)";
			stmt = con.prepareStatement(insertQry);
		int	arraySize = 0;
			
			if( !StringUtils.isEmpty(obj.getSafety_equipment_numbers()) && obj.getSafety_equipment_numbers().length > 0) {
				obj.setSafety_equipment_numbers(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafety_equipment_numbers()));
				if(arraySize < obj.getSafety_equipment_numbers().length) {
					arraySize = obj.getSafety_equipment_numbers().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getSafety_equipment_details()) && obj.getSafety_equipment_details().length > 0) {
				obj.setSafety_equipment_details(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafety_equipment_details()));
				if(arraySize < obj.getSafety_equipment_details().length) {
					arraySize = obj.getSafety_equipment_details().length;
				}
			}if( !StringUtils.isEmpty(obj.getValidity_dates()) && obj.getValidity_dates().length > 0) {
				obj.setValidity_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getValidity_dates()));
				if(arraySize < obj.getValidity_dates().length) {
					arraySize = obj.getValidity_dates().length;
				}
			}if( !StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
				obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
				if(arraySize < obj.getRemarkss().length) {
					arraySize = obj.getRemarkss().length;
				}
			}if( !StringUtils.isEmpty(obj.getAttachments()) && obj.getAttachments().length > 0) {
				obj.setAttachments(CommonMethods.replaceEmptyByNullInSringArray(obj.getAttachments()));
				if(arraySize < obj.getAttachments().length) {
					arraySize = obj.getAttachments().length;
				}
			}
			for (int i = 0; i < arraySize; i++) {
			    int k = 1;
			    stmt.setString(k++,(obj.getContract_id_fk().length() > 0)?obj.getContract_id_fk():null);
			    stmt.setString(k++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
			    stmt.setString(k++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
				stmt.setString(k++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
			    stmt.setString(k++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
			    stmt.setString(k++,(obj.getAttachments().length > 0)?obj.getAttachments()[i]:null);
			    MultipartFile file = obj.getSafetyEquipmentFile();
			    if (null != file && !file.isEmpty()){
			    	String saveDirectory = CommonConstants.SAFETYEQUIPMENT_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
			    }
			   
				stmt.addBatch();
			}
			
				c = stmt.executeBatch();
				if(stmt != null){stmt.close();}

			if(c.length > 0) {
				flag = true;
			}
		 
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public boolean updateSafetyEquipment(SafetyEquipment obj)throws Exception{
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE safety_equipment set "
					+ "contract_id_fk= :contract_id_fk, safety_equipment_number= :safety_equipment_number, safety_equipment_detail= :safety_equipment_detail,"
					+ "validity_date= :validity_date, remarks= :remarks, attachment= :attachment "
					+ "where safety_equipment_id= :safety_equipment_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteSafetyEquipment(SafetyEquipment obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE FROM safety_equipment where safety_equipment_id= :safety_equipment_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(deleteQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public List<Work> getworkList()throws Exception{
		List<Work> objsList = null;
		try {
			String qry ="select work_id as work_id_fk,work_name from work ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Project> getProjectsList()throws Exception{
		List<Project> objsList = null;
		try {
			String qry ="select project_id ,project_name from project ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
