package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.TAFinancialsDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.TAFinancials;


@Repository
public class TAFinancialsDaoImpl implements TAFinancialsDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<TAFinancials> taFinancialsList(TAFinancials obj) throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry ="SELECT ID as financial_id, work_id as work_id_fk ,w.work_name,c.contract_short_name,w.work_short_name,c.contract_short_name, contract_id_fk, month, sum(planned) as planned, sum(actual) as actual, sum(payment_received) as payment_received " + 
					" FROM ta_financials t " + 
					" left join contract c on c.contract_id = t.contract_id_fk " + 
					" left join work w on c.work_id_fk = w.work_id where DATE(month) <= DATE(CONVERT(date, getdate())) and status = ? ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry +" group by contract_id_fk";
			Object[] pValues = new Object[arrSize];
			

			int i = 0;
			
			pValues[i++] = CommonConstants.ACTIVE;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TAFinancials> getTAFinancialsWorksList(TAFinancials obj) throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry = "select distinct work_id as work_id_fk, w.work_short_name from ta_financials t " + 
					"left join contract c on c.contract_id = t.contract_id_fk " + 
					"left join work w on c.work_id_fk = w.work_id  " + 
					"where work_id_fk is not null and work_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and t.contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY work_id,work_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TAFinancials> getTAFinancialsContractsList(TAFinancials obj) throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry = "select distinct contract_id_fk, c.contract_short_name from ta_financials t " + 
					"left join contract c on c.contract_id = t.contract_id_fk " + 
					"where contract_id_fk is not null and contract_id_fk <> '' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and t.contract_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + "GROUP BY contract_id_fk,contract_short_name ";
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TAFinancials> getWorksList() throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry ="select work_id as work_id_fk,work_name,work_short_name from work ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public TAFinancials getTAFinancials(TAFinancials obj) throws Exception {
		TAFinancials sObj =null;
		
		try {
			String qry = "select w.work_id as work_id_fk ,c.contract_name,c.contract_short_name,w.work_name,w.work_short_name,contract_id_fk "
					+"from ta_financials t "
					+"LEFT OUTER join contract c on contract_id_fk =c.contract_id " 
					+"LEFT OUTER join work w on c.work_id_fk = w.work_id "  
					+"where ID is not null";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getID())) {
				qry = qry + " and ID = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getID())) {
				pValues[i++] = obj.getID();
			}
			sObj = (TAFinancials)jdbcTemplate.queryForObject(qry, pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));	
			
			if(!StringUtils.isEmpty(sObj) && !StringUtils.isEmpty(obj.getID())) {
			
				List<TAFinancials> objsList = null;
			String qryDetails = "select ID,contract_id_fk, FORMAT(month,'%Y-%m') AS month,"
					+ "cast(planned as CHAR) as planned,cast(actual as CHAR) as actual,cast(payment_received as CHAR) as payment_received,planned_units as planned_unit,actual_units as actual_unit,payment_received_units as payment_unit "
					+ "from ta_financials "
					+"where contract_id_fk = ? and status = ? order by month desc";
			
			objsList = jdbcTemplate.query(qryDetails, new Object[] {sObj.getContract_id_fk(),CommonConstants.ACTIVE}, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));	
			sObj.setTaFinancials(objsList);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return sObj;
	}

	@Override
	public boolean addTAFinancials(TAFinancials obj) throws Exception {
		Connection con = null;
		PreparedStatement insertStmt = null;
		boolean flag = false;		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String insertQry = "INSERT INTO ta_financials"
					+ "(contract_id_fk, month, planned, actual, payment_received, status, planned_units, actual_units, payment_received_units)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertQry);
			
			int	arraySize = 0;
			if( !StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
				obj.setMonths(CommonMethods.replaceEmptyByNullInSringArray(obj.getMonths()));
				if(arraySize < obj.getMonths().length) {
					arraySize = obj.getMonths().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPlanneds()) && obj.getPlanneds().length > 0) {
				obj.setPlanneds(CommonMethods.replaceEmptyByNullInSringArray(obj.getPlanneds()));
				if(arraySize < obj.getPlanneds().length) {
					arraySize = obj.getPlanneds().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getActuals()) && obj.getActuals().length > 0) {
				obj.setActuals(CommonMethods.replaceEmptyByNullInSringArray(obj.getActuals()));
				if(arraySize < obj.getActuals().length) {
					arraySize = obj.getActuals().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPayment_receiveds()) && obj.getPayment_receiveds().length > 0) {
				obj.setPayment_receiveds(CommonMethods.replaceEmptyByNullInSringArray(obj.getPayment_receiveds()));
				if(arraySize < obj.getPayment_receiveds().length) {
					arraySize = obj.getPayment_receiveds().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPlanned_units()) && obj.getPlanned_units().length > 0) {
				obj.setPlanned_units(CommonMethods.replaceEmptyByNullInSringArray(obj.getPlanned_units()));
				if(arraySize < obj.getPlanned_units().length) {
					arraySize = obj.getPlanned_units().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getActual_units()) && obj.getActual_units().length > 0) {
				obj.setActual_units(CommonMethods.replaceEmptyByNullInSringArray(obj.getActual_units()));
				if(arraySize < obj.getActual_units().length) {
					arraySize = obj.getActual_units().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPayment_received_units()) && obj.getPayment_received_units().length > 0) {
				obj.setPayment_received_units(CommonMethods.replaceEmptyByNullInSringArray(obj.getPayment_received_units()));
				if(arraySize < obj.getPayment_received_units().length) {
					arraySize = obj.getPayment_received_units().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
				for (int i = 0; i < arraySize; i++) {
					 int k = 1;
					 if( obj.getMonths().length > 0 && !StringUtils.isEmpty(obj.getMonths()[i])) {
						 String date = obj.getMonths()[i] + "-01";
						 insertStmt.setString(k++,obj.getContract_id_fk());
						 insertStmt.setString(k++,(date));
						 insertStmt.setString(k++,(obj.getPlanneds().length > 0)?obj.getPlanneds()[i]:null);
						 insertStmt.setString(k++,(obj.getActuals().length > 0)?obj.getActuals()[i]:null);
						 insertStmt.setString(k++,(obj.getPayment_receiveds().length > 0)?obj.getPayment_receiveds()[i]:null);
						 insertStmt.setString(k++,CommonConstants.ACTIVE);
						 insertStmt.setString(k++,(obj.getPlanned_units().length > 0)?obj.getPlanned_units()[i]:null);
						 insertStmt.setString(k++,(obj.getActual_units().length > 0)?obj.getActual_units()[i]:null);
						 insertStmt.setString(k++,(obj.getPayment_received_units().length > 0)?obj.getPayment_received_units()[i]:null);
						 insertStmt.addBatch();
					 }
				}
			}
			int[] insertCount = insertStmt.executeBatch();
			if(insertCount.length > 0) {
				flag = true;
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Add TA Financial");
				formHistory.setForm_action_type("Add");
				formHistory.setForm_details("New TA Financial "+obj.getContract_id_fk() + " Created");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
		con.commit();
	    }catch(Exception e){ 
	    	con.rollback();
			e.printStackTrace();
			throw new Exception(e);
	    }finally {
			DBConnectionHandler.closeJDBCResoucrs(con, insertStmt, null);
	   }
	   return flag;
	}

	@Override
	public boolean updateTAFinancials(TAFinancials obj) throws Exception {
		Connection con = null;
		PreparedStatement updateStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement stmt = null;
		boolean flag = false;		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			String inactiveQry = "UPDATE ta_financials set status = ? where contract_id_fk = ?";		 
			stmt = con.prepareStatement(inactiveQry);
			stmt.setString(1,CommonConstants.INACTIVE);
			stmt.setString(2,obj.getContract_id_fk());
			stmt.executeUpdate();
			if(stmt != null){stmt.close();}
			
			String updateQry = "UPDATE ta_financials set "
					+ "month= ?, planned= ?, actual= ?,payment_received = ?,status = ?,planned_units = ?,actual_units = ?,payment_received_units = ? "
					+ "where ID= ?";
			updateStmt = con.prepareStatement(updateQry);
			
			String insertqry = "INSERT INTO ta_financials"
					+ "(contract_id_fk, month, planned, actual, payment_received, status, planned_units, actual_units, payment_received_units)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			insertStmt = con.prepareStatement(insertqry); 
			int arraySize = 0; 
			
			if( !StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
				obj.setMonths(CommonMethods.replaceEmptyByNullInSringArray(obj.getMonths()));
				if(arraySize < obj.getMonths().length) {
					arraySize = obj.getMonths().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPlanneds()) && obj.getPlanneds().length > 0) {
				obj.setPlanneds(CommonMethods.replaceEmptyByNullInSringArray(obj.getPlanneds()));
				if(arraySize < obj.getPlanneds().length) {
					arraySize = obj.getPlanneds().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getActuals()) && obj.getActuals().length > 0) {
				obj.setActuals(CommonMethods.replaceEmptyByNullInSringArray(obj.getActuals()));
				if(arraySize < obj.getActuals().length) {
					arraySize = obj.getActuals().length;
				}  
			}
			if( !StringUtils.isEmpty(obj.getPayment_receiveds()) && obj.getPayment_receiveds().length > 0) {
				obj.setPayment_receiveds(CommonMethods.replaceEmptyByNullInSringArray(obj.getPayment_receiveds()));
				if(arraySize < obj.getPayment_receiveds().length) {
					arraySize = obj.getPayment_receiveds().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPlanned_units()) && obj.getPlanned_units().length > 0) {
				obj.setPlanned_units(CommonMethods.replaceEmptyByNullInSringArray(obj.getPlanned_units()));
				if(arraySize < obj.getPlanned_units().length) {
					arraySize = obj.getPlanned_units().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getActual_units()) && obj.getActual_units().length > 0) {
				obj.setActual_units(CommonMethods.replaceEmptyByNullInSringArray(obj.getActual_units()));
				if(arraySize < obj.getActual_units().length) {
					arraySize = obj.getActual_units().length;
				}
			}
			if( !StringUtils.isEmpty(obj.getPayment_received_units()) && obj.getPayment_received_units().length > 0) {
				obj.setPayment_received_units(CommonMethods.replaceEmptyByNullInSringArray(obj.getPayment_received_units()));
				if(arraySize < obj.getPayment_received_units().length) {
					arraySize = obj.getPayment_received_units().length;
				}
			}
			if(!StringUtils.isEmpty(obj.getMonths()) && obj.getMonths().length > 0) {
				for (int i = 0; i < arraySize; i++) {
					String fId = obj.getIDs()[i];
					if(!StringUtils.isEmpty(fId)) {
						 int k =1;
						 String date = obj.getMonths()[i] + "-01";
						 updateStmt.setString(k++,(date));
						 updateStmt.setString(k++,(obj.getPlanneds().length > 0)?obj.getPlanneds()[i]:null);
						 updateStmt.setString(k++,(obj.getActuals().length > 0)?obj.getActuals()[i]:null);
						 updateStmt.setString(k++,(obj.getPayment_receiveds().length > 0)?obj.getPayment_receiveds()[i]:null);
						 updateStmt.setString(k++,CommonConstants.ACTIVE);
						 updateStmt.setString(k++,(obj.getPlanned_units().length > 0)?obj.getPlanned_units()[i]:null);
						 updateStmt.setString(k++,(obj.getActual_units().length > 0)?obj.getActual_units()[i]:null);
						 updateStmt.setString(k++,(obj.getPayment_received_units().length > 0)?obj.getPayment_received_units()[i]:null);
						 updateStmt.setString(k++,obj.getIDs()[i]);
						 updateStmt.addBatch();
	
					}else {
						
						 int t = 1;
						 if( obj.getMonths().length > 0 && !StringUtils.isEmpty(obj.getMonths()[i])) {
							 String date = obj.getMonths()[i] + "-01";
							 insertStmt.setString(t++,obj.getContract_id_fk());
							 insertStmt.setString(t++,(date));
							 insertStmt.setString(t++,(obj.getPlanneds().length > 0)?obj.getPlanneds()[i]:null);
							 insertStmt.setString(t++,(obj.getActuals().length > 0)?obj.getActuals()[i]:null);
							 insertStmt.setString(t++,(obj.getPayment_receiveds().length > 0)?obj.getPayment_receiveds()[i]:null);
							 insertStmt.setString(t++,CommonConstants.ACTIVE);
							 insertStmt.setString(t++,(obj.getPlanned_units().length > 0)?obj.getPlanned_units()[i]:null);
							 insertStmt.setString(t++,(obj.getActual_units().length > 0)?obj.getActual_units()[i]:null);
							 insertStmt.setString(t++,(obj.getPayment_received_units().length > 0)?obj.getPayment_received_units()[i]:null);
							 insertStmt.addBatch();
						 }
					}
				}
			}
			int[] insertCount = insertStmt.executeBatch();
			int[] updateCount = updateStmt.executeBatch();
			if(insertCount.length > 0 || updateCount.length > 0) {
				flag = true;
				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getUser_id());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Works");
				formHistory.setForm_name("Update TA Financial");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("TA Financial "+obj.getContract_id_fk() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
				/********************************************************************************/
			}
			DBConnectionHandler.closeJDBCResoucrs(null, insertStmt, null);
			con.commit();
	   }catch(Exception e){ 
			con.rollback();
			e.printStackTrace();
			throw new Exception(e);
	   }
	   finally {
		DBConnectionHandler.closeJDBCResoucrs(con, updateStmt, null);
	  }		
	   return flag;	
   }

	@Override
	public List<TAFinancials> getContractsList() throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry ="select work_id as work_id_fk,contract_id as contract_id_fk,contract_name,contract_short_name from contract c "
					+ "LEFT JOIN work w on c.work_id_fk = w.work_id ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));	
		}catch(Exception e){ 
		throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TAFinancials> getWorkListForFinancialsForm(TAFinancials obj) throws Exception {
		List<TAFinancials> objsList = new ArrayList<TAFinancials>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name,project_id_fk,project_name "
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
		
	}

	@Override
	public List<TAFinancials> getContractsListForFinancialsForm(TAFinancials obj) throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry ="select contract_id as contract_id_fk,work_id_fk,contract_name,contract_short_name "
					+ "from contract c "
					+ "where contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			qry = qry + " order by contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public int getTotalRecords(TAFinancials obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry ="SELECT  count(DISTINCT contract_id_fk) as total_records FROM ta_financials t " + 
					" left join contract c on c.contract_id = t.contract_id_fk " + 
					" left join work w on c.work_id_fk = w.work_id where DATE(month) <= DATE(CONVERT(date, getdate())) and t.status = ? ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.work_id_fk like ? or w.work_short_name like ? or contract_id_fk like ? or contract_short_name like ?"
						+ " or planned like ? or actual like ? or payment_received like ? )";
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
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
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
				throw new Exception(e);
			}
			return totalRecords;
	}

	@Override
	public List<TAFinancials> getTAFinancialsList(TAFinancials obj, int startIndex, int offset, String searchParameter)
			throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry ="SELECT ID as financial_id, work_id as work_id_fk ,w.work_name,c.contract_short_name,w.work_short_name,c.contract_short_name, contract_id_fk, month, sum(planned) as planned, sum(actual) as actual, sum(payment_received) as payment_received " + 
					" FROM ta_financials t " + 
					" left join contract c on c.contract_id = t.contract_id_fk " + 
					" left join work w on c.work_id_fk = w.work_id where CONVERT(datetime, MONTH, 103) <= CONVERT(datetime, getdate(), 103) ";
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ? ";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.work_id_fk like ? or w.work_short_name like ? or contract_id_fk like ? or contract_short_name like ? "
						+ " or planned like ? or actual like ? or payment_received like ? )";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}	
			
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				qry = qry + " group by ID,WORK_ID,WORK_NAME,contract_short_name,W.work_short_name,contract_id_fk,MONTH ";
				arrSize++;
				arrSize++;
			}	
			
			Object[] pValues = new Object[arrSize];
			

			int i = 0;
			
			pValues[i++] = CommonConstants.ACTIVE;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(searchParameter)) {
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
		    objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));

		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<TAFinancials> getUnitsList() throws Exception {
		List<TAFinancials> objsList = null;
		try {
			String qry = "select id, unit, value from money_unit";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<TAFinancials>(TAFinancials.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
