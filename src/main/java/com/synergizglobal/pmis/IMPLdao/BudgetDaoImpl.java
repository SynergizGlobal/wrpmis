package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Budget;

@Repository
public class BudgetDaoImpl implements BudgetDao {


	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	
	@Override
	public List<Budget> budgetList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			/*String qry ="select budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,max(b.financial_year_fk) as financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, " + 
					"cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " + 
					",b.remarks from budget b " + 
					"left join work w on b.work_id_fk = w.work_id " + 
					"left join financial_year f on b.financial_year_fk = f.financial_year " + 
					"left join project p on  w.project_id_fk = p.project_id where budget_id is not null and status = ? ";*/
			
			String qry ="select budget_id,work_id_fk,w.work_name,w.work_short_name,p.project_id,p.project_name,max(b.financial_year_fk) as financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant, " 
					+ "b.remarks from budget b "
					+ "LEFT JOIN work w on b.work_id_fk = w.work_id "
					+ "LEFT JOIN financial_year f on b.financial_year_fk = f.financial_year " 
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE b.financial_year_fk = (SELECT (CASE WHEN MONTH(NOW()) >= 4 THEN concat(YEAR(NOW()), '-',SUBSTR(YEAR(NOW())+1,3,2)) ELSE concat(YEAR(NOW())-1,'-', SUBSTR(YEAR(NOW()),3,2)) END) AS financial_year) " 
					+ "AND budget_id is not null and status = ? ";
			
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			qry = qry +" GROUP BY work_id_fk";

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
			throw new Exception(e.getMessage());
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
				String qryDetails = "select budget_id,b.financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate, cast(revised_estimate as CHAR) as revised_estimate, cast(final_estimate as CHAR) as final_estimate,"+
						"cast(budget_grant as CHAR) as budget_grant, cast(revised_grant as CHAR) as revised_grant, cast(final_grant as CHAR) as final_grant, b.remarks, b.attachment "
						+ "from budget b " 
						+"left join financial_year f on b.financial_year_fk = f.financial_year where work_id_fk = ? and status = ? ORDER BY financial_year_fk DESC";
				
				objsList = jdbcTemplate.query(qryDetails, new Object[] {budget.getWork_id_fk(),CommonConstants.ACTIVE}, new BeanPropertyRowMapper<Budget>(Budget.class));	
				budget.setBudget(objsList);
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return budget;
		
	}

	@Override
	public boolean addBudget(Budget budget) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;
		int[] insertCount = {};
		try {
			
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String insert_qry = "INSERT into  budget ( work_id_fk, financial_year_fk, budget_estimate, revised_estimate, "
					+ "final_estimate, budget_grant, revised_grant, final_grant, attachment, status) "
					+"VALUES (?,?,?,?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insert_qry); 
			int arraySize = 0; 
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
			String[] documentNames = new String[arraySize];
			if(!StringUtils.isEmpty(budget.getBudgetFile()) && budget.getBudgetFile().length > 0) {
				if(arraySize < budget.getBudgetFile().length) {
						arraySize = budget.getBudgetFile().length;
				}
				String saveDirectory = CommonConstants.BUDGET_FILE_SAVING_PATH ;
				documentNames = new String[arraySize];
				for (int k = 0; k < documentNames.length; k++) {
					/*if (rs.next()) {
						String id = rs.getString(1);
						obj.setDocument_no(id);
					}*/
					MultipartFile file = budget.getBudgetFile()[k];
					if (null != file && !file.isEmpty()){
						String fileName = file.getOriginalFilename();
						//DateFormat df = new SimpleDateFormat("ddMMYY-HHmm"); 
						//String fileName_new = "Document-"+obj.getSafety_equipment_id() +"-"+ df.format(new Date()) +"."+ fileName.split("\\.")[1];
						documentNames[k] = fileName;
						FileUploads.singleFileSaving(file, saveDirectory, fileName);
						budget.setAttachment(fileName);
					}else {
						documentNames[k] = null;
					}
			    }
			}
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
					    insertStmt.setString(p++,(documentNames.length > 0)?documentNames[i]:null);	
					    insertStmt.setString(p++,CommonConstants.ACTIVE);
					   
					    insertStmt.addBatch();
				    }
			  }
		      insertCount = insertStmt.executeBatch();
		   }
				
		   if(insertCount.length > 0) {
				flag = true;
		   }
		   con.commit();
		}catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
		}
		return flag;
	}

	@Override
	public boolean updateBudget(Budget budget) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement stmt = null;
		boolean flag = false;
		int[] updateCount = {};
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String inactiveQry = "UPDATE budget set status = ? where work_id_fk = ?";		 
			stmt = con.prepareStatement(inactiveQry);
			stmt.setString(1,CommonConstants.INACTIVE);
			stmt.setString(2,budget.getWork_id_fk());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			String updateQry = "UPDATE budget set "
					+ "financial_year_fk= ?, budget_estimate=? ,revised_estimate= ?, final_estimate= ?, budget_grant= ?,revised_grant = ?,final_grant = ?,attachment=? ,status=?"
					+ "where budget_id= ?";
			updateStmt = con.prepareStatement(updateQry);
			String insert_qry = "INSERT into  budget ( work_id_fk, financial_year_fk, budget_estimate, revised_estimate, "
					+ "final_estimate, budget_grant, revised_grant, final_grant, attachment, status) "
					+"VALUES (?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(insert_qry); 
			int arraySize = 0; 
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
			String saveDirectory = CommonConstants.BUDGET_FILE_SAVING_PATH ;
			List<MultipartFile> files = new ArrayList<MultipartFile>();
			if(!StringUtils.isEmpty(budget.getFinancial_year_fks()) && budget.getFinancial_year_fks().length > 0) {
				for (int i = 0; i < arraySize; i++) {
					String bId = budget.getBudget_ids()[i];
					if(!StringUtils.isEmpty(bId)) {
						int p =1;
						if( budget.getFinancial_year_fks().length > 0 && !StringUtils.isEmpty(budget.getFinancial_year_fks()[i])) {
							 	String docFileName = null;
							    MultipartFile file = budget.getBudgetFile()[i];
								if (null != file && !file.isEmpty()){
									String fileName = file.getOriginalFilename();
									docFileName = fileName;
									FileUploads.singleFileSaving(file, saveDirectory, docFileName);
								} else {
									docFileName  = (budget.getBudgetFileNames().length > 0)?budget.getBudgetFileNames()[i]:null;
								} 
								updateStmt.setString(p++,(budget.getFinancial_year_fks().length > 0)?budget.getFinancial_year_fks()[i]:null);
								updateStmt.setString(p++,(budget.getBudget_estimates().length > 0)?budget.getBudget_estimates()[i]:null);
								updateStmt.setString(p++,(budget.getRevised_estimates().length > 0)?budget.getRevised_estimates()[i]:null);
								updateStmt.setString(p++,(budget.getFinal_estimates().length > 0)?budget.getFinal_estimates()[i]:null);
								updateStmt.setString(p++,(budget.getBudget_grants().length > 0)?budget.getBudget_grants()[i]:null);
								updateStmt.setString(p++,(budget.getRevised_grants().length > 0)?budget.getRevised_grants()[i]:null);
								updateStmt.setString(p++,(budget.getFinal_grants().length > 0)?budget.getFinal_grants()[i]:null);
								updateStmt.setString(p++,docFileName);	
								updateStmt.setString(p++,CommonConstants.ACTIVE);
								updateStmt.setString(p++,(budget.getBudget_ids()[i]));
								updateStmt.addBatch();
					  }
					}else {
						String[] documentNames = new String[arraySize];
						if(!StringUtils.isEmpty(budget.getBudgetFile()) && budget.getBudgetFile().length > 0) {
							if(arraySize < budget.getBudgetFile().length) {
									arraySize = budget.getBudgetFile().length;
							}
							String saveDirectory1 = CommonConstants.DOCUMENT_FILES_SAVING_PATH ;
							documentNames = new String[arraySize];
							for (int k = 0; k < documentNames.length; k++) {
								MultipartFile file = budget.getBudgetFile()[k];
								if (null != file && !file.isEmpty()){
									String fileName = file.getOriginalFilename();
									documentNames[k] = fileName;
									FileUploads.singleFileSaving(file, saveDirectory1, fileName);
									budget.setAttachment(fileName);
								}else {
									documentNames[k] = null;
								}
						    }
						}
					    int p = 1;
						if( budget.getFinancial_year_fks().length > 0 && !StringUtils.isEmpty(budget.getFinancial_year_fks()[i])) {
							    MultipartFile file = budget.getBudgetFile()[i];
								files.add(file);
							    stmt.setString(p++,(budget.getWork_id_fk()));
							    stmt.setString(p++,(budget.getFinancial_year_fks().length > 0)?budget.getFinancial_year_fks()[i]:null);
							    stmt.setString(p++,(budget.getBudget_estimates().length > 0)?budget.getBudget_estimates()[i]:null);
							    stmt.setString(p++,(budget.getRevised_estimates().length > 0)?budget.getRevised_estimates()[i]:null);
							    stmt.setString(p++,(budget.getFinal_estimates().length > 0)?budget.getFinal_estimates()[i]:null);
							    stmt.setString(p++,(budget.getBudget_grants().length > 0)?budget.getBudget_grants()[i]:null);
							    stmt.setString(p++,(budget.getRevised_grants().length > 0)?budget.getRevised_grants()[i]:null);
							    stmt.setString(p++,(budget.getFinal_grants().length > 0)?budget.getFinal_grants()[i]:null);
							    stmt.setString(p++,(documentNames.length > 0)?documentNames[i]:null);	
							    stmt.setString(p++,CommonConstants.ACTIVE);
							    stmt.addBatch();
						}
					}
				}
			}
			updateCount = updateStmt.executeBatch();
			
			
			int[] insertCount = stmt.executeBatch();
		
			if(updateCount.length > 0 || insertCount.length > 0) {
				flag = true;
			}

			DBConnectionHandler.closeJDBCResoucrs(null, stmt, null);
			con.commit();
		}catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
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
	public List<Budget> getBudgetWorksList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "SELECT work_id_fk,w.work_name,w.work_short_name from budget b " + 
					"LEFT JOIN work w on b.work_id_fk = w.work_id "+
					"LEFT JOIN project p on w.project_id_fk = p.project_id  " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				qry = qry + " and b.financial_year_fk = ? ";
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
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFinancial_year_fk())) {
				pValues[i++] = obj.getFinancial_year_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<Budget>(Budget.class));
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
				qry = qry + " and work_id_fk = ?";
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
			qry = qry + "GROUP BY project_id_fk ";
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
			throw new Exception(e.getMessage());
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
				qry = qry + " and work_id_fk = ?";
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
			qry = qry + "GROUP BY b.financial_year_fk ";
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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> getBudgetExportList(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry ="SELECT budget_id,work_id_fk,w.work_name,p.project_id,p.project_name,b.financial_year_fk,cast(budget_estimate as CHAR) as budget_estimate,cast(budget_grant as CHAR) as budget_grant, " 
					+ "cast(revised_estimate as CHAR) as revised_estimate,cast(revised_grant as CHAR) as revised_grant,cast(final_estimate as CHAR) as final_estimate,cast(final_grant as CHAR) as final_grant " 
					+ ",b.remarks from budget b " 
					+ "LEFT JOIN work w on b.work_id_fk = w.work_id "
					+ "LEFT JOIN financial_year f on b.financial_year_fk = f.financial_year " 
					+ "LEFT JOIN project p on  w.project_id_fk = p.project_id "
					+ "WHERE b.financial_year_fk = (SELECT (CASE WHEN MONTH(NOW()) >= 4 THEN concat(YEAR(NOW()), '-',SUBSTR(YEAR(NOW())+1,3,2)) ELSE concat(YEAR(NOW())-1,'-', SUBSTR(YEAR(NOW()),3,2)) END) AS financial_year) " 
					+ "AND budget_id is not null and status = ? ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getProject_id_fk())) {
				qry = qry + " and project_id = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			qry = qry +" order BY work_id_fk asc,financial_year_fk desc";

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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> getProjectsListForBudgetForm(Budget obj) throws Exception {
		List<Budget> objsList = null;
		try {
			String qry = "select project_id,project_name from `project` order by project_id asc";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<Budget>(Budget.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<Budget> getWorkListForBudgetForm(Budget obj) throws Exception {
		List<Budget> objsList = new ArrayList<Budget>();
		try {
			String qry = "select work_id,work_name,work_short_name,project_id_fk,project_name "
					+ "from `work` w "
					+ "LEFT OUTER JOIN `project` p ON project_id_fk = project_id "
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
	
	
}
