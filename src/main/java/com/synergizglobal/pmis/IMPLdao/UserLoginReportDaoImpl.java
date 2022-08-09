package com.synergizglobal.pmis.IMPLdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.synergizglobal.pmis.Idao.UserLoginReportDao;
import com.synergizglobal.pmis.model.User;
@Repository
public class UserLoginReportDaoImpl implements UserLoginReportDao{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public List<User> getDepartmentList(User dObj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "SELECT login_date_time,department_name,u2.designation as reporting_to_designation,u2.user_name as reporting_to_name, u.designation, u.user_name, count(*) as loginCount " + 
					"FROM user_login_details left join [user] u on user_id_fk = u.user_id  " + 
					"left join [user] u2 on u2.user_id = u.reporting_to_id_srfk " + 
					"left join department on u.department_fk = department " + 
					"where u.department_fk IS NOT NULL and u.department_fk <> '' and user_id_fk like 'PMIS_%' and login_date_time >= DATEADD(day, -7, GETDATE())  " + 
					"group by department_name ,login_date_time, u2.designation, u.designation,u2.user_name, u.user_name " + 
					"order by department_name, u2.designation, u.designation";
			
		    objsList = jdbcTemplate.query( qry, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public User getUserLoginDetails(User obj) throws Exception {
		
		return null;
	}

	@Override
	public List<User> getDesignationList(User dObj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "SELECT login_date_time,department_name,u2.designation as reporting_to_designation,u2.user_name as reporting_to_name, u.designation, u.user_name, count(*) as loginCount " + 
					"FROM user_login_details left join [user] u on user_id_fk = u.user_id  " + 
					"left join [user] u2 on u2.user_id = u.reporting_to_id_srfk " + 
					"left join department on u.department_fk = department " + 
					"where user_id_fk like 'PMIS_%' and login_date_time >= DATEADD(day, -7, GETDATE())  and department_name = ? " + 
					"group by u2.designation,login_date_time ,department_name,u.user_name,u2.user_name,u.designation " + 
					"order by department_name, u2.designation, u.designation";
			
		    objsList = jdbcTemplate.query( qry, new Object[] {dObj.getDepartment_name()}, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}

	@Override
	public List<User> getUserLoginList(User dObj) throws Exception {
		List<User> objsList = null;
		try {
			String qry = "SELECT login_date_time,department_name,u2.designation as reporting_to_designation,u2.user_name as reporting_to_name, u.designation, u.user_name, count(*) as loginCount " + 
					"FROM user_login_details left join [user] u on user_id_fk = u.user_id  " + 
					"left join [user] u2 on u2.user_id = u.reporting_to_id_srfk " + 
					"left join department on u.department_fk = department " + 
					"where user_id_fk like 'PMIS_%' and login_date_time >= DATEADD(day, -7, GETDATE())  and department_name = ? and u2.designation = ? " + 
					"group by department_name, u2.designation, u.designation,login_date_time,u.user_name,u2.user_name " + 
					"order by department_name, u2.designation, u.designation";
			
		    objsList = jdbcTemplate.query( qry, new Object[] {dObj.getDepartment_name(),dObj.getReporting_to_designation()}, new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception e){ 
			e.printStackTrace();
			throw new Exception(e);
		}
		return objsList;
	}
	
	
}
