package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.UserDao;
import com.synergizglobal.pmis.common.CommonMethods;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.CommonConstants2;
import com.synergizglobal.pmis.model.User;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	
	@Override
	public List<User> getUserRoles() throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_role_name,user_role_code from user_role";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserDepartments() throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select department,department_name from department";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserReportingToList(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_id,designation,user_name from user u where u.user_name not like '%user%' and u.pmis_key_fk not like '%SGS%' ";
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk()) && CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) {
				qry = qry + " and (department_fk = ? or department_fk = ?)";
				arrSize++;
				arrSize++;
			}else if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_fk = ?";
				arrSize++;
			} 
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk()) && CommonConstants.USER_TYPE_HOD.equals(obj.getUser_type_fk())) {
				pValues[i++] = obj.getDepartment_fk();
				pValues[i++] = CommonConstants.DEPARTMENT_ID_MANAGEMENT;
			}else if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			} 
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<User>(User.class));	
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<User> getUserAccessTypes(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_access_type,user_access_table from user_access_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<User> getContractsForUserAccessTypes(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select contract_id as access_value_id,contract_name as access_value_name from contract";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<User> getDepartmentsForUserAccessTypes(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select department as access_value_id,department_name as access_value_name from department";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<User> getModulesForUserAccessTypes(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select module_name as access_value_id,module_name as access_value_name from module";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}
	
	@Override
	public List<User> getWorksForUserAccessTypes(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select work_id as access_value_id,work_name as access_value_name from work";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}	

	@Override
	public List<User> getUsersList(User obj) throws Exception {
		List<User> objsList = new ArrayList<User>();
		try {
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.personal_contact_number as CHAR) as personal_contact_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_type_fk,u.user_image,department_name,usr.designation as reporting_to_name,"
					+ "(select DATE_FORMAT(max(login_date_time),'%d-%m-%Y %h:%i %p') from user_login_details where user_id_fk = u.user_id ) as last_login,"
					+ "(select COUNT(*) from user_login_details where user_id_fk = u.user_id and DATE(login_date_time) >= DATE(NOW()) - INTERVAL 7 DAY) as last7DaysLogins,"
					+ "(select COUNT(*) from user_login_details where user_id_fk = u.user_id and DATE(login_date_time) >= DATE(NOW()) - INTERVAL 30 DAY) as last30DaysLogins "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id is not null" ;
			
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and u.user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and u.reporting_to_id_srfk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			/*pValues[i++] = CommonConstants2.LOGIN_EVENT_TYPE_LOGIN;
			pValues[i++] = CommonConstants2.LOGIN_EVENT_TYPE_LOGIN;
			pValues[i++] = CommonConstants2.LOGIN_EVENT_TYPE_LOGIN;*/
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}
			
			
			qry = qry + " order by case when (u.user_id like '%Dummy%') then 0 else 1 end desc,case when (u.user_name like '%user%')  then 0 else 1 end desc, case when(u.pmis_key_fk like '%SGS%') then 0 else 1 end desc";
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public String addUser(User obj) throws Exception {
		String userId = null;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			
			String user_id = getMaxUserId(obj.getUser_role_code());
			obj.setUser_id(user_id);
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO user"
					+ "(user_id,user_name,password,designation,email_id,mobile_number,personal_contact_number,landline,extension,department_fk,reporting_to_id_srfk,user_type_fk,pmis_key_fk,user_role_name_fk,remarks,user_image) "
					+ "VALUES "
					+ "(:user_id,:user_name,:password,:designation,:email_id,:mobile_number,:personal_contact_number,:landline,:extension,:department_fk,:reporting_to_id_srfk,:user_type_fk,:pmis_key_fk,:user_role_name_fk,:remarks,:user_image)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				userId = user_id;
			}
			if(!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
				obj.setUser_access_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_types()));
			}
			if(!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(obj.getUser_access_values()) && obj.getUser_access_values().length > 0) {
				obj.setUser_access_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_values()));
			}
			
			if(!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {				
				
				String[] types = obj.getUser_access_types();
				String[] values = obj.getUser_access_values();
				
				String qryUserPermissions = "INSERT INTO user_access (user_id_fk,user_access_type_fk,access_value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions,
			            new BatchPreparedStatementSetter() {
			                 
			                @Override
			                public void setValues(PreparedStatement ps, int i) throws SQLException {
			                    ps.setString(1, obj.getUser_id());
			                    ps.setString(2, types.length > 0?types[i]:null);
			                    ps.setString(3, values.length > 0?values[i]:null);			                    
			                }
			                @Override  
			                public int getBatchSize() {
			                	int arraySize = 0;
			    				if(!StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
			    					obj.setUser_access_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_types()));
			    					if(arraySize < obj.getUser_access_types().length) {
			    						arraySize = obj.getUser_access_types().length;
			    					}
			    				}
			    				if(!StringUtils.isEmpty(obj.getUser_access_values()) && obj.getUser_access_values().length > 0) {
			    					obj.setUser_access_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_values()));
			    					if(arraySize < obj.getUser_access_values().length) {
			    						arraySize = obj.getUser_access_values().length;
			    					}
			    				}
			                    return arraySize;
			                }
			            });
				
			}
			transactionManager.commit(status);
		}catch(Exception e){ 
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return userId;
	}
	
	public String getMaxUserId(String role_code) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String user_id = null;;
		try{
			connection = dataSource.getConnection();
			String maxIdQry = "SELECT CONCAT(SUBSTRING(user_id, 1, LENGTH(user_id)-7),'_"+role_code+"_',LPAD(MAX(SUBSTRING(user_id, 9, LENGTH(user_id)))+1,3,'0') ) AS maxId "
					+ "FROM user WHERE user_id LIKE 'PMIS_%'";
			
			stmt = connection.prepareStatement(maxIdQry);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				user_id = rs.getString("maxId");
			}
		}catch(Exception e){ 			
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(connection, stmt, rs);
		}
		return user_id;
	}

	@Override
	public User getUser(User obj) throws Exception {
		User uobj = null;
		try {
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.personal_contact_number as CHAR) as personal_contact_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_type_fk,u.user_image,department_name,usr.user_name as reporting_to_name "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id = ? " ;
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and u.user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and u.reporting_to_id_srfk = ?";
				arrSize++;
			}
			
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			
			pValues[i++] = obj.getUser_id();			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			
			uobj = (User)jdbcTemplate.queryForObject( qry, pValues, new BeanPropertyRowMapper<User>(User.class));
			
			if(!StringUtils.isEmpty(uobj) && !StringUtils.isEmpty(uobj.getUser_id())) {
				String qryUserPermission = "select user_access_type_fk as user_access_type,access_value from user_access where user_id_fk = ? " ;
				
				List<User> permObjsList = jdbcTemplate.query(qryUserPermission, new Object[] {uobj.getUser_id()}, new BeanPropertyRowMapper<User>(User.class));	
				
				uobj.setUserPermissions(permObjsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return uobj;
	}

	@Override
	public boolean updateUser(User obj) throws Exception {
		boolean flag = false;
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {			  
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE user SET user_name=:user_name,designation=:designation,email_id=:email_id,user_type_fk= :user_type_fk,mobile_number=:mobile_number,personal_contact_number=:personal_contact_number,landline=:landline,extension=:extension,department_fk=:department_fk,reporting_to_id_srfk=:reporting_to_id_srfk,pmis_key_fk=:pmis_key_fk,user_role_name_fk=:user_role_name_fk,user_image=:user_image "
					+ "WHERE user_id = :user_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag && !StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
				obj.setUser_access_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_types()));
			}
			if(flag && !StringUtils.isEmpty(obj.getUser_access_values()) && obj.getUser_access_values().length > 0) {
				obj.setUser_access_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_values()));
			}
			
			if(flag && !StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
				
				String deleteQry = "DELETE from user_access where user_id_fk = :user_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);	
				
				String[] types = obj.getUser_access_types();
				String[] values = obj.getUser_access_values();
				
				String qryUserPermissions = "INSERT INTO user_access (user_id_fk,user_access_type_fk,access_value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions,
			            new BatchPreparedStatementSetter() {
			                 
						 @Override
			                public void setValues(PreparedStatement ps, int i) throws SQLException {
			                    ps.setString(1, obj.getUser_id());
			                    ps.setString(2, types.length > 0?types[i]:null);
			                    ps.setString(3, values.length > 0?values[i]:null);			                    
			                }
			                @Override  
			                public int getBatchSize() {
			                	int arraySize = 0;
			    				if(!StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
			    					obj.setUser_access_types(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_types()));
			    					if(arraySize < obj.getUser_access_types().length) {
			    						arraySize = obj.getUser_access_types().length;
			    					}
			    				}
			    				if(!StringUtils.isEmpty(obj.getUser_access_values()) && obj.getUser_access_values().length > 0) {
			    					obj.setUser_access_values(CommonMethods.replaceEmptyByNullInSringArray(obj.getUser_access_values()));
			    					if(arraySize < obj.getUser_access_values().length) {
			    						arraySize = obj.getUser_access_values().length;
			    					}
			    				}
			                    return arraySize;
			                }
			            });
				
			}
			
			transactionManager.commit(status);
		}catch(Exception e){ 
			e.printStackTrace();
			transactionManager.rollback(status);
			throw new Exception(e);
		}
		return flag;
	}

	@Override
	public boolean deleteUser(User obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int uploadUsers(List<User> usersList) throws Exception {
		int count = 0;
		boolean flag = false;
		int uploadedCount = 0;
		try {
			for (User user : usersList) {				
				
				String userId = checkUserIdExitence(user.getUser_id());
				String user_id = null;
				if(StringUtils.isEmpty(user.getUser_id())) {
					String user_role_code_qry = "select user_role_code from user_role where user_role_name = ?";
					String user_role_code = (String)jdbcTemplate.queryForObject( user_role_code_qry,new Object[] {user.getUser_role_name_fk()}, String.class);
					user_id = getMaxUserId(user_role_code);
				}else {
					user_id = user.getUser_id();
				}
				String department_qry = "select department from department where department_name = ?";
				String department_id = (String)jdbcTemplate.queryForObject( department_qry,new Object[] {user.getDepartment_name()}, String.class);					
				user.setDepartment_fk(department_id);
				user.setUser_id(user_id);
				
				List<User> reporting_to_list = getReportingToUserId(user.getReporting_to_id_srfk());
				if(!StringUtils.isEmpty(reporting_to_list) && reporting_to_list.size() == 1) {
					String reporting_to = reporting_to_list.get(0).getUser_id();
					user.setReporting_to_id_srfk(reporting_to);
					if(StringUtils.isEmpty(userId)) {
						NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
						String qry = "INSERT INTO user"
								+ "(user_id,user_name,password,designation,email_id,mobile_number,personal_contact_number,landline,extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks) "
								+ "VALUES "
								+ "(:user_id,:user_name,:password,:designation,:email_id,:mobile_number,:personal_contact_number,:landline,:extension,:department_fk,:reporting_to_id_srfk,:pmis_key_fk,:user_role_name_fk,:remarks)";		 
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);		 
						count = namedParamJdbcTemplate.update(qry, paramSource);
					}else {
						NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
						String qry = "UPDATE user set "
								+ "user_name = :user_name,designation =:designation,email_id =:email_id,mobile_number =:mobile_number,"
								+ "department_fk =:department_fk,reporting_to_id_srfk =:reporting_to_id_srfk,user_role_name_fk =:user_role_name_fk"
								+ " where user_id = :user_id";
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);		 
						count = namedParamJdbcTemplate.update(qry, paramSource);
					}
					uploadedCount++;
				}else {
					if(StringUtils.isEmpty(userId)) {
						NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
						String qry = "INSERT INTO user"
								+ "(user_id,user_name,password,designation,email_id,mobile_number,personal_contact_number,landline,extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks) "
								+ "VALUES "
								+ "(:user_id,:user_name,:password,:designation,:email_id,:mobile_number,:personal_contact_number,:landline,:extension,:department_fk,:reporting_to_id_srfk,:pmis_key_fk,:user_role_name_fk,:remarks)";		 
						BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);		 
						count = namedParamJdbcTemplate.update(qry, paramSource);
					}else {
						NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
						String qry = "UPDATE user set "
								+ "user_name = :user_name,designation =:designation,email_id =:email_id,mobile_number =:mobile_number,"
								+ "department_fk =:department_fk,reporting_to_id_srfk =:reporting_to_id_srfk,user_role_name_fk =:user_role_name_fk"
								+ " where user_id = :user_id";
							BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);		 
						count = namedParamJdbcTemplate.update(qry, paramSource);
					}
					uploadedCount++;
				}
				
				if(count > 0) {
					flag = true;
				}
				
				if(flag && !StringUtils.isEmpty(user.getUserPermissions()) && user.getUserPermissions().size() > 0) {
					String qryUserPermissions = "INSERT INTO user_access (user_id_fk,user_access_type_fk,access_value) VALUES  (?,?,?)";		
					
					int[] counts = jdbcTemplate.batchUpdate(qryUserPermissions,
				            new BatchPreparedStatementSetter() {				                 
				                @Override
				                public void setValues(PreparedStatement ps, int i) throws SQLException {
				                	String permission = user.getUserPermissions().get(i).getUser_access_type();
				                	String value = user.getUserPermissions().get(i).getAccess_value();
				                    ps.setString(1, user.getUser_id());
				                    ps.setString(2, !StringUtils.isEmpty(permission)?permission:null);
				                    ps.setString(3, !StringUtils.isEmpty(value)?value:null);			                    
				                }
				                @Override  
				                public int getBatchSize() {
				                    return user.getUserPermissions().size();
				                }
				            });
					
				}
			}
			
			count = usersList.size();
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return uploadedCount;
	}
	@Override
	public List<User> getReportingToUserId(String reporting_to_id_srfk) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_id from user  where designation = ?";
			int arrSize = 1;
			Object[] pValues = new Object[arrSize];
			pValues[0] = reporting_to_id_srfk;
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	private String checkUserIdExitence(String user_id) throws Exception {
		PreparedStatement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		String userId = null;
		try{
			con = dataSource.getConnection();
			String maxIdQry = "select user_id from user where user_id = ?";
			stmt = con.prepareStatement(maxIdQry);
			stmt.setString(1, user_id);
			rs = stmt.executeQuery();  
			if(rs.next()) {
				userId = rs.getString("user_id");
				
			}
		}catch(Exception e){ 		
			e.printStackTrace();
			throw new Exception(e);
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return userId;
	}

	@Override
	public List<User> getPmisKeys() throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select pmis_key as pmis_key_fk from pmis_key";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public String checkPMISKeyAvailability(User obj) throws Exception {
		String pmis_key = "NoKey";
		try {
			String qry = "select count(*) from pmis_key where pmis_key = ?";
			
			int count = jdbcTemplate.queryForObject( qry,new Object[] {obj.getPmis_key_fk()}, Integer.class);	
			
			if(count > 0) {
				pmis_key = "Available";
				qry = "select count(*) from user where pmis_key_fk = ?";				
				count = jdbcTemplate.queryForObject( qry,new Object[] {obj.getPmis_key_fk()}, Integer.class);	
				if(count > 0) {
					pmis_key = "Taken";
				}
			}
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return pmis_key;
	}

	@Override
	public List<User> getUserRolesFilter(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_role_name_fk from user u "
					+ "where user_role_name_fk is not null and user_role_name_fk <> '' " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and reporting_to_id_srfk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY user_role_name_fk";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserDepartmentsFilter(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select u.department_fk,department_name "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "where u.department_fk is not null and u.department_fk <> '' " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and u.user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and u.reporting_to_id_srfk = ?";
				arrSize++;
			}if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY u.department_fk";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserReportingToListFilter(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select usr.designation,u.reporting_to_id_srfk AS user_id,usr.user_name as reporting_to_name "
					+ "from user u "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.reporting_to_id_srfk is not null and u.reporting_to_id_srfk <> ''" ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and u.user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and u.reporting_to_id_srfk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY u.reporting_to_id_srfk ";
			qry = qry + " ORDER BY FIELD(usr.user_type_fk,'Management','HOD','DyHOD','Officers ( Jr./Sr. Scale )','Others','Training'), FIELD(usr.designation,'ED Civil','CPM I','CPM II','CPM III','CPM V','CE','GGM Civil','ED S&T','CSTE','GM Electrical','CEE Project I','CEE Project II','ED Finance & Planning','FA&CAO','GM GA&S','CPO','COM','GM Procurement','OSD','CVO','Demo-HOD-Elec','Demo-HOD-Engg','Demo-HOD-S&T',usr.designation)" ;

			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUsersExportList(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.personal_contact_number as CHAR) as personal_contact_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_image,department_name,usr.user_name as reporting_to_name,usr.designation as reporting_to_designation,u.user_type_fk  "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "LEFT OUTER JOIN user_type ut ON u.user_type_fk = ut.user_type "
					+ "where u.user_id is not null and u.user_id <> ?" ;
			int arrSize = 1;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and u.user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and u.reporting_to_id_srfk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ?";
				arrSize++;
			}
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			pValues[i++] = CommonConstants.USER;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}
			
			//qry = qry + " order by case when (u.user_id like '%Dummy%') then 0 else 1 end desc,case when (u.user_name like '%user%')  then 0 else 1 end desc, case when(u.pmis_key_fk like '%SGS%') then 0 else 1 end desc";
			qry = qry + "order by u.user_type_fk is null, field(u.user_type_fk,'Management','HOD','DyHOD','Officers ( Jr./Sr. Scale )','Others','Training')";
;			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));	
			
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserTypes() throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_type as user_type_fk from user_type";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserTypesFilter(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select u.user_type_fk from user u "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_type_fk is not null and u.user_type_fk <> '' " ;
			int arrSize = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				qry = qry + " and u.user_role_name_fk = ?";
				arrSize++;
			}	
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ?";
				arrSize++;
			}			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				qry = qry + " and u.reporting_to_id_srfk = ?";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ?";
				arrSize++;
			}
			qry = qry + " GROUP BY u.user_type_fk order by field (u.user_type_fk,'Management','HOD','DyHOD','Officers ( Jr./Sr. Scale )','Others','Training')";
			Object[] pValues = new Object[arrSize];
			
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_role_name_fk())) {
				pValues[i++] = obj.getUser_role_name_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getReporting_to_id_srfk())) {
				pValues[i++] = obj.getReporting_to_id_srfk();
			}
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getAllUsersList(User obj) throws Exception {
		List<User> objsList = new ArrayList<User>();
		try {
			String qry = "select distinct u.user_id,u.user_name,u.password,u.designation,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.personal_contact_number as CHAR) as personal_contact_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_type_fk,u.user_image,department_name,usr.designation as reporting_to_name,"
					+ "(select DATE_FORMAT(max(login_date_time),'%d-%m-%Y %h:%i %p') from user_login_details where user_id_fk = u.user_id ) as last_login,"
					+ "(select COUNT(*) from user_login_details where user_id_fk = u.user_id and DATE(login_date_time) >= DATE(NOW()) - INTERVAL 7 DAY) as last7DaysLogins,"
					+ "(select COUNT(*) from user_login_details where user_id_fk = u.user_id and DATE(login_date_time) >= DATE(NOW()) - INTERVAL 30 DAY) as last30DaysLogins "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id is not null" ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id())) {
				qry = qry + " and u.user_id not in (?) ";
				arrSize++;
			}
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{
			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					qry = qry + " and u.department_fk = ? ";
					arrSize++;
				}
				
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
					if(obj.getUser_type_fk().compareTo("HOD")==0)
					{
						qry = qry + " and u.user_type_fk in ('HOD','DyHOD','Officers ( Jr./Sr. Scale )') ";
					}
					if(obj.getUser_type_fk().compareTo("DyHOD")==0)
					{
						qry = qry + " and u.user_type_fk in ('DyHOD','Officers ( Jr./Sr. Scale )') ";
					}
					if(obj.getUser_type_fk().compareTo("Management")==0)
					{
						qry = qry + " and u.user_type_fk in ('Management','HOD') ";
					}
					if(obj.getUser_type_fk().compareTo("Officers ( Jr./Sr. Scale )")==0)
					{
						qry = qry + " and u.user_type_fk in ('Officers ( Jr./Sr. Scale )') ";
					}
					if(obj.getUser_type_fk().compareTo("Others")==0)
					{
						qry = qry + " and u.user_type_fk in ('Others') ";
					}
					if(obj.getUser_type_fk().compareTo("Training")==0)
					{
						qry = qry + " and u.user_type_fk in ('Training') ";
					}						
				}
			}
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id())) {
				pValues[i++] = obj.getUser_id();
			}	
			
			if(!StringUtils.isEmpty(obj) &&  !CommonConstants.ROLE_CODE_IT_ADMIN.equals(obj.getUser_role_code())) 
			{			
				if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
					pValues[i++] = obj.getDepartment_fk();
				}
			}
			
			qry = qry + " order by case when (u.user_id like '%Dummy%') then 0 else 1 end desc,case when (u.user_name like '%user%')  then 0 else 1 end desc, case when(u.pmis_key_fk like '%SGS%') then 0 else 1 end desc";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<User>(User.class));	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getResponsiblePersonUsers(User obj) throws Exception {
		List<User> objsList = new ArrayList<User>();
		try {
			String qry = "select distinct u.user_id,u.user_name,u.password,u.designation,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.personal_contact_number as CHAR) as personal_contact_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_type_fk,u.user_image,department_name,usr.designation as reporting_to_name,"
					+ "(select DATE_FORMAT(max(login_date_time),'%d-%m-%Y %h:%i %p') from user_login_details where user_id_fk = u.user_id ) as last_login,"
					+ "(select COUNT(*) from user_login_details where user_id_fk = u.user_id and DATE(login_date_time) >= DATE(NOW()) - INTERVAL 7 DAY) as last7DaysLogins,"
					+ "(select COUNT(*) from user_login_details where user_id_fk = u.user_id and DATE(login_date_time) >= DATE(NOW()) - INTERVAL 30 DAY) as last30DaysLogins "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id is not null" ;
			
			int arrSize = 0;
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id())) {
				qry = qry + " and u.user_id not in (?) ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				qry = qry + " and u.department_fk = ? ";
				arrSize++;
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				qry = qry + " and u.user_type_fk = ? ";
				arrSize++;
			}
			
			
			Object[] pValues = new Object[arrSize];
			int i = 0;
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_id())) {
				pValues[i++] = obj.getUser_id();
			}	
			
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getDepartment_fk())) {
				pValues[i++] = obj.getDepartment_fk();
			}
			
			if(!StringUtils.isEmpty(obj) && !StringUtils.isEmpty(obj.getUser_type_fk())) {
				pValues[i++] = obj.getUser_type_fk();
			}	
			
			
			qry = qry + " order by case when (u.user_id like '%Dummy%') then 0 else 1 end desc,case when (u.user_name like '%user%')  then 0 else 1 end desc, case when(u.pmis_key_fk like '%SGS%') then 0 else 1 end desc";
			
			objsList = jdbcTemplate.query( qry,pValues, new BeanPropertyRowMapper<User>(User.class));	
			
		}catch(Exception e){ 
			throw new Exception(e);
		}
		return objsList;
	}

}
