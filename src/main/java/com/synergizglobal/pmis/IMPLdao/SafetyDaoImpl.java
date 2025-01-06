package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			String qry = "SELECT safety_id,contract_id_fk,w.work_short_name,safety_incident,u8.user_name as nominated_authority,s.hod_user_id_fk,u.designation,c.contract_short_name,title,d.department_name,description,FORMAT(date,'dd-MM-yyyy') AS date,location,latitude,longitude,reported_by,responsible_person,u.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,FORMAT(closure_date,'dd-MM-yyyy') AS closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "FORMAT(investigation_completed,'dd-MM-yyyy') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,compensation,FORMAT(payment_date,'dd-MM-yyyy') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name, "
					+ "compensation_units,m.unit as compensation_unit,s.modified_by,FORMAT(s.modified_date,'dd-MM-yyyy') as modified_date,safety_seq_id,reported_by,u2.designation as responsible_person,(select STRING_AGG(user_name,',') from safety_committee_members cmb left join [user] u7 on cmb.committee_member_name = u7.user_id where safety_id_fk = s.safety_id) as committe_members "
					+ "from safety s "
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "left outer join [user] u8 on s.nominated_authority = u8.user_id "
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
	
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";	
				
				arrSize++;
				arrSize++;
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
			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSearchStr())) {
				qry = qry + " and (c.contract_id like ? or c.contract_short_name like ? or title like ? or location like ? "
						+ "or u2.designation like ? or department_name like ? or category_fk like ? or status_fk like ? or safety_seq_id like ?)";
				arrSize++;
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				pValues[i++] = obj.getHod_user_id_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getSearchStr())) {
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";
				pValues[i++] = "%"+obj.getSearchStr()+"%";				
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
	
	private String getAutoGeneratedSafetyId(Safety obj) {
		Safety dObj = null;
		Safety WorkCodedObj = null;
		String safetyid=null;
		try {
			String qry ="select work_code  from work where work_id='"+obj.getWork_id_fk()+"'" ;
			WorkCodedObj = (Safety)jdbcTemplate.queryForObject(qry, new BeanPropertyRowMapper<Safety>(Safety.class));	
			
			
			String input = obj.getContract_id_fk();   
			String lastFourDigits = "";   
			 
			if (input.length() > 4) 
			{
			    lastFourDigits = input.substring(input.length() - 4);
			} 
			else
			{
			    lastFourDigits = input;
			}			


				String qry2="select CONCAT('"+WorkCodedObj.getWork_code()+"','-"+lastFourDigits+"-',case when len(safety_seq_id)=3 then concat('0',safety_seq_id) when len(safety_seq_id)=2 then concat('00',safety_seq_id) when len(safety_seq_id)=1 then concat('000',safety_seq_id) end) as safety_id from(" + 
						"select (case when (select count(*) from safety where left(safety_seq_id,2) ='"+WorkCodedObj.getWork_code()+"')>0 then Max(SUBSTRING( safety_seq_id , LEN(safety_seq_id) -  CHARINDEX('-',REVERSE(safety_seq_id)) + 2  , LEN(safety_seq_id)  ))+1 else 1 end )as safety_seq_id from safety where left(safety_seq_id,2) ='"+WorkCodedObj.getWork_code()+"') as a";
						dObj = (Safety)jdbcTemplate.queryForObject(qry2, new Object[] {}, new BeanPropertyRowMapper<Safety>(Safety.class));
						safetyid = dObj.getSafety_id();
			
		}catch(Exception e){ 
			e.printStackTrace();
		}
	    return safetyid;
	}	

	@Override
	public String addSafety(Safety obj) throws Exception {
		String safetyid = null;
		//TransactionDefinition def = new DefaultTransactionDefinition();
		//TransactionStatus status = transactionManager.getTransaction(def);
		String safety_id = null;
		String safety_seq_id = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			
			safety_seq_id = getAutoGeneratedSafetyId(obj);
			obj.setSafety_seq_id(safety_seq_id);
			
			
			String qry = "INSERT INTO safety"
					+ "(contract_id_fk,hod_user_id_fk,title,description,date,location,latitude,longitude,reported_by,responsible_person,category_fk,impact_fk,root_cause_fk,status_fk,"
					+ "closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,investigation_completed,corrective_measure_short_term,"
					+ "corrective_measure_long_term,compensation,payment_date,remarks,compensation_units,committee_member_name,created_by,created_date,approve_corrective_measure,safety_incident,safety_seq_id) "
					+ "VALUES "
					+ "(:contract_id_fk,:hod_user_id_fk,:title,:description,:date,:location,:latitude,:longitude,:reported_by,:responsible_person,:category_fk,:impact_fk,:root_cause_fk,:status_fk,:"
					+ "closure_date,:lti_hours,:equipment_impact,:people_impact,:work_impact,:committee_formed_fk,:committee_required_fk,:investigation_completed,:corrective_measure_short_term,:"
					+ "corrective_measure_long_term,:compensation,:payment_date,:remarks,:compensation_units,:committee_member_name,:created_by_user_id_fk,CURRENT_TIMESTAMP,:approve_corrective_measure,:safety_incident,:safety_seq_id)";	
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			KeyHolder keyHolder = new GeneratedKeyHolder();
		    int count = template.update(qry, paramSource, keyHolder);
			if(count > 0) {
				safety_id = String.valueOf(keyHolder.getKey().intValue());
				obj.setSafety_id(safety_id);
				safetyid = obj.getSafety_seq_id();
				if(!StringUtils.isEmpty(obj.getSafetyFiles()) && obj.getSafetyFiles().size() > 0){						
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

				sendEmailWithSafetyStatusAlert(obj.getUser_id(),obj.getUser_role_code(),safety_id, "Add", null, null, null);
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Safety");
				formHistory.setForm_name("Add Safety ");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Safety  "+obj.getSafety_id() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			//transactionManager.commit(status);
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			throw new Exception(e);
		}
		return safetyid;
	}
	
	
	public void sendEmailWithSafetyStatusAlert(String userid,String role,String safety_id, String action_type,String existing_status_fk, String existing_responsible_person, String existing_committe_members)
			throws Exception {

		try {
			String emailsQry = "select i.safety_id,w.work_short_name,i.contract_id_fk,i.status_fk,i.reported_by,c.contract_short_name,u8.user_name as nominated_authority,i.nominated_authority as nominated_authority_email_id,safety_incident,approve_corrective_measure,w.work_name,"
					+ "c.contract_name,i.category_fk,i.title,i.location,i.remarks,"
					+ "u2.user_name as responsible_person,"
					+ "u2.designation as responsible_person_designation,"
					+ "u2.email_id as responsible_person_email_id,"
					+ "u4.email_id as contract_hod_email_id,u5.email_id as contract_dyhod_email_id,"
					+ "i.responsible_person as responsible_person_user_id,"
					+ "c.hod_user_id_fk as contract_hod_user_id,c.dy_hod_user_id_fk as contract_dyhod_user_id,impact_fk,"
					+ " i.remarks,corrective_measure_long_term,i.created_by as reported_by_user_id,u6.email_id as reported_by_email_id,"
					+ "corrective_measure_short_term,"
					+ "(select STRING_AGG(user_name,',') from safety_committee_members cmb left join [user] u7 on cmb.committee_member_name = u7.user_id where safety_id_fk = i.safety_id) as committe_members,u7.user_name as modified_by "
					+ "from safety i " 
					+ "left outer join [user] u2 on i.responsible_person = u2.user_id "
					+ "LEFT OUTER JOIN contract c ON i.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u4 on c.hod_user_id_fk = u4.user_id "
					+ "left outer join [user] u5 on c.dy_hod_user_id_fk = u5.user_id "
					+ "left outer join [user] u6 on i.created_by = u6.user_id "
					+ "left outer join [user] u7 on i.modified_by = u7.user_id "
					+ "left outer join [user] u8 on i.nominated_authority = u8.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "where safety_id = ?   ";

			Object[] pValues = new Object[] { safety_id };
			

			Safety iObj = (Safety) jdbcTemplate.queryForObject(emailsQry, pValues,new BeanPropertyRowMapper<Safety>(Safety.class));
			
			if(!StringUtils.isEmpty(iObj)) {
				
				String committe_user_ids_query = "select user_name as committee_member_name from safety_committee_members m left join [user] u on u.user_id=m.committee_member_name where safety_id_fk = ? group by user_name";
				String committe_user_email_ids_query = "select email_id FROM [user] where user_id in(select committee_member_name from safety_committee_members where safety_id_fk = ? group by committee_member_name)";
				String committe_users_query = "select user_id FROM [user] where user_id in(select committee_member_name from safety_committee_members where safety_id_fk = ? group by committee_member_name)";

				
				List<String> committe_user_ids = jdbcTemplate.queryForList(committe_user_ids_query,new Object[]{safety_id}, String.class);
				List<String> committe_user_email_ids = jdbcTemplate.queryForList( committe_user_email_ids_query,new Object[]{safety_id},String.class);
				List<String> committe_users = jdbcTemplate.queryForList( committe_users_query,new Object[]{safety_id},String.class);
				
				
				/*********************************************************************************************/
				NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

				String issueMessageQry = "INSERT INTO messages (message,user_id_fk,redirect_url,created_date,message_type)"
						+ "VALUES" + "(:message,:user_id_fk,:redirect_url,CURRENT_TIMESTAMP,:message_type)";
				String[] existing_committe_members_arr = null;
				if(!StringUtils.isEmpty(existing_committe_members)) {
					existing_committe_members_arr = existing_committe_members.split(",");
				}


				
				String message1 = "An incident against " + iObj.getContract_short_name() + " has been raised.";
				
				String message2 = "An incident against " + iObj.getContract_short_name() + " has been assigned to you as a Committee member.";

				String message3 = "An incident against " + iObj.getContract_short_name() + " has been updated";
				String message5 = "An incident against " + iObj.getContract_short_name() + " has been nominated to you";
				
				String message6 = "An incident against " + iObj.getContract_short_name() + " has been assigned to you as a Responsible Person";
				
				String message4 = "An incident against " + iObj.getContract_short_name() + " has been " + iObj.getStatus_fk();

				String hod_user_id = iObj.getContract_hod_user_id();
				String dy_hod_user_id = iObj.getContract_dyhod_user_id();
				String responsible_person_user_id = iObj.getResponsible_person_user_id();
				String reported_by_user_id = iObj.getReported_by_user_id();
				String nominated_authority = iObj.getNominated_authority_email_id();
				
				
				String redirect_url = "/get-safety?safety_id=" + iObj.getSafety_id();
				
				String message_type = "Safety";
				
				if(!"Update".equals(action_type)){		
					if(!StringUtils.isEmpty(hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(hod_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
					if(!StringUtils.isEmpty(dy_hod_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(dy_hod_user_id);
						msgObj.setMessage(message1);
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
					if(!StringUtils.isEmpty(reported_by_user_id)) {
						Messages msgObj = new Messages();
						msgObj.setUser_id_fk(reported_by_user_id);
						msgObj.setMessage(message1);
						msgObj.setRedirect_url(redirect_url);
						msgObj.setMessage_type(message_type);
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
						template.update(issueMessageQry, paramSource);
					}
				} else {
					if(!StringUtils.isEmpty(iObj.getStatus_fk()) && "Closed".equals(iObj.getStatus_fk())) {
						
						for (String user_id : committe_users) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(user_id);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						
						if(!StringUtils.isEmpty(hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(hod_user_id);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(dy_hod_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(dy_hod_user_id);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(responsible_person_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(responsible_person_user_id);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(reported_by_user_id)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(reported_by_user_id);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(nominated_authority)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(nominated_authority);
							msgObj.setMessage(message4);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}						
					}else if(!StringUtils.isEmpty(iObj.getStatus_fk())
							&& iObj.getStatus_fk().equals(existing_status_fk)) {
						for (String user_id : committe_users) {
							boolean exst_flag = false;
							if(!StringUtils.isEmpty(existing_committe_members_arr)) {
								for (String existing_committe_member : existing_committe_members_arr) {
									if(!StringUtils.isEmpty(user_id) && !StringUtils.isEmpty(existing_committe_member) && 
											user_id.equals(existing_committe_member)) {
										exst_flag = true;
									}
								}
							}
							String message = "";
							if(exst_flag)message = message3; else message = message2;
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(user_id);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}
						if(!StringUtils.isEmpty(responsible_person_user_id)) {
							String message = "";
							if(responsible_person_user_id.equals(existing_responsible_person))message = message3; else message = message1;
							
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
						if(!StringUtils.isEmpty(nominated_authority)) {
							Messages msgObj = new Messages();
							msgObj.setUser_id_fk(nominated_authority);
							msgObj.setMessage(message3);
							msgObj.setRedirect_url(redirect_url);
							msgObj.setMessage_type(message_type);
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
							template.update(issueMessageQry, paramSource);
						}						
						
					}
					else if(!StringUtils.isEmpty(iObj.getCorrective_measure_short_term()))
							{
								for (String user_id : committe_users) {
									boolean exst_flag = false;
									if(!StringUtils.isEmpty(existing_committe_members_arr)) {
										for (String existing_committe_member : existing_committe_members_arr) {
											if(!StringUtils.isEmpty(user_id) && !StringUtils.isEmpty(existing_committe_member) && 
													user_id.equals(existing_committe_member)) {
												exst_flag = true;
											}
										}
									}
									String message = "";
									if(exst_flag)message = message3; else message = message2;
									Messages msgObj = new Messages();
									msgObj.setUser_id_fk(user_id);
									msgObj.setMessage(message3);
									msgObj.setRedirect_url(redirect_url);
									msgObj.setMessage_type(message_type);
									BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(msgObj);
									template.update(issueMessageQry, paramSource);
								}
								if(!StringUtils.isEmpty(responsible_person_user_id)) {
									String message = "";
									if(responsible_person_user_id.equals(existing_responsible_person))message = message3; else message = message1;
									
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
								if(!StringUtils.isEmpty(nominated_authority)) {
									Messages msgObj = new Messages();
									msgObj.setUser_id_fk(nominated_authority);
									msgObj.setMessage(message3);
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
				
				
				if(!"Update".equals(action_type)){
					if("Open".equals(iObj.getStatus_fk())) {
						if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
							mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
						}
						else if(!StringUtils.isEmpty(reported_by_user_id)) 
						{
								if(getReported_by_email_id(reported_by_user_id)!=null)
								{
									mailTo = mailTo + getReported_by_email_id(reported_by_user_id) + ",";
								}
								else
								{
									if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
										mailTo = mailTo + iObj.getContract_dyhod_email_id() + ",";
									}
									if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
										mailTo = mailTo + iObj.getContract_hod_email_id() + ",";
									}									
								}
						}							
						if(getReported_by_email_id(reported_by_user_id)!=null)
						{
							if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
								mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
							}
							if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
								mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
							}
						}
					}					
				} else {
					if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
					}
					if(!StringUtils.isEmpty(iObj.getNominated_authority())) {
						mailTo = mailTo + getReported_by_email_id(iObj.getNominated_authority_email_id()) + ",";
					}
					
					if(!StringUtils.isEmpty(committe_user_email_ids)) {
						for (String committe_user_email_id : committe_user_email_ids) {
							mailTo = mailTo + committe_user_email_id + ",";
						}
					}					

					if(!StringUtils.isEmpty(iObj.getResponsible_person_email_id())) {
						mailTo = mailTo + iObj.getResponsible_person_email_id() + ",";
					}	

					if(!StringUtils.isEmpty(iObj.getContract_hod_email_id())) {
						mailCC = mailCC + iObj.getContract_hod_email_id() + ",";
					}
					if(!StringUtils.isEmpty(iObj.getContract_dyhod_email_id())) {
						mailCC = mailCC + iObj.getContract_dyhod_email_id() + ",";
					}	
					if(!StringUtils.isEmpty(committe_user_email_ids)) {
						for (String committe_user_email_id : committe_user_email_ids) {
							mailTo = mailTo + committe_user_email_id + ",";
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

				if(!"Update".equals(action_type)) {
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

				if(!"Update".equals(action_type)) {
					mailBodyHeader = mailBodyHeader + "added.";
				}else if(!"Closed".equals(iObj.getStatus_fk())){
					
					if(!StringUtils.isEmpty(iObj.getNominated_authority())) {
						mailBodyHeader = mailBodyHeader+" "+iObj.getResponsible_person() + " You have been added for updating the safety incident details by "+iObj.getModified_by();
						String CommitteUsers="";
						if(!StringUtils.isEmpty(committe_user_ids)) 
						{
							for (String committe_user : committe_user_ids) 
							{
								CommitteUsers = CommitteUsers + committe_user + ",";
							}
						}
						mailBodyHeader = mailBodyHeader+" Committee members are "+CommitteUsers;
						mailBodyHeader = mailBodyHeader+" "+iObj.getNominated_authority()+" "+": You have been added for reviewing and approving the safety incident details proposed by " ;
					}
					else
					{
						mailBodyHeader = mailBodyHeader + "updated ";
					}					
				}else{
					mailBodyHeader = mailBodyHeader + iObj.getStatus_fk();
				}


				iObj.setMail_body_header(mailBodyHeader);

				String emailSubject = "PMIS Safety Notification - An incident ";
				
				if(!"Update".equals(action_type)) {
					emailSubject = emailSubject + "added.";
				}else if(!"Closed".equals(iObj.getStatus_fk())){

					if(!StringUtils.isEmpty(iObj.getNominated_authority())) {
						emailSubject = "Nomination of Responsible Person & Approval Authority for Safety Incident.";
					}
					else
					{
						emailSubject = emailSubject  + "updated.";
					}
				}else{
					emailSubject = emailSubject + iObj.getStatus_fk();
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
					emailSender.sendEmailWithSafetyStatusAlert(mail, iObj, today_date, current_year);
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	private String getReported_by_email_id(String reported_by_user_id) throws Exception
	{
		String Reported_by_email_id="";
		try {
			String qry = "select email_id FROM [user] where user_id = ?";
			Reported_by_email_id = (String) jdbcTemplate.queryForObject(qry, new Object[] { reported_by_user_id }, String.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return Reported_by_email_id;
	}	

	@Override
	public Safety getSafety(Safety obj) throws Exception {
		Safety sobj = null;
		try {
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,c.hod_user_id_fk,c.dy_hod_user_id_fk,w.work_short_name,title,description,FORMAT(date,'dd-MM-yyyy') AS date,location,latitude,longitude,reported_by,responsible_person,u.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,FORMAT(closure_date,'dd-MM-yyyy') AS closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "FORMAT(investigation_completed,'dd-MM-yyyy') AS investigation_completed,corrective_measure_short_term,corrective_measure_long_term,compensation,FORMAT(payment_date,'dd-MM-yyyy') AS payment_date,s.remarks,contract_name,"
					+ "work_id_fk,work_name,safety_seq_id,project_id_fk,project_name,s.compensation_units,s.committee_member_name,"
					+ "(select STRING_AGG(committee_member_name,',') from safety_committee_members where safety_id_fk = ?) as committe_members,nominated_authority,safety_incident,approve_corrective_measure "
					+ "from safety s "
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "where safety_id = ? ";
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ((s.reported_by = '"+obj.getUser_id()+"' or s.nominated_authority = '"+obj.getUser_id()+"' or s.responsible_person = '"+obj.getUser_id()+"' or s.created_by = '"+obj.getUser_id()+"' or c.hod_user_id_fk = '"+obj.getUser_id()+"' or c.dy_hod_user_id_fk = '"+obj.getUser_id()+"' "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = '"+obj.getUser_id()+"'))";
				
				qry = qry + "or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"' " + 
						"union all " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')  " + 
						"or dy_hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')) as a) )";				
			}	
			
			sobj = (Safety)jdbcTemplate.queryForObject( qry, new Object[]{obj.getSafety_id(),obj.getSafety_id()}, new BeanPropertyRowMapper<Safety>(Safety.class));	
			
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
	public String updateSafety(Safety obj) throws Exception {
		boolean flag = false;
		String safetyid = null;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			connection = dataSource.getConnection();
			String qry = "UPDATE safety SET contract_id_fk=:contract_id_fk,hod_user_id_fk=:hod_user_id_fk,title=:title,description=:description,date=:date,location=:location,latitude=:latitude,longitude=:longitude,reported_by=:reported_by,responsible_person=:responsible_person,category_fk=:category_fk,impact_fk=:impact_fk,root_cause_fk=:root_cause_fk,status_fk=:status_fk,"
					+ "closure_date=:closure_date,lti_hours=:lti_hours,equipment_impact=:equipment_impact,people_impact=:people_impact,work_impact=:work_impact,committee_formed_fk=:committee_formed_fk,committee_required_fk = :committee_required_fk,investigation_completed=:investigation_completed,corrective_measure_short_term=:corrective_measure_short_term,"
					+ "corrective_measure_long_term=:corrective_measure_long_term,compensation=:compensation,payment_date=:payment_date,remarks=:remarks,compensation_units=:compensation_units,committee_member_name=:committee_member_name,modified_by=:created_by_user_id_fk,modified_date=CURRENT_TIMESTAMP,nominated_authority=:nominated_authority,safety_incident=:safety_incident,approve_corrective_measure=:approve_corrective_measure  "
					+ "WHERE safety_id = :safety_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = template.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
				safetyid=obj.getSafety_seq_id();
			}
			if(flag) {
				String qryDelete = "select * from safety_files where safety_id_fk = ?";
				stmt = connection.prepareStatement(qryDelete);
				stmt.setString(1,obj.getSafety_id());
				ResultSet rs = null;
				rs = stmt.executeQuery();
				
		        /*if (rs != null) 
		        {
		            while (rs.next()) 
		            {
		            	String saveDirectory = CommonConstants2.SAFETY_FILE_SAVING_PATH;
		            	File file = new File(saveDirectory);
		            	deleteFiles(file,rs.getString("attachment"));
		            }
		        }*/
		        
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
							String fileName_new = "Safety-"+obj.getSafety_id()+""+getAlphaNumericString(8) +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
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
				String existing_status_fk = obj.getExisting_status_fk();
				String existing_responsible_person = obj.getExisting_responsible_person();
				
				sendEmailWithSafetyStatusAlert(obj.getUser_id(),obj.getUser_role_code(),safety_id, "Update", existing_status_fk,existing_responsible_person, obj.getExisting_committe_members());
				
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
		return safetyid;
	}
	
	public static String getAlphaNumericString(int n)
		 {
		 
		  // chose a Character random from this String
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		 
		  // create StringBuffer size of AlphaNumericString
		  StringBuilder sb = new StringBuilder(n);
		 
		  for (int i = 0; i < n; i++) {
		 
		   // generate a random number between
		   // 0 to AlphaNumericString variable length
		   int index
		    = (int)(AlphaNumericString.length()
		      * Math.random());
		 
		   // add Character one by one in end of sb
		   sb.append(AlphaNumericString
		      .charAt(index));
		  }
		 
		  return sb.toString();
		 }	
	
	public static void deleteFiles(File dirPath,String filename) {
	      File filesList[] = dirPath.listFiles();
	      for(File file : filesList) 
	      {
	    	 String pathName=file.getName();
	         if(file.isFile() && pathName.compareTo(filename)==0) 
	         {
	            file.delete();
	         } 
	      }
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
			}	
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY contract_id_fk,c.contract_id,contract_name,contract_short_name";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ "where u.department_fk is not null and u.department_fk <> ''  ";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
			}				
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY u.department_fk,department,department_name";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ " where category_fk is not null and category_fk <> ''  ";
			
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ " where status_fk is not null and status_fk <> ''  ";
			
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ " where work_id_fk is not null and work_id_fk <> ''  ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory_fk())) {
				qry = qry + " and category_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getStatus_fk())) {
				qry = qry + " and status_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?)) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}

			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
			}	
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY work_id,work_short_name";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
					+ "LEFT OUTER JOIN department d ON u.department_fk  = d.department "
					+ "where c.hod_user_id_fk is not null and c.hod_user_id_fk <> ''  ";
					
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
			}				
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " GROUP BY c.hod_user_id_fk,u.designation,u.user_name,u.designation ORDER BY case when u.designation='ED Civil' then 1  " + 
					"   when u.designation='CPM I' then 2  " + 
					"   when u.designation='CPM II' then 3 " + 
					"   when u.designation='CPM III' then 4  " + 
					"   when u.designation='CPM V' then 5 " + 
					"   when u.designation='CE' then 6  " + 
					"   when u.designation='ED S&T' then 7  " + 
					"   when u.designation='CSTE' then 8 " + 
					"   when u.designation='GM Electrical' then 9 " + 
					"   when u.designation='CEE Project I' then 10 " + 
					"   when u.designation='CEE Project II' then 11 " + 
					"   when u.designation='ED Finance & Planning' then 12 " + 
					"   when u.designation='AGM Civil' then 13 " + 
					"   when u.designation='DyCPM Civil' then 14 " + 
					"   when u.designation='DyCPM III' then 15 " + 
					"   when u.designation='DyCPM V' then 16 " + 
					"   when u.designation='DyCE EE' then 17 " + 
					"   when u.designation='DyCE Badlapur' then 18 " + 
					"   when u.designation='DyCPM Pune' then 19 " + 
					"   when u.designation='DyCE Proj' then 20 " + 
					"   when u.designation='DyCEE I' then 21 " + 
					"   when u.designation='DyCEE Projects' then 22 " + 
					"   when u.designation='DyCEE PSI' then 23 " + 
					"   when u.designation='DyCSTE I' then 24 " + 
					"   when u.designation='DyCSTE IT' then 25 " + 
					"   when u.designation='DyCSTE Projects' then 26 " + 
					"   when u.designation='XEN Consultant' then 27 " + 
					"   when u.designation='AEN Adhoc' then 28 " + 
					"   when u.designation='AEN Project' then 29 " + 
					"   when u.designation='AEN P-Way' then 30 " + 
					"   when u.designation='AEN' then 31 " + 
					"   when u.designation='Sr Manager Signal' then 32  " + 
					"   when u.designation='Manager Signal' then 33 " + 
					"   when u.designation='Manager Civil' then 34  " + 
					"   when u.designation='Manager OHE' then 35 " + 
					"   when u.designation='Manager GS' then 36 " + 
					"   when u.designation='Manager Finance' then 37 " + 
					"   when u.designation='Planning Manager' then 38 " + 
					"   when u.designation='Manager Project' then 39 " + 
					"   when u.designation='Manager' then 40  " + 
					"   when u.designation='SSE' then 41 " + 
					"   when u.designation='SSE Project' then 42 " + 
					"   when u.designation='SSE Works' then 43 " + 
					"   when u.designation='SSE Drg' then 44 " + 
					"   when u.designation='SSE BR' then 45 " + 
					"   when u.designation='SSE P-Way' then 46 " + 
					"   when u.designation='SSE OHE' then 47 " + 
					"   when u.designation='SPE' then 48 " + 
					"   when u.designation='PE' then 49 " + 
					"   when u.designation='JE' then 50 " + 
					"   when u.designation='Demo-HOD-Elec' then 51 " + 
					"   when u.designation='Demo-HOD-Engg' then 52 " + 
					"   when u.designation='Demo-HOD-S&T' then 53 " + 
					" " + 
					"   end asc" ;
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
			String qry = "select distinct project_id as project_id_fk,project_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "LEFT OUTER JOIN contract c ON w.work_id  = c.work_id_fk "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "where work_id is not null  ";
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				/*qry = qry + " and c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"' " + 
						"union all " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')  " + 
						"or dy_hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')) as a) ";	*/	
				
				qry = qry + " and c.contract_id in(SELECT contract_id_fk FROM contract_executive where executive_user_id_fk='"+obj.getUser_id()+"'" + 
						") ";				
				
			}
			
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
			String qry = "select distinct work_id as work_id_fk,work_name,work_short_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "LEFT OUTER JOIN contract c ON w.work_id  = c.work_id_fk "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "where work_id is not null  ";
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				/*qry = qry + " and c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"' " + 
						"union all " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')  " + 
						"or dy_hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')) as a) ";	*/	
				qry = qry + " and c.contract_id in(SELECT contract_id_fk FROM contract_executive where executive_user_id_fk='"+obj.getUser_id()+"'" + 
						") ";					
				
			}			
					
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
					+ "left join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "where c.contract_id is not null ";
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				/*qry = qry + " and c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"' " + 
						"union all " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')  " + 
						"or dy_hod_user_id_fk=(select reporting_to_id_srfk from [user] where user_id='"+obj.getUser_id()+"')) as a) ";*/
				qry = qry + " and c.contract_id in(SELECT contract_id_fk FROM contract_executive where executive_user_id_fk='"+obj.getUser_id()+"'" + 
						") ";					
				
			}			
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {			
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
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {			
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
					+ "FROM [user] "
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
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				
				if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
					qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
							"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
				}					
				
				arrSize++;
				arrSize++;
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
						+ "or u2.designation like ? or department_name like ? or category_fk like ? or status_fk like ? or safety_seq_id like ?)";
				arrSize++;
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
			String qry = "SELECT safety_id,contract_id_fk,s.hod_user_id_fk,u.designation,c.contract_short_name,title,d.department_name,description,FORMAT(date,'dd-MM-yyyy') AS date,location,latitude,longitude,reported_by,u2.designation as responsible_person,u.department_fk,"
					+ "category_fk,impact_fk,root_cause_fk,status_fk,FORMAT(closure_date,'dd-MM-yyyy') AS closure_date,lti_hours,equipment_impact,people_impact,work_impact,committee_formed_fk,committee_required_fk,"
					+ "FORMAT(investigation_completed,'dd-MM-yyyy') AS investigation_completed,safety_seq_id,corrective_measure_short_term,corrective_measure_long_term,compensation,FORMAT(payment_date,'dd-MM-yyyy') AS payment_date,s.remarks,contract_name,work_id_fk,work_name,project_id_fk,project_name,s.modified_by,FORMAT(s.modified_date,'dd-MM-yyyy') as modified_date  "
					+ "from safety s "
					+ "left outer join [user] u2 on s.responsible_person = u2.user_id "					
					+ "LEFT OUTER JOIN contract c ON s.contract_id_fk  = c.contract_id "
					+ "left outer join [user] u ON c.hod_user_id_fk= u.user_id "
					+ "LEFT OUTER JOIN work w ON c.work_id_fk  = w.work_id "
					+ "LEFT OUTER JOIN project p ON w.project_id_fk  = p.project_id "
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ((s.reported_by = ? or s.nominated_authority = ? or s.responsible_person = ? or s.created_by = ? or c.hod_user_id_fk = ? or c.dy_hod_user_id_fk = ? "
						+ "or s.safety_id in(select safety_id_fk from safety_committee_members where committee_member_name = ?))";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;				
			}
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " or c.contract_id in(select distinct contract_id from( " + 
						"SELECT distinct contract_id FROM contract where hod_user_id_fk='"+obj.getUser_id()+"' or dy_hod_user_id_fk='"+obj.getUser_id()+"') as a)) ";				
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod_user_id_fk())) {
				qry = qry + " and c.hod_user_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.contract_id like ? or c.contract_short_name like ? or title like ? or location like ? "
						+ "or u2.designation like ? or department_name like ? or category_fk like ? or status_fk like ? or safety_seq_id like ?)";
				arrSize++;
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
				qry = qry + " order by safety_id ASC offset ? rows  fetch next ? rows only";
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
			
			if(!StringUtils.isEmpty(obj) && !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();				
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
			String qry = "SELECT designation,user_name,user_id,reporting_to_id_srfk FROM [user] where designation <> '' and user_name not like '%user%' and pmis_key_fk not like '%SGS%' and user_type_fk not in('Others') ";
			/*int arrSize = 0;
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
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Safety>(Safety.class));*/

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Safety> getNominatedAuthorityListForSafetyForm(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT designation,user_name,user_id,reporting_to_id_srfk FROM [user] where user_type_fk is not null and user_type_fk in('HOD','DyHOD','Management') ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

	@Override
	public List<Safety> getUersList(Safety obj) throws Exception {
		List<Safety> objsList = null;
		try {
			String qry = "SELECT user_id,user_name,designation,user_type_fk FROM [user] where user_type_fk in ('HOD','DyHOD') ORDER BY case when user_type_fk='HOD' then 1 " + 
					"when user_type_fk='DyHOD' then 2  end asc ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Safety>(Safety.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	

}
