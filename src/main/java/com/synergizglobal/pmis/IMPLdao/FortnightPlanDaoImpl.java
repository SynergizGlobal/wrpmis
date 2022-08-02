package com.synergizglobal.pmis.IMPLdao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Idao.FortnightPlanDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.common.EMailSender;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.Mail;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.Design;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.Issue;
import com.synergizglobal.pmis.model.Messages;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.FortnightPlan;
import com.synergizglobal.pmis.model.SourceOfFund;

@Repository
public class FortnightPlanDaoImpl implements FortnightPlanDao {
	public static Logger logger = Logger.getLogger(FortnightPlanDaoImpl.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Override
	public List<FortnightPlan> getFortnightPlanList(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "SELECT distinct f.fortnightly_plan_id,category,contract_short_name,structure_type_fk,structure,sum(cast(cum_planned_last_st as decimal(10,2))) as cum_planned_last_st,sum(cast(cum_actual_last_st as decimal(10,2))) as cum_actual_last_st,sum(cast(planned_current_st as decimal(10,2))) as planned_current_st "
					+ "from fortnightly_plan f "
					+ "LEFT JOIN contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT join structure st ON st.structure_id  = s.structure_id_fk "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where f.fortnightly_plan_id is not null and u.status='Active'" ;
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				qry = qry + " and category = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				qry = qry + " and critical_item = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}
			
			qry = qry + " group by f.fortnightly_plan_id,category,contract_short_name,structure,structure_type_fk,structure";
			
			
			qry = qry + " union all ";


			qry = qry +" SELECT distinct fortnightly_plan_update_data_id as fortnightly_plan_id,u.category,'' as contract_short_name,structure,'' as structure_type_fk, " + 
					"planned_progress_on_last_fortnight as cum_planned_last_st,actual_progress_on_last_fortnight as cum_actual_last_st, " + 
					"plan_for_the_current_fortnight as planned_current_st  " + 
					"from fortnightly_plan_update_data u " + 
					"LEFT JOIN work w on u.work_id =w.work_id  " + 
					"where 0=0 "; 	
			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and u.work_id = ? ";
				arrSize++;
			}		
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				pValues[i++] = obj.getCategory();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				pValues[i++] = obj.getCritical_item();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanCategoryList() throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "select module_name from module ";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlan(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "SELECT distinct w.work_id as work_id_fk,fortnightly_plan_update_id,f.contract_id_fk,category,critical_item,period,st.structure,component,sum(cast(cum_planned_last_structure as decimal(10,2))) as cum_planned_last_structure,\r\n" + 
						"sum(cast(cum_actual_last_structure as decimal(10,2))) as cum_actual_last_structure,\r\n" + 
						"sum(cast(planned_current_structure as decimal(10,2))) as planned_current_structure  "
					+ "from fortnightly_plan f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT join structure st ON st.structure_id  = s.structure_id_fk "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where f.fortnightly_plan_id = "+obj.getFortnightly_plan_id()+" and u.status='Active' group by fortnightly_plan_update_id,w.work_id,f.contract_id_fk,category,critical_item,period,st.structure,component";

			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateFortnightPlan(FortnightPlan obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Connection connection = null;
		PreparedStatement updateStmt = null;		
		try {
			connection = dataSource.getConnection();
			String qry = "UPDATE fortnightly_plan_update SET remarks=? WHERE fortnightly_plan_update_id = ? ";	
			
			String Str2[]=obj.getRemarks().split(",");
			String Str1[]=obj.getFortnightly_plan_update_id().split(",");			

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRemarks())) 
			{
				for (int i = 0; i < Str2.length; i++) 
				{
	
						String Remark=Str2[i].trim();
						if(!StringUtils.isEmpty(Remark) && !StringUtils.isEmpty(Remark))
						{
							updateStmt = connection.prepareStatement(qry);
							updateStmt.setString(1, Remark);
							updateStmt.setString(2, Str1[i]);
							updateStmt.executeUpdate();
							flag=true;
						}
				}
			}

				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("FortnightPlan");
				formHistory.setForm_name("Update FortnightPlan ");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("FortnightPlan  "+obj.getFortnightly_plan_id() + " Updated");
				formHistory.setWork_id_fk(obj.getWork_id_fk());
				formHistory.setContract_id_fk(obj.getContract_id_fk());
				
				flag = formsHistoryDao.saveFormHistory(formHistory);

			transactionManager.commit(status);
		}
		catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, updateStmt, null);
		}		

		return flag;
	}
	
	@Override
	public int getTotalRecords(FortnightPlan obj, String searchParameter) throws Exception {
		int totalRecords = 0;
		try {
			String qry = "SELECT count(*) as total_records "
					+ "from fortnightly_plan f "
					+ "LEFT JOIN contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where fortnightly_plan_id is not null and u.status='Active'" ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				qry = qry + " and category = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				qry = qry + " and critical_item = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}

			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.contract_id like ? or c.contract_short_name like ? or category like ? or critical_item like ? "
						+ "or period like ? or work_id_fk like ? or work_short_name like ? or total_items like ?)";
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

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				pValues[i++] = obj.getCategory();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				pValues[i++] = obj.getCritical_item();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
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
			}
			totalRecords = jdbcTemplate.queryForObject( qry,pValues,Integer.class);
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return totalRecords;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanList(FortnightPlan obj, int startIndex, int offset, String searchParameter) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "SELECT *  "
					+ "from fortnightly_plan f "
					+ "LEFT JOIN contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where fortnightly_plan_id is not null and u.status='Active'" ;

			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				qry = qry + " and category = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				qry = qry + " and critical_item = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(searchParameter)) {
				qry = qry + " and (c.contract_id like ? or c.contract_short_name like ? or category like ? or critical_item like ? "
						+ "or period like ? or work_id_fk like ? or work_short_name like ? or total_items like ?)";
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
				qry = qry + " order by fortnightly_plan_id ASC offset ? rows  fetch next ? rows only";
				arrSize++;
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				pValues[i++] = obj.getCategory();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				pValues[i++] = obj.getCritical_item();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
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
			}
			if(!StringUtils.isEmpty(startIndex) && !StringUtils.isEmpty(offset)) {
				pValues[i++] = startIndex;
				pValues[i++] = offset;
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanWorkList() throws Exception {
		List<FortnightPlan> objsList = new ArrayList<FortnightPlan>();
		try {
			String qry = "select work_id as work_id_fk,work_name,work_short_name "
					+ "from work w "
					+ "LEFT OUTER JOIN project p ON project_id_fk = project_id "
					+ "where work_id is not null ";
			
			qry = qry + " order by work_id asc";
			objsList = jdbcTemplate.query( qry,  new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanContractList(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry ="select distinct c.contract_id as contract_id_fk,c.hod_user_id_fk,c.contract_name,c.contract_short_name,c.work_id_fk "
					+ "from contract c "
					+ "left join contract_executive c1 on c1.contract_id_fk = c.contract_id "
					+ "LEFT JOIN [user] u ON c.hod_user_id_fk= u.user_id "
					+ "where c.contract_id is not null ";
			
			int arrSize = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					qry = qry + " and c1.department_id_fk = ? and u.department_fk = ?";
					arrSize++;
					arrSize++;
				}
			}			
			qry = qry + " order by c.contract_id asc";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					pValues[i++] = obj.getDepartment_fk();
					pValues[i++] = obj.getDepartment_fk();
				
				}
			}			
				
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
				
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanCriticalItemList() throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "select structure_type as critical_item from structure_type";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanPeriodList() throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "select module_name from module";			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
 
	@Override
	public List<FortnightPlan> getWorksListFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "SELECT w.work_id as work_id_fk,w.work_short_name  "
					+ "from fortnightly_plan f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where f.fortnightly_plan_id is not null and u.status='Active' " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry+ " group by w.work_id,w.work_short_name";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			objsList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<FortnightPlan> getContractListFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objList = null;
		try {
			String qry = "SELECT f.contract_id_fk,c.contract_short_name  "
					+ "from fortnightly_plan f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where f.fortnightly_plan_id is not null and u.status='Active' " ;
			
			int arrSize=0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry+" group by f.contract_id_fk,c.contract_short_name";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			objList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));

			
		} catch (Exception e) {
			throw new Exception(e);
		}
		return objList;
	}

	@Override
	public List<FortnightPlan> getPeriodListFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "SELECT period  "
					+ "from fortnightly_plan f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "LEFT JOIN fortnightly_plan_structure s ON s.fortnightly_plan_id  = f.fortnightly_plan_id "
					+ "LEFT JOIN fortnightly_plan_update u ON u.fortnightly_plan_structure_id  = s.fortnightly_plan_structure_id  "
					+ "where f.fortnightly_plan_id is not null AND period is not null and u.status='Active'" ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry+ " group by period";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				pValues[i++] = obj.getContract_id_fk();
			}
			
			objsList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public boolean updateFortnightlyPlan(FortnightPlan obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Connection connection = null;
		PreparedStatement updateStmt = null;	

		String query = " insert into fortnightly_plan_update_data (work_id, category, critical_item, period, structure, activity, scope_of_work, planned_progress_on_last_fortnight, actual_progress_on_last_fortnight, plan_for_the_current_fortnight, completion_status, remarks)"
	               + " values (?,?,?, ?, ?, ?,?,?,?, ?, ?, ?)";

	  PreparedStatement preparedStmt = null;
	  Connection con = null;
	  try
	  {
		con = dataSource.getConnection();
		preparedStmt = con.prepareStatement(query);
	    
		String Str2[]=obj.getRemarks().split(",");
		String Str1[]=obj.getStructure().split(",");
		String Str3[]=obj.getRemarks().split(",");

		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRemarks())) 
		{
			for (int i = 0; i < obj.getActivity().length; i++) 
			{
		      preparedStmt.setString(1, obj.getWork_id_fk());
		      preparedStmt.setString(2, obj.getCategory()!=null ?obj.getCategory():null);
		      preparedStmt.setString(3, obj.getCritical_item()!=null ?obj.getCritical_item():null);
		      preparedStmt.setString(4, obj.getPeriod());
		      preparedStmt.setString(5, Str1[i]);
		      preparedStmt.setString(6, obj.getActivity()[i]);
		      preparedStmt.setString(7, obj.getScope_of_work()[i]);
		      preparedStmt.setString(8, obj.getPlanned_progress_on_last_fortnight()[i]);
		      preparedStmt.setString(9, obj.getActual_progress_on_last_fortnight()[i]);
		      preparedStmt.setString(10, obj.getPlan_for_the_current_fortnight()[i]);
		      preparedStmt.setString(11, obj.getChkcompletion_status()[i]);
		      preparedStmt.setString(12, Str3[i]);	      
		      preparedStmt.execute();
			}
		}

	
			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
			formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
			formHistory.setModule_name_fk("FortnightPlan");
			formHistory.setForm_name("Update FortnightPlan ");
			formHistory.setForm_action_type("Update");
			formHistory.setForm_details("FortnightPlan  "+obj.getFortnightly_plan_id() + " Updated");
			formHistory.setWork_id_fk(obj.getWork_id_fk());
			formHistory.setContract_id_fk(obj.getContract_id_fk());
			
			flag = formsHistoryDao.saveFormHistory(formHistory);
			transactionManager.commit(status);
		}
		catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, updateStmt, null);
		}		

		return flag;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanManual(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {

			 String qry="SELECT distinct fortnightly_plan_update_data_id as fortnightly_plan_id,w.work_id as work_id_fk,critical_item,u.category,'' as contract_short_name,structure,'' as structure_type_fk, " + 
						"planned_progress_on_last_fortnight as cum_planned_last_st,actual_progress_on_last_fortnight as cum_actual_last_st, " + 
						"plan_for_the_current_fortnight as planned_current_st  from fortnightly_plan_update_data u LEFT JOIN work w on u.work_id =w.work_id where u.fortnightly_plan_update_data_id = "+obj.getFortnightly_plan_id();
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	

}
