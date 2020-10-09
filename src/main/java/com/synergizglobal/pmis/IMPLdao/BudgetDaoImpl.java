package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.BudgetDao;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.Work;

@Repository
public class BudgetDaoImpl implements BudgetDao {


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<Work> getFinancialYearList() throws Exception {
		List<Work> objsList = null;
		try {
			String qry ="select financial_year from financial_year ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Work>(Work.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Project> getProjectsList() throws Exception {
		List<Project> objsList = null;
		try {
			String qry ="select project_id as project_id_fk,project_name from project ";
				objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Project>(Project.class));	
		}catch(Exception e){ 
		throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> budgetList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select budget_id,work_id_fk,w.work_name,p.project_id,p.project_name,financial_year_fk,budget_grant, " + 
					"revised_estimate,revised_grant,final_estimate,final_grant " + 
					"from budget b " + 
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

}
