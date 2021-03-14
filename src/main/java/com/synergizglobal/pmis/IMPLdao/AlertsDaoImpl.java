package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public boolean generateAterts() throws Exception {
		boolean flag = false;
		List<Alerts> list = new ArrayList<Alerts>();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			
			/***************************** BG alerts*******************************************************/
			String bgQryAlert1 = "select bg.contract_id_fk as contract_id, '1st Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email " 
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
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email " 
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
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email " 
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
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email "
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
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email "
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
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,u1.email_id as hod_email,u2.email_id as dy_hod_email "
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
			/*String cpQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Contract Period' as alert_type,"
					+ "(case when contract_revised_date is not null then CONCAT('Contract revised date : ',DATE_FORMAT(contract_revised_date,'%d-%b-%Y') ) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value,hod_email,dy_hod_email"
					+ " from contract_view " 
					+ "where contract_status = 'In Progress' and (contract_revised_date is not null OR doc is not null) "
					+ "and (DATEDIFF((CASE WHEN contract_revised_date is not null THEN contract_revised_date WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 30 " 
					+ "and DATEDIFF((CASE WHEN contract_revised_date is not null THEN contract_revised_date WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 15)";*/
			
			String cpQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "u1.email_id as hod_email,u2.email_id as dy_hod_email " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null " 
					+ "LEFT JOIN user u1 ON c.hod_user_id_fk = u1.user_id "
					+ "LEFT JOIN user u2 ON c.dy_hod_user_id_fk = u2.user_id " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 30 " 
					+ "and DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 15)";
			
			List<Alerts> cpQryAlert1List = jdbcTemplate.query( cpQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert1List) && cpQryAlert1List.size() > 0) {
				list.addAll(cpQryAlert1List);
			}
			
			/*String cpQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Period' as alert_type,"
					+ "(case when contract_revised_date is not null then CONCAT('Contract revised date : ',DATE_FORMAT(contract_revised_date,'%d-%b-%Y') ) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value,hod_email,dy_hod_email"
					+ " from contract_view "
					+ "where contract_status = 'In Progress' and (contract_revised_date is not null OR doc is not null) "
					+ "and (DATEDIFF((CASE WHEN contract_revised_date is not null THEN contract_revised_date WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 15 " 
					+ "and DATEDIFF((CASE WHEN contract_revised_date is not null THEN contract_revised_date WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 7)";*/
			
			String cpQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "u1.email_id as hod_email,u2.email_id as dy_hod_email " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null " 
					+ "LEFT JOIN user u1 ON c.hod_user_id_fk = u1.user_id "
					+ "LEFT JOIN user u2 ON c.dy_hod_user_id_fk = u2.user_id " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 15 " 
					+ "and DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 7)";
			
			List<Alerts> cpQryAlert2List = jdbcTemplate.query( cpQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert2List) && cpQryAlert2List.size() > 0) {
				list.addAll(cpQryAlert2List);
			}
			
			/*String cpQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Period' as alert_type,"
					+ "(case when contract_revised_date is not null then CONCAT('Contract revised date : ',DATE_FORMAT(contract_revised_date,'%d-%b-%Y') ) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value,hod_email,dy_hod_email"
					+ " from contract_view " 
					+ "where contract_status = 'In Progress' and (contract_revised_date is not null OR doc is not null) "
					+ "and (DATEDIFF((CASE WHEN contract_revised_date is not null THEN contract_revised_date WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 7) ";*/
			
			String cpQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "u1.email_id as hod_email,u2.email_id as dy_hod_email " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null " 
					+ "LEFT JOIN user u1 ON c.hod_user_id_fk = u1.user_id "
					+ "LEFT JOIN user u2 ON c.dy_hod_user_id_fk = u2.user_id " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 7) ";
			
			List<Alerts> cpQryAlert3List = jdbcTemplate.query( cpQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert3List) && cpQryAlert3List.size() > 0) {
				list.addAll(cpQryAlert3List);
			}
			
			/***************************** Contract Value alerts*******************************************************/
			/*String cvQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',cumulative_expenditure,"
					+ "(CASE WHEN revised_cost is not null THEN ', Revised Cost : ' WHEN estimated_cost is not null THEN ', Estimated Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END)," + 
					"(CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"
					+ ") AS alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and ((cumulative_expenditure is not null "
					+ "and (( cumulative_expenditure* 100) / (CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 95 "
					+ "and (( cumulative_expenditure* 100) / (CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) < 120))";*/
			
			String cvQryAlert1 = "select c.contract_id,'1st Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',"  
					+ "(select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN ', Revised Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END), "  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "u1.email_id as hod_email,u2.email_id as dy_hod_email "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null "  
					+ "LEFT JOIN user u1 ON c.hod_user_id_fk = u1.user_id "  
					+ "LEFT JOIN user u2 ON c.dy_hod_user_id_fk = u2.user_id " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (((select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) is not null "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 95 "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) < 120))";
			
			List<Alerts> cvQryAlert1List = jdbcTemplate.query( cvQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert1List) && cvQryAlert1List.size() > 0) {
				list.addAll(cvQryAlert1List);
			}
			
			/*String cvQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',cumulative_expenditure,"
					+ "(CASE WHEN revised_cost is not null THEN ', Revised Cost : ' WHEN estimated_cost is not null THEN ', Estimated Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END)," + 
					"(CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"
					+ " ) AS alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and ((cumulative_expenditure is not null "
					+ "and (( cumulative_expenditure* 100) / (CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 120 "
					+ "and (( cumulative_expenditure* 100) / (CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) < 145))";*/
			
			String cvQryAlert2 = "select c.contract_id,'2nd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',"  
					+ "(select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN ', Revised Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END), "  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "u1.email_id as hod_email,u2.email_id as dy_hod_email "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null "  
					+ "LEFT JOIN user u1 ON c.hod_user_id_fk = u1.user_id "  
					+ "LEFT JOIN user u2 ON c.dy_hod_user_id_fk = u2.user_id " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (((select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) is not null "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 120 "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) < 145))";
			
			
			List<Alerts> cvQryAlert2List = jdbcTemplate.query( cvQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert2List) && cvQryAlert2List.size() > 0) {
				list.addAll(cvQryAlert2List);
			}
			
			/*String cvQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',cumulative_expenditure,"
					+ "(CASE WHEN revised_cost is not null THEN ', Revised Cost : ' WHEN estimated_cost is not null THEN ', Estimated Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END)," + 
					"(CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"
					+ " ) AS alert_value,hod_email,dy_hod_email"
					+ " from contract_view " + 
					"where contract_status = 'In Progress' " + 
					"and ((cumulative_expenditure is not null "
					+ "and (( cumulative_expenditure* 100) / (CASE WHEN revised_cost is not null THEN revised_cost WHEN estimated_cost is not null THEN estimated_cost WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 145))";*/
			
			String cvQryAlert3 = "select c.contract_id,'3rd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',"  
					+ "(select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN ', Revised Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END), "  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "u1.email_id as hod_email,u2.email_id as dy_hod_email "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null "  
					+ "LEFT JOIN user u1 ON c.hod_user_id_fk = u1.user_id "  
					+ "LEFT JOIN user u2 ON c.dy_hod_user_id_fk = u2.user_id " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (((select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) is not null "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 145 ))";
			
			
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
			
			
			String qryUserPermissions = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,hod_email,dy_hod_email,remarks) VALUES  (?,?,?,?,?,?,?,?,?)";		
			
			int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions, new BatchPreparedStatementSetter() { 
								@Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
									String alert_level = list.get(i).getAlert_level();
									String alert_type = list.get(i).getAlert_type();
									String contract_id = list.get(i).getContract_id();
									String alert_value = list.get(i).getAlert_value();
									
				                    ps.setString(1, alert_level);
				                    ps.setString(2, alert_type);
				                    ps.setString(3, contract_id);
				                    ps.setString(4, CommonConstants.ACTIVE);
				                    ps.setString(5, alert_value);
				                    ps.setString(6, "1");
				                    ps.setString(7, list.get(i).getHod_email());
				                    ps.setString(8, list.get(i).getDy_hod_email());
				                    ps.setString(9, getAlertRemarks(alert_type,contract_id,alert_value));
				                }
				                private String getAlertRemarks(String alert_type, String contract_id,
										String alert_value) {
				                	String remarks = null;
				                	try {
				                		//String remarksQry ="select remarks from alerts where alert_type_fk = ? and contract_id = ? and alert_value = ? and DATE(created_date) = DATE((NOW() - INTERVAL 1 DAY))";
				                		String remarksQry ="select remarks from alerts where alert_type_fk = ? and contract_id = ? and alert_value = ? "
				                				+ "AND created_date = (select max(created_date) from alerts where alert_type_fk = ? and contract_id = ? and alert_value = ? )";
				                		Object[] pValues = new Object[] {alert_type,contract_id,alert_value,alert_type,contract_id,alert_value};
					        			remarks = jdbcTemplate.queryForObject( remarksQry,pValues, String.class);
									} catch (Exception e) {
										// TODO: handle exception
									}
				                	
									return remarks;
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
						
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,IFNULL(a.remarks,'') as remarks " 
					+ "from alerts a "  
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
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
					String emailSubject = "PMIS Contract Alerts";
					
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
					String emailSubject = "PMIS Contract Alerts";
					
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
				String emailSubject = "PMIS Contract Alerts";
				
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
				String emailSubject = "PMIS Contract Alerts";
				
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
				String emailSubject = "PMIS Contract Alerts";
				
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
				String emailSubject = "PMIS Contract Alerts";
				
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
			/*if(!StringUtils.isEmpty(allAlertsList) && allAlertsList.size() > 0){
				String emailSubject = "PMIS Contract Alerts";
				
				Mail mail = new Mail();
				mail.setMailTo(CommonConstants2.ALERTS_EMAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alerts.vm");
					
				logger.error("sendNotificationAlertMails() >> Sending mail to rajiv.dhupkar@synergizglobal.com,raviteja.reddy@synergizglobal.com: Start ");	
				emailSender.sendEmailWithAlerts(mail,allAlertsList); 
				logger.error("sendNotificationAlertMails() >> Sending mail to rajiv.dhupkar@synergizglobal.com,raviteja.reddy@synergizglobal.com: End ");	
				flag = true;
			}*/
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
						
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,IFNULL(a.remarks,'') as remarks " 
					+ "from alerts a " 
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
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
					String emailSubject = "PMIS Contract Alerts";
					
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
					String emailSubject = "PMIS Contract Alerts";
					
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
	public boolean sendAlertsToRajivRavi() throws Exception {
		boolean flag = false;
		try {
			/*String qry ="select alert_id,alert_level,alert_type_fk,contract_id,created_date,alert_status,alert_value,count"
					+ " from alerts where alert_status = ? and contract_id is not null and contract_id <> '' and count <> 0 ";*/
			
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks " 
					+ "from alerts a " 
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			String emailSubject = "PMIS Contract Alerts";
			
			Mail mail = new Mail();
			mail.setMailTo(CommonConstants2.ALERTS_EMAIL);
			mail.setMailSubject(emailSubject);
			mail.setTemplateName("alerts.vm");
			
			if(objsList != null && objsList.size() > 0){
				EMailSender emailSender = new EMailSender();
				logger.error("sendAlertsToRajivRavi() >> Sending mail : Start ");	
				emailSender.sendEmailWithAlerts(mail,objsList); 
				logger.error("sendAlertsToRajivRavi() >> Sending mail : End ");
				flag = true;
			}

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}


	@Override
	public List<Alerts> getAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,IFNULL(a.remarks,'') as remarks,count,u.designation as hod,"
					+ "work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,c.work_id_fk,work_id,work_name,c.contract_short_name "
					+ "from alerts a " 
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
				qry = qry + " and alert_level = ? ";	
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) ";
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level <> ? ";
				arrSize++;
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level = ? ";
				arrSize++;
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
				
			}else{
				qry = qry + " and (a.hod_email = ? OR a.dy_hod_email = ?) ";
				arrSize++;
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			
			qry = qry + " order by hod,work_short_name,a.contract_id asc, alert_level desc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
				pValues[i++] = "3rd Alert";				
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
				pValues[i++] = "3rd Alert";
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
				pValues[i++] = "3rd Alert";
			}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
				
			}else{
				pValues[i++] = obj.getEmail_id();	
				pValues[i++] = obj.getEmail_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/*dyfacao1@mrvc.gov.in - 1st 2nd 3rd of BG & Insurance
			facao2@mrvc.gov.in - 2nd 3rd of BG & Insurance
			df@mrvc.gov.in - 3rd of BG & Insurance
			
			dy HOD - 1st, 2nd 3rd alert of their contracts
			HOD - 2nd, 3rd alerts of their contracts
			cmd@mrvc.gov.in - only 3rd alerts of all contracts..*/
			

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<Alerts> getContractorsFilterListInAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = null;
		try {
			String qry = "SELECT c.contractor_id_fk,ctr.contractor_id,ctr.contractor_name "
					+ "from alerts a "
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "and c.contractor_id_fk is not null and c.contractor_id_fk <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY c.contractor_id_fk ORDER BY c.contractor_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<Alerts> getContractsFilterListInAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = null;
		try {
			String qry = "SELECT c.contract_id,c.contract_name,c.contract_short_name "
					+ "from alerts a "
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "and a.contract_id is not null and a.contract_id <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY a.contract_id ORDER BY a.contract_id";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<Alerts> getHODFilterListInAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = null;
		try {
			String qry = "SELECT u.user_id,u.user_name,u.designation as hod "
					+ "from alerts a "
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "and u.designation is not null and u.designation <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY u.designation ORDER BY u.designation";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<Alerts> getWorksFilterListInAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = null;
		try {
			String qry = "SELECT c.work_id_fk,work_id,work_name,work_short_name "
					+ "from alerts a "
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "and c.work_id_fk is not null and c.work_id_fk <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public List<Alerts> getAlertTypesFilterListInAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = null;
		try {
			String qry = "SELECT alert_type_fk "
					+ "from alerts a "
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "and c.work_id_fk is not null and c.work_id_fk <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY alert_type_fk ORDER BY alert_type_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}


	@Override
	public boolean addAlertRemarks(Alerts obj) throws Exception {
		boolean flag = false;
		try {
			String updateQry = "update alerts set remarks = ? where alert_id = ?";	
			Object[] pValues = new Object[] {obj.getRemarks(),obj.getAlert_id()};
			int c = jdbcTemplate.update(updateQry,pValues);	
			if(c > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return flag;
	}


	@Override
	public Map<String,List<Alerts>> getAlertsForHeaderNotifications(Alerts obj) throws Exception {
		Map<String,List<Alerts>> objs = new LinkedHashMap<String,List<Alerts>>();
		try {
			
			
			String qry = "select alert_level "
					+ "from alerts a " 
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
				qry = qry + " and alert_level = ? ";	
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) ";
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level <> ? ";
				arrSize++;
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level = ? ";
				arrSize++;
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
				
			}else{
				qry = qry + " and (a.hod_email = ? OR a.dy_hod_email = ?) ";
				arrSize++;
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			
			qry = qry + " group by alert_level order by alert_level desc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
				pValues[i++] = "3rd Alert";				
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
				pValues[i++] = "3rd Alert";
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
				pValues[i++] = "3rd Alert";
			}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
				
			}else{
				pValues[i++] = obj.getEmail_id();	
				pValues[i++] = obj.getEmail_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			
			List<Alerts> alertLevelsobjsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			for (Alerts alertLevel : alertLevelsobjsList) {
				
				obj.setAlert_level(alertLevel.getAlert_level());
				
				qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,IFNULL(a.remarks,'') as remarks,count,u.designation as hod,"
						+ "work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,c.work_id_fk,work_id,work_name,c.contract_short_name "
						+ "from alerts a " 
						+ "left outer join contract c on a.contract_id = c.contract_id " 
						+ "left outer join work w on c.work_id_fk = w.work_id " 
						+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left outer join user u on c.hod_user_id_fk = u.user_id "
						+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
				
				arrSize = 1;
				if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
					qry = qry + " and alert_level = ? ";	
					arrSize++;
				}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
					qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) ";
					arrSize++;
					arrSize++;
				}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
					qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level <> ? ";
					arrSize++;
					arrSize++;
					arrSize++;
				}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
					qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level = ? ";
					arrSize++;
					arrSize++;
					arrSize++;
				}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
					
				}else{
					qry = qry + " and (a.hod_email = ? OR a.dy_hod_email = ?) ";
					arrSize++;
					arrSize++;
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and c.work_id_fk = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
					qry = qry + " and a.contract_id = ?";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					qry = qry + " and c.contractor_id_fk = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
					qry = qry + " and a.alert_type_fk = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
					qry = qry + " and a.alert_level = ?";
					arrSize++;
				}
				
				qry = qry + " order by hod,work_short_name,a.contract_id asc, alert_level desc";
				
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
					pValues[i++] = "3rd Alert";				
				}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
					pValues[i++] = "Bank Guarantee";	
					pValues[i++] = "Insurance";	
				}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
					pValues[i++] = "Bank Guarantee";	
					pValues[i++] = "Insurance";	
					pValues[i++] = "3rd Alert";
				}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
					pValues[i++] = "Bank Guarantee";	
					pValues[i++] = "Insurance";	
					pValues[i++] = "3rd Alert";
				}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
					
				}else{
					pValues[i++] = obj.getEmail_id();	
					pValues[i++] = obj.getEmail_id();
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
					pValues[i++] = obj.getContract_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
					pValues[i++] = obj.getContractor_id_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
					pValues[i++] = obj.getAlert_type_fk();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
					pValues[i++] = obj.getHod();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
					pValues[i++] = obj.getAlert_level();
				}
				
				List<Alerts> objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				objs.put(alertLevel.getAlert_level(), objsList);
			}
			
			
			/*dyfacao1@mrvc.gov.in - 1st 2nd 3rd of BG & Insurance
			facao2@mrvc.gov.in - 2nd 3rd of BG & Insurance
			df@mrvc.gov.in - 3rd of BG & Insurance
			
			dy HOD - 1st, 2nd 3rd alert of their contracts
			HOD - 2nd, 3rd alerts of their contracts
			cmd@mrvc.gov.in - only 3rd alerts of all contracts..*/
			

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objs;
	}


	@Override
	public int getAlertsCount(Alerts obj) throws Exception {
		int count = 0;;
		try {
			String qry = "select count(*) "
					+ "from alerts a " 
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
				qry = qry + " and alert_level = ? ";	
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) ";
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level <> ? ";
				arrSize++;
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
				qry = qry + " and (alert_type_fk = ? OR alert_type_fk = ?) and alert_level = ? ";
				arrSize++;
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
				
			}else{
				qry = qry + " and (a.hod_email = ? OR a.dy_hod_email = ?) ";
				arrSize++;
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				qry = qry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				qry = qry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("cmd@mrvc.gov.in")){
				pValues[i++] = "3rd Alert";				
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("dyfacao1@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("facao2@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
				pValues[i++] = "3rd Alert";
			}else if(!StringUtils.isEmpty(obj) && obj.getEmail_id().equals("df@mrvc.gov.in")) {
				pValues[i++] = "Bank Guarantee";	
				pValues[i++] = "Insurance";	
				pValues[i++] = "3rd Alert";
			}else if(!StringUtils.isEmpty(obj) && obj.getUser_role_name().equals("IT Admin")) {
				
			}else{
				pValues[i++] = obj.getEmail_id();	
				pValues[i++] = obj.getEmail_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				pValues[i++] = obj.getContractor_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				pValues[i++] = obj.getAlert_type_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				pValues[i++] = obj.getHod();
			}
			
			Integer c = jdbcTemplate.queryForObject( qry,pValues, (Integer.class));
			if(c != null && c > 0) {
				count = c;
			}
			/*dyfacao1@mrvc.gov.in - 1st 2nd 3rd of BG & Insurance
			facao2@mrvc.gov.in - 2nd 3rd of BG & Insurance
			df@mrvc.gov.in - 3rd of BG & Insurance
			
			dy HOD - 1st, 2nd 3rd alert of their contracts
			HOD - 2nd, 3rd alerts of their contracts
			cmd@mrvc.gov.in - only 3rd alerts of all contracts..*/
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return count;
	}

	
}
