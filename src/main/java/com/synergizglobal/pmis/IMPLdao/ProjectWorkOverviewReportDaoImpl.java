package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mysql.cj.jdbc.CallableStatement;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.ProjectWorkOverviewReportDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Work;
import com.synergizglobal.pmis.model.Contract;
@Repository
public class ProjectWorkOverviewReportDaoImpl implements ProjectWorkOverviewReportDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;

	@Autowired
	MessagesDao messagesDao;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	

	@Override
	public List<Contract> getWorksFilterListInPOR(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "SELECT distinct work_id_fk,w.work_name,w.work_short_name "
					+ "from contract c " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Contract> getProjectsFilterListInPOR(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "SELECT project_id_fk,p.project_name "
					+ "from contract c " + 
					"LEFT JOIN work w on c.work_id_fk = w.work_id " + 
					"LEFT JOIN project p on w.project_id_fk = p.project_id " +
					"where project_id_fk is not null and project_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and w.project_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY project_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Work> getProjectOverviewReportList(Work obj) throws Exception {
		List<Work> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry = "SELECT distinct w.work_short_name,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost,latest_revised_cost,\r\n"
					+ "sum(e.gross_work_done*e.gross_work_done_units) as cumulative_total_expenditure,\r\n"
					+ "\r\n"
					+ "ifnull((select sum(e1.gross_work_done*e1.gross_work_done_units) from expenditure e1\r\n"
					+ "					where contract_id_fk=c.contract_id and work_id='"+obj.getWork_id_fk()+"' \r\n"
					+ "					and voucher_type=(SELECT CASE WHEN MONTH(NOW()) >= 4 THEN concat(YEAR(NOW()), '-',SUBSTR(YEAR(NOW())+1,3,2)) ELSE concat(YEAR(NOW())-1,'-', SUBSTR(YEAR(NOW()),3,2)) END)\r\n"
					+ "					)\r\n"
					+ "					 ,0) as expenditure_current_fy,\r\n"
					+ "                     \r\n"
					+ "                     \r\n"
					+ " ifnull((select sum(e1.gross_work_done*e1.gross_work_done_units) from expenditure e1\r\n"
					+ "					where contract_id_fk=c.contract_id and work_id='"+obj.getWork_id_fk()+"' and\r\n"
					+ "					date<=(SELECT date_format(str_to_date(concat(YEAR(CURDATE())-1,'-03-31'), '%Y-%m-%d'), '%Y-%m-%d'))\r\n"
					+ "					)\r\n"
					+ "					 ,0) as expenditure_end_of_fy ,\r\n"
					+ "                     \r\n"
					+ "   ifnull((select budget_estimate from budget e1\r\n"
					+ "					where work_id_fk='"+obj.getWork_id_fk()+"' and\r\n"
					+ "					financial_year_fk=(SELECT CASE WHEN MONTH(NOW()) >= 4 THEN concat(YEAR(NOW()), '-',SUBSTR(YEAR(NOW())+1,3,2)) ELSE concat(YEAR(NOW())-1,'-', SUBSTR(YEAR(NOW()),3,2)) END)\r\n"
					+ "					)\r\n"
					+ "					 ,0) as budget_grant_current_fy ,ifnull(target_doc,'') as target_completion_date ,\r\n"
					+ "                     \r\n"
					+ "                     \r\n"
					+ "  ifnull(round((select sum(work_per) from activities_scurve s \r\n"
					+ "left join contract c on c.contract_id=s.contract_id \r\n"
					+ "where c.contract_id is not null and work_id='"+obj.getWork_id_fk()+"' ),2),'')  as physical_progress,ifnull(round((select sum(e.gross_work_done*e.gross_work_done_units)/sum(ifnull(revised_cost, ifnull(awarded_cost,ifnull(estimated_cost,0)))) from contract_details c where work_id='"+obj.getWork_id_fk()+"'),2),'') AS financial_progress "
					+ " from contract c \r\n"
					+ "										LEFT JOIN work w on c.work_id_fk = w.work_id \r\n"
					+ "					                  left join work_yearly_sanction wy on wy.work_id_fk=w.work_id\r\n"
					+ "										LEFT JOIN project p on w.project_id_fk = p.project_id \r\n"
					+ "					                  left join expenditure e on e.contract_id_fk= c.contract_id and e.voucher_type=(SELECT CASE WHEN MONTH(NOW()) >= 4 THEN concat(YEAR(NOW()), '-',SUBSTR(YEAR(NOW())+1,3,2)) ELSE concat(YEAR(NOW())-1,'-', SUBSTR(YEAR(NOW()),3,2)) END)\r\n"
					+ "					                  LEFT JOIN budget b on b.work_id_fk = w.work_id and b.financial_year_fk=(SELECT CASE WHEN MONTH(NOW()) >= 4 THEN concat(YEAR(NOW()), '-',SUBSTR(YEAR(NOW())+1,3,2)) ELSE concat(YEAR(NOW())-1,'-', SUBSTR(YEAR(NOW()),3,2)) END)\r\n"
					+ "					                  \r\n"
					+ "										where c.work_id_fk is not null and c.work_id_fk <> '' and work_id='"+obj.getWork_id_fk()+"'\r\n";

		
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));
			for (Work cObj : objsList) {
				String cumulative_total_expenditure = cObj.getCumulative_total_expenditure();
				String cumulative_total_expenditure_value = "";
				if(!StringUtils.isEmpty(cumulative_total_expenditure)) {
					double val = (Double.parseDouble(cumulative_total_expenditure))/10000000;
					cumulative_total_expenditure_value = numberFormatter.format(val);
				}
				cObj.setCumulative_total_expenditure(cumulative_total_expenditure_value);

				String expenditure_current_fy = cObj.getExpenditure_current_fy();
				String expenditure_current_fy_value = "";
				if(!StringUtils.isEmpty(expenditure_current_fy)) {
					double val = (Double.parseDouble(expenditure_current_fy))/10000000;
					expenditure_current_fy_value = numberFormatter.format(val);
				}
				cObj.setExpenditure_current_fy(expenditure_current_fy_value);
				
				String expenditure_end_of_fy = cObj.getExpenditure_end_of_fy();
				String expenditure_end_of_fy_value = "";
				if(!StringUtils.isEmpty(expenditure_end_of_fy)) {
					double val = (Double.parseDouble(expenditure_end_of_fy))/10000000;
					expenditure_end_of_fy_value = numberFormatter.format(val);
				}
				cObj.setExpenditure_end_of_fy(expenditure_end_of_fy_value);				
			}			
			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	
	
	@Override
	public List<Contract> getProjectPhotos(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "select DATE_FORMAT(sd.created_date,'%d-%m-%Y') as Date,sd.attachment,s.structure_type_fk as strip_chart_type_fk,structure as name "
					+ "from structure s "
					+ "inner join structure_documents sd on sd.structure_id_fk=s.structure_id "
					+ "inner join work w on s.work_id_fk = w.work_id "
					+ "inner join project p on w.project_id_fk = p.project_id where structure_id is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and s.work_id_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Contract> getStructuralProgress(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "SELECT structure_type as strip_chart_type_fk,ifnull(round(sum(structure_type_per)*100,2),0) as structure_type_per,ifnull((select actual_completion_date from structure where structure_type_fk=structure_type and actual_completion_date is not null limit 1),'') as actual_completion_date FROM pmis.activities_scurve \r\n"
					+ "where structure_type is not null and category = 'Actual' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			qry=qry+" group by structure_type,work_id order by structure_type";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Contract> getListOfContracts(Contract obj) throws Exception {
		List<Contract> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry = "select distinct c.contract_id,c.department as department_id_fk,contract_short_name,ifnull(loa_date,'') as loa_date,ifnull(actual_completion_date,'') as actual_completion_date,ifnull(round(SUM(contract_per),2),'') AS actual_physical_progress,ifnull(estimated_cost,'') as estimated_cost,ifnull(awarded_cost,'') as awarded_cost,ifnull(remarks,'') as remarks from contract_details c\r\n"
					+ "left join activities_scurve b on b.contract_id=c.contract_id where 0=0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id = ?";
				arrSize++;
			}
			qry=qry+" group by contract_id order by FIELD(c.department,'Engineering','Electrical','Signalling & Telecom')";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
			for (Contract cObj : objsList) {
				String awarded_cost = cObj.getAwarded_cost();
				String awarded_cost_value = "";
				if(!StringUtils.isEmpty(awarded_cost)) {
					double val = (Double.parseDouble(awarded_cost))/10000000;
					awarded_cost_value = numberFormatter.format(val);
				}
				cObj.setAwarded_cost(awarded_cost_value);

				String estimated_cost = cObj.getEstimated_cost();
				String estimated_cost_value = "";
				if(!StringUtils.isEmpty(estimated_cost)) {
					double val = (Double.parseDouble(estimated_cost))/10000000;
					estimated_cost_value = numberFormatter.format(val);
				}
				cObj.setEstimated_cost(estimated_cost_value);
			}			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<LandAcquisition> getLandAcquisitionStatus(LandAcquisition obj) throws Exception {
		List<LandAcquisition> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry = "select concat(li.category_fk,' Land') as type_of_land,ifnull(round(sum(area_to_be_acquired),2),'') as area_to_be_acquired,ifnull(round(sum(area_acquired),2),'') as area_acquired,\r\n"
					+ "ifnull(round(sum(area_to_be_acquired)-sum(area_acquired),2),'') as balance,\r\n"
					+ "ifnull(sum(jm_fee_amount*jm_fee_amount_units),'') AS jm_fee_amount,\r\n"
					+ "\r\n"
					+ "ifnull(round(case when li.category_fk='Government' then (select sum(ifnull(amount_paid,0)* ifnull(amount_paid_units,0)) from la_land_identification li\r\n"
					+ "left join la_government_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Government')\r\n"
					+ "when li.category_fk='Private' then (select sum(ifnull(total_compensation,0))  from la_land_identification li\r\n"
					+ "left join la_private_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Private') else 0 end,2),0)\r\n"
					+ "\r\n"
					+ " as payment_amount_units_railway\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "					 from la_land_identification li   \r\n"
					+ "					left join work w on li.work_id_fk = w.work_id \r\n"
					+ "					 left join land_executives le on li.work_id_fk = le.work_id_fk  \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join la_sub_category sc on li.la_sub_category_fk = sc.id \r\n"
					+ "					left join la_category c on sc.la_category_fk = c.la_category \r\n"
					+ "					where la_id is not null  ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			qry=qry+" group by li.category_fk union all ";
			
			qry = qry+"select 'Total',ifnull(round(sum(area_to_be_acquired),2),'') as area_to_be_acquired,ifnull(round(sum(area_acquired),2),'') as area_acquired,\r\n"
					+ "ifnull(round(sum(area_to_be_acquired)-sum(area_acquired),2),'') as balance,\r\n"
					+ "ifnull(sum(jm_fee_amount*jm_fee_amount_units),'') AS jm_fee_amount,ifnull(round(case when li.category_fk='Government' then (select sum(ifnull(amount_paid,0)* ifnull(amount_paid_units,0)) from la_land_identification li\r\n"
					+ "left join la_government_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Government')\r\n"
					+ "when li.category_fk='Private' then (select sum(ifnull(total_compensation,0))  from la_land_identification li\r\n"
					+ "left join la_private_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Private') else 0 end,2),0) as payment_amount_units_railway\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "					 from la_land_identification li   \r\n"
					+ "					left join work w on li.work_id_fk = w.work_id \r\n"
					+ "					 left join land_executives le on li.work_id_fk = le.work_id_fk  \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join la_sub_category sc on li.la_sub_category_fk = sc.id \r\n"
					+ "					left join la_category c on sc.la_category_fk = c.la_category \r\n"
					+ "					where la_id is not null  ";
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}			
			arrSize=arrSize+2;
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
				pValues[i++] = obj.getWork_id_fk();
				pValues[i++] = obj.getWork_id_fk();
				pValues[i++] = obj.getWork_id_fk();
				pValues[i++] = obj.getWork_id_fk();
				pValues[i++] = obj.getWork_id_fk();				
			}
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<LandAcquisition>(LandAcquisition.class));
			
			for (LandAcquisition cObj : objsList) {
				String Payment_amount_units_railway = cObj.getPayment_amount_units_railway();
				String Payment_amount_units_railway_value = "";
				if(!StringUtils.isEmpty(Payment_amount_units_railway)) {
					double val = (Double.parseDouble(Payment_amount_units_railway))/10000000;
					Payment_amount_units_railway_value = numberFormatter.format(val);
				}
				cObj.setPayment_amount_units_railway(Payment_amount_units_railway_value);
			}					
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	
	@Override
	public List<Contract> getProcurementStatus(Contract obj) throws Exception {
		List<Contract> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry = "select work_name,count(c.contract_id) as 'total',sum(estimated_cost) as estimated_cost,\r\n"
					+ "                    \r\n"
					+ "                   (select group_concat(distinct structure_type SEPARATOR ', ') from activities_scurve where work_id_fk='"+obj.getWork_id_fk()+"') as strip_chart_type_fk,\r\n"
					+ "                    \r\n"
					+ "                   \r\n"
					+ "                    \r\n"
					+ "                      (select count(*)\r\n"
					+ "                    from contract c  \r\n"
					+ "					left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci  \r\n"
					+ "					left join contractor cr on c.contractor_id_fk = cr.contractor_id \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join user u on c.hod_user_id_fk = u.user_id\r\n"
					+ "					left join department hoddt on u.department_fk = hoddt.department\r\n"
					+ "					left join user us on c.dy_hod_user_id_fk = us.user_id\r\n"
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and contract_status_fk='Awarded') as contract_details_types,\r\n"
					+ "                    \r\n"
					+ "                     (select sum(awarded_cost) as awarded_cost\r\n"
					+ "                    from contract c  \r\n"
					+ "					left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci  \r\n"
					+ "					left join contractor cr on c.contractor_id_fk = cr.contractor_id \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join user u on c.hod_user_id_fk = u.user_id\r\n"
					+ "					left join department hoddt on u.department_fk = hoddt.department\r\n"
					+ "					left join user us on c.dy_hod_user_id_fk = us.user_id\r\n"
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and contract_status_fk='Awarded' )   as awarded_cost,               \r\n"
					+ " ifnull((select group_concat(distinct structure_type) as Scope from activities_scurve s \r\n"
					+ "left join contract c on c.contract_id=s.contract_id \r\n"
					+ "where c.contract_id is not null and work_id='"+obj.getWork_id_fk()+"' and contract_status_fk='Awarded'),'') as scope_of_contract\r\n"
					+ "\r\n"
					+ "                    \r\n"
					+ "                    from contract c  \r\n"
					+ "					left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci  \r\n"
					+ "					left join contractor cr on c.contractor_id_fk = cr.contractor_id \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join user u on c.hod_user_id_fk = u.user_id\r\n"
					+ "					left join department hoddt on u.department_fk = hoddt.department\r\n"
					+ "					left join user us on c.dy_hod_user_id_fk = us.user_id\r\n"
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"'\r\n"
					+ "                    \r\n"
					+ "                    \r\n"
					+ "                    union all\r\n"
					+ "                    \r\n"
					+ "                    \r\n"
					+ "                    \r\n"
					+ "                     select hoddt.department_name,count(c.contract_id) as 'total',sum(estimated_cost) as estimated_cost,\r\n"
					+ "                    \r\n"
					+ "                   (select group_concat(distinct structure_type) from activities_scurve where work_id_fk='"+obj.getWork_id_fk()+"' and department=hoddt.department_name) as strip_chart_type_fk,\r\n"
					+ "                    \r\n"
					+ "                   \r\n"
					+ "                    \r\n"
					+ "                      (select count(*)\r\n"
					+ "                    from contract c  \r\n"
					+ "					left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci  \r\n"
					+ "					left join contractor cr on c.contractor_id_fk = cr.contractor_id \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join user u on c.hod_user_id_fk = u.user_id\r\n"
					+ "					left join department hoddt on u.department_fk = hoddt.department\r\n"
					+ "					left join user us on c.dy_hod_user_id_fk = us.user_id\r\n"
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and contract_status_fk='Awarded') as contract_details_types,\r\n"
					+ "                    \r\n"
					+ "                     (select sum(awarded_cost) as awarded_cost\r\n"
					+ "                    from contract c  \r\n"
					+ "					left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci  \r\n"
					+ "					left join contractor cr on c.contractor_id_fk = cr.contractor_id \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join user u on c.hod_user_id_fk = u.user_id\r\n"
					+ "					left join department hoddt on u.department_fk = hoddt.department\r\n"
					+ "					left join user us on c.dy_hod_user_id_fk = us.user_id\r\n"
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and contract_status_fk='Awarded' )   as awarded_cost,  \r\n"
					+ "                    \r\n"
					+ " ifnull((select group_concat(distinct structure_type) as Scope from activities_scurve s \r\n"
					+ "left join contract c on c.contract_id=s.contract_id \r\n"
					+ "where c.contract_id is not null and work_id='"+obj.getWork_id_fk()+"' and contract_status_fk='Awarded' and department=hoddt.department_name),'') as scope_of_contract\r\n"
					+ "\r\n"
					+ "                    \r\n"
					+ "                    from contract c  \r\n"
					+ "					left join work w on c.work_id_fk = w.work_id COLLATE utf8mb4_unicode_ci  \r\n"
					+ "					left join contractor cr on c.contractor_id_fk = cr.contractor_id \r\n"
					+ "					left join project p on w.project_id_fk = p.project_id \r\n"
					+ "					left join user u on c.hod_user_id_fk = u.user_id\r\n"
					+ "					left join department hoddt on u.department_fk = hoddt.department\r\n"
					+ "					left join user us on c.dy_hod_user_id_fk = us.user_id\r\n"
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"'\r\n"
					+ "                    \r\n"
					+ "                    group by hoddt.department_name  ";
			
			objsList = jdbcTemplate.query( qry,new BeanPropertyRowMapper<Contract>(Contract.class));
			
			for (Contract cObj : objsList) {
				String awarded_cost = cObj.getAwarded_cost();
				String awarded_cost_value = "";
				if(!StringUtils.isEmpty(awarded_cost)) {
					double val = (Double.parseDouble(awarded_cost))/10000000;
					awarded_cost_value = numberFormatter.format(val);
				}
				cObj.setAwarded_cost(awarded_cost_value);

				String estimated_cost = cObj.getEstimated_cost();
				String estimated_cost_value = "";
				if(!StringUtils.isEmpty(estimated_cost)) {
					double val = (Double.parseDouble(estimated_cost))/10000000;
					estimated_cost_value = numberFormatter.format(val);
				}
				cObj.setEstimated_cost(estimated_cost_value);
			}			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

}

