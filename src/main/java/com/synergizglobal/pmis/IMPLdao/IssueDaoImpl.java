package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.IssueDao;
import com.synergizglobal.pmis.model.Issue;

@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Issue> getIssuesList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select issue_id,contract_id_fk,activity_id_fk,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,latitude,longitude,reported_by,responsible_person,i.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where issue_id is not null " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority_fk())) {
				qry = qry + " and priority_fk = ?";
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
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority_fk())) {
				pValues[i++] = obj.getPriority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
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

	@Override
	public List<Issue> getIssuesStatusList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select status from issue_status";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesPriorityList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select priority from issue_priority";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssuesCategoryList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select category from issue_category";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addIssue(Issue obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO issue"
					+ "(contract_id_fk,activity_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk," 
					+"priority_fk,category_fk,status_fk,corrective_measure,resolved_date,escalated_to,remarks) "
					+ "VALUES "
					+ "(:contract_id_fk,:activity_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:department_fk,:" 
					+ "priority_fk,:category_fk,:status_fk,:corrective_measure,:resolved_date,:escalated_to,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public Issue getIssue(Issue obj) throws Exception {
		Issue iobj = null;
		try {
			String qry = "select issue_id,contract_id_fk,activity_id_fk,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,latitude,longitude,reported_by,responsible_person,i.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "where issue_id = ? " ;
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority_fk())) {
				qry = qry + " and priority_fk = ?";
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
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = obj.getIssue_id();
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPriority_fk())) {
				pValues[i++] = obj.getPriority_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			
			iobj = (Issue)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return iobj;
	}

	@Override
	public boolean updateIssue(Issue obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE issue SET "
					+ "contract_id_fk=:contract_id_fk,activity_id_fk=:activity_id_fk,title=:title,description=:description,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,department_fk=:department_fk,"  
					+ "priority_fk=:priority_fk,category_fk=:category_fk,status_fk=:status_fk,corrective_measure=:corrective_measure,resolved_date=:resolved_date,escalated_to=:escalated_to,remarks=:remarks "
					+ "where issue_id = :issue_id" ;		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteIssue(Issue obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Issue> getActivityList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select strip_chart_activity_id as activity_id_fk,strip_chart_activity_name as activity_name,strip_chart_component_fk from strip_chart_activity";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getDepartmentList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select department as department_fk from department";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
