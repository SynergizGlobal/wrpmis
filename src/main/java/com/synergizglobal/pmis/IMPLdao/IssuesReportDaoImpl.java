package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.IssuesReportDao;
import com.synergizglobal.pmis.model.Issue;

@Repository
public class IssuesReportDaoImpl implements IssuesReportDao {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Issue> getWorksListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,w.work_short_name from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
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
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			qry = qry + " GROUP BY work_id_fk ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getContractsListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getHODListInIssuesReport(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name,hod_user_id_fk,designation,user_name as hod_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where hod_user_id_fk is not null and hod_user_id_fk <> '' ";
					
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
			
			qry = qry + " GROUP BY hod_user_id_fk";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Issue> getPendingIssues(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select issue_id,contract_id_fk,d.department_name,activity,c.contract_short_name,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,other_organization,i.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,"
					+ "i.attachment,i.zonal_railway_fk,r.railway_name,c.contractor_id_fk,ctr.contractor_id,ctr.contractor_name,"
					+ "d.department_name,hod_user_id_fk,designation,user_name as hod_name,DATEDIFF(NOW(), date) as pending_since,DATE_FORMAT(date,'%d-%m-%Y') AS date "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN contractor ctr ON c.contractor_id_fk= ctr.contractor_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON i.department_fk  = d.department "
					+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk COLLATE utf8mb4_unicode_ci = r.railway_id "
					+ "where issue_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk <> ?";
				arrSize++;
			}
			qry = qry + " ORDER BY i.date,location ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
}
