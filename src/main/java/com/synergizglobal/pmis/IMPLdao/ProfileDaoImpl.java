package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.ProfileDao;
import com.synergizglobal.pmis.model.User;

@Repository
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;
	
	@Override
	public User getUserProfile(String userId) throws Exception {
		User userDetails = null;
		try{  
			
			/*String qry = "select user_id,user_name,password,designation,email_id,cast(mobile_number as CHAR) as mobile_number,cast(landline as CHAR) as landline,cast(extension as CHAR) as extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks "
					+ "from user "
					+ "where user_id = BINARY ?";*/
			String qry = "select u.user_id,u.user_name,u.password,u.designation,u.email_id,cast(u.mobile_number as CHAR) as mobile_number,cast(u.landline as CHAR) as landline,cast(u.extension as CHAR) as extension,u.department_fk,"
					+ "u.reporting_to_id_srfk,u.pmis_key_fk,u.user_role_name_fk,u.remarks,u.user_image,department_name,usr.user_name as reporting_to_name,usr.designation as reporting_to_designation "
					+ "from user u "
					+ "LEFT OUTER JOIN department d ON u.department_fk = d.department "
					+ "LEFT OUTER JOIN user usr ON u.reporting_to_id_srfk = usr.user_id "
					+ "where u.user_id = ? " ;
			
			
			userDetails = (User)jdbcTemplate.queryForObject( qry,  new Object[] {userId}, new BeanPropertyRowMapper<User>(User.class));	
			
			if(!StringUtils.isEmpty(userDetails) && !StringUtils.isEmpty(userDetails.getUser_id())) {
				List<User> objsList = null;
				String qryUserPermission = "select user_access_type_fk as user_access_type,access_value from user_access where user_id_fk = ? " ;
				
				objsList = jdbcTemplate.query(qryUserPermission, new Object[] {userId}, new BeanPropertyRowMapper<User>(User.class));	
				
				userDetails.setUserPermissions(objsList);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		return userDetails;

	}

}
