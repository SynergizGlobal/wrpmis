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
			String qry = "select distinct work_id,work_id_fk,work_short_name from vw_ProjectOverviewReport where 0=0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id= ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department = ? ";
				arrSize++;
			}
			/*if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and (hod_user_id_fk = ? or dy_hod_user_id_fk = ? or "
						+ "contract_id in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk))";
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
	public List<Contract> getDepartmentFilterListInPOR(Contract obj) throws Exception {
		List<Contract> objsList = null;
		try {
			String qry = "select distinct department as department_fk,department_name from vw_ProjectOverviewReport where 0=0 ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " department = ? ";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
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
			String qry = "select * from vw_ProjectOverviewReport where 0=0 " ; 

			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department = ?";
				arrSize++;
			}
	
			qry=qry+"  ORDER BY case when department_name='Engineering' then 1 when department_name='Electrical' then 2 when department_name='Signalling & Telecom' then 3 when department_name='Planning' then 4 end asc ";
			
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
				
				String estimated_cost = cObj.getEstimated_cost();
				String estimated_cost_value = "0";
				if(!StringUtils.isEmpty(estimated_cost)) {
					double val = (Double.parseDouble(estimated_cost))/10000000;
					estimated_cost_value = numberFormatter.format(val);
				}
				cObj.setEstimated_cost(estimated_cost_value);				
				
				String awarded_cost = cObj.getAwarded_cost();
				String awarded_cost_value = "0";
				if(!StringUtils.isEmpty(awarded_cost)) {
					double val = (Double.parseDouble(awarded_cost))/10000000;
					awarded_cost_value = numberFormatter.format(val);
				}
				cObj.setAwarded_cost(awarded_cost_value);
				
				String cumulative_expenditure = cObj.getCumulative_expenditure();
				String cumulative_expenditure_value = "0";
				if(!StringUtils.isEmpty(cumulative_expenditure)) {
					double val = (Double.parseDouble(cumulative_expenditure))/10000000;
					cumulative_expenditure_value = numberFormatter.format(val);
				}
				cObj.setCumulative_expenditure(cumulative_expenditure_value);				
				
				String actual_financial_progress = "0";
				if(!StringUtils.isEmpty(cObj.getActual_financial_progress())) {
					double val = (Double.parseDouble(cObj.getActual_financial_progress()))/10000000;
					actual_financial_progress = numberFormatter.format(val);
				}
				cObj.setActual_financial_progress(actual_financial_progress);
				
				String last_financial_progress = "0";
				if(!StringUtils.isEmpty(cObj.getLast_financial_progress())) {
					double val = (Double.parseDouble(cObj.getLast_financial_progress()))/10000000;
					last_financial_progress = numberFormatter.format(val);
				}
				cObj.setLast_financial_progress(last_financial_progress);				
				
				String actual_physical_progress = "0";
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

