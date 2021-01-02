package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Alerts;
@Repository
public class AlertsDaoImpl implements AlertsDao{
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
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 15)";
			
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
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 15 and DATEDIFF(valid_upto ,NOW()) > 7)";
			
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
					+ "where contract_status_fk = 'In Progress' and DATEDIFF(valid_upto ,NOW()) <= 7";
			
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
					+"where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 30 and DATEDIFF(valid_upto ,NOW()) > 15)";
			
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
					+ "where contract_status_fk = 'In Progress' and (DATEDIFF(valid_upto ,NOW()) <= 15 and DATEDIFF(valid_upto ,NOW()) > 7)";
			
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
					+ "where contract_status_fk = 'In Progress' and DATEDIFF(valid_upto ,NOW()) <= 7";
			
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
	public List<Alerts> getAlertsList() throws Exception {
		List<Alerts> objsList = null;
		try {
			/*String qry ="select alert_id,alert_level,alert_type_fk,contract_id,created_date,alert_status,alert_value,count"
					+ " from alerts where alert_status = ? and contract_id is not null and contract_id <> '' and count <> 0 ";*/
			
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,hod,work_short_name,contract_short_name  " + 
					"from alerts a " + 
					"left outer join contract_view cv on a.contract_id COLLATE utf8mb4_unicode_ci = cv.contract_id " + 
					"where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0";
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	//@Override
	public List<Alerts> getAlertsList2() throws Exception {
		List<Alerts> objsList = null;
		try {
			
			String qry = "select alert_id,alert_level,alert_type_fk,a.contract_id,created_date,alert_status,alert_value,count,hod,work_short_name,contract_short_name  " + 
					"from alerts a " + 
					"left outer join contract_view cv on a.contract_id COLLATE utf8mb4_unicode_ci = cv.contract_id " + 
					"where alert_status = ? and a.contract_id is not null and a.contract_id <> '' and count <> 0";
			
			Object[] pValues = new Object[] {CommonConstants.ACTIVE};
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Alerts>(Alerts.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
}
