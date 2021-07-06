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
			String qry = "select progress_id,progress_date,activity_id_fk,completed_scope,attachment_url,remarks,created_date,created_by_user_id_fk,dyhod_user_id_fk "
					+ "from approvable_activity_progress ap "
					+ "LEFT JOIN activities a ON ap.activity_id_fk = a.activity_id "
					+ "LEFT JOIN contract c ON a.contract_id_fk = c.contract_id "
					+ "where dyhod_user_id_fk = ? ";
			int arrSize = 1;			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
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
			
			Object[] pValues = new Object[arrSize];			
			
			int i = 0;
			pValues[i++] = obj.getDyhod_user_id_fk();
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Activity>(Activity.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
