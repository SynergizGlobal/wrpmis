package com.synergizglobal.pmis.IMPLdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.synergizglobal.pmis.Idao.ProfileDao;
import com.synergizglobal.pmis.common.DBConnectionHandler;
import com.synergizglobal.pmis.model.User;

@Repository
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public User getUserProfile(String userId) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User userDetails = null;
		try{  
			con = dataSource.getConnection();
			
			String qry = "select user_id,user_name,password,designation,email_id,cast(mobile_number as CHAR) as mobile_number,cast(landline as CHAR) as landline,cast(extension as CHAR) as extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks "
					+ "from user "
					+ "where user_id = BINARY ?";
			
			
			stmt = con.prepareStatement(qry);
			stmt.setString(1,userId);
			
			rs = stmt.executeQuery();  
			if(rs.next()) {
				userDetails = new User();
				
				userDetails.setUser_id(rs.getString("user_id"));
				userDetails.setUser_name(rs.getString("user_name"));
				userDetails.setEmail_id(rs.getString("email_id"));
				userDetails.setDepartment_fk(rs.getString("department_fk"));
				userDetails.setDesignation(rs.getString("designation"));
				userDetails.setReporting_to_id_srfk(rs.getString("reporting_to_id_srfk"));
				userDetails.setUser_role_name_fk(rs.getString("user_role_name_fk"));
				userDetails.setMobile_number(rs.getString("mobile_number"));
				userDetails.setLandline(rs.getString("landline"));
				userDetails.setExtension(rs.getString("extension"));
				userDetails.setPmis_key_fk(rs.getString("pmis_key_fk"));
				userDetails.setRemarks(rs.getString("remarks"));
				userDetails.setUserPermissions(getUserPermissions(userDetails.getUser_id(),con));
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return userDetails;

	}

	private List<User> getUserPermissions(String user_id, Connection con) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User obj = null;
		List<User> userPermissions = new ArrayList<User>();
		try{  
			
			String qry = "select user_access_type_fk as user_access_type,access_value from user_access where user_id_fk = ? ";
			
			stmt = con.prepareStatement(qry);
			stmt.setString(1,user_id);
			
			rs = stmt.executeQuery();  
			while(rs.next()) {
				obj = new User();
				
				obj.setUser_access_type(rs.getString("user_access_type"));
				obj.setAccess_value(rs.getString("access_value"));
				
				userPermissions.add(obj);
			}
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(null, stmt, rs);
		}
		return userPermissions;
	}

}
