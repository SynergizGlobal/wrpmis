package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.BudgetDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

@Repository
public class BudgetDaoImpl implements BudgetDao {


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Budget> getFinancialYearsList() throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select financial_year_fk from budget b GROUP BY financial_year_fk ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> getProjectsList() throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select project_id_fk,work_id_fk,p.project_name from budget b " + 
					"LEFT JOIN work w on w.work_id = b.work_id_fk " + 
					"Left JOIN project p on w.project_id_fk = p.project_id "+
					 "GROUP BY project_id_fk ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> getWorksList() throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select work_id_fk,w.work_name from budget b "
					+ "LEFT JOIN work w  on b.work_id_fk = w.work_id "
					+ "GROUP BY work_id_fk ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	@Override
	public List<Budget> budgetList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select budget_id,work_id_fk,w.work_name,p.project_id,p.project_name,financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, " + 
					"cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " + 
					",b.remarks from budget b " + 
					"left join work w on b.work_id_fk = w.work_id " + 
					"left join financial_year f on b.financial_year_fk = f.financial_year " + 
					"left join project p on  w.project_id_fk = p.project_id where budget_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				qry = qry + " and financial_year_fk = ?";
				arrSize++;
			}	
			Object[] pValues = new Object[arrSize];
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				pValues[i++] = obj.getFinancial_year_fk();
			}
			
		 objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));

		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public Budget getBudget(Budget obj)throws Exception{
		Budget budget = null;
		try {
			String qry = "select budget_id, work_id_fk,w.work_name,p.project_name, b.financial_year_fk,w.project_id_fk, cast(budget_estimate as CHAR) as budget_estimate, cast(august_review_estimate as CHAR) as august_review_estimate, cast(revised_estimate as CHAR) as revised_estimate, cast(final_estimate as CHAR) as final_estimate," + 
					"cast(budget_grant as CHAR) as budget_grant, cast(revised_grant as CHAR) as revised_grant, cast(final_grant as CHAR) as final_grant, b.remarks, b.attachment from budget b " + 
					"left join work w on b.work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id " + 
					"left join financial_year f on b.financial_year_fk = f.financial_year where budget_id is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBudget_id())) {
				qry = qry + " and budget_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBudget_id())) {
				pValues[i++] = obj.getBudget_id();
			}
			budget = (Budget)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Budget>(Budget.class));	
				
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return budget;
	}

	@Override
	public boolean addBudget(Budget budget) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			String insertQry = "INSERT INTO budget"
					+ "(work_id_fk, financial_year_fk, budget_estimate, august_review_estimate, revised_estimate, "
					+ "final_estimate, budget_grant, revised_grant, final_grant, attachment, remarks)"
					+ "VALUES"
					+ "(:work_id_fk,:financial_year_fk,:budget_estimate,:august_review_estimate,:revised_estimate,"
					+ ":final_estimate,:budget_grant,:revised_grant,:final_grant,:attachment,:remarks)";
			
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(budget);		 
			int count = namedParamJdbcTemplate.update(insertQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateBudget(Budget budget) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String updateQry = "UPDATE budget set "
					+ "work_id_fk= :work_id_fk, financial_year_fk= :financial_year_fk, budget_estimate= :budget_estimate, "
					+ "august_review_estimate= :august_review_estimate, revised_estimate= :revised_estimate, final_estimate= :final_estimate,budget_grant= :budget_grant, "
					+ "revised_grant= :revised_grant, final_grant= :final_grant, attachment = :attachment, remarks= :remarks "
					+ "where budget_id= :budget_id";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(budget);		 
			int count = namedParamJdbcTemplate.update(updateQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteBudget(Budget obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE FROM budget where budget_id= :budget_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(deleteQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public List<Budget> getFinancialYearList() throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select financial_year from financial_year ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> getProjectList() throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select project_id,project_name from project ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}
	
	
}
