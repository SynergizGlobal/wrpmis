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
			String qry = "SELECT distinct min(F.ID) as fortnightly_plan_id,category,contract_short_name,structure_type as structure_type_fk,structure,\r\n" + 
					"cast(max(isnull(s_cum_planned_till_date,0)) as varchar) as cum_planned_last_st,\r\n" + 
					"cast(max(isnull(s_cum_actual_till_date,0)) as varchar) as cum_actual_last_st,\r\n" + 
					"cast(max(isnull(s_planned_current_fortnight,0)) as varchar) as planned_current_st,\r\n" + 
					"\r\n" + 
					"cast(max(isnull(s_actual_current_fortnight,0)) as varchar)  as actual_current_st,0 as data_id,case when isnull([float],0)<=15 then 'red' when DATEDIFF(day,getdate(),expected_finish)<=30 then 'orange' else 'black' end as color \r\n" + 
					"from fortnight_temp f \r\n" + 
					"LEFT JOIN contract c ON c.contract_id  = f.contract_id_fk \r\n" + 
					"LEFT JOIN work w on c.work_id_fk =w.work_id \r\n" + 
					"where f.status='Active' " ;
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				qry = qry + " and ( "
						+ "structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id from contract where (hod_user_id_fk = ? or dy_hod_user_id_fk = ?) group by contract_id) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.contract_id_fk in(select contract_id_fk from contract_executive where executive_user_id_fk = ? group by contract_id_fk) group by structure_id_fk) "
						+ "or structure in (select structure from structure_contract_responsible_people s inner join structure s1 on s1.structure_id=s.structure_id_fk where s.responsible_people_id_fk = ? group by structure_id_fk)) ";
				arrSize++;
				arrSize++;
				arrSize++;
				arrSize++;
			}			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and f.contract_id_fk = ? ";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and c.work_id_fk = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCategory())) {
				qry = qry + " and category = ? ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical_item())) {
				qry = qry + " and critical_item = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				String Str[]=obj.getPeriod().split("_");
				if(Str.length==2)
				{
					qry = qry + " and fortnight_start = ? and fortnight_finish=? ";
					arrSize++;
					arrSize++;
				}
				else
				{
					qry = qry + " and (cast(fortnight_start as varchar) = ? or cast(fortnight_finish as varchar)=?) ";
					arrSize++;
					arrSize++;					
				}

			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical())) {
				
				if(obj.getCritical().compareTo("Yes")==0)
				{
					qry = qry + "  and isnull([float],0)<=15 ";
				}
				else if(obj.getCritical().compareTo("No")==0)
				{
					qry = qry + "  and isnull([float],0)>15 ";
				}				
			}			
			
			qry = qry + " group by category,contract_short_name,structure_type,structure,expected_finish,[float]  ";
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) {
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
				pValues[i++] = obj.getUser_id();
			}			

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
				String Str[]=obj.getPeriod().split("_");
				if(Str.length==2)
				{
					pValues[i++] = Str[0];
					pValues[i++] = Str[1];
				}
				else
				{
					pValues[i++] = obj.getPeriod();
					pValues[i++] = obj.getPeriod();
				}
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCritical())) {
				//pValues[i++] = obj.getCritical();
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
		
			String qry = "SELECT distinct category  "
					+ "from fortnight_temp f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "where f.status='Active' and category is not null " ;			
		
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlan(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "SELECT  work_id as work_id_fk,ID AS fortnightly_plan_id,t.contract_id_fk,t.category,t.structure_type as total_items,cum_planned_last_structure,\r\n" + 
					"					cum_actual_last_structure, planned_current_structure,t.structure,t.component					\r\n" + 
					"					\r\n" + 
					"					from fortnight_temp t\r\n" + 
					"\r\n" + 
					"					inner join (SELECT  w.work_id as work_id_fk,ID AS fortnightly_plan_id,f.contract_id_fk,category,structure_type as critical_item,sum(cast(isnull(planned_last_fortnight,0) as decimal(10,2))) as cum_planned_last_structure,\r\n" + 
					"					sum(cast(isnull(actual_last_fortnight,0) as decimal(10,2))) as cum_actual_last_structure,sum(cast(isnull(planned_current_fortnight,0) as decimal(10,2))) as planned_current_structure,structure,component\r\n" + 
					"					from fortnight_temp f \r\n" + 
					"					LEFT join contract c ON c.contract_id  = f.contract_id_fk\r\n" + 
					"					LEFT JOIN work w on c.work_id_fk =w.work_id \r\n" + 
					"					where f.ID = "+obj.getFortnightly_plan_id()+" and f.status='Active' \r\n" + 
					"					group by w.work_id,f.ID,f.contract_id_fk,f.category,structure,component,structure_type) as a\r\n" + 
					"\r\n" + 
					"					on a.contract_id_fk=t.contract_id_fk and a.work_id_fk=t.work_id and a.critical_item=t.structure_type and a.category=t.category and a.structure=t.structure and t.status='Active' ";

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
			String qry = "UPDATE fortnight_temp SET remarks=? WHERE ID = ? ";	
			
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
					+ "from fortnight_temp f "
					+ "LEFT JOIN contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "where f.status='Active' " ;
			
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
					+ "from fortnight_temp f "
					+ "LEFT JOIN contract c ON c.contract_id  = f.contract_id_fk "
					+ "where f.status='Active'" ;

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
			String qry = "select critical_item as module_name from critical_item";			
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
		
			String qry = "SELECT FORMAT (fortnight_start, 'dd MMMM, yy')+'-'+FORMAT (fortnight_finish, 'dd MMMM, yy') as period,cast(fortnight_start as varchar)+'_'+cast(fortnight_finish as varchar) as period_value "
					+ "from fortnight_temp f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "where f.status='Active' " ;			

			
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}
 
	@Override
	public List<FortnightPlan> getWorksListFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "select distinct work_id_fk,work_short_name from (SELECT w.work_id as work_id_fk,w.work_short_name  "
					+ "from fortnight_temp f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "where f.status='Active' " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry+ " group by w.work_id,w.work_short_name union all\r\n" + 
					"select w.work_id as work_id_fk,w.work_short_name    from fortnightly_plan_update_data f LEFT JOIN work w on f.work_id =w.work_id group by w.work_id,w.work_short_name) as a ";
			
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
		
			String qry = "select distinct period,period_value from(SELECT distinct FORMAT (fortnight_start, 'dd MMMM, yy')+'-'+FORMAT (fortnight_finish, 'dd MMMM, yy') as period,cast(fortnight_start as varchar)+'_'+cast(fortnight_finish as varchar) as period_value  "
					+ "from fortnight_temp f "
					+ "LEFT join contract c ON c.contract_id  = f.contract_id_fk "
					+ "LEFT JOIN work w on c.work_id_fk =w.work_id "
					+ "where f.status='Active' " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getContract_id_fk())) {
				qry = qry + " and contract_id_fk = ?";
				arrSize++;
			}	
			qry = qry + " ) as a ";

			
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
	
	private int getMaxDataId() throws Exception
	{
		int DataId=0;
		try {
			String qry = "select case when count(*)=0 then 1 else max(data_id)+1 end from fortnightly_plan_update_data";
			DataId = (int) jdbcTemplate.queryForObject(qry, int.class);
		} catch (Exception e) {
			throw new Exception(e);
		}		
		return DataId;
	}	

	@Override
	public boolean updateFortnightlyPlan(FortnightPlan obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Connection connection = null;
		PreparedStatement updateStmt = null;	
		
		NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
		
		String deleteQry = "DELETE from fortnightly_plan_update_data where data_id = :data_id";		 
		paramSource = new BeanPropertySqlParameterSource(obj);		 
		int count = namedParamJdbcTemplate.update(deleteQry, paramSource);		
		

		String query = " insert into fortnightly_plan_update_data (work_id, category, critical_item, period, structure, activity, scope_of_work, planned_progress_on_last_fortnight, actual_progress_on_last_fortnight, plan_for_the_current_fortnight, completion_status, remarks,data_id)"
	               + " values (?,?,?, ?, ?, ?,?,?,?, ?, ?, ?,?)";

	  PreparedStatement preparedStmt = null;
	  Connection con = null;
	  try
	  {
		con = dataSource.getConnection();
		preparedStmt = con.prepareStatement(query);
	    
		String Str2[]=obj.getRemarks().split(",");
		String Str3[]=obj.getRemarks().split(",");

		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getRemarks())) 
		{
			int dataid=getMaxDataId();
			for (int i = 0; i < obj.getActivity().length; i++) 
			{
		      preparedStmt.setString(1, obj.getWork_id_fk());
		      preparedStmt.setString(2, obj.getCategory()!=null ?obj.getCategory():null);
		      preparedStmt.setString(3, obj.getCritical_item()[i]);
		      preparedStmt.setString(4, obj.getPeriod());
		      preparedStmt.setString(5, obj.getStructure());
		      preparedStmt.setString(6, obj.getActivity()[i]);
		      preparedStmt.setString(7, obj.getScope_of_work()[i]);
		      preparedStmt.setString(8, obj.getPlanned_progress_on_last_fortnight()[i]);
		      preparedStmt.setString(9, obj.getActual_progress_on_last_fortnight()[i]);
		      preparedStmt.setString(10, obj.getPlan_for_the_current_fortnight()[i]);
		      preparedStmt.setString(11, obj.getChkcompletion_status()[i]);
		      preparedStmt.setString(12, Str3[i]);
		      preparedStmt.setInt(13, dataid);
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

			 String qry="SELECT distinct fortnightly_plan_update_data_id as fortnightly_plan_id,w.work_id as work_id_fk,critical_item as total_items,u.category,'' as contract_short_name,structure,'' as structure_type_fk, " + 
						"planned_progress_on_last_fortnight as cum_planned_last_st,actual_progress_on_last_fortnight as cum_actual_last_st, " + 
						"plan_for_the_current_fortnight as planned_current_st,activity as activity_name,scope_of_work as scope,completion_status,u.remarks,data_id,period  from fortnightly_plan_update_data u LEFT JOIN work w on u.work_id =w.work_id where u.data_id = "+obj.getFortnightly_plan_id();
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightPlanModuleCategoryList() throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "select module_name from module " ;			
		
			objsList = jdbcTemplate.query(qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public boolean refreshExecutionActivities(String userId) throws Exception {
		boolean flag=false;
		Connection connection = null;
		java.sql.CallableStatement statement = null;
		ResultSet resultSet = null;
		try{
				connection = dataSource.getConnection();	
				logger.error("callingStoredProcedures fortnight :"+ new Date());	
				String qry1 = "exec dbo.[fortnight] ?";			
				statement = connection.prepareCall(qry1);
				statement.setString(1, userId);
				statement.executeQuery();
				flag=true;
				DBConnectionHandler.closeJDBCResoucrs(null, statement, resultSet);
				logger.error("callingStoredProcedures Ends fortnight :"+ new Date());	

		}catch(Exception e){ 
		}finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, statement, resultSet);
		}
		return true;	
	}

	@Override
	public boolean saveFortnightDataUploadFile(FortnightPlan obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FortnightPlan> getFortnightQuarterlyPlanItemList() throws Exception {
		List<FortnightPlan> objsList = new ArrayList<FortnightPlan>();
		try {
			String qry = "select distinct item "
					+ "from fortnight_quarterly_plan where item is not null ";
			
			qry = qry + " order by item asc";
			objsList = jdbcTemplate.query( qry,  new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightQuarterlyPlanPeriodList() throws Exception {
		List<FortnightPlan> objsList = new ArrayList<FortnightPlan>();
		try {
			String qry = "select distinct period "
					+ "from fortnight_quarterly_plan ";
			
			qry = qry + " order by period asc";
			objsList = jdbcTemplate.query( qry,  new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
			
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean insertQuarterlyPlan(FortnightPlan obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Connection connection = null;
		PreparedStatement updateStmt = null;	

		String query = " insert into fortnight_quarterly_plan (work_id_fk, period, structure, item, criticality, TDC, scope_of_work)"
	               + " values (?,?,?, ?, ?, ?,?)";
		

		String queryChild = " insert into fortnight_quarterly_plan_activities (fortnight_quarterly_plan_id,fortnight,activity_name,units,cumulative_progress)"
	               + " values (?,?,?,?,?)";

	  PreparedStatement preparedStmt = null;
	  PreparedStatement preparedStmtChild = null;
	  Connection con = null;
	  try
	  {
		  long Key=0;
		con = dataSource.getConnection();
		preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		preparedStmtChild = con.prepareStatement(queryChild);

	      preparedStmt.setString(1, obj.getWork_id_fk());
	      preparedStmt.setString(2, obj.getPeriod()!=null ?obj.getPeriod():null);
	      preparedStmt.setString(3, obj.getStructure());
	      preparedStmt.setString(4, obj.getItem());
	      preparedStmt.setString(5, obj.getCriticality());
	      preparedStmt.setString(6, obj.getTdc_calendar());
	      preparedStmt.setString(7, obj.getScope_of_work_quarterly());
	      preparedStmt.execute();
	      
			ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
			if (generatedKeys.next()) 
			{
				Key=generatedKeys.getLong(1);
          }
			if(preparedStmt != null){preparedStmt.close();}	      

		

		if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFortnight())) 
		{
			for (int i = 0; i < obj.getActivity().length; i++) 
			{
				preparedStmtChild.setLong(1, Key);
				preparedStmtChild.setString(2, obj.getFortnight()[i]);
				preparedStmtChild.setString(3, obj.getActivity()[i]);
				preparedStmtChild.setString(4, obj.getUnits()[i]);
				preparedStmtChild.setString(5, obj.getCumulative_progress()[i]);				
				preparedStmtChild.execute();
			}
		}
		
		if(preparedStmtChild != null){preparedStmtChild.close();}	

	
			FormHistory formHistory = new FormHistory();
			formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
			formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
			formHistory.setModule_name_fk("FortnightPlan");
			formHistory.setForm_name("Insert Fortnight Quarterly Plan ");
			formHistory.setForm_action_type("Insert");
			formHistory.setForm_details("Fortnight Quarterly Plan  "+obj.getFortnight_quarterly_plan_id() + " Updated");
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
	public List<FortnightPlan> getWorksListQuarterlyFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "select distinct w.work_id as work_id_fk,w.work_short_name from fortnight_quarterly_plan p\r\n" + 
					"left join fortnight_quarterly_plan_activities a on a.fortnight_quarterly_plan_id=p.fortnight_quarterly_plan_id\r\n" + 
					"LEFT JOIN work w on p.work_id_fk =w.work_id\r\n" + 
					"where p.work_id_fk is not null and fortnight is not  null and pending_progress is null and reason_for_shortfall is null " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFortnight_date())) {
				qry = qry + " and fortnight = ?";
				arrSize++;
			}
			qry = qry+ " group by w.work_id,w.work_short_name ";
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getFortnight_date())) {
				pValues[i++] = obj.getFortnight_date();
			}			
			objsList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<FortnightPlan> getPeriodListQuarterlyFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "select distinct period from fortnight_quarterly_plan p\r\n" + 
					"left join fortnight_quarterly_plan_activities a on a.fortnight_quarterly_plan_id=p.fortnight_quarterly_plan_id\r\n" + 
					"LEFT JOIN work w on p.work_id_fk =w.work_id\r\n" + 
					"where p.work_id_fk is not null and fortnight is not  null and pending_progress is null and reason_for_shortfall is null " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFortnight_date())) {
				qry = qry + " and fortnight = ?";
				arrSize++;
			}			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getFortnight_date())) {
				pValues[i++] = obj.getFortnight_date();
			}			
			objsList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<FortnightPlan> getItemListQuarterlyFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "SELECT distinct item  "
					+ "from fortnight_quarterly_plan f "
					+ "LEFT JOIN work w on f.work_id_fk =w.work_id "
					+ "where item is not null " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			objsList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightQuarterlyPlanList(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "SELECT distinct p.fortnight_quarterly_plan_id as fortnight_quarterly_plan_id,work_id_fk,item,criticality,scope_of_work as scope_of_work_quarterly,TDC as tdc_calendar " + 
					"from fortnight_quarterly_plan p\r\n" + 
					"left join fortnight_quarterly_plan_activities a on a.fortnight_quarterly_plan_id=p.fortnight_quarterly_plan_id\r\n" + 
					"LEFT JOIN work w on p.work_id_fk =w.work_id\r\n" + 
					"where p.work_id_fk is not null and fortnight is not null and pending_progress is null and reason_for_shortfall is null ";
			int arrSize = 0;
			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and p.work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getItem())) {
				qry = qry + " and item = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCriticality())) {
				qry = qry + " and criticality = ?";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;

			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;

			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getItem())) {
				pValues[i++] = obj.getItem();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getCriticality())) {
				pValues[i++] = obj.getCriticality();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
					pValues[i++] = obj.getPeriod();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<FortnightPlan> getFortnightListQuarterlyFilter(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
		
			String qry = "select distinct fortnight from fortnight_quarterly_plan p\r\n" + 
					"left join fortnight_quarterly_plan_activities a on a.fortnight_quarterly_plan_id=p.fortnight_quarterly_plan_id\r\n" + 
					"LEFT JOIN work w on p.work_id_fk =w.work_id\r\n" + 
					"where p.work_id_fk is not null and fortnight is not  null and pending_progress is null and reason_for_shortfall is null " ;			
			
			int arrSize =0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFortnight_date())) {
				qry = qry + " and fortnight = ?";
				arrSize++;
			}			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getFortnight_date())) {
				pValues[i++] = obj.getFortnight_date();
			}			
			objsList = jdbcTemplate.query(qry, pValues,new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return objsList;
	}

	@Override
	public List<FortnightPlan> getfortnightActivities(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {
			String qry = "SELECT p.fortnight_quarterly_plan_id as fortnightly_plan_id,structure,units,cumulative_progress,activity_name,item,criticality,scope_of_work as scope_of_work_quarterly,TDC as tdc_calendar,isnull(pending_progress,'') as pending_progress,isnull(reason_for_shortfall,'')  as reason_for_shortfall " + 
					"from fortnight_quarterly_plan p\r\n" + 
					"left join fortnight_quarterly_plan_activities a on a.fortnight_quarterly_plan_id=p.fortnight_quarterly_plan_id\r\n" + 
					"LEFT JOIN work w on p.work_id_fk =w.work_id\r\n" + 
					"where p.work_id_fk is not null and fortnight is not null and pending_progress is null and reason_for_shortfall is null " ;
			int arrSize = 0;
			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getWork_id_fk())) {
				qry = qry + " and work_id_fk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getPeriod())) {
				qry = qry + " and period = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getFortnight_date())) {
				qry = qry + " and fortnight = ?";
				arrSize++;
			}

			Object[] pValues = new Object[arrSize];
			int i = 0;
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getWork_id_fk())) {
				pValues[i++] = obj.getWork_id_fk();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getPeriod())) {
				pValues[i++] = obj.getPeriod();
			}
			if (!StringUtils.isEmpty(obj)&& !StringUtils.isEmpty(obj.getFortnight_date())) {
				pValues[i++] = obj.getFortnight_date();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public boolean updateQuarterlyPlanActivities(FortnightPlan obj) throws Exception {
		boolean flag = false;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		Connection connection = null;
		PreparedStatement updateStmt = null;		
		try {
			connection = dataSource.getConnection();
			String qry = "UPDATE fortnight_quarterly_plan_activities SET pending_progress=?,reason_for_shortfall=? WHERE fortnight_quarterly_plan_activity_id = ? ";
			
			
			for (int i = 0; i < obj.getPending_progress().length; i++) 
			{
				updateStmt = connection.prepareStatement(qry);
				updateStmt.setString(1, obj.getPending_progress()[i]);
				updateStmt.setString(2, obj.getReason_for_shortfall()[i]);
				updateStmt.setString(3, obj.getFortnight_quarterly_plan_activity_id()[i]);
				updateStmt.executeUpdate();
				flag=true;
			}			

				FormHistory formHistory = new FormHistory();
				formHistory.setCreated_by_user_id_fk(obj.getCreated_by_user_id_fk());
				formHistory.setUser(obj.getDesignation()+" - "+obj.getUser_name());
				formHistory.setModule_name_fk("Quarterly Fortnight Plan");
				formHistory.setForm_name("Update Quarterly Plan Activities ");
				formHistory.setForm_action_type("Update");
				formHistory.setForm_details("Quarterly Fortnight Plan  "+obj.getFortnight_quarterly_plan_activity_id() + " Updated");
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
	public List<FortnightPlan> getQuarterlyPlanManual(FortnightPlan obj) throws Exception {
		List<FortnightPlan> objsList = null;
		try {

			String qry = "SELECT p.fortnight_quarterly_plan_id as fortnightly_plan_id,fortnight as fortnight_date,work_id_fk,period,structure,units as unit,cumulative_progress as cum_progress,activity_name,item,criticality,scope_of_work as scope_of_work_quarterly,TDC as tdc_calendar,isnull(pending_progress,'') as pending_progress,isnull(reason_for_shortfall,'')  as reason_for_shortfall " + 
					"from fortnight_quarterly_plan p\r\n" + 
					"left join fortnight_quarterly_plan_activities a on a.fortnight_quarterly_plan_id=p.fortnight_quarterly_plan_id\r\n" + 
					"LEFT JOIN work w on p.work_id_fk =w.work_id\r\n" + 
					"where p.work_id_fk is not null and fortnight is not null and pending_progress is null and reason_for_shortfall is null and p.fortnight_quarterly_plan_id="+obj.getFortnightly_plan_id();
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<FortnightPlan>(FortnightPlan.class));	

		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

}
