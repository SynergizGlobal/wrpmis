package com.synergizglobal.pmis.IMPLdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FormHistory;
@Repository
public class AlertsDaoImpl implements AlertsDao{
	
	public static Logger logger = Logger.getLogger(AlertsDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;

	@Override
	public boolean generateAterts() throws Exception {
		boolean flag = false;
		List<Alerts> list = new ArrayList<Alerts>();
		/*TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);*/
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			/***************************** BG alerts*******************************************************/
			String bgQryAlert1 = "select bg.contract_id_fk as contract_id, 1st Alert as alert_level,Bank Guarantee as alert_type,"
					+ "(case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,',' ,bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) " 
					+ "else CONCAT(Bank guarantee valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.bg_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity " 
					+ "from contract c " 
					+ "left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "(DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = 1st Alert) and DATEDIFF(valid_upto ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = 1st Alert)) and release_date is null";
			
			List<Alerts> bgQryAlert1List = jdbcTemplate.query( bgQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert1List) && bgQryAlert1List.size() > 0) {
				list.addAll(bgQryAlert1List);
			}			
			
			String bgQryAlert2 = "select bg.contract_id_fk as contract_id, 2nd Alert as alert_level,Bank Guarantee as alert_type,"
					+ "(case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,',' ,bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) " 
					+ "else CONCAT(Bank guarantee valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.bg_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity " 
					+ "from contract c "
					+ "left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "(DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = 2nd Alert) and DATEDIFF(valid_upto ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = 2nd Alert)) and release_date is null";
			
			List<Alerts> bgQryAlert2List = jdbcTemplate.query( bgQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert2List) && bgQryAlert2List.size() > 0) {
				list.addAll(bgQryAlert2List);
			}	
			
			String bgQryAlert3 = "select bg.contract_id_fk as contract_id, 3rd Alert as alert_level,Bank Guarantee as alert_type,"
					+ "(case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,',' ,bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) " 
					+ "else CONCAT(Bank guarantee valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.bg_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity " 
					+ "from contract c " 
					+ "left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "(DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = 3rd Alert) and DATEDIFF(valid_upto ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = 3rd Alert)) and release_date is null";
			
			List<Alerts> bgQryAlert3List = jdbcTemplate.query( bgQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert3List) && bgQryAlert3List.size() > 0) {
				list.addAll(bgQryAlert3List);
			}
			
			String bgQryAlert4 = "select bg.contract_id_fk as contract_id, Overdue as alert_level,Bank Guarantee as alert_type,"
					+ "(case when (bg.bg_type_fk is not null and bg.bg_number is not null) then CONCAT(bg.bg_type_fk,',' ,bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is null and bg.bg_number is not null) then CONCAT(bg.bg_number,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.bg_type_fk is not null and bg.bg_number is null) then CONCAT(bg.bg_type_fk,  valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) " 
					+ "else CONCAT(Bank guarantee valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.bg_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity  " 
					+ "from contract c " 
					+ "left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Bank Guarantee and alert_level_fk = Overdue) and release_date is null";
			
			List<Alerts> bgQryAlert4List = jdbcTemplate.query( bgQryAlert4, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert4List) && bgQryAlert4List.size() > 0) {
				list.addAll(bgQryAlert4List);
			}
			
			List<Alerts> bgAlertsWithoutBGDetailsList = getBGAlertsWithoutBGDetails();
			if(!StringUtils.isEmpty(bgAlertsWithoutBGDetailsList) && bgAlertsWithoutBGDetailsList.size() > 0) {
				list.addAll(bgAlertsWithoutBGDetailsList);
			}
			
			/***************************** Insurance alerts*******************************************************/
			String insuranceQryAlert1 = "select bg.contract_id_fk as contract_id, 1st Alert as alert_level,Insurance as alert_type,"
					+ "(case when (bg.insurance_type_fk is not null and bg.insurance_number is not null) then CONCAT(bg.insurance_type_fk,',' ,bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is null and bg.insurance_number is not null) then CONCAT(bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is not null and bg.insurance_number is null) then CONCAT(bg.insurance_type_fk,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "else CONCAT(Insurance valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.insurance_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity "
					+ "from contract c " 
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "(DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = 1st Alert) and DATEDIFF(valid_upto ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = 1st Alert)) and (bg.released_fk = No or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert1List = jdbcTemplate.query( insuranceQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert1List) && insuranceQryAlert1List.size() > 0) {
				list.addAll(insuranceQryAlert1List);
			}
			
			String insuranceQryAlert2 = "select bg.contract_id_fk as contract_id, 2nd Alert as alert_level,Insurance as alert_type,"
					+ "(case when (bg.insurance_type_fk is not null and bg.insurance_number is not null) then CONCAT(bg.insurance_type_fk,',' ,bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is null and bg.insurance_number is not null) then CONCAT(bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is not null and bg.insurance_number is null) then CONCAT(bg.insurance_type_fk,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "else CONCAT(Insurance valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.insurance_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity "
					+ "from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "(DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = 2nd Alert) and DATEDIFF(valid_upto ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = 2nd Alert)) and (bg.released_fk = No or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert2List = jdbcTemplate.query( insuranceQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert2List) && insuranceQryAlert2List.size() > 0) {
				list.addAll(insuranceQryAlert2List);
			}
			
			String insuranceQryAlert3 = "select bg.contract_id_fk as contract_id, 3rd Alert as alert_level,Insurance as alert_type,"
					+ "(case when (bg.insurance_type_fk is not null and bg.insurance_number is not null) then CONCAT(bg.insurance_type_fk,',' ,bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is null and bg.insurance_number is not null) then CONCAT(bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is not null and bg.insurance_number is null) then CONCAT(bg.insurance_type_fk,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "else CONCAT(Insurance valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.insurance_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity "
					+ "from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "(DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = 3rd Alert) and DATEDIFF(valid_upto ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = 3rd Alert)) and (bg.released_fk = No or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert3List = jdbcTemplate.query( insuranceQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert3List) && insuranceQryAlert3List.size() > 0) {
				list.addAll(insuranceQryAlert3List);
			}
			
			String insuranceQryAlert4 = "select bg.contract_id_fk as contract_id, Overdue as alert_level,Insurance as alert_type,"
					+ "(case when (bg.insurance_type_fk is not null and bg.insurance_number is not null) then CONCAT(bg.insurance_type_fk,',' ,bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is null and bg.insurance_number is not null) then CONCAT(bg.insurance_number,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "when (bg.insurance_type_fk is not null and bg.insurance_number is null) then CONCAT(bg.insurance_type_fk,  Valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) "
					+ "else CONCAT(Insurance valid upto ,FORMAT(valid_upto,'%d/%m/%Y') ) end ) as alert_value,"
					+ "concat(/get-contract?contract_id=,bg.contract_id_fk,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "bg.insurance_number as details,FORMAT(valid_upto,'%Y-%m-%d') as validity "
					+ " from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where contract_status_fk in (In Progress) and "
					+ "DATEDIFF(valid_upto ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Insurance and alert_level_fk = Overdue) and (bg.released_fk = No or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert4List = jdbcTemplate.query( insuranceQryAlert4, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert4List) && insuranceQryAlert4List.size() > 0) {
				list.addAll(insuranceQryAlert4List);
			}
			

			List<Alerts> insuranceAlertsWithoutBGDetailsList = getInsuranceAlertsWithoutInsuranceDetails();
			if(!StringUtils.isEmpty(insuranceAlertsWithoutBGDetailsList) && insuranceAlertsWithoutBGDetailsList.size() > 0) {
				list.addAll(insuranceAlertsWithoutBGDetailsList);
			}
			
			/***************************** Contract Period alerts*******************************************************/
			
			String cpQryAlert1 = "select contract_id,1st Alert as alert_level,Contract Period as alert_type," 
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then (CONCAT(Date of Completion : ,FORMAT(cr.revised_doc,'%d/%m/%Y') )) " 
					+ "when doc is not null then CONCAT(Date of Completion : ,FORMAT(doc,'%d/%m/%Y') ) else  end ) as alert_value," 
					+ "concat(/get-contract?contract_id=,contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then FORMAT(cr.revised_doc,'%Y-%m-%d') " 
					+ "when doc is not null then FORMAT(doc,'%Y-%m-%d') else  end ) as validity " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = Yes and cr.revised_doc is not null " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = 1st Alert) " 
					+ "and DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = 1st Alert))";
			
			List<Alerts> cpQryAlert1List = jdbcTemplate.query( cpQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert1List) && cpQryAlert1List.size() > 0) {
				list.addAll(cpQryAlert1List);
			}
			String cpQryAlert2 = "select contract_id,2nd Alert as alert_level,Contract Period as alert_type," 
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then (CONCAT(Date of Completion : ,FORMAT(cr.revised_doc,'%d/%m/%Y') )) " 
					+ "when doc is not null then CONCAT(Date of Completion : ,FORMAT(doc,'%d/%m/%Y') ) else  end ) as alert_value," 
					+ "concat(/get-contract?contract_id=,contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then FORMAT(cr.revised_doc,'%Y-%m-%d') " 
					+ "when doc is not null then FORMAT(doc,'%Y-%m-%d') else  end ) as validity "  
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = Yes and cr.revised_doc is not null "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = 2nd Alert) " 
					+ "and DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = 2nd Alert))";
			
			List<Alerts> cpQryAlert2List = jdbcTemplate.query( cpQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert2List) && cpQryAlert2List.size() > 0) {
				list.addAll(cpQryAlert2List);
			}
			
			
			String cpQryAlert3 = "select contract_id,3rd Alert as alert_level,Contract Period as alert_type," 
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then (CONCAT(Date of Completion : ,FORMAT(cr.revised_doc,'%d/%m/%Y') )) " 
					+ "when doc is not null then CONCAT(Date of Completion : ,FORMAT(doc,'%d/%m/%Y') ) else  end ) as alert_value," 
					+ "concat(/get-contract?contract_id=,contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then FORMAT(cr.revised_doc,'%Y-%m-%d') " 
					+ "when doc is not null then FORMAT(doc,'%Y-%m-%d') else  end ) as validity " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = Yes and cr.revised_doc is not null "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = 3rd Alert) " 
					+ "and DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) > (select second_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = 3rd Alert))";
			
			List<Alerts> cpQryAlert3List = jdbcTemplate.query( cpQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert3List) && cpQryAlert3List.size() > 0) {
				list.addAll(cpQryAlert3List);
			}
			
			String cpQryAlert4 = "select contract_id,Overdue as alert_level,Contract Period as alert_type," 
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then (CONCAT(Date of Completion : ,FORMAT(cr.revised_doc,'%d/%m/%Y') )) " 
					+ "when doc is not null then CONCAT(Date of Completion : ,FORMAT(doc,'%d/%m/%Y') ) else  end ) as alert_value," 
					+ "concat(/get-contract?contract_id=,contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id,"
					+ "(case when (cr.action = Yes and cr.revised_doc is not null) then FORMAT(cr.revised_doc,'%Y-%m-%d') " 
					+ "when doc is not null then FORMAT(doc,'%Y-%m-%d') else  end ) as validity " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = Yes and cr.revised_doc is not null "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = Yes and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE  END) ,GETDATE()) <= (select first_condition_value from alert_conditions where alert_type_fk = Contract Period and alert_level_fk = Overdue)) ";
			
			List<Alerts> cpQryAlert4List = jdbcTemplate.query( cpQryAlert4, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert4List) && cpQryAlert4List.size() > 0) {
				list.addAll(cpQryAlert4List);
			}
			
			/***************************** Contract Value alerts*******************************************************/
			
			String cvQryAlert1 = "select c.contract_id,1st Alert as alert_level,Contract Value as alert_type,"
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) as cumulative_expenditure,"
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END) as awarded_or_revised_cost,"
					+ "CONCAT(Cumulative expenditure : Rs ,"  
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN , Revised Cost : Rs  WHEN awarded_cost is not null THEN , Awarded Cost : Rs  ELSE  END), "  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = Yes and cr.revised_amount is not null "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "having (cumulative_expenditure >= 0 "    
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) >= (select first_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = 1st Alert) "  
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) < (select second_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = 1st Alert) )";
			
			List<Alerts> cvQryAlert1List = jdbcTemplate.query( cvQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert1List) && cvQryAlert1List.size() > 0) {
				list.addAll(cvQryAlert1List);
			}
			
			
			String cvQryAlert2 = "select c.contract_id,2nd Alert as alert_level,Contract Value as alert_type,"
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) as cumulative_expenditure,"
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END) as awarded_or_revised_cost,"
					+ "CONCAT(Cumulative expenditure : Rs ,"  
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN , Revised Cost : Rs  WHEN awarded_cost is not null THEN , Awarded Cost : Rs  ELSE  END), "  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = Yes and cr.revised_amount is not null " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "having (cumulative_expenditure >= 0 "   
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) >= (select first_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = 2nd Alert) "  
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) < (select second_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = 2nd Alert) )";
			
			
			List<Alerts> cvQryAlert2List = jdbcTemplate.query( cvQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert2List) && cvQryAlert2List.size() > 0) {
				list.addAll(cvQryAlert2List);
			}
			
			String cvQryAlert3 = "select c.contract_id,3rd Alert as alert_level,Contract Value as alert_type,"
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) as cumulative_expenditure,"
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END) as awarded_or_revised_cost,"
					+ "CONCAT(Cumulative expenditure : Rs ,"  
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN , Revised Cost : Rs  WHEN awarded_cost is not null THEN , Awarded Cost : Rs  ELSE  END), "  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = Yes and cr.revised_amount is not null " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "having (cumulative_expenditure >= 0 "   
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) >= (select first_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = 3rd Alert) "  
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) < (select second_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = 3rd Alert) )";
			
			List<Alerts> cvQryAlert3List = jdbcTemplate.query( cvQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert3List) && cvQryAlert3List.size() > 0) {
				list.addAll(cvQryAlert3List);
			}
			
			String cvQryAlert4 = "select c.contract_id,Overdue as alert_level,Contract Value as alert_type,"
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) as cumulative_expenditure,"
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END) as awarded_or_revised_cost,"
					+ "CONCAT(Cumulative expenditure : Rs ,"  
					+ "(select CAST(SUM(x.gross_work_done*x.gross_work_done_units) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN , Revised Cost : Rs  WHEN awarded_cost is not null THEN , Awarded Cost : Rs  ELSE  END), "  
					+ "(CASE WHEN (cr.revision_amounts_status = Yes and cr.revised_amount is not null) THEN cr.revised_amount*cr.revised_amount_units WHEN awarded_cost is not null THEN awarded_cost*awarded_cost_units ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=revisionDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = Yes and cr.revised_amount is not null "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "where c.contract_status_fk in (In Progress) " 
					+ "having (cumulative_expenditure >= 0 "  
					+ "and (( cumulative_expenditure* 100) / (awarded_or_revised_cost)) >= (select first_condition_value from alert_conditions where alert_type_fk = Contract Value and alert_level_fk = Overdue) )";
	
	
			List<Alerts> cvQryAlert4List = jdbcTemplate.query( cvQryAlert4, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert4List) && cvQryAlert4List.size() > 0) {
				list.addAll(cvQryAlert4List);
			}
			
			
			
			String cvQryAlert5 = "select c.contract_id,Flag as alert_level,Execution as alert_type,hod_user_id_fk,dy_hod_user_id_fk,concat(actual progress lagging by , substring(round(ISNULL(physical_planned-physical_actual,0),4)*100,1,LENGTH(round(ISNULL(physical_planned-physical_actual,0),4)*100)-2),'%') as alert_value,concat(/new-activities-update?contract_id=,c.contract_id) as redirect_url "
					+ "from contract_progress c left join contract c1 on c1.contract_id=c.contract_id where physical_planned-physical_actual>0.1 and DATEDIFF(curdate(), c1.modified_date)>=90";
	
	
			List<Alerts> cvQryAlert5List = jdbcTemplate.query( cvQryAlert5, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert5List) && cvQryAlert5List.size() > 0) {
				list.addAll(cvQryAlert5List);
			}
	
			
			String cvQryAlert6 = "select w.work_id,Flag as alert_level,R&R as alert_type,re.executive_user_id_fk as hod_user_id_fk,\r\n"
					+ "concat(structure_id,\" structures in \",location_name,\" not updated in last \", DATEDIFF(curdate(), r.modified_date), \" days\") as alert_value,\r\n"
					+ "concat(/get-rr/,r.rr_id) as redirect_url\r\n"
					+ " from rr r left JOIN work w ON w.work_id=r.work_id left join rr_executives re on re.work_id_fk=r.work_id where DATEDIFF(curdate(), r.modified_date)>=90 and r.handed_over_to_execution is null group by sub_location_name,w.work_id";
	
	
			List<Alerts> cvQryAlert6List = jdbcTemplate.query( cvQryAlert6, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert6List) && cvQryAlert6List.size() > 0) {
				list.addAll(cvQryAlert6List);
			}
			
			
			String cvQryAlert7 = "select w.work_id,Flag as alert_level,Land Acquisition as alert_type,re.executive_user_id_fk as hod_user_id_fk,\r\n"
					+ "concat(\"Land activity of for \",chainage_from,\" to \",chainage_to,\" not updated in last \", DATEDIFF(curdate(), r.modified_date), \" days\") as alert_value,\r\n"
					+ "concat(/get-land-acquisition/,r.la_id) as redirect_url\r\n"
					+ " from la_land_identification r left JOIN work w ON w.work_id=r.work_id_fk left join land_executives re on re.work_id_fk=r.work_id_fk where DATEDIFF(curdate(), r.modified_date)>=90 and r.la_land_status_fk <> Acquired group by village,w.work_id";
	
	
			List<Alerts> cvQryAlert7List = jdbcTemplate.query( cvQryAlert7, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert7List) && cvQryAlert7List.size() > 0) {
				list.addAll(cvQryAlert7List);
			}
			
			String cvQryAlert8 = "select distinct c1.contract_id,Flag as alert_level,Contract Milestone as alert_type,hod_user_id_fk,dy_hod_user_id_fk,\r\n"
					+ "concat(\"Milestone \",milestone_id,\" Completion Date of \", c1.contract_short_name, \" has passed i.e. \",(select FORMAT(min(milestone_date),'%d/%m/%Y') from contract_milestones mc where mc.contract_id_fk=r.contract_id_fk)) as alert_value,concat(/get-contract/,c1.contract_id,/#milestoneDetails) as redirect_url\r\n"
					+ " from contract_milestones r left join contract c1 on c1.contract_id=r.contract_id_fk  where curdate()>milestone_date and c1.status=Open and contract_status_fk=In Progress ";
	
	
			List<Alerts> cvQryAlert8List = jdbcTemplate.query( cvQryAlert8, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert8List) && cvQryAlert8List.size() > 0) {
				list.addAll(cvQryAlert8List);
			}	
			
			
			String cvQryAlert9 = "select distinct c1.contract_id,Flag as alert_level,Drawing as alert_type,hod AS hod_user_id_fk,dy_hod AS dy_hod_user_id_fk,concat(\"Drawing No. \",r.mrvc_drawing_no,\" has not been approved by \",r.approving_railway,\" in \", DATEDIFF(curdate(), ds.submitted_date), \" days\") as alert_value,concat(/get-design/,r.design_id) as redirect_url from DESIGN r left join contract c1 on c1.contract_id=r.contract_id_fk  left join design_status ds on ds.design_id_fk=r.design_id   where DATEDIFF(curdate(), ds.submitted_date)>=60 and gfc_released is Null";
	
	
			List<Alerts> cvQryAlert9List = jdbcTemplate.query( cvQryAlert9, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert9List) && cvQryAlert9List.size() > 0) {
				list.addAll(cvQryAlert9List);
			}
			
			
			String cvQryAlert10 = "select Flag as alert_level,Utility Shifting as alert_type,concat(utility_shifting_id,\" is not shifted by \", owner_name,\" beyond \",ISNULL(requirement_stage_fk,''),ISNULL(FORMAT(start_date,'%d/%m/%Y'),'')) as alert_value,concat(/get-utility-shifting/,r.utility_shifting_id) as redirect_url,ue.executive_user_id_fk as hod_user_id_fk from utility_shifting r left join work w on w.work_id=r.work_id_fk  left join utility_shifting_executives ue on ue.work_id_fk=r.work_id_fk where DATEDIFF(curdate(), r.modified_date)>=90 and shifting_status_fk!=Completed group by utility_category_fk";
	
	
			List<Alerts> cvQryAlert10List = jdbcTemplate.query( cvQryAlert10, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert10List) && cvQryAlert10List.size() > 0) {
				list.addAll(cvQryAlert10List);
			}			
			
			
			/*************************Alerts insertion********************************************/
			String updateQry = "update alerts set alert_status = ? where count <> 0";	
			Object[] pValues = new Object[] {CommonConstants.INACTIVE};
			jdbcTemplate.update(updateQry,pValues);	
			
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,count,remarks,redirect_url,details,valid_upto,amendment_not_required_in_contract,work_id)"
					+ " VALUES  (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			String qryAlertsSendManually = "select user_id_fk,alert_type_fk,alert_level_fk from alerts_send_manually ";			
			List<Alerts> alertsSendManually = jdbcTemplate.query( qryAlertsSendManually, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			
			for (Alerts obj : list) {				
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				String details = obj.getDetails();
				String valid_upto = obj.getValidity();
				String work_id = obj.getWork_id();
				
				String amendment_not_required_in_contract = getAmendmentNotRequiredInContract(contract_id,alert_type,alert_value,connection);
				String last_alert_id = getLastAlertId(contract_id,alert_level,alert_type,alert_value,connection);
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, getAlertRemarks(alert_type,contract_id,alert_value,connection));
                stmt.setString(p++, redirect_url);
                stmt.setString(p++, details);
                stmt.setString(p++, valid_upto);
                stmt.setString(p++, amendment_not_required_in_contract);
                
                if(alert_type.contains("R&R") || alert_type.contains("Land Acquisition") || alert_type.contains("Risk"))
                {
                	stmt.setString(p++, work_id);
                }
                else
                {
                	stmt.setString(p++, null);
                }
                int c = stmt.executeUpdate();
                resultSet = stmt.getGeneratedKeys();
                if(c > 0) {
                	String alert_id = null;
                	if(resultSet.next()) {
                		alert_id = String.valueOf(resultSet.getLong(1));
                	}
                	DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
                	
                	String qry = "INSERT INTO alerts_user(alerts_id_fk,user_id_fk,read_time)VALUES(?,?,?)";
    				stmt = connection.prepareStatement(qry);
    				
    				if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {
    					String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getHod_user_id_fk(),connection);
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getHod_user_id_fk());
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
					}
    				
    				if(!StringUtils.isEmpty(obj.getDy_hod_user_id_fk())) {
    					String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getDy_hod_user_id_fk(),connection);
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getDy_hod_user_id_fk());
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
    				}
    				
    				/******************************************************************/
    				
    				int arrSize = 0;					
					List<Contract> departments = getDepartmentList(contract_id, connection);
					for (Contract dept : departments) {
						int size = dept.getExecutivesList().size();
						arrSize = arrSize + size;
					}
					
					String contractExecutivesIds[]  = new String[arrSize];
					
					int i = 0;
					for (Contract dept : departments) {
						for (Contract exec : dept.getExecutivesList()) {
							contractExecutivesIds[i++] = exec.getExecutive_user_id_fk();
						}
					}
					
					for(int k=0; k<contractExecutivesIds.length; k++) {
						String read_time = getReadTimeFromAlertsUser(last_alert_id,contractExecutivesIds[k],connection);
						p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, contractExecutivesIds[k]);
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
				    }
					
					/******************************************************************/
    				
					/*if(!StringUtils.isEmpty(obj.getReporting_to_user_id()) && "3rd Alert".equals(alert_level) 
							&& ("Contract Period".equals(alert_type) || "Contract Value".equals(alert_type))) {
					    p = 1;
						stmt.setString(p++, alert_id);
					    stmt.setString(p++, obj.getReporting_to_user_id());
					    stmt.addBatch();
					}*/
    				
    				for (Alerts aObj : alertsSendManually) {
    					if(StringUtils.isEmpty(aObj.getAlert_level_fk()) && (!StringUtils.isEmpty(aObj.getAlert_type_fk()) && aObj.getAlert_type_fk().equals(alert_type))) {
    						String read_time = getReadTimeFromAlertsUser(last_alert_id,aObj.getUser_id_fk(),connection);
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.setString(p++, read_time);
    		                stmt.addBatch();
    					}
    					if(StringUtils.isEmpty(aObj.getAlert_type_fk()) && (!StringUtils.isEmpty(aObj.getAlert_level_fk()) && aObj.getAlert_level_fk().equals(alert_level))) {
    						String read_time = getReadTimeFromAlertsUser(last_alert_id,aObj.getUser_id_fk(),connection);
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.setString(p++, read_time);
    		                stmt.addBatch();
    					}
    					if((!StringUtils.isEmpty(aObj.getAlert_type_fk()) && aObj.getAlert_type_fk().equals(alert_type)) && (!StringUtils.isEmpty(aObj.getAlert_level_fk()) 
    							&& aObj.getAlert_level_fk().equals(alert_level))) {
    						String read_time = getReadTimeFromAlertsUser(last_alert_id, aObj.getUser_id_fk(),connection);
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.setString(p++, read_time);
    		                stmt.addBatch();
    					}
    					
    					if(StringUtils.isEmpty(aObj.getAlert_type_fk()) && StringUtils.isEmpty(aObj.getAlert_level_fk())) {
    						String read_time = getReadTimeFromAlertsUser(last_alert_id,aObj.getUser_id_fk(),connection);
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.setString(p++, read_time);
    		                stmt.addBatch();
    					}
					}   				
    				
	                stmt.executeBatch();
                }
			}
			
			generateIssueAlertsByCronJob();
			
			
			/*Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date); // dont forget this if date is arbitrary
			
			SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
			String month = monthFormat.format(date).toUpperCase();
			//int month = cal.get(Calendar.MONTH); // 0 being January
			int year = cal.get(Calendar.YEAR);
			
			int day = cal.get(Calendar.DAY_OF_MONTH);*/  
            //if(day != 2 && day != 4 ) {
            	generateRiskMainAlertsByCronJob();
            //}
			
            generateMitigationAndATRRiskAlertsByCronJob();
			
			flag = true;
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}

	private List<Alerts> getBGAlertsWithoutBGDetails() throws Exception {
		List<Alerts> list = new ArrayList<Alerts>();
		try {
			/***************************** BG alerts*******************************************************/
			String bgQryAlert1 = "select c.contract_id, 1st Alert as alert_level,Bank Guarantee as alert_type,"  
					+ "CONCAT(PBG for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id " 
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and bg_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 30 and DATEDIFF(loa_date ,GETDATE()) > 20)" 
					+ "and (select count(valid_upto) from bank_guarantee where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
				
			List<Alerts> bgQryAlert1List = jdbcTemplate.query( bgQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert1List) && bgQryAlert1List.size() > 0) {
				list.addAll(bgQryAlert1List);
			}
			
			String bgQryAlert2 = "select c.contract_id, 2nd Alert as alert_level,Bank Guarantee as alert_type,"  
					+ "CONCAT(PBG for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id " 
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and bg_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 20 and DATEDIFF(loa_date ,GETDATE()) > 10)" 
					+ "and (select count(valid_upto) from bank_guarantee where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
			
			List<Alerts> bgQryAlert2List = jdbcTemplate.query( bgQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert2List) && bgQryAlert2List.size() > 0) {
				list.addAll(bgQryAlert2List);
			}
			
			String bgQryAlert3 = "select c.contract_id, 3rd Alert as alert_level,Bank Guarantee as alert_type,"  
					+ "CONCAT(PBG for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id " 
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and bg_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 10 and DATEDIFF(loa_date ,GETDATE()) > 0)" 
					+ "and (select count(valid_upto) from bank_guarantee where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
			
			List<Alerts> bgQryAlert3List = jdbcTemplate.query( bgQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert3List) && bgQryAlert3List.size() > 0) {
				list.addAll(bgQryAlert3List);
			}

			String bgQryAlert4 = "select c.contract_id, Overdue as alert_level,Bank Guarantee as alert_type,"  
					+ "CONCAT(PBG for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=bgDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id "
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and bg_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 0)" 
					+ "and (select count(valid_upto) from bank_guarantee where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
			
			List<Alerts> bgQryAlert4List = jdbcTemplate.query( bgQryAlert4, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert4List) && bgQryAlert4List.size() > 0) {
				list.addAll(bgQryAlert4List);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return list;
	}
	
	private List<Alerts> getInsuranceAlertsWithoutInsuranceDetails() throws Exception {
		List<Alerts> list = new ArrayList<Alerts>();
		try {
			/***************************** Insurance alerts*******************************************************/
			String bgQryAlert1 = "select c.contract_id, 1st Alert as alert_level,Insurance as alert_type,"  
					+ "CONCAT(Insurance for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id " 
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and insurance_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 30 and DATEDIFF(loa_date ,GETDATE()) > 20)" 
					+ "and (select count(valid_upto) from insurance where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
				
			List<Alerts> bgQryAlert1List = jdbcTemplate.query( bgQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert1List) && bgQryAlert1List.size() > 0) {
				list.addAll(bgQryAlert1List);
			}
			
			String bgQryAlert2 = "select c.contract_id, 2nd Alert as alert_level,Insurance as alert_type,"  
					+ "CONCAT(Insurance for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id " 
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and insurance_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 20 and DATEDIFF(loa_date ,GETDATE()) > 10)" 
					+ "and (select count(valid_upto) from insurance where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
			
			List<Alerts> bgQryAlert2List = jdbcTemplate.query( bgQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert2List) && bgQryAlert2List.size() > 0) {
				list.addAll(bgQryAlert2List);
			}
			
			String bgQryAlert3 = "select c.contract_id, 3rd Alert as alert_level,Insurance as alert_type,"  
					+ "CONCAT(Insurance for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id " 
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and insurance_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 10 and DATEDIFF(loa_date ,GETDATE()) > 0)" 
					+ "and (select count(valid_upto) from insurance where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
			
			List<Alerts> bgQryAlert3List = jdbcTemplate.query( bgQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert3List) && bgQryAlert3List.size() > 0) {
				list.addAll(bgQryAlert3List);
			}

			String bgQryAlert4 = "select c.contract_id, Overdue as alert_level,Insurance as alert_type,"  
					+ "CONCAT(Insurance for new contract to be submitted) as alert_value," 
					+ "concat(/get-contract?contract_id=,c.contract_id,&tab_name=insuranceDetails) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk,u.reporting_to_id_srfk as reporting_to_user_id "
					+ "from contract c " 
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where contract_status_fk in (In Progress) and insurance_required = Yes and loa_date is not null " 
					+ "and (DATEDIFF(loa_date ,GETDATE()) <= 0)" 
					+ "and (select count(valid_upto) from insurance where contract_id_fk = c.contract_id and valid_upto is not null) = 0";
			
			List<Alerts> bgQryAlert4List = jdbcTemplate.query( bgQryAlert4, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert4List) && bgQryAlert4List.size() > 0) {
				list.addAll(bgQryAlert4List);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return list;
	}

	private List<Contract> getDepartmentList(String contract_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT id, contract_id_fk, department_id_fk,department_name, executive_user_id_fk from contract_executive ce  "
					+ "Left JOIN department dt on ce.department_id_fk = dt.department  "
					+ " where contract_id_fk = ? group by department_id_fk";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setDepartment_id_fk(resultSet.getString("department_id_fk"));
				
				obj.setExecutivesList(getExecutivesList(contract_id,obj.getDepartment_id_fk(),con));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return objsList;
	}
	
	private List<Contract> getExecutivesList(String contract_id,String departmentID, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		List<Contract> objsList = new ArrayList<Contract>();
		Contract obj = null;
		try {
			String qry ="SELECT executive_user_id_fk,u.user_name,u.designation from contract_executive c "
					+ "left join [user] u on c.executive_user_id_fk = u.user_id where contract_id_fk = ? and  department_id_fk = ?";
			stmt = con.prepareStatement(qry);
			stmt.setString(1, contract_id);
			stmt.setString(2, departmentID);
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				obj = new Contract();
				obj.setExecutive_user_id_fk(resultSet.getString("executive_user_id_fk"));
				obj.setUser_name(resultSet.getString("u.user_name"));
				obj.setDesignation(resultSet.getString("u.designation"));
				objsList.add(obj);
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
		}
		return objsList;
	}
	
	private String getAmendmentNotRequiredInContract(String contract_id, String alert_type, String alert_value, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String amendment_not_required_in_contract = null;
		try {
			String remarksQry ="select amendment_not_required_in_contract from alerts where contract_id = ? and alert_type_fk = ?  and alert_value = ?"
					+ " AND created_date = (select max(created_date) from alerts where contract_id = ? and alert_type_fk = ? and alert_value = ? offset 0 rows  fetch next 1 rows only)  offset 0 rows  fetch next 1 rows only";
			stmt = connection.prepareStatement(remarksQry);
			int p = 1;
            stmt.setString(p++, contract_id);
            stmt.setString(p++, alert_type);
            stmt.setString(p++, alert_value);
            stmt.setString(p++, contract_id);
            stmt.setString(p++, alert_type);
            stmt.setString(p++, alert_value);
            
            rs = stmt.executeQuery();
            if(rs.next()) {
            	amendment_not_required_in_contract = rs.getString("amendment_not_required_in_contract");
            }
            
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return amendment_not_required_in_contract;
	}
	
	private String getLastAlertId(String contract_id, String alert_level,  String alert_type, String alert_value,Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String alert_id = null;
		try {
			String qry ="select alert_id "
					+ "from alerts "
					+ "where alert_level = ? and alert_type_fk = ?  and alert_value = ? ";
					
			
			if(!StringUtils.isEmpty(contract_id)) {
				qry = qry + " and contract_id = ?"; 
			}
			qry = qry + " and created_date = (select max(created_date) "
					+ "from alerts "
					+ "where alert_level = ? and alert_type_fk = ? and alert_value = ? ";
			if(!StringUtils.isEmpty(contract_id)) {
				qry = qry + " and contract_id = ?"; 
			}		
			qry = qry + "offset 0 rows  fetch next 1 rows only)  offset 0 rows  fetch next 1 rows only";
			
			stmt = connection.prepareStatement(qry);
			int p = 1;
			stmt.setString(p++, alert_level);
            stmt.setString(p++, alert_type);
            stmt.setString(p++, alert_value);
            if(!StringUtils.isEmpty(contract_id)) {
            	stmt.setString(p++, contract_id);
			}
            stmt.setString(p++, alert_level);
            stmt.setString(p++, alert_type);
            stmt.setString(p++, alert_value);
            if(!StringUtils.isEmpty(contract_id)) {
            	stmt.setString(p++, contract_id);
			}
            
            rs = stmt.executeQuery();
            if(rs.next()) {
            	alert_id = rs.getString("alert_id");
            }
            
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return alert_id;
	}
	


	private String getReadTimeFromAlertsUser(String last_alert_id, String user_id_fk, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String read_time = null;
		try {
			String qry ="select read_time "
					+ "from alerts_user where alerts_id_fk = ? and user_id_fk = ? ";
			
			stmt = connection.prepareStatement(qry);
			int p = 1;
            
            stmt.setString(p++, last_alert_id);
            stmt.setString(p++, user_id_fk);
            
            rs = stmt.executeQuery();
            if(rs.next()) {
            	read_time = rs.getString("read_time");
            }
            
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return read_time;
	}
	

	public boolean generateIssueAlertsByCronJob() throws Exception {
		boolean flag = false;
		List<Alerts> list = new ArrayList<Alerts>();
		/*TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);*/
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			/***************************** Issue alerts*******************************************************/
			
			String qryAlert1 = "select contract_id_fk as contract_id, 1st Alert as alert_level,Issue as alert_type,"
					+ "concat(i.title,<br>Pending Since : ,DATEDIFF(GETDATE(), date), days.) as alert_value,"
					+ "(CASE WHEN status_fk = Closed THEN concat(/InfoViz/issues/closed-issues/,issue_id) ELSE concat(/InfoViz/issues/open-issues/,issue_id) END) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,i.corrective_measure,i.remarks "
					+ "from issue i "
					+ "LEFT JOIN contract c ON i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk  = d.department "
					+ "where status_fk <> Closed "
					+ "and DATEDIFF(GETDATE(),date) >= (select first_condition_value from alert_conditions where alert_type_fk = Issue and alert_level_fk = 1st Alert) and DATEDIFF(GETDATE(),date) < (select second_condition_value from alert_conditions where alert_type_fk = Issue and alert_level_fk = 1st Alert)";
			
		
			List<Alerts> alert1List = jdbcTemplate.query( qryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(alert1List) && alert1List.size() > 0) {
				list.addAll(alert1List);
			}			
			
			String qryAlert2 = "select contract_id_fk as contract_id, 2nd Alert as alert_level,Issue as alert_type,"
					+ "concat(i.title,<br>Pending Since : ,DATEDIFF(GETDATE(), date), days.) as alert_value,"
					+ "(CASE WHEN status_fk = Closed THEN concat(/InfoViz/issues/closed-issues/,issue_id) ELSE concat(/InfoViz/issues/open-issues/,issue_id) END) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,i.corrective_measure,i.remarks "
					+ "from issue i "
					+ "LEFT JOIN contract c ON i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk  = d.department "
					+ "where status_fk <> Closed "
					+ "and DATEDIFF(GETDATE(),date) >= (select first_condition_value from alert_conditions where alert_type_fk = Issue and alert_level_fk = 2nd Alert) and DATEDIFF(GETDATE(),date) < (select second_condition_value from alert_conditions where alert_type_fk = Issue and alert_level_fk = 2nd Alert)";
			
			List<Alerts> alert2List = jdbcTemplate.query( qryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(alert2List) && alert2List.size() > 0) {
				list.addAll(alert2List);
			}	
			
			/*String qryAlert3 = "select contract_id_fk as contract_id, 3rd Alert as alert_level,Issue as alert_type,"
					+ "concat(i.title,<br>Pending Since : ,DATEDIFF(GETDATE(), date), days.) as alert_value,"
					+ "(CASE WHEN status_fk = Closed THEN concat(/InfoViz/issues/closed-issues/,issue_id) ELSE concat(/InfoViz/issues/open-issues/,issue_id) END) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,i.corrective_measure,i.remarks "
					+ "from issue i "
					+ "LEFT JOIN contract c ON i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk  = d.department "
					+ "where status_fk <> Closed "
					+ "and DATEDIFF(GETDATE(),date) >= (select first_condition_value from alert_conditions where alert_type_fk = Issue and alert_level_fk = 3rd Alert)";
			
			List<Alerts> alert3List = jdbcTemplate.query( qryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(alert3List) && alert3List.size() > 0) {
				list.addAll(alert3List);
			}*/
			
			
			/*************************Alerts insertion********************************************/
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,count,remarks,redirect_url) VALUES  (?,?,?,?,?,?,?,?)";		
			
			for (Alerts obj : list) {
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				String remarks = null;
				
				if(!StringUtils.isEmpty(obj.getCorrective_measure())) {
					remarks = obj.getCorrective_measure();
				}
				if(!StringUtils.isEmpty(obj.getRemarks())) {
					remarks = remarks + "\n" + obj.getRemarks();
				}
				
				String last_alert_id = getLastAlertId(contract_id,alert_level,alert_type,alert_value,connection);
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, remarks);
                stmt.setString(p++, redirect_url);
                
                
                int c = stmt.executeUpdate();
                resultSet = stmt.getGeneratedKeys();
                if(c > 0) {
                	String alert_id = null;
                	if(resultSet.next()) {
                		alert_id = String.valueOf(resultSet.getLong(1));
                	}
                	DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
                	
                	String qry = "INSERT INTO alerts_user(alerts_id_fk,user_id_fk,read_time)VALUES(?,?,?)";
    				stmt = connection.prepareStatement(qry);
    				
    				if(!StringUtils.isEmpty(obj.getAlert_level()) && obj.getAlert_level().equals("2nd Alert")) {
    					if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {
    						String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getHod_user_id_fk(),connection);
			                p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, obj.getHod_user_id_fk());
			                stmt.setString(p++, read_time);
			                stmt.addBatch();
    					}
    				}
    				
    				if(!StringUtils.isEmpty(obj.getAlert_level()) && obj.getAlert_level().equals("3rd Alert")) {
    					String read_time = getReadTimeFromAlertsUser(last_alert_id,"PMIS_SU_001",connection);
    					p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, "PMIS_SU_001");
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
			                
		                if(!StringUtils.isEmpty(obj.getDepartment_name()) && obj.getDepartment_name().equals("Engineering")) {
		                	read_time = getReadTimeFromAlertsUser(last_alert_id,"PMIS_SU_002",connection);
		                	p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, "PMIS_SU_002");
			                stmt.setString(p++, read_time);
			                stmt.addBatch();
		                }
		                if(!StringUtils.isEmpty(obj.getDepartment_name()) && (obj.getDepartment_name().equals("Signalling & Telecom") 
		                		|| obj.getDepartment_name().equals("Electrical"))) {
		                	read_time = getReadTimeFromAlertsUser(last_alert_id,"PMIS_SU_003",connection);
		                	p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, "PMIS_SU_003");
			                stmt.setString(p++, read_time);
			                stmt.addBatch();
		                }
		                
		                if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {
		                	read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getHod_user_id_fk(),connection);
			                p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, obj.getHod_user_id_fk());
			                stmt.setString(p++, read_time);
			                stmt.addBatch();
    					}
		                
    				}
    				
    				if(!StringUtils.isEmpty(obj.getResponsible_person())) {
    					String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getResponsible_person(),connection);
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getResponsible_person());
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
					}
    				
    				if(!StringUtils.isEmpty(obj.getDy_hod_user_id_fk())) {
    					String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getDy_hod_user_id_fk(),connection);
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getDy_hod_user_id_fk());
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
    				}
    				
    				if(!StringUtils.isEmpty(obj.getEscalated_to())) {
    					String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getEscalated_to(),connection);
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getEscalated_to());
		                stmt.setString(p++, read_time);
		                stmt.addBatch();
    				}
    				
	                stmt.executeBatch();
                }
			}
			
			flag = true;
			
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}
	
	private boolean generateRiskMainAlertsByCronJob() throws Exception {
		boolean flag = false;
		List<Alerts> list = new ArrayList<Alerts>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;		
		try {
			connection = dataSource.getConnection();
			
			List<Alerts> risk_alerts = new ArrayList<Alerts>();
			
			String work_ids_qry = "select rwh.sub_work,rwh.work_id_fk as work_id,hod_user_id_fk,work_name,work_short_name,reporting_to_id_srfk as reporting_to_user_id "  
					+ "from risk_work_hod rwh "
					+ "left join work w on rwh.work_id_fk = w.work_id " 
					+ "left join [user] u on rwh.hod_user_id_fk = u.user_id " 
					+ "where (risk_work_completed is null or risk_work_completed = No or risk_work_completed = ) "
					+ "and (select count(*) from risk r1 left join risk_revision rr1 on r1.risk_id_pk = rr1.risk_id_pk_fk where r1.sub_work = rwh.sub_work and FORMAT(rr1.date,'%m-%Y') = FORMAT(GETDATE(),'%m-%Y')) <= 0 "
					+ "and (select count(*) from risk r1 left join risk_revision rr1 on r1.risk_id_pk = rr1.risk_id_pk_fk where r1.sub_work = rwh.sub_work) >= 1 "
					+ "group by rwh.sub_work,hod_user_id_fk";		
			risk_alerts = jdbcTemplate.query( work_ids_qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/***************************** Risk alerts*******************************************************/
			if(!StringUtils.isEmpty(risk_alerts) && risk_alerts.size() > 0) {
				 Date date = new Date();
				 //Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-06"); 
				 Calendar cal = Calendar.getInstance();
	             cal.setTime(date); // dont forget this if date is arbitrary
	             
	             SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
	             String month = monthFormat.format(date).toUpperCase();
	             //int month = cal.get(Calendar.MONTH); // 0 being January
	             int year = cal.get(Calendar.YEAR);
	             
	             int day = cal.get(Calendar.DAY_OF_MONTH);                
	             int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	             //System.out.println("Days : "+days);
	             //System.out.println("day : "+day);
	             for (Alerts alerts : risk_alerts) {
            		 Alerts aObj = new Alerts();
            		 if(day == 1 || day == 2) {
            			 aObj.setAlert_level("1st Alert");
            			 aObj.setAlert_value("1st Alert!! Risk assessment of "+alerts.getSub_work()+ " is due.");
            		 }else if(day == 3 || day == 4) {
            			 aObj.setAlert_level("2nd Alert");
            			 aObj.setAlert_value("2nd Alert!! Risk assessment of "+alerts.getSub_work()+ " is due.");
            		 }else if(day == 5) {
            			 aObj.setAlert_level("3rd Alert");
            			 aObj.setAlert_value("3rd Alert!! Risk assessment of "+alerts.getSub_work()+ " is due.");
            		 }else if(day > 5) {
            			 aObj.setAlert_level("Overdue");
            			 aObj.setAlert_value("Urgent!! Risk assessment of "+alerts.getSub_work()+ " is overdue.");
            		 }
            		 aObj.setAlert_type("Risk");
            		 aObj.setRedirect_url("/risk-assessment?sub_work="+alerts.getSub_work());
            		 aObj.setUser_id_fk(alerts.getHod_user_id_fk());
            		 aObj.setWork_id(alerts.getWork_id());
            		 if(day > 5) {
            			 aObj.setReporting_to_user_id(alerts.getReporting_to_user_id());
            		 }
 	 				 list.add(aObj);
				 }
			}
			/*************************Alerts insertion********************************************/
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,count,remarks,redirect_url,work_id) VALUES  (?,?,?,?,?,?,?,?,?)";		
			
			for (Alerts obj : list) {
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				String work_id = obj.getWork_id();
				
				String last_alert_id = getLastAlertId(contract_id,alert_level,alert_type,alert_value,connection);
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, null);
                stmt.setString(p++, redirect_url);
                stmt.setString(p++, work_id);
                
                int c = stmt.executeUpdate();
                resultSet = stmt.getGeneratedKeys();
                if(c > 0) {
                	String alert_id = null;
                	if(resultSet.next()) {
                		alert_id = String.valueOf(resultSet.getLong(1));
                	}
                	DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
                	
                	String qry = "INSERT INTO alerts_user(alerts_id_fk,user_id_fk,no_email,read_time)VALUES(?,?,?,?)";
    				stmt = connection.prepareStatement(qry);
    				
    				String read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getUser_id_fk(),connection);
    				
    				p = 1;
    				stmt.setString(p++, alert_id);
	                stmt.setString(p++, obj.getUser_id_fk());
	                stmt.setString(p++, null);
	                stmt.setString(p++, read_time);
	                stmt.addBatch();
	                
					/*if(!StringUtils.isEmpty(obj.getReporting_to_user_id()) 
							&& ("3rd Alert".equals(obj.getAlert_level()) || "Overdue".equals(obj.getAlert_level())) ) {*/
					if(!StringUtils.isEmpty(obj.getReporting_to_user_id()) 
							&& ("Overdue".equals(obj.getAlert_level())) ) {
						read_time = getReadTimeFromAlertsUser(last_alert_id,obj.getReporting_to_user_id(),connection);
						p = 1;
						stmt.setString(p++, alert_id);
					    stmt.setString(p++, obj.getReporting_to_user_id());
					    stmt.setString(p++, "Yes");
					    stmt.setString(p++, read_time);
					    stmt.addBatch();
					}
					if("Overdue".equals(obj.getAlert_level()) && "PMIS_SU_002".equals(obj.getReporting_to_user_id())) {
						read_time = getReadTimeFromAlertsUser(last_alert_id,"PMIS_SU_006",connection);
						p = 1;
						stmt.setString(p++, alert_id);
					    stmt.setString(p++, "PMIS_SU_006");
					    stmt.setString(p++, "Yes");
					    stmt.setString(p++, read_time);
					    stmt.addBatch();
					    
					    read_time = getReadTimeFromAlertsUser(last_alert_id,"PMIS_SU_052",connection);
					    p = 1;
						stmt.setString(p++, alert_id);
					    stmt.setString(p++, "PMIS_SU_052");
					    stmt.setString(p++, "Yes");
					    stmt.setString(p++, read_time);
					    stmt.addBatch();
					}
	                
					/*p = 1;
					stmt.setString(p++, alert_id);
					stmt.setString(p++, "PMIS_SU_003");
					stmt.addBatch();*/
    				
	                stmt.executeBatch();
                }
			}
			
			flag = true;
			
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}
	
	private boolean generateMitigationAndATRRiskAlertsByCronJob() throws Exception {
		boolean flag = false;
		List<Alerts> list = new ArrayList<Alerts>();
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;	
		try {
			connection = dataSource.getConnection();
			
			String mitigation_alerts_qry = "select r.sub_work,owner,u.user_id as owner_user_id,FORMAT(date,'dd-MMM-yy') as assessment_date,"
					+ "(CASE "
					+ "WHEN (rr.date is not null and DATEDIFF(GETDATE(),rr.date) = 1) THEN 1st Alert "
					+ "WHEN (rr.date is not null and DATEDIFF(GETDATE(),rr.date) = 2) THEN 2nd Alert " 
					+ "WHEN (rr.date is not null and DATEDIFF(GETDATE(),rr.date) = 3) THEN 3rd Alert "
					+ "ELSE Overdue "
					+ "END "
					+ ") as alert_level,(select r1.work_id_fk from risk_work_hod r1 where r1.sub_work = r.sub_work) as work_id "
					+ "from risk_revision rr " 
					+ "left join risk r on risk_id_pk_fk = risk_id_pk "
					+ "left join risk_work_hod r1 on r1.sub_work = r.sub_work "
					+ "left join [user] u on owner = u.designation " 
					+ "where date = (SELECT MAX(date) FROM risk_revision LEFT JOIN risk ON risk_id_pk = risk_id_pk_fk WHERE sub_work = r.sub_work) " 
					+ "and priority_fk <> Accepted " 
					+ "and (mitigation_plan is null or mitigation_plan = ) " 
					+ "group by r.sub_work,owner";
			
			List<Alerts> risk_mitigation_alerts = jdbcTemplate.query( mitigation_alerts_qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/***************************** Risk alerts*******************************************************/
			if(!StringUtils.isEmpty(risk_mitigation_alerts) && risk_mitigation_alerts.size() > 0) {
	             for (Alerts alerts : risk_mitigation_alerts) {
            		 Alerts aObj = new Alerts();
            		 aObj.setAlert_level(alerts.getAlert_level());
            		 aObj.setAlert_value("Please update mitigation plan against prioritized risk(s) of "+alerts.getSub_work() + ".");
            		 aObj.setAlert_type("Risk");
            		 aObj.setRedirect_url("/risk-atr-update?sub_work="+alerts.getSub_work()+"&assessment_date="+alerts.getAssessment_date());
            		 aObj.setOwner_user_id(alerts.getOwner_user_id());
            		 aObj.setWork_id(alerts.getWork_id());
 	 				 list.add(aObj);
				 }
			}
			
			/****************************************************************************************/

			String atr_alerts_qry = "select distinct r.sub_work,owner,u1.user_id as owner_user_id,responsible_person,u2.user_id as responsible_person_user_id,count(risk_action_id) as racount,FORMAT(date,'dd-MMM-yy') as assessment_date, "
					+ "(CASE "
					+ "WHEN (rr.date is not null and DATEDIFF(GETDATE(),rr.date) = 1) THEN 1st Alert "
					+ "WHEN (rr.date is not null and DATEDIFF(GETDATE(),rr.date) = 2) THEN 2nd Alert " 
					+ "WHEN (rr.date is not null and DATEDIFF(GETDATE(),rr.date) = 3) THEN 3rd Alert "
					+ "ELSE Overdue "
					+ "END "
					+ ") as alert_level,(select r1.work_id_fk from risk_work_hod r1 where r1.sub_work = r.sub_work) as work_id "
					+ "from risk_revision rr "  
					+ "left join risk r on risk_id_pk_fk = risk_id_pk "
					+ "left join risk_action ra on risk_revision_id_fk = risk_revision_id "
					+ "left join [user] u1 on owner = u1.designation "
					+ "left join [user] u2 on responsible_person = u2.designation " 
					+ "where date = (SELECT MAX(date) FROM risk_revision LEFT JOIN risk ON risk_id_pk = risk_id_pk_fk WHERE sub_work = r.sub_work) " 
					+ "and priority_fk <> Accepted " 
					+ "group by r.sub_work,risk_revision_id having racount = 0 "  
					+ "order by r.sub_work";
			
			List<Alerts> risk_atr_alerts = jdbcTemplate.query( atr_alerts_qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/***************************** Risk alerts*******************************************************/
			if(!StringUtils.isEmpty(risk_atr_alerts) && risk_atr_alerts.size() > 0) {
				
	             for (Alerts alerts : risk_atr_alerts) {
            		 Alerts aObj = new Alerts();
            		 aObj.setAlert_level(alerts.getAlert_level());
            		 aObj.setAlert_value("Please update ATR against prioritized risk(s) of "+alerts.getSub_work() + ".");
            		 aObj.setAlert_type("Risk");
            		 aObj.setRedirect_url("/risk-atr-update?sub_work="+alerts.getSub_work()+"&assessment_date="+alerts.getAssessment_date());
            		 aObj.setOwner_user_id(alerts.getOwner_user_id());
            		 aObj.setResponsible_person_user_id(alerts.getResponsible_person_user_id());
            		 /**************************************************/
            		 aObj.setHod_email(alerts.getOwner());
            		 aObj.setWork_id(alerts.getWork_id());
            		 /**************************************************/
 	 				 list.add(aObj);
				 }
			}
			
			/*************************Alerts insertion********************************************/
			
			String qryInsert = "INSERT INTO alerts "
					+ "(alert_level,alert_type_fk,contract_id,alert_status,alert_value,count,remarks,redirect_url,hod_email,work_id)"
					+ " VALUES  (?,?,?,?,?,?,?,?,?,?)";		
			
			for (Alerts obj : list) {
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				String hod_email = obj.getHod_email();
				String work_id = obj.getWork_id();
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, null);
                stmt.setString(p++, redirect_url);
                stmt.setString(p++, hod_email);
                stmt.setString(p++, work_id);
                
                int c = stmt.executeUpdate();
                resultSet = stmt.getGeneratedKeys();
                if(c > 0) {
                	String alert_id = null;
                	if(resultSet.next()) {
                		alert_id = String.valueOf(resultSet.getLong(1));
                	}
                	DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
                	
                	String qry = "INSERT INTO alerts_user(alerts_id_fk,user_id_fk)VALUES(?,?)";
    				stmt = connection.prepareStatement(qry);
    				
    				if(!StringUtils.isEmpty(obj.getOwner_user_id())) {
    					p = 1;
    					stmt.setString(p++, alert_id);
    	                stmt.setString(p++, obj.getOwner_user_id());
    	                stmt.addBatch();
    				}
    				if(!StringUtils.isEmpty(obj.getResponsible_person_user_id())) {
    					p = 1;
    					stmt.setString(p++, alert_id);
    	                stmt.setString(p++, obj.getResponsible_person_user_id());
    	                stmt.addBatch();
    				}    				
	                stmt.executeBatch();
                }
			}
			
			flag = true;
			
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}

	private String getAlertRemarks(String alert_type, String contract_id, String alert_value, Connection connection) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String remarks = null;
		try {
			String remarksQry ="select remarks from alerts where alert_type_fk = ? and contract_id = ? and alert_value = ? "
    				+ "AND created_date = (select max(created_date) from alerts where alert_type_fk = ? and contract_id = ? and alert_value = ? )";
			stmt = connection.prepareStatement(remarksQry);
			int p = 1;
            stmt.setString(p++, alert_type);
            stmt.setString(p++, contract_id);
            stmt.setString(p++, alert_value);
            stmt.setString(p++, alert_type);
            stmt.setString(p++, contract_id);
            stmt.setString(p++, alert_value);
            
            rs = stmt.executeQuery();
            if(rs.next()) {
            	remarks = rs.getString("remarks");
            }
            
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return remarks;
	}


	@Override
	public boolean sendEMailNotificationWithContractAlerts(String alert_type) throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			String userIdQry = "SELECT user_id_fk,u2.email_id "
					+ "FROM alerts a " 
					+ "left join alerts_user u on u.alerts_id_fk = a.alert_id " 
					+ "left join [user] u2 on u.user_id_fk = u2.user_id " 
					+ "where a.alert_status = Active and count <> 0 "
					+ "and (amendment_not_required_in_contract is null or amendment_not_required_in_contract =  or amendment_not_required_in_contract = No) and u2.email_id is not null and u2.email_id <>  and a.alert_type_fk <> Risk ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(alert_type)) {
				String atP = "";
				userIdQry = userIdQry + " and a.alert_type_fk IN(";
				String[] types = alert_type.split(",");
				for (int p=0; p < types.length; p++) {
					userIdQry = userIdQry + (atP.contains("?")?",":"")+"?";
					atP = atP + "?";
					arrSize++;
				}
				userIdQry = userIdQry + ")";
			}
			userIdQry = userIdQry +  "group by user_id_fk";
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(alert_type)) {
				String[] types = alert_type.split(",");
				for (int p=0; p < types.length; p++) {
					pValues[i++] = types[p];
				}
			}
			
			List<Alerts> userIdList = jdbcTemplate.query( userIdQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			Map<String,List<Alerts>> alerts = new LinkedHashMap<String, List<Alerts>>();
			for (Alerts uObj : userIdList) {
				alerts = new LinkedHashMap<String, List<Alerts>>();
				String aLevelQry = "select alert_level,"
						+ "(select first_condition_value from alert_conditions ac where ac.alert_type_fk = a.alert_type_fk and ac.alert_level_fk = a.alert_level) as condition_value " 
						+ "from alerts a "  
						+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id "
						+ "left outer join work w1 on w1.work_id = a.work_id "
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
						+ "where (amendment_not_required_in_contract is null or amendment_not_required_in_contract =  or amendment_not_required_in_contract = No) and alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk <> Risk ";
				
				arrSize = 2;
				if(!StringUtils.isEmpty(alert_type)) {
					String atP = "";
					aLevelQry = aLevelQry + " and a.alert_type_fk IN(";
					String[] types = alert_type.split(",");
					for (int p=0; p < types.length; p++) {
						aLevelQry = aLevelQry + (atP.contains("?")?",":"")+"?";
						atP = atP + "?";
						arrSize++;
					}
					aLevelQry = aLevelQry + ")";
				}
				aLevelQry = aLevelQry +  " group by alert_level order by alert_level desc";
				
				
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				pValues[i++] = uObj.getUser_id_fk();
				if(!StringUtils.isEmpty(alert_type)) {
					String[] types = alert_type.split(",");
					for (int p=0; p < types.length; p++) {
						pValues[i++] = types[p];
					}
				}
				
				List<Alerts> alert_levels = jdbcTemplate.query( aLevelQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				
				for (Alerts lObj : alert_levels) {
					
					String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,count,u.designation as hod,"
							+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,ISNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
							+ "left join contract c on a.contract_id = c.contract_id " 
							+ "left join work w on c.work_id_fk = w.work_id "
							+ "left outer join work w1 on w1.work_id = a.work_id "
							+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
							+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
							+ "where (amendment_not_required_in_contract is null or amendment_not_required_in_contract =  or amendment_not_required_in_contract = No) and alert_level = ? and alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk <> Risk ";
					
					arrSize = 3;
					if(!StringUtils.isEmpty(alert_type)) {
						String atP = "";
						qry = qry + " and a.alert_type_fk IN(";
						String[] types = alert_type.split(",");
						for (int p=0; p < types.length; p++) {
							qry = qry + (atP.contains("?")?",":"")+"?";
							atP = atP + "?";
							arrSize++;
						}
						qry = qry + ")";
					}
					qry = qry +  " order by hod,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as,a.contract_id asc, alert_level desc";
					
					
					pValues = new Object[arrSize];
					i = 0;
					pValues[i++] = lObj.getAlert_level();
					pValues[i++] = CommonConstants.ACTIVE;
					pValues[i++] = uObj.getUser_id_fk();
					if(!StringUtils.isEmpty(alert_type)) {
						String[] types = alert_type.split(",");
						for (int p=0; p < types.length; p++) {
							pValues[i++] = types[p];
						}
					}
					
					List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
					
					for (Alerts alert : allAlertsList) {
						if("Bank Guarantee".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("bank_guarantee.png");
						}else if("Insurance".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("insurance.png");
						}else if("Contract Period".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("contract_period.png");
						}else if("Contract Value".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("contract_value.png");
						}else if("Issue".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("issue.png");
						}
					}
					
					String alert_level = lObj.getAlert_level();
					if(alert_type.equals(CommonConstants2.ALERT_TYPE_CONTRACT_VALUE)) {
						if(alert_level.equals("Overdue")) {
							alert_level = alert_level + " (Expenditure>Contract Value)";
						}else if(alert_level.equals("3rd Alert")) {
							alert_level = alert_level + " (Expenditure>"+lObj.getCondition_value()+"% of Contract Value)";
						}else if(alert_level.equals("2nd Alert")) {
							alert_level = alert_level + " (Expenditure>"+lObj.getCondition_value()+"% of Contract Value)";
						}else if(alert_level.equals("1st Alert")) {
							alert_level = alert_level + " (Expenditure>"+lObj.getCondition_value()+"% of Contract Value)";
						}
					}else{
						if(alert_level.equals("Overdue")) {
							alert_level = alert_level + " (Expired)";
						}else if(alert_level.equals("3rd Alert")) {
							alert_level = alert_level + " (Expiry in "+lObj.getCondition_value()+" days)";
						}else if(alert_level.equals("2nd Alert")) {
							alert_level = alert_level + " (Expiry in "+lObj.getCondition_value()+" days)";
						}else if(alert_level.equals("1st Alert")) {
							alert_level = alert_level + " (Expiry in "+lObj.getCondition_value()+" days)";
						}
					}
					
					int k = 0;
					for (Alerts alert : allAlertsList) {
						if(k+1 < allAlertsList.size()) {
							Alerts tempAObj =  allAlertsList.get(k+1);
							if(alert.getHod().equals(tempAObj.getHod()) && alert.getContract_short_name().equals(tempAObj.getContract_short_name())) {
								alert.setRowspan("true");
								tempAObj.setHod("");
								tempAObj.setContract_short_name("");
							}
						}
						k++;
					}
					
					if(!StringUtils.isEmpty(allAlertsList) && allAlertsList.size() > 0) {
						alerts.put(alert_level, allAlertsList);
					}
				}
				
				if(alerts != null && alerts.size() > 0) {
					SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
		            String today_date = monthFormat.format(new Date()).toUpperCase();
		            
		            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
		            String current_year = yearFormat.format(new Date()).toUpperCase();
		            
					String emailSubject = "PMIS "+alert_type+" Alerts";
		            //String alert_types = "Contract";
		            //String emailSubject = "PMIS Contract Alerts";
					Mail mail = new Mail();
					mail.setMailTo(uObj.getEmail_id());
					mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("ContractAlerts.vm");
					
					logger.error("sendEMailNotificationWithContractAlerts() >> Alert Type "+alert_type+ ". Sending mail to "+uObj.getEmail_id()+": Start ");	
					emailSender.sendEmailWithContractAlerts(mail,alerts,today_date,current_year,alert_type); 
					logger.error("sendEMailNotificationWithContractAlerts() >> Alert Type "+alert_type+ ".  Sending mail to "+uObj.getEmail_id()+": End ");
				}
					
				flag = true;
				Thread.sleep(1000*10);//time is in ms (1000 ms = 1 second)
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	
	@Override
	public boolean sendEMailNotificationWithIssueAlerts(String alert_type) throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			
			String userIdQry = "SELECT user_id_fk,u2.email_id "
					+ "FROM alerts a " 
					+ "left join alerts_user u on u.alerts_id_fk = a.alert_id " 
					+ "left join [user] u2 on u.user_id_fk = u2.user_id " 
					+ "where a.alert_status = Active and count <> 0 "
					+ "and (amendment_not_required_in_contract is null or amendment_not_required_in_contract =  or amendment_not_required_in_contract = No) and u2.email_id is not null and u2.email_id <>  and a.alert_type_fk <> Risk ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(alert_type)) {
				String atP = "";
				userIdQry = userIdQry + " and a.alert_type_fk IN(";
				String[] types = alert_type.split(",");
				for (int p=0; p < types.length; p++) {
					userIdQry = userIdQry + (atP.contains("?")?",":"")+"?";
					atP = atP + "?";
					arrSize++;
				}
				userIdQry = userIdQry + ")";
			}
			userIdQry = userIdQry +  "group by user_id_fk";
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(alert_type)) {
				String[] types = alert_type.split(",");
				for (int p=0; p < types.length; p++) {
					pValues[i++] = types[p];
				}
			}
			
			List<Alerts> userIdList = jdbcTemplate.query( userIdQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			Map<String,List<Alerts>> alerts = new LinkedHashMap<String, List<Alerts>>();
			for (Alerts uObj : userIdList) {
				alerts = new LinkedHashMap<String, List<Alerts>>();
				String aLevelQry = "select alert_level " 
						+ "from alerts a "  
						+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id "
						+ "left outer join work w1 on w1.work_id = a.work_id "
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
						+ "where (amendment_not_required_in_contract is null or amendment_not_required_in_contract =  or amendment_not_required_in_contract = No) and alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk <> Risk ";
				
				arrSize = 2;
				if(!StringUtils.isEmpty(alert_type)) {
					String atP = "";
					aLevelQry = aLevelQry + " and a.alert_type_fk IN(";
					String[] types = alert_type.split(",");
					for (int p=0; p < types.length; p++) {
						aLevelQry = aLevelQry + (atP.contains("?")?",":"")+"?";
						atP = atP + "?";
						arrSize++;
					}
					aLevelQry = aLevelQry + ")";
				}
				aLevelQry = aLevelQry +  " group by alert_level order by alert_level desc";
				
				
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				pValues[i++] = uObj.getUser_id_fk();
				if(!StringUtils.isEmpty(alert_type)) {
					String[] types = alert_type.split(",");
					for (int p=0; p < types.length; p++) {
						pValues[i++] = types[p];
					}
				}
				
				List<Alerts> alert_levels = jdbcTemplate.query( aLevelQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				
				for (Alerts lObj : alert_levels) {
					
					String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,count,u.designation as hod,"
							+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,ISNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
							+ "left join contract c on a.contract_id = c.contract_id " 
							+ "left join work w on c.work_id_fk = w.work_id " 
							+ "left outer join work w1 on w1.work_id = a.work_id "
							+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
							+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
							+ "where (amendment_not_required_in_contract is null or amendment_not_required_in_contract =  or amendment_not_required_in_contract = No) and alert_level = ? and alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk <> Risk ";
					
					arrSize = 3;
					if(!StringUtils.isEmpty(alert_type)) {
						String atP = "";
						qry = qry + " and a.alert_type_fk IN(";
						String[] types = alert_type.split(",");
						for (int p=0; p < types.length; p++) {
							qry = qry + (atP.contains("?")?",":"")+"?";
							atP = atP + "?";
							arrSize++;
						}
						qry = qry + ")";
					}
					qry = qry +  " order by hod,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,a.contract_id asc, alert_level desc";
					
					
					pValues = new Object[arrSize];
					i = 0;
					pValues[i++] = lObj.getAlert_level();
					pValues[i++] = CommonConstants.ACTIVE;
					pValues[i++] = uObj.getUser_id_fk();
					if(!StringUtils.isEmpty(alert_type)) {
						String[] types = alert_type.split(",");
						for (int p=0; p < types.length; p++) {
							pValues[i++] = types[p];
						}
					}
					
					List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
					
					alerts.put(lObj.getAlert_level(), allAlertsList);
				}
				
				if(alerts != null && alerts.size() > 0) {					
					SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
		            String today_date = monthFormat.format(new Date()).toUpperCase();
		            
		            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
		            String current_year = yearFormat.format(new Date()).toUpperCase();
		            
					String alert_types = "Issue";
		            String emailSubject = "PMIS Issue Alerts";
		            
					Mail mail = new Mail();
					mail.setMailTo(uObj.getEmail_id());
					mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("IssueAlerts.vm");
					
					logger.error("sendEMailNotificationWithIssueAlerts() >> Sending mail to "+uObj.getEmail_id()+": Start ");	
					emailSender.sendEmailWithIssueAlerts(mail,alerts,today_date,current_year,alert_types); 
					logger.error("sendEMailNotificationWithIssueAlerts() >> Sending mail to "+uObj.getEmail_id()+": End ");
				}
					
				flag = true;
				Thread.sleep(1000*10);//time is in ms (1000 ms = 1 second)
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	
	@Override
	public boolean sendEMailNotificationWithRiskAlerts() throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			String userIdQry = "SELECT au.user_id_fk,u.email_id,ucc.email_id as reporting_to_email_id,u.reporting_to_id_srfk as reporting_to_user_id "
					+ "FROM alerts a " 
					+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
					+ "left join [user] u on au.user_id_fk = u.user_id "
					+ "left join [user] ucc on u.reporting_to_id_srfk = ucc.user_id "
					+ "where a.alert_status = Active and count <> 0 "
					+ "and u.email_id is not null and u.email_id <>  and a.alert_type_fk = Risk and no_email is null "
					+ "group by au.user_id_fk";
			
			List<Alerts> userIdList = jdbcTemplate.query( userIdQry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			if(userIdList != null && userIdList.size() > 0) {
				for (Alerts uObj : userIdList) {
					String qry = "select alert_id,alert_level,alert_type_fk,a.created_date,alert_status,alert_value,count,"
							+ "(CASE "
							+ "WHEN a.hod_email is null THEN u.designation "
							+ "ELSE a.hod_email "
							+ "END ) as hod,"
							+ "ISNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join alerts_user au on au.alerts_id_fk = a.alert_id "
							+ "left join [user] u on au.user_id_fk = u.user_id " 
							+ "where alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk = Risk "
							+ "order by hod,alert_level desc";
					
					Object[] pValues = new Object[] {CommonConstants.ACTIVE,uObj.getUser_id_fk()};
					List<Alerts> riskAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
					
					List<Alerts> riskMainAlertsList = new ArrayList<Alerts>();
					List<Alerts> riskMitigationPlanAlertsList = new ArrayList<Alerts>();
					List<Alerts> riskATRAlertsList = new ArrayList<Alerts>();
					
					boolean isOverdue = false;
					if(riskAlertsList != null && riskAlertsList.size() > 0) {
						for (Alerts alerts : riskAlertsList) {
							String sub_work = "";
							if(!StringUtils.isEmpty(alerts.getRedirect_url())) {
								String[] url_arr = alerts.getRedirect_url().split("=");
								sub_work = url_arr[1];
								
								if(sub_work.contains("&assessment_date")) {
									String[] url_arr2 = sub_work.split("&assessment_date");
									sub_work = url_arr2[0];
								}								
							}
							if(!StringUtils.isEmpty(sub_work)) {
								String workQry = "select rwh.sub_work,rwh.work_id_fk as work_id,hod_user_id_fk,work_name,work_short_name "  
										+ "from risk_work_hod rwh "
										+ "left join work w on rwh.work_id_fk = w.work_id "
										+ "where rwh.sub_work = ?";			
								List<Alerts> wObjList = jdbcTemplate.query( workQry,new Object[]{sub_work},new BeanPropertyRowMapper<Alerts>(Alerts.class));
								if(wObjList != null && wObjList.size() > 0) {
									for (Alerts wObj : wObjList) {
										if(!StringUtils.isEmpty(wObj)) {
											alerts.setSub_work(sub_work);
											alerts.setWork_id(wObj.getWork_id());
											alerts.setWork_name(wObj.getWork_name());
											alerts.setWork_short_name(wObj.getWork_short_name());
										}
									}
								}
							}							
							if(!StringUtils.isEmpty(alerts.getAlert_value())) {
								if(alerts.getAlert_value().contains("Risk assessment of")) {
									riskMainAlertsList.add(alerts);
									if("Overdue".equals(alerts.getAlert_level())) {
										isOverdue = true;
									}
								}else if(alerts.getAlert_value().contains("Please update mitigation plan against")) {
									riskMitigationPlanAlertsList.add(alerts);
								}else if(alerts.getAlert_value().contains("Please update ATR against")) {
									riskATRAlertsList.add(alerts);
								}
							}							
						}
						SimpleDateFormat df = new SimpleDateFormat("dd-MMM-YYYY");
			            String today_date = df.format(new Date()).toUpperCase();
			            
			            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
			            String current_year = yearFormat.format(new Date()).toUpperCase();			            
						
						String emailSubject = null;			
								
						Date date = new Date();
						Calendar cal = Calendar.getInstance();
						cal.setTime(date); // dont forget this if date is arbitrary
						
						SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
						String month = monthFormat.format(date).toUpperCase();
						//int month = cal.get(Calendar.MONTH); // 0 being January
						int year = cal.get(Calendar.YEAR);
						
						int day = cal.get(Calendar.DAY_OF_MONTH);  
			            if(day != 2 && day != 4 ) {
							if(riskMainAlertsList.size() > 0 && !StringUtils.isEmpty(uObj.getEmail_id())) {	
								Mail mail = new Mail();
								mail.setMailTo(uObj.getEmail_id());
								mail.setMailBcc(CommonConstants.BCC_MAIL);
								mail.setTemplateName("RiskAlerts.vm");
								if(isOverdue && !StringUtils.isEmpty(uObj.getReporting_to_email_id())) {
									mail.setMailCc(uObj.getReporting_to_email_id());
								}
								if(isOverdue && "PMIS_SU_002".equals(uObj.getReporting_to_user_id())) {
									List<String> ccmails = jdbcTemplate.queryForList("select email_id from user where user_id in(PMIS_SU_006,PMIS_SU_052)",String.class);
									if(!StringUtils.isEmpty(ccmails) && ccmails.size() > 0) {
										String ccemails = org.apache.commons.lang3.StringUtils.join(ccmails, ',');
										mail.setMailCc(mail.getMailCc()+","+ccemails);
									}
									
								}
								emailSubject = "PMIS Risk Assessment Due";
								mail.setMailSubject(emailSubject);
								logger.error("sendEMailNotificationWithRiskAlert() >>Assessment Due Sending mail to "+uObj.getEmail_id()+": Start ");	
								emailSender.sendEmailWithRiskAlerts(mail,riskMainAlertsList,today_date,current_year); 
								logger.error("sendEMailNotificationWithRiskAlert() >>Assessment Due Sending mail to "+uObj.getEmail_id()+": End ");
							}
			            }
						if(riskMitigationPlanAlertsList.size() > 0 && !StringUtils.isEmpty(uObj.getEmail_id())) {
							Mail mail = new Mail();
							mail.setMailTo(uObj.getEmail_id());
							mail.setMailBcc(CommonConstants.BCC_MAIL);
							mail.setTemplateName("RiskAlerts.vm");
							emailSubject = "PMIS Risk Assessment- Mitigation Plan";
							mail.setMailSubject(emailSubject);
							logger.error("sendEMailNotificationWithRiskAlert() >> Mitigation Plan Sending mail to "+uObj.getEmail_id()+": Start ");	
							emailSender.sendEmailWithRiskAlerts(mail,riskMitigationPlanAlertsList,today_date,current_year); 
							logger.error("sendEMailNotificationWithRiskAlert() >> Mitigation Plan Sending mail to "+uObj.getEmail_id()+": End ");
						}
						if(riskATRAlertsList.size() > 0 && !StringUtils.isEmpty(uObj.getEmail_id())) {
							Mail mail = new Mail();
							mail.setMailTo(uObj.getEmail_id());
							mail.setMailBcc(CommonConstants.BCC_MAIL);
							mail.setTemplateName("RiskAlerts.vm");
							emailSubject = "PMIS Risk Assessment- Action Taken Report";
							mail.setMailSubject(emailSubject);
							logger.error("sendEMailNotificationWithRiskAlert() >> ATR Sending mail to "+uObj.getEmail_id()+": Start ");	
							emailSender.sendEmailWithRiskAlerts(mail,riskATRAlertsList,today_date,current_year); 
							logger.error("sendEMailNotificationWithRiskAlert() >> ATR Sending mail to "+uObj.getEmail_id()+": End ");
						}						
						
					}
						
					flag = true;
					Thread.sleep(1000*10);//time is in ms (1000 ms = 1 second)
				}
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}

	
	@Override
	public boolean sendEMailNotificationAlertsToITAdmins() throws Exception {
		boolean flag = false;
		try {
			List<Alerts> alert_types = new ArrayList<Alerts>();
			
			/*String aTypeQry = "select alert_type_fk " 
					+ "from alerts a " 
					+ "left join contract c on a.contract_id = c.contract_id " 
					+ "left join work w on c.work_id_fk = w.work_id " 
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 and a.alert_type_fk <> Risk "
					+ "group by alert_type_fk order by alert_type_fk desc";
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> alert_types = jdbcTemplate.query( aTypeQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));*/
			Alerts contractAlertTypeObj = new Alerts();
			contractAlertTypeObj.setAlert_type_fk(CommonConstants2.ALERT_TYPE_CONTRACT);
			alert_types.add(contractAlertTypeObj);
			
			Alerts issueAlertObj = new Alerts();
			issueAlertObj.setAlert_type_fk(CommonConstants2.ALERT_TYPE_ISSUE);
			alert_types.add(issueAlertObj);
			
			Alerts riskAlertObj = new Alerts();
			riskAlertObj.setAlert_type_fk(CommonConstants2.ALERT_TYPE_RISK);
			alert_types.add(riskAlertObj);
			
			for (Alerts tObj : alert_types) {
				 if(!StringUtils.isEmpty(tObj.getAlert_type_fk()) && tObj.getAlert_type_fk().equals("Risk")) {
					 sendEmailNotificationWithRiskAlertsToITAmdin(tObj.getAlert_type_fk());
				 }else {
					 sendEmailNotificationWithContractAndIssueAlertToITAmdin(tObj.getAlert_type_fk());
				 }
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	
	public boolean sendEmailNotificationWithContractAndIssueAlertToITAmdin(String alert_type) throws Exception {
		boolean flag = false;
		try {
			if(!StringUtils.isEmpty(alert_type)) {
				Map<String,List<Alerts>> alerts = new LinkedHashMap<String, List<Alerts>>();
				String aLevelQry = "select alert_level,"
						+ "(select first_condition_value from alert_conditions ac where ac.alert_type_fk = Bank Guarantee and ac.alert_level_fk = a.alert_level) as bg_condition_value, " 
						+ "(select first_condition_value from alert_conditions ac where ac.alert_type_fk = Insurance and ac.alert_level_fk = a.alert_level) as insure_condition_value, " 
						+ "(select first_condition_value from alert_conditions ac where ac.alert_type_fk = Contract Period and ac.alert_level_fk = a.alert_level) as cp_condition_value, " 
						+ "(select first_condition_value from alert_conditions ac where ac.alert_type_fk = Contract Value and ac.alert_level_fk = a.alert_level) as cv_condition_value " 
						+ "from alerts a " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id "
						+ "left outer join work w1 on w1.work_id = a.work_id "
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join [user] u on c.hod_user_id_fk = u.user_id "
						+ "where alert_status = ? and count <> 0 "; 
						//+ "where alert_status = ? and a.alert_type_fk = ? "
						//+ "group by alert_level order by alert_level desc";
				int arrSize = 1;
				if(!StringUtils.isEmpty(alert_type)) {
					String atP = "";
					aLevelQry = aLevelQry + " and a.alert_type_fk IN(";
					String[] types = alert_type.split(",");
					for (int p=0; p < types.length; p++) {
						aLevelQry = aLevelQry + (atP.contains("?")?",":"")+"?";
						atP = atP + "?";
						arrSize++;
					}
					aLevelQry = aLevelQry + ")";
				}
				aLevelQry = aLevelQry +  " group by alert_level order by alert_level desc";
				
				
				Object[] pValues = new Object[arrSize];
				
				int i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				if(!StringUtils.isEmpty(alert_type)) {
					String[] types = alert_type.split(",");
					for (int p=0; p < types.length; p++) {
						pValues[i++] = types[p];
					}
				}
				
				//Object[] pValues = new Object[] {CommonConstants.ACTIVE,tObj.getAlert_type_fk()};
				List<Alerts> alert_levels = jdbcTemplate.query( aLevelQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				
				for (Alerts lObj : alert_levels) {	
					String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,count,u.designation as hod,"
							+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,contract_short_name,contractor_name,ISNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join contract c on a.contract_id = c.contract_id " 
							+ "left join work w on c.work_id_fk = w.work_id "
							+ "left outer join work w1 on w1.work_id = a.work_id "
							+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
							+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
							+ "where alert_level = ? and alert_status = ? and count <> 0 ";
							//+ "where alert_level = ? and alert_status = ? and count <> 0 and a.alert_type_fk = ? "
							//+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
					arrSize = 2;
					if(!StringUtils.isEmpty(alert_type)) {
						String atP = "";
						qry = qry + " and a.alert_type_fk IN(";
						String[] types = alert_type.split(",");
						for (int p=0; p < types.length; p++) {
							qry = qry + (atP.contains("?")?",":"")+"?";
							atP = atP + "?";
							arrSize++;
						}
						qry = qry + ")";
					}
					
					qry = qry +  " order by hod,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,a.contract_id asc, alert_level desc";
					
					pValues = new Object[arrSize];
					
					i = 0;
					pValues[i++] = lObj.getAlert_level();
					pValues[i++] = CommonConstants.ACTIVE;
					if(!StringUtils.isEmpty(alert_type)) {
						String[] types = alert_type.split(",");
						for (int p=0; p < types.length; p++) {
							pValues[i++] = types[p];
						}
					}
					
					//pValues = new Object[] {lObj.getAlert_level(),CommonConstants.ACTIVE,tObj.getAlert_type_fk()};
					List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
					
					for (Alerts alert : allAlertsList) {
						if("Bank Guarantee".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("bank_guarantee.png");
						}else if("Insurance".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("insurance.png");
						}else if("Contract Period".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("contract_period.png");
						}else if("Contract Value".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("contract_value.png");
						}else if("Issue".equals(alert.getAlert_type_fk()) ) {
							alert.setAlert_type_image("issue.png");
						}
					}
					
					String alert_level = lObj.getAlert_level();
					if(!alert_type.equals(CommonConstants2.ALERT_TYPE_ISSUE)) {
						if(alert_level.equals("Overdue")) {
							alert_level = alert_level + " (Expenditure>Contract Value OR Validity Expired)";
						}else if(alert_level.equals("3rd Alert")) {
							alert_level = alert_level + " (Expenditure>"+lObj.getCv_condition_value()+"% of Contract Value OR Validity Expiry in "+lObj.getBg_condition_value()+" days)";
						}else if(alert_level.equals("2nd Alert")) {
							alert_level = alert_level + " (Expenditure>"+lObj.getCv_condition_value()+"% of Contract Value OR Validity Expiry in "+lObj.getBg_condition_value()+" days)";
						}else if(alert_level.equals("1st Alert")) {
							alert_level = alert_level + " (Expenditure>"+lObj.getCv_condition_value()+"% of Contract Value OR Validity Expiry in "+lObj.getBg_condition_value()+" days)";
						}
					}					
					alerts.put(alert_level, allAlertsList);
				}
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
	            String today_date = dateFormat.format(new Date()).toUpperCase();
	            
	            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
	            String current_year = yearFormat.format(new Date()).toUpperCase();
	            
	            String emailSubject = "PMIS Contract Alerts";
	            if(!StringUtils.isEmpty(alert_type) && alert_type.equals("Issue")) {
	            	emailSubject = "PMIS Issue Alerts";
	            }
				
				
				if(alerts != null && alerts.size() > 0){					
					String itAdminsEmailsQry = "SELECT STRING_AGG(email_id) FROM [user] where email_id is not null and email_id <>  and user_role_name_fk = IT Admin";
					String itAdminsEmails = jdbcTemplate.queryForObject( itAdminsEmailsQry,String.class);
					if(!StringUtils.isEmpty(itAdminsEmails)) {
						EMailSender emailSender = new EMailSender();
						Mail mail = new Mail();
						mail.setMailTo(itAdminsEmails);
						mail.setMailSubject(emailSubject);
						if(!StringUtils.isEmpty(alert_type) && alert_type.equals("Issue")) {
							mail.setTemplateName("IssueAlerts.vm");
							emailSender.sendEmailWithIssueAlerts(mail,alerts,today_date,current_year,alert_type); 
						}else {
							alert_type = "Contract";
							mail.setTemplateName("ContractAlertsITAdmins.vm");
							emailSender.sendEmailWithContractAlerts(mail,alerts,today_date,current_year,alert_type); 
						}
					}
					flag = true;
				}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}
	
	public boolean sendEmailNotificationWithRiskAlertsToITAmdin(String alert_type) throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			String qry = "select alert_id,alert_level,alert_type_fk,a.created_date,alert_status,alert_value,count,u.designation as hod,"
					+ "ISNULL(a.remarks,'') as remarks,redirect_url " 
					+ "from alerts a "  
					+ "left join risk_work_hod rwh on ((rwh.sub_work = REPLACE(a.redirect_url,/risk-assessment?sub_work=','')) "
					+ "or (rwh.sub_work = substring_index(REPLACE(a.redirect_url,'/risk-atr-update?sub_work=',''), '&assessment_date=',1) )) "
					+ "LEFT JOIN [user] u on rwh.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 and a.alert_type_fk = ? "
					+ "order by hod,alert_level desc";
			
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE,alert_type};
			List<Alerts> riskAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			if(riskAlertsList != null && riskAlertsList.size() > 0) {
				
				for (Alerts alerts : riskAlertsList) {
					String sub_work = "";
					if(!StringUtils.isEmpty(alerts.getRedirect_url())) {
						String[] url_arr = alerts.getRedirect_url().split("=");
						sub_work = url_arr[1];
						
						if(sub_work.contains("&assessment_date")) {
							String[] url_arr2 = sub_work.split("&assessment_date");
							sub_work = url_arr2[0];
						}	
					}
					if(!StringUtils.isEmpty(sub_work)) {
						if(!StringUtils.isEmpty(sub_work)) {
							String workQry = "select rwh.sub_work,rwh.work_id_fk as work_id,hod_user_id_fk,work_name,work_short_name "  
									+ "from risk_work_hod rwh "
									+ "left join work w on rwh.work_id_fk = w.work_id "
									+ "where rwh.sub_work = ?";			
							List<Alerts> wObjList = jdbcTemplate.query( workQry,new Object[]{sub_work},new BeanPropertyRowMapper<Alerts>(Alerts.class));
							if(wObjList != null && wObjList.size() > 0) {
								for (Alerts wObj : wObjList) {
									if(!StringUtils.isEmpty(wObj)) {
										alerts.setSub_work(sub_work);
										alerts.setWork_id(wObj.getWork_id());
										alerts.setWork_name(wObj.getWork_name());
										alerts.setWork_short_name(wObj.getWork_short_name());
									}
								}
							}
						}
					}
				}
				
				SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
	            String today_date = monthFormat.format(new Date()).toUpperCase();
	            
	            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
	            String current_year = yearFormat.format(new Date()).toUpperCase();
				
	            String itAdminsEmailsQry = "SELECT STRING_AGG(email_id) FROM [user] where email_id is not null and email_id <>  and user_role_name_fk = IT Admin";
				String itAdminsEmails = jdbcTemplate.queryForObject( itAdminsEmailsQry,String.class);
				if(!StringUtils.isEmpty(itAdminsEmails)) {
					String emailSubject = "PMIS Risk Alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(itAdminsEmails);
					//mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("RiskAlerts.vm");
					logger.error("sendRiskNotificationAlertMailsToRaviRajiv() >>: Start ");	
					emailSender.sendEmailWithRiskAlerts(mail,riskAlertsList,today_date,current_year); 
					logger.error("sendRiskNotificationAlertMailsToRaviRajiv() >> : End ");
					
				}
				/******************************************************************/
				/*String qryIncharge = "select module_name,incharge_user_id_fk,u.email_id "
						+ "from module m "
						+ "LEFT JOIN [user] u on m.incharge_user_id_fk = u.user_id "
						+ "WHERE module_name = Risk" ;				
				
				List<Alerts> inchargeEmailList = jdbcTemplate.query( qryIncharge, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				for (Alerts alerts : inchargeEmailList) {
					if(!StringUtils.isEmpty(alerts.getEmail_id())) {
						mail = new Mail();
						mail.setMailTo(alerts.getEmail_id());
						mail.setMailSubject(emailSubject);
						mail.setTemplateName("RiskAlerts.vm");
						
						logger.error("sendRiskNotificationAlertMailsToRaviRajiv() >>: Incharge email Start : "+alerts.getEmail_id());	
						emailSender.sendEmailWithRiskAlerts(mail,riskAlertsList,today_date,current_year); 
						logger.error("sendRiskNotificationAlertMailsToRaviRajiv() >> : Incharge email End : "+alerts.getEmail_id());
					}
				}*/
				/******************************************************************/
			}
				
			flag = true;
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return flag;
	}


	@Override
	public List<Alerts> getAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,ISNULL(a.remarks,'') as remarks,count,u.designation as hod,"
					+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,c.work_id_fk,work_id,work_name,c.contract_short_name,redirect_url "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			
			qry = qry + " order by u.designation,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,a.contract_id asc, alert_level desc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
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
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and  count <> 0 "
					+ "and c.contractor_id_fk is not null and c.contractor_id_fk <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			qry = qry + " GROUP BY c.contractor_id_fk ORDER BY c.contractor_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			throw new Exception(e);
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
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and  count <> 0 ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			qry = qry + " GROUP BY a.contract_id ORDER BY a.contract_id";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			throw new Exception(e);
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
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and  count <> 0 "
					+ "and u.designation is not null and u.designation <> '' ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			qry = qry + " GROUP BY u.designation ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
					"   when u.designation='CPM I' then 2 \r\n" + 
					"   when u.designation='CPM II' then 3\r\n" + 
					"   when u.designation='CPM III' then 4 \r\n" + 
					"   when u.designation='CPM V' then 5\r\n" + 
					"   when u.designation='CE' then 6 \r\n" + 
					"   when u.designation='ED S&T' then 7 \r\n" + 
					"   when u.designation='CSTE' then 8\r\n" + 
					"   when u.designation='GM Electrical' then 9\r\n" + 
					"   when u.designation='CEE Project I' then 10\r\n" + 
					"   when u.designation='CEE Project II' then 11\r\n" + 
					"   when u.designation='ED Finance & Planning' then 12\r\n" + 
					"   when u.designation='AGM Civil' then 13\r\n" + 
					"   when u.designation='DyCPM Civil' then 14\r\n" + 
					"   when u.designation='DyCPM III' then 15\r\n" + 
					"   when u.designation='DyCPM V' then 16\r\n" + 
					"   when u.designation='DyCE EE' then 17\r\n" + 
					"   when u.designation='DyCE Badlapur' then 18\r\n" + 
					"   when u.designation='DyCPM Pune' then 19\r\n" + 
					"   when u.designation='DyCE Proj' then 20\r\n" + 
					"   when u.designation='DyCEE I' then 21\r\n" + 
					"   when u.designation='DyCEE Projects' then 22\r\n" + 
					"   when u.designation='DyCEE PSI' then 23\r\n" + 
					"   when u.designation='DyCSTE I' then 24\r\n" + 
					"   when u.designation='DyCSTE IT' then 25\r\n" + 
					"   when u.designation='DyCSTE Projects' then 26\r\n" + 
					"   when u.designation='XEN Consultant' then 27\r\n" + 
					"   when u.designation='AEN Adhoc' then 28\r\n" + 
					"   when u.designation='AEN Project' then 29\r\n" + 
					"   when u.designation='AEN P-Way' then 30\r\n" + 
					"   when u.designation='AEN' then 31\r\n" + 
					"   when u.designation='Sr Manager Signal' then 32 \r\n" + 
					"   when u.designation='Manager Signal' then 33\r\n" + 
					"   when u.designation='Manager Civil' then 34 \r\n" + 
					"   when u.designation='Manager OHE' then 35\r\n" + 
					"   when u.designation='Manager GS' then 36\r\n" + 
					"   when u.designation='Manager Finance' then 37\r\n" + 
					"   when u.designation='Planning Manager' then 38\r\n" + 
					"   when u.designation='Manager Project' then 39\r\n" + 
					"   when u.designation='Manager' then 40 \r\n" + 
					"   when u.designation='SSE' then 41\r\n" + 
					"   when u.designation='SSE Project' then 42\r\n" + 
					"   when u.designation='SSE Works' then 43\r\n" + 
					"   when u.designation='SSE Drg' then 44\r\n" + 
					"   when u.designation='SSE BR' then 45\r\n" + 
					"   when u.designation='SSE P-Way' then 46\r\n" + 
					"   when u.designation='SSE OHE' then 47\r\n" + 
					"   when u.designation='SPE' then 48\r\n" + 
					"   when u.designation='PE' then 49\r\n" + 
					"   when u.designation='JE' then 50\r\n" + 
					"   when u.designation='Demo-HOD-Elec' then 51\r\n" + 
					"   when u.designation='Demo-HOD-Engg' then 52\r\n" + 
					"   when u.designation='Demo-HOD-S&T' then 53\r\n" + 
					"\r\n" + 
					"   end asc" ;

			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Alerts> getWorksFilterListInAlerts(Alerts obj) throws Exception {
		List<Alerts> objsList = null;
		try {
			String qry = "SELECT case when c.work_id_fk is null then w1.work_id else c.work_id_fk end as work_id_fk,case when w.work_id is null then w1.work_id else w.work_id end as work_id,case when w.work_name is null then w1.work_name else w.work_name end as work_name,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name "
					+ "from alerts a "
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and  count <> 0 "
					+ "and (c.work_id_fk is not null or w1.work_id is not null) and (c.work_id_fk <>  or w1.work_id <> ) ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			qry = qry + " GROUP BY c.work_id_fk ORDER BY c.work_id_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			throw new Exception(e);
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
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and  count <> 0 "
					+ "and (c.work_id_fk is not null or w1.work_id is not null) and (c.work_id_fk <>  or w1.work_id <> ) ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			qry = qry + " GROUP BY alert_type_fk ORDER BY alert_type_fk";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public boolean addAlertRemarks(Alerts obj) throws Exception {
		boolean flag = false;
		try {
			String updateQry = "update alerts set remarks = ?,amendment_not_required_in_contract = ? where alert_id = ?";	
			Object[] pValues = new Object[] {obj.getRemarks(),obj.getAmendment_not_required_in_contract(),obj.getAlert_id()};
			int c = jdbcTemplate.update(updateQry,pValues);	
			if(c > 0) {
				flag = true;
				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Others");
				formHistory.setForm_name("Alerts");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Updated alert remarks: "+obj.getRemarks());
				formHistory.setWork_id_fk(obj.getWork_id());
				formHistory.setContract_id_fk(obj.getContract_id());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
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
					+ "from alerts a ";  
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id ";
					}
			
			qry = qry + "left join contract c on a.contract_id = c.contract_id " 
					+ "left join work w on c.work_id_fk = w.work_id "
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 ";
					
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
				
				
				qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,count,u.designation as hod,"
						+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,ISNULL(a.remarks,'') as remarks,redirect_url ";
				
						if(!"IT Admin".equals(obj.getUser_role_name())) {
							qry = qry + ",au.alerts_user_id,au.read_time "; 
						}
				
						qry = qry + "from alerts a " ; 
						
						if(!"IT Admin".equals(obj.getUser_role_name())) {
							qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
						}
				
				qry = qry + "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id " 
						+ "left outer join work w1 on w1.work_id = a.work_id "
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_status = ? and count <> 0 ";
				
				arrSize = 1;
				if(!"IT Admin".equals(obj.getUser_role_name())) {
					qry = qry + " and au.user_id_fk = ? ";
					arrSize++;
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
					arrSize++;
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
				
				qry = qry + " order by u.designation,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,a.contract_id asc, alert_level desc";
				
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				if(!"IT Admin".equals(obj.getUser_role_name())) {
					pValues[i++] = obj.getUser_id();
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
					pValues[i++] = obj.getWork_id_fk();
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

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objs;
	}


	@Override
	public int getAlertsCount(Alerts obj) throws Exception {
		int count = 0;;
		try {
			String qry = "select count(*) "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry +"left join contract c on a.contract_id = c.contract_id " 
					+ "left join work w on c.work_id_fk = w.work_id " 
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and count <> 0 ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return count;
	}


	@Override
	public boolean callingStoredProcedures() throws Exception {
		Connection connection = null;
		CallableStatement stmt = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			
			logger.error("callingStoredProcedures Start 1_scurve_planned :"+ new Date());	
			String qry1 = "call 1_scurve_planned()";			
			stmt = connection.prepareCall(qry1);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 1_scurve_planned :"+ new Date());
			
			logger.error("callingStoredProcedures Start 2_scurve_expected :"+ new Date());	
			String qry2 = "call 2_scurve_expected()";			
			stmt = connection.prepareCall(qry2);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 2_scurve_expected :"+ new Date());
			
			logger.error("callingStoredProcedures Start 3_scurve_obc :"+ new Date());	
			String qry3 = "call 3_scurve_obc()";			
			stmt = connection.prepareCall(qry3);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 3_scurve_obc :"+ new Date());
			
			logger.error("callingStoredProcedures Start create_calendar_dates :"+ new Date());	
			String qry5 = "call create_calendar_dates()";			
			stmt = connection.prepareCall(qry5);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends create_calendar_dates :"+ new Date());
			
			logger.error("callingStoredProcedures Start create_user_calendar_dates :"+ new Date());
			String qry6 = "call create_user_calendar_dates()";			
			stmt = connection.prepareCall(qry6);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends create_user_calendar_dates :"+ new Date());
			
			logger.error("callingStoredProcedures Start 4_scurve :"+ new Date());	
			String qry7 = "call 4_scurve()";			
			stmt = connection.prepareCall(qry7);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 4_scurve :"+ new Date());
			
			logger.error("callingStoredProcedures Start 5_scurve_wo_ms :"+ new Date());	
			String qry8 = "call 5_scurve_wo_ms()";			
			stmt = connection.prepareCall(qry8);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 5_scurve_wo_ms :"+ new Date());				
			
			flag = true;
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}

	@Override
	public int getTotalRecords(Alerts obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry = "select count(*) as total_records from alerts a "; 
					if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id "
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (u.designation like ? or c.work_id_fk like ? or w1.work_id like ? or (case when w.work_short_name is null then w1.work_short_name else w.work_short_name end) like ? or a.contract_id like ?"
						+ " or c.contract_short_name like ? or contractor_name like ? or alert_type_fk like ? or alert_level like ? or alert_value like ? or a.remarks like ?)";
				arrSize++;
				arrSize++;
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
			
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			
			qry = qry + " order by u.designation,(case when w.work_short_name is null then w1.work_short_name else w.work_short_name end),a.contract_id asc, alert_level desc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
	public List<Alerts> getAlertsList(Alerts obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,ISNULL(a.remarks,'') as remarks,count,u.designation as hod,"
					+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,case when c.work_id_fk is null then w1.work_id else c.work_id_fk end as work_id_fk,case when w.work_id is null then w1.work_id else w.work_id end as work_id ,case when w.work_name is null then w1.work_name else w.work_name end as work_name,c.contract_short_name,redirect_url,amendment_not_required_in_contract  "
					+ "from alerts a "; 
					if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join work w1 on w1.work_id = a.work_id  "
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					//+ "where  count <> 0 and alert_status = ? ";
					+ "where count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and (c.work_id_fk = ? or w1.work_id= ? )";
				arrSize++;
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
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (u.designation like ? or c.work_id_fk like ? or w.work_short_name like ? or a.contract_id like ?"
						+ " or c.contract_short_name like ? or contractor_name like ? or alert_type_fk like ? or alert_level like ? or alert_value like ? or a.remarks like ?)";
				arrSize++;
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
			
			qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				//qry = qry + " order by u.designation,work_short_name,a.contract_id asc, alert_level desc limit ?,?";
				qry = qry + " order by alert_level desc offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
				pValues[i++] = "%"+searchParameter+"%";
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}


	@Override
	public List<Alerts> getAlertTypes(Alerts obj) throws Exception {
		List<Alerts> alertTypes = new ArrayList<Alerts>();
		try {
					
			String qry = "select alert_type_fk " 
					+ "from alerts a ";  
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left join contract c on a.contract_id = c.contract_id " 
					+ "left join work w on c.work_id_fk = w.work_id " 
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 ";
					
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " (and c.work_id_fk = ? or w1.work_id = ? )";
				arrSize++;
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
			
			//qry = qry + " and a.alert_type_fk not in (Issue,Risk)";
			
			qry = qry + " group by alert_type_fk order by alert_type_fk ASC";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			alertTypes = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return alertTypes;
	}

	@Override
	public List<Alerts> getAlertTypesForGenerateSendAlerts() throws Exception {
		List<Alerts> alertTypes = new ArrayList<Alerts>();
		try {	
			String qry = "select alert_type as alert_type_fk from alert_type"; 
			alertTypes = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return alertTypes;
	}

	@Override
	public List<Alerts> getAlertLevelsForGenerateSendAlerts() throws Exception {
		List<Alerts> alertLevels = new ArrayList<Alerts>();
		try {	
			String qry = "select alert_level from alert_level"; 
			alertLevels = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return alertLevels;
	}

	@Override
	public List<Alerts> getSendToListForGenerateSendAlerts() throws Exception {
		List<Alerts> sendToList = new ArrayList<Alerts>();
		try {	
			String qry = "select user_id,user_name,designation,email_id,user_role_name_fk,user_type_fk "
					+ "FROM [user] where email_id is not null and email_id <> "; 
			sendToList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return sendToList;
	}

	@Override
	public boolean sendAlertsToParticulars(Alerts obj) throws Exception {
		boolean flag = false;
		try {
			String[] alert_types = null;
			if(!StringUtils.isEmpty(obj.getAlert_type_fk())) {
				alert_types = obj.getAlert_type_fk().split(",");
			}
			
			String[] alert_levels = null;
			if(!StringUtils.isEmpty(obj.getAlert_level())) {
				alert_levels = obj.getAlert_level().split(",");
			}
			
			EMailSender emailSender = new EMailSender();
			Map<String,List<Alerts>> alerts = new LinkedHashMap<String, List<Alerts>>();
			String aLevelQry = "select alert_level " 
					+ "from alerts a "
					+ "where alert_status = ? and count <> 0 ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(alert_types)) {
				aLevelQry = aLevelQry + " and a.alert_type_fk IN(";
				String pQ = "";
				for (int i = 0; i < alert_types.length; i++) {
					pQ = pQ + "?,";
					arrSize++;
				}
				if(!StringUtils.isEmpty(pQ)) {
					aLevelQry =  aLevelQry + org.apache.commons.lang3.StringUtils.chop(pQ);  
				}
				aLevelQry = aLevelQry + ")";
			}
			
			if(!StringUtils.isEmpty(alert_levels)) {
				aLevelQry = aLevelQry + " and a.alert_level IN( ";
				String pQ = "";
				for (int i = 0; i < alert_levels.length; i++) {
					pQ = pQ + "?,";
					arrSize++;
				}
				if(!StringUtils.isEmpty(pQ)) {
					aLevelQry =  aLevelQry + org.apache.commons.lang3.StringUtils.chop(pQ);  
				}
				aLevelQry = aLevelQry + ")";
			}
			
			aLevelQry = aLevelQry+  " group by alert_level order by alert_level desc";
			
			int p = 0;
			Object[] pValues = new Object[arrSize];
			pValues[p++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(alert_types)) {
				for (int i = 0; i < alert_types.length; i++) {
					pValues[p++] = alert_types[i];
				}
			}
			
			if(!StringUtils.isEmpty(alert_levels)) {
				for (int i = 0; i < alert_levels.length; i++) {
					pValues[p++] = alert_levels[i];
				}
			}
			
			List<Alerts> alert_levelsList = jdbcTemplate.query( aLevelQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			for (Alerts lObj : alert_levelsList) {
				
				String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,count,u.designation as hod,"
						+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,ISNULL(a.remarks,'') as remarks,redirect_url " 
						+ "from alerts a " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id "
						+ "left outer join work w1 on w1.work_id = a.work_id "
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join [user] u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_level = ? and alert_status = ? and count <> 0  ";
				
				arrSize = 2;
				if(!StringUtils.isEmpty(alert_types)) {
					qry = qry + " and a.alert_type_fk IN(";
					String pQ = "";
					for (int i = 0; i < alert_types.length; i++) {
						pQ = pQ + "?,";
						arrSize++;
					}
					if(!StringUtils.isEmpty(pQ)) {
						qry =  qry + org.apache.commons.lang3.StringUtils.chop(pQ);  
					}
					qry = qry + ")";
				}
				
				qry = qry + " order by hod,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,a.contract_id asc, alert_level desc";
				
				p = 0;
				pValues = new Object[arrSize];
				pValues[p++] = lObj.getAlert_level();
				pValues[p++] = CommonConstants.ACTIVE;
				if(!StringUtils.isEmpty(alert_types)) {
					for (int i = 0; i < alert_types.length; i++) {
						pValues[p++] = alert_types[i];
					}
				}
				List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				for (Alerts alert : allAlertsList) {
						String sub_work = "";
						if(!StringUtils.isEmpty(alert.getRedirect_url()) && alert.getRedirect_url().contains("sub_work")) {
							String[] url_arr = alert.getRedirect_url().split("=");
							sub_work = url_arr[1];
							
							if(sub_work.contains("&assessment_date")) {
								String[] url_arr2 = sub_work.split("&assessment_date");
								sub_work = url_arr2[0];
							}								
						}
						if(!StringUtils.isEmpty(sub_work)) {
							String workQry = "select rwh.sub_work,rwh.work_id_fk as work_id,hod_user_id_fk,work_name,work_short_name,u.designation as hod "  
									+ "from risk_work_hod rwh "
									+ "left join work w on rwh.work_id_fk = w.work_id "
									+ "left join [user] u on rwh.hod_user_id_fk = u.user_id " 
									+ "where rwh.sub_work = ?";			
							List<Alerts> wObjList = jdbcTemplate.query( workQry,new Object[]{sub_work},new BeanPropertyRowMapper<Alerts>(Alerts.class));
							if(wObjList != null && wObjList.size() > 0) {
								for (Alerts wObj : wObjList) {
									if(!StringUtils.isEmpty(wObj)) {
										alert.setContract_short_name(sub_work);
										alert.setWork_id(wObj.getWork_id());
										alert.setWork_name(wObj.getWork_name());
										alert.setWork_short_name(wObj.getWork_short_name());
										alert.setHod(wObj.getHod());
									}
								}
							}
						}
					if("Bank Guarantee".equals(alert.getAlert_type_fk()) ) {
						alert.setAlert_type_image("bank_guarantee.png");
					}else if("Insurance".equals(alert.getAlert_type_fk()) ) {
						alert.setAlert_type_image("insurance.png");
					}else if("Contract Period".equals(alert.getAlert_type_fk()) ) {
						alert.setAlert_type_image("contract_period.png");
					}else if("Contract Value".equals(alert.getAlert_type_fk()) ) {
						alert.setAlert_type_image("contract_value.png");
					}else if("Issue".equals(alert.getAlert_type_fk()) ) {
						alert.setAlert_type_image("issue.png");
					}else if("Risk".equals(alert.getAlert_type_fk()) ) {
						alert.setAlert_type_image("risk.png");
					}
				}
				
				alerts.put(lObj.getAlert_level(), allAlertsList); 
			}
			
			List<String> email_ids = new ArrayList<String>();
			if(!StringUtils.isEmpty(obj.getEmail_id())) {
				email_ids = Arrays.asList(obj.getEmail_id().split(","));
			}				
			for (String email_id : email_ids) {
				if(alerts != null && alerts.size() > 0 && !StringUtils.isEmpty(email_id)) {
					
					SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
		            String today_date = monthFormat.format(new Date()).toUpperCase();
		            
		            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
		            String current_year = yearFormat.format(new Date()).toUpperCase();
		            
		            String alert_type = "";
		            
		            if(!StringUtils.isEmpty(obj.getAlert_type_fk()) && obj.getAlert_type_fk().length() > 0) {
						alert_type = String.join(",", obj.getAlert_type_fk());
					}else {
						alert_type = "Contract";
					}
		            
					String emailSubject = "PMIS Alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(email_id);
					mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("sendAlertsToITAdmins.vm");
					
					logger.error("sendAlertsToParticulars() >> Sending mail to "+email_id+": Start ");	
					emailSender.sendEmailWithContractAlerts(mail,alerts,today_date,current_year,alert_type); 
					logger.error("sendAlertsToParticulars() >> Sending mail to "+email_id+": End ");
					flag = true;
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public Map<String,List<Alerts>> getContractAlerts(Alerts obj) throws Exception {
		Map<String,List<Alerts>> objsList = new LinkedHashMap<String,List<Alerts>>();
		try {
			String hodQry = "select u.designation as hod "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						hodQry = hodQry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
					hodQry = hodQry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and a.alert_type_fk <> Issue and a.alert_type_fk <> Risk and u.designation is not null and alert_status = ? ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				hodQry = hodQry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				hodQry = hodQry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				hodQry = hodQry + " and a.contract_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContractor_id_fk())) {
				hodQry = hodQry + " and c.contractor_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_type_fk())) {
				hodQry = hodQry + " and a.alert_type_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				hodQry = hodQry + " and u.designation = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
				hodQry = hodQry + " and a.alert_level = ?";
				arrSize++;
			}
			
			hodQry = hodQry + " group by u.designation order by u.designation asc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
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
			
			List<Alerts> hodObjsList = jdbcTemplate.query( hodQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			for (Alerts hodObj : hodObjsList) {			
				String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,a.created_date,alert_status,alert_value,ISNULL(a.remarks,'') as remarks,count,u.designation as hod,"
						+ "case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,c.work_id_fk,w.work_id,w.work_name,c.contract_short_name,redirect_url,"
						+ "a.details,FORMAT(a.valid_upto,'dd-MM-yyyy') as validity "
						+ "from alerts a "; 
						if(!"IT Admin".equals(obj.getUser_role_name())) {
							qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
						}
				
				qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
						+ "left outer join work w on c.work_id_fk = w.work_id " 
						+ "left outer join work w1 on w1.work_id = a.work_id "
						+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
						+ "where  count <> 0 and a.alert_type_fk <> Issue and a.alert_type_fk <> Risk and alert_status = ? ";
				
				arrSize = 1;
				if(!"IT Admin".equals(obj.getUser_role_name())) {
					qry = qry + " and au.user_id_fk = ? ";
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
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
					qry = qry + " and a.alert_level = ?";
					arrSize++;
				}
				
				qry = qry + " order by u.designation,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end,a.contract_id asc, alert_level desc";
				
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				if(!"IT Admin".equals(obj.getUser_role_name())) {
					pValues[i++] = obj.getUser_id();
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
				if(!StringUtils.isEmpty(hodObj) && !StringUtils.isEmpty(hodObj.getHod())) {
					pValues[i++] = hodObj.getHod();
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
					pValues[i++] = obj.getAlert_level();
				}
				
				List<Alerts> alertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				objsList.put(hodObj.getHod(), alertsList);
			}

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Alerts> getHodListInAlertsReport(Alerts obj) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select u.designation as hod "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
					qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and a.alert_type_fk <> Issue and a.alert_type_fk <> Risk and alert_status = ? ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
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
			
			//qry = qry + " group by u.designation order by u.designation asc";
			qry = qry + " GROUP BY u.designation ORDER BY case when u.designation='ED Civil' then 1 \r\n" + 
					"   when u.designation='CPM I' then 2 \r\n" + 
					"   when u.designation='CPM II' then 3\r\n" + 
					"   when u.designation='CPM III' then 4 \r\n" + 
					"   when u.designation='CPM V' then 5\r\n" + 
					"   when u.designation='CE' then 6 \r\n" + 
					"   when u.designation='ED S&T' then 7 \r\n" + 
					"   when u.designation='CSTE' then 8\r\n" + 
					"   when u.designation='GM Electrical' then 9\r\n" + 
					"   when u.designation='CEE Project I' then 10\r\n" + 
					"   when u.designation='CEE Project II' then 11\r\n" + 
					"   when u.designation='ED Finance & Planning' then 12\r\n" + 
					"   when u.designation='AGM Civil' then 13\r\n" + 
					"   when u.designation='DyCPM Civil' then 14\r\n" + 
					"   when u.designation='DyCPM III' then 15\r\n" + 
					"   when u.designation='DyCPM V' then 16\r\n" + 
					"   when u.designation='DyCE EE' then 17\r\n" + 
					"   when u.designation='DyCE Badlapur' then 18\r\n" + 
					"   when u.designation='DyCPM Pune' then 19\r\n" + 
					"   when u.designation='DyCE Proj' then 20\r\n" + 
					"   when u.designation='DyCEE I' then 21\r\n" + 
					"   when u.designation='DyCEE Projects' then 22\r\n" + 
					"   when u.designation='DyCEE PSI' then 23\r\n" + 
					"   when u.designation='DyCSTE I' then 24\r\n" + 
					"   when u.designation='DyCSTE IT' then 25\r\n" + 
					"   when u.designation='DyCSTE Projects' then 26\r\n" + 
					"   when u.designation='XEN Consultant' then 27\r\n" + 
					"   when u.designation='AEN Adhoc' then 28\r\n" + 
					"   when u.designation='AEN Project' then 29\r\n" + 
					"   when u.designation='AEN P-Way' then 30\r\n" + 
					"   when u.designation='AEN' then 31\r\n" + 
					"   when u.designation='Sr Manager Signal' then 32 \r\n" + 
					"   when u.designation='Manager Signal' then 33\r\n" + 
					"   when u.designation='Manager Civil' then 34 \r\n" + 
					"   when u.designation='Manager OHE' then 35\r\n" + 
					"   when u.designation='Manager GS' then 36\r\n" + 
					"   when u.designation='Manager Finance' then 37\r\n" + 
					"   when u.designation='Planning Manager' then 38\r\n" + 
					"   when u.designation='Manager Project' then 39\r\n" + 
					"   when u.designation='Manager' then 40 \r\n" + 
					"   when u.designation='SSE' then 41\r\n" + 
					"   when u.designation='SSE Project' then 42\r\n" + 
					"   when u.designation='SSE Works' then 43\r\n" + 
					"   when u.designation='SSE Drg' then 44\r\n" + 
					"   when u.designation='SSE BR' then 45\r\n" + 
					"   when u.designation='SSE P-Way' then 46\r\n" + 
					"   when u.designation='SSE OHE' then 47\r\n" + 
					"   when u.designation='SPE' then 48\r\n" + 
					"   when u.designation='PE' then 49\r\n" + 
					"   when u.designation='JE' then 50\r\n" + 
					"   when u.designation='Demo-HOD-Elec' then 51\r\n" + 
					"   when u.designation='Demo-HOD-Engg' then 52\r\n" + 
					"   when u.designation='Demo-HOD-S&T' then 53\r\n" + 
					"\r\n" + 
					"   end asc" ;

			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Alerts> getWorksListInAlertsReport(Alerts obj) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select c.work_id_fk as work_id,case when w.work_short_name is null then w1.work_short_name else w.work_short_name end as work_short_name "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
					qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join work w1 on w1.work_id = a.work_id "
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and a.alert_type_fk <> Issue and a.alert_type_fk <> Risk and alert_status = ? ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
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
			
			qry = qry + " group by c.work_id_fk order by c.work_id_fk asc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Alerts> getAlertLevelsListInAlertsReport(Alerts obj) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select a.alert_level "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
					qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and a.alert_type_fk <> Issue and a.alert_type_fk <> Risk and alert_status = ? ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
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
			
			qry = qry + " group by a.alert_level order by a.alert_level asc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Alerts> getAlertTypesListInAlertsReport(Alerts obj) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select a.alert_type_fk "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
					qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join [user] u on c.hod_user_id_fk = u.user_id "
					+ "where  count <> 0 and a.alert_type_fk <> Issue and a.alert_type_fk <> Risk and alert_status = ? ";
			
			int arrSize = 1;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				qry = qry + " and au.user_id_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
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
			
			qry = qry + " group by a.alert_type_fk order by a.alert_type_fk asc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!"IT Admin".equals(obj.getUser_role_name())) {
				pValues[i++] = obj.getUser_id();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean readIssueAlert(String alerts_user_id) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
			String msgUpdateqry = "UPDATE alerts_user SET read_time = CURRENT_TIMESTAMP where alerts_user_id = :alerts_user_id";
			
			Alerts obj = new Alerts();
			obj.setAlerts_user_id(alerts_user_id);
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
			int count = template.update(msgUpdateqry, paramSource);
			if (count > 0) {
				flag = true;
			}
			transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean runPlannedNormalDistributionDay() throws Exception {
		Connection connection = null;
		CallableStatement stmt = null;
		ResultSet resultSet = null;
		boolean flag = false;
		try {
			connection = dataSource.getConnection();
			
			logger.error("callingStoredProcedures Start 1_scurve_planned :"+ new Date());	
			String qry1 = "call 1_scurve_planned()";			
			stmt = connection.prepareCall(qry1);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 1_scurve_planned :"+ new Date());
			
			logger.error("callingStoredProcedures Start 2_scurve_expected :"+ new Date());	
			String qry2 = "call 2_scurve_expected()";			
			stmt = connection.prepareCall(qry2);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 2_scurve_expected :"+ new Date());
			
			logger.error("callingStoredProcedures Start 3_scurve_obc :"+ new Date());	
			String qry3 = "call 3_scurve_obc()";			
			stmt = connection.prepareCall(qry3);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 3_scurve_obc :"+ new Date());
			
			logger.error("callingStoredProcedures Start 4_scurve :"+ new Date());	
			String qry4 = "call 4_scurve()";			
			stmt = connection.prepareCall(qry4);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 4_scurve :"+ new Date());
			
			logger.error("callingStoredProcedures Start 5_scurve_wo_ms :"+ new Date());	
			String qry5 = "call 5_scurve_wo_ms()";			
			stmt = connection.prepareCall(qry5);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			logger.error("callingStoredProcedures Ends 5_scurve_wo_ms :"+ new Date());			
			
			flag = true;
		}catch(Exception e){ 
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}
	
}
