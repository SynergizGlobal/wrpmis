package com.synergizglobal.pmis.IMPLdao;

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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.SafetyDao;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Safety;

@Repository
public class SafetyDaoImpl implements SafetyDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Safety> getSafetyList(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,title,d.department_name,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,s.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,DATE_FORMAT(closure_date,'%d-%m-%Y') AS closure_date,cast(lti_hours as CHAR) as lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "DATE_FORMAT(investigation_completed,'%d-%m-%Y') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,status_remark_fk,cast(compensation as CHAR) as compensation,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON s.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON s.department_fk  = d.department "
					+ "where safety_id is not null " ;
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyStatusList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select status from safety_status";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyImpactList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select impact from safety_impact";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyCategoryList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select category,short_description from safety_category";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyRootCauseList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select root_cause from safety_root_cause";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addSafety(Safety obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String safety_id = null;		
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO safety"
					+ "(contract_id_fk,hod_user_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk,category_fk,impact_fk,root_cause_fk,status_fk,"
					+ "closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,investigation_completed,corrective_measure_short_term,"
					+ "corrective_measure_long_term,compensation,payment_date,remarks,attachment) "
					+ "VALUES "
					+ "(:contract_id_fk,:hod_user_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:department_fk,:category_fk,:impact_fk,:root_cause_fk,:status_fk,:"
					+ "closure_date,:lti_hours,:equipment_impact,:people_impact,:work_impact,:committee_formed_fk,:committee_required_fk,:investigation_completed,:corrective_measure_short_term,:"
					+ "corrective_measure_long_term,:compensation,:payment_date,:remarks,:attachment)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				safety_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setSafety_id(safety_id);
				flag = true;
				
				MultipartFile file = obj.getSafetyFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
					String fileName_new = "Safety-"+obj.getSafety_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
					FileUploads.singleFileSaving(file, saveDirectory, fileName_new);
					obj.setAttachment(fileName_new);
					
					String updateQry = "UPDATE safety set attachment= :attachment where safety_id= :safety_id ";
					BeanPropertySqlParameterSource paramSource1 = new BeanPropertySqlParameterSource(obj);		
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
	public Safety getSafety(Safety obj) throws Exception {
		Safety sobj = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,w.work_short_name,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,s.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,DATE_FORMAT(closure_date,'%d-%m-%Y') AS closure_date,cast(lti_hours as CHAR) as lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "DATE_FORMAT(investigation_completed,'%d-%m-%Y') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,cast(compensation as CHAR) as compensation,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name,s.attachment "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON s.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where safety_id = ? ";
			
			int arrSize = 1;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getImpact_fk())) {
				qry = qry + " and impact_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRoot_cause_fk())) {
				qry = qry + " and root_cause_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getSafety_id();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getImpact_fk())) {
				pValues[i++] = obj.getImpact_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRoot_cause_fk())) {
				pValues[i++] = obj.getRoot_cause_fk();
			}
			sobj = (Safety)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return sobj;
	}

	@Override
	public boolean updateSafety(Safety obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		MultipartFile file = obj.getSafetyFile();
		if (null != file && !file.isEmpty()){
			String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH ;
			String fileName = file.getOriginalFilename();
			DateFormat df = new SimpleDateFormat("ddMMYY-HHmm");
			String fileName_new = "Safety-"+obj.getSafety_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
			FileUploads.singleFileSaving(file, saveDirectory, fileName_new);
			obj.setAttachment(fileName_new);	
		}
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE safety SET contract_id_fk=:contract_id_fk,hod_user_id_fk=:hod_user_id_fk,title=:title,description=:description,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,department_fk=:department_fk,category_fk=:category_fk,impact_fk=:impact_fk,root_cause_fk=:root_cause_fk,status_fk=:status_fk,"
					+ "closure_date=:closure_date,lti_hours=:lti_hours,equipment_impact=:equipment_impact,people_impact=:people_impact,work_impact=:work_impact,committee_formed_fk=:committee_formed_fk,committee_required_fk = :committee_required_fk,investigation_completed=:investigation_completed,corrective_measure_short_term=:corrective_measure_short_term,"
					+ "corrective_measure_long_term=:corrective_measure_long_term,compensation=:compensation,payment_date=:payment_date,remarks=:remarks,attachment=:attachment "
					+ "WHERE safety_id = :safety_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteSafety(Safety obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Safety> getDepartmentList() throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select department as department_fk,department_name from department";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getContractsListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where contract_id_fk is not null and contract_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY contract_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getDepartmentsListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT s.department_fk,department,department_name from safety s "
					+ "LEFT OUTER JOIN department d ON s.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where s.department_fk is not null and s.department_fk <> '' ";
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY s.department_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getCategoryListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT category_fk from safety s "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ " where category_fk is not null and category_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY category_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getStatusListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT status_fk from safety s "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ " where status_fk is not null and status_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY status_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getWorksListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,work_short_name from safety s "
					+ "LEFT JOIN contract c on s.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ " where work_id_fk is not null and work_id_fk <> '' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY work_id_fk ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Safety> getHODListFilterInSafety(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT s.hod_user_id_fk,designation,user_name as hod_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON s.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where s.hod_user_id_fk is not null and s.hod_user_id_fk <> '' ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and s.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and s.hod_user_id_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY s.hod_user_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getProjectsListForSafetyForm(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select project_id as project_id_fk,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getWorkListForSafetyForm(Safety obj) throws Exception {
		List<Safety> objsList = new ArrayList<Safety>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getContractsListForSafetyForm(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry ="select c.contract_id as contract_id_fk,c.contract_name,c.contract_short_name,c.work_id_fk "
					+ "from contract c "
					+ "where c.contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by c.contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getHODListForSafetyForm(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry ="select user_id as hod_user_id_fk,designation "
					+ "from user "
					+ "where user_type_fk = ? order by designation asc";
			
			Object[] pValues = new Object[] {CommonConstants.USER_TYPE_HOD};
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	

}
