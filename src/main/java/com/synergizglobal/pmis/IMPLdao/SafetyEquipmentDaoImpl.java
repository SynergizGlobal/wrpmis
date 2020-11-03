package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.SafetyEquipmentDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.Work;

@Repository
public class SafetyEquipmentDaoImpl implements SafetyEquipmentDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<SafetyEquipment> getSafetyEquipment(SafetyEquipment obj)throws Exception{
		List<SafetyEquipment> objsList = null;
		try {
			String qry = "select safety_equipment_id,contract_id_fk,c.contract_name, safety_equipment_number,safety_equipment_detail, validity_date,s.remarks from safety_equipment s " + 
						 "left join contract c on  s.contract_id_fk = c.contract_id  where safety_equipment_id is not null";
			
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
	public SafetyEquipment getSafetyEquipmentDetails(SafetyEquipment obj)throws Exception{
	SafetyEquipment sObj =null;
		
		try {
			String qry = "select w.work_id,safety_equipment_id,c.contract_name,p.project_id,p.project_name,w.work_name,contract_id_fk from safety_equipment s "
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
					+"where safety_equipment_id is not null and contract_id_fk = ? ";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getContract_id_fk()}, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));	
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
		PreparedStatement insertStmt = null;
		ResultSet rs = null;
		
		boolean flag = false;		
		try {
			con = dataSource.getConnection();
			String insertQry = "INSERT INTO safety_equipment"
					+ "(contract_id_fk, safety_equipment_number, safety_equipment_detail, validity_date, remarks, attachment)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry,Statement.RETURN_GENERATED_KEYS);
			
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
			}
			
			if( !StringUtils.isEmpty(obj.getSafety_equipment_ids()) && obj.getSafety_equipment_ids().length > 0) {
				obj.setSafety_equipment_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafety_equipment_ids()));
				if(arraySize < obj.getSafety_equipment_ids().length) {
					arraySize = obj.getSafety_equipment_ids().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getSafetyEquipmentFileNames()) && obj.getSafetyEquipmentFileNames().length > 0) {
				obj.setSafetyEquipmentFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafetyEquipmentFileNames()));
				if(arraySize < obj.getSafetyEquipmentFileNames().length) {
					arraySize = obj.getSafetyEquipmentFileNames().length;
				}
			}
			String[] documentNames = new String[arraySize];
			
			for (int i = 0; i < arraySize; i++) {
			    int p = 1;
			    insertStmt.setString(p++,(obj.getContract_id_fk()));
			    insertStmt.setString(p++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
			    insertStmt.setString(p++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
			    insertStmt.setString(p++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
			    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
			    insertStmt.setString(p++,(documentNames.length > 0)?documentNames[i]:null);	
			    insertStmt.addBatch();
			}
			int[] insertCount = insertStmt.executeBatch();		
			rs = insertStmt.getGeneratedKeys();
			
			if(insertCount.length > 0) {
				flag = true;
				if(!StringUtils.isEmpty(obj.getSafetyEquipmentFile()) && obj.getSafetyEquipmentFile().length > 0) {
					if(arraySize < obj.getSafetyEquipmentFile().length) {
						arraySize = obj.getSafetyEquipmentFile().length;
					}
					String saveDirectory = CommonConstants.SAFETYEQUIPMENT_FILE_SAVING_PATH ;
					documentNames = new String[arraySize];
					for (int i = 0; i < documentNames.length; i++) {
						if (rs.next()) {
							String id = rs.getString(1);
							obj.setSafety_equipment_id(id);
						}
						MultipartFile file = obj.getSafetyEquipmentFile()[i];
						if (null != file && !file.isEmpty()){
							String fileName = file.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							String fileName_new = "Safety_Equipment-"+obj.getSafety_equipment_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							documentNames[i] = fileName_new;
							FileUploads.singleFileSaving(file, saveDirectory, fileName_new);
							obj.setAttachment(fileName_new);
						} else if (!StringUtils.isEmpty(obj.getSafetyEquipmentFileNames()[i])){
							documentNames[i] = obj.getSafetyEquipmentFileNames()[i];
						} else {
							documentNames[i] = null;
						}
						NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);		
						String updateQry = "UPDATE safety_equipment set attachment= :attachment where safety_equipment_id= :safety_equipment_id ";
						BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
						template.update(updateQry, paramSource1);
					}
				}
			}
		 
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public boolean updateSafetyEquipment(SafetyEquipment obj)throws Exception{
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String updateQry = "UPDATE safety_equipment set "
					+ "safety_equipment_number= ?, safety_equipment_detail=? ,"
					+ "validity_date= ?, remarks= ?, attachment= ? "
					+ "where safety_equipment_id= ?";
			
			updateStmt = con.prepareStatement(updateQry);
			
			String insertQry = "INSERT INTO safety_equipment"
					+ "(contract_id_fk, safety_equipment_number, safety_equipment_detail, validity_date, remarks, attachment)"
					+ "VALUES"
					+ "(?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			
			
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
				}
				
				if( !StringUtils.isEmpty(obj.getSafety_equipment_ids()) && obj.getSafety_equipment_ids().length > 0) {
					obj.setSafety_equipment_ids(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafety_equipment_ids()));
					if(arraySize < obj.getSafety_equipment_ids().length) {
						arraySize = obj.getSafety_equipment_ids().length;
					}
				}
				
				
				if( !StringUtils.isEmpty(obj.getSafetyEquipmentFileNames()) && obj.getSafetyEquipmentFileNames().length > 0) {
					obj.setSafetyEquipmentFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafetyEquipmentFileNames()));
					if(arraySize < obj.getSafetyEquipmentFileNames().length) {
						arraySize = obj.getSafetyEquipmentFileNames().length;
					}
				}
				
				
				String[] documentNames = new String[arraySize];
				if(!StringUtils.isEmpty(obj.getSafetyEquipmentFile()) && obj.getSafetyEquipmentFile().length > 0) {
					if(arraySize < obj.getSafetyEquipmentFile().length) {
						arraySize = obj.getSafetyEquipmentFile().length;
					}
					String saveDirectory = CommonConstants.SAFETYEQUIPMENT_FILE_SAVING_PATH ;
					documentNames = new String[arraySize];
					for (int i = 0; i < documentNames.length; i++) {
						MultipartFile file = obj.getSafetyEquipmentFile()[i];
						if (null != file && !file.isEmpty()){
							String fileName = file.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							String fileName_new = "Safety_Equipment-"+obj.getSafety_equipment_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							documentNames[i] = fileName_new;
							obj.setAttachment(fileName_new);
							FileUploads.singleFileSaving(file, saveDirectory, fileName);
						} else if (!StringUtils.isEmpty(obj.getSafetyEquipmentFileNames()[i])){
							documentNames[i] = obj.getSafetyEquipmentFileNames()[i];
						} else {
							documentNames[i] = null;
						}
					}
				}
				
				
				for (int i = 0; i < arraySize; i++) {
					String sId = obj.getSafety_equipment_ids()[i];
					if(!StringUtils.isEmpty(sId)) {
					    int k = 1;
					    updateStmt.setString(k++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
					    updateStmt.setString(k++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
					    updateStmt.setString(k++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
					    updateStmt.setString(k++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
					    updateStmt.setString(k++,(documentNames.length > 0)?documentNames[i]:null);	
					    updateStmt.setString(k++,(obj.getSafety_equipment_ids()[i]));
					    updateStmt.addBatch();
					} else {
					    int p = 1;
					    insertStmt.setString(p++,(obj.getContract_id_fk()));
					    insertStmt.setString(p++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
					    insertStmt.setString(p++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
					    insertStmt.setString(p++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
					    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
					    insertStmt.setString(p++,(documentNames.length > 0)?documentNames[i]:null);	
					    insertStmt.addBatch();
					}
				}
				
				
				int[] updateCount = updateStmt.executeBatch();
				
				int[] insertCount = insertStmt.executeBatch();
				
			
					
				if(updateCount.length > 0 || insertCount.length > 0) {
					flag = true;
				}
				con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
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
	public List<SafetyEquipment> getProjectsList()throws Exception{
		List<SafetyEquipment> objsList = null;
		try {
			String qry ="select project_id ,project_name from project ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<SafetyEquipment> contractListFilterInSafetyEquipment() throws Exception {
		List<SafetyEquipment> objsList = null;
		try {
			String qry ="select contract_id_fk ,c.contract_name,c.contract_short_name from safety_equipment s "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "GROUP BY contract_id_fk ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
