package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.SafetyEquipment;
import com.synergizglobal.pmis.model.SafetyEquipment;
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
			String qry = "select safety_equipment_id,contract_id_fk,c.contract_name,c.contract_short_name, safety_equipment_number,safety_equipment_detail, "
					+ "DATE_FORMAT(max(validity_date),'%d-%m-%Y') AS validity_date, "
					+ "inspecting_official,DATE_FORMAT(max(last_inspection_date),'%d-%m-%Y') AS last_inspection_date,DATE_FORMAT(max(next_inspection_due),'%d-%m-%Y') AS next_inspection_due "
					+ "from safety_equipment s "  
					+"left join contract c on  s.contract_id_fk = c.contract_id  where safety_equipment_id is not null";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry +" GROUP BY contract_id_fk";

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
			String qry = "select w.work_id,safety_equipment_id,c.contract_name,p.project_id,p.project_name,w.work_name,contract_id_fk "
					+ "from safety_equipment s "
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
					+"safety_equipment_detail, DATE_FORMAT(validity_date,'%d-%m-%Y') AS validity_date,remarks,attachment, "
					+ "inspecting_official,DATE_FORMAT(last_inspection_date,'%d-%m-%Y') AS last_inspection_date,DATE_FORMAT(next_inspection_due,'%d-%m-%Y') AS next_inspection_due "
					+ "from safety_equipment "
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
					+ "(contract_id_fk, safety_equipment_number, safety_equipment_detail, validity_date, remarks, attachment,"
					+ "inspecting_official,last_inspection_date,next_inspection_due)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?)";
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
			}
			if( !StringUtils.isEmpty(obj.getValidity_dates()) && obj.getValidity_dates().length > 0) {
				obj.setValidity_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getValidity_dates()));
				if(arraySize < obj.getValidity_dates().length) {
					arraySize = obj.getValidity_dates().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
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
			
			if( !StringUtils.isEmpty(obj.getInspecting_officials()) && obj.getInspecting_officials().length > 0) {
				obj.setInspecting_officials(CommonMethods.replaceEmptyByNullInSringArray(obj.getInspecting_officials()));
				if(arraySize < obj.getInspecting_officials().length) {
					arraySize = obj.getInspecting_officials().length;
				}
			}
			
			if( !StringUtils.isEmpty(obj.getLast_inspection_dates()) && obj.getLast_inspection_dates().length > 0) {
				obj.setLast_inspection_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getLast_inspection_dates()));
				if(arraySize < obj.getLast_inspection_dates().length) {
					arraySize = obj.getLast_inspection_dates().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getNext_inspection_dues()) && obj.getNext_inspection_dues().length > 0) {
				obj.setNext_inspection_dues(CommonMethods.replaceEmptyByNullInSringArray(obj.getNext_inspection_dues()));
				if(arraySize < obj.getNext_inspection_dues().length) {
					arraySize = obj.getNext_inspection_dues().length;
				}
			}
			
			String[] documentNames = new String[arraySize];
			if(!StringUtils.isEmpty(obj.getSafety_equipment_numbers()) && obj.getSafety_equipment_numbers().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( obj.getSafety_equipment_numbers().length > 0 && !StringUtils.isEmpty(obj.getSafety_equipment_numbers()[i])) {
					    insertStmt.setString(p++,(obj.getContract_id_fk()));
					    insertStmt.setString(p++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
					    insertStmt.setString(p++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
					    insertStmt.setString(p++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
					    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
					    insertStmt.setString(p++,(documentNames.length > 0)?documentNames[i]:null);	
					    
					    insertStmt.setString(p++,(obj.getInspecting_officials().length > 0)?obj.getInspecting_officials()[i]:null);
					    insertStmt.setString(p++,DateParser.parse((obj.getLast_inspection_dates().length > 0)?obj.getLast_inspection_dates()[i]:null));
					    insertStmt.setString(p++,DateParser.parse((obj.getNext_inspection_dues().length > 0)?obj.getNext_inspection_dues()[i]:null));
					    
					    insertStmt.addBatch();
				    }
				}
			}
			int[] insertCount = insertStmt.executeBatch();		
			rs = insertStmt.getGeneratedKeys();
			
			if(insertCount.length > 0) {
				flag = true;
				int arrSize =0;
				if(!StringUtils.isEmpty(obj.getSafetyEquipmentFile()) && obj.getSafetyEquipmentFile().length > 0) {
					if(arrSize < obj.getSafetyEquipmentFile().length) {
						arrSize = obj.getSafetyEquipmentFile().length;
					}
					String saveDirectory = CommonConstants.SAFETYEQUIPMENT_FILE_SAVING_PATH ;
					documentNames = new String[arrSize];
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
							
							NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);		
							String updateQry = "UPDATE safety_equipment set attachment= :attachment where safety_equipment_id= :safety_equipment_id ";
							BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
							template.update(updateQry, paramSource1);
						}else {
							documentNames[i] = null;
						}
						
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
		PreparedStatement stmt = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String updateQry = "UPDATE safety_equipment set "
					+ "safety_equipment_number= ?, safety_equipment_detail=? ,"
					+ "validity_date= ?, remarks= ?, attachment= ?,inspecting_official = ?,last_inspection_date = ?,next_inspection_due = ? "
					+ "where safety_equipment_id= ?";
			
			updateStmt = con.prepareStatement(updateQry);
			
			String insertQry = "INSERT INTO safety_equipment"
					+ "(contract_id_fk, safety_equipment_number, safety_equipment_detail, validity_date, remarks, attachment,"
					+ "inspecting_official,last_inspection_date,next_inspection_due)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?)";
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
				
				if( !StringUtils.isEmpty(obj.getInspecting_officials()) && obj.getInspecting_officials().length > 0) {
					obj.setInspecting_officials(CommonMethods.replaceEmptyByNullInSringArray(obj.getInspecting_officials()));
					if(arraySize < obj.getInspecting_officials().length) {
						arraySize = obj.getInspecting_officials().length;
					}
				}
				
				if( !StringUtils.isEmpty(obj.getLast_inspection_dates()) && obj.getLast_inspection_dates().length > 0) {
					obj.setLast_inspection_dates(CommonMethods.replaceEmptyByNullInSringArray(obj.getLast_inspection_dates()));
					if(arraySize < obj.getLast_inspection_dates().length) {
						arraySize = obj.getLast_inspection_dates().length;
					}
				}
				if( !StringUtils.isEmpty(obj.getNext_inspection_dues()) && obj.getNext_inspection_dues().length > 0) {
					obj.setNext_inspection_dues(CommonMethods.replaceEmptyByNullInSringArray(obj.getNext_inspection_dues()));
					if(arraySize < obj.getNext_inspection_dues().length) {
						arraySize = obj.getNext_inspection_dues().length;
					}
				}
				
				String saveDirectory = CommonConstants.SAFETYEQUIPMENT_FILE_SAVING_PATH ;
				List<MultipartFile> files = new ArrayList<MultipartFile>();
				if(!StringUtils.isEmpty(obj.getSafety_equipment_numbers()) && obj.getSafety_equipment_numbers().length > 0) {
					for (int i = 0; i < arraySize; i++) {
						String sId = obj.getSafety_equipment_ids()[i];
						if(!StringUtils.isEmpty(sId)) {
						    int k = 1;
						    if( obj.getSafety_equipment_numbers().length > 0 && !StringUtils.isEmpty(obj.getSafety_equipment_numbers()[i])) {
							    String docFileName = null;
							    MultipartFile file = obj.getSafetyEquipmentFile()[i];
								if (null != file && !file.isEmpty()){
									String fileName = file.getOriginalFilename();
									DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
									String fileName_new = "Safety_Equipment-"+obj.getSafety_equipment_ids()[i] +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
									docFileName = fileName_new;
									FileUploads.singleFileSaving(file, saveDirectory, docFileName);
								} else {
									docFileName  = (obj.getSafetyEquipmentFileNames().length > 0)?obj.getSafetyEquipmentFileNames()[i]:null;
								} 
							    updateStmt.setString(k++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
							    updateStmt.setString(k++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
							    updateStmt.setString(k++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
							    updateStmt.setString(k++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
							    updateStmt.setString(k++,docFileName);	
							    
							    updateStmt.setString(k++,(obj.getInspecting_officials().length > 0)?obj.getInspecting_officials()[i]:null);
							    updateStmt.setString(k++,DateParser.parse((obj.getLast_inspection_dates().length > 0)?obj.getLast_inspection_dates()[i]:null));
							    updateStmt.setString(k++,DateParser.parse((obj.getNext_inspection_dues().length > 0)?obj.getNext_inspection_dues()[i]:null));
							    
							    updateStmt.setString(k++,(obj.getSafety_equipment_ids()[i]));
							    updateStmt.addBatch();
						    }
						} else {
						    int p = 1;
						    if( obj.getSafety_equipment_numbers().length > 0 && !StringUtils.isEmpty(obj.getSafety_equipment_numbers()[i])) {
							    MultipartFile file = obj.getSafetyEquipmentFile()[i];
								files.add(file);
							    insertStmt.setString(p++,(obj.getContract_id_fk()));
							    insertStmt.setString(p++,(obj.getSafety_equipment_numbers().length > 0)?obj.getSafety_equipment_numbers()[i]:null);
							    insertStmt.setString(p++,(obj.getSafety_equipment_details().length > 0)?obj.getSafety_equipment_details()[i]:null);
							    insertStmt.setString(p++,DateParser.parse((obj.getValidity_dates().length > 0)?obj.getValidity_dates()[i]:null));
							    insertStmt.setString(p++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
							    insertStmt.setString(p++,null);	
							    
							    insertStmt.setString(p++,(obj.getInspecting_officials().length > 0)?obj.getInspecting_officials()[i]:null);
							    insertStmt.setString(p++,DateParser.parse((obj.getLast_inspection_dates().length > 0)?obj.getLast_inspection_dates()[i]:null));
							    insertStmt.setString(p++,DateParser.parse((obj.getNext_inspection_dues().length > 0)?obj.getNext_inspection_dues()[i]:null));
							    					    
							    insertStmt.addBatch();
						    }
						}
					}
				}
				
				int[] updateCount = updateStmt.executeBatch();
				
				
				int[] insertCount = insertStmt.executeBatch();
				
				
				List<Integer> generatedIds = new ArrayList<Integer>();
				try (ResultSet rs = insertStmt.getGeneratedKeys()) {
			        if (rs != null) {
			            while (rs.next()) {
			                int generatedId = rs.getInt(1);
			                generatedIds.add(generatedId);
			            }
			        }
			        DBConnectionHandler.closeJDBCResoucrs(null, null, rs);
			    } catch (Exception e) {
			    	throw new Exception(e.getMessage());
			    }
				String updateAttachmentQry = "update safety_equipment set attachment =? where safety_equipment_id =?";
				
				for (int j = 0; j < generatedIds.size(); j++) {
					stmt = con.prepareStatement(updateAttachmentQry);
					int k =1;					
					if( obj.getSafetyEquipmentFileNames().length > 0 && !StringUtils.isEmpty(obj.getSafetyEquipmentFileNames()[j])) {
						String docFileName = null;
					    MultipartFile file = files.get(j);
						if (null != file && !file.isEmpty()){
							String fileName = file.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							String fileName_new = "Safety_Equipment-"+generatedIds.get(j).toString()+"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							docFileName = fileName_new;
							FileUploads.singleFileSaving(file, saveDirectory, docFileName);
						} else {
							docFileName  = null;
						} 
						
						stmt.setString(k++,docFileName);					
						stmt.setString(k++,generatedIds.get(j).toString());
						stmt.executeUpdate();
					}
					DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
				}
				
					
				if(updateCount.length > 0 || insertCount.length > 0) {
					flag = true;
				}

				DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
				con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
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
	public List<SafetyEquipment> getProjectsListForSafetyEquipmentForm(SafetyEquipment obj)throws Exception{
		List<SafetyEquipment> objsList = null;
		try {
			String qry ="select project_id as project_id_fk,project_name from project order by project_id asc ";
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

	@Override
	public List<SafetyEquipment> getSafetyEquipmentExportList(SafetyEquipment obj) throws Exception {
		List<SafetyEquipment> objsList = null;
		try {
			String qry = "select safety_equipment_id,contract_id_fk,c.contract_name, safety_equipment_number,safety_equipment_detail, "
					+ "DATE_FORMAT(validity_date,'%d-%m-%Y') AS validity_date, "
					+ "inspecting_official,DATE_FORMAT(last_inspection_date,'%d-%m-%Y') AS last_inspection_date,DATE_FORMAT(next_inspection_due,'%d-%m-%Y') AS next_inspection_due,s.remarks "
					+ "from safety_equipment s "  
					+"left join contract c on  s.contract_id_fk = c.contract_id  where safety_equipment_id is not null";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			qry = qry +" ORDER BY contract_id_fk,last_inspection_date DESC";

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
	public List<SafetyEquipment> getWorkListForSafetyEquipmentForm(SafetyEquipment obj) throws Exception {
		List<SafetyEquipment> objsList = new ArrayList<SafetyEquipment>();
		try {
			String qry = "select work_id as work_id_fk ,work_name,work_short_name,project_id_fk,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<SafetyEquipment> getContractsListForSafetyEquipmentForm(SafetyEquipment obj) throws Exception {
		List<SafetyEquipment> objsList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk "
					+ "from contract "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<SafetyEquipment>(SafetyEquipment.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
