package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
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
		try {
			String qry = "select progress_id,progress_date,activity_id_fk,a.scope as total_scope,a.completed as cumulative_completed,"
					+ "ap.completed_scope as actual_for_the_day,(IFNULL(a.scope,0) - IFNULL(a.completed,0)) as remaining_scope,"
					+ "attachment_url,ap.remarks,DATE_FORMAT(ap.created_date,'%d-%m-%Y') as updated_on,"
					+ "ap.created_by_user_id_fk,dyhod_user_id_fk,u.user_name as updated_by,"
					+ "DATE_FORMAT(approved_on,'%d-%m-%Y') as approved_on,DATE_FORMAT(rejected_on,'%d-%m-%Y') as rejected_on,approval_status_fk,"
					+ "c.work_id_fk,w.work_short_name,a.contract_id_fk,c.contract_short_name,a.component,a.component_id,structure,activity_name "
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN user u ON ap.created_by_user_id_fk = u.user_id "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id is not null";
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ap.dyhod_user_id_fk = ?";
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
	public List<Activity> getWorksInApprovableActivities(Activity obj) throws Exception {
		List<Activity> objsList = null;
		try {
			String qry = "select c.work_id_fk,work_short_name "
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id is not null";
			
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ap.dyhod_user_id_fk = ?";
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
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w ON c.work_id_fk = w.work_id "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ap.dyhod_user_id_fk = ?";
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
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ap.dyhod_user_id_fk = ?";
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
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk = d.department "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ap.dyhod_user_id_fk = ?";
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
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "LEFT JOIN user u ON ap.created_by_user_id_fk = u.user_id "
					+ "where progress_id is not null";
					
			int arrSize = 0;			
			
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ap.dyhod_user_id_fk = ?";
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
		try {
			String qry = "select progress_id,progress_date,activity_id_fk as activity_id,IFNULL(a.scope,0) as scope,IFNULL(a.completed,0) as completed,"
					+ "ap.completed_scope as actual_for_the_day,(IFNULL(a.scope,0) - IFNULL(a.completed,0)) as remaining_scope,"
					+ "attachment_url,ap.remarks,DATE_FORMAT(ap.created_date,'%d-%m-%Y') as updated_on,"
					+ "ap.created_by_user_id_fk,dyhod_user_id_fk,u.user_name as updated_by,"
					+ "DATE_FORMAT(approved_on,'%d-%m-%Y') as approved_on,DATE_FORMAT(rejected_on,'%d-%m-%Y') as rejected_on,approval_status_fk,"
					+ "c.work_id_fk,w.work_short_name,a.contract_id_fk,c.contract_short_name,a.component,a.component_id,structure,activity_name "
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
				float actual_for_the_day = Float.parseFloat(aObj.getActual_for_the_day());
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
					pValues[i++] = aObj.getActivity_id();
					
					int count = jdbcTemplate.update( updateQry, pValues);			
					if(count > 0) {
						jdbcTemplate.update( "UPDATE approvable_activity_progress set approval_status_fk = ?, approved_on = CURRENT_TIMESTAMP where progress_id = ?",
								new Object[]{"Approved",aObj.getProgress_id()});	
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
		}catch(Exception e){ 
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
			int c = jdbcTemplate.update( "UPDATE approvable_activity_progress set approval_status_fk = ?, rejected_on = CURRENT_TIMESTAMP where progress_id = ?",
						new Object[]{"Rejected",obj.getProgress_id()});	
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

}
