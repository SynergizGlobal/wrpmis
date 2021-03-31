package com.synergizglobal.pmis.IMPLdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ " from contract c " + 
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk "
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 15) and release_date is null";
			
			List<Alerts> bgQryAlert1List = jdbcTemplate.query( bgQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert1List) && bgQryAlert1List.size() > 0) {
				list.addAll(bgQryAlert1List);
			}			
			
			String bgQryAlert2 = "select bg.contract_id_fk as contract_id, '2nd Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ " from contract c " +
					"left outer join bank_guarantee bg on c.contract_id = bg.contract_id_fk "
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 15 and DATEDIFF(valid_upto ,NOW()) > 7) and release_date is null";
			
			List<Alerts> bgQryAlert2List = jdbcTemplate.query( bgQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(bgQryAlert2List) && bgQryAlert2List.size() > 0) {
				list.addAll(bgQryAlert2List);
			}	
			
			String bgQryAlert3 = "select bg.contract_id_fk as contract_id, '3rd Alert' as alert_level,'Bank Guarantee' as alert_type,"
					+ "(case when bg.bg_type_fk is not null then CONCAT(bg.bg_type_fk,' ',bg.bg_number, ' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Bank guarantee ',bg.bg_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
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
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "
					+ "from contract c " + 
					"left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 15) and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert1List = jdbcTemplate.query( insuranceQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert1List) && insuranceQryAlert1List.size() > 0) {
				list.addAll(insuranceQryAlert1List);
			}
			
			String insuranceQryAlert2 = "select bg.contract_id_fk as contract_id, '2nd Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "
					+ " from contract c "
					+ "left outer join insurance bg on c.contract_id = bg.contract_id_fk " 
					+ "where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 15 and DATEDIFF(valid_upto ,NOW()) > 7) and (bg.released_fk = 'No' or bg.released_fk is null)";
			
			List<Alerts> insuranceQryAlert2List = jdbcTemplate.query( insuranceQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(insuranceQryAlert2List) && insuranceQryAlert2List.size() > 0) {
				list.addAll(insuranceQryAlert2List);
			}
			
			String insuranceQryAlert3 = "select bg.contract_id_fk as contract_id, '3rd Alert' as alert_level,'Insurance' as alert_type,"
					+ "(case when bg.insurance_type_fk is not null then CONCAT(bg.insurance_type_fk,' ',bg.insurance_number, ' Valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) " 
					+ "else CONCAT('Insurance ',bg.insurance_number,' valid upto ',DATE_FORMAT(valid_upto,'%d-%b-%Y') ) end ) as alert_value,"
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "
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
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null " 
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 30 " 
					+ "and DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 15)";
			
			List<Alerts> cpQryAlert1List = jdbcTemplate.query( cpQryAlert1, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert1List) && cpQryAlert1List.size() > 0) {
				list.addAll(cpQryAlert1List);
			}
			String cpQryAlert2 = "select contract_id,'2nd Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
					+ "from contract c " 
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_doc is not null "
					+ "where c.contract_status_fk = 'In Progress' " 
					+ "and (DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) <= 15 " 
					+ "and DATEDIFF((CASE WHEN (cr.action = 'Yes' and cr.revised_doc is not null) THEN revised_doc WHEN doc is not null THEN doc ELSE '' END) ,NOW()) > 7)";
			
			List<Alerts> cpQryAlert2List = jdbcTemplate.query( cpQryAlert2, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			if(!StringUtils.isEmpty(cpQryAlert2List) && cpQryAlert2List.size() > 0) {
				list.addAll(cpQryAlert2List);
			}
			
			
			String cpQryAlert3 = "select contract_id,'3rd Alert' as alert_level,'Contract Period' as alert_type," 
					+ "(case when (cr.action = 'Yes' and cr.revised_doc is not null) then (CONCAT('Contract revised date : ',DATE_FORMAT(cr.revised_doc,'%d-%b-%Y') )) " 
					+ "when doc is not null then CONCAT('Date of Completion : ',DATE_FORMAT(doc,'%d-%b-%Y') ) else '' end ) as alert_value," 
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk " 
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
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "  
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
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "  
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
					+ "concat('/get-contract/',contract_id) as redirect_url,hod_user_id_fk,dy_hod_user_id_fk "  
					+ "from contract c "  
					+ "LEFT JOIN contract_revision cr on cr.contract_id_fk = c.contract_id and cr.action = 'Yes' and cr.revised_amount is not null "
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
			
			
			String qryInsert = "INSERT INTO alerts (alert_level,alert_type_fk,contract_id,alert_status,alert_value,`count`,remarks,redirect_url) VALUES  (?,?,?,?,?,?,?,?)";
			
			/*int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions, new BatchPreparedStatementSetter() { 
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
			           	});*/
			//transactionManager.commit(status);	
			
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
			
			flag = true;
		}catch(Exception e){ 
			//transactionManager.rollback(status);
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, resultSet);
		}
		return flag;
	}

	
	@Override
	public boolean sendNotificationAlertMails() throws Exception {
		boolean flag = false;
		try {
			EMailSender emailSender = new EMailSender();
			
			
			String userIdQry = "SELECT user_id_fk,u2.email_id FROM alerts a " 
					+ "left join alerts_user u on u.alerts_id_fk = a.alert_id " 
					+ "left join user u2 on u.user_id_fk = u2.user_id " 
					+ "where a.alert_status = 'Active' and count <> 0 "
					+ "and u2.email_id is not null and u2.email_id <> '' group by user_id_fk";
			
			List<Alerts> userIdList = jdbcTemplate.query( userIdQry, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			for (Alerts uObj : userIdList) {
				String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,u.designation as hod,"
						+ "work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks,redirect_url " 
						+ "from alerts a "  
						+ "left join alerts_user au on au.alerts_id_fk = a.alert_id " 
						+ "left join contract c on a.contract_id = c.contract_id " 
						+ "left join work w on c.work_id_fk = w.work_id " 
						+ "left join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
						+ "left join user u on c.hod_user_id_fk = u.user_id " 
						+ "where alert_status = ? and au.user_id_fk = ? and count <> 0 "
						+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
				
				
				Object[] pValues = new Object[] {CommonConstants.ACTIVE,uObj.getUser_id_fk()};
				List<Alerts> allAlertsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
				
				if(allAlertsList != null && allAlertsList.size() > 0) {
					String emailSubject = "PMIS Contract & Issue Alerts";
					
					Mail mail = new Mail();
					mail.setMailTo(uObj.getEmail_id());
					mail.setMailBcc(CommonConstants.BCC_MAIL);
					mail.setMailSubject(emailSubject);
					mail.setTemplateName("alerts.vm");
					
					logger.error("sendNotificationAlertMails() >> Sending mail to "+uObj.getEmail_id()+": Start ");	
					emailSender.sendEmailWithAlerts(mail,allAlertsList); 
					logger.error("sendNotificationAlertMails() >> Sending mail to "+uObj.getEmail_id()+": End ");
				}
					
				flag = true;
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
			/*String qry ="select alert_id,alert_level,alert_type_fk,contract_id,created_date,alert_status,alert_value,count"
					+ " from alerts where alert_status = ? and contract_id is not null and contract_id <> '' and count <> 0 ";*/
			
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,"
					+ "u.designation as hod,work_short_name,contract_short_name,contractor_name,IFNULL(a.remarks,'') as remarks,redirect_url " 
					+ "from alerts a " 
					+ "left outer join contract c on a.contract_id = c.contract_id " 
					+ "left outer join work w on c.work_id_fk = w.work_id " 
					+ "left outer join contractor ctr on c.contractor_id_fk = ctr.contractor_id " 
					+ "left outer join user u on c.hod_user_id_fk = u.user_id " 
					+ "where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0 "
					+ "order by hod,work_short_name,a.contract_id asc, alert_level desc";
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
			List<Alerts> objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));
			
			String emailSubject = "PMIS Contract & Issue Alerts";
			
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
					+ "concat('Issue ',status_fk,': ',i.title) as alert_value,concat('/get-issue/',issue_id) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk "
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
					+ "concat('Issue ',status_fk,': ',i.title) as alert_value,concat('/get-issue/',issue_id) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk "
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
					+ "concat('Issue ',status_fk,': ',i.title) as alert_value,concat('/get-issue/',issue_id) as redirect_url,"
					+ "d.department_name,responsible_person,escalated_to,"
					+ "c.hod_user_id_fk,c.dy_hod_user_id_fk,created_by_user_id_fk "
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
			
			/*int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions, new BatchPreparedStatementSetter() { 
								@Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
									String alert_level = list.get(i).getAlert_level();
									String alert_type = list.get(i).getAlert_type();
									String contract_id = list.get(i).getContract_id();
									String alert_value = list.get(i).getAlert_value();
									String redirect_url = list.get(i).getRedirect_url();
									
									int p = 1;
				                    ps.setString(p++, alert_level);
				                    ps.setString(p++, alert_type);
				                    ps.setString(p++, contract_id);
				                    ps.setString(p++, CommonConstants.ACTIVE);
				                    ps.setString(p++, alert_value);
				                    ps.setString(p++, "1");
				                    ps.setString(p++, getAlertRemarks(alert_type,contract_id,alert_value));
				                    ps.setString(p++, redirect_url);
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
			           	});*/
			
			
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
			//transactionManager.rollback(status);
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
				qry = qry + " and (u.designation like ? or c.work_id_fk like ? or w.work_short_name like ? or c.contractor_id_fk like ?"
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
				qry = qry + " and (u.designation like ? or c.work_id_fk like ? or w.work_short_name like ? or c.contractor_id_fk like ?"
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

	
}
