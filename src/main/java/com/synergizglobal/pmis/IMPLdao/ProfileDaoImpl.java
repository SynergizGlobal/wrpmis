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
	public List<User> getUserProfile(String userId) throws Exception {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User userDetails = null;
		//HttpSession session=null;
		List<User> profileList = new ArrayList<User>();
		
		try{  
			con = dataSource.getConnection();
			
			String qry = "select user_id,user_name,password,designation,email_id,cast(mobile_number as CHAR) as mobile_number,cast(landline as CHAR) as landline,cast(extension as CHAR) as extension,department_fk,reporting_to_id_srfk,pmis_key_fk,user_role_name_fk,remarks "
					+ "from user "
					+ "where user_id = BINARY ?";
			
			
			stmt = con.prepareStatement(qry);
			stmt.setString(1,userId);
			
			rs = stmt.executeQuery();  
			while(rs.next()) {
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
				
				
				profileList.add(userDetails);
			}
			
			
		}catch(Exception e){ 
			throw new Exception(e.getMessage());
		}
		finally {
			DBConnectionHandler.closeJDBCResoucrs(con, stmt, rs);
		}
		return profileList;

	}

}
