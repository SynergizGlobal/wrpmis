package com.synergizglobal.pmis.IMPLdao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ProgressApprovalDao;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Activity;
@Repository
public class ProgressApprovalDaoImpl implements ProgressApprovalDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Activity> getApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry = "select progress_id,progress_date,activity_id_fk,a.scope as total_scope,a.completed as cumulative_completed,"
					+ "ap.completed_scope as actual_for_the_day,(IFNULL(a.scope,0) - IFNULL(a.completed,0)) as remaining_scope,"
					+ "attachment_url,ap.remarks,DATE_FORMAT(ap.created_date,'%d-%m-%Y') as updated_on,"
					+ "ap.created_by_user_id_fk,aph.dyhod_user_id_fk,u.user_name as updated_by,approved_or_rejected_by,"
					+ "DATE_FORMAT(approved_on,'%d-%m-%Y') as approved_on,DATE_FORMAT(rejected_on,'%d-%m-%Y') as rejected_on,approval_status_fk,"
					+ "c.work_id_fk,w.work_short_name,a.contract_id_fk,c.contract_short_name,a.component,a.component_id,structure,activity_name,updated_scope "
					+ "from approvable_activity_progress_dyhod aph "
					+ "LEFT JOIN approvable_activity_progress ap ON aph.progress_id_fk = ap.progress_id "
					+ "LEFT JOIN user u ON ap.created_by_user_id_fk = u.user_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id is not null";
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and aph.dyhod_user_id_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				qry = qry + " and ap.created_by_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				qry = qry + " and ap.approval_status_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by ap.progress_id ";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) 
			{
				if(obj.getApproval_status_fk()=="Approved")
				{
					qry = qry + " order by approved_on desc";
				}
				else if(obj.getApproval_status_fk()=="Rejected")
				{
					qry = qry + " order by rejected_on desc";
				}	
				else if(obj.getApproval_status_fk()=="Pending")
				{
					qry = qry + " order by created_date desc";
				}				
			}			
			
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getDyhod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				pValues[i++] = obj.getUpdated_by_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				pValues[i++] = obj.getApproval_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));		
			for (Activity aObj : objsList) {
				if(!StringUtils.isEmpty(aObj.getCumulative_completed())) {
					aObj.setCumulative_completed(numberFormatter.format(Double.parseDouble(aObj.getCumulative_completed())));
				}
				if(!StringUtils.isEmpty(aObj.getRemaining_scope())) {
					aObj.setRemaining_scope(numberFormatter.format(Double.parseDouble(aObj.getRemaining_scope())));
				}
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
	@Override
	public List<Activity> getWorksInApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select c.work_id_fk,work_short_name "
					+ "from approvable_activity_progress_dyhod aph "
					+ "LEFT JOIN approvable_activity_progress ap ON aph.progress_id_fk = ap.progress_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id is not null";
			
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and aph.dyhod_user_id_fk = ?";
				arrSize++;
			}
					
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				qry = qry + " and ap.created_by_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				qry = qry + " and ap.approval_status_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by c.work_id_fk order by c.work_id_fk";
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getDyhod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				pValues[i++] = obj.getUpdated_by_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				pValues[i++] = obj.getApproval_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Activity> getContractsInApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select a.contract_id_fk,contract_short_name "
					+ "from approvable_activity_progress_dyhod aph "
					+ "LEFT JOIN approvable_activity_progress ap ON aph.progress_id_fk = ap.progress_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and aph.dyhod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				qry = qry + " and ap.created_by_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				qry = qry + " and ap.approval_status_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by a.contract_id_fk order by a.contract_id_fk";
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getDyhod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				pValues[i++] = obj.getUpdated_by_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				pValues[i++] = obj.getApproval_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Activity> getStructuresInApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select structure "
					+ "from approvable_activity_progress_dyhod aph "
					+ "LEFT JOIN approvable_activity_progress ap ON aph.progress_id_fk = ap.progress_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and aph.dyhod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				qry = qry + " and ap.created_by_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				qry = qry + " and ap.approval_status_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by a.structure order by a.structure";
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getDyhod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				pValues[i++] = obj.getUpdated_by_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				pValues[i++] = obj.getApproval_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Activity> getDepartmentsInApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select c.department_fk,department_name "
					+ "from approvable_activity_progress_dyhod aph "
					+ "LEFT JOIN approvable_activity_progress ap ON aph.progress_id_fk = ap.progress_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk = d.department "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and aph.dyhod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				qry = qry + " and ap.created_by_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				qry = qry + " and ap.approval_status_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by c.department_fk order by c.department_fk";
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getDyhod_user_id_fk();
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				pValues[i++] = obj.getUpdated_by_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				pValues[i++] = obj.getApproval_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Activity> getUpdatedByListInApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select ap.created_by_user_id_fk as user_id,user_name "
					+ "from approvable_activity_progress_dyhod aph "
					+ "LEFT JOIN approvable_activity_progress ap ON aph.progress_id_fk = ap.progress_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN user u ON ap.created_by_user_id_fk = u.user_id "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and aph.dyhod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				qry = qry + " and a.structure = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				qry = qry + " and ap.created_by_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				qry = qry + " and ap.approval_status_fk = ?";
				arrSize++;
			}
			
			qry = qry + " group by ap.created_by_user_id_fk order by ap.created_by_user_id_fk";
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getDyhod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStructure())) {
				pValues[i++] = obj.getStructure();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUpdated_by_user_id_fk())) {
				pValues[i++] = obj.getUpdated_by_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getApproval_status_fk())) {
				pValues[i++] = obj.getApproval_status_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public Activity approveActivityProgress(Activity obj) throws Exception {
		Activity aObj = new Activity();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			String qry = "select progress_id,progress_date,activity_id_fk as activity_id,IFNULL(a.scope,0) as scope,IFNULL(a.completed,0) as completed,"
					+ "ap.completed_scope as actual_for_the_day,(IFNULL(a.scope,0) - IFNULL(a.completed,0)) as remaining_scope,"
					+ "attachment_url,ap.remarks,DATE_FORMAT(ap.created_date,'%d-%m-%Y') as updated_on,"
					+ "ap.created_by_user_id_fk,approved_or_rejected_by,u.user_name as updated_by,"
					+ "DATE_FORMAT(approved_on,'%d-%m-%Y') as approved_on,DATE_FORMAT(rejected_on,'%d-%m-%Y') as rejected_on,approval_status_fk,"
					+ "c.work_id_fk,w.work_short_name,a.contract_id_fk,c.contract_short_name,a.component,a.component_id,structure,activity_name,updated_scope "
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN user u ON ap.created_by_user_id_fk = u.user_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id  = ?";			
			
			Object[] pValues = new Object[] {obj.getProgress_id()};	
			
			List<Activity> objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
			for (Activity activity : objsList) {
				aObj = activity;	
			}
			
			if(!StringUtils.isEmpty(aObj)) {
				float scope = Float.parseFloat(aObj.getScope());
				float completed = Float.parseFloat(aObj.getCompleted());
				float remaining = Float.parseFloat(aObj.getRemaining_scope());
				float actual_for_the_day = Float.parseFloat(aObj.getActual_for_the_day()==null?"0":aObj.getActual_for_the_day());
				
				
				
				if((completed+actual_for_the_day) <= scope) {
					String updateQry = "UPDATE activities SET completed = ? + ?";	
					int arrSize = 3;
					
					if(completed == 0) {
						updateQry = updateQry + ", actual_start = ?";
						arrSize++;
					}
					
					
					if(scope == (completed+actual_for_the_day)) {
						updateQry = updateQry + ", planned_finish = ?";
						arrSize++;
					}	
					
					if(aObj.getUpdated_scope()!=null && Float.parseFloat(aObj.getUpdated_scope())>0)
					{
						updateQry = updateQry + ", Scope = ?";
						arrSize++;						
					}
					
					updateQry = updateQry + " WHERE activity_id = ?";
					
					pValues = new Object[arrSize];
					int i = 0;			
					pValues[i++] = aObj.getCompleted();			
					pValues[i++] = aObj.getActual_for_the_day();
					if(completed == 0) {
						pValues[i++] = aObj.getProgress_date();
					}
					if(scope == (completed+actual_for_the_day)) {
						pValues[i++] = aObj.getProgress_date();
					}
					
					if(aObj.getUpdated_scope()!=null && Float.parseFloat(aObj.getUpdated_scope())>0)
					{
						pValues[i++] = aObj.getUpdated_scope();
					}
					
					pValues[i++] = aObj.getActivity_id();
					
					int count = jdbcTemplate.update( updateQry, pValues);			
					if(count > 0) {
						jdbcTemplate.update( "UPDATE approvable_activity_progress set approval_status_fk = ?,approved_or_rejected_by = ?, approved_on = CURRENT_TIMESTAMP where progress_id = ?",
								new Object[]{"Approved",obj.getDyhod_user_id_fk(),aObj.getProgress_id()});	
						
						String pQry = "INSERT INTO activity_progress(progress_date,activity_id_fk,completed_scope,remarks,created_by_user_id_fk,approval_datails_id_fk)"
								+ "SELECT progress_date,activity_id_fk,completed_scope,remarks,created_by_user_id_fk,progress_id "
								+ "FROM approvable_activity_progress "
								+ "WHERE progress_id = ?";
						jdbcTemplate.update( pQry,new Object[]{aObj.getProgress_id()});
						
						aObj.setMessage_flag(true);
						aObj.setMessage("Activity progress approved.");
					}else {
						aObj.setMessage_flag(false);
						aObj.setMessage("Please try again after sometime.");
					}
				}else{
					aObj.setMessage_flag(false);
					aObj.setMessage("Acceptable progress is "+remaining+". But here actual for the day is "+actual_for_the_day);
				}
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			aObj.setMessage_flag(false);
			aObj.setMessage("Please try again after sometime.");
			throw new Exception(e.getMessage());
		}
		return aObj;
	}
	
	@Override
	public Activity rejectActivityProgress(Activity obj) throws Exception {
		Activity aObj = new Activity();
		try {
			int c = jdbcTemplate.update( "UPDATE approvable_activity_progress set approval_status_fk = ?,approved_or_rejected_by = ?, rejected_on = CURRENT_TIMESTAMP where progress_id = ?",
						new Object[]{"Rejected",obj.getDyhod_user_id_fk(),obj.getProgress_id()});	
			if(c > 0) {
				aObj.setMessage_flag(true);
				aObj.setMessage("Activity progress rejected.");
			}else {
				aObj.setMessage_flag(false);
				aObj.setMessage("Please try again after sometime.");
			}
		}catch(Exception e){ 
			aObj.setMessage_flag(false);
			aObj.setMessage("Please try again after sometime.");
			throw new Exception(e.getMessage());
		}
		return aObj;
	}


	@Override
	public Activity approveMultipleActivityProgress(Activity obj) throws Exception {
		Activity aObj = new Activity();
		try {
			List<Activity> approvableList = new ArrayList<Activity>();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProgress_id())) {
				String qry = "select progress_id,progress_date,activity_id_fk as activity_id,IFNULL(a.scope,0) as scope,IFNULL(a.completed,0) as completed,"
						+ "ap.completed_scope as actual_for_the_day,(IFNULL(a.scope,0) - IFNULL(a.completed,0)) as remaining_scope,"
						+ "attachment_url,ap.remarks,DATE_FORMAT(ap.created_date,'%d-%m-%Y') as updated_on,"
						+ "ap.created_by_user_id_fk,approved_or_rejected_by,u.user_name as updated_by,"
						+ "DATE_FORMAT(approved_on,'%d-%m-%Y') as approved_on,DATE_FORMAT(rejected_on,'%d-%m-%Y') as rejected_on,approval_status_fk,"
						+ "c.work_id_fk,w.work_short_name,a.contract_id_fk,c.contract_short_name,a.component,a.component_id,structure,activity_name "
						+ "from approvable_activity_progress ap "
						+ "LEFT JOIN user u ON ap.created_by_user_id_fk = u.user_id "
						+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
						+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
						+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
						+ "where progress_id  in (";
				String placeholders = "";
				String progress_id = obj.getProgress_id().replaceAll("'", "");
				String[] progress_ids = progress_id.split(",");
				for(int p=0;p<progress_ids.length;p++) {
					if(placeholders.length() > 0) {
						placeholders = placeholders + ",";
					}else{
						placeholders = placeholders + "";
					}
					placeholders = placeholders + "?";
				}
				
				qry = qry + placeholders+")";			
				
				Object[] pValues = new Object[progress_ids.length];	
				for(int p=0;p<progress_ids.length;p++) {
					pValues[p] = progress_ids[p].trim();
				}
				List<Activity> objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));
				for (Activity activity : objsList) {
					approvableList.add(activity);	
				}
			}
			
			String successMessage = "";
			String failureMessage = "";
			int successCount = 0;
			int failureCount = 0;
			
			if(!StringUtils.isEmpty(approvableList) && approvableList.size() > 0) {
				for (Activity activity : approvableList) {
					float scope = Float.parseFloat(activity.getScope());
					float completed = Float.parseFloat(activity.getCompleted());
					float remaining = Float.parseFloat(activity.getRemaining_scope());
					float actual_for_the_day = Float.parseFloat(activity.getActual_for_the_day());
					if((completed+actual_for_the_day) <= scope) {
						String updateQry = "UPDATE activities SET completed = ? + ?";	
						int arrSize = 3;
						
						if(completed == 0) {
							updateQry = updateQry + ", actual_start = ?";
							arrSize++;
						}				
						if(scope == (completed+actual_for_the_day)) {
							updateQry = updateQry + ", planned_finish = ?";
							arrSize++;
						}				
						updateQry = updateQry + " WHERE activity_id = ?";
						
						Object[] pValues = new Object[arrSize];
						int i = 0;			
						pValues[i++] = activity.getCompleted();			
						pValues[i++] = activity.getActual_for_the_day();
						if(completed == 0) {
							pValues[i++] = activity.getProgress_date();
						}
						if(scope == (completed+actual_for_the_day)) {
							pValues[i++] = activity.getProgress_date();
						}
						pValues[i++] = activity.getActivity_id();
						
						int count = jdbcTemplate.update( updateQry, pValues);			
						if(count > 0) {							
							jdbcTemplate.update( "UPDATE approvable_activity_progress set approval_status_fk = ?,approved_or_rejected_by = ?, approved_on = CURRENT_TIMESTAMP where progress_id = ?",
									new Object[]{"Approved",obj.getDyhod_user_id_fk(),activity.getProgress_id()});	
							
							String pQry = "INSERT INTO activity_progress(progress_date,activity_id_fk,completed_scope,remarks,created_by_user_id_fk,approval_datails_id_fk)"
									+ "SELECT progress_date,activity_id_fk,completed_scope,remarks,created_by_user_id_fk,progress_id "
									+ "FROM approvable_activity_progress "
									+ "WHERE progress_id = ?";
							jdbcTemplate.update( pQry,new Object[]{activity.getProgress_id()});
							
							successCount++;
						}else {
							failureCount++;
						}
					}else{
						failureCount++;
					}
				}
				aObj.setMessage_flag(true);
				if(successCount > 0) {
					successMessage = successMessage + successCount+ " Activities approved.";
				}
				if(failureCount > 0 && !StringUtils.isEmpty(successMessage)) {
					successMessage = successMessage + "And "+failureCount+" activities failed to approve";
				}else if(failureCount > 0){
					successMessage = successMessage + failureCount+" activities failed to approve";
				}
				
				aObj.setMessage_flag(true);
				aObj.setMessage(successMessage);
			}else{
				aObj.setMessage_flag(false);
				aObj.setMessage("There is no activities to approve");
			}
		}catch(Exception e){ 
			aObj.setMessage_flag(false);
			aObj.setMessage("Please try after sometime.");
			throw new Exception(e.getMessage());
		}
		return aObj;
	}


	@Override
	public Activity rejectMultipleActivityProgress(Activity obj) throws Exception {
		Activity aObj = new Activity();
		try {
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProgress_id())) {
				String qry  = "UPDATE approvable_activity_progress set approval_status_fk = ?,approved_or_rejected_by = ?, rejected_on = CURRENT_TIMESTAMP where progress_id in(";				
				
				String progress_id = obj.getProgress_id().replaceAll("'", "");
				String[] progress_ids = progress_id.split(",");
				String placeholders = "";
				for(int p=0;p<progress_ids.length;p++) {
					if(placeholders.length() > 0) {
						placeholders = placeholders + ",";
					}else{
						placeholders = placeholders + "";
					}
					placeholders = placeholders + "?";
				}
				
				qry = qry + placeholders+")";			
				
				Object[] pValues = new Object[2+(progress_ids.length)];	
				int i = 0;
				pValues[i++] = "Rejected";
				pValues[i++] = obj.getDyhod_user_id_fk();
				for(int p=0;p<progress_ids.length;p++) {
					pValues[i++] = progress_ids[p].trim();
				}
				
				int c = jdbcTemplate.update( qry,pValues);	
				if(c > 0) {
					aObj.setMessage_flag(true);
					aObj.setMessage("You are rejected "+ progress_ids.length +" activities" );
				}else {
					aObj.setMessage_flag(false);
					aObj.setMessage("Please try after sometime.");
				}
			}
		}catch(Exception e){ 
			aObj.setMessage_flag(false);
			aObj.setMessage("Please try after sometime.");
			throw new Exception(e.getMessage());
		}
		return aObj;
	}

}
