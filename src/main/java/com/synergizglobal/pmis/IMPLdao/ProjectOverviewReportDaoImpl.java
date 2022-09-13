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
import com.synergizglobal.pmis.Idao.ProjectOverviewReportDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.Contract;
@Repository
public class ProjectOverviewReportDaoImpl implements ProjectOverviewReportDao{
	
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
					"left join department d on d.department=c.department_fk "+
					"where work_id_fk is not null and work_id_fk <> ''   ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and d.department = ? ";
				arrSize++;
			}
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? or "
						+ "contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
				arrSize++;
				arrSize++;
				arrSize++;
			}*/
			qry = qry + " GROUP BY work_id_fk,w.work_name,w.work_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}*/
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Contract> getDepartmentFilterListInPOR(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "select distinct dt.department as department_fk,dt.department_name" + 
					"					from contract c   " + 
					"					left join work w on c.work_id_fk = w.work_id    " + 
					"					left join contractor cr on c.contractor_id_fk = cr.contractor_id   " + 
					"					left join project p on w.project_id_fk = p.project_id   " + 
					"					left join [user] u on c.hod_user_id_fk = u.user_id " + 
					"					left join department hoddt on u.department_fk = hoddt.department " + 
					"					left join [user] us on c.dy_hod_user_id_fk = us.user_id " + 
					"					left join contract_executive ce on c.contract_id = ce.contract_id_fk " + 
					"					left join department dt on ce.department_id_fk = dt.department " + 
					"					where contract_id is not null and dt.department_name is not null ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and dt.department = ? ";
				arrSize++;
			}
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? or "
						+ "contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk)) ";
				arrSize++;
				arrSize++;
				arrSize++;
			}*/
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}*/
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	@Override
	public List<Contract> getProjectOverviewReportList(Contract obj) throws Exception {
		List<Contract> objsList = null;
		NumberFormat numberFormatter = new DecimalFormat("#0.00");
		try {
			String qry = "select * from (SELECT distinct contract_short_name,work_short_name, " + 
					"					                    case when (case when cr.revised_amount is null then awarded_cost*awarded_cost_units else revised_amount*revised_amount_units end) is null then (estimated_cost*estimated_cost_units) else (case when cr.revised_amount is null then awarded_cost*awarded_cost_units else revised_amount*revised_amount_units end) end as awarded_cost, + " + 
					"					                    SUM((e.gross_work_done * e.gross_work_done_units)) cumulative_expenditure,w.work_id,  " + 
					"					                    ISNULL((select sum(e1.gross_work_done*e1.gross_work_done_units) from expenditure e1  " + 
					"					where contract_id_fk=c.contract_id  " + 
					"					and voucher_type=(SELECT CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(cast(YEAR(CONVERT(date, getdate()))+1 as varchar),3,2)) ELSE concat(cast(YEAR(CONVERT(date, getdate()))-1 as varchar),'-', SUBSTRING(cast(YEAR(CONVERT(date, getdate())) as varchar),3,2)) END)  " + 
					"					)  " + 
					"					 ,0) as actual_financial_progress,d1.department as department,  " + 
					"					                    ISNULL((case when (case when cr.revised_amount is null then awarded_cost*awarded_cost_units else revised_amount*revised_amount_units end) is null then (estimated_cost*estimated_cost_units) else (case when cr.revised_amount is null then awarded_cost*awarded_cost_units else revised_amount*revised_amount_units end) end),0)-ISNULL((SUM((e.gross_work_done * e.gross_work_done_units))),0) AS actual_physical_progress,case when c.contract_id like '%null%' and (contract_name like '%Miscellaneous-%' or contract_name like '%Land-%') then 'Non Bank Funds' else d1.department_name end department_name   " + 
					"										,contract_status_fk from contract c   " + 
					"										LEFT JOIN work w on c.work_id_fk = w.work_id  " + 
					"										LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"						LEFT JOIN [user] u ON c.hod_user_id_fk = u.user_id 	left join department d1 on d1.department=u.department_fk                     left join contract_revision cr on cr.contract_id_fk = c.contract_id and cr.revision_amounts_status = 'Yes'  " + 
					"					                    left join expenditure e on e.contract_id_fk= c.contract_id  " + 
					"					LEFT join money_unit mu1 ON c.estimated_cost_units = mu1.value LEFT join money_unit mu2 ON c.awarded_cost_units = mu2.value LEFT join money_unit mu3 ON c.completed_cost_units = mu3.value 	where work_id_fk is not null and work_id_fk <> ''     " + 
					"					 " + 
					"					group by work_id,contract_id,contract_name,department,contract_status_fk,department_name,contract_short_name,work_short_name,revised_amount,awarded_cost," + 
					"					revised_amount_units,estimated_cost,estimated_cost_units,awarded_cost_units,revised_amount_units ) as a where 0=0 " ; 

			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department = ?";
				arrSize++;
			}
	
			qry=qry+"  ORDER BY case when department_name='Engineering' then 1 when department_name='Electrical' then 2 when department_name='Signalling & Telecom' then 3 end asc ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
		
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Contract>(Contract.class));
			
			for (Contract cObj : objsList) {
				String awarded_cost = cObj.getAwarded_cost();
				String awarded_cost_value = "";
				if(!StringUtils.isEmpty(awarded_cost)) {
					double val = (Double.parseDouble(awarded_cost))/10000000;
					awarded_cost_value = numberFormatter.format(val);
				}
				cObj.setAwarded_cost(awarded_cost_value);
				
				String cumulative_expenditure_value = "";
				if(!StringUtils.isEmpty(cObj.getCumulative_expenditure())) {
					double val = (Double.parseDouble(cObj.getCumulative_expenditure()))/10000000;
					cumulative_expenditure_value = numberFormatter.format(val);
				}
				cObj.setCumulative_expenditure(cumulative_expenditure_value);
				
				String actual_financial_progress = "";
				if(!StringUtils.isEmpty(cObj.getActual_financial_progress())) {
					double val = (Double.parseDouble(cObj.getActual_financial_progress()))/10000000;
					actual_financial_progress = numberFormatter.format(val);
				}
				cObj.setActual_financial_progress(actual_financial_progress);
				
				String actual_physical_progress = "";
				if(!StringUtils.isEmpty(cObj.getActual_physical_progress())) {
					double val = (Double.parseDouble(cObj.getActual_physical_progress()))/10000000;
					actual_physical_progress = numberFormatter.format(val);
				}
				cObj.setActual_physical_progress(actual_physical_progress);	
				
			}			

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

}

