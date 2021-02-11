package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.SafetyReportDao;
import com.synergizglobal.pmis.model.Safety;

@Repository
public class SafetyReportDaoImpl implements SafetyReportDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Safety> getWorksListInSafetyReport(Safety obj) throws Exception {
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
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			qry = qry + " GROUP BY work_id_fk ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getContractsListInSafetyReport(Safety obj) throws Exception {
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
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			qry = qry + " GROUP BY contract_id_fk";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Safety> getSafetyReportData(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,c.contract_short_name,title,d.department_name,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,s.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,DATE_FORMAT(closure_date,'%d-%m-%Y') AS closure_date,cast(lti_hours as CHAR) as lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "DATE_FORMAT(investigation_completed,'%d-%m-%Y') AS investigation_completed,corrective_measure_short_term,"
					+ "corrective_measure_long_term,status_remark_fk,cast(compensation as CHAR) as compensation,"
					+ "DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,work_short_name,"
					+ "project_id_fk,project_name,hod_user_id_fk,designation,user_name as hod_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk <> ?";
				arrSize++;
			}
			qry = qry + " ORDER BY status_fk DESC";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
}
