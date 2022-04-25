package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.SafetyDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Safety;
import com.synergizglobal.pmis.model.SourceOfFund;

@Repository
public class SafetyDaoImpl implements SafetyDao {
	public static Logger logger = Logger.getLogger(SafetyDaoImpl.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<Safety> getSafetyList(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,title,d.department_name,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,u.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,DATE_FORMAT(closure_date,'%d-%m-%Y') AS closure_date,cast(lti_hours as CHAR) as lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "DATE_FORMAT(investigation_completed,'%d-%m-%Y') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,cast(compensation as CHAR) as compensation,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name, "
					+ "compensation_units,m.unit as compensation_unit,s.modified_by,DATE_FORMAT(s.modified_date,'%d-%m-%Y') as modified_date,reported_by,u2.designation as responsible_person "
					+ "from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON c.department_fk  = d.department "
					+ "LEFT OUTER JOIN money_unit m ON s.compensation_units  = m.value  "
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
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
			throw new Exception(e);
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
			throw new Exception(e);
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
			throw new Exception(e);
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
					+ "(contract_id_fk,hod_user_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,category_fk,impact_fk,root_cause_fk,status_fk,"
					+ "closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,investigation_completed,corrective_measure_short_term,"
					+ "corrective_measure_long_term,compensation,payment_date,remarks,compensation_units,committee_member_name,created_by,created_date) "
					+ "VALUES "
					+ "(:contract_id_fk,:hod_user_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:category_fk,:impact_fk,:root_cause_fk,:status_fk,:"
					+ "closure_date,:lti_hours,:equipment_impact,:people_impact,:work_impact,:committee_formed_fk,:committee_required_fk,:investigation_completed,:corrective_measure_short_term,:"
					+ "corrective_measure_long_term,:compensation,:payment_date,:remarks,:compensation_units,:committee_member_name,:created_by_user_id_fk,CURRENT_TIMESTAMP)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				safety_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setSafety_id(safety_id);
				flag = true;
				if(flag) 
				{
					if(!StringUtils.isEmpty(obj.getSafetyFiles()) && obj.getSafetyFiles().size() > 0) 
					{
						
						String fileQry = "INSERT INTO safety_files (attachment,safety_id_fk)VALUES(:attachment,:safety_id)";
						
						List<MultipartFile> issueFiles = obj.getSafetyFiles();
						for (MultipartFile multipartFile : issueFiles) {
							if(null != multipartFile && !multipartFile.isEmpty()){
								String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH;
								String fileName = multipartFile.getOriginalFilename();
								DateFormat df = new SimpleDateFormat("ddMMYY-HHmm");
								String fileName_new = "Safety-"+obj.getSafety_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
								FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName_new);
								
								Safety fileObj = new Safety();
								fileObj.setAttachment(fileName_new);
								fileObj.setSafety_id(obj.getSafety_id());
								paramSource = new BeanPropertySqlParameterSource(fileObj);	
								template.update(fileQry, paramSource);
							}
						}
					}
					
					String safety_status = obj.getStatus_fk();
					String reported_by_email_id = obj.getReported_by_email_id();					

					sendEmailWithSafetyStatusAlert(safety_id, safety_status, reported_by_email_id, obj.getExisting_status_fk(), null,
							null);					
					
					/*if(!StringUtils.isEmpty(obj.getCommittee_member_names()) && obj.getCommittee_member_names().length > 0) 
					{
						Safety fileCommitteeMembersObj = new Safety();
						String fileQry = "INSERT INTO safety_committee_members (committee_member_name,safety_id_fk)VALUES(:committee_member_name,:safety_id)";
						
						for (int i = 0; i < obj.getCommittee_member_names().length; i++) {
							fileCommitteeMembersObj = new Safety();
							fileCommitteeMembersObj.setCommittee_member_name(obj.getCommittee_member_names()[i]);
							fileCommitteeMembersObj.setSafety_id(obj.getSafety_id());
							paramSource = new BeanPropertySqlParameterSource(fileCommitteeMembersObj);	
							template.update(fileQry, paramSource);
						}
					}*/						
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Safety");
				formHistory.setForm_name("Add Safety ");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Safety  "+obj.getSafety_id() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}
	
	
	public void sendEmailWithSafetyStatusAlert(String safety_id, String safety_status, String reported_by_email_id,
			String existing_status_fk, String existing_responsible_person, String existing_escalated_to)
			throws Exception {

		try {

			String emailsQry = "select i.safety_id,w.work_short_name,i.contract_id_fk,i.status_fk,i.reported_by,c.contract_short_name,w.work_name,c.contract_name,i.category_fk,i.title,i.location,i.remarks,"
					+ "u2.designation as responsible_person_designation,"
					+ "u2.email_id as responsible_person_email_id,"
					+ "u4.email_id as contract_hod_email_id,u5.email_id as contract_dyhod_email_id,"
					+ "i.responsible_person as responsible_person_user_id,"
					+ "c.hod_user_id_fk as contract_hod_user_id,c.dy_hod_user_id_fk as contract_dyhod_user_id,impact_fk,"
					+ " i.remarks,corrective_measure_long_term,i.created_by as reported_by_user_id,u6.email_id as reported_by_email_id,"
					+ "corrective_measure_short_term,"
					+ "(select group_concat(user_name) from safety_committee_members cmb left join user u7 on cmb.committee_member_name = u7.user_id where safety_id_fk = i.safety_id) as committe_members "
					+ "from safety i " 
					+ "LEFT OUTER JOIN user u2 on i.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u4 on c.hod_user_id_fk = u4.user_id "
					+ "LEFT OUTER JOIN user u5 on c.dy_hod_user_id_fk = u5.user_id "
					+ "LEFT OUTER JOIN user u6 on i.created_by = u6.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "where safety_id = ? ";

			Object[] pValues = new Object[] { safety_id };

			Safety iObj = (Safety) jdbcTemplate.queryForObject(emailsQry, pValues,new BeanPropertyRowMapper<Safety>(Safety.class));
			
			if(!StringUtils.isEmpty(iObj)) {
				
				String committe_user_ids_query = "select committee_member_name from safety_committee_members where safety_id_fk = ? group by committee_member_name";
				String committe_user_email_ids_query = "select email_id from user where user_id in(select committee_member_name from safety_committee_members where safety_id_fk = ? group by committee_member_name)";
				
				List<String> committe_user_ids = jdbcTemplate.queryForList(committe_user_ids_query,new Object[]{safety_id}, String.class);
				List<String> committe_user_email_ids = jdbcTemplate.queryForList( committe_user_email_ids_query,new Object[]{safety_id},String.class);
				
				/*********************************************************************************************/
				NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

				String issueMessageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
						+ "VALUES" + "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";

				String safetystatus = null;
				if(!StringUtils.isEmpty(iObj.getStatus_fk())) {
					safetystatus = iObj.getStatus_fk().toLowerCase();
				}
				String message1 = "A new safety against " + iObj.getContract_short_name() + " has been " + safetystatus + " to you";

				String message2 = "An incident against " + iObj.getContract_short_name() + " has been " + safetystatus;

				String message3 = "An incident against " + iObj.getContract_short_name() + " has been ";
				
				String message4 = "An incident against " + iObj.getContract_short_name() + " has been assigned to you as a Committee member";

				if(!"Update".equals(safety_status)){		
					message3 = message3 + safetystatus;
				}else{
					message3 = message3 + "updated";
				}

				String hod_user_id = "", dy_hod_user_id = "", responsible_person_user_id = "",reported_by_user_id = "";
				if("Open".equals(iObj.getStatus_fk())) {
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
					reported_by_user_id = iObj.getReported_by_user_id();
				}else if("Closed".equals(iObj.getStatus_fk())) {
					hod_user_id = iObj.getContract_hod_user_id();
					dy_hod_user_id = iObj.getContract_dyhod_user_id();
					responsible_person_user_id = iObj.getResponsible_person_user_id();
					reported_by_user_id = iObj.getReported_by_user_id();
				}
				String redirect_url = "/get-safety?safety_id=" + iObj.getSafety_id();
				
				String message_type = "Safety";
				
				if(!"Update".equals(safety_status)){				
					if(!StringUtils.isEmpty(iObj.getStatus_fk()) && "Open".equals(iObj.getStatus_fk())
							&& !iObj.getStatus_fk().equals(existing_status_fk)) {
						if(!StringUtils.isEmpty(dy_hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(dy_hod_user_id);
							msgObj.setMessage(message1);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(hod_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
					}
					
				} else {
					
					if(!StringUtils.isEmpty(iObj.getStatus_fk()) && "Closed".equals(iObj.getStatus_fk())
							&& !iObj.getStatus_fk().equals(existing_status_fk)) {
						if(!StringUtils.isEmpty(hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(hod_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(dy_hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(dy_hod_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(responsible_person_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(responsible_person_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(reported_by_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(reported_by_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
					}else if(!StringUtils.isEmpty(iObj.getStatus_fk())
							&& !iObj.getStatus_fk().equals(existing_status_fk)) {
						if(!StringUtils.isEmpty(hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(hod_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(dy_hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(dy_hod_user_id);
							msgObj.setMessage(message2);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(responsible_person_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(responsible_person_user_id);
							msgObj.setMessage(message1);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
					} else if(!StringUtils.isEmpty(iObj.getStatus_fk())
							&& iObj.getStatus_fk().equals(existing_status_fk)
							&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
							&& iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
						if(!StringUtils.isEmpty(responsible_person_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(responsible_person_user_id);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(hod_user_id);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(dy_hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(dy_hod_user_id);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
					}else if(!StringUtils.isEmpty(iObj.getStatus_fk())
							&& iObj.getStatus_fk().equals(existing_status_fk)
							&& !StringUtils.isEmpty(iObj.getResponsible_person_user_id())
							&& !iObj.getResponsible_person_user_id().equals(existing_responsible_person)) {
						if(!StringUtils.isEmpty(responsible_person_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(responsible_person_user_id);
							msgObj.setMessage(message1);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(hod_user_id);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(dy_hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(dy_hod_user_id);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
					}
					
					for (String user_id : committe_user_ids) {
						if(!StringUtils.isEmpty(user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(user_id);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
					}
					
				}

				/*********************************************************************************************/
				String mailTo = "";
				String mailCC = "";
				
				
				if(!"Update".equals(safety_status)){
					if("Open".equals(iObj.getStatus_fk())) {
						if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
							mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
						}
						if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
							mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
						}
						if(!StringUtils.isEmpty(iObj.getReported_by_email_id())) {
							mailCC = mailCC + iObj.getReported_by_email_id() + ",";
						}
						if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
							mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
						}
					}else if("Closed".equals(iObj.getStatus_fk())) {
						if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
							mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
						}
						if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
							mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
						}
						if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
							mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
						}
						if(!StringUtils.isEmpty(iObj.getReported_by_email_id())) {
							mailCC = mailCC + iObj.getReported_by_email_id() + ",";
						}
					}
					
				} else {
					
					if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
					}
					if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
					}
					if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
					}	
					
					for (String email_id : committe_user_email_ids) {
						if(!StringUtils.isEmpty(email_id)) {
							mailTo = mailTo + email_id + ",";
						}
					}
				}
				
				

				if(!StringUtils.isEmpty(mailTo)) {
					mailTo = org.apache.commons.lang3.StringUtils.chop(mailTo);
				}

				if(!StringUtils.isEmpty(mailCC)) {
					mailCC = org.apache.commons.lang3.StringUtils.chop(mailCC);
				}

				String mailBodyHeader = "";

				if(!"Update".equals(safety_status)) {
					if(iObj.getStatus_fk().equals("Open")) {
						mailBodyHeader = mailBodyHeader + "A new safety ";
					}else if(iObj.getStatus_fk().equals("Closed")) {
						mailBodyHeader = mailBodyHeader + "An ";
					}
				}else{
					mailBodyHeader = mailBodyHeader + "An ";
				}
				mailBodyHeader = mailBodyHeader + "incident against ";
				
				if(!StringUtils.isEmpty(iObj.getContract_id_fk())) {
					mailBodyHeader = mailBodyHeader + iObj.getContract_short_name();
				}
				mailBodyHeader = mailBodyHeader + " has been ";				

				if(!"Update".equals(safety_status)) {
					mailBodyHeader = mailBodyHeader + safetystatus;
				}else{
					mailBodyHeader = mailBodyHeader + "updated ";
				}


				iObj.setMail_body_header(mailBodyHeader);

				String emailSubject = "PMIS Safety Notification - Safety ";

				if(!StringUtils.isEmpty(iObj.getStatus_fk()) && !StringUtils.isEmpty(existing_status_fk)
						&& iObj.getStatus_fk().equals(existing_status_fk)) {
					if("Closed".equals(iObj.getStatus_fk())) {
						emailSubject = emailSubject + iObj.getStatus_fk();
					} else {
						emailSubject = emailSubject + "Updated";
					}
				} else {
					emailSubject = emailSubject + safety_status;
				}

				Mail mail = new Mail();
				mail.setMailTo(mailTo);
				mail.setMailCc(mailCC);
				mail.setMailBcc(CommonConstants.BCC_MAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("SafetyStatusAlert.vm");

				SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
				String today_date = monthFormat.format(new Date()).toUpperCase();

				SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
				String current_year = yearFormat.format(new Date()).toUpperCase();

				if(!StringUtils.isEmpty(mailTo)) {
					EMailSender emailSender = new EMailSender();
					/*logger.error("sendEmailWithSafetyStatusAlert() >> Sending mail to " + mailTo + ": Start ");
					logger.error("sendEmailWithSafetyStatusAlert() >> Sending mail CC " + mailCC + ": Start ");*/
					emailSender.sendEmailWithSafetyStatusAlert(mail, iObj, today_date, current_year);
					/*logger.error("sendEmailWithSafetyStatusAlert() >> Sending mail to " + mailTo + ": end ");
					logger.error("sendEmailWithSafetyStatusAlert() >> Sending mail CC " + mailCC + ": end ");*/
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}

	}	

	@Override
	public Safety getSafety(Safety obj) throws Exception {
		Safety sobj = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,c.hod_user_id_fk,c.dy_hod_user_id_fk,w.work_short_name,title,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,responsible_person,u.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,DATE_FORMAT(closure_date,'%d-%m-%Y') AS closure_date,cast(lti_hours as CHAR) as lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "DATE_FORMAT(investigation_completed,'%d-%m-%Y') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,cast(compensation as CHAR) as compensation,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name,s.compensation_units,s.committee_member_name,"
					+ "s.status_fk as existing_status_fk  "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
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
			
			if(!StringUtils.isEmpty(sobj)) {				
				String filesQry ="select id, safety_id_fk, attachment from safety_files where safety_id_fk = ? ";					
				List<Safety> objsList = jdbcTemplate.query( filesQry,new Object[] {obj.getSafety_id()}, new BeanPropertyRowMapper<Safety>(Safety.class));					
				if(!StringUtils.isEmpty(objsList)) {
					sobj.setSafetyFilesList(objsList);
				}
				String filesCMQry ="select id, safety_id_fk, committee_member_name from safety_committee_members where safety_id_fk = ? ";					
				List<Safety> objsCMList = jdbcTemplate.query( filesCMQry,new Object[] {obj.getSafety_id()}, new BeanPropertyRowMapper<Safety>(Safety.class));					
				if(!StringUtils.isEmpty(objsCMList)) {
					sobj.setSafetyCommitteeMembersList(objsCMList);
				}				
			}	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return sobj;
	}

	@Override
	public boolean updateSafety(Safety obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE safety SET contract_id_fk=:contract_id_fk,hod_user_id_fk=:hod_user_id_fk,title=:title,description=:description,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,category_fk=:category_fk,impact_fk=:impact_fk,root_cause_fk=:root_cause_fk,status_fk=:status_fk,"
					+ "closure_date=:closure_date,lti_hours=:lti_hours,equipment_impact=:equipment_impact,people_impact=:people_impact,work_impact=:work_impact,committee_formed_fk=:committee_formed_fk,committee_required_fk = :committee_required_fk,investigation_completed=:investigation_completed,corrective_measure_short_term=:corrective_measure_short_term,"
					+ "corrective_measure_long_term=:corrective_measure_long_term,compensation=:compensation,payment_date=:payment_date,remarks=:remarks,compensation_units=:compensation_units,committee_member_name=:committee_member_name,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP  "
					+ "WHERE safety_id = :safety_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			if(flag) {
				String deleteFilesQry = "delete from safety_files where safety_id_fk = :safety_id";
				
				Safety fileObj = new Safety();
				fileObj.setSafety_id(obj.getSafety_id());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				template.update(deleteFilesQry, paramSource);
				
				String fileQry = "INSERT INTO safety_files (attachment,safety_id_fk)VALUES(:attachment,:safety_id)";
				
				int arraySize = 0;
				if(!StringUtils.isEmpty(obj.getSafetyFileNames()) && obj.getSafetyFileNames().length > 0 ) {
					obj.setSafetyFileNames(CommonMethods.replaceEmptyByNullInSringArray(obj.getSafetyFileNames()));
					if(arraySize < obj.getSafetyFileNames().length) {
						arraySize = obj.getSafetyFileNames().length;
					}
				}
				for (int i = 0; i < arraySize; i++) {
					fileObj = new Safety();
					fileObj.setAttachment(obj.getSafetyFileNames()[i]);
					fileObj.setSafety_id(obj.getSafety_id());
					paramSource = new BeanPropertySqlParameterSource(fileObj);	
					template.update(fileQry, paramSource);
				}
				
				String deletecommitteeMembersQry = "delete from safety_committee_members where safety_id_fk = :safety_id";
				
				Safety fileCommitteeMembersObj = new Safety();
				fileCommitteeMembersObj.setSafety_id(obj.getSafety_id());
				paramSource = new BeanPropertySqlParameterSource(obj);	
				template.update(deletecommitteeMembersQry, paramSource);

				
				String committeeMembersQry = "INSERT INTO safety_committee_members (committee_member_name,safety_id_fk)VALUES(:committee_member_name,:safety_id)";
				
				int arrayCMSize = 0;
				if(!StringUtils.isEmpty(obj.getCommittee_member_names()) && obj.getCommittee_member_names().length > 0 ) {
					obj.setCommittee_member_names(CommonMethods.replaceEmptyByNullInSringArray(obj.getCommittee_member_names()));
					if(arrayCMSize < obj.getCommittee_member_names().length) {
						arrayCMSize = obj.getCommittee_member_names().length;
					}
				}

				for (int i = 0; i < arrayCMSize; i++) {
					fileCommitteeMembersObj = new Safety();
					fileCommitteeMembersObj.setCommittee_member_name(obj.getCommittee_member_names()[i]);
					fileCommitteeMembersObj.setSafety_id(obj.getSafety_id());
					paramSource = new BeanPropertySqlParameterSource(fileCommitteeMembersObj);	
					template.update(committeeMembersQry, paramSource);
				}
							
				
				if(!StringUtils.isEmpty(obj.getSafetyFiles()) && obj.getSafetyFiles().size() > 0) {
					List<MultipartFile> issueFiles = obj.getSafetyFiles();
					for (MultipartFile multipartFile : issueFiles) {
						if(null != multipartFile && !multipartFile.isEmpty()){
							String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH;
							String fileName = multipartFile.getOriginalFilename();
							DateFormat df = new SimpleDateFormat("ddMMYY-HHmm");
							String fileName_new = "Safety-"+obj.getSafety_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
							FileUploads.singleFileSaving(multipartFile, saveDirectory, fileName_new);
							
							fileObj = new Safety();
							fileObj.setAttachment(fileName_new);
							fileObj.setSafety_id(obj.getSafety_id());
							paramSource = new BeanPropertySqlParameterSource(fileObj);	
							template.update(fileQry, paramSource);
						}
					}
				} 
				String safety_id = obj.getSafety_id();
				String safety_status = obj.getStatus_fk();
				String existing_status_fk = obj.getExisting_status_fk();
				String reported_by_email_id = obj.getReported_by_email_id();
				String existing_responsible_person = obj.getExisting_responsible_person();
				String existing_escalated_to = obj.getExisting_escalated_to();
				/*if(!StringUtils.isEmpty(obj.getExisting_responsible_person()) && !StringUtils.isEmpty(obj.getResponsible_person()) && 
						obj.getExisting_responsible_person().compareTo(obj.getResponsible_person())!=0){
					sendEmailWithSafetyStatusAlert(safety_id, "Update", reported_by_email_id, existing_status_fk,existing_responsible_person, existing_escalated_to);
				}*/
				
				sendEmailWithSafetyStatusAlert(safety_id, "Update", reported_by_email_id, existing_status_fk,existing_responsible_person, existing_escalated_to);
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Safety");
				formHistory.setForm_name("Update Safety ");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Safety  "+obj.getSafety_id() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getContractsListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT contract_id_fk,c.contract_id,contract_name,contract_short_name from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ "where contract_id_fk is not null and contract_id_fk <> '' ";
					
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY contract_id_fk";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getDepartmentsListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT u.department_fk,department,department_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ "where u.department_fk is not null and u.department_fk <> '' ";
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY u.department_fk";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getCategoryListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT category_fk from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ " where category_fk is not null and category_fk <> '' ";
			
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY category_fk";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getStatusListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT status_fk from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ " where status_fk is not null and status_fk <> '' ";
			
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY status_fk";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getWorksListFilter(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT work_id as work_id_fk,work_short_name from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ " where work_id_fk is not null and work_id_fk <> '' ";
			
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY work_id_fk";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Safety> getHODListFilterInSafety(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT c.hod_user_id_fk,u.designation,u.user_name as hod_name "
					+ "from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ "where c.hod_user_id_fk is not null and c.hod_user_id_fk <> '' ";
					
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY c.hod_user_id_fk ORDER BY FIELD(u.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T'),u.designation" ;
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
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
			String qry ="select distinct c.contract_id as contract_id_fk,c.hod_user_id_fk,c.contract_name,c.contract_short_name,c.work_id_fk "
					+ "from contract c "
					+ "left join contract_executive c1 on c1.contract_id_fk = c.contract_id "
					+ "LEFT JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "where c.contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					qry = qry + " and c1.department_id_fk = ? and u.department_fk = ?";
					arrSize++;
					arrSize++;
				}
			}			
			qry = qry + " order by c.contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					pValues[i++] = obj.getDepartment_fk();
					pValues[i++] = obj.getDepartment_fk();
				
				}
			}			
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
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
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Safety obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry = "SELECT count(*) as total_records from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.contract_id like ? or c.contract_short_name like ? or title like ? or location like ? "
						+ "or u2.designation like ? or department_name like ? or category_fk like ? or status_fk like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<Safety> getSafetyList(Safety obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,title,d.department_name,description,DATE_FORMAT(date,'%d-%m-%Y') AS date,location,cast(latitude as CHAR) as latitude,cast(longitude as CHAR) as longitude,reported_by,u2.designation as responsible_person,u.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,DATE_FORMAT(closure_date,'%d-%m-%Y') AS closure_date,cast(lti_hours as CHAR) as lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "DATE_FORMAT(investigation_completed,'%d-%m-%Y') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,cast(compensation as CHAR) as compensation,DATE_FORMAT(payment_date,'%d-%m-%Y') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name,s.modified_by,DATE_FORMAT(s.modified_date,'%d-%m-%Y') as modified_date  "
					+ "from safety s "
					+ "LEFT OUTER JOIN user u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk COLLATE utf8mb4_unicode_ci = c.contract_id "
					+ "LEFT OUTER JOIN user u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk COLLATE utf8mb4_unicode_ci = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk COLLATE utf8mb4_unicode_ci = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
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
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.contract_id like ? or c.contract_short_name like ? or title like ? or location like ? "
						+ "or u2.designation like ? or department_name like ? or category_fk like ? or status_fk like ?)";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " order by safety_id ASC limit ?,?";
				arrSize++;
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_DATA_ADMIN.equals(obj.getUser_role_code())
					&& !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getUnitsList(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getResponsiblePersonsListForSafetyForm(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT designation,user_name,user_id,reporting_to_id_srfk FROM user where designation <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and reporting_to_id_srfk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY designation";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Safety> getUersList(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation,user_type_fk FROM user where user_type_fk in ('HOD','DyHOD') ORDER BY FIELD(user_type_fk,'HOD','DyHOD') ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	

}
