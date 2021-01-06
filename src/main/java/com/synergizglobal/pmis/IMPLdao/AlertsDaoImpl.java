package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Alerts;
@Repository
public class AlertsDaoImpl implements AlertsDao{
	
	public static Logger logger = Logger.getLogger(AlertsDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Override
	public boolean generateAtertsByCronJob() throws Exception {
		boolean flag = false;
		List<Alerts> list = new ArrayList<Alerts>();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			
			/***************************** BG alerts*******************************************************/
			String bgQryAlert1 = "select bg.contract_id_fk as contract_id, '1st Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email " 
					+ " from contract c " + 
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk " 
					+ "left outer join user u1 on c.hod_user_id_fk = u1.user_id "
					+ "left outer join user u2 on c.dy_hod_user_id_fk = u2.user_id " 
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 15) and release_date is null";
			
			List<Alerts> bgQryAlert1List = jdbcTemplate.query( bgQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert1List) && bgQryAlert1List.size() > 0) {
				list.addAll(bgQryAlert1List);
			}			
			
			String bgQryAlert2 = "select bg.contract_id_fk as contract_id, '2nd Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email " 
					+ " from contract c " +
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk " 
					+ "left outer join user u1 on c.hod_user_id_fk = u1.user_id "
					+ "left outer join user u2 on c.dy_hod_user_id_fk = u2.user_id " 
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 15 and DATEDIFF(valid_upto ,NOW()) > 7) and release_date is null";
			
			List<Alerts> bgQryAlert2List = jdbcTemplate.query( bgQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert2List) && bgQryAlert2List.size() > 0) {
				list.addAll(bgQryAlert2List);
			}	
			
			String bgQryAlert3 = "select bg.contract_id_fk as contract_id, '3rd Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email " 
					+ " from contract c " + 
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk " 
					+ "left outer join user u1 on c.hod_user_id_fk = u1.user_id "
					+ "left outer join user u2 on c.dy_hod_user_id_fk = u2.user_id " 
					+ "where contract_status_fk = 'In Progress' and DATEDIFF(valid_upto ,NOW()) <= 7 and release_date is null";
			
			List<Alerts> bgQryAlert3List = jdbcTemplate.query( bgQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert3List) && bgQryAlert3List.size() > 0) {
				list.addAll(bgQryAlert3List);
			}
			
			/***************************** Insurance alerts*******************************************************/
			String insuranceQryAlert1 = "select bg.contract_id_fk as contract_id, '1st Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email "
					+ "from contract c " + 
					"left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+ "left outer join user u1 on c.hod_user_id_fk = u1.user_id "
					+ "left outer join user u2 on c.dy_hod_user_id_fk = u2.user_id " 
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 15) and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert1List = jdbcTemplate.query( insuranceQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert1List) && insuranceQryAlert1List.size() > 0) {
				list.addAll(insuranceQryAlert1List);
			}
			
			String insuranceQryAlert2 = "select bg.contract_id_fk as contract_id, '2nd Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email "
					+ " from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+ "left outer join user u1 on c.hod_user_id_fk = u1.user_id "
					+ "left outer join user u2 on c.dy_hod_user_id_fk = u2.user_id " 
					+ "where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 15 and DATEDIFF(valid_upto ,NOW()) > 7) and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert2List = jdbcTemplate.query( insuranceQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert2List) && insuranceQryAlert2List.size() > 0) {
				list.addAll(insuranceQryAlert2List);
			}
			
			String insuranceQryAlert3 = "select bg.contract_id_fk as contract_id, '3rd Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email "
					+ " from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk "
					+ "left outer join user u1 on c.hod_user_id_fk = u1.user_id "
					+ "left outer join user u2 on c.dy_hod_user_id_fk = u2.user_id "
					+ "where contract_status_fk = 'In Progress' and DATEDIFF(valid_upto ,NOW()) <= 7 and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert3List = jdbcTemplate.query( insuranceQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert3List) && insuranceQryAlert3List.size() > 0) {
				list.addAll(insuranceQryAlert3List);
			}
			
			
			/***************************** Contract Period alerts*******************************************************/
			String cpQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Contract Period' as alert_type,"
					+ "(case when contract_revised_date is not null and doc is null then CONCAT('Contract revised date : ',DATE_FORMAT(contract_revised_date,'%d-%b-%Y') ) " 
					+ "when contract_revised_date is null and doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else null end ) as alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' and ((contract_revised_date is not null and doc is null and DATEDIFF(contract_revised_date ,NOW()) <= 30 and DATEDIFF(contract_revised_date ,NOW()) > 15) or (contract_revised_date is null and doc is not null and DATEDIFF(doc ,NOW()) <= 30 and DATEDIFF(doc ,NOW()) > 15))";
			
			List<Alerts> cpQryAlert1List = jdbcTemplate.query( cpQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert1List) && cpQryAlert1List.size() > 0) {
				list.addAll(cpQryAlert1List);
			}
			
			String cpQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Period' as alert_type,"
					+ "(case when contract_revised_date is not null and doc is null then CONCAT('Contract revised date : ',DATE_FORMAT(contract_revised_date,'%d-%b-%Y') ) " 
					+ "when contract_revised_date is null and doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else null end ) as alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' and ((contract_revised_date is not null and doc is null and DATEDIFF(contract_revised_date ,NOW()) <= 15 and DATEDIFF(contract_revised_date ,NOW()) > 7) or (contract_revised_date is null and doc is not null and DATEDIFF(doc ,NOW()) <= 15 and DATEDIFF(doc ,NOW()) > 7))";
			
			List<Alerts> cpQryAlert2List = jdbcTemplate.query( cpQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert2List) && cpQryAlert2List.size() > 0) {
				list.addAll(cpQryAlert2List);
			}
			
			String cpQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Period' as alert_type,"
					+ "(case when contract_revised_date is not null and doc is null then CONCAT('Contract revised date : ',DATE_FORMAT(contract_revised_date,'%d-%b-%Y') ) " 
					+ "when contract_revised_date is null and doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else null end ) as alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' and ((contract_revised_date is not null and doc is null and DATEDIFF(contract_revised_date ,NOW()) <= 7) or (contract_revised_date is null and doc is not null and DATEDIFF(doc ,NOW()) <= 7))";
			
			List<Alerts> cpQryAlert3List = jdbcTemplate.query( cpQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert3List) && cpQryAlert3List.size() > 0) {
				list.addAll(cpQryAlert3List);
			}
			
			/***************************** Contract Value alerts*******************************************************/
			String cvQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',cumulative_expenditure, ', Revised cost : ',revised_cost ) AS alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and ((cumulative_expenditure is not null and revised_cost is not null and completed_cost is null and ((revised_cost * 100) / cumulative_expenditure) > 95 and ((revised_cost * 100) / cumulative_expenditure) <= 120) or (cumulative_expenditure is not null and completed_cost is not null and revised_cost is null and ((completed_cost * 100) / cumulative_expenditure) > 95 and ((completed_cost * 100) / cumulative_expenditure) <= 120))";
			
			List<Alerts> cvQryAlert1List = jdbcTemplate.query( cvQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert1List) && cvQryAlert1List.size() > 0) {
				list.addAll(cvQryAlert1List);
			}
			
			String cvQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',cumulative_expenditure, ', Revised cost : ',revised_cost ) AS alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and ((cumulative_expenditure is not null and revised_cost is not null and completed_cost is null and ((revised_cost * 100) / cumulative_expenditure) > 120  and ((revised_cost * 100) / cumulative_expenditure) <= 145) or (cumulative_expenditure is not null and completed_cost is not null and revised_cost is null and ((completed_cost * 100) / cumulative_expenditure) >= 120 and ((completed_cost * 100) / cumulative_expenditure) <= 145))";
			
			List<Alerts> cvQryAlert2List = jdbcTemplate.query( cvQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert2List) && cvQryAlert2List.size() > 0) {
				list.addAll(cvQryAlert2List);
			}
			
			String cvQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',cumulative_expenditure, ', Revised cost : ',revised_cost ) AS alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and ((cumulative_expenditure is not null and revised_cost is not null and completed_cost is null and ((revised_cost * 100) / cumulative_expenditure) > 145) or (cumulative_expenditure is not null and completed_cost is not null and revised_cost is null and ((completed_cost * 100) / cumulative_expenditure) > 145))";
			
			List<Alerts> cvQryAlert3List = jdbcTemplate.query( cvQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert3List) && cvQryAlert3List.size() > 0) {
				list.addAll(cvQryAlert3List);
			}
			
			/***************************** Physical progress alerts*******************************************************/
			/*String ppQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Physical Progress' as alert_type,"
					+ "CONCAT('Physical planned : ',TRUNCATE(physical_planned*100, 2),'%, Physical actual : ',TRUNCATE(physical_actual*100, 2),'%' ) AS alert_value"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and physical_planned is not null and physical_actual is not null and (((physical_planned-physical_actual)*100)/physical_planned) >= 20 and (((physical_planned-physical_actual)*100)/physical_planned) <= 30";
			
			List<Alerts> ppQryAlert1List = jdbcTemplate.query( ppQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(ppQryAlert1List) && ppQryAlert1List.size() > 0) {
				list.addAll(ppQryAlert1List);
			}
			
			String ppQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Physical Progress' as alert_type,"
					+ "CONCAT('Physical planned : ',TRUNCATE(physical_planned*100, 2),'%, Physical actual : ',TRUNCATE(physical_actual*100, 2),'%' ) AS alert_value"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and physical_planned is not null and physical_actual is not null and (((physical_planned-physical_actual)*100)/physical_planned) >= 31  and (((physical_planned-physical_actual)*100)/physical_planned) <= 39";
			
			List<Alerts> ppQryAlert2List = jdbcTemplate.query( ppQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(ppQryAlert2List) && ppQryAlert2List.size() > 0) {
				list.addAll(ppQryAlert2List);
			}
			
			String ppQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Physical Progress' as alert_type,"
					+ "CONCAT('Physical planned : ',TRUNCATE(physical_planned*100, 2),'%, Physical actual : ',TRUNCATE(physical_actual*100, 2),'%' ) AS alert_value"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and physical_planned is not null and physical_actual is not null and (((physical_planned-physical_actual)*100)/physical_planned) >= 40";
			
			List<Alerts> ppQryAlert3List = jdbcTemplate.query( ppQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(ppQryAlert3List) && ppQryAlert3List.size() > 0) {
				list.addAll(ppQryAlert3List);
			}*/
			
			
			/*************************Alerts insertion********************************************/
			String updateQry = "update alerts set alert_status = ? where contract_id is not null and contract_id <> '' and count <> 0";	
			Object[] pValues = new Object[] {CommonConstants.INACTIVE};
			jdbcTemplate.update(updateQry,pValues);	
			
			
			String qryUserPermissions = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,hod_email,dy_hod_email) VALUES  (?,?,?,?,?,?,?,?)";		
			
			int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions, new BatchPreparedStatementSetter() { 
								@Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
				                    ps.setString(1, list.get(i).getAlert_level());
				                    ps.setString(2, list.get(i).getAlert_type());
				                    ps.setString(3, list.get(i).getContract_id());
				                    ps.setString(4, CommonConstants.ACTIVE);
				                    ps.setString(5, list.get(i).getAlert_value());
				                    ps.setString(6, "1");
				                    ps.setString(7, list.get(i).getHod_email());
				                    ps.setString(8, list.get(i).getDy_hod_email());
				                }
				                @Override  
				                public int getBatchSize() {
				                	 return list.size();
				                }
		               	});
			transactionManager.commit(status);	
			
			if(counts.length > 0) {
				flag =  true;
			}
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	
	@Override
	public boolean sendNotificationAlertMails() throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			String dyHODQry ="select group_concat(distinct dy_hod_email) from alerts where alert_status = ? and dy_hod_email is not null and dy_hod_email <> '' and contract_id is not null and contract_id <> '' and count <> 0 group by alert_status";
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			String dyHODEmails = jdbcTemplate.queryForObject( dyHODQry,pValues, String.class);
			
			String hodQry ="select group_concat(distinct hod_email) from alerts where alert_status = ? and hod_email is not null and hod_email <> '' and contract_id is not null and contract_id <> '' and count <> 0 group by alert_status";
			pValues = new Object[] {CommonConstants.ACTIVE};
			String hodEmails = jdbcTemplate.queryForObject( hodQry,pValues, String.class);
						
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,hod,work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email  " + 
					"from alerts a " + 
					"left outer join contract_view cv on a.contract_id COLLATE utf8mb4_unicode_ci = cv.contract_id " + 
					"where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
			
			pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/*dyfacao1@mrvc.gov.in - 1st 2nd 3rd of BG & Insurance
			facao2@mrvc.gov.in - 2nd 3rd of BG & Insurance
			df@mrvc.gov.in - 3rd of BG & Insurance
			
			dy HOD - 1st, 2nd 3rd alert of their contracts
			HOD - 2nd, 3rd alerts of their contracts
			cmd@mrvc.gov.in - only 3rd alerts of all contracts..*/
			
			
			List<Alerts> dyfacao1AlertsList = new ArrayList<Alerts>();
			List<Alerts> facao2AlertsList = new ArrayList<Alerts>();
			List<Alerts> dfAlertsList = new ArrayList<Alerts>();
			
			if(!StringUtils.isEmpty(allAlertsList) && allAlertsList.size() > 0) {
				for (Alerts alerts : allAlertsList) {
					if(alerts.getAlert_type_fk().equals("Bank Guarantee") || alerts.getAlert_type_fk().equals("Insurance")) {
						dyfacao1AlertsList.add(alerts);
						if(alerts.getAlert_level().equals("3rd Alert")) {
							dfAlertsList.add(alerts);						
						}else {
							facao2AlertsList.add(alerts);
						}
					}
				}
			}
			
			if(!StringUtils.isEmpty(dyHODEmails)) {
				
				List<String> dyHODEmailsList = Arrays.asList(dyHODEmails.split(",", -1));
				for (String emailId : dyHODEmailsList) {
					List<Alerts> dyHodAlertsList = new ArrayList<Alerts>();
					for (Alerts alerts : allAlertsList) {
						if(!StringUtils.isEmpty(alerts.getDy_hod_email()) && alerts.getDy_hod_email().equals(emailId)) {
							dyHodAlertsList.add(alerts);						
						}
					}
					String emailSubject = "Upcoming alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(emailId);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					if(!StringUtils.isEmpty(dyHodAlertsList) && dyHodAlertsList.size() > 0){					
						logger.error("sendNotificationAlertMails() >> Sending mail to Dy HOD "+emailId+"> : Start ");	
						emailSender.sendEmailWithAlerts(mail,dyHodAlertsList); 
						logger.error("sendNotificationAlertMails() >> Sending mail to Dy HOD "+emailId+"> : End ");	
						flag = true;
					}
				}
			}
			/***************************************************************************/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(hodEmails)) {
				List<String> hodEmailsList = Arrays.asList(hodEmails.split(",", -1));
				for (String emailId : hodEmailsList) {
					List<Alerts> hodAlertsList = new ArrayList<Alerts>();
					for (Alerts alerts : allAlertsList) {
						if(!StringUtils.isEmpty(alerts.getHod_email()) && alerts.getHod_email().equals(emailId)) {
							hodAlertsList.add(alerts);						
						}
					}
					String emailSubject = "Upcoming alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(emailId);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					if(!StringUtils.isEmpty(hodAlertsList) && hodAlertsList.size() > 0){
						logger.error("sendNotificationAlertMails() >> Sending mail to HOD "+emailId+"> : Start ");	
						emailSender.sendEmailWithAlerts(mail,hodAlertsList); 
						logger.error("sendNotificationAlertMails() >> Sending mail to HOD "+emailId+"> : End ");	
						flag = true;
					}
				}
			}
			/***************************************************************************/
			
			/***************************************************************************/	
			List<Alerts> cmdAlertsList = new ArrayList<Alerts>();
			for (Alerts alerts : allAlertsList) {
				if(alerts.getAlert_level().equals("3rd Alert")) {
					cmdAlertsList.add(alerts);						
				}
			}
			
			if(!StringUtils.isEmpty(cmdAlertsList) && cmdAlertsList.size() > 0){
				String emailSubject = "Upcoming alerts";
				
				Mail mail = new Mail();
				mail.setMailTo("cmd@mrvc.gov.in");
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alerts.vm");
				
				logger.error("sendNotificationAlertMails() >> Sending mail to cmd@mrvc.gov.in: Start ");	
				emailSender.sendEmailWithAlerts(mail,cmdAlertsList); 
				logger.error("sendNotificationAlertMails() >> Sending mail to cmd@mrvc.gov.in: End ");	
				flag = true;
			}
			/***************************************************************************/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(dyfacao1AlertsList) && dyfacao1AlertsList.size() > 0){
				String emailSubject = "Upcoming alerts";
				
				Mail mail = new Mail();
				mail.setMailTo("dyfacao1@mrvc.gov.in");
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alerts.vm");
				
				logger.error("sendNotificationAlertMails() >> Sending mail to dyfacao1@mrvc.gov.in: Start ");	
				emailSender.sendEmailWithAlerts(mail,dyfacao1AlertsList); 
				logger.error("sendNotificationAlertMails() >> Sending mail to dyfacao1@mrvc.gov.in: End ");	
				flag = true;
			}
			/***************************************************************************/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(facao2AlertsList) && facao2AlertsList.size() > 0){
				String emailSubject = "Upcoming alerts";
				
				Mail mail = new Mail();
				mail.setMailTo("facao2@mrvc.gov.in");
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alerts.vm");
				
				logger.error("sendNotificationAlertMails() >> Sending mail to facao2@mrvc.gov.in: Start ");	
				emailSender.sendEmailWithAlerts(mail,facao2AlertsList); 
				logger.error("sendNotificationAlertMails() >> Sending mail to facao2@mrvc.gov.in: End ");	
				flag = true;
			}
			/***************************************************************************/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(dfAlertsList) && dfAlertsList.size() > 0){
				String emailSubject = "Upcoming alerts";
				
				Mail mail = new Mail();
				mail.setMailTo("df@mrvc.gov.in");
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alerts.vm");
				
				logger.error("sendNotificationAlertMails() >> Sending mail to df@mrvc.gov.in: Start ");	
				emailSender.sendEmailWithAlerts(mail,dfAlertsList); 
				logger.error("sendNotificationAlertMails() >> Sending mail to df@mrvc.gov.in: End ");	
				flag = true;
			}
			/***************************************************************************/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(allAlertsList) && allAlertsList.size() > 0){
				String emailSubject = "Upcoming alerts";
				
				Mail mail = new Mail();
				mail.setMailTo(CommonConstants2.ALERTS_EMAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alerts.vm");
					
				logger.error("sendNotificationAlertMails() >> Sending mail to rajiv.dhupkar@synergizglobal.com,raviteja.reddy@synergizglobal.com: Start ");	
				emailSender.sendEmailWithAlerts(mail,allAlertsList); 
				logger.error("sendNotificationAlertMails() >> Sending mail to rajiv.dhupkar@synergizglobal.com,raviteja.reddy@synergizglobal.com: End ");	
				flag = true;
			}
			/***************************************************************************/

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}


	@Override
	public boolean sendAlertsToHodDyHodByManual() throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			String dyHODQry ="select group_concat(distinct dy_hod_email) from alerts where alert_status = ? and dy_hod_email is not null and dy_hod_email <> '' and contract_id is not null and contract_id <> '' and count <> 0 group by alert_status";
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			String dyHODEmails = jdbcTemplate.queryForObject( dyHODQry,pValues, String.class);
			
			String hodQry ="select group_concat(distinct hod_email) from alerts where alert_status = ? and hod_email is not null and hod_email <> '' and contract_id is not null and contract_id <> '' and count <> 0 group by alert_status";
			pValues = new Object[] {CommonConstants.ACTIVE};
			String hodEmails = jdbcTemplate.queryForObject( hodQry,pValues, String.class);
						
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,hod,work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email  " + 
					"from alerts a " + 
					"left outer join contract_view cv on a.contract_id COLLATE utf8mb4_unicode_ci = cv.contract_id " + 
					"where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
			
			pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/*dyfacao1@mrvc.gov.in - 1st 2nd 3rd of BG & Insurance
			facao2@mrvc.gov.in - 2nd 3rd of BG & Insurance
			df@mrvc.gov.in - 3rd of BG & Insurance
			
			dy HOD - 1st, 2nd 3rd alert of their contracts
			HOD - 2nd, 3rd alerts of their contracts
			cmd@mrvc.gov.in - only 3rd alerts of all contracts..*/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(dyHODEmails)) {
				
				List<String> dyHODEmailsList = Arrays.asList(dyHODEmails.split(",", -1));
				for (String emailId : dyHODEmailsList) {
					List<Alerts> dyHodAlertsList = new ArrayList<Alerts>();
					for (Alerts alerts : allAlertsList) {
						if(!StringUtils.isEmpty(alerts.getDy_hod_email()) && alerts.getDy_hod_email().equals(emailId)) {
							dyHodAlertsList.add(alerts);						
						}
					}
					String emailSubject = "Upcoming alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(emailId);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					if(!StringUtils.isEmpty(dyHodAlertsList) && dyHodAlertsList.size() > 0){					
						logger.error("sendAlertsToHodDyHodByManual() >> Sending mail to Dy HOD "+emailId+"> : Start ");	
						emailSender.sendEmailWithAlerts(mail,dyHodAlertsList); 
						logger.error("sendAlertsToHodDyHodByManual() >> Sending mail to Dy HOD "+emailId+"> : End ");	
						flag = true;
					}
				}
			}
			/***************************************************************************/
			
			/***************************************************************************/
			if(!StringUtils.isEmpty(hodEmails)) {
				List<String> hodEmailsList = Arrays.asList(hodEmails.split(",", -1));
				for (String emailId : hodEmailsList) {
					List<Alerts> hodAlertsList = new ArrayList<Alerts>();
					for (Alerts alerts : allAlertsList) {
						if(!StringUtils.isEmpty(alerts.getHod_email()) && alerts.getHod_email().equals(emailId)) {
							hodAlertsList.add(alerts);						
						}
					}
					String emailSubject = "Upcoming alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(emailId);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					if(!StringUtils.isEmpty(hodAlertsList) && hodAlertsList.size() > 0){
						logger.error("sendAlertsToHodDyHodByManual() >> Sending mail to HOD "+emailId+"> : Start ");	
						emailSender.sendEmailWithAlerts(mail,hodAlertsList); 
						logger.error("sendAlertsToHodDyHodByManual() >> Sending mail to HOD "+emailId+"> : End ");	
						flag = true;
					}
				}
			}
			/***************************************************************************/
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	

	
	@Override
	public boolean generateAndSendAlertsToRajivRaviByManual() throws Exception {
		boolean flag = false;
		try {
			/*String qry ="select alert_id,alert_level,alert_type_fk,contract_id,created_date,alert_status,alert_value,count"
					+ " from alerts where alert_status = ? and contract_id is not null and contract_id <> '' and count <> 0 ";*/
			
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,hod,work_short_name,contract_short_name,contractor_name  " + 
					"from alerts a " + 
					"left outer join contract_view cv on a.contract_id COLLATE utf8mb4_unicode_ci = cv.contract_id " + 
					"where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			String emailSubject = "Upcoming alerts";
			
			Mail mail = new Mail();
			mail.setMailTo(CommonConstants2.ALERTS_EMAIL);
			mail.setMailSubject(emailSubject);
			mail.setTemplateName("alerts.vm");
			
			if(objsList != null && objsList.size() > 0){
				EMailSender emailSender = new EMailSender();
				logger.error("sendMailAlerts() >> Sending mail : Start ");	
				emailSender.sendEmailWithAlerts(mail,objsList); 
				logger.error("sendMailAlerts() >> Sending mail : End ");
				flag = true;
			}

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	
}
