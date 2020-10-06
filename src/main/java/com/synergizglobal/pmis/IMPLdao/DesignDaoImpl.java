package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.DesignDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FOB;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.User;

@Repository
public class DesignDaoImpl implements DesignDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Design> design(Design obj)throws Exception{
		List<Design> objsList = null;
		try {
			String qry ="select design_id,d.contract_id_fk,d.structure_type_fk,d.drawing_type_fk,d.department_id_fk,d.hod,d.dy_hod,d.structure_type_fk,d.contractor_drawing_no,"
					+ "d.mrvc_drawing_no,d.division_drawing_no,d.drawing_title,d.hq_drawing_no " + 
					"from design d "
					+ "LEFT JOIN contract c on d.contract_id_fk = c.contract_id "
					
					+ "LEFT JOIN user u on d.hod = u.user_id where design_id is not null"
					;
				
			int arrSize = 0;
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
			Object[] pValues = new Object[arrSize];
			int i = 0;
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
			String qry ="select revision_status as as_built_status from revision_status";
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
			String qry ="select c.work_id_fk,w.project_id_fk,d.contract_id_fk,d.department_id_fk,d.consultant_contract_id_fk,d.proof_consultant_contract_id_fk,d.hod,d.dy_hod," + 
					"d.prepared_by_id_fk,d.structure_type_fk,d.component,d.drawing_type_fk,d.contractor_drawing_no,d.mrvc_drawing_no,d.division_drawing_no" + 
					",d.hq_drawing_no,d.drawing_title,d.planned_start,d.planned_finish,d.revision,d.consultant_submission,d.mrvc_reviewed,d.divisional_approval," + 
					"d.hq_approval,d.gfc_released,d.as_built_status,d.as_built_date,d.remarks from design d " + 
					"LEFT OUTER JOIN contract c ON d.contract_id_fk = c.contract_id "
					+"LEFT OUTER JOIN work w  ON c.work_id_fk  =  w.work_id " + 
					"LEFT OUTER JOIN project p  ON w.project_id_fk  =  p.project_id "
					+ "where design_id is not null" ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDesign_id())) {
				qry = qry + " and design_id = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDesign_id())) {
				pValues[i++] = obj.getDesign_id();
			}
			dObj = (Design)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Design>(Design.class));
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return dObj;
	}

	@Override
	public boolean addDesign(Design obj) throws Exception {
		boolean flag = false;
		try{
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String qry = "INSERT INTO design (contract_id_fk,department_id_fk,hod,dy_hod,prepared_by_id_fk,consultant_contract_id_fk,proof_consultant_contract_id_fk,"
					+ "structure_type_fk,component,drawing_type_fk,contractor_drawing_no,mrvc_drawing_no,division_drawing_no,hq_drawing_no,drawing_title"
					+ "planned_start,planned_finish,revision,consultant_submission,mrvc_reviewed,divisional_approval,hq_approval"
					+ "gfc_released,as_built_status,as_built_date,remarks) "
					+ "VALUES(:contract_id_fk,:department_id_fk,:hod,:dy_hod,:prepared_by_id_fk,:consultant_contract_id_fk,:proof_consultant_contract_id_fk,:structure_type_fk"
					+ ",:component,:drawing_type_fk,:contractor_drawing_no,:mrvc_drawing_no,:division_drawing_no,:hq_drawing_no,:drawing_title,:planned_start,:planned_finish,"
					+ ":revision,:consultant_submission,:mrvc_reviewed,:divisional_approval,:hq_approval,:gfc_released,:as_built_status,:as_built_date,:remarks)";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag && !StringUtils.isEmpty(obj.getRevisions()) && obj.getRevisions().length > 0) {
				String[] designRevision = obj.getRevisions();
				String[] consultantSubmission = obj.getConsultant_submissions();
				String[] mrvcReviewed = obj.getMrvc_revieweds();
				String[] divisionalApproval = obj.getDivisional_approvals();
				String[] hqApproval = obj.getHq_approvals();
				String[] revisionStatus = obj.getRevision_status_fks();
				String[] remarks = obj.getRemarkss();

				String qryDesignRevision = "INSERT INTO design_revisions (design_id,revision,consultant_submission,mrvc_reviewed,divisional_approval"
						+ "hq_approval,revision_status_fk,remarks) VALUES(?,?,?,?,?,?,?,?)";
				int[] counts = jdbcTemplate.batchUpdate(qryDesignRevision,
			            new BatchPreparedStatementSetter() {

							@Override
							public void setValues(PreparedStatement ps, int i) throws SQLException {
								if(!StringUtils.isEmpty(obj.getDesign_id()) && obj.getDesign_id().length() > 0) {
									   ps.setString(1, obj.getDesign_id());
								}else {
									   ps.setString(1, null);
								}	
								if(!StringUtils.isEmpty(designRevision) && designRevision.length > 0) {
									   ps.setString(2,designRevision[i]);
								}else {
									   ps.setString(2, null);
								}	
								if(!StringUtils.isEmpty(consultantSubmission) && consultantSubmission.length > 0) {
									   ps.setString(3,consultantSubmission[i]);
								}else {
									   ps.setString(3, null);
								}	
								if(!StringUtils.isEmpty(mrvcReviewed) && mrvcReviewed.length > 0) {
									   ps.setString(4,mrvcReviewed[i]);
								}else {
									   ps.setString(4, null);
								}
								if(!StringUtils.isEmpty(divisionalApproval) && divisionalApproval.length > 0) {
									   ps.setString(5,divisionalApproval[i]);
								}else {
									   ps.setString(5, null);
								}
								if(!StringUtils.isEmpty(hqApproval) && hqApproval.length > 0) {
									   ps.setString(6,hqApproval[i]);
								}else {
									   ps.setString(6, null);
								}
								if(!StringUtils.isEmpty(revisionStatus) && revisionStatus.length > 0) {
									   ps.setString(7,revisionStatus[i]);
								}else {
									   ps.setString(7, null);
								}
								if(!StringUtils.isEmpty(remarks) && remarks.length > 0) {
									   ps.setString(8,remarks[i]);
								}else {
									   ps.setString(8, null);
								}
							}
							@Override
							public int getBatchSize() {
								 return obj.getRevisions().length;
							}
				  });
					
				}

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
			
		return flag;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
