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

import com.synergizglobal.pmis.Idao.BudgetDao;
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
public class BudgetDaoImpl implements BudgetDao {


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
			
			String qry ="select budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,max(b.financial_year_fk) as financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant  from budget b LEFT JOIN work w on b.work_id_fk = w.work_id LEFT JOIN financial_year f on b.financial_year_fk = f.financial_year LEFT JOIN project p on  w.project_id_fk = p.project_id WHERE b.financial_year_fk = (SELECT (CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(cast(YEAR(CONVERT(date, getdate()))+1 as varchar),3,2)) ELSE concat(cast(YEAR(CONVERT(date, getdate()))-1 as varchar),'-', \r\n" + 
					"SUBSTRING(cast(YEAR(CONVERT(date, getdate())) as varchar),3,2)) END) AS financial_year) AND budget_id is not null and status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			qry = qry +" group by budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,budget_estimate,budget_grant,revised_estimate,revised_grant,final_estimate,final_grant ";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
	public Budget getBudget(Budget obj)throws Exception{
		Budget budget = null;
		try {
			String qry = "select budget_id, work_id_fk,w.work_name,p.project_name,w.work_short_name,w.project_id_fk from budget b" + 
					"left join work w on work_id_fk = w.work_id " + 
					"left join project p on w.project_id_fk = p.project_id where budget_id is not null" ; 
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
			if(!StringUtils.isEmpty(budget) && !StringUtils.isEmpty(budget.getBudget_id())) {
				List<Budget> objsList = null;
				String qryDetails = "select budget_id,b.financial_year_fk,budget_estimate, revised_estimate, final_estimate,"+
						"budget_grant, revised_grant,final_grant "
						+ "from budget b " 
						+"left join financial_year f on b.financial_year_fk = f.financial_year where work_id_fk = ?  ORDER BY financial_year_fk DESC";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {budget.getWork_id_fk()}, new BeanPropertyRowMapper<Budget>(Budget.class));	
				budget.setBudget(objsList);
				if(!StringUtils.isEmpty(objsList)) {
					for(Budget list : budget.getBudget()) {
						
						String qry2 ="select id, budget_id_fk, attachment  from budget_files where budget_id_fk = ?";
						List<Budget> objList1 = jdbcTemplate.query( qry2,new Object[] {list.getBudget_id()}, new BeanPropertyRowMapper<Budget>(Budget.class));

						list.setBudgetFilesList(objList1);
					}
					
				}
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
			String insert_qry = "INSERT into  budget ( work_id_fk, financial_year_fk, budget_estimate, revised_estimate, "
					+ "final_estimate, budget_grant, revised_grant, final_grant, status) "
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
			int loopTimes=0;
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( budget.getFinancial_year_fks().length > 0 && !StringUtils.isEmpty(budget.getFinancial_year_fks()[i])) {
					    insertStmt.setString(p++,(budget.getWork_id_fk()));
					    insertStmt.setString(p++,(budget.getFinancial_year_fks().length > 0)?budget.getFinancial_year_fks()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_estimates().length > 0)?budget.getBudget_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_estimates().length > 0)?budget.getRevised_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_estimates().length > 0)?budget.getFinal_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_grants().length > 0)?budget.getBudget_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_grants().length > 0)?budget.getRevised_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_grants().length > 0)?budget.getFinal_grants()[i]:null);
					    insertStmt.setString(p++,CommonConstants.ACTIVE);
					   
					    //insertStmt.addBatch();
					    insertStmt.executeUpdate();
					    loopTimes++;
				    }
				    //insertCount = insertStmt.executeBatch();
				    rs = insertStmt.getGeneratedKeys();
				    
				    if(loopTimes > 0) {
						flag = true;
						String budgetId = null;
						if (rs.next()) {
							budgetId = rs.getString(1);
							budget.setBudget_id(budgetId);
						}
						if(!StringUtils.isEmpty(budget.getBudgetFiles()) && budget.getBudgetFiles().length > 0) {
							
									String file_insert_qry = "INSERT into  budget_files ( budget_id_fk, work_id_fk,attachment) VALUES (?,?,?)";
									PreparedStatement insertStmt1 = con.prepareStatement(file_insert_qry);
									int len = budget.getBudgetFiles().length;
									int  j = 0;
										while ( j < budget.getFilecounts()[i] ) {
											    int k = 1;
											    int a = r++;  
											    if(a <= (len-1)) {
													 MultipartFile fundFiles = budget.getBudgetFiles()[a];
													 if (null != fundFiles && !fundFiles.isEmpty()){
														String saveDirectory = CommonConstants.BUDGET_FILE_SAVING_PATH ;
														String fileName = fundFiles.getOriginalFilename();
														FileUploads.singleFileSaving(fundFiles, saveDirectory, fileName);
														budget.setAttachment(fileName);
														
														 insertStmt1.setString(k++,(budget.getBudget_id()));
														 insertStmt1.setString(k++,(budget.getWork_id_fk()));
														 insertStmt1.setString(k++,(budget.getAttachment()));
														 //insertStmt1.addBatch();
														 insertStmt1.executeUpdate();
														 j++;
													 }
											    }else {
											    	j++;
											    }
										}
								//int[] insertCount1 = insertStmt1.executeBatch();
						}	
						
					}	
			  }
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(budget.getCreated_by_user_id_fk());
				formHistory.setUser(budget.getDesignation()+" - "+budget.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Add Budget");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New Budget for "+budget.getWork_id_fk() + " Created");
				formHistory.setWork_id_fk(budget.getWork_id_fk());
				//formHistory.setContract(budget.getContract_id_fk());
				
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
			String deleteQry = "DELETE from budget_files  where work_id_fk = ?";		 
			stmt = con.prepareStatement(deleteQry);
			stmt.setString(1,budget.getWork_id_fk());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			String inactiveQry = "DELETE from budget  where work_id_fk = ?";		 
			stmt = con.prepareStatement(inactiveQry);
			stmt.setString(1,budget.getWork_id_fk());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			
			
			String insert_qry = "INSERT into  budget ( work_id_fk, financial_year_fk, budget_estimate, revised_estimate, "
					+ "final_estimate, budget_grant, revised_grant, final_grant, status) "
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
			int loopTimes=0;
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				for (int i = 0; i < arraySize; i++) {
				    int p = 1;
				    if( budget.getFinancial_year_fks().length > 0 && !StringUtils.isEmpty(budget.getFinancial_year_fks()[i])) {
					    insertStmt.setString(p++,(budget.getWork_id_fk()));
					    insertStmt.setString(p++,(budget.getFinancial_year_fks().length > 0)?budget.getFinancial_year_fks()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_estimates().length > 0)?budget.getBudget_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_estimates().length > 0)?budget.getRevised_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_estimates().length > 0)?budget.getFinal_estimates()[i]:null);
					    insertStmt.setString(p++,(budget.getBudget_grants().length > 0)?budget.getBudget_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getRevised_grants().length > 0)?budget.getRevised_grants()[i]:null);
					    insertStmt.setString(p++,(budget.getFinal_grants().length > 0)?budget.getFinal_grants()[i]:null);
					    insertStmt.setString(p++,CommonConstants.ACTIVE);
					   
					    //insertStmt.addBatch();
					    insertStmt.executeUpdate();
					    loopTimes++;
				    }
				    //insertCount = insertStmt.executeBatch();
				    rs = insertStmt.getGeneratedKeys();
				    
				    if(loopTimes > 0) {
						flag = true;
						String budgetId = null;
						if (rs.next()) {
							budgetId = rs.getString(1);
							budget.setBudget_id(budgetId);
						}
						if(!StringUtils.isEmpty(budget.getBudgetFiles()) && budget.getBudgetFiles().length > 0) {
							
									String file_insert_qry = "INSERT into  budget_files ( budget_id_fk, work_id_fk,attachment) VALUES (?,?,?)";
									insertStmt1 = con.prepareStatement(file_insert_qry);
									int len = budget.getBudgetFiles().length;
									
									int  j = 0;
									int budgetNamesLen = 0;
									 String budgetFileName = null;
										while ( j < budget.getFilecounts()[i] ) {
											    int k = 1;
											    int a = r++;  
											    if(!StringUtils.isEmpty(budget.getBudgetFileNames())) {
												     budgetNamesLen = budget.getBudgetFileNames().length ;
												}
											    if(a <= (len-1)) {
											    	 String saveDirectory = CommonConstants.BUDGET_FILE_SAVING_PATH ;
													   
													    MultipartFile fundFiles = budget.getBudgetFiles()[a];
														if (null != fundFiles && !fundFiles.isEmpty()){
															String fileName = fundFiles.getOriginalFilename();
															//budgetFileName = fileName;
															FileUploads.singleFileSaving(fundFiles, saveDirectory, fileName);
														} 
											    } 
											    if(a <= (budgetNamesLen-1)) {
															if(a <= (budgetNamesLen-1)) {
																budgetFileName  = (budget.getBudgetFileNames().length > 0)?budget.getBudgetFileNames()[a]:null;
															}
														
														if(!StringUtils.isEmpty(budgetFileName)){
															 insertStmt1.setString(k++,(budget.getBudget_id()));
															 insertStmt1.setString(k++,(budget.getWork_id_fk()));
															 insertStmt1.setString(k++,(budgetFileName));
															 //insertStmt1.addBatch();
															 insertStmt1.executeUpdate();
															 j++;
														}
											    }else {
											    	j++;
											    }
										}
								//int[] insertCount1 = insertStmt1.executeBatch();
						}	
						
					}	
			  }
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(budget.getCreated_by_user_id_fk());
				formHistory.setUser(budget.getDesignation()+" - "+budget.getUser_name());
				formHistory.setModule_name_fk("Finance");
				formHistory.setForm_name("Update Budget");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Budget for "+budget.getWork_id_fk() + " Updated");
				formHistory.setWork_id_fk(budget.getWork_id_fk());
				//formHistory.setContract(budget.getContract_id_fk());
				
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
			String deleteQry = "DELETE FROM budget where budget_id= :budget_id ";
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
	public List<Budget> getBudgetWorksList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT work_id_fk,w.work_name,w.work_short_name from budget b " + 
					"LEFT JOIN work w on b.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				qry = qry + " and b.financial_year_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY work_id_fk,w.work_name,w.work_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				pValues[i++] = obj.getFinancial_year_fk();
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
	public List<Budget> getBudgetProjectsList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT p.project_id as project_id_fk,p.project_name from budget b " + 
					"LEFT JOIN work w on b.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where project_id_fk is not null and project_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				qry = qry + " and b.financial_year_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY project_id,p.project_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				pValues[i++] = obj.getFinancial_year_fk();
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
			String qry = "SELECT b.financial_year_fk  from budget b " + 
					"LEFT JOIN work w on b.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where financial_year_fk is not null and financial_year_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				qry = qry + " and b.financial_year_fk = ? ";
				arrSize++;
			}
			qry = qry + " GROUP BY b.financial_year_fk ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				pValues[i++] = obj.getFinancial_year_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<Budget> getBudgetExportList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="SELECT budget_id,work_id_fk,w.work_name,p.project_id,p.project_name,b.financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " 
					+ " from budget b " 
					+ "LEFT JOIN work w on b.work_id_fk = w.work_id "
					+ "LEFT JOIN financial_year f on b.financial_year_fk = f.financial_year " 
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE b.financial_year_fk = (SELECT (CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), -,SUBSTRING(YEAR(CONVERT(date, getdate()))+1,3,2)) ELSE concat(YEAR(CONVERT(date, getdate()))-1,-, SUBSTRING(YEAR(CONVERT(date, getdate())),3,2)) END) AS financial_year) " 
					+ "AND budget_id is not null and status = ? ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}	
			qry = qry +" order BY work_id_fk asc,financial_year_fk desc ";

			Object[] pValues = new Object[arrSize];
			int i = 0;
			pValues[i++] = CommonConstants.ACTIVE;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				pValues[i++] = obj.getProject_id_fk();
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
	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception {
		List<Budget> objsList = new ArrayList<Budget>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null ";
					
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + "and project_id_fk = ? ";
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
			String qry ="select count(DISTINCT work_id_fk) as total_records from budget b "
					+ "LEFT JOIN work w on b.work_id_fk = w.work_id "
					+ "LEFT JOIN financial_year f on b.financial_year_fk = f.financial_year " 
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE b.financial_year_fk = (SELECT (CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(cast(YEAR(CONVERT(date, getdate()))+1 as varchar),3,2)) ELSE concat(cast(YEAR(CONVERT(date, getdate()))-1 as varchar),'-', \r\n" + 
					"SUBSTRING(cast(YEAR(CONVERT(date, getdate())) as varchar),3,2)) END) AS financial_year) AND budget_id is not null and status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (b.work_id_fk like ? or w.work_short_name like ? or financial_year_fk like ?"
						+ " or budget_estimate like ? or budget_grant like ? or revised_estimate like ? or revised_grant like ? or final_estimate like ? or final_grant like ?)";
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
	public List<Budget> getBudgetList(Budget obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="select budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,max(b.financial_year_fk) as financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " 
					+ " from budget b "
					+ "LEFT JOIN work w on b.work_id_fk = w.work_id "
					+ "LEFT JOIN financial_year f on b.financial_year_fk = f.financial_year " 
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE "
					+ "b.financial_year_fk = (SELECT (CASE WHEN MONTH(CONVERT(date, getdate())) >= 4 THEN concat(YEAR(CONVERT(date, getdate())), '-',SUBSTRING(cast(YEAR(CONVERT(date, getdate()))+1 as varchar),3,2)) ELSE concat(cast(YEAR(CONVERT(date, getdate()))-1 as varchar),'-', \r\n" + 
					"SUBSTRING(cast(YEAR(CONVERT(date, getdate())) as varchar),3,2)) END) AS financial_year) " 
					+ "AND "
					+ "budget_id is not null and status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (b.work_id_fk like ? or w.work_short_name like ? or financial_year_fk like ?"
						+ " or budget_estimate like ? or budget_grant like ? or revised_estimate like ? or revised_grant like ? or final_estimate like ? or final_grant like ?)";
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
				qry = qry + " group by budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,budget_estimate,budget_grant,revised_estimate,revised_grant,final_estimate,final_grant ORDER BY budget_id ASC offset ? rows  fetch next ? rows only";
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
	
	
}
