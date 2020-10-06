package com.synergizglobal.pmis.IMPLdao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.UserDao;
import com.synergizglobal.pmis.model.User;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public List<User> getUserRoles() throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_role_name from user_role";
			
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public List<User> getUserReportingToList() throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select user_id,designation,user_name from user u ";
			objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));	
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		return objsList;
	}	

	@Override
	public List<User> getUsersList(User obj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.email_id,ROUND(u.mobile_number) as mobile_number,ROUND(u.landline) as landline,u.extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,department_name,usr.user_name as reporting_to_name "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id is not null " ;
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
			
			objsList = jdbcTemplate.query( qry, pValues, new BeanPropertyRowMapper<User>(User.class));	
			
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return objsList;
	}

	@Override
	public boolean addUser(User obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "INSERT INTO user"
					+ "(user_id,user_name,password,designation,email_id,mobile_number,landline,extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks) "
					+ "VALUES "
					+ "(:user_id,:user_name,:password,:designation,:email_id,:mobile_number,:landline,:extension,:department_fk,:reporting_to_id_srfk,:pmis_key_fk,:user_role_name_fk,:remarks)";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag && !StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
				
				String[] types = obj.getUser_access_types();
				String[] values = obj.getUser_access_values();
				
				String qryFOBDetail = "INSERT INTO user_access (user_id_fk,user_access_type_fk,access_value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
			            new BatchPreparedStatementSetter() {
			                 
			                @Override
			                public void setValues(PreparedStatement ps, int i) throws SQLException {
			                    ps.setString(1, obj.getUser_id());
			                    ps.setString(2, types[i]);
			                    ps.setString(3, values[i]);			                    
			                }
			                @Override  
			                public int getBatchSize() {
			                    return obj.getUser_access_types().length;
			                }
			            });
				
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public User getUser(User obj) throws Exception {
		User uobj = null;
		try {
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.email_id,ROUND(u.mobile_number) as mobile_number,ROUND(u.landline) as landline,u.extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,department_name,usr.user_name as reporting_to_name "
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
				List<User> objsList = null;
				String qryUserPermission = "select user_access_type_fk as user_access_type,access_value from user_access where user_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryUserPermission, new Object[] {uobj.getUser_id()}, new BeanPropertyRowMapper<User>(User.class));	
				
				uobj.setUserPermissions(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return uobj;
	}

	@Override
	public boolean updateUser(User obj) throws Exception {
		boolean flag = false;
		try {
			NamedParameterJdbcTemplate namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);			 
			String qry = "UPDATE user SET user_name=:user_name,designation=:designation,email_id=:email_id,mobile_number=:mobile_number,landline=:landline,extension=:extension,department_fk=:department_fk,reporting_to_id_srfk=:reporting_to_id_srfk,pmis_key_fk=:pmis_key_fk,user_role_name_fk=:user_role_name_fk,remarks=:remarks "
					+ "WHERE user_id = :user_id";		 
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);		 
			int count = namedParamJdbcTemplate.update(qry, paramSource);			
			if(count > 0) {
				flag = true;
			}
			
			if(flag && !StringUtils.isEmpty(obj.getUser_access_types()) && obj.getUser_access_types().length > 0) {
				
				String deleteQry = "DELETE from user_access where user_id_fk = :user_id";		 
				paramSource = new BeanPropertySqlParameterSource(obj);		 
				count = namedParamJdbcTemplate.update(deleteQry, paramSource);	
				
				String[] types = obj.getUser_access_types();
				String[] values = obj.getUser_access_values();
				
				String qryFOBDetail = "INSERT INTO user_access (user_id_fk,user_access_type_fk,access_value) VALUES  (?,?,?)";		
				
				int[] counts = jdbcTemplate.batchUpdate(qryFOBDetail,
			            new BatchPreparedStatementSetter() {
			                 
			                @Override
			                public void setValues(PreparedStatement ps, int i) throws SQLException {
			                    ps.setString(1, obj.getUser_id());
			                    ps.setString(2, types[i]);
			                    ps.setString(3, values[i]);			                    
			                }
			                @Override  
			                public int getBatchSize() {
			                    return obj.getUser_access_types().length;
			                }
			            });
				
			}
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean deleteUser(User obj) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
