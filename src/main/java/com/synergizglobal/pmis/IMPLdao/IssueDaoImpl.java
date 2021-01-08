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

import com.synergizglobal.pmis.Idao.IssueDao;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Issue;

@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Override
	public List<Issue> getIssuesList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select issue_id,contract_id_fk,d.department_name,activity,c.contract_short_name,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,i.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name,i.attachment,i.zonal_railway_fk,r.railway_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
				qry = qry + " and i.department_fk = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
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
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String issue_id = null;	
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO issue"
					+ "(contract_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,department_fk," 
					+"priority_fk,category_fk,status_fk,corrective_measure,resolved_date,escalated_to,remarks,attachment,zonal_railway_fk) "
					+ "VALUES "
					+ "(:contract_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:department_fk,:" 
					+ "priority_fk,:category_fk,:status_fk,:corrective_measure,:resolved_date,:escalated_to,:remarks,:attachment,:zonal_railway_fk)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = template.update(qry, paramSource, keyHolder);			
			if(count > 0) {
				issue_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setIssue_id(issue_id);
				flag = true;
				
				MultipartFile file = obj.getIssueFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
					String fileName_new = "Issue-"+obj.getIssue_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
					FileUploads.singleFileSaving(file, saveDirectory, fileName_new);
					obj.setAttachment(fileName_new);
					
					String updateQry = "UPDATE issue set attachment= :attachment where issue_id= :issue_id ";
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
	public Issue getIssue(Issue obj) throws Exception {
		Issue iobj = null;
		try {
			String qry = "select issue_id,contract_id_fk,activity,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,i.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name,i.attachment,i.zonal_railway_fk,r.railway_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk COLLATE utf8mb4_unicode_ci = r.railway_id "
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
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE issue SET "
					+ "contract_id_fk=:contract_id_fk,title=:title,description=:description,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,department_fk=:department_fk,"  
					+ "priority_fk=:priority_fk,category_fk=:category_fk,status_fk=:status_fk,corrective_measure=:corrective_measure,resolved_date=:resolved_date,escalated_to=:escalated_to,remarks=:remarks,attachment=:attachment,zonal_railway_fk=:zonal_railway_fk "
					+ "where issue_id = :issue_id" ;		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
				MultipartFile file = obj.getIssueFile();
				if (null != file && !file.isEmpty() && file.getSize() > 0){
					String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH ;
					String fileName = file.getOriginalFilename();
					DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
					String fileName_new = "Issue-"+obj.getIssue_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
					FileUploads.singleFileSaving(file, saveDirectory, fileName_new);
					obj.setAttachment(fileName_new);
					
					String updateQry = "UPDATE issue set attachment= :attachment where issue_id= :issue_id ";
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
	public boolean deleteIssue(Issue obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Issue> getDepartmentList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "select department as department_fk,department_name from department";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getRailwayList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT railway_id,railway_name from railway";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Issue> getContractsListFilter(Issue obj) throws Exception {
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and i.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			qry = qry + " GROUP BY contract_id_fk";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getDepartmentsListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT i.department_fk,department,department_name from issue i "
					+ "LEFT OUTER JOIN department d ON i.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where i.department_fk is not null and i.department_fk <> '' ";
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
				qry = qry + " and i.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			qry = qry + " GROUP BY i.department_fk";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getCategoryListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT category_fk from issue i "
			+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
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
				qry = qry + " and i.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			
			qry = qry + " GROUP BY category_fk ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getStatusListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT status_fk from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "where status_fk is not null and status_fk <> '' ";
			
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
				qry = qry + " and i.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			
			qry = qry + " GROUP BY status_fk ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getWorksListFilter(Issue obj) throws Exception {
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and i.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			
			qry = qry + " GROUP BY work_id_fk ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getResponsiblePersonsListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT responsible_person,u.user_name from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on i.responsible_person = u.designation "
					+ "where responsible_person is not null and responsible_person <> '' ";
			
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
				qry = qry + " and i.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				pValues[i++] = obj.getCategory_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				pValues[i++] = obj.getStatus_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				pValues[i++] = obj.getResponsible_person();
			}
			
			qry = qry + " GROUP BY responsible_person ";
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getProjectsListForIssueForm(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry ="select project_id as project_id_fk,project_name from project order by project_id asc ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getWorkListForIssueForm(Issue obj) throws Exception {
		List<Issue> objsList = new ArrayList<Issue>();
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Issue> getContractsListForIssueForm(Issue obj) throws Exception {
		List<Issue> objsList = null;
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
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
				
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

}
