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
import com.synergizglobal.pmis.model.Contractor;
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
			qry = qry + "GROUP BY work_id_fk,w.work_name,w.work_short_name ";
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
			qry = qry + " GROUP BY project_id_fk,p.project_name ";
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
			String qry = "SELECT distinct w.work_short_name,p.project_name,sanctioned_year_fk,sanctioned_estimated_cost,latest_revised_cost, "
					+ "sum(e.gross_work_done*e.gross_work_done_units) as cumulative_total_expenditure, "
					+ " "
					+ "ISNULL((select sum(e1.gross_work_done*e1.gross_work_done_units) from expenditure e1 "
					+ "					where contract_id_fk=c.contract_id and work_id='"+obj.getWork_id_fk()+"'  "
					+ "					and voucher_type=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(YEAR(CONVERT(date, getdate()))+1,3,2)) ELSE concat(YEAR(CONVERT(date, getdate()))-1,'-', SUBSTRING(YEAR(CONVERT(date, getdate())),3,2)) END) "
					+ "					) "
					+ "					 ,0) as expenditure_current_fy, "
					+ "                      "
					+ "                      "
					+ " ISNULL((select sum(e1.gross_work_done*e1.gross_work_done_units) from expenditure e1 "
					+ "					where contract_id_fk=c.contract_id and work_id='"+obj.getWork_id_fk()+"' and "
					+ "					date<=(SELECT FORMAT(str_to_date(concat(YEAR(CONVERT(date, getdate()))-1,'-03-31'), '%Y-%m-%d'), '%Y-%m-%d')) "
					+ "					) "
					+ "					 ,0) as expenditure_end_of_fy , "
					+ "                      "
					+ "   ISNULL((select budget_estimate from budget e1 "
					+ "					where work_id_fk='"+obj.getWork_id_fk()+"' and "
					+ "					financial_year_fk=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(YEAR(CONVERT(date, getdate()))+1,3,2)) ELSE concat(YEAR(CONVERT(date, getdate()))-1,'-', SUBSTRING(YEAR(CONVERT(date, getdate())),3,2)) END) "
					+ "					) "
					+ "					 ,0) as budget_grant_current_fy ,ISNULL(target_doc,'') as target_completion_date , "
					+ "                      "
					+ "                      "
					+ "  ISNULL(round((select sum(work_per) from activities_scurve s  "
					+ "left join contract c on c.contract_id=s.contract_id  "
					+ "where c.contract_id is not null and work_id='"+obj.getWork_id_fk()+"' ),2),'')  as physical_progress,ISNULL(round((select sum(e.gross_work_done*e.gross_work_done_units)/sum(ISNULL(revised_cost, ISNULL(awarded_cost,ISNULL(estimated_cost,0)))) from contract_details c where work_id='"+obj.getWork_id_fk()+"'),2),'') AS financial_progress "
					+ " from contract c  "
					+ "										LEFT JOIN work w on c.work_id_fk = w.work_id  "
					+ "					                  left join work_yearly_sanction wy on wy.work_id_fk=w.work_id "
					+ "										LEFT JOIN project p on w.project_id_fk = p.project_id  "
					+ "					                  left join expenditure e on e.contract_id_fk= c.contract_id and e.voucher_type=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(YEAR(CONVERT(date, getdate()))+1,3,2)) ELSE concat(YEAR(CONVERT(date, getdate()))-1,'-', SUBSTRING(YEAR(CONVERT(date, getdate())),3,2)) END) "
					+ "					                  LEFT JOIN budget b on b.work_id_fk = w.work_id and b.financial_year_fk=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(YEAR(CONVERT(date, getdate()))+1,3,2)) ELSE concat(YEAR(CONVERT(date, getdate()))-1,'-', SUBSTRING(YEAR(CONVERT(date, getdate())),3,2)) END) "
					+ "					                   "
					+ "										where c.work_id_fk is not null and c.work_id_fk <> '' and work_id='"+obj.getWork_id_fk()+"' ";

		
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
			String qry = "select FORMAT(sd.created_date,'dd-MM-yyyy') as Date,sd.attachment,s.structure_type_fk as strip_chart_type_fk,structure as name "
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
			String qry = "SELECT structure_type as strip_chart_type_fk,ISNULL(round(sum(structure_type_per)*100,2),0) as structure_type_per,ISNULL((select actual_completion_date from structure where structure_type_fk=structure_type and actual_completion_date is not null offset 0 rows  fetch next 1 rows only),'') as actual_completion_date FROM pmis.activities_scurve  "
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
			String qry = "select distinct c.contract_id,c.department as department_id_fk,contract_short_name,ISNULL(loa_date,'') as loa_date,ISNULL(actual_completion_date,'') as actual_completion_date,ISNULL(round(SUM(contract_per),2),'') AS actual_physical_progress,ISNULL(estimated_cost,'') as estimated_cost,ISNULL(awarded_cost,'') as awarded_cost,ISNULL(remarks,'') as remarks from contract_details c "
					+ "left join activities_scurve b on b.contract_id=c.contract_id where 0=0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id = ?";
				arrSize++;
			}
			qry=qry+" ORDER BY case when c.department='Engineering' then 1 " + 
					"when c.department='Electrical' then 2 when c.department='Signalling & Telecom' then 3  end asc";
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
			String qry = "select concat(li.category_fk,' Land') as type_of_land,ISNULL(round(sum(area_to_be_acquired),2),'') as area_to_be_acquired,ISNULL(round(sum(area_acquired),2),'') as area_acquired, "
					+ "ISNULL(round(sum(area_to_be_acquired)-sum(area_acquired),2),'') as balance, "
					+ "ISNULL(sum(jm_fee_amount*jm_fee_amount_units),'') AS jm_fee_amount, "
					+ " "
					+ "ISNULL(round(case when li.category_fk='Government' then (select sum(ISNULL(amount_paid,0)* ISNULL(amount_paid_units,0)) from la_land_identification li "
					+ "left join la_government_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Government') "
					+ "when li.category_fk='Private' then (select sum(ISNULL(total_compensation,0))  from la_land_identification li "
					+ "left join la_private_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Private') else 0 end,2),0) "
					+ " "
					+ " as payment_amount_units_railway "
					+ " "
					+ " "
					+ "					 from la_land_identification li    "
					+ "					left join work w on li.work_id_fk = w.work_id  "
					+ "					 left join land_executives le on li.work_id_fk = le.work_id_fk   "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					left join la_sub_category sc on li.la_sub_category_fk = sc.id  "
					+ "					left join la_category c on sc.la_category_fk = c.la_category  "
					+ "					where la_id is not null  ";
			int arrSize = 2;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			qry=qry+" group by li.category_fk union all ";
			
			qry = qry+"select 'Total',ISNULL(round(sum(area_to_be_acquired),2),'') as area_to_be_acquired,ISNULL(round(sum(area_acquired),2),'') as area_acquired, "
					+ "ISNULL(round(sum(area_to_be_acquired)-sum(area_acquired),2),'') as balance, "
					+ "ISNULL(sum(jm_fee_amount*jm_fee_amount_units),'') AS jm_fee_amount,ISNULL(round(case when li.category_fk='Government' then (select sum(ISNULL(amount_paid,0)* ISNULL(amount_paid_units,0)) from la_land_identification li "
					+ "left join la_government_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Government') "
					+ "when li.category_fk='Private' then (select sum(ISNULL(total_compensation,0))  from la_land_identification li "
					+ "left join la_private_land_acquisition lg on lg.la_id_fk=li.la_id where work_id_fk=? and category_fk='Private') else 0 end,2),0) as payment_amount_units_railway "
					+ " "
					+ " "
					+ "					 from la_land_identification li    "
					+ "					left join work w on li.work_id_fk = w.work_id  "
					+ "					 left join land_executives le on li.work_id_fk = le.work_id_fk   "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					left join la_sub_category sc on li.la_sub_category_fk = sc.id  "
					+ "					left join la_category c on sc.la_category_fk = c.la_category  "
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
			String qry = "select * from (select work_name ,sum(awarded_cost) as awarded_cost,sum(estimated_cost) as estimated_cost,sum(ISNULL(total,0)) as total,sum(contract_details_types) as contract_details_types from (select work_name,hoddt.department_name,round(sum(ISNULL(awarded_cost,0)*ISNULL(awarded_cost_units,0))/10000000,2) as awarded_cost, "
					+ "  "
					+ " round(sum(ISNULL(estimated_cost,0)*ISNULL(estimated_cost_units,0))/10000000,2) as estimated_cost , "
					+ "  "
					+ "  "
					+ "       (select count(*) "
					+ "  "
					+ " from contract c   "
					+ "					left join work w on c.work_id_fk = w.work_id    "
					+ "					left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "					left join department hoddt1 on u.department_fk = hoddt1.department "
					+ "					LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id "
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and hoddt1.department_name=hoddt.department_name "
					+ "                     "
					+ "                    and contract_name <> 'Miscellaneous' and  contract_name <> 'Land') as total, "
					+ "                     "
					+ "       (select count(*) "
					+ "  "
					+ " from contract c   "
					+ "					left join work w on c.work_id_fk = w.work_id    "
					+ "					left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "					left join department hoddt2 on u.department_fk = hoddt2.department "
					+ "					LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id "
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and hoddt2.department_name=hoddt.department_name "
					+ "                     "
					+ "                    and contract_name <> 'Miscellaneous' and  contract_name <> 'Land'                     "
					+ "                     "
					+ "                    and contract_status_fk<>'Not Awarded') as contract_details_types "
					+ "  "
					+ " from contract c   "
					+ "					left join work w on c.work_id_fk = w.work_id    "
					+ "					left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "					left join department hoddt on u.department_fk = hoddt.department "
					+ "					LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id "
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"'  "
					+ "                     "
					+ "                    and contract_name <> 'Miscellaneous' and  contract_name <> 'Land' "
					+ "                     "
					+ "                  group by hoddt.department_name                     "
					+ "                    ) as a "
					+ "  "
					+ "  "
					+ "  "
					+ "  "
					+ "  "
					+ " UNION ALL  "
					+ "  "
					+ " select hoddt.department_name,round(sum(ISNULL(awarded_cost,0)*ISNULL(awarded_cost_units,0))/10000000,2) as awarded_cost, "
					+ "  "
					+ " round(sum(ISNULL(estimated_cost,0)*ISNULL(estimated_cost_units,0))/10000000,2) as estimated_cost , "
					+ "  "
					+ "  "
					+ "       (select count(*) "
					+ "  "
					+ " from contract c   "
					+ "					left join work w on c.work_id_fk = w.work_id    "
					+ "					left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "					left join department hoddt1 on u.department_fk = hoddt1.department "
					+ "					LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id "
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and hoddt1.department_name=hoddt.department_name "
					+ "                     "
					+ "                    and contract_name <> 'Miscellaneous' and  contract_name <> 'Land') as total, "
					+ "                     "
					+ "       (select count(*) "
					+ "  "
					+ " from contract c   "
					+ "					left join work w on c.work_id_fk = w.work_id    "
					+ "					left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "					left join department hoddt2 on u.department_fk = hoddt2.department "
					+ "					LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id "
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"' and hoddt2.department_name=hoddt.department_name "
					+ "                     "
					+ "                    and contract_name <> 'Miscellaneous' and  contract_name <> 'Land'                     "
					+ "                     "
					+ "                    and contract_status_fk<>'Not Awarded') as contract_details_types "
					+ "  "
					+ " from contract c   "
					+ "					left join work w on c.work_id_fk = w.work_id    "
					+ "					left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "					left join project p on w.project_id_fk = p.project_id  "
					+ "					LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "					left join department hoddt on u.department_fk = hoddt.department "
					+ "					LEFT JOIN [user] us on c.dy_hod_user_id_fk = us.user_id "
					+ "					where contract_id is not null and w.work_id='"+obj.getWork_id_fk()+"'  "
					+ "                     "
					+ "                    and contract_name <> 'Miscellaneous' and  contract_name <> 'Land' "
					+ "                     "
					+ "                                       "
					+ "                    group by hoddt.department_name) as a  ORDER BY case when work_name='Engineering' then 1 " + 
					"when work_name='Electrical' then 2 when work_name='Signalling & Telecom' then 3  end asc";
			
			objsList = jdbcTemplate.query( qry,new BeanPropertyRowMapper<Contract>(Contract.class));
			
			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Contract> getFinanceReportContracts(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry ="select work_short_name as contract_name,'' as value,'' as actual_physical_progress,'' as actual_financial_progress from work where work_id='"+obj.getWork_id_fk()+"' "
					+ "union all "
					+ "select 'Civil Works' as contract_name,'' as value,'' as actual_physical_progress,'' as actual_financial_progress from work where work_id='"+obj.getWork_id_fk()+"' "
					+ "union all "
					+ "select c.contract_short_name as contract_name, "
					+ "case when cr.revised_amount is null then round(awarded_cost*awarded_cost_units/1000000,2) else round(revised_amount*revised_amount_units/1000000,2) end as value, "
					+ "round(sum(contract_per)*100,2) as actual_physical_progress,'' as actual_financial_progress from contract c "
					+ "left join activities_scurve a on c.contract_id=a.contract_id  "
					+ "left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "left join department hoddt on u.department_fk = hoddt.department "
					+ "and date>=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())),'-04-01') ELSE concat(YEAR(CONVERT(date, getdate()))-1,'-04-01') END) "
					+ "and date<=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate()))+1,'-03-31') else concat(YEAR(CONVERT(date, getdate())),'-03-31') end ) "
					+ "where c.status='Open' and c.contract_name not like '%Miscellaneous-%' and  c.contract_name not like '%Land-%' "
					+ "and category='planned' and c.work_id_fk='"+obj.getWork_id_fk()+"' and hoddt.department_name='Engineering' "
					+ "group by c.contract_id "
					+ " "
					+ "union all "
					+ "select 'Electrical and Signalling works' as contract_name,'' as value,'' as actual_physical_progress,'' as actual_financial_progress from work where work_id='"+obj.getWork_id_fk()+"' "
					+ "union all "
					+ " "
					+ "select c.contract_short_name as contract_name, "
					+ "case when cr.revised_amount is null then round(awarded_cost*awarded_cost_units/1000000,2) else round(revised_amount*revised_amount_units/1000000,2) end as value, "
					+ "round(sum(contract_per)*100,2) as actual_physical_progress,'' as actual_financial_progress from contract c "
					+ "left join activities_scurve a on c.contract_id=a.contract_id  "
					+ "left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes' "
					+ "LEFT JOIN [user] u on c.hod_user_id_fk = u.user_id "
					+ "left join department hoddt on u.department_fk = hoddt.department "
					+ "and date>=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())),'-04-01') ELSE concat(YEAR(CONVERT(date, getdate()))-1,'-04-01') END) "
					+ "and date<=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate()))+1,'-03-31') else concat(YEAR(CONVERT(date, getdate())),'-03-31') end ) "
					+ "where c.status='Open' and c.contract_name not like '%Miscellaneous-%' and  c.contract_name not like '%Land-%' "
					+ "and category='planned' and c.work_id_fk='"+obj.getWork_id_fk()+"' and hoddt.department_name in('Engineering','Signalling & Telecom') "
					+ "group by c.contract_id";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public ResultSet getFinanceSummaryReport() throws Exception {
		
		Connection connection = null;
		java.sql.CallableStatement statement = null;
		java.sql.CallableStatement statementChild = null;
		
        connection = dataSource.getConnection();
		ResultSet rs = null;
		ResultSet finalresult=null;
		try{
				connection = dataSource.getConnection();	
				String qry ="select ROW_NUMBER() OVER (\r\n" + 
						"            ORDER BY (select case when [Name Of Work]='Central Railway FOB' then 'desc' else 'asc' end)) as 'S. No',[Name Of Work],cast(sum([Sanctioned Cost (Cr)]) as decimal(10,2)) as [Sanctioned Cost (Cr)], " + 
						 
						"cast(sum([Expenditure Up to Last FY (Cr)]) as decimal(10,2)) as [Expenditure Up to Last FY (Cr)],	cast([BG/BE this FY (Cr)] as decimal(10,2)) as [BG/BE this FY (Cr)],	cast([RG/RE this FY (Cr)] as decimal(10,2)) as [RG/RE this FY (Cr)], cast([FG/FE this FY (Cr)] as decimal(10,2)) as [FG/FE this FY (Cr)], " + 
						 
						 
						"cast(sum([Expenditure this FY (Cr)]) as decimal(10,2)) as [Expenditure this FY (Cr)],cast(sum([Expenditure till date (Cr)]) as decimal(10,2)) as [Expenditure till date (Cr)] from " + 
						 
						 
						 
						 
						 
						 
						"(select distinct project_name as 'Name Of Work',sum(isnull(sanctioned_completion_cost,0))/count(*) as 'Sanctioned Cost (Cr)',sum(isnull(e.[Exp till Last FY],0))/10000000/count(distinct isnull(financial_year_fk,1)) as 'Expenditure Up to Last FY (Cr)', " + 
						 
						 
						"( select case when sum(isnull(budget_grant,0))=0 then sum(isnull(budget_estimate,0)) else sum(isnull(budget_grant,0)) end as budget_grant " + 
						"from budget b2 left join work w2 on w2.work_id=b2.work_id_fk " + 
						"where w2.project_id_fk=p.project_id and financial_year_fk= " + 
						"(select concat(Year(GETDATE()),'-',Right(Year(GETDATE())+1,2))) " + 
						") " + 
						 
						 
						 
						"as 'BG/BE this FY (Cr)', " + 
						 
						"(select case when sum(isnull(revised_grant,0))=0 then sum(isnull(revised_estimate,0)) else sum(isnull(revised_grant,0)) end as revised_grant " + 
						"from budget b3 left join work w3 on w3.work_id=b3.work_id_fk " + 
						"where w3.project_id_fk=p.project_id and financial_year_fk= " + 
						"(select concat(Year(GETDATE()),'-',Right(Year(GETDATE())+1,2))) " + 
						 
						 
						 
						") " + 
						 
						"as 'RG/RE this FY (Cr)',(select case when sum(isnull(final_grant,0))=0 then sum(isnull(final_estimate,0)) else sum(isnull(final_grant,0)) end as final_grant " + 
						"from budget b4 left join work w4 on w4.work_id=b4.work_id_fk " + 
						"where w4.project_id_fk=p.project_id and financial_year_fk= " + 
						"(select concat(Year(GETDATE()),'-',Right(Year(GETDATE())+1,2))) " + 
						 
						 
						 
						") as 'FG/FE this FY (Cr)', " + 
						 
						"sum(isnull(e.[Exp This Year],0))/10000000/count(distinct isnull(financial_year_fk,1)) as 'Expenditure this FY (Cr)', " + 
						 
						"sum(isnull(e.[Exp till date],0))/10000000/count(distinct isnull(financial_year_fk,1)) " + 
						 
						"as 'Expenditure till date (Cr)' " + 
						 
						 
						" from project p " + 
						"left outer join work w on w.project_id_fk=p.project_id " + 
						"left outer join Contract_expenditure_details e on e.work_id=w.work_id " + 
						"left outer join budget b on  b.work_id_fk=w.work_id " + 
						 
						 
						 
						"group by project_name,project_id,w.work_id) as a " + 
						 
						"group by [Name Of Work],a.[BG/BE this FY (Cr)],a.[FG/FE this FY (Cr)],a.[RG/RE this FY (Cr)] " + 
						 
						 
						 
						" order by (select case when [Name Of Work]='Central Railway FOB' then 'desc' else 'asc' end) ";
				statement = connection.prepareCall(qry);
				rs=statement.executeQuery();


		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);			
		}
        return rs;		

	}
	@Override
	public List<Contract> getFinanceSummaryReportByProjectWorks() throws Exception {
		Connection connection = null;
		List<Contract> objsList = null;
		try{
				connection = dataSource.getConnection();	
				String qry ="select project_name,[Name Of Work] as work_short_name,cast(sum([Sanctioned Cost (Cr)]) as decimal(10,2)) as awarded_cost,cast(sum([Expenditure Up to Last FY (Cr)]) as decimal(10,2)) as cumulative_expenditure,	cast([BG/BE this FY (Cr)] as decimal(10,2)) as bg_value,	cast([RG/RE this FY (Cr)] as decimal(10,2)) as value,cast([FG/FE this FY (Cr)] as decimal(10,2)) as insurance_value,cast(sum([Expenditure this FY (Cr)]) as decimal(10,2)) as expenditure,cast(sum([Expenditure till date (Cr)]) as decimal(10,2)) as estimated_cost " + 
						" from " + 
								
								
								
								
								
								
								"(select distinct work_short_name as 'Name Of Work',project_name,sum(isnull(sanctioned_completion_cost,0))/count(*) as 'Sanctioned Cost (Cr)',sum(isnull(e.[Exp till Last FY],0))/10000000/count(distinct isnull(financial_year_fk,1)) as 'Expenditure Up to Last FY (Cr)', " + 
								
								
								"( select case when sum(isnull(budget_grant,0))=0 then sum(isnull(budget_estimate,0)) else sum(isnull(budget_grant,0)) end as budget_grant " + 
								"from budget b2 left join work w2 on w2.work_id=b2.work_id_fk " + 
								"where w2.project_id_fk=p.project_id and w2.work_id=w.work_id and financial_year_fk= " + 
								"(select concat(Year(GETDATE()),'-',Right(Year(GETDATE())+1,2))) " + 
								") " + 
								
								
								
								"as 'BG/BE this FY (Cr)', " + 
								
								"(select case when sum(isnull(revised_grant,0))=0 then sum(isnull(revised_estimate,0)) else sum(isnull(revised_grant,0)) end as revised_grant " + 
								"from budget b3 left join work w3 on w3.work_id=b3.work_id_fk " + 
								"where w3.project_id_fk=p.project_id and w3.work_id=w.work_id and financial_year_fk= " + 
								"(select concat(Year(GETDATE()),'-',Right(Year(GETDATE())+1,2))) " + 
								
								
								
								") " + 
								
								"as 'RG/RE this FY (Cr)',(select case when sum(isnull(final_grant,0))=0 then sum(isnull(final_estimate,0)) else sum(isnull(final_grant,0)) end as final_grant " + 
								"from budget b4 left join work w4 on w4.work_id=b4.work_id_fk " + 
								"where w4.project_id_fk=p.project_id and w4.work_id=w.work_id and financial_year_fk= " + 
								"(select concat(Year(GETDATE()),'-',Right(Year(GETDATE())+1,2))) " +  
								
								
								
								") as 'FG/FE this FY (Cr)', " + 
								
								"sum(isnull(e.[Exp This Year],0))/10000000/count(distinct isnull(financial_year_fk,1)) as 'Expenditure this FY (Cr)', " + 
								
								"sum(isnull(e.[Exp till date],0))/10000000/count(distinct isnull(financial_year_fk,1)) " + 
								
								"as 'Expenditure till date (Cr)' " + 
								
								
								" from project p " + 
								"left outer join work w on w.project_id_fk=p.project_id " + 
								"left outer join Contract_expenditure_details e on e.work_id=w.work_id " + 
								"left outer join budget b on  b.work_id_fk=w.work_id " + 
								
								
								
								"group by work_short_name,project_id,project_name,w.work_id) as a " + 
								
								"group by [Name Of Work],a.project_name,a.[BG/BE this FY (Cr)],a.[FG/FE this FY (Cr)],a.[RG/RE this FY (Cr)] " + 
								
								
								
								" order by (select case when [Name Of Work]='Central Railway FOB' then 'desc' else 'asc' end)";	
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Contract>(Contract.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);			
		}
        return objsList;	
	}

}

