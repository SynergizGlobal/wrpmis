package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.DesignDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Risk;

@Repository
public class DesignDaoImpl implements DesignDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Design> getDesigns(Design obj)throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select design_id,c.work_id_fk,w.project_id_fk,w.work_name,c.contract_name,c.contract_short_name,d.contract_id_fk,d.department_id_fk,d.consultant_contract_id_fk,d.proof_consultant_contract_id_fk,d.hod,d.dy_hod," + 
					"d.prepared_by_id_fk,d.structure_type_fk,d.component,d.drawing_type_fk,d.contractor_drawing_no,d.mrvc_drawing_no,d.division_drawing_no,DATE_FORMAT(d.query_replied_to_hq,'%d-%m-%Y') AS query_replied_to_hq,"
					+ "DATE_FORMAT(d.query_raised_by_hq,'%d-%m-%Y') AS query_raised_by_hq,DATE_FORMAT(d.query_replied_to_division,'%d-%m-%Y') AS query_replied_to_division,DATE_FORMAT(d.query_raised_by_division,'%d-%m-%Y') AS query_raised_by_division" + 
					",d.hq_drawing_no,d.drawing_title,DATE_FORMAT(d.planned_start,'%d-%m-%Y') AS planned_start,DATE_FORMAT(d.planned_finish,'%d-%m-%Y') AS planned_finish,d.revision,clearance_to_consultant,DATE_FORMAT(d.submitted_to_division,'%d-%m-%Y') AS submitted_to_division,"
					+ "DATE_FORMAT(d.submitted_to_hq,'%d-%m-%Y') AS submitted_to_hq,crs_sanction_fk,DATE_FORMAT(d.consultant_submission,'%d-%m-%Y') AS consultant_submission,DATE_FORMAT(d.mrvc_reviewed,'%d-%m-%Y') AS mrvc_reviewed,DATE_FORMAT(d.divisional_approval,'%d-%m-%Y') AS divisional_approval,"
					+ "DATE_FORMAT(d.submitted_for_crs_sanction,'%d-%m-%Y') AS submitted_for_crs_sanction,DATE_FORMAT(d.query_raised_for_crs_sanction,'%d-%m-%Y') AS query_raised_for_crs_sanction,"
					+ "DATE_FORMAT(d.query_replied_for_crs_sanction,'%d-%m-%Y') AS query_replied_for_crs_sanction,DATE_FORMAT(d.crs_sanction_approved,'%d-%m-%Y') AS crs_sanction_approved," + 
					 "DATE_FORMAT(d.hq_approval,'%d-%m-%Y') AS hq_approval,DATE_FORMAT(d.gfc_released,'%d-%m-%Y') AS gfc_released,d.as_built_status,DATE_FORMAT(d.as_built_date,'%d-%m-%Y') AS as_built_date,d.remarks,d.attachment,d.divisional_submission_fk,d.hq_submission_fk "
					+ "from design d "  
					+"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id "
					+"LEFT OUTER JOIN work w  ON c.work_id_fk  =  w.work_id " 
					+"LEFT OUTER JOIN project p  ON w.project_id_fk  =  p.project_id "
					+ " where design_id is not null";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			//qry = qry + " limit 10";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getDesignsList(Design obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select design_id,c.work_id_fk,w.project_id_fk,w.work_name,c.contract_name,c.contract_short_name,d.contract_id_fk,d.department_id_fk,d.consultant_contract_id_fk,d.proof_consultant_contract_id_fk,d.hod,d.dy_hod," + 
					"d.prepared_by_id_fk,d.structure_type_fk,d.component,d.drawing_type_fk,d.contractor_drawing_no,d.mrvc_drawing_no,d.division_drawing_no" + 
					",d.hq_drawing_no,d.drawing_title,DATE_FORMAT(d.planned_start,'%d-%m-%Y') AS planned_start,DATE_FORMAT(d.planned_finish,'%d-%m-%Y') AS planned_finish,d.revision,clearance_to_consultant,"
					+ "DATE_FORMAT(d.consultant_submission,'%d-%m-%Y') AS consultant_submission,DATE_FORMAT(d.mrvc_reviewed,'%d-%m-%Y') AS mrvc_reviewed,DATE_FORMAT(d.divisional_approval,'%d-%m-%Y') AS divisional_approval," + 
					"DATE_FORMAT(d.hq_approval,'%d-%m-%Y') AS hq_approval,DATE_FORMAT(d.gfc_released,'%d-%m-%Y') AS gfc_released,d.as_built_status,DATE_FORMAT(d.as_built_date,'%d-%m-%Y') AS as_built_date,d.remarks,d.attachment,d.divisional_submission_fk,d.hq_submission_fk "
					+ "from design d "  
					+"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id "
					+"LEFT OUTER JOIN work w  ON c.work_id_fk  =  w.work_id " 
					+"LEFT OUTER JOIN project p  ON w.project_id_fk  =  p.project_id "
					+ " where design_id is not null";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (contract_id_fk like ? or c.contract_name like ? or d.drawing_title like ? or d.structure_type_fk like ?"
						+ " or drawing_type_fk like ? or d.contractor_drawing_no like ? or d.mrvc_drawing_no like ? or d.division_drawing_no like ? or d.hq_drawing_no like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " ORDER BY design_id ASC limit ?,?";
				arrSize++;
				arrSize++;
			}	
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Design obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(*) as total_records from design d "  
					+"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id "
					+"LEFT OUTER JOIN work w  ON c.work_id_fk  =  w.work_id " 
					+"LEFT OUTER JOIN project p  ON w.project_id_fk  =  p.project_id "
					+ " where design_id is not null";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (contract_id_fk like ? or c.contract_name like ? or d.drawing_title like ? or d.structure_type_fk like ?"
						+ " or drawing_type_fk like ? or d.contractor_drawing_no like ? or d.mrvc_drawing_no like ? or d.division_drawing_no like ? or d.hq_drawing_no like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			//qry = qry + " limit 10";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return totalRecords;
	}
	
	@Override
	public List<Design> structureList()throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select structure_type as structure_type_fk from structure_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Design> drawingTypeList()throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select drawing_type as drawing_type_fk from drawing_type";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	@Override
	public List<Design> getDepartmentList()throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select department as department_id_fk,department_name from department";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Design> getContractList()throws Exception{
		List<Design> objList = null;
		try {
			String qry ="select contract_id as contract_id_fk,contract_name from contract";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objList;
	}
	
	@Override
	public List<Design> getPreparedByList()throws Exception{
		List<Design> objList = null;
		try {
			String qry ="select prepared_by as prepared_by_id_fk from design_prepared_by";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objList;
	}
	
	@Override
	public List<Design> getRevisionStatuses()throws Exception{
		List<Design> objList = null;
		try {
			String qry ="select revision_status from revision_status";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objList;
	}
	
	@Override
	public List<Design> getAsBuiltStatuses()throws Exception{
		List<Design> objList = null;
		try {
			String qry ="select as_built_status from as_built_status";
				objList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objList;
	}


	@Override
	public Design getDesignDetails(Design obj)throws Exception{
		Design dObj = null;
		try {
			String qry ="select design_id,c.work_id_fk,w.project_id_fk,p.project_name,c.contract_short_name,w.work_short_name,d.contract_id_fk,d.department_id_fk,d.consultant_contract_id_fk,d.proof_consultant_contract_id_fk,d.hod,d.dy_hod," + 
					"d.prepared_by_id_fk,d.structure_type_fk,d.component,d.drawing_type_fk,d.contractor_drawing_no,d.mrvc_drawing_no,d.division_drawing_no" + 
					",d.hq_drawing_no,d.drawing_title,DATE_FORMAT(d.planned_start,'%d-%m-%Y') AS planned_start,DATE_FORMAT(d.planned_finish,'%d-%m-%Y') AS planned_finish,d.revision,clearance_to_consultant,"
					+ "DATE_FORMAT(d.consultant_submission,'%d-%m-%Y') AS consultant_submission,DATE_FORMAT(d.mrvc_reviewed,'%d-%m-%Y') AS mrvc_reviewed,DATE_FORMAT(d.divisional_approval,'%d-%m-%Y') AS divisional_approval," + 
					"DATE_FORMAT(d.hq_approval,'%d-%m-%Y') AS hq_approval,DATE_FORMAT(d.gfc_released,'%d-%m-%Y') AS gfc_released,d.as_built_status,DATE_FORMAT(d.as_built_date,'%d-%m-%Y') AS as_built_date,"
					+ "d.remarks,d.attachment,d.divisional_submission_fk,d.hq_submission_fk,DATE_FORMAT(d.submitted_to_division,'%d-%m-%Y') AS submitted_to_division,DATE_FORMAT(d.submitted_to_hq,'%d-%m-%Y') AS submitted_to_hq "
					+ ",DATE_FORMAT(d.query_raised_by_division,'%d-%m-%Y') AS query_raised_by_division,DATE_FORMAT(d.query_replied_to_division,'%d-%m-%Y') AS query_replied_to_division,"
					+ "DATE_FORMAT(d.query_raised_by_hq,'%d-%m-%Y') AS query_raised_by_hq,DATE_FORMAT(d.query_replied_to_hq,'%d-%m-%Y') AS query_replied_to_hq,"
					+ "DATE_FORMAT(d.submitted_for_crs_sanction,'%d-%m-%Y') AS submitted_for_crs_sanction,crs_sanction_fk,DATE_FORMAT(d.query_raised_for_crs_sanction,'%d-%m-%Y') AS query_raised_for_crs_sanction,"
					+ "DATE_FORMAT(d.query_replied_for_crs_sanction,'%d-%m-%Y') AS query_replied_for_crs_sanction,DATE_FORMAT(d.crs_sanction_approved,'%d-%m-%Y') AS crs_sanction_approved "
					+ "from design d "  
					+"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id "
					+"LEFT OUTER JOIN work w  ON c.work_id_fk  =  w.work_id " + 
					"LEFT OUTER JOIN project p  ON w.project_id_fk  =  p.project_id "
					+ "where design_id is not null and design_id = ?" ;
			
			dObj = (Design)jdbcTemplate.queryForObject(qry, new Object[] {obj.getDesign_id()}, new BeanPropertyRowMapper<Design>(Design.class));
			
			if(!StringUtils.isEmpty(dObj)) {
				String qry2 ="select revision,DATE_FORMAT(consultant_submission,'%d-%m-%Y') AS consultant_submission,DATE_FORMAT(mrvc_reviewed,'%d-%m-%Y') AS mrvc_reviewed,"
						+ "DATE_FORMAT(divisional_approval,'%d-%m-%Y') AS divisional_approval,DATE_FORMAT(hq_approval,'%d-%m-%Y') AS hq_approval,revision_status_fk,remarks from design_revisions where design_id_fk = ?";
				List<Design> objList = jdbcTemplate.query( qry2,new Object[] {obj.getDesign_id()}, new BeanPropertyRowMapper<Design>(Design.class));
				dObj.setDesignRevisions(objList);
			}
			
			if(!StringUtils.isEmpty(dObj)) {
				String qry2 ="select id, design_id_fk, attachment  from design_files where design_id_fk = ?";
				List<Design> objList = jdbcTemplate.query( qry2,new Object[] {obj.getDesign_id()}, new BeanPropertyRowMapper<Design>(Design.class));
				dObj.setDesignFilesList(objList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return dObj;
	}

	@Override
	public boolean addDesign(Design obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "INSERT INTO design (contract_id_fk,department_id_fk,hod,dy_hod,prepared_by_id_fk,consultant_contract_id_fk,proof_consultant_contract_id_fk,"
					+ "structure_type_fk,component,drawing_type_fk,contractor_drawing_no,mrvc_drawing_no,division_drawing_no,hq_drawing_no,drawing_title,"
					+ "planned_start,planned_finish,revision,clearance_to_consultant,consultant_submission,mrvc_reviewed,divisional_approval,hq_approval,"
					+ "gfc_released,as_built_status,as_built_date,remarks,attachment,divisional_submission_fk,hq_submission_fk,crs_sanction_fk,submitted_to_division,submitted_to_hq,query_raised_by_division,query_replied_to_division,query_raised_by_hq," + 
					"query_replied_to_hq,submitted_for_crs_sanction,query_raised_for_crs_sanction,query_replied_for_crs_sanction,crs_sanction_approved) "
					+ "VALUES(:contract_id_fk,:department_id_fk,:hod,:dy_hod,:prepared_by_id_fk,:consultant_contract_id_fk,:proof_consultant_contract_id_fk,:structure_type_fk"
					+ ",:component,:drawing_type_fk,:contractor_drawing_no,:mrvc_drawing_no,:division_drawing_no,:hq_drawing_no,:drawing_title,:planned_start,:planned_finish,"
					+ ":revision,:clearance_to_consultant,:consultant_submission,:mrvc_reviewed,:divisional_approval,:hq_approval,:gfc_released,:as_built_status,:as_built_date,:remarks,:attachment,:divisional_submission_fk,:hq_submission_fk,:crs_sanction_fk,:submitted_to_division,:submitted_to_hq,:query_raised_by_division,:query_replied_to_division"
					+ ",:query_raised_by_hq,:query_replied_to_hq,:submitted_for_crs_sanction,:query_raised_for_crs_sanction,:query_replied_for_crs_sanction,:crs_sanction_approved)";
			

			
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
		    KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);
		    //return keyHolder.getKey().intValue();

			//BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			//int count = namedParamJdbcTemplate.update(qry, paramSource);		
		    String designId = null;
			if(count > 0) {
				 designId = String.valueOf(keyHolder.getKey().intValue());
				 obj.setDesign_id(designId);
				 flag = true;
				 if(!StringUtils.isEmpty(obj.getDesignFiles()) && obj.getDesignFiles().size() > 0) {
						
						String file_insert_qry = "INSERT into  design_files ( design_id_fk, attachment) VALUES (:design_id,:attachment)";
						
						List<MultipartFile> designFiles = obj.getDesignFiles();
						for (MultipartFile multipartFile : designFiles) {
							if (null != multipartFile && !multipartFile.isEmpty()){
								String saveDirectory = CommonConstants2.DESIGN_FILE_SAVING_PATH ;
								String fileName = multipartFile.getOriginalFilename();
								DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
								String fileName_new = "Design-"+obj.getDesign_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName_new);
								
								Design fileObj = new Design();
								fileObj.setDesign_id(obj.getDesign_id());
								fileObj.setAttachment(fileName_new);
								//fileObj.setStatus(CommonConstants.ACTIVE);
								
								paramSource = new BeanPropertySqlParameterSource(fileObj);	
								namedParamJdbcTemplate.update(file_insert_qry, paramSource);
							}
						}
					}	
			}
			
			if(flag) {
				
				String qryDesignRevision = "INSERT INTO design_revisions (design_id_fk,revision,consultant_submission,mrvc_reviewed,divisional_approval,"
						+ "hq_approval,revision_status_fk,remarks) VALUES(?,?,?,?,?,?,?,?)";
				
				int[] counts = jdbcTemplate.batchUpdate(qryDesignRevision,
			            new BatchPreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps, int i) throws SQLException {
								try {
									int k = 1;
									ps.setString(k++, obj.getDesign_id());
									ps.setString(k++,(obj.getRevisions().length > 0)?obj.getRevisions()[i]:null);									
									ps.setString(k++,DateParser.parse((obj.getConsultant_submissions().length > 0)?obj.getConsultant_submissions()[i]:null));
									ps.setString(k++,DateParser.parse((obj.getMrvc_revieweds().length > 0)?obj.getMrvc_revieweds()[i]:null));
									ps.setString(k++,DateParser.parse((obj.getDivisional_approvals().length > 0)?obj.getDivisional_approvals()[i]:null));
									ps.setString(k++,DateParser.parse((obj.getHq_approvals().length > 0)?obj.getHq_approvals()[i]:null));
									ps.setString(k++,(obj.getRevision_status_fks().length > 0)?obj.getRevision_status_fks()[i]:null);
									ps.setString(k++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
								
								} catch (Exception e) {
									
								}
							}
							@Override
							public int getBatchSize() {
								int arraySize = 0;
								if(!StringUtils.isEmpty(obj.getRevisions()) && obj.getRevisions().length > 0) {
									obj.setRevisions(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevisions()));
									if(arraySize < obj.getRevisions().length) {
										arraySize = obj.getRevisions().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getConsultant_submissions()) && obj.getConsultant_submissions().length > 0) {
									obj.setConsultant_submissions(CommonMethods.replaceEmptyByNullInSringArray(obj.getConsultant_submissions()));
									if(arraySize < obj.getConsultant_submissions().length) {
										arraySize = obj.getConsultant_submissions().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getMrvc_revieweds()) && obj.getMrvc_revieweds().length > 0) {
									obj.setMrvc_revieweds(CommonMethods.replaceEmptyByNullInSringArray(obj.getMrvc_revieweds()));
									if(arraySize < obj.getMrvc_revieweds().length) {
										arraySize = obj.getMrvc_revieweds().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getDivisional_approvals()) && obj.getDivisional_approvals().length > 0) {
									obj.setDivisional_approvals(CommonMethods.replaceEmptyByNullInSringArray(obj.getDivisional_approvals()));
									if(arraySize < obj.getDivisional_approvals().length) {
										arraySize = obj.getDivisional_approvals().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getHq_approvals()) && obj.getHq_approvals().length > 0) {
									obj.setHq_approvals(CommonMethods.replaceEmptyByNullInSringArray(obj.getHq_approvals()));
									if(arraySize < obj.getHq_approvals().length) {
										arraySize = obj.getHq_approvals().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getRevision_status_fks()) && obj.getRevision_status_fks().length > 0) {
									obj.setRevision_status_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevision_status_fks()));
									if(arraySize < obj.getRevision_status_fks().length) {
										arraySize = obj.getRevision_status_fks().length;
									}
								}			
								if(!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
									obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
									if(arraySize < obj.getRemarkss().length) {
										arraySize = obj.getRemarkss().length;
									}
								}
								return arraySize;
						}
				  });
				
				
				String issueId = null;
				if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
					String issuesQry = "INSERT INTO issue(contract_id_fk,title,description,reported_by,priority_fk,category_fk,status_fk,date,department_fk)VALUES(?,?,?,?,?,?,?,CURDATE(),?)";				
					KeyHolder holder = new GeneratedKeyHolder();
					jdbcTemplate.update(new PreparedStatementCreator() {
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps = connection.prepareStatement(issuesQry, Statement.RETURN_GENERATED_KEYS);
							int i = 1;
							ps.setString(i++, !StringUtils.isEmpty(obj.getContract_id_fk())?obj.getContract_id_fk():null);
							ps.setString(i++, obj.getIssue_description());
							ps.setString(i++, obj.getIssue_description());
							ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
							ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
							ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
							ps.setString(i++, "Raised");
							ps.setString(i++, !StringUtils.isEmpty(obj.getDepartment_id_fk())?obj.getDepartment_id_fk():null);
							return ps;
						}
					}, holder);

					issueId = String.valueOf(holder.getKey().longValue());				
				}else{
					issueId = null;
				}
			}

			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
			
		return flag;
	}

	@Override
	public boolean updateDesign(Design obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try{
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "UPDATE design SET department_id_fk=:department_id_fk,hod=:hod,dy_hod=:dy_hod,prepared_by_id_fk=:prepared_by_id_fk,consultant_contract_id_fk=:consultant_contract_id_fk,proof_consultant_contract_id_fk=:proof_consultant_contract_id_fk,structure_type_fk=:structure_type_fk"
					+ ",component=:component,drawing_type_fk=:drawing_type_fk,contractor_drawing_no=:contractor_drawing_no,mrvc_drawing_no=:mrvc_drawing_no,division_drawing_no=:division_drawing_no,hq_drawing_no=:hq_drawing_no,drawing_title=:drawing_title,planned_start=:planned_start,planned_finish=:planned_finish,"
					+ "revision=:revision,clearance_to_consultant=:clearance_to_consultant,consultant_submission=:consultant_submission,mrvc_reviewed=:mrvc_reviewed,divisional_approval=:divisional_approval,hq_approval=:hq_approval,gfc_released=:gfc_released,as_built_status=:as_built_status,as_built_date=:as_built_date,remarks=:remarks,attachment=:attachment,"
					+ "divisional_submission_fk=:divisional_submission_fk,hq_submission_fk=:hq_submission_fk,submitted_to_division=:submitted_to_division,submitted_to_hq=:submitted_to_hq,  "
					+ "query_raised_by_division=:query_raised_by_division,query_replied_to_division=:query_replied_to_division,query_raised_by_hq=:query_raised_by_hq," + 
					" query_replied_to_hq=:query_replied_to_hq,crs_sanction_fk=:crs_sanction_fk,submitted_for_crs_sanction=:submitted_for_crs_sanction,"
					+ "query_raised_for_crs_sanction=:query_raised_for_crs_sanction,query_replied_for_crs_sanction=:query_replied_for_crs_sanction,"
					+ "crs_sanction_approved=:crs_sanction_approved "
					+ "WHERE design_id = :design_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
				
				String deleteFilesQry = "delete from design_files  where design_id_fk = :design_id";		 
				Design fileObj = new Design();
				fileObj.setDesign_id(obj.getDesign_id());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				namedParamJdbcTemplate.update(deleteFilesQry, paramSource);
			
				String insert_qry = "INSERT into  design_files ( design_id_fk, attachment) VALUES (:design_id,:attachment)";
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getDesignFileNames()) && obj.getDesignFileNames().length > 0 ) {
					obj.setDesignFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getDesignFileNames()));
					if(arraySize < obj.getDesignFileNames().length) {
						arraySize = obj.getDesignFileNames().length;
					}
				}
				for (int i = 0; i < arraySize; i++) {
					fileObj = new Design();
					fileObj.setDesign_id(obj.getDesign_id());
					fileObj.setAttachment(obj.getDesignFileNames()[i]);
					paramSource = new BeanPropertySqlParameterSource(fileObj);	
					namedParamJdbcTemplate.update(insert_qry, paramSource);
				}
				
				if(!StringUtils.isEmpty(obj.getDesignFiles()) && obj.getDesignFiles().size() > 0) {
					List<MultipartFile> fobFiles = obj.getDesignFiles();
					for (MultipartFile multipartFile : fobFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.DESIGN_FILE_SAVING_PATH ;
							String fileName = multipartFile.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm");
							String fileName_new = "Design-"+obj.getDesign_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName_new);
							obj.setAttachment(fileName_new);
						
							fileObj = new Design();
							fileObj.setDesign_id(obj.getDesign_id());
							fileObj.setAttachment(fileName_new);
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							namedParamJdbcTemplate.update(insert_qry, paramSource);
						}
					}
				}
			}
			if(flag) {
				String deleteQry = "DELETE from design_revisions where design_id_fk = :design_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);
			
				String qryDesignRevision = "INSERT INTO design_revisions (design_id_fk,revision,consultant_submission,mrvc_reviewed,divisional_approval,"
						+ "hq_approval,revision_status_fk,remarks) VALUES(?,?,?,?,?,?,?,?)";
				
				int[] counts = jdbcTemplate.batchUpdate(qryDesignRevision,
			            new BatchPreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps, int i) throws SQLException {
								try {
									int k = 1;
									ps.setString(k++, obj.getDesign_id());
									ps.setString(k++,(obj.getRevisions().length > 0)?obj.getRevisions()[i]:null);									
									ps.setString(k++,DateParser.parse((obj.getConsultant_submissions().length > 0)?obj.getConsultant_submissions()[i]:null));
									ps.setString(k++,DateParser.parse((obj.getMrvc_revieweds().length > 0)?obj.getMrvc_revieweds()[i]:null));
									ps.setString(k++,DateParser.parse((obj.getDivisional_approvals().length > 0)?obj.getDivisional_approvals()[i]:null));
									ps.setString(k++,DateParser.parse((obj.getHq_approvals().length > 0)?obj.getHq_approvals()[i]:null));
									ps.setString(k++,(obj.getRevision_status_fks().length > 0)?obj.getRevision_status_fks()[i]:null);
									ps.setString(k++,(obj.getRemarkss().length > 0)?obj.getRemarkss()[i]:null);
								
								} catch (Exception e) {
									
								}
							}
							@Override
							public int getBatchSize() {
								int arraySize = 0;
								if(!StringUtils.isEmpty(obj.getRevisions()) && obj.getRevisions().length > 0) {
									obj.setRevisions(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevisions()));
									if(arraySize < obj.getRevisions().length) {
										arraySize = obj.getRevisions().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getConsultant_submissions()) && obj.getConsultant_submissions().length > 0) {
									obj.setConsultant_submissions(CommonMethods.replaceEmptyByNullInSringArray(obj.getConsultant_submissions()));
									if(arraySize < obj.getConsultant_submissions().length) {
										arraySize = obj.getConsultant_submissions().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getMrvc_revieweds()) && obj.getMrvc_revieweds().length > 0) {
									obj.setMrvc_revieweds(CommonMethods.replaceEmptyByNullInSringArray(obj.getMrvc_revieweds()));
									if(arraySize < obj.getMrvc_revieweds().length) {
										arraySize = obj.getMrvc_revieweds().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getDivisional_approvals()) && obj.getDivisional_approvals().length > 0) {
									obj.setDivisional_approvals(CommonMethods.replaceEmptyByNullInSringArray(obj.getDivisional_approvals()));
									if(arraySize < obj.getDivisional_approvals().length) {
										arraySize = obj.getDivisional_approvals().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getHq_approvals()) && obj.getHq_approvals().length > 0) {
									obj.setHq_approvals(CommonMethods.replaceEmptyByNullInSringArray(obj.getHq_approvals()));
									if(arraySize < obj.getHq_approvals().length) {
										arraySize = obj.getHq_approvals().length;
									}
								}
								if(!StringUtils.isEmpty(obj.getRevision_status_fks()) && obj.getRevision_status_fks().length > 0) {
									obj.setRevision_status_fks(CommonMethods.replaceEmptyByNullInSringArray(obj.getRevision_status_fks()));
									if(arraySize < obj.getRevision_status_fks().length) {
										arraySize = obj.getRevision_status_fks().length;
									}
								}			
								if(!StringUtils.isEmpty(obj.getRemarkss()) && obj.getRemarkss().length > 0) {
									obj.setRemarkss(CommonMethods.replaceEmptyByNullInSringArray(obj.getRemarkss()));
									if(arraySize < obj.getRemarkss().length) {
										arraySize = obj.getRemarkss().length;
									}
								}
								return arraySize;
						}
				  });
				
				
				
				String issueId = null;
				if(!StringUtils.isEmpty(obj.getIs_there_issue()) && obj.getIs_there_issue().equalsIgnoreCase("yes")){
					String issuesQry = "INSERT INTO issue(contract_id_fk,title,description,reported_by,priority_fk,category_fk,status_fk,date,department_fk)VALUES(?,?,?,?,?,?,?,CURDATE(),?)";				
					KeyHolder holder = new GeneratedKeyHolder();
					jdbcTemplate.update(new PreparedStatementCreator() {
						@Override
						public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
							PreparedStatement ps = connection.prepareStatement(issuesQry, Statement.RETURN_GENERATED_KEYS);
							int i = 1;
							ps.setString(i++, !StringUtils.isEmpty(obj.getContract_id_fk())?obj.getContract_id_fk():null);
							ps.setString(i++, obj.getIssue_description());
							ps.setString(i++, obj.getIssue_description());
							ps.setString(i++, !StringUtils.isEmpty(obj.getCreated_by_user_id_fk())?obj.getCreated_by_user_id_fk():null);
							ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_priority_id())?obj.getIssue_priority_id():null);
							ps.setString(i++, !StringUtils.isEmpty(obj.getIssue_category_id())?obj.getIssue_category_id():null);
							ps.setString(i++, "Raised");
							ps.setString(i++, !StringUtils.isEmpty(obj.getDepartment_id_fk())?obj.getDepartment_id_fk():null);
							return ps;
						}
					}, holder);

					issueId = String.valueOf(holder.getKey().longValue());				
				}else{
					issueId = null;
				}
			}

			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e);
		}
			
		return flag;
	}

	@Override
	public int uploadDesigns(List<Design> designsList) throws Exception {
		int count = 0;
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String qry = "INSERT INTO design (work_id_fk,contract_id_fk,department_id_fk,hod,dy_hod,prepared_by_id_fk,consultant_contract_id_fk,proof_consultant_contract_id_fk,"
					+ "structure_type_fk,component,drawing_type_fk,contractor_drawing_no,mrvc_drawing_no,division_drawing_no,hq_drawing_no,drawing_title,"
					+ "planned_start,planned_finish,revision,clearance_to_consultant,consultant_submission,mrvc_reviewed,divisional_approval,hq_approval,"
					+ "gfc_released,as_built_status,as_built_date,remarks,attachment,divisional_submission_fk,submitted_to_division,hq_submission_fk,submitted_to_hq,submited_to_proof_consultant_fk,approval_by_proof_consultant_fk,query_raised_by_division,query_replied_to_division,query_raised_by_hq," + 
					"query_replied_to_hq,submitted_for_crs_sanction,query_raised_for_crs_sanction,query_replied_for_crs_sanction,crs_sanction_approved) "
					+ "VALUES(:work_id_fk,:contract_id_fk,:department_id_fk,:hod,:dy_hod,:prepared_by_id_fk,:consultant_contract_id_fk,:proof_consultant_contract_id_fk,:structure_type_fk"
					+ ",:component,:drawing_type_fk,:contractor_drawing_no,:mrvc_drawing_no,:division_drawing_no,:hq_drawing_no,:drawing_title,:planned_start,:planned_finish,"
					+ ":revision,:clearance_to_consultant,:consultant_submission,:mrvc_reviewed,:divisional_approval,:hq_approval,:gfc_released,:as_built_status,:as_built_date,:remarks,:attachment,"
					+ ":divisional_submission_fk,:submitted_to_division,:hq_submission_fk,:submitted_to_hq,:submited_to_proof_consultant_fk,:approval_by_proof_consultant_fk,:query_raised_by_division,:query_replied_to_division"  
					+ ",:query_raised_by_hq,:query_replied_to_hq,:submitted_for_crs_sanction,:query_raised_for_crs_sanction,:query_replied_for_crs_sanction,:crs_sanction_approved)";
			
			for (Design obj : designsList) {
				/*String department = null;
				if(!StringUtils.isEmpty(obj.getDepartment_id_fk())) { 
				  String deptqry ="SELECT department from department where department_name = ?"; 
				  department = (String)jdbcTemplate.queryForObject( deptqry,new Object[]{obj.getDepartment_id_fk()} ,String.class); 
				}
				obj.setDepartment_id_fk(department);*/
				 
				if(!StringUtils.isEmpty(obj.getPrepared_by_id_fk())) {
					String preparedByQry = "INSERT INTO design_prepared_by (prepared_by) SELECT * FROM (SELECT ?) AS tmp "
							+ "WHERE NOT EXISTS ( SELECT prepared_by FROM design_prepared_by WHERE prepared_by = ? LIMIT 1 )";
					jdbcTemplate.update( preparedByQry, new Object[] {obj.getPrepared_by_id_fk(),obj.getPrepared_by_id_fk()});
				}
				
				if(!StringUtils.isEmpty(obj.getStructure_type_fk())) {
					String stQry = "INSERT INTO structure_type (structure_type) SELECT * FROM (SELECT ?) AS tmp "
							+ "WHERE NOT EXISTS ( SELECT structure_type FROM structure_type WHERE structure_type = ? LIMIT 1 )";
					jdbcTemplate.update( stQry, new Object[] {obj.getStructure_type_fk(),obj.getStructure_type_fk()});
				}
				
				if(!StringUtils.isEmpty(obj.getDrawing_type_fk())) {
					String dtQry = "INSERT INTO drawing_type (drawing_type) SELECT * FROM (SELECT ?) AS tmp "
							+ "WHERE NOT EXISTS ( SELECT drawing_type FROM drawing_type WHERE drawing_type = ? LIMIT 1 )";
					jdbcTemplate.update( dtQry, new Object[] {obj.getDrawing_type_fk(),obj.getDrawing_type_fk()});
				}
				
				
				SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			    KeyHolder keyHolder = new GeneratedKeyHolder();
			    count = namedParamJdbcTemplate.update(qry, paramSource, keyHolder);
			    //return keyHolder.getKey().intValue();

				//BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
				//int count = namedParamJdbcTemplate.update(qry, paramSource);		
			    String designId = null;
				if(count > 0) {
					 designId = String.valueOf(keyHolder.getKey().intValue());
					 flag = true;
				}
				obj.setDesign_id(designId);
				
				
				if(flag && !StringUtils.isEmpty(obj.getDesignRevisions())) {
					String qryDesignRevision = "INSERT INTO design_revisions (design_id_fk,revision,revision_date,consultant_submission,mrvc_reviewed,divisional_approval,"
							+ "hq_approval,revision_status_fk,remarks) VALUES(?,?,?,?,?,?,?,?,?)";
					
					int[] counts = jdbcTemplate.batchUpdate(qryDesignRevision,
				            new BatchPreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps, int i) throws SQLException {
									try {										
										String revision = obj.getDesignRevisions().get(i).getRevision();
										String revision_date = obj.getDesignRevisions().get(i).getRevision_date();
										String consultant_submission = obj.getDesignRevisions().get(i).getConsultant_submission();
										String mrvc_reviewed = obj.getDesignRevisions().get(i).getMrvc_reviewed();
										String divisional_approval = obj.getDesignRevisions().get(i).getDivisional_approval();
										String hq_approval = obj.getDesignRevisions().get(i).getHq_approval();
										String revision_status_fk = obj.getDesignRevisions().get(i).getRevision_status_fk();
										String remarks = obj.getDesignRevisions().get(i).getRemarks();
										
										if(!StringUtils.isEmpty(revision_status_fk)) {
											String dtQry = "INSERT INTO revision_status (revision_status) SELECT * FROM (SELECT ?) AS tmp "
													+ "WHERE NOT EXISTS ( SELECT revision_status FROM revision_status WHERE revision_status = ? LIMIT 1 )";
											jdbcTemplate.update( dtQry, new Object[] {revision_status_fk,revision_status_fk});
										}
										
										int k = 1;
										ps.setString(k++, obj.getDesign_id());
										ps.setString(k++,!StringUtils.isEmpty(revision)?revision:null);
										ps.setString(k++,DateParser.parse(!StringUtils.isEmpty(revision_date)?revision_date:null));
										ps.setString(k++,DateParser.parse(!StringUtils.isEmpty(consultant_submission)?consultant_submission:null));
										ps.setString(k++,DateParser.parse(!StringUtils.isEmpty(mrvc_reviewed)?mrvc_reviewed:null));
										ps.setString(k++,DateParser.parse(!StringUtils.isEmpty(divisional_approval)?divisional_approval:null));
										ps.setString(k++,DateParser.parse(!StringUtils.isEmpty(hq_approval)?hq_approval:null));
										ps.setString(k++,!StringUtils.isEmpty(revision_status_fk)?revision_status_fk:null);
										ps.setString(k++,!StringUtils.isEmpty(remarks)?remarks:null);
									
									} catch (Exception e) {
										
									}
								}
								@Override
								public int getBatchSize() {
									return obj.getDesignRevisions().size();
							}
					  });
				}
				
			}
			
			count = designsList.size();
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return count;
	}

	@Override
	public List<Design> getHodListFilter(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select hod from design where hod is not null and hod <> '' ";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			qry = qry + " group by hod";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getDepartmentListFilter(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select department_id_fk,department_name from design "  
					+"LEFT OUTER JOIN department ON department_id_fk = department "
					+ " where department_id_fk is not null and department_id_fk <> '' ";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			qry = qry + " group by department_id_fk";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getContractListFilter(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select d.contract_id_fk,contract_name,contract_short_name "
					+ "from design d "  
					+"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id "
					+ " where d.contract_id_fk is not null and d.contract_id_fk <> '' ";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and d.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and d.contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			qry = qry + " group by d.contract_id_fk";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getStructureListFilter(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select structure_type_fk from design where structure_type_fk is not null and structure_type_fk <> '' ";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			qry = qry + " group by structure_type_fk";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getDrawingTypeListFilter(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select drawing_type_fk from design where drawing_type_fk is not null and drawing_type_fk <> '' ";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			qry = qry + " group by drawing_type_fk";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getWorksListFilter(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select work_id as work_id_fk,w.work_short_name from design d "
					+ "LEFT JOIN work w on d.work_id_fk = w.work_id "
					+ "where work_id_fk is not null and work_id_fk <> '' ";
				
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				qry = qry + " and department_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and hod = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				qry = qry + " and structure_type_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				qry = qry + " and drawing_type_fk = ?";
				arrSize++;
			}
			qry = qry + " group by work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_id_fk())) {
				pValues[i++] = obj.getDepartment_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure_type_fk())) {
				pValues[i++] = obj.getStructure_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDrawing_type_fk())) {
				pValues[i++] = obj.getDrawing_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getProjectsListForDesignForm(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry = "select project_id,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Design> getWorkListForDesignForm(Design obj) throws Exception {
		List<Design> objsList = new ArrayList<Design>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Design> getContractsListForDesignForm(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry ="select contract_id,contract_name,contract_short_name,work_id_fk "
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
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Design>(Design.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean saveDesignDataUploadFile(Design design) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String design_data_id = null;		
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO design_data"
					+ "(uploaded_file, status, remarks, uploaded_by_user_id_fk, uploaded_on) "
					+ "VALUES "
					+ "( :uploaded_file, :status, :remarks, :uploaded_by_user_id_fk,CURRENT_TIMESTAMP)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(design);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				design_data_id = String.valueOf(keyHolder.getKey().intValue());
				design.setDesign_data_id(design_data_id);
				flag = true;
				
				MultipartFile file = design.getDesignFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants.DESIGN_UPLOADED_FILE_SAVING_PATH ;
					String fileName = design_data_id + "_" +file.getOriginalFilename();
					FileUploads.singleFileSaving(file, saveDirectory, fileName);
					
					design.setUploaded_file(fileName);
					String updateQry = "UPDATE design_data set uploaded_file= :uploaded_file where design_data_id= :design_data_id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(design);		
					template.update(updateQry, paramSource1);
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public List<Design> getDesignUploadsList(Design obj) throws Exception {
		List<Design> objsList = null;
		try {
			String qry = "SELECT design_data_id, uploaded_file, dd.status, dd.remarks, uploaded_by_user_id_fk, DATE_FORMAT(uploaded_on,'%d-%b-%Y') as uploaded_on "
					+ "from design_data dd " 
					+ "LEFT JOIN user u ON dd.uploaded_by_user_id_fk = u.user_id "
					+ "where design_data_id is not null";
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Design>(Design.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
}
