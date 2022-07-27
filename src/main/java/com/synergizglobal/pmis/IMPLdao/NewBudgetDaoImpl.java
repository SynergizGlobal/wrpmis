package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.NewBudgetDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.SourceOfFund;
import com.synergizglobal.pmis.model.Training;

@Repository
public class NewBudgetDaoImpl implements NewBudgetDao {


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	FormsHistoryDao formsHistoryDao;
	
	@Override
	public List<Budget> budgetList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
		
			String qry ="select new_budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,max(b.financial_year_fk) as financial_year_fk,cast(new_budget_estimate as CHAR) as budget_estimate,cast(new_budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant, " 
					+ " from new_budget b "+
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE new_budget_id is not null and status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			qry = qry +" GROUP BY contract_id_fk";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}				
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public Budget getNewBudget(Budget obj)throws Exception{
		Budget budget = null;
		try {
			String qry = "select new_budget_id as budget_id, work_id_fk,b.contract_id_fk as contract_id,w.work_name,p.project_name,w.work_short_name,w.project_id_fk from new_budget b " + 
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "+ 
					"left join project p on w.project_id_fk = p.project_id where new_budget_id is not null" ; 
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBudget_id())) {
				qry = qry + " and new_budget_id = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getBudget_id())) {
				pValues[i++] = obj.getBudget_id();
			}
			budget = (Budget)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<Budget>(Budget.class));	
			if(!StringUtils.isEmpty(budget) && !StringUtils.isEmpty(budget.getBudget_id())) {
				List<Budget> objsList = null;
				String qryDetails = "select new_budget_id as budget_id,FORMAT(CONCAT(b.financial_year_fk,'-00'),'%Y-%m') AS financial_year_fk,cast(new_budget_estimate as CHAR) as budget_estimate, cast(revised_estimate as CHAR) as revised_estimate, cast(final_estimate as CHAR) as final_estimate,"+
						"cast(new_budget_grant as CHAR) as budget_grant, cast(revised_grant as CHAR) as revised_grant, cast(final_grant as CHAR) as final_grant "
						+ "from new_budget b " 
						+" where contract_id_fk = ?  ORDER BY financial_year_fk DESC";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {budget.getContract_id()}, new BeanPropertyRowMapper<Budget>(Budget.class));	
				budget.setBudget(objsList);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}
		return budget;
		
	}

	@Override
	public boolean addBudget(Budget budget) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		ResultSet rs = null;
		int[] insertCount = {};
		int z = 0;
		
		try {
			
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String insert_qry = "INSERT into  new_budget ( contract_id_fk, financial_year_fk, new_budget_estimate, revised_estimate, "
					+ "final_estimate, new_budget_grant, revised_grant, final_grant, status) "
					+"VALUES (?,?,?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry,Statement.RETURN_GENERATED_KEYS); 
			int arraySize = 0; 
			int r = 0;
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				budget.setFinancial_year_fks(CommonMethods.replaceEmptyByNullInSringArray(budget.getFinancial_year_fks()));
				if(arraySize < budget.getFinancial_year_fks().length) {
					arraySize = budget.getFinancial_year_fks().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getBudget_estimates()) && budget.getBudget_estimates().length > 0) {
				budget.setBudget_estimates(CommonMethods.replaceEmptyByNullInSringArray(budget.getBudget_estimates()));
				if(arraySize < budget.getBudget_estimates().length) {
					arraySize = budget.getBudget_estimates().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getRevised_estimates()) && budget.getRevised_estimates().length > 0) {
				budget.setRevised_estimates(CommonMethods.replaceEmptyByNullInSringArray(budget.getRevised_estimates()));
				if(arraySize < budget.getRevised_estimates().length) {
					arraySize = budget.getRevised_estimates().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getFinal_estimates()) && budget.getFinal_estimates().length > 0) {
				budget.setFinal_estimates(CommonMethods.replaceEmptyByNullInSringArray(budget.getFinal_estimates()));
				if(arraySize < budget.getFinal_estimates().length) {
					arraySize = budget.getFinal_estimates().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getBudget_grants()) && budget.getBudget_grants().length > 0) {
				budget.setBudget_grants(CommonMethods.replaceEmptyByNullInSringArray(budget.getBudget_grants()));
				if(arraySize < budget.getBudget_grants().length) {
					arraySize = budget.getBudget_grants().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getRevised_grants()) && budget.getRevised_grants().length > 0) {
				budget.setRevised_grants(CommonMethods.replaceEmptyByNullInSringArray(budget.getRevised_grants()));
				if(arraySize < budget.getRevised_grants().length) {
					arraySize = budget.getRevised_grants().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getFinal_grants()) && budget.getFinal_grants().length > 0) {
				budget.setFinal_grants(CommonMethods.replaceEmptyByNullInSringArray(budget.getFinal_grants()));
				if(arraySize < budget.getFinal_grants().length) {
					arraySize = budget.getFinal_grants().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( budget.getFinancial_year_fks().length > 0 && !StringUtils.isEmpty(budget.getFinancial_year_fks()[i])) {
					    insertStmt.setString(p++,(budget.getContract_id()));
					    insertStmt.setString(p++,(budget.getFinancial_year_fks().length > 0)?budget.getFinancial_year_fks()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_estimates().length > 0)?budget.getBudget_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_estimates().length > 0)?budget.getRevised_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_estimates().length > 0)?budget.getFinal_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_grants().length > 0)?budget.getBudget_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_grants().length > 0)?budget.getRevised_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_grants().length > 0)?budget.getFinal_grants()[i]:null);
					    insertStmt.setString(p++,CommonConstants.ACTIVE);
					   
					    insertStmt.addBatch();
				    }
				    insertCount = insertStmt.executeBatch();
			  }
				if(insertCount.length > 0) 
				{
					flag = true;
				}				
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(budget.getCreated_by_user_id_fk());
				formHistory.setUser(budget.getDesignation()+" - "+budget.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Add Budget");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Budget for "+budget.getContract_id() + " Created");
				formHistory.setWork_id_fk(budget.getContract_id());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
		}
		   con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return flag;
	}

	@Override
	public boolean updateBudget(Budget budget) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		ResultSet rs = null;
		int[] insertCount = {};
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			con.setAutoCommit(false);
			
			String inactiveQry = "DELETE from new_budget  where contract_id_fk = ?";		 
			stmt = con.prepareStatement(inactiveQry);
			stmt.setString(1,budget.getContract_id());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			
			
			String insert_qry = "INSERT into  new_budget ( contract_id_fk, financial_year_fk, new_budget_estimate, revised_estimate, "
					+ "final_estimate, new_budget_grant, revised_grant, final_grant, status) "
					+"VALUES (?,?,?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry,Statement.RETURN_GENERATED_KEYS); 
			int arraySize = 0; 
			int r = 0;
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				budget.setFinancial_year_fks(CommonMethods.replaceEmptyByNullInSringArray(budget.getFinancial_year_fks()));
				if(arraySize < budget.getFinancial_year_fks().length) {
					arraySize = budget.getFinancial_year_fks().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getBudget_estimates()) && budget.getBudget_estimates().length > 0) {
				budget.setBudget_estimates(CommonMethods.replaceEmptyByNullInSringArray(budget.getBudget_estimates()));
				if(arraySize < budget.getBudget_estimates().length) {
					arraySize = budget.getBudget_estimates().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getRevised_estimates()) && budget.getRevised_estimates().length > 0) {
				budget.setRevised_estimates(CommonMethods.replaceEmptyByNullInSringArray(budget.getRevised_estimates()));
				if(arraySize < budget.getRevised_estimates().length) {
					arraySize = budget.getRevised_estimates().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getFinal_estimates()) && budget.getFinal_estimates().length > 0) {
				budget.setFinal_estimates(CommonMethods.replaceEmptyByNullInSringArray(budget.getFinal_estimates()));
				if(arraySize < budget.getFinal_estimates().length) {
					arraySize = budget.getFinal_estimates().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getBudget_grants()) && budget.getBudget_grants().length > 0) {
				budget.setBudget_grants(CommonMethods.replaceEmptyByNullInSringArray(budget.getBudget_grants()));
				if(arraySize < budget.getBudget_grants().length) {
					arraySize = budget.getBudget_grants().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getRevised_grants()) && budget.getRevised_grants().length > 0) {
				budget.setRevised_grants(CommonMethods.replaceEmptyByNullInSringArray(budget.getRevised_grants()));
				if(arraySize < budget.getRevised_grants().length) {
					arraySize = budget.getRevised_grants().length;
				}
			}
			if(!StringUtils.isEmpty(budget.getFinal_grants()) && budget.getFinal_grants().length > 0) {
				budget.setFinal_grants(CommonMethods.replaceEmptyByNullInSringArray(budget.getFinal_grants()));
				if(arraySize < budget.getFinal_grants().length) {
					arraySize = budget.getFinal_grants().length;
				}
			}
			
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( budget.getFinancial_year_fks().length > 0 && !StringUtils.isEmpty(budget.getFinancial_year_fks()[i])) {
					    insertStmt.setString(p++,(budget.getContract_id()));
					    insertStmt.setString(p++,(budget.getFinancial_year_fks().length > 0)?budget.getFinancial_year_fks()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_estimates().length > 0)?budget.getBudget_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_estimates().length > 0)?budget.getRevised_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_estimates().length > 0)?budget.getFinal_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_grants().length > 0)?budget.getBudget_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_grants().length > 0)?budget.getRevised_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_grants().length > 0)?budget.getFinal_grants()[i]:null);
					    insertStmt.setString(p++,CommonConstants.ACTIVE);
					   
					    insertStmt.addBatch();
				    }
				    insertCount = insertStmt.executeBatch();

			  }
				if(insertCount.length > 0) {
					flag = true;
				}
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(budget.getCreated_by_user_id_fk());
				formHistory.setUser(budget.getDesignation()+" - "+budget.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Update New Budget");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("New Budget for "+budget.getContract_id() + " Updated");
				formHistory.setWork_id_fk(budget.getContract_id());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
		}

			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt1, null);
			con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, rs);
		}
		return flag;
	}

	@Override
	public boolean deleteBudget(Budget obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);	
			String deleteQry = "DELETE from new_budget where new_budget_id= :new_budget_id ";
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(deleteQry, paramSource);			
			if(count > 0) {
				flag = true;
			}
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
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
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getNewBudgetWorksList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT work_id_fk,w.work_name,w.work_short_name from new_budget b " + 
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY work_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getNewBudgetProjectsList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT p.project_id as project_id_fk,p.project_name from new_budget b " + 
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where project_id_fk is not null and project_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY project_id_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getFinancialYearsList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT b.financial_year_fk  from new_budget b " + 
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where financial_year_fk is not null and financial_year_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			qry = qry + "GROUP BY b.financial_year_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getNewBudgetExportList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="SELECT new_budget_id as budget_id,c.contract_id,work_id_fk,w.work_name,p.project_id,p.project_name,FORMAT(CONCAT(b.financial_year_fk,'-00'),'%Y-%m') AS financial_year_fk,cast(new_budget_estimate as CHAR) as budget_estimate,cast(new_budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " 
					+ " from new_budget b "+ 
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE new_budget_id is not null and b.status = ? ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			qry = qry +" order BY contract_id_fk asc,financial_year_fk desc";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}				
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getProjectsListForBudgetForm(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "select project_id,project_name from project order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<Budget> getContractsListForBudgetForm(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "select contract_id,contract_short_name as contract_name from contract WHERE 0=0 ";
			
			if(StringUtils.isEmpty(obj.getBudget_id())) {
				qry = qry +" and contract_id not in(select contract_id_fk from new_budget) ";
			}
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry +" order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

	@Override
	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception {
		List<Budget> objsList = new ArrayList<Budget>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ?";
				arrSize++;
			}
			
			qry = qry + " order by work_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}	
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(Budget obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="select count(DISTINCT work_id_fk) as total_records from new_budget b "+
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE new_budget_id is not null and b.status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}				
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (b.contract_id_fk like ? or c.contract_short_name like ? or financial_year_fk like ?"
						+ " or new_budget_estimate like ? or new_budget_grant like ? or revised_estimate like ? or revised_grant like ? or final_estimate like ? or final_grant like ?)";
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
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
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
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<Budget> getNewBudgetList(Budget obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select new_budget_id as budget_id,work_id_fk,w.work_name,w.work_short_name,c.contract_id,c.contract_short_name as contract_name,p.project_id,p.project_name,max(b.financial_year_fk) as financial_year_fk,cast(new_budget_estimate as CHAR) as budget_estimate,cast(new_budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " 
					+ " from new_budget b "+
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE new_budget_id is not null and b.status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}				
			
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (b.contract_id_fk like ? or c.contract_short_name like ? or financial_year_fk like ?"
						+ " or new_budget_estimate like ? or new_budget_grant like ? or revised_estimate like ? or revised_grant like ? or final_estimate like ? or final_grant like ?)";
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
				qry = qry + " GROUP BY contract_id_fk ORDER BY new_budget_id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
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
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getNewBudgetContractsList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT c.contract_id,c.contract_short_name as contract_name from new_budget b " + 
					"LEFT JOIN contract c on c.contract_id = b.contract_id_fk "+
					"LEFT JOIN work w on c.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where project_id_fk is not null and project_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				qry = qry + " and c.contract_id = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ? ";
				arrSize++;
			}
			qry = qry + "GROUP BY contract_id ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id())) {
				pValues[i++] = obj.getContract_id();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	
}
