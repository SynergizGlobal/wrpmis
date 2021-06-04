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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.AlertsDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
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
		/*TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);*/
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			/***************************** BG alerts*******************************************************/
			String bgQryAlert1 = "select bg.contract_id_fk as contract_id, '1st Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ " from contract c " + 
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk "
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 60 and DATEDIFF(valid_upto ,NOW()) > 30) and release_date is null";
			
			List<Alerts> bgQryAlert1List = jdbcTemplate.query( bgQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert1List) && bgQryAlert1List.size() > 0) {
				list.addAll(bgQryAlert1List);
			}			
			
			String bgQryAlert2 = "select bg.contract_id_fk as contract_id, '2nd Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ " from contract c " +
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk "
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 7) and release_date is null";
			
			List<Alerts> bgQryAlert2List = jdbcTemplate.query( bgQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert2List) && bgQryAlert2List.size() > 0) {
				list.addAll(bgQryAlert2List);
			}	
			
			String bgQryAlert3 = "select bg.contract_id_fk as contract_id, '3rd Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ " from contract c " + 
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk " 
					+ "where contract_status_fk = 'In Progress' and DATEDIFF(valid_upto ,NOW()) <= 7 and release_date is null";
			
			List<Alerts> bgQryAlert3List = jdbcTemplate.query( bgQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert3List) && bgQryAlert3List.size() > 0) {
				list.addAll(bgQryAlert3List);
			}
			
			/***************************** Insurance alerts*******************************************************/
			String insuranceQryAlert1 = "select bg.contract_id_fk as contract_id, '1st Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "
					+ "from contract c " + 
					"left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 60 and DATEDIFF(valid_upto ,NOW()) > 30) and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert1List = jdbcTemplate.query( insuranceQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert1List) && insuranceQryAlert1List.size() > 0) {
				list.addAll(insuranceQryAlert1List);
			}
			
			String insuranceQryAlert2 = "select bg.contract_id_fk as contract_id, '2nd Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "
					+ " from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+ "where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 7) and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert2List = jdbcTemplate.query( insuranceQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert2List) && insuranceQryAlert2List.size() > 0) {
				list.addAll(insuranceQryAlert2List);
			}
			
			String insuranceQryAlert3 = "select bg.contract_id_fk as contract_id, '3rd Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "
					+ " from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk "
					+ "where contract_status_fk = 'In Progress' and DATEDIFF(valid_upto ,NOW()) <= 7 and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert3List = jdbcTemplate.query( insuranceQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert3List) && insuranceQryAlert3List.size() > 0) {
				list.addAll(insuranceQryAlert3List);
			}
			
			
			/***************************** Contract Period alerts*******************************************************/
			
			String cpQryAlert1 = "select contract_id,'1st Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 60 " 
					+ "and DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 30)";
			
			List<Alerts> cpQryAlert1List = jdbcTemplate.query( cpQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert1List) && cpQryAlert1List.size() > 0) {
				list.addAll(cpQryAlert1List);
			}
			String cpQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null "
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 30 " 
					+ "and DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 7)";
			
			List<Alerts> cpQryAlert2List = jdbcTemplate.query( cpQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert2List) && cpQryAlert2List.size() > 0) {
				list.addAll(cpQryAlert2List);
			}
			
			
			String cpQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null "
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 7) ";
			
			List<Alerts> cpQryAlert3List = jdbcTemplate.query( cpQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert3List) && cpQryAlert3List.size() > 0) {
				list.addAll(cpQryAlert3List);
			}
			
			/***************************** Contract Value alerts*******************************************************/
			
			String cvQryAlert1 = "select c.contract_id,'1st Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',"  
					+ "(select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN ', Revised Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END), "  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null "
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (((select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) is not null "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 95 "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) < 120))";
			
			List<Alerts> cvQryAlert1List = jdbcTemplate.query( cvQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert1List) && cvQryAlert1List.size() > 0) {
				list.addAll(cvQryAlert1List);
			}
			
			
			String cvQryAlert2 = "select c.contract_id,'2nd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',"  
					+ "(select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN ', Revised Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END), "  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (((select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) is not null "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 120 "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) < 145))";
			
			
			List<Alerts> cvQryAlert2List = jdbcTemplate.query( cvQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert2List) && cvQryAlert2List.size() > 0) {
				list.addAll(cvQryAlert2List);
			}
			
			
			String cvQryAlert3 = "select c.contract_id,'3rd Alert' as alert_level,'Contract Value' as alert_type,CONCAT('Cumulative expenditure : ',"  
					+ "(select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id),"  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN ', Revised Cost : ' WHEN awarded_cost is not null THEN ', Awarded Cost : ' ELSE '' END), "  
					+ "(CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)"  
					+ ") AS alert_value," 
					+ "concat('/get-alerts/') as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null "
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (((select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id) is not null "  
					+ "and (( (select CAST(SUM(x.gross_work_done) as CHAR) from expenditure x where x.contract_id_fk = c.contract_id)* 100) / (CASE WHEN (cr.action = 'Yes' and cr.revised_amount is not null) THEN cr.revised_amount WHEN awarded_cost is not null THEN awarded_cost ELSE 0 END)) >= 145 ))";
			
			
			List<Alerts> cvQryAlert3List = jdbcTemplate.query( cvQryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cvQryAlert3List) && cvQryAlert3List.size() > 0) {
				list.addAll(cvQryAlert3List);
			}
			
			
			/*************************Alerts insertion********************************************/
			String updateQry = "update alerts set alert_status = ? where count <> 0";	
			Object[] pValues = new Object[] {CommonConstants.INACTIVE};
			jdbcTemplate.update(updateQry,pValues);	
			
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,remarks,redirect_url) VALUES  (?,?,?,?,?,?,?,?)";
			
			String qryAlertsSendManually = "select user_id_fk,alert_type_fk,alert_level_fk from alerts_send_manually ";			
			List<Alerts> alertsSendManually = jdbcTemplate.query( qryAlertsSendManually, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			
			for (Alerts obj : list) {
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, getAlertRemarks(alert_type,contract_id,alert_value,connection));
                stmt.setString(p++, redirect_url);
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
    				
    				if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getHod_user_id_fk());
		                stmt.addBatch();
					}
    				
    				if(!StringUtils.isEmpty(obj.getDy_hod_user_id_fk())) {
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getDy_hod_user_id_fk());
		                stmt.addBatch();
    				}
    				
    				for (Alerts aObj : alertsSendManually) {
    					if(StringUtils.isEmpty(aObj.getAlert_level_fk()) && (!StringUtils.isEmpty(aObj.getAlert_type_fk()) && aObj.getAlert_type_fk().equals(alert_type))) {
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.addBatch();
    					}
    					if(StringUtils.isEmpty(aObj.getAlert_type_fk()) && (!StringUtils.isEmpty(aObj.getAlert_level_fk()) && aObj.getAlert_level_fk().equals(alert_level))) {
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.addBatch();
    					}
    					if((!StringUtils.isEmpty(aObj.getAlert_type_fk()) && aObj.getAlert_type_fk().equals(alert_type)) && (!StringUtils.isEmpty(aObj.getAlert_level_fk()) && aObj.getAlert_level_fk().equals(alert_level))) {
    						p = 1;
    	    				stmt.setString(p++, alert_id);
    		                stmt.setString(p++, aObj.getUser_id_fk());
    		                stmt.addBatch();
    					}
					}   				
    				
	                stmt.executeBatch();
                }
			}
			
			generateIssueAlertsByCronJob();
			
			
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
            cal.setTime(date); // don't forget this if date is arbitrary
            
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
            String month = monthFormat.format(date).toUpperCase();
            //int month = cal.get(Calendar.MONTH); // 0 being January
            int year = cal.get(Calendar.YEAR);
            
            int day = cal.get(Calendar.DAY_OF_MONTH);  
            if(day != 2 && day != 4 ) {
            	generateRiskMainAlertsByCronJob();
            }
			
            generateMitigationAndATRRiskAlertsByCronJob();
			
			flag = true;
		}catch(Exception e){ 
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
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
			
			String qryAlert1 = "select contract_id_fk as contract_id, '1st Alert' as alert_level,'Issue' as alert_type,"
					+ "concat(i.title,'<br>Pending Since : ',DATEDIFF(NOW(), date),' days.') as alert_value,"
					+ "(CASE WHEN status_fk = 'Closed' THEN concat('/InfoViz/issues/closed-issues/',issue_id) ELSE concat('/InfoViz/issues/open-issues/',issue_id) END) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,i.corrective_measure,i.remarks "
					+ "from issue i "
					+ "LEFT JOIN contract c ON i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk  = d.department "
					+ "where status_fk <> 'Closed' "
					+ "and DATEDIFF(NOW(),date) >= 30 and DATEDIFF(NOW(),date) < 60";
			
		
			List<Alerts> alert1List = jdbcTemplate.query( qryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(alert1List) && alert1List.size() > 0) {
				list.addAll(alert1List);
			}			
			
			String qryAlert2 = "select contract_id_fk as contract_id, '2nd Alert' as alert_level,'Issue' as alert_type,"
					+ "concat(i.title,'<br>Pending Since : ',DATEDIFF(NOW(), date),' days.') as alert_value,"
					+ "(CASE WHEN status_fk = 'Closed' THEN concat('/InfoViz/issues/closed-issues/',issue_id) ELSE concat('/InfoViz/issues/open-issues/',issue_id) END) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,i.corrective_measure,i.remarks "
					+ "from issue i "
					+ "LEFT JOIN contract c ON i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk  = d.department "
					+ "where status_fk <> 'Closed' "
					+ "and DATEDIFF(NOW(),date) >= 60 and DATEDIFF(NOW(),date) < 90";
			
			List<Alerts> alert2List = jdbcTemplate.query( qryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(alert2List) && alert2List.size() > 0) {
				list.addAll(alert2List);
			}	
			
			String qryAlert3 = "select contract_id_fk as contract_id, '3rd Alert' as alert_level,'Issue' as alert_type,"
					+ "concat(i.title,'<br>Pending Since : ',DATEDIFF(NOW(), date),' days.') as alert_value,"
					+ "(CASE WHEN status_fk = 'Closed' THEN concat('/InfoViz/issues/closed-issues/',issue_id) ELSE concat('/InfoViz/issues/open-issues/',issue_id) END) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk,i.corrective_measure,i.remarks "
					+ "from issue i "
					+ "LEFT JOIN contract c ON i.contract_id_fk = c.contract_id "
					+ "LEFT JOIN department d ON c.department_fk  = d.department "
					+ "where status_fk <> 'Closed' "
					+ "and DATEDIFF(NOW(),date) >= 90";
			
			List<Alerts> alert3List = jdbcTemplate.query( qryAlert3, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(alert3List) && alert3List.size() > 0) {
				list.addAll(alert3List);
			}
			
			
			/*************************Alerts insertion********************************************/
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,remarks,redirect_url) VALUES  (?,?,?,?,?,?,?,?)";		
			
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
                	
                	String qry = "INSERT INTO alerts_user(alerts_id_fk,user_id_fk)VALUES(?,?)";
    				stmt = connection.prepareStatement(qry);
    				
    				if(!StringUtils.isEmpty(obj.getAlert_level()) && obj.getAlert_level().equals("2nd Alert")) {
    					if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {
			                p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, obj.getHod_user_id_fk());
			                stmt.addBatch();
    					}
    				}
    				
    				if(!StringUtils.isEmpty(obj.getAlert_level()) && obj.getAlert_level().equals("3rd Alert")) {
    					p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, "PMIS_SU_001");
		                stmt.addBatch();
			                
		                if(!StringUtils.isEmpty(obj.getDepartment_name()) && obj.getDepartment_name().equals("Engineering")) {
		                	p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, "PMIS_SU_002");
			                stmt.addBatch();
		                }
		                if(!StringUtils.isEmpty(obj.getDepartment_name()) && (obj.getDepartment_name().equals("Signalling & Telecom") 
		                		|| obj.getDepartment_name().equals("Electrical"))) {
		                	p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, "PMIS_SU_003");
			                stmt.addBatch();
		                }
		                
		                if(!StringUtils.isEmpty(obj.getHod_user_id_fk())) {
			                p = 1;
		    				stmt.setString(p++, alert_id);
			                stmt.setString(p++, obj.getHod_user_id_fk());
			                stmt.addBatch();
    					}
		                
    				}
    				
    				if(!StringUtils.isEmpty(obj.getResponsible_person())) {
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getResponsible_person());
		                stmt.addBatch();
					}
    				
    				if(!StringUtils.isEmpty(obj.getDy_hod_user_id_fk())) {
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getDy_hod_user_id_fk());
		                stmt.addBatch();
    				}
    				
    				if(!StringUtils.isEmpty(obj.getEscalated_to())) {
		                p = 1;
	    				stmt.setString(p++, alert_id);
		                stmt.setString(p++, obj.getEscalated_to());
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
					+ "left join risk_upload ru on rwh.sub_work = ru.sub_work " 
					+ "left join user u on rwh.hod_user_id_fk = u.user_id " 
					+ "where (select count(*) from risk_upload where sub_work = rwh.sub_work and `status` = 'Success' and DATE_FORMAT(assessment_date,'%m-%Y') = DATE_FORMAT(NOW(),'%m-%Y')) <= 0 "
					+ "and (select count(*) from risk_upload where sub_work = rwh.sub_work and `status` = 'Success') >= 1 "
					+ "group by rwh.work_id_fk,hod_user_id_fk";		
			risk_alerts = jdbcTemplate.query( work_ids_qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/***************************** Risk alerts*******************************************************/
			if(!StringUtils.isEmpty(risk_alerts) && risk_alerts.size() > 0) {
				 Date date = new Date();
				 Calendar cal = Calendar.getInstance();
	             cal.setTime(date); // don't forget this if date is arbitrary
	             
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
            		 aObj.setReporting_to_user_id(alerts.getReporting_to_user_id());
 	 				 list.add(aObj);
				 }
			}
			/*************************Alerts insertion********************************************/
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,remarks,redirect_url) VALUES  (?,?,?,?,?,?,?,?)";		
			
			for (Alerts obj : list) {
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, null);
                stmt.setString(p++, redirect_url);
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
    				
    				p = 1;
    				stmt.setString(p++, alert_id);
	                stmt.setString(p++, obj.getUser_id_fk());
	                stmt.addBatch();
	                
					if(!StringUtils.isEmpty(obj.getReporting_to_user_id()) 
							&& ("3rd Alert".equals(obj.getAlert_level()) || "Overdue".equals(obj.getAlert_level())) ) {
						p = 1;
						stmt.setString(p++, alert_id);
					    stmt.setString(p++, obj.getReporting_to_user_id());
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
			
			String mitigation_alerts_qry = "select sub_work,owner,u.user_id as owner_user_id,DATE_FORMAT(date,'%d-%b-%Y') as assessment_date,"
					+ "(CASE "
					+ "WHEN (rr.`date` is not null and DATEDIFF(NOW(),rr.`date`) = 1) THEN '1st Alert' "
					+ "WHEN (rr.`date` is not null and DATEDIFF(NOW(),rr.`date`) = 2) THEN '2nd Alert' " 
					+ "WHEN (rr.`date` is not null and DATEDIFF(NOW(),rr.`date`) = 3) THEN '3rd Alert' "
					+ "ELSE 'Overdue' "
					+ "END "
					+ ") as alert_level " 
					+ "from risk_revision rr " 
					+ "left join risk r on risk_id_pk_fk = risk_id_pk "
					+ "left join user u on owner = u.designation " 
					+ "where date = (SELECT MAX(`date`) FROM `risk_revision` LEFT JOIN `risk` ON `risk_id_pk` = `risk_id_pk_fk` WHERE `sub_work` = `r`.`sub_work`) " 
					+ "and priority_fk <> 'Accepted' " 
					+ "and (mitigation_plan is null or mitigation_plan = '') " 
					+ "group by sub_work,owner";
			
			List<Alerts> risk_mitigation_alerts = jdbcTemplate.query( mitigation_alerts_qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/***************************** Risk alerts*******************************************************/
			if(!StringUtils.isEmpty(risk_mitigation_alerts) && risk_mitigation_alerts.size() > 0) {
	             for (Alerts alerts : risk_mitigation_alerts) {
            		 Alerts aObj = new Alerts();
            		 //aObj.setAlert_level("1st Alert");
            		 aObj.setAlert_value("Please update mitigation plan against prioritized risk(s) of "+alerts.getSub_work() + ".");
            		 aObj.setAlert_type("Risk");
            		 aObj.setRedirect_url("/risk-atr-update?sub_work="+alerts.getSub_work()+"&assessment_date="+alerts.getAssessment_date());
            		 aObj.setOwner_user_id(alerts.getOwner_user_id());
 	 				 list.add(aObj);
				 }
			}
			
			/****************************************************************************************/

			String atr_alerts_qry = "select distinct sub_work,owner,u1.user_id as owner_user_id,responsible_person,u2.user_id as responsible_person_user_id,count(risk_action_id) as racount,DATE_FORMAT(date,'%d-%b-%Y') as assessment_date, "
					+ "(CASE "
					+ "WHEN (rr.`date` is not null and DATEDIFF(NOW(),rr.`date`) = 1) THEN '1st Alert' "
					+ "WHEN (rr.`date` is not null and DATEDIFF(NOW(),rr.`date`) = 2) THEN '2nd Alert' " 
					+ "WHEN (rr.`date` is not null and DATEDIFF(NOW(),rr.`date`) = 3) THEN '3rd Alert' "
					+ "ELSE 'Overdue' "
					+ "END "
					+ ") as alert_level "
					+ "from risk_revision rr "  
					+ "left join risk r on risk_id_pk_fk = risk_id_pk " 
					+ "left join risk_action ra on risk_revision_id_fk = risk_revision_id "
					+ "left join user u1 on owner = u1.designation "
					+ "left join user u2 on responsible_person = u2.designation " 
					+ "where date = (SELECT MAX(`date`) FROM `risk_revision` LEFT JOIN `risk` ON `risk_id_pk` = `risk_id_pk_fk` WHERE `sub_work` = `r`.`sub_work`) " 
					+ "and priority_fk <> 'Accepted' " 
					+ "group by sub_work,risk_revision_id having racount = 0 "  
					+ "order by sub_work";
			
			List<Alerts> risk_atr_alerts = jdbcTemplate.query( atr_alerts_qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			/***************************** Risk alerts*******************************************************/
			if(!StringUtils.isEmpty(risk_atr_alerts) && risk_atr_alerts.size() > 0) {
				
	             for (Alerts alerts : risk_atr_alerts) {
            		 Alerts aObj = new Alerts();
            		 //aObj.setAlert_level("1st Alert");
            		 aObj.setAlert_value("Please update ATR against prioritized risk(s) of "+alerts.getSub_work() + ".");
            		 aObj.setAlert_type("Risk");
            		 aObj.setRedirect_url("/risk-atr-update?sub_work="+alerts.getSub_work()+"&assessment_date="+alerts.getAssessment_date());
            		 aObj.setOwner_user_id(alerts.getOwner_user_id());
            		 aObj.setResponsible_person_user_id(alerts.getResponsible_person_user_id());
 	 				 list.add(aObj);
				 }
			}
			
			/*************************Alerts insertion********************************************/
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,remarks,redirect_url) VALUES  (?,?,?,?,?,?,?,?)";		
			
			for (Alerts obj : list) {
				stmt = connection.prepareStatement(qryInsert,Statement.RETURN_GENERATED_KEYS);
				String alert_level = obj.getAlert_level();
				String alert_type = obj.getAlert_type();
				String contract_id = obj.getContract_id();
				String alert_value = obj.getAlert_value();
				String redirect_url = obj.getRedirect_url();
				
				int p = 1;
                stmt.setString(p++, alert_level);
                stmt.setString(p++, alert_type);
                stmt.setString(p++, contract_id);
                stmt.setString(p++, CommonConstants.ACTIVE);
                stmt.setString(p++, alert_value);
                stmt.setString(p++, "1");
                stmt.setString(p++, null);
                stmt.setString(p++, redirect_url);
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
	public boolean sendNotificationAlertMails(String alert_type) throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			
			String userIdQry = "SELECT user_id_fk,u2.email_id "
					+ "FROM alerts a " 
					+ "left join alerts_user u on u.alerts_id_fk = a.alert_id " 
					+ "left join user u2 on u.user_id_fk = u2.user_id " 
					+ "where a.alert_status = 'Active' and count <> 0 "
					+ "and u2.email_id is not null and u2.email_id <> '' and a.alert_type_fk <> 'Risk' ";
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(alert_type)) {
				userIdQry = userIdQry + " and a.alert_type_fk = ?";
				arrSize++;
			}
			userIdQry = userIdQry +  "group by user_id_fk";
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(alert_type)) {
				pValues[i++] = alert_type;
			}
			
			List<Alerts> userIdList = jdbcTemplate.query( userIdQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			Map<String,List<Alerts>> alerts = new LinkedHashMap<String, List<Alerts>>();
			for (Alerts uObj : userIdList) {
				String aLevelQry = "select alert_level " 
						+ "from alerts a "  
						+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id " 
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join user u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk <> 'Risk' ";
				
				arrSize = 2;
				if(!StringUtils.isEmpty(alert_type)) {
					aLevelQry = aLevelQry + " and a.alert_type_fk = ?";
					arrSize++;
				}
				aLevelQry = aLevelQry +  " group by alert_level order by alert_level desc";
				
				
				pValues = new Object[arrSize];
				i = 0;
				pValues[i++] = CommonConstants.ACTIVE;
				pValues[i++] = uObj.getUser_id_fk();
				if(!StringUtils.isEmpty(alert_type)) {
					pValues[i++] = alert_type;
				}
				
				List<Alerts> alert_levels = jdbcTemplate.query( aLevelQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				
				for (Alerts lObj : alert_levels) {
					
					String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,"
							+ "work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
							+ "left join contract c on a.contract_id = c.contract_id " 
							+ "left join work w on c.work_id_fk = w.work_id " 
							+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
							+ "left join user u on c.hod_user_id_fk = u.user_id " 
							+ "where alert_level = ? and alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk <> 'Risk' ";
					
					arrSize = 3;
					if(!StringUtils.isEmpty(alert_type)) {
						qry = qry + " and a.alert_type_fk = ?";
						arrSize++;
					}
					qry = qry +  " order by hod,work_short_name,a.contract_id asc, alert_level desc";
					
					
					pValues = new Object[arrSize];
					i = 0;
					pValues[i++] = lObj.getAlert_level();
					pValues[i++] = CommonConstants.ACTIVE;
					pValues[i++] = uObj.getUser_id_fk();
					if(!StringUtils.isEmpty(alert_type)) {
						pValues[i++] = alert_type;
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
					
					alerts.put(lObj.getAlert_level(), allAlertsList);
				}
				
				logger.error("sendNotificationAlertMails() >> before - "+uObj.getEmail_id() + " : "+alerts.size());	
				if(alerts != null && alerts.size() > 0) {
					logger.error("sendNotificationAlertMails() >> After - "+uObj.getEmail_id() + " : "+alerts.size());	
					
					SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
		            String today_date = monthFormat.format(new Date()).toUpperCase();
		            
		            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
		            String current_year = yearFormat.format(new Date()).toUpperCase();
		            
					String emailSubject = "PMIS "+alert_type+" Alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(uObj.getEmail_id());
					mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					logger.error("sendNotificationAlertMails() >> Alert Type "+alert_type+ ". Sending mail to "+uObj.getEmail_id()+": Start ");	
					emailSender.sendEmailWithAlerts(mail,alerts,today_date,current_year,alert_type); 
					logger.error("sendNotificationAlertMails() >> Alert Type "+alert_type+ ".  Sending mail to "+uObj.getEmail_id()+": End ");
				}
					
				flag = true;
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public boolean sendRiskNotificationAlertMails() throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			String userIdQry = "SELECT au.user_id_fk,u.email_id,ucc.email_id as reporting_to_email_id "
					+ "FROM alerts a " 
					+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
					+ "left join user u on au.user_id_fk = u.user_id "
					+ "left join user ucc on u.reporting_to_id_srfk = ucc.user_id "
					+ "where a.alert_status = 'Active' and count <> 0 "
					+ "and u.email_id is not null and u.email_id <> '' and a.alert_type_fk = 'Risk' "
					+ "group by au.user_id_fk";
			
			List<Alerts> userIdList = jdbcTemplate.query( userIdQry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			if(userIdList != null && userIdList.size() > 0) {
				for (Alerts uObj : userIdList) {
					String qry = "select alert_id,alert_level,alert_type_fk,created_date,alert_status,alert_value,count,u.designation as hod,"
							+ "IFNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join alerts_user au on au.alerts_id_fk = a.alert_id "
							+ "left join user u on au.user_id_fk = u.user_id " 
							+ "where alert_status = ? and au.user_id_fk = ? and count <> 0 and a.alert_type_fk = 'Risk' "
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
								}else if(alerts.getAlert_value().contains("Please update mitigation plan against")) {
									riskMitigationPlanAlertsList.add(alerts);
								}else if(alerts.getAlert_value().contains("Please update ATR against")) {
									riskATRAlertsList.add(alerts);
								}
							}
							
							if("Overdue".equals(alerts.getAlert_level())) {
								isOverdue = true;
							}
							
						}
						
						SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
			            String today_date = monthFormat.format(new Date()).toUpperCase();
			            
			            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
			            String current_year = yearFormat.format(new Date()).toUpperCase();
			            
			            Mail mail = new Mail();
						mail.setMailTo(uObj.getEmail_id());
						mail.setMailBcc(CommonConstants.BCC_MAIL);
						mail.setTemplateName("Risk_Alerts.vm");
						
						String emailSubject = "PMIS Risk Assessment Due";						
						if(riskMainAlertsList.size() > 0) {							
							if(isOverdue && !StringUtils.isEmpty(uObj.getReporting_to_email_id())) {
								mail.setMailCc(uObj.getReporting_to_email_id());
							}
							emailSubject = "PMIS Risk Assessment Due";
							mail.setMailSubject(emailSubject);
							logger.error("sendRiskNotificationAlertMails() >>Assessment Due Sending mail to "+uObj.getEmail_id()+": Start ");	
							emailSender.sendEmailWithRiskAlerts(mail,riskMainAlertsList,today_date,current_year); 
							logger.error("sendRiskNotificationAlertMails() >>Assessment Due Sending mail to "+uObj.getEmail_id()+": End ");
						}
						if(riskMitigationPlanAlertsList.size() > 0) {
							emailSubject = "PMIS Risk Assessment- Mitigation Plan";
							mail.setMailSubject(emailSubject);
							logger.error("sendRiskNotificationAlertMails() >> Mitigation Plan Sending mail to "+uObj.getEmail_id()+": Start ");	
							emailSender.sendEmailWithRiskAlerts(mail,riskMitigationPlanAlertsList,today_date,current_year); 
							logger.error("sendRiskNotificationAlertMails() >> Mitigation Plan Sending mail to "+uObj.getEmail_id()+": End ");
						}
						if(riskATRAlertsList.size() > 0) {
							emailSubject = "PMIS Risk Assessment- Action Taken Report";
							mail.setMailSubject(emailSubject);
							logger.error("sendRiskNotificationAlertMails() >> ATR Sending mail to "+uObj.getEmail_id()+": Start ");	
							emailSender.sendEmailWithRiskAlerts(mail,riskATRAlertsList,today_date,current_year); 
							logger.error("sendRiskNotificationAlertMails() >> ATR Sending mail to "+uObj.getEmail_id()+": End ");
						}						
						
					}
						
					flag = true;
				}
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	
	@Override
	public boolean sendAlertsToRajivRavi() throws Exception {
		boolean flag = false;
		try {
			
			
			String aTypeQry = "select alert_type_fk " 
					+ "from alerts a " 
					+ "left join contract c on a.contract_id = c.contract_id " 
					+ "left join work w on c.work_id_fk = w.work_id " 
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join user u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 and a.alert_type_fk <> 'Risk' "
					+ "group by alert_type_fk order by alert_type_fk desc";
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> alert_types = jdbcTemplate.query( aTypeQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			for (Alerts tObj : alert_types) {	
				Map<String,List<Alerts>> alerts = new LinkedHashMap<String, List<Alerts>>();
				String aLevelQry = "select alert_level " 
						+ "from alerts a " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id " 
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join user u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_status = ? and count <> 0 and a.alert_type_fk = ? "
						+ "group by alert_level order by alert_level desc";
				pValues = new Object[] {CommonConstants.ACTIVE,tObj.getAlert_type_fk()};
				List<Alerts> alert_levels = jdbcTemplate.query( aLevelQry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				
				for (Alerts lObj : alert_levels) {	
					String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,"
							+ "work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks,redirect_url " 
							+ "from alerts a "  
							+ "left join contract c on a.contract_id = c.contract_id " 
							+ "left join work w on c.work_id_fk = w.work_id " 
							+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
							+ "left join user u on c.hod_user_id_fk = u.user_id " 
							+ "where alert_level = ? and alert_status = ? and count <> 0 and a.alert_type_fk = ? "
							+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
					
					
					pValues = new Object[] {lObj.getAlert_level(),CommonConstants.ACTIVE,tObj.getAlert_type_fk()};
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
					
					alerts.put(lObj.getAlert_level(), allAlertsList);
				}
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY");
	            String today_date = dateFormat.format(new Date()).toUpperCase();
	            
	            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
	            String current_year = yearFormat.format(new Date()).toUpperCase();
	            
				String emailSubject = "PMIS "+tObj.getAlert_type_fk()+" Alerts";
				
				Mail mail = new Mail();
				mail.setMailTo(CommonConstants2.ALERTS_EMAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("alertsRajivRavi.vm");
				
				if(alerts != null && alerts.size() > 0){
					EMailSender emailSender = new EMailSender();
					logger.error("sendAlertsToRajivRavi() >> Sending mail : Start ");	
					emailSender.sendEmailWithAlertsRajivRavi(mail,alerts,tObj.getAlert_type_fk(),today_date,current_year); 
					logger.error("sendAlertsToRajivRavi() >> Sending mail : End ");
					flag = true;
				}
			}
			sendRiskNotificationAlertMailsToRaviRajiv();
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	public boolean sendRiskNotificationAlertMailsToRaviRajiv() throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			String qry = "select alert_id,alert_level,alert_type_fk,created_date,alert_status,alert_value,count,u.designation as hod,"
					+ "IFNULL(a.remarks,'') as remarks,redirect_url " 
					+ "from alerts a "  
					+ "left join risk_work_hod rwh on ((rwh.sub_work = REPLACE(a.redirect_url,'/risk-assessment?sub_work=','')) "
					+ "or (rwh.sub_work = substring_index(REPLACE(a.redirect_url,'/risk-atr-update?sub_work=',''), '&assessment_date=',1) )) "
					+ "left join `user` u on rwh.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 and a.alert_type_fk = 'Risk' "
					+ "order by hod,alert_level desc";
			
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
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
				
				String emailSubject = "PMIS Risk Assessment Due";
				
				Mail mail = new Mail();
				mail.setMailTo(CommonConstants2.ALERTS_EMAIL);
				//mail.setMailBcc(CommonConstants.BCC_MAIL);
				mail.setMailSubject(emailSubject);
				mail.setTemplateName("Risk_Alerts.vm");
				
				logger.error("sendRiskNotificationAlertMailsToRaviRajiv() >>: Start ");	
				emailSender.sendEmailWithRiskAlerts(mail,riskAlertsList,today_date,current_year); 
				logger.error("sendRiskNotificationAlertMailsToRaviRajiv() >> : End ");
			}
				
			flag = true;
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
					+ "work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,c.work_id_fk,work_id,work_name,c.contract_short_name,redirect_url "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
				qry = qry + " and a.alert_level = ?";
				arrSize++;
			}
			
			qry = qry + " order by u.designation,work_short_name,a.contract_id asc, alert_level desc";
			
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
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

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
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
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
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
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
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
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
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
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
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
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
					+ "from alerts a ";  
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id ";
					}
			
			qry = qry + "left join contract c on a.contract_id = c.contract_id " 
					+ "left join work w on c.work_id_fk = w.work_id " 
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join user u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 ";
					
			int arrSize = 1;
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
				
				
				qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,"
						+ "work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks,redirect_url " 
						+ "from alerts a " ; 
						
						if(!"IT Admin".equals(obj.getUser_role_name())) {
							qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
						}
				
				qry = qry + "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id " 
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join user u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_status = ? and count <> 0 ";
				
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
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
					qry = qry + " and u.designation = ? ";
					arrSize++;
				}
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getAlert_level())) {
					qry = qry + " and a.alert_level = ?";
					arrSize++;
				}
				
				qry = qry + " order by u.designation,work_short_name,a.contract_id asc, alert_level desc";
				
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
			throw new Exception(e.getMessage());
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
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join user u on c.hod_user_id_fk = u.user_id "
					+ "where alert_status = ? and count <> 0 ";
			
			int arrSize = 1;
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
			
			String qry1 = "call 1_project_months()";			
			stmt = connection.prepareCall(qry1);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			
			String qry2 = "call 2_planned_normal_distribution_day()";			
			stmt = connection.prepareCall(qry2);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			
			String qry3 = "call 3_actual_normal_distribution_day()";			
			stmt = connection.prepareCall(qry3);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			
			String qry4 = "call 4_progress_monthly_summary_bell()";			
			stmt = connection.prepareCall(qry4);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			
			String qry5 = "call create_calendar_dates()";			
			stmt = connection.prepareCall(qry5);			
			stmt.executeQuery();  
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, resultSet);
			
			flag = true;
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
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
			
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
			
			qry = qry + " order by u.designation,work_short_name,a.contract_id asc, alert_level desc";
			
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
			
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return totalRecords;
	}


	@Override
	public List<Alerts> getAlertsList(Alerts obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Alerts> objsList = new ArrayList<Alerts>();
		try {
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,IFNULL(a.remarks,'') as remarks,count,u.designation as hod,"
					+ "work_short_name,contract_short_name,contractor_name,a.hod_email,a.dy_hod_email,c.work_id_fk,work_id,work_name,c.contract_short_name,redirect_url "
					+ "from alerts a "; 
					if(!"IT Admin".equals(obj.getUser_role_name())) {
						qry = qry + "left join alerts_user au on au.alerts_id_fk = a.alert_id "; 
					}
			
			qry = qry + "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id "
					+ "where a.contract_id is not null and a.contract_id <> '' and count <> 0 and alert_status = ? ";
			
			int arrSize = 1;
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
			
			qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
			
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " order by u.designation,work_short_name,a.contract_id asc, alert_level desc limit ?,?";
				arrSize++;
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
			throw new Exception(e.getMessage());
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
					+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left join user u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and count <> 0 ";
					
			int arrSize = 1;
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getHod())) {
				qry = qry + " and u.designation = ? ";
				arrSize++;
			}
			
			//qry = qry + " and a.alert_type_fk not in ('Issue','Risk')";
			
			qry = qry + " group by alert_type_fk order by alert_type_fk ASC";
			
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
			alertTypes = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		return alertLevels;
	}

	@Override
	public List<Alerts> getSendToListForGenerateSendAlerts() throws Exception {
		List<Alerts> sendToList = new ArrayList<Alerts>();
		try {	
			String qry = "select user_id,user_name,designation,email_id,user_role_name_fk,user_type_fk "
					+ "from user where email_id is not null and email_id <> ''"; 
			sendToList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
					+ "where alert_status = ? and count <> 0 and a.alert_type_fk <> 'Risk' ";
			
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
				
				String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,"
						+ "work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks,redirect_url " 
						+ "from alerts a " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id " 
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join user u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_level = ? and alert_status = ? and count <> 0 and a.alert_type_fk <> 'Risk' ";
				
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
				
				qry = qry + " order by hod,work_short_name,a.contract_id asc, alert_level desc";
				
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
				
				alerts.put(lObj.getAlert_level(), allAlertsList);
			}
			
			List<String> email_ids = new ArrayList<String>();
			if(!StringUtils.isEmpty(obj.getEmail_id())) {
				email_ids = Arrays.asList(obj.getEmail_id().split(","));
			}				
			for (String email_id : email_ids) {
				logger.error("sendAlertsToParticulars() >> before - "+email_id + " : "+alerts.size());	
				if(alerts != null && alerts.size() > 0 && !StringUtils.isEmpty(email_id)) {
					logger.error("sendAlertsToParticulars() >> After - "+email_id + " : "+alerts.size());	
					
					SimpleDateFormat monthFormat = new SimpleDateFormat("dd-MMM-YYYY");
		            String today_date = monthFormat.format(new Date()).toUpperCase();
		            
		            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
		            String current_year = yearFormat.format(new Date()).toUpperCase();
		            
		            String alert_type = "";
		            
		            if(!StringUtils.isEmpty(obj.getAlert_type_fk()) && obj.getAlert_type_fk().length() > 0) {
						alert_type = String.join(",", obj.getAlert_type_fk());
					}else {
						alert_type = "Contract & Issue";
					}
		            
					String emailSubject = "PMIS "+alert_type+" Alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(email_id);
					mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					logger.error("sendAlertsToParticulars() >> Sending mail to "+email_id+": Start ");	
					emailSender.sendEmailWithAlerts(mail,alerts,today_date,current_year,alert_type); 
					logger.error("sendAlertsToParticulars() >> Sending mail to "+email_id+": End ");
					flag = true;
				}
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	
}
