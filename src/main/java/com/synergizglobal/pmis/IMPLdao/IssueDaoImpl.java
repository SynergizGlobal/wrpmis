package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
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
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.User;

@Repository
public class IssueDaoImpl implements IssueDao {
	public static Logger logger = Logger.getLogger(IssueDaoImpl.class);
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
			String qry = "select issue_id,contract_id_fk,d.department_name,c.contract_short_name,title,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,c.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,i.zonal_railway_fk,r.railway_name,"
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,railway_name,DATE_FORMAT(assigned_date,'%d-%m-%Y') AS assigned_date,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk "
					+ "from issue i "
					+ "LEFT OUTER JOIN user u2 on i.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN user u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? or created_by_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			e.printStackTrace();
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
			String qry ="select contract_id as contract_id_fk,contract_name,contract_short_name,work_id_fk,"
					+ "hod_user_id_fk,dy_hod_user_id_fk "
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

	@Override
	public boolean addIssue(Issue obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		String issue_id = null;	
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO issue"
					+ "(contract_id_fk,title,date,location,latitude,longitude,reported_by,responsible_person," 
					+"priority_fk,category_fk,status_fk,assigned_date,corrective_measure,resolved_date,escalated_to,remarks,"
					+ "zonal_railway_fk,other_organization,escalation_date,created_by_user_id_fk,created_date) "
					+ "VALUES "
					+ "(:contract_id_fk,:title,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:" 
					+ "priority_fk,:category_fk,:status_fk,:assigned_date,:corrective_measure,:resolved_date,:escalated_to,:remarks,"
					+ ":zonal_railway_fk,:other_organization,:escalation_date,:created_by_user_id_fk,CURRENT_TIMESTAMP)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);	
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = template.update(qry, paramSource, keyHolder);			
			if(count > 0) {
				issue_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setIssue_id(issue_id);
				flag = true;
				
				/*MultipartFile file = obj.getIssueFile();
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
				}*/
				
				if(!StringUtils.isEmpty(obj.getIssueFiles()) && obj.getIssueFiles().size() > 0) {
					
					String fileQry = "INSERT INTO issue_files (file_name,issue_id_fk)VALUES(:file_name,:issue_id)";
					
					List<MultipartFile> issueFiles = obj.getIssueFiles();
					for (MultipartFile multipartFile : issueFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH + issue_id +File.separator ;
							String fileName = multipartFile.getOriginalFilename();
							/*DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							String fileName_new = "Issue-"+issue_id +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];*/
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							Issue fileObj = new Issue();
							fileObj.setFile_name(fileName);
							fileObj.setIssue_id(issue_id);
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							template.update(fileQry, paramSource);
						}
					}
				}	

				String issue_status = obj.getStatus_fk();
				String reported_by_email_id = obj.getReported_by_email_id();
				sendEmailWithIssueAlert(issue_id,issue_status,reported_by_email_id,obj.getExisting_status_fk(),null,null);
				
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
		Issue iObj = null;
		try {
			String qry = "select issue_id,contract_id_fk,title,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,c.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,c.contract_short_name,project_id_fk,project_name,i.zonal_railway_fk,r.railway_name,other_organization,"
					+ "DATE_FORMAT(escalation_date,'%d-%m-%Y') AS escalation_date,DATE_FORMAT(assigned_date,'%d-%m-%Y') AS assigned_date, "
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,i.status_fk as existing_status_fk "
					+ "from issue i "
					+ "LEFT OUTER JOIN user u2 on i.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN user u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
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
			
			iObj = (Issue)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));
			
			if(!StringUtils.isEmpty(iObj)) {				
				String filesQry ="select file_name,issue_id_fk as issue_id from issue_files where issue_id_fk = ? ";					
				List<Issue> objsList = jdbcTemplate.query( filesQry,new Object[] {obj.getIssue_id()}, new BeanPropertyRowMapper<Issue>(Issue.class));					
				if(!StringUtils.isEmpty(objsList)) {
					iObj.setIssueFilesList(objsList);
				}
				
				String fileNamesQry ="select group_concat(file_name) as attachments from issue_files where issue_id_fk = ? ";					
				Issue fileNames = jdbcTemplate.queryForObject( fileNamesQry,new Object[] {obj.getIssue_id()}, new BeanPropertyRowMapper<Issue>(Issue.class));					
				if(!StringUtils.isEmpty(fileNames)) {
					iObj.setAttachments(fileNames.getAttachments());
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id())) {
					if(obj.getUser_id().equals(iObj.getResponsible_person()) || obj.getUser_id().equals(iObj.getEscalated_to()) 
							|| obj.getUser_id().equals(iObj.getHod_user_id_fk()) || obj.getUser_id().equals(iObj.getDy_hod_user_id_fk()) 
							|| obj.getUser_role_code().equals(CommonConstants.ROLE_CODE_IT_ADMIN) ){
						iObj.setReadonlyForm(false);
					}else {
						iObj.setReadonlyForm(true);
					}
					
				}
				
				if(!StringUtils.isEmpty(obj.getMessage_id())) {
					NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
					String msgUpdateqry = "UPDATE messages SET read_time=CURRENT_TIMESTAMP where message_id = :message_id" ;	
					
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
					template.update(msgUpdateqry, paramSource);	
				}
				
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return iObj;
	}

	@Override
	public boolean updateIssue(Issue obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE issue SET "
					+ "title=:title,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,"  
					+ "priority_fk=:priority_fk,category_fk=:category_fk,status_fk=:status_fk,corrective_measure=:corrective_measure,resolved_date=:resolved_date,escalated_to=:escalated_to,"
					+ "remarks=:remarks,zonal_railway_fk=:zonal_railway_fk,other_organization=:other_organization,"
					+ "escalation_date=:escalation_date,assigned_date=:assigned_date "
					+ "where issue_id = :issue_id" ;		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
				/*MultipartFile file = obj.getIssueFile();
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
				}*/
				
				String deleteFilesQry = "delete from issue_files where issue_id_fk = :issue_id";
				
				Issue fileObj = new Issue();
				fileObj.setIssue_id(obj.getIssue_id());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				template.update(deleteFilesQry, paramSource);
				
				String fileQry = "INSERT INTO issue_files (file_name,issue_id_fk)VALUES(:file_name,:issue_id)";
				
				if(!StringUtils.isEmpty(obj.getIssueFiles()) && obj.getIssueFiles().size() > 0) {
					List<MultipartFile> issueFiles = obj.getIssueFiles();
					for (MultipartFile multipartFile : issueFiles) {
						if (null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.ISSUE_FILE_SAVING_PATH + obj.getIssue_id() +File.separator ;
							String fileName = multipartFile.getOriginalFilename();
							/*DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
							String fileName_new = "Issue-"+obj.getIssue_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];*/
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName);
							
							fileObj = new Issue();
							fileObj.setFile_name(fileName);
							fileObj.setIssue_id(obj.getIssue_id());
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							template.update(fileQry, paramSource);
						}
					}
				}	
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getIssueFileNames()) && obj.getIssueFileNames().length > 0 ) {
					obj.setIssueFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getIssueFileNames()));
					if(arraySize < obj.getIssueFileNames().length) {
						arraySize = obj.getIssueFileNames().length;
					}
				}
				
				for (int i = 0; i < arraySize; i++) {
					fileObj = new Issue();
					fileObj.setFile_name(obj.getIssueFileNames()[i]);
					fileObj.setIssue_id(obj.getIssue_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);	
					template.update(fileQry, paramSource);
				}
				
				
				String issue_id = obj.getIssue_id();
				String issue_status = obj.getStatus_fk();
				String existing_status_fk = obj.getExisting_status_fk();
				String reported_by_email_id = obj.getReported_by_email_id();
				String existing_responsible_person = obj.getExisting_responsible_person();
				String existing_escalated_to = obj.getExisting_escalated_to();
				sendEmailWithIssueAlert(issue_id,issue_status,reported_by_email_id,existing_status_fk,existing_responsible_person,existing_escalated_to);
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	
	public void sendEmailWithIssueAlert(String issue_id,String issue_status,String reported_by_email_id, 
			String existing_status_fk, String existing_responsible_person, String existing_escalated_to) throws Exception {
		
		try {
		
			String emailsQry = "select i.issue_id,w.work_short_name,i.contract_id_fk,i.status_fk,i.reported_by,c.contract_short_name,w.work_name,c.contract_name,i.category_fk,i.priority_fk,i.title,i.location,i.corrective_measure,i.remarks,"
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
					+ "u2.email_id as responsible_person_email_id,u3.email_id as escalated_to_email_id,"
					+ "u4.email_id as contract_hod_email_id,u5.email_id as contract_dyhod_email_id,"
					+ "u2.user_id as responsible_person_user_id,u3.user_id as escalated_to_user_id,"
					+ "u4.user_id as contract_hod_user_id,u5.user_id as contract_dyhod_user_id,"
					+ "u1.email_id as created_by_email_id "
					+ "from issue i "
					+ "LEFT OUTER JOIN user u1 on i.created_by_user_id_fk = u1.user_id "
					+ "LEFT OUTER JOIN user u2 on i.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN user u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u4 on c.hod_user_id_fk = u4.user_id "
					+ "LEFT OUTER JOIN user u5 on c.dy_hod_user_id_fk = u5.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "where issue_id = ? "; 
			
			
			Object[] pValues = new Object[] {issue_id};
			
			Issue iObj = (Issue)jdbcTemplate.queryForObject(emailsQry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
			if(!StringUtils.isEmpty(iObj)) {
				
				/*********************************************************************************************/
				NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);	
				
				String issueMessageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
						+ "VALUES"
						+ "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";	
				
				
				String message1 = "A new issue against "+iObj.getContract_id_fk()+" has been "+iObj.getStatus_fk() + " to you";
				
				String message2 = "An issue against "+iObj.getContract_id_fk()+" has been "+iObj.getStatus_fk();
				
				String message3 = "An issue against "+iObj.getContract_id_fk()+" has been updated";
				
				String hod_user_id = "",dy_hod_user_id = "",responsible_person_user_id = "",escalated_to_user_id = "",created_by_user_id = "";
				if("Raised".equals(iObj.getStatus_fk())) {
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
					created_by_user_id = iObj.getCreated_by_user_id_fk();
				}else if("Assigned".equals(iObj.getStatus_fk())) {
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
				}else if("Escalated".equals(iObj.getStatus_fk())) {
					escalated_to_user_id = iObj.getEscalated_to_user_id();
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
				}else if("Closed".equals(iObj.getStatus_fk())) {
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					escalated_to_user_id = iObj.getEscalated_to_user_id();
					created_by_user_id = iObj.getCreated_by_user_id_fk();
				}
				
				if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Raised".equals(iObj.getStatus_fk()) 
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(created_by_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(created_by_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}
				if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Assigned".equals(iObj.getStatus_fk()) 
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}else if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Assigned".equals(iObj.getStatus_fk()) 
						&& iObj.getStatus_fk().equals(existing_status_fk) 
						&& !StringUtils.isEmpty(iObj.getResponsible_person()) && iObj.getResponsible_person().equals(existing_responsible_person) ) {
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}else if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Assigned".equals(iObj.getStatus_fk()) 
						&& iObj.getStatus_fk().equals(existing_status_fk) 
						&& !StringUtils.isEmpty(iObj.getResponsible_person()) && !iObj.getResponsible_person().equals(existing_responsible_person) ) {
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}
				
				if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Escalated".equals(iObj.getStatus_fk()) 
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if(!StringUtils.isEmpty(escalated_to_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message2);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}else if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Escalated".equals(iObj.getStatus_fk()) 
						&& iObj.getStatus_fk().equals(existing_status_fk)
						&& !StringUtils.isEmpty(iObj.getEscalated_to()) && iObj.getEscalated_to().equals(existing_escalated_to)) {
					if(!StringUtils.isEmpty(escalated_to_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}else if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Escalated".equals(iObj.getStatus_fk()) 
						&& iObj.getStatus_fk().equals(existing_status_fk)
						&& !StringUtils.isEmpty(iObj.getEscalated_to()) && !iObj.getEscalated_to().equals(existing_escalated_to)) {
					if(!StringUtils.isEmpty(escalated_to_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}
				
				if( !StringUtils.isEmpty(iObj.getStatus_fk()) && "Closed".equals(iObj.getStatus_fk()) 
						&& !iObj.getStatus_fk().equals(existing_status_fk)) {
					if(!StringUtils.isEmpty(hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(responsible_person_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(responsible_person_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(escalated_to_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(escalated_to_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
					if(!StringUtils.isEmpty(created_by_user_id)) {				
						String redirect_url = "/get-issue/"+iObj.getIssue_id();
						String message_type = "Issue";
						
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(created_by_user_id);
						msgObj.setMessage(message3);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);	
						template.update(issueMessageQry, paramSource);				
					}
				}
				
				
				
				
				/*********************************************************************************************/
				String mailTo = "";
				String mailCC = "";
				
				if("Raised".equals(iObj.getStatus_fk())) {
					if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						mailTo = mailTo + iObj.getContract_dyhod_email_id()+",";
					}
					if(!StringUtils.isEmpty(reported_by_email_id)) {
						mailCC = mailCC + reported_by_email_id+",";
					}
					if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						mailCC = mailCC + iObj.getContract_hod_email_id()+",";
					}
				}else if("Assigned".equals(iObj.getStatus_fk())) {
					if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						mailTo = mailTo + iObj.getResponsible_person_email_id()+",";
					}
					if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						mailCC = mailCC + iObj.getContract_dyhod_email_id()+",";
					}
				}else if("Escalated".equals(iObj.getStatus_fk())) {
					if(!StringUtils.isEmpty(iObj.getEscalated_to_email_id())) {
						mailTo = mailTo + iObj.getEscalated_to_email_id()+",";
					}
					if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						mailCC = mailCC + iObj.getResponsible_person_email_id()+",";
					}
					/*if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						mailCC = mailCC + iObj.getContract_hod_email_id()+",";
					}*/
					if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						mailCC = mailCC + iObj.getContract_dyhod_email_id()+",";
					}
				}else if("Closed".equals(iObj.getStatus_fk())) {
					if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						mailTo = mailTo + iObj.getContract_hod_email_id()+",";
					}
					if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						mailCC = mailCC + iObj.getContract_dyhod_email_id()+",";
					}
					if(!StringUtils.isEmpty(iObj.getEscalated_to_email_id())) {
						mailCC = mailCC + iObj.getEscalated_to_email_id()+",";
					}
					if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						mailCC = mailCC + iObj.getResponsible_person_email_id()+",";
					}
					if(!StringUtils.isEmpty(iObj.getCreated_by_email_id())) {
						mailCC = mailCC + iObj.getCreated_by_email_id()+",";
					}
				}
				
				if(!StringUtils.isEmpty(mailTo)) {
					mailTo =  org.apache.commons.lang3.StringUtils.chop(mailTo);  
				}
				
				if(!StringUtils.isEmpty(mailCC)) {
					mailCC =  org.apache.commons.lang3.StringUtils.chop(mailCC);  
				}
				
				String mailBodyHeader = "";
				
				if(!StringUtils.isEmpty(iObj.getStatus_fk()) && !iObj.getStatus_fk().equals(existing_status_fk) 
						&& !iObj.getStatus_fk().equals("Closed")) {
					mailBodyHeader = mailBodyHeader + "A new ";
				}else {
					mailBodyHeader = mailBodyHeader + "An ";
				}
				mailBodyHeader = mailBodyHeader + "issue against ";
				
				if(!StringUtils.isEmpty(iObj.getContract_id_fk())) {
					mailBodyHeader = mailBodyHeader + iObj.getContract_id_fk();
				}
				mailBodyHeader = mailBodyHeader + " has been ";
				
				if(!StringUtils.isEmpty(iObj.getStatus_fk()) && !StringUtils.isEmpty(existing_status_fk) 
						&& iObj.getStatus_fk().equals(existing_status_fk)) {
					mailBodyHeader = mailBodyHeader + "updated ";
				}else{
					mailBodyHeader = mailBodyHeader + iObj.getStatus_fk();
					if("Assigned".equals(iObj.getStatus_fk()) || "Escalated".equals(iObj.getStatus_fk()) ) {
						mailBodyHeader = mailBodyHeader + " to you ";
					}
				}
				
				iObj.setMail_body_header(mailBodyHeader);
				
				String emailSubject = "PMIS Issue Alert - Issue "+issue_status;
				
				Mail mail = new Mail();
				mail.setMailTo(mailTo);
				mail.setMailCc(mailCC);
				mail.setMailBcc(CommonConstants.BCC_MAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("IssueAlert.vm");
				
				if(!StringUtils.isEmpty(mailTo)){		
					EMailSender emailSender = new EMailSender();
					logger.error("sendEmailWithIssueAlert() >> Sending mail to "+mailTo+": Start ");
					logger.error("sendEmailWithIssueAlert() >> Sending mail CC "+mailCC+": Start ");
					emailSender.sendEmailWithIssueAlert(mail,iObj);
					logger.error("sendEmailWithIssueAlert() >> Sending mail to "+mailTo+": end ");
					logger.error("sendEmailWithIssueAlert() >> Sending mail CC "+mailCC+": end ");
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}

	@Override
	public boolean deleteIssue(Issue obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public List<Issue> getContractsListFilter(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name "
					+ "from issue i "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
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
			String qry = "SELECT c.department_fk,department,department_name "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
					+ "where c.department_fk is not null and c.department_fk <> '' ";
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			} 
			
			qry = qry + " GROUP BY c.department_fk";
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
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
			String qry = "SELECT category_fk "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
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
			String qry = "SELECT status_fk "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
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
			String qry = "SELECT work_id as work_id_fk,w.work_short_name "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
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
			String qry = "SELECT responsible_person,u.user_name "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
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
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getResponsible_person())) {
				qry = qry + " and responsible_person = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			} 
			qry = qry + " GROUP BY responsible_person ";
			
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
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Issue> getHODListFilterInIssue(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT hod_user_id_fk,u.designation "
					+ "from issue i "
					+ "LEFT JOIN contract c on i.contract_id_fk = c.contract_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk COLLATE utf8mb4_unicode_ci = d.department "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN user u on c.hod_user_id_fk = u.user_id "
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and c.department_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			} 
			qry = qry + " group by u.designation";
			
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.USER_TYPE_MANAGEMENT.equals(obj.getUser_type()) 
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			} 
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getReportedByList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT user_id as reported_by_user_id,designation as reported_by_designation "
					+ "from user "
					+ "where user_type_fk = ? group by designation order by designation";
			
			Object[] pValues = new Object[] {CommonConstants.USER_TYPE_HOD};
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getResponsiblePersonList(Issue obj) throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT user_id as responsible_person_user_id,designation as responsible_person_designation "
					+ "from user "
					+ "where user_type_fk = ? ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_name())){
				qry = qry + "and department_fk = (select department from department where department_name = ?)";
				arrSize++;
			}
			qry = qry + "group by designation order by designation";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.USER_TYPE_DYHOD;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_name())){
				pValues[i++] = obj.getDepartment_name();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getEscalatedToList() throws Exception {
		List<Issue> objsList = null;
		try {
			String qry = "SELECT user_id as escalated_to_user_id,designation as escalated_to_designation "
					+ "from user "
					+ "where (user_type_fk = ? or user_type_fk = ?) group by designation order by designation";
			
			Object[] pValues = new Object[] {CommonConstants.USER_TYPE_HOD,CommonConstants.USER_TYPE_MANAGEMENT};
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Issue> getIssueAlerts(User uObj) throws Exception {
		List<Issue> objsList = null;
		try {
			
			
			String qry = "select issue_id,contract_id_fk,d.department_name,c.contract_short_name,i.title,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,other_organization,c.department_fk," 
					+ "priority_fk,category_fk,status_fk,corrective_measure,DATE_FORMAT(resolved_date,'%d-%m-%Y') AS resolved_date,escalated_to,i.remarks,contract_name,work_id_fk,work_name,work_short_name,project_id_fk,project_name,"
					+ "i.zonal_railway_fk,r.railway_name,c.contractor_id_fk,ctr.contractor_id,ctr.contractor_name,"
					+ "d.department_name,hod_user_id_fk,u.designation,u.user_name as hod_name,DATEDIFF(NOW(), date) as pending_since,DATE_FORMAT(date,'%d-%m-%Y') AS date, "
					+ "u2.designation as responsible_person_designation,u3.designation as escalated_to_designation,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk "
					+ "from issue i "
					+ "LEFT OUTER JOIN user u2 on i.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN user u3 on i.escalated_to = u3.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN contractor ctr ON c.contractor_id_fk= ctr.contractor_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT OUTER JOIN railway r ON i.zonal_railway_fk COLLATE utf8mb4_unicode_ci = r.railway_id "
					+ "where status_fk <> ? and date_sub(now(),interval 30 day) >= date " ;
			int arrSize = 1;
			//if(!StringUtils.isEmpty(uObj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(uObj.getUser_role_code())) {
				qry = qry + " and (i.responsible_person = ? or i.escalated_to = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			//}
			
			qry = qry + " ORDER BY location,i.date ASC";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = "Closed";
			
			//if(!StringUtils.isEmpty(uObj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(uObj.getUser_role_code())) {
				pValues[i++] = uObj.getUser_id();
				pValues[i++] = uObj.getUser_id();
				pValues[i++] = uObj.getUser_id();
				pValues[i++] = uObj.getUser_id();
			//}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Issue>(Issue.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	

}
